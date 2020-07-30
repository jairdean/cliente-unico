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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_HABITO_ENFERMEDAD")
@NamedQueries({ @NamedQuery(name = "TipoHabitoEnfermedad.findAll", query = "SELECT t FROM TipoHabitoEnfermedad t"),
		@NamedQuery(name = "TipoHabitoEnfermedad.findByCodTipoHabito", query = "SELECT t FROM TipoHabitoEnfermedad t WHERE t.codTipoHabito = :codTipoHabito"),
		@NamedQuery(name = "TipoHabitoEnfermedad.findByTipoHabito", query = "SELECT t FROM TipoHabitoEnfermedad t WHERE t.tipoHabito = :tipoHabito"),
		@NamedQuery(name = "TipoHabitoEnfermedad.findByIndicador", query = "SELECT t FROM TipoHabitoEnfermedad t WHERE t.indicador = :indicador"),
		@NamedQuery(name = "TipoHabitoEnfermedad.findByDetallar", query = "SELECT t FROM TipoHabitoEnfermedad t WHERE t.detallar = :detallar") })
public class TipoHabitoEnfermedad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8088356588141734797L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COD_TIPO_HABITO")
	private Short codTipoHabito;
	@Basic(optional = false)
	@Column(name = "TIPO_HABITO")
	private String tipoHabito;
	@Basic(optional = false)
	@Column(name = "INDICADOR")
	private char indicador;
	@Basic(optional = false)
	@Column(name = "DETALLAR")
	private char detallar;

	@Basic(optional = false)
	@Column(name = "VALOR")
	private char valor;

	@Basic(optional = false)
	@Column(name = "SEXO")
	private char sexo;

	@Basic(optional = false)
	@Column(name = "CATEGORIA")
	private char categoria;// G Global, E Especialista, T Todos

	public TipoHabitoEnfermedad() {
	}

	public TipoHabitoEnfermedad(Short codTipoHabito) {
		this.codTipoHabito = codTipoHabito;
	}

	public TipoHabitoEnfermedad(Short codTipoHabito, String tipoHabito, char indicador, char detallar) {
		this.codTipoHabito = codTipoHabito;
		this.tipoHabito = tipoHabito;
		this.indicador = indicador;
		this.detallar = detallar;
	}

	public Short getCodTipoHabito() {
		return codTipoHabito;
	}

	public void setCodTipoHabito(Short codTipoHabito) {
		this.codTipoHabito = codTipoHabito;
	}

	public String getTipoHabito() {
		return tipoHabito;
	}

	public void setTipoHabito(String tipoHabito) {
		this.tipoHabito = tipoHabito;
	}

	public char getIndicador() {
		return indicador;
	}

	public void setIndicador(char indicador) {
		this.indicador = indicador;
	}

	public char getDetallar() {
		return detallar;
	}

	public void setDetallar(char detallar) {
		this.detallar = detallar;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codTipoHabito != null ? codTipoHabito.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoHabitoEnfermedad)) {
			return false;
		}
		TipoHabitoEnfermedad other = (TipoHabitoEnfermedad) object;
		if ((this.codTipoHabito == null && other.codTipoHabito != null)
				|| (this.codTipoHabito != null && !this.codTipoHabito.equals(other.codTipoHabito))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.TipoHabitoEnfermedad[codTipoHabito=" + codTipoHabito + "]";
	}

	public char getValor() {
		return valor;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public char getCategoria() {
		return categoria;
	}

	public void setCategoria(char categoria) {
		this.categoria = categoria;
	}

}
