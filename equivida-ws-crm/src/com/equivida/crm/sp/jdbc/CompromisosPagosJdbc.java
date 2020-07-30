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

import com.equivida.crm.rs.CompromisosPagosRs;
import com.equivida.crm.sp.CompromisosPagosSp;
import com.equivida.crm.sp.CompromisosPagosSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "CompromisosPagosSp")
public class CompromisosPagosJdbc implements CompromisosPagosSp,
		CompromisosPagosSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;
	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CompromisosPagosRs> compromisosPagosSp(String poliza,
			Integer sucursal, Integer ramo) throws SQLException {

		System.out.println("SP: sp_lista_pagos_x_pol");

		PreparedStatement pstmt = null;
		List<CompromisosPagosRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}

		try {
			pstmt = conn.prepareStatement("{call sp_lista_pagos_x_pol(?,?,?)}");

			pstmt.setString(1, poliza);
			pstmt.setInt(2, sucursal);
			pstmt.setInt(3, ramo);

			pstmt.setQueryTimeout(600);
			respuesta = new ArrayList<CompromisosPagosRs>();

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<CompromisosPagosRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						CompromisosPagosRs aux = new CompromisosPagosRs();

						aux.setPoliza(rs.getString(1));
						aux.setFechaPago(rs.getString(4));
						aux.setMonto(rs.getBigDecimal(5));

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
