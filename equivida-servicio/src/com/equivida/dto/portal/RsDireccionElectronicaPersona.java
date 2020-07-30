/**
 * 
 */
package com.equivida.dto.portal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author saviasoft5
 * 
 */
public class RsDireccionElectronicaPersona implements Serializable {

	private static final long serialVersionUID = -8812308218077779984L;

	private BigDecimal idDireccionElec;
	private Integer codTipoDireccionElec;
	private String tipoDireccionElec;
	private String direccionElec;

	public BigDecimal getIdDireccionElec() {
		return idDireccionElec;
	}

	public void setIdDireccionElec(BigDecimal idDireccionElec) {
		this.idDireccionElec = idDireccionElec;
	}

	public Integer getCodTipoDireccionElec() {
		return codTipoDireccionElec;
	}

	public void setCodTipoDireccionElec(Integer codTipoDireccionElec) {
		this.codTipoDireccionElec = codTipoDireccionElec;
	}

	public String getTipoDireccionElec() {
		return tipoDireccionElec;
	}

	public void setTipoDireccionElec(String tipoDireccionElec) {
		this.tipoDireccionElec = tipoDireccionElec;
	}

	public String getDireccionElec() {
		return direccionElec;
	}

	public void setDireccionElec(String direccionElec) {
		this.direccionElec = direccionElec;
	}

}
