/**
 * 
 */
package com.equivida.constant;

/**
 * @author daniel
 *
 */
public enum EsClienteEnum {

	SI('S'), NO('N');
	
	private char codigo;

	private EsClienteEnum(char codigo) {
		this.codigo = codigo;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}
	
	
	
}
