/**
 * 
 */
package com.equivida.constant;

/**
 * @author juan
 *
 */
public enum TipoContratanteEnum {
	PR("PRIVADO"), PU("PUBLICO"), MI("MIXTO");

	private String descripcion;

	TipoContratanteEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
