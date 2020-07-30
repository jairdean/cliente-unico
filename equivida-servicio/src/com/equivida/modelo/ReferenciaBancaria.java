/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "REFERENCIA_BANCARIA")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferenciaBancaria implements Serializable {

	private static final long serialVersionUID = 4940834262459043776L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_REFERENCIA_BANCARIA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secReferenciaBancaria;

	@JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName = "SEC_PERSONA_NATURAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private PersonaNatural personaNatural;

	@JoinColumn(name = "SEC_INSTITUCION_FINANCIERA", referencedColumnName = "SEC_INSTITUCION_FINANCIERA")
	@ManyToOne(optional = false)
	private InstitucionFinanciera institucionFinanciera;

	@JoinColumn(name = "SEC_TIPO_SERVICIO_INST_FIN", referencedColumnName = "SEC_TIPO_SERVICIO_INST_FIN")
	@ManyToOne(optional = false)
	private TipoServicioInstFin tipoServicioInstFin;

	/**
	 * Para el combo en la parte web
	 */
	@Transient
	@XmlTransient
	private List<TipoServicioInstFin> tipoServicioInstFinServicioLista;

	/**
	 * Para el formulario web
	 * 
	 * @return
	 */
	@Transient
	public Map<String, Short> getTipoServicioInsFinItems() {
		Map<String, Short> listaItems = new LinkedHashMap<String, Short>();
		if (this.tipoServicioInstFinServicioLista != null) {

			for (TipoServicioInstFin tsif : this.tipoServicioInstFinServicioLista) {
				String label = tsif.getTipoServicioFinanciero()
						.getTipoServicio();
				Short value = tsif.getSecTipoServicioInstFin();
				listaItems.put(label, value);
			}
		}

		return listaItems;
	}

	/**
	 * @return the secReferenciaBancaria
	 */
	public Integer getSecReferenciaBancaria() {
		return secReferenciaBancaria;
	}

	/**
	 * @param secReferenciaBancaria
	 *            the secReferenciaBancaria to set
	 */
	public void setSecReferenciaBancaria(Integer secReferenciaBancaria) {
		this.secReferenciaBancaria = secReferenciaBancaria;
	}

	/**
	 * @return the personaNatural
	 */
	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	/**
	 * @param personaNatural
	 *            the personaNatural to set
	 */
	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	/**
	 * @return the institucionFinanciera
	 */
	public InstitucionFinanciera getInstitucionFinanciera() {
		return institucionFinanciera;
	}

	/**
	 * @param institucionFinanciera
	 *            the institucionFinanciera to set
	 */
	public void setInstitucionFinanciera(
			InstitucionFinanciera institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}

	public TipoServicioInstFin getTipoServicioInstFin() {
		return tipoServicioInstFin;
	}

	public void setTipoServicioInstFin(TipoServicioInstFin tipoServicioInstFin) {
		this.tipoServicioInstFin = tipoServicioInstFin;
	}

	public List<TipoServicioInstFin> getTipoServicioInstFinServicioLista() {
		return tipoServicioInstFinServicioLista;
	}

	public void setTipoServicioInstFinServicioLista(
			List<TipoServicioInstFin> tipoServicioInstFinServicioLista) {
		this.tipoServicioInstFinServicioLista = tipoServicioInstFinServicioLista;
	}
}