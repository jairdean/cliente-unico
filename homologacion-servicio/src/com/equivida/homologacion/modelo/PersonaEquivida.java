package com.equivida.homologacion.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONA_EQUIVIDA")
public class PersonaEquivida implements Serializable {

	private static final long serialVersionUID = 4253864875802162515L;

	@Id
	@Column(name = "SEC_UNICO_PERSONA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secUnicoPersona;

	@Column(name = "SEC_PERSONA_EQUIVIDA")
	private Integer secPersonaEquivida;

	@Column(name = "COD_SISTEMA")
	private Short codSistema;

	@Column(name = "ID_PERSONA_DESTINO")
	private Long idPersonaDestino;

	@Column(name = "COD_TIPO_PERSONA")
	private Short codTipoPersona;

	@Column(name = "SEC_PERSONA_NATURAL")
	private Integer secPersonaNatural;

	@Column(name = "SEC_PERSONA_JURIDICA")
	private Integer secPersonaJuridica;

	public Integer getSecUnicoPersona() {
		return secUnicoPersona;
	}

	public void setSecUnicoPersona(Integer secUnicoPersona) {
		this.secUnicoPersona = secUnicoPersona;
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

	public Short getCodTipoPersona() {
		return codTipoPersona;
	}

	public void setCodTipoPersona(Short codTipoPersona) {
		this.codTipoPersona = codTipoPersona;
	}

	public Integer getSecPersonaNatural() {
		return secPersonaNatural;
	}

	public void setSecPersonaNatural(Integer secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
	}

	public Integer getSecPersonaJuridica() {
		return secPersonaJuridica;
	}

	public void setSecPersonaJuridica(Integer secPersonaJuridica) {
		this.secPersonaJuridica = secPersonaJuridica;
	}

	public Long getIdPersonaDestino() {
		return idPersonaDestino;
	}

	public void setIdPersonaDestino(Long idPersonaDestino) {
		this.idPersonaDestino = idPersonaDestino;
	}

	@Override
	public String toString() {
		return "PersonaEquivida [secUnicoPersona=" + secUnicoPersona
				+ ", secPersonaEquivida=" + secPersonaEquivida
				+ ", codSistema=" + codSistema + ", idPersonaDestino="
				+ idPersonaDestino + ", codTipoPersona=" + codTipoPersona
				+ ", secPersonaNatural=" + secPersonaNatural
				+ ", secPersonaJuridica=" + secPersonaJuridica + "]";
	}
}
