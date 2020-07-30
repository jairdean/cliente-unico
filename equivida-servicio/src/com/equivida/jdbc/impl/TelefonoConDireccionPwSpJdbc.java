/**
 * 
 */
package com.equivida.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.equivida.dto.portal.RsTelefonoDireccionPW;
import com.equivida.jdbc.TelefonoConDireccionPwSp;
import com.equivida.jdbc.TelefonoConDireccionPwSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "TelefonoConDireccionPwSp")
public class TelefonoConDireccionPwSpJdbc implements TelefonoConDireccionPwSp,
		TelefonoConDireccionPwSpRemoto {

	@Resource(mappedName = "java:equividaDS")
	private DataSource dataSource;
	Connection conn;

	public List<RsTelefonoDireccionPW> consultaTelefonoDireccion(
			Integer secPersona) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsTelefonoDireccionPW> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "SELECT T.NRO_TELEFONO, T.EXT_TELEFONO, DF.SEC_DIRECCION FROM EQUIVIDA.TELEFONO T, EQUIVIDA.DIRECCION_TELEFONO DF "
					+ "WHERE T.SEC_TELEFONO = DF.SEC_TELEFONO AND T.SEC_PERSONA = ? AND T.ESTADO = 'A'";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, secPersona);

			rs = pstmt.executeQuery();

			respuesta = new ArrayList<RsTelefonoDireccionPW>();

			while (rs.next()) {
				RsTelefonoDireccionPW aux = new RsTelefonoDireccionPW();
				aux.setNroTelefono(rs.getString(1));
				aux.setExtTelefono(rs.getString(2));
				aux.setIdDireccion(rs.getInt(3));

				respuesta.add(aux);
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
		return respuesta;
	}
}
