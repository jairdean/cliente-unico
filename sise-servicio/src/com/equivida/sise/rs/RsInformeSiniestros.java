package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsInformeSiniestros implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal cod_suc;
	private BigDecimal cod_ramo;
	private BigDecimal nro_pol;
	private BigDecimal ejercicio;
	private BigDecimal nro_stro;
	private String fecha_stro;
	private String fecha_nodificacion;
	private String cobertura;
	private String tipo_estimacion;
	private BigDecimal valor_estimado;
	private BigDecimal valor_pagado;
	private String estado;
	private String asegurado;
	private String campo1;
	private String campo2;
	private String campo3;

	/**
	 * @return the cod_suc
	 */
	public BigDecimal getCod_suc() {
		return cod_suc;
	}

	/**
	 * @param cod_suc
	 *            the cod_suc to set
	 */
	public void setCod_suc(BigDecimal cod_suc) {
		this.cod_suc = cod_suc;
	}

	/**
	 * @return the cod_ramo
	 */
	public BigDecimal getCod_ramo() {
		return cod_ramo;
	}

	/**
	 * @param cod_ramo
	 *            the cod_ramo to set
	 */
	public void setCod_ramo(BigDecimal cod_ramo) {
		this.cod_ramo = cod_ramo;
	}

	/**
	 * @return the nro_pol
	 */
	public BigDecimal getNro_pol() {
		return nro_pol;
	}

	/**
	 * @param nro_pol
	 *            the nro_pol to set
	 */
	public void setNro_pol(BigDecimal nro_pol) {
		this.nro_pol = nro_pol;
	}

	/**
	 * @return the ejercicio
	 */
	public BigDecimal getEjercicio() {
		return ejercicio;
	}

	/**
	 * @param ejercicio
	 *            the ejercicio to set
	 */
	public void setEjercicio(BigDecimal ejercicio) {
		this.ejercicio = ejercicio;
	}

	/**
	 * @return the nro_stro
	 */
	public BigDecimal getNro_stro() {
		return nro_stro;
	}

	/**
	 * @param nro_stro
	 *            the nro_stro to set
	 */
	public void setNro_stro(BigDecimal nro_stro) {
		this.nro_stro = nro_stro;
	}

	/**
	 * @return the fecha_stro
	 */
	public String getFecha_stro() {
		return fecha_stro;
	}

	/**
	 * @param fecha_stro
	 *            the fecha_stro to set
	 */
	public void setFecha_stro(String fecha_stro) {
		this.fecha_stro = fecha_stro;
	}

	/**
	 * @return the fecha_nodificacion
	 */
	public String getFecha_nodificacion() {
		return fecha_nodificacion;
	}

	/**
	 * @param fecha_nodificacion
	 *            the fecha_nodificacion to set
	 */
	public void setFecha_nodificacion(String fecha_nodificacion) {
		this.fecha_nodificacion = fecha_nodificacion;
	}

	/**
	 * @return the cobertura
	 */
	public String getCobertura() {
		return cobertura;
	}

	/**
	 * @param cobertura
	 *            the cobertura to set
	 */
	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	/**
	 * @return the tipo_estimacion
	 */
	public String getTipo_estimacion() {
		return tipo_estimacion;
	}

	/**
	 * @param tipo_estimacion
	 *            the tipo_estimacion to set
	 */
	public void setTipo_estimacion(String tipo_estimacion) {
		this.tipo_estimacion = tipo_estimacion;
	}

	/**
	 * @return the valor_estimado
	 */
	public BigDecimal getValor_estimado() {
		return valor_estimado;
	}

	/**
	 * @param valor_estimado
	 *            the valor_estimado to set
	 */
	public void setValor_estimado(BigDecimal valor_estimado) {
		this.valor_estimado = valor_estimado;
	}

	/**
	 * @return the valor_pagado
	 */
	public BigDecimal getValor_pagado() {
		return valor_pagado;
	}

	/**
	 * @param valor_pagado
	 *            the valor_pagado to set
	 */
	public void setValor_pagado(BigDecimal valor_pagado) {
		this.valor_pagado = valor_pagado;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the asegurado
	 */
	public String getAsegurado() {
		return asegurado;
	}

	/**
	 * @param asegurado
	 *            the asegurado to set
	 */
	public void setAsegurado(String asegurado) {
		this.asegurado = asegurado;
	}

	/**
	 * @return the campo1
	 */
	public String getCampo1() {
		return campo1;
	}

	/**
	 * @param campo1
	 *            the campo1 to set
	 */
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	/**
	 * @return the campo2
	 */
	public String getCampo2() {
		return campo2;
	}

	/**
	 * @param campo2
	 *            the campo2 to set
	 */
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	/**
	 * @return the campo3
	 */
	public String getCampo3() {
		return campo3;
	}

	/**
	 * @param campo3
	 *            the campo3 to set
	 */
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

}