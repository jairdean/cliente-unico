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

import com.equivida.sise.rs.RsAsientoDiario;
import com.equivida.sise.sp.AsientoDiarioSp;
import com.equivida.sise.sp.AsientoDiarioSpRemoto;

@Stateless(name = "AsientoDiarioSp")
public class AsientoDiarioSpJdbc implements AsientoDiarioSp,
		AsientoDiarioSpRemoto {

	@Resource(mappedName = "java:siseAF")
	private DataSource dataSource;

	//@Resource(mappedName = "java:crm-sql")
	//private DataSource dataSource;

	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsAsientoDiario> llamarAsientoDiarioSp(Integer empCodigo,
			String nroFactura, Integer codigoProveedor, Integer tipo,
			Integer codSuc, String codCtaCb, String codDebCred,
			BigDecimal impMo, BigDecimal impEq, BigDecimal codMoneda,
			BigDecimal impCambio, Integer codAnalisis, BigDecimal codConcepto,
			String codClaveConcepto, String txtDesc, String fecha,
			BigDecimal codValor, BigDecimal numValor, BigDecimal codCCosto,
			Integer usrCodigo, Integer secuencia, String codTipoRegistro)
			throws SQLException {

		PreparedStatement pstmt = null;
		List<RsAsientoDiario> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {

			StringBuffer sql = new StringBuffer(
					"EXEC sp_interfaz_asientos_diarios ");

			if (empCodigo != null) {
				sql.append(" @empcodigo = " + empCodigo + ",");
			}
			if (nroFactura != null && nroFactura.length() > 0) {
				sql.append(" @nro_factura = '" + nroFactura + "',");
			}
			if (codigoProveedor != null) {
				sql.append(" @codigo_proveedor = " + codigoProveedor + ",");
			}
			if (tipo != null) {
				sql.append(" @tipo = " + tipo + ",");
			}
			if (codSuc != null) {
				sql.append(" @cod_suc = " + codSuc + ",");
			}
			if (codAnalisis != null) {
				sql.append(" @cod_analisis = " + codAnalisis + ",");
			}
			if (codCtaCb != null) {
				sql.append(" @cod_cta_cb = '" + codCtaCb + "',");
			}
			if (codDebCred != null) {
				sql.append(" @cod_deb_cred = '" + codDebCred + "',");
			}
			if (impMo != null) {
				sql.append(" @importe_mo = " + impMo + ",");
			}
			if (impEq != null) {
				sql.append(" @importe_eq = " + impEq + ",");
			}
			if (codMoneda != null) {
				sql.append(" @cod_moneda = " + codMoneda + ",");
			}
			if (impCambio != null) {
				sql.append(" @imp_cambio = " + impCambio + ",");
			}
			if (codClaveConcepto != null) {
				sql.append(" @cod_clave_concepto = '" + codClaveConcepto + "',");
			}
			if (codConcepto != null) {
				sql.append(" @cod_concepto = " + codConcepto + ",");
			}
			if (txtDesc != null && txtDesc.length() > 0) {
				sql.append(" @txt_desc = '" + txtDesc + "',");
			}
			if (fecha != null && fecha.length() > 0) {
				sql.append(" @fec_mov = '" + fecha + "',");
			}
			if (codValor != null) {
				sql.append(" @cod_valor = " + codValor + ",");
			}
			if (numValor != null) {
				sql.append(" @num_valor = " + numValor + ",");
			}
			if (codCCosto != null) {
				sql.append(" @cod_ccosto = " + codCCosto + ",");
			}
			if (usrCodigo != null) {
				sql.append(" @usrcodigo = '" + usrCodigo + "',");
			}
			if (secuencia != null) {
				sql.append(" @secuencia = " + secuencia + ",");
			}
			if (codTipoRegistro != null && codTipoRegistro.length() > 0) {
				sql.append(" @cod_tipo_registro = '" + codTipoRegistro + "',");
			}

			sql = new StringBuffer(sql.toString()
					.substring(0, sql.length() - 1));
			pstmt = conn.prepareStatement(sql.toString());

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsAsientoDiario>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						System.out.println("iter...");
						RsAsientoDiario aux = new RsAsientoDiario();

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