/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

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

import com.equivida.constant.EncontradoBeneficiarioEnum;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "BENEFICIARIO_POLIZA")
@NamedQueries({
		@NamedQuery(name = "BeneficiarioPoliza.findAll", query = "SELECT b FROM BeneficiarioPoliza b"),
		@NamedQuery(name = "BeneficiarioPoliza.findBySecBeneficiarioPoliza", query = "SELECT b FROM BeneficiarioPoliza b WHERE b.secBeneficiarioPoliza = :secBeneficiarioPoliza"),
		@NamedQuery(name = "BeneficiarioPoliza.findByPctBeneficio", query = "SELECT b FROM BeneficiarioPoliza b WHERE b.pctBeneficio = :pctBeneficio"),
		@NamedQuery(name = "BeneficiarioPoliza.findByTipoBeneficiario", query = "SELECT b FROM BeneficiarioPoliza b WHERE b.tipoBeneficiario = :tipoBeneficiario") })
public class BeneficiarioPoliza implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2561624213757881545L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_BENEFICIARIO_POLIZA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secBeneficiarioPoliza;
	@Basic(optional = false)
	@Column(name = "PCT_BENEFICIO", precision = 9, scale = 6, columnDefinition = "decimal(9,6)")
	private BigDecimal pctBeneficio;
	@Basic(optional = false)
	@Column(name = "TIPO_BENEFICIARIO")
	private char tipoBeneficiario;
	@JoinColumn(name = "SEC_INTERVINIENTE", referencedColumnName = "SEC_INTERVINIENTE")
	@ManyToOne(optional = false)
	private Interviniente interviniente;
	@JoinColumn(name = "SEC_BENEFICIARIO", referencedColumnName = "SEC_BENEFICIARIO")
	@ManyToOne(optional = false)
	private Beneficiario beneficiario;

	@JoinColumn(name = "COD_TIPO_PARENTESCO", referencedColumnName = "COD_TIPO_PARENTESCO")
	@ManyToOne(optional = false)
	private TipoParentescoRelacion tipoParentescoRelacion;

	@Transient
	private EncontradoBeneficiarioEnum encontradoBeneficiarioEnum;

	public BeneficiarioPoliza() {
	}

	public BeneficiarioPoliza(Integer secBeneficiarioPoliza) {
		this.secBeneficiarioPoliza = secBeneficiarioPoliza;
	}

	public BeneficiarioPoliza(Integer secBeneficiarioPoliza,
			BigDecimal pctBeneficio, char tipoBeneficiario) {
		this.secBeneficiarioPoliza = secBeneficiarioPoliza;
		this.pctBeneficio = pctBeneficio;
		this.tipoBeneficiario = tipoBeneficiario;
	}

	public Integer getSecBeneficiarioPoliza() {
		return secBeneficiarioPoliza;
	}

	public void setSecBeneficiarioPoliza(Integer secBeneficiarioPoliza) {
		this.secBeneficiarioPoliza = secBeneficiarioPoliza;
	}

	public BigDecimal getPctBeneficio() {
		return pctBeneficio;
	}

	public void setPctBeneficio(BigDecimal pctBeneficio) {
		this.pctBeneficio = pctBeneficio;
	}

	public char getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	public void setTipoBeneficiario(char tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	public Interviniente getInterviniente() {
		return interviniente;
	}

	public void setInterviniente(Interviniente interviniente) {
		this.interviniente = interviniente;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beneficiario == null) ? 0 : beneficiario.hashCode());
		result = prime
				* result
				+ ((encontradoBeneficiarioEnum == null) ? 0
						: encontradoBeneficiarioEnum.hashCode());
		result = prime * result
				+ ((interviniente == null) ? 0 : interviniente.hashCode());
		result = prime * result
				+ ((pctBeneficio == null) ? 0 : pctBeneficio.hashCode());
		result = prime
				* result
				+ ((secBeneficiarioPoliza == null) ? 0 : secBeneficiarioPoliza
						.hashCode());
		result = prime * result + tipoBeneficiario;
		result = prime
				* result
				+ ((tipoParentescoRelacion == null) ? 0
						: tipoParentescoRelacion.hashCode());
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
		BeneficiarioPoliza other = (BeneficiarioPoliza) obj;
		if (beneficiario == null) {
			if (other.beneficiario != null)
				return false;
		} else if (!beneficiario.equals(other.beneficiario))
			return false;
		if (encontradoBeneficiarioEnum != other.encontradoBeneficiarioEnum)
			return false;
		if (interviniente == null) {
			if (other.interviniente != null)
				return false;
		} else if (!interviniente.equals(other.interviniente))
			return false;
		if (pctBeneficio == null) {
			if (other.pctBeneficio != null)
				return false;
		} else if (!pctBeneficio.equals(other.pctBeneficio))
			return false;
		if (secBeneficiarioPoliza == null) {
			if (other.secBeneficiarioPoliza != null)
				return false;
		} else if (!secBeneficiarioPoliza.equals(other.secBeneficiarioPoliza))
			return false;
		if (tipoBeneficiario != other.tipoBeneficiario)
			return false;
		if (tipoParentescoRelacion == null) {
			if (other.tipoParentescoRelacion != null)
				return false;
		} else if (!tipoParentescoRelacion.equals(other.tipoParentescoRelacion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.BeneficiarioPoliza[secBeneficiarioPoliza="
				+ secBeneficiarioPoliza + "]";
	}

	public TipoParentescoRelacion getTipoParentescoRelacion() {
		return tipoParentescoRelacion;
	}

	public void setTipoParentescoRelacion(
			TipoParentescoRelacion tipoParentescoRelacion) {
		this.tipoParentescoRelacion = tipoParentescoRelacion;
	}

	/**
	 * @return the encontradoBeneficiarioEnum
	 */
	public EncontradoBeneficiarioEnum getEncontradoBeneficiarioEnum() {
		return encontradoBeneficiarioEnum;
	}

	/**
	 * @param encontradoBeneficiarioEnum
	 *            the encontradoBeneficiarioEnum to set
	 */
	public void setEncontradoBeneficiarioEnum(
			EncontradoBeneficiarioEnum encontradoBeneficiarioEnum) {
		this.encontradoBeneficiarioEnum = encontradoBeneficiarioEnum;
	}

}
