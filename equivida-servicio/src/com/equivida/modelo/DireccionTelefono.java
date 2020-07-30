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

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "DIRECCION_TELEFONO")
@NamedQueries({ @NamedQuery(name = "DireccionTelefono.findAll", query = "SELECT d FROM DireccionTelefono d") })
public class DireccionTelefono implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3988524028214825632L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_DIRECCION_TELEFONO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secDireccionTelefono;
	@JoinColumn(name = "SEC_TELEFONO", referencedColumnName = "SEC_TELEFONO")
	@ManyToOne(optional = false)
	private Telefono telefono;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	private Persona persona;
	@JoinColumn(name = "SEC_DIRECCION", referencedColumnName = "SEC_DIRECCION")
	@ManyToOne(optional = false)
	private Direccion direccion;

	// auditoria
	@Column(name = "USR_CREACION")
	private String usrCreacion;

	@Column(name = "TTY_CREACION")
	private String ttyCreacion;// terminal

	@Column(name = "PRG_CREACION")
	private String prgCreacion;

	@Column(name = "CTA_CREACION")
	private String ctaCreacion;

	@Column(name = "USR_MODIFICACION")
	private String usrModificacion;

	@Column(name = "TTY_MODIFICACION")
	private String ttyModificacion;

	@Column(name = "PRG_MODIFICACION")
	private String prgModificacion;

	@Column(name = "CTA_MODIFICACION")
	private String ctaModificacion;

	@Transient
	private DireccionTelefono original;

	public DireccionTelefono() {
	}

	public DireccionTelefono(Integer secDireccionTelefono) {
		this.secDireccionTelefono = secDireccionTelefono;
	}

	public Integer getSecDireccionTelefono() {
		return secDireccionTelefono;
	}

	public void setSecDireccionTelefono(Integer secDireccionTelefono) {
		this.secDireccionTelefono = secDireccionTelefono;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.DireccionTelefono[secDireccionTelefono=" + secDireccionTelefono + "]";
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		result = prime * result + ((secDireccionTelefono == null) ? 0 : secDireccionTelefono.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DireccionTelefono other = (DireccionTelefono) obj;
		if (direccion == null) {
			if (other.direccion != null) {
				return false;
			}
		} else if (!direccion.equals(other.direccion)) {
			return false;
		}
		if (persona == null) {
			if (other.persona != null) {
				return false;
			}
		} else if (!persona.equals(other.persona)) {
			return false;
		}
		if (secDireccionTelefono == null) {
			if (other.secDireccionTelefono != null) {
				return false;
			}
		} else if (!secDireccionTelefono.equals(other.secDireccionTelefono)) {
			return false;
		}
		if (telefono == null) {
			if (other.telefono != null) {
				return false;
			}
		} else if (!telefono.equals(other.telefono)) {
			return false;
		}
		return true;
	}

	public String getUsrCreacion() {
		return usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public String getTtyCreacion() {
		return ttyCreacion;
	}

	public void setTtyCreacion(String ttyCreacion) {
		this.ttyCreacion = ttyCreacion;
	}

	public String getPrgCreacion() {
		return prgCreacion;
	}

	public void setPrgCreacion(String prgCreacion) {
		this.prgCreacion = prgCreacion;
	}

	public String getCtaCreacion() {
		return ctaCreacion;
	}

	public void setCtaCreacion(String ctaCreacion) {
		this.ctaCreacion = ctaCreacion;
	}

	public String getUsrModificacion() {
		return usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public String getTtyModificacion() {
		return ttyModificacion;
	}

	public void setTtyModificacion(String ttyModificacion) {
		this.ttyModificacion = ttyModificacion;
	}

	public String getPrgModificacion() {
		return prgModificacion;
	}

	public void setPrgModificacion(String prgModificacion) {
		this.prgModificacion = prgModificacion;
	}

	public String getCtaModificacion() {
		return ctaModificacion;
	}

	public void setCtaModificacion(String ctaModificacion) {
		this.ctaModificacion = ctaModificacion;
	}

	public DireccionTelefono getOriginal() {
		return original;
	}

	public void setOriginal(DireccionTelefono original) {
		this.original = original;
	}

}
