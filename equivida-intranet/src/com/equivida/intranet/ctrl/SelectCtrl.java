package com.equivida.intranet.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.equivida.constant.AntiguedadEnum;
import com.equivida.constant.Constantes;
import com.equivida.constant.EmpresaEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.SexoEnum;
import com.equivida.constant.TipoBeneficiarioEnum;
import com.equivida.constant.TipoConceptoContratanteEnum;
import com.equivida.constant.TipoContratanteEnum;
import com.equivida.constant.TipoDeportePracticaEnum;
import com.equivida.constant.TipoEmpleoEnum;
import com.equivida.constant.TipoFormularioContratanteEnum;
import com.equivida.constant.TipoFormularioEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.constant.TipoPersonaEnum;
import com.equivida.intranet.util.BaseCtrl;
import com.equivida.modelo.CategoriaPpe;
import com.equivida.modelo.CompaniaSeguro;
import com.equivida.modelo.Deporte;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.EstadoRcs;
import com.equivida.modelo.InstitucionFinanciera;
import com.equivida.modelo.RamoSeguro;
import com.equivida.modelo.TipoActividad;
import com.equivida.modelo.TipoDireccion;
import com.equivida.modelo.TipoDireccionElectronica;
import com.equivida.modelo.TipoEstado;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoOtraOcupacion;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.modelo.TipoPersonaJuridica;
import com.equivida.modelo.TipoRiesgo;
import com.equivida.modelo.TipoTelefono;
import com.equivida.modelo.TipoVisa;
import com.equivida.servicio.CategoriaPpeServicio;
import com.equivida.servicio.CompaniaSeguroServicio;
import com.equivida.servicio.DeporteServicio;
import com.equivida.servicio.EstadoCivilServicio;
import com.equivida.servicio.EstadoRcsServicio;
import com.equivida.servicio.InstitucionFinancieraServicio;
import com.equivida.servicio.RamoSeguroServicio;
import com.equivida.servicio.TipoActividadServicio;
import com.equivida.servicio.TipoDireccionElectronicaServicio;
import com.equivida.servicio.TipoDireccionServicio;
import com.equivida.servicio.TipoEstadoServicio;
import com.equivida.servicio.TipoIdentificacionServicio;
import com.equivida.servicio.TipoOtraOcupacionServicio;
import com.equivida.servicio.TipoParentescoRelacionServicio;
import com.equivida.servicio.TipoPersonaJuridicaServicio;
import com.equivida.servicio.TipoRiesgoServicio;
import com.equivida.servicio.TipoTelefonoServicio;
import com.equivida.servicio.TipoVisaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.util.Criteria;

@ManagedBean(name = "selectCtrl")
@ViewScoped
public class SelectCtrl extends BaseCtrl {

	private static final long serialVersionUID = -8684596223802346235L;

	@EJB(mappedName = "EstadoCivilServicio/local")
	private EstadoCivilServicio estadoCivilServicio;

	@EJB(mappedName = "DeporteServicio/local")
	private DeporteServicio deporteServicio;

	@EJB(mappedName = "TipoEstadoServicio/local")
	private TipoEstadoServicio tipoEstadoServicio;

	@EJB(mappedName = "TipoPersonaJuridicaServicio/local")
	private TipoPersonaJuridicaServicio tipoPersonaJuridicaServicio;

	@EJB(mappedName = "TipoIdentificacionServicio/local")
	private TipoIdentificacionServicio tipoIdentificacionServicio;

	@EJB(mappedName = "TipoRiesgoServicio/local")
	private TipoRiesgoServicio tipoRiesgoServicio;

	@EJB(mappedName = "TipoDireccionServicio/local")
	private TipoDireccionServicio tipoDireccionServicio;

	@EJB(mappedName = "TipoDireccionElectronicaServicio/local")
	private TipoDireccionElectronicaServicio tipoDireccionElectronicaServicio;

	@EJB(mappedName = "TipoTelefonoServicio/local")
	private TipoTelefonoServicio tipoTelefonoServicio;

	@EJB(mappedName = "TipoActividadServicio/local")
	private TipoActividadServicio tipoActividadServicio;

	@EJB(mappedName = "TipoParentescoRelacionServicio/local")
	private TipoParentescoRelacionServicio tipoParentescoRelacionServicio;

	@EJB(mappedName = "CategoriaPpeServicio/local")
	private CategoriaPpeServicio categoriaPpeServicio;

	@EJB(mappedName = "InstitucionFinancieraServicio/local")
	private InstitucionFinancieraServicio institucionFinancieraServicio;

