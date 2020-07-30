package com.equivida.constant;

/**
 * @author Daniel Cardenas
 */
public enum EstadoRcsEnum {

	MODIFICACION('M', "Modificaci\u00F3n"), NUEVO('N', "Nuevo");

	private char codigo;
	private String etiqueta;

	private EstadoRcsEnum(char codigo, String etiqueta) {
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

	public static EstadoRcsEnum buscarPorCodigo(char codigo) {
		for (EstadoRcsEnum e : values()) {
			if (e.getCodigo() == codigo) {
				return e;
			}
		}
		return null;
	}
}