/**
 * 
 */
package com.equivida.smartdata.constante;

/**
 * @author juan
 *
 */
public enum TipoDireccionEnum {
	DOMICILIO(new Short("1")), TRABAJO(new Short("2"));

	private Short codigoenBase;

	TipoDireccionEnum(Short codigoenBase) {
		this.codigoenBase = codigoenBase;
	}

	public Short getCodigoenBase() {
		return codigoenBase;
	}

}
