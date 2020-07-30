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
public class RequeridoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2084267998221170309L;

	/**
	 * 
	 */
	public RequeridoException() {
	}

	/**
	 * @param arg0
	 */
	public RequeridoException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public RequeridoException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public RequeridoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}