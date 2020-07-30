package com.equivida.crm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final String FORMATO = "yyyy/MM/dd";
	public static final String FORMATO_SIN_SLASH = "yyyyMMdd";

	/**
	 * Elformato es yyyy/MM/dd/
	 * 
	 * @param yyyymmdd
	 * @return
	 * @throws ParseException
	 */
	public static final Date getFecha(String yyyymmdd) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(FORMATO);
		if (yyyymmdd == null || yyyymmdd.equals("")) {
			return null;
		}
		Date date = new Date();
		date = format.parse(yyyymmdd);

		return date;
	}

	/**
	 * Elformato es yyyyMMdd
	 * 
	 * @param yyyymmdd
	 * @return
	 * @throws ParseException
	 */
	public static final Date getFechaSL(String yyyymmdd) throws ParseException {
		System.out.println(yyyymmdd);
		SimpleDateFormat format = new SimpleDateFormat(FORMATO_SIN_SLASH);
		
		if (yyyymmdd == null || yyyymmdd.equals("")) {
			return null;
		}
		Date date = new Date();
		date = format.parse(yyyymmdd);

		return date;
	}

	/**
	 * Para mensajes de error en sp's
	 * 
	 * @param fecha
	 * @return
	 */
	public static String formatear(Date fecha) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.format(fecha);
	}
}