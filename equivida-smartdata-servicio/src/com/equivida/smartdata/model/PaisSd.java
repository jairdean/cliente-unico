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
@Table(name = "PAIS")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaisSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "COD_PAIS")
	private Short codPais;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 64)
	@Column(name = "PAIS")
	private String pais;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "codPais")
	@XmlTransient
	private List<ProvinciaSd> provinciaList;

	public PaisSd() {
	}

	public PaisSd(Short codPais) {
		this.codPais = codPais;
	}

	public PaisSd(Short codPais, String pais) {
		this.codPais = codPais;
		this.pais = pais;
	}

	public Short getCodPais() {
		return codPais;
	}

	public void setCodPais(Short codPais) {
		this.codPais = codPais;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<ProvinciaSd> getProvinciaList() {
		return provinciaList;
	}

	public void setProvinciaList(List<ProvinciaSd> provinciaList) {
		this.provinciaList = provinciaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codPais != null ? codPais.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PaisSd)) {
			return false;
		}
		PaisSd other = (PaisSd) object;
		if ((this.codPais == null && other.codPais != null)
				|| (this.codPais != null && !this.codPais.equals(other.codPais))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.Pais[ codPais=" + codPais + " ]";
	}

}
