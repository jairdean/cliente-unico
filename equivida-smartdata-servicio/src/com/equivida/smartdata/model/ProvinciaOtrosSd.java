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
@Table(name = "PROVINCIA_OTROS")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProvinciaOtrosSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "SEC_PROVINCIA")
	private Short secProvincia;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 2)
	@Column(name = "COD_PROVINCIA_IESS")
	private String codProvinciaIess;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 3)
	@Column(name = "COD_PROVINCIA_SRI")
	private String codProvinciaSri;

	public ProvinciaOtrosSd() {
	}

	public ProvinciaOtrosSd(Short secProvincia) {
		this.secProvincia = secProvincia;
	}

	public ProvinciaOtrosSd(Short secProvincia, String codProvinciaIess, String codProvinciaSri) {
		this.secProvincia = secProvincia;
		this.codProvinciaIess = codProvinciaIess;
		this.codProvinciaSri = codProvinciaSri;
	}

	public Short getSecProvincia() {
		return secProvincia;
	}

	public void setSecProvincia(Short secProvincia) {
		this.secProvincia = secProvincia;
	}

	// IESS
	public String getCodProvinciaIess() {
		return codProvinciaIess;
	}

	public void setCodProvinciaIess(String codProvinciaIess) {
		this.codProvinciaIess = codProvinciaIess;
	}

	// SRI
	public String getCodProvinciaSri() {
		return codProvinciaSri;
	}

	public void setCodProvinciaSri(String codProvinciaSri) {
		this.codProvinciaSri = codProvinciaSri;
	}

}
