package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsCumulosDePago implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String poliza;

	private String estado;

	private BigDecimal montoVida;

	private BigDecimal montoMyda;

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getMontoVida() {
		return montoVida;
	}

	public void setMontoVida(BigDecimal montoVida) {
		this.montoVida = montoVida;
	}

	public BigDecimal getMontoMyda() {
		return montoMyda;
	}

	public void setMontoMyda(BigDecimal montoMyda) {
		this.montoMyda = montoMyda;
	}
}