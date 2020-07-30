/**
 * 
 */
package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Daniel Cardenas
 * 
 */
@Entity
@Table(name = "CANTON_TMUNICIPIO")
public class CantonTMunicipio implements Serializable {

	private static final long serialVersionUID = 2048731732536704334L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_CANTON_MUNICIPIO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secCantonMunicipio;

	@Column(name = "SEC_CANTON")
	private Short secCanton;

	@Column(name = "COD_PAIS")
	private Short codPais;

	@Column(name = "COD_DPTO")
	private Short codDpto;

	@Column(name = "COD_MUNICIPIO")
	private Short codMunicipio;

	public Short getSecCantonMunicipio() {
		return secCantonMunicipio;
	}

	public void setSecCantonMunicipio(Short secCantonMunicipio) {
		this.secCantonMunicipio = secCantonMunicipio;
	}

	public Short getSecCanton() {
		return secCanton;
	}

	public void setSecCanton(Short secCanton) {
		this.secCanton = secCanton;
	}

	public Short getCodPais() {
		return codPais;
	}

	public void setCodPais(Short codPais) {
		this.codPais = codPais;
	}

	public Short getCodDpto() {
		return codDpto;
	}

	public void setCodDpto(Short codDpto) {
		this.codDpto = codDpto;
	}

	public Short getCodMunicipio() {
		return codMunicipio;
	}

	public void setCodMunicipio(Short codMunicipio) {
		this.codMunicipio = codMunicipio;
	}
}