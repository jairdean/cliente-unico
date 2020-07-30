package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RespuestaInsertMPersona implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal idPersona;
	private BigDecimal numFilas;
	private String idProceso;
	private String txtError;
	private String campoOut1;
	private String campoOut2;
	private String campoOut3;

	public String getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	public String getTxtError() {
		return txtError;
	}

	public void setTxtError(String txtError) {
		this.txtError = txtError;
	}

	public BigDecimal getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(BigDecimal idPersona) {
		this.idPersona = idPersona;
	}

	public BigDecimal getNumFilas() {
		return numFilas;
	}

	public void setNumFilas(BigDecimal numFilas) {
		this.numFilas = numFilas;
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

}