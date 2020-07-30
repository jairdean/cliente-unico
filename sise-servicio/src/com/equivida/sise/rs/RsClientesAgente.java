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
public class RsClientesAgente implements Serializable {

	private static final long serialVersionUID = -8812308218077779984L;

	private String numeroDocumento;
	private String contratante;
	private BigDecimal idPersona;
	private BigDecimal codAseg;
	private String ramos;
	private String campo1;
	private String campo2;
	private String campo3;

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getContratante() {
		return contratante;
	}

	public void setContratante(String contratante) {
		this.contratante = contratante;
	}

	public BigDecimal getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(BigDecimal idPersona) {
		this.idPersona = idPersona;
	}

	public BigDecimal getCodAseg() {
		return codAseg;
	}

	public void setCodAseg(BigDecimal codAseg) {
		this.codAseg = codAseg;
	}

	public String getCampo1() {
		return campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo2() {
		return campo2;
	}

	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	public String getCampo3() {
		return campo3;
	}

	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	/**
	 * @return the ramos
	 */
	public String getRamos() {
		return ramos;
	}

	/**
	 * @param ramos the ramos to set
	 */
	public void setRamos(String ramos) {
		this.ramos = ramos;
	}

}
