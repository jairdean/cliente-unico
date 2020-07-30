package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsEstadoPrestamo implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal codigoSucursal;
	private BigDecimal codigoRamo;
	private BigDecimal numeroPoliza;
	private Integer numeroPrestamo;
	private String fechaConcesion;
	private BigDecimal numeroTasa;
	private BigDecimal impCapital;
	private BigDecimal impInteres;
	private BigDecimal devoluciones;
	private BigDecimal impSaldo;
	private String descCond;
	private String tarjeta;
	private Integer snActivo;
	private String campoOut1;
	private String campoOut2;
	private String campoOut3;

	public BigDecimal getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(BigDecimal codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public BigDecimal getCodigoRamo() {
		return codigoRamo;
	}

	public void setCodigoRamo(BigDecimal codigoRamo) {
		this.codigoRamo = codigoRamo;
	}

	public BigDecimal getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(BigDecimal numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public Integer getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(Integer numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public String getFechaConcesion() {
		return fechaConcesion;
	}

	public void setFechaConcesion(String fechaConcesion) {
		this.fechaConcesion = fechaConcesion;
	}

	public BigDecimal getNumeroTasa() {
		return numeroTasa;
	}

	public void setNumeroTasa(BigDecimal numeroTasa) {
		this.numeroTasa = numeroTasa;
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

	public BigDecimal getDevoluciones() {
		return devoluciones;
	}

	public void setDevoluciones(BigDecimal devoluciones) {
		this.devoluciones = devoluciones;
	}

	public BigDecimal getImpSaldo() {
		return impSaldo;
	}

	public void setImpSaldo(BigDecimal impSaldo) {
		this.impSaldo = impSaldo;
	}

	public Integer getSnActivo() {
		return snActivo;
	}

	public void setSnActivo(Integer snActivo) {
		this.snActivo = snActivo;
	}

	public String getCampoOut1() {
		return campoOut1;
	}

	public void setCampoOut1(String campoOut1) {
		this.campoOut1 = campoOut1;
	}

	public String getCampoOut2() {
		return campoOut2;
	}

	public void setCampoOut2(String campoOut2) {
		this.campoOut2 = campoOut2;
	}

	public String getCampoOut3() {
		return campoOut3;
	}

	public void setCampoOut3(String campoOut3) {
		this.campoOut3 = campoOut3;
	}

	public String getDescCond() {
		return descCond;
	}

	public void setDescCond(String descCond) {
		this.descCond = descCond;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

}