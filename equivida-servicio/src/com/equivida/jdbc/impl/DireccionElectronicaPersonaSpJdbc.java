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

import com.equivida.dto.portal.RsDireccionElectronicaPersona;
import com.equivida.jdbc.DireccionElectronicaPersonaSp;
import com.equivida.jdbc.DireccionElectronicaPersonaSpRemoto;

@Stateless(name = "DireccionElectronicaPersonaSp")
public class DireccionElectronicaPersonaSpJdbc implements
		DireccionElectronicaPersonaSp, DireccionElectronicaPersonaSpRemoto {

	@Resource(mappedName = "java:equividaDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsDireccionElectronicaPersona> llamarDireccionElectronicaPersonaSp(
			String id_persona) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsDireccionElectronicaPersona> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "SELECT F.SEC_DIRECCION_ELECTRONICA, F.COD_TIPO_DIRECCION_ELECTRONICA, G.TIPO_DIRECCION_ELECTRONICA, F.DIRECCION_ELECTRONICA "
					+ "FROM EQUIVIDA.DIRECCION_ELECTRONICA F, EQUIVIDA.TIPO_DIRECCION_ELECTRONICA G"
					+ " WHERE F.SEC_PERSONA = ?"
					+ " AND F.ESTADO = 'A' AND F.COD_TIPO_DIRECCION_ELECTRONICA = G.COD_TIPO_DIRECCION_ELECTRONICA";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id_persona);

			rs = pstmt.executeQuery();

			respuesta = new ArrayList<RsDireccionElectronicaPersona>();
			while (rs.next()) {
				RsDireccionElectronicaPersona aux = new RsDireccionElectronicaPersona();
				aux.setIdDireccionElec(rs.getBigDecimal(1));
				aux.setCodTipoDireccionElec(rs.getInt(2));
				aux.setTipoDireccionElec(rs.getString(3));
				aux.setDireccionElec(rs.getString(4));

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
					if (conn != null)
						conn.close();
				}
			}
		}

		return respuesta;

	}
}