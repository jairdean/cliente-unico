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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "RUC_PERSONA_NATURAL")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "RucPersonaNatural.findAll", query = "SELECT p FROM RucPersonaNatural p"),
		@NamedQuery(name = "RucPersonaNatural.findBySecRucPersonaNatural", query = "SELECT p FROM RucPersonaNatural p WHERE p.secRucPersonaNatural = :secRucPersonaNatural"),
		@NamedQuery(name = "RucPersonaNatural.findByIdentificacion", query = "SELECT p FROM RucPersonaNatural p WHERE p.identificacion = :identificacion") })
public class RucPersonaNatural implements Serializable {

	private static final long serialVersionUID = -3646108173778980847L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_RUC_PERSONA_NATURAL")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secRucPersonaNatural;

	// @Basic(optional = false)
	// @Column(name = "COD_TIPO_IDENTIFICACION")
	// private String codTipoIdentificacion;

	@JoinColumn(name = "COD_TIPO_IDENTIFICACION", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = false)
	private TipoIdentificacion tipoIdentificacion;

	@Basic(optional = false)
	@Column(name = "IDENTIFICACION", columnDefinition = "char")
	private String identificacion;

	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne
	private PersonaNatural personaNatural;

	public Integer getSecRucPersonaNatural() {
		return secRucPersonaNatural;
	}

	public void setSecRucPersonaNatural(Integer secRucPersonaNatural) {
		this.secRucPersonaNatural = secRucPersonaNatural;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

}