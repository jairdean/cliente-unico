/**
 * 
 */
package com.equivida.crm.sp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.crm.rs.ListaCuponesPendientesRs;
import com.equivida.crm.sp.ListaCuponesPendientesSp;
import com.equivida.crm.sp.ListaCuponesPendientesSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCuponesPendientesSp")
public class ListaCuponesPendientesJdbc implements ListaCuponesPendientesSp,
		ListaCuponesPendientesSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaCuponesPendientesRs> listaCuponesPendientesSp(
			Integer idPersona, Integer tipo, String numeroDocumento)
			throws SQLException {

		System.out.println("SP: sp_lista_cupones_pend");

		PreparedStatement pstmt = null;
		List<ListaCuponesPendientesRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");

			conn = dataSource.getConnection();

			// conn.setAutoCommit(false);

			// conn.setTransactionIsolation(Connection.TRANSACTION_NONE);
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {

			String prmSql = "EXEC sp_lista_cupones_pend ";
			if (idPersona != null) {
				prmSql = prmSql + "@id_persona = " + idPersona + ",";
			}
			if (tipo != null && numeroDocumento != null) {
				prmSql = prmSql + "@tipo_doc = " + tipo + ", @nro_doc = '"
						+ numeroDocumento + "'";
			} else {
				prmSql = prmSql.substring(0, prmSql.length() - 1);
			}

			pstmt = conn.prepareStatement(prmSql);

			pstmt.setQueryTimeout(600);
			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<ListaCuponesPendientesRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						ListaCuponesPendientesRs aux = new ListaCuponesPendientesRs();

						aux.setSecuencial_persona(rs.getInt(1));
						aux.setNumero_poliza(rs.getBigDecimal(2));
						aux.setOrigen(rs.getString(3));
						aux.setNro_cupones_pendientes(rs.getInt(4));
						aux.setEstado_ult_cupon(rs.getString(5));
						aux.setDias_morosidad(rs.getInt(6));
						aux.setMonto(rs.getBigDecimal(7));
						aux.setPeriodo_gracia(rs.getString(8));
						aux.setInstitucion_conducto(rs.getString(9));
						aux.setNro_conducto(rs.getInt(10));
						aux.setFecha_vigencia_conducto(rs.getInt(11));
						respuesta.add(aux);
					}
					rs.close();
				} else {
					count = pstmt.getUpdateCount();
				}// end if-else tieneResulset
				if (count >= 0) {
					// procesa de acuerdo al contador de actualización, lo
					// ignoramos...
				}
				tieneResulset = pstmt.getMoreResults();

			}// end while count

		} finally {
			try {
				if (rs != null)
					rs.close();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} finally {
					conn.close();
				}
			}
		}
		return respuesta;
	}

}
