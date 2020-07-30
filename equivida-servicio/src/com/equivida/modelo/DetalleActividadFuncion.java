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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.equivida.util.StringUtil;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "DETALLE_ACTIVIDAD_FUNCION")
@NamedQueries({
		@NamedQuery(name = "DetalleActividadFuncion.findAll", query = "SELECT d FROM DetalleActividadFuncion d"),
		@NamedQuery(name = "DetalleActividadFuncion.findBySecDetalleFunciones", query = "SELECT d FROM DetalleActividadFuncion d WHERE d.secDetalleFunciones = :secDetalleFunciones"),
		@NamedQuery(name = "DetalleActividadFuncion.findByDetalleFunciones", query = "SELECT d FROM DetalleActividadFuncion d WHERE d.detalleFunciones = :detalleFunciones") })
public class DetalleActividadFuncion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_DETALLE_FUNCIONES")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secDetalleFunciones;
	@Basic(optional = false)
	@Column(name = "DETALLE_FUNCIONES")
	private String detalleFunciones;
	// @JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName =
	// "SEC_PERSONA_NATURAL")
	// @OneToOne(optional = false)
	// private PersonaNatural personaNatural;
	@Column(name = "SEC_PERSONA_NATURAL")
	private Integer secPersonaNatural;

	public DetalleActividadFuncion() {
	}

	public DetalleActividadFuncion(Integer secDetalleFunciones) {
		this.secDetalleFunciones = secDetalleFunciones;
	}

	public DetalleActividadFuncion(Integer secDetalleFunciones,
			String detalleFunciones) {
		this.secDetalleFunciones = secDetalleFunciones;
		this.detalleFunciones = detalleFunciones;
	}

	public Integer getSecDetalleFunciones() {
		return secDetalleFunciones;
	}

	public void setSecDetalleFunciones(Integer secDetalleFunciones) {
		this.secDetalleFunciones = secDetalleFunciones;
	}

	public String getDetalleFunciones() {
		return detalleFunciones;
	}

	public void setDetalleFunciones(String detalleFunciones) {
		if(detalleFunciones!=null){
			this.detalleFunciones = StringUtil.toUpper(detalleFunciones).trim();
		}
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secDetalleFunciones != null ? secDetalleFunciones.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DetalleActividadFuncion)) {
			return false;
		}
		DetalleActividadFuncion other = (DetalleActividadFuncion) object;
		if ((this.secDetalleFunciones == null && other.secDetalleFunciones != null)
				|| (this.secDetalleFunciones != null && !this.secDetalleFunciones
						.equals(other.secDetalleFunciones))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.DetalleActividadFuncion[secDetalleFunciones="
				+ secDetalleFunciones + "]";
	}

	public Integer getSecPersonaNatural() {
		return secPersonaNatural;
	}

	public void setSecPersonaNatural(Integer secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
	}

}
