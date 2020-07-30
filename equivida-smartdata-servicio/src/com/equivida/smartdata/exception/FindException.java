/* 
 * FindException.java
 */
package com.equivida.smartdata.exception;

/**
 * @author Juan Ochoa
 *
 */
public class FindException extends Exception {
	private static final long serialVersionUID = -5373689121460739578L;

	/**
	 * 
	 */
	public FindException() {
	}

	/**
	 * @param arg0
	 */
	public FindException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public FindException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public FindException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}