package com.equivida.dto;

import java.util.List;

import com.equivida.modelo.Rcs;

public class ResultadoWebserviceListaReservada {

	private boolean conRiesgo;

	private List<RiesgoDto> riesgoDtoLista;

	private String contenidoXml;

	private Rcs rcs;// para servicio web

	public boolean isConRiesgo() {
		return conRiesgo;
	}

	public void setConRiesgo(boolean conRiesgo) {
		this.conRiesgo = conRiesgo;
	}

	/**
	 * @return the riesgoDtoLista
	 */
	public List<RiesgoDto> getRiesgoDtoLista() {
		return riesgoDtoLista;
	}

	/**
	 * @param riesgoDtoLista
	 *            the riesgoDtoLista to set
	 */
	public void setRiesgoDtoLista(List<RiesgoDto> riesgoDtoLista) {
		this.riesgoDtoLista = riesgoDtoLista;
	}

	public String getContenidoXml() {
		return contenidoXml;
	}

	public void setContenidoXml(String contenidoXml) {
		this.contenidoXml = contenidoXml;
	}

	public Rcs getRcs() {
		return rcs;
	}

	public void setRcs(Rcs rcs) {
		this.rcs = rcs;
	}
}
