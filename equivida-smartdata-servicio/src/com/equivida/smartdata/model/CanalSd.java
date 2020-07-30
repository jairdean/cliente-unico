/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "CANAL")
public class CanalSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_CANAL")
	private Short secCanal;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "CANAL")
	private String canal;

	public CanalSd() {
	}

	public CanalSd(Short secCanal) {
		this.secCanal = secCanal;
	}

	public CanalSd(Short secCanal, String canal) {
		this.secCanal = secCanal;
		this.canal = canal;
	}

	public Short getSecCanal() {
		return secCanal;
	}

	public void setSecCanal(Short secCanal) {
		this.secCanal = secCanal;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secCanal != null ? secCanal.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CanalSd)) {
			return false;
		}
		CanalSd other = (CanalSd) object;
		if ((this.secCanal == null && other.secCanal != null)
				|| (this.secCanal != null && !this.secCanal.equals(other.secCanal))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.Canal[ secCanal=" + secCanal + " ]";
	}

}
