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

import com.equivida.constant.Constantes;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_DIRECCION")
@NamedQueries({
		@NamedQuery(name = "TipoDireccion.findAll", query = "SELECT t FROM TipoDireccion t"),
		@NamedQuery(name = "TipoDireccion.findByCodTipoDireccion", query = "SELECT t FROM TipoDireccion t WHERE t.codTipoDireccion = :codTipoDireccion"),
		@NamedQuery(name = "TipoDireccion.findByTipoDireccion", query = "SELECT t FROM TipoDireccion t WHERE t.tipoDireccion = :tipoDireccion") })
public class TipoDireccion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 975056617961413140L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_TIPO_DIRECCION")
	private Short codTipoDireccion;
	@Basic(optional = false)
	@Column(name = "TIPO_DIRECCION")
	private String tipoDireccion;

	public TipoDireccion() {
	}

	public boolean isTrabajo() {
		if (this.codTipoDireccion.shortValue() == Constantes.TIPO_DIRECCION_ID_TRABAJO) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDomicilio() {
		if (this.codTipoDireccion.shortValue() == Constantes.TIPO_DIRECCION_ID_DOMICILIO) {
			return true;
		} else {
			return false;
		}
	}

	public TipoDireccion(Short codTipoDireccion) {
		this.codTipoDireccion = codTipoDireccion;
	}

	public TipoDireccion(Short codTipoDireccion, String tipoDireccion) {
		this.codTipoDireccion = codTipoDireccion;
		this.tipoDireccion = tipoDireccion;
	}

	public Short getCodTipoDireccion() {
		return codTipoDireccion;
	}

	public void setCodTipoDireccion(Short codTipoDireccion) {
		this.codTipoDireccion = codTipoDireccion;
	}

	public String getTipoDireccion() {
		return tipoDireccion;
	}

	public void setTipoDireccion(String tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codTipoDireccion != null ? codTipoDireccion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoDireccion)) {
			return false;
		}
		TipoDireccion other = (TipoDireccion) object;
		if ((this.codTipoDireccion == null && other.codTipoDireccion != null)
				|| (this.codTipoDireccion != null && !this.codTipoDireccion
						.equals(other.codTipoDireccion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.TipoDireccion[codTipoDireccion="
				+ codTipoDireccion + "]";
	}
}