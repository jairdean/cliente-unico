/**
 * 
 */
package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author saviasoft5
 * 
 */
public class InsertDireccionPersona implements Serializable {

	private static final long serialVersionUID = -8812308218077779984L;

	private BigDecimal idDireccion;
	private BigDecimal numFilas;
	private String codError;
	private String msgError;
	private String campoOut1;
	private String campoOut2;
	private String campoOut3;

	/**
	 * @return the numFilas
	 */
	@XmlElement(nillable = true)
	public BigDecimal getNumFilas() {
		return numFilas;
	}

	/**
	 * @param numFilas
	 *            the numFilas to set
	 */
	public void setNumFilas(BigDecimal numFilas) {
		this.numFilas = numFilas;
	}

	/**
	 * @return the codError
	 */
	@XmlElement(nillable = true)
	public String getCodError() {
		return codError;
	}

	/**
	 * @param codError
	 *            the codError to set
	 */
	public void setCodError(String codError) {
		this.codError = codError;
	}

	/**
	 * @return the msgError
	 */
	@XmlElement(nillable = true)
	public String getMsgError() {
		return msgError;
	}

	/**
	 * @param msgError
	 *            the msgError to set
	 */
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	/**
	 * @return the campoOut1
	 */
	@XmlElement(nillable = true)
	public String getCampoOut1() {
		return campoOut1;
	}

	/**
	 * @param campoOut1
	 *            the campoOut1 to set
	 */
	public void setCampoOut1(String campoOut1) {
		this.campoOut1 = campoOut1;
	}

	/**
	 * @return the campoOut2
	 */
	@XmlElement(nillable = true)
	public String getCampoOut2() {
		return campoOut2;
	}

	/**
	 * @param campoOut2
	 *            the campoOut2 to set
	 */
	public void setCampoOut2(String campoOut2) {
		this.campoOut2 = campoOut2;
	}

	/**
	 * @return the campoOut3
	 */
	@XmlElement(nillable = true)
	public String getCampoOut3() {
		return campoOut3;
	}

	/**
	 * @param campoOut3
	 *            the campoOut3 to set
	 */
	public void setCampoOut3(String campoOut3) {
		this.campoOut3 = campoOut3;
	}

	/**
	 * @return the idDireccion
	 */
	@XmlElement(nillable = true)
	public BigDecimal getIdDireccion() {
		return idDireccion;
	}

	/**
	 * @param idDireccion
	 *            the idDireccion to set
	 */
	public void setIdDireccion(BigDecimal idDireccion) {
		this.idDireccion = idDireccion;
	}

}
