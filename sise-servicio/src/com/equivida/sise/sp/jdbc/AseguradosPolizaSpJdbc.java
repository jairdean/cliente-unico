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

import com.equivida.sise.rs.RsAseguradosPoliza;
import com.equivida.sise.sp.AseguradosPolizaSp;
import com.equivida.sise.sp.AseguradosPolizaSpRemoto;

@Stateless(name = "AseguradosPolizaSp")
public class AseguradosPolizaSpJdbc implements AseguradosPolizaSp,
		AseguradosPolizaSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;

	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsAseguradosPoliza> llamarAseguradosPolizaSp(
			BigDecimal cod_suc, BigDecimal cod_ramo, BigDecimal nro_pol,
			Integer sn_solo_activos, Date fec_desde, Date fec_hasta,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsAseguradosPoliza> respuesta = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			// conn = DataSourceUtil.obtenerConexionSegunHorario(dataSource,
			// dataSourceBak);
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_ws_list_aseg_poliza(?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, cod_suc);
			pstmt.setBigDecimal(2, cod_ramo);
			pstmt.setBigDecimal(3, nro_pol);
			pstmt.setInt(4, sn_solo_activos);
			pstmt.setTimestamp(5, new java.sql.Timestamp(fec_desde.getTime()));
			pstmt.setTimestamp(6, new java.sql.Timestamp(fec_hasta.getTime()));

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsAseguradosPoliza>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						System.out.println("iter...");
						RsAseguradosPoliza aux = new RsAseguradosPoliza();
						aux.setApellido1(rs.getString(1));
						aux.setApellido2(rs.getString(2));
						aux.setNombre(rs.getString(3));
						aux.setTipoDocumento(rs.getString(4));
						aux.setDocumento(rs.getString(5));
						aux.setFechaAlta(rs.getString(6));
						aux.setFechaBaja(rs.getString(7));
						aux.setFechaRehabilitacion(rs.getString(8));
						aux.setEstadoActual(rs.getString(9));
						aux.setTipoAsegurado(rs.getString(10));
						aux.setCodAsegurado(rs.getString(11));
						aux.setNroAsegurdo(rs.getString(12));
						aux.setCampo1(rs.getString(13));
						aux.setCampo2(rs.getString(14));
						aux.setCampo3(rs.getString(15));

						respuesta.add(aux);
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