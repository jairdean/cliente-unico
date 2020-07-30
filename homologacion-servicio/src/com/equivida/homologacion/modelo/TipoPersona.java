package com.equivida.homologacion.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_PERSONA")
public class TipoPersona implements Serializable {

	private static final long serialVersionUID = -2873741107153106296L;

	@Id
	@Column(name = "COD_TIPO_PERSONA")
	private Short codTipoPersona;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "ACTIVO")
	private char activo;// A-I

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public char getActivo() {
		return activo;
	}

	public void setActivo(char activo) {
		this.activo = activo;
	}

	public Short getCodTipoPersona() {
		return codTipoPersona;
	}

	public void setCodTipoPersona(Short codTipoPersona) {
		this.codTipoPersona = codTipoPersona;
	}
}