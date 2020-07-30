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

import com.equivida.util.StringUtil;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "INGRESO_MENSUAL_ADICIONAL")
@NamedQueries({
		@NamedQuery(name = "IngresoMensualAdicional.findAll", query = "SELECT i FROM IngresoMensualAdicional i"),
		@NamedQuery(name = "IngresoMensualAdicional.findBySecIngresoAdicional", query = "SELECT i FROM IngresoMensualAdicional i WHERE i.secIngresoAdicional = :secIngresoAdicional"),
		@NamedQuery(name = "IngresoMensualAdicional.findByMntIngresoAdicional", query = "SELECT i FROM IngresoMensualAdicional i WHERE i.mntIngresoAdicional = :mntIngresoAdicional"),
		@NamedQuery(name = "IngresoMensualAdicional.findByMntEgresoAdicional", query = "SELECT i FROM IngresoMensualAdicional i WHERE i.mntEgresoAdicional = :mntEgresoAdicional"),
		@NamedQuery(name = "IngresoMensualAdicional.findByFuenteIngresoAdicional", query = "SELECT i FROM IngresoMensualAdicional i WHERE i.fuenteIngresoAdicional = :fuenteIngresoAdicional") })
public class IngresoMensualAdicional implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6110926851218912064L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_INGRESO_ADICIONAL", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secIngresoAdicional;
	@Basic(optional = false)
	@Column(name = "MNT_INGRESO_ADICIONAL", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private BigDecimal mntIngresoAdicional;
	@Basic(optional = false)
	@Column(name = "MNT_EGRESO_ADICIONAL", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private BigDecimal mntEgresoAdicional;
	@Basic(optional = false)
	@Column(name = "FUENTE_INGRESO_ADICIONAL", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private String fuenteIngresoAdicional;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	private PersonaNatural personaNatural;

	public IngresoMensualAdicional() {
	}

	public IngresoMensualAdicional(Integer secIngresoAdicional) {
		this.secIngresoAdicional = secIngresoAdicional;
	}

	public IngresoMensualAdicional(Integer secIngresoAdicional,
			BigDecimal mntIngresoAdicional, BigDecimal mntEgresoAdicional,
			String fuenteIngresoAdicional) {
		this.secIngresoAdicional = secIngresoAdicional;
		this.mntIngresoAdicional = mntIngresoAdicional;
		this.mntEgresoAdicional = mntEgresoAdicional;
		this.fuenteIngresoAdicional = fuenteIngresoAdicional;
	}

	public Integer getSecIngresoAdicional() {
		return secIngresoAdicional;
	}

	public void setSecIngresoAdicional(Integer secIngresoAdicional) {
		this.secIngresoAdicional = secIngresoAdicional;
	}

	public BigDecimal getMntIngresoAdicional() {
		return mntIngresoAdicional;
	}

	public void setMntIngresoAdicional(BigDecimal mntIngresoAdicional) {
		this.mntIngresoAdicional = mntIngresoAdicional;
	}

	public BigDecimal getMntEgresoAdicional() {
		return mntEgresoAdicional;
	}

	public void setMntEgresoAdicional(BigDecimal mntEgresoAdicional) {
		this.mntEgresoAdicional = mntEgresoAdicional;
	}

	public String getFuenteIngresoAdicional() {
		return fuenteIngresoAdicional;
	}

	public void setFuenteIngresoAdicional(String fuenteIngresoAdicional) {
		if (fuenteIngresoAdicional != null) {
			this.fuenteIngresoAdicional = StringUtil.toUpper(
					fuenteIngresoAdicional).trim();
		}
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.IngresoMensualAdicional[secIngresoAdicional="
				+ secIngresoAdicional + "]";
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
		result = prime
				* result
				+ ((fuenteIngresoAdicional == null) ? 0
						: fuenteIngresoAdicional.hashCode());
		result = prime
				* result
				+ ((mntEgresoAdicional == null) ? 0 : mntEgresoAdicional
						.hashCode());
		result = prime
				* result
				+ ((mntIngresoAdicional == null) ? 0 : mntIngresoAdicional
						.hashCode());
		result = prime * result
				+ ((personaNatural == null) ? 0 : personaNatural.hashCode());
		result = prime
				* result
				+ ((secIngresoAdicional == null) ? 0 : secIngresoAdicional
						.hashCode());
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
		IngresoMensualAdicional other = (IngresoMensualAdicional) obj;
		if (fuenteIngresoAdicional == null) {
			if (other.fuenteIngresoAdicional != null) {
				return false;
			}
		} else if (!fuenteIngresoAdicional.equals(other.fuenteIngresoAdicional)) {
			return false;
		}
		if (mntEgresoAdicional == null) {
			if (other.mntEgresoAdicional != null) {
				return false;
			}
		} else if (!mntEgresoAdicional.equals(other.mntEgresoAdicional)) {
			return false;
		}
		if (mntIngresoAdicional == null) {
			if (other.mntIngresoAdicional != null) {
				return false;
			}
		} else if (!mntIngresoAdicional.equals(other.mntIngresoAdicional)) {
			return false;
		}
		if (personaNatural == null) {
			if (other.personaNatural != null) {
				return false;
			}
		} else if (!personaNatural.equals(other.personaNatural)) {
			return false;
		}
		if (secIngresoAdicional == null) {
			if (other.secIngresoAdicional != null) {
				return false;
			}
		} else if (!secIngresoAdicional.equals(other.secIngresoAdicional)) {
			return false;
		}
		return true;
	}

}
