package com.equivida.dto.portal;

import java.io.Serializable;

public class RsRespuestaPregunta implements Serializable {

	private static final long serialVersionUID = 4770830353897705343L;

	private String respuesta;

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
