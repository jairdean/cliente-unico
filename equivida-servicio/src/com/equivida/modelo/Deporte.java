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
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "DEPORTE")
public class Deporte implements Serializable {

	private static final long serialVersionUID = 3012682997953120592L;

	@Id
	@Basic(optional = false)
	@Column(name = "COD_DEPORTE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short codDeporte;

	@Column(name = "DEPORTE", length = 16)
	private String deporte;

	public Deporte() {
	}

	public Short getCodDeporte() {
		return codDeporte;
	}

	public void setCodDeporte(Short codDeporte) {
		this.codDeporte = codDeporte;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codDeporte == null) ? 0 : codDeporte.hashCode());
		result = prime * result + ((deporte == null) ? 0 : deporte.hashCode());
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
		Deporte other = (Deporte) obj;
		if (codDeporte == null) {
			if (other.codDeporte != null)
				return false;
		} else if (!codDeporte.equals(other.codDeporte))
			return false;
		if (deporte == null) {
			if (other.deporte != null)
				return false;
		} else if (!deporte.equals(other.deporte))
			return false;
		return true;
	}
}