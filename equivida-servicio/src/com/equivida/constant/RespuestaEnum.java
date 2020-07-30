package com.equivida.constant;

/**
 * @author Daniel Cardenas
 * 
 */
public enum RespuestaEnum {

	SI('S'), NO('N');

	private char codigo;

	private RespuestaEnum(char codigo) {
		this.codigo = codigo;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}
}
