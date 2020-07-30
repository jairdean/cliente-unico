/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PERFIL_FINANCIERO_JURIDICA")
@NamedQueries({
		@NamedQuery(name = "PerfilFinancieroJuridica.findAll", query = "SELECT p FROM PerfilFinancieroJuridica p"),
		@NamedQuery(name = "PerfilFinancieroJuridica.findBySecPerfilFinanciero", query = "SELECT p FROM PerfilFinancieroJuridica p WHERE p.secPerfilFinanciero = :secPerfilFinanciero"),
		@NamedQuery(name = "PerfilFinancieroJuridica.findByActivos", query = "SELECT p FROM PerfilFinancieroJuridica p WHERE p.activos = :activos"),
		@NamedQuery(name = "PerfilFinancieroJuridica.findByPasivos", query = "SELECT p FROM PerfilFinancieroJuridica p WHERE p.pasivos = :pasivos"),
		@NamedQuery(name = "PerfilFinancieroJuridica.findByPatrimonio", query = "SELECT p FROM PerfilFinancieroJuridica p WHERE p.patrimonio = :patrimonio"),
		@NamedQuery(name = "PerfilFinancieroJuridica.findByIngresos", query = "SELECT p FROM PerfilFinancieroJuridica p WHERE p.ingresos = :ingresos"),
		@NamedQuery(name = "PerfilFinancieroJuridica.findByEgresos", query = "SELECT p FROM PerfilFinancieroJuridica p WHERE p.egresos = :egresos"),
		@NamedQuery(name = "PerfilFinancieroJuridica.findByUtilidad", query = "SELECT p FROM PerfilFinancieroJuridica p WHERE p.utilidad = :utilidad") })
public class PerfilFinancieroJuridica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 537578464600773183L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PERFIL_FINANCIERO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secPerfilFinanciero;
	@Basic(optional = false)
	@Column(name = "ACTIVOS", scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal activos;
	@Basic(optional = false)
	@Column(name = "PASIVOS", scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal pasivos;
	@Basic(optional = false)
	@Column(name = "PATRIMONIO", scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal patrimonio;
	@Basic(optional = false)
	@Column(name = "INGRESOS", scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal ingresos;
	@Basic(optional = false)
	@Column(name = "EGRESOS", scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal egresos;
	@Basic(optional = false)
	@Column(name = "UTILIDAD", scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal utilidad;
	@Column(name = "FCH_CORTE")
	@Temporal(TemporalType.DATE)
	private Date fchCorte;

	@JoinColumn(name = "SEC_PERSONA_JURIDICA", referencedColumnName = "SEC_PERSONA_JURIDICA")
	@OneToOne(optional = false)
	private PersonaJuridica personaJuridica;

	public PerfilFinancieroJuridica() {
	}

	public PerfilFinancieroJuridica(Integer secPerfilFinanciero) {
		this.secPerfilFinanciero = secPerfilFinanciero;
	}

	public PerfilFinancieroJuridica(Integer secPerfilFinanciero, BigDecimal activos, BigDecimal pasivos,
			BigDecimal patrimonio, BigDecimal ingresos, BigDecimal egresos, BigDecimal utilidad) {
		this.secPerfilFinanciero = secPerfilFinanciero;
		this.activos = activos;
		this.pasivos = pasivos;
		this.patrimonio = patrimonio;
		this.ingresos = ingresos;
		this.egresos = egresos;
		this.utilidad = utilidad;
	}

	public Integer getSecPerfilFinanciero() {
		return secPerfilFinanciero;
	}

	public void setSecPerfilFinanciero(Integer secPerfilFinanciero) {
		this.secPerfilFinanciero = secPerfilFinanciero;
	}

	public BigDecimal getActivos() {
		return activos;
	}

	public void setActivos(BigDecimal activos) {
		this.activos = activos;
	}

	public BigDecimal getPasivos() {
		return pasivos;
	}

	public void setPasivos(BigDecimal pasivos) {
		this.pasivos = pasivos;
	}

	public BigDecimal getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(BigDecimal patrimonio) {
		this.patrimonio = patrimonio;
	}

	public BigDecimal getIngresos() {
		return ingresos;
	}

	public void setIngresos(BigDecimal ingresos) {
		this.ingresos = ingresos;
	}

	public BigDecimal getEgresos() {
		return egresos;
	}

	public void setEgresos(BigDecimal egresos) {
		this.egresos = egresos;
	}

	public BigDecimal getUtilidad() {
		return utilidad;
	}

	public void setUtilidad(BigDecimal utilidad) {
		this.utilidad = utilidad;
	}

	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	/**
	 * @return the fchCorte
	 */
	public Date getFchCorte() {
		return fchCorte;
	}

	/**
	 * @param fchCorte the fchCorte to set
	 */
	public void setFchCorte(Date fchCorte) {
		this.fchCorte = fchCorte;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secPerfilFinanciero != null ? secPerfilFinanciero.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PerfilFinancieroJuridica)) {
			return false;
		}
		PerfilFinancieroJuridica other = (PerfilFinancieroJuridica) object;
		if ((this.secPerfilFinanciero == null && other.secPerfilFinanciero != null)
				|| (this.secPerfilFinanciero != null && !this.secPerfilFinanciero.equals(other.secPerfilFinanciero))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.PerfilFinancieroJuridica[secPerfilFinanciero=" + secPerfilFinanciero + "]";
	}

}
