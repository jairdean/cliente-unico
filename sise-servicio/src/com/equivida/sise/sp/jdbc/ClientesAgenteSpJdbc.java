package com.equivida.sise.sp.jdbc;

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

import com.equivida.sise.rs.RsClientesAgente;
import com.equivida.sise.sp.ClientesAgenteSp;
import com.equivida.sise.sp.ClientesAgenteSpRemote;

@Stateless(name = "ClientesAgenteSp")
public class ClientesAgenteSpJdbc implements ClientesAgenteSp,
		ClientesAgenteSpRemote {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)
	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsClientesAgente> llamarClientesAgenteSp(String numDoc,
			Integer tipoAgente, Integer codAgente, Integer snActivas,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsClientesAgente> respuesta = null;
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
			StringBuffer sql = new StringBuffer("EXEC sp_ws_clientes_agente ");

			if (tipoAgente != null) {
				sql.append(" @cod_tipo_agente = " + tipoAgente + ",");
			}
			if (codAgente != null) {
				sql.append(" @cod_agente = " + codAgente + ",");
			}
			if (snActivas != null) {
				sql.append(" @sn_activas = " + snActivas + ",");
			}
			if (numDoc != null && numDoc.length() > 0) {
				sql.append(" @nro_doc = '" + numDoc + "',");
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

			sql = new StringBuffer(sql.toString()
					.substring(0, sql.length() - 1));
			System.out.println(">>>>>>>>>>>><    " + sql);
			pstmt = conn.prepareStatement(sql.toString());
			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			int count = 0;

			respuesta = new ArrayList<RsClientesAgente>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsClientesAgente aux = new RsClientesAgente();
						aux.setNumeroDocumento(rs.getString(1));
						aux.setContratante(rs.getString(2));
						aux.setIdPersona(rs.getBigDecimal(3));
						aux.setCodAseg(rs.getBigDecimal(4));
						aux.setRamos(rs.getString(5));
						aux.setCampo1(rs.getString(6));
						aux.setCampo2(rs.getString(7));
						aux.setCampo3(rs.getString(8));

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

			/*
			 * rs = pstmt.executeQuery();
			 * 
			 * respuesta = new ArrayList<RsClientesAgente>();
			 * 
			 * while (rs.next()) { RsClientesAgente aux = new
			 * RsClientesAgente(); aux.setNumeroDocumento(rs.getString(1));
			 * aux.setContratante(rs.getString(2));
			 * aux.setIdPersona(rs.getBigDecimal(3));
			 * aux.setCodAseg(rs.getBigDecimal(4));
			 * aux.setRamos(rs.getString(5)); aux.setCampo1(rs.getString(6));
			 * aux.setCampo2(rs.getString(7)); aux.setCampo3(rs.getString(8));
			 * 
			 * respuesta.add(aux);
			 * 
			 * }
			 */

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