/**
 * 
 */
package com.equivida.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.equivida.dto.portal.RsPreguntaUsuario;
import com.equivida.jdbc.PreguntaUsuarioPwSp;
import com.equivida.jdbc.PreguntaUsuarioPwSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "PreguntaUsuarioPwSp")
public class PreguntaUsuarioPwSpJdbc implements PreguntaUsuarioPwSp,
		PreguntaUsuarioPwSpRemoto {

	@Resource(mappedName = "java:equividaWebDS")
	private DataSource dataSource;
	Connection conn;

	public RsPreguntaUsuario preguntaUsuario(Integer secPersona,
			Integer secPregunta, String respuesta) throws SQLException {
		PreparedStatement pstmt = null;
		RsPreguntaUsuario resp = null;
		Integer rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "INSERT INTO EQUIVIDA.PREGUNTA_USUARIO(SEC_USUARIO, SEC_PREGUNTA, RESPUESTA) VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, secPersona);
			pstmt.setInt(2, secPregunta);
			pstmt.setString(3, respuesta);

			resp = new RsPreguntaUsuario();
			rs = pstmt.executeUpdate();

			resp.setFilas(rs);
			resp.setMensajeError("");
			resp.setCodigoError(0);

		} catch (SQLException e) {
			resp.setMensajeError(e.getMessage());
			resp.setCodigoError(e.getErrorCode());
			System.err.println("SQLException: " + e.getErrorCode()
					+ e.getMessage());
		} finally {
			try {
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
