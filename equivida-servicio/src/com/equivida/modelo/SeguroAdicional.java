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

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "SEGURO_ADICIONAL")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "SeguroAdicional.findAll", query = "SELECT s FROM SeguroAdicional s"),
		@NamedQuery(name = "SeguroAdicional.findBySecSeguroAdicional", query = "SELECT s FROM SeguroAdicional s WHERE s.secSeguroAdicional = :secSeguroAdicional"),
		@NamedQuery(name = "SeguroAdicional.findByRespuesta", query = "SELECT s FROM SeguroAdicional s WHERE s.respuesta = :respuesta") })
public class SeguroAdicional implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1355637893695655914L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_SEGURO_ADICIONAL")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secSeguroAdicional;
	@Basic(optional = false)
	@Column(name = "RESPUESTA")
	private char respuesta;
	@JoinColumn(name = "SEC_TIPO_ADICIONAL", referencedColumnName = "SEC_TIPO_ADICIONAL")
	@ManyToOne(optional = false)
	private TipoOtroSeguro tipoOtroSeguro;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	public SeguroAdicional() {
	}

	public SeguroAdicional(Integer secSeguroAdicional) {
		this.secSeguroAdicional = secSeguroAdicional;
	}

	public SeguroAdicional(Integer secSeguroAdicional, char respuesta) {
		this.secSeguroAdicional = secSeguroAdicional;
		this.respuesta = respuesta;
	}

	public Integer getSecSeguroAdicional() {
		return secSeguroAdicional;
	}

	public void setSecSeguroAdicional(Integer secSeguroAdicional) {
		this.secSeguroAdicional = secSeguroAdicional;
	}

	public char getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(char respuesta) {
		this.respuesta = respuesta;
	}

	public TipoOtroSeguro getTipoOtroSeguro() {
		return tipoOtroSeguro;
	}

	public void setTipoOtroSeguro(TipoOtroSeguro tipoOtroSeguro) {
		this.tipoOtroSeguro = tipoOtroSeguro;
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
		hash += (secSeguroAdicional != null ? secSeguroAdicional.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SeguroAdicional)) {
			return false;
		}
		SeguroAdicional other = (SeguroAdicional) object;
		if ((this.secSeguroAdicional == null && other.secSeguroAdicional != null)
				|| (this.secSeguroAdicional != null && !this.secSeguroAdicional
						.equals(other.secSeguroAdicional))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.SeguroAdicional[secSeguroAdicional="
				+ secSeguroAdicional + "]";
	}

}
