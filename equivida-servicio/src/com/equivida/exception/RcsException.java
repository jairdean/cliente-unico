package com.equivida.exception;

import javax.ejb.ApplicationException;

import com.equivida.dto.MensajeRcsDto;

/**
 * @author Juan Ochoa
 * 
 */
@ApplicationException(rollback = true)
@SuppressWarnings(value = "serial")
public class RcsException extends Exception {

	private MensajeRcsDto mensajeRcs;

	public RcsException() {
	}

	public RcsException(String message) {
		super(message);
	}

	public RcsException(MensajeRcsDto mensajeRcs) {
		this.mensajeRcs = mensajeRcs;
	}

	public RcsException(Throwable cause) {
		super(cause);
	}

	public RcsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @return the mensajeRcs
	 */
	public MensajeRcsDto getMensajeRcs() {
		return mensajeRcs;
	}

	/**
	 * @param mensajeRcs the mensajeRcs to set
	 */
	public void setMensajeRcs(MensajeRcsDto mensajeRcs) {
		this.mensajeRcs = mensajeRcs;
	}

}
