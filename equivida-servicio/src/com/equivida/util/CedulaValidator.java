package com.equivida.util;

/*
 * CedulaValidator.java - Valida la c?dula de ciudadan?a de Ecuador 
 * 
 * Copyleft 2009 Daniel C?rdenas - daniel.cardenas@saviasoft.com
 * 
 * Este programa es Software Libre; usted puede redistribuirlo y/o modificarlo
 * bajo los t?rminos de la "GNU General Public License" como lo publica la "FSF
 * Free Software Foundation", o (a su elecci?n) de cualquier versi?n posterior.
 * 
 * Este programa es distribuido con la esperanza de que le ser? ?til, pero SIN
 * NINGUNA GARANTIA; incluso sin la garant?a impl?cita por el MERCADEO o
 * EJERCICIO DE ALGUN PROPOSITO en particular. Vea la
 * "GNU General Public License" para m?s detalles.
 * 
 * Usted debe haber recibido una copia de la "GNU General Public
 * License" junto con este programa, si no, escriba a la "FSF Free Software
 * Foundation, Inc.", 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * 
 * Ejemplo de utilizaci?n:
 * 
 * boolean valid = CedulaValidator.validate("1713470111");
 * 
 */
public class CedulaValidator {

	static final int MULT = 2;
	static final int TOTAL_PROVINCES = 24;

	/**
	 * Valida el n?mero de c?dula de Ecuador
	 * 
	 * @param identificationNumber
	 *            Solo d?gitos, sin guiones.
	 * 
	 * @return
	 */
	public static boolean validate(String identificationNumber) {

		boolean valid = false;

		// si no tiene 10 d?gitos es inv?lida
		if (identificationNumber.length() != 10) {
			return valid;
		}

		String province = identificationNumber.substring(0, 2);

		try {

			// si sus dos primeros d?gitos son inv?lidos
			if (Integer.parseInt(province) > TOTAL_PROVINCES) {
				return valid;
			}

			int totalEven = 0; // pares
			int totalOdd = 0; // impares

			// la ?ltima posici?n no cuenta solo es verificador
			int totalValidNumbers = identificationNumber.length() - 1;
			int verifier = Integer
					.parseInt(identificationNumber.charAt(9) + "");

			for (int i = 0; i < totalValidNumbers; i++) {
				int digit = Integer.parseInt(identificationNumber.charAt(i)
						+ "");
				if (i % 2 == 0) {// si son impares
					int product = digit * MULT;
					if (product > 9) {
						product = product - 9;
					}
					totalEven += product;
				} else { // si son pares
					totalOdd += digit;
				}
			}

			int total = totalOdd + totalEven;

			String totalString = String.valueOf(total + 10);

			// se verifica la decena superior
			if (totalString.length() > 1) {

				int first = Integer.parseInt(totalString.charAt(0) + "");
				total = Integer.parseInt(first + "0") - total;
				if (total == 10) {
					total = 0;
				}

			}

			int result = total;

			// si el n?mero verificador es igual al resultado del algoritmo
			// entonces es una c?dula v?lida

			if (result == verifier) {
				valid = true;
			}

			return valid;

		} catch (NumberFormatException e) {
			System.out.println("Error en cedula..." + identificationNumber);
			valid = false;
			return valid;
		}

	}
}
