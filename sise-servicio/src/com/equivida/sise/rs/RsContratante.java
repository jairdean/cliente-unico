/**
 * 
 */
package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author saviasoft5
 * 
 */
public class RsContratante implements Serializable {

	private static final long serialVersionUID = 3380157958345900616L;

	private BigDecimal id_persona_wkf;
	private String txt_id_persona_wkf;
	private BigDecimal id_proceso_wkf;
	private String message_wkf;
	private int cod_error;

	/**
	 * @return the id_persona_wkf
	 */
	public BigDecimal getId_persona_wkf() {
		return id_persona_wkf;
	}

	/**
	 * @param id_persona_wkf the id_persona_wkf to set
	 */
	public void setId_persona_wkf(BigDecimal id_persona_wkf) {
		this.id_persona_wkf = id_persona_wkf;
	}

	/**
	 * @return the txt_id_persona_wkf
	 */
	public String getTxt_id_persona_wkf() {
		return txt_id_persona_wkf;
	}

	/**
	 * @param txt_id_persona_wkf the txt_id_persona_wkf to set
	 */
	public void setTxt_id_persona_wkf(String txt_id_persona_wkf) {
		this.txt_id_persona_wkf = txt_id_persona_wkf;
	}

	/**
	 * @return the id_proceso_wkf
	 */
	public BigDecimal getId_proceso_wkf() {
		return id_proceso_wkf;
	}

	/**
	 * @param id_proceso_wkf the id_proceso_wkf to set
	 */
	public void setId_proceso_wkf(BigDecimal id_proceso_wkf) {
		this.id_proceso_wkf = id_proceso_wkf;
	}

	/**
	 * @return the message_wkf
	 */
	public String getMessage_wkf() {
		return message_wkf;
	}

	/**
	 * @param message_wkf the message_wkf to set
	 */
	public void setMessage_wkf(String message_wkf) {
		this.message_wkf = message_wkf;
	}

	/**
	 * @return the cod_error
	 */
	public int getCod_error() {
		return cod_error;
	}

	/**
	 * @param cod_error the cod_error to set
	 */
	public void setCod_error(int cod_error) {
		this.cod_error = cod_error;
	}

}
