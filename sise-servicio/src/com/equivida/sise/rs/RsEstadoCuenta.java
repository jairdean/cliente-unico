package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsEstadoCuenta implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String nombreAsegurado;
	private String fechaVigenDesde;
	private BigDecimal valorAsegurado;
	private BigDecimal saldoCuentaIndividual;
	private BigDecimal saldoDeudaPorPrestamo;
	private BigDecimal valorRescateTotal;
	private BigDecimal cargosPorRescate;
	private BigDecimal depositosEfectuados;
	private BigDecimal gastosAdmin;
	private BigDecimal deduccionMensual;
	private BigDecimal interesesAcreditados;
	private BigDecimal tasaRendimientoMensual;
	private BigDecimal saldo;
	private BigDecimal aaaa_proceso;
	private BigDecimal mm_proceso;
	private String campo1;
	private String campo2;
	private String campo3;
	//
	private String sucursal;
	private String txtRamo;
	private BigDecimal nroPol;
	private String moneda;
	private String fechaReserva;
	private String txtConcepto;
	private BigDecimal pjeInteres;
	private BigDecimal impCredito; 
	private BigDecimal impDebito; 
	private BigDecimal impSaldo;
	
	private Integer idProceso;	
	private Integer idPvCero;
	private BigDecimal codAseg;
	private String txtAsegurado;
	private Integer codAsegCont;
	private String txtContratante;
	private String txtDireccion;
	private String txtAgente;
	private BigDecimal edadIniVig;
	private String fecNac;
	private String txtSexo;
	private String txtDescCond;
	private BigDecimal codProducto;
	private String txtProducto;	
	private BigDecimal impSumaAseg;
	private BigDecimal codFormaPago;
	private String txtFormaPago;
	private BigDecimal impRescateG;
	private BigDecimal impPrestamo;
	private BigDecimal impRescateCobBasica;
	private BigDecimal impSaldoInicial;
	private BigDecimal impSaldoFinal;	
	private BigDecimal impSaldoPrestamos;
	private BigDecimal impCargosRescateApAd;
	private BigDecimal impRescateApAd;
	private BigDecimal impRescateTotal;
	private BigDecimal impReserva;
	private BigDecimal impGastos;
	private BigDecimal impIE;
	private BigDecimal impRetiro;
	private BigDecimal impSaldoPrestamoCober;
	private BigDecimal impPagoPrimas;
	private BigDecimal impAjustes;
	private BigDecimal impDisponible;
	private String txtConductoPago;	
	private BigDecimal pjeTasaInteres;
	private BigDecimal impDM;	
	private BigDecimal impBF;
	private String txtOpcion;
	private String fecHasta;
	private String fecGracia;
	private String fecCancelacion;
	private String fecDesde;
	private BigDecimal impSaldoPrestamoApAd;
	
	
	
	public BigDecimal getImpSaldoPrestamoApAd() {
		return impSaldoPrestamoApAd;
	}

	public void setImpSaldoPrestamoApAd(BigDecimal impSaldoPrestamoApAd) {
		this.impSaldoPrestamoApAd = impSaldoPrestamoApAd;
	}

	public String getFecDesde() {
		return fecDesde;
	}

	public void setFecDesde(String fecDesde) {
		this.fecDesde = fecDesde;
	}

	/**
	 * @return the nombreAsegurado
	 */
	public String getNombreAsegurado() {
		return nombreAsegurado;
	}

	/**
	 * @param nombreAsegurado
	 *            the nombreAsegurado to set
	 */
	public void setNombreAsegurado(String nombreAsegurado) {
		this.nombreAsegurado = nombreAsegurado;
	}

	/**
	 * @return the fechaVigenDesde
	 */
	public String getFechaVigenDesde() {
		return fechaVigenDesde;
	}

	/**
	 * @param fechaVigenDesde
	 *            the fechaVigenDesde to set
	 */
	public void setFechaVigenDesde(String fechaVigenDesde) {
		this.fechaVigenDesde = fechaVigenDesde;
	}

	/**
	 * @return the valorAsegurado
	 */
	public BigDecimal getValorAsegurado() {
		return valorAsegurado;
	}

	/**
	 * @param valorAsegurado
	 *            the valorAsegurado to set
	 */
	public void setValorAsegurado(BigDecimal valorAsegurado) {
		this.valorAsegurado = valorAsegurado;
	}

	/**
	 * @return the saldoCuentaIndividual
	 */
	public BigDecimal getSaldoCuentaIndividual() {
		return saldoCuentaIndividual;
	}

	/**
	 * @param saldoCuentaIndividual
	 *            the saldoCuentaIndividual to set
	 */
	public void setSaldoCuentaIndividual(BigDecimal saldoCuentaIndividual) {
		this.saldoCuentaIndividual = saldoCuentaIndividual;
	}

	/**
	 * @return the saldoDeudaPorPrestamo
	 */
	public BigDecimal getSaldoDeudaPorPrestamo() {
		return saldoDeudaPorPrestamo;
	}

	/**
	 * @param saldoDeudaPorPrestamo
	 *            the saldoDeudaPorPrestamo to set
	 */
	public void setSaldoDeudaPorPrestamo(BigDecimal saldoDeudaPorPrestamo) {
		this.saldoDeudaPorPrestamo = saldoDeudaPorPrestamo;
	}

	/**
	 * @return the valorRescateTotal
	 */
	public BigDecimal getValorRescateTotal() {
		return valorRescateTotal;
	}

	/**
	 * @param valorRescateTotal
	 *            the valorRescateTotal to set
	 */
	public void setValorRescateTotal(BigDecimal valorRescateTotal) {
		this.valorRescateTotal = valorRescateTotal;
	}

	/**
	 * @return the cargosPorRescate
	 */
	public BigDecimal getCargosPorRescate() {
		return cargosPorRescate;
	}

	/**
	 * @param cargosPorRescate
	 *            the cargosPorRescate to set
	 */
	public void setCargosPorRescate(BigDecimal cargosPorRescate) {
		this.cargosPorRescate = cargosPorRescate;
	}

	/**
	 * @return the depositosEfectuados
	 */
	public BigDecimal getDepositosEfectuados() {
		return depositosEfectuados;
	}

	/**
	 * @param depositosEfectuados
	 *            the depositosEfectuados to set
	 */
	public void setDepositosEfectuados(BigDecimal depositosEfectuados) {
		this.depositosEfectuados = depositosEfectuados;
	}

	/**
	 * @return the gastosAdmin
	 */
	public BigDecimal getGastosAdmin() {
		return gastosAdmin;
	}

	/**
	 * @param gastosAdmin
	 *            the gastosAdmin to set
	 */
	public void setGastosAdmin(BigDecimal gastosAdmin) {
		this.gastosAdmin = gastosAdmin;
	}

	/**
	 * @return the deduccionMensual
	 */
	public BigDecimal getDeduccionMensual() {
		return deduccionMensual;
	}

	/**
	 * @param deduccionMensual
	 *            the deduccionMensual to set
	 */
	public void setDeduccionMensual(BigDecimal deduccionMensual) {
		this.deduccionMensual = deduccionMensual;
	}

	/**
	 * @return the interesesAcreditados
	 */
	public BigDecimal getInteresesAcreditados() {
		return interesesAcreditados;
	}

	/**
	 * @param interesesAcreditados
	 *            the interesesAcreditados to set
	 */
	public void setInteresesAcreditados(BigDecimal interesesAcreditados) {
		this.interesesAcreditados = interesesAcreditados;
	}

	/**
	 * @return the tasaRendimientoMensual
	 */
	public BigDecimal getTasaRendimientoMensual() {
		return tasaRendimientoMensual;
	}

	/**
	 * @param tasaRendimientoMensual
	 *            the tasaRendimientoMensual to set
	 */
	public void setTasaRendimientoMensual(BigDecimal tasaRendimientoMensual) {
		this.tasaRendimientoMensual = tasaRendimientoMensual;
	}

	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo
	 *            the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the campo1
	 */
	public String getCampo1() {
		return campo1;
	}

	/**
	 * @param campo1
	 *            the campo1 to set
	 */
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	/**
	 * @return the campo2
	 */
	public String getCampo2() {
		return campo2;
	}

	/**
	 * @param campo2
	 *            the campo2 to set
	 */
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	/**
	 * @return the campo3
	 */
	public String getCampo3() {
		return campo3;
	}

	/**
	 * @param campo3
	 *            the campo3 to set
	 */
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}


	
	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getTxtRamo() {
		return txtRamo;
	}

	public void setTxtRamo(String txtRamo) {
		this.txtRamo = txtRamo;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getTxtConcepto() {
		return txtConcepto;
	}

	public void setTxtConcepto(String txtConcepto) {
		this.txtConcepto = txtConcepto;
	}



	public BigDecimal getNroPol() {
		return nroPol;
	}

	public void setNroPol(BigDecimal nroPol) {
		this.nroPol = nroPol;
	}

	public BigDecimal getPjeInteres() {
		return pjeInteres;
	}

	public void setPjeInteres(BigDecimal pjeInteres) {
		this.pjeInteres = pjeInteres;
	}

	public BigDecimal getImpCredito() {
		return impCredito;
	}

	public void setImpCredito(BigDecimal impCredito) {
		this.impCredito = impCredito;
	}

	public BigDecimal getImpDebito() {
		return impDebito;
	}

	public void setImpDebito(BigDecimal impDebito) {
		this.impDebito = impDebito;
	}

	public BigDecimal getImpSaldo() {
		return impSaldo;
	}

	public void setImpSaldo(BigDecimal impSaldo) {
		this.impSaldo = impSaldo;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Integer getIdPvCero() {
		return idPvCero;
	}

	public void setIdPvCero(Integer idPvCero) {
		this.idPvCero = idPvCero;
	}

	public BigDecimal getCodAaseg() {
		return codAseg;
	}

	public void setCodAseg(BigDecimal codAseg) {
		this.codAseg = codAseg;
	}

	public String getTxtAsegurado() {
		return txtAsegurado;
	}

	public void setTxtAsegurado(String txtAsegurado) {
		this.txtAsegurado = txtAsegurado;
	}

	public Integer getCodAsegCont() {
		return codAsegCont;
	}

	public void setCodAsegCont(Integer codAsegCont) {
		this.codAsegCont = codAsegCont;
	}

	public String getTxtContratante() {
		return txtContratante;
	}

	public void setTxtContratante(String txtContratante) {
		this.txtContratante = txtContratante;
	}

	public String getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(String txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public String getTxtAgente() {
		return txtAgente;
	}

	public void setTxtAgente(String txtAgente) {
		this.txtAgente = txtAgente;
	}

	public BigDecimal getEdadIniVig() {
		return edadIniVig;
	}

	public void setEdadIniVig(BigDecimal edadIniVig) {
		this.edadIniVig = edadIniVig;
	}

	public String getFecNac() {
		return fecNac;
	}

	public void setFecNac(String fecNac) {
		this.fecNac = fecNac;
	}

	public String getTxtSexo() {
		return txtSexo;
	}

	public void setTxtSexo(String txtSexo) {
		this.txtSexo = txtSexo;
	}

	public String getTxtDescCond() {
		return txtDescCond;
	}

	public void setTxtDescCond(String txtDescCond) {
		this.txtDescCond = txtDescCond;
	}

	public String getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(String txtProducto) {
		this.txtProducto = txtProducto;
	}

	public BigDecimal getImpSumaAseg() {
		return impSumaAseg;
	}

	public void setImpSumaAseg(BigDecimal impSumaAseg) {
		this.impSumaAseg = impSumaAseg;
	}

	public String getTxtFormaPago() {
		return txtFormaPago;
	}

	public void setTxtFormaPago(String txtFormaPago) {
		this.txtFormaPago = txtFormaPago;
	}

	public BigDecimal getImpRescateG() {
		return impRescateG;
	}

	public void setImpRescateG(BigDecimal impRescateG) {
		this.impRescateG = impRescateG;
	}

	public BigDecimal getImpPrestamo() {
		return impPrestamo;
	}

	public void setImpPrestamo(BigDecimal impPrestamo) {
		this.impPrestamo = impPrestamo;
	}

	public BigDecimal getImpRescateCobBasica() {
		return impRescateCobBasica;
	}

	public void setImpRescateCobBasica(BigDecimal impRescateCobBasica) {
		this.impRescateCobBasica = impRescateCobBasica;
	}

	public BigDecimal getImpSaldoInicial() {
		return impSaldoInicial;
	}

	public void setImpSaldoInicial(BigDecimal impSaldoInicial) {
		this.impSaldoInicial = impSaldoInicial;
	}

	public BigDecimal getImpSaldoFinal() {
		return impSaldoFinal;
	}

	public void setImpSaldoFinal(BigDecimal impSaldoFinal) {
		this.impSaldoFinal = impSaldoFinal;
	}

	public BigDecimal getImpSaldoPrestamos() {
		return impSaldoPrestamos;
	}

	public void setImpSaldoPrestamos(BigDecimal impSaldoPrestamos) {
		this.impSaldoPrestamos = impSaldoPrestamos;
	}

	public BigDecimal getImpCargosRescateApAd() {
		return impCargosRescateApAd;
	}

	public void setImpCargosRescateApAd(BigDecimal impCargosRescateApAd) {
		this.impCargosRescateApAd = impCargosRescateApAd;
	}

	public BigDecimal getImpRescateApAd() {
		return impRescateApAd;
	}

	public void setImpRescateApAd(BigDecimal impRescateApAd) {
		this.impRescateApAd = impRescateApAd;
	}

	public BigDecimal getImpRescateTotal() {
		return impRescateTotal;
	}

	public void setImpRescateTotal(BigDecimal impRescateTotal) {
		this.impRescateTotal = impRescateTotal;
	}

	public BigDecimal getImpReserva() {
		return impReserva;
	}

	public void setImpReserva(BigDecimal impReserva) {
		this.impReserva = impReserva;
	}

	public BigDecimal getImpGastos() {
		return impGastos;
	}

	public void setImpGastos(BigDecimal impGastos) {
		this.impGastos = impGastos;
	}

	public BigDecimal getImpIE() {
		return impIE;
	}

	public void setImpIE(BigDecimal impIE) {
		this.impIE = impIE;
	}

	public BigDecimal getImpRetiro() {
		return impRetiro;
	}

	public void setImpRetiro(BigDecimal impRetiro) {
		this.impRetiro = impRetiro;
	}

	public BigDecimal getImpSaldoPrestamoCober() {
		return impSaldoPrestamoCober;
	}

	public void setImpSaldoPrestamoCober(BigDecimal impSaldoPrestamoCober) {
		this.impSaldoPrestamoCober = impSaldoPrestamoCober;
	}

	public BigDecimal getImpPagoPrimas() {
		return impPagoPrimas;
	}

	public void setImpPagoPrimas(BigDecimal impPagoPrimas) {
		this.impPagoPrimas = impPagoPrimas;
	}

	public BigDecimal getImpAjustes() {
		return impAjustes;
	}

	public void setImpAjustes(BigDecimal impAjustes) {
		this.impAjustes = impAjustes;
	}

	public BigDecimal getImpDisponible() {
		return impDisponible;
	}

	public void setImpDisponible(BigDecimal impDisponible) {
		this.impDisponible = impDisponible;
	}

	public String getTxtConductoPago() {
		return txtConductoPago;
	}

	public void setTxtConductoPago(String txtConductoPago) {
		this.txtConductoPago = txtConductoPago;
	}

	public BigDecimal getPjeTasaInteres() {
		return pjeTasaInteres;
	}

	public void setPjeTasaInteres(BigDecimal pjeTasaInteres) {
		this.pjeTasaInteres = pjeTasaInteres;
	}

	public BigDecimal getAaaa_proceso() {
		return aaaa_proceso;
	}

	public void setAaaa_proceso(BigDecimal aaaa_proceso) {
		this.aaaa_proceso = aaaa_proceso;
	}

	public BigDecimal getMm_proceso() {
		return mm_proceso;
	}

	public void setMm_proceso(BigDecimal mm_proceso) {
		this.mm_proceso = mm_proceso;
	}

	public BigDecimal getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(BigDecimal codProducto) {
		this.codProducto = codProducto;
	}

	public BigDecimal getCodFormaPago() {
		return codFormaPago;
	}

	public void setCodFormaPago(BigDecimal codFormaPago) {
		this.codFormaPago = codFormaPago;
	}

	public BigDecimal getCodAseg() {
		return codAseg;
	}

	public BigDecimal getImpDM() {
		return impDM;
	}

	public void setImpDM(BigDecimal impDM) {
		this.impDM = impDM;
	}

	public BigDecimal getImpBF() {
		return impBF;
	}

	public void setImpBF(BigDecimal impBF) {
		this.impBF = impBF;
	}

	public String getTxtOpcion() {
		return txtOpcion;
	}

	public void setTxtOpcion(String txtOpcion) {
		this.txtOpcion = txtOpcion;
	}

	public String getFecHasta() {
		return fecHasta;
	}

	public void setFecHasta(String fecHasta) {
		this.fecHasta = fecHasta;
	}

	public String getFecGracia() {
		return fecGracia;
	}

	public void setFecGracia(String fecGracia) {
		this.fecGracia = fecGracia;
	}

	public String getFecCancelacion() {
		return fecCancelacion;
	}

	public void setFecCancelacion(String fecCancelacion) {
		this.fecCancelacion = fecCancelacion;
	}
	
}