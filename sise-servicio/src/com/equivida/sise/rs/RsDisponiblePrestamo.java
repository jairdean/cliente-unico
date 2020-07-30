package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsDisponiblePrestamo implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal NroCedula;
	private BigDecimal saldoPrestamo;
	private BigDecimal costoRescate;
	private BigDecimal valorRescate;
	private BigDecimal EDM;
	private BigDecimal pje_int;
	private BigDecimal impDisponiblePrest;
	private String campo1;
	private String campo2;
	private String campo3;
	private String msgError;

	public BigDecimal getNroCedula() {
		return NroCedula;
	}

	public void setNroCedula(BigDecimal nroCedula) {
		NroCedula = nroCedula;
	}

	public BigDecimal getSaldoPrestamo() {
		return saldoPrestamo;
	}

	public void setSaldoPrestamo(BigDecimal saldoPrestamo) {
		this.saldoPrestamo = saldoPrestamo;
	}

	public BigDecimal getCostoRescate() {
		return costoRescate;
	}

	public void setCostoRescate(BigDecimal costoRescate) {
		this.costoRescate = costoRescate;
	}

	public BigDecimal getValorRescate() {
		return valorRescate;
	}

	public void setValorRescate(BigDecimal valorRescate) {
		this.valorRescate = valorRescate;
	}

	public BigDecimal getEDM() {
		return EDM;
	}

	public void setEDM(BigDecimal eDM) {
		EDM = eDM;
	}

	public BigDecimal getPje_int() {
		return pje_int;
	}

	public void setPje_int(BigDecimal pje_int) {
		this.pje_int = pje_int;
	}

	public String getCampo1() {
		return campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo2() {
		return campo2;
	}

	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	public String getCampo3() {
		return campo3;
	}

	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	public BigDecimal getImpDisponiblePrest() {
		return impDisponiblePrest;
	}

	public void setImpDisponiblePrest(BigDecimal impDisponiblePrest) {
		this.impDisponiblePrest = impDisponiblePrest;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	
}