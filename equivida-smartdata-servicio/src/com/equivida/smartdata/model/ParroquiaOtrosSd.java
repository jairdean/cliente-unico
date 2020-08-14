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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jairo
 */
@Entity
@Table(name = "PARROQUIA_OTROS")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParroquiaOtrosSd implements Serializable {
	
	@JoinColumn(name = "SEC_PARROQUIA", referencedColumnName = "SEC_PARROQUIA")
	@ManyToOne(optional = false)
	@XmlTransient
	private ParroquiaSd secParroquia;

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

	public ParroquiaOtrosSd(ParroquiaSd secParroquia) {
		this.secParroquia = secParroquia;
	}

	public ParroquiaOtrosSd(String codParroquiaIess, String codParroquiaSri) {
		this.codParroquiaIess = codParroquiaIess;
		this.codParroquiaSri = codParroquiaSri;
	}

	public ParroquiaSd getSecParroquia() {
		return secParroquia;
	}

	public void setSecParroquia(ParroquiaSd secParroquia) {
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
