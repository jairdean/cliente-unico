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

import com.equivida.crm.rs.ListaCondicionPeriodoGraciaRs;
import com.equivida.crm.sp.ListaCondicionPeriodoGraciaSp;
import com.equivida.crm.sp.ListaCondicionPeriodoGraciaSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCondicionPeriodoGraciaSp")
public class ListaCondicionPeriodoGraciaJdbc implements
		ListaCondicionPeriodoGraciaSp, ListaCondicionPeriodoGraciaSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaCondicionPeriodoGraciaRs> listaCondicionPeriodoGraciaSp()
			throws SQLException {

		System.out.println("SP: sp_lista_cond_pgracia");

		PreparedStatement pstmt = null;
		List<ListaCondicionPeriodoGraciaRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call sp_lista_cond_pgracia()}");
			pstmt.setQueryTimeout(600);
			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<ListaCondicionPeriodoGraciaRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						ListaCondicionPeriodoGraciaRs aux = new ListaCondicionPeriodoGraciaRs();

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
