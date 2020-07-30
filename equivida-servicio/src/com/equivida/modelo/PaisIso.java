/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PAIS_ISO")
@NamedQueries({
		@NamedQuery(name = "PaisIso.findAll", query = "SELECT p FROM PaisIso p"),
		@NamedQuery(name = "PaisIso.findByCodPais", query = "SELECT p FROM PaisIso p WHERE p.codPais = :codPais"),
		@NamedQuery(name = "PaisIso.findByPais1", query = "SELECT p FROM PaisIso p WHERE p.pais1 = :pais1"),
		@NamedQuery(name = "PaisIso.findByPrfTelefonico", query = "SELECT p FROM PaisIso p WHERE p.prfTelefonico = :prfTelefonico"),
		@NamedQuery(name = "PaisIso.findBySufijo", query = "SELECT p FROM PaisIso p WHERE p.sufijo = :sufijo"),
		@NamedQuery(name = "PaisIso.findByCodigoIso", query = "SELECT p FROM PaisIso p WHERE p.codigoIso = :codigoIso") })
public class PaisIso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793377627918209362L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_PAIS")
	private Short codPais;
	@Basic(optional = false)
	@Column(name = "PAIS")
	private String pais1;
	@Basic(optional = false)
	@Column(name = "PRF_TELEFONICO")
	private short prfTelefonico;
	@Column(name = "SUFIJO", length = 2, columnDefinition = "char(2)")
	private String sufijo;
	@Column(name = "CODIGO_ISO", length = 3, columnDefinition = "char(3)")
	private String codigoIso;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paisIso")
	private Collection<ProvinciaInec> provinciaInecCollection;
	@JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private Pais pais;

	public PaisIso() {
	}

	public PaisIso(Short codPais) {
		this.codPais = codPais;
	}

	public PaisIso(Short codPais, String pais1, short prfTelefonico) {
		this.codPais = codPais;
		this.pais1 = pais1;
		this.prfTelefonico = prfTelefonico;
	}

	public Short getCodPais() {
		return codPais;
	}

	public void setCodPais(Short codPais) {
		this.codPais = codPais;
	}

	public String getPais1() {
		return pais1;
	}

	public void setPais1(String pais1) {
		this.pais1 = pais1;
	}

	public short getPrfTelefonico() {
		return prfTelefonico;
	}

	public void setPrfTelefonico(short prfTelefonico) {
		this.prfTelefonico = prfTelefonico;
	}

	public String getSufijo() {
		return sufijo;
	}

	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
	}

	public String getCodigoIso() {
		return codigoIso;
	}

	public void setCodigoIso(String codigoIso) {
		this.codigoIso = codigoIso;
	}

	public Collection<ProvinciaInec> getProvinciaInecCollection() {
		return provinciaInecCollection;
	}

	public void setProvinciaInecCollection(
			Collection<ProvinciaInec> provinciaInecCollection) {
		this.provinciaInecCollection = provinciaInecCollection;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codPais != null ? codPais.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PaisIso)) {
			return false;
		}
		PaisIso other = (PaisIso) object;
		if ((this.codPais == null && other.codPais != null)
				|| (this.codPais != null && !this.codPais.equals(other.codPais))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.PaisIso[codPais=" + codPais + "]";
	}

}
