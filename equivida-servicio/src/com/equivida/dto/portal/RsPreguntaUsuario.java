package com.equivida.dto.portal;

import java.io.Serializable;

public class RsPreguntaUsuario implements Serializable {

	private static final long serialVersionUID = 7491920907577958331L;

	private String mensajeError;
	private Integer codigoError;
	private Integer filas;

	/**
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * @param mensajeError
	 *            the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * @return the codigoError
	 */
	public Integer getCodigoError() {
		return codigoError;
	}

	/**
	 * @param codigoError
	 *            the codigoError to set
	 */
	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * @return the filas
	 */
	public Integer getFilas() {
		return filas;
	}

	/**
	 * @param filas
	 *            the filas to set
	 */
	public void setFilas(Integer filas) {
		this.filas = filas;
	}

}
