/**
 * 
 */
package com.equivida.constant;

/**
 * @author daniel
 * 
 */
public enum AntiguedadEnum {

	ANIOS('A', "anios"), MESES('M', "meses");

	private char codigo;
	private String keyBundle;

	private AntiguedadEnum(char codigo, String keyBundle) {
		this.codigo = codigo;
		this.keyBundle = keyBundle;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}

	public String getKeyBundle() {
		return keyBundle;
	}

	public void setKeyBundle(String keyBundle) {
		this.keyBundle = keyBundle;
	}

}
