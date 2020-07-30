package com.equivida.dto;

public class RcsRespuestaDto {

	private String identificacion;

	private String error;

	private String contenidoXml;

	public String getIndentificacion() {
		return identificacion;
	}

	public void setIndentificacion(String indentificacion) {
		this.identificacion = indentificacion;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getContenidoXml() {
		return contenidoXml;
	}

	public void setContenidoXml(String contenidoXml) {
		this.contenidoXml = contenidoXml;
	}

}
