package com.equivida.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.dto.portal.RsDatosPersonaJuridica;
import com.equivida.jdbc.DatosPersonaJuridicaSp;
import com.equivida.jdbc.DatosPersonaJuridicaSpRemoto;

@Stateless(name = "DatosPersonaJuridicaSp")
public class DatosPersonaJuridicaSpJdbc implements DatosPersonaJuridicaSp,
		DatosPersonaJuridicaSpRemoto {

	@Resource(mappedName = "java:equividaDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsDatosPersonaJuridica> llamarDatosPersonaJuridicaSp(
			String cod_tipo_identificacion, String identificacion)
			throws SQLException {
		PreparedStatement pstmt = null;
		List<RsDatosPersonaJuridica> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "SELECT A.SEC_PERSONA, A.SEC_PERSONA_JURIDICA, A.RAZON_SOCIAL "
					+ "FROM EQUIVIDA.PERSONA_JURIDICA A WHERE A.COD_TIPO_IDENTIFICACION = ? AND A.IDENTIFICACION = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, cod_tipo_identificacion);
			pstmt.setString(2, identificacion);

			rs = pstmt.executeQuery();

			respuesta = new ArrayList<RsDatosPersonaJuridica>();
			while (rs.next()) {
				RsDatosPersonaJuridica aux = new RsDatosPersonaJuridica();
				aux.setIdPersona(rs.getBigDecimal(1));
				aux.setIdPersonaJuridica(rs.getBigDecimal(2));
				aux.setRazonSocial(rs.getString(3));

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
					if(conn != null)
						conn.close();
				}
			}
		}

		return respuesta;

	}
}