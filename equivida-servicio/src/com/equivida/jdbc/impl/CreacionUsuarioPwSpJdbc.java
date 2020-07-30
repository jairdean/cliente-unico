/**
 * 
 */
package com.equivida.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.equivida.dto.portal.RsCreacionUsuario;
import com.equivida.jdbc.CreacionUsuarioPwSp;
import com.equivida.jdbc.CreacionUsuarioPwSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "CreacionUsuarioPwSp")
public class CreacionUsuarioPwSpJdbc implements CreacionUsuarioPwSp,
		CreacionUsuarioPwSpRemoto {

	@Resource(mappedName = "java:equividaWebDS")
	private DataSource dataSource;
	Connection conn;

	public RsCreacionUsuario creacionUsuario(Integer secPersona,
			String usuario, String password, Integer secPerfil,
			Integer secFigura, String modificar) throws SQLException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmtB = null;
		RsCreacionUsuario respuesta = null;
		Statement s;
		Integer rs = null;
		ResultSet rsB = null;
		String strEncriptPassword = null;
		String strContolTimestamp = null;
		String strSQL = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmtB = conn
					.prepareStatement("SELECT CURRENT_TIMESTAMP FROM SYSIBM.SYSDUMMY1");
			rsB = pstmtB.executeQuery();

			while (rsB.next()) {
				strContolTimestamp = rsB.getString(1);
			}

			strEncriptPassword = strContolTimestamp.substring(0, 4)
					+ strContolTimestamp.substring(5, 7)
					+ strContolTimestamp.substring(8, 10)
					+ strContolTimestamp.substring(11, 13)
					+ strContolTimestamp.substring(14, 16)
					+ strContolTimestamp.substring(17, 19)
					+ strContolTimestamp.substring(20,
							strContolTimestamp.length());

			s = conn.createStatement();

			strSQL = "SET ENCRYPTION PASSWORD = '" + strEncriptPassword + "'";

			s.execute(strSQL);

			String sql = "INSERT INTO EQUIVIDA.USUARIO_WEB(SEC_PERSONA_NATURAL, USUARIO, PASSWORD,"
					+ "SEC_PERFIL_USUARIO, SEC_FIGURA, MODIFICAR, TS_CONTROL, ACTIVO) "
					+ "VALUES("
					+ secPersona
					+ ",'"
					+ usuario
					+ "',ENCRYPT('"
					+ password
					+ "'),"
					+ secPerfil
					+ ","
					+ secFigura
					+ ",'"
					+ modificar
					+ "',TIMESTAMP('"
					+ strContolTimestamp
					+ "'),'A')";

			respuesta = new RsCreacionUsuario();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeUpdate();

			respuesta.setMensaje(rs.toString());
			respuesta.setMensajeError("");
			respuesta.setCodigoError(0);

		} catch (SQLException e) {
			respuesta.setMensajeError(e.getMessage());
			respuesta.setCodigoError(e.getErrorCode());
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
		return respuesta;
	}
}
