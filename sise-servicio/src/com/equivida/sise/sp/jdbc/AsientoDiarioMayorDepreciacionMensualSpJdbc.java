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

import org.jboss.ejb3.annotation.TransactionTimeout;

import com.equivida.sise.rs.RsAsientoDiarioMayorDepreciacionMensual;
import com.equivida.sise.sp.AsientoDiarioMayorDepreciacionMensualSp;
import com.equivida.sise.sp.AsientoDiarioMayorDepreciacionMensualSpRemoto;

@Stateless(name = "AsientoDiarioMayorDepreciacionMensualSp")
public class AsientoDiarioMayorDepreciacionMensualSpJdbc implements
		AsientoDiarioMayorDepreciacionMensualSp,
		AsientoDiarioMayorDepreciacionMensualSpRemoto {

	@Resource(mappedName = "java:siseAF")
	private DataSource dataSource;

	//@Resource(mappedName = "java:crm-sql")
	//private DataSource dataSource;

	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@TransactionTimeout(120)
	public List<RsAsientoDiarioMayorDepreciacionMensual> llamarAsientoDiarioMayorDepreciacionMensualSp(
			Integer empCodigo, Integer anio, Integer perCodigo,
			Integer idAsientos, Integer secuencia, Integer snMcb,
			BigDecimal codSistema, BigDecimal codDestino,
			BigDecimal nroAsiento, BigDecimal codCtaCb, String codDebCred,
			BigDecimal codAnalisis, BigDecimal codConcepto,
			BigDecimal codMoneda, BigDecimal impMo, BigDecimal impEq,
			BigDecimal impCambio, BigDecimal codSuc,
			BigDecimal nroCorrelaAnalisis, BigDecimal codCCosto,
			String txtDesc, String fecAnticuacion, String codClaveConcepto,
			String fecMov, String codTipoRegistro) throws SQLException {

		PreparedStatement pstmt = null;
		List<RsAsientoDiarioMayorDepreciacionMensual> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {

			StringBuffer sql = new StringBuffer(
					"EXEC sp_interfaz_asientos_mayor ");

			if (empCodigo != null) {
				sql.append(" @empcodigo = " + empCodigo + ",");
			}
			if (anio != null) {
				sql.append(" @anio = " + anio + ",");
			}

			if (perCodigo != null) {
				sql.append(" @percodigo = " + perCodigo + ",");
			}
			if (idAsientos != null) {
				sql.append(" @id_asientos = " + idAsientos + ",");
			}
			if (snMcb != null) {
				sql.append(" @sn_mcb = " + snMcb + ",");
			}

			if (secuencia != null) {
				sql.append(" @secuencia = " + secuencia + ",");
			}

			if (codSistema != null) {
				sql.append(" @cod_sistema = " + codSistema + ",");
			}
			if (codDestino != null) {
				sql.append(" @cod_destino = " + codDestino + ",");
			}
			if (nroAsiento != null) {
				sql.append(" @nro_asiento = " + nroAsiento + ",");
			}
			if (codCtaCb != null) {
				sql.append(" @cod_cta_cb = '" + codCtaCb + "',");
			}
			if (codDebCred != null) {
				sql.append(" @cod_deb_cred = '" + codDebCred + "',");
			}
			if (codAnalisis != null) {
				sql.append(" @cod_analisis = " + codAnalisis + ",");
			}
			if (codConcepto != null) {
				sql.append(" @cod_concepto = " + codConcepto + ",");
			}
			if (codMoneda != null) {
				sql.append(" @cod_moneda = " + codMoneda + ",");
			}
			if (impMo != null) {
				sql.append(" @imp_mo = " + impMo + ",");
			}
			if (impEq != null) {
				sql.append(" @imp_eq = " + impEq + ",");
			}
			if (impCambio != null) {
				sql.append(" @imp_cambio = " + impCambio + ",");
			}
			if (codSuc != null) {
				sql.append(" @cod_suc = " + codSuc + ",");
			}
			if (nroCorrelaAnalisis != null) {
				sql.append(" @nro_correla_analisis = " + nroCorrelaAnalisis
						+ ",");
			}
			if (codCCosto != null) {
				sql.append(" @cod_ccosto = " + codCCosto + ",");
			}

			if (fecAnticuacion != null && fecAnticuacion.length() > 0) {
				sql.append(" @fec_anticuacion = '" + fecAnticuacion + "',");
			}
			if (fecMov != null && fecMov.length() > 0) {
				sql.append(" @fec_mov = '" + fecMov + "',");
			}

			if (codClaveConcepto != null && codClaveConcepto.length() > 0) {
				sql.append(" @cod_clave_concepto = '" + codClaveConcepto + "',");
			}

			if (codTipoRegistro != null && codTipoRegistro.length() > 0) {
				sql.append(" @cod_tipo_registro = '" + codTipoRegistro + "',");
			}

			if (txtDesc != null && txtDesc.length() > 0) {
				sql.append(" @txt_desc = '" + txtDesc + "',");
			}

			sql = new StringBuffer(sql.toString()
					.substring(0, sql.length() - 1));

			System.out.println("SQL: " + sql);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setQueryTimeout(600);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsAsientoDiarioMayorDepreciacionMensual>();
			while (count >= 0) {

				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {

						RsAsientoDiarioMayorDepreciacionMensual aux = new RsAsientoDiarioMayorDepreciacionMensual();

						aux.setSecuencial(rs.getString(1));
						aux.setCodTipoRegistro(rs.getString(2));
						aux.setCodError(rs.getString(3));
						aux.setTxtError(rs.getString(4));

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