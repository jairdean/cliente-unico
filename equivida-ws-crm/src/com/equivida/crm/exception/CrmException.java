/*
 * @Copyright 2014. Saviasoft.
 * 
 */
package com.equivida.crm.exception;

/**
 * @author Daniel Cardenas
 */
public class CrmException extends Exception {

	private static final long serialVersionUID = -5965415042002632089L;

	public CrmException() {
		super();
	}

	public CrmException(String message, Throwable cause) {
		super(message, cause);
	}

	public CrmException(String message) {
		super(message);
	}

	public CrmException(Throwable cause) {
		super(cause);
	}
}
