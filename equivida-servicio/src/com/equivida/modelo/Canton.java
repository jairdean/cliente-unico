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
@Table(name = "CANTON")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "Canton.findAll", query = "SELECT c FROM Canton c"),
		@NamedQuery(name = "Canton.findBySecCanton", query = "SELECT c FROM Canton c WHERE c.secCanton = :secCanton"),
		@NamedQuery(name = "Canton.findByCanton", query = "SELECT c FROM Canton c WHERE c.canton = :canton") })
public class Canton implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3463127541750025861L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_CANTON")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secCanton;
	@Basic(optional = false)
	@Column(name = "CANTON")
	private String canton;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "canton")
	@XmlTransient
	private Collection<Parroquia> parroquiaCollection;
	@JoinColumn(name = "SEC_PROVINCIA", referencedColumnName = "SEC_PROVINCIA")
	@ManyToOne(optional = false)
	@XmlTransient
	private Provincia provincia;

	// @JoinColumn(name = "SEC_CANTON", referencedColumnName = "SEC_CANTON",
	// insertable = false, updatable = false)
	// @OneToOne(optional = false)
	// private CantonInec cantonInec;

	public Canton() {
	}

	public Canton(Short secCanton, String canton) {
		this.secCanton = secCanton;
		this.canton = canton;
	}

	public Canton(Short secCanton, String nombreCanton, Short secProvincia,
			String nombreProv, Short secPais, String nombrePais) {
		this.secCanton = secCanton;
		this.canton = nombreCanton;
		this.setProvincia(new Provincia(secProvincia, nombreProv));
		this.getProvincia().setPais(new Pais(secPais, nombrePais));
	}

	public Canton(Short secCanton) {
		this.secCanton = secCanton;
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

	public Collection<Parroquia> getParroquiaCollection() {
		return parroquiaCollection;
	}

	public void setParroquiaCollection(Collection<Parroquia> parroquiaCollection) {
		this.parroquiaCollection = parroquiaCollection;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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
		if (!(object instanceof Canton)) {
			return false;
		}
		Canton other = (Canton) object;
		if ((this.secCanton == null && other.secCanton != null)
				|| (this.secCanton != null && !this.secCanton
						.equals(other.secCanton))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Canton[secCanton=" + secCanton + "]";
	}

}
