/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JAIRO
 */
@Entity
@Table(name = "SUCURSAL")
public class SucursalSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_SUCURSAL")
	private Integer secSucursal;
	
	@JoinColumn(name = "SEC_PERSONA_JURIDICA", referencedColumnName = "SEC_PERSONA_JURIDICA")
	@ManyToOne(optional = true)
	@XmlTransient
	private PersonaJuridicaSd personaJuridica;

	
	@Size(min = 1, max = 5)
	@Column(name = "COD_SUCURSAL")
	@XmlTransient
	private String codSucursal;
	
	public SucursalSd() {
	}

	public SucursalSd(Integer secSucursal) {
		this.secSucursal = secSucursal;
	}

	public SucursalSd(Integer secSucursal, String codSucursal) {
		this.secSucursal = secSucursal;
		this.codSucursal = codSucursal;
	}

	
	public Integer getSecSucursal() {
		return secSucursal;
	}

	public void setSecSucursal(Integer secSucursal) {
		this.secSucursal = secSucursal;
	}

	public PersonaJuridicaSd getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridicaSd personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public String getCodSucursal() {
		return codSucursal;
	}

	public void setCodSucursal(String codSucursal) {
		this.codSucursal = codSucursal;
	}

}
