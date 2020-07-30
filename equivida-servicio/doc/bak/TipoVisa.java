package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_VISA")
public class TipoVisa implements Serializable {

	private static final long serialVersionUID = -195074289455624236L;

	@Id
	@Basic(optional = false)
	@Column(name = "sec_tipo_visa")
	private Short secTipoVisa;

	@Column(name = "cod_tipo_visa", length = 8)
	private String codTipoVisa;

	@Column(name = "estado_migratorio", length = 64)
	private String estadoMigratorio;

	public String getCodTipoVisa() {
		return codTipoVisa;
	}

	public void setCodTipoVisa(String codTipoVisa) {
		this.codTipoVisa = codTipoVisa;
	}

	public Short getSecTipoVisa() {
		return secTipoVisa;
	}

	public void setSecTipoVisa(Short secTipoVisa) {
		this.secTipoVisa = secTipoVisa;
	}

	public String getEstadoMigratorio() {
		return estadoMigratorio;
	}

	public void setEstadoMigratorio(String estadoMigratorio) {
		this.estadoMigratorio = estadoMigratorio;
	}
}
