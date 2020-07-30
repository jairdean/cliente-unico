package com.equivida.dto.portal;

import java.io.Serializable;

public class RsTelefonoPW implements Serializable {

	private static final long serialVersionUID = 7337209529771107874L;

	private Integer codTipoTelefono;
	private String nroTelefono;

	/**
	 * @return the codTipoTelefono
	 */
	public Integer getCodTipoTelefono() {
		return codTipoTelefono;
	}

	/**
	 * @param codTipoTelefono
	 *            the codTipoTelefono to set
	 */
	public void setCodTipoTelefono(Integer codTipoTelefono) {
		this.codTipoTelefono = codTipoTelefono;
	}

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

}
