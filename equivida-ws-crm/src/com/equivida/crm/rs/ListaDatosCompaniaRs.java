package com.equivida.crm.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class ListaDatosCompaniaRs implements Serializable {

	private static final long serialVersionUID = 8008906937764164202L;

	private String tipoDocumento;
	private String numeroDocumento;
	private Integer secuencialEmpresa;
	private String razonSocial;
	private String razonComercial;
	private String segmento;
	private BigDecimal primaPromedioAnual;
	private BigDecimal siniestralidadAcumulada;
	private String estadoCobranza;
	private String tipoCliente;
	private String tipoBroker;
	private Integer numeroPolizas;

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento
	 *            the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial
	 *            the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the razonComercial
	 */
	public String getRazonComercial() {
		return razonComercial;
	}

	/**
	 * @param razonComercial
	 *            the razonComercial to set
	 */
	public void setRazonComercial(String razonComercial) {
		this.razonComercial = razonComercial;
	}

	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento
	 *            the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * @return the primaPromedioAnual
	 */
	public BigDecimal getPrimaPromedioAnual() {
		return primaPromedioAnual;
	}

	/**
	 * @param primaPromedioAnual
	 *            the primaPromedioAnual to set
	 */
	public void setPrimaPromedioAnual(BigDecimal primaPromedioAnual) {
		this.primaPromedioAnual = primaPromedioAnual;
	}

	/**
	 * @return the siniestralidadAcumulada
	 */
	public BigDecimal getSiniestralidadAcumulada() {
		return siniestralidadAcumulada;
	}

	/**
	 * @param siniestralidadAcumulada
	 *            the siniestralidadAcumulada to set
	 */
	public void setSiniestralidadAcumulada(BigDecimal siniestralidadAcumulada) {
		this.siniestralidadAcumulada = siniestralidadAcumulada;
	}

	/**
	 * @return the estadoCobranza
	 */
	public String getEstadoCobranza() {
		return estadoCobranza;
	}

	/**
	 * @param estadoCobranza
	 *            the estadoCobranza to set
	 */
	public void setEstadoCobranza(String estadoCobranza) {
		this.estadoCobranza = estadoCobranza;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente
	 *            the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return the tipoBroker
	 */
	public String getTipoBroker() {
		return tipoBroker;
	}

	/**
	 * @param tipoBroker
	 *            the tipoBroker to set
	 */
	public void setTipoBroker(String tipoBroker) {
		this.tipoBroker = tipoBroker;
	}

	/**
	 * @return the numeroPolizas
	 */
	public Integer getNumeroPolizas() {
		return numeroPolizas;
	}

	/**
	 * @param numeroPolizas
	 *            the numeroPolizas to set
	 */
	public void setNumeroPolizas(Integer numeroPolizas) {
		this.numeroPolizas = numeroPolizas;
	}

	/**
	 * @return the secuencialEmpresa
	 */
	public Integer getSecuencialEmpresa() {
		return secuencialEmpresa;
	}

	/**
	 * @param secuencialEmpresa
	 *            the secuencialEmpresa to set
	 */
	public void setSecuencialEmpresa(Integer secuencialEmpresa) {
		this.secuencialEmpresa = secuencialEmpresa;
	}

}
