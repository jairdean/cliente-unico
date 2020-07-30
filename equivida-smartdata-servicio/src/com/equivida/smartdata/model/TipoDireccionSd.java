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
@Table(name = "TIPO_DIRECCION")
public class TipoDireccionSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "COD_TIPO_DIRECCION")
	private Short codTipoDireccion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 16)
	@Column(name = "TIPO_DIRECCION")
	private String tipoDireccion;

	public TipoDireccionSd() {
	}

	public TipoDireccionSd(Short codTipoDireccion) {
		this.codTipoDireccion = codTipoDireccion;
	}

	public TipoDireccionSd(Short codTipoDireccion, String tipoDireccion) {
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
		if (!(object instanceof TipoDireccionSd)) {
			return false;
		}
		TipoDireccionSd other = (TipoDireccionSd) object;
		if ((this.codTipoDireccion == null && other.codTipoDireccion != null)
				|| (this.codTipoDireccion != null && !this.codTipoDireccion.equals(other.codTipoDireccion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.TipoDireccion[ codTipoDireccion=" + codTipoDireccion + " ]";
	}

}
