package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.InsertDireccionPersona;
import com.equivida.sise.sp.InsertDireccionPersonaSp;
import com.equivida.sise.sp.InsertDireccionPersonaSpRemoto;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "InsertDireccionPersonaSp")
public class InsertDireccionPersonaSpJdbc implements InsertDireccionPersonaSp,
		InsertDireccionPersonaSpRemoto {

	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;

	// @Resource(mappedName = "java:crm-sql")
	// private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public InsertDireccionPersona llamarInsertDireccionPersonaSp(
			BigDecimal id_persona, BigDecimal cod_tipo_dir,
			String txt_direccion, BigDecimal cod_municipio,
			BigDecimal cod_dpto, BigDecimal cod_pais, String txt_edificio,
			String txt_urbanizacion, String txt_sector, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException {
		PreparedStatement pstmt = null;
		InsertDireccionPersona respuesta = null;
		ResultSet rs = null;
		
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_insert_mpersonadir(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			// max rows de uno, si no se pone esta linea toma mucho tiempo la
			// llamada
			pstmt.setMaxRows(1);

			pstmt.setBigDecimal(1, id_persona);
			pstmt.setBigDecimal(2, cod_tipo_dir);
			pstmt.setString(3, EncodingUtil.encodeCP850(txt_direccion));
			pstmt.setBigDecimal(4, cod_municipio);
			pstmt.setBigDecimal(5, cod_dpto);
			pstmt.setBigDecimal(6, cod_pais);
			pstmt.setString(7, EncodingUtil.encodeCP850(txt_edificio));
			pstmt.setString(8, EncodingUtil.encodeCP850(txt_urbanizacion));
			pstmt.setString(9, EncodingUtil.encodeCP850(txt_sector));
			pstmt.setString(10, campoin1);
			pstmt.setString(11, campoin2);
			pstmt.setString(12, campoin3);
			pstmt.setString(13, campoin4);
			pstmt.setString(14, campoin5);
			

			respuesta = new InsertDireccionPersona();

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {

						respuesta.setNumFilas(rs.getBigDecimal(1));
						respuesta.setCodError(rs.getString(2));
						respuesta.setMsgError(rs.getString(3));
						respuesta.setIdDireccion(rs.getBigDecimal(4));
						respuesta.setCampoOut1(rs.getString(5));
						respuesta.setCampoOut2(rs.getString(6));
						respuesta.setCampoOut3(rs.getString(7));
						break;
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