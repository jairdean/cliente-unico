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
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "DEPORTE_PRACTICA")
public class DeportePractica implements Serializable {

	private static final long serialVersionUID = 7387090646154176810L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_DEPORTE_PRACTICA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secDeportePractica;

	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	private PersonaNatural personaNatural;

	@JoinColumn(name = "COD_DEPORTE", referencedColumnName = "COD_DEPORTE")
	@ManyToOne(optional = false)
	private Deporte deporte;

	@Column(name = "TIPO_PRACTICA")
	private char tipoPractica;// aficionado-profesional

	public DeportePractica() {
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public char getTipoPractica() {
		return tipoPractica;
	}

	public void setTipoPractica(char tipoPractica) {
		this.tipoPractica = tipoPractica;
	}

	public Integer getSecDeportePractica() {
		return secDeportePractica;
	}

	public void setSecDeportePractica(Integer secDeportePractica) {
		this.secDeportePractica = secDeportePractica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deporte == null) ? 0 : deporte.hashCode());
		result = prime
				* result
				+ ((secDeportePractica == null) ? 0 : secDeportePractica
						.hashCode());
		result = prime * result + tipoPractica;
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
		DeportePractica other = (DeportePractica) obj;
		if (deporte == null) {
			if (other.deporte != null)
				return false;
		} else if (!deporte.equals(other.deporte))
			return false;
		if (secDeportePractica == null) {
			if (other.secDeportePractica != null)
				return false;
		} else if (!secDeportePractica.equals(other.secDeportePractica))
			return false;
		if (tipoPractica != other.tipoPractica)
			return false;
		return true;
	}

}