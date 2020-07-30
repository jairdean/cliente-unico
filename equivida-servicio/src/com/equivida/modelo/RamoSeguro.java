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
@Table(name = "RAMO_SEGURO")
public class RamoSeguro implements Serializable {

	private static final long serialVersionUID = -3062408148398705110L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_TIPO_RAMO")
	private Short secTipoRamo;

	@Basic(optional = false)
	@Column(name = "TIPO_RAMO", length = 32)
	private String tipoRamo;

	public RamoSeguro() {
	}

	public RamoSeguro(Short secTipoRamo) {
		super();
		this.secTipoRamo = secTipoRamo;
	}

	public Short getSecTipoRamo() {
		return secTipoRamo;
	}

	public void setSecTipoRamo(Short secTipoRamo) {
		this.secTipoRamo = secTipoRamo;
	}

	public String getTipoRamo() {
		return tipoRamo;
	}

	public void setTipoRamo(String tipoRamo) {
		this.tipoRamo = tipoRamo;
	}
}