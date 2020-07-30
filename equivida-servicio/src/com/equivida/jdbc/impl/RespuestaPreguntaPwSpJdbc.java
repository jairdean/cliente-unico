/**
 * 
 */
package com.equivida.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.equivida.dto.portal.RsRespuestaPregunta;
import com.equivida.jdbc.RespuestaPreguntaPwSp;
import com.equivida.jdbc.RespuestaPreguntaPwSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "RespuestaPreguntaPwSp")
public class RespuestaPreguntaPwSpJdbc implements RespuestaPreguntaPwSp,
		RespuestaPreguntaPwSpRemoto {

	@Resource(mappedName = "java:equividaWebDS")
	private DataSource dataSource;
	Connection conn;

	public RsRespuestaPregunta respuestaPregunta(Integer secUsuario,
			Integer secPregunta, String estado) throws SQLException {
		PreparedStatement pstmt = null;
		RsRespuestaPregunta resp = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "SELECT RESPUESTA FROM EQUIVIDA.PREGUNTA_USUARIO PU WHERE PU.SEC_USUARIO = ? AND SEC_PREGUNTA = ? AND PU.ESTADO = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, secUsuario);
			pstmt.setInt(2, secPregunta);
			pstmt.setString(3, estado);

			rs = pstmt.executeQuery();

			resp = new RsRespuestaPregunta();

			while (rs.next()) {
				resp.setRespuesta(rs.getString(1));
			}

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
		return resp;
	}
}
