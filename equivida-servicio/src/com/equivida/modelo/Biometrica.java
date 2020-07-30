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
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "BIOMETRICA")
public class Biometrica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1688643810882350196L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_INFORMACION_BIOMETRICA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secInformacionBiometrica;
	@Basic(optional = false)
	@Column(name = "ESTATURA", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private BigDecimal estatura;
	@Basic(optional = false)
	@Column(name = "PESO", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private BigDecimal peso;
	@Basic(optional = false)
	@Column(name = "DIFERENCIA_ULTIMO_ANIO", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	private BigDecimal diferenciaUltimoAnio;
	@Basic(optional = false)
	@Column(name = "GANADO_PERDIDO")
	private char ganadoPerdido;
	@Column(name = "CAUSA_DIFERENCIA")
	private String causaDiferencia;

	@Column(name = "PRESION_ARTERIAL", length = 16)
	private String presionArterial;

	@Column(name = "SEC_PERSONA_NATURAL")
	private Integer secPersonaNatural;
	// @JoinColumn(name = "SEC_PERSONA_NATURAL", referencedColumnName =
	// "SEC_PERSONA_NATURAL")
	// @OneToOne(optional = false)
	// private PersonaNatural personaNatural;

	@Transient
	private BigDecimal ganadosUltAnioKilos;

	@Transient
	private int ganadosUltAnioLibras;

	@Transient
	private BigDecimal perdidosUltAnioKilos;

	@Transient
	private int perdidosUltAnioLibras;

	@Transient
	private int pesoLibras;

	@Transient
	private BigDecimal pesoKilos;

	public Biometrica() {
	}

	public Biometrica(Integer secInformacionBiometrica) {
		this.secInformacionBiometrica = secInformacionBiometrica;
	}

	// public Biometrica(Integer secInformacionBiometrica, BigDecimal estatura,
	// int peso, int diferenciaUltimoAnio, char ganadoPerdido) {
	// this.secInformacionBiometrica = secInformacionBiometrica;
	// this.estatura = estatura;
	// this.peso = peso;
	// this.diferenciaUltimoAnio = diferenciaUltimoAnio;
	// this.ganadoPerdido = ganadoPerdido;
	// }

	public Integer getSecInformacionBiometrica() {
		return secInformacionBiometrica;
	}

	public void setSecInformacionBiometrica(Integer secInformacionBiometrica) {
		this.secInformacionBiometrica = secInformacionBiometrica;
	}

	public BigDecimal getEstatura() {
		return estatura;
	}

	public void setEstatura(BigDecimal estatura) {
		this.estatura = estatura;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getDiferenciaUltimoAnio() {
		return diferenciaUltimoAnio;
	}

	public void setDiferenciaUltimoAnio(BigDecimal diferenciaUltimoAnio) {
		this.diferenciaUltimoAnio = diferenciaUltimoAnio;
	}

	public char getGanadoPerdido() {
		return ganadoPerdido;
	}

	public void setGanadoPerdido(char ganadoPerdido) {
		this.ganadoPerdido = ganadoPerdido;
	}

	public String getCausaDiferencia() {
		return causaDiferencia;
	}

	public void setCausaDiferencia(String causaDiferencia) {
		this.causaDiferencia = causaDiferencia;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secInformacionBiometrica != null ? secInformacionBiometrica.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Biometrica)) {
			return false;
		}
		Biometrica other = (Biometrica) object;
		if ((this.secInformacionBiometrica == null && other.secInformacionBiometrica != null)
				|| (this.secInformacionBiometrica != null
						&& !this.secInformacionBiometrica.equals(other.secInformacionBiometrica))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Biometrica[secInformacionBiometrica=" + secInformacionBiometrica + "]";
	}

	public BigDecimal getPesoKilos() {
		return pesoKilos;
	}

	public void setPesoKilos(BigDecimal pesoKilos) {
		this.pesoKilos = pesoKilos;
	}

	public int getPesoLibras() {
		return pesoLibras;
	}

	public void setPesoLibras(int pesoLibras) {
		this.pesoLibras = pesoLibras;
	}

	public BigDecimal getGanadosUltAnioKilos() {
		return ganadosUltAnioKilos;
	}

	public void setGanadosUltAnioKilos(BigDecimal ganadosUltAnioKilos) {
		this.ganadosUltAnioKilos = ganadosUltAnioKilos;
	}

	public int getGanadosUltAnioLibras() {
		return ganadosUltAnioLibras;
	}

	public void setGanadosUltAnioLibras(int ganadosUltAnioLibras) {
		this.ganadosUltAnioLibras = ganadosUltAnioLibras;
	}

	public BigDecimal getPerdidosUltAnioKilos() {
		return perdidosUltAnioKilos;
	}

	public void setPerdidosUltAnioKilos(BigDecimal perdidosUltAnioKilos) {
		this.perdidosUltAnioKilos = perdidosUltAnioKilos;
	}

	public int getPerdidosUltAnioLibras() {
		return perdidosUltAnioLibras;
	}

	public void setPerdidosUltAnioLibras(int perdidosUltAnioLibras) {
		this.perdidosUltAnioLibras = perdidosUltAnioLibras;
	}

	public Integer getSecPersonaNatural() {
		return secPersonaNatural;
	}

	public void setSecPersonaNatural(Integer secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
	}

	public String getPresionArterial() {
		return presionArterial;
	}

	public void setPresionArterial(String presionArterial) {
		this.presionArterial = presionArterial;
	}

}
