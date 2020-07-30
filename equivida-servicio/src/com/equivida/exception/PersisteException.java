package com.equivida.exception;

/**
 * @author Daniel Cardenas
 * 
 */
public class PersisteException extends Exception {

	private static final long serialVersionUID = 5585423765836127364L;

	public PersisteException() {
	}

	public PersisteException(String message) {
		super(message);
	}

	public PersisteException(Throwable cause) {
		super(cause);
	}

	public PersisteException(String message, Throwable cause) {
		super(message, cause);
	}

}
