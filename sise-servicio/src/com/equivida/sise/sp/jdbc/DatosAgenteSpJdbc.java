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

import com.equivida.sise.rs.RsDatosAgente;
import com.equivida.sise.sp.DatosAgenteSp;
import com.equivida.sise.sp.DatosAgenteSpRemoto;

@Stateless(name = "DatosAgenteSp")
public class DatosAgenteSpJdbc implements DatosAgenteSp, DatosAgenteSpRemoto {

	// @Resource(mappedName = "java:sise7WEBDS")
	// private DataSource dataSource;
	// @Resource(mappedName = "java:sise7WEBDSBAK")
	// private DataSource dataSourceBak;// el datasource de backup (madrugada)

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsDatosAgente> llamarDatosAgenteSp(String nro_doc,
			String campo1, String campo2, String campo3, String campo4,
			String campo5) throws SQLException {
		PreparedStatement pstmt = null;
		List<RsDatosAgente> respuesta = null;
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
			pstmt = conn.prepareStatement("{call sp_ws_datos_agente (?)}");
			pstmt.setString(1, nro_doc);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsDatosAgente>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsDatosAgente aux = new RsDatosAgente();
						aux.setNumeroDocumento(rs.getString(5));
						aux.setRazonSocial(rs.getString(4));
						aux.setDireccion(rs.getString(13));
						aux.setMunicipio(rs.getString(8));
						aux.setTelefono(rs.getString(15));
						aux.setIdPersona(rs.getBigDecimal(16));
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