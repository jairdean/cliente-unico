package com.equivida.intranet.ctrl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.equivida.constant.Constantes;
import com.equivida.dto.ResultadoWebserviceListaReservada;
import com.equivida.dto.RiesgoDto;
import com.equivida.intranet.util.BaseCtrl;
import com.equivida.intranet.util.PagerRcs;
import com.equivida.modelo.Rcs;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.WsDatosPersonaServicio;

@ManagedBean(name = "rcsCtrl")
@ViewScoped
public class RcsCtrl extends BaseCtrl {

	private static final long serialVersionUID = 5758546358431593659L;

	@EJB(mappedName = "RcsServicio/local")
	private RcsServicio rcsServicio;

	@EJB(mappedName = "WsDatosPersonaServicio/local")
	private WsDatosPersonaServicio wsDatosPersonaServicio;

	private List<SelectItem> usuarioItems;

	private List<SelectItem> usuarioCreacionItems;

	private List<RiesgoDto> riesgoDtoLista;

	private PagerRcs pager;

	private boolean mostrarPopupAprobar;
	private boolean mostrarPopupRechazar;

	private Rcs rcs;// para aprobar / rechaar

	public void abrirPopupAprobar() {
		rcs = (Rcs) getExternalContext().getRequestMap().get("rcs");

		mostrarPopupAprobar = true;
		mostrarPopupRechazar = false;

	}

	public void abrirPopupRechazar() {

		rcs = (Rcs) getExternalContext().getRequestMap().get("rcs");

		mostrarPopupRechazar = true;
		mostrarPopupAprobar = false;
	}

	public void aprobar() {

		if (rcs.getComentario().trim().equals("")) {
			String m = "Comentario obligatorio";
			addErrorMessage(null, m, m);
			return;
		}

		rcsServicio.aprobar(rcs, getRemoteUser());

		// actualiza la lista
		buscar();

		String m = "Se ha aprobado correctamente.";
		addInfoMessage(null, m, m);
		mostrarPopupAprobar = false;
		mostrarPopupRechazar = false;
	}

	public void rechazar() {

		if (rcs.getComentario().trim().equals("")) {
			String m = "Comentario obligatorio";
			addErrorMessage(null, m, m);
			return;
		}

		rcsServicio.rechazar(rcs, getRemoteUser());

		// actualiza la lista
		buscar();

		String m = "Se ha guardado correctamente.";
		addInfoMessage(null, m, m);
		mostrarPopupAprobar = false;
		mostrarPopupRechazar = false;

	}

	public void buscar() {

		if (!validarFiltros()) {
			return;
		}

		pager.search();

	}

	private boolean validarFiltros() {

		if (pager.getFechaDesde().compareTo(pager.getFechaHasta()) > 0) {
			String m = "La fecha desde no puede ser mayor a la fecha hasta";
			addErrorMessage(null, m, m);
			return false;
		}

		return true;

	}

	public void verDetalle() {
		Rcs rcs = (Rcs) getExternalContext().getRequestMap().get("rcs");

		if (rcs.isMostrarRiesgoLista()) {
			rcs.setMostrarRiesgoLista(false);
			return;
		}

		for (Rcs r : pager.getList()) {
			r.setMostrarRiesgoLista(false);
		}

		rcs.setMostrarRiesgoLista(true);

		ResultadoWebserviceListaReservada r = wsDatosPersonaServicio.armarEnBaseXml(rcs.getContenidoXml());

		if (r.isConRiesgo()) {

			// rcs.getPersonaNaturalTransient().setNombres(null);// para que
			// vuelva a generar

			riesgoDtoLista = r.getRiesgoDtoLista();
		}

	}

