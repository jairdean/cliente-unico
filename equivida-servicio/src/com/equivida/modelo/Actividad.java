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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.equivida.constant.EstadoEnum;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "ACTIVIDAD")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
		@NamedQuery(name = "Actividad.findBySecActividad", query = "SELECT a FROM Actividad a WHERE a.secActividad = :secActividad"),
		@NamedQuery(name = "Actividad.findByEstado", query = "SELECT a FROM Actividad a WHERE a.estado = :estado") })
public class Actividad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8432119877249977893L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_ACTIVIDAD")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secActividad;
	@Basic(optional = false)
	@Column(name = "ESTADO")
	@XmlTransient
	private char estado;// para historico, es decir si alguna vez en la bdd
						// estaba activo A y cambia, entonces que se quede en
						// estado I de inactivos
	@Column(name = "DETALLE", length = 64)
	private String detalle;// para guardar el detalle de otras(detalle) o viajes
							// (frecuencia)

	@Basic(optional = false)
	@JoinColumn(name = "COD_ACTIVIDAD", referencedColumnName = "COD_ACTIVIDAD")
	@ManyToOne(optional = false)
	private TipoActividad tipoActividad;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	@Transient
	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo();
	}

	@Transient
	private boolean seleccionado;

	public Actividad() {
	}

	public Actividad(Integer secActividad) {
		this.secActividad = secActividad;
	}

	public Integer getSecActividad() {
		return secActividad;
	}

	public void setSecActividad(Integer secActividad) {
		this.secActividad = secActividad;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public TipoActividad getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(TipoActividad tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secActividad != null ? secActividad.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Actividad)) {
			return false;
		}
		Actividad other = (Actividad) object;
		if ((this.secActividad == null && other.secActividad != null)
				|| (this.secActividad != null && !this.secActividad
						.equals(other.secActividad))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Actividad[secActividad=" + secActividad
				+ "]";
	}

	public boolean isSeleccionado() {
		if (this.getActivo()) {
			seleccionado = true;
		} else {
			seleccionado = false;
		}
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		if (seleccionado) {
			this.setEstado(EstadoEnum.ACTIVO.getCodigo());
		} else {
			this.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
		this.seleccionado = seleccionado;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

}
