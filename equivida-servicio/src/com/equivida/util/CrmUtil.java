/*
 * @Copyright 2014. Saviasoft.
 * 
 */
package com.equivida.util;

public class CrmUtil {

	public static String obtenerXmlSoloDePersona(String xml) {

		int ini = xml.indexOf("<PERS>") + 6;

		int fin = xml.indexOf("</PERS>");

		System.out.println();

		return xml.substring(ini, fin);
	}

	public static String obtenerXmlPorTag(String xml, String tagInicial,
			String tagFinal) {

		int ini = xml.indexOf(tagInicial) + tagInicial.length();

		int fin = xml.indexOf(tagFinal);

		if (ini < 0 || fin < 0) {
			return null;
		}

		return xml.substring(ini, fin);
	}
}