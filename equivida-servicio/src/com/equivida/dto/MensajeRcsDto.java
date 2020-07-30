/* 
 * MensajeRcsDto.java
 * Abr 19, 2016 
 * Copyright 2011 Saviasoft Cia. Ltda. 
 */
package com.equivida.dto;

import com.equivida.modelo.Rcs;

/**
 * @author Daniel Cardenas
 *
 */
public class MensajeRcsDto {

	private boolean puedeContinuar = true;
	private String mensajeInterfaz;
	private Rcs rcs;// para servicio web

	public MensajeRcsDto() {
	}

	public boolean isPuedeContinuar() {
		return puedeContinuar;
	}

	public void setPuedeContinuar(boolean puedeContinuar) {
		this.puedeContinuar = puedeContinuar;
	}

	public String getMensajeInterfaz() {
		return mensajeInterfaz;
	}

	public void setMensajeInterfaz(String mensajeInterfaz) {
		this.mensajeInterfaz = mensajeInterfaz;
	}

	public Rcs getRcs() {
		return rcs;
	}

	public void setRcs(Rcs rcs) {
		this.rcs = rcs;
	}
}
