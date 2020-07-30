package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

public class RsConsultaPolizas implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String estadoPoliza;
	private BigDecimal numeroPoliza;
	private BigDecimal codigoSucursal;
	private BigDecimal codigoRamo;
	private String fechaDesde;
	private String fechaHasta;
	private BigDecimal codigoTipoAgente;
	private BigDecimal codigoAgente;
	private String nombreAgente;
	private String codigoTipoPersona;
	private String tipoAsegurado;
	private Integer numeroAsegurado;
	private Integer numeroPariente;
	private String campo1;
	private String campo2;
	private String campo3;
	private String numeroDocAgente;
	private String numeroDocContratante;
	
	
	/**
	 * @return the estadoPoliza
	 */
	@XmlElement(nillable = true)
	public String getEstadoPoliza() {
		return estadoPoliza;
	}

	/**
	 * @param estadoPoliza
	 *            the estadoPoliza to set
	 */
	public void setEstadoPoliza(String estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
	}

	/**
	 * @return the numeroPoliza
	 */
	@XmlElement(nillable = true)
	public BigDecimal getNumeroPoliza() {
		return numeroPoliza;
	}

	/**
	 * @param numeroPoliza
	 *            the numeroPoliza to set
	 */
	public void setNumeroPoliza(BigDecimal numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	/**
	 * @return the codigoSucursal
	 */
	@XmlElement(nillable = true)
	public BigDecimal getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * @param codigoSucursal
	 *            the codigoSucursal to set
	 */
	public void setCodigoSucursal(BigDecimal codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	/**
	 * @return the codigoRamo
	 */
	@XmlElement(nillable = true)
	public BigDecimal getCodigoRamo() {
		return codigoRamo;
	}

	/**
	 * @param codigoRamo
	 *            the codigoRamo to set
	 */
	public void setCodigoRamo(BigDecimal codigoRamo) {
		this.codigoRamo = codigoRamo;
	}

	/**
	 * @return the codigoTipoAgente
	 */
	@XmlElement(nillable = true)
	public BigDecimal getCodigoTipoAgente() {
		return codigoTipoAgente;
	}

	/**
	 * @param codigoTipoAgente
	 *            the codigoTipoAgente to set
	 */
	public void setCodigoTipoAgente(BigDecimal codigoTipoAgente) {
		this.codigoTipoAgente = codigoTipoAgente;
	}

	/**
	 * @return the codigoAgente
	 */
	@XmlElement(nillable = true)
	public BigDecimal getCodigoAgente() {
		return codigoAgente;
	}

	/**
	 * @param codigoAgente
	 *            the codigoAgente to set
	 */
	public void setCodigoAgente(BigDecimal codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	/**
	 * @return the nombreAgente
	 */
	@XmlElement(nillable = true)
	public String getNombreAgente() {
		return nombreAgente;
	}

	/**
	 * @param nombreAgente
	 *            the nombreAgente to set
	 */
	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}


	/**
	 * @return the fechaDesde
	 */
	@XmlElement(nillable = true)
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	@XmlElement(nillable = true)
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getCodigoTipoPersona() {
		return codigoTipoPersona;
	}

	public void setCodigoTipoPersona(String codigoTipoPersona) {
		this.codigoTipoPersona = codigoTipoPersona;
	}

	/**
	 * @return the tipoAsegurado
	 */
	public String getTipoAsegurado() {
		return tipoAsegurado;
	}

	/**
	 * @param tipoAsegurado the tipoAsegurado to set
	 */
	public void setTipoAsegurado(String tipoAsegurado) {
		this.tipoAsegurado = tipoAsegurado;
	}

	/**
	 * @return the numeroAsegurado
	 */
	public Integer getNumeroAsegurado() {
		return numeroAsegurado;
	}

	/**
	 * @param numeroAsegurado the numeroAsegurado to set
	 */
	public void setNumeroAsegurado(Integer numeroAsegurado) {
		this.numeroAsegurado = numeroAsegurado;
	}

	/**
	 * @return the numeroPariente
	 */
	public Integer getNumeroPariente() {
		return numeroPariente;
	}

	/**
	 * @param numeroPariente the numeroPariente to set
	 */
	public void setNumeroPariente(Integer numeroPariente) {
		this.numeroPariente = numeroPariente;
	}

	/**
	 * @return the campo1
	 */
	public String getCampo1() {
		return campo1;
	}

	/**
	 * @param campo1 the campo1 to set
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
	 * @param campo2 the campo2 to set
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
	 * @param campo3 the campo3 to set
	 */
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	public String getNumeroDocAgente() {
		return numeroDocAgente;
	}

	public void setNumeroDocAgente(String numeroDocAgente) {
		this.numeroDocAgente = numeroDocAgente;
	}

	public String getNumeroDocContratante() {
		return numeroDocContratante;
	}

	public void setNumeroDocContratante(String numeroDocContratante) {
		this.numeroDocContratante = numeroDocContratante;
	}

}