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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.equivida.util.StringUtil;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "OTRO_EMPLEO")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "OtroEmpleo.findAll", query = "SELECT o FROM OtroEmpleo o"),
		@NamedQuery(name = "OtroEmpleo.findBySecOtroEmpleo", query = "SELECT o FROM OtroEmpleo o WHERE o.secOtroEmpleo = :secOtroEmpleo"),
		@NamedQuery(name = "OtroEmpleo.findByDetalle", query = "SELECT o FROM OtroEmpleo o WHERE o.detalle = :detalle") })
public class OtroEmpleo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6783680371478841480L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_OTRO_EMPLEO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secOtroEmpleo;
	@Basic(optional = false)
	@Column(name = "DETALLE")
	private String detalle;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	public OtroEmpleo() {
	}

	public OtroEmpleo(Integer secOtroEmpleo) {
		this.secOtroEmpleo = secOtroEmpleo;
	}

	public OtroEmpleo(Integer secOtroEmpleo, String detalle) {
		this.secOtroEmpleo = secOtroEmpleo;
		this.detalle = detalle;
	}

	public Integer getSecOtroEmpleo() {
		return secOtroEmpleo;
	}

	public void setSecOtroEmpleo(Integer secOtroEmpleo) {
		this.secOtroEmpleo = secOtroEmpleo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		if (detalle != null) {
			this.detalle = StringUtil.toUpper(detalle).trim();
		}
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.OtroEmpleo[secOtroEmpleo=" + secOtroEmpleo
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detalle == null) ? 0 : detalle.hashCode());
		result = prime * result
				+ ((personaNatural == null) ? 0 : personaNatural.hashCode());
		result = prime * result
				+ ((secOtroEmpleo == null) ? 0 : secOtroEmpleo.hashCode());
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
		OtroEmpleo other = (OtroEmpleo) obj;
		if (detalle == null) {
			if (other.detalle != null)
				return false;
		} else if (!detalle.equals(other.detalle))
			return false;
		if (personaNatural == null) {
			if (other.personaNatural != null)
				return false;
		} else if (!personaNatural.equals(other.personaNatural))
			return false;
		if (secOtroEmpleo == null) {
			if (other.secOtroEmpleo != null)
				return false;
		} else if (!secOtroEmpleo.equals(other.secOtroEmpleo))
			return false;
		return true;
	}

}
