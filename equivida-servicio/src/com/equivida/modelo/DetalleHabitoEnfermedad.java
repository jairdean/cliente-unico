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
@Table(name = "DETALLE_HABITO_ENFERMEDAD")
@NamedQueries({
		@NamedQuery(name = "DetalleHabitoEnfermedad.findAll", query = "SELECT d FROM DetalleHabitoEnfermedad d"),
		@NamedQuery(name = "DetalleHabitoEnfermedad.findBySecDetalle", query = "SELECT d FROM DetalleHabitoEnfermedad d WHERE d.secDetalle = :secDetalle"),
		@NamedQuery(name = "DetalleHabitoEnfermedad.findByDetalle", query = "SELECT d FROM DetalleHabitoEnfermedad d WHERE d.detalle = :detalle") })
public class DetalleHabitoEnfermedad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -98322053636505137L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_DETALLE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secDetalle;
	@Basic(optional = false)
	@Column(name = "DETALLE")
	private String detalle;
	@JoinColumn(name = "SEC_HABITO_ENFERMEDAD", referencedColumnName = "SEC_HABITO_ENFERMEDAD")
	@ManyToOne(optional = false)
	private HabitoEnfermedad habitoEnfermedad;
	@JoinColumn(name = "COD_PREGUNTA", referencedColumnName = "COD_PREGUNTA")
	@ManyToOne(optional = false)
	private PreguntaHabitoEnfermedad preguntaHabitoEnfermedad;

	@Transient
	private Double detalleDouble;

	public DetalleHabitoEnfermedad() {
	}

	public DetalleHabitoEnfermedad(Integer secDetalle) {
		this.secDetalle = secDetalle;
	}

	public DetalleHabitoEnfermedad(Integer secDetalle, String detalle) {
		this.secDetalle = secDetalle;
		this.detalle = detalle;
	}

	public Integer getSecDetalle() {
		return secDetalle;
	}

	public void setSecDetalle(Integer secDetalle) {
		this.secDetalle = secDetalle;
	}

	public HabitoEnfermedad getHabitoEnfermedad() {
		return habitoEnfermedad;
	}

	public void setHabitoEnfermedad(HabitoEnfermedad habitoEnfermedad) {
		this.habitoEnfermedad = habitoEnfermedad;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secDetalle != null ? secDetalle.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DetalleHabitoEnfermedad)) {
			return false;
		}
		DetalleHabitoEnfermedad other = (DetalleHabitoEnfermedad) object;
		if ((this.secDetalle == null && other.secDetalle != null)
				|| (this.secDetalle != null && !this.secDetalle
						.equals(other.secDetalle))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.DetalleHabitoEnfermedad[secDetalle="
				+ secDetalle + "]";
	}

	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(String detalle) {
		if (detalle != null) {
			this.detalle = StringUtil.toUpper(detalle).trim();
		}
	}

	public PreguntaHabitoEnfermedad getPreguntaHabitoEnfermedad() {
		return preguntaHabitoEnfermedad;
	}

	public void setPreguntaHabitoEnfermedad(
			PreguntaHabitoEnfermedad preguntaHabitoEnfermedad) {
		this.preguntaHabitoEnfermedad = preguntaHabitoEnfermedad;
	}

	public Double getDetalleDouble() {
		if (detalleDouble == null) {
			if (detalle != null) {
				try {
					detalleDouble = Double.parseDouble(detalle);
				} catch (NumberFormatException e) {
					System.out
							.println("ERROR: no se puede transformar a numerico: "
									+ detalle);
					detalleDouble = null;
				}
			}
		}
		return detalleDouble;
	}

	public void setDetalleDouble(Double detalleDouble) {
		this.detalleDouble = detalleDouble;
	}

}
