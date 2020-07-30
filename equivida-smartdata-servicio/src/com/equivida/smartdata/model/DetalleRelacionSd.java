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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "DETALLE_RELACION")
public class DetalleRelacionSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_DETALLE_RELACION")
	private Short secDetalleRelacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 16)
	@Column(name = "DETALLE_RELACION")
	private String detalleRelacion;
	@JoinColumn(name = "COD_TIPO_PARENTESCO", referencedColumnName = "COD_TIPO_PARENTESCO")
	@ManyToOne(optional = false)
	private TipoParentescoRelacionSd codTipoParentesco;

	public DetalleRelacionSd() {
	}

	public DetalleRelacionSd(Short secDetalleRelacion) {
		this.secDetalleRelacion = secDetalleRelacion;
	}

	public DetalleRelacionSd(Short secDetalleRelacion, String detalleRelacion) {
		this.secDetalleRelacion = secDetalleRelacion;
		this.detalleRelacion = detalleRelacion;
	}

	public Short getSecDetalleRelacion() {
		return secDetalleRelacion;
	}

	public void setSecDetalleRelacion(Short secDetalleRelacion) {
		this.secDetalleRelacion = secDetalleRelacion;
	}

	public String getDetalleRelacion() {
		return detalleRelacion;
	}

	public void setDetalleRelacion(String detalleRelacion) {
		this.detalleRelacion = detalleRelacion;
	}

	public TipoParentescoRelacionSd getCodTipoParentesco() {
		return codTipoParentesco;
	}

	public void setCodTipoParentesco(TipoParentescoRelacionSd codTipoParentesco) {
		this.codTipoParentesco = codTipoParentesco;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secDetalleRelacion != null ? secDetalleRelacion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DetalleRelacionSd)) {
			return false;
		}
		DetalleRelacionSd other = (DetalleRelacionSd) object;
		if ((this.secDetalleRelacion == null && other.secDetalleRelacion != null)
				|| (this.secDetalleRelacion != null && !this.secDetalleRelacion.equals(other.secDetalleRelacion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.DetalleRelacion[ secDetalleRelacion=" + secDetalleRelacion + " ]";
	}

}
