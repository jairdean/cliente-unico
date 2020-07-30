package com.equivida.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase utilitaria para fechas
 * 
 * @author Daniel Cardenas
 *
 */
public class DateUtil {

	public static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

	/**
	 * Clase que obtiene el numero de dias entre dos fechas
	 * 
	 * @param masActual
	 * @param masAntigua
	 * @return
	 */
	public static int getDiferenciaEnDias(Date masActual, Date masAntigua) {
		return (int) ((masActual.getTime() - masAntigua.getTime()) / DAY_IN_MILLIS);
	}

	/**
	 * Metodo de prueba del metodo
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date date = sdf.parse("30/12/2015");
		cal1.setTime(date);
		date = sdf.parse("02/01/2016");
		cal2.setTime(date);
		
		System.out.println(getDiferenciaEnDias(cal2.getTime(), cal1.getTime()));

	}

}
