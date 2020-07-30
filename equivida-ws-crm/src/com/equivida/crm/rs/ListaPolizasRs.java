/**
 * 
 */
package com.equivida.crm.rs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author saviasoft5
 * 
 */
public class ListaPolizasRs implements Serializable {

	private static final long serialVersionUID = -3549457935371005804L;

	private BigDecimal Numero_Poliza;
	private Integer Porc_Extraprima;
	private String Contratante;
	private Integer Sucursal;
	private Integer Ramo;
	private String Estado_entrega_poliza;
	private String Estado_poliza;
	private BigDecimal Prima_mensual;
	private String Periodicidad_pago;
	private String Fecha_inicio_vigencia;
	private Integer Antiguedad_poliza;
	private Integer Estado_Cuenta;
	private String Tiene_deuda;
	private Integer Monto_Deuda;
	private String Forma_Pago;
	private String Fecha_A_Caducar;
	private String Periodo_Gracia;
	private String Tipo_Conducto;
	private String Nro_Conducto;
	private String Estado_Conducto;
	private String Institucion_Conducto;
	private Integer Fecha_Vigencia_Conducto;
	private Integer Nro_Cupones_Pendientes;


	/**
	 * @return the porc_Extraprima
	 */
	public Integer getPorc_Extraprima() {
		return Porc_Extraprima;
	}

	/**
	 * @param porc_Extraprima
	 *            the porc_Extraprima to set
	 */
	public void setPorc_Extraprima(Integer porc_Extraprima) {
		Porc_Extraprima = porc_Extraprima;
	}

	/**
	 * @return the contratante
	 */
	public String getContratante() {
		return Contratante;
	}

	/**
	 * @param contratante
	 *            the contratante to set
	 */
	public void setContratante(String contratante) {
		Contratante = contratante;
	}

	/**
	 * @return the sucursal
	 */
	public Integer getSucursal() {
		return Sucursal;
	}

	/**
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(Integer sucursal) {
		Sucursal = sucursal;
	}

	/**
	 * @return the ramo
	 */
	public Integer getRamo() {
		return Ramo;
	}

	/**
	 * @param ramo
	 *            the ramo to set
	 */
	public void setRamo(Integer ramo) {
		Ramo = ramo;
	}

	/**
	 * @return the estado_entrega_poliza
	 */
	public String getEstado_entrega_poliza() {
		return Estado_entrega_poliza;
	}

	/**
	 * @param estado_entrega_poliza
	 *            the estado_entrega_poliza to set
	 */
	public void setEstado_entrega_poliza(String estado_entrega_poliza) {
		Estado_entrega_poliza = estado_entrega_poliza;
	}

	/**
	 * @return the estado_poliza
	 */
	public String getEstado_poliza() {
		return Estado_poliza;
	}

	/**
	 * @param estado_poliza
	 *            the estado_poliza to set
	 */
	public void setEstado_poliza(String estado_poliza) {
		Estado_poliza = estado_poliza;
	}

	/**
	 * @return the prima_mensual
	 */
	public BigDecimal getPrima_mensual() {
		return Prima_mensual;
	}

	/**
	 * @param prima_mensual
	 *            the prima_mensual to set
	 */
	public void setPrima_mensual(BigDecimal prima_mensual) {
		Prima_mensual = prima_mensual;
	}

	/**
	 * @return the periodicidad_pago
	 */
	public String getPeriodicidad_pago() {
		return Periodicidad_pago;
	}

	/**
	 * @param periodicidad_pago
	 *            the periodicidad_pago to set
	 */
	public void setPeriodicidad_pago(String periodicidad_pago) {
		Periodicidad_pago = periodicidad_pago;
	}


	/**
	 * @return the antiguedad_poliza
	 */
	public Integer getAntiguedad_poliza() {
		return Antiguedad_poliza;
	}

	/**
	 * @param antiguedad_poliza
	 *            the antiguedad_poliza to set
	 */
	public void setAntiguedad_poliza(Integer antiguedad_poliza) {
		Antiguedad_poliza = antiguedad_poliza;
	}

	/**
	 * @return the estado_Cuenta
	 */
	public Integer getEstado_Cuenta() {
		return Estado_Cuenta;
	}

	/**
	 * @param estado_Cuenta
	 *            the estado_Cuenta to set
	 */
	public void setEstado_Cuenta(Integer estado_Cuenta) {
		Estado_Cuenta = estado_Cuenta;
	}

	/**
	 * @return the monto_Deuda
	 */
	public Integer getMonto_Deuda() {
		return Monto_Deuda;
	}

