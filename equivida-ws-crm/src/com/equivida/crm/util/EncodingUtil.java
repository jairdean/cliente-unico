package com.equivida.crm.util;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {

	public static String encodeCP850(String cadena) {
		try {
			if (cadena == null || cadena.equals("")) {
				return null;
			}
			return new String(cadena.getBytes("iso-8859-1"), "CP850");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encodeCP850toUTF8(String cadena) {
		try {
			if (cadena == null || cadena.equals("")) {
				return null;
			}
			return new String(cadena.getBytes("CP850"), "iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
