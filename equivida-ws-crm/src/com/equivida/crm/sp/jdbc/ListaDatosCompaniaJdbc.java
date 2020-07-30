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

import com.equivida.crm.rs.ListaDatosCompaniaRs;
import com.equivida.crm.sp.ListaDatosCompaniaSp;
import com.equivida.crm.sp.ListaDatosCompaniaSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaDatosCompaniaSp")
public class ListaDatosCompaniaJdbc implements ListaDatosCompaniaSp,
		ListaDatosCompaniaSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaDatosCompaniaRs> listaDatosCompaniaSp(Integer idPersona)
			throws SQLException {

		System.out.println("SP: sp_lista_datos_compania");

		PreparedStatement pstmt = null;
		List<ListaDatosCompaniaRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call sp_lista_datos_compania(?)}");

			pstmt.setInt(1, idPersona);
			pstmt.setQueryTimeout(600);
			rs = pstmt.executeQuery();
			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<ListaDatosCompaniaRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						ListaDatosCompaniaRs aux = new ListaDatosCompaniaRs();

						aux.setTipoDocumento(rs.getString(1));
						aux.setNumeroDocumento(rs.getString(2));
						aux.setSecuencialEmpresa(rs.getInt(3));
						aux.setRazonSocial(rs.getString(4));
						aux.setRazonComercial(rs.getString(5));
						aux.setSegmento(rs.getString(6));
						aux.setPrimaPromedioAnual(rs.getBigDecimal(7));
						aux.setSiniestralidadAcumulada(rs.getBigDecimal(8));
						aux.setEstadoCobranza(rs.getString(9));
						aux.setTipoCliente(rs.getString(10));
						aux.setTipoBroker(rs.getString(11));
						aux.setNumeroPolizas(rs.getInt(12));

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
