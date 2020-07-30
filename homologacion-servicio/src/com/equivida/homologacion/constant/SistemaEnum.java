package com.equivida.homologacion.constant;

public enum SistemaEnum {

	SISE((short) 1);

	private SistemaEnum(short codigo) {
		this.codigo = codigo;
	}

	private short codigo;

	public short getCodigo() {
		return codigo;
	}

	public void setCodigo(short codigo) {
		this.codigo = codigo;
	}
}
