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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PROVINCIA")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
		@NamedQuery(name = "Provincia.findBySecProvincia", query = "SELECT p FROM Provincia p WHERE p.secProvincia = :secProvincia"),
		@NamedQuery(name = "Provincia.findByProvincia", query = "SELECT p FROM Provincia p WHERE p.provincia = :provincia") })
public class Provincia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1665079921155025458L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PROVINCIA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secProvincia;
	@Basic(optional = false)
	@Column(name = "PROVINCIA")
	private String provincia;

	@Column(name = "COD_AREA")
	private String codArea;// para el numero telefonico

	@JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS")
	@ManyToOne(optional = false)
	@XmlTransient
	private Pais pais;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "provincia")
	@XmlTransient
	private Collection<Canton> cantonCollection;

	public Provincia() {
	}

	public Provincia(Short secProvincia) {
		this.secProvincia = secProvincia;
	}

	public Provincia(Short secProvincia, String provincia) {
		this.secProvincia = secProvincia;
		this.provincia = provincia;
	}

	public Short getSecProvincia() {
		return secProvincia;
	}

	public void setSecProvincia(Short secProvincia) {
		this.secProvincia = secProvincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Collection<Canton> getCantonCollection() {
		return cantonCollection;
	}

	public void setCantonCollection(Collection<Canton> cantonCollection) {
		this.cantonCollection = cantonCollection;
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
		if (!(object instanceof Provincia)) {
			return false;
		}
		Provincia other = (Provincia) object;
		if ((this.secProvincia == null && other.secProvincia != null)
				|| (this.secProvincia != null && !this.secProvincia
						.equals(other.secProvincia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Provincia[secProvincia=" + secProvincia
				+ "]";
	}

	public String getCodArea() {
		return codArea;
	}

	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

}
