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

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "INTERVINIENTE")
@NamedQueries({
		@NamedQuery(name = "Interviniente.findAll", query = "SELECT i FROM Interviniente i"),
		@NamedQuery(name = "Interviniente.findBySecInterviniente", query = "SELECT i FROM Interviniente i WHERE i.secInterviniente = :secInterviniente"),
		@NamedQuery(name = "Interviniente.findByNroPoliza", query = "SELECT i FROM Interviniente i WHERE i.nroPoliza = :nroPoliza"),
		@NamedQuery(name = "Interviniente.findByEstPoliza", query = "SELECT i FROM Interviniente i WHERE i.estPoliza = :estPoliza") })
public class Interviniente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 447825095259498835L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_INTERVINIENTE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secInterviniente;
	@Basic(optional = false)
	@Column(name = "NRO_POLIZA")
	private String nroPoliza;
	@Basic(optional = false)
	@Column(name = "EST_POLIZA")
	private char estPoliza;
	@JoinColumn(name = "COD_PRODUCTO", referencedColumnName = "COD_PRODUCTO")
	@ManyToOne(optional = false)
	private Producto producto;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	private Persona persona;

	public Interviniente() {
	}

	public Interviniente(Integer secInterviniente) {
		this.secInterviniente = secInterviniente;
	}

	public Interviniente(Integer secInterviniente, String nroPoliza,
			char estPoliza) {
		this.secInterviniente = secInterviniente;
		this.nroPoliza = nroPoliza;
		this.estPoliza = estPoliza;
	}

	public Integer getSecInterviniente() {
		return secInterviniente;
	}

	public void setSecInterviniente(Integer secInterviniente) {
		this.secInterviniente = secInterviniente;
	}

	public String getNroPoliza() {
		return nroPoliza;
	}

	public void setNroPoliza(String nroPoliza) {
		this.nroPoliza = nroPoliza;
	}

	public char getEstPoliza() {
		return estPoliza;
	}

	public void setEstPoliza(char estPoliza) {
		this.estPoliza = estPoliza;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + estPoliza;
		result = prime * result
				+ ((nroPoliza == null) ? 0 : nroPoliza.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		result = prime * result
				+ ((producto == null) ? 0 : producto.hashCode());
		result = prime
				* result
				+ ((secInterviniente == null) ? 0 : secInterviniente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interviniente other = (Interviniente) obj;
		if (estPoliza != other.estPoliza)
			return false;
		if (nroPoliza == null) {
			if (other.nroPoliza != null)
				return false;
		} else if (!nroPoliza.equals(other.nroPoliza))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		if (secInterviniente == null) {
			if (other.secInterviniente != null)
				return false;
		} else if (!secInterviniente.equals(other.secInterviniente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Interviniente[secInterviniente="
				+ secInterviniente + "]";
	}

}
