package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.MasegHeader;
import com.equivida.sise.sp.MasegHeaderSp;
import com.equivida.sise.sp.MasegHeaderSpRemoto;
import com.equivida.sise.util.EncodingUtil;

@Stateless(name = "MasegHeaderSp")
public class MasegHeaderSpJdbc implements MasegHeaderSp, MasegHeaderSpRemoto {

	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;

	//@Resource(mappedName = "java:crm-sql")
	//private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public MasegHeader llamarMasegHeaderSp(BigDecimal idPersona,
			BigDecimal codFiguraAseg, BigDecimal codTipoAseg,
			BigDecimal codImpAseg, BigDecimal codTipoAgente,
			BigDecimal codAgente, Date fechaAlta, Date fechaBaja,
			BigDecimal codigoOcupacion, Integer avisoVto, String codAsegVinc,
			Date fechaUltMod, String codUsuario, String nombFactura,
			BigDecimal tazaFianzas, Integer consorcio, BigDecimal codMoneda,
			BigDecimal impSueldo, BigDecimal codDeporte, String edificio,
			String urbanizacion, String sector, String nombresConyuge,
			String apellidoConyuge, BigDecimal codTipoDocConyuge,
			String numeroDocConyuge, String campoIn1, String campoIn2,
			String campoIn3, String campoIn4, String campoIn5)
			throws SQLException {
		PreparedStatement pstmt = null;
		MasegHeader respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_insert_maseg_header_edm(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, idPersona);
			pstmt.setBigDecimal(2, codFiguraAseg);
			pstmt.setBigDecimal(3, codTipoAseg);
			pstmt.setBigDecimal(4, codImpAseg);
			pstmt.setBigDecimal(5, codTipoAgente);
			pstmt.setBigDecimal(6, codAgente);
			if (fechaAlta != null) {
				pstmt.setTimestamp(7,
						new java.sql.Timestamp(fechaAlta.getTime()));
			} else {
				pstmt.setTimestamp(7, null);
			}

			Timestamp fb = null;
			if (fechaBaja != null) {
				fb = new java.sql.Timestamp(fechaUltMod.getTime());
			}
			pstmt.setTimestamp(8, fb);
			pstmt.setBigDecimal(9, codigoOcupacion);
			pstmt.setInt(10, avisoVto);
			pstmt.setString(11, codAsegVinc);
			if (fechaUltMod != null) {
				pstmt.setTimestamp(12,
						new java.sql.Timestamp(fechaUltMod.getTime()));
			} else {
				pstmt.setTimestamp(12, null);
			}
			pstmt.setString(13, codUsuario);
			pstmt.setString(14, EncodingUtil.encodeCP850(nombFactura));
			pstmt.setBigDecimal(15, tazaFianzas);
			pstmt.setInt(16, consorcio);
			pstmt.setBigDecimal(17, codMoneda);
			pstmt.setBigDecimal(18, impSueldo);
			pstmt.setBigDecimal(19, codDeporte);
			pstmt.setString(20, EncodingUtil.encodeCP850(edificio));
			pstmt.setString(21, EncodingUtil.encodeCP850(urbanizacion));
			pstmt.setString(22, EncodingUtil.encodeCP850(sector));
			pstmt.setString(23, EncodingUtil.encodeCP850(nombresConyuge));
			pstmt.setString(24, EncodingUtil.encodeCP850(apellidoConyuge));
			pstmt.setBigDecimal(25, codTipoDocConyuge);
			pstmt.setString(26, EncodingUtil.encodeCP850(numeroDocConyuge));
			/*
			 * pstmt.setString(27, campoIn1); pstmt.setString(28, campoIn2);
			 * pstmt.setString(29, campoIn3); pstmt.setString(30, campoIn4);
			 * pstmt.setString(31, campoIn5);
			 */

			respuesta = new MasegHeader();

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						respuesta.setCodAseg(rs.getBigDecimal(1));
						respuesta.setNumFilas(rs.getBigDecimal(2));
						respuesta.setCodError(rs.getString(3));
						respuesta.setMsgError(rs.getString(4));
						respuesta.setCampoOut1(rs.getString(5));
						respuesta.setCampoOut2(rs.getString(6));
						respuesta.setCampoOut3(rs.getString(7));
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
			respuesta.setMsgError("CODE: " + e.getErrorCode() + " Message:"
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