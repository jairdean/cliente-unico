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

/**
 * 
 * @author saviasoft4
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
		@NamedQuery(name = "Pais.findByCodPais", query = "SELECT p FROM Pais p WHERE p.codPais = :codPais"),
		@NamedQuery(name = "Pais.findByPais", query = "SELECT p FROM Pais p WHERE p.pais = :pais"),
		@NamedQuery(name = "Pais.findByPrfTelefonico", query = "SELECT p FROM Pais p WHERE p.prfTelefonico = :prfTelefonico") })
public class Pais implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2491314816793996851L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_PAIS")
	private Short codPais;
	@Basic(optional = false)
	@Column(name = "PAIS")
	private String pais;
	@Basic(optional = false)
	@Column(name = "PRF_TELEFONICO")
	private short prfTelefonico;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
	// private Collection<PersonaJuridica> personaJuridicaCollection;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
	// private Collection<Provincia> provinciaCollection;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
	// private Collection<Telefono> telefonoCollection;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
	// private Collection<PersonaNatural> personaNaturalCollection;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais1")
	// private Collection<PersonaNatural> personaNaturalCollection1;

	public Pais() {
	}

	public Pais(Short codPais) {
		this.codPais = codPais;
	}

	public Pais(Short codPais, String pais) {
		this.codPais = codPais;
		this.pais = pais;
	}

	public Pais(Short codPais, String pais, short prfTelefonico) {
		this.codPais = codPais;
		this.pais = pais;
		this.prfTelefonico = prfTelefonico;
	}

	public Short getCodPais() {
		return codPais;
	}

	public void setCodPais(Short codPais) {
		this.codPais = codPais;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public short getPrfTelefonico() {
		return prfTelefonico;
	}

	public void setPrfTelefonico(short prfTelefonico) {
		this.prfTelefonico = prfTelefonico;
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
		if (!(object instanceof Pais)) {
			return false;
		}
		Pais other = (Pais) object;
		if ((this.codPais == null && other.codPais != null)
				|| (this.codPais != null && !this.codPais.equals(other.codPais))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Pais [codPais=" + codPais + ", pais=" + pais
				+ ", prfTelefonico=" + prfTelefonico + "]";
	}
}
