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

import com.equivida.sise.rs.RsInfoSaldosPolizas;
import com.equivida.sise.sp.InfoSaldosPolizasSp;
import com.equivida.sise.sp.InfoSaldosPolizasSpRemoto;

@Stateless(name = "InfoSaldosPolizasSp")
public class InfoSaldosPolizasSpJdbc implements InfoSaldosPolizasSp,
		InfoSaldosPolizasSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsInfoSaldosPolizas> llamarInfoSaldosPolizasSp(
			BigDecimal cod_suc, BigDecimal cod_ramo, BigDecimal nro_pol,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsInfoSaldosPolizas> respuesta = null;
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
			pstmt = conn
					.prepareStatement("{call sp_ws_info_saldos_poliza(?,?,?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, cod_suc);
			pstmt.setBigDecimal(2, cod_ramo);
			pstmt.setBigDecimal(3, nro_pol);
			pstmt.setString(4, campoin1);
			pstmt.setString(5, campoin2);
			pstmt.setString(6, campoin3);
			pstmt.setString(7, campoin4);
			pstmt.setString(8, campoin5);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsInfoSaldosPolizas>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {

						RsInfoSaldosPolizas aux = new RsInfoSaldosPolizas();
						aux.setCodSucursal(rs.getInt(1));
						aux.setCodRamo(rs.getInt(2));
						aux.setNroPoliza(rs.getBigDecimal(3));
						aux.setValorAnual(rs.getBigDecimal(4));
						aux.setNroCuentasMora(rs.getInt(5));
						aux.setValorMora(rs.getBigDecimal(6));
						aux.setPeriocidadPago(rs.getString(7));
						aux.setCampo1(rs.getString(8));
						aux.setCampo2(rs.getString(9));
						aux.setCampo3(rs.getString(10));

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