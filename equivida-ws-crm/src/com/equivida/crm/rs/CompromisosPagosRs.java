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
public class CompromisosPagosRs implements Serializable {

	private static final long serialVersionUID = -3549457935371005804L;

	String poliza;
	String fechaPago;
	BigDecimal monto;
	/**
	 * @return the poliza
	 */
	public String getPoliza() {
		return poliza;
	}
	/**
	 * @param poliza the poliza to set
	 */
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}
	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
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
	
	
}
