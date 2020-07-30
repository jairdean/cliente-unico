package org.tempuri.test;

public class PruebaStr {
	public static void main(String[] args) {
		String result = "<Asegurado><DetalleAseg><CodAseg>210lplpl90</CodAseg><txtError>- no existe persona ingresada - no existe dirección - no existe teléfono</txtError></DetalleAseg></Asegurado>";

		int posIni = result.indexOf("<CodAseg>");
		posIni = posIni + "<CodAseg>".length();
		int posFin = result.indexOf("</CodAseg>");

		String a = result.substring(posIni, posFin);
		System.out.println("respuesta: " + a);
	}
}
