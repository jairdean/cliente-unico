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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "PROFESION")
public class ProfesionSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_PROFESION")
	private Short secProfesion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 4)
	@Column(name = "COD_PROFESION_RC")
	private String codProfesionRc;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "PROFESION_RC")
	private String profesionRc;

	public ProfesionSd() {
	}

	public ProfesionSd(Short secProfesion) {
		this.secProfesion = secProfesion;
	}

	public ProfesionSd(Short secProfesion, String codProfesionRc, String profesionRc) {
		this.secProfesion = secProfesion;
		this.codProfesionRc = codProfesionRc;
		this.profesionRc = profesionRc;
	}

	public Short getSecProfesion() {
		return secProfesion;
	}

	public void setSecProfesion(Short secProfesion) {
		this.secProfesion = secProfesion;
	}

	public String getCodProfesionRc() {
		return codProfesionRc;
	}

	public void setCodProfesionRc(String codProfesionRc) {
		this.codProfesionRc = codProfesionRc;
	}

	public String getProfesionRc() {
		return profesionRc;
	}

	public void setProfesionRc(String profesionRc) {
		this.profesionRc = profesionRc;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secProfesion != null ? secProfesion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProfesionSd)) {
			return false;
		}
		ProfesionSd other = (ProfesionSd) object;
		if ((this.secProfesion == null && other.secProfesion != null)
				|| (this.secProfesion != null && !this.secProfesion.equals(other.secProfesion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.Profesion[ secProfesion=" + secProfesion + " ]";
	}

}
