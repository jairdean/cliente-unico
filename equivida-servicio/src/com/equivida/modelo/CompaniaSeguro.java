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
@Table(name = "COMPANIA_SEGURO")
public class CompaniaSeguro implements Serializable {

	private static final long serialVersionUID = -7653664423798242853L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_CIA_SEGURO")
	private Short secCiaSeguro;

	@Basic(optional = false)
	@Column(name = "CIA_SEGURO")
	private String ciaSeguro;

	public CompaniaSeguro() {
	}
	
	public CompaniaSeguro(Short secCiaSeguro) {
		super();
		this.secCiaSeguro = secCiaSeguro;
	}

	public CompaniaSeguro(Short secCiaSeguro, String ciaSeguro) {
		super();
		this.secCiaSeguro = secCiaSeguro;
		this.ciaSeguro = ciaSeguro;
	}



	public Short getSecCiaSeguro() {
		return secCiaSeguro;
	}

	public void setSecCiaSeguro(Short secCiaSeguro) {
		this.secCiaSeguro = secCiaSeguro;
	}

	public String getCiaSeguro() {
		return ciaSeguro;
	}

	public void setCiaSeguro(String ciaSeguro) {
		this.ciaSeguro = ciaSeguro;
	}
}