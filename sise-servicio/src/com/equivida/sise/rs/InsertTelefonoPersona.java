/**
 * 
 */
package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author saviasoft5
 * 
 */
public class InsertTelefonoPersona implements Serializable {

	private static final long serialVersionUID = -8812308218077779984L;

	private BigDecimal idTelefono;
	private BigDecimal numFilas;
	private String codError;
	private String msgError;
	private String campoOut1;
	private String campoOut2;
	private String campoOut3;

	/**
	 * @return the numFilas
	 */
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
	 * @return the idTelefono
	 */
	public BigDecimal getIdTelefono() {
		return idTelefono;
	}

	/**
	 * @param idTelefono the idTelefono to set
	 */
	public void setIdTelefono(BigDecimal idTelefono) {
		this.idTelefono = idTelefono;
	}


}
