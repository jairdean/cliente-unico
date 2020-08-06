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
 * @author juan
 */
@Entity
@Table(name = "INFORMACION_ADICIONAL")
@XmlAccessorType(XmlAccessType.FIELD)
public class InformacionAdicionalSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_INFORMACION_ADIC")
	private Integer secInformacionAdic;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 13)
	@Column(name = "IDENTIFICACION", columnDefinition = "char")
	private String identificacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	@Basic(optional = false)
	@Size(max = 128)
	@Column(name = "NOMBRE_COMERCIAL")
	private String nombreComercial;
	@Basic(optional = false)
	@NotNull
	@Column(name = "FCH_INSCRIPCION")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaInscripcion")
	@XmlSchemaType(name = "date")
	private Date fchInscripcion;
	@Basic(optional = false)
	@NotNull
	@Column(name = "FCH_INICIO_ACTIVIDADES")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaInicioActividades")
	@XmlSchemaType(name = "date")
	private Date fchInicioActividades;
	@Column(name = "FCH_CANCELACION")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaCancelacion")
	@XmlSchemaType(name = "date")
	private Date fchCancelacion;
	@Column(name = "FCH_SUSPENSION")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaSuspension")
	@XmlSchemaType(name = "date")
	private Date fchSuspension;
	@Column(name = "FCH_REINICIO")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaReinicio")
	@XmlSchemaType(name = "date")
	private Date fchReinicio;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "PRINCIPAL")
	private String principal;
	@Size(max = 8)
	@Column(name = "NUMERO")
	private String numero;
	@Size(max = 128)
	@Column(name = "SECUNDARIA")
	private String secundaria;
	@Size(max = 128)
	@Column(name = "REFERENCIA")
	private String referencia;
	@Size(max = 12)
	@Column(name = "TELEFONO")
	private String telefono;
	@Size(max = 64)
	@Column(name = "E_MAIL")
	private String eMail;
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
	@XmlElement(name = "tipoIdentificacion")
	private TipoIdentificacionSd codTipoIdentificacion;
	@JoinColumn(name = "SEC_PROVINCIA", referencedColumnName = "SEC_PROVINCIA")
	@ManyToOne(optional = false)
	@XmlElement(name = "provincia")
	private ProvinciaSd secProvincia;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNaturalSd secPersonaNatural;
	@JoinColumn(name = "SEC_PARROQUIA", referencedColumnName = "SEC_PARROQUIA")
	@ManyToOne(optional = false)
	@XmlElement(name = "parroquia")
	private ParroquiaSd secParroquia;
	@JoinColumn(name = "SEC_CANTON", referencedColumnName = "SEC_CANTON")
	@ManyToOne(optional = false)
	@XmlElement(name = "canton")
	private CantonSd secCanton;
	@JoinColumn(name = "SEC_CANAL", referencedColumnName = "SEC_CANAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private CanalSd secCanal;
	@JoinColumn(name = "COD_ACTIVIDAD_ECONOMICA", referencedColumnName = "COD_ACTIVIDAD_ECONOMICA")
	@ManyToOne(optional = false)
	@XmlElement(name = "actividadEconomica")
	private ActividadEconomicaSd codActividadEconomica;

	public InformacionAdicionalSd() {
	}

	public InformacionAdicionalSd(Integer secInformacionAdic) {
		this.secInformacionAdic = secInformacionAdic;
	}

	public InformacionAdicionalSd(Integer secInformacionAdic, String identificacion, String razonSocial,
			Date fchInscripcion, Date fchInicioActividades, String principal, String usrCreacion, Date tsCreacion,
			String usrModificacion, Date tsModificacion, String nombreComercial) {
		this.secInformacionAdic = secInformacionAdic;
		this.identificacion = identificacion;
		this.razonSocial = razonSocial;
		this.fchInscripcion = fchInscripcion;
		this.fchInicioActividades = fchInicioActividades;
		this.principal = principal;
		this.usrCreacion = usrCreacion;
		this.tsCreacion = tsCreacion;
		this.usrModificacion = usrModificacion;
		this.tsModificacion = tsModificacion;
		this.nombreComercial = nombreComercial;
	}

	public Integer getSecInformacionAdic() {
		return secInformacionAdic;
	}

	public void setSecInformacionAdic(Integer secInformacionAdic) {
		this.secInformacionAdic = secInformacionAdic;
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

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public Date getFchInscripcion() {
		return fchInscripcion;
	}

	public void setFchInscripcion(Date fchInscripcion) {
		this.fchInscripcion = fchInscripcion;
	}

	public Date getFchInicioActividades() {
		return fchInicioActividades;
	}

	public void setFchInicioActividades(Date fchInicioActividades) {
		this.fchInicioActividades = fchInicioActividades;
	}

	public Date getFchCancelacion() {
		return fchCancelacion;
	}

	public void setFchCancelacion(Date fchCancelacion) {
		this.fchCancelacion = fchCancelacion;
	}

	public Date getFchSuspension() {
		return fchSuspension;
	}

	public void setFchSuspension(Date fchSuspension) {
		this.fchSuspension = fchSuspension;
	}

	public Date getFchReinicio() {
		return fchReinicio;
	}

	public void setFchReinicio(Date fchReinicio) {
		this.fchReinicio = fchReinicio;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSecundaria() {
		return secundaria;
	}

	public void setSecundaria(String secundaria) {
		this.secundaria = secundaria;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
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

	public void setCodTipoIdentificacion(TipoIdentificacionSd codTipoIdentificacion) {
		this.codTipoIdentificacion = codTipoIdentificacion;
	}

	public ProvinciaSd getSecProvincia() {
		return secProvincia;
	}

	public void setSecProvincia(ProvinciaSd secProvincia) {
		this.secProvincia = secProvincia;
	}

	public PersonaNaturalSd getSecPersonaNatural() {
		return secPersonaNatural;
	}

	public void setSecPersonaNatural(PersonaNaturalSd secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
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

	public ActividadEconomicaSd getCodActividadEconomica() {
		return codActividadEconomica;
	}

	public void setCodActividadEconomica(ActividadEconomicaSd codActividadEconomica) {
		this.codActividadEconomica = codActividadEconomica;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secInformacionAdic != null ? secInformacionAdic.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof InformacionAdicionalSd)) {
			return false;
		}
		InformacionAdicionalSd other = (InformacionAdicionalSd) object;
		if ((this.secInformacionAdic == null && other.secInformacionAdic != null)
				|| (this.secInformacionAdic != null && !this.secInformacionAdic.equals(other.secInformacionAdic))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.InformacionAdicional[ secInformacionAdic=" + secInformacionAdic + " ]";
	}

}
