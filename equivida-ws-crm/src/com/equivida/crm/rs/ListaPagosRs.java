/**
 * 
 */
package com.equivida.crm.rs;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author saviasoft5
 * 
 */
public class ListaPagosRs implements Serializable {

	private static final long serialVersionUID = -3549457935371005804L;

	Integer secuencial_persona;
	Integer ramo;
	Integer sucursal;
	BigDecimal numero_poliza;
	String fecha_pago;
	BigDecimal valor_pago;
	Integer no_transaccion;
	Integer mes;
	String mensaje;
	/**
	 * @return the secuencial_persona
	 */
	public Integer getSecuencial_persona() {
		return secuencial_persona;
	}
	/**
	 * @param secuencial_persona the secuencial_persona to set
	 */
	public void setSecuencial_persona(Integer secuencial_persona) {
		this.secuencial_persona = secuencial_persona;
	}
	/**
	 * @return the ramo
	 */
	public Integer getRamo() {
		return ramo;
	}
	/**
	 * @param ramo the ramo to set
	 */
	public void setRamo(Integer ramo) {
		this.ramo = ramo;
	}
	/**
	 * @return the sucursal
	 */
	public Integer getSucursal() {
		return sucursal;
	}
	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @return the numero_poliza
	 */
	public BigDecimal getNumero_poliza() {
		return numero_poliza;
	}
	/**
	 * @param numero_poliza the numero_poliza to set
	 */
	public void setNumero_poliza(BigDecimal numero_poliza) {
		this.numero_poliza = numero_poliza;
	}
	/**
	 * @return the fecha_pago
	 */
	public String getFecha_pago() {
		return fecha_pago;
	}
	/**
	 * @param fecha_pago the fecha_pago to set
	 */
	public void setFecha_pago(String fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	/**
	 * @return the valor_pago
	 */
	public BigDecimal getValor_pago() {
		return valor_pago;
	}
	/**
	 * @param valor_pago the valor_pago to set
	 */
	public void setValor_pago(BigDecimal valor_pago) {
		this.valor_pago = valor_pago;
	}
	/**
	 * @return the no_transaccion
	 */
	public Integer getNo_transaccion() {
		return no_transaccion;
	}
	/**
	 * @param no_transaccion the no_transaccion to set
	 */
	public void setNo_transaccion(Integer no_transaccion) {
		this.no_transaccion = no_transaccion;
	}
	/**
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}
	/**
	 * @param mes the mes to set
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
