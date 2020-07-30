package com.equivida.util.webservice;

/**
 * Clase util para consumir el servicio web MPERSONA
 * 
 * @author Daniel Cardenas
 * 
 */
public class MPersonaTelUtil {

	public static int obtenerTipoTelefono(Short codTipoTelefono, boolean principal) {

		int tipo = 1;

		if (principal) {
			tipo = 7;
			return tipo;
		}

		switch (codTipoTelefono) {
		case 1:
			tipo = 1;
			break;

		case 2:
			tipo = 2;
			break;

		case 3:// movil personal
			tipo = 3;// celular
			break;

		case 4:// movil oficina
			tipo = 3;// celular
			break;

		case 5:// fax oficina
			tipo = 2;
			break;

		case 6: // celular
			tipo = 3;// celular
			break;

		case 7:// fax
			tipo = 4;// alterno
			break;

		case 8:// fax
			tipo = 4;// alterno
			break;

		default:
			break;
		}
		return tipo;
	}
}
