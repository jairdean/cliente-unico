package com.equivida.intranet.util;

import java.util.Date;

import com.equivida.modelo.Rcs;
import com.equivida.servicio.RcsServicio;
import com.saviasoft.util.PagerAbstract;

public class PagerRcs extends PagerAbstract<Rcs> {

	private static final long serialVersionUID = -95734499710598864L;

	private RcsServicio servicio;

	private Date fechaDesde;
	private Date fechaHasta;
	private String idUsuario;
	private Character codEstadoRcs;
	private boolean contratante;

	public PagerRcs(int rowPerPage, RcsServicio servicio, Date fechaDesde,
			Date fechaHasta, String idUsuario, Character codEstadoRcs, boolean contratante) {
		super(rowPerPage);
		this.servicio = servicio;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.idUsuario = idUsuario;
		this.codEstadoRcs = codEstadoRcs;
		this.contratante=contratante;
	}

	@Override
	protected Long getTotal() {
		return servicio.obtenerPorFechaUsuarioIdPaginadoTotal(fechaDesde,
				fechaHasta, idUsuario, codEstadoRcs, contratante);
	}

	@Override
	protected void loadPage() {
		setList(servicio.obtenerPorFechaUsuarioIdPaginado(fechaDesde,
				fechaHasta, idUsuario, codEstadoRcs, contratante, super.getInitIndex(),
				super.getRowPerPage()));
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Character getCodEstadoRcs() {
		return codEstadoRcs;
	}

	public void setCodEstadoRcs(Character codEstadoRcs) {
		this.codEstadoRcs = codEstadoRcs;
	}

	public boolean isContratante() {
		return contratante;
	}

	public void setContratante(boolean contratante) {
		this.contratante = contratante;
	}
}