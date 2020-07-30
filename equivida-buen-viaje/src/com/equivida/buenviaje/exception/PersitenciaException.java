/* 
 * RequeridoException.java
 */
package com.equivida.buenviaje.exception;

import javax.ejb.ApplicationException;

/**
 * @author Juan Ochoa
 *
 */
@ApplicationException(rollback = true)
public class PersitenciaException extends Exception {
	private static final long serialVersionUID = -8281223964539852039L;

	/**
	 * 
	 */
	public PersitenciaException() {
	}

	/**
	 * @param arg0
	 */
	public PersitenciaException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public PersitenciaException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PersitenciaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}