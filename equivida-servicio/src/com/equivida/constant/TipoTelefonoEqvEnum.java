/**
 * 
 */
package com.equivida.constant;

/**
 * @author juan
 *
 */
public enum TipoTelefonoEqvEnum {
	DOMICILIO(new Short("1")), OFICINA(new Short("2")), MOVIL_OFICINA(new Short("4"));

	private Short codigoTipoTelefono;

	/**
	 * Constructor por defecto.
	 * 
	 * @param codigoTipoParentesco
	 */
	TipoTelefonoEqvEnum(Short codigoTipoTelefono) {
		this.codigoTipoTelefono = codigoTipoTelefono;
	}

	/**
	 * @return the codigoTipoParentesco
	 */
	public Short getCodigoTipoTelefono() {
		return codigoTipoTelefono;
	}
}
