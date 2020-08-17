/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "PERSONA_JURIDICA")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonaJuridicaSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_PERSONA_JURIDICA")
	@XmlTransient
	private Integer secPersonaJuridica;
	@Basic(optional = false)
	@Column(name = "IDENTIFICACION", length = 13, columnDefinition = "char(13)")
	private String identificacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	@Size(max = 80)
	@Column(name = "ACTIVIDAD_IESS")
	private String actividadIess;
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
	@JoinColumn(name = "COD_TIPO_IDENTIFICACION", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = false)
	@XmlTransient
	private TipoIdentificacionSd codTipoIdentificacion;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@OneToOne(optional = false)
	@XmlTransient
	private PersonaSd secPersona;
	@JoinColumn(name = "SEC_CANAL", referencedColumnName = "SEC_CANAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private CanalSd secCanal;
	@JoinColumn(name = "COD_ACTIVIDAD_ECONOMICA", referencedColumnName = "COD_ACTIVIDAD_ECONOMICA")
	@ManyToOne
	@XmlTransient
	private ActividadEconomicaSd codActividadEconomica;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaJuridica", fetch
	// = FetchType.LAZY)
	@Transient
	@XmlTransient
	private List<EmpleoDependienteSd> empleoDependienteList;
	
	
	public PersonaJuridicaSd() {
	}

	public PersonaJuridicaSd(Integer secPersonaJuridica) {
		this.secPersonaJuridica = secPersonaJuridica;
	}

	public PersonaJuridicaSd(Integer secPersonaJuridica, String identificacion,
			String razonSocial, String usrCreacion, Date tsCreacion,
			String usrModificacion, Date tsModificacion) {
		this.secPersonaJuridica = secPersonaJuridica;
		this.identificacion = identificacion;
		this.razonSocial = razonSocial;
		this.usrCreacion = usrCreacion;
		this.tsCreacion = tsCreacion;
		this.usrModificacion = usrModificacion;
		this.tsModificacion = tsModificacion;
	}
	
	public Integer getSecPersonaJuridica() {
		return secPersonaJuridica;
	}

	public void setSecPersonaJuridica(Integer secPersonaJuridica) {
		this.secPersonaJuridica = secPersonaJuridica;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getActividadIess() {
		return actividadIess;
	}

	public void setActividadIess(String actividadIess) {
		this.actividadIess = actividadIess;
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

	public TipoIdentificacionSd getCodTipoIdentificacion() {
		return codTipoIdentificacion;
	}

	public void setCodTipoIdentificacion(
			TipoIdentificacionSd codTipoIdentificacion) {
		this.codTipoIdentificacion = codTipoIdentificacion;
	}

	public PersonaSd getSecPersona() {
		return secPersona;
	}

	public void setSecPersona(PersonaSd secPersona) {
		this.secPersona = secPersona;
	}

	public CanalSd getSecCanal() {
		return secCanal;
	}

	public void setSecCanal(CanalSd secCanal) {
		this.secCanal = secCanal;
	}

	public ActividadEconomicaSd getCodActividadEconomica() {
		return codActividadEconomica;
	}

	public void setCodActividadEconomica(
			ActividadEconomicaSd codActividadEconomica) {
		this.codActividadEconomica = codActividadEconomica;
	}

	public List<EmpleoDependienteSd> getEmpleoDependienteList() {
		return empleoDependienteList;
	}

	public void setEmpleoDependienteList(
			List<EmpleoDependienteSd> empleoDependienteList) {
		this.empleoDependienteList = empleoDependienteList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secPersonaJuridica != null ? secPersonaJuridica.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PersonaJuridicaSd)) {
			return false;
		}
		PersonaJuridicaSd other = (PersonaJuridicaSd) object;
		if ((this.secPersonaJuridica == null && other.secPersonaJuridica != null)
				|| (this.secPersonaJuridica != null && !this.secPersonaJuridica
						.equals(other.secPersonaJuridica))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.PersonaJuridica[ secPersonaJuridica="
				+ secPersonaJuridica + " ]";
	}

}
