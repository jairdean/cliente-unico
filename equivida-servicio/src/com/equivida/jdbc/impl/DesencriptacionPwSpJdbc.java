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

import com.equivida.dto.portal.RsDesencriptacion;
import com.equivida.jdbc.DesencriptacionPwSp;
import com.equivida.jdbc.DesencriptacionPwSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "DesencriptacionPwSp")
public class DesencriptacionPwSpJdbc implements DesencriptacionPwSp,
		DesencriptacionPwSpRemoto {

	@Resource(mappedName = "java:equividaWebDS")
	private DataSource dataSource;
	Connection conn;

	public RsDesencriptacion desencriptacion(String usuario)
			throws SQLException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmtB = null;
		RsDesencriptacion resp = null;
		ResultSet rs = null;
		ResultSet rsB = null;
		Statement s;
		String strContolTimestamp = null;
		String strEncriptPassword = null;
		String strSQL = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {

			pstmtB = conn
					.prepareStatement("SELECT TS_CONTROL FROM EQUIVIDA.USUARIO_WEB WHERE USUARIO = ?");

			pstmtB.setString(1, usuario);

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

			String sql = "SELECT DECRYPT_CHAR(PASSWORD) FROM EQUIVIDA.USUARIO_WEB WHERE USUARIO = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, usuario);

			rs = pstmt.executeQuery();

			resp = new RsDesencriptacion();

			while (rs.next()) {
				resp.setPassword(rs.getString(1));
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
