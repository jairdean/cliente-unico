/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

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

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "CIUDAD")
public class Ciudad implements Serializable {

	private static final long serialVersionUID = -9219088609606050607L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_CIUDAD")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secCiudad;
	@Basic(optional = false)
	@Column(name = "CIUDAD", length = 32)
	private String ciudad;

	@JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS")
	@ManyToOne(optional = false)
	private Pais pais;

	public Ciudad() {
	}

	public Ciudad(Short secCiudad) {
		this.secCiudad = secCiudad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Short getSecCiudad() {
		return secCiudad;
	}

	public void setSecCiudad(Short secCiudad) {
		this.secCiudad = secCiudad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result
				+ ((secCiudad == null) ? 0 : secCiudad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (secCiudad == null) {
			if (other.secCiudad != null)
				return false;
		} else if (!secCiudad.equals(other.secCiudad))
			return false;
		return true;
	}

	/**
	 * No cambiar! Clase que sirve para el componente autocomplete.
	 */
	@Override
	public String toString() {
		return ciudad;
	}
}