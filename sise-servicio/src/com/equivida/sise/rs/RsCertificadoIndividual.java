package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsCertificadoIndividual implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal nroAsegurado;
	private String apellido1;
	private String apellido2;
	private String nombre;
	private String nroDocumento;
	private BigDecimal codSucursal;
	private BigDecimal codRamo;
	private BigDecimal nroPoliza;
	private String vigenciaDesde;
	private String vigenciaHasta;
	private String contratante;
	private String cobertura;
	private BigDecimal valorAsegurado;
	private String campo1;
	private String campo2;
	private String campo3;

	/**
	 * @return the nroAsegurado
	 */
	public BigDecimal getNroAsegurado() {
		return nroAsegurado;
	}

	/**
	 * @param nroAsegurado
	 *            the nroAsegurado to set
	 */
	public void setNroAsegurado(BigDecimal nroAsegurado) {
		this.nroAsegurado = nroAsegurado;
	}

	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1
	 *            the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @param apellido2
	 *            the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nroDocumento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * @param nroDocumento
	 *            the nroDocumento to set
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * @return the codSucursal
	 */
	public BigDecimal getCodSucursal() {
		return codSucursal;
	}

	/**
	 * @param codSucursal
	 *            the codSucursal to set
	 */
	public void setCodSucursal(BigDecimal codSucursal) {
		this.codSucursal = codSucursal;
	}

	/**
	 * @return the codRamo
	 */
	public BigDecimal getCodRamo() {
		return codRamo;
	}

	/**
	 * @param codRamo
	 *            the codRamo to set
	 */
	public void setCodRamo(BigDecimal codRamo) {
		this.codRamo = codRamo;
	}

	/**
	 * @return the nroPoliza
	 */
	public BigDecimal getNroPoliza() {
		return nroPoliza;
	}

	/**
	 * @param nroPoliza
	 *            the nroPoliza to set
	 */
	public void setNroPoliza(BigDecimal nroPoliza) {
		this.nroPoliza = nroPoliza;
	}

	/**
	 * @return the contratante
	 */
	public String getContratante() {
		return contratante;
	}

	/**
	 * @param contratante
	 *            the contratante to set
	 */
	public void setContratante(String contratante) {
		this.contratante = contratante;
	}

	/**
	 * @return the cobertura
	 */
	public String getCobertura() {
		return cobertura;
	}

	/**
	 * @param cobertura
	 *            the cobertura to set
	 */
	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
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

	/**
	 * @return the vigenciaDesde
	 */
	public String getVigenciaDesde() {
		return vigenciaDesde;
	}

	/**
	 * @param vigenciaDesde the vigenciaDesde to set
	 */
	public void setVigenciaDesde(String vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	/**
	 * @return the vigenciaHasta
	 */
	public String getVigenciaHasta() {
		return vigenciaHasta;
	}

	/**
	 * @param vigenciaHasta the vigenciaHasta to set
	 */
	public void setVigenciaHasta(String vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

}