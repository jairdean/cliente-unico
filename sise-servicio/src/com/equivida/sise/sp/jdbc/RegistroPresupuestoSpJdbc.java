package com.equivida.sise.sp.jdbc;

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

import com.equivida.sise.obj.DetalleRegistoPresupuesto;
import com.equivida.sise.rs.RsRegistroPresupuesto;
import com.equivida.sise.sp.RegistroPresupuestoSp;
import com.equivida.sise.sp.RegistroPresupuestoSpRemoto;
import com.equivida.sise.util.DateUtil;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "RegistroPresupuestoSp")
public class RegistroPresupuestoSpJdbc implements RegistroPresupuestoSp,
		RegistroPresupuestoSpRemoto {

	@Resource(mappedName = "java:sise4WEBDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsRegistroPresupuesto> llamarRegistroPresupuestoSp(
			List<DetalleRegistoPresupuesto> detalle) throws SQLException {

		List<RsRegistroPresupuesto> respuesta = new ArrayList<RsRegistroPresupuesto>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
			for (DetalleRegistoPresupuesto detalleRegistoPresupuesto : detalle) {
				RsRegistroPresupuesto respuestaAux = null;
				Date inicio = new Date();
				try {
					pstmt = conn
							.prepareStatement("{call sp_graba_proc_det_den(?,?,?,?,?,?,?,?)}");
					pstmt.setBigDecimal(1,
							detalleRegistoPresupuesto.getI_imp_id_sesion());
					pstmt.setBigDecimal(2, detalleRegistoPresupuesto
							.getI_imp_cod_procedimiento());
					pstmt.setBigDecimal(3,
							detalleRegistoPresupuesto.getI_cnt_cantidad());
					pstmt.setString(4, EncodingUtil
							.encodeCP850(detalleRegistoPresupuesto
									.getI_cod_diagnostico()));
					pstmt.setBigDecimal(5, detalleRegistoPresupuesto
							.getI_tipo_identif_medico());
					pstmt.setString(6, EncodingUtil
							.encodeCP850(detalleRegistoPresupuesto
									.getI_identif_medico()));
					pstmt.setBigDecimal(7, detalleRegistoPresupuesto
							.getI_cod_procedimiento_baja());
					pstmt.setBigDecimal(8,
							detalleRegistoPresupuesto.getI_nro_pieza_dental());

					pstmt.setQueryTimeout(420);
					rs = pstmt.executeQuery();

					respuestaAux = new RsRegistroPresupuesto();

					while (rs.next()) {
						respuestaAux.setGrabo(rs.getString(1));
						respuestaAux.setMensaje(rs.getString(2));
						respuestaAux.setIdSesion(rs.getString(3));
						respuestaAux.setCodProcedimiento(rs.getString(4));
						respuestaAux.setCantidad(rs.getString(5));
						respuestaAux.setDiagnostico(rs.getString(6));
						respuestaAux.setCobertura(rs.getString(7));
						respuestaAux.setPvp(rs.getString(8));
						respuestaAux.setValProcedimiento(rs.getString(9));
						respuestaAux.setValCubierto(rs.getString(10));
						respuestaAux.setCoPago(rs.getString(11));
						respuestaAux.setNovedad(rs.getString(12));
						respuestaAux.setDiasCarencia(rs.getString(13));
						respuestaAux.setFechaVencimiento(rs.getString(14));
					}
					respuesta.add(respuestaAux);
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getErrorCode()
							+ e.getMessage());

					respuestaAux = new RsRegistroPresupuesto();

					String mensaje = "";

					String llamada = "{call sp_graba_proc_det_den("
							+ detalleRegistoPresupuesto.getI_imp_id_sesion()
							+ ","
							+ detalleRegistoPresupuesto
									.getI_imp_cod_procedimiento()
							+ ","
							+ detalleRegistoPresupuesto.getI_cnt_cantidad()
							+ ",'"
							+ detalleRegistoPresupuesto.getI_cod_diagnostico()
							+ "',"
							+ detalleRegistoPresupuesto
									.getI_tipo_identif_medico()
							+ ",'"
							+ detalleRegistoPresupuesto.getI_identif_medico()
							+ "',"
							+ detalleRegistoPresupuesto
									.getI_cod_procedimiento_baja() + ","
							+ detalleRegistoPresupuesto.getI_nro_pieza_dental()
							+ ")}";

					String errorSQL = "ERROR SQL: " + e.getErrorCode() + " : "
							+ e.getMessage();
					mensaje = llamada + errorSQL;

					Date fin = new Date();
					mensaje = mensaje + " : Inicio("
							+ DateUtil.formatear(inicio) + ")Fin("
							+ DateUtil.formatear(fin) + ")";

					respuesta = new ArrayList<RsRegistroPresupuesto>();
					respuestaAux.setMensaje(mensaje);
					respuestaAux.setGrabo("2");// ERROR sql
					respuesta.add(respuestaAux);

				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
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