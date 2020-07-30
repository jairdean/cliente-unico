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

import com.equivida.constant.Constantes;

/**
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "ESTADO_RCS")
public class EstadoRcs implements Serializable {

	private static final long serialVersionUID = -7792695593042137225L;

	@Id
	@Column(name = "COD_ESTADO")
	private Character codEstado;

	@Basic(optional = false)
	@Column(name = "ESTADO")
	private String estado;

	@Basic(optional = false)
	@Column(name = "NUM_DIAS")
	private short numDias;// numero de dias maximos en ese estado

	public boolean isPendiente() {
		if (getCodEstado() == Constantes.ID_ESTADO_RCS_PENDIENTE) {
			return true;
		}
		return false;
	}

	public boolean isAprobado() {
		if (getCodEstado() == Constantes.ID_ESTADO_RCS_APROBADO) {
			return true;
		}
		return false;
	}

	public boolean isRechazado() {
		if (getCodEstado() == Constantes.ID_ESTADO_RCS_RECHAZADO) {
			return true;
		}
		return false;
	}

	/**
	 * Constructor
	 */
	public EstadoRcs() {
	}

	/**
	 * Constructor
	 * 
	 * @param codEstado
	 */
	public EstadoRcs(Character codEstado) {
		super();
		this.codEstado = codEstado;
	}

	public char getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(char codEstado) {
		this.codEstado = codEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public short getNumDias() {
		return numDias;
	}

	public void setNumDias(short numDias) {
		this.numDias = numDias;
	}
}