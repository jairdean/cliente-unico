/**
 * 
 */
package com.equivida.intranet.ctrl;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.equivida.constant.Constantes;
import com.equivida.dto.ContratanteListaDto;
import com.equivida.intranet.util.BaseCtrl;
import com.equivida.intranet.util.PagerContratante;
import com.equivida.servicio.ContratanteServicio;

/**
 * @author juan
 *
 */
@ManagedBean(name = "listadoContratanteCtrl")
@SessionScoped
public class ListadoContratanteCtrl extends BaseCtrl {
	private static final long serialVersionUID = -9215284794144341738L;

	@EJB(mappedName = "ContratanteServicio/local")
	private ContratanteServicio contratanteServicio;

	@ManagedProperty("#{sesionCtrl}")
	private SesionCtrl sesionCtrl;

	public String numeroDocumento = "";
	public String apellidoPaterno = "";
	public String apellidoMaterno = "";
	public String primerNombre = "";
	public String segundoNombre = "";
	private boolean criteriaOrderByAsc = true;

	private boolean mostrarResultado = false;

	private PagerContratante pager;

	public String editarContratante() {
		ContratanteListaDto p = (ContratanteListaDto) getExternalContext().getRequestMap().get("p");

		System.out.println("se edita: " + p.getIdentificacion());
		
		//borra rcs
		sesionCtrl.setMensajeRcsDto(null);

		return "/pages/contratante/formulario.jsf?faces-redirect=true&sec=" + p.getId() + "&iden="
				+ p.getIdentificacion() + "&tipo=" + p.getTipo();
	}

	/**
	 * 
	 * @return
	 */
	public String search() {

		getPager().setParametros(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre);

		pager.search();

		mostrarResultado = true;

		return null;
	}

	/**
	 * @return the pager
	 */
	public PagerContratante getPager() {
		if (pager == null) {
			pager = new PagerContratante(Constantes.PAGER_ENUM_NUM_REGISTROS, contratanteServicio, numeroDocumento,
					apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre);
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

	public void setPager(PagerContratante pager) {
		this.pager = pager;
	}

	public SesionCtrl getSesionCtrl() {
		return sesionCtrl;
	}

	public void setSesionCtrl(SesionCtrl sesionCtrl) {
		this.sesionCtrl = sesionCtrl;
	}

	/**
	 * @return the contratanteServicio
	 */
	public ContratanteServicio getContratanteServicio() {
		return contratanteServicio;
	}

	/**
	 * @param contratanteServicio the contratanteServicio to set
	 */
	public void setContratanteServicio(ContratanteServicio contratanteServicio) {
		this.contratanteServicio = contratanteServicio;
	}

}
