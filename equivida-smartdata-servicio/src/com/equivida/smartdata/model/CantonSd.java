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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "CANTON")
@XmlAccessorType(XmlAccessType.FIELD)
public class CantonSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_CANTON")
	private Short secCanton;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 64)
	@Column(name = "CANTON")
	private String canton;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "secCanton")
	@XmlTransient
	private List<ParroquiaSd> parroquiaList;
	@JoinColumn(name = "SEC_PROVINCIA", referencedColumnName = "SEC_PROVINCIA")
	@ManyToOne(optional = false)
	@XmlTransient
	private ProvinciaSd secProvincia;

	public CantonSd() {
	}

	public CantonSd(Short secCanton) {
		this.secCanton = secCanton;
	}

	public CantonSd(Short secCanton, String canton) {
		this.secCanton = secCanton;
		this.canton = canton;
	}

	public Short getSecCanton() {
		return secCanton;
	}

	public void setSecCanton(Short secCanton) {
		this.secCanton = secCanton;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public List<ParroquiaSd> getParroquiaList() {
		return parroquiaList;
	}

	public void setParroquiaList(List<ParroquiaSd> parroquiaList) {
		this.parroquiaList = parroquiaList;
	}

	public ProvinciaSd getSecProvincia() {
		return secProvincia;
	}

	public void setSecProvincia(ProvinciaSd secProvincia) {
		this.secProvincia = secProvincia;
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
		if (!(object instanceof CantonSd)) {
			return false;
		}
		CantonSd other = (CantonSd) object;
		if ((this.secCanton == null && other.secCanton != null)
				|| (this.secCanton != null && !this.secCanton.equals(other.secCanton))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.Canton[ secCanton=" + secCanton + " ]";
	}

}
