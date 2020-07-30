package com.equivida.exception;

import javax.ejb.ApplicationException;

/**
 * @author Juan Ochoa
 * 
 */
@ApplicationException(rollback = true)
@SuppressWarnings(value = "serial")
public class RazonSocialException extends Exception {



	public RazonSocialException() {
	}

	public RazonSocialException(String message) {
		super(message);
	}


	public RazonSocialException(Throwable cause) {
		super(cause);
	}

	public RazonSocialException(String message, Throwable cause) {
		super(message, cause);
	}

	

}
