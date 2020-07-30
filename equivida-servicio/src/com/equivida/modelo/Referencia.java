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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.equivida.util.StringUtil;

/**
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "REFERENCIA")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "Referencia.findAll", query = "SELECT r FROM Referencia r"),
		@NamedQuery(name = "Referencia.findBySecReferencia", query = "SELECT r FROM Referencia r WHERE r.secReferencia = :secReferencia"),
		@NamedQuery(name = "Referencia.findByDenominacion", query = "SELECT r FROM Referencia r WHERE r.denominacion = :denominacion") })
public class Referencia implements Serializable {

	private static final long serialVersionUID = 7376415735900401235L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_REFERENCIA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secReferencia;
	@Basic(optional = false)
	@Column(name = "NRO_TELEFONO_CONVENCIONAL")
	private String nroTelefonoConvencional;
	@Basic(optional = false)
	@Column(name = "NRO_TELEFONO_CELULAR")
	private String nroTelefonoCelular;
	@Basic(optional = false)
	@Column(name = "DENOMINACION")
	private String denominacion;
	@Column(name = "FAMILIAR")
	private char familiarNoVivaUd;
	@JoinColumn(name = "COD_TIPO_PARENTESCO", referencedColumnName = "COD_TIPO_PARENTESCO")
	@ManyToOne(optional = false)
	private TipoParentescoRelacion tipoParentescoRelacion;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	// auditoria
	@Column(name = "USR_CREACION")
	@XmlTransient
	private String usrCreacion;

	@Column(name = "TTY_CREACION")
	@XmlTransient
	private String ttyCreacion;// terminal

	@Column(name = "PRG_CREACION")
	@XmlTransient
	private String prgCreacion;

	@Column(name = "CTA_CREACION")
	@XmlTransient
	private String ctaCreacion;

	@Column(name = "USR_MODIFICACION")
	@XmlTransient
	private String usrModificacion;

	@Column(name = "TTY_MODIFICACION")
	@XmlTransient
	private String ttyModificacion;

	@Column(name = "PRG_MODIFICACION")
	@XmlTransient
	private String prgModificacion;

	@Column(name = "CTA_MODIFICACION")
	@XmlTransient
	private String ctaModificacion;

	@Transient
	@XmlTransient
	private Referencia original;

	public Referencia() {
	}

	public Referencia(Integer secReferencia) {
		this.secReferencia = secReferencia;
	}

	public Integer getSecReferencia() {
		return secReferencia;
	}

	public void setSecReferencia(Integer secReferencia) {
		this.secReferencia = secReferencia;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		if (denominacion != null) {
			this.denominacion = StringUtil.toUpper(denominacion).trim();
		} else {
			this.denominacion = denominacion;
		}
	}

	public TipoParentescoRelacion getTipoParentescoRelacion() {
		return tipoParentescoRelacion;
	}

	public void setTipoParentescoRelacion(
			TipoParentescoRelacion tipoParentescoRelacion) {
		this.tipoParentescoRelacion = tipoParentescoRelacion;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public String getNroTelefonoConvencional() {
		return nroTelefonoConvencional;
	}

	public void setNroTelefonoConvencional(String nroTelefonoConvencional) {
		this.nroTelefonoConvencional = nroTelefonoConvencional;
	}

	public String getNroTelefonoCelular() {
		return nroTelefonoCelular;
	}

	public void setNroTelefonoCelular(String nroTelefonoCelular) {
		this.nroTelefonoCelular = nroTelefonoCelular;
	}

	public char getFamiliarNoVivaUd() {
		return familiarNoVivaUd;
	}

	public void setFamiliarNoVivaUd(char familiarNoVivaUd) {
		this.familiarNoVivaUd = familiarNoVivaUd;
	}

	public String getUsrCreacion() {
		return usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public String getTtyCreacion() {
		return ttyCreacion;
	}

	public void setTtyCreacion(String ttyCreacion) {
		this.ttyCreacion = ttyCreacion;
	}

	public String getPrgCreacion() {
		return prgCreacion;
	}

	public void setPrgCreacion(String prgCreacion) {
		this.prgCreacion = prgCreacion;
	}

	public String getCtaCreacion() {
		return ctaCreacion;
	}

	public void setCtaCreacion(String ctaCreacion) {
		this.ctaCreacion = ctaCreacion;
	}

	public String getUsrModificacion() {
		return usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public String getTtyModificacion() {
		return ttyModificacion;
	}

	public void setTtyModificacion(String ttyModificacion) {
		this.ttyModificacion = ttyModificacion;
	}

	public String getPrgModificacion() {
		return prgModificacion;
	}

	public void setPrgModificacion(String prgModificacion) {
		this.prgModificacion = prgModificacion;
	}

	public String getCtaModificacion() {
		return ctaModificacion;
	}

	public void setCtaModificacion(String ctaModificacion) {
		this.ctaModificacion = ctaModificacion;
	}

	public Referencia getOriginal() {
		return original;
	}

	public void setOriginal(Referencia original) {
		this.original = original;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((denominacion == null) ? 0 : denominacion.hashCode());
		result = prime * result + familiarNoVivaUd;
		result = prime
				* result
				+ ((nroTelefonoCelular == null) ? 0 : nroTelefonoCelular
						.hashCode());
		result = prime
				* result
				+ ((nroTelefonoConvencional == null) ? 0
						: nroTelefonoConvencional.hashCode());
		result = prime * result
				+ ((secReferencia == null) ? 0 : secReferencia.hashCode());
		result = prime
				* result
				+ ((tipoParentescoRelacion == null) ? 0
						: tipoParentescoRelacion.hashCode());
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
		Referencia other = (Referencia) obj;
		if (denominacion == null) {
			if (other.denominacion != null)
				return false;
		} else if (!denominacion.equals(other.denominacion))
			return false;
		if (familiarNoVivaUd != other.familiarNoVivaUd)
			return false;
		if (nroTelefonoCelular == null) {
			if (other.nroTelefonoCelular != null)
				return false;
		} else if (!nroTelefonoCelular.equals(other.nroTelefonoCelular))
			return false;
		if (nroTelefonoConvencional == null) {
			if (other.nroTelefonoConvencional != null)
				return false;
		} else if (!nroTelefonoConvencional
				.equals(other.nroTelefonoConvencional))
			return false;
		if (secReferencia == null) {
			if (other.secReferencia != null)
				return false;
		} else if (!secReferencia.equals(other.secReferencia))
			return false;
		if (tipoParentescoRelacion == null) {
			if (other.tipoParentescoRelacion != null)
				return false;
		} else if (!tipoParentescoRelacion.equals(other.tipoParentescoRelacion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Referencia [secReferencia=" + secReferencia
				+ ", nroTelefonoConvencional=" + nroTelefonoConvencional
				+ ", nroTelefonoCelular=" + nroTelefonoCelular
				+ ", denominacion=" + denominacion + ", familiarNoVivaUd="
				+ familiarNoVivaUd + ", tipoParentescoRelacion="
				+ tipoParentescoRelacion + "]";
	}
}