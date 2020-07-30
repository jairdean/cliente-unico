/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "INSTITUCION_FINANCIERA")
public class InstitucionFinanciera implements Serializable {

	private static final long serialVersionUID = -8647190243122535672L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_INSTITUCION_FINANCIERA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secInstitucionFinanciera;

	@Column(name = "INSTITUCION_FINANCIERA", length = 128)
	private String institucionFinanciera;

	@JoinColumn(name = "SEC_TIPO_FINANCIERA", referencedColumnName = "SEC_TIPO_FINANCIERA")
	@ManyToOne(optional = false)
	private TipoInstitucionFinanciera tipoInstitucionFinanciera;

	public InstitucionFinanciera() {
	}

	public InstitucionFinanciera(Short secInstitucionFinanciera,
			String institucionFinanciera,
			TipoInstitucionFinanciera tipoInstitucionFinanciera) {
		this.secInstitucionFinanciera = secInstitucionFinanciera;
		this.institucionFinanciera = institucionFinanciera;
		this.tipoInstitucionFinanciera = tipoInstitucionFinanciera;
	}

	public InstitucionFinanciera(
			TipoInstitucionFinanciera tipoInstitucionFinanciera) {
		this.tipoInstitucionFinanciera = tipoInstitucionFinanciera;
	}

	

	/**
	 * @return the tipoInstitucionFinanciera
	 */
	public TipoInstitucionFinanciera getTipoInstitucionFinanciera() {
		return tipoInstitucionFinanciera;
	}

	/**
	 * @param tipoInstitucionFinanciera
	 *            the tipoInstitucionFinanciera to set
	 */
	public void setTipoInstitucionFinanciera(
			TipoInstitucionFinanciera tipoInstitucionFinanciera) {
		this.tipoInstitucionFinanciera = tipoInstitucionFinanciera;
	}

	/**
	 * @return the secInstitucionFinanciera
	 */
	public Short getSecInstitucionFinanciera() {
		return secInstitucionFinanciera;
	}

	/**
	 * @param secInstitucionFinanciera
	 *            the secInstitucionFinanciera to set
	 */
	public void setSecInstitucionFinanciera(Short secInstitucionFinanciera) {
		this.secInstitucionFinanciera = secInstitucionFinanciera;
	}

	/**
	 * @return the institucionFinanciera
	 */
	public String getInstitucionFinanciera() {
		return institucionFinanciera;
	}

	/**
	 * @param institucionFinanciera
	 *            the institucionFinanciera to set
	 */
	public void setInstitucionFinanciera(String institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}
}