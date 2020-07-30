package com.equivida.homologacion.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TELEFONO_EQUIVIDA")
public class TelefonoEquivida implements Serializable {

	private static final long serialVersionUID = -319246061656395785L;

	@Id
	@Column(name = "SEC_UNICO_TELEFONO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secUnicoTelefono;

	@Column(name = "SEC_PERSONA_EQUIVIDA")
	private Integer secPersonaEquivida;

	@Column(name = "SEC_TELEFONO_EQUIVIDA")
	private Integer secTelefonoEquivida;

	@Column(name = "COD_SISTEMA")
	private Short codSistema;

	@Column(name = "SEC_TELEFONO_DESTINO")
	private Integer secTelefonoDestino;

	@Column(name = "ID_PERSONA_DESTINO")
	private Integer idPersonaDestino;

	public Integer getSecUnicoTelefono() {
		return secUnicoTelefono;
	}

	public void setSecUnicoTelefono(Integer secUnicoTelefono) {
		this.secUnicoTelefono = secUnicoTelefono;
	}

	public Integer getSecPersonaEquivida() {
		return secPersonaEquivida;
	}

	public void setSecPersonaEquivida(Integer secPersonaEquivida) {
		this.secPersonaEquivida = secPersonaEquivida;
	}

	public Short getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Short codSistema) {
		this.codSistema = codSistema;
	}

	public Integer getSecTelefonoDestino() {
		return secTelefonoDestino;
	}

	public void setSecTelefonoDestino(Integer secTelefonoDestino) {
		this.secTelefonoDestino = secTelefonoDestino;
	}

	public Integer getIdPersonaDestino() {
		return idPersonaDestino;
	}

	public void setIdPersonaDestino(Integer idPersonaDestino) {
		this.idPersonaDestino = idPersonaDestino;
	}

	public Integer getSecTelefonoEquivida() {
		return secTelefonoEquivida;
	}

	public void setSecTelefonoEquivida(Integer secTelefonoEquivida) {
		this.secTelefonoEquivida = secTelefonoEquivida;
	}

}
