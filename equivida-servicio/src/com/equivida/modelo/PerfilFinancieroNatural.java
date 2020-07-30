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
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PERFIL_FINANCIERO_NATURAL")
public class PerfilFinancieroNatural implements Serializable {

	private static final long serialVersionUID = 9096243283890710884L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PERFIL_FINANCIERO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secPerfilFinanciero;

	@Column(name = "MNT_ACTIVOS", precision = 12, scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal mntActivos;

	@Column(name = "MNT_PASIVOS", precision = 12, scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal mntPasivos;

	@Column(name = "SEC_PERSONA_NATURAL")
	private Integer secPersonaNatural;

	public PerfilFinancieroNatural() {
	}

	public PerfilFinancieroNatural(Integer secPerfilFinanciero) {
		this.secPerfilFinanciero = secPerfilFinanciero;
	}

	/**
	 * @return the secPerfilFinanciero
	 */
	public Integer getSecPerfilFinanciero() {
		return secPerfilFinanciero;
	}

	/**
	 * @param secPerfilFinanciero
	 *            the secPerfilFinanciero to set
	 */
	public void setSecPerfilFinanciero(Integer secPerfilFinanciero) {
		this.secPerfilFinanciero = secPerfilFinanciero;
	}

	/**
	 * @return the mntActivos
	 */
	public BigDecimal getMntActivos() {
		return mntActivos;
	}

	/**
	 * @param mntActivos
	 *            the mntActivos to set
	 */
	public void setMntActivos(BigDecimal mntActivos) {
		this.mntActivos = mntActivos;
	}

	/**
	 * @return the mntPasivos
	 */
	public BigDecimal getMntPasivos() {
		return mntPasivos;
	}

	/**
	 * @param mntPasivos
	 *            the mntPasivos to set
	 */
	public void setMntPasivos(BigDecimal mntPasivos) {
		this.mntPasivos = mntPasivos;
	}

	/**
	 * @return the secPersonaNatural
	 */
	public Integer getSecPersonaNatural() {
		return secPersonaNatural;
	}

	/**
	 * @param secPersonaNatural
	 *            the secPersonaNatural to set
	 */
	public void setSecPersonaNatural(Integer secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
	}

}