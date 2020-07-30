package com.equivida.constant;

public enum TipoFormularioEnum {
	
	CLIENTE_UNICO(0l, "cliente.unico"), ASOCIACION(1l, "asociacion");
	
	public Long getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(Long secuencial) {
		this.secuencial = secuencial;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	private Long secuencial;
	private String bundle;
	
	private TipoFormularioEnum(Long secuencial, String bundle) {
		this.secuencial = secuencial;
		this.bundle = bundle;
	}
}