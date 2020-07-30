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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PROVINCIA_INEC")
@NamedQueries({
		@NamedQuery(name = "ProvinciaInec.findAll", query = "SELECT p FROM ProvinciaInec p"),
		@NamedQuery(name = "ProvinciaInec.findBySecProvincia", query = "SELECT p FROM ProvinciaInec p WHERE p.secProvincia = :secProvincia"),
		@NamedQuery(name = "ProvinciaInec.findByCodProvincia", query = "SELECT p FROM ProvinciaInec p WHERE p.codProvincia = :codProvincia"),
		@NamedQuery(name = "ProvinciaInec.findByProvincia1", query = "SELECT p FROM ProvinciaInec p WHERE p.provincia1 = :provincia1") })
public class ProvinciaInec implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4327623434316195135L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PROVINCIA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secProvincia;
	@Column(name = "COD_PROVINCIA", length = 2, columnDefinition = "char(2)")
	private String codProvincia;
	@Basic(optional = false)
	@Column(name = "PROVINCIA")
	private String provincia1;

	@JoinColumn(name = "SEC_PROVINCIA", referencedColumnName = "SEC_PROVINCIA", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private Provincia provincia;

	@JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS")
	@ManyToOne(optional = false)
	private PaisIso paisIso;

	public ProvinciaInec() {
	}

	public ProvinciaInec(Short secProvincia) {
		this.secProvincia = secProvincia;
	}

	public ProvinciaInec(Short secProvincia, String provincia1) {
		this.secProvincia = secProvincia;
		this.provincia1 = provincia1;
	}

	public Short getSecProvincia() {
		return secProvincia;
	}

	public void setSecProvincia(Short secProvincia) {
		this.secProvincia = secProvincia;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getProvincia1() {
		return provincia1;
	}

	public void setProvincia1(String provincia1) {
		this.provincia1 = provincia1;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public PaisIso getPaisIso() {
		return paisIso;
	}

	public void setPaisIso(PaisIso paisIso) {
		this.paisIso = paisIso;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secProvincia != null ? secProvincia.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProvinciaInec)) {
			return false;
		}
		ProvinciaInec other = (ProvinciaInec) object;
		if ((this.secProvincia == null && other.secProvincia != null)
				|| (this.secProvincia != null && !this.secProvincia
						.equals(other.secProvincia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.ProvinciaInec[secProvincia=" + secProvincia
				+ "]";
	}

}
