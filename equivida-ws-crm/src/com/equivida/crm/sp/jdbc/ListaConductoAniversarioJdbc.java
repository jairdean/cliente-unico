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

import com.equivida.crm.rs.ListaConductoAniversarioRs;
import com.equivida.crm.sp.ListaConductoAniversarioSp;
import com.equivida.crm.sp.ListaConductoAniversarioSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaConductoAniversarioSp")
public class ListaConductoAniversarioJdbc implements
		ListaConductoAniversarioSp, ListaConductoAniversarioSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaConductoAniversarioRs> listaConductoAniversarioSp(
			Integer meses, Integer codigoCobertura) throws SQLException {

		System.out.println("SP: sp_lista_cond_aniv");

		PreparedStatement pstmt = null;
		List<ListaConductoAniversarioRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call sp_lista_cond_aniv(?,?)}");

			pstmt.setInt(1, meses);
			pstmt.setInt(2, codigoCobertura);

			pstmt.setQueryTimeout(600);
			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<ListaConductoAniversarioRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						ListaConductoAniversarioRs aux = new ListaConductoAniversarioRs();

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
