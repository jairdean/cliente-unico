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

import com.equivida.crm.rs.ListaAseguradoCuponPendienteRs;
import com.equivida.crm.sp.ListaAseguradoCuponPendienteSp;
import com.equivida.crm.sp.ListaAseguradoCuponPendienteSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaAseguradoCuponPendienteSp")
public class ListaAseguradoCuponPendienteJdbc implements
		ListaAseguradoCuponPendienteSp, ListaAseguradoCuponPendienteSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaAseguradoCuponPendienteRs> listaAseguradoCuponPendienteSp()
			throws SQLException {

		System.out.println("SP: sp_lista_aseg_cupon_pend");

		PreparedStatement pstmt = null;
		List<ListaAseguradoCuponPendienteRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {

			pstmt = conn.prepareStatement("{call sp_lista_aseg_cupon_pend()}");
			pstmt.setQueryTimeout(600);
			respuesta = new ArrayList<ListaAseguradoCuponPendienteRs>();

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						ListaAseguradoCuponPendienteRs aux = new ListaAseguradoCuponPendienteRs();
						aux.setSecuencial_persona(rs.getInt(1));
						aux.setTipo(rs.getInt(2));
						aux.setNumeroDocumento(rs.getString(3));
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
