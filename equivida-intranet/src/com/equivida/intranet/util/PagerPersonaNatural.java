package com.equivida.intranet.util;

import com.equivida.constant.RespuestaEnum;
import com.equivida.modelo.PersonaNatural;
import com.equivida.servicio.PersonaNaturalServicio;
import com.saviasoft.util.PagerAbstract;

public class PagerPersonaNatural extends PagerAbstract<PersonaNatural> {

	private static final long serialVersionUID = -95734499710598864L;

	private PersonaNaturalServicio servicio;

	private String numeroDocumento;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String primerNombre;
	private String segundoNombre;
	private RespuestaEnum cliente;
	private String ordenadoPor;
	private boolean asc;

	public PagerPersonaNatural(int rowPerPage, PersonaNaturalServicio servicio,
			String numeroDocumento, String apellidoPaterno,
			String apellidoMaterno, String primerNombre, String segundoNombre,
			RespuestaEnum cliente, String ordenadoPor, boolean asc) {
		super(rowPerPage);
		this.servicio = servicio;
		setParametros(numeroDocumento, apellidoPaterno, apellidoMaterno,
				primerNombre, segundoNombre, cliente, ordenadoPor, asc);
	}

	public void setParametros(String numeroDocumento, String apellidoPaterno,
			String apellidoMaterno, String primerNombre, String segundoNombre,
			RespuestaEnum cliente, String ordenadoPor, boolean asc) {
		this.numeroDocumento = numeroDocumento;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.cliente = cliente;
		this.ordenadoPor = ordenadoPor;
		this.asc = asc;
	}

	@Override
	protected Long getTotal() {
		return servicio.obtenerTotalListadoPaginado(numeroDocumento,
				apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre,
				cliente);
	}

	@Override
	protected void loadPage() {
		setList(servicio.obtenerListadoPaginado(numeroDocumento,
				apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre,
				cliente, ordenadoPor, asc, getInitIndex(), getRowPerPage()));
	}
}