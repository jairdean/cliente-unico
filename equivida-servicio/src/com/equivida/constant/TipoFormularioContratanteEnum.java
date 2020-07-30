package com.equivida.constant;

public enum TipoFormularioContratanteEnum {
	V("VIDA"), AP("ACCIDENTES PERSONALES");

	private String descripcion;

	TipoFormularioContratanteEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
