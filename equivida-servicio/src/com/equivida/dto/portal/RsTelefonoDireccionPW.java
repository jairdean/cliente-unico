package com.equivida.dto.portal;

import java.io.Serializable;

public class RsTelefonoDireccionPW implements Serializable {

	private static final long serialVersionUID = 7337209529771107874L;

	private String nroTelefono;
	private String extTelefono;
	private Integer idDireccion;

	/**
	 * @return the nroTelefono
	 */
	public String getNroTelefono() {
		return nroTelefono;
	}

	/**
	 * @param nroTelefono
	 *            the nroTelefono to set
	 */
	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	/**
	 * @return the extTelefono
	 */
	public String getExtTelefono() {
		return extTelefono;
	}

	/**
	 * @param extTelefono
	 *            the extTelefono to set
	 */
	public void setExtTelefono(String extTelefono) {
		this.extTelefono = extTelefono;
	}

	/**
	 * @return the idDireccion
	 */
	public Integer getIdDireccion() {
		return idDireccion;
	}

	/**
	 * @param idDireccion
	 *            the idDireccion to set
	 */
	public void setIdDireccion(Integer idDireccion) {
		this.idDireccion = idDireccion;
	}

}
