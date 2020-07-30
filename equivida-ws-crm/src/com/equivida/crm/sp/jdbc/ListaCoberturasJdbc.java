/**
 * 
 */
package com.equivida.crm.sp.jdbc;

import java.math.BigDecimal;
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

import com.equivida.crm.rs.ListaCoberturasRs;
import com.equivida.crm.sp.ListaCoberturasSp;
import com.equivida.crm.sp.ListaCoberturasSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCoberturasSp")
public class ListaCoberturasJdbc implements ListaCoberturasSp,
		ListaCoberturasSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;
	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaCoberturasRs> listaCoberturasSp(Integer codigoSucursal,
			Integer ramo, BigDecimal numeroPoliza) throws SQLException {

		System.out.println("SP: sp_lista_coberturas");

		PreparedStatement pstmt = null;
		List<ListaCoberturasRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call sp_lista_coberturas(?,?,?)}");

			pstmt.setBigDecimal(3, numeroPoliza);
			pstmt.setInt(1, codigoSucursal);
			pstmt.setInt(2, ramo);
			pstmt.setQueryTimeout(600);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<ListaCoberturasRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {

						ListaCoberturasRs aux = new ListaCoberturasRs();

						// aux.setSucursal(rs.getInt(1));
						// aux.setRamo(rs.getInt(2));
						aux.setNumero_poliza(rs.getBigDecimal(1));
						aux.setCobertura(rs.getString(2));
						aux.setMonto(rs.getBigDecimal(3));
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
