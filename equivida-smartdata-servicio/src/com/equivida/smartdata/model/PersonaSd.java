/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.equivida.smartdata.constante.TipoParentescoEnum;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "PERSONA")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "secPersona", "codTipoIdentificacion", "identificacion",
		"denominacion", "personaNatural", "relacionesSinBase", "relaciones",
		"conyuge", "direccionNoPersisteList", "todosTelefonos", "vehiculoList" })
public class PersonaSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_PERSONA", updatable = false, insertable = false)
	private Integer secPersona;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 24)
	@Column(name = "IDENTIFICACION")
	private String identificacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 256)
	@Column(name = "DENOMINACION")
	private String denominacion;
	@JoinColumn(name = "COD_TIPO_IDENTIFICACION", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = false)
	private TipoIdentificacionSd codTipoIdentificacion;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "secPersona")
	private PersonaNaturalSd personaNatural;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "secPersona")
	@XmlTransient
	private List<TelefonoSd> telefonoList;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "secPersona")
	@XmlTransient
	private PersonaJuridicaSd personaJuridica;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "secPersona")
	@XmlTransient
	private List<DireccionSd> direccionList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "secPersona")
	@XmlElement(name = "vehiculo")
	private List<VehiculoSd> vehiculoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaP")
	@XmlElement(name = "relacionBase")
	private List<RelacionSd> relaciones;

	@XmlElement(name = "relacion")
	@Transient
	private List<RelacionSd> relacionesSinBase; // RElaciones para mostrar pero
												// que no estan en la base de
												// datos los objetos

	@XmlElement(name = "direccion")
	@Transient
	private List<DireccionSd> direccionNoPersisteList;

	@XmlTransient
	@Transient
	private List<TelefonoSd> telefonoNoPersisteList;

	@Transient
	private RelacionSd conyuge;

	public PersonaSd() {
	}

	public PersonaSd(Integer secPersona) {
		this.secPersona = secPersona;
	}

	public PersonaSd(Integer secPersona, String identificacion,
			String denominacion) {
		this.secPersona = secPersona;
		this.identificacion = identificacion;
		this.denominacion = denominacion;
	}

	public Integer getSecPersona() {
		return secPersona;
	}

	public void setSecPersona(Integer secPersona) {
		this.secPersona = secPersona;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public TipoIdentificacionSd getCodTipoIdentificacion() {
		return codTipoIdentificacion;
	}

	public void setCodTipoIdentificacion(
			TipoIdentificacionSd codTipoIdentificacion) {
		this.codTipoIdentificacion = codTipoIdentificacion;
	}

	public PersonaNaturalSd getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNaturalSd personaNatural) {
		this.personaNatural = personaNatural;
	}

	public List<TelefonoSd> getTelefonoList() {
		return telefonoList;
	}

	public void setTelefonoList(List<TelefonoSd> telefonoList) {
		this.telefonoList = telefonoList;
	}

	public PersonaJuridicaSd getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridicaSd personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	/**
	 * @return the direccionList
	 */
	public List<DireccionSd> getDireccionList() {
		return direccionList;
	}

	/**
	 * @param direccionList
	 *            the direccionList to set
	 */
	public void setDireccionList(List<DireccionSd> direccionList) {
		this.direccionList = direccionList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secPersona != null ? secPersona.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PersonaSd)) {
			return false;
		}
		PersonaSd other = (PersonaSd) object;
		if ((this.secPersona == null && other.secPersona != null)
				|| (this.secPersona != null && !this.secPersona
						.equals(other.secPersona))) {
			return false;
		}
		return true;
	}

	

	@Override
	public String toString() {
		return "PersonaSd [secPersona=" + secPersona + ", identificacion=" + identificacion + ", denominacion="
				+ denominacion + ", codTipoIdentificacion=" + codTipoIdentificacion + ", personaNatural="
				+ personaNatural + ", telefonoList=" + telefonoList + ", personaJuridica=" + personaJuridica
				+ ", direccionList=" + direccionList + ", vehiculoList=" + vehiculoList + ", relaciones=" + relaciones
				+ ", relacionesSinBase=" + relacionesSinBase + ", direccionNoPersisteList=" + direccionNoPersisteList
				+ ", telefonoNoPersisteList=" + telefonoNoPersisteList + ", conyuge=" + conyuge + "]";
	}

	public List<VehiculoSd> getVehiculoList() {
		return vehiculoList;
	}

	public void setVehiculoList(List<VehiculoSd> vehiculoList) {
		this.vehiculoList = vehiculoList;
	}

	/**
	 * @return the relaciones
	 */
	public List<RelacionSd> getRelaciones() {
		return relaciones;
	}

	/**
	 * @param relaciones
	 *            the relaciones to set
	 */
	public void setRelaciones(List<RelacionSd> relaciones) {
		this.relaciones = relaciones;
	}

	/**
	 * @return the conyuge
	 */
	public RelacionSd getConyuge() {
		if (conyuge == null) {
			if (getTodasLasRelaciones() != null
					&& !getTodasLasRelaciones().isEmpty()) {
				for (RelacionSd r : getTodasLasRelaciones()) {
					if (TipoParentescoEnum.CONYUGE.getCodigoTipoParentesco()
							.equals(r.getTipoParentesco()
									.getCodTipoParentesco())) {
						conyuge = r;
						break;
					}
				}
			}
		}
		return conyuge;
	}

	/**
	 * @param conyuge
	 *            the conyuge to set
	 */
	public void setConyuge(RelacionSd conyuge) {
		this.conyuge = conyuge;
	}

	/**
	 * @return the relacionesSinBase
	 */
	public List<RelacionSd> getRelacionesSinBase() {
		return relacionesSinBase;
	}

	/**
	 * @param relacionesSinBase
	 *            the relacionesSinBase to set
	 */
	public void setRelacionesSinBase(List<RelacionSd> relacionesSinBase) {
		this.relacionesSinBase = relacionesSinBase;
	}

	/**
	 * Devuelve las relaciones que estan en la base y las que no tienen datos en
	 * la base pero se pueden mostrar.
	 * 
	 * @return
	 */
	public List<RelacionSd> getTodasLasRelaciones() {
		List<RelacionSd> resp = new ArrayList<RelacionSd>();

		if (this.relaciones != null && !this.relaciones.isEmpty()) {
			resp.addAll(this.relaciones);
		}

		if (this.relacionesSinBase != null && !this.relacionesSinBase.isEmpty()) {
			resp.addAll(this.relacionesSinBase);
		}

		return resp;
	}

	/**
	 * @return the direccionNoPersisteList
	 */
	public List<DireccionSd> getDireccionNoPersisteList() {
		return direccionNoPersisteList;
	}

	/**
	 * @param direccionNoPersisteList
	 *            the direccionNoPersisteList to set
	 */
	public void setDireccionNoPersisteList(
			List<DireccionSd> direccionNoPersisteList) {
		this.direccionNoPersisteList = direccionNoPersisteList;
	}

	/**
	 * 
	 * Obtiene todas las direcciones para presentar en la pantalla.
	 * 
	 * @return
	 */
	public List<DireccionSd> getTodasDirecciones() {
		List<DireccionSd> resp = new ArrayList<DireccionSd>();

		if (this.direccionList != null && !this.direccionList.isEmpty()) {
			resp.addAll(this.direccionList);
		}

		if (this.direccionNoPersisteList != null
				&& !this.direccionNoPersisteList.isEmpty()) {
			resp.addAll(this.direccionNoPersisteList);
		}

		return resp;
	}

	/**
	 * @return the telefonoNoPersisteList
	 */
	public List<TelefonoSd> getTelefonoNoPersisteList() {
		return telefonoNoPersisteList;
	}

	/**
	 * @param telefonoNoPersisteList
	 *            the telefonoNoPersisteList to set
	 */
	public void setTelefonoNoPersisteList(
			List<TelefonoSd> telefonoNoPersisteList) {
		this.telefonoNoPersisteList = telefonoNoPersisteList;
	}

	@XmlElement(name = "telefonoSinDireccion")
	public List<TelefonoSd> getTodosTelefonos() {
		List<TelefonoSd> resp = new ArrayList<TelefonoSd>();

		if (this.telefonoList != null && !this.telefonoList.isEmpty()) {
			resp.addAll(this.telefonoList);
		}

		if (this.telefonoNoPersisteList != null
				&& !this.telefonoNoPersisteList.isEmpty()) {
			resp.addAll(this.telefonoNoPersisteList);
		}

		return resp;
	}

}
