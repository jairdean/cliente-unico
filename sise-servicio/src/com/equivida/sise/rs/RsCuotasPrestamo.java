package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsCuotasPrestamo implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal numeroCuota;
	private BigDecimal impCapital;
	private BigDecimal impInteres;
	private BigDecimal impCuota;
	private String estado;
	private String fechaVencimiento;
	private BigDecimal impSaldo;

	public BigDecimal getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(BigDecimal numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public BigDecimal getImpCapital() {
		return impCapital;
	}

	public void setImpCapital(BigDecimal impCapital) {
		this.impCapital = impCapital;
	}

	public BigDecimal getImpInteres() {
		return impInteres;
	}

	public void setImpInteres(BigDecimal impInteres) {
		this.impInteres = impInteres;
	}

	public BigDecimal getImpCuota() {
		return impCuota;
	}

	public void setImpCuota(BigDecimal impCuota) {
		this.impCuota = impCuota;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getImpSaldo() {
		return impSaldo;
	}

	public void setImpSaldo(BigDecimal impSaldo) {
		this.impSaldo = impSaldo;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

}