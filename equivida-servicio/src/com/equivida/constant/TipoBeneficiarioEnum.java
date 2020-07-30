/**
 * 
 */
package com.equivida.constant;

/**
 * @author daniel
 * 
 */
public enum TipoBeneficiarioEnum {

	PRINCIPAL('P', "principal"), CONTINGENTE('C', "contigente");

	private char codigo;

	private String keyBundle;

	private TipoBeneficiarioEnum(char codigo, String keyBundle) {
		this.codigo = codigo;
		this.keyBundle = keyBundle;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}

	public String getKeyBundle() {
		return keyBundle;
	}

	public void setKeyBundle(String keyBundle) {
		this.keyBundle = keyBundle;
	}
}