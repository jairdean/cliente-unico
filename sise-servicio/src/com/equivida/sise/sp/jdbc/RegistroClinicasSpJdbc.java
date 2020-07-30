package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.RsRegistroClinicas;
import com.equivida.sise.sp.RegistroClinicasSp;
import com.equivida.sise.sp.RegistroClinicasSpRemoto;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "RegistroClinicasSp")
public class RegistroClinicasSpJdbc implements RegistroClinicasSp,
		RegistroClinicasSpRemoto {

	@Resource(mappedName = "java:sise4WEBDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RsRegistroClinicas llamarRegistroClinicasSp(
			BigDecimal i_cod_tipo_doc, String i_nro_doc,
			String i_txt_apellido1, BigDecimal i_cod_especialidad,
			String i_txt_direccion_principal, BigDecimal i_canton,
			BigDecimal i_provincia, BigDecimal i_ciudad, BigDecimal i_sector,
			String i_txt_direccion_mail, String i_txt_telefono,
			String i_tele_cel, BigDecimal i_cod_operadora, String i_contacto)
			throws SQLException {
		PreparedStatement pstmt = null;
		RsRegistroClinicas respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_insertclinica_den(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, i_cod_tipo_doc);
			pstmt.setString(2, i_nro_doc);
			pstmt.setString(3, EncodingUtil.encodeCP850(i_txt_apellido1));
			pstmt.setBigDecimal(4, i_cod_especialidad);
			pstmt.setString(5,
					EncodingUtil.encodeCP850(i_txt_direccion_principal));
			pstmt.setBigDecimal(6, i_canton);
			pstmt.setBigDecimal(7, i_provincia);
			pstmt.setBigDecimal(8, i_ciudad);
			pstmt.setBigDecimal(9, i_sector);
			pstmt.setString(10, EncodingUtil.encodeCP850(i_txt_direccion_mail));
			pstmt.setString(11, EncodingUtil.encodeCP850(i_txt_telefono));
			pstmt.setString(12, i_tele_cel);
			pstmt.setBigDecimal(13, i_cod_operadora);
			pstmt.setString(14, i_contacto);

			pstmt.setQueryTimeout(420);
			rs = pstmt.executeQuery();

			respuesta = new RsRegistroClinicas();
			System.out.println("in  JDBC " + rs);

			while (rs.next()) {
				respuesta.setGrabo(rs.getString(1));
				respuesta.setMessage(rs.getString(2));
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