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

import com.equivida.util.StringUtil;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "BENEFICIARIO")
@NamedQueries({
		@NamedQuery(name = "Beneficiario.findAll", query = "SELECT b FROM Beneficiario b"),
		@NamedQuery(name = "Beneficiario.findBySecBeneficiario", query = "SELECT b FROM Beneficiario b WHERE b.secBeneficiario = :secBeneficiario"),
		@NamedQuery(name = "Beneficiario.findByIdentificacion", query = "SELECT b FROM Beneficiario b WHERE b.identificacion = :identificacion"),
		@NamedQuery(name = "Beneficiario.findByApellidoPaterno", query = "SELECT b FROM Beneficiario b WHERE b.apellidoPaterno = :apellidoPaterno"),
		@NamedQuery(name = "Beneficiario.findByApellidoMaterno", query = "SELECT b FROM Beneficiario b WHERE b.apellidoMaterno = :apellidoMaterno"),
		@NamedQuery(name = "Beneficiario.findByPrimerNombre", query = "SELECT b FROM Beneficiario b WHERE b.primerNombre = :primerNombre"),
		@NamedQuery(name = "Beneficiario.findBySegundoNombre", query = "SELECT b FROM Beneficiario b WHERE b.segundoNombre = :segundoNombre"),
		@NamedQuery(name = "Beneficiario.findByDenominacion", query = "SELECT b FROM Beneficiario b WHERE b.denominacion = :denominacion") })
public class Beneficiario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6202251495802054764L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_BENEFICIARIO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secBeneficiario;
	@Column(name = "IDENTIFICACION")
	private String identificacion;
	@Column(name = "APELLIDO_PATERNO")
	private String apellidoPaterno;
	@Column(name = "APELLIDO_MATERNO")
	private String apellidoMaterno;
	@Column(name = "PRIMER_NOMBRE")
	private String primerNombre;
	@Column(name = "SEGUNDO_NOMBRE")
	private String segundoNombre;
	@Column(name = "DENOMINACION")
	private String denominacion;
	@JoinColumn(name = "COD_TIPO_IDENTIFICACION", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = false)
	private TipoIdentificacion tipoIdentificacion;

	@Transient
	private String apellidos;

	@Transient
	private String nombres;

	@Transient
	private String nombresApellidos;

	public Beneficiario() {
	}

	public Beneficiario(Integer secBeneficiario) {
		this.secBeneficiario = secBeneficiario;
	}

	public Integer getSecBeneficiario() {
		return secBeneficiario;
	}

	public void setSecBeneficiario(Integer secBeneficiario) {
		this.secBeneficiario = secBeneficiario;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = StringUtil.toUpper(identificacion).trim();
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		if (apellidoPaterno != null) {
			this.apellidoPaterno = StringUtil.toUpper(apellidoPaterno).trim();
		} else {
			this.apellidoPaterno = apellidoPaterno;
		}
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		if (apellidoMaterno != null) {
			this.apellidoMaterno = StringUtil.toUpper(apellidoMaterno).trim();
		} else {
			this.apellidoMaterno = apellidoMaterno;
		}
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = StringUtil.toUpper(denominacion).trim();
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellidoMaterno == null) ? 0 : apellidoMaterno.hashCode());
		result = prime * result
				+ ((apellidoPaterno == null) ? 0 : apellidoPaterno.hashCode());
		result = prime * result
				+ ((denominacion == null) ? 0 : denominacion.hashCode());
		result = prime * result
				+ ((identificacion == null) ? 0 : identificacion.hashCode());
		result = prime * result
				+ ((primerNombre == null) ? 0 : primerNombre.hashCode());
		result = prime * result
				+ ((secBeneficiario == null) ? 0 : secBeneficiario.hashCode());
		result = prime * result
				+ ((segundoNombre == null) ? 0 : segundoNombre.hashCode());
		result = prime
				* result
				+ ((tipoIdentificacion == null) ? 0 : tipoIdentificacion
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
		Beneficiario other = (Beneficiario) obj;
		if (apellidoMaterno == null) {
			if (other.apellidoMaterno != null)
				return false;
		} else if (!apellidoMaterno.equals(other.apellidoMaterno))
			return false;
		if (apellidoPaterno == null) {
			if (other.apellidoPaterno != null)
				return false;
		} else if (!apellidoPaterno.equals(other.apellidoPaterno))
			return false;
		if (denominacion == null) {
			if (other.denominacion != null)
				return false;
		} else if (!denominacion.equals(other.denominacion))
			return false;
		if (identificacion == null) {
			if (other.identificacion != null)
				return false;
		} else if (!identificacion.equals(other.identificacion))
			return false;
		if (primerNombre == null) {
			if (other.primerNombre != null)
				return false;
		} else if (!primerNombre.equals(other.primerNombre))
			return false;
		if (secBeneficiario == null) {
			if (other.secBeneficiario != null)
				return false;
		} else if (!secBeneficiario.equals(other.secBeneficiario))
			return false;
		if (segundoNombre == null) {
			if (other.segundoNombre != null)
				return false;
		} else if (!segundoNombre.equals(other.segundoNombre))
			return false;
		if (tipoIdentificacion == null) {
			if (other.tipoIdentificacion != null)
				return false;
		} else if (!tipoIdentificacion.equals(other.tipoIdentificacion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Beneficiario[secBeneficiario="
				+ secBeneficiario + "]";
	}

	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @param primerNombre
	 *            the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		if (primerNombre != null) {
			this.primerNombre = StringUtil.toUpper(primerNombre).trim();
		} else {
			this.primerNombre = null;
		}
	}

	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @param segundoNombre
	 *            the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		if (segundoNombre != null) {
			this.segundoNombre = StringUtil.toUpper(segundoNombre).trim();
		} else {
			this.segundoNombre = null;
		}
	}

	public String getNombres() {
		if (nombres == null) {
			nombres = getPrimerNombre() + " " + getSegundoNombre();
		}
		return nombres;
	}

	public void setNombres(String nombres) {
		if (nombres != null) {
			this.nombres = nombres.toUpperCase();
		} else {
			this.nombres = nombres;
		}
	}

	public String getNombresApellidos() {
		if (nombresApellidos == null) {
			if (primerNombre == null) {
				primerNombre = "";
			}
			if (segundoNombre == null) {
				segundoNombre = "";
			}
			if (apellidoPaterno == null) {
				apellidoPaterno = "";
			}
			if (apellidoMaterno == null) {
				apellidoMaterno = "";
			}

			nombresApellidos = primerNombre.trim() + " " + segundoNombre.trim()
					+ " " + apellidoPaterno.trim() + " "
					+ apellidoMaterno.trim();
		}
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getApellidos() {
		if (apellidos == null) {
			if (apellidoPaterno == null) {
				apellidoPaterno = "";
			}
			if (apellidoMaterno == null) {
				apellidoMaterno = "";
			}
			apellidos = apellidoPaterno + " " + apellidoMaterno;
		}
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

}
