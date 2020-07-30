/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PERSONA_JURIDICA")
@NamedQueries({ @NamedQuery(name = "PersonaJuridica.findAll", query = "SELECT p FROM PersonaJuridica p"),
		@NamedQuery(name = "PersonaJuridica.findBySecPersonaJuridica", query = "SELECT p FROM PersonaJuridica p WHERE p.secPersonaJuridica = :secPersonaJuridica"),
		@NamedQuery(name = "PersonaJuridica.findByIdentificacion", query = "SELECT p FROM PersonaJuridica p WHERE p.identificacion = :identificacion"),
		@NamedQuery(name = "PersonaJuridica.findByFchConstitucion", query = "SELECT p FROM PersonaJuridica p WHERE p.fchConstitucion = :fchConstitucion"),
		@NamedQuery(name = "PersonaJuridica.findByFchDisolucion", query = "SELECT p FROM PersonaJuridica p WHERE p.fchDisolucion = :fchDisolucion"),
		@NamedQuery(name = "PersonaJuridica.findByTsCreacion", query = "SELECT p FROM PersonaJuridica p WHERE p.tsCreacion = :tsCreacion"),
		@NamedQuery(name = "PersonaJuridica.findByTsModificacion", query = "SELECT p FROM PersonaJuridica p WHERE p.tsModificacion = :tsModificacion"),
		@NamedQuery(name = "PersonaJuridica.findByRazonSocial", query = "SELECT p FROM PersonaJuridica p WHERE p.razonSocial = :razonSocial"),
		@NamedQuery(name = "PersonaJuridica.findByNombreComercial", query = "SELECT p FROM PersonaJuridica p WHERE p.nombreComercial = :nombreComercial"),
		@NamedQuery(name = "PersonaJuridica.findByObjetoSocial", query = "SELECT p FROM PersonaJuridica p WHERE p.objetoSocial = :objetoSocial"),
		@NamedQuery(name = "PersonaJuridica.findByNombreContacto", query = "SELECT p FROM PersonaJuridica p WHERE p.nombreContacto = :nombreContacto"),
		@NamedQuery(name = "PersonaJuridica.findByEMailContacto", query = "SELECT p FROM PersonaJuridica p WHERE p.emailContacto = :emailContacto") })
