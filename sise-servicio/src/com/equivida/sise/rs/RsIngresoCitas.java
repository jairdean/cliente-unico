package com.equivida.sise.rs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class RsIngresoCitas implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String atendido;
	private String numPoliza;
	private String nombreContratante;
	private String codTipoDocumento;
	private String numDocumento;
	private String primerApellido;
	private String segundoApellido;
	private String nombreAsegurado;
	private String numAsegurado;
	private String numPariente;
	private String plan;
	private String codPlan;
	private String red;
	private String codRed;
	private String fechaBaja;
	private String titular;
	private String mensaje;

	/**
	 * @return the atendido
	 */
	@XmlElement(nillable = true)
	public String getAtendido() {
		return atendido;
	}

	/**
	 * @param atendido
	 *            the atendido to set
	 */
	public void setAtendido(String atendido) {
		this.atendido = atendido;
	}

	/**
	 * @return the numPoliza
	 */
	@XmlElement(nillable = true)
	public String getNumPoliza() {
		return numPoliza;
	}

	/**
	 * @param numPoliza
	 *            the numPoliza to set
	 */
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}

	/**
	 * @return the nombreContratante
	 */
	@XmlElement(nillable = true)
	public String getNombreContratante() {
		return nombreContratante;
	}

	/**
	 * @param nombreContratante
	 *            the nombreContratante to set
	 */
	public void setNombreContratante(String nombreContratante) {
		this.nombreContratante = nombreContratante;
	}

	/**
	 * @return the codTipoDocumento
	 */
	@XmlElement(nillable = true)
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}

	/**
	 * @param codTipoDocumento
	 *            the codTipoDocumento to set
	 */
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}

	/**
	 * @return the numDocumento
	 */
	@XmlElement(nillable = true)
	public String getNumDocumento() {
		return numDocumento;
	}

	/**
	 * @param numDocumento
	 *            the numDocumento to set
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	/**
	 * @return the primerApellido
	 */
	@XmlElement(nillable = true)
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * @param primerApellido
	 *            the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * @return the numAsegurado
	 */
	@XmlElement(nillable = true)
	public String getNumAsegurado() {
		return numAsegurado;
	}

	/**
	 * @param numAsegurado
	 *            the numAsegurado to set
	 */
	public void setNumAsegurado(String numAsegurado) {
		this.numAsegurado = numAsegurado;
	}

	/**
	 * @return the numPariente
	 */
	@XmlElement(nillable = true)
	public String getNumPariente() {
		return numPariente;
	}

	/**
	 * @param numPariente
	 *            the numPariente to set
	 */
	public void setNumPariente(String numPariente) {
		this.numPariente = numPariente;
	}

	/**
	 * @return the plan
	 */
	@XmlElement(nillable = true)
	public String getPlan() {
		return plan;
	}

	/**
	 * @param plan
	 *            the plan to set
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}

	/**
	 * @return the codPlan
	 */
	@XmlElement(nillable = true)
	public String getCodPlan() {
		return codPlan;
	}

	/**
	 * @param codPlan
	 *            the codPlan to set
	 */
	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}

	/**
	 * @return the red
	 */
	@XmlElement(nillable = true)
	public String getRed() {
		return red;
	}

	/**
	 * @param red
	 *            the red to set
	 */
	public void setRed(String red) {
		this.red = red;
	}

	/**
	 * @return the codRed
	 */
	@XmlElement(nillable = true)
	public String getCodRed() {
		return codRed;
	}

	/**
	 * @param codRed
	 *            the codRed to set
	 */
	public void setCodRed(String codRed) {
		this.codRed = codRed;
	}

	/**
	 * @return the fechaBaja
	 */
	@XmlElement(nillable = true)
	public String getFechaBaja() {
		return fechaBaja;
	}

	/**
	 * @param fechaBaja
	 *            the fechaBaja to set
	 */
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	/**
	 * @return the titular
	 */
	@XmlElement(nillable = true)
	public String getTitular() {
		return titular;
	}

	/**
	 * @param titular
	 *            the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * @return the mensaje
	 */
	@XmlElement(nillable = true)
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the nombreAsegurado
	 */
	@XmlElement(nillable = true)
	public String getNombreAsegurado() {
		return nombreAsegurado;
	}

	/**
	 * @param nombreAsegurado the nombreAsegurado to set
	 */
	public void setNombreAsegurado(String nombreAsegurado) {
		this.nombreAsegurado = nombreAsegurado;
	}

	/**
	 * @return the segundoApellido
	 */
	@XmlElement(nillable = true)
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * @param segundoApellido the segundoApellido to set
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

}