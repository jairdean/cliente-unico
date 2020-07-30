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
@Table(name = "TIPO_TELEFONO")
public class TipoTelefonoSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "COD_TIPO_TELEFONO")
	private Short codTipoTelefono;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 16)
	@Column(name = "TIPO_TELEFONO")
	private String tipoTelefono;

	public TipoTelefonoSd() {
	}

	public TipoTelefonoSd(Short codTipoTelefono) {
		this.codTipoTelefono = codTipoTelefono;
	}

	public TipoTelefonoSd(Short codTipoTelefono, String tipoTelefono) {
		this.codTipoTelefono = codTipoTelefono;
		this.tipoTelefono = tipoTelefono;
	}

	public Short getCodTipoTelefono() {
		return codTipoTelefono;
	}

	public void setCodTipoTelefono(Short codTipoTelefono) {
		this.codTipoTelefono = codTipoTelefono;
	}

	public String getTipoTelefono() {
		return tipoTelefono;
	}

	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codTipoTelefono != null ? codTipoTelefono.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoTelefonoSd)) {
			return false;
		}
		TipoTelefonoSd other = (TipoTelefonoSd) object;
		if ((this.codTipoTelefono == null && other.codTipoTelefono != null)
				|| (this.codTipoTelefono != null && !this.codTipoTelefono.equals(other.codTipoTelefono))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.TipoTelefono[ codTipoTelefono=" + codTipoTelefono + " ]";
	}

}
