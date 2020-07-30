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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "HISTORIA_MEDICA_FAMILIAR")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "HistoriaMedicaFamiliar.findAll", query = "SELECT h FROM HistoriaMedicaFamiliar h"),
		@NamedQuery(name = "HistoriaMedicaFamiliar.findBySecHistoriaMedica", query = "SELECT h FROM HistoriaMedicaFamiliar h WHERE h.secHistoriaMedica = :secHistoriaMedica"),
		@NamedQuery(name = "HistoriaMedicaFamiliar.findByEdad", query = "SELECT h FROM HistoriaMedicaFamiliar h WHERE h.edad = :edad"),
		@NamedQuery(name = "HistoriaMedicaFamiliar.findByEdadDiagnostico", query = "SELECT h FROM HistoriaMedicaFamiliar h WHERE h.edadDiagnostico = :edadDiagnostico"),
		@NamedQuery(name = "HistoriaMedicaFamiliar.findByVivo", query = "SELECT h FROM HistoriaMedicaFamiliar h WHERE h.vivo = :vivo"),
		@NamedQuery(name = "HistoriaMedicaFamiliar.findByDetalles", query = "SELECT h FROM HistoriaMedicaFamiliar h WHERE h.detalles = :detalles") })
public class HistoriaMedicaFamiliar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6092894970794049205L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_HISTORIA_MEDICA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secHistoriaMedica;
	@Basic(optional = false)
	@Column(name = "EDAD")
	private short edad;
	@Basic(optional = false)
	@Column(name = "EDAD_DIAGNOSTICO")
	private short edadDiagnostico;// para difunto
	@Basic(optional = false)
	@Column(name = "VIVO")
	private char vivo;
	@Basic(optional = false)
	@Column(name = "DETALLES")
	private String detalles;
	@JoinColumn(name = "COD_TIPO_PARENTESCO", referencedColumnName = "COD_TIPO_PARENTESCO")
	@ManyToOne(optional = false)
	private TipoParentescoRelacion tipoParentescoRelacion;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	@Transient
	private short edadM;// para difunto
	@Transient
	private String detallesM;// para difunto

	public HistoriaMedicaFamiliar() {
	}

	public HistoriaMedicaFamiliar(Integer secHistoriaMedica) {
		this.secHistoriaMedica = secHistoriaMedica;
	}

	public HistoriaMedicaFamiliar(Integer secHistoriaMedica, short edad,
			short edadDiagnostico, char vivo, String detalles) {
		this.secHistoriaMedica = secHistoriaMedica;
		this.edad = edad;
		this.edadDiagnostico = edadDiagnostico;
		this.vivo = vivo;
		this.detalles = detalles;
	}

	public Integer getSecHistoriaMedica() {
		return secHistoriaMedica;
	}

	public void setSecHistoriaMedica(Integer secHistoriaMedica) {
		this.secHistoriaMedica = secHistoriaMedica;
	}

	public short getEdad() {
		return edad;
	}

	public void setEdad(short edad) {
		this.edad = edad;
	}

	public short getEdadDiagnostico() {
		return edadDiagnostico;
	}

	public void setEdadDiagnostico(short edadDiagnostico) {
		this.edadDiagnostico = edadDiagnostico;
	}

	public char getVivo() {
		return vivo;
	}

	public void setVivo(char vivo) {
		this.vivo = vivo;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public TipoParentescoRelacion getTipoParentescoRelacion() {
		return tipoParentescoRelacion;
	}

	public void setTipoParentescoRelacion(
			TipoParentescoRelacion tipoParentescoRelacion) {
		this.tipoParentescoRelacion = tipoParentescoRelacion;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public short getEdadM() {
		return edadM;
	}

	public void setEdadM(short edadM) {
		this.edadM = edadM;
	}

	public String getDetallesM() {
		return detallesM;
	}

	public void setDetallesM(String detallesM) {
		this.detallesM = detallesM;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((detalles == null) ? 0 : detalles.hashCode());
		result = prime * result
				+ ((detallesM == null) ? 0 : detallesM.hashCode());
		result = prime * result + edad;
		result = prime * result + edadDiagnostico;
		result = prime * result + edadM;
		result = prime * result
				+ ((personaNatural == null) ? 0 : personaNatural.hashCode());
		result = prime
				* result
				+ ((secHistoriaMedica == null) ? 0 : secHistoriaMedica
						.hashCode());
		result = prime
				* result
				+ ((tipoParentescoRelacion == null) ? 0
						: tipoParentescoRelacion.hashCode());
		result = prime * result + vivo;
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
		HistoriaMedicaFamiliar other = (HistoriaMedicaFamiliar) obj;
		if (detalles == null) {
			if (other.detalles != null)
				return false;
		} else if (!detalles.equals(other.detalles))
			return false;
		if (detallesM == null) {
			if (other.detallesM != null)
				return false;
		} else if (!detallesM.equals(other.detallesM))
			return false;
		if (edad != other.edad)
			return false;
		if (edadDiagnostico != other.edadDiagnostico)
			return false;
		if (edadM != other.edadM)
			return false;
		if (personaNatural == null) {
			if (other.personaNatural != null)
				return false;
		} else if (!personaNatural.equals(other.personaNatural))
			return false;
		if (secHistoriaMedica == null) {
			if (other.secHistoriaMedica != null)
				return false;
		} else if (!secHistoriaMedica.equals(other.secHistoriaMedica))
			return false;
		if (tipoParentescoRelacion == null) {
			if (other.tipoParentescoRelacion != null)
				return false;
		} else if (!tipoParentescoRelacion.equals(other.tipoParentescoRelacion))
			return false;
		if (vivo != other.vivo)
			return false;
		return true;
	}

}
