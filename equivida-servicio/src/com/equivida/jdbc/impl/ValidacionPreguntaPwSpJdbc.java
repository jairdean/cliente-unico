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

import com.equivida.dto.portal.RsValidacionPregunta;
import com.equivida.jdbc.ValidacionPreguntaPwSp;
import com.equivida.jdbc.ValidacionPreguntaPwSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ValidacionPreguntaPwSp")
public class ValidacionPreguntaPwSpJdbc implements ValidacionPreguntaPwSp,
		ValidacionPreguntaPwSpRemoto {

	@Resource(mappedName = "java:equividaWebDS")
	private DataSource dataSource;
	Connection conn;

	public RsValidacionPregunta validacionPregunta(Integer secUsuario,
			String estado) throws SQLException {
		PreparedStatement pstmt = null;
		RsValidacionPregunta resp = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "SELECT PV.PREGUNTA FROM EQUIVIDA.PREGUNTA_VALIDACION PV, EQUIVIDA.PREGUNTA_USUARIO PU"
					+ " WHERE PV.SEC_PREGUNTA = PU.SEC_PREGUNTA AND PU.SEC_USUARIO = ? AND PU.ESTADO = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, secUsuario);
			pstmt.setString(2, estado);

			resp = new RsValidacionPregunta();
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resp.setPregunta(rs.getString(1));
				resp.setMensaje("");
			}

		} catch (SQLException e) {
			resp.setMensaje(e.getMessage());
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
