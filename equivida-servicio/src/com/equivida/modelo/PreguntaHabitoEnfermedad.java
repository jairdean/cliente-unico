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

import com.equivida.constant.Constantes;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PREGUNTA_HABITO_ENFERMEDAD")
@NamedQueries({
		@NamedQuery(name = "PreguntaHabitoEnfermedad.findAll", query = "SELECT p FROM PreguntaHabitoEnfermedad p"),
		@NamedQuery(name = "PreguntaHabitoEnfermedad.findByCodPregunta", query = "SELECT p FROM PreguntaHabitoEnfermedad p WHERE p.codPregunta = :codPregunta"),
		@NamedQuery(name = "PreguntaHabitoEnfermedad.findByPregunta", query = "SELECT p FROM PreguntaHabitoEnfermedad p WHERE p.pregunta = :pregunta") })
public class PreguntaHabitoEnfermedad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -428215126997194035L;
	@Id
	@Basic(optional = false)
	@Column(name = "COD_PREGUNTA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codPregunta;
	@Basic(optional = false)
	@Column(name = "PREGUNTA")
	private String pregunta;
	@JoinColumn(name = "COD_TIPO_HABITO", referencedColumnName = "COD_TIPO_HABITO")
	@ManyToOne(optional = false)
	private TipoHabitoEnfermedad tipoHabitoEnfermedad;
	@Column(name = "TIPO_DATO")
	private Character tipoDato;// 9: numérico
	// A: alfabético
	// X: alfanumérico.
	// 9: numérico
	@Basic(optional = false)
	@Column(name = "LIMITE_INFERIOR", precision = 10, scale = 2, columnDefinition = "decimal(10,2)")
	private BigDecimal limiteInferior;
	@Column(name = "LIMITE_SUPERIOR", precision = 10, scale = 2, columnDefinition = "decimal(10,2)")
	private BigDecimal limiteSuperior;

	@Basic(optional = false)
	@Column(name = "CATEGORIA")
	private char categoria;// G Global, E Especialista, T Todos

	public PreguntaHabitoEnfermedad() {
	}

	public PreguntaHabitoEnfermedad(Integer codPregunta) {
		this.codPregunta = codPregunta;
	}

	public PreguntaHabitoEnfermedad(Integer codPregunta, String pregunta, TipoHabitoEnfermedad tipoHabitoEnfermedad,
			Character tipoDato, BigDecimal limiteInferior, BigDecimal limiteSuperior, char categoria) {
		this.codPregunta = codPregunta;
		this.pregunta = pregunta;
		this.tipoHabitoEnfermedad = tipoHabitoEnfermedad;
		this.tipoDato = tipoDato;
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
		this.categoria = categoria;
	}

	@Transient
	public boolean isNumerico() {
		if (this.tipoDato != null && this.tipoDato.charValue() == Constantes.PREGUNTA_ID_TIPO_DATO_NUMERICO) {
			return true;
		}
		return false;
	}

	@Transient
	public Double getLimiteSuperiorDouble() {
		return limiteSuperior.doubleValue();
	}

	@Transient
	public Double getLimiteInferiorDouble() {
		return limiteInferior.doubleValue();
	}

	public Integer getCodPregunta() {
		return codPregunta;
	}

	public void setCodPregunta(Integer codPregunta) {
		this.codPregunta = codPregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public TipoHabitoEnfermedad getTipoHabitoEnfermedad() {
		return tipoHabitoEnfermedad;
	}

	public void setTipoHabitoEnfermedad(TipoHabitoEnfermedad tipoHabitoEnfermedad) {
		this.tipoHabitoEnfermedad = tipoHabitoEnfermedad;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codPregunta != null ? codPregunta.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PreguntaHabitoEnfermedad)) {
			return false;
		}
		PreguntaHabitoEnfermedad other = (PreguntaHabitoEnfermedad) object;
		if ((this.codPregunta == null && other.codPregunta != null)
				|| (this.codPregunta != null && !this.codPregunta.equals(other.codPregunta))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.PreguntaHabitoEnfermedad[codPregunta=" + codPregunta + "]";
	}

	public Character getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(Character tipoDato) {
		this.tipoDato = tipoDato;
	}

	public BigDecimal getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(BigDecimal limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public BigDecimal getLimiteSuperior() {
		return limiteSuperior;
	}

	public void setLimiteSuperior(BigDecimal limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}

	public char getCategoria() {
		return categoria;
	}

	public void setCategoria(char categoria) {
		this.categoria = categoria;
	}
}
