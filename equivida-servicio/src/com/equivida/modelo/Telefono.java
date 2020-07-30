/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "TELEFONO")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "Telefono.findAll", query = "SELECT t FROM Telefono t"),
		@NamedQuery(name = "Telefono.findBySecTelefono", query = "SELECT t FROM Telefono t WHERE t.secTelefono = :secTelefono"),
		@NamedQuery(name = "Telefono.findByCodArea", query = "SELECT t FROM Telefono t WHERE t.codArea = :codArea"),
		@NamedQuery(name = "Telefono.findByNroTelefono", query = "SELECT t FROM Telefono t WHERE t.nroTelefono = :nroTelefono"),
		@NamedQuery(name = "Telefono.findByExtTelefono", query = "SELECT t FROM Telefono t WHERE t.extTelefono = :extTelefono"),
		@NamedQuery(name = "Telefono.findByFchCreacion", query = "SELECT t FROM Telefono t WHERE t.fchCreacion = :fchCreacion"),
		@NamedQuery(name = "Telefono.findByFchModificacion", query = "SELECT t FROM Telefono t WHERE t.fchModificacion = :fchModificacion") })
public class Telefono implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2861963736513659389L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_TELEFONO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secTelefono;
	@Basic(optional = false)
	@Column(name = "COD_AREA")
	private String codArea;
	@Basic(optional = false)
	@Column(name = "NRO_TELEFONO")
	private String nroTelefono;
	@Column(name = "EXT_TELEFONO")
	private String extTelefono;
	@Basic(optional = false)
	@Column(name = "TS_CREACION", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date fchCreacion;
	@Basic(optional = false)
	@Column(name = "TS_MODIFICACION", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date fchModificacion;
	@JoinColumn(name = "COD_TIPO_TELEFONO", referencedColumnName = "COD_TIPO_TELEFONO")
	@ManyToOne(optional = false)
	private TipoTelefono tipoTelefono;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	@XmlTransient
	private Persona persona;
	@JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS")
	@ManyToOne(optional = false)
	private Pais pais;

	@Transient
	private Telefono original;

	// @Transient
	// private Collection<DireccionTelefono> direccionTelefonoCollection;

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
	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo();
	}

	@Transient
	private Boolean principal;

	@Transient
	private String telefonoConCodigoAreaAnterior;

	@Transient
	public String getTelefonoConCodigoArea() {
		String codArea = this.getCodArea();
		if (codArea == null) {
			codArea = "";
		}
		String telf = codArea + this.getNroTelefono();
		return telf;
	}

	@Transient
	public String getTelefonoConAsteriscos() {
		String anterior = getTelefonoConCodigoAreaAnterior();
		String nuevo = getTelefonoConCodigoArea();
		if (anterior == null) {
			anterior = nuevo + "";
		}
		String conAsteriscos = anterior + "***" + nuevo;
		return conAsteriscos;
	}

	@Column(name = "ESTADO")
	private char estado;// A-I

	public Telefono() {
	}

	public Telefono(Integer secTelefono) {
		this.secTelefono = secTelefono;
	}

	public Telefono(Integer secTelefono, String codArea, String nroTelefono, Date fchCreacion, Date fchModificacion) {
		this.secTelefono = secTelefono;
		this.codArea = codArea;
		this.nroTelefono = nroTelefono;
		this.fchCreacion = fchCreacion;
		this.fchModificacion = fchModificacion;
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

	public String getExtTelefono() {
		return extTelefono;
	}

	public void setExtTelefono(String extTelefono) {
		this.extTelefono = extTelefono;
	}

	public Date getFchCreacion() {
		return fchCreacion;
	}

	public void setFchCreacion(Date fchCreacion) {
		this.fchCreacion = fchCreacion;
	}

	public Date getFchModificacion() {
		return fchModificacion;
	}

	public void setFchModificacion(Date fchModificacion) {
		this.fchModificacion = fchModificacion;
	}

	public TipoTelefono getTipoTelefono() {
		return tipoTelefono;
	}

	public void setTipoTelefono(TipoTelefono tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Telefono [secTelefono=" + secTelefono + ", codArea=" + codArea + ", nroTelefono=" + nroTelefono
				+ ", extTelefono=" + extTelefono + ", tipoTelefono=" + tipoTelefono + ", persona="
				+ ((persona != null) ? persona.getSecPersona() : "") + ", pais=" + pais + "]";
	}

	/**
	 * @return the principal
	 */
	public Boolean getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codArea == null) ? 0 : codArea.hashCode());
		result = prime * result + estado;
		result = prime * result + ((extTelefono == null) ? 0 : extTelefono.hashCode());
		result = prime * result + ((nroTelefono == null) ? 0 : nroTelefono.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		result = prime * result + ((principal == null) ? 0 : principal.hashCode());
		result = prime * result + ((secTelefono == null) ? 0 : secTelefono.hashCode());
		result = prime * result + ((tipoTelefono == null) ? 0 : tipoTelefono.hashCode());
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
		Telefono other = (Telefono) obj;
		if (codArea == null) {
			if (other.codArea != null)
				return false;
		} else if (!codArea.equals(other.codArea))
			return false;
		if (estado != other.estado)
			return false;
		if (extTelefono == null) {
			if (other.extTelefono != null)
				return false;
		} else if (!extTelefono.equals(other.extTelefono))
			return false;
		if (nroTelefono == null) {
			if (other.nroTelefono != null)
				return false;
		} else if (!nroTelefono.equals(other.nroTelefono))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		if (principal == null) {
			if (other.principal != null)
				return false;
		} else if (!principal.equals(other.principal))
			return false;
		if (secTelefono == null) {
			if (other.secTelefono != null)
				return false;
		} else if (!secTelefono.equals(other.secTelefono))
			return false;
		if (tipoTelefono == null) {
			if (other.tipoTelefono != null)
				return false;
		} else if (!tipoTelefono.equals(other.tipoTelefono))
			return false;
		return true;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public String getTelefonoConCodigoAreaAnterior() {
		return telefonoConCodigoAreaAnterior;
	}

	public void setTelefonoConCodigoAreaAnterior(String telefonoConCodigoAreaAnterior) {
		this.telefonoConCodigoAreaAnterior = telefonoConCodigoAreaAnterior;
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

	public Telefono getOriginal() {
		return original;
	}

	public void setOriginal(Telefono original) {
		this.original = original;
	}
}
