package com.equivida.constant;

public enum TipoIdentificacionEnum {

	CEDULA('C'), RUC('R'), PASAPORTE('P'), MATRICULA('M'), OTRO('O');

	private char codigo;

	private TipoIdentificacionEnum(char codigo) {
		this.codigo = codigo;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}

	public static boolean isParaIngresoPersonaNatural(char codigoTipo) {
		TipoIdentificacionEnum[] valores = new TipoIdentificacionEnum[] { CEDULA, PASAPORTE };

		boolean encontrado = false;
		for (TipoIdentificacionEnum tipoIdentificacionEnum : valores) {
			if (tipoIdentificacionEnum.getCodigo() == codigoTipo) {
				encontrado = true;
				break;
			}
		}

		return encontrado;
	}

	public static boolean isParaIngresoBeneficiario(char codigoTipo) {
		TipoIdentificacionEnum[] valores = new TipoIdentificacionEnum[] { CEDULA, RUC };

		boolean encontrado = false;
		for (TipoIdentificacionEnum tipoIdentificacionEnum : valores) {
			if (tipoIdentificacionEnum.getCodigo() == codigoTipo) {
				encontrado = true;
				break;
			}
		}

		return encontrado;
	}

	public static boolean isParaRepresentateLegal(char codigoTipo) {
		TipoIdentificacionEnum[] valores = new TipoIdentificacionEnum[] { CEDULA, PASAPORTE, RUC };

		boolean encontrado = false;
		for (TipoIdentificacionEnum tipoIdentificacionEnum : valores) {
			if (tipoIdentificacionEnum.getCodigo() == codigoTipo) {
				encontrado = true;
				break;
			}
		}

		return encontrado;
	}
	
	public static TipoIdentificacionEnum obtenerPorCodigo(char codigoTipo) {
		TipoIdentificacionEnum[] valores = values();

		for (TipoIdentificacionEnum tipoIdentificacionEnum : valores) {
			if (tipoIdentificacionEnum.getCodigo() == codigoTipo) {
				return tipoIdentificacionEnum;
			}
		}

		return null;
	}

}