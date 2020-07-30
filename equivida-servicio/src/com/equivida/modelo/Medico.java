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
@Table(name = "MEDICO")
@NamedQueries({
		@NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m"),
		@NamedQuery(name = "Medico.findBySecMedico", query = "SELECT m FROM Medico m WHERE m.secMedico = :secMedico"),
		@NamedQuery(name = "Medico.findByApellidosMedico", query = "SELECT m FROM Medico m WHERE m.apellidosMedico = :apellidosMedico"),
		@NamedQuery(name = "Medico.findByNombresMedico", query = "SELECT m FROM Medico m WHERE m.nombresMedico = :nombresMedico"),
		@NamedQuery(name = "Medico.findBySecEspecialidad", query = "SELECT m FROM Medico m WHERE m.secEspecialidad = :secEspecialidad") })
public class Medico implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1054156401240278339L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_MEDICO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secMedico;
	@Basic(optional = false)
	@Column(name = "APELLIDOS_MEDICO")
	private String apellidosMedico;
	@Basic(optional = false)
	@Column(name = "NOMBRES_MEDICO")
	private String nombresMedico;
	@Basic(optional = false)
	@Column(name = "SEC_ESPECIALIDAD")
	private short secEspecialidad;
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "medico")
	//private Collection<MedicoConsultado> medicoConsultadoCollection;

	public Medico() {
	}

	public Medico(Integer secMedico) {
		this.secMedico = secMedico;
	}

	public Medico(Integer secMedico, String apellidosMedico,
			String nombresMedico, short secEspecialidad) {
		this.secMedico = secMedico;
		this.apellidosMedico = apellidosMedico;
		this.nombresMedico = nombresMedico;
		this.secEspecialidad = secEspecialidad;
	}

	public Integer getSecMedico() {
		return secMedico;
	}

	public void setSecMedico(Integer secMedico) {
		this.secMedico = secMedico;
	}

	public String getApellidosMedico() {
		return apellidosMedico;
	}

	public void setApellidosMedico(String apellidosMedico) {
		this.apellidosMedico = apellidosMedico;
	}

	public String getNombresMedico() {
		return nombresMedico;
	}

	public void setNombresMedico(String nombresMedico) {
		this.nombresMedico = nombresMedico;
	}

	public short getSecEspecialidad() {
		return secEspecialidad;
	}

	public void setSecEspecialidad(short secEspecialidad) {
		this.secEspecialidad = secEspecialidad;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secMedico != null ? secMedico.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Medico)) {
			return false;
		}
		Medico other = (Medico) object;
		if ((this.secMedico == null && other.secMedico != null)
				|| (this.secMedico != null && !this.secMedico
						.equals(other.secMedico))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Medico[secMedico=" + secMedico + "]";
	}

}
