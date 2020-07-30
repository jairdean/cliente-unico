package com.equivida.util;

public class ValidatorUtils {

	/**
	 * Valida que tenga minimo 2 caracteres y sin simbolos
	 * 
	 * @param texto
	 * @return true si hay error
	 */
	public static boolean validaNombre(String texto) {

		if (texto.matches("[\u00D1A-Z]{2,}")) {

			return false;
		} else {
			return true;
		}

	}
	
	/**
	 * Valida que tenga minimo 2 caracteres y sin simbolos
	 * 
	 * @param texto
	 * @return true si hay error
	 */
	public static boolean validaSoloNumeros(String texto) {

		if (texto.matches("[0-9]+")) {

			return false;
		} else {
			return true;
		}

	}
	
	public static void main(String[] args) {
		System.out.println("Tiene Error:"+validaNombre("MUÑOZ"));
		System.out.println("Tiene Error:"+validaSoloNumeros("1"));
	}

}
