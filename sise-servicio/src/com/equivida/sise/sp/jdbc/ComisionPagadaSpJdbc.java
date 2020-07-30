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

import com.equivida.sise.rs.RsComisionPagada;
import com.equivida.sise.sp.ComisionPagadaSp;
import com.equivida.sise.sp.ComisionPagadaSpRemoto;

@Stateless(name = "ComisionPagadaSp")
public class ComisionPagadaSpJdbc implements ComisionPagadaSp,
		ComisionPagadaSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsComisionPagada> llamarComsionPagadaSp(Integer tipoAgente,
			Integer codAgente, String fechaDesde, String fechaHasta,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsComisionPagada> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
			// conn = DataSourceUtil.obtenerConexionSegunHorario(dataSource,
			// dataSourceBak);
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_ws_comis_pagada (?,?,?,?,?,?,?,?,?)}");
			pstmt.setInt(1, tipoAgente);
			pstmt.setInt(2, codAgente);
			pstmt.setString(3, fechaDesde);
			pstmt.setString(4, fechaHasta);
			pstmt.setString(5, campoin1);
			pstmt.setString(6, campoin2);
			pstmt.setString(7, campoin3);
			pstmt.setString(8, campoin4);
			pstmt.setString(9, campoin5);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsComisionPagada>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsComisionPagada aux = new RsComisionPagada();
						aux.setCodComprobante(rs.getBigDecimal(1));
						aux.setComprobante(rs.getString(2));
						aux.setNroComprobante(rs.getBigDecimal(3));
						aux.setValor(rs.getBigDecimal(4));
						aux.setFecha(rs.getString(5));
						aux.setNombreBanco(rs.getString(6));
						aux.setCuentaOCheque(rs.getString(7));
						aux.setCuentaCorrienta(rs.getString(8));
						aux.setCodigosucursal(rs.getInt(9));
						aux.setSucursal(rs.getString(10));
						aux.setMail(rs.getString(11));
						aux.setCampo1(rs.getString(12));
						aux.setCampo2(rs.getString(13));
						aux.setCampo3(rs.getString(14));

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