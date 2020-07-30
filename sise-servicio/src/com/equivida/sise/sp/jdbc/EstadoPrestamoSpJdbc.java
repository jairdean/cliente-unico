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

import com.equivida.sise.rs.RsEstadoPrestamo;
import com.equivida.sise.sp.EstadoPrestamoSp;
import com.equivida.sise.sp.EstadoPrestamoSpRemoto;

@Stateless(name = "EstadoPrestamoSp")
public class EstadoPrestamoSpJdbc implements EstadoPrestamoSp,
		EstadoPrestamoSpRemoto {

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)
	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<RsEstadoPrestamo> llamarEstadoPrestamoSp(
			Integer cod_tipo_doc, String nro_doc, Integer sn_activos,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException {
		PreparedStatement pstmt = null;
		ArrayList<RsEstadoPrestamo> respuesta = null;
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
					.prepareStatement("EXEC sp_ws_estado_prestamo @cod_tipo_doc = "
							+ cod_tipo_doc
							+ ", @nro_doc = '"
							+ nro_doc
							+ "', @sn_activos = " + sn_activos);

			/*
			 * pstmt.setString(8, campoin1); pstmt.setString(7, campoin2);
			 * pstmt.setString(9, campoin3); pstmt.setString(9, campoin4);
			 * pstmt.setString(10, campoin5);
			 */

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsEstadoPrestamo>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsEstadoPrestamo aux = new RsEstadoPrestamo();
						aux.setCodigoSucursal(rs.getBigDecimal(1));
						aux.setCodigoRamo(rs.getBigDecimal(2));
						aux.setNumeroPoliza(rs.getBigDecimal(3));
						aux.setNumeroPrestamo(rs.getInt(4));
						aux.setFechaConcesion(rs.getString(5));
						aux.setNumeroTasa(rs.getBigDecimal(6));
						aux.setImpCapital(rs.getBigDecimal(7));
						aux.setImpInteres(rs.getBigDecimal(8));
						aux.setDevoluciones(rs.getBigDecimal(9));
						aux.setImpSaldo(rs.getBigDecimal(10));
						aux.setSnActivo(rs.getInt(11));
						aux.setDescCond(rs.getString(12));
						aux.setTarjeta(rs.getString(13));
						aux.setCampoOut1(rs.getString(14));
						aux.setCampoOut2(rs.getString(15));
						aux.setCampoOut3(rs.getString(16));

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