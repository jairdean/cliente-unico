/**
 * 
 */
package com.equivida.buenviaje.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author juan
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personaRespuesta")
public class PersonaRespuesta implements Serializable {
	private static final long serialVersionUID = -3637350990118508369L;

	@XmlElement(name = "idPersona")
	private Long idPersona;
	@XmlElement(name = "codigoAsegurado")
	private String codigoAsegurado;
	@XmlElement(name = "personaConsulta")
	private String personaConsulta;

	public PersonaRespuesta() {
	}

	public PersonaRespuesta(Long idPersona, String codigoAsegurado,
			String personaConsulta) {
		this.idPersona = idPersona;
		this.codigoAsegurado = codigoAsegurado;
		this.personaConsulta = personaConsulta;
	}

	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona
	 *            the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return the personaConsulta
	 */
	public String getPersonaConsulta() {
		return personaConsulta;
	}

	/**
	 * @param personaConsulta
	 *            the personaConsulta to set
	 */
	public void setPersonaConsulta(String personaConsulta) {
		this.personaConsulta = personaConsulta;
	}

	/**
	 * @return the codigoAsegurado
	 */
	public String getCodigoAsegurado() {
		return codigoAsegurado;
	}

	/**
	 * @param codigoAsegurado
	 *            the codigoAsegurado to set
	 */
	public void setCodigoAsegurado(String codigoAsegurado) {
		this.codigoAsegurado = codigoAsegurado;
	}

}