	@EJB(mappedName = "TipoOtraOcupacionServicio/local")
	private TipoOtraOcupacionServicio tipoOtraOcupacionServicio;

	@EJB(mappedName = "TipoVisaServicio/local")
	private TipoVisaServicio tipoVisaServicio;

	@EJB(mappedName = "CompaniaSeguroServicio/local")
	private CompaniaSeguroServicio companiaSeguroServicio;

	@EJB(mappedName = "RamoSeguroServicio/local")
	private RamoSeguroServicio ramoSeguroServicio;

	@EJB(mappedName = "EstadoRcsServicio/local")
	private EstadoRcsServicio estadoRcsServicio;

	private List<SelectItem> estadoCivilItems;

	private List<SelectItem> estadoCivilVisiblesItems;

	private List<SelectItem> tipoPeronaJuridicaItems;

	private List<SelectItem> tipoEmpleoItems;

	private List<SelectItem> generoItems;

	private List<SelectItem> tipoDocumentoItems;

	private List<SelectItem> tipoDocumentoParaBeneficiacioItems;

	private List<SelectItem> tipoDocumentoParaPersonaNaturalItems;

	private List<SelectItem> selectSN;

	private List<SelectItem> tipoDireccionItems;

	private List<SelectItem> tipoDireccionElectronicaItems;

	private List<SelectItem> tipoTelefonoItems;

	private List<SelectItem> tipoTelefonoSinDireccionItems;

	private List<SelectItem> actividadesItems;

	private List<TipoActividad> tipoActividades;

	private List<SelectItem> tipoBeneficiarioItems;

	private List<SelectItem> categoriaPepItems;

	private List<SelectItem> tipoParentescoFamiliaresItems;

	private List<SelectItem> tipoParentescoFamiliaresYNoFamiliaresItems;

	private List<SelectItem> antiguedadItems;

	private List<SelectItem> tipoRiesgoItems;

	private List<SelectItem> deportesItems;

	private List<SelectItem> tipoEstadoPersonaItems;

	private List<SelectItem> tipoDeportePracticaItems;

	private List<SelectItem> tipoFormularioItems;

	private List<SelectItem> tipoVisaItems;

	private List<SelectItem> institucionFinancieraItems;

	private List<SelectItem> companiaSeguroItems;

	private List<SelectItem> ramoSeguroItems;

	private List<SelectItem> estadoRcsItems;

	private List<SelectItem> empresaItems;

	private List<SelectItem> tipoPersonaItems;

	private List<SelectItem> tipoFormularioContratanteItems;

	private List<SelectItem> tipoContratanteItems;

	private List<SelectItem> tipoConceptoContratanteItems;

	/**
	 * @return the estadoCivilItems
	 */
	public List<SelectItem> getEstadoCivilItems() {
		if (estadoCivilItems == null) {
			estadoCivilItems = new ArrayList<SelectItem>();
			List<EstadoCivil> tmpList = estadoCivilServicio.findAll();
			for (EstadoCivil estadoCivil : tmpList) {
				SelectItem item = new SelectItem(estadoCivil.getCodEstadoCivil(), estadoCivil.getEstadoCivil());
				estadoCivilItems.add(item);
			}
		}

		return estadoCivilItems;
	}

	/**
	 * @param estadoCivilItems the estadoCivilItems to set
	 */
	public void setEstadoCivilItems(List<SelectItem> estadoCivilItems) {
		this.estadoCivilItems = estadoCivilItems;
	}

	/**
	 * @return the tipoEmpleoItems
	 */
	public List<SelectItem> getTipoEmpleoItems() {
		if (tipoEmpleoItems == null) {
			tipoEmpleoItems = new ArrayList<SelectItem>();

			List<TipoOtraOcupacion> lista = tipoOtraOcupacionServicio.findAll();
			for (TipoOtraOcupacion tipo : lista) {
				if (tipo.getCodOtraOcupacion().shortValue() != Constantes.ID_TIPO_OTRA_OCUPACION_NINGUNA) {
					tipoEmpleoItems.add(new SelectItem(tipo.getCodOtraOcupacion(), tipo.getOtraOcupacion()));
				}
			}

			TipoEmpleoEnum[] values = TipoEmpleoEnum.values();
			for (TipoEmpleoEnum st : values) {
				String label = getBundleEtiquetas(st.getKeyBundle(), null);
				SelectItem item = new SelectItem(st.getTipoEmpleo(), label);
				tipoEmpleoItems.add(item);
			}

		}
		return tipoEmpleoItems;
	}

	/**
	 * @param tipoOficioItems the tipoOficioItems to set
	 */
	public void setTipoEmpleoItems(List<SelectItem> tipoEmpleoItems) {
		this.tipoEmpleoItems = tipoEmpleoItems;
	}

