package com.equivida.buenviaje.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaOut", propOrder = { "error", "mensaje",
		"personaRespuestaLista" })
public class ConsultaOut implements Serializable {
	@XmlElement(name = "error")
	private String error;
	@XmlElement(name = "mensaje")
	private String mensaje;
	@XmlElement(name = "personaRespuesta")
	private List<PersonaRespuesta> personaRespuestaLista;

	/**
	 * @return the personaRespuestaLista
	 */
	public List<PersonaRespuesta> getPersonaRespuestaLista() {
		return personaRespuestaLista;
	}

	/**
	 * @param personaRespuestaLista
	 *            the personaRespuestaLista to set
	 */
	public void setPersonaRespuestaLista(
			List<PersonaRespuesta> personaRespuestaLista) {
		this.personaRespuestaLista = personaRespuestaLista;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
