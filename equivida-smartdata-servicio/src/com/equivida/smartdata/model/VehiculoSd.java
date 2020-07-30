/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "VEHICULO")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehiculoSd implements Serializable {
	private static final long serialVersionUID = 2470690818052997584L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_VEHICULO")
	private Integer secVehiculo;
	@Basic(optional = false)
	@NotNull
	@Column(name = "PLACA")
	private String placa;
	@Basic(optional = false)
	@NotNull
	@Column(name = "CHASIS")
	private String chasis;
	@Basic(optional = false)
	@NotNull
	@Column(name = "MARCA")
	private String marca;
	@Basic(optional = false)
	@NotNull
	@Column(name = "AVALUO", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private BigDecimal avaluo;
	@Basic(optional = false)
	@NotNull
	@Column(name = "MODELO")
	private String modelo;
	@Basic(optional = false)
	@NotNull
	@Column(name = "ANIO")
	private int anio;
	@Basic(optional = false)
	@NotNull
	@Column(name = "USR_CREACION")
	private String usrCreacion;
	@Column(name = "TS_CREACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tsCreacion;
	@Basic(optional = false)
	@NotNull
	@Column(name = "USR_MODIFICACION", columnDefinition = "char")
	private String usrModificacion;
	@Column(name = "TS_MODIFICACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tsModificacion;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaSd secPersona;
	@JoinColumn(name = "SEC_CANAL", referencedColumnName = "SEC_CANAL")
	@ManyToOne(optional = false)
	private CanalSd secCanal;

	public VehiculoSd() {
	}

	public VehiculoSd(Integer secVehiculo) {
		this.secVehiculo = secVehiculo;
	}

	public VehiculoSd(Integer secVehiculo, String placa, String chasis,
			String marca, BigDecimal avaluo, String modelo, int anio,
			String usrCreacion, Date tsCreacion, String usrModificacion,
			Date tsModificacion) {
		this.secVehiculo = secVehiculo;
		this.placa = placa;
		this.chasis = chasis;
		this.marca = marca;
		this.avaluo = avaluo;
		this.modelo = modelo;
		this.anio = anio;
		this.usrCreacion = usrCreacion;
		this.tsCreacion = tsCreacion;
		this.usrModificacion = usrModificacion;
		this.tsModificacion = tsModificacion;
	}

	public Integer getSecVehiculo() {
		return secVehiculo;
	}

	public void setSecVehiculo(Integer secVehiculo) {
		this.secVehiculo = secVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChasis() {
		return chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(BigDecimal avaluo) {
		this.avaluo = avaluo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
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

	public PersonaSd getSecPersona() {
		return secPersona;
	}

	public void setSecPersona(PersonaSd secPersona) {
		this.secPersona = secPersona;
	}

	public CanalSd getSecCanal() {
		return secCanal;
	}

	public void setSecCanal(CanalSd secCanal) {
		this.secCanal = secCanal;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secVehiculo != null ? secVehiculo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof VehiculoSd)) {
			return false;
		}
		VehiculoSd other = (VehiculoSd) object;
		if ((this.secVehiculo == null && other.secVehiculo != null)
				|| (this.secVehiculo != null && !this.secVehiculo
						.equals(other.secVehiculo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.Vehiculo[ secVehiculo="
				+ secVehiculo + " ]";
	}

}
