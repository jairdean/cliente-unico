/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PARROQUIA_INEC")
@NamedQueries({
		@NamedQuery(name = "ParroquiaInec.findAll", query = "SELECT p FROM ParroquiaInec p"),
		@NamedQuery(name = "ParroquiaInec.findBySecParroquia", query = "SELECT p FROM ParroquiaInec p WHERE p.secParroquia = :secParroquia"),
		@NamedQuery(name = "ParroquiaInec.findByCodProvincia", query = "SELECT p FROM ParroquiaInec p WHERE p.codProvincia = :codProvincia"),
		@NamedQuery(name = "ParroquiaInec.findByCodCanton", query = "SELECT p FROM ParroquiaInec p WHERE p.codCanton = :codCanton"),
		@NamedQuery(name = "ParroquiaInec.findByCodParroquia", query = "SELECT p FROM ParroquiaInec p WHERE p.codParroquia = :codParroquia"),
		@NamedQuery(name = "ParroquiaInec.findByParroquia", query = "SELECT p FROM ParroquiaInec p WHERE p.parroquia = :parroquia"),
		@NamedQuery(name = "ParroquiaInec.findByCabecera", query = "SELECT p FROM ParroquiaInec p WHERE p.cabecera = :cabecera") })
public class ParroquiaInec implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7444240816065606813L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PARROQUIA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secParroquia;
	@Column(name = "COD_PROVINCIA", length = 2, columnDefinition = "char(2)")
	private String codProvincia;
	@Column(name = "COD_CANTON", length = 2, columnDefinition = "char(2)")
	private String codCanton;
	@Column(name = "COD_PARROQUIA", length = 2, columnDefinition = "char(2)")
	private String codParroquia;
	@Basic(optional = false)
	@Column(name = "PARROQUIA")
	private String parroquia;
	@Basic(optional = false)
	@Column(name = "CABECERA")
	private char cabecera;

	@JoinColumn(name = "SEC_PARROQUIA", referencedColumnName = "SEC_PARROQUIA", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private Parroquia parroquia1;

	public ParroquiaInec() {
	}

	public ParroquiaInec(Short secParroquia) {
		this.secParroquia = secParroquia;
	}

	public ParroquiaInec(Short secParroquia, String parroquia, char cabecera) {
		this.secParroquia = secParroquia;
		this.parroquia = parroquia;
		this.cabecera = cabecera;
	}

	public Short getSecParroquia() {
		return secParroquia;
	}

	public void setSecParroquia(Short secParroquia) {
		this.secParroquia = secParroquia;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getCodCanton() {
		return codCanton;
	}

	public void setCodCanton(String codCanton) {
		this.codCanton = codCanton;
	}

	public String getCodParroquia() {
		return codParroquia;
	}

	public void setCodParroquia(String codParroquia) {
		this.codParroquia = codParroquia;
	}

	public String getParroquia() {
		return parroquia;
	}

	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	public char getCabecera() {
		return cabecera;
	}

	public void setCabecera(char cabecera) {
		this.cabecera = cabecera;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secParroquia != null ? secParroquia.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ParroquiaInec)) {
			return false;
		}
		ParroquiaInec other = (ParroquiaInec) object;
		if ((this.secParroquia == null && other.secParroquia != null)
				|| (this.secParroquia != null && !this.secParroquia
						.equals(other.secParroquia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.ParroquiaInec[secParroquia=" + secParroquia
				+ "]";
	}

	public Parroquia getParroquia1() {
		return parroquia1;
	}

	public void setParroquia1(Parroquia parroquia1) {
		this.parroquia1 = parroquia1;
	}
}