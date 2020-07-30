package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "TIPO_MOTIVO_SEGURO")
public class TipoMotivoSeguro implements Serializable {

	private static final long serialVersionUID = -9052954209136985614L;

	@Id
	@Basic(optional = false)
	@Column(name = "COD_TIPO_MOTIVO")
	private Short codTipoMotivo;

	@Column(name = "TIPO_MOTIVO")
	private String tipoMotivo;

	@Column(name = "DETALLAR")
	private char detallar;

	public TipoMotivoSeguro() {
	}

	public Short getCodTipoMotivo() {
		return codTipoMotivo;
	}

	public void setCodTipoMotivo(Short codTipoMotivo) {
		this.codTipoMotivo = codTipoMotivo;
	}

	public String getTipoMotivo() {
		return tipoMotivo;
	}

	public void setTipoMotivo(String tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}

	public char getDetallar() {
		return detallar;
	}

	public void setDetallar(char detallar) {
		this.detallar = detallar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codTipoMotivo == null) ? 0 : codTipoMotivo.hashCode());
		result = prime * result + detallar;
		result = prime * result
				+ ((tipoMotivo == null) ? 0 : tipoMotivo.hashCode());
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
		TipoMotivoSeguro other = (TipoMotivoSeguro) obj;
		if (codTipoMotivo == null) {
			if (other.codTipoMotivo != null)
				return false;
		} else if (!codTipoMotivo.equals(other.codTipoMotivo))
			return false;
		if (detallar != other.detallar)
			return false;
		if (tipoMotivo == null) {
			if (other.tipoMotivo != null)
				return false;
		} else if (!tipoMotivo.equals(other.tipoMotivo))
			return false;
		return true;
	}
}
