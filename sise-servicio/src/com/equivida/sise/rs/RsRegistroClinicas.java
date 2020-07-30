package com.equivida.sise.rs;

import java.io.Serializable;

public class RsRegistroClinicas implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String message;
	private String grabo;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the grabo
	 */
	public String getGrabo() {
		return grabo;
	}

	/**
	 * @param grabo the grabo to set
	 */
	public void setGrabo(String grabo) {
		this.grabo = grabo;
	}

}