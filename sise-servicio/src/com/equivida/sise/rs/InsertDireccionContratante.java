/**
 * 
 */
package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author saviasoft5
 * 
 */
public class InsertDireccionContratante implements Serializable {

	private static final long serialVersionUID = 1773189449016019409L;

	private String message_wkf;

	private String cod_error;

	private BigDecimal id_mpersona_dir_out;

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
	public BigDecimal getId_mpersona_dir_out() {
		return id_mpersona_dir_out;
	}

	public void setId_mpersona_dir_out(BigDecimal id_mpersona_dir_out) {
		this.id_mpersona_dir_out = id_mpersona_dir_out;
	}
}