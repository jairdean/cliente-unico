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

import com.equivida.sise.rs.RsInformeSiniestros;
import com.equivida.sise.sp.InformeSiniestrosSp;
import com.equivida.sise.sp.InformeSiniestrosSpRemoto;

@Stateless(name = "InformeSiniestrosSp")
public class InformeSiniestrosSpJdbc implements InformeSiniestrosSp,
		InformeSiniestrosSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsInformeSiniestros> llamarInformeSiniestrosSp(Integer cod_suc,
			Integer cod_ramo, Integer nro_stro, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException {
		PreparedStatement pstmt = null;
		List<RsInformeSiniestros> respuesta = null;
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
			pstmt = conn.prepareStatement("EXEC sp_ws_stro  @cod_suc = "
					+ cod_suc + ", @cod_ramo = " + cod_ramo + ", @nro_stro ="
					+ nro_stro + "," + "@campoin1='" + campoin1
					+ "', @campoin2='" + campoin2 + "', @campoin3='" + campoin3
					+ "', @campoin4='" + campoin4 + "', @campoin5='" + campoin5
					+ "'");

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsInformeSiniestros>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsInformeSiniestros aux = new RsInformeSiniestros();

						aux.setCod_suc(rs.getBigDecimal(1));
						aux.setCod_ramo(rs.getBigDecimal(2));
						aux.setNro_pol(rs.getBigDecimal(3));
						aux.setEjercicio(rs.getBigDecimal(4));
						aux.setNro_stro(rs.getBigDecimal(5));
						aux.setFecha_stro(rs.getString(6));
						aux.setFecha_nodificacion(rs.getString(7));
						aux.setCobertura(rs.getString(8));
						aux.setTipo_estimacion(rs.getString(9));
						aux.setValor_estimado(rs.getBigDecimal(10));
						aux.setValor_pagado(rs.getBigDecimal(11));
						aux.setEstado(rs.getString(12));
						aux.setAsegurado(rs.getString(13));
						aux.setCampo1(rs.getString(14));
						aux.setCampo2(rs.getString(15));
						aux.setCampo3(rs.getString(16));

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