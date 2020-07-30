/**
 * 
 */
package com.equivida.databook.exception;

/**
 * @author juan
 *
 */
public class DatabookException extends Exception {
	private static final long serialVersionUID = 3695358722984648771L;

	/**
	 * 
	 */
	public DatabookException() {
	}

	/**
	 * @param arg0
	 */
	public DatabookException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public DatabookException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DatabookException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
