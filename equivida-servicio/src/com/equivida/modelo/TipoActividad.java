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
@Table(name = "TIPO_ACTIVIDAD")
@NamedQueries({
		@NamedQuery(name = "TipoActividad.findAll", query = "SELECT t FROM TipoActividad t"),
		@NamedQuery(name = "TipoActividad.findByCodActividad", query = "SELECT t FROM TipoActividad t WHERE t.codActividad = :codActividad"),
		@NamedQuery(name = "TipoActividad.findByActividad", query = "SELECT t FROM TipoActividad t WHERE t.actividad = :actividad") })
public class TipoActividad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8647400874974715105L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_ACTIVIDAD")
	private Short codActividad;
	@Column(name = "ACTIVIDAD")
	private String actividad;
	@Basic(optional = false)
	@Column(name = "PREGUNTA")
	private String pregunta;//Ej: Detalle o Frecuencia. Si es N entonces no pregunta detalle

	public TipoActividad() {
	}

	public TipoActividad(Short codActividad) {
		this.codActividad = codActividad;
	}

	public Short getCodActividad() {
		return codActividad;
	}

	public void setCodActividad(Short codActividad) {
		this.codActividad = codActividad;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codActividad != null ? codActividad.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TipoActividad)) {
			return false;
		}
		TipoActividad other = (TipoActividad) object;
		if ((this.codActividad == null && other.codActividad != null)
				|| (this.codActividad != null && !this.codActividad
						.equals(other.codActividad))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.TipoActividad[codActividad=" + codActividad
				+ "]";
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
}
