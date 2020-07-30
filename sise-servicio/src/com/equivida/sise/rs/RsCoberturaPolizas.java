package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsCoberturaPolizas implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private Integer cod_ind_categ;
	private BigDecimal imp_suma_aseg;
	private BigDecimal pje_tasa;
	private String txt_desc_categ;
	private String txt_desc2;
	private String cobertura;
	private String fec_vto;
	private BigDecimal imp_capital;
	private BigDecimal imp_prima;
	private String campoout1;
	private String campoout2;
	private String campoout3;

	/**
	 * @return the cod_ind_categ
	 */
	public Integer getCod_ind_categ() {
		return cod_ind_categ;
	}

	/**
	 * @param cod_ind_categ
	 *            the cod_ind_categ to set
	 */
	public void setCod_ind_categ(Integer cod_ind_categ) {
		this.cod_ind_categ = cod_ind_categ;
	}

	/**
	 * @return the imp_suma_aseg
	 */
	public BigDecimal getImp_suma_aseg() {
		return imp_suma_aseg;
	}

	/**
	 * @param imp_suma_aseg
	 *            the imp_suma_aseg to set
	 */
	public void setImp_suma_aseg(BigDecimal imp_suma_aseg) {
		this.imp_suma_aseg = imp_suma_aseg;
	}

	/**
	 * @return the pje_tasa
	 */
	public BigDecimal getPje_tasa() {
		return pje_tasa;
	}

	/**
	 * @param pje_tasa
	 *            the pje_tasa to set
	 */
	public void setPje_tasa(BigDecimal pje_tasa) {
		this.pje_tasa = pje_tasa;
	}

	/**
	 * @return the txt_desc_categ
	 */
	public String getTxt_desc_categ() {
		return txt_desc_categ;
	}

	/**
	 * @param txt_desc_categ
	 *            the txt_desc_categ to set
	 */
	public void setTxt_desc_categ(String txt_desc_categ) {
		this.txt_desc_categ = txt_desc_categ;
	}

	/**
	 * @return the txt_desc2
	 */
	public String getTxt_desc2() {
		return txt_desc2;
	}

	/**
	 * @param txt_desc2
	 *            the txt_desc2 to set
	 */
	public void setTxt_desc2(String txt_desc2) {
		this.txt_desc2 = txt_desc2;
	}

	/**
	 * @return the campoout1
	 */
	public String getCampoout1() {
		return campoout1;
	}

	/**
	 * @param campoout1
	 *            the campoout1 to set
	 */
	public void setCampoout1(String campoout1) {
		this.campoout1 = campoout1;
	}

	/**
	 * @return the campoout2
	 */
	public String getCampoout2() {
		return campoout2;
	}

	/**
	 * @param campoout2
	 *            the campoout2 to set
	 */
	public void setCampoout2(String campoout2) {
		this.campoout2 = campoout2;
	}

	/**
	 * @return the campoout3
	 */
	public String getCampoout3() {
		return campoout3;
	}

	/**
	 * @param campoout3
	 *            the campoout3 to set
	 */
	public void setCampoout3(String campoout3) {
		this.campoout3 = campoout3;
	}

	/**
	 * @return the cobertura
	 */
	public String getCobertura() {
		return cobertura;
	}

	/**
	 * @param cobertura
	 *            the cobertura to set
	 */
	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	/**
	 * @return the fec_vto
	 */
	public String getFec_vto() {
		return fec_vto;
	}

	/**
	 * @param fec_vto
	 *            the fec_vto to set
	 */
	public void setFec_vto(String fec_vto) {
		this.fec_vto = fec_vto;
	}

	/**
	 * @return the imp_capital
	 */
	public BigDecimal getImp_capital() {
		return imp_capital;
	}

	/**
	 * @param imp_capital
	 *            the imp_capital to set
	 */
	public void setImp_capital(BigDecimal imp_capital) {
		this.imp_capital = imp_capital;
	}

	/**
	 * @return the imp_prima
	 */
	public BigDecimal getImp_prima() {
		return imp_prima;
	}

	/**
	 * @param imp_prima
	 *            the imp_prima to set
	 */
	public void setImp_prima(BigDecimal imp_prima) {
		this.imp_prima = imp_prima;
	}

}