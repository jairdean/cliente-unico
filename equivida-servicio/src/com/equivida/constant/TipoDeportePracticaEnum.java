package com.equivida.constant;

/**
 * @author Daniel Cardenas
 * 
 */
public enum TipoDeportePracticaEnum {

	AFICIONADO('A', "aficionado"), PROFESIONAL('P', "profesional");

	private char codigo;

	private String keyBundle;

	private TipoDeportePracticaEnum(char codigo, String keyBundle) {
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
