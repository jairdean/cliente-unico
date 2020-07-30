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

import com.equivida.sise.rs.RsComisionNoPagada;
import com.equivida.sise.sp.ComisionNoPagadaSp;
import com.equivida.sise.sp.ComisionNoPagadaSpRemoto;

@Stateless(name = "ComisionNoPagadaSp")
public class ComisionNoPagadaSpJdbc implements ComisionNoPagadaSp,
		ComisionNoPagadaSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsComisionNoPagada> llamarComisionNoPagadaSp(
			Integer cod_tipo_agente, Integer cod_agente, String fec_hasta,
			String fec_desde, String campoin1, String campoin2,
			String campoin3, String campoin4, String campoin5)
			throws SQLException {
		PreparedStatement pstmt = null;
		List<RsComisionNoPagada> respuesta = null;
		ResultSet rs = null;
		try {
			Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
			// conn = DataSourceUtil.obtenerConexionSegunHorario(dataSource,
			// dataSourceBak);
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_ws_comis_no_pagada(?,?,?,?,?,?,?,?,?)}");
			pstmt.setInt(1, cod_tipo_agente);
			pstmt.setInt(2, cod_agente);
			pstmt.setString(3, fec_hasta);
			pstmt.setString(4, fec_desde);
			pstmt.setString(5, campoin1);
			pstmt.setString(6, campoin2);
			pstmt.setString(7, campoin3);
			pstmt.setString(8, campoin4);
			pstmt.setString(9, campoin5);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsComisionNoPagada>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsComisionNoPagada aux = new RsComisionNoPagada();
						aux.setPoliza(rs.getString(1));
						aux.setNumFactura(rs.getBigDecimal(2));
						aux.setAgenteDesc(rs.getString(3));
						aux.setFechaCobroPago(rs.getString(4));
						aux.setImpPrima(rs.getBigDecimal(5));
						aux.setPjeComision(rs.getBigDecimal(6));
						aux.setIdPv(rs.getInt(7));
						// aux.setImpPrimaTotal(rs.getBigDecimal(8)); Solicitud
						// 22732
						aux.setImpComisNormal(rs.getBigDecimal(8));
						aux.setDebCred(rs.getBigDecimal(9));
						aux.setImpComisExtra(rs.getBigDecimal(10));
						aux.setCampo1(rs.getString(11));
						aux.setCampo2(rs.getString(12));
						aux.setCampo3(rs.getString(13));

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