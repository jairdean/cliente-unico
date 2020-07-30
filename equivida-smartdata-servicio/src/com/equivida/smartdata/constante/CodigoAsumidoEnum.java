/**
 * 
 */
package com.equivida.smartdata.constante;

/**
 * @author juan
 *
 */
public enum CodigoAsumidoEnum {
	ACTIVIDAD_ECONOMICA(new Short("4829")), PROFESION(new Short("2327"));

	CodigoAsumidoEnum(Short codigoSd) {
		this.codigoSd = codigoSd;
	}

	private Short codigoSd;

	/**
	 * @return the codigoSd
	 */
	public Short getCodigoSd() {
		return codigoSd;
	}

	/**
	 * @param codigoSd
	 *            the codigoSd to set
	 */
	public void setCodigoSd(Short codigoSd) {
		this.codigoSd = codigoSd;
	}

}
