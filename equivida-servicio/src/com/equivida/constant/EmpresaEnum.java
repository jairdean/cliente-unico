package com.equivida.constant;

public enum EmpresaEnum {

	EQUIVIDA("Especialista", new String[] { "E", "T" }, "#f68f00", "#ffffff"),
	COLVIDA("Global", new String[] { "G", "T" }, "#f9eb00", "#275eb9");

	private String[] categorias;
	private String etiqueta;
	private String color;// background color HTML
	private String colorFuente;// color HTML

	private EmpresaEnum(String etiqueta, String[] categorias, String color, String colorFuente) {
		this.etiqueta = etiqueta;
		this.categorias = categorias;
		this.color = color;
		this.colorFuente = colorFuente;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public boolean isEspecialista() {
		return this.equals(EQUIVIDA);
	}

	public boolean isGlobal() {
		return this.equals(COLVIDA);
	}

	public String[] getCategorias() {
		return categorias;
	}

	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColorFuente() {
		return colorFuente;
	}

	public void setColorFuente(String colorFuente) {
		this.colorFuente = colorFuente;
	}
}
