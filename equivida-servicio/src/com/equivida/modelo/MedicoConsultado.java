/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "MEDICO_CONSULTADO")
@NamedQueries({
		@NamedQuery(name = "MedicoConsultado.findAll", query = "SELECT m FROM MedicoConsultado m"),
		@NamedQuery(name = "MedicoConsultado.findBySecConsulta", query = "SELECT m FROM MedicoConsultado m WHERE m.secConsulta = :secConsulta"),
		@NamedQuery(name = "MedicoConsultado.findByFchConsulta", query = "SELECT m FROM MedicoConsultado m WHERE m.fchConsulta = :fchConsulta"),
		@NamedQuery(name = "MedicoConsultado.findByRazonConsulta", query = "SELECT m FROM MedicoConsultado m WHERE m.razonConsulta = :razonConsulta") })
public class MedicoConsultado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2881737272784487071L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_CONSULTA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secConsulta;
	@Basic(optional = false)
	@Column(name = "FCH_CONSULTA")
	@Temporal(TemporalType.DATE)
	private Date fchConsulta;
	@Basic(optional = false)
	@Column(name = "RAZON_CONSULTA", length = 128)
	private String razonConsulta;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	private PersonaNatural personaNatural;
	@JoinColumn(name = "SEC_MEDICO", referencedColumnName = "SEC_MEDICO")
	@ManyToOne(optional = false)
	private Medico medico;

	public MedicoConsultado() {
	}

	public MedicoConsultado(Integer secConsulta) {
		this.secConsulta = secConsulta;
	}

	public Integer getSecConsulta() {
		return secConsulta;
	}

	public void setSecConsulta(Integer secConsulta) {
		this.secConsulta = secConsulta;
	}

	public Date getFchConsulta() {
		return fchConsulta;
	}

	public void setFchConsulta(Date fchConsulta) {
		this.fchConsulta = fchConsulta;
	}

	public String getRazonConsulta() {
		return razonConsulta;
	}

	public void setRazonConsulta(String razonConsulta) {
		this.razonConsulta = razonConsulta;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secConsulta != null ? secConsulta.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MedicoConsultado)) {
			return false;
		}
		MedicoConsultado other = (MedicoConsultado) object;
		if ((this.secConsulta == null && other.secConsulta != null)
				|| (this.secConsulta != null && !this.secConsulta
						.equals(other.secConsulta))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.MedicoConsultado[secConsulta="
				+ secConsulta + "]";
	}

}
