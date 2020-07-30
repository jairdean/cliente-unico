package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsConsultaSaldos implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String sn_grabo;
	private String mensaje;
	private BigDecimal presupuesto;
	private String paciente;
	private String procedimiento;
	private BigDecimal imp_pvp;
	private BigDecimal imp_valor_eq;
	private BigDecimal imp_valor_cubierto;
	private BigDecimal imp_co_pago;
	private String fec_atencion;
	private String fec_pago;
	private BigDecimal cod_pres;
	private String medico;

	/**
	 * @return the sn_grabo
	 */
	public String getSn_grabo() {
		return sn_grabo;
	}

	/**
	 * @param sn_grabo
	 *            the sn_grabo to set
	 */
	public void setSn_grabo(String sn_grabo) {
		this.sn_grabo = sn_grabo;
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
	 * @return the presupuesto
	 */
	public BigDecimal getPresupuesto() {
		return presupuesto;
	}

	/**
	 * @param presupuesto
	 *            the presupuesto to set
	 */
	public void setPresupuesto(BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
	}

	/**
	 * @return the paciente
	 */
	public String getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the imp_pvp
	 */
	public BigDecimal getImp_pvp() {
		return imp_pvp;
	}

	/**
	 * @param imp_pvp
	 *            the imp_pvp to set
	 */
	public void setImp_pvp(BigDecimal imp_pvp) {
		this.imp_pvp = imp_pvp;
	}

	/**
	 * @return the imp_valor_eq
	 */
	public BigDecimal getImp_valor_eq() {
		return imp_valor_eq;
	}

	/**
	 * @param imp_valor_eq
	 *            the imp_valor_eq to set
	 */
	public void setImp_valor_eq(BigDecimal imp_valor_eq) {
		this.imp_valor_eq = imp_valor_eq;
	}

	/**
	 * @return the imp_valor_cubierto
	 */
	public BigDecimal getImp_valor_cubierto() {
		return imp_valor_cubierto;
	}

	/**
	 * @param imp_valor_cubierto
	 *            the imp_valor_cubierto to set
	 */
	public void setImp_valor_cubierto(BigDecimal imp_valor_cubierto) {
		this.imp_valor_cubierto = imp_valor_cubierto;
	}

	/**
	 * @return the imp_co_pago
	 */
	public BigDecimal getImp_co_pago() {
		return imp_co_pago;
	}

	/**
	 * @param imp_co_pago
	 *            the imp_co_pago to set
	 */
	public void setImp_co_pago(BigDecimal imp_co_pago) {
		this.imp_co_pago = imp_co_pago;
	}

	/**
	 * @return the fec_atencion
	 */
	public String getFec_atencion() {
		return fec_atencion;
	}

	/**
	 * @param fec_atencion
	 *            the fec_atencion to set
	 */
	public void setFec_atencion(String fec_atencion) {
		this.fec_atencion = fec_atencion;
	}

	/**
	 * @return the fec_pago
	 */
	public String getFec_pago() {
		return fec_pago;
	}

	/**
	 * @param fec_pago
	 *            the fec_pago to set
	 */
	public void setFec_pago(String fec_pago) {
		this.fec_pago = fec_pago;
	}

	/**
	 * @return the cod_pres
	 */
	public BigDecimal getCod_pres() {
		return cod_pres;
	}

	/**
	 * @param cod_pres
	 *            the cod_pres to set
	 */
	public void setCod_pres(BigDecimal cod_pres) {
		this.cod_pres = cod_pres;
	}

	/**
	 * @return the medico
	 */
	public String getMedico() {
		return medico;
	}

	/**
	 * @param medico
	 *            the medico to set
	 */
	public void setMedico(String medico) {
		this.medico = medico;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the procedimiento
	 */
	public String getProcedimiento() {
		return procedimiento;
	}

	/**
	 * @param procedimiento the procedimiento to set
	 */
	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}

}