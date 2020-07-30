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

import com.equivida.dto.portal.RsPersonaNaturalPW;
import com.equivida.jdbc.PersonaNaturalPwSp;
import com.equivida.jdbc.PersonaNaturalPwSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "PersonaNaturalPwSp")
public class PersonaNaturalPwSpJdbc implements PersonaNaturalPwSp,
		PersonaNaturalPwSpRemoto {

	@Resource(mappedName = "java:equividaDS")
	private DataSource dataSource;
	
	@Resource(mappedName = "java:equividaYageDS")
	private DataSource dataSourceYage;
	
	Connection conn;

	public RsPersonaNaturalPW consultaPersonaNatural(String codTipoId, String id)
			throws SQLException {
		PreparedStatement pstmt = null;
		RsPersonaNaturalPW respuesta = new RsPersonaNaturalPW();

		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "SELECT A.SEC_PERSONA, A.SEC_PERSONA_NATURAL, A.APELLIDO_PATERNO,"
					+ " A.APELLIDO_MATERNO, A.PRIMER_NOMBRE, A.SEGUNDO_NOMBRE, A.SEXO, A.FCH_NACIMIENTO "
					+ "FROM EQUIVIDA.PERSONA_NATURAL A WHERE A.COD_TIPO_IDENTIFICACION = ? AND A.IDENTIFICACION = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, codTipoId);
			pstmt.setString(2, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				respuesta.setIdPersona(rs.getInt(1));
				respuesta.setIdPersonaNatural(rs.getInt(2));
				respuesta.setApellidoPaterno(rs.getString(3));
				respuesta.setApellidoMaterno(rs.getString(4));
				respuesta.setPrimerNombre(rs.getString(5));
				respuesta.setSegundoNombre(rs.getString(6));
				respuesta.setSexo(rs.getString(7));
				respuesta.setFechaNacimiento(rs.getDate(8));

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

	@Override
	public RsPersonaNaturalPW consultarDatosPersona(String cedula)
			throws SQLException {
		PreparedStatement pstmt = null;
		RsPersonaNaturalPW respuesta = null;
		ResultSet rs = null;
		List<RsPersonaNaturalPW> arrRes = new ArrayList<RsPersonaNaturalPW>();

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSourceYage.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String sql = "SELECT A.PRIMER_NOMBRE, A.SEGUNDO_NOMBRE, A.APELLIDO_PATERNO, A.APELLIDO_MATERNO, CONCAT(T.COD_AREA,T.NRO_TELEFONO) as telefono, D.DIRECCION_ELECTRONICA"
					+ " FROM EQUIVIDA.PERSONA_NATURAL A LEFT JOIN EQUIVIDA.TELEFONO T ON A.SEC_PERSONA = T.SEC_PERSONA AND (T.COD_TIPO_TELEFONO IN(3, 4, 6)) "
					+ " LEFT JOIN EQUIVIDA.DIRECCION_ELECTRONICA D ON A.SEC_PERSONA = D.SEC_PERSONA WHERE A.IDENTIFICACION = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, cedula);

			rs = pstmt.executeQuery();
			respuesta = new RsPersonaNaturalPW();
			

			while (rs.next()) {
				respuesta.setCodigoResultado((short)0);
				if (rs.getString(1) == null) {
					respuesta.setPrimerNombre("");
				} else {
					respuesta.setPrimerNombre(rs.getString(1));
				}
				if (rs.getString(2) == null) {
					respuesta.setSegundoNombre("");
				} else {
					respuesta.setSegundoNombre(rs.getString(2));
				}
				if (rs.getString(3) == null) {
					respuesta.setApellidoPaterno("");
				} else {
					respuesta.setApellidoPaterno(rs.getString(3));
				}
				if (rs.getString(4) == null) {
					respuesta.setApellidoMaterno("");
				} else {
					respuesta.setApellidoMaterno(rs.getString(4));
				}
				if (rs.getString(5) == null) {
					respuesta.setCelular("");
				} else {
					respuesta.setCelular(rs.getString(5));
				}
				if (rs.getString(6) == null) {
					respuesta.setEmail("");
				} else {
					respuesta.setEmail(rs.getString(6));
				}

				arrRes.add(respuesta);
			}
			
			if(arrRes.size()==0){
				respuesta.setCodigoResultado((short)1);
				respuesta.setMensaje("NO EXISTE EL USUARIO");
				arrRes.add(respuesta);
			}

		} catch (SQLException e) {
			respuesta.setCodigoResultado((short)1);
			respuesta.setMensaje("ERROR SQL. "+e.getMessage());
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

		return arrRes.get(0);
	}
}
