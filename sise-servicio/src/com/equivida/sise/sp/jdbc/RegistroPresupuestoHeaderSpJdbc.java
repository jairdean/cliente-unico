package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.RsRegistroPresupuestoHeader;
import com.equivida.sise.sp.RegistroPresupuestoHeaderSp;
import com.equivida.sise.sp.RegistroPresupuestoHeaderSpRemoto;
import com.equivida.sise.util.DateUtil;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "RegistroPresupuestoHeaderSp")
public class RegistroPresupuestoHeaderSpJdbc implements
		RegistroPresupuestoHeaderSp, RegistroPresupuestoHeaderSpRemoto {

	@Resource(mappedName = "java:sise4WEBDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RsRegistroPresupuestoHeader llamarRegistroPresupuestoHeaderSp(
			BigDecimal i_imp_id_sesion, BigDecimal i_imp_nro_pol,
			BigDecimal i_nro_aseg, BigDecimal i_nro_pariente,
			BigDecimal i_imp_tipo_identifaseg, String i_txt_cedula,
			BigDecimal i_imp_cod_plan, BigDecimal i_imp_cod_red,
			BigDecimal i_imp_tipo_identifpres, String i_txt_identifpres,
			BigDecimal i_imp_cod_suc, BigDecimal i_imp_tipo_identifmdiag,
			String i_txt_identifmdiag, String i_tipo_reg) throws SQLException {

		PreparedStatement pstmt = null;
		RsRegistroPresupuestoHeader respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		Date inicio = new Date();
		try {
			pstmt = conn
					.prepareStatement("{call sp_graba_proc_header_den(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			pstmt.setBigDecimal(1, i_imp_id_sesion);
			pstmt.setBigDecimal(2, i_imp_nro_pol);
			pstmt.setBigDecimal(3, i_nro_aseg);
			pstmt.setBigDecimal(4, i_nro_pariente);
			pstmt.setBigDecimal(5, i_imp_tipo_identifaseg);
			pstmt.setString(6, EncodingUtil.encodeCP850(i_txt_cedula));
			pstmt.setBigDecimal(7, i_imp_cod_plan);
			pstmt.setBigDecimal(8, i_imp_cod_red);
			pstmt.setBigDecimal(9, i_imp_tipo_identifpres);
			pstmt.setString(10, EncodingUtil.encodeCP850(i_txt_identifpres));
			pstmt.setBigDecimal(11, i_imp_cod_suc);
			pstmt.setBigDecimal(12, i_imp_tipo_identifmdiag);
			pstmt.setString(13, EncodingUtil.encodeCP850(i_txt_identifmdiag));
			pstmt.setString(14, EncodingUtil.encodeCP850(i_tipo_reg));

			pstmt.setQueryTimeout(420);
			rs = pstmt.executeQuery();

			respuesta = new RsRegistroPresupuestoHeader();
			while (rs.next()) {
				respuesta.setGrabo(rs.getString(1));
				respuesta.setMessage(rs.getString(2));

			}

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getErrorCode()
					+ e.getMessage());

			respuesta = new RsRegistroPresupuestoHeader();
			respuesta.setGrabo("2");// ERROR sql

			String mensaje = "";
			String llamada = "{call sp_graba_proc_header_den ("
					+ i_imp_id_sesion + "," + i_imp_nro_pol + "," + i_nro_aseg
					+ "," + i_nro_pariente + "," + i_imp_tipo_identifaseg
					+ ",'" + i_txt_cedula + "'," + i_imp_cod_plan + ","
					+ i_imp_cod_red + "," + i_imp_tipo_identifpres + ",'"
					+ i_txt_identifpres + "'," + i_imp_cod_suc + ","
					+ i_imp_tipo_identifmdiag + ",'" + i_txt_identifmdiag
					+ "','" + i_tipo_reg + "')}";
			String errorSQL = "ERROR SQL: " + e.getErrorCode() + " : "
					+ e.getMessage();
			mensaje = llamada + errorSQL;
			
			Date fin = new Date();
			mensaje = mensaje + " : Inicio(" + DateUtil.formatear(inicio)
					+ ")Fin(" + DateUtil.formatear(fin) + ")";

			respuesta.setMessage(mensaje);

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