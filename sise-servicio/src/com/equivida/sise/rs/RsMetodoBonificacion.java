package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsMetodoBonificacion implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal regAtencion;
	private BigDecimal recalculoPresupuesto;
	private String msgError;
	private String idPresupuesto;
	private String codProcedimiento;
	private String cantidad;
	private String idDiagnostico;
	private String cobertura;
	private String pvp;
	private String pvpEq;
	private String valorCubierto;
	private String coPago;
	private String novedadAtencion;
	private String diasCarencia;
	private String fechaVencimientoCarencia;

	/**
	 * @return the regAtencion
	 */
	public BigDecimal getRegAtencion() {
		return regAtencion;
	}

	/**
	 * @param regAtencion
	 *            the regAtencion to set
	 */
	public void setRegAtencion(BigDecimal regAtencion) {
		this.regAtencion = regAtencion;
	}

	/**
	 * @return the recalculoPresupuesto
	 */
	public BigDecimal getRecalculoPresupuesto() {
		return recalculoPresupuesto;
	}

	/**
	 * @param recalculoPresupuesto
	 *            the recalculoPresupuesto to set
	 */
	public void setRecalculoPresupuesto(BigDecimal recalculoPresupuesto) {
		this.recalculoPresupuesto = recalculoPresupuesto;
	}

	/**
	 * @return the msgError
	 */
	public String getMsgError() {
		return msgError;
	}

	/**
	 * @param msgError
	 *            the msgError to set
	 */
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	/**
	 * @return the idPresupuesto
	 */
	public String getIdPresupuesto() {
		return idPresupuesto;
	}

	/**
	 * @param idPresupuesto
	 *            the idPresupuesto to set
	 */
	public void setIdPresupuesto(String idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}

	/**
	 * @return the codProcedimiento
	 */
	public String getCodProcedimiento() {
		return codProcedimiento;
	}

	/**
	 * @param codProcedimiento
	 *            the codProcedimiento to set
	 */
	public void setCodProcedimiento(String codProcedimiento) {
		this.codProcedimiento = codProcedimiento;
	}

	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the idDiagnostico
	 */
	public String getIdDiagnostico() {
		return idDiagnostico;
	}

	/**
	 * @param idDiagnostico
	 *            the idDiagnostico to set
	 */
	public void setIdDiagnostico(String idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
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
	 * @return the pvp
	 */
	public String getPvp() {
		return pvp;
	}

	/**
	 * @param pvp
	 *            the pvp to set
	 */
	public void setPvp(String pvp) {
		this.pvp = pvp;
	}

	/**
	 * @return the pvpEq
	 */
	public String getPvpEq() {
		return pvpEq;
	}

	/**
	 * @param pvpEq
	 *            the pvpEq to set
	 */
	public void setPvpEq(String pvpEq) {
		this.pvpEq = pvpEq;
	}

	/**
	 * @return the valorCubierto
	 */
	public String getValorCubierto() {
		return valorCubierto;
	}

	/**
	 * @param valorCubierto
	 *            the valorCubierto to set
	 */
	public void setValorCubierto(String valorCubierto) {
		this.valorCubierto = valorCubierto;
	}

	/**
	 * @return the coPago
	 */
	public String getCoPago() {
		return coPago;
	}

	/**
	 * @param coPago
	 *            the coPago to set
	 */
	public void setCoPago(String coPago) {
		this.coPago = coPago;
	}

	/**
	 * @return the novedadAtencion
	 */
	public String getNovedadAtencion() {
		return novedadAtencion;
	}

	/**
	 * @param novedadAtencion
	 *            the novedadAtencion to set
	 */
	public void setNovedadAtencion(String novedadAtencion) {
		this.novedadAtencion = novedadAtencion;
	}

	/**
	 * @return the diasCarencia
	 */
	public String getDiasCarencia() {
		return diasCarencia;
	}

	/**
	 * @param diasCarencia
	 *            the diasCarencia to set
	 */
	public void setDiasCarencia(String diasCarencia) {
		this.diasCarencia = diasCarencia;
	}

	/**
	 * @return the fechaVencimientoCarencia
	 */
	public String getFechaVencimientoCarencia() {
		return fechaVencimientoCarencia;
	}

	/**
	 * @param fechaVencimientoCarencia
	 *            the fechaVencimientoCarencia to set
	 */
	public void setFechaVencimientoCarencia(String fechaVencimientoCarencia) {
		this.fechaVencimientoCarencia = fechaVencimientoCarencia;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}