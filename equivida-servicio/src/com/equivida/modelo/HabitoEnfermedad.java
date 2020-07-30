/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.util.Collection;

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

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "HABITO_ENFERMEDAD")
@NamedQueries({
		@NamedQuery(name = "HabitoEnfermedad.findAll", query = "SELECT h FROM HabitoEnfermedad h"),
		@NamedQuery(name = "HabitoEnfermedad.findBySecHabitoEnfermedad", query = "SELECT h FROM HabitoEnfermedad h WHERE h.secHabitoEnfermedad = :secHabitoEnfermedad") })
public class HabitoEnfermedad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4091976474905725984L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_HABITO_ENFERMEDAD")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secHabitoEnfermedad;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "habitoEnfermedad")
	// private Collection<DetalleHabitoEnfermedad>
	// detalleHabitoEnfermedadCollection;
	@JoinColumn(name = "COD_TIPO_HABITO", referencedColumnName = "COD_TIPO_HABITO")
	@ManyToOne(optional = false)
	private TipoHabitoEnfermedad tipoHabitoEnfermedad;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	private PersonaNatural personaNatural;

	@Transient
	private Collection<DetalleHabitoEnfermedad> detalleHabitoEnfermedadFormularioCollection;

	@Column(name = "RESPUESTA")
	private Character respuesta;

	public HabitoEnfermedad() {
	}

	public HabitoEnfermedad(Integer secHabitoEnfermedad) {
		this.secHabitoEnfermedad = secHabitoEnfermedad;
	}

	public Integer getSecHabitoEnfermedad() {
		return secHabitoEnfermedad;
	}

	public void setSecHabitoEnfermedad(Integer secHabitoEnfermedad) {
		this.secHabitoEnfermedad = secHabitoEnfermedad;
	}

	public TipoHabitoEnfermedad getTipoHabitoEnfermedad() {
		return tipoHabitoEnfermedad;
	}

	public void setTipoHabitoEnfermedad(
			TipoHabitoEnfermedad tipoHabitoEnfermedad) {
		this.tipoHabitoEnfermedad = tipoHabitoEnfermedad;
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
		hash += (secHabitoEnfermedad != null ? secHabitoEnfermedad.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof HabitoEnfermedad)) {
			return false;
		}
		HabitoEnfermedad other = (HabitoEnfermedad) object;
		if ((this.secHabitoEnfermedad == null && other.secHabitoEnfermedad != null)
				|| (this.secHabitoEnfermedad != null && !this.secHabitoEnfermedad
						.equals(other.secHabitoEnfermedad))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.HabitoEnfermedad[secHabitoEnfermedad="
				+ secHabitoEnfermedad + "]";
	}

	/**
	 * @return the respuesta
	 */
	public Character getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(Character respuesta) {
		this.respuesta = respuesta;
	}

	public Collection<DetalleHabitoEnfermedad> getDetalleHabitoEnfermedadFormularioCollection() {
		return detalleHabitoEnfermedadFormularioCollection;
	}

	public void setDetalleHabitoEnfermedadFormularioCollection(
			Collection<DetalleHabitoEnfermedad> detalleHabitoEnfermedadFormularioCollection) {
		this.detalleHabitoEnfermedadFormularioCollection = detalleHabitoEnfermedadFormularioCollection;
	}

}
