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
@Table(name = "CANTON_OTROS")
@XmlAccessorType(XmlAccessType.FIELD)
public class CantonOtrosSd implements Serializable {
	
	@JoinColumn(name = "SEC_CANTON", referencedColumnName = "SEC_CANTON")
	@ManyToOne(optional = false)
	@XmlTransient
	private CantonSd secCanton;

	@Basic(optional = false)
	@Size(max = 4)
	@Column(name = "COD_CANTON_IESS")
	private String codCantonIess;

	@Basic(optional = false)
	@Size(max = 5)
	@Column(name = "COD_CANTON_SRI")
	private String codCantonSri;

	public CantonOtrosSd() {
	}

	public CantonOtrosSd(CantonSd secCanton) {
		this.secCanton = secCanton;
	}

	public CantonOtrosSd(String codCantonIess, String codCantonSri) {
		this.codCantonIess = codCantonIess;
		this.codCantonSri = codCantonSri;
	}

	public CantonSd getSecCanton() {
		return secCanton;
	}

	public void setSecCanton(CantonSd secCanton) {
		this.secCanton = secCanton;
	}

	// IESS
	public String getCodCantonIess() {
		return codCantonIess;
	}

	public void setCodCantonIess(String codCantonIess) {
		this.codCantonIess = codCantonIess;
	}

	// SRI
	public String getCodCantonSri() {
		return codCantonSri;
	}

	public void setCodCantonSri(String codCantonSri) {
		this.codCantonSri = codCantonSri;
	}

}
