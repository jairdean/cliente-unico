/**
 * 
 */
package com.equivida.smartdata.model;

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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author juan
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "DIRECCION_TELEFONO")
public class DireccionTelefonoSd implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_DIRECCION_TELEFONO")
	private Integer secDireccionTelefono;

	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaSd persona;

	@JoinColumn(name = "SEC_TELEFONO", referencedColumnName = "SEC_TELEFONO")
	@ManyToOne(optional = false)
	@XmlElement(name = "telefonoDireccion")
	private TelefonoSd telefono;

	@JoinColumn(name = "SEC_DIRECCION", referencedColumnName = "SEC_DIRECCION")
	@ManyToOne(optional = false)
	@XmlTransient
	private DireccionSd direccion;

	/**
	 * @return the secDireccionTelefono
	 */
	public Integer getSecDireccionTelefono() {
		return secDireccionTelefono;
	}

	/**
	 * @param secDireccionTelefono
	 *            the secDireccionTelefono to set
	 */
	public void setSecDireccionTelefono(Integer secDireccionTelefono) {
		this.secDireccionTelefono = secDireccionTelefono;
	}

	/**
	 * @return the persona
	 */
	public PersonaSd getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            the persona to set
	 */
	public void setPersona(PersonaSd persona) {
		this.persona = persona;
	}

	/**
	 * @return the telefono
	 */
	public TelefonoSd getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(TelefonoSd telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the direccion
	 */
	public DireccionSd getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(DireccionSd direccion) {
		this.direccion = direccion;
	}

}
