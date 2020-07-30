/**
 * 
 */
package com.equivida.buenviaje.util;

import java.util.Comparator;

import com.equivida.buenviaje.dto.PersonaConsulta;
import com.equivida.modelo.PersonaNatural;

/**
 * @author juan
 *
 */
public class Comparador implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
		int resp = 0;

		PersonaConsulta pc = (PersonaConsulta) arg0;
		PersonaNatural pn = (PersonaNatural) arg1;

		boolean datosComunesIguales = isDatosComunesIguales(pc, pn);

		if (!datosComunesIguales) {
			resp = 1;
		}

		return resp;
	}

	/**
	 * Compara datos comunes de los 3 tipos de personas Solicitante, Asegurado y
	 * Facturado.
	 * 
	 * @param pc
	 * @param pn
	 * @return
	 */
	private boolean isDatosComunesIguales(PersonaConsulta pc, PersonaNatural pn) {
		boolean iguales = true;

		if (pn != null) {

			if (!isStringEquals(pc.getTipoDocumento().toString(), pn
					.getTipoIdentificacion().getCodTipoIdentificacion()
					.toString())) {
				iguales = false;
			}

			if (!isStringEquals(pc.getNoDocumento(), pn.getIdentificacion())) {
				iguales = false;
			}

			if (!isStringEquals(pc.getPrimerApellido(), pn.getApellidoPaterno())) {
				iguales = false;
			}

			if (!isStringEquals(pc.getPrimerNombre(), pn.getPrimerNombre())) {
				iguales = false;
			}
		}
		return iguales;
	}

	/**
	 * Compara 2 Strings.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private boolean isStringEquals(String s1, String s2) {
		return s1.trim().toUpperCase().equals(s2.trim().toUpperCase());
	}
}
