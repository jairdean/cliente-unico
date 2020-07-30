package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
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

import com.equivida.sise.rs.RsInfoPoliza;
import com.equivida.sise.sp.InfoPolizaSp;
import com.equivida.sise.sp.InfoPolizaSpRemote;

@Stateless(name = "InfoPolizaSp")
public class InfoPolizaSpJdbc implements InfoPolizaSp, InfoPolizaSpRemote {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsInfoPoliza> llamarInfoPolizaSp(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException {
		PreparedStatement pstmt = null;
		List<RsInfoPoliza> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			// conn = DataSourceUtil.obtenerConexionSegunHorario(dataSource,
			// dataSourceBak);
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call sp_ws_info_poliza(?,?,?)}");
			// .prepareStatement("{call sp_ws_info_poliza(?,?,?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, cod_suc);
			pstmt.setBigDecimal(2, cod_ramo);
			pstmt.setBigDecimal(3, nro_pol);
			/*
			 * pstmt.setString(4, campoin1); pstmt.setString(5, campoin2);
			 * pstmt.setString(6, campoin3); pstmt.setString(7, campoin4);
			 * pstmt.setString(8, campoin5);
			 */

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsInfoPoliza>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsInfoPoliza aux = new RsInfoPoliza();
						aux.setDescRamo(rs.getString(1));
						aux.setNumeroPoliza(rs.getBigDecimal(2));
						aux.setInicioVigencia(rs.getString(3));
						aux.setFinVigencia(rs.getString(4));
						aux.setNombreContratante(rs.getString(5));
						aux.setNumDocContratante(rs.getString(6));
						aux.setNombreAseg(rs.getString(7));
						aux.setNumDocAseg(rs.getString(8));
						aux.setCondicion(rs.getString(9));
						aux.setImpValorAseg(rs.getBigDecimal(10));
						aux.setpPago(rs.getString(11));
						aux.setOcupacion(rs.getString(12));
						aux.setEstadoPoliza(rs.getString(13));
						aux.setTipo(rs.getString(14));
						aux.setCampo1(rs.getString(15));
						aux.setCampo2(rs.getString(16));
						aux.setCampo3(rs.getString(17));

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