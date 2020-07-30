package com.equivida.util;

import java.math.BigDecimal;

import com.equivida.constant.Constantes;

/**
 * @author Daniel Cardenas
 * 
 */
public class EquivalenciaKilosLibras {

	public static BigDecimal getKilos(int libras) {
		return BigDecimal
				.valueOf(libras / Constantes.EQUIVALENCIA_KILOS_LIBRAS).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static int getLibras(BigDecimal kilos) {
		int libras = (kilos.multiply(BigDecimal
				.valueOf(Constantes.EQUIVALENCIA_KILOS_LIBRAS))).intValue();
		return libras;
	}
}
