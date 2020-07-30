package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.RsIngresoCitas;
import com.equivida.sise.sp.IngresoCitasSp;
import com.equivida.sise.sp.IngresoCitasSpRemoto;
import com.equivida.sise.util.DateUtil;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "IngresoCitasSp")
public class IngresoCitasSpJdbc implements IngresoCitasSp, IngresoCitasSpRemoto {

	// @Resource(mappedName = "java:bioredDS")
	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsIngresoCitas> llamarIngresoCitasSp(BigDecimal i_imp_modo,
			BigDecimal i_imp_tipo_doc, String i_txt_nro_ced,
			BigDecimal i_imp_cod_aseg, String i_txt_apellido1,
			String i_txt_apellido2) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RsIngresoCitas> lista = new ArrayList<RsIngresoCitas>();

		if (i_txt_apellido2 != null && i_txt_apellido2.equals("TIMEOUT")) {
			try {
				System.err
						.println("emulando timeout, para calcular cuando es el timeout del jboss");
				Thread.sleep(300000);
				lista = new ArrayList<RsIngresoCitas>();
				RsIngresoCitas respuesta = new RsIngresoCitas();
				String mensaje = "";
				String llamada = "{call sp_cons_asegurado_den(" + i_imp_modo
						+ "," + i_imp_tipo_doc + ",'" + i_txt_nro_ced + "',"
						+ i_imp_cod_aseg + ",'" + i_txt_apellido1 + "','"
						+ i_txt_apellido2 + "')} ";
				String errorSQL = "no hubo timeout jboss...";
				mensaje = llamada + errorSQL;

				respuesta.setAtendido("2");// ERROR en el sql
				respuesta.setMensaje(mensaje);

				lista.add(respuesta);
				return lista;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
			System.out.println("conexion ok");

		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
			RsIngresoCitas respuesta = new RsIngresoCitas();
			respuesta.setAtendido("2");
			respuesta.setMensaje("No existe conexion!!!");
			lista.add(respuesta);
			return lista;
		}
		Date inicio = new Date();
		try {
			pstmt = conn
					.prepareStatement("{call sp_cons_asegurado_den(?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, i_imp_modo);
			pstmt.setBigDecimal(2, i_imp_tipo_doc);
			pstmt.setString(3, i_txt_nro_ced);
			pstmt.setBigDecimal(4, i_imp_cod_aseg);
			pstmt.setString(5, EncodingUtil.encodeCP850(i_txt_apellido1));
			pstmt.setString(6, EncodingUtil.encodeCP850(i_txt_apellido2));

			// timeout//timeout//timeout//timeout
			// if (i_txt_apellido2 != null &&
			// i_txt_apellido2.equals("TIMEOUTSQL")) {
			pstmt.setQueryTimeout(420);// un segundo, segundos!! no milis...
			// }

			System.out.println("empieza a ejecutar SP");

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			lista = new ArrayList<RsIngresoCitas>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsIngresoCitas respuesta = new RsIngresoCitas();
						respuesta.setAtendido(rs.getString(1));
						respuesta.setNumPoliza(rs.getString(2));
						respuesta.setNombreContratante(rs.getString(3));
						respuesta.setCodTipoDocumento(rs.getString(4));
						respuesta.setNumDocumento(rs.getString(5));
						respuesta.setPrimerApellido(EncodingUtil
								.encodeCP850toUTF8(rs.getString(6)));
						respuesta.setSegundoApellido(EncodingUtil
								.encodeCP850toUTF8(rs.getString(7)));
						respuesta.setNombreAsegurado(EncodingUtil
								.encodeCP850toUTF8(rs.getString(8)));
						respuesta.setNumAsegurado(rs.getString(9));
						respuesta.setNumPariente(rs.getString(10));
						respuesta.setPlan(rs.getString(11));
						respuesta.setCodPlan(rs.getString(12));
						respuesta.setRed(rs.getString(13));
						respuesta.setCodRed(rs.getString(14));
						respuesta.setFechaBaja(rs.getString(15));
						respuesta.setTitular(EncodingUtil.encodeCP850toUTF8(rs
								.getString(16)));
						respuesta.setMensaje(rs.getString(17));

						lista.add(respuesta);

					}
					rs.close();
				} else {
					count = pstmt.getUpdateCount();
				}// end if-else tieneResulset
				if (count >= 0) {
					// procesa de acuerdo al contador de actualización, lo
					// ignoramos...
				}
				tieneResulset = pstmt.getMoreResults();

			}// end while count

		} catch (SQLException e) {

			lista = new ArrayList<RsIngresoCitas>();
			RsIngresoCitas respuesta = new RsIngresoCitas();
			String mensaje = "";
			String llamada = "{call sp_cons_asegurado_den(" + i_imp_modo + ","
					+ i_imp_tipo_doc + ",'" + i_txt_nro_ced + "',"
					+ i_imp_cod_aseg + ",'" + i_txt_apellido1 + "','"
					+ i_txt_apellido2 + "')} ";
			String errorSQL = "SQLException: " + e.getErrorCode() + " : "
					+ e.getMessage();
			mensaje = llamada + errorSQL;
			Date fin = new Date();
			mensaje = mensaje + " : Inicio(" + DateUtil.formatear(inicio)
					+ ")Fin(" + DateUtil.formatear(fin) + ")";

			System.err.println(mensaje);

			respuesta.setAtendido("2");// ERROR en el sql
			respuesta.setMensaje(mensaje);

			lista.add(respuesta);

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

		System.out.println("sale IngresoCitasSpJdbc.llamarIngresoCitasSp");

		return lista;

	}
}