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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "INGRESO_MENSUAL")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "IngresoMensual.findAll", query = "SELECT i FROM IngresoMensual i"),
		@NamedQuery(name = "IngresoMensual.findBySecIngresoPersona", query = "SELECT i FROM IngresoMensual i WHERE i.secIngresoPersona = :secIngresoPersona"),
		@NamedQuery(name = "IngresoMensual.findByMntIngresoMensual", query = "SELECT i FROM IngresoMensual i WHERE i.mntIngresoMensual = :mntIngresoMensual"),
		@NamedQuery(name = "IngresoMensual.findByMntEgresoMensual", query = "SELECT i FROM IngresoMensual i WHERE i.mntEgresoMensual = :mntEgresoMensual") })
public class IngresoMensual implements Serializable {

	private static final long serialVersionUID = -8217453422222488498L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_INGRESO_PERSONA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secIngresoPersona;
	@Basic(optional = false)
	@Column(name = "MNT_INGRESO_MENSUAL", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private BigDecimal mntIngresoMensual;
	@Basic(optional = false)
	@Column(name = "MNT_EGRESO_MENSUAL", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private BigDecimal mntEgresoMensual;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@OneToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	@Transient
	private BigDecimal temporalMontoIngreso;

	public IngresoMensual() {
	}

	public IngresoMensual(Integer secIngresoPersona) {
		this.secIngresoPersona = secIngresoPersona;
	}

	public IngresoMensual(Integer secIngresoPersona, BigDecimal mntIngresoMensual, BigDecimal mntEgresoMensual) {
		this.secIngresoPersona = secIngresoPersona;
		this.mntIngresoMensual = mntIngresoMensual;
		this.mntEgresoMensual = mntEgresoMensual;
	}

	public Integer getSecIngresoPersona() {
		return secIngresoPersona;
	}

	public void setSecIngresoPersona(Integer secIngresoPersona) {
		this.secIngresoPersona = secIngresoPersona;
	}

	public BigDecimal getMntIngresoMensual() {
		return mntIngresoMensual;
	}

	public void setMntIngresoMensual(BigDecimal mntIngresoMensual) {
		this.mntIngresoMensual = mntIngresoMensual;
	}

	public BigDecimal getMntEgresoMensual() {
		return mntEgresoMensual;
	}

	public void setMntEgresoMensual(BigDecimal mntEgresoMensual) {
		this.mntEgresoMensual = mntEgresoMensual;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mntEgresoMensual == null) ? 0 : mntEgresoMensual.hashCode());
		result = prime * result + ((mntIngresoMensual == null) ? 0 : mntIngresoMensual.hashCode());
		result = prime * result + ((personaNatural == null) ? 0 : personaNatural.hashCode());
		result = prime * result + ((secIngresoPersona == null) ? 0 : secIngresoPersona.hashCode());
		return result;
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		IngresoMensual other = (IngresoMensual) obj;
		if (mntEgresoMensual == null) {
			if (other.mntEgresoMensual != null) {
				return false;
			}
		} else if (!mntEgresoMensual.equals(other.mntEgresoMensual)) {
			return false;
		}
		if (mntIngresoMensual == null) {
			if (other.mntIngresoMensual != null) {
				return false;
			}
		} else if (!mntIngresoMensual.equals(other.mntIngresoMensual)) {
			return false;
		}
		if (personaNatural == null) {
			if (other.personaNatural != null) {
				return false;
			}
		} else if (!personaNatural.equals(other.personaNatural)) {
			return false;
		}
		if (secIngresoPersona == null) {
			if (other.secIngresoPersona != null) {
				return false;
			}
		} else if (!secIngresoPersona.equals(other.secIngresoPersona)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the temporalMontoIngreso
	 */
	public BigDecimal getTemporalMontoIngreso() {
		return temporalMontoIngreso;
	}

	/**
	 * @param temporalMontoIngreso the temporalMontoIngreso to set
	 */
	public void setTemporalMontoIngreso(BigDecimal temporalMontoIngreso) {
		this.temporalMontoIngreso = temporalMontoIngreso;
	}

	@Override
	public String toString() {
		return "IngresoMensual [secIngresoPersona=" + secIngresoPersona + ", mntIngresoMensual=" + mntIngresoMensual
				+ ", mntEgresoMensual=" + mntEgresoMensual + ", personaNatural="
				+ ((personaNatural != null) ? personaNatural.getSecPersonaNatural() : "") + "]";
	}

}
