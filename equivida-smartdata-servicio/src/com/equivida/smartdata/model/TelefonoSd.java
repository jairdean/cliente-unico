/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "TELEFONO")
@XmlAccessorType(XmlAccessType.FIELD)
public class TelefonoSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_TELEFONO")
	private Integer secTelefono;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 4)
	@Column(name = "COD_AREA")
	private String codArea;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 12)
	@Column(name = "NRO_TELEFONO")
	private String nroTelefono;
	@Basic(optional = false)
	@NotNull
	@Column(name = "ESTADO")
	@XmlTransient
	private char estado;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "USR_CREACION")
	@XmlTransient
	private String usrCreacion;
	@Basic(optional = false)
	@Column(name = "TS_CREACION", insertable = false, updatable = false)
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
	@Column(name = "TS_MODIFICACION", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date tsModificacion;
	@JoinColumn(name = "COD_TIPO_TELEFONO", referencedColumnName = "COD_TIPO_TELEFONO")
	@ManyToOne(optional = false)
	private TipoTelefonoSd codTipoTelefono;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaSd secPersona;
	@JoinColumn(name = "SEC_CANAL", referencedColumnName = "SEC_CANAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private CanalSd secCanal;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "telefono")
	@XmlTransient
	private List<DireccionTelefonoSd> direccionTelefonoList;

	public TelefonoSd() {
	}

	public TelefonoSd(Integer secTelefono) {
		this.secTelefono = secTelefono;
	}

	public TelefonoSd(Integer secTelefono, String codArea, String nroTelefono, char estado, String usrCreacion,
			Date tsCreacion, String usrModificacion, Date tsModificacion) {
		this.secTelefono = secTelefono;
		this.codArea = codArea;
		this.nroTelefono = nroTelefono;
		this.estado = estado;
		this.usrCreacion = usrCreacion;
		this.tsCreacion = tsCreacion;
		this.usrModificacion = usrModificacion;
		this.tsModificacion = tsModificacion;
	}

	public Integer getSecTelefono() {
		return secTelefono;
	}

	public void setSecTelefono(Integer secTelefono) {
		this.secTelefono = secTelefono;
	}

	public String getCodArea() {
		return codArea;
	}

	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

	public String getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
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

	public TipoTelefonoSd getCodTipoTelefono() {
		return codTipoTelefono;
	}

	public void setCodTipoTelefono(TipoTelefonoSd codTipoTelefono) {
		this.codTipoTelefono = codTipoTelefono;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secTelefono != null ? secTelefono.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TelefonoSd)) {
			return false;
		}
		TelefonoSd other = (TelefonoSd) object;
		if ((this.secTelefono == null && other.secTelefono != null)
				|| (this.secTelefono != null && !this.secTelefono.equals(other.secTelefono))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TelefonoSd [secTelefono=" + secTelefono + ", codArea=" + codArea + ", nroTelefono=" + nroTelefono
				+ ", estado=" + estado + ", usrCreacion=" + usrCreacion + ", tsCreacion=" + tsCreacion
				+ ", usrModificacion=" + usrModificacion + ", tsModificacion=" + tsModificacion + ", codTipoTelefono="
				+ ((codTipoTelefono != null) ? codTipoTelefono.getCodTipoTelefono() : null) + ", secPersona="
				+ ((secPersona != null) ? secPersona.getSecPersona() : null) + ", secCanal="
				+ ((secCanal != null) ? secCanal.getCanal() : null) + "]";
	}

	/**
	 * @return the direccionTelefonoList
	 */
	public List<DireccionTelefonoSd> getDireccionTelefonoList() {
		return direccionTelefonoList;
	}

	/**
	 * @param direccionTelefonoList
	 *            the direccionTelefonoList to set
	 */
	public void setDireccionTelefonoList(List<DireccionTelefonoSd> direccionTelefonoList) {
		this.direccionTelefonoList = direccionTelefonoList;
	}

}
