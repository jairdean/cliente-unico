/**
 * 
 */
package com.equivida.constant;

/**
 * @author juan
 *
 */
public enum TipoOcupacionEnum {
	ERDD("En relación de dependencia"), I("Independiente"), E("Estudiante"), AC("Ama de casa"), J("Jubilado");

	private String descripcion;

	TipoOcupacionEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
