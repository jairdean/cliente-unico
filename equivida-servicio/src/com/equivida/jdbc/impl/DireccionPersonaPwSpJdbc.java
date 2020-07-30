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

import com.equivida.dto.portal.RsDireccionPersonaPW;
import com.equivida.jdbc.DireccionPersonaPwSp;
import com.equivida.jdbc.DireccionPersonaPwSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "DireccionPersonaPwSp")
public class DireccionPersonaPwSpJdbc implements DireccionPersonaPwSp,
		DireccionPersonaPwSpRemoto {

	@Resource(mappedName = "java:equividaDS")
	private DataSource dataSource;
	Connection conn;

	public List<RsDireccionPersonaPW> consultaDireccionPersona(
			Integer secPersona) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsDireccionPersonaPW> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "SELECT B.SEC_DIRECCION, B.COD_TIPO_DIRECCION, B.PRINCIPAL, B.NUMERO, B.SECUNDARIA, B.BARRIO, B.REFERENCIA, B.SEC_CANTON, PROV.SEC_PROVINCIA, PROV.COD_PAIS "
					+ "FROM EQUIVIDA.DIRECCION B ,LATERAL (SELECT P.SEC_PROVINCIA, P.COD_PAIS FROM EQUIVIDA.PROVINCIA P WHERE P.SEC_PROVINCIA = "
					+ "(SELECT C.SEC_PROVINCIA FROM EQUIVIDA.CANTON C WHERE C.SEC_CANTON = B.SEC_CANTON)) AS PROV"
					+ " WHERE B.SEC_PERSONA = ? AND B.ESTADO = 'A'";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, secPersona);

			rs = pstmt.executeQuery();

			respuesta = new ArrayList<RsDireccionPersonaPW>();

			while (rs.next()) {
				RsDireccionPersonaPW aux = new RsDireccionPersonaPW();
				aux.setIdDireccion(rs.getBigDecimal(1));
				aux.setCodTipoDireccion(rs.getInt(2));
				aux.setCallePrincipal(rs.getString(3));
				aux.setNumero(rs.getString(4));
				aux.setCalleSecundaria(rs.getString(5));
				aux.setBarrio(rs.getString(6));
				aux.setReferencia(rs.getString(7));
				aux.setIdCanton(rs.getInt(8));
				aux.setIdProvincia(rs.getInt(9));
				aux.setIdPais(rs.getInt(10));

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
