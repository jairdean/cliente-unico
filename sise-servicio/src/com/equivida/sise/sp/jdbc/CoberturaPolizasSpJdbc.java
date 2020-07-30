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

import com.equivida.sise.rs.RsCoberturaPolizas;
import com.equivida.sise.sp.CoberturaPolizaSp;
import com.equivida.sise.sp.CoberturaPolizaSpRemoto;

@Stateless(name = "CoberturaPolizasSp")
public class CoberturaPolizasSpJdbc implements CoberturaPolizaSp,
		CoberturaPolizaSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)
	private Connection conn;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private BigDecimal RAMO_A1 = new BigDecimal(50);
	private BigDecimal RAMO_A2 = new BigDecimal(25);
	private BigDecimal RAMO_B1 = new BigDecimal(59);
	private BigDecimal RAMO_B2 = new BigDecimal(55);
	private BigDecimal RAMO_B3 = new BigDecimal(54);
	private BigDecimal RAMO_B4 = new BigDecimal(52);
	private BigDecimal RAMO_B5 = new BigDecimal(58);

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsCoberturaPolizas> llamarCoberturaPolizaSp(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException {
		PreparedStatement pstmt = null;
		List<RsCoberturaPolizas> respuesta = null;
		ResultSet rs = null;
		try {
			//Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
			//conn = DataSourceUtil.obtenerConexionSegunHorario(dataSource,
				//	dataSourceBak);
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_ws_coberturas_poliza(?,?,?)}");
			// .prepareStatement("{call sp_ws_coberturas_poliza(?,?,?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, cod_suc);
			pstmt.setBigDecimal(2, cod_ramo);
			pstmt.setBigDecimal(3, nro_pol);
			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsCoberturaPolizas>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						if (cod_ramo.equals(RAMO_A1)
								|| cod_ramo.equals(RAMO_A2)) {
							RsCoberturaPolizas aux = new RsCoberturaPolizas();
							aux.setCod_ind_categ(rs.getInt(1));
							aux.setTxt_desc_categ(rs.getString(2));
							aux.setTxt_desc2(rs.getString(7));
							aux.setImp_suma_aseg(rs.getBigDecimal(8));
							aux.setPje_tasa(rs.getBigDecimal(10));
							aux.setCampoout1(rs.getString(11));
							aux.setCampoout2(rs.getString(12));
							aux.setCampoout3(rs.getString(13));
							respuesta.add(aux);
						}
						if (cod_ramo.equals(RAMO_B1)
								|| cod_ramo.equals(RAMO_B2)
								|| cod_ramo.equals(RAMO_B3)
								|| cod_ramo.equals(RAMO_B4)
								|| cod_ramo.equals(RAMO_B5)) {
							RsCoberturaPolizas aux = new RsCoberturaPolizas();

							aux.setCobertura(rs.getString(1));
							aux.setFec_vto(rs.getString(2));
							aux.setImp_capital(rs.getBigDecimal(3));
							aux.setImp_prima(rs.getBigDecimal(4));
							aux.setCampoout1(rs.getString(7));
							aux.setCampoout2(rs.getString(8));
							aux.setCampoout3(rs.getString(9));

							respuesta.add(aux);
						}

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