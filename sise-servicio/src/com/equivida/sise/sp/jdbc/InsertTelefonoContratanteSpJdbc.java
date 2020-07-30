package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.equivida.sise.rs.InsertTelefonoContratante;
import com.equivida.sise.sp.InsertTelefonoContratanteSp;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "InsertTelefonoContratanteSp")
public class InsertTelefonoContratanteSpJdbc implements InsertTelefonoContratanteSp {

	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;

	// @Resource(mappedName = "java:crm-sql")
	// private DataSource dataSource;

	private Connection conn;

	@Override
	public InsertTelefonoContratante llamarInsertTelefonoPersonaSp(BigDecimal id_persona_wkf, BigDecimal cod_tipo_telef,
			String txt_telefono) throws SQLException {
		PreparedStatement pstmt = null;
		InsertTelefonoContratante respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call spi_mpersona_telef_wkf(?,?,?,?,?,?)}");
			
			System.out.println("id_persona_wkf:"+id_persona_wkf);
			System.out.println("cod_tipo_telef:"+cod_tipo_telef);
			System.out.println("txt_telefono:"+txt_telefono);

			// max rows de uno, si no se pone esta linea toma mucho tiempo la
			// llamada
			pstmt.setMaxRows(1);

			pstmt.setBigDecimal(1, id_persona_wkf);
			pstmt.setBigDecimal(2, cod_tipo_telef);
			pstmt.setString(3, EncodingUtil.encodeCP850(txt_telefono));
			pstmt.setString(4, "");// message_wkf
			pstmt.setString(5, "");// cod_error
			pstmt.setString(6, "");// id_mper_telef_cod_out

			respuesta = new InsertTelefonoContratante();

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						respuesta.setMessage_wkf(rs.getString(1));
						respuesta.setCod_error(rs.getString(2));
						respuesta.setId_mper_telef_cod_out(rs.getBigDecimal(3));
						break;
					}
					rs.close();
				} else {
					count = pstmt.getUpdateCount();
				} // end if-else tieneResulset
				if (count >= 0) {
					// procesa de acuerdo al contador de actualización, lo
					// ignoramos...
				}
				tieneResulset = pstmt.getMoreResults();

			} // end while count

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getErrorCode() + e.getMessage());
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