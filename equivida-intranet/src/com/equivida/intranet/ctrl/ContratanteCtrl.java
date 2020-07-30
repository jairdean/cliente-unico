package com.equivida.intranet.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.xml.bind.ValidationException;

import com.equivida.constant.PerfilEquividaEnum;
import com.equivida.constant.TipoConceptoContratanteEnum;
import com.equivida.constant.TipoContratanteEnum;
import com.equivida.constant.TipoEmpleoEnum;
import com.equivida.constant.TipoFormularioContratanteEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.constant.TipoOcupacionEnum;
import com.equivida.constant.TipoPersonaEnum;
import com.equivida.intranet.util.BasePersonaCtrl;
import com.equivida.modelo.InstitucionFinanciera;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.ReferenciaBancaria;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.servicio.ContratanteServicio;
import com.equivida.servicio.InstitucionFinancieraServicio;
import com.equivida.servicio.TipoIdentificacionServicio;
import com.equivida.servicio.TipoServicioInstFinServicio;
import com.equivida.util.ValidatorUtils;

@ManagedBean(name = "contratanteCtrl")
@ViewScoped
public class ContratanteCtrl extends BasePersonaCtrl {
	private static final long serialVersionUID = 770598259006544700L;

	@EJB(mappedName = "TipoIdentificacionServicio/local")
	private TipoIdentificacionServicio tipoIdentificacionServicio;
	@EJB(mappedName = "InstitucionFinancieraServicio/local")
	private InstitucionFinancieraServicio institucionFinancieraServicio;
	@EJB(mappedName = "TipoServicioInstFinServicio/local")
	private TipoServicioInstFinServicio tipoServicioInstFinServicio;
	@EJB(mappedName = "ContratanteServicio/local")
	private ContratanteServicio contratanteServicio;

	private TipoFormularioContratanteEnum tipoFormulario = TipoFormularioContratanteEnum.V;
	private TipoPersonaEnum tipoPersona = TipoPersonaEnum.N;
	private Character tipoIdentificacion;
	private Character tipoIdentificacionRepLegal = TipoIdentificacionEnum.CEDULA.getCodigo();
	private TipoContratanteEnum tipoContratante;
	private TipoConceptoContratanteEnum tipoConceptoContratante;
	private TipoOcupacionEnum tipoOcupacionPN;
	private String identificacion;
	private String identificacionRepLegal;

	private boolean verformularioPJ = false;
	private boolean verformularioPN = false;

	@ManagedProperty(value = "#{selectCtrl}")
	private SelectCtrl selectCtrl;

	@ManagedProperty(value = "#{contratantePNCtrl}")
	private ContratantePNCtrl contratantePNCtrl;

	@ManagedProperty(value = "#{contratantePJCtrl}")
	private ContratantePJCtrl contratantePJCtrl;

	private List<SelectItem> tipoIdentificacionContratanteItems;
	private List<SelectItem> tipoIdentificacionItems;

	private List<SelectItem> tipoEmpleoItems;

	private String controlEdicion;

	public void ponerTipoInstitucionPN(AjaxBehaviorEvent event) {
		ReferenciaBancaria rb = (ReferenciaBancaria) getExternalContext().getRequestMap().get("refb");
		Short secInst = rb.getInstitucionFinanciera().getSecInstitucionFinanciera();
		if (secInst != -1) {
			InstitucionFinanciera ifi = institucionFinancieraServicio.findByPk(secInst);
			rb.setInstitucionFinanciera(ifi);

			// carga los posibles tipos
			rb.setTipoServicioInstFinServicioLista(tipoServicioInstFinServicio
					.obtenerPorTipoFinanciera(ifi.getTipoInstitucionFinanciera().getSecTipoFinanciera()));

		}

	}

	public String verificarSiExisteContratante() {
		try {
			validarDatosDefinicion();

			if (TipoPersonaEnum.J.equals(tipoPersona)) {
				verformularioPJ = true;
				verformularioPN = false;

				getContratantePJCtrl().setPersonaJuridica(contratanteServicio.verificarExistenciaPJ(identificacion));
			} else {

				verformularioPJ = false;
				verformularioPN = true;
				getContratantePNCtrl().setPersonaNatural(contratanteServicio.verificarExistenciaPN(identificacion));
			}
		} catch (ValidationException e) {
			addErrorMessage(null, e.getMessage(), null);
		}

		return null;
	}

