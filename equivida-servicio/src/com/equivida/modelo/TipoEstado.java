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
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "TIPO_ESTADO")
public class TipoEstado implements Serializable {

	private static final long serialVersionUID = 8931156527691866670L;

	@Id
	@Basic(optional = false)
	@Column(name = "COD_ESTADO")
	private Short codEstado;
	@Basic(optional = false)
	@Column(name = "ESTADO_ASEGURADO", length = 32)
	private String estadoAsegurado;

	public TipoEstado() {
	}
	
	public TipoEstado(Short codEstado) {
		this.codEstado = codEstado;
	}

	public Short getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(Short codEstado) {
		this.codEstado = codEstado;
	}

	public String getEstadoAsegurado() {
		return estadoAsegurado;
	}

	public void setEstadoAsegurado(String estadoAsegurado) {
		this.estadoAsegurado = estadoAsegurado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codEstado == null) ? 0 : codEstado.hashCode());
		result = prime * result
				+ ((estadoAsegurado == null) ? 0 : estadoAsegurado.hashCode());
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
		TipoEstado other = (TipoEstado) obj;
		if (codEstado == null) {
			if (other.codEstado != null)
				return false;
		} else if (!codEstado.equals(other.codEstado))
			return false;
		if (estadoAsegurado == null) {
			if (other.estadoAsegurado != null)
				return false;
		} else if (!estadoAsegurado.equals(other.estadoAsegurado))
			return false;
		return true;
	}
}