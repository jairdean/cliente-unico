package com.equivida.homologacion.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIRECCION_ELECTRONICA_EQUIVIDA")
public class DireccionElectronicaEquivida implements Serializable {

	private static final long serialVersionUID = -1011721840543423780L;

	@Id
	@Column(name = "SEC_UNICO_DIRECCION_ELECT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secUnicoDireccionElect;

	@Column(name = "SEC_PERSONA_EQUIVIDA")
	private Integer secPersonaEquivida;

	@Column(name = "SEC_DIRECCION_ELECT_EQUIVIDA")
	private Integer secDireccionElectEquivida;

	@Column(name = "COD_SISTEMA")
	private Short codSistema;

	@Column(name = "SEC_DIRECCION_ELECT_DESTINO")
	private Integer secDireccionElectDestino;

	@Column(name = "ID_PERSONA_DESTINO")
	private Integer idPersonaDestino;

	public Integer getSecUnicoDireccionElect() {
		return secUnicoDireccionElect;
	}

	public void setSecUnicoDireccionElect(Integer secUnicoDireccionElect) {
		this.secUnicoDireccionElect = secUnicoDireccionElect;
	}

	public Integer getSecPersonaEquivida() {
		return secPersonaEquivida;
	}

	public void setSecPersonaEquivida(Integer secPersonaEquivida) {
		this.secPersonaEquivida = secPersonaEquivida;
	}

	public Integer getSecDireccionElectEquivida() {
		return secDireccionElectEquivida;
	}

	public void setSecDireccionElectEquivida(Integer secDireccionElectEquivida) {
		this.secDireccionElectEquivida = secDireccionElectEquivida;
	}

	public Short getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Short codSistema) {
		this.codSistema = codSistema;
	}

	public Integer getSecDireccionElectDestino() {
		return secDireccionElectDestino;
	}

	public void setSecDireccionElectDestino(Integer secDireccionElectDestino) {
		this.secDireccionElectDestino = secDireccionElectDestino;
	}

	public Integer getIdPersonaDestino() {
		return idPersonaDestino;
	}

	public void setIdPersonaDestino(Integer idPersonaDestino) {
		this.idPersonaDestino = idPersonaDestino;
	}

}