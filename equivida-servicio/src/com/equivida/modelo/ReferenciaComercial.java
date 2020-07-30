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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "REFERENCIA_COMERCIAL")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferenciaComercial implements Serializable {

	private static final long serialVersionUID = 6129658850612679124L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_REFERENCIA_COMERCIAL")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secReferenciaComercial;

	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	@Column(name = "ENTIDAD_REFERENCIA", length = 64)
	private String entidadReferencia;

	@Column(name = "MNT_REFERENCIA", precision = 12, scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal mntReferencia;

	@Column(name = "TLF_ENTIDAD", length = 16)
	@XmlTransient
	private String tlfEntidad;

	/**
	 * @return the secReferenciaComercial
	 */
	public Integer getSecReferenciaComercial() {
		return secReferenciaComercial;
	}

	/**
	 * @param secReferenciaComercial
	 *            the secReferenciaComercial to set
	 */
	public void setSecReferenciaComercial(Integer secReferenciaComercial) {
		this.secReferenciaComercial = secReferenciaComercial;
	}

	/**
	 * @return the personaNatural
	 */
	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	/**
	 * @param personaNatural
	 *            the personaNatural to set
	 */
	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	/**
	 * @return the entidadReferencia
	 */
	public String getEntidadReferencia() {
		return entidadReferencia;
	}

	/**
	 * @param entidadReferencia
	 *            the entidadReferencia to set
	 */
	public void setEntidadReferencia(String entidadReferencia) {
		this.entidadReferencia = entidadReferencia;
	}

	/**
	 * @return the mntReferencia
	 */
	public BigDecimal getMntReferencia() {
		return mntReferencia;
	}

	/**
	 * @param mntReferencia
	 *            the mntReferencia to set
	 */
	public void setMntReferencia(BigDecimal mntReferencia) {
		this.mntReferencia = mntReferencia;
	}

	/**
	 * @return the tlfEntidad
	 */
	public String getTlfEntidad() {
		return tlfEntidad;
	}

	/**
	 * @param tlfEntidad
	 *            the tlfEntidad to set
	 */
	public void setTlfEntidad(String tlfEntidad) {
		this.tlfEntidad = tlfEntidad;
	}

}