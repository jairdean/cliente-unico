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

import com.equivida.sise.rs.RsCertificadoIndividual;
import com.equivida.sise.sp.CertificadoIndividualSp;
import com.equivida.sise.sp.CertificadoIndividualSpRemoto;

@Stateless(name = "CertificadoIndividualSp")
public class CertificadoIndividualSpJdbc implements CertificadoIndividualSp,
		CertificadoIndividualSpRemoto {

	//@Resource(mappedName = "java:sise7WEBDS")
	//private DataSource dataSource;
	//@Resource(mappedName = "java:sise7WEBDSBAK")
	//private DataSource dataSourceBak;// el datasource de backup (madrugada)
	
	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;
	
	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsCertificadoIndividual> llamarCertificadoIndividualSp(
			BigDecimal cod_suc, BigDecimal cod_ramo, BigDecimal nro_pol,
			BigDecimal nro_aseg, String nro_doc, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException {
		PreparedStatement pstmt = null;
		List<RsCertificadoIndividual> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
			//conn = DataSourceUtil.obtenerConexionSegunHorario(dataSource,
			//		dataSourceBak);
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			StringBuffer sql = new StringBuffer(
					"EXEC sp_ws_certif_aseg_poliza ");

			if (cod_suc != null) {
				sql.append(" @cod_suc = " + cod_suc + ",");
			}
			if (cod_ramo != null) {
				sql.append(" @cod_ramo = " + cod_ramo + ",");
			}
			if (nro_pol != null) {
				sql.append(" @nro_pol = " + nro_pol + ",");
			}
			if (nro_aseg != null) {
				sql.append(" @nro_aseg = " + nro_aseg + ",");
			}
			if (nro_doc != null && nro_doc.length() > 0) {
				sql.append(" @nro_doc = '" + nro_doc + "',");
			}
			if (campoin1 != null && campoin1.length() > 0) {
				sql.append(" @campoin1 = " + campoin1 + ",");
			}
			if (campoin2 != null && campoin2.length() > 0) {
				sql.append(" @campoin2 = " + campoin2 + ",");
			}
			if (campoin3 != null && campoin3.length() > 0) {
				sql.append(" @campoin3 = " + campoin3 + ",");
			}
			if (campoin4 != null && campoin4.length() > 0) {
				sql.append(" @campoin4 = " + campoin4 + ",");
			}
			if (campoin5 != null && campoin5.length() > 0) {
				sql.append(" @campoin5 = " + campoin5 + ",");
			}

			System.out.println(">>>>>>>>>>>>>>><    " + sql);
			sql = new StringBuffer(sql.toString()
					.substring(0, sql.length() - 1));
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsCertificadoIndividual>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsCertificadoIndividual aux = new RsCertificadoIndividual();
						aux.setNroAsegurado(rs.getBigDecimal(1));
						aux.setApellido1(rs.getString(2));
						aux.setApellido2(rs.getString(3));
						aux.setNombre(rs.getString(4));
						aux.setNroDocumento(rs.getString(5));
						aux.setCodSucursal(rs.getBigDecimal(6));
						aux.setCodRamo(rs.getBigDecimal(7));
						aux.setNroPoliza(rs.getBigDecimal(8));
						aux.setVigenciaDesde(rs.getString(9));
						aux.setVigenciaHasta(rs.getString(10));
						aux.setContratante(rs.getString(11));
						aux.setCobertura(rs.getString(12));
						aux.setValorAsegurado(rs.getBigDecimal(13));
						aux.setCampo1(rs.getString(14));
						aux.setCampo2(rs.getString(15));
						aux.setCampo3(rs.getString(16));

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