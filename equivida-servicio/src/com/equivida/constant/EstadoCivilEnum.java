package com.equivida.constant;

public enum EstadoCivilEnum {

	SOLTERO(Integer.valueOf(1).shortValue()), CASADO(Integer.valueOf(2)
			.shortValue()), DIVORCIADO(Integer.valueOf(3).shortValue()), VIUDO(
			Integer.valueOf(4).shortValue()), UNION_LIBRE(Integer.valueOf(5)
			.shortValue()), NO_DISPONIBLE(Integer.valueOf(0).shortValue());

	private short codigo;

	private EstadoCivilEnum(short codigo) {

		this.codigo = codigo;
	}

	public short getCodigo() {
		return codigo;
	}

	public void setCodigo(short codigo) {
		this.codigo = codigo;
	}

	public static EstadoCivilEnum[] getEstadoConConyuge() {
		return new EstadoCivilEnum[] { CASADO, UNION_LIBRE };
	}
}
