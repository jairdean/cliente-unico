/**
 * 
 */
package com.equivida.smartdata.dto;

import java.io.Serializable;

/**
 * @author juan
 *
 */
public class PersonaRelacionDto implements Serializable {
	private String identificacion;
	private String nombre;

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion
	 *            the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
