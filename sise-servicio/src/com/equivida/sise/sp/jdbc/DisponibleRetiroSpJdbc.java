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

import com.equivida.sise.rs.RsDisponibleRetiro;
import com.equivida.sise.sp.DisponibleRetiroSp;
import com.equivida.sise.sp.DisponibleRetiroSpRemoto;

@Stateless(name = "DisponibleRetiroSp")
public class DisponibleRetiroSpJdbc implements DisponibleRetiroSpRemoto,
		DisponibleRetiroSp {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsDisponibleRetiro> llamarDisponibleRetiroSp(
			BigDecimal cod_sucursal, BigDecimal cod_ramo,
			BigDecimal nro_poliza, Date fec_hasta, Integer sn_muestra,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsDisponibleRetiro> respuesta = null;
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
					.prepareStatement("{call sp_ws_disponible_retiro(?,?,?,?,?)}");

			pstmt.setBigDecimal(1, cod_sucursal);
			pstmt.setBigDecimal(2, cod_ramo);
			pstmt.setBigDecimal(3, nro_poliza);
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

			respuesta = new ArrayList<RsDisponibleRetiro>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsDisponibleRetiro aux = new RsDisponibleRetiro();

						aux.setSaldoCuenta(rs.getBigDecimal(1));
						aux.setSaldoPrestamo(rs.getBigDecimal(2));
						aux.setCostoRescate(rs.getBigDecimal(3));
						aux.setDerechoRetiro(rs.getBigDecimal(4));
						aux.setValorRescate(rs.getBigDecimal(5));
						aux.setValorMaximoRetiro(rs.getBigDecimal(6));
						aux.setMensaje(rs.getString(7));
						aux.setCampo1(rs.getString(8));
						aux.setCampo2(rs.getString(9));
						aux.setCampo3(rs.getString(10));

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