	/**
	 * @return the tipoPeronaJuridicaItems
	 */
	public List<SelectItem> getTipoPeronaJuridicaItems() {

		if (tipoPeronaJuridicaItems == null) {
			tipoPeronaJuridicaItems = new ArrayList<SelectItem>();
			List<TipoPersonaJuridica> tmpList = tipoPersonaJuridicaServicio.findAll();
			for (TipoPersonaJuridica tipoPersonaJuridica : tmpList) {
				SelectItem item = new SelectItem(tipoPersonaJuridica.getCodTipoPersonaJuridica(),
						tipoPersonaJuridica.getTipoPersonaJuridica());
				tipoPeronaJuridicaItems.add(item);
			}
		}

		return tipoPeronaJuridicaItems;
	}

	/**
	 * @param tipoPeronaJuridicaItems the tipoPeronaJuridicaItems to set
	 */
	public void setTipoPeronaJuridicaItems(List<SelectItem> tipoPeronaJuridicaItems) {
		this.tipoPeronaJuridicaItems = tipoPeronaJuridicaItems;
	}

	/**
	 * @return the generoItems
	 */
	public List<SelectItem> getGeneroItems() {

		if (generoItems == null) {
			generoItems = new ArrayList<SelectItem>();
			SexoEnum[] values = SexoEnum.values();
			for (SexoEnum item : values) {
				generoItems.add(new SelectItem(item.getCodigo(), item.toString()));
			}
		}
		return generoItems;
	}

	/**
	 * @param generoItems the generoItems to set
	 */
	public void setGeneroItems(List<SelectItem> sexoItems) {
		this.generoItems = sexoItems;
	}

	/**
	 * @return the tipoDocumentoItems
	 */
	public List<SelectItem> getTipoDocumentoItems() {

		if (tipoDocumentoItems == null) {
			tipoDocumentoItems = new ArrayList<SelectItem>();
			List<TipoIdentificacion> tmpList = tipoIdentificacionServicio.findAll();
			for (TipoIdentificacion tipoIdentificacion : tmpList) {
				SelectItem item = new SelectItem(tipoIdentificacion.getCodTipoIdentificacion(),
						tipoIdentificacion.getTipoIdentificacion());
				tipoDocumentoItems.add(item);
			}
		}

		return tipoDocumentoItems;
	}

	/**
	 * @param tipoDocumentoItems the tipoDocumentoItems to set
	 */
	public void setTipoDocumentoItems(List<SelectItem> tipoDocumentoItems) {
		this.tipoDocumentoItems = tipoDocumentoItems;
	}

	/**
	 * @return the selectSN
	 */
	public List<SelectItem> getSelectSN() {
		if (selectSN == null) {
			selectSN = new ArrayList<SelectItem>();
			RespuestaEnum[] values = RespuestaEnum.values();
			for (RespuestaEnum item : values) {
				selectSN.add(new SelectItem(item.getCodigo(), item.toString()));
			}
		}
		return selectSN;
	}

	/**
	 * @param selectSN the selectSN to set
	 */
	public void setSelectSN(List<SelectItem> selectSN) {
		this.selectSN = selectSN;
	}

	/**
	 * @return the tipoDireccionItems
	 */
	public List<SelectItem> getTipoDireccionItems() {
		if (tipoDireccionItems == null) {
			tipoDireccionItems = new ArrayList<SelectItem>();
			List<TipoDireccion> tmpList = tipoDireccionServicio.findAll();
			for (TipoDireccion tipoDireccion : tmpList) {
				SelectItem item = new SelectItem(tipoDireccion.getCodTipoDireccion(), tipoDireccion.getTipoDireccion());
				tipoDireccionItems.add(item);
			}
		}
		return tipoDireccionItems;
	}

	/**
	 * @param tipoDireccionItems the tipoDireccionItems to set
	 */
	public void setTipoDireccionItems(List<SelectItem> tipoDireccionItems) {
		this.tipoDireccionItems = tipoDireccionItems;
	}

	/**
	 * @return the tipoDireccionElectronicaItems
	 */
	public List<SelectItem> getTipoDireccionElectronicaItems() {
		if (tipoDireccionElectronicaItems == null) {
			tipoDireccionElectronicaItems = new ArrayList<SelectItem>();
			List<TipoDireccionElectronica> tmpList = tipoDireccionElectronicaServicio.findAll();
			for (TipoDireccionElectronica te : tmpList) {
				// no se considera Correo electrónico empresarial ni sitio Web, se ha pedido
				// mejor un estado en la bdd, pero nos han dicho que lo pongamos no mas en
				// codigo
				if (te.getTipoDireccionElectronica().contains("empresarial")
						|| te.getTipoDireccionElectronica().contains("web")) {
					continue;
				}
				SelectItem item = new SelectItem(te.getCodTipoDireccionElectronica(), te.getTipoDireccionElectronica());
				tipoDireccionElectronicaItems.add(item);
			}
		}
		return tipoDireccionElectronicaItems;
	}

