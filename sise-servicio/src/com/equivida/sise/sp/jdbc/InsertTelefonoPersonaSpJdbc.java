package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.InsertTelefonoPersona;
import com.equivida.sise.sp.InsertTelefonoPersonaSp;
import com.equivida.sise.sp.InsertTelefonoPersonaSpRemoto;

@Stateless(name = "InsertTelefonoPersonaSp")
public class InsertTelefonoPersonaSpJdbc implements
		InsertTelefonoPersonaSpRemoto, InsertTelefonoPersonaSp {

	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;

	// @Resource(mappedName = "java:crm-sql")
	// private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public InsertTelefonoPersona llamarInsertTelefonoPersonaSp(
			BigDecimal id_persona, BigDecimal cod_tipo_telef,
			String txt_telefono, String campoin1, String campoin2,
			String campoin3, String campoin4, String campoin5)
			throws SQLException {
		PreparedStatement pstmt = null;
		InsertTelefonoPersona respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_insert_mpersona_telef(?,?,?,?,?,?,?,?)}");

			// max rows de uno, si no se pone esta linea toma mucho tiempo la
			// llamada
			pstmt.setMaxRows(1);

			pstmt.setBigDecimal(1, id_persona);
			pstmt.setBigDecimal(2, cod_tipo_telef);
			pstmt.setString(3, txt_telefono);
			pstmt.setString(4, campoin1);
			pstmt.setString(5, campoin2);
			pstmt.setString(6, campoin3);
			pstmt.setString(7, campoin4);
			pstmt.setString(8, campoin5);

			respuesta = new InsertTelefonoPersona();

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						respuesta.setNumFilas(rs.getBigDecimal(1));
						respuesta.setCodError(rs.getString(2));
						respuesta.setMsgError(rs.getString(3));
						respuesta.setIdTelefono(rs.getBigDecimal(4));
						respuesta.setCampoOut1(rs.getString(5));
						respuesta.setCampoOut2(rs.getString(6));
						respuesta.setCampoOut3(rs.getString(7));
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