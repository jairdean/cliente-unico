package com.equivida.sise.rs;

import java.io.Serializable;

public class RsAsientoDiarioMayorDepreciacionMensual implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private String secuencial;
	private String codTipoRegistro;
	private String codError;
	private String txtError;
	
	public String getSecuencial() {
		return secuencial;
	}
	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}
	public String getCodTipoRegistro() {
		return codTipoRegistro;
	}
	public void setCodTipoRegistro(String codTipoRegistro) {
		this.codTipoRegistro = codTipoRegistro;
	}
	public String getCodError() {
		return codError;
	}
	public void setCodError(String codError) {
		this.codError = codError;
	}
	public String getTxtError() {
		return txtError;
	}
	public void setTxtError(String txtError) {
		this.txtError = txtError;
	}
	
}