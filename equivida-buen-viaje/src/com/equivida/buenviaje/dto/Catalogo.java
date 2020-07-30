/**
 * 
 */
package com.equivida.buenviaje.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author juan
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "catalogo", propOrder = { "codigo", "nombre" })
public class Catalogo implements Serializable {
	@XmlElement(name = "codigo")
	private String codigo;
	@XmlElement(name = "nombre")
	private String nombre;

	public Catalogo() {
	}

	public Catalogo(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