	/**
	 * @param tipoDireccionElectronicaItems the tipoDireccionElectronicaItems to set
	 */
	public void setTipoDireccionElectronicaItems(List<SelectItem> tipoDireccionElectronicaItems) {
		this.tipoDireccionElectronicaItems = tipoDireccionElectronicaItems;
	}

	/**
	 * @return the tipoTelefonoItems
	 */
	public List<SelectItem> getTipoTelefonoItems() {
		if (tipoTelefonoItems == null) {
			tipoTelefonoItems = new ArrayList<SelectItem>();
			String[] criteriasAnd = { "reqDireccion" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
			Object[] params = new Object[] { RespuestaEnum.SI.getCodigo() };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			List<TipoTelefono> tmpList = tipoTelefonoServicio.findByCriterias(criteria);

			for (TipoTelefono tipoTlf : tmpList) {
				SelectItem item = new SelectItem(tipoTlf.getCodTipoTelefono(), tipoTlf.getTipoTelefono());
				tipoTelefonoItems.add(item);
			}
		}
		return tipoTelefonoItems;
	}

	/**
	 * @param tipoTelefonoItems the tipoTelefonoItems to set
	 */
	public void setTipoTelefonoItems(List<SelectItem> tipoTelefonoItems) {
		this.tipoTelefonoItems = tipoTelefonoItems;
	}

	/**
	 * @return the tipoTelefonoSinDireccionItems
	 */
	public List<SelectItem> getTipoTelefonoSinDireccionItems() {

		if (tipoTelefonoSinDireccionItems == null) {
			tipoTelefonoSinDireccionItems = new ArrayList<SelectItem>();
			String[] criteriasAnd = { "reqDireccion", "codTipoTelefono", "codigoVisible" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS, CriteriaTypeEnum.SHORT_NOT_EQUALS,
					CriteriaTypeEnum.STRING_EQUALS };
			Object[] params = new Object[] { RespuestaEnum.NO.getCodigo(), new Integer(0).shortValue(),
					RespuestaEnum.SI.getCodigo() };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			List<TipoTelefono> tmpList = tipoTelefonoServicio.findByCriterias(criteria);

			for (TipoTelefono tipoTlf : tmpList) {
				SelectItem item = new SelectItem(tipoTlf.getCodTipoTelefono(), tipoTlf.getTipoTelefono());
				tipoTelefonoSinDireccionItems.add(item);
			}
		}

		return tipoTelefonoSinDireccionItems;
	}

	/**
	 * @param tipoTelefonoSinDireccionItems the tipoTelefonoSinDireccionItems to set
	 */
	public void setTipoTelefonoSinDireccionItems(List<SelectItem> tipoTelefonoSinDireccionItems) {
		this.tipoTelefonoSinDireccionItems = tipoTelefonoSinDireccionItems;
	}

	/**
	 * @return the actividadesItems
	 */
	public List<SelectItem> getActividadesItems() {

		if (actividadesItems == null) {
			actividadesItems = new ArrayList<SelectItem>();
			List<TipoActividad> tmpList = tipoActividadServicio.findAll();
			for (TipoActividad tipoActividad : tmpList) {
				SelectItem item = new SelectItem(tipoActividad.getCodActividad(), tipoActividad.getActividad());
				actividadesItems.add(item);
			}
		}

		return actividadesItems;
	}

	/**
	 * @param actividadesItems the actividadesItems to set
	 */
	public void setActividadesItems(List<SelectItem> actividadesItems) {
		this.actividadesItems = actividadesItems;
	}

	/**
	 * @return the tipoBeneficiarioItems
	 */
	public List<SelectItem> getTipoBeneficiarioItems() {
		if (tipoBeneficiarioItems == null) {
			tipoBeneficiarioItems = new ArrayList<SelectItem>();
			TipoBeneficiarioEnum[] values = TipoBeneficiarioEnum.values();
			for (TipoBeneficiarioEnum st : values) {
				String label = getBundleEtiquetas(st.getKeyBundle(), null);
				SelectItem item = new SelectItem(st.getCodigo(), label);
				tipoBeneficiarioItems.add(item);
			}
		}

		return tipoBeneficiarioItems;
	}

	/**
	 * @param tipoBeneficiarioItems the tipoBeneficiarioItems to set
	 */
	public void setTipoBeneficiarioItems(List<SelectItem> tipoBeneficiarioItems) {
		this.tipoBeneficiarioItems = tipoBeneficiarioItems;
	}

	/**
	 * @return the categoriaPepItems
	 */
	public List<SelectItem> getCategoriaPepItems() {

		if (categoriaPepItems == null) {
			categoriaPepItems = new ArrayList<SelectItem>();
			List<CategoriaPpe> categoriaPpeList = categoriaPpeServicio.findAll();
			for (CategoriaPpe categoriaPpe : categoriaPpeList) {
				SelectItem item = new SelectItem(categoriaPpe.getCodCategoriaPpe(), categoriaPpe.getCategoriaPpe());
				categoriaPepItems.add(item);
			}
		}
		return categoriaPepItems;
	}

	/**
	 * public List<SelectItem> getTipoFormularioItems() { return
	 * tipoFormularioItems; }
	 * 
	 * public void setTipoFormularioItems(List<SelectItem> tipoFormularioItems) {
	 * this.tipoFormularioItems = tipoFormularioItems; }
	 * 
	 * @param categoriaPepItems the categoriaPepItems to set
	 */
	public void setCategoriaPepItems(List<SelectItem> categoriaPepItems) {
		this.categoriaPepItems = categoriaPepItems;
	}

	public TipoParentescoRelacionServicio getTipoParentescoRelacionServicio() {
		return tipoParentescoRelacionServicio;
	}

	public void setTipoParentescoRelacionServicio(TipoParentescoRelacionServicio tipoParentescoRelacionServicio) {
		this.tipoParentescoRelacionServicio = tipoParentescoRelacionServicio;
	}

	public List<SelectItem> getTipoParentescoFamiliaresYNoFamiliaresItems() {
		if (tipoParentescoFamiliaresYNoFamiliaresItems == null) {
			tipoParentescoFamiliaresYNoFamiliaresItems = new ArrayList<SelectItem>();
			String[] criteriasAnd = null;
			CriteriaTypeEnum[] typesAnd = null;
			Object[] params = null;
			String[] orderBy = { "tipoParentesco" };
			boolean[] asc = { true };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params, orderBy, asc);
			List<TipoParentescoRelacion> tmpList = tipoParentescoRelacionServicio.findByCriterias(criteria);

			for (TipoParentescoRelacion tipo : tmpList) {
				SelectItem item = new SelectItem(tipo.getCodTipoParentesco(), tipo.getTipoParentesco());
				tipoParentescoFamiliaresYNoFamiliaresItems.add(item);

			}
		}
		return tipoParentescoFamiliaresYNoFamiliaresItems;
	}

