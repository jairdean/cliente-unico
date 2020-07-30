/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "TIPO_PARENTESCO_RELACION")
public class TipoParentescoRelacionSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "COD_TIPO_PARENTESCO")
	private Short codTipoParentesco;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "TIPO_PARENTESCO")
	private String tipoParentesco;
	@Basic(optional = false)
	@NotNull
	@Column(name = "FLG_FAMILIAR")
	private char flgFamiliar;
	@Basic(optional = false)
	@NotNull
	@Column(name = "SEXO")
	private char sexo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoParentesco")
	private List<DetalleRelacionSd> detalleRelacionList;

	public TipoParentescoRelacionSd() {
	}

	public TipoParentescoRelacionSd(Short codTipoParentesco) {
		this.codTipoParentesco = codTipoParentesco;
	}

	public TipoParentescoRelacionSd(Short codTipoParentesco, String tipoParentesco, char flgFamiliar, char sexo) {
		this.codTipoParentesco = codTipoParentesco;
		this.tipoParentesco = tipoParentesco;
		this.flgFamiliar = flgFamiliar;
		this.sexo = sexo;
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public List<DetalleRelacionSd> getDetalleRelacionList() {
		return detalleRelacionList;
	}

	public void setDetalleRelacionList(List<DetalleRelacionSd> detalleRelacionList) {
		this.detalleRelacionList = detalleRelacionList;
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
		if (!(object instanceof TipoParentescoRelacionSd)) {
			return false;
		}
		TipoParentescoRelacionSd other = (TipoParentescoRelacionSd) object;
		if ((this.codTipoParentesco == null && other.codTipoParentesco != null)
				|| (this.codTipoParentesco != null && !this.codTipoParentesco.equals(other.codTipoParentesco))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.TipoParentescoRelacion[ codTipoParentesco=" + codTipoParentesco + " ]";
	}

}
