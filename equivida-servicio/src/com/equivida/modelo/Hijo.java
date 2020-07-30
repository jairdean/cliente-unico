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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "HIJO")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "Hijo.findAll", query = "SELECT c FROM Hijo c"),
		@NamedQuery(name = "Hijo.findBySecHijo", query = "SELECT c FROM Hijo c WHERE c.secHijo = :secHijo") })
public class Hijo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3463127541750025861L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_HIJO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secHijo;

	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	private PersonaNatural personaNatural;

	@Column(name = "APELLIDO")
	private String apellido;
	@Basic(optional = false)
	@Column(name = "NOMBRE")
	private String nombre;

	@Basic(optional = true)
	@Column(name = "FCH_NACIMIENTO")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaNacimiento")
	@XmlSchemaType(name = "date")
	private Date fchNacimiento;

	@JoinColumn(name = "COD_OCUPACION", referencedColumnName = "COD_OCUPACION")
	@ManyToOne(optional = false)
	private Ocupacion ocupacion;

	/**
	 * @return the secHijo
	 */
	public Integer getSecHijo() {
		return secHijo;
	}

	/**
	 * @param secHijo the secHijo to set
	 */
	public void setSecHijo(Integer secHijo) {
		this.secHijo = secHijo;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFchNacimiento() {
		return fchNacimiento;
	}

	public void setFchNacimiento(Date fchNacimiento) {
		this.fchNacimiento = fchNacimiento;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((fchNacimiento == null) ? 0 : fchNacimiento.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((ocupacion == null) ? 0 : ocupacion.hashCode());
		result = prime * result + ((personaNatural == null) ? 0 : personaNatural.hashCode());
		result = prime * result + ((secHijo == null) ? 0 : secHijo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Hijo)) {
			return false;
		}
		Hijo other = (Hijo) obj;
		if (apellido == null) {
			if (other.apellido != null) {
				return false;
			}
		} else if (!apellido.equals(other.apellido)) {
			return false;
		}
		if (fchNacimiento == null) {
			if (other.fchNacimiento != null) {
				return false;
			}
		} else if (!fchNacimiento.equals(other.fchNacimiento)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (ocupacion == null) {
			if (other.ocupacion != null) {
				return false;
			}
		} else if (!ocupacion.equals(other.ocupacion)) {
			return false;
		}
		if (personaNatural == null) {
			if (other.personaNatural != null) {
				return false;
			}
		} else if (!personaNatural.equals(other.personaNatural)) {
			return false;
		}
		if (secHijo == null) {
			if (other.secHijo != null) {
				return false;
			}
		} else if (!secHijo.equals(other.secHijo)) {
			return false;
		}
		return true;
	}

}
