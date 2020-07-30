/* 
 * EmpleoDependienteException.java
 * Oct 19, 2011 
 * Copyright 2011 Saviasoft Cia. Ltda. 
 */
package com.equivida.exception;

/**
 * @author Daniel Cardenas
 *
 */
public class EmpleoDependienteException extends Exception {

	private static final long serialVersionUID = -4268542600936139033L;

	/**
	 * 
	 */
	public EmpleoDependienteException() {
	}

	/**
	 * @param arg0
	 */
	public EmpleoDependienteException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public EmpleoDependienteException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public EmpleoDependienteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}