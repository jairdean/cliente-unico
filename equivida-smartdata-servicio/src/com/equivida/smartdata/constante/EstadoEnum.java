package com.equivida.smartdata.constante;

public enum EstadoEnum {
	A('A'), I('I');

	private char estadoChar;

	EstadoEnum(char estadoChar) {
		this.estadoChar = estadoChar;
	}

	public char getEstadoChar() {
		return estadoChar;
	}

}