	/**
	 * @param monto_Deuda
	 *            the monto_Deuda to set
	 */
	public void setMonto_Deuda(Integer monto_Deuda) {
		Monto_Deuda = monto_Deuda;
	}


	/**
	 * @return the tipo_Conducto
	 */
	public String getTipo_Conducto() {
		return Tipo_Conducto;
	}

	/**
	 * @param tipo_Conducto
	 *            the tipo_Conducto to set
	 */
	public void setTipo_Conducto(String tipo_Conducto) {
		Tipo_Conducto = tipo_Conducto;
	}

	/**
	 * @return the estado_Conducto
	 */
	public String getEstado_Conducto() {
		return Estado_Conducto;
	}

	/**
	 * @param estado_Conducto
	 *            the estado_Conducto to set
	 */
	public void setEstado_Conducto(String estado_Conducto) {
		Estado_Conducto = estado_Conducto;
	}

	/**
	 * @return the institucion_Conducto
	 */
	public String getInstitucion_Conducto() {
		return Institucion_Conducto;
	}

	/**
	 * @param institucion_Conducto
	 *            the institucion_Conducto to set
	 */
	public void setInstitucion_Conducto(String institucion_Conducto) {
		Institucion_Conducto = institucion_Conducto;
	}

	/**
	 * @return the fecha_Vigencia_Conducto
	 */
	public Integer getFecha_Vigencia_Conducto() {
		return Fecha_Vigencia_Conducto;
	}

	/**
	 * @param fecha_Vigencia_Conducto
	 *            the fecha_Vigencia_Conducto to set
	 */
	public void setFecha_Vigencia_Conducto(Integer fecha_Vigencia_Conducto) {
		Fecha_Vigencia_Conducto = fecha_Vigencia_Conducto;
	}

	/**
	 * @return the nro_Cupones_Pendientes
	 */
	public Integer getNro_Cupones_Pendientes() {
		return Nro_Cupones_Pendientes;
	}

	/**
	 * @param nro_Cupones_Pendientes
	 *            the nro_Cupones_Pendientes to set
	 */
	public void setNro_Cupones_Pendientes(Integer nro_Cupones_Pendientes) {
		Nro_Cupones_Pendientes = nro_Cupones_Pendientes;
	}

	/**
	 * @return the fecha_inicio_vigencia
	 */
	public String getFecha_inicio_vigencia() {
		return Fecha_inicio_vigencia;
	}

	/**
	 * @param fecha_inicio_vigencia the fecha_inicio_vigencia to set
	 */
	public void setFecha_inicio_vigencia(String fecha_inicio_vigencia) {
		Fecha_inicio_vigencia = fecha_inicio_vigencia;
	}

	/**
	 * @return the fecha_A_Caducar
	 */
	public String getFecha_A_Caducar() {
		return Fecha_A_Caducar;
	}

	/**
	 * @param fecha_A_Caducar the fecha_A_Caducar to set
	 */
	public void setFecha_A_Caducar(String fecha_A_Caducar) {
		Fecha_A_Caducar = fecha_A_Caducar;
	}

	/**
	 * @return the forma_Pago
	 */
	public String getForma_Pago() {
		return Forma_Pago;
	}

	/**
	 * @param forma_Pago the forma_Pago to set
	 */
	public void setForma_Pago(String forma_Pago) {
		Forma_Pago = forma_Pago;
	}

	/**
	 * @return the nro_Conducto
	 */
	public String getNro_Conducto() {
		return Nro_Conducto;
	}

	/**
	 * @param nro_Conducto the nro_Conducto to set
	 */
	public void setNro_Conducto(String nro_Conducto) {
		Nro_Conducto = nro_Conducto;
	}

	/**
	 * @return the periodo_Gracia
	 */
	public String getPeriodo_Gracia() {
		return Periodo_Gracia;
	}

	/**
	 * @param periodo_Gracia the periodo_Gracia to set
	 */
	public void setPeriodo_Gracia(String periodo_Gracia) {
		Periodo_Gracia = periodo_Gracia;
	}

	/**
	 * @return the tiene_deuda
	 */
	public String getTiene_deuda() {
		return Tiene_deuda;
	}

	/**
	 * @param tiene_deuda the tiene_deuda to set
	 */
	public void setTiene_deuda(String tiene_deuda) {
		Tiene_deuda = tiene_deuda;
	}

	/**
	 * @return the numero_Poliza
	 */
	public BigDecimal getNumero_Poliza() {
		return Numero_Poliza;
	}

	/**
	 * @param numero_Poliza the numero_Poliza to set
	 */
	public void setNumero_Poliza(BigDecimal numero_Poliza) {
		Numero_Poliza = numero_Poliza;
	}

	
}
