package com.equivida.comparator;

import java.util.Comparator;

import com.equivida.modelo.Referencia;

/**
 * @author Daniel Cardenas
 * 
 */
public class ReferenciaComparator implements Comparator<Referencia> {

	@Override
	public int compare(Referencia r0, Referencia r1) {
		Integer r0i = r0.getSecReferencia();
		Integer r1i = r1.getSecReferencia();

		return (r0i.compareTo(r1i));// por menos uno para que S sea mayor a
											// N
	}

}