	public void setTipoParentescoFamiliaresYNoFamiliaresItems(
			List<SelectItem> tipoParentescoFamiliaresYNoFamiliaresItems) {
		this.tipoParentescoFamiliaresYNoFamiliaresItems = tipoParentescoFamiliaresYNoFamiliaresItems;
	}

	public List<SelectItem> getTipoParentescoFamiliaresItems() {
		if (tipoParentescoFamiliaresItems == null) {
			tipoParentescoFamiliaresItems = new ArrayList<SelectItem>();
			String[] criteriasAnd = { "flgFamiliar" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
			Object[] params = new Object[] { "S" };
			String[] orderBy = { "tipoParentesco" };
			boolean[] asc = { true };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params, orderBy, asc);
			List<TipoParentescoRelacion> tmpList = tipoParentescoRelacionServicio.findByCriterias(criteria);

			for (TipoParentescoRelacion tipo : tmpList) {
				SelectItem item = new SelectItem(tipo.getCodTipoParentesco(), tipo.getTipoParentesco());
				tipoParentescoFamiliaresItems.add(item);

			}
		}
		return tipoParentescoFamiliaresItems;
	}

	public void setTipoParentescoFamiliaresItems(List<SelectItem> tipoParentescoFamiliaresItems) {
		this.tipoParentescoFamiliaresItems = tipoParentescoFamiliaresItems;
	}

	public List<SelectItem> getAntiguedadItems() {
		if (antiguedadItems == null) {
			antiguedadItems = new ArrayList<SelectItem>();
			AntiguedadEnum[] values = AntiguedadEnum.values();
			for (AntiguedadEnum ae : values) {
				String label = getBundleEtiquetas(ae.getKeyBundle(), null);
				SelectItem item = new SelectItem(ae.getCodigo(), label);
				antiguedadItems.add(item);
			}
		}
		return antiguedadItems;
	}

