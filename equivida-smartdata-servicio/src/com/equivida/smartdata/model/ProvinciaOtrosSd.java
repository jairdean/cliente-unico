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
@Table(name = "PROVINCIA_OTROS")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProvinciaOtrosSd implements Serializable {
	
	@JoinColumn(name = "SEC_PROVINCIA", referencedColumnName = "SEC_PROVINCIA")
	@ManyToOne(optional = false)
	@XmlTransient
	private ProvinciaSd secProvincia;
	

	@Basic(optional = false)
	@Size(max = 2)
	@Column(name = "COD_PROVINCIA_IESS")
	private String codProvinciaIess;

	@Basic(optional = false)
	@Size(max = 3)
	@Column(name = "COD_PROVINCIA_SRI")
	private String codProvinciaSri;

	public ProvinciaOtrosSd() {
	}

	public ProvinciaOtrosSd(ProvinciaSd secProvincia) {
		this.secProvincia = secProvincia;
	}

	public ProvinciaOtrosSd(String codProvinciaIess, String codProvinciaSri) {
		this.codProvinciaIess = codProvinciaIess;
		this.codProvinciaSri = codProvinciaSri;
	}

	public ProvinciaSd getSecProvincia() {
		return secProvincia;
	}

	public void setSecProvincia(ProvinciaSd secProvincia) {
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