	private void validarDatosDefinicion() throws ValidationException {
		StringBuilder msg = new StringBuilder(500);

		if (tipoPersona == null) {
			msg.append("- Tipo persona<br/>");
		}

		if (tipoIdentificacion == null) {
			msg.append("- Tipo identificaci\u00F3n<br/>");
		}

		if (identificacion == null || identificacion.trim().length() <= 0) {
			msg.append("- Identificaci\u00F3n<br/>");
		} else {
			if (identificacion.length() != 13) {
				msg.append("- Identificaci\u00F3n debe tener 13 caracteres<br/>");
			}
		}

		if (msg.length() > 0) {
			String error = "Por favor revise: <br/><br/>".concat(msg.toString());

			throw new ValidationException(error);
		} else {
			if (ValidatorUtils.validaSoloNumeros(identificacion)) {
				throw new ValidationException("Solo se aceptan d\u00EDgitos");
			}
		}

		if (tipoPersona.equals(TipoPersonaEnum.N)) {
			if (identificacion.charAt(2) == '9' || identificacion.charAt(2) == '6') {
				throw new ValidationException("Identificaci\u00F3n no corresponde a persona natural");
			}
		} else {
			if (identificacion.charAt(2) != '9' && identificacion.charAt(2) != '6') {
				throw new ValidationException("Idetificaci\u00F3n no corresponde a persona jur\u00EDdica");
			}
		}
	}

	public void cambiarTipoIdentificacionRepLegalPJ(ValueChangeEvent e) {
		tipoIdentificacionRepLegal = (Character) e.getNewValue();
	}

	/**
	 * @return the tipoIdentificacionContratanteItems
	 */
	public List<SelectItem> getTipoIdentificacionContratanteItems() {
		if (tipoIdentificacionContratanteItems == null) {
			tipoIdentificacionContratanteItems = new ArrayList<SelectItem>();
			tipoIdentificacionContratanteItems
					.add(new SelectItem(TipoIdentificacionEnum.RUC.getCodigo(), TipoIdentificacionEnum.RUC.toString()));
		}

		return tipoIdentificacionContratanteItems;
	}

	/**
	 * @param tipoIdentificacionContratanteItems the
	 *                                           tipoIdentificacionContratanteItems
	 *                                           to set
	 */
	public void setTipoIdentificacionContratanteItems(List<SelectItem> tipoIdentificacionContratanteItems) {
		this.tipoIdentificacionContratanteItems = tipoIdentificacionContratanteItems;
	}

	public List<SelectItem> getTipoIdentificacionItems() {
		if (tipoIdentificacionItems == null) {
			tipoIdentificacionItems = new ArrayList<SelectItem>();

			List<TipoIdentificacion> tmpList = tipoIdentificacionServicio.findAll();

			for (TipoIdentificacion tipoIdentificacion : tmpList) {
				if (TipoIdentificacionEnum
						.isParaRepresentateLegal(tipoIdentificacion.getCodTipoIdentificacion().charValue())) {
					SelectItem item = new SelectItem(tipoIdentificacion.getCodTipoIdentificacion(),
							tipoIdentificacion.getTipoIdentificacion());
					tipoIdentificacionItems.add(item);
				}
			}
		}

		return tipoIdentificacionItems;
	}

	public void setTipoIdentificacionItems(List<SelectItem> tipoIdentificacionItems) {
		this.tipoIdentificacionItems = tipoIdentificacionItems;
	}

