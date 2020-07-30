package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.RsActualizacionDatos;
import com.equivida.sise.sp.ActualizacionDatosSp;
import com.equivida.sise.sp.ActualizacionDatosSpRemoto;
import com.equivida.sise.util.DateUtil;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "ActualizacionDatosSp")
public class ActualizacionDatosSpJdbc implements ActualizacionDatosSp,
		ActualizacionDatosSpRemoto {

	// @Resource(mappedName = "java:siseWEBDS")
	@Resource(mappedName = "java:sise4WEBDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RsActualizacionDatos llamarActualizacionDatosSp(
			BigDecimal i_imp_poliza, BigDecimal i_imp_nro_asegurado,
			BigDecimal i_imp_nro_pariente,
			BigDecimal i_imp_tipo_identificacion, String i_txt_identificacion,
			String i_txt_primer_apellido, String i_txt_segundo_apellido,
			String i_txt_nombres, Date i_dat_fecha_nacimiento,
			String i_txt_direccion_principal, BigDecimal i_imp_provincia,
			BigDecimal i_imp_canton, String i_txt_direccion_domicilio,
			BigDecimal i_imp_provinciad, BigDecimal i_imp_cantond,
			String i_txt_telefono_principal, String i_txt_telefono_celular,
			BigDecimal i_imp_operadora, String i_txt_direccion_mail)
			throws SQLException {


		PreparedStatement pstmt = null;
		RsActualizacionDatos respuesta = null;
		ResultSet rs = null;

		Timestamp fn = null;
		if (i_dat_fecha_nacimiento != null) {
			fn = new java.sql.Timestamp(i_dat_fecha_nacimiento.getTime());
		}

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			System.out.println("obtiene conexion");
			conn = dataSource.getConnection();
			System.out.println("conexion ok");
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
			respuesta = new RsActualizacionDatos();
			respuesta.setGrabo("2");
			respuesta.setMessage("No existe conexion!!!");
			return respuesta;
		}
		Date inicio = new Date();
		try {
			System.out.println("prepara statement");
			pstmt = conn
					.prepareStatement("{call sp_actdatos_biored_den(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, i_imp_poliza);
			pstmt.setBigDecimal(2, i_imp_nro_asegurado);
			pstmt.setBigDecimal(3, i_imp_nro_pariente);
			pstmt.setBigDecimal(4, i_imp_tipo_identificacion);
			pstmt.setString(5, i_txt_identificacion);
			pstmt.setString(6, EncodingUtil.encodeCP850(i_txt_primer_apellido));
			pstmt.setString(7, EncodingUtil.encodeCP850(i_txt_segundo_apellido));
			pstmt.setString(8, EncodingUtil.encodeCP850(i_txt_nombres));
			pstmt.setTimestamp(9, fn);
			pstmt.setString(10,
					EncodingUtil.encodeCP850(i_txt_direccion_principal));
			pstmt.setBigDecimal(11, i_imp_provincia);
			pstmt.setBigDecimal(12, i_imp_canton);
			pstmt.setString(13,
					EncodingUtil.encodeCP850(i_txt_direccion_domicilio));
			pstmt.setBigDecimal(14, i_imp_provinciad);
			pstmt.setBigDecimal(15, i_imp_cantond);
			pstmt.setString(16, i_txt_telefono_principal);
			pstmt.setString(17, i_txt_telefono_celular);
			pstmt.setBigDecimal(18, i_imp_operadora);
			pstmt.setString(19, EncodingUtil.encodeCP850(i_txt_direccion_mail));

			// timeout
			pstmt.setQueryTimeout(420);// un segundo, segundos!! no milis...

			System.out.println("empieza a ejecutar SP");

			rs = pstmt.executeQuery();

			respuesta = new RsActualizacionDatos();
			System.out.println("in  JDBC " + rs);

			while (rs.next()) {
				respuesta.setGrabo(rs.getString(1));
				respuesta.setMessage(rs.getString(2));
			}

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getErrorCode()
					+ e.getMessage());

			respuesta = new RsActualizacionDatos();

			String mensaje = "";
			String llamada = "{call sp_actdatos_biored_den(" + i_imp_poliza
					+ "," + i_imp_nro_asegurado + "," + i_imp_nro_pariente
					+ "," + i_imp_tipo_identificacion + ","
					+ i_txt_identificacion + ","
					+ EncodingUtil.encodeCP850(i_txt_primer_apellido) + ","
					+ EncodingUtil.encodeCP850(i_txt_segundo_apellido) + ","
					+ EncodingUtil.encodeCP850(i_txt_nombres) + "," + fn + ","
					+ EncodingUtil.encodeCP850(i_txt_direccion_principal) + ","
					+ i_imp_provincia + "," + i_imp_canton + ","
					+ EncodingUtil.encodeCP850(i_txt_direccion_domicilio) + ","
					+ i_imp_provinciad + "," + i_imp_cantond + ","
					+ i_txt_telefono_principal + "," + i_txt_telefono_celular
					+ "," + i_imp_operadora + ","
					+ EncodingUtil.encodeCP850(i_txt_direccion_mail) + ")} ";

			String errorSQL = "SQLException: " + e.getErrorCode() + " : "
					+ e.getMessage();

			mensaje = llamada + errorSQL;
			Date fin = new Date();
			mensaje = mensaje + " : Inicio(" + DateUtil.formatear(inicio)
					+ ")Fin(" + DateUtil.formatear(fin) + ")";

			System.err.println(mensaje);

			respuesta.setGrabo("2");// ERROR en el sql
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

		System.out
				.println("sale ActualizacionDatosSpJdbc.llamarActualizacionDatosSp");

		return respuesta;

	}
}