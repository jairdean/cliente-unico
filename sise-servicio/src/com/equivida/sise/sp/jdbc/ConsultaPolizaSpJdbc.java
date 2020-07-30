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

import com.equivida.sise.rs.RsConsultaPolizas;
import com.equivida.sise.sp.ConsultaPolizasSp;
import com.equivida.sise.sp.ConsultaPolizasSpRemoto;

@Stateless(name = "ConsultaPolizasSp")
public class ConsultaPolizaSpJdbc implements ConsultaPolizasSp,
		ConsultaPolizasSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsConsultaPolizas> llamarConsultaPolizasSp(
			BigDecimal id_persona, String num_doc, Integer sn_activas,
			Integer tipo_busca, String campo1, String campo2, String campo3,
			String campo4, String campo5) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsConsultaPolizas> respuesta = null;
		ResultSet rs = null;
		
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
			// conn = DataSourceUtil.obtenerConexionSegunHorario(dataSource,
			// dataSourceBak);
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {

			StringBuffer sql = new StringBuffer("EXEC sp_ws_persona_poliza ");

			if (id_persona != null) {
				sql.append(" @id_persona = " + id_persona + ",");
			}
			if (num_doc != null && num_doc.length() > 0) {
				sql.append(" @nro_doc = '" + num_doc + "',");
			}
			if (sn_activas != null) {
				sql.append(" @sn_activas = " + sn_activas + ",");
			}
			if (tipo_busca != null) {
				sql.append(" @tipo_busca = " + tipo_busca + ",");
			}
			if (campo1 != null && campo1.length() > 0) {
				sql.append(" @campoin1 = " + campo1 + ",");
			}
			if (campo2 != null && campo2.length() > 0) {
				sql.append(" @campoin2 = " + campo2 + ",");
			}
			if (campo3 != null && campo3.length() > 0) {
				sql.append(" @campoin3 = " + campo3 + ",");
			}
			if (campo4 != null && campo4.length() > 0) {
				sql.append(" @campoin4 = " + campo4 + ",");
			}
			if (campo5 != null && campo5.length() > 0) {
				sql.append(" @campoin5 = " + campo5 + ",");
			}
			
			sql = new StringBuffer(sql.toString()
					.substring(0, sql.length() - 1));

			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setEscapeProcessing(true);
			pstmt.setQueryTimeout(30);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsConsultaPolizas>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsConsultaPolizas aux = new RsConsultaPolizas();
						aux.setEstadoPoliza(rs.getString(1));
						aux.setNumeroPoliza(rs.getBigDecimal(2));
						aux.setCodigoSucursal(rs.getBigDecimal(3));
						aux.setCodigoRamo(rs.getBigDecimal(4));
						aux.setFechaDesde(rs.getString(5));
						aux.setFechaHasta(rs.getString(6));
						aux.setCodigoTipoAgente(rs.getBigDecimal(7));
						aux.setCodigoAgente(rs.getBigDecimal(8));
						aux.setNombreAgente(rs.getString(9));
						aux.setCodigoTipoPersona(rs.getString(10));
						aux.setTipoAsegurado(rs.getString(11));
						aux.setNumeroAsegurado(rs.getInt(12));
						aux.setNumeroPariente(rs.getInt(13));
						aux.setNumeroDocAgente(rs.getString(14));
						aux.setNumeroDocContratante(rs.getString(15));
						aux.setCampo1(rs.getString(16));
						aux.setCampo2(rs.getString(17));
						aux.setCampo3(rs.getString(18));

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