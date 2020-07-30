package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.equivida.sise.rs.InsertDireccionContratante;
import com.equivida.sise.sp.InsertDireccionContratanteSp;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "InsertDireccionContratanteSp")
public class InsertDireccionContratanteSpJdbc implements InsertDireccionContratanteSp {

	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;

	// @Resource(mappedName = "java:crm-sql")
	// private DataSource dataSource;

	private Connection conn;

	@Override
	public InsertDireccionContratante llamarInsertDireccionContratanteSp(BigDecimal id_persona_wkf,
			BigDecimal cod_tipo_dir, String txt_direccion, BigDecimal cod_municipio, BigDecimal cod_dpto,
			BigDecimal cod_pais) throws SQLException {
		PreparedStatement pstmt = null;
		InsertDireccionContratante respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call spi_mpersona_dir_wkf(?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("id_persona_wkf:"+id_persona_wkf);
			System.out.println("cod_tipo_dir:"+cod_tipo_dir);
			System.out.println("txt_direccion:"+txt_direccion);
			System.out.println("cod_municipio:"+cod_municipio);
			System.out.println("cod_dpto:"+cod_dpto);
			System.out.println("cod_pais:"+cod_pais);

			// max rows de uno, si no se pone esta linea toma mucho tiempo la
			// llamada
			pstmt.setMaxRows(1);

			pstmt.setBigDecimal(1, id_persona_wkf);
			pstmt.setBigDecimal(2, cod_tipo_dir);
			pstmt.setString(3, EncodingUtil.encodeCP850(txt_direccion));
			pstmt.setBigDecimal(4, cod_municipio);
			pstmt.setBigDecimal(5, cod_dpto);
			pstmt.setBigDecimal(6, cod_pais);
			pstmt.setString(7, "");// message_wkf
			pstmt.setString(8, "");// cod_error
			pstmt.setString(9, "");// id_mpersona_dir_out

			respuesta = new InsertDireccionContratante();

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						respuesta.setMessage_wkf(rs.getString(1));
						respuesta.setCod_error(rs.getString(2));
						respuesta.setId_mpersona_dir_out(rs.getBigDecimal(3));
						break;
					}
					rs.close();
				} else {
					count = pstmt.getUpdateCount();
				} // end if-else tieneResulset
				if (count >= 0) {
					// procesa de acuerdo al contador de actualización, lo
					// ignoramos...
				}
				tieneResulset = pstmt.getMoreResults();

			} // end while count

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getErrorCode() + e.getMessage());
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