package com.equivida.homologacion.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIRECCION_EQUIVIDA")
public class DireccionEquivida implements Serializable {

	private static final long serialVersionUID = 5438372233678285335L;

	@Id
	@Column(name = "SEC_UNICO_DIRECCION")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secUnicoDireccion;

	@Column(name = "SEC_PERSONA_EQUIVIDA")
	private Integer secPersonaEquivida;

	@Column(name = "SEC_DIRECCION_EQUIVIDA")
	private Integer secDireccionEquivida;

	@Column(name = "COD_SISTEMA")
	private Short codSistema;

	@Column(name = "SEC_DIRECCION_DESTINO")
	private Integer secDireccionDestino;

	@Column(name = "ID_PERSONA_DESTINO")
	private Integer idPersonaDestino;

	public Integer getSecPersonaEquivida() {
		return secPersonaEquivida;
	}

	public void setSecPersonaEquivida(Integer secPersonaEquivida) {
		this.secPersonaEquivida = secPersonaEquivida;
	}

	public Integer getSecDireccionEquivida() {
		return secDireccionEquivida;
	}

	public void setSecDireccionEquivida(Integer secDireccionEquivida) {
		this.secDireccionEquivida = secDireccionEquivida;
	}

	public Short getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Short codSistema) {
		this.codSistema = codSistema;
	}

	public Integer getSecUnicoDireccion() {
		return secUnicoDireccion;
	}

	public void setSecUnicoDireccion(Integer secUnicoDireccion) {
		this.secUnicoDireccion = secUnicoDireccion;
	}

	@Override
	public String toString() {
		return "DireccionEquivida [secUnicoDireccion=" + secUnicoDireccion
				+ ", secPersonaEquivida=" + secPersonaEquivida
				+ ", secDireccionEquivida=" + secDireccionEquivida
				+ ", codSistema=" + codSistema + ", secDireccionDestino="
				+ secDireccionDestino + ", idPersonaDestino="
				+ idPersonaDestino + "]";
	}

	public Integer getIdPersonaDestino() {
		return idPersonaDestino;
	}

	public void setIdPersonaDestino(Integer idPersonaDestino) {
		this.idPersonaDestino = idPersonaDestino;
	}

	public Integer getSecDireccionDestino() {
		return secDireccionDestino;
	}

	public void setSecDireccionDestino(Integer secDireccionDestino) {
		this.secDireccionDestino = secDireccionDestino;
	}
}