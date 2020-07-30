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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author saviasoft4
 */
@Entity
@Table(name = "PARROQUIA")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "Parroquia.findAll", query = "SELECT p FROM Parroquia p"),
		@NamedQuery(name = "Parroquia.findBySecParroquia", query = "SELECT p FROM Parroquia p WHERE p.secParroquia = :secParroquia"),
		@NamedQuery(name = "Parroquia.findByParroquia", query = "SELECT p FROM Parroquia p WHERE p.parroquia = :parroquia"),
		@NamedQuery(name = "Parroquia.findByCabecera", query = "SELECT p FROM Parroquia p WHERE p.cabecera = :cabecera") })
public class Parroquia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3277179329566724369L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PARROQUIA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short secParroquia;
	@Basic(optional = false)
	@Column(name = "PARROQUIA")
	private String parroquia;
	@Basic(optional = false)
	@Column(name = "CABECERA")
	private char cabecera;
	@JoinColumn(name = "SEC_CANTON", referencedColumnName = "SEC_CANTON")
	@ManyToOne(optional = false)
	@XmlTransient
	private Canton canton;

	public Parroquia() {
	}

	public Parroquia(Short secParroquia) {
		this.secParroquia = secParroquia;
	}

	public Parroquia(Short secParroquia, String parroquia) {
		this.secParroquia = secParroquia;
		this.parroquia = parroquia;
	}

	public Parroquia(Short secParroquia, String parroquia, char cabecera) {
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

	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
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
		if (!(object instanceof Parroquia)) {
			return false;
		}
		Parroquia other = (Parroquia) object;
		if ((this.secParroquia == null && other.secParroquia != null)
				|| (this.secParroquia != null && !this.secParroquia
						.equals(other.secParroquia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Parroquia[secParroquia=" + secParroquia
				+ "]";
	}

}
