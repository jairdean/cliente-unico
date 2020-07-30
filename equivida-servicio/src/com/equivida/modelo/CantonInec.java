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
@Table(name = "CANTON_INEC")
@NamedQueries({
		@NamedQuery(name = "CantonInec.findAll", query = "SELECT c FROM CantonInec c"),
		@NamedQuery(name = "CantonInec.findBySecCanton", query = "SELECT c FROM CantonInec c WHERE c.secCanton = :secCanton"),
		@NamedQuery(name = "CantonInec.findByCodProvincia", query = "SELECT c FROM CantonInec c WHERE c.codProvincia = :codProvincia"),
		@NamedQuery(name = "CantonInec.findByCodCanton", query = "SELECT c FROM CantonInec c WHERE c.codCanton = :codCanton"),
		@NamedQuery(name = "CantonInec.findByCanton", query = "SELECT c FROM CantonInec c WHERE c.canton = :canton") })
public class CantonInec implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4455987084371468546L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_CANTON")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secCanton;
	@Column(name = "COD_PROVINCIA", columnDefinition = "char(2)")
	private String codProvincia;
	@Column(name = "COD_CANTON", length = 2, columnDefinition = "char(2)")
	private String codCanton;
	@Basic(optional = false)
	@Column(name = "CANTON")
	private String canton;
	@JoinColumn(name = "SEC_CANTON", referencedColumnName = "SEC_CANTON", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private Canton canton1;

	public CantonInec() {
	}

	public CantonInec(Short secCanton) {
		this.secCanton = secCanton;
	}

	public CantonInec(Short secCanton, String canton) {
		this.secCanton = secCanton;
		this.canton = canton;
	}

	public Short getSecCanton() {
		return secCanton;
	}

	public void setSecCanton(Short secCanton) {
		this.secCanton = secCanton;
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

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public Canton getCanton1() {
		return canton1;
	}

	public void setCanton1(Canton canton1) {
		this.canton1 = canton1;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secCanton != null ? secCanton.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CantonInec)) {
			return false;
		}
		CantonInec other = (CantonInec) object;
		if ((this.secCanton == null && other.secCanton != null)
				|| (this.secCanton != null && !this.secCanton
						.equals(other.secCanton))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.CantonInec[secCanton=" + secCanton + "]";
	}

}
