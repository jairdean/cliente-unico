package com.equivida.intranet.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.equivida.constant.EmpresaEnum;
import com.equivida.constant.PerfilEquividaEnum;
import com.equivida.constant.TipoFormularioEnum;
import com.equivida.dto.MensajeRcsDto;
import com.equivida.intranet.util.BaseCtrl;

@ManagedBean(name = "sesionCtrl")
@SessionScoped
public class SesionCtrl extends BaseCtrl {

	private static final long serialVersionUID = -613622560389453677L;

	private Long formularioSeleccionado = TipoFormularioEnum.CLIENTE_UNICO.getSecuencial();// Cliente Unico, Asociacion

	private MensajeRcsDto mensajeRcsDto;

	private EmpresaEnum empresa;

	private List<EmpresaEnum> empresas;

	public boolean isModuloAsegurado() {
		return isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())
				|| isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())
				|| isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())
				|| isUserInRole(PerfilEquividaEnum.CU_CUMPLIMIENTO.toString())
				|| isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())
				|| isUserInRole(PerfilEquividaEnum.CU_SERV_CLIENTE.toString())
				|| isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())
				|| isUserInRole(PerfilEquividaEnum.CU_GLOBAL.toString())
				|| isUserInRole(PerfilEquividaEnum.CU_ESPECIALISTA.toString());
	}

	public boolean isModuloContratante() {
		return isUserInRole(PerfilEquividaEnum.CU_CONSULTA_CONTRATANTE.toString())
				|| isUserInRole(PerfilEquividaEnum.CU_CONTRATANTE.toString())
				|| isUserInRole(PerfilEquividaEnum.CU_CUMPLIMIENTO.toString());
	}

	public boolean isIngresarContratante() {
		return isUserInRole(PerfilEquividaEnum.CU_CONTRATANTE.toString());
	}

	public boolean isRcsContratante() {
		return isUserInRole(PerfilEquividaEnum.CU_CUMPLIMIENTO.toString());
	}

	public void cambiarEmpresa(ValueChangeEvent event) {
		if (event.getNewValue() != null) {

			empresa = (EmpresaEnum) event.getNewValue();

			System.out.println("cambia a " + empresa);

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(getHttpServletRequest().getContextPath() + "/pages/index.jsf");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("ERROR no puede redireccionar a index");
			}
		}

	}

	public Long getFormularioSeleccionado() {
		return formularioSeleccionado;
	}

	public void setFormularioSeleccionado(Long formularioSeleccionado) {
		this.formularioSeleccionado = formularioSeleccionado;
	}

	public MensajeRcsDto getMensajeRcsDto() {
		return mensajeRcsDto;
	}

	public void setMensajeRcsDto(MensajeRcsDto mensajeRcsDto) {
		this.mensajeRcsDto = mensajeRcsDto;
	}

	public String ponerEspecialista() {
		this.empresa = EmpresaEnum.EQUIVIDA;
		return "/pages/index.jsf";
	}

	public String ponerGlobal() {
		this.empresa = EmpresaEnum.COLVIDA;
		return "/pages/index.jsf";
	}

	public EmpresaEnum getEmpresa() {
		if (empresa == null) {
			List<EmpresaEnum> empresas = getEmpresas();
			if (empresas.size() == 1) {
				empresa = empresas.get(0);
			} else {
				// se queda en null y se programa para que seleccione cual empresa

			}
		}

		return empresa;
	}

	public void setEmpresa(EmpresaEnum empresa) {

		this.empresa = empresa;
	}

	public List<EmpresaEnum> getEmpresas() {
		if (empresas == null) {
			empresas = new ArrayList<EmpresaEnum>();
			if (isUserInRole(PerfilEquividaEnum.CU_ESPECIALISTA.toString())) {
				System.out.println(PerfilEquividaEnum.CU_ESPECIALISTA);
				empresas.add(EmpresaEnum.EQUIVIDA);
			}
			if (isUserInRole(PerfilEquividaEnum.CU_GLOBAL.toString())) {
				System.out.println(PerfilEquividaEnum.CU_GLOBAL);
				empresas.add(EmpresaEnum.COLVIDA);
			}
			System.out.println("total empresas:" + empresas.size());
		}
		return empresas;
	}

	public void setEmpresas(List<EmpresaEnum> empresas) {
		this.empresas = empresas;
	}
}