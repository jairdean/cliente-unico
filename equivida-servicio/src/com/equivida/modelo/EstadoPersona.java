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
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "ESTADO_PERSONA")
public class EstadoPersona implements Serializable {

	private static final long serialVersionUID = -7731682731581875254L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_ESTADO_PERSONA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secEstadoPersona;

	@Column(name = "OBSERVACIONES", length = 128)
	private String observaciones;// para guardar el detalle de otras(detalle) o
									// viajes (frecuencia)

	@Basic(optional = false)
	@JoinColumn(name = "COD_ESTADO", referencedColumnName = "COD_ESTADO")
	@ManyToOne(optional = false)
	private TipoEstado tipoEstado;

	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	private PersonaNatural personaNatural;

	public EstadoPersona() {
	}

	public Integer getSecEstadoPersona() {
		return secEstadoPersona;
	}

	public void setSecEstadoPersona(Integer secEstadoPersona) {
		this.secEstadoPersona = secEstadoPersona;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public TipoEstado getTipoEstado() {
		return tipoEstado;
	}

	public void setTipoEstado(TipoEstado tipoEstado) {
		this.tipoEstado = tipoEstado;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((observaciones == null) ? 0 : observaciones.hashCode());
		result = prime * result
				+ ((personaNatural == null) ? 0 : personaNatural.hashCode());
		result = prime
				* result
				+ ((secEstadoPersona == null) ? 0 : secEstadoPersona.hashCode());
		result = prime * result
				+ ((tipoEstado == null) ? 0 : tipoEstado.hashCode());
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
		EstadoPersona other = (EstadoPersona) obj;
		if (observaciones == null) {
			if (other.observaciones != null)
				return false;
		} else if (!observaciones.equals(other.observaciones))
			return false;
		if (personaNatural == null) {
			if (other.personaNatural != null)
				return false;
		} else if (!personaNatural.equals(other.personaNatural))
			return false;
		if (secEstadoPersona == null) {
			if (other.secEstadoPersona != null)
				return false;
		} else if (!secEstadoPersona.equals(other.secEstadoPersona))
			return false;
		if (tipoEstado == null) {
			if (other.tipoEstado != null)
				return false;
		} else if (!tipoEstado.equals(other.tipoEstado))
			return false;
		return true;
	}
}
