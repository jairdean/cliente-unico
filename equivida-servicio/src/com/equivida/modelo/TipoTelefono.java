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
@Table(name = "TIPO_TELEFONO")
@NamedQueries({
		@NamedQuery(name = "TipoTelefono.findAll", query = "SELECT t FROM TipoTelefono t"),
		@NamedQuery(name = "TipoTelefono.findByCodTipoTelefono", query = "SELECT t FROM TipoTelefono t WHERE t.codTipoTelefono = :codTipoTelefono"),
		@NamedQuery(name = "TipoTelefono.findByTipoTelefono", query = "SELECT t FROM TipoTelefono t WHERE t.tipoTelefono = :tipoTelefono") })
public class TipoTelefono implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3055887144002733449L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_TIPO_TELEFONO")
	private Short codTipoTelefono;
	@Basic(optional = false)
	@Column(name = "TIPO_TELEFONO")
	private String tipoTelefono;
	@Basic(optional = false)
	@Column(name = "REQ_DIRECCION")
	private char reqDireccion;
	@Basic(optional = false)
	@Column(name = "CODIGO_VISIBLE")
	private char codigoVisible;

	public TipoTelefono() {
	}

	public boolean isDomicilio() {
		if (this.codTipoTelefono.shortValue() == Constantes.TIPO_TELEFONO_DOMICILIO) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOficina() {
		if (this.codTipoTelefono.shortValue() == Constantes.TIPO_TELEFONO_OFICINA) {
			return true;
		} else {
			return false;
		}
	}

	public TipoTelefono(Short codTipoTelefono) {
		this.codTipoTelefono = codTipoTelefono;
	}

	public TipoTelefono(Short codTipoTelefono, String tipoTelefono) {
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
		if (!(object instanceof TipoTelefono)) {
			return false;
		}
		TipoTelefono other = (TipoTelefono) object;
		if ((this.codTipoTelefono == null && other.codTipoTelefono != null)
				|| (this.codTipoTelefono != null && !this.codTipoTelefono
						.equals(other.codTipoTelefono))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.TipoTelefono[codTipoTelefono="
				+ codTipoTelefono + "]";
	}

	/**
	 * @return the reqDireccion
	 */
	public char getReqDireccion() {
		return reqDireccion;
	}

	/**
	 * @param reqDireccion
	 *            the reqDireccion to set
	 */
	public void setReqDireccion(char reqDireccion) {
		this.reqDireccion = reqDireccion;
	}

	public char getCodigoVisible() {
		return codigoVisible;
	}

	public void setCodigoVisible(char codigoVisible) {
		this.codigoVisible = codigoVisible;
	}

}
