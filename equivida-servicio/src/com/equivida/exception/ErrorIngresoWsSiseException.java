package com.equivida.exception;

/**
 * Clase para controlar cuando no se ingresa persona en el ws de siseo
 * 
 * @author Daniel Cardenas
 * 
 */
public class ErrorIngresoWsSiseException extends Exception {

	private static final long serialVersionUID = 8582180374250777053L;

	public ErrorIngresoWsSiseException() {
		super();
	}

	public ErrorIngresoWsSiseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorIngresoWsSiseException(String message) {
		super(message);
	}

	public ErrorIngresoWsSiseException(Throwable cause) {
		super(cause);
	}
}
