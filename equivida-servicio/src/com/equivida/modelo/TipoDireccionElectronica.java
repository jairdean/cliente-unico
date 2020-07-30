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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.equivida.constant.Constantes;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_DIRECCION_ELECTRONICA")
@NamedQueries({
		@NamedQuery(name = "TipoDireccionElectronica.findAll", query = "SELECT t FROM TipoDireccionElectronica t"),
		@NamedQuery(name = "TipoDireccionElectronica.findByCodTipoDireccionElectronica", query = "SELECT t FROM TipoDireccionElectronica t WHERE t.codTipoDireccionElectronica = :codTipoDireccionElectronica"),
		@NamedQuery(name = "TipoDireccionElectronica.findByTipoDireccionElectronica", query = "SELECT t FROM TipoDireccionElectronica t WHERE t.tipoDireccionElectronica = :tipoDireccionElectronica") })
public class TipoDireccionElectronica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1500967897201002286L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_TIPO_DIRECCION_ELECTRONICA")
	private Short codTipoDireccionElectronica;
	@Basic(optional = false)
	@Column(name = "TIPO_DIRECCION_ELECTRONICA")
	private String tipoDireccionElectronica;

	public TipoDireccionElectronica() {
	}

	@Transient
	public boolean isPersonal() {
		if (this.getCodTipoDireccionElectronica().shortValue() == Constantes.TIPO_DIRECCION_ELECTRONICA_ID_PERSONAL) {
			return true;
		} else {
			return false;
		}
	}

	@Transient
	public boolean isTrabajo() {
		if (this.getCodTipoDireccionElectronica().shortValue() == Constantes.TIPO_DIRECCION_ELECTRONICA_ID_TRABAJO) {
			return true;
		} else {
			return false;
		}
	}

	public TipoDireccionElectronica(Short codTipoDireccionElectronica) {
		this.codTipoDireccionElectronica = codTipoDireccionElectronica;
	}

	public TipoDireccionElectronica(Short codTipoDireccionElectronica,
			String tipoDireccionElectronica) {
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
		hash += (codTipoDireccionElectronica != null ? codTipoDireccionElectronica
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoDireccionElectronica)) {
			return false;
		}
		TipoDireccionElectronica other = (TipoDireccionElectronica) object;
		if ((this.codTipoDireccionElectronica == null && other.codTipoDireccionElectronica != null)
				|| (this.codTipoDireccionElectronica != null && !this.codTipoDireccionElectronica
						.equals(other.codTipoDireccionElectronica))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.TipoDireccionElectronica[codTipoDireccionElectronica="
				+ codTipoDireccionElectronica + "]";
	}

}
