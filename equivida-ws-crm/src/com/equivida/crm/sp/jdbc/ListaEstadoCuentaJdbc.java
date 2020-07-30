/**
 * 
 */
package com.equivida.crm.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.crm.rs.ListaEstadoCuentaRs;
import com.equivida.crm.sp.ListaEstadoCuentaSp;
import com.equivida.crm.sp.ListaEstadoCuentaSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaEstadoCuentaSp")
public class ListaEstadoCuentaJdbc implements ListaEstadoCuentaSp,
		ListaEstadoCuentaSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	private Integer RAMO59 = 59;
	private Integer RAMO55 = 55;
	private Integer RAMO58 = 58;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaEstadoCuentaRs> listaEstadoCuentaSp(Integer cod_suc,
			Integer cod_ramo, BigDecimal nro_pol, String fec_desde,
			String fec_hasta) throws SQLException {

		System.out.println("SP: sp_ws_estado_cuenta");

		PreparedStatement pstmt = null;
		List<ListaEstadoCuentaRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println("ZZZZ  " + dateFormat.format(date));

		try {
			pstmt = conn
					.prepareStatement("{call sp_ws_estado_cuenta(?,?,?,?,?)}");
			pstmt.setInt(1, cod_suc);
			pstmt.setInt(2, cod_ramo);
			pstmt.setBigDecimal(3, nro_pol);
			pstmt.setString(4, "01/01/2001");
			pstmt.setString(5, dateFormat.format(date));
			pstmt.setQueryTimeout(600);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<ListaEstadoCuentaRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						ListaEstadoCuentaRs aux = new ListaEstadoCuentaRs();

						if (RAMO55.equals(cod_ramo)) {
							aux.setNombreAsegurado(rs.getString(1));
							aux.setSucursal(rs.getString(2));
							aux.setTxtRamo(rs.getString(3));
							aux.setNroPol(rs.getBigDecimal(4));
							aux.setFechaVigenDesde(rs.getString(5));
							aux.setMoneda(rs.getString(6));
							aux.setFechaReserva(rs.getString(7));
							aux.setTxtConcepto(rs.getString(8));
							aux.setPjeInteres(rs.getBigDecimal(9));
							aux.setImpCredito(rs.getBigDecimal(10));
							aux.setImpDebito(rs.getBigDecimal(11));
							aux.setImpSaldo(rs.getBigDecimal(12));

						}

						if (RAMO58.equals(cod_ramo)) {

							aux.setIdProceso(rs.getInt(1));
							aux.setIdPvCero(rs.getInt(2));
							aux.setMoneda(rs.getString(3));
							aux.setNroPol(rs.getBigDecimal(4));
							aux.setFechaVigenDesde(rs.getString(5));
							aux.setCodAseg(rs.getBigDecimal(6));
							aux.setTxtAsegurado(rs.getString(7));
							aux.setCodAsegCont(rs.getInt(8));
							aux.setTxtContratante(rs.getString(9));
							aux.setTxtDireccion(rs.getString(10));
							aux.setTxtAgente(rs.getString(11));
							aux.setEdadIniVig(rs.getBigDecimal(12));
							aux.setFecNac(rs.getString(13));
							aux.setTxtSexo(rs.getString(14));
							aux.setTxtDescCond(rs.getString(15));
							aux.setCodProducto(rs.getBigDecimal(16));
							aux.setTxtProducto(rs.getString(17));
							aux.setImpSumaAseg(rs.getBigDecimal(18));
							aux.setCodFormaPago(rs.getBigDecimal(19));
							aux.setTxtFormaPago(rs.getString(20));
							aux.setImpRescateG(rs.getBigDecimal(21));
							aux.setImpPrestamo(rs.getBigDecimal(22));
							aux.setImpRescateCobBasica(rs.getBigDecimal(23));
							aux.setImpSaldoInicial(rs.getBigDecimal(24));
							aux.setImpSaldoFinal(rs.getBigDecimal(25));
							aux.setImpSaldoPrestamos(rs.getBigDecimal(26));
							aux.setImpCargosRescateApAd(rs.getBigDecimal(27));
							aux.setImpRescateApAd(rs.getBigDecimal(28));
							aux.setImpRescateTotal(rs.getBigDecimal(29));
							aux.setAaaa_proceso(rs.getBigDecimal(30));
							aux.setMm_proceso(rs.getBigDecimal(31));
							aux.setImpReserva(rs.getBigDecimal(32));
							aux.setImpGastos(rs.getBigDecimal(33));
							aux.setImpIE(rs.getBigDecimal(34));
							aux.setImpRetiro(rs.getBigDecimal(35));
							aux.setImpSaldoPrestamoCober(rs.getBigDecimal(36));
							aux.setImpPagoPrimas(rs.getBigDecimal(37));
							aux.setImpAjustes(rs.getBigDecimal(38));
							aux.setImpDisponible(rs.getBigDecimal(39));
							aux.setTxtConductoPago(rs.getString(40));
							aux.setPjeTasaInteres(rs.getBigDecimal(41));

						}

						if (RAMO59.equals(cod_ramo)) {

							aux.setIdProceso(rs.getInt(1));
							aux.setIdPvCero(rs.getInt(2));
							aux.setMoneda(rs.getString(3));
							aux.setNroPol(rs.getBigDecimal(4));
							aux.setFechaVigenDesde(rs.getString(5));
							aux.setCodAseg(rs.getBigDecimal(6));
							aux.setTxtAsegurado(rs.getString(7));
							aux.setCodAsegCont(rs.getInt(8));
							aux.setTxtContratante(rs.getString(9));
							aux.setTxtDireccion(rs.getString(10));
							aux.setTxtAgente(rs.getString(11));
							aux.setEdadIniVig(rs.getBigDecimal(12));
							aux.setFecNac(rs.getString(13));
							aux.setTxtSexo(rs.getString(14));
							aux.setTxtDescCond(rs.getString(15));
							aux.setCodProducto(rs.getBigDecimal(16));
							aux.setTxtProducto(rs.getString(17));
							aux.setImpSumaAseg(rs.getBigDecimal(18));
							aux.setCodFormaPago(rs.getBigDecimal(19));
							aux.setTxtFormaPago(rs.getString(20));
							aux.setImpRescateG(rs.getBigDecimal(21));
							aux.setImpPrestamo(rs.getBigDecimal(22));
							aux.setImpRescateCobBasica(rs.getBigDecimal(23));
							aux.setImpSaldoInicial(rs.getBigDecimal(24));
							aux.setImpSaldoFinal(rs.getBigDecimal(25));
							aux.setImpSaldoPrestamoApAd(rs.getBigDecimal(26));
							aux.setImpCargosRescateApAd(rs.getBigDecimal(27));
							aux.setImpRescateApAd(rs.getBigDecimal(28));
							aux.setImpRescateTotal(rs.getBigDecimal(29));
							aux.setAaaa_proceso(rs.getBigDecimal(30));
							aux.setMm_proceso(rs.getBigDecimal(31));
							aux.setImpReserva(rs.getBigDecimal(32));
							aux.setImpGastos(rs.getBigDecimal(33));
							aux.setImpIE(rs.getBigDecimal(34));
							aux.setImpRetiro(rs.getBigDecimal(35));
							aux.setImpSaldoPrestamos(rs.getBigDecimal(36));
							aux.setImpPagoPrimas(rs.getBigDecimal(37));
							aux.setImpAjustes(rs.getBigDecimal(38));
							aux.setImpDisponible(rs.getBigDecimal(39));
							aux.setImpDM(rs.getBigDecimal(40));
							aux.setImpBF(rs.getBigDecimal(41));
							aux.setTxtOpcion(rs.getString(42));
							aux.setTxtConductoPago(rs.getString(43));
							aux.setPjeTasaInteres(rs.getBigDecimal(44));
							aux.setFecDesde(rs.getString(45));
							aux.setFecHasta(rs.getString(46));
							aux.setFecGracia(rs.getString(47));
							aux.setFecCancelacion(rs.getString(48));

						}

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
		} finally {
			try {
				if (rs != null)
					rs.close();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} finally {
					if (conn != null) {
						conn.close();
					}
				}
			}
		}
		return respuesta;
	}

}
