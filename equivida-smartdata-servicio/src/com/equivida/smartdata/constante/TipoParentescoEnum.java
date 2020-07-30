/**
 * 
 */
package com.equivida.smartdata.constante;

/**
 * @author juan
 *
 */
public enum TipoParentescoEnum {
	CONYUGE(new Short("15")), PADRE(new Short("1")), MADRE(new Short("2"));

	private Short codigoTipoParentesco;

	/**
	 * Constructor por defecto.
	 * 
	 * @param codigoTipoParentesco
	 */
	TipoParentescoEnum(Short codigoTipoParentesco) {
		this.codigoTipoParentesco = codigoTipoParentesco;
	}

	/**
	 * @return the codigoTipoParentesco
	 */
	public Short getCodigoTipoParentesco() {
		return codigoTipoParentesco;
	}

}
