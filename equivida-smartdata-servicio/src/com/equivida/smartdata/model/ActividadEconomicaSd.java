/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "ACTIVIDAD_ECONOMICA")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActividadEconomicaSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "COD_ACTIVIDAD_ECONOMICA")
	private Short codActividadEconomica;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 9)
	@Column(name = "COD_ACT_ECONOMICA_SRI")
	@XmlTransient
	private String codActEconomicaSri;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 720)
	@Column(name = "ACTIVIDAD_ECONOMICA_SRI")
	private String actividadEconomicaSri;
	@Column(name = "ULTIMO_NODO_SRI")
	@XmlTransient
	private Character ultimoNodoSri;
	@Size(max = 9)
	@Column(name = "CODIGO_PADRE_SRI")
	@XmlTransient
	private String codigoPadreSri;
	@Column(name = "NIVEL_SRI")
	@XmlTransient
	private Short nivelSri;
	@OneToMany(mappedBy = "codActividadEconomica", fetch = FetchType.LAZY)
	@XmlTransient
	private List<PersonaJuridicaSd> personaJuridicaList;

	public ActividadEconomicaSd() {
	}

	public ActividadEconomicaSd(Short codActividadEconomica) {
		this.codActividadEconomica = codActividadEconomica;
	}

	public ActividadEconomicaSd(Short codActividadEconomica,
			String codActEconomicaSri, String actividadEconomicaSri) {
		this.codActividadEconomica = codActividadEconomica;
		this.codActEconomicaSri = codActEconomicaSri;
		this.actividadEconomicaSri = actividadEconomicaSri;
	}

	public Short getCodActividadEconomica() {
		return codActividadEconomica;
	}

	public void setCodActividadEconomica(Short codActividadEconomica) {
		this.codActividadEconomica = codActividadEconomica;
	}

	public String getCodActEconomicaSri() {
		return codActEconomicaSri;
	}

	public void setCodActEconomicaSri(String codActEconomicaSri) {
		this.codActEconomicaSri = codActEconomicaSri;
	}

	public String getActividadEconomicaSri() {
		return actividadEconomicaSri;
	}

	public void setActividadEconomicaSri(String actividadEconomicaSri) {
		this.actividadEconomicaSri = actividadEconomicaSri;
	}

	public Character getUltimoNodoSri() {
		return ultimoNodoSri;
	}

	public void setUltimoNodoSri(Character ultimoNodoSri) {
		this.ultimoNodoSri = ultimoNodoSri;
	}

	public String getCodigoPadreSri() {
		return codigoPadreSri;
	}

	public void setCodigoPadreSri(String codigoPadreSri) {
		this.codigoPadreSri = codigoPadreSri;
	}

	public Short getNivelSri() {
		return nivelSri;
	}

	public void setNivelSri(Short nivelSri) {
		this.nivelSri = nivelSri;
	}

	public List<PersonaJuridicaSd> getPersonaJuridicaList() {
		return personaJuridicaList;
	}

	public void setPersonaJuridicaList(
			List<PersonaJuridicaSd> personaJuridicaList) {
		this.personaJuridicaList = personaJuridicaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codActividadEconomica != null ? codActividadEconomica
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ActividadEconomicaSd)) {
			return false;
		}
		ActividadEconomicaSd other = (ActividadEconomicaSd) object;
		if ((this.codActividadEconomica == null && other.codActividadEconomica != null)
				|| (this.codActividadEconomica != null && !this.codActividadEconomica
						.equals(other.codActividadEconomica))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.ActividadEconomica[ codActividadEconomica="
				+ codActividadEconomica + " ]";
	}

}
