package com.equivida.smartdata.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "EMPLEO_DEPENDIENTE")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmpleoDependienteSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_EMPLEO_DEPENDIENTE")
	private Integer secEmpleoDependiente;
	@Basic(optional = false)
	@Column(name = "CARGO", length = 250, nullable = false)
	private String cargo;
	@Column(name = "MNT_SALARIO", precision = 12, scale = 2, columnDefinition = "decimal(12,2)")
	private BigDecimal mntSalario;
	@Basic(optional = false)
	@NotNull
	@Column(name = "FCH_INGRESO")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaIngreso")
	@XmlSchemaType(name = "date")
	private Date fchIngreso;
	@Basic
	@Column(name = "FCH_SALIDA")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaSalida")
	@XmlSchemaType(name = "date")
	private Date fchSalida;
	@Basic(optional = false)
	@NotNull
	@Column(name = "ESTADO")
	@XmlTransient
	private char estado;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "USR_CREACION")
	@XmlTransient
	private String usrCreacion;
	@Basic(optional = false)
	@Column(name = "TS_CREACION", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date tsCreacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "USR_MODIFICACION")
	@XmlTransient
	private String usrModificacion;
	@Basic(optional = false)
	@Column(name = "TS_MODIFICACION", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date tsModificacion;
	@JoinColumn(name = "SEC_CANAL", referencedColumnName = "SEC_CANAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private CanalSd secCanal;
	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNaturalSd personaNatural;
	@JoinColumn(name = "SEC_PERSONA_JURIDICA", referencedColumnName = "SEC_PERSONA_JURIDICA")
	@ManyToOne(optional = false)
	@XmlElement(name="empleador")
	private PersonaJuridicaSd personaJuridica;

	public Integer getSecEmpleoDependiente() {
		return secEmpleoDependiente;
	}

	public void setSecEmpleoDependiente(Integer secEmpleoDependiente) {
		this.secEmpleoDependiente = secEmpleoDependiente;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public BigDecimal getMntSalario() {
		return mntSalario;
	}

	public void setMntSalario(BigDecimal mntSalario) {
		this.mntSalario = mntSalario;
	}

	public Date getFchIngreso() {
		return fchIngreso;
	}

	public void setFchIngreso(Date fchIngreso) {
		this.fchIngreso = fchIngreso;
	}

	public Date getFchSalida() {
		return fchSalida;
	}

	public void setFchSalida(Date fchSalida) {
		this.fchSalida = fchSalida;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public String getUsrCreacion() {
		return usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public Date getTsCreacion() {
		return tsCreacion;
	}

	public void setTsCreacion(Date tsCreacion) {
		this.tsCreacion = tsCreacion;
	}

	public String getUsrModificacion() {
		return usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public Date getTsModificacion() {
		return tsModificacion;
	}

	public void setTsModificacion(Date tsModificacion) {
		this.tsModificacion = tsModificacion;
	}

	public CanalSd getSecCanal() {
		return secCanal;
	}

	public void setSecCanal(CanalSd secCanal) {
		this.secCanal = secCanal;
	}

	public PersonaNaturalSd getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNaturalSd personaNatural) {
		this.personaNatural = personaNatural;
	}

	public PersonaJuridicaSd getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridicaSd personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((secEmpleoDependiente == null) ? 0 : secEmpleoDependiente
						.hashCode());
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
		EmpleoDependienteSd other = (EmpleoDependienteSd) obj;
		if (secEmpleoDependiente == null) {
			if (other.secEmpleoDependiente != null)
				return false;
		} else if (!secEmpleoDependiente.equals(other.secEmpleoDependiente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.EmpleoDependiente[ secEmpleoDependiente="
				+ secEmpleoDependiente + "]";
	}

}
