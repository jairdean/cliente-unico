package com.equivida.smartdata.constante;

public enum GeneroEnum {
	MASCULINO('M', new Byte("1")), FEMENINO('F', new Byte("2"));

	private char codigoSexoSD;
	private byte codGeneroDB;

	GeneroEnum(char codigoSexoSD, byte codGeneroDB) {
		this.codigoSexoSD = codigoSexoSD;
		this.codGeneroDB = codGeneroDB;
	}

	public static char getCodigoSexoSMPorCodigoGeneroDB(byte codigoDB) {
		char resp = 'F';

		for (GeneroEnum g : GeneroEnum.values()) {
			if (g.getCodGeneroDB() == codigoDB) {
				resp = g.getCodigoSexoSD();
			}
		}

		return resp;
	}

	public char getCodigoSexoSD() {
		return codigoSexoSD;
	}

	public byte getCodGeneroDB() {
		return codGeneroDB;
	}

}
