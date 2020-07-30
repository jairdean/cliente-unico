/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

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

import com.equivida.constant.EstadoEnum;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "SEGURO_VIGENTE")
@NamedQueries({
		@NamedQuery(name = "SeguroVigente.findAll", query = "SELECT s FROM SeguroVigente s"),
		@NamedQuery(name = "SeguroVigente.findBySecSeguro", query = "SELECT s FROM SeguroVigente s WHERE s.secSeguro = :secSeguro"),
		@NamedQuery(name = "SeguroVigente.findByValorSeguro", query = "SELECT s FROM SeguroVigente s WHERE s.valorSeguro = :valorSeguro") })
public class SeguroVigente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5921940728226109400L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_SEGURO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secSeguro;
	// @Basic(optional = false)
	// @Column(name = "PLAN_SEGURO")
	// private String planSeguro; //antes xistia esta columna
	@Basic(optional = false)
	@Column(name = "VALOR_SEGURO", precision = 12, scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal valorSeguro;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	private PersonaNatural personaNatural;// la persona natural

	@JoinColumn(name = "SEC_CIA_SEGURO", referencedColumnName = "SEC_CIA_SEGURO")
	@ManyToOne(optional = false)
	private CompaniaSeguro companiaSeguro;// la persona natural

	// @JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	// @ManyToOne(optional = false)
	// private Persona persona;// la compania

	@JoinColumn(name = "SEC_TIPO_RAMO", referencedColumnName = "SEC_TIPO_RAMO")
	@ManyToOne(optional = false)
	private RamoSeguro ramoSeguro;

	@Column(name = "ESTADO")
	private char estado;// para historico, es decir si alguna vez en la bdd
						// estaba activo A y cambia, entonces que se quede en
						// estado I de inactivos

	@Transient
	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo();
	}

	public SeguroVigente() {
	}

	public SeguroVigente(Integer secSeguro) {
		this.secSeguro = secSeguro;
	}

	public Integer getSecSeguro() {
		return secSeguro;
	}

	public void setSecSeguro(Integer secSeguro) {
		this.secSeguro = secSeguro;
	}

	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.SeguroVigente[secSeguro=" + secSeguro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((secSeguro == null) ? 0 : secSeguro.hashCode());
		result = prime * result
				+ ((valorSeguro == null) ? 0 : valorSeguro.hashCode());
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
		SeguroVigente other = (SeguroVigente) obj;
		if (secSeguro == null) {
			if (other.secSeguro != null)
				return false;
		} else if (!secSeguro.equals(other.secSeguro))
			return false;
		if (valorSeguro == null) {
			if (other.valorSeguro != null)
				return false;
		} else if (!valorSeguro.equals(other.valorSeguro))
			return false;
		return true;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public RamoSeguro getRamoSeguro() {
		return ramoSeguro;
	}

	public void setRamoSeguro(RamoSeguro ramoSeguro) {
		this.ramoSeguro = ramoSeguro;
	}

	public CompaniaSeguro getCompaniaSeguro() {
		return companiaSeguro;
	}

	public void setCompaniaSeguro(CompaniaSeguro companiaSeguro) {
		this.companiaSeguro = companiaSeguro;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

}
