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
@Table(name = "EMPLEO_DEPENDIENTE")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "EmpleoDependiente.findAll", query = "SELECT e FROM EmpleoDependiente e"),
		@NamedQuery(name = "EmpleoDependiente.findBySecEmpleoDependiente", query = "SELECT e FROM EmpleoDependiente e WHERE e.secEmpleoDependiente = :secEmpleoDependiente"),
		@NamedQuery(name = "EmpleoDependiente.findByTiempoEmpresa", query = "SELECT e FROM EmpleoDependiente e WHERE e.tiempoEmpresa = :tiempoEmpresa"),
		@NamedQuery(name = "EmpleoDependiente.findByEstado", query = "SELECT e FROM EmpleoDependiente e WHERE e.estado = :estado"),
		@NamedQuery(name = "EmpleoDependiente.findByCargo", query = "SELECT e FROM EmpleoDependiente e WHERE e.cargo = :cargo") })
public class EmpleoDependiente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 213179270762498778L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_EMPLEO_DEPENDIENTE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secEmpleoDependiente;
	@Basic(optional = false)
	@Column(name = "TIEMPO_EMPRESA", precision = 4, scale = 2, columnDefinition = "decimal(4,2)")
	private BigDecimal tiempoEmpresa;
	@Basic(optional = false)
	@Column(name = "ESTADO")
	@XmlTransient
	private char estado;// A activo o I inactivo
	@Basic(optional = false)
	@Column(name = "CARGO")
	private String cargo;

	@Basic(optional = false)
	@Column(name = "COD_TIEMPO")
	private char codTiempo;// A=anios, M=meses

	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	@Column(name = "NEGOCIO_EMPRESA", length = 96)
	private String negocioEmpresa;

	@JoinColumn(name = "COD_ACTIVIDAD_ECONOMICA", referencedColumnName = "COD_ACTIVIDAD_ECONOMICA")
	@ManyToOne(optional = false)
	private ActividadEconomica actividadEconomica;

	@Transient
	@XmlTransient
	private BigDecimal salarioTr;

	// @JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	// @ManyToOne(optional = false)
	// private Persona persona;// empresa para la que trabaja

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

	public EmpleoDependiente() {
	}

	public EmpleoDependiente(Integer secEmpleoDependiente) {
		this.secEmpleoDependiente = secEmpleoDependiente;
	}

	public EmpleoDependiente(Integer secEmpleoDependiente, BigDecimal tiempoEmpresa, char estado, String cargo) {
		this.secEmpleoDependiente = secEmpleoDependiente;
		this.tiempoEmpresa = tiempoEmpresa;
		this.estado = estado;
		this.cargo = cargo;
	}

	public Integer getSecEmpleoDependiente() {
		return secEmpleoDependiente;
	}

	public void setSecEmpleoDependiente(Integer secEmpleoDependiente) {
		this.secEmpleoDependiente = secEmpleoDependiente;
	}

	public BigDecimal getTiempoEmpresa() {
		return tiempoEmpresa;
	}

	public void setTiempoEmpresa(BigDecimal tiempoEmpresa) {
		this.tiempoEmpresa = tiempoEmpresa;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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
		hash += (secEmpleoDependiente != null ? secEmpleoDependiente.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleoDependiente)) {
			return false;
		}
		EmpleoDependiente other = (EmpleoDependiente) object;
		if ((this.secEmpleoDependiente == null && other.secEmpleoDependiente != null)
				|| (this.secEmpleoDependiente != null
						&& !this.secEmpleoDependiente.equals(other.secEmpleoDependiente))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.EmpleoDependiente[secEmpleoDependiente=" + secEmpleoDependiente + "]";
	}

	public char getCodTiempo() {
		return codTiempo;
	}

	public void setCodTiempo(char codTiempo) {
		this.codTiempo = codTiempo;
	}

	public String getNegocioEmpresa() {
		return negocioEmpresa;
	}

	public void setNegocioEmpresa(String negocioEmpresa) {
		this.negocioEmpresa = negocioEmpresa;
	}

	public ActividadEconomica getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(ActividadEconomica actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	/**
	 * @return the salarioTr
	 */
	public BigDecimal getSalarioTr() {
		return salarioTr;
	}

	/**
	 * @param salarioTr the salarioTr to set
	 */
	public void setSalarioTr(BigDecimal salarioTr) {
		this.salarioTr = salarioTr;
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
