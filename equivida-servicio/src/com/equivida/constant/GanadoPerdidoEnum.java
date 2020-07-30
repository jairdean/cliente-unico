package com.equivida.constant;

public enum GanadoPerdidoEnum {

	GANADO('G'), PERDIDO('P');

	private char codigo;

	private GanadoPerdidoEnum(char codigo) {
		this.codigo = codigo;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}

}
