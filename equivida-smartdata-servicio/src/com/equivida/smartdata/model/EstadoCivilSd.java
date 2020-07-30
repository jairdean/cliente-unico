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

/**
 *
 * @author juan
 */
@Entity
@Table(name = "ESTADO_CIVIL")
public class EstadoCivilSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "COD_ESTADO_CIVIL")
	private Short codEstadoCivil;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 16)
	@Column(name = "ESTADO_CIVIL")
	private String estadoCivil;

	public EstadoCivilSd() {
	}

	public EstadoCivilSd(Short codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}

	public EstadoCivilSd(Short codEstadoCivil, String estadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
		this.estadoCivil = estadoCivil;
	}

	public Short getCodEstadoCivil() {
		return codEstadoCivil;
	}

	public void setCodEstadoCivil(Short codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codEstadoCivil != null ? codEstadoCivil.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EstadoCivilSd)) {
			return false;
		}
		EstadoCivilSd other = (EstadoCivilSd) object;
		if ((this.codEstadoCivil == null && other.codEstadoCivil != null)
				|| (this.codEstadoCivil != null && !this.codEstadoCivil.equals(other.codEstadoCivil))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.EstadoCivil[ codEstadoCivil=" + codEstadoCivil + " ]";
	}

}
