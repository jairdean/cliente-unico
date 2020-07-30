/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_PARENTESCO_RELACION")
@NamedQueries({ @NamedQuery(name = "TipoParentescoRelacion.findAll", query = "SELECT t FROM TipoParentescoRelacion t"),
		@NamedQuery(name = "TipoParentescoRelacion.findByCodTipoParentesco", query = "SELECT t FROM TipoParentescoRelacion t WHERE t.codTipoParentesco = :codTipoParentesco"),
		@NamedQuery(name = "TipoParentescoRelacion.findByTipoParentesco", query = "SELECT t FROM TipoParentescoRelacion t WHERE t.tipoParentesco = :tipoParentesco"),
		@NamedQuery(name = "TipoParentescoRelacion.findByFlgFamiliar", query = "SELECT t FROM TipoParentescoRelacion t WHERE t.flgFamiliar = :flgFamiliar") })
public class TipoParentescoRelacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3468872337699695012L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_TIPO_PARENTESCO")
	private Short codTipoParentesco;
	@Basic(optional = false)
	@Column(name = "TIPO_PARENTESCO")
	private String tipoParentesco;
	@Basic(optional = false)
	@Column(name = "FLG_FAMILIAR")
	private char flgFamiliar;
	@Basic(optional = false)
	@Column(name = "SEXO")
	private char sexo; // M-F-I

	public TipoParentescoRelacion() {
	}

	public TipoParentescoRelacion(Short codTipoParentesco) {
		this.codTipoParentesco = codTipoParentesco;
	}

	public TipoParentescoRelacion(Short codTipoParentesco, String tipoParentesco, char flgFamiliar) {
		this.codTipoParentesco = codTipoParentesco;
		this.tipoParentesco = tipoParentesco;
		this.flgFamiliar = flgFamiliar;
	}

	public Short getCodTipoParentesco() {
		return codTipoParentesco;
	}

	public void setCodTipoParentesco(Short codTipoParentesco) {
		this.codTipoParentesco = codTipoParentesco;
	}

	public String getTipoParentesco() {
		return tipoParentesco;
	}

	public void setTipoParentesco(String tipoParentesco) {
		this.tipoParentesco = tipoParentesco;
	}

	public char getFlgFamiliar() {
		return flgFamiliar;
	}

	public void setFlgFamiliar(char flgFamiliar) {
		this.flgFamiliar = flgFamiliar;
	}

	/**
	 * @return the sexo
	 */
	public char getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codTipoParentesco != null ? codTipoParentesco.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoParentescoRelacion)) {
			return false;
		}
		TipoParentescoRelacion other = (TipoParentescoRelacion) object;
		if ((this.codTipoParentesco == null && other.codTipoParentesco != null)
				|| (this.codTipoParentesco != null && !this.codTipoParentesco.equals(other.codTipoParentesco))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.TipoParentescoRelacion[codTipoParentesco=" + codTipoParentesco + "]";
	}

}
