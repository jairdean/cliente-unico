/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

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
import javax.persistence.Transient;

import com.equivida.constant.EstadoEnum;

/**
 * 
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "MOTIVO_SEGURO")
public class MotivoSeguro implements Serializable {

	private static final long serialVersionUID = 8027235728360779865L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_MOTIVO_SEGURO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secMotivoSeguro;

	@Basic(optional = false)
	@JoinColumn(name = "COD_TIPO_MOTIVO", referencedColumnName = "COD_TIPO_MOTIVO")
	@ManyToOne(optional = false)
	private TipoMotivoSeguro tipoMotivoSeguro;

	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	private Persona persona;
	
	@Column(name = "DETALLE", length = 64)
	private String detalle;//para guardar el detalle de otros (especiqfique)
	
	@Basic(optional = false)
	@Column(name = "ESTADO")
	private char estado;// para historico, es decir si alguna vez en la bdd
						// estaba activo A y cambia, entonces que se quede en
						// estado I de inactivos

	@Transient
	private boolean seleccionado;
	
	@Transient
	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo();
	}

	public MotivoSeguro() {
	}

	public Integer getSecMotivoSeguro() {
		return secMotivoSeguro;
	}

	public void setSecMotivoSeguro(Integer secMotivoSeguro) {
		this.secMotivoSeguro = secMotivoSeguro;
	}

	public TipoMotivoSeguro getTipoMotivoSeguro() {
		return tipoMotivoSeguro;
	}

	public void setTipoMotivoSeguro(TipoMotivoSeguro tipoMotivoSeguro) {
		this.tipoMotivoSeguro = tipoMotivoSeguro;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isSeleccionado() {
		if (this.getActivo()) {
			seleccionado = true;
		} else {
			seleccionado = false;
		}
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		if (seleccionado) {
			this.setEstado(EstadoEnum.ACTIVO.getCodigo());
		} else {
			this.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
		this.seleccionado = seleccionado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detalle == null) ? 0 : detalle.hashCode());
		result = prime * result + estado;
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		result = prime * result
				+ ((secMotivoSeguro == null) ? 0 : secMotivoSeguro.hashCode());
		result = prime
				* result
				+ ((tipoMotivoSeguro == null) ? 0 : tipoMotivoSeguro.hashCode());
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
		MotivoSeguro other = (MotivoSeguro) obj;
		if (detalle == null) {
			if (other.detalle != null)
				return false;
		} else if (!detalle.equals(other.detalle))
			return false;
		if (estado != other.estado)
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		if (secMotivoSeguro == null) {
			if (other.secMotivoSeguro != null)
				return false;
		} else if (!secMotivoSeguro.equals(other.secMotivoSeguro))
			return false;
		if (tipoMotivoSeguro == null) {
			if (other.tipoMotivoSeguro != null)
				return false;
		} else if (!tipoMotivoSeguro.equals(other.tipoMotivoSeguro))
			return false;
		return true;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
}