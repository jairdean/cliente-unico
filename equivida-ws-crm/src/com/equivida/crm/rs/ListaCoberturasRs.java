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
public class ListaCoberturasRs implements Serializable {

	private static final long serialVersionUID = -3549457935371005804L;

	BigDecimal sucursal;
	Integer ramo;
	BigDecimal Numero_poliza;
	String cobertura;
	BigDecimal monto;
	String mensaje;
	
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
	 * @return the cobertura
	 */
	public String getCobertura() {
		return cobertura;
	}
	/**
	 * @param cobertura the cobertura to set
	 */
	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}
	/**
	 * @return the monto
	 */
	public BigDecimal getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
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
	/**
	 * @return the sucursal
	 */
	public BigDecimal getSucursal() {
		return sucursal;
	}
	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(BigDecimal sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @return the numero_poliza
	 */
	public BigDecimal getNumero_poliza() {
		return Numero_poliza;
	}
	/**
	 * @param numero_poliza the numero_poliza to set
	 */
	public void setNumero_poliza(BigDecimal numero_poliza) {
		Numero_poliza = numero_poliza;
	}

}
