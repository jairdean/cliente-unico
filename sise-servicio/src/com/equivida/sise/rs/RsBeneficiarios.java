package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsBeneficiarios implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal nroAsegurado;
	private Integer nroPariente;
	private BigDecimal nroBeneficiario;
	private String apellido1;
	private String apellido2;
	private String nombre;
	private BigDecimal codDocumento;
	private String tipoDocumento;
	private String nroDocumento;
	private BigDecimal codParentesco;
	private String parentesco;
	private BigDecimal codLeyenda;
	private String tipoBeneficiario;
	private BigDecimal pjePartic;
	private String fechaAlta;
	private String fechaBaja;
	private String campo1;
	private String campo2;
	private String campo3;

	public BigDecimal getNroAsegurado() {
		return nroAsegurado;
	}

	public void setNroAsegurado(BigDecimal nroAsegurado) {
		this.nroAsegurado = nroAsegurado;
	}

	public Integer getNroPariente() {
		return nroPariente;
	}

	public void setNroPariente(Integer nroPariente) {
		this.nroPariente = nroPariente;
	}

	public BigDecimal getNroBeneficiario() {
		return nroBeneficiario;
	}

	public void setNroBeneficiario(BigDecimal nroBeneficiario) {
		this.nroBeneficiario = nroBeneficiario;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getCodDocumento() {
		return codDocumento;
	}

	public void setCodDocumento(BigDecimal codDocumento) {
		this.codDocumento = codDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public BigDecimal getCodParentesco() {
		return codParentesco;
	}

	public void setCodParentesco(BigDecimal codParentesco) {
		this.codParentesco = codParentesco;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public BigDecimal getCodLeyenda() {
		return codLeyenda;
	}

	public void setCodLeyenda(BigDecimal codLeyenda) {
		this.codLeyenda = codLeyenda;
	}

	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	public void setTipoBeneficiario(String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	public BigDecimal getPjePartic() {
		return pjePartic;
	}

	public void setPjePartic(BigDecimal pjePartic) {
		this.pjePartic = pjePartic;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
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

}