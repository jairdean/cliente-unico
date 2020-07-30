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

import com.equivida.crm.rs.ListaPolizasRs;
import com.equivida.crm.sp.ListaPolizasSp;
import com.equivida.crm.sp.ListaPolizasSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaPolizasSp")
public class ListaPolizasJdbc implements ListaPolizasSp, ListaPolizasSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaPolizasRs> listaPolizasSp(Integer idPersona, Integer tipo,
			String numeroDocumento) throws SQLException {

		System.out.println("SP: sp_lista_pol_aseg");
		//System.out.println("idPersona:" + idPersona);
		//System.out.println("tipo:" + tipo);
		//System.out.println("numeroDocumento:" + numeroDocumento);

		PreparedStatement pstmt = null;
		List<ListaPolizasRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String prmSql = "EXEC sp_lista_pol_aseg ";
			if (idPersona != null) {
				prmSql = prmSql + "@id_persona = " + idPersona + ",";
			}
			if (tipo != null && numeroDocumento != null) {
				prmSql = prmSql + "@tipo_doc = " + tipo + ", @nro_doc = '"
						+ numeroDocumento + "'";
			} else {
				prmSql = prmSql.substring(0, prmSql.length() - 1);
			}

			System.out.println("prmSql:" + prmSql);

			pstmt = conn.prepareStatement(prmSql);

			pstmt.setQueryTimeout(600);
			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<ListaPolizasRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						ListaPolizasRs aux = new ListaPolizasRs();

						aux.setNumero_Poliza(rs.getBigDecimal(1));
						aux.setPorc_Extraprima(rs.getInt(2));
						aux.setContratante(rs.getString(3));
						aux.setSucursal(rs.getInt(4));
						aux.setRamo(rs.getInt(5));
						aux.setEstado_entrega_poliza(rs.getString(6));
						aux.setEstado_poliza(rs.getString(7));
						aux.setPrima_mensual(rs.getBigDecimal(8));
						aux.setPeriodicidad_pago(rs.getString(9));
						aux.setFecha_inicio_vigencia(rs.getString(10));
						aux.setAntiguedad_poliza(rs.getInt(11));
						// aux.setEstado_Cuenta(rs.getInt(12));
						aux.setTiene_deuda(rs.getString(12));
						aux.setMonto_Deuda(rs.getInt(13));
						aux.setForma_Pago(rs.getString(14));
						aux.setFecha_A_Caducar(rs.getString(15));
						aux.setPeriodo_Gracia(rs.getString(16));
						aux.setTipo_Conducto(rs.getString(17));
						aux.setNro_Conducto(rs.getString(18));
						aux.setEstado_Conducto(rs.getString(19));
						aux.setInstitucion_Conducto(rs.getString(20));
						aux.setFecha_Vigencia_Conducto(rs.getInt(21));
						aux.setNro_Cupones_Pendientes(rs.getInt(22));

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