	public void setAntiguedadItems(List<SelectItem> antiguedadItems) {
		this.antiguedadItems = antiguedadItems;
	}

	public List<SelectItem> getEstadoCivilVisiblesItems() {
		if (estadoCivilVisiblesItems == null) {
			estadoCivilVisiblesItems = new ArrayList<SelectItem>();

			String[] criteriasAnd = { "codigoVisible" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
			Object[] params = new Object[] { "S" };
			String[] orderBy = { "estadoCivil" };
			boolean[] asc = { true };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params, orderBy, asc);

			List<EstadoCivil> tmpList = estadoCivilServicio.findByCriterias(criteria);
			for (EstadoCivil estadoCivil : tmpList) {
				SelectItem item = new SelectItem(estadoCivil.getCodEstadoCivil(), estadoCivil.getEstadoCivil());
				estadoCivilVisiblesItems.add(item);
			}
		}
		return estadoCivilVisiblesItems;
	}

	public void setEstadoCivilVisiblesItems(List<SelectItem> estadoCivilVisiblesItems) {
		this.estadoCivilVisiblesItems = estadoCivilVisiblesItems;
	}

	public List<SelectItem> getTipoDocumentoParaPersonaNaturalItems() {
		if (tipoDocumentoParaPersonaNaturalItems == null) {
			tipoDocumentoParaPersonaNaturalItems = new ArrayList<SelectItem>();
			List<TipoIdentificacion> tmpList = tipoIdentificacionServicio.findAll();
			for (TipoIdentificacion tipoIdentificacion : tmpList) {
				if (TipoIdentificacionEnum
						.isParaIngresoPersonaNatural(tipoIdentificacion.getCodTipoIdentificacion().charValue())) {
					SelectItem item = new SelectItem(tipoIdentificacion.getCodTipoIdentificacion(),
							tipoIdentificacion.getTipoIdentificacion());
					tipoDocumentoParaPersonaNaturalItems.add(item);
				}
			}
		}
		return tipoDocumentoParaPersonaNaturalItems;
	}

	public void setTipoDocumentoParaPersonaNaturalItems(List<SelectItem> tipoDocumentoParaPersonaNaturalItems) {
		this.tipoDocumentoParaPersonaNaturalItems = tipoDocumentoParaPersonaNaturalItems;
	}

	public List<SelectItem> getTipoRiesgoItems() {
		if (tipoRiesgoItems == null) {
			tipoRiesgoItems = new ArrayList<SelectItem>();
			List<TipoRiesgo> tmpList = tipoRiesgoServicio.findAll();
			for (TipoRiesgo tipoRiesgo : tmpList) {

				SelectItem item = new SelectItem(tipoRiesgo.getCodTipoRiesgo(), tipoRiesgo.getTipoRiesgo() + "");
				tipoRiesgoItems.add(item);
			}
		}
		return tipoRiesgoItems;
	}

	public void setTipoRiesgoItems(List<SelectItem> tipoRiesgoItems) {
		this.tipoRiesgoItems = tipoRiesgoItems;
	}

	public List<TipoActividad> getTipoActividades() {
		if (tipoActividades == null) {
			tipoActividades = tipoActividadServicio.findAll();
		}
		return tipoActividades;
	}

	public void setTipoActividades(List<TipoActividad> tipoActividades) {
		this.tipoActividades = tipoActividades;
	}

	public List<SelectItem> getDeportesItems() {
		if (deportesItems == null) {
			List<Deporte> tmpList = deporteServicio.findAll();
			deportesItems = new ArrayList<SelectItem>();
			for (Deporte d : tmpList) {
				SelectItem si = new SelectItem(d.getCodDeporte(), d.getDeporte());
				deportesItems.add(si);
			}

		}
		return deportesItems;
	}

	public void setDeportesItems(List<SelectItem> deportesItems) {
		this.deportesItems = deportesItems;
	}

	public List<SelectItem> getTipoDeportePracticaItems() {
		if (tipoDeportePracticaItems == null) {
			TipoDeportePracticaEnum[] values = TipoDeportePracticaEnum.values();
			tipoDeportePracticaItems = new ArrayList<SelectItem>();
			for (TipoDeportePracticaEnum t : values) {
				SelectItem si = new SelectItem(t.getCodigo(), getBundleEtiquetas(t.getKeyBundle(), null));
				tipoDeportePracticaItems.add(si);
			}

		}
		return tipoDeportePracticaItems;
	}

	public void setTipoDeportePracticaItems(List<SelectItem> tipoDeportePracticaItems) {
		this.tipoDeportePracticaItems = tipoDeportePracticaItems;
	}

