package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsInfoSaldosPolizas implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private Integer codSucursal;
	private Integer codRamo;
	private BigDecimal nroPoliza;
	private BigDecimal valorAnual;
	private Integer nroCuentasMora;
	private BigDecimal valorMora;
	private String periocidadPago;
	private String campo1;
	private String campo2;
	private String campo3;

	public Integer getCodSucursal() {
		return codSucursal;
	}

	public void setCodSucursal(Integer codSucursal) {
		this.codSucursal = codSucursal;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public BigDecimal getNroPoliza() {
		return nroPoliza;
	}

	public void setNroPoliza(BigDecimal nroPoliza) {
		this.nroPoliza = nroPoliza;
	}

	public BigDecimal getValorAnual() {
		return valorAnual;
	}

	public void setValorAnual(BigDecimal valorAnual) {
		this.valorAnual = valorAnual;
	}

	public Integer getNroCuentasMora() {
		return nroCuentasMora;
	}

	public void setNroCuentasMora(Integer nroCuentasMora) {
		this.nroCuentasMora = nroCuentasMora;
	}

	public BigDecimal getValorMora() {
		return valorMora;
	}

	public void setValorMora(BigDecimal valorMora) {
		this.valorMora = valorMora;
	}

	public String getCampo1() {
		return campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo2() {
		return campo2;
	}

	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	public String getCampo3() {
		return campo3;
	}

	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	public String getPeriocidadPago() {
		return periocidadPago;
	}

	public void setPeriocidadPago(String periocidadPago) {
		this.periocidadPago = periocidadPago;
	}

}