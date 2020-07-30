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

import com.equivida.constant.TipoIdentificacionEnum;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_IDENTIFICACION")
@NamedQueries({ @NamedQuery(name = "TipoIdentificacion.findAll", query = "SELECT t FROM TipoIdentificacion t"),
		@NamedQuery(name = "TipoIdentificacion.findByCodTipoIdentificacion", query = "SELECT t FROM TipoIdentificacion t WHERE t.codTipoIdentificacion = :codTipoIdentificacion"),
		@NamedQuery(name = "TipoIdentificacion.findByTipoIdentificacion", query = "SELECT t FROM TipoIdentificacion t WHERE t.tipoIdentificacion = :tipoIdentificacion") })
public class TipoIdentificacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1928046911460830951L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_TIPO_IDENTIFICACION")
	private Character codTipoIdentificacion;
	@Basic(optional = false)
	@Column(name = "TIPO_IDENTIFICACION")
	private String tipoIdentificacion;
	@Column(name = "SEC_TIPO_IDENTIFICACION")
	private Short secTipoIdentificacion;

	public TipoIdentificacion() {
	}

	public TipoIdentificacion(Short secTipoIdentificacion, String tipoIdentificacion) {
		this.secTipoIdentificacion = secTipoIdentificacion;
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public boolean isPasaporte() {
		System.out.println("psaporte?" + this.codTipoIdentificacion);
		if (this.codTipoIdentificacion != null
				&& this.codTipoIdentificacion.charValue() == TipoIdentificacionEnum.PASAPORTE.getCodigo()) {
			return true;
		}
		return false;
	}

	public boolean isRuc() {
		System.out.println("ruc?" + this.codTipoIdentificacion);
		if (this.codTipoIdentificacion != null
				&& this.codTipoIdentificacion.charValue() == TipoIdentificacionEnum.RUC.getCodigo()) {
			return true;
		}
		return false;
	}

	public boolean isCedula() {
		if (this.codTipoIdentificacion != null
				&& this.codTipoIdentificacion.charValue() == TipoIdentificacionEnum.CEDULA.getCodigo()) {
			return true;
		}
		return false;
	}

	public TipoIdentificacion(Character codTipoIdentificacion) {
		this.codTipoIdentificacion = codTipoIdentificacion;
	}

	public TipoIdentificacion(Character codTipoIdentificacion, String tipoIdentificacion) {
		this.codTipoIdentificacion = codTipoIdentificacion;
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public Character getCodTipoIdentificacion() {
		return codTipoIdentificacion;
	}

	public void setCodTipoIdentificacion(Character codTipoIdentificacion) {
		this.codTipoIdentificacion = codTipoIdentificacion;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codTipoIdentificacion != null ? codTipoIdentificacion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoIdentificacion)) {
			return false;
		}
		TipoIdentificacion other = (TipoIdentificacion) object;
		if ((this.codTipoIdentificacion == null && other.codTipoIdentificacion != null)
				|| (this.codTipoIdentificacion != null
						&& !this.codTipoIdentificacion.equals(other.codTipoIdentificacion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.TipoIdentificacion[codTipoIdentificacion=" + codTipoIdentificacion + "]";
	}

	public Short getSecTipoIdentificacion() {
		return secTipoIdentificacion;
	}

	public void setSecTipoIdentificacion(Short secTipoIdentificacion) {
		this.secTipoIdentificacion = secTipoIdentificacion;
	}
}