	public List<SelectItem> getTipoDocumentoParaBeneficiacioItems() {
		if (tipoDocumentoParaBeneficiacioItems == null) {
			if (tipoDocumentoParaBeneficiacioItems == null) {
				tipoDocumentoParaBeneficiacioItems = new ArrayList<SelectItem>();
				List<TipoIdentificacion> tmpList = tipoIdentificacionServicio.findAll();
				for (TipoIdentificacion tipoIdentificacion : tmpList) {
					if (TipoIdentificacionEnum
							.isParaIngresoBeneficiario(tipoIdentificacion.getCodTipoIdentificacion().charValue())) {
						SelectItem item = new SelectItem(tipoIdentificacion.getCodTipoIdentificacion(),
								tipoIdentificacion.getTipoIdentificacion());
						tipoDocumentoParaBeneficiacioItems.add(item);
					}
				}
			}
		}
		return tipoDocumentoParaBeneficiacioItems;
	}

	public void setTipoDocumentoParaBeneficiacioItems(List<SelectItem> tipoDocumentoParaBeneficiacioItems) {
		this.tipoDocumentoParaBeneficiacioItems = tipoDocumentoParaBeneficiacioItems;
	}

	public TipoEstadoServicio getTipoEstadoServicio() {
		return tipoEstadoServicio;
	}

	public void setTipoEstadoServicio(TipoEstadoServicio tipoEstadoServicio) {
		this.tipoEstadoServicio = tipoEstadoServicio;
	}

	public List<SelectItem> getTipoEstadoPersonaItems() {
		if (tipoEstadoPersonaItems == null) {
			List<TipoEstado> tmpList = tipoEstadoServicio.findAll();
			tipoEstadoPersonaItems = new ArrayList<SelectItem>();
			for (TipoEstado te : tmpList) {
				SelectItem si = new SelectItem(te.getCodEstado(), te.getEstadoAsegurado());
				tipoEstadoPersonaItems.add(si);
			}

		}
		return tipoEstadoPersonaItems;
	}

	public void setTipoEstadoPersonaItems(List<SelectItem> tipoEstadoPersonaItems) {
		this.tipoEstadoPersonaItems = tipoEstadoPersonaItems;
	}

	public List<SelectItem> getTipoFormularioItems() {
		if (tipoFormularioItems == null) {
			tipoFormularioItems = new ArrayList<SelectItem>();
			TipoFormularioEnum[] values = TipoFormularioEnum.values();
			for (TipoFormularioEnum item : values) {
				String label = getBundleEtiquetas(item.getBundle(), null);
				SelectItem si = new SelectItem(item.getSecuencial(), label);
				tipoFormularioItems.add(si);
			}
		}
		return tipoFormularioItems;
	}

	public void setTipoFormularioItems(List<SelectItem> tipoFormularioItems) {
		this.tipoFormularioItems = tipoFormularioItems;
	}

	/**
	 * @return the institucionFinancieraItems
	 */
	public List<SelectItem> getInstitucionFinancieraItems() {
		if (institucionFinancieraItems == null) {
			List<InstitucionFinanciera> lista = institucionFinancieraServicio.obtenerOrdenadoPorNombre();
			institucionFinancieraItems = new ArrayList<SelectItem>();
			for (InstitucionFinanciera ifi : lista) {
				institucionFinancieraItems
						.add(new SelectItem(ifi.getSecInstitucionFinanciera(), ifi.getInstitucionFinanciera()));
			}
		}
		return institucionFinancieraItems;
	}

	/**
	 * @param institucionFinancieraItems the institucionFinancieraItems to set
	 */
	public void setInstitucionFinancieraItems(List<SelectItem> institucionFinancieraItems) {
		this.institucionFinancieraItems = institucionFinancieraItems;
	}

	public List<SelectItem> getTipoVisaItems() {
		if (tipoVisaItems == null) {
			List<TipoVisa> lista = tipoVisaServicio.findAll();
			tipoVisaItems = new ArrayList<SelectItem>();
			for (TipoVisa tp : lista) {
				String label = tp.getCodTipoVisa() + " " + tp.getEstadoMigratorio();
				tipoVisaItems.add(new SelectItem(tp.getSecTipoVisa(), label));
			}
		}
		return tipoVisaItems;
	}

	public void setTipoVisaItems(List<SelectItem> tipoVisaItems) {
		this.tipoVisaItems = tipoVisaItems;
	}

