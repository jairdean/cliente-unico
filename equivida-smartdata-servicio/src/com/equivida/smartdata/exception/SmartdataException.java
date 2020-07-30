/* 
 * SmartdataException.java
 */
package com.equivida.smartdata.exception;

import javax.ejb.ApplicationException;

/**
 * @author Juan Ochoa
 *
 */
@ApplicationException(rollback = true)
public class SmartdataException extends Exception {

	private static final long serialVersionUID = -4268542600936139033L;

	/**
	 * 
	 */
	public SmartdataException() {
	}

	/**
	 * @param arg0
	 */
	public SmartdataException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public SmartdataException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SmartdataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}