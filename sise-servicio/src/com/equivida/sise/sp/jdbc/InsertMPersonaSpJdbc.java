package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.RespuestaInsertMPersona;
import com.equivida.sise.sp.InsertMPersonaSp;
import com.equivida.sise.sp.InsertMPersonaSpRemoto;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "InsertMPersonaSp")
public class InsertMPersonaSpJdbc implements InsertMPersonaSp,
		InsertMPersonaSpRemoto {

	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;

	// @Resource(mappedName = "java:crm-sql")
	// private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RespuestaInsertMPersona llamarSp(String txt_apellido1,
			String txt_apellido2, String txt_nombre, BigDecimal cod_tipo_doc,
			String nro_doc, BigDecimal cod_tipo_iva, String nro_nit,
			Date fec_nac, String txt_lugar_nac, String txt_sexo,
			BigDecimal cod_est_civil, String cod_tipo_persona,
			String txt_origen, String txt_nombres_conyuge,
			String txt_apellidos_conyuge, BigDecimal cod_tipo_doc_conyuge,
			String nro_doc_conyuge, String campo_in_1, String campo_in_2,
			String campo_in_3, String campo_in_4, String campo_in_5)
			throws SQLException {
		PreparedStatement pstmt = null;
		RespuestaInsertMPersona respuesta = null;
		ResultSet rs = null;

		boolean juridica = false;
		if (cod_tipo_persona.equals("J")) {
			juridica = true;
			System.out.println("es juridica");
		}

		System.out.println("SEXO:" + txt_sexo);

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
			System.out.println("INFORMACION DE CONEXION: **************** ");
			DatabaseMetaData data = conn.getMetaData();
			System.out.println("URL:  " + data.getURL());
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		
		try {

			System.out.println(" en statemt sp_insert_mpersona");

			// String
			// sqlPrueba="{call sp_insert_mpersona('pru ruc 12',NULL,null,2,NULL,1,'1709727745001',NULL,NULL,'M',1,'J',null,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)}";

			// stmt = conn.createStatement();
			// .prepareStatement(sqlPrueba);

			pstmt = conn
					.prepareStatement("{call sp_insert_mpersona(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			// max rows de uno, si no se pone esta linea toma mucho tiempo la
			// llamada
			pstmt.setMaxRows(1);

			pstmt.setString(1, EncodingUtil.encodeCP850(txt_apellido1));
			if (juridica) {
				pstmt.setString(2, null);
				pstmt.setString(3, null);
			} else {
				pstmt.setString(2, EncodingUtil.encodeCP850(txt_apellido2));
				pstmt.setString(3, EncodingUtil.encodeCP850(txt_nombre));
			}
			pstmt.setBigDecimal(4, cod_tipo_doc);
			if (juridica) {
				pstmt.setString(5, null);
			} else {
				pstmt.setString(5, EncodingUtil.encodeCP850(nro_doc));
			}
			pstmt.setBigDecimal(6, cod_tipo_iva);
			pstmt.setString(7, EncodingUtil.encodeCP850(nro_nit));
			if (fec_nac != null) {
				pstmt.setTimestamp(8, new java.sql.Timestamp(fec_nac.getTime()));
			} else {
				pstmt.setTimestamp(8, null);
			}

			if (juridica) {
				pstmt.setString(9, null);
			} else {
				pstmt.setString(9, EncodingUtil.encodeCP850(txt_lugar_nac));
			}

			pstmt.setString(10, EncodingUtil.encodeCP850(txt_sexo));
			pstmt.setBigDecimal(11, cod_est_civil);
			pstmt.setString(12, EncodingUtil.encodeCP850(cod_tipo_persona));

			if (juridica) {
				pstmt.setString(13, null);
				pstmt.setString(14, null);
				pstmt.setString(15, null);
				pstmt.setBigDecimal(16, null);
				pstmt.setString(17, null);
				pstmt.setString(18, null);
				pstmt.setString(19, null);
				pstmt.setString(20, null);
				pstmt.setString(21, null);
				pstmt.setString(22, null);
			} else {
				pstmt.setString(13, EncodingUtil.encodeCP850(txt_origen));
				pstmt.setString(14,
						EncodingUtil.encodeCP850(txt_nombres_conyuge));
				pstmt.setString(15,
						EncodingUtil.encodeCP850(txt_apellidos_conyuge));
				pstmt.setBigDecimal(16, cod_tipo_doc_conyuge);
				pstmt.setString(17, EncodingUtil.encodeCP850(nro_doc_conyuge));
				pstmt.setString(18, EncodingUtil.encodeCP850(campo_in_1));
				pstmt.setString(19, EncodingUtil.encodeCP850(campo_in_2));
				pstmt.setString(20, EncodingUtil.encodeCP850(campo_in_3));
				pstmt.setString(21, EncodingUtil.encodeCP850(campo_in_4));
				pstmt.setString(22, EncodingUtil.encodeCP850(campo_in_5));
			}
			
			System.out.println("Parametros de ingreso al insertar persona:");
			System.out.println("PREPARED STATEMENT >>>>>>>>>>>>>> " + pstmt);

			respuesta = new RespuestaInsertMPersona();

			int count = 0;

			boolean tieneResulset = pstmt.execute();

			System.out.println("tiene.:" + tieneResulset);

			while (count >= 0) {
				if (tieneResulset) {

					rs = pstmt.getResultSet();

					while (rs.next()) {

						respuesta.setIdPersona(rs.getBigDecimal(1));
						respuesta.setNumFilas(rs.getBigDecimal(2));
						respuesta.setIdProceso(rs.getString(3));
						respuesta.setTxtError(rs.getString(4));
						respuesta.setCampoOut1(rs.getString(5));
						respuesta.setCampoOut1(rs.getString(6));
						respuesta.setCampoOut1(rs.getString(7));
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

			System.out.println("FIN");

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