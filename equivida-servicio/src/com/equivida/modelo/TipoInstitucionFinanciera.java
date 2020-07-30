/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_FINANCIERA")
public class TipoInstitucionFinanciera implements Serializable {

	private static final long serialVersionUID = 219870328621339179L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_TIPO_FINANCIERA")
	private Short secTipoFinanciera;

	@Column(name = "TIPO_FINANCIERA", length = 64)
	private String tipoFinanciera;

	@Column(name = "CODIGO_VISIBLE")
	private char codigoVisible;
	
	public TipoInstitucionFinanciera() {
	}

	public TipoInstitucionFinanciera(Short secTipoFinanciera) {
		this.secTipoFinanciera = secTipoFinanciera;
	}

	public TipoInstitucionFinanciera(Short secTipoFinanciera,
			String tipoFinanciera) {
		this.secTipoFinanciera = secTipoFinanciera;
		this.tipoFinanciera = tipoFinanciera;
	}

	/**
	 * @return the secTipoFinanciera
	 */
	public Short getSecTipoFinanciera() {
		return secTipoFinanciera;
	}

	/**
	 * @param secTipoFinanciera
	 *            the secTipoFinanciera to set
	 */
	public void setSecTipoFinanciera(Short secTipoFinanciera) {
		this.secTipoFinanciera = secTipoFinanciera;
	}

	/**
	 * @return the tipoFinanciera
	 */
	public String getTipoFinanciera() {
		return tipoFinanciera;
	}

	/**
	 * @param tipoFinanciera
	 *            the tipoFinanciera to set
	 */
	public void setTipoFinanciera(String tipoFinanciera) {
		this.tipoFinanciera = tipoFinanciera;
	}

	/**
	 * @return the codigoVisible
	 */
	public char getCodigoVisible() {
		return codigoVisible;
	}

	/**
	 * @param codigoVisible
	 *            the codigoVisible to set
	 */
	public void setCodigoVisible(char codigoVisible) {
		this.codigoVisible = codigoVisible;
	}
}