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

import com.equivida.sise.rs.RsRegistroMedicos;
import com.equivida.sise.sp.RegistroMedicosSp;
import com.equivida.sise.sp.RegistroMedicosSpRemoto;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "RegistroMedicosSp")
public class RegistroMedicosSpJdbc implements RegistroMedicosSp,
		RegistroMedicosSpRemoto {

	@Resource(mappedName = "java:sise4WEBDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RsRegistroMedicos llamarRegistroMedicosSp(
			BigDecimal i_imp_tipoid_prestador, String i_txt_nro_iden_prestador,
			BigDecimal i_imp_tipo_iden_medico, String i_txt_nro_iden_medico,
			String i_txt_licencia, String i_txt_apellido1_medico,
			String i_txt_apellido2_medico, String i_txt_nombres_medico,
			String i_txt_mail_medico, String i_txt_telef_conv,
			String i_txt_telef_cel, BigDecimal i_imp_operadora_cel,
			BigDecimal i_cod_suc) throws SQLException {
		PreparedStatement pstmt = null;
		RsRegistroMedicos respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_registro_medico_den(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, i_imp_tipoid_prestador);
			pstmt.setString(2, i_txt_nro_iden_prestador);
			pstmt.setBigDecimal(3, i_imp_tipo_iden_medico);
			pstmt.setString(4, i_txt_nro_iden_medico);
			pstmt.setString(5, i_txt_licencia);
			pstmt.setString(6, EncodingUtil.encodeCP850(i_txt_apellido1_medico));
			pstmt.setString(7, EncodingUtil.encodeCP850(i_txt_apellido2_medico));
			pstmt.setString(8, EncodingUtil.encodeCP850(i_txt_nombres_medico));
			pstmt.setString(9, EncodingUtil.encodeCP850(i_txt_mail_medico));
			pstmt.setString(10, i_txt_telef_conv);
			pstmt.setString(11, i_txt_telef_cel);
			pstmt.setBigDecimal(12, i_imp_operadora_cel);
			pstmt.setBigDecimal(13, i_cod_suc);

			pstmt.setQueryTimeout(420);
			rs = pstmt.executeQuery();

			respuesta = new RsRegistroMedicos();
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