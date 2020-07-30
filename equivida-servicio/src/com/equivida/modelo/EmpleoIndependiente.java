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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.equivida.constant.EstadoEnum;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "EMPLEO_INDEPENDIENTE")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "EmpleoIndependiente.findAll", query = "SELECT e FROM EmpleoIndependiente e"),
		@NamedQuery(name = "EmpleoIndependiente.findBySecEmpleoIndependiente", query = "SELECT e FROM EmpleoIndependiente e WHERE e.secEmpleoIndependiente = :secEmpleoIndependiente"),
		@NamedQuery(name = "EmpleoIndependiente.findByEstado", query = "SELECT e FROM EmpleoIndependiente e WHERE e.estado = :estado") })
public class EmpleoIndependiente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6431415502555186732L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_EMPLEO_INDEPENDIENTE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secEmpleoIndependiente;
	@Column(name = "TIEMPO_EMPRESA", precision = 4, scale = 2, columnDefinition = "decimal(4,2)")
	private BigDecimal tiempoEmpresa;
	@Basic(optional = false)
	@Column(name = "ESTADO")
	private char estado;
	@Basic(optional = false)
	@Column(name = "CARGO")
	private String cargo;

	@Basic(optional = false)
	@Column(name = "COD_TIEMPO")
	private char codTiempo;// A=anios, M=meses

	@JoinColumn(name = "COD_ACTIVIDAD_ECONOMICA", referencedColumnName = "COD_ACTIVIDAD_ECONOMICA")
	@ManyToOne(optional = false)
	private ActividadEconomica actividadEconomica;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	@Transient
	private String nombreActividadEconomicaTr;
	@Transient
	private Short idActividadEconomicaTr;
	@Transient
	private String razonSocialTr;

	@Transient
	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo();
	}

	public EmpleoIndependiente() {
	}

	public EmpleoIndependiente(Integer secEmpleoIndependiente) {
		this.secEmpleoIndependiente = secEmpleoIndependiente;
	}

	public Integer getSecEmpleoIndependiente() {
		return secEmpleoIndependiente;
	}

	public void setSecEmpleoIndependiente(Integer secEmpleoIndependiente) {
		this.secEmpleoIndependiente = secEmpleoIndependiente;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secEmpleoIndependiente != null ? secEmpleoIndependiente.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleoIndependiente)) {
			return false;
		}
		EmpleoIndependiente other = (EmpleoIndependiente) object;
		if ((this.secEmpleoIndependiente == null && other.secEmpleoIndependiente != null)
				|| (this.secEmpleoIndependiente != null
						&& !this.secEmpleoIndependiente.equals(other.secEmpleoIndependiente))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.EmpleoIndependiente[secEmpleoIndependiente=" + secEmpleoIndependiente + "]";
	}

	/**
	 * @return the tiempoEmpresa
	 */
	public BigDecimal getTiempoEmpresa() {
		return tiempoEmpresa;
	}

	/**
	 * @param tiempoEmpresa the tiempoEmpresa to set
	 */
	public void setTiempoEmpresa(BigDecimal tiempoEmpresa) {
		this.tiempoEmpresa = tiempoEmpresa;
	}

	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public char getCodTiempo() {
		return codTiempo;
	}

	public void setCodTiempo(char codTiempo) {
		this.codTiempo = codTiempo;
	}

	public ActividadEconomica getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(ActividadEconomica actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	/**
	 * @return the nombreActividadEconomicaTr
	 */
	public String getNombreActividadEconomicaTr() {
		return nombreActividadEconomicaTr;
	}

	/**
	 * @param nombreActividadEconomicaTr the nombreActividadEconomicaTr to set
	 */
	public void setNombreActividadEconomicaTr(String nombreActividadEconomicaTr) {
		this.nombreActividadEconomicaTr = nombreActividadEconomicaTr;
	}

	/**
	 * @return the razonSocialTr
	 */
	public String getRazonSocialTr() {
		return razonSocialTr;
	}

	/**
	 * @param razonSocialTr the razonSocialTr to set
	 */
	public void setRazonSocialTr(String razonSocialTr) {
		this.razonSocialTr = razonSocialTr;
	}

	/**
	 * @return the idActividadEconomicaTr
	 */
	public Short getIdActividadEconomicaTr() {
		return idActividadEconomicaTr;
	}

	/**
	 * @param idActividadEconomicaTr the idActividadEconomicaTr to set
	 */
	public void setIdActividadEconomicaTr(Short idActividadEconomicaTr) {
		this.idActividadEconomicaTr = idActividadEconomicaTr;
	}

}
