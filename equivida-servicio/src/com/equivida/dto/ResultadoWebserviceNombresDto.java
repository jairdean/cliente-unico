package com.equivida.dto;

/**
 * Calse que indica si uel resultado de web service hubo cambio o no y si hubo
 * cambio en sugerencia pone este cambio
 * 
 * @author Daniel Cardenas
 * 
 */
public class ResultadoWebserviceNombresDto {

	private boolean valido;
	private String sexoSugerido;
	private boolean cambio;
	private String sugerenciaNombre1;
	private String sugerenciaNombre2;
	private String sugerenciaApellido1;
	private String sugerenciaApellido2;
	private boolean error;

	public boolean isCambio() {
		return cambio;
	}

	public void setCambio(boolean cambio) {
		this.cambio = cambio;
	}

	public String getSugerenciaNombre1() {
		return sugerenciaNombre1;
	}

	public void setSugerenciaNombre1(String sugerenciaNombre1) {
		this.sugerenciaNombre1 = sugerenciaNombre1;
	}

	public String getSugerenciaNombre2() {
		return sugerenciaNombre2;
	}

	public void setSugerenciaNombre2(String sugerenciaNombre2) {
		this.sugerenciaNombre2 = sugerenciaNombre2;
	}

	public String getSugerenciaApellido1() {
		return sugerenciaApellido1;
	}

	public void setSugerenciaApellido1(String sugerenciaApellido1) {
		this.sugerenciaApellido1 = sugerenciaApellido1;
	}

	public String getSugerenciaApellido2() {
		return sugerenciaApellido2;
	}

	public void setSugerenciaApellido2(String sugerenciaApellido2) {
		this.sugerenciaApellido2 = sugerenciaApellido2;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public boolean isValido() {
		return valido;
	}

	public String getSexoSugerido() {
		return sexoSugerido;
	}

	public void setSexoSugerido(String sexoSugerido) {
		this.sexoSugerido = sexoSugerido;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}