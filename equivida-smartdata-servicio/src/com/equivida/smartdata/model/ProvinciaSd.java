/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "PROVINCIA")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProvinciaSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "SEC_PROVINCIA")
	private Short secProvincia;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "PROVINCIA")
	private String provincia;
	@Size(max = 2)
	@Column(name = "COD_AREA")
	private String codArea;
	@JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS")
	@ManyToOne(optional = false)
	@XmlTransient
	private PaisSd codPais;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "secProvincia")
	@XmlTransient
	private List<CantonSd> cantonList;

	public ProvinciaSd() {
	}

	public ProvinciaSd(Short secProvincia) {
		this.secProvincia = secProvincia;
	}

	public ProvinciaSd(Short secProvincia, String provincia) {
		this.secProvincia = secProvincia;
		this.provincia = provincia;
	}

	public Short getSecProvincia() {
		return secProvincia;
	}

	public void setSecProvincia(Short secProvincia) {
		this.secProvincia = secProvincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodArea() {
		return codArea;
	}

	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

	public PaisSd getCodPais() {
		return codPais;
	}

	public void setCodPais(PaisSd codPais) {
		this.codPais = codPais;
	}

	public List<CantonSd> getCantonList() {
		return cantonList;
	}

	public void setCantonList(List<CantonSd> cantonList) {
		this.cantonList = cantonList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secProvincia != null ? secProvincia.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProvinciaSd)) {
			return false;
		}
		ProvinciaSd other = (ProvinciaSd) object;
		if ((this.secProvincia == null && other.secProvincia != null)
				|| (this.secProvincia != null && !this.secProvincia.equals(other.secProvincia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.Provincia[ secProvincia=" + secProvincia + " ]";
	}

}
