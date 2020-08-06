/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jairo
 */
@Entity
@Table(name = "DIRECCION_ELECTRONICA")
@XmlAccessorType(XmlAccessType.FIELD)
public class DireccionElectronicaSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_DIRECCION_ELECTRONICA")
	private Integer secDireccionElectronica;

	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaSd secPersona;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 64)
	@Column(name = "DIRECCION_ELECTRONICA")
	private String direccionElectronica;

	@JoinColumn(name = "COD_TIPO_DIRECCION_ELECTRONICA", referencedColumnName = "COD_TIPO_DIRECCION_ELECTRONICA")
	@ManyToOne(optional = false)
	@XmlTransient
	private TipoDireccionElectronicaSd codTipoDireccionElectronica;

	@JoinColumn(name = "SEC_CANAL", referencedColumnName = "SEC_CANAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private CanalSd secCanal;

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

	public DireccionElectronicaSd() {
	}

	public DireccionElectronicaSd(Integer secDireccionElectronica, String direccionElectronica, char estado,
			String usrCreacion, Date tsCreacion, String usrModificacion, Date tsModificacion) {
		this.secDireccionElectronica = secDireccionElectronica;
		this.direccionElectronica = direccionElectronica;
		this.estado = estado;
		this.usrCreacion = usrCreacion;
		this.tsCreacion = tsCreacion;
		this.usrModificacion = usrModificacion;
		this.tsModificacion = tsModificacion;
	}

	public Integer getSecDireccionElectronica() {
		return secDireccionElectronica;
	}

	public void setSecDireccionElectronica(Integer secDireccionElectronica) {
		this.secDireccionElectronica = secDireccionElectronica;
	}

	public PersonaSd getSecPersona() {
		return secPersona;
	}

	public void setSecPersona(PersonaSd secPersona) {
		this.secPersona = secPersona;
	}

	public String getDireccionElectronica() {
		return direccionElectronica;
	}

	public void setDireccionElectronica(String direccionElectronica) {
		this.direccionElectronica = direccionElectronica;
	}

	public TipoDireccionElectronicaSd getCodTipoDireccionElectronica() {
		return codTipoDireccionElectronica;
	}

	public void setCodTipoDireccionElectronica(TipoDireccionElectronicaSd codTipoDireccionElectronica) {
		this.codTipoDireccionElectronica = codTipoDireccionElectronica;
	}

	public CanalSd getSecCanal() {
		return secCanal;
	}

	public void setSecCanal(CanalSd secCanal) {
		this.secCanal = secCanal;
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
}
