package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsDisponibleRetiro implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal saldoCuenta;
	private BigDecimal saldoPrestamo;
	private BigDecimal costoRescate;
	private BigDecimal derechoRetiro;
	private BigDecimal valorRescate;
	private BigDecimal valorMaximoRetiro;
	private String mensaje;
	private String campo1;
	private String campo2;
	private String campo3;

	public BigDecimal getSaldoPrestamo() {
		return saldoPrestamo;
	}

	public void setSaldoPrestamo(BigDecimal saldoPrestamo) {
		this.saldoPrestamo = saldoPrestamo;
	}

	public BigDecimal getCostoRescate() {
		return costoRescate;
	}

	public void setCostoRescate(BigDecimal costoRescate) {
		this.costoRescate = costoRescate;
	}

	public BigDecimal getValorRescate() {
		return valorRescate;
	}

	public void setValorRescate(BigDecimal valorRescate) {
		this.valorRescate = valorRescate;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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

	public BigDecimal getDerechoRetiro() {
		return derechoRetiro;
	}

	public void setDerechoRetiro(BigDecimal derechoRetiro) {
		this.derechoRetiro = derechoRetiro;
	}

	public BigDecimal getValorMaximoRetiro() {
		return valorMaximoRetiro;
	}

	public void setValorMaximoRetiro(BigDecimal valorMaximoRetiro) {
		this.valorMaximoRetiro = valorMaximoRetiro;
	}

	public BigDecimal getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(BigDecimal saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

}