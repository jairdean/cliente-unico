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
@Table(name = "PERSONA_PE")
@NamedQueries({
		@NamedQuery(name = "PersonaPe.findAll", query = "SELECT p FROM PersonaPe p"),
		@NamedQuery(name = "PersonaPe.findBySecPersonaPpe", query = "SELECT p FROM PersonaPe p WHERE p.secPersonaPpe = :secPersonaPpe"),
		@NamedQuery(name = "PersonaPe.findByEstado", query = "SELECT p FROM PersonaPe p WHERE p.estado = :estado") })
public class PersonaPe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8363555949372982988L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PERSONA_PPE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secPersonaPpe;
	@Basic(optional = false)
	@Column(name = "ESTADO")
	private char estado;
	
	@Basic(optional = false)
	@Column(name = "ORGANISMO_ENTIDAD",length=48)
	private String organismoEntidad;
	
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	private PersonaNatural personaNatural;
	//@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	//@ManyToOne(optional = false)
	//private Persona persona;
	@JoinColumn(name = "COD_CATEGORIA_PPE", referencedColumnName = "COD_CATEGORIA_PPE")
	@ManyToOne(optional = false)
	private CategoriaPpe categoriaPpe;

	@Transient
	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo();
	}
	
	public PersonaPe() {
	}

	public PersonaPe(Integer secPersonaPpe) {
		this.secPersonaPpe = secPersonaPpe;
	}

	public PersonaPe(Integer secPersonaPpe, char estado) {
		this.secPersonaPpe = secPersonaPpe;
		this.estado = estado;
	}

	public Integer getSecPersonaPpe() {
		return secPersonaPpe;
	}

	public void setSecPersonaPpe(Integer secPersonaPpe) {
		this.secPersonaPpe = secPersonaPpe;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public CategoriaPpe getCategoriaPpe() {
		return categoriaPpe;
	}

	public void setCategoriaPpe(CategoriaPpe categoriaPpe) {
		this.categoriaPpe = categoriaPpe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoriaPpe == null) ? 0 : categoriaPpe.hashCode());
		result = prime * result + estado;
		result = prime * result
				+ ((secPersonaPpe == null) ? 0 : secPersonaPpe.hashCode());
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
		PersonaPe other = (PersonaPe) obj;
		if (categoriaPpe == null) {
			if (other.categoriaPpe != null)
				return false;
		} else if (!categoriaPpe.equals(other.categoriaPpe))
			return false;
		if (estado != other.estado)
			return false;
		if (secPersonaPpe == null) {
			if (other.secPersonaPpe != null)
				return false;
		} else if (!secPersonaPpe.equals(other.secPersonaPpe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonaPe [secPersonaPpe=" + secPersonaPpe + ", estado="
				+ estado + ", personaNatural=" + personaNatural + ", organismo="+organismoEntidad+", categoriaPpe=" + categoriaPpe + "]";
	}

	public String getOrganismoEntidad() {
		return organismoEntidad;
	}

	public void setOrganismoEntidad(String organismoEntidad) {
		this.organismoEntidad = organismoEntidad;
	}
}
