package com.equivida.sise.rs;

import java.io.Serializable;

public class RsRegistroPresupuesto implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String grabo;
	private String mensaje;
	private String idSesion;
	private String codProcedimiento;
	private String cantidad;
	private String diagnostico;
	private String cobertura;
	private String pvp;
	private String valProcedimiento;
	private String valCubierto;
	private String coPago;
	private String novedad;
	private String diasCarencia;
	private String fechaVencimiento;

	/**
	 * @return the grabo
	 */
	public String getGrabo() {
		return grabo;
	}

	/**
	 * @param grabo
	 *            the grabo to set
	 */
	public void setGrabo(String grabo) {
		this.grabo = grabo;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the idSesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * @param idSesion
	 *            the idSesion to set
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
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
	 * @return the diagnostico
	 */
	public String getDiagnostico() {
		return diagnostico;
	}

	/**
	 * @param diagnostico
	 *            the diagnostico to set
	 */
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
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
	 * @return the valProcedimiento
	 */
	public String getValProcedimiento() {
		return valProcedimiento;
	}

	/**
	 * @param valProcedimiento
	 *            the valProcedimiento to set
	 */
	public void setValProcedimiento(String valProcedimiento) {
		this.valProcedimiento = valProcedimiento;
	}

	/**
	 * @return the valCubierto
	 */
	public String getValCubierto() {
		return valCubierto;
	}

	/**
	 * @param valCubierto
	 *            the valCubierto to set
	 */
	public void setValCubierto(String valCubierto) {
		this.valCubierto = valCubierto;
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
	 * @return the novedad
	 */
	public String getNovedad() {
		return novedad;
	}

	/**
	 * @param novedad
	 *            the novedad to set
	 */
	public void setNovedad(String novedad) {
		this.novedad = novedad;
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
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

}