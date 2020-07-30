package com.equivida.exception;

import javax.ejb.ApplicationException;

/**
 * @author Juan Ochoa
 * 
 */
@ApplicationException(rollback = true)
@SuppressWarnings(value = "serial")
public class ContratanteException extends Exception {

	private String keyLabel;
	private Object params[];
	private boolean buscarKey = false;

	public ContratanteException() {
	}

	public ContratanteException(String message) {
		super(message);
	}

	public ContratanteException(String keyLabel, Object params[], boolean buscarKey) {
		this.keyLabel = keyLabel;
		this.params = params;
		this.buscarKey = buscarKey;
	}

	public ContratanteException(Throwable cause) {
		super(cause);
	}

	public ContratanteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @return the keyLabel
	 */
	public String getKeyLabel() {
		return keyLabel;
	}

	/**
	 * @param keyLabel the keyLabel to set
	 */
	public void setKeyLabel(String keyLabel) {
		this.keyLabel = keyLabel;
	}

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}

	/**
	 * @return the buscarKey
	 */
	public boolean isBuscarKey() {
		return buscarKey;
	}

	/**
	 * @param buscarKey the buscarKey to set
	 */
	public void setBuscarKey(boolean buscarKey) {
		this.buscarKey = buscarKey;
	}

}
