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
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_OTRA_OCUPACION")
public class TipoOtraOcupacion implements Serializable {

	private static final long serialVersionUID = -7476010915378780041L;

	@Id
	@Basic(optional = false)
	@Column(name = "COD_OTRA_OCUPACION")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short codOtraOcupacion;

	@Column(name = "OTRA_OCUPACION", length = 16)
	private String otraOcupacion;

	public TipoOtraOcupacion() {
	}

	public TipoOtraOcupacion(Short codOtraOcupacion) {
		this.codOtraOcupacion = codOtraOcupacion;
	}

	/**
	 * @return the codOtraOcupacion
	 */
	public Short getCodOtraOcupacion() {
		return codOtraOcupacion;
	}

	/**
	 * @param codOtraOcupacion
	 *            the codOtraOcupacion to set
	 */
	public void setCodOtraOcupacion(Short codOtraOcupacion) {
		this.codOtraOcupacion = codOtraOcupacion;
	}

	/**
	 * @return the otraOcupacion
	 */
	public String getOtraOcupacion() {
		return otraOcupacion;
	}

	/**
	 * @param otraOcupacion
	 *            the otraOcupacion to set
	 */
	public void setOtraOcupacion(String otraOcupacion) {
		this.otraOcupacion = otraOcupacion;
	}
}