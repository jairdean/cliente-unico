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
 * @author juan
 */
@Entity
@Table(name = "PARROQUIA")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParroquiaSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "SEC_PARROQUIA")
	private Short secParroquia;
	@Size(max = 64)
	@Column(name = "PARROQUIA")
	private String parroquia;
	@Column(name = "CABECERA")
	private Character cabecera;
	@JoinColumn(name = "SEC_CANTON", referencedColumnName = "SEC_CANTON")
	@ManyToOne(optional = false)
	@XmlTransient
	private CantonSd secCanton;

	public ParroquiaSd() {
	}

	public ParroquiaSd(Short secParroquia) {
		this.secParroquia = secParroquia;
	}

	public Short getSecParroquia() {
		return secParroquia;
	}

	public void setSecParroquia(Short secParroquia) {
		this.secParroquia = secParroquia;
	}

	public String getParroquia() {
		return parroquia;
	}

	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	public Character getCabecera() {
		return cabecera;
	}

	public void setCabecera(Character cabecera) {
		this.cabecera = cabecera;
	}

	public CantonSd getSecCanton() {
		return secCanton;
	}

	public void setSecCanton(CantonSd secCanton) {
		this.secCanton = secCanton;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secParroquia != null ? secParroquia.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ParroquiaSd)) {
			return false;
		}
		ParroquiaSd other = (ParroquiaSd) object;
		if ((this.secParroquia == null && other.secParroquia != null)
				|| (this.secParroquia != null && !this.secParroquia.equals(other.secParroquia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.Parroquia[ secParroquia=" + secParroquia + " ]";
	}

}
