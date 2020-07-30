package com.equivida.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Transient;

import com.equivida.constant.EstadoEnum;
import com.equivida.constant.TipoEmpleoEnum;
import com.equivida.util.StringUtil;

/**
 * @author Daniel Cardenas
 * 
 */
public class EmpleoDto implements Serializable {

	private static final long serialVersionUID = 2110468851965426779L;

	private Integer secEmpleo;

	private Short tipoEmpleo;
	private String tipoEmpleoNombre;

	// empresa - dependientes
	private Short actividadEconomicaId;
	private String actividadEconomicaNombre;

	// tipo de negocio - independientes
	private String negocioEmpresaNombre;

	private BigDecimal tiempoEmpresa;
	private char codTiempo;
	private String cargo;
	private char estado;// A activo o I inactivo
	
	@Transient
	private boolean mensajeTr = false;
	
	public Integer getSecEmpleo() {
		return secEmpleo;
	}

	public void setSecEmpleo(Integer secEmpleo) {
		this.secEmpleo = secEmpleo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((actividadEconomicaId == null) ? 0 : actividadEconomicaId
						.hashCode());
		result = prime
				* result
				+ ((actividadEconomicaNombre == null) ? 0
						: actividadEconomicaNombre.hashCode());
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + codTiempo;
		result = prime * result + estado;
		result = prime
				* result
				+ ((negocioEmpresaNombre == null) ? 0 : negocioEmpresaNombre
						.hashCode());
		result = prime * result
				+ ((secEmpleo == null) ? 0 : secEmpleo.hashCode());
		result = prime * result
				+ ((tiempoEmpresa == null) ? 0 : tiempoEmpresa.hashCode());
		result = prime * result
				+ ((tipoEmpleo == null) ? 0 : tipoEmpleo.hashCode());
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
		EmpleoDto other = (EmpleoDto) obj;
		if (actividadEconomicaId == null) {
			if (other.actividadEconomicaId != null)
				return false;
		} else if (!actividadEconomicaId.equals(other.actividadEconomicaId))
			return false;
		if (actividadEconomicaNombre == null) {
			if (other.actividadEconomicaNombre != null)
				return false;
		} else if (!actividadEconomicaNombre
				.equals(other.actividadEconomicaNombre))
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (codTiempo != other.codTiempo)
			return false;
		if (estado != other.estado)
			return false;
		if (negocioEmpresaNombre == null) {
			if (other.negocioEmpresaNombre != null)
				return false;
		} else if (!negocioEmpresaNombre.equals(other.negocioEmpresaNombre))
			return false;
		if (secEmpleo == null) {
			if (other.secEmpleo != null)
				return false;
		} else if (!secEmpleo.equals(other.secEmpleo))
			return false;
		if (tiempoEmpresa == null) {
			if (other.tiempoEmpresa != null)
				return false;
		} else if (!tiempoEmpresa.equals(other.tiempoEmpresa))
			return false;
		if (tipoEmpleo == null) {
			if (other.tipoEmpleo != null)
				return false;
		} else if (!tipoEmpleo.equals(other.tipoEmpleo))
			return false;
		return true;
	}

	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo();
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public boolean isDependiente() {
		return tipoEmpleo.equals(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());
	}

	public boolean isIndependiente() {
		return tipoEmpleo.equals(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());
	}

	public BigDecimal getTiempoEmpresa() {
		return tiempoEmpresa;
	}

	public void setTiempoEmpresa(BigDecimal tiempoEmpresa) {
		this.tiempoEmpresa = tiempoEmpresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		if (cargo != null) {
			this.cargo = StringUtil.toUpper(cargo).trim();
		}
	}

	public Short getTipoEmpleo() {
		return tipoEmpleo;
	}

	public void setTipoEmpleo(Short tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	public char getCodTiempo() {
		return codTiempo;
	}

	public void setCodTiempo(char codTiempo) {
		this.codTiempo = codTiempo;
	}

	public Short getActividadEconomicaId() {
		return actividadEconomicaId;
	}

	public void setActividadEconomicaId(Short actividadEconomicaId) {
		this.actividadEconomicaId = actividadEconomicaId;
	}

	public String getActividadEconomicaNombre() {
		return actividadEconomicaNombre;
	}

	public void setActividadEconomicaNombre(String actividadEconomicaNombre) {
		this.actividadEconomicaNombre = actividadEconomicaNombre;
	}

	public String getNegocioEmpresaNombre() {
		return negocioEmpresaNombre;
	}

	public void setNegocioEmpresaNombre(String negocioEmpresaNombre) {
		this.negocioEmpresaNombre = negocioEmpresaNombre;
	}

	/**
	 * @return the tipoEmpleoNombre
	 */
	public String getTipoEmpleoNombre() {
		return tipoEmpleoNombre;
	}

	/**
	 * @param tipoEmpleoNombre
	 *            the tipoEmpleoNombre to set
	 */
	public void setTipoEmpleoNombre(String tipoEmpleoNombre) {
		this.tipoEmpleoNombre = tipoEmpleoNombre;
	}

	public boolean isMensajeTr() {
		return mensajeTr;
	}

	public void setMensajeTr(boolean mensajeTr) {
		this.mensajeTr = mensajeTr;
	}
}