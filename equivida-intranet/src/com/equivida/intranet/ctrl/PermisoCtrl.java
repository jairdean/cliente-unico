package com.equivida.intranet.ctrl;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import com.equivida.constant.PerfilEquividaEnum;
import com.equivida.intranet.util.BaseCtrl;

@ManagedBean(name = "permisoCtrl")
@SessionScoped
public class PermisoCtrl extends BaseCtrl {

	private static final long serialVersionUID = -6757324644127347192L;

	public boolean isDeshabilitadoReadOnlyTipoDocumento() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.CU_SERV_CLIENTE.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}
		return r;
	}

	/**
	 * Habilita si tiene el link de ingreso de nueva persona
	 * 
	 * @return
	 */
	public boolean isIngresoPersona() {
		boolean r = true;
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = false;
		}
		return r;
	}

	public boolean isReadOnlyNumeroDocumento() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.CU_SERV_CLIENTE.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}
		return r;
	}

	public boolean isVisibleEstadoCliente() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisiblePersonaPep() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisibleProfesionEmpleos() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisibleCualEstasActividades() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisibleConduceMoto() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisibleHaTenidoAccidentes() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisiblePilotoRealizaEstudios() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisibleDeportes() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisibleIngresosActividad() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisibleActivosPasivos() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}

		return r;
	}

	public boolean isVisibleReferenciasPersonales() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}
		return r;
	}

	public boolean isVisibleMotivoSeguro() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}
		return r;
	}

	public boolean isVisibleSeguroVigente() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}
		return r;
	}

	public boolean isVisibleBiometrica() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SERV_CLIENTE.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}
		return r;
	}

	public boolean isVisibleHabitoPersona() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}
		return r;
	}

	public boolean isVisibleHistoriaFamiliar() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}
		return r;
	}

	public boolean isVisibleConductosDePago() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.ClienteUnico.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_ASIST_SUSCRIPCION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_EMISION.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SUSCRIPTOR.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.CU_SERV_CLIENTE.toString())) {
			r = true;
		}
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			r = true;
		}
		return r;
	}

	public boolean isPuedeConsultarHistoricoRcs() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.CU_CUMPLIMIENTO.toString())) {
			r = true;
		}
		return r;
	}
	
	public boolean isPuedeConsultarHistoricoRcsContratante() {
		boolean r = false;
		if (isUserInRole(PerfilEquividaEnum.CU_CUMPLIMIENTO.toString())) {
			r = true;
		}
		return r;
	}
}
