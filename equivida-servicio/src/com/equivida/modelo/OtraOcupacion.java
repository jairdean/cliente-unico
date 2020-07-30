/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "OTRA_OCUPACION")
public class OtraOcupacion implements Serializable {

	private static final long serialVersionUID = -4607151622245621615L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_OTRA_OCUPACION")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secOtraOcupacion;

	@Column(name = "SEC_PERSONA_NATURAL")
	private Integer secPersonaNatural;

	@JoinColumn(name = "COD_OTRA_OCUPACION", referencedColumnName = "COD_OTRA_OCUPACION")
	@ManyToOne
	private TipoOtraOcupacion tipoOtraOcupacion;

	@Column(name = "TIEMPO_OCUPACION", precision = 4, scale = 2, columnDefinition = "decimal(4,2)")
	private BigDecimal tiempoOcupacion;

	@Basic(optional = false)
	@Column(name = "COD_TIEMPO")
	private char codTiempo;// A=anios, M=meseso

	@Basic(optional = false)
	@Column(name = "ESTADO")
	private char estado;

	public OtraOcupacion() {
	}

	/**
	 * @return the secOtraOcupacion
	 */
	public Integer getSecOtraOcupacion() {
		return secOtraOcupacion;
	}

	/**
	 * @param secOtraOcupacion
	 *            the secOtraOcupacion to set
	 */
	public void setSecOtraOcupacion(Integer secOtraOcupacion) {
		this.secOtraOcupacion = secOtraOcupacion;
	}

	/**
	 * @return the secPersonaNatural
	 */
	public Integer getSecPersonaNatural() {
		return secPersonaNatural;
	}

	/**
	 * @param secPersonaNatural
	 *            the secPersonaNatural to set
	 */
	public void setSecPersonaNatural(Integer secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
	}

	/**
	 * @return the tipoOtraOcupacion
	 */
	public TipoOtraOcupacion getTipoOtraOcupacion() {
		return tipoOtraOcupacion;
	}

	/**
	 * @param tipoOtraOcupacion
	 *            the tipoOtraOcupacion to set
	 */
	public void setTipoOtraOcupacion(TipoOtraOcupacion tipoOtraOcupacion) {
		this.tipoOtraOcupacion = tipoOtraOcupacion;
	}

	/**
	 * @return the tiempoOcupacion
	 */
	public BigDecimal getTiempoOcupacion() {
		return tiempoOcupacion;
	}

	/**
	 * @param tiempoOcupacion
	 *            the tiempoOcupacion to set
	 */
	public void setTiempoOcupacion(BigDecimal tiempoOcupacion) {
		this.tiempoOcupacion = tiempoOcupacion;
	}

	/**
	 * @return the codTiempo
	 */
	public char getCodTiempo() {
		return codTiempo;
	}

	/**
	 * @param codTiempo
	 *            the codTiempo to set
	 */
	public void setCodTiempo(char codTiempo) {
		this.codTiempo = codTiempo;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
}