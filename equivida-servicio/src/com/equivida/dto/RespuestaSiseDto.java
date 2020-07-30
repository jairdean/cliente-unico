package com.equivida.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.equivida.modelo.CantonTMunicipio;

/**
 * @author Daniel Cardenas
 * 
 */
public class RespuestaSiseDto implements Serializable {

	private static final long serialVersionUID = 8408565747876396255L;

	private Long idPersonaSise;

	private Long codAseguridadoSise;

	private CantonTMunicipio cantonTMunicipioPrimeraDireccion;

	private List<String> errorLista = new ArrayList<String>();

	public Long getIdPersonaSise() {
		return idPersonaSise;
	}

	public void setIdPersonaSise(Long idPersonaSise) {
		this.idPersonaSise = idPersonaSise;
	}

	public List<String> getErrorLista() {
		return errorLista;
	}

	public void setErrorLista(List<String> errorLista) {
		this.errorLista = errorLista;
	}

	public CantonTMunicipio getCantonTMunicipioPrimeraDireccion() {
		return cantonTMunicipioPrimeraDireccion;
	}

	public void setCantonTMunicipioPrimeraDireccion(CantonTMunicipio cantonTMunicipioPrimeraDireccion) {
		this.cantonTMunicipioPrimeraDireccion = cantonTMunicipioPrimeraDireccion;
	}

	@Override
	public String toString() {
		return "RespuestaSiseDto [idPersonaSise=" + idPersonaSise + ", cantonTMunicipioPrimeraDireccion="
				+ cantonTMunicipioPrimeraDireccion + ", errorLista=" + errorLista + "]";
	}

	public Long getCodAseguridadoSise() {
		return codAseguridadoSise;
	}

	public void setCodAseguridadoSise(Long codAseguridadoSise) {
		this.codAseguridadoSise = codAseguridadoSise;
	}

}