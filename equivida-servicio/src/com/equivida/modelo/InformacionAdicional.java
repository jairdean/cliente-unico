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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.equivida.util.StringUtil;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "INFORMACION_ADICIONAL")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "InformacionAdicional.findAll", query = "SELECT i FROM InformacionAdicional i"),
		@NamedQuery(name = "InformacionAdicional.findBySecInformacionAdicional", query = "SELECT i FROM InformacionAdicional i WHERE i.secInformacionAdicional = :secInformacionAdicional"),
		@NamedQuery(name = "InformacionAdicional.findByPiloto", query = "SELECT i FROM InformacionAdicional i WHERE i.piloto = :piloto"),
		@NamedQuery(name = "InformacionAdicional.findByCilindraje", query = "SELECT i FROM InformacionAdicional i WHERE i.cilindraje = :cilindraje"),
		@NamedQuery(name = "InformacionAdicional.findByUso", query = "SELECT i FROM InformacionAdicional i WHERE i.uso = :uso"),
		@NamedQuery(name = "InformacionAdicional.findByAccidentes", query = "SELECT i FROM InformacionAdicional i WHERE i.accidentes = :accidentes") })
public class InformacionAdicional implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6072804402437950858L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_INFORMACION_ADICIONAL")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secInformacionAdicional;
	@Column(name = "PILOTO")
	private Character piloto;
	@Column(name = "CILINDRAJE")
	private Integer cilindraje;
	@Column(name = "USO")
	private String uso;
	@Column(name = "ACCIDENTES")
	private String accidentes;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@OneToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	@Transient
	private Character conduceMoto;

	@Transient
	private Character respuestaSN;// para accidentes

	public InformacionAdicional() {
	}

	public InformacionAdicional(Integer secInformacionAdicional) {
		this.secInformacionAdicional = secInformacionAdicional;
	}

	public Integer getSecInformacionAdicional() {
		return secInformacionAdicional;
	}

	public void setSecInformacionAdicional(Integer secInformacionAdicional) {
		this.secInformacionAdicional = secInformacionAdicional;
	}

	public Character getPiloto() {
		return piloto;
	}

	public void setPiloto(Character piloto) {
		this.piloto = piloto;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		if (uso != null) {
			this.uso = StringUtil.toUpper(uso).trim();
		}
	}

	public String getAccidentes() {
		return accidentes;
	}

	public void setAccidentes(String accidentes) {
		if (accidentes != null) {
			this.accidentes = StringUtil.toUpper(accidentes).trim();
		}
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
		hash += (secInformacionAdicional != null ? secInformacionAdicional
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof InformacionAdicional)) {
			return false;
		}
		InformacionAdicional other = (InformacionAdicional) object;
		if ((this.secInformacionAdicional == null && other.secInformacionAdicional != null)
				|| (this.secInformacionAdicional != null && !this.secInformacionAdicional
						.equals(other.secInformacionAdicional))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.InformacionAdicional[secInformacionAdicional="
				+ secInformacionAdicional + "]";
	}

	/**
	 * @return the conduceMoto
	 */
	public Character getConduceMoto() {
		return conduceMoto;
	}

	/**
	 * @param conduceMoto
	 *            the conduceMoto to set
	 */
	public void setConduceMoto(Character conduceMoto) {
		this.conduceMoto = conduceMoto;
	}

	/**
	 * @return the respuestaSN
	 */
	public Character getRespuestaSN() {
		return respuestaSN;
	}

	/**
	 * @param respuestaSN
	 *            the respuestaSN to set
	 */
	public void setRespuestaSN(Character respuestaSN) {
		this.respuestaSN = respuestaSN;
	}

}
