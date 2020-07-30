package com.equivida.smartdata.model;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.equivida.smartdata.dto.PersonaRelacionDto;

@Entity
@Table(name = "RELACION")
@XmlAccessorType(XmlAccessType.FIELD)
public class RelacionSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_RELACION")
	private Integer secRelacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "USR_CREACION")
	@XmlTransient
	private String usrCreacion;
	@Basic(optional = false)
	@Column(name = "TS_CREACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date tsCreacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "USR_MODIFICACION")
	@XmlTransient
	private String usrModificacion;
	@Basic(optional = false)
	@Column(name = "TS_MODIFICACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date tsModificacion;
	@JoinColumn(name = "SEC_PERSONA_P", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaSd personaP;
	@JoinColumn(name = "SEC_PERSONA_R", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaSd personaR;
	@JoinColumn(name = "COD_TIPO_PARENTESCO", referencedColumnName = "COD_TIPO_PARENTESCO")
	@ManyToOne(optional = false)
	@XmlTransient
	private TipoParentescoRelacionSd tipoParentesco;
	@JoinColumn(name = "SEC_DETALLE_RELACION", referencedColumnName = "SEC_DETALLE_RELACION")
	@ManyToOne(optional = true)
	@XmlTransient
	private DetalleRelacionSd detalleRelacion;
	@JoinColumn(name = "SEC_CANAL", referencedColumnName = "SEC_CANAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private CanalSd secCanal;

	@Transient
	@XmlTransient
	private boolean existePersonaR;

	@Transient
	@XmlElement(name = "personaRelacion")
	private PersonaRelacionDto personaRelacion;

	/**
	 * @return the personaRelacion
	 */
	public PersonaRelacionDto getPersonaRelacion() {
		if (this.personaR != null) {
			personaRelacion = new PersonaRelacionDto();
			personaRelacion
					.setIdentificacion(this.personaR.getIdentificacion());
			personaRelacion.setNombre(this.personaR.getDenominacion());
		}
		return personaRelacion;
	}

	/**
	 * @param personaRelacion
	 *            the personaRelacion to set
	 */
	public void setPersonaRelacion(PersonaRelacionDto personaRelacion) {
		this.personaRelacion = personaRelacion;
	}

	public Integer getSecRelacion() {
		return secRelacion;
	}

	public void setSecRelacion(Integer secRelacion) {
		this.secRelacion = secRelacion;
	}

	public String getUsrCreacion() {
		return usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public Date getTsCreacion() {
		return tsCreacion;
	}

	public void setTsCreacion(Date tsCreacion) {
		this.tsCreacion = tsCreacion;
	}

	public String getUsrModificacion() {
		return usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public Date getTsModificacion() {
		return tsModificacion;
	}

	public void setTsModificacion(Date tsModificacion) {
		this.tsModificacion = tsModificacion;
	}

	public PersonaSd getPersonaP() {
		return personaP;
	}

	public void setPersonaP(PersonaSd personaP) {
		this.personaP = personaP;
	}

	public PersonaSd getPersonaR() {
		return personaR;
	}

	public void setPersonaR(PersonaSd personaR) {
		this.personaR = personaR;
	}

	public TipoParentescoRelacionSd getTipoParentesco() {
		return tipoParentesco;
	}

	public void setTipoParentesco(TipoParentescoRelacionSd tipoParentesco) {
		this.tipoParentesco = tipoParentesco;
	}

	public DetalleRelacionSd getDetalleRelacion() {
		return detalleRelacion;
	}

	public void setDetalleRelacion(DetalleRelacionSd detalleRelacion) {
		this.detalleRelacion = detalleRelacion;
	}

	public CanalSd getSecCanal() {
		return secCanal;
	}

	public void setSecCanal(CanalSd secCanal) {
		this.secCanal = secCanal;
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
		result = prime * result
				+ ((secRelacion == null) ? 0 : secRelacion.hashCode());
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
		if (!(obj instanceof RelacionSd)) {
			return false;
		}
		RelacionSd other = (RelacionSd) obj;
		if (secRelacion == null) {
			if (other.secRelacion != null) {
				return false;
			}
		} else if (!secRelacion.equals(other.secRelacion)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "com.equivida.smartdata.model.Relacion[ secRelacion="
				+ secRelacion + " ]";
	}

	/**
	 * @return the existePersonaR
	 */
	public boolean isExistePersonaR() {
		return existePersonaR;
	}

	/**
	 * @param existePersonaR
	 *            the existePersonaR to set
	 */
	public void setExistePersonaR(boolean existePersonaR) {
		this.existePersonaR = existePersonaR;
	}

}
