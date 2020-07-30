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
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_SERVICIO_FINANCIERO")
public class TipoServicioFinanciero implements Serializable {

	private static final long serialVersionUID = -3055887144002733449L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_TIPO_SERVICIO")
	private Short secTipoServicio;

	@Basic(optional = false)
	@Column(name = "TIPO_SERVICIO", length = 32)
	private String tipoServicio;

	public TipoServicioFinanciero() {
	}

	public Short getSecTipoServicio() {
		return secTipoServicio;
	}

	public void setSecTipoServicio(Short secTipoServicio) {
		this.secTipoServicio = secTipoServicio;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((secTipoServicio == null) ? 0 : secTipoServicio.hashCode());
		result = prime * result
				+ ((tipoServicio == null) ? 0 : tipoServicio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoServicioFinanciero other = (TipoServicioFinanciero) obj;
		if (secTipoServicio == null) {
			if (other.secTipoServicio != null)
				return false;
		} else if (!secTipoServicio.equals(other.secTipoServicio))
			return false;
		if (tipoServicio == null) {
			if (other.tipoServicio != null)
				return false;
		} else if (!tipoServicio.equals(other.tipoServicio))
			return false;
		return true;
	}
}