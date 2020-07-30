/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.equivida.util.StringUtil;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PERSONA")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
		@NamedQuery(name = "Persona.findBySecPersona", query = "SELECT p FROM Persona p WHERE p.secPersona = :secPersona"),
		@NamedQuery(name = "Persona.findByCliente", query = "SELECT p FROM Persona p WHERE p.cliente = :cliente"),
		@NamedQuery(name = "Persona.findByDenominacion", query = "SELECT p FROM Persona p WHERE p.denominacion = :denominacion"),
		@NamedQuery(name = "Persona.findByFchCreacion", query = "SELECT p FROM Persona p WHERE p.fchCreacion = :fchCreacion"),
		@NamedQuery(name = "Persona.findByFchModificacion", query = "SELECT p FROM Persona p WHERE p.fchModificacion = :fchModificacion") })
public class Persona implements Serializable {

	private static final long serialVersionUID = 2084073995341041277L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PERSONA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secPersona;
	@Basic(optional = false)
	@Column(name = "CLIENTE")
	@XmlTransient
	private char cliente;
	@Basic(optional = false)
	@Column(name = "DENOMINACION")
	private String denominacion;

	@Basic(optional = false)
	@Column(name = "TS_CREACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date fchCreacion;
	@Basic(optional = false)
	@Column(name = "TS_MODIFICACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date fchModificacion;
	@JoinColumn(name = "COD_TIPO_IDENTIFICACION", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = false)
	private TipoIdentificacion tipoIdentificacion;

	@Basic(optional = false)
	@Column(name = "CATEGORIA")
	@XmlTransient
	private char categoria;// G Global, E Especialista

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	// @Transient
	// private Collection<SeguroVigente> segurosVigentesCollection;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	@Transient
	@XmlTransient
	private Collection<MotivoSeguro> motivoSeguroFormularioCollection;

	// @Transient
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	// private Collection<Interviniente> intervinienteCollection;

	// @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
	@Transient
	@XmlTransient
	private PersonaNatural personaNaturalTransient;
	
	@Transient
	@XmlTransient
	private PersonaJuridica personaJuridicaTransient;
	
	// cascade NONE para que cuando actualiza recurepar los id y luego grabar en
	// direccion-telefono
	@XmlTransient
	@OneToMany(mappedBy = "persona")
	private Collection<Direccion> direccionCollection;

	// @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
	@Transient
	@XmlTransient
	private PersonaComponenteExclusion personaComponenteExclusionTransient;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	// private Collection<DireccionElectronica> direccionElectronicaCollection;

	@Transient
	@XmlTransient
	private Collection<DireccionElectronica> direccionElectronicaFormularioCollection;

	// cascade NONE para que cuando actualiza recurepar los id y luego grabar en
	// direccion-telefono
	// @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
	@Transient
	@XmlTransient
	private ContactoPreferido contactoPreferidoTransient;

	// cascade NONE para que cuando actualiza recurepar los id y luego grabar en
	// direccion-telefono
	@OneToMany(mappedBy = "persona")
	@XmlTransient
	private Collection<Telefono> telefonoCollection;

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

	// @Transient
	// private Collection<DireccionTelefono> direccionTelefonoCollection;

	@Transient
	private Collection<Telefono> telefonoSinDireccionCollection;// los moviles

	@Transient
	private Collection<BeneficiarioPoliza> beneficiarioPolizaCollection;

	@Transient
	private List<Hijo> hijos; // listado de hojos

	@Transient
	private List<Hijo> hijosEliminar;

	@Transient
	@XmlTransient
	private Telefono noCelularSoloTra;
	@Transient
	@XmlTransient
	private DireccionElectronica emailFacturacionElectronicaTra;
	@Transient
	@XmlTransient
	private DireccionElectronica emailPersonalTra;

	public List<Hijo> getHijosEliminar() {
		return hijosEliminar;
	}

	public void setHijosEliminar(List<Hijo> hijosEliminar) {
		this.hijosEliminar = hijosEliminar;
	}

	@Transient
	public Collection<DireccionElectronica> getDireccionElectronicaActivosCollection() {
		Collection<DireccionElectronica> activos = new ArrayList<DireccionElectronica>();

		if (direccionElectronicaFormularioCollection != null) {
			for (DireccionElectronica de : direccionElectronicaFormularioCollection) {
				if (de.getActivo()) {
					activos.add(de);
				}
			}
		}

		return activos;
	}

	@Transient
	public int getTotalDireccionesElectronicasActivos() {
		return getDireccionElectronicaActivosCollection().size();
	}

	@Transient
	public Collection<Direccion> getDireccionActivosCollection() {
		Collection<Direccion> activos = new ArrayList<Direccion>();
		if (direccionCollection != null) {
			for (Direccion dir : direccionCollection) {
				if (dir.getActivo()) {
					activos.add(dir);
				}
			}
		}
		return activos;
	}

	@Transient
	public int getTotalDireccionesActivos() {
		return getDireccionActivosCollection().size();
	}

	@Transient
	public int getTotalTelefonoSinDireccionesActivos() {
		int total = 0;

		if (telefonoSinDireccionCollection != null) {
			for (Telefono t : telefonoSinDireccionCollection) {
				if (t.getActivo()) {
					total++;
				}
			}
		}

		return total;
	}

	@Transient
	public Collection<Telefono> getTelefonoSinDireccionActivosCollection() {
		Collection<Telefono> activos = new ArrayList<Telefono>();

		if (telefonoSinDireccionCollection != null && !telefonoSinDireccionCollection.isEmpty()) {
			for (Telefono telf : telefonoSinDireccionCollection) {
				if (telf.getActivo()) {
					activos.add(telf);
				}
			}
		}

		return activos;
	}

	@Transient
	@XmlTransient
	private Persona original;

	public Persona() {
	}

	public Persona(Integer secPersona) {
		this.secPersona = secPersona;
	}

	public Persona(Integer secPersona, char cliente, String denominacion, Date fchCreacion, Date fchModificacion) {
		this.secPersona = secPersona;
		this.cliente = cliente;
		this.denominacion = denominacion;
		this.fchCreacion = fchCreacion;
		this.fchModificacion = fchModificacion;
	}

	public Integer getSecPersona() {
		return secPersona;
	}

	public void setSecPersona(Integer secPersona) {
		this.secPersona = secPersona;
	}

	public char getCliente() {
		return cliente;
	}

	public void setCliente(char cliente) {
		this.cliente = cliente;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		if (denominacion != null) {
			this.denominacion = StringUtil.toUpper(denominacion).trim();
		}
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

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public Collection<Telefono> getTelefonoCollection() {
		return telefonoCollection;
	}

	public void setTelefonoCollection(Collection<Telefono> telefonoCollection) {
		this.telefonoCollection = telefonoCollection;
	}

	public Collection<Direccion> getDireccionCollection() {
		return direccionCollection;
	}

	public void setDireccionCollection(Collection<Direccion> direccionCollection) {
		this.direccionCollection = direccionCollection;
	}

	/**
	 * @return the direccionTelefonoCollection
	 */
	// public Collection<DireccionTelefono> getDireccionTelefonoCollection() {
	// return direccionTelefonoCollection;
	// }

	/**
	 * @param direccionTelefonoCollection the direccionTelefonoCollection to set
	 */
	// public void setDireccionTelefonoCollection(
	// Collection<DireccionTelefono> direccionTelefonoCollection) {
	// this.direccionTelefonoCollection = direccionTelefonoCollection;
	// }

	public Collection<Telefono> getTelefonoSinDireccionCollection() {
		return telefonoSinDireccionCollection;
	}

	public void setTelefonoSinDireccionCollection(Collection<Telefono> telefonoSinDireccionCollection) {
		this.telefonoSinDireccionCollection = telefonoSinDireccionCollection;
	}

	public Collection<BeneficiarioPoliza> getBeneficiarioPolizaCollection() {
		return beneficiarioPolizaCollection;
	}

	public void setBeneficiarioPolizaCollection(Collection<BeneficiarioPoliza> beneficiarioPolizaCollection) {
		this.beneficiarioPolizaCollection = beneficiarioPolizaCollection;
	}

	public Collection<MotivoSeguro> getMotivoSeguroFormularioCollection() {
		return motivoSeguroFormularioCollection;
	}

	public void setMotivoSeguroFormularioCollection(Collection<MotivoSeguro> motivoSeguroFormularioCollection) {
		this.motivoSeguroFormularioCollection = motivoSeguroFormularioCollection;
	}

	public PersonaComponenteExclusion getPersonaComponenteExclusionTransient() {
		return personaComponenteExclusionTransient;
	}

	public void setPersonaComponenteExclusionTransient(PersonaComponenteExclusion personaComponenteExclusionTransient) {
		this.personaComponenteExclusionTransient = personaComponenteExclusionTransient;
	}

	public PersonaNatural getPersonaNaturalTransient() {
		return personaNaturalTransient;
	}

	public void setPersonaNaturalTransient(PersonaNatural personaNaturalTransient) {
		this.personaNaturalTransient = personaNaturalTransient;
	}

	public ContactoPreferido getContactoPreferidoTransient() {
		return contactoPreferidoTransient;
	}

	public void setContactoPreferidoTransient(ContactoPreferido contactoPreferidoTransient) {
		this.contactoPreferidoTransient = contactoPreferidoTransient;
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

	public String getCtaCreacion() {
		return ctaCreacion;
	}

	public void setCtaCreacion(String ctaCreacion) {
		this.ctaCreacion = ctaCreacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cliente;
		result = prime * result + ((denominacion == null) ? 0 : denominacion.hashCode());
		result = prime * result + ((secPersona == null) ? 0 : secPersona.hashCode());
		result = prime * result + ((tipoIdentificacion == null) ? 0 : tipoIdentificacion.hashCode());
		result = prime * result + categoria;
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
		Persona other = (Persona) obj;
		if (cliente != other.cliente)
			return false;
		if (categoria != other.categoria)
			return false;
		if (denominacion == null) {
			if (other.denominacion != null)
				return false;
		} else if (!denominacion.equals(other.denominacion))
			return false;
		if (secPersona == null) {
			if (other.secPersona != null)
				return false;
		} else if (!secPersona.equals(other.secPersona))
			return false;
		if (tipoIdentificacion == null) {
			if (other.tipoIdentificacion != null)
				return false;
		} else if (!tipoIdentificacion.equals(other.tipoIdentificacion))
			return false;
		return true;
	}

	public Persona getOriginal() {
		return original;
	}

	public void setOriginal(Persona original) {
		this.original = original;
	}

	@Override
	public String toString() {
		return "Persona [secPersona=" + secPersona + ", cliente=" + cliente + ", denominacion=" + denominacion
				+ ", tipoIdentificacion=" + tipoIdentificacion + ", categoria=" + categoria + "]";
	}

	public Collection<DireccionElectronica> getDireccionElectronicaFormularioCollection() {
		return direccionElectronicaFormularioCollection;
	}

	public void setDireccionElectronicaFormularioCollection(
			Collection<DireccionElectronica> direccionElectronicaFormularioCollection) {
		this.direccionElectronicaFormularioCollection = direccionElectronicaFormularioCollection;
	}

	public List<Hijo> getHijos() {
		return hijos;
	}

	public void setHijos(List<Hijo> hijos) {
		this.hijos = hijos;
	}

	public char getCategoria() {
		return categoria;
	}

	public void setCategoria(char categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the noCelularSoloTra
	 */
	public Telefono getNoCelularSoloTra() {
		return noCelularSoloTra;
	}

	/**
	 * @param noCelularSoloTra the noCelularSoloTra to set
	 */
	public void setNoCelularSoloTra(Telefono noCelularSoloTra) {
		this.noCelularSoloTra = noCelularSoloTra;
	}

	/**
	 * @return the emailFacturacionElectronicaTra
	 */
	public DireccionElectronica getEmailFacturacionElectronicaTra() {
		return emailFacturacionElectronicaTra;
	}

	/**
	 * @param emailFacturacionElectronicaTra the emailFacturacionElectronicaTra to
	 *                                       set
	 */
	public void setEmailFacturacionElectronicaTra(DireccionElectronica emailFacturacionElectronicaTra) {
		this.emailFacturacionElectronicaTra = emailFacturacionElectronicaTra;
	}

	/**
	 * @return the emailPersonalTra
	 */
	public DireccionElectronica getEmailPersonalTra() {
		return emailPersonalTra;
	}

	/**
	 * @param emailPersonalTra the emailPersonalTra to set
	 */
	public void setEmailPersonalTra(DireccionElectronica emailPersonalTra) {
		this.emailPersonalTra = emailPersonalTra;
	}

	public PersonaJuridica getPersonaJuridicaTransient() {
		return personaJuridicaTransient;
	}

	public void setPersonaJuridicaTransient(PersonaJuridica personaJuridicaTransient) {
		this.personaJuridicaTransient = personaJuridicaTransient;
	}

}