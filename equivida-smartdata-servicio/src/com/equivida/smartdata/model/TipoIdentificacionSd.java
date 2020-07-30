/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "TIPO_IDENTIFICACION")
@XmlAccessorType(XmlAccessType.FIELD)
public class TipoIdentificacionSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "COD_TIPO_IDENTIFICACION")
	@XmlTransient
	private Character codTipoIdentificacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "TIPO_IDENTIFICACION")
	private String tipoIdentificacion;

	@Transient
	@XmlElement(name = "codTipoIdentificacion")
	private String codTipoIdentificacionStr;

	public TipoIdentificacionSd() {
	}

	public TipoIdentificacionSd(Character codTipoIdentificacion) {
		this.codTipoIdentificacion = codTipoIdentificacion;
	}

	public TipoIdentificacionSd(Character codTipoIdentificacion,
			String tipoIdentificacion) {
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
		hash += (codTipoIdentificacion != null ? codTipoIdentificacion
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoIdentificacionSd)) {
			return false;
		}
		TipoIdentificacionSd other = (TipoIdentificacionSd) object;
		if ((this.codTipoIdentificacion == null && other.codTipoIdentificacion != null)
				|| (this.codTipoIdentificacion != null && !this.codTipoIdentificacion
						.equals(other.codTipoIdentificacion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.TipoIdentificacion[ codTipoIdentificacion="
				+ codTipoIdentificacion + " ]";
	}

	/**
	 * @return the codTipoIdentificacionStr
	 */
	public String getCodTipoIdentificacionStr() {
		codTipoIdentificacionStr = new String(
				new char[] { this.codTipoIdentificacion });
		return codTipoIdentificacionStr;
	}

	/**
	 * @param codTipoIdentificacionStr
	 *            the codTipoIdentificacionStr to set
	 */
	public void setCodTipoIdentificacionStr(String codTipoIdentificacionStr) {
		this.codTipoIdentificacionStr = codTipoIdentificacionStr;
	}

}
