package com.equivida.homologacion.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SISTEMA_DESTINO")
public class SistemaDestino implements Serializable {

	private static final long serialVersionUID = 6909948507583793222L;

	@Id
	@Column(name = "COD_SISTEMA")
	private Short secSistemaDestino;

	@Column(name = "NOMBRE_SISTEMA")
	private String nombreSistema;

	@Column(name = "ACTIVO")
	private char activo;// A-I

	public Short getSecSistemaDestino() {
		return secSistemaDestino;
	}

	public void setSecSistemaDestino(Short secSistemaDestino) {
		this.secSistemaDestino = secSistemaDestino;
	}

	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	public char getActivo() {
		return activo;
	}

	public void setActivo(char activo) {
		this.activo = activo;
	}
}