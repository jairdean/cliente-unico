package com.equivida.intranet.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtils {

	public static List<Integer> getYearsFromToCurrent(int year) {
		List<Integer> result = new ArrayList<Integer>();

		int actualYear = getCurrentYearInt();

		for (int i = year; i <= actualYear; i++) {
			result.add(i);
		}

		return result;
	}

	public static String getCurrentYear() {
		return "" + Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * Obtiene el anio actual
	 * 
	 * @return
	 */
	public static int getCurrentYearInt() {
		Calendar now = Calendar.getInstance();

		return now.get(Calendar.YEAR);
	}

}