	public TipoPersonaEnum getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersonaEnum tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public Character getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(Character tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public SelectCtrl getSelectCtrl() {
		return selectCtrl;
	}

	public void setSelectCtrl(SelectCtrl selectCtrl) {
		this.selectCtrl = selectCtrl;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public TipoFormularioContratanteEnum getTipoFormulario() {
		return tipoFormulario;
	}

	public void setTipoFormulario(TipoFormularioContratanteEnum tipoFormulario) {
		this.tipoFormulario = tipoFormulario;
	}

	public boolean isVerformularioPJ() {
		return verformularioPJ;
	}

	public void setVerformularioPJ(boolean verformularioPJ) {
		this.verformularioPJ = verformularioPJ;
	}

	public boolean isVerformularioPN() {
		return verformularioPN;
	}

	public void setVerformularioPN(boolean verformularioPN) {
		this.verformularioPN = verformularioPN;
	}

	public TipoContratanteEnum getTipoContratante() {
		return tipoContratante;
	}

	public void setTipoContratante(TipoContratanteEnum tipoContratante) {
		this.tipoContratante = tipoContratante;
	}

	public TipoConceptoContratanteEnum getTipoConceptoContratante() {
		return tipoConceptoContratante;
	}

	public void setTipoConceptoContratante(TipoConceptoContratanteEnum tipoConceptoContratante) {
		this.tipoConceptoContratante = tipoConceptoContratante;
	}

	public Character getTipoIdentificacionRepLegal() {
		return tipoIdentificacionRepLegal;
	}

	public void setTipoIdentificacionRepLegal(Character tipoIdentificacionRepLegal) {
		this.tipoIdentificacionRepLegal = tipoIdentificacionRepLegal;
	}

	public String getIdentificacionRepLegal() {
		return identificacionRepLegal;
	}

	public void setIdentificacionRepLegal(String identificacionRepLegal) {
		this.identificacionRepLegal = identificacionRepLegal;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	/**
	 * @return the tipoOcupacionItems
	 */
	public List<SelectItem> getTipoEmpleoItems() {
		if (tipoEmpleoItems == null) {
			tipoEmpleoItems = new ArrayList<SelectItem>();

			for (TipoEmpleoEnum to : TipoEmpleoEnum.values()) {
				tipoEmpleoItems.add(new SelectItem(to.getTipoEmpleo(), getBundleEtiquetas(to.getKeyBundle(), null)));
			}
		}
		return tipoEmpleoItems;
	}

	/**
	 * @param tipoOcupacionItems the tipoOcupacionItems to set
	 */
	public void setTipoEmpleoItems(List<SelectItem> tipoEmpleoItems) {
		this.tipoEmpleoItems = tipoEmpleoItems;
	}

	/**
	 * @return the tipoOcupacionPN
	 */
	public TipoOcupacionEnum getTipoOcupacionPN() {
		return tipoOcupacionPN;
	}

	/**
	 * @param tipoOcupacionPN the tipoOcupacionPN to set
	 */
	public void setTipoOcupacionPN(TipoOcupacionEnum tipoOcupacionPN) {
		this.tipoOcupacionPN = tipoOcupacionPN;
	}

	/**
	 * @return the contratantePNCtrl
	 */
	public ContratantePNCtrl getContratantePNCtrl() {
		return contratantePNCtrl;
	}

	/**
	 * @param contratantePNCtrl the contratantePNCtrl to set
	 */
	public void setContratantePNCtrl(ContratantePNCtrl contratantePNCtrl) {
		this.contratantePNCtrl = contratantePNCtrl;
	}

	/**
	 * @return the contratantePJCtrl
	 */
	public ContratantePJCtrl getContratantePJCtrl() {
		return contratantePJCtrl;
	}

	/**
	 * @param contratantePJCtrl the contratantePJCtrl to set
	 */
	public void setContratantePJCtrl(ContratantePJCtrl contratantePJCtrl) {
		this.contratantePJCtrl = contratantePJCtrl;
	}

	/**
	 * @return the controlEdicion
	 */
	public String getControlEdicion() {
		if (controlEdicion == null) {
			controlEdicion = "OK";

			String sec = getHttpServletRequestParam("sec");
			String tipo = getHttpServletRequestParam("tipo");
			String ruc = getHttpServletRequestParam("iden");

			if (sec != null && sec.trim().length() > 0 && tipo != null && tipo.trim().length() > 0 && ruc != null
					&& ruc.trim().length() > 0) {
				TipoPersonaEnum tipoEnum = TipoPersonaEnum.valueOf(tipo);
				Integer secInt = Integer.valueOf(sec);

				if (TipoPersonaEnum.N.equals(tipoEnum)) {
					verformularioPJ = false;
					verformularioPN = true;

					getContratantePNCtrl().setPersonaNatural(contratanteServicio.verificarExistenciaPN(secInt, ruc));
				} else {
					verformularioPJ = true;
					verformularioPN = false;

					getContratantePJCtrl().setPersonaJuridica(contratanteServicio.verificarExistenciaPJ(ruc));
				}
			}
		}
		return controlEdicion;
	}

	/**
	 * @param controlEdicion the controlEdicion to set
	 */
	public void setControlEdicion(String controlEdicion) {
		this.controlEdicion = controlEdicion;
	}

	public boolean isHabilitarBtnGuardarContratante() {
		if (isUserInRole(PerfilEquividaEnum.CU_CONTRATANTE.toString())) {
			return true;
		}
		return false;
	}
}
