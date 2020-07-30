/* 
 * EmpleoIndependienteException.java
 * Oct 19, 2011 
 * Copyright 2011 Saviasoft Cia. Ltda. 
 */
package com.equivida.exception;

/**
 * @author saviasoft4
 * 
 */
public class EmpleoIndependienteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2391631479476548736L;

	/**
	 * 
	 */
	public EmpleoIndependienteException() {
	}

	/**
	 * @param message
	 */
	public EmpleoIndependienteException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public EmpleoIndependienteException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EmpleoIndependienteException(String message, Throwable cause) {
		super(message, cause);
	}

}
