
package com.equivida.intranet.clientws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rcsRespuestaDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rcsRespuestaDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenidoXml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rcsRespuestaDto", propOrder = {
    "contenidoXml",
    "error",
    "indentificacion"
})
public class RcsRespuestaDto {

    protected String contenidoXml;
    protected String error;
    protected String indentificacion;

    /**
     * Gets the value of the contenidoXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContenidoXml() {
        return contenidoXml;
    }

    /**
     * Sets the value of the contenidoXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContenidoXml(String value) {
        this.contenidoXml = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

    /**
     * Gets the value of the indentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndentificacion() {
        return indentificacion;
    }

    /**
     * Sets the value of the indentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndentificacion(String value) {
        this.indentificacion = value;
    }

}
