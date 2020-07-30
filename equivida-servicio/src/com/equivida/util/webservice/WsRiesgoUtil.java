package com.equivida.util.webservice;

import com.equivida.constant.TipoIdentificacionEnum;

public class WsRiesgoUtil {

	/**
	 * Valores que se envian segun el documento del proveedor de este web
	 * service
	 * 
	 * @param tipoIdentificacion
	 * @return
	 */
	public static int obtenerTipoIdentificacion(char tipoIdentificacion) {

		int tipo = 0;
		if (tipoIdentificacion == TipoIdentificacionEnum.CEDULA.getCodigo()) {
			tipo = 1;
		} else if (tipoIdentificacion == TipoIdentificacionEnum.PASAPORTE
				.getCodigo()) {
			tipo = 3;
		} else {
			tipo = 2;
		}

		return tipo;
	}
}
