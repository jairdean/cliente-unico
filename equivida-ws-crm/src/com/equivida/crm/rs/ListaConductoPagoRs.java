/**
 * 
 */
package com.equivida.crm.rs;

import java.io.Serializable;

/**
 * @author saviasoft5
 * 
 */
public class ListaConductoPagoRs implements Serializable {

	private static final long serialVersionUID = -3549457935371005804L;

	Integer secuencial_persona;
	Integer tipo;
	String numeroDocumento;
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
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
