package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsComisionNoPagada implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String poliza;
	private BigDecimal numFactura;
	private String agenteDesc;
	private String fechaCobroPago;
	private BigDecimal impPrima;
	private BigDecimal pjeComision;
	private Integer idPv;
	private BigDecimal impPrimaTotal;
	private BigDecimal impComisNormal;
	private BigDecimal debCred;
	private BigDecimal impComisExtra;
	private String campo1;
	private String campo2;
	private String campo3;

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public BigDecimal getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(BigDecimal numFactura) {
		this.numFactura = numFactura;
	}

	public String getAgenteDesc() {
		return agenteDesc;
	}

	public void setAgenteDesc(String agenteDesc) {
		this.agenteDesc = agenteDesc;
	}

	public String getFechaCobroPago() {
		return fechaCobroPago;
	}

	public void setFechaCobroPago(String fechaCobroPago) {
		this.fechaCobroPago = fechaCobroPago;
	}

	public BigDecimal getImpPrima() {
		return impPrima;
	}

	public void setImpPrima(BigDecimal impPrima) {
		this.impPrima = impPrima;
	}

	public BigDecimal getPjeComision() {
		return pjeComision;
	}

	public void setPjeComision(BigDecimal pjeComision) {
		this.pjeComision = pjeComision;
	}

	public Integer getIdPv() {
		return idPv;
	}

	public void setIdPv(Integer idPv) {
		this.idPv = idPv;
	}

	public BigDecimal getImpPrimaTotal() {
		return impPrimaTotal;
	}

	public void setImpPrimaTotal(BigDecimal impPrimaTotal) {
		this.impPrimaTotal = impPrimaTotal;
	}

	public BigDecimal getImpComisNormal() {
		return impComisNormal;
	}

	public void setImpComisNormal(BigDecimal impComisNormal) {
		this.impComisNormal = impComisNormal;
	}

	public BigDecimal getDebCred() {
		return debCred;
	}

	public void setDebCred(BigDecimal debCred) {
		this.debCred = debCred;
	}

	public BigDecimal getImpComisExtra() {
		return impComisExtra;
	}

	public void setImpComisExtra(BigDecimal impComisExtra) {
		this.impComisExtra = impComisExtra;
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

}