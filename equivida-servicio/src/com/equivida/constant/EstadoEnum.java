package com.equivida.constant;

public enum EstadoEnum {
	
	ACTIVO('A'), INACTIVO('I');
	
	private char codigo;

	private EstadoEnum(char codigo) {
		this.codigo = codigo;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}

}
