package com.equivida.util.webservice;

/**
 * Clase util para consumir el servicio web MPERSONA
 * 
 * @author Daniel Cardenas
 * 
 */
public class MPersonaDirUtil {

	public static int COD_EMAIL_SISE = 6;
	public static int COD_EMAIL_SISE_2 = 10;

	public static int obtenerTipoDireccion(Short codTipoDireccion, boolean principal) {

		int tipo = 1;

		if (principal) {
			tipo = 7;
			return tipo;
		}

		switch (codTipoDireccion) {
		case 1:
			tipo = 1;
			break;

		case 2:
			tipo = 2;
			break;

		case 3:
			tipo = 2;
			break;

		case 4:
			tipo = 2;
			break;

		default:
			break;
		}
		return tipo;
	}
}
