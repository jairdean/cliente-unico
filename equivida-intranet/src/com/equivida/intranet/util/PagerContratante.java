package com.equivida.intranet.util;

import com.equivida.dto.ContratanteListaDto;
import com.equivida.servicio.ContratanteServicio;
import com.saviasoft.util.PagerAbstract;

/**
 * Pager para contratantes.
 * 
 * @author juan
 *
 */
public class PagerContratante extends PagerAbstract<ContratanteListaDto> {

	private static final long serialVersionUID = -95734499710598864L;

	private ContratanteServicio servicio;

	private String numeroDocumento;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String primerNombre;
	private String segundoNombre;

	public PagerContratante(int rowPerPage, ContratanteServicio servicio, String numeroDocumento,
			String apellidoPaterno, String apellidoMaterno, String primerNombre, String segundoNombre) {
		super(rowPerPage);

		this.servicio = servicio;

		setParametros(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre);
	}

	public void setParametros(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre) {
		this.numeroDocumento = numeroDocumento;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
	}

	@Override
	protected Long getTotal() {
		return servicio.obtenerTotalListadoPaginado(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre,
				segundoNombre);
	}

	@Override
	protected void loadPage() {
		setList(servicio.obtenerListadoPaginado(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre,
				segundoNombre, getInitIndex(), getRowPerPage()));
	}
}