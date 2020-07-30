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

import com.equivida.sise.rs.RsBeneficiarios;
import com.equivida.sise.sp.BeneficiariosSp;
import com.equivida.sise.sp.BeneficiariosSpRemoto;

@Stateless(name = "BeneficiariosSp")
public class BeneficiariosSpJdbc implements BeneficiariosSp,
		BeneficiariosSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsBeneficiarios> llamarBeneficiariosSp(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, BigDecimal cod_aseg,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsBeneficiarios> respuesta = null;
		ResultSet rs = null;
		try {
			//Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
			// conn = DataSourceUtil.obtenerConexionSegunHorario(dataSource,
			// dataSourceBak);
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_ws_list_benef_aseg_poliza(?,?,?,?)}");
			pstmt.setBigDecimal(1, cod_suc);
			pstmt.setBigDecimal(2, cod_ramo);
			pstmt.setBigDecimal(3, nro_pol);
			pstmt.setBigDecimal(4, cod_aseg);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsBeneficiarios>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsBeneficiarios aux = new RsBeneficiarios();
						aux.setNroAsegurado(rs.getBigDecimal(1));
						aux.setNroPariente(rs.getInt(2));
						aux.setNroBeneficiario(rs.getBigDecimal(3));
						aux.setApellido1(rs.getString(4));
						aux.setApellido2(rs.getString(5));
						aux.setNombre(rs.getString(6));
						aux.setCodDocumento(rs.getBigDecimal(7));
						aux.setTipoDocumento(rs.getString(8));
						aux.setNroDocumento(rs.getString(9));
						aux.setCodParentesco(rs.getBigDecimal(10));
						aux.setParentesco(rs.getString(11));
						aux.setCodLeyenda(rs.getBigDecimal(12));
						aux.setTipoBeneficiario(rs.getString(13));
						aux.setPjePartic(rs.getBigDecimal(14));
						aux.setFechaAlta(rs.getString(15));
						aux.setFechaBaja(rs.getString(16));
						aux.setCampo1(rs.getString(17));
						aux.setCampo2(rs.getString(18));
						aux.setCampo3(rs.getString(19));

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