/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "ESTADO_CIVIL")
@NamedQueries({
		@NamedQuery(name = "EstadoCivil.findAll", query = "SELECT e FROM EstadoCivil e"),
		@NamedQuery(name = "EstadoCivil.findByCodEstadoCivil", query = "SELECT e FROM EstadoCivil e WHERE e.codEstadoCivil = :codEstadoCivil"),
		@NamedQuery(name = "EstadoCivil.findByEstadoCivil", query = "SELECT e FROM EstadoCivil e WHERE e.estadoCivil = :estadoCivil") })
public class EstadoCivil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8743724442095988962L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_ESTADO_CIVIL")
	private Short codEstadoCivil;
	@Basic(optional = false)
	@Column(name = "ESTADO_CIVIL")
	private String estadoCivil;

	@Column(name = "CODIGO_VISIBLE")
	private char codigoVisible;

	public EstadoCivil() {
	}

	public EstadoCivil(Short codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}

	public EstadoCivil(Short codEstadoCivil, String estadoCivil) {
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
		if (!(object instanceof EstadoCivil)) {
			return false;
		}
		EstadoCivil other = (EstadoCivil) object;
		if ((this.codEstadoCivil == null && other.codEstadoCivil != null)
				|| (this.codEstadoCivil != null && !this.codEstadoCivil
						.equals(other.codEstadoCivil))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.EstadoCivil[codEstadoCivil="
				+ codEstadoCivil + "]";
	}

	public char getCodigoVisible() {
		return codigoVisible;
	}

	public void setCodigoVisible(char codigoVisible) {
		this.codigoVisible = codigoVisible;
	}

}
