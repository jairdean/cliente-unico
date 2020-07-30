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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "ESPECIALIDAD_MEDICA")
@NamedQueries({
		@NamedQuery(name = "EspecialidadMedica.findAll", query = "SELECT e FROM EspecialidadMedica e"),
		@NamedQuery(name = "EspecialidadMedica.findBySecEspecialidad", query = "SELECT e FROM EspecialidadMedica e WHERE e.secEspecialidad = :secEspecialidad"),
		@NamedQuery(name = "EspecialidadMedica.findByEspecialidad", query = "SELECT e FROM EspecialidadMedica e WHERE e.especialidad = :especialidad") })
public class EspecialidadMedica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1596251757864104845L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_ESPECIALIDAD")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secEspecialidad;
	@Basic(optional = false)
	@Column(name = "ESPECIALIDAD")
	private String especialidad;

	public EspecialidadMedica() {
	}

	public EspecialidadMedica(Short secEspecialidad) {
		this.secEspecialidad = secEspecialidad;
	}

	public EspecialidadMedica(Short secEspecialidad, String especialidad) {
		this.secEspecialidad = secEspecialidad;
		this.especialidad = especialidad;
	}

	public Short getSecEspecialidad() {
		return secEspecialidad;
	}

	public void setSecEspecialidad(Short secEspecialidad) {
		this.secEspecialidad = secEspecialidad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secEspecialidad != null ? secEspecialidad.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof EspecialidadMedica)) {
			return false;
		}
		EspecialidadMedica other = (EspecialidadMedica) object;
		if ((this.secEspecialidad == null && other.secEspecialidad != null)
				|| (this.secEspecialidad != null && !this.secEspecialidad
						.equals(other.secEspecialidad))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.EspecialidadMedica[secEspecialidad="
				+ secEspecialidad + "]";
	}

}
