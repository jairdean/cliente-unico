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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "OCUPACION")
@NamedQueries({
		@NamedQuery(name = "Ocupacion.findAll", query = "SELECT o FROM Ocupacion o"),
		@NamedQuery(name = "Ocupacion.findByCodOcupacion", query = "SELECT o FROM Ocupacion o WHERE o.codOcupacion = :codOcupacion"),
		@NamedQuery(name = "Ocupacion.findByOcupacion", query = "SELECT o FROM Ocupacion o WHERE o.ocupacion = :ocupacion") })
public class Ocupacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6671009276477943422L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_OCUPACION")
	private Short codOcupacion;
	@Basic(optional = false)
	@Column(name = "OCUPACION")
	private String ocupacion;

	@ManyToOne
	@JoinColumn(name = "COD_TIPO_RIESGO", referencedColumnName = "COD_TIPO_RIESGO")
	private TipoRiesgo tipoRiesgo;
	
	@Column(name = "CODIGO_VISIBLE")
	private char codigoVisible;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "ocupacion")
	// private Collection<PersonaNatural> personaNaturalCollection;

	public Ocupacion() {
	}

	public Ocupacion(Short codOcupacion) {
		this.codOcupacion = codOcupacion;
	}

	public Ocupacion(Short codOcupacion, String ocupacion) {
		this.codOcupacion = codOcupacion;
		this.ocupacion = ocupacion;
	}

	public Short getCodOcupacion() {
		return codOcupacion;
	}

	public void setCodOcupacion(Short codOcupacion) {
		this.codOcupacion = codOcupacion;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	// public Collection<PersonaNatural> getPersonaNaturalCollection() {
	// return personaNaturalCollection;
	// }

	// public void setPersonaNaturalCollection(
	// Collection<PersonaNatural> personaNaturalCollection) {
	// this.personaNaturalCollection = personaNaturalCollection;
	// }

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codOcupacion != null ? codOcupacion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Ocupacion)) {
			return false;
		}
		Ocupacion other = (Ocupacion) object;
		if ((this.codOcupacion == null && other.codOcupacion != null)
				|| (this.codOcupacion != null && !this.codOcupacion
						.equals(other.codOcupacion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Ocupacion[codOcupacion=" + codOcupacion
				+ "]";
	}

	public TipoRiesgo getTipoRiesgo() {
		return tipoRiesgo;
	}

	public void setTipoRiesgo(TipoRiesgo tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}

	public char getCodigoVisible() {
		return codigoVisible;
	}

	public void setCodigoVisible(char codigoVisible) {
		this.codigoVisible = codigoVisible;
	}

}
