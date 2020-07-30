package com.equivida.exception;

/**
 * @author Daniel Cardenas
 * 
 */
public class NoEncuentraDatosException extends Exception {

	private static final long serialVersionUID = 5585423765836127364L;

	public NoEncuentraDatosException() {
	}

	public NoEncuentraDatosException(String message) {
		super(message);
	}

	public NoEncuentraDatosException(Throwable cause) {
		super(cause);
	}

	public NoEncuentraDatosException(String message, Throwable cause) {
		super(message, cause);
	}
}
