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

import com.equivida.sise.rs.RsMetodoBonificacion;
import com.equivida.sise.sp.MetodoBonificacionSp;
import com.equivida.sise.sp.MetodoBonificacionSpRemoto;
import com.equivida.sise.util.DateUtil;

@Stateless(name = "MetodoBonificacionSp")
public class MetodoBonificacionSpJdbc implements MetodoBonificacionSp,
		MetodoBonificacionSpRemoto {

	@Resource(mappedName = "java:sise4WEBDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RsMetodoBonificacion llamarMetodoBonificacionSp(BigDecimal i_modo,
			BigDecimal i_id_session, BigDecimal i_cod_procedimiento,
			BigDecimal i_cod_tipo_doc, String i_nro_doc, BigDecimal i_nro_op,
			BigDecimal i_nro_pieza_dental) throws SQLException {
		PreparedStatement pstmt = null;
		RsMetodoBonificacion respuesta = null;
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
					.prepareStatement("{call sp_presupuesto_den(?,?,?,?,?,?,?)}");

			pstmt.setBigDecimal(1, i_modo);
			pstmt.setBigDecimal(2, i_id_session);
			pstmt.setBigDecimal(3, i_cod_procedimiento);
			pstmt.setBigDecimal(4, i_cod_tipo_doc);
			pstmt.setString(5, i_nro_doc);
			pstmt.setBigDecimal(6, i_nro_op);
			pstmt.setBigDecimal(7, i_nro_pieza_dental);

			pstmt.setQueryTimeout(420);
			rs = pstmt.executeQuery();
			respuesta = new RsMetodoBonificacion();

			while (rs.next()) {
				respuesta.setRegAtencion(rs.getBigDecimal(1));
				respuesta.setRecalculoPresupuesto(rs.getBigDecimal(2));
				respuesta.setMsgError(rs.getString(3));
				respuesta.setIdPresupuesto(rs.getString(4));
				respuesta.setCodProcedimiento(rs.getString(5));
				respuesta.setCantidad(rs.getString(6));
				respuesta.setIdDiagnostico(rs.getString(7));
				respuesta.setCobertura(rs.getString(8));
				respuesta.setPvp(rs.getString(9));
				respuesta.setPvpEq(rs.getString(10));
				respuesta.setValorCubierto(rs.getString(11));
				respuesta.setCoPago(rs.getString(12));
				respuesta.setNovedadAtencion(rs.getString(13));
				respuesta.setDiasCarencia(rs.getString(14));
				respuesta.setFechaVencimientoCarencia(rs.getString(15));
			}

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getErrorCode()
					+ e.getMessage());

			respuesta = new RsMetodoBonificacion();

			String mensaje = "";
			String llamada = "{call sp_presupuesto_den(" + i_modo + ","
					+ i_id_session + "," + i_cod_procedimiento + ","
					+ i_cod_tipo_doc + ",'" + i_nro_doc + "'," + i_nro_op + ","
					+ i_nro_pieza_dental + ")}";
			String errorSQL = "ERROR SQL: " + e.getErrorCode() + " : "
					+ e.getMessage();
			mensaje = llamada + errorSQL;
			Date fin = new Date();
			mensaje = mensaje + " : Inicio(" + DateUtil.formatear(inicio)
					+ ")Fin(" + DateUtil.formatear(fin) + ")";

			respuesta.setRegAtencion(BigDecimal.valueOf(2));// ERROR sql
			respuesta.setMsgError(mensaje);

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