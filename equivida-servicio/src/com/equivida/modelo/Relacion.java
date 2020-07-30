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

import com.equivida.constant.EstadoEnum;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "RELACION")
@NamedQueries({ @NamedQuery(name = "Relacion.findAll", query = "SELECT r FROM Relacion r"),
		@NamedQuery(name = "Relacion.findBySecRelacion", query = "SELECT r FROM Relacion r WHERE r.secRelacion = :secRelacion") })
public class Relacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5111111080463281270L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_RELACION")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secRelacion;
	@JoinColumn(name = "COD_TIPO_PARENTESCO", referencedColumnName = "COD_TIPO_PARENTESCO")
	@ManyToOne(optional = false)
	private TipoParentescoRelacion tipoParentescoRelacion;
	@JoinColumn(name = "SEC_PERSONA_RELACION", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	private Persona persona;// el pariente
	@JoinColumn(name = "SEC_PERSONA_PRINCIPAL", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	private Persona persona1;// la principal

	@Column(name = "ESTADO")
	private char estado;// A-I

	@Transient
	public boolean isMuestraNombres() {
		if (getPersona().getPersonaNaturalTransient() != null
				&& (getPersona().getPersonaNaturalTransient().getTipoIdentificacion().isCedula()
						|| getPersona().getPersonaNaturalTransient().getTipoIdentificacion().isPasaporte())) {
			return true;
		}
		return false;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public Relacion() {
	}

	public Relacion(Integer secRelacion) {
		this.secRelacion = secRelacion;
	}

	public Integer getSecRelacion() {
		return secRelacion;
	}

	public void setSecRelacion(Integer secRelacion) {
		this.secRelacion = secRelacion;
	}

	public TipoParentescoRelacion getTipoParentescoRelacion() {
		return tipoParentescoRelacion;
	}

	public void setTipoParentescoRelacion(TipoParentescoRelacion tipoParentescoRelacion) {
		this.tipoParentescoRelacion = tipoParentescoRelacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Persona getPersona1() {
		return persona1;
	}

	public void setPersona1(Persona persona1) {
		this.persona1 = persona1;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Relacion[secRelacion=" + secRelacion + "]";
	}

	@Transient
	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + estado;
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		result = prime * result + ((persona1 == null) ? 0 : persona1.hashCode());
		result = prime * result + ((secRelacion == null) ? 0 : secRelacion.hashCode());
		result = prime * result + ((tipoParentescoRelacion == null) ? 0 : tipoParentescoRelacion.hashCode());
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
		Relacion other = (Relacion) obj;
		if (estado != other.estado)
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		if (persona1 == null) {
			if (other.persona1 != null)
				return false;
		} else if (!persona1.equals(other.persona1))
			return false;
		if (secRelacion == null) {
			if (other.secRelacion != null)
				return false;
		} else if (!secRelacion.equals(other.secRelacion))
			return false;
		if (tipoParentescoRelacion == null) {
			if (other.tipoParentescoRelacion != null)
				return false;
		} else if (!tipoParentescoRelacion.equals(other.tipoParentescoRelacion))
			return false;
		return true;
	}
}
