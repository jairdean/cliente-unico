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
@Table(name = "TIPO_DIRECCION_ELECTRONICA")
public class TipoDireccionElectronicaSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "COD_TIPO_DIRECCION_ELECTRONICA")
	private Short codTipoDireccionElectronica;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "TIPO_DIRECCION_ELECTRONICA")
	private String tipoDireccionElectronica;

	public TipoDireccionElectronicaSd() {
	}

	public TipoDireccionElectronicaSd(Short codTipoDireccionElectronica) {
		this.codTipoDireccionElectronica = codTipoDireccionElectronica;
	}

	public TipoDireccionElectronicaSd(Short codTipoDireccionElectronica, String tipoDireccionElectronica) {
		this.codTipoDireccionElectronica = codTipoDireccionElectronica;
		this.tipoDireccionElectronica = tipoDireccionElectronica;
	}

	public Short getCodTipoDireccionElectronica() {
		return codTipoDireccionElectronica;
	}

	public void setCodTipoDireccionElectronica(Short codTipoDireccionElectronica) {
		this.codTipoDireccionElectronica = codTipoDireccionElectronica;
	}

	public String getTipoDireccionElectronica() {
		return tipoDireccionElectronica;
	}

	public void setTipoDireccionElectronica(String tipoDireccionElectronica) {
		this.tipoDireccionElectronica = tipoDireccionElectronica;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codTipoDireccionElectronica != null ? codTipoDireccionElectronica.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoDireccionElectronicaSd)) {
			return false;
		}
		TipoDireccionElectronicaSd other = (TipoDireccionElectronicaSd) object;
		if ((this.codTipoDireccionElectronica == null && other.codTipoDireccionElectronica != null)
				|| (this.codTipoDireccionElectronica != null
						&& !this.codTipoDireccionElectronica.equals(other.codTipoDireccionElectronica))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.TipoDireccionElectronica[ codTipoDireccionElectronica="
				+ codTipoDireccionElectronica + " ]";
	}

}
