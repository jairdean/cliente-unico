/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "ACTIVIDAD_ECONOMICA")
@NamedQueries({ @NamedQuery(name = "ActividadEconomica.findAll", query = "SELECT a FROM ActividadEconomica a"),
		@NamedQuery(name = "ActividadEconomica.findByCodActividadEconomica", query = "SELECT a FROM ActividadEconomica a WHERE a.codActividadEconomica = :codActividadEconomica"),
		@NamedQuery(name = "ActividadEconomica.findByActividadEconomica", query = "SELECT a FROM ActividadEconomica a WHERE a.actividadEconomica = :actividadEconomica") })
public class ActividadEconomica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -41747613374795811L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_ACTIVIDAD_ECONOMICA")
	private Short codActividadEconomica;
	@Basic(optional = false)
	@Column(name = "ACTIVIDAD_ECONOMICA")
	private String actividadEconomica;
	
	@Column(name = "COD_CIIU")
	private String codCiiu;
	
	@Column(name = "COD_CIUU_PADRE")
	private String codCiiuPadre;

	@Column(name = "CODIGO_VISIBLE")
	private char codigoVisible;

	@Column(name = "NIVEL_CIIU")
	private Short nivelCiiu;

	@Transient
	private String actividadEconomicaNivel2Transient;

	@Transient
	private String actividadEconomicaNivel1Transient;

	public ActividadEconomica() {
	}

	public ActividadEconomica(Short codActividadEconomica) {
		this.codActividadEconomica = codActividadEconomica;
	}

	public ActividadEconomica(Short codActividadEconomica, String actividadEconomica) {
		this.codActividadEconomica = codActividadEconomica;
		this.actividadEconomica = actividadEconomica;
	}

	public Short getCodActividadEconomica() {
		return codActividadEconomica;
	}

	public void setCodActividadEconomica(Short codActividadEconomica) {
		this.codActividadEconomica = codActividadEconomica;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codActividadEconomica != null ? codActividadEconomica.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ActividadEconomica)) {
			return false;
		}
		ActividadEconomica other = (ActividadEconomica) object;
		if ((this.codActividadEconomica == null && other.codActividadEconomica != null)
				|| (this.codActividadEconomica != null
						&& !this.codActividadEconomica.equals(other.codActividadEconomica))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.ActividadEconomica[codActividadEconomica=" + codActividadEconomica + "]";
	}

	public char getCodigoVisible() {
		return codigoVisible;
	}

	public void setCodigoVisible(char codigoVisible) {
		this.codigoVisible = codigoVisible;
	}

	public Short getNivelCiiu() {
		return nivelCiiu;
	}

	public void setNivelCiiu(Short nivelCiiu) {
		this.nivelCiiu = nivelCiiu;
	}

	public String getActividadEconomicaNivel2Transient() {
		return actividadEconomicaNivel2Transient;
	}

	public void setActividadEconomicaNivel2Transient(String actividadEconomicaNivel2Transient) {
		this.actividadEconomicaNivel2Transient = actividadEconomicaNivel2Transient;
	}

	public String getActividadEconomicaNivel1Transient() {
		return actividadEconomicaNivel1Transient;
	}

	public void setActividadEconomicaNivel1Transient(String actividadEconomicaNivel1Transient) {
		this.actividadEconomicaNivel1Transient = actividadEconomicaNivel1Transient;
	}

	public String getCodCiiu() {
		return codCiiu;
	}

	public void setCodCiiu(String codCiiu) {
		this.codCiiu = codCiiu;
	}

	public String getCodCiiuPadre() {
		return codCiiuPadre;
	}

	public void setCodCiiuPadre(String codCiiuPadre) {
		this.codCiiuPadre = codCiiuPadre;
	}

}
