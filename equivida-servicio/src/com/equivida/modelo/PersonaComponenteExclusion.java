package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONA_COMPONENTE_EXCLUSION")
public class PersonaComponenteExclusion implements Serializable {

	private static final long serialVersionUID = -5973319907716503970L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PERSONA_COMPONENTE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secPersonaComponente;

	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@OneToOne(optional = false)
	private Persona persona;

	public Integer getSecPersonaComponente() {
		return secPersonaComponente;
	}

	public void setSecPersonaComponente(Integer secPersonaComponente) {
		this.secPersonaComponente = secPersonaComponente;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
