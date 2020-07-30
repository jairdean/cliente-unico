package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsConductoPago implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String conductoPago;
	private String numerCuentaTarjeta;
	private String Anio;
	private String Mes;
	private BigDecimal implimiteTarjeta;
	private String tipoCuenta;
	private Integer campo;
	private String estado;
	private BigDecimal numeroSeguridad;

	public String getConductoPago() {
		return conductoPago;
	}

	public void setConductoPago(String conductoPago) {
		this.conductoPago = conductoPago;
	}

	public String getNumerCuentaTarjeta() {
		return numerCuentaTarjeta;
	}

	public void setNumerCuentaTarjeta(String numerCuentaTarjeta) {
		this.numerCuentaTarjeta = numerCuentaTarjeta;
	}

	public String getAnio() {
		return Anio;
	}

	public void setAnio(String anio) {
		Anio = anio;
	}

	public String getMes() {
		return Mes;
	}

	public void setMes(String mes) {
		Mes = mes;
	}

	public BigDecimal getImplimiteTarjeta() {
		return implimiteTarjeta;
	}

	public void setImplimiteTarjeta(BigDecimal implimiteTarjeta) {
		this.implimiteTarjeta = implimiteTarjeta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Integer getCampo() {
		return campo;
	}

	public void setCampo(Integer campo) {
		this.campo = campo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getNumeroSeguridad() {
		return numeroSeguridad;
	}

	public void setNumeroSeguridad(BigDecimal numeroSeguridad) {
		this.numeroSeguridad = numeroSeguridad;
	}

}