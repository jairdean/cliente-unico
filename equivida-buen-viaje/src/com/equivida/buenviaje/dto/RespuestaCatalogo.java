/**
 * 
 */
package com.equivida.buenviaje.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author juan
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respuestaCatalogo")
public class RespuestaCatalogo implements Serializable {
	@XmlElement(name = "catalogo")
	private List<Catalogo> catalogoLista;
	@XmlAttribute(name = "tipo")
	private String tipoCatalogo;

	public RespuestaCatalogo() {
	}

	public RespuestaCatalogo(List<Catalogo> catalogoLista, String tipoCatalogo) {
		this.catalogoLista = catalogoLista;
		this.tipoCatalogo = tipoCatalogo;
	}

	/**
	 * @return the catalogoLista
	 */
	public List<Catalogo> getCatalogoLista() {
		return catalogoLista;
	}

	/**
	 * @param catalogoLista
	 *            the catalogoLista to set
	 */
	public void setCatalogoLista(List<Catalogo> catalogoLista) {
		this.catalogoLista = catalogoLista;
	}

	/**
	 * @return the tipoCatalogo
	 */
	public String getTipoCatalogo() {
		return tipoCatalogo;
	}

	/**
	 * @param tipoCatalogo
	 *            the tipoCatalogo to set
	 */
	public void setTipoCatalogo(String tipoCatalogo) {
		this.tipoCatalogo = tipoCatalogo;
	}

}
