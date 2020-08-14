/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jairo
 */
@Entity
@Table(name = "PARROQUIA_OTROS")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParroquiaOtrosSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "SEC_PARROQUIA")
	private Short secParroquia;

	@Basic(optional = false)
	@Size(max = 6)
	@Column(name = "COD_PARROQUIA_IESS")
	private String codParroquiaIess;

	@Basic(optional = false)
	@Size(max = 7)
	@Column(name = "COD_PARROQUIA_SRI")
	private String codParroquiaSri;

	public ParroquiaOtrosSd() {
	}

	public ParroquiaOtrosSd(Short secParroquia) {
		this.secParroquia = secParroquia;
	}

	public ParroquiaOtrosSd(Short secParroquia, String codParroquiaIess, String codParroquiaSri) {
		this.secParroquia = secParroquia;
		this.codParroquiaIess = codParroquiaIess;
		this.codParroquiaSri = codParroquiaSri;
	}

	public Short getSecParroquia() {
		return secParroquia;
	}

	public void setSecParroquia(Short secParroquia) {
		this.secParroquia = secParroquia;
	}

	// IESS
	public String getCodParroquiaIess() {
		return codParroquiaIess;
	}

	public void setCodParroquiaIess(String codParroquiaIess) {
		this.codParroquiaIess = codParroquiaIess;
	}

	// SRI
	public String getCodParroquiaSri() {
		return codParroquiaSri;
	}

	public void setCodParroquiaSri(String codParroquiaSri) {
		this.codParroquiaSri = codParroquiaSri;
	}

}
