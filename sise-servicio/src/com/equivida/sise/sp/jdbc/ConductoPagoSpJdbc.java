package com.equivida.sise.sp.jdbc;

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

import com.equivida.sise.rs.RsConductoPago;
import com.equivida.sise.sp.ConductoPagoSp;
import com.equivida.sise.sp.ConductoPagoSpRemoto;

@Stateless(name = "ConductoPagoSp")
public class ConductoPagoSpJdbc implements ConductoPagoSp, ConductoPagoSpRemoto {

	// @Resource(mappedName = "java:siseDS")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsConductoPago> llamarConductoPagoSp(BigDecimal nro_perstamo)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RsConductoPago> listaRetorno = new ArrayList<RsConductoPago>();
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call sp_lista_conductos(?)}");
			pstmt.setBigDecimal(1, nro_perstamo);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			listaRetorno = new ArrayList<RsConductoPago>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {

						RsConductoPago respuesta = new RsConductoPago();
						respuesta.setConductoPago(rs.getString(1));
						respuesta.setNumerCuentaTarjeta(rs.getString(2));
						respuesta.setAnio(rs.getString(3));
						respuesta.setMes(rs.getString(4));
						respuesta.setImplimiteTarjeta(rs.getBigDecimal(5));
						respuesta.setTipoCuenta(rs.getString(6));
						respuesta.setCampo(rs.getInt(7));
						respuesta.setEstado(rs.getString(8));
						respuesta.setNumeroSeguridad(rs.getBigDecimal(9));
						listaRetorno.add(respuesta);

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
		return listaRetorno;
	}
}