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

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "INGRESO_ANUAL")
@NamedQueries({
		@NamedQuery(name = "IngresoAnual.findAll", query = "SELECT i FROM IngresoAnual i"),
		@NamedQuery(name = "IngresoAnual.findBySecIngresoAnual", query = "SELECT i FROM IngresoAnual i WHERE i.secIngresoAnual = :secIngresoAnual"),
		@NamedQuery(name = "IngresoAnual.findByAnio", query = "SELECT i FROM IngresoAnual i WHERE i.anio = :anio"),
		@NamedQuery(name = "IngresoAnual.findByMntIngresoAnual", query = "SELECT i FROM IngresoAnual i WHERE i.mntIngresoAnual = :mntIngresoAnual") })
public class IngresoAnual implements Serializable {

	private static final long serialVersionUID = -3723700505359694737L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_INGRESO_ANUAL")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secIngresoAnual;
	@Basic(optional = false)
	@Column(name = "ANIO")
	private short anio;
	@Basic(optional = false)
	@Column(name = "MNT_INGRESO_ANUAL", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private BigDecimal mntIngresoAnual;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	private PersonaNatural personaNatural;

	public IngresoAnual() {
	}

	public IngresoAnual(Integer secIngresoAnual) {
		this.secIngresoAnual = secIngresoAnual;
	}

	public IngresoAnual(Integer secIngresoAnual, short anio,
			BigDecimal mntIngresoAnual) {
		this.secIngresoAnual = secIngresoAnual;
		this.anio = anio;
		this.mntIngresoAnual = mntIngresoAnual;
	}

	public Integer getSecIngresoAnual() {
		return secIngresoAnual;
	}

	public void setSecIngresoAnual(Integer secIngresoAnual) {
		this.secIngresoAnual = secIngresoAnual;
	}

	public short getAnio() {
		return anio;
	}

	public void setAnio(short anio) {
		this.anio = anio;
	}

	public BigDecimal getMntIngresoAnual() {
		return mntIngresoAnual;
	}

	public void setMntIngresoAnual(BigDecimal mntIngresoAnual) {
		this.mntIngresoAnual = mntIngresoAnual;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.IngresoAnual[secIngresoAnual="
				+ secIngresoAnual + "]";
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
		result = prime * result + anio;
		result = prime * result
				+ ((mntIngresoAnual == null) ? 0 : mntIngresoAnual.hashCode());
		result = prime * result
				+ ((personaNatural == null) ? 0 : personaNatural.hashCode());
		result = prime * result
				+ ((secIngresoAnual == null) ? 0 : secIngresoAnual.hashCode());
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
		IngresoAnual other = (IngresoAnual) obj;
		if (anio != other.anio) {
			return false;
		}
		if (mntIngresoAnual == null) {
			if (other.mntIngresoAnual != null) {
				return false;
			}
		} else if (!mntIngresoAnual.equals(other.mntIngresoAnual)) {
			return false;
		}
		if (personaNatural == null) {
			if (other.personaNatural != null) {
				return false;
			}
		} else if (!personaNatural.equals(other.personaNatural)) {
			return false;
		}
		if (secIngresoAnual == null) {
			if (other.secIngresoAnual != null) {
				return false;
			}
		} else if (!secIngresoAnual.equals(other.secIngresoAnual)) {
			return false;
		}
		return true;
	}

}
