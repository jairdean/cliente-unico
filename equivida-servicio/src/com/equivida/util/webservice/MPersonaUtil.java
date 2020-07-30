package com.equivida.util.webservice;

import com.equivida.constant.EstadoCivilEnum;
import com.equivida.constant.TipoIdentificacionEnum;

/**
 * Clase util para consumir el servicio web MPERSONA
 * 
 * @author Daniel Cardenas
 * 
 */
public class MPersonaUtil {

	public static int obtenerTipoIdentificacion(char tipoIdentificacion) {
		int tipo = 0;
		if (tipoIdentificacion == TipoIdentificacionEnum.CEDULA.getCodigo()) {
			tipo = 1;
		} else if (tipoIdentificacion == TipoIdentificacionEnum.RUC.getCodigo()) {
			tipo = 2;
		} else if (tipoIdentificacion == TipoIdentificacionEnum.PASAPORTE
				.getCodigo()) {
			tipo = 3;
		} else if (tipoIdentificacion == TipoIdentificacionEnum.MATRICULA
				.getCodigo()) {
			tipo = 5;
		} else {
			tipo = 10;
		}
		return tipo;
	}

	public static int obtenerEstadoCivil(Short estadoCivil) {
		int estado = 0;
		if (estadoCivil.intValue() == EstadoCivilEnum.SOLTERO.getCodigo()) {
			estado = 1;
		} else if (estadoCivil.intValue() == EstadoCivilEnum.CASADO.getCodigo()) {
			estado = 2;
		} else if (estadoCivil.intValue() == EstadoCivilEnum.DIVORCIADO
				.getCodigo()) {
			estado = 4;
		} else if (estadoCivil.intValue() == EstadoCivilEnum.VIUDO.getCodigo()) {
			estado = 5;
		} else {
			estado = 1;// se envia soltero si no se ingresa
		}
		return estado;
	}
}
