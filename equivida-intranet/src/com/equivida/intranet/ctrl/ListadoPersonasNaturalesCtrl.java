package com.equivida.intranet.ctrl;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.equivida.constant.Constantes;
import com.equivida.constant.EmpresaEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.intranet.util.BaseCtrl;
import com.equivida.intranet.util.PagerPersonaNatural;
import com.equivida.modelo.PersonaNatural;
import com.equivida.servicio.PersonaNaturalServicio;

@ManagedBean(name = "listadoPersonasNaturalesCtrl")
@SessionScoped
public class ListadoPersonasNaturalesCtrl extends BaseCtrl {

	private static final long serialVersionUID = 2202056357723442748L;

	@EJB(mappedName = "PersonaNaturalServicio/local")
	private PersonaNaturalServicio personaNaturalServicio;
	
	@ManagedProperty("#{sesionCtrl}")
	private SesionCtrl sesionCtrl;

	public String numeroDocumento = "";
	public String apellidoPaterno = "";
	public String apellidoMaterno = "";
	public String primerNombre = "";
	public String segundoNombre = "";
	private boolean criteriaOrderByAsc = true;

	private boolean mostrarResultado = false;

	private PagerPersonaNatural pager;

	public String editarPersonaNatural() {
		
		sesionCtrl.setMensajeRcsDto(null);
		
		getSession().removeAttribute("dto");
		PersonaNatural p = (PersonaNatural) getExternalContext()
				.getRequestMap().get("p");
		
		
		if (getSesionCtrl().getEmpresa().equals(EmpresaEnum.EQUIVIDA)) {
			return "/pages/formularios/personaNatural.jsf?faces-redirect=true&p="
					+ p.getSecPersonaNatural();
		} else {
			return "/pages/formularios/personaNaturalColvida.jsf?faces-redirect=true&p="
			+ p.getSecPersonaNatural();
		}
		
		
	}

	/**
	 * 
	 * @return
	 */
	public String search() {

		getPager().setParametros(numeroDocumento, apellidoPaterno,
				apellidoMaterno, primerNombre, segundoNombre, RespuestaEnum.SI,
				"pn.apellido_paterno", true);

		pager.search();

		mostrarResultado = true;

		return null;
	}

	/**
	 * @return the pager
	 */
	public PagerPersonaNatural getPager() {
		if (pager == null) {
			pager = new PagerPersonaNatural(
					Constantes.PAGER_ENUM_NUM_REGISTROS,
					personaNaturalServicio, numeroDocumento, apellidoPaterno,
					apellidoMaterno, primerNombre, segundoNombre,
					RespuestaEnum.SI, "pn.apellido_paterno", true);
		}
		return pager;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public boolean isCriteriaOrderByAsc() {
		return criteriaOrderByAsc;
	}

	public void setCriteriaOrderByAsc(boolean criteriaOrderByAsc) {
		this.criteriaOrderByAsc = criteriaOrderByAsc;
	}

	public boolean isMostrarResultado() {
		return mostrarResultado;
	}

	public void setMostrarResultado(boolean mostrarResultado) {
		this.mostrarResultado = mostrarResultado;
	}

	public void setPager(PagerPersonaNatural pager) {
		this.pager = pager;
	}

	public SesionCtrl getSesionCtrl() {
		return sesionCtrl;
	}

	public void setSesionCtrl(SesionCtrl sesionCtrl) {
		this.sesionCtrl = sesionCtrl;
	}
}