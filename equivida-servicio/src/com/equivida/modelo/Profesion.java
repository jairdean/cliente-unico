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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PROFESION")
@NamedQueries({
		@NamedQuery(name = "Profesion.findAll", query = "SELECT p FROM Profesion p"),
		@NamedQuery(name = "Profesion.findBySecProfesion", query = "SELECT p FROM Profesion p WHERE p.secProfesion = :secProfesion"),
		@NamedQuery(name = "Profesion.findByProfesion", query = "SELECT p FROM Profesion p WHERE p.profesion = :profesion") })
public class Profesion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5144757354501642541L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PROFESION")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secProfesion;
	@Basic(optional = false)
	@Column(name = "PROFESION")
	private String profesion;
//	@OneToMany(mappedBy = "profesion")
//	private Collection<PersonaNatural> personaNaturalCollection;

	public Profesion() {
	}

	public Profesion(Short secProfesion) {
		this.secProfesion = secProfesion;
	}

	public Profesion(Short secProfesion, String profesion) {
		this.secProfesion = secProfesion;
		this.profesion = profesion;
	}

	public Short getSecProfesion() {
		return secProfesion;
	}

	public void setSecProfesion(Short secProfesion) {
		this.secProfesion = secProfesion;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	//public Collection<PersonaNatural> getPersonaNaturalCollection() {
	//	return personaNaturalCollection;
	//}

	//public void setPersonaNaturalCollection(
	//		Collection<PersonaNatural> personaNaturalCollection) {
	//	this.personaNaturalCollection = personaNaturalCollection;
	//}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secProfesion != null ? secProfesion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Profesion)) {
			return false;
		}
		Profesion other = (Profesion) object;
		if ((this.secProfesion == null && other.secProfesion != null)
				|| (this.secProfesion != null && !this.secProfesion
						.equals(other.secProfesion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Profesion[secProfesion=" + secProfesion
				+ "]";
	}

}
