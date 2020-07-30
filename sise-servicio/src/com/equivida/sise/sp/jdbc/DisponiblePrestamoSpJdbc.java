package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.RsDisponiblePrestamo;
import com.equivida.sise.sp.DisponiblePrestamoSp;
import com.equivida.sise.sp.DisponiblePrestamoSpRemoto;

@Stateless(name = "DisponiblePrestamoSp")
public class DisponiblePrestamoSpJdbc implements DisponiblePrestamoSpRemoto,
		DisponiblePrestamoSp {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)
	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsDisponiblePrestamo> llamarDisponiblePrestamoSp(
			BigDecimal cod_suc, BigDecimal cod_ramo, BigDecimal nro_pol,
			Date fec_hasta, Integer sn_muestra, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException {
		PreparedStatement pstmt = null;
		List<RsDisponiblePrestamo> respuesta = null;
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
			pstmt = conn
					.prepareStatement("{call sp_ws_disponible_prestamo(?,?,?,?,?)}");

			pstmt.setBigDecimal(1, cod_suc);
			pstmt.setBigDecimal(2, cod_ramo);
			pstmt.setBigDecimal(3, nro_pol);
			pstmt.setTimestamp(4, new java.sql.Timestamp(fec_hasta.getTime()));
			pstmt.setInt(5, sn_muestra);
			/*
			 * pstmt.setString(6, campoin1); pstmt.setString(7, campoin2);
			 * pstmt.setString(8, campoin3); pstmt.setString(9, campoin4);
			 * pstmt.setString(10, campoin5);
			 */

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsDisponiblePrestamo>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsDisponiblePrestamo aux = new RsDisponiblePrestamo();

						aux.setNroCedula(rs.getBigDecimal(1));
						aux.setSaldoPrestamo(rs.getBigDecimal(2));
						aux.setCostoRescate(rs.getBigDecimal(3));
						aux.setValorRescate(rs.getBigDecimal(4));
						aux.setEDM(rs.getBigDecimal(5));
						aux.setPje_int(rs.getBigDecimal(6));
						aux.setImpDisponiblePrest(rs.getBigDecimal(7));
						aux.setCampo1(rs.getString(9));
						aux.setCampo2(rs.getString(10));
						aux.setCampo3(rs.getString(11));
						aux.setMsgError(rs.getString(8));

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