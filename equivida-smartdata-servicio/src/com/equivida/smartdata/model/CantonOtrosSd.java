/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@SuppressWarnings("serial")
@Entity
@Table(name = "CANTON_OTROS")
public class CantonOtrosSd implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_CANTON")
	private Short secCanton;

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

	public CantonOtrosSd(Short secCanton) {
		this.secCanton = secCanton;
	}

	public CantonOtrosSd(Short secCanton, String codCantonIess, String codCantonSri) {
		this.secCanton = secCanton;
		this.codCantonIess = codCantonIess;
		this.codCantonSri = codCantonSri;
	}

	public Short getSecCanton() {
		return secCanton;
	}

	public void setSecCanton(Short secCanton) {
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
