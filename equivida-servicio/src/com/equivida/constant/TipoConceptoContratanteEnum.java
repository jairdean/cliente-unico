/**
 * 
 */
package com.equivida.constant;

/**
 * @author juan
 *
 */
public enum TipoConceptoContratanteEnum {
	P("PAGADOR"), A("ASEGURADO");

	private String descripcion;

	TipoConceptoContratanteEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
