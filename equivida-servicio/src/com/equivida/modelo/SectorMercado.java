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

/**
 *
 * @author saviasoft4
 */
@Entity
@Table(name = "SECTOR_MERCADO")
@NamedQueries({ @NamedQuery(name = "SectorMercado.findAll", query = "SELECT t FROM SectorMercado t"),
		@NamedQuery(name = "SectorMercado.findByCodSectorMercado", query = "SELECT t FROM SectorMercado t WHERE t.codSectorMercado = :codSectorMercado") })
public class SectorMercado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "COD_SECTOR_MERCADO")
	private Short codSectorMercado;
	@Basic(optional = false)
	@Column(name = "SECTOR_MERCADO")
	private String sectorMercado;

	public SectorMercado() {
	}
	
	public SectorMercado(Short codSectorMercado) {
		super();
		this.codSectorMercado = codSectorMercado;
	}

	/**
	 * @return the codSectorMercado
	 */
	public Short getCodSectorMercado() {
		return codSectorMercado;
	}

	/**
	 * @param codSectorMercado the codSectorMercado to set
	 */
	public void setCodSectorMercado(Short codSectorMercado) {
		this.codSectorMercado = codSectorMercado;
	}

	/**
	 * @return the sectorMercado
	 */
	public String getSectorMercado() {
		return sectorMercado;
	}

	/**
	 * @param sectorMercado the sectorMercado to set
	 */
	public void setSectorMercado(String sectorMercado) {
		this.sectorMercado = sectorMercado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codSectorMercado != null ? codSectorMercado.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SectorMercado)) {
			return false;
		}
		SectorMercado other = (SectorMercado) object;
		if ((this.codSectorMercado == null && other.codSectorMercado != null)
				|| (this.codSectorMercado != null && !this.codSectorMercado.equals(other.codSectorMercado))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.SectorMercado[codSectorMercado=" + codSectorMercado + "]";
	}

}
