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

import com.equivida.constant.EstadoEnum;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "DIRECCION_ELECTRONICA")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "DireccionElectronica.findAll", query = "SELECT d FROM DireccionElectronica d"),
		@NamedQuery(name = "DireccionElectronica.findBySecDireccionElectronica", query = "SELECT d FROM DireccionElectronica d WHERE d.secDireccionElectronica = :secDireccionElectronica"),
		@NamedQuery(name = "DireccionElectronica.findByDireccionElectronica", query = "SELECT d FROM DireccionElectronica d WHERE d.direccionElectronica = :direccionElectronica") })
public class DireccionElectronica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3504649328308940660L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_DIRECCION_ELECTRONICA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secDireccionElectronica;

	@Basic(optional = false)
	@Column(name = "DIRECCION_ELECTRONICA")
	private String direccionElectronica;

	@JoinColumn(name = "COD_TIPO_DIRECCION_ELECTRONICA", referencedColumnName = "COD_TIPO_DIRECCION_ELECTRONICA")
	@ManyToOne(optional = false)
	private TipoDireccionElectronica tipoDireccionElectronica;

	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	@XmlTransient
	private Persona persona;

	@Column(name = "ESTADO")
	@XmlTransient
	private char estado;// A-I-D

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
	private DireccionElectronica original;

	@Transient
	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo() || this.estado == 'D';
	}

	@Transient
	private Boolean principalTransient;

	public DireccionElectronica() {
	}

	public DireccionElectronica(Integer secDireccionElectronica) {
		this.secDireccionElectronica = secDireccionElectronica;
	}

	public DireccionElectronica(Integer secDireccionElectronica, String direccionElectronica) {
		this.secDireccionElectronica = secDireccionElectronica;
		this.direccionElectronica = direccionElectronica;
	}

	public Integer getSecDireccionElectronica() {
		return secDireccionElectronica;
	}

	public void setSecDireccionElectronica(Integer secDireccionElectronica) {
		this.secDireccionElectronica = secDireccionElectronica;
	}

	public String getDireccionElectronica() {
		return direccionElectronica;
	}

	public void setDireccionElectronica(String direccionElectronica) {
		this.direccionElectronica = direccionElectronica;
	}

	public TipoDireccionElectronica getTipoDireccionElectronica() {
		return tipoDireccionElectronica;
	}

	public void setTipoDireccionElectronica(TipoDireccionElectronica tipoDireccionElectronica) {
		this.tipoDireccionElectronica = tipoDireccionElectronica;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.DireccionElectronica[secDireccionElectronica=" + secDireccionElectronica + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccionElectronica == null) ? 0 : direccionElectronica.hashCode());
		result = prime * result + estado;
		result = prime * result + ((secDireccionElectronica == null) ? 0 : secDireccionElectronica.hashCode());
		result = prime * result + ((tipoDireccionElectronica == null) ? 0 : tipoDireccionElectronica.hashCode());
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
		DireccionElectronica other = (DireccionElectronica) obj;
		if (direccionElectronica == null) {
			if (other.direccionElectronica != null)
				return false;
		} else if (!direccionElectronica.equals(other.direccionElectronica))
			return false;
		if (estado != other.estado)
			return false;
		if (secDireccionElectronica == null) {
			if (other.secDireccionElectronica != null)
				return false;
		} else if (!secDireccionElectronica.equals(other.secDireccionElectronica))
			return false;
		if (tipoDireccionElectronica == null) {
			if (other.tipoDireccionElectronica != null)
				return false;
		} else if (!tipoDireccionElectronica.equals(other.tipoDireccionElectronica))
			return false;
		return true;
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

	public DireccionElectronica getOriginal() {
		return original;
	}

	public void setOriginal(DireccionElectronica original) {
		this.original = original;
	}

	public Boolean getPrincipalTransient() {
		if (principalTransient == null) {
			if (estado == 'D') {
				principalTransient = true;
			} else {
				principalTransient = false;
			}
		}
		return principalTransient;
	}

	public void setPrincipalTransient(Boolean principalTransient) {
		if(principalTransient!=null) {
			if(principalTransient==true) {
				this.estado='D';
			}
		}
		this.principalTransient = principalTransient;
	}
}