public class PersonaJuridica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2838602371366087838L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PERSONA_JURIDICA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secPersonaJuridica;
	@Basic(optional = false)
	@Column(name = "IDENTIFICACION", length = 13, columnDefinition = "char(13)")
	private String identificacion;
	@Basic(optional = false)
	@Column(name = "FCH_CONSTITUCION")
	@Temporal(TemporalType.DATE)
	private Date fchConstitucion;
	@Column(name = "FCH_DISOLUCION")
	@Temporal(TemporalType.DATE)
	private Date fchDisolucion;
	@Basic(optional = false)
	@Column(name = "TS_CREACION", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tsCreacion;
	@Basic(optional = false)
	@Column(name = "TS_MODIFICACION", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tsModificacion;
	@Basic(optional = false)
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	@Basic(optional = false)
	@Column(name = "NOMBRE_COMERCIAL")
	private String nombreComercial;
	@Basic(optional = false)
	@Column(name = "OBJETO_SOCIAL")
	private String objetoSocial;
	@Basic(optional = false)
	@Column(name = "NOMBRE_CONTACTO")
	private String nombreContacto;
	@Basic(optional = false)
	@Column(name = "E_MAIL_CONTACTO")
	private String emailContacto;
	@JoinColumn(name = "COD_TIPO_PERSONA_JURIDICA", referencedColumnName = "COD_TIPO_PERSONA_JURIDICA")
	@ManyToOne(optional = false)
	private TipoPersonaJuridica tipoPersonaJuridica;
	@JoinColumn(name = "COD_TIPO_IDENTIFICACION", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = false)
	private TipoIdentificacion tipoIdentificacion;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@OneToOne(optional = false)
	private Persona persona;
	@JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS")
	@ManyToOne(optional = false)
	private Pais pais;
	@JoinColumn(name = "COD_ACTIVIDAD_ECONOMICA", referencedColumnName = "COD_ACTIVIDAD_ECONOMICA")
	@ManyToOne(optional = false)
	private ActividadEconomica actividadEconomica;
	@JoinColumn(name = "COD_SECTOR_MERCADO", referencedColumnName = "COD_SECTOR_MERCADO")
	@ManyToOne(optional = false)
	private SectorMercado sectorMercado;

	// auditoria
	@Column(name = "USR_CREACION")
	private String usrCreacion;

	@Column(name = "TTY_CREACION")
	private String ttyCreacion;// terminal

	@Column(name = "PRG_CREACION")
	private String prgCreacion;

	@Column(name = "CTA_CREACION")
	private String ctaCreacion;

	@Column(name = "USR_MODIFICACION")
	private String usrModificacion;

	@Column(name = "TTY_MODIFICACION")
	private String ttyModificacion;

	@Column(name = "PRG_MODIFICACION")
	private String prgModificacion;

	@Column(name = "CTA_MODIFICACION")
	private String ctaModificacion;

	@Transient
	private PerfilFinancieroJuridica perfilFinancieroJuridicaTransient;

	@Transient
	private List<Relacion> accionistaListTra;

	@Transient
	private Persona representante;

	@Transient
	private PersonaNatural accionistaIteracionRcs;// solo para iterar y validar RCS

	@Transient
	private int tiempoMercado;

	@Transient
	private String moneda;

	public PersonaJuridica() {
	}

	public PersonaJuridica(Integer secPersonaJuridica) {
		this.secPersonaJuridica = secPersonaJuridica;
	}

	public PersonaJuridica(Integer secPersonaJuridica, String identificacion, Date fchConstitucion, Date tsCreacion,
			Date tsModificacion, String razonSocial, String nombreComercial, String objetoSocial, String nombreContacto,
			String emailContacto) {
		this.secPersonaJuridica = secPersonaJuridica;
		this.identificacion = identificacion;
		this.fchConstitucion = fchConstitucion;
		this.tsCreacion = tsCreacion;
		this.tsModificacion = tsModificacion;
		this.razonSocial = razonSocial;
		this.nombreComercial = nombreComercial;
		this.objetoSocial = objetoSocial;
		this.nombreContacto = nombreContacto;
		this.emailContacto = emailContacto;
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

	public Date getFchConstitucion() {
		return fchConstitucion;
	}

	public void setFchConstitucion(Date fchConstitucion) {
		this.fchConstitucion = fchConstitucion;
	}

	public Date getFchDisolucion() {
		return fchDisolucion;
	}

	public void setFchDisolucion(Date fchDisolucion) {
		this.fchDisolucion = fchDisolucion;
	}

	public Date getTsCreacion() {
		return tsCreacion;
	}

	public void setTsCreacion(Date tsCreacion) {
		this.tsCreacion = tsCreacion;
	}

	public Date getTsModificacion() {
		return tsModificacion;
	}

	public void setTsModificacion(Date tsModificacion) {
		this.tsModificacion = tsModificacion;
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

	public String getObjetoSocial() {
		return objetoSocial;
	}

	public void setObjetoSocial(String objetoSocial) {
		this.objetoSocial = objetoSocial;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public TipoPersonaJuridica getTipoPersonaJuridica() {
		return tipoPersonaJuridica;
	}

	public void setTipoPersonaJuridica(TipoPersonaJuridica tipoPersonaJuridica) {
		this.tipoPersonaJuridica = tipoPersonaJuridica;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
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

	public ActividadEconomica getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(ActividadEconomica actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
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
		if (!(object instanceof PersonaJuridica)) {
			return false;
		}
		PersonaJuridica other = (PersonaJuridica) object;
		if ((this.secPersonaJuridica == null && other.secPersonaJuridica != null)
				|| (this.secPersonaJuridica != null && !this.secPersonaJuridica.equals(other.secPersonaJuridica))) {
			return false;
		}
		return true;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	/**
	 * @return the accionistaListTra
	 */
	public List<Relacion> getAccionistaListTra() {
		return accionistaListTra;
	}

	@Transient
	public List<Relacion> getAccionistaListTraActivos() {
		List<Relacion> activos = new ArrayList<Relacion>();
		if (accionistaListTra != null) {
			for (Relacion r : accionistaListTra) {
				if (r.getActivo()) {
					TipoParentescoRelacion tpw = r.getTipoParentescoRelacion();
					r.setTipoParentescoRelacion(new TipoParentescoRelacion(tpw.getCodTipoParentesco(),
							tpw.getTipoParentesco(), tpw.getFlgFamiliar()));
					activos.add(r);
				}
			}
		}
		return activos;
	}

	/**
	 * @param accionistaListTra the accionistaListTra to set
	 */
	public void setAccionistaListTra(List<Relacion> accionistaListTra) {
		this.accionistaListTra = accionistaListTra;
	}

	/**
	 * @return the sectorMercado
	 */
	public SectorMercado getSectorMercado() {
		return sectorMercado;
	}

	/**
	 * @param sectorMercado the sectorMercado to set
	 */
	public void setSectorMercado(SectorMercado sectorMercado) {
		this.sectorMercado = sectorMercado;
	}

	/**
	 * @return the representante
	 */
	public Persona getRepresentante() {
		return representante;
	}

	/**
	 * @param representante the representante to set
	 */
	public void setRepresentante(Persona representante) {
		this.representante = representante;
	}

	public int getTiempoMercado() {

		if (fchConstitucion != null) {

			Calendar inicio = Calendar.getInstance();
			inicio.setTime(fchConstitucion);

			Calendar fin = Calendar.getInstance();
			if (fchDisolucion != null) {
				fin.setTime(fchDisolucion);
			}

			tiempoMercado = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
		}
		return tiempoMercado;
	}

	public void setTiempoMercado(int tiempoMercado) {
		this.tiempoMercado = tiempoMercado;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public PerfilFinancieroJuridica getPerfilFinancieroJuridicaTransient() {
		return perfilFinancieroJuridicaTransient;
	}

	public void setPerfilFinancieroJuridicaTransient(PerfilFinancieroJuridica perfilFinancieroJuridicaTransient) {
		this.perfilFinancieroJuridicaTransient = perfilFinancieroJuridicaTransient;
	}

	public PersonaNatural getAccionistaIteracionRcs() {
		return accionistaIteracionRcs;
	}

	public void setAccionistasIteracionRcs(PersonaNatural accionistaIteracionRcs) {
		this.accionistaIteracionRcs = accionistaIteracionRcs;
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

	@Override
	public String toString() {
		return "PersonaJuridica ["
				+ (secPersonaJuridica != null ? "secPersonaJuridica=" + secPersonaJuridica + ", " : "")
				+ (identificacion != null ? "identificacion=" + identificacion + ", " : "")
				+ (fchConstitucion != null ? "fchConstitucion=" + fchConstitucion + ", " : "")
				+ (fchDisolucion != null ? "fchDisolucion=" + fchDisolucion + ", " : "")
				+ (tsCreacion != null ? "tsCreacion=" + tsCreacion + ", " : "")
				+ (tsModificacion != null ? "tsModificacion=" + tsModificacion + ", " : "")
				+ (razonSocial != null ? "razonSocial=" + razonSocial + ", " : "")
				+ (nombreComercial != null ? "nombreComercial=" + nombreComercial + ", " : "")
				+ (objetoSocial != null ? "objetoSocial=" + objetoSocial + ", " : "")
				+ (nombreContacto != null ? "nombreContacto=" + nombreContacto + ", " : "")
				+ (emailContacto != null ? "emailContacto=" + emailContacto + ", " : "")
				+ (tipoPersonaJuridica != null ? "tipoPersonaJuridica=" + tipoPersonaJuridica + ", " : "")
				+ (tipoIdentificacion != null ? "tipoIdentificacion=" + tipoIdentificacion + ", " : "")
				+ (persona != null ? "persona=" + persona.toString() + ", " : "")
				+ (pais != null ? "pais=" + pais + ", " : "")
				+ (actividadEconomica != null ? "actividadEconomica=" + actividadEconomica + ", " : "")
				+ (sectorMercado != null ? "sectorMercado=" + sectorMercado + ", " : "")
				+ (usrCreacion != null ? "usrCreacion=" + usrCreacion + ", " : "")
				+ (ttyCreacion != null ? "ttyCreacion=" + ttyCreacion + ", " : "")
				+ (prgCreacion != null ? "prgCreacion=" + prgCreacion + ", " : "")
				+ (ctaCreacion != null ? "ctaCreacion=" + ctaCreacion + ", " : "")
				+ (usrModificacion != null ? "usrModificacion=" + usrModificacion + ", " : "")
				+ (ttyModificacion != null ? "ttyModificacion=" + ttyModificacion + ", " : "")
				+ (prgModificacion != null ? "prgModificacion=" + prgModificacion + ", " : "")
				+ (ctaModificacion != null ? "ctaModificacion=" + ctaModificacion + ", " : "") + "tiempoMercado="
				+ tiempoMercado + "]";
	}

}