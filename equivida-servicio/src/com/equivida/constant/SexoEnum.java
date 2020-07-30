package com.equivida.constant;

/**
 * @author Daniel Cardenas
 * 
 */
public enum SexoEnum {

	MASCULINO('M'), FEMENINO('F');

	private char codigo;

	private SexoEnum(char codigo) {
		this.codigo = codigo;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}
}
