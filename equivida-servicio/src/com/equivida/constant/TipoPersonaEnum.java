/**
 * 
 */
package com.equivida.constant;

/**
 * @author juan
 *
 */
public enum TipoPersonaEnum {
	N("NATURAL", "F"), J("JURIDICA", "J");

	private String descripcion;
	private String codSise;

	TipoPersonaEnum(String descripcion, String codSise) {
		this.descripcion = descripcion;
		this.codSise = codSise;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return the codSise
	 */
	public String getCodSise() {
		return codSise;
	}

}