	public List<SelectItem> getCompaniaSeguroItems() {
		if (companiaSeguroItems == null) {
			List<CompaniaSeguro> lista = companiaSeguroServicio.obtenerOrdenadoPorNombre();
			companiaSeguroItems = new ArrayList<SelectItem>();
			for (CompaniaSeguro c : lista) {
				SelectItem si = new SelectItem(c.getSecCiaSeguro(), c.getCiaSeguro());
				companiaSeguroItems.add(si);
			}
		}
		return companiaSeguroItems;
	}

	public void setCompaniaSeguroItems(List<SelectItem> companiaSeguroItems) {
		this.companiaSeguroItems = companiaSeguroItems;
	}

	public List<SelectItem> getRamoSeguroItems() {
		if (ramoSeguroItems == null) {
			List<RamoSeguro> lista = ramoSeguroServicio.obtenerOrdenadoPorNombre();
			ramoSeguroItems = new ArrayList<SelectItem>();
			for (RamoSeguro r : lista) {
				SelectItem si = new SelectItem(r.getSecTipoRamo(), r.getTipoRamo());
				ramoSeguroItems.add(si);
			}
		}
		return ramoSeguroItems;
	}

	public void setRamoSeguroItems(List<SelectItem> ramoSeguroItems) {
		this.ramoSeguroItems = ramoSeguroItems;
	}

	public List<SelectItem> getEstadoRcsItems() {
		if (estadoRcsItems == null) {
			estadoRcsItems = new ArrayList<SelectItem>();
			List<EstadoRcs> erLista = estadoRcsServicio.obtenerTodos();
			for (EstadoRcs obj : erLista) {
				SelectItem si = new SelectItem(obj.getCodEstado(), obj.getEstado());
				estadoRcsItems.add(si);
			}
		}
		return estadoRcsItems;
	}

	public void setEstadoRcsItems(List<SelectItem> estadoRcsItems) {
		this.estadoRcsItems = estadoRcsItems;
	}

	public List<SelectItem> getEmpresaItems() {

		if (empresaItems == null) {
			empresaItems = new ArrayList<SelectItem>();

			for (EmpresaEnum e : EmpresaEnum.values()) {

				empresaItems.add(new SelectItem(e, e.getEtiqueta()));
			}

		}
		return empresaItems;
	}

	public void setEmpresaItems(List<SelectItem> empresaItems) {
		this.empresaItems = empresaItems;
	}

	public List<SelectItem> getTipoPersonaItems() {
		if (tipoPersonaItems == null) {
			tipoPersonaItems = new ArrayList<SelectItem>();
			
			for (TipoPersonaEnum tp : TipoPersonaEnum.values()) {
				tipoPersonaItems.add(new SelectItem(tp, tp.getDescripcion()));
			}
		}

		return tipoPersonaItems;
	}

	public void setTipoPersonaItems(List<SelectItem> tipoPersonaItems) {
		this.tipoPersonaItems = tipoPersonaItems;
	}

	public List<SelectItem> getTipoFormularioContratanteItems() {
		if (tipoFormularioContratanteItems == null) {
			tipoFormularioContratanteItems = new ArrayList<SelectItem>();

			for (TipoFormularioContratanteEnum tfc : TipoFormularioContratanteEnum.values()) {
				tipoFormularioContratanteItems.add(new SelectItem(tfc, tfc.getDescripcion()));
			}
		}
		return tipoFormularioContratanteItems;
	}

	public void setTipoFormularioContratanteItems(List<SelectItem> tipoFormularioContratanteItems) {
		this.tipoFormularioContratanteItems = tipoFormularioContratanteItems;
	}

	public List<SelectItem> getTipoContratanteItems() {
		if (tipoContratanteItems == null) {
			tipoContratanteItems = new ArrayList<SelectItem>();

			for (TipoContratanteEnum t : TipoContratanteEnum.values()) {
				tipoContratanteItems.add(new SelectItem(t, t.getDescripcion()));
			}
		}
		return tipoContratanteItems;
	}

	public void setTipoContratanteItems(List<SelectItem> tipoContratanteItems) {
		this.tipoContratanteItems = tipoContratanteItems;
	}

	public List<SelectItem> getTipoConceptoContratanteItems() {
		if (tipoConceptoContratanteItems == null) {
			tipoConceptoContratanteItems = new ArrayList<SelectItem>();

			for (TipoConceptoContratanteEnum t : TipoConceptoContratanteEnum.values()) {
				tipoConceptoContratanteItems.add(new SelectItem(t, t.getDescripcion()));
			}
		}
		return tipoConceptoContratanteItems;
	}

	public void setTipoConceptoContratanteItems(List<SelectItem> tipoConceptoContratanteItems) {
		this.tipoConceptoContratanteItems = tipoConceptoContratanteItems;
	}

}