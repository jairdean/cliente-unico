package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsComisionPagada implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String comprobante;
	private BigDecimal nroComprobante;
	private BigDecimal codComprobante;
	private BigDecimal valor;
	private String fecha;
	private String nombreBanco;
	private String cuentaOCheque;
	private String cuentaCorrienta;
	private Integer codigosucursal;
	private String sucursal;
	private String mail;
	private String campo1;
	private String campo2;
	private String campo3;
	/**
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}
	/**
	 * @param comprobante the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	/**
	 * @return the nroComprobante
	 */
	public BigDecimal getNroComprobante() {
		return nroComprobante;
	}
	/**
	 * @param nroComprobante the nroComprobante to set
	 */
	public void setNroComprobante(BigDecimal nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the nombreBanco
	 */
	public String getNombreBanco() {
		return nombreBanco;
	}
	/**
	 * @param nombreBanco the nombreBanco to set
	 */
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	/**
	 * @return the cuentaOCheque
	 */
	public String getCuentaOCheque() {
		return cuentaOCheque;
	}
	/**
	 * @param cuentaOCheque the cuentaOCheque to set
	 */
	public void setCuentaOCheque(String cuentaOCheque) {
		this.cuentaOCheque = cuentaOCheque;
	}
	/**
	 * @return the cuentaCorrienta
	 */
	public String getCuentaCorrienta() {
		return cuentaCorrienta;
	}
	/**
	 * @param cuentaCorrienta the cuentaCorrienta to set
	 */
	public void setCuentaCorrienta(String cuentaCorrienta) {
		this.cuentaCorrienta = cuentaCorrienta;
	}
	/**
	 * @return the codigosucursal
	 */
	public Integer getCodigosucursal() {
		return codigosucursal;
	}
	/**
	 * @param codigosucursal the codigosucursal to set
	 */
	public void setCodigosucursal(Integer codigosucursal) {
		this.codigosucursal = codigosucursal;
	}
	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}
	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the campo1
	 */
	public String getCampo1() {
		return campo1;
	}
	/**
	 * @param campo1 the campo1 to set
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
	 * @param campo2 the campo2 to set
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
	 * @param campo3 the campo3 to set
	 */
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}
	/**
	 * @return the codComprobante
	 */
	public BigDecimal getCodComprobante() {
		return codComprobante;
	}
	/**
	 * @param codComprobante the codComprobante to set
	 */
	public void setCodComprobante(BigDecimal codComprobante) {
		this.codComprobante = codComprobante;
	}

}