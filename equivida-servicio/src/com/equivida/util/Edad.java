/* 
 * Edad.java
 * Oct 27, 2009 
 * Copyright 2009 Saviasoft Cia. Ltda. 
 */
package com.equivida.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Daniel Cardenas
 * 
 */
public class Edad implements Serializable {

	private static final long serialVersionUID = -2264171399926467429L;
	private int anios;
	private int meses;
	private int dias;
	private String edadTotal;

	public Edad() {
	}

	public Edad(Long fechaNacimientoMilis) {

		Calendar age = Calendar.getInstance(Locale.getDefault());

		age.setTimeInMillis(Math.abs(fechaNacimientoMilis
				- System.currentTimeMillis()));
		anios = (age.get(Calendar.YEAR) - 1970);
		meses = age.get(Calendar.MONTH);
		dias = age.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @return the anios
	 */
	public int getAnios() {
		return anios;
	}

	/**
	 * @param anios
	 *            the anios to set
	 */
	public void setAnios(int anios) {
		this.anios = anios;
	}

	/**
	 * @return the meses
	 */
	public int getMeses() {
		return meses;
	}

	/**
	 * @param meses
	 *            the meses to set
	 */
	public void setMeses(int meses) {
		this.meses = meses;
	}

	/**
	 * @return the dias
	 */
	public int getDias() {
		return dias;
	}

	/**
	 * @param dias
	 *            the dias to set
	 */
	public void setDias(int dias) {
		this.dias = dias;
	}

	/**
	 * @return the edadTotal
	 */
	public String getEdadTotal() {
		if (edadTotal == null) {

			edadTotal = this.anios + "a " + this.meses + "m " + this.dias + "d";

		}
		return edadTotal;
	}

	public String getEdadParaReporte() {
		String edadParaReporte = "";

		if (!getEdadTotal().equals("")) {
			if (this.anios == 0) {
				edadParaReporte = this.meses + "m " + this.dias + "d";
			} else {
				edadParaReporte = this.anios + "a";
			}
		} else {
			edadParaReporte = edadTotal;// es decir, ND
		}

		return edadParaReporte;
	}

	/**
	 * @param edadTotal
	 *            the edadTotal to set
	 */
	public void setEdadTotal(String edadTotal) {
		this.edadTotal = edadTotal;
	}
}