package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsInfoPoliza implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String descRamo;
	private BigDecimal numeroPoliza;
	private String inicioVigencia;
	private String finVigencia;
	private String nombreContratante;
	private String numDocContratante;
	private String nombreAseg;
	private String numDocAseg;
	private String condicion;
	private BigDecimal impValorAseg;
	private String pPago;
	private String ocupacion;
	private String estadoPoliza;
	private String tipo;
	private String campo1;
	private String campo2;
	private String campo3;

	public String getDescRamo() {
		return descRamo;
	}

	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}

	public BigDecimal getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(BigDecimal numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public String getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(String inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public String getFinVigencia() {
		return finVigencia;
	}

	public void setFinVigencia(String finVigencia) {
		this.finVigencia = finVigencia;
	}

	public String getNombreContratante() {
		return nombreContratante;
	}

	public void setNombreContratante(String nombreContratante) {
		this.nombreContratante = nombreContratante;
	}

	public String getNumDocContratante() {
		return numDocContratante;
	}

	public void setNumDocContratante(String numDocContratante) {
		this.numDocContratante = numDocContratante;
	}

	public String getNombreAseg() {
		return nombreAseg;
	}

	public void setNombreAseg(String nombreAseg) {
		this.nombreAseg = nombreAseg;
	}

	public String getNumDocAseg() {
		return numDocAseg;
	}

	public void setNumDocAseg(String numDocAseg) {
		this.numDocAseg = numDocAseg;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public BigDecimal getImpValorAseg() {
		return impValorAseg;
	}

	public void setImpValorAseg(BigDecimal impValorAseg) {
		this.impValorAseg = impValorAseg;
	}

	public String getpPago() {
		return pPago;
	}

	public void setpPago(String pPago) {
		this.pPago = pPago;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getEstadoPoliza() {
		return estadoPoliza;
	}

	public void setEstadoPoliza(String estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
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

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}