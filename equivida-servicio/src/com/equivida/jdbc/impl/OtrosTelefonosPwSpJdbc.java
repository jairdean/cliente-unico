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

import com.equivida.dto.portal.RsTelefonoPW;
import com.equivida.jdbc.OtrosTelefonosPwSp;
import com.equivida.jdbc.OtrosTelefonosPwSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "OtrosTelefonosPwSp")
public class OtrosTelefonosPwSpJdbc implements OtrosTelefonosPwSp,
		OtrosTelefonosPwSpRemoto {

	@Resource(mappedName = "java:equividaDS")
	private DataSource dataSource;
	Connection conn;

	public List<RsTelefonoPW> consultaOtrosTelefonos(Integer secPersona)
			throws SQLException {
		PreparedStatement pstmt = null;
		List<RsTelefonoPW> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "SELECT T.COD_TIPO_TELEFONO, T.NRO_TELEFONO FROM EQUIVIDA.TELEFONO T WHERE T.SEC_TELEFONO NOT IN (SELECT T.SEC_TELEFONO FROM "
					+ "EQUIVIDA.TELEFONO T, EQUIVIDA.DIRECCION_TELEFONO DT WHERE T.SEC_TELEFONO = DT.SEC_TELEFONO"
					+ " AND T.SEC_PERSONA = ?) AND T.SEC_PERSONA = ? AND T.ESTADO = 'A'";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, secPersona);
			pstmt.setInt(2, secPersona);

			rs = pstmt.executeQuery();

			respuesta = new ArrayList<RsTelefonoPW>();

			while (rs.next()) {
				RsTelefonoPW aux = new RsTelefonoPW();
				aux.setCodTipoTelefono(rs.getInt(1));
				aux.setNroTelefono(rs.getString(2));

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
