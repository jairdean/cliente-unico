package com.equivida.util;

import java.util.Collection;

/**
 * Clase utilitaria para manejar cadenas de texto
 * 
 * @author Daniel Cardenas
 *
 */
public class StringUtil {

	public static String toUpper(String cadenaCambiar) {

		String cambiado = cadenaCambiar;

		if (cambiado != null) {
			cambiado = cambiado.toUpperCase();
		}

		return cambiado;
	}

	/**
	 * Arma la cadena para los sql IN
	 * 
	 * @param lista
	 * @return
	 */
	public static String armarInList(Collection<String> lista) {
		StringBuilder r = new StringBuilder("");
		for (String s : lista) {
			r.append("'" + s + "',");
		}
		if (r.toString().endsWith(",")) {
			r.deleteCharAt((r.length() - 1));
		}

		return r.toString();
	}

	/**
	 * Arma la cadena para los sql IN
	 * 
	 * @param array
	 * @return
	 */
	public static String armarInList(String[] array) {
		StringBuilder r = new StringBuilder("");
		for (String s : array) {
			r.append("'" + s + "',");
		}
		if (r.toString().endsWith(",")) {
			r.deleteCharAt((r.length() - 1));
		}

		return r.toString();
	}

	/**
	 * Arma la cadena para los sql IN
	 * 
	 * @param array
	 * @return
	 */
	public static String armarInList(short[] array) {
		StringBuilder r = new StringBuilder("");
		for (short s : array) {
			r.append(+s + ",");
		}
		if (r.toString().endsWith(",")) {
			r.deleteCharAt((r.length() - 1));
		}

		return r.toString();
	}

}
