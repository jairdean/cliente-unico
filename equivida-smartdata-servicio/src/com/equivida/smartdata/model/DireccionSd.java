/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity(name = "DireccionSd")
@Table(name = "DIRECCION")
@XmlAccessorType(XmlAccessType.FIELD)
public class DireccionSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_DIRECCION")
	private Integer secDireccion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 192)
	@Column(name = "DIRECCION")
	private String direccion;
	@XmlTransient
	@Basic(optional = false)
	@NotNull
	@Column(name = "ESTADO")
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
	@JoinColumn(name = "COD_TIPO_DIRECCION", referencedColumnName = "COD_TIPO_DIRECCION")
	@ManyToOne(optional = false)
	@XmlElement(name = "tipoDireccion")
	private TipoDireccionSd codTipoDireccion;
	@JoinColumn(name = "SEC_PROVINCIA", referencedColumnName = "SEC_PROVINCIA")
	@ManyToOne(optional = false)
	@XmlElement(name = "provincia")
	private ProvinciaSd secProvincia;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@OneToOne(optional = false)
	@XmlTransient
	private PersonaSd secPersona;
	@JoinColumn(name = "SEC_PARROQUIA", referencedColumnName = "SEC_PARROQUIA")
	@ManyToOne(optional = false)
	@XmlElement(name = "parroquia")
	private ParroquiaSd secParroquia;
	@JoinColumn(name = "SEC_CANTON", referencedColumnName = "SEC_CANTON")
	@ManyToOne(optional = false)
	@XmlElement(name = "canton")
	private CantonSd secCanton;
	@XmlTransient
	@JoinColumn(name = "SEC_CANAL", referencedColumnName = "SEC_CANAL")
	@ManyToOne(optional = false)
	private CanalSd secCanal;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "direccion")
	@Transient
	@XmlTransient
	private List<DireccionTelefonoSd> direccionTelefonoList;

	@Transient
	private String referencia;

	@XmlTransient
	@Transient
	private boolean direccionCompleta;

	@Transient
	private String principalTr;

	@Transient
	private String numeroTr;

	@Transient
	private String secundariaTr;

	@Transient
	@XmlElement(name = "telefonoDireccion")
	private List<String> telefonoLista;

	public DireccionSd() {
	}

	public DireccionSd(Integer secDireccion) {
		this.secDireccion = secDireccion;
	}

	public DireccionSd(Integer secDireccion, String direccion, char estado, String usrCreacion, Date tsCreacion,
			String usrModificacion, Date tsModificacion) {
		this.secDireccion = secDireccion;
		this.direccion = direccion;
		this.estado = estado;
		this.usrCreacion = usrCreacion;
		this.tsCreacion = tsCreacion;
		this.usrModificacion = usrModificacion;
		this.tsModificacion = tsModificacion;
	}

	/**
	 * @return the telefonoLista
	 */
	public List<String> getTelefonoLista() {
		if (this.direccionTelefonoList != null && !this.direccionTelefonoList.isEmpty()) {
			telefonoLista = new ArrayList<String>();

			for (DireccionTelefonoSd dt : this.direccionTelefonoList) {
				telefonoLista.add(dt.getTelefono().getNroTelefono());
			}
		}

		return telefonoLista;
	}

	/**
	 * @param telefonoLista
	 *            the telefonoLista to set
	 */
	public void setTelefonoLista(List<String> telefonoLista) {
		this.telefonoLista = telefonoLista;
	}

	public Integer getSecDireccion() {
		return secDireccion;
	}

	public void setSecDireccion(Integer secDireccion) {
		this.secDireccion = secDireccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public TipoDireccionSd getCodTipoDireccion() {
		return codTipoDireccion;
	}

	public void setCodTipoDireccion(TipoDireccionSd codTipoDireccion) {
		this.codTipoDireccion = codTipoDireccion;
	}

	public ProvinciaSd getSecProvincia() {
		return secProvincia;
	}

	public void setSecProvincia(ProvinciaSd secProvincia) {
		this.secProvincia = secProvincia;
	}

	public PersonaSd getSecPersona() {
		return secPersona;
	}

	public void setSecPersona(PersonaSd secPersona) {
		this.secPersona = secPersona;
	}

	public ParroquiaSd getSecParroquia() {
		return secParroquia;
	}

	public void setSecParroquia(ParroquiaSd secParroquia) {
		this.secParroquia = secParroquia;
	}

	public CantonSd getSecCanton() {
		return secCanton;
	}

	public void setSecCanton(CantonSd secCanton) {
		this.secCanton = secCanton;
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
		hash += (secDireccion != null ? secDireccion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DireccionSd)) {
			return false;
		}
		DireccionSd other = (DireccionSd) object;
		if ((this.secDireccion == null && other.secDireccion != null)
				|| (this.secDireccion != null && !this.secDireccion.equals(other.secDireccion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.Direccion[ secDireccion=" + secDireccion + ", direccion=" + direccion
				+ ",secPersona=" + ((secPersona == null) ? "null" : secPersona.getSecPersona()) + " ]";
	}

	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia
	 *            the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the direccionCompleta
	 */
	public boolean isDireccionCompleta() {
		return direccionCompleta;
	}

	/**
	 * @param direccionCompleta
	 *            the direccionCompleta to set
	 */
	public void setDireccionCompleta(boolean direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	/**
	 * @return the principalTr
	 */
	public String getPrincipalTr() {
		return principalTr;
	}

	/**
	 * @param principalTr
	 *            the principalTr to set
	 */
	public void setPrincipalTr(String principalTr) {
		this.principalTr = principalTr;
	}

	/**
	 * @return the numeroTr
	 */
	public String getNumeroTr() {
		return numeroTr;
	}

	/**
	 * @param numeroTr
	 *            the numeroTr to set
	 */
	public void setNumeroTr(String numeroTr) {
		this.numeroTr = numeroTr;
	}

	/**
	 * @return the secundariaTr
	 */
	public String getSecundariaTr() {
		return secundariaTr;
	}

	/**
	 * @param secundariaTr
	 *            the secundariaTr to set
	 */
	public void setSecundariaTr(String secundariaTr) {
		this.secundariaTr = secundariaTr;
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
