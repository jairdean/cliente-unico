package com.equivida.sise.sp.jdbc;

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

import com.equivida.sise.rs.RsCumulosDePago;
import com.equivida.sise.sp.CumulosDePagoSp;

@Stateless(name = "CumulosDePagoSp")
public class CumulosDePagoSpJdbc implements CumulosDePagoSp {

	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;

	// @Resource(mappedName = "java:crm-sql")
	// private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsCumulosDePago> consultar(String numeroDocumento)
			throws SQLException {

		System.out.println("consultar cumulos..." + numeroDocumento);

		PreparedStatement pstmt = null;
		List<RsCumulosDePago> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call dbo.sp_ws_cumulos_VI(?)}");
			pstmt.setString(1, numeroDocumento);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsCumulosDePago>();
			while (count >= 0) {
				if (tieneResulset) {
					System.out.println("antes pstmt.getResultSet()");
					rs = pstmt.getResultSet();
					System.out.println("despues pstmt.getResultSet()");
					while (rs.next()) {

						System.out.println("3:" + rs.getString(3));
						System.out.println(rs.getString(5));
						System.out.println(rs.getString(7));
						System.out.println(rs.getString(8));

						RsCumulosDePago aux = new RsCumulosDePago();
						aux.setPoliza(rs.getString(3));
						aux.setEstado(rs.getString(5));
						aux.setMontoVida(rs.getBigDecimal(7));
						aux.setMontoMyda(rs.getBigDecimal(8));

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

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getErrorCode()
					+ e.getMessage());
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