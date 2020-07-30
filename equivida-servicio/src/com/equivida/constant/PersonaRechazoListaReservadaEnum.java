package com.equivida.constant;

/**
 * @author Daniel Cardenas
 */
public enum PersonaRechazoListaReservadaEnum {

	EN_LISTA_PERSONA('P', "Persona"), EN_LISTA_CONYUGE_PERSONA('C', "C\u00F3nyuge"),
	EN_LISTA_PERSONA_JURIDICA('J', "Jur\u00EDdica"), EN_LISTA_REPRESENTANTE_LEGAL('R', "Representante Legal"),
	EN_LISTA_CONYUGE_REPRESENTANTE_LEGAL('Y', "C\u00F3nyuge Representante Legal"),
	EN_LISTA_ACCIONISTA('A', "Accionista");

	private char codigo;
	private String etiqueta;

	private PersonaRechazoListaReservadaEnum(char codigo, String etiqueta) {
		this.codigo = codigo;
		this.etiqueta = etiqueta;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public static PersonaRechazoListaReservadaEnum buscarPorCodigo(char codigo) {
		for (PersonaRechazoListaReservadaEnum e : values()) {
			if (e.getCodigo() == codigo) {
				return e;
			}
		}
		return null;
	}
}
