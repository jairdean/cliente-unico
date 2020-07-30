/**
 * 
 */
package com.equivida.smartdata.constante;

/**
 * @author juan
 *
 */
public enum TipoTelefonoEnum {
	DOMICILIO(new Short("1")), OFICINA(new Short("2"));

	private Short codigoTipoTelefono;

	/**
	 * Constructor por defecto.
	 * 
	 * @param codigoTipoParentesco
	 */
	TipoTelefonoEnum(Short codigoTipoTelefono) {
		this.codigoTipoTelefono = codigoTipoTelefono;
	}

	/**
	 * @return the codigoTipoParentesco
	 */
	public Short getCodigoTipoTelefono() {
		return codigoTipoTelefono;
	}
}
