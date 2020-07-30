package com.equivida.sise.sp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.RsCuotasPrestamo;
import com.equivida.sise.sp.CuotasPrestamoSp;
import com.equivida.sise.sp.CuotasPrestamoSpRemoto;

@Stateless(name = "CuotasPrestamoSp")
public class CuotasPrestamoSpJdbc implements CuotasPrestamoSp,
		CuotasPrestamoSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<RsCuotasPrestamo> llamarCuotasPrestamoSp(
			Integer nro_perstamo, String campoin1, String campoin2,
			String campoin3, String campoin4, String campoin5)
			throws SQLException {
		PreparedStatement pstmt = null;
		ArrayList<RsCuotasPrestamo> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			// conn = DataSourceUtil.obtenerConexionSegunHorario(dataSource,
			// dataSourceBak);
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call sp_ws_cuotas_prestamo(?)}");
			pstmt.setInt(1, nro_perstamo);
			/*
			 * pstmt.setString(2, campoin1); pstmt.setString(3, campoin2);
			 * pstmt.setString(4, campoin3); pstmt.setString(5, campoin4);
			 * pstmt.setString(5, campoin5);
			 */

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsCuotasPrestamo>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsCuotasPrestamo aux = new RsCuotasPrestamo();

						aux.setNumeroCuota(rs.getBigDecimal(1));
						aux.setImpCapital(rs.getBigDecimal(2));
						aux.setImpInteres(rs.getBigDecimal(3));
						aux.setImpCuota(rs.getBigDecimal(4));
						aux.setEstado(rs.getString(5));
						aux.setFechaVencimiento(rs.getString(6));
						aux.setImpSaldo(rs.getBigDecimal(7));

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