	public void exportarExcel() {

		if (!validarFiltros()) {
			return;
		}

		List<Rcs> rcsListaExcel = rcsServicio.obtenerPorFechaUsuarioIdPaginado(pager.getFechaDesde(),
				pager.getFechaHasta(), pager.getIdUsuario(), pager.getCodEstadoRcs(), pager.isContratante(), -1, -1);

		Workbook wb = new HSSFWorkbook();
		// Workbook wb = new XSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		String textoHoja = "Asegurado";
		if (pager.isContratante()) {
			textoHoja = "Contratante";
		}
		Sheet sheet = wb.createSheet("Consulta RCS " + textoHoja);

		int contador = 0;
		// titulo reporte
		Row row = sheet.createRow((short) contador);

		sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
				0, // last row (0-based)
				0, // first column (0-based)
				12 // last column (0-based)
		));

		row.createCell(0).setCellValue("Consulta de coincidencias de lista reservada");

		// filtros reporte
		contador++;
		row = sheet.createRow((short) contador);
		row.createCell(0).setCellValue("Usuario:");
		if (pager.getIdUsuario() == null || pager.getIdUsuario().trim().equals("")) {
			row.createCell(1).setCellValue("Todos");
		} else {
			row.createCell(1).setCellValue(pager.getIdUsuario());
		}
		row.createCell(2).setCellValue("Fecha desde:");
		Cell cell = row.createCell(3);
		CellStyle cellStyleFechaFiltro = wb.createCellStyle();
		cellStyleFechaFiltro.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
		cell.setCellValue(pager.getFechaDesde());
		cell.setCellStyle(cellStyleFechaFiltro);

		row.createCell(4).setCellValue("Fecha hasta:");
		cell = row.createCell(5);
		cell.setCellValue(pager.getFechaHasta());
		cell.setCellStyle(cellStyleFechaFiltro);

		contador++;
		// encabezado de columnas
		row = sheet.createRow((short) contador);
		row.createCell(0).setCellValue("IDENTIFICACION");
		row.createCell(1).setCellValue("NOMBRE");
		row.createCell(2).setCellValue("USUARIO INGRESO");
		row.createCell(3).setCellValue("FECHA INGRESO");
		row.createCell(4).setCellValue("ESTADO");
		row.createCell(5).setCellValue("PERSONA/CONYUGE");
		row.createCell(6).setCellValue("COINCIDENCIA - FILE");
		row.createCell(7).setCellValue("COINCIDENCIA - LISTING");
		row.createCell(8).setCellValue("COINCIDENCIA - SCORE");
		row.createCell(9).setCellValue("ESTADO RCS");
		row.createCell(10).setCellValue("USUARIO MODIFICACION");
		row.createCell(11).setCellValue("FECHA MODIFICACION");
		row.createCell(12).setCellValue("COMENTARIO");

		contador++;

		CellStyle cellStyleFecha = wb.createCellStyle();
		cellStyleFecha.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm"));

		for (Rcs rcs : rcsListaExcel) {

			ResultadoWebserviceListaReservada r = wsDatosPersonaServicio.armarEnBaseXml(rcs.getContenidoXml());

			if (r.getRiesgoDtoLista() == null || r.getRiesgoDtoLista().size() == 0) {

				row = sheet.createRow((short) contador);

				// identificacion
				row.createCell(0).setCellValue(rcs.getIdentificacion());

				// nombre y apellidos
				row.createCell(1).setCellValue(rcs.getDenominacion());

				row.createCell(2).setCellValue(rcs.getUsrCreacion());

				// fecha
				cell = row.createCell(3);

				cell.setCellValue(rcs.getTsCreacion());
				cell.setCellStyle(cellStyleFecha);

				// tipo
				row.createCell(4).setCellValue(rcs.getEstadoRcsEnumTransient().getEtiqueta());

				// persona-conyuge
				row.createCell(5).setCellValue(rcs.getControlRcsEnumTransient().getEtiqueta());

				// lista riesgo
				row.createCell(6).setCellValue("");
				row.createCell(7).setCellValue("");
				row.createCell(8).setCellValue("");
				row.createCell(9).setCellValue("");
				row.createCell(10).setCellValue("");
				row.createCell(11).setCellValue("");
				row.createCell(12).setCellValue("");

				contador++;
			} else {

				for (RiesgoDto dto : r.getRiesgoDtoLista()) {

					row = sheet.createRow((short) contador);

					// identificacion
					row.createCell(0).setCellValue(rcs.getIdentificacion());

					// nombre y apellidos
					row.createCell(1).setCellValue(rcs.getDenominacion());

					row.createCell(2).setCellValue(rcs.getUsrCreacion());

					// fecha
					cell = row.createCell(3);

					cell.setCellValue(rcs.getTsCreacion());
					cell.setCellStyle(cellStyleFecha);

					// tipo
					row.createCell(4).setCellValue(rcs.getEstadoRcsEnumTransient().getEtiqueta());

					// persona-conyuge
					row.createCell(5).setCellValue(rcs.getControlRcsEnumTransient().getEtiqueta());

					// xml
					row.createCell(6).setCellValue(dto.getFile());
					row.createCell(7).setCellValue(dto.getListing());
					row.createCell(8).setCellValue(dto.getScore());

					row.createCell(9).setCellValue(rcs.getEstadoRcs().getEstado());
					row.createCell(10).setCellValue(rcs.getUsrModificacion());
					// fecha mod
					cell = row.createCell(11);
					cell.setCellValue(rcs.getTsModificacion());
					cell.setCellStyle(cellStyleFecha);

					row.createCell(12).setCellValue(rcs.getComentario());

					contador++;
				}

			} // fin else

		}

		getHttpServletResponse().setContentType("application/xls");
		getHttpServletResponse().addHeader("Content-Disposition", "attachment; filename=consultaRcs.xls");

		OutputStream responseOutputStream;
		try {
			responseOutputStream = getHttpServletResponse().getOutputStream();
			wb.write(responseOutputStream);

			FacesContext.getCurrentInstance().responseComplete();
			wb.close();
		} catch (IOException e) {
			addErrorMessage(null, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
	}

	public List<RiesgoDto> getRiesgoDtoLista() {
		return riesgoDtoLista;
	}

	public void setRiesgoDtoLista(List<RiesgoDto> riesgoDtoLista) {
		this.riesgoDtoLista = riesgoDtoLista;
	}

	public List<SelectItem> getUsuarioItems() {
		if (usuarioItems == null) {
			usuarioItems = new ArrayList<SelectItem>();
			List<String> usuarioLista = rcsServicio.obtenerDistintosUsuarios();
			for (String u : usuarioLista) {
				usuarioItems.add(new SelectItem(u));
			}
		}
		return usuarioItems;
	}

	public void setUsuarioItems(List<SelectItem> usuarioItems) {
		this.usuarioItems = usuarioItems;
	}

	public PagerRcs getPager() {
		if (pager == null) {
			Date fechaDesde = new Date();
			Date fechaHasta = new Date();
			String idUsuario = null;
			Character codEstadoRcs = null;
			System.out.println("getHttpServletRequest().getRequestURI():" + getHttpServletRequest().getRequestURI());
			boolean contratante = false;
			if (getHttpServletRequest().getRequestURI().contains("contratante")) {
				contratante = true;
			}
			System.out.println("contratante:" + contratante);
			pager = new PagerRcs(Constantes.PAGER_ENUM_NUM_REGISTROS, rcsServicio, fechaDesde, fechaHasta, idUsuario,
					codEstadoRcs, contratante);
		}
		return pager;
	}

	public void setPager(PagerRcs pager) {
		this.pager = pager;
	}

	public boolean isMostrarPopupAprobar() {
		return mostrarPopupAprobar;
	}

	public void setMostrarPopupAprobar(boolean mostrarPopupAprobar) {
		this.mostrarPopupAprobar = mostrarPopupAprobar;
	}

	public boolean isMostrarPopupRechazar() {
		return mostrarPopupRechazar;
	}

	public void setMostrarPopupRechazar(boolean mostrarPopupRechazar) {
		this.mostrarPopupRechazar = mostrarPopupRechazar;
	}

	public Rcs getRcs() {
		return rcs;
	}

	public void setRcs(Rcs rcs) {
		this.rcs = rcs;
	}

	public List<SelectItem> getUsuarioCreacionItems() {
		if (usuarioCreacionItems == null) {
			usuarioCreacionItems = new ArrayList<SelectItem>();
			List<String> usuarioLista = rcsServicio.obtenerDistintosUsuariosCreacion();
			for (String u : usuarioLista) {
				usuarioCreacionItems.add(new SelectItem(u));
			}
		}
		return usuarioCreacionItems;
	}

	public void setUsuarioCreacionItems(List<SelectItem> usuarioCreacionItems) {
		this.usuarioCreacionItems = usuarioCreacionItems;
	}
}
