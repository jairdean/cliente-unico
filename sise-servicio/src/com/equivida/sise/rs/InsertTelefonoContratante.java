/**
 * 
 */
package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Daniel Cardenas
 * 
 */
public class InsertTelefonoContratante implements Serializable {

	private static final long serialVersionUID = 5114294693773845751L;

	private String message_wkf;

	private String cod_error;

	private BigDecimal id_mper_telef_cod_out;

	@XmlElement(nillable = true)
	public String getMessage_wkf() {
		return message_wkf;
	}

	public void setMessage_wkf(String message_wkf) {
		this.message_wkf = message_wkf;
	}

	@XmlElement(nillable = true)
	public String getCod_error() {
		return cod_error;
	}

	public void setCod_error(String cod_error) {
		this.cod_error = cod_error;
	}

	@XmlElement(nillable = true)
	public BigDecimal getId_mper_telef_cod_out() {
		return id_mper_telef_cod_out;
	}

	public void setId_mper_telef_cod_out(BigDecimal id_mper_telef_cod_out) {
		this.id_mper_telef_cod_out = id_mper_telef_cod_out;
	}
}
