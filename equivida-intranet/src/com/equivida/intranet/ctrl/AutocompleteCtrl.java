package com.equivida.intranet.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.equivida.constant.Constantes;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.intranet.util.BaseCtrl;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Canton;
import com.equivida.modelo.Ocupacion;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Parroquia;
import com.equivida.modelo.Persona;
import com.equivida.modelo.Profesion;
import com.equivida.modelo.Provincia;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.servicio.ActividadEconomicaServicio;
import com.equivida.servicio.CantonServicio;
import com.equivida.servicio.OcupacionServicio;
import com.equivida.servicio.PaisServicio;
import com.equivida.servicio.ParroquiaServicio;
import com.equivida.servicio.PersonaServicio;
import com.equivida.servicio.ProfesionServicio;
import com.equivida.servicio.ProvinciaServicio;
import com.equivida.servicio.TipoParentescoRelacionServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.util.Criteria;

@ManagedBean(name = "autocompleteCtrl")
@ViewScoped
public class AutocompleteCtrl extends BaseCtrl {

	private static final long serialVersionUID = 509480758842488705L;

	@EJB(mappedName = "PaisServicio/local")
	private PaisServicio paisServicio;

	@EJB(mappedName = "ProvinciaServicio/local")
	private ProvinciaServicio provinciaServicio;

	@EJB(mappedName = "CantonServicio/local")
	private CantonServicio cantonServicio;

	@EJB(mappedName = "ParroquiaServicio/local")
	private ParroquiaServicio parroquiaServicio;

	@EJB(mappedName = "PersonaServicio/local")
	private PersonaServicio personaServicio;

	@EJB(mappedName = "ActividadEconomicaServicio/local")
	private ActividadEconomicaServicio actividadEconomicaServicio;

	@EJB(mappedName = "ProfesionServicio/local")
	private ProfesionServicio profesionServicio;

	@EJB(mappedName = "OcupacionServicio/local")
	private OcupacionServicio ocupacionServicio;

	@EJB(mappedName = "TipoParentescoRelacionServicio/local")
	private TipoParentescoRelacionServicio tipoParentescoRelacionServicio;

	private Short paisDomicilioId = Constantes.PAIS_ID_ECUADOR;

	private Short provinciaDomicilioId;

	private Short cantonDomicilioId;

	private Short paisTrabajoId = Constantes.PAIS_ID_ECUADOR;

	private Short provinciaTrabajoId;

	private Short cantonTrabajoId;

	private Map<String, Pais> paisesMap;

	private List<ActividadEconomica> actividadEconomicaLista;
	private List<Pais> paisLista;
	private List<Provincia> provinciaLista;
	private List<Canton> cantonLista;
	private List<Parroquia> parroquiaLista;

	private List<Pais> paisTrabajoLista;
	private List<Provincia> provinciaTrabajoLista;
	private List<Canton> cantonTrabajoLista;
	private List<Parroquia> parroquiaTrabajoLista;

	private List<Persona> personaLista;

	private List<Profesion> profesionLista;

	private List<Ocupacion> ocupacionLista;

	private List<Ocupacion> tipoParentescoRelacionLista;

	public List<ActividadEconomica> autocompleteActividadEconomica(String pref) {

		List<ActividadEconomica> resp = new ArrayList<ActividadEconomica>();

		int minChars = 1;

		if (pref.length() >= minChars) {
			String[] criteriasAnd = { "codigoVisible", "actividadEconomica", "nivelCiiu" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS, CriteriaTypeEnum.STRING_LIKE,
					CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { RespuestaEnum.SI.getCodigo(), pref, new Integer(3).shortValue() };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = actividadEconomicaServicio.findByCriterias(criteria);
		}

		return resp;
	}

	public List<Pais> autocompletePais(String pref) {

		List<Pais> resp = new ArrayList<Pais>();

		int minChars = 1;

		if (paisLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "pais" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_STARTS_WITH, };
			Object[] params = new Object[] { pref };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = paisServicio.findByCriterias(criteria);
		}

		System.out.println("auto pais");
		paisesMap = new HashMap<String, Pais>();
		for (Pais p : resp) {
			paisesMap.put(p.getPais(), p);
			System.out.println(p.getPais());
		}

		return resp;
	}

	public void seleccionaProvinciaDomicilio(ActionEvent event) {
		Provincia p = (Provincia) getExternalContext().getRequestMap().get("varProvincia");
		provinciaDomicilioId = p.getSecProvincia();
	}

	public List<Provincia> autocompleteProvincia(String pref) {

		List<Provincia> resp = new ArrayList<Provincia>();

		int minChars = 1;

		if (provinciaLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "provincia", "pais.codPais" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, paisDomicilioId };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = provinciaServicio.findByCriterias(criteria);

		} else {
			Iterator<Provincia> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Provincia pais = (Provincia) iterator.next();
				if (pais.getProvincia() != null && pais.getProvincia().toLowerCase().indexOf(pref.toLowerCase()) >= 0
						|| "".equals(pref)) {
					resp.add(pais);
				}
			}

		}

		return resp;
	}

	public List<Canton> autocompleteCanton(String pref) {

		List<Canton> resp = new ArrayList<Canton>();

		int minChars = 1;

		if (cantonLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "canton", "provincia.secProvincia" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, provinciaDomicilioId };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = cantonServicio.findByCriterias(criteria);
		} else {
			Iterator<Canton> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Canton canton = (Canton) iterator.next();
				if (canton.getCanton() != null && canton.getCanton().toLowerCase().indexOf(pref.toLowerCase()) >= 0
						|| "".equals(pref)) {
					resp.add(canton);
				}
			}

		}

		return resp;
	}

	public List<Parroquia> autocompleteParroquia(String pref) {

		List<Parroquia> resp = new ArrayList<Parroquia>();

		int minChars = 1;

		if (parroquiaLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "parroquia", "canton.secCanton" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, cantonDomicilioId };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = parroquiaServicio.findByCriterias(criteria);

		} else {
			Iterator<Parroquia> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Parroquia parroquia = (Parroquia) iterator.next();
				if (parroquia.getParroquia() != null
						&& parroquia.getParroquia().toLowerCase().indexOf(pref.toLowerCase()) >= 0 || "".equals(pref)) {
					resp.add(parroquia);
				}
			}

		}

		return resp;
	}

	public List<Pais> autocompletePaisTrabajo(String pref) {

		List<Pais> resp = new ArrayList<Pais>();

		int minChars = 1;

		if (paisTrabajoLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "pais" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, };
			Object[] params = new Object[] { pref };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = paisServicio.findByCriterias(criteria);

		} else {
			Iterator<Pais> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Pais pais = (Pais) iterator.next();
				if (pais.getPais() != null && pais.getPais().toLowerCase().indexOf(pref.toLowerCase()) >= 0
						|| "".equals(pref)) {
					resp.add(pais);
				}
			}

		}

		return resp;
	}

	public List<Provincia> autocompleteProvinciaTrabajo(String pref) {

		List<Provincia> resp = new ArrayList<Provincia>();

		int minChars = 1;

		if (provinciaTrabajoLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "provincia", "pais.codPais" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, paisTrabajoId };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = provinciaServicio.findByCriterias(criteria);

		} else {
			Iterator<Provincia> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Provincia pais = (Provincia) iterator.next();
				if (pais.getProvincia() != null && pais.getProvincia().toLowerCase().indexOf(pref.toLowerCase()) >= 0
						|| "".equals(pref)) {
					resp.add(pais);
				}
			}

		}

		return resp;
	}

	public List<Canton> autocompleteCantonTrabajo(String pref) {

		List<Canton> resp = new ArrayList<Canton>();

		int minChars = 1;

		if (cantonTrabajoLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "canton", "provincia.secProvincia" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, provinciaTrabajoId };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = cantonServicio.findByCriterias(criteria);

		} else {
			Iterator<Canton> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Canton canton = (Canton) iterator.next();
				if (canton.getCanton() != null && canton.getCanton().toLowerCase().indexOf(pref.toLowerCase()) >= 0
						|| "".equals(pref)) {
					resp.add(canton);
				}
			}

		}

		return resp;
	}

	public List<Parroquia> autocompleteParroquiaTrabajo(String pref) {

		List<Parroquia> resp = new ArrayList<Parroquia>();

		int minChars = 1;

		if (parroquiaTrabajoLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "parroquia", "canton.secCanton" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, cantonTrabajoId };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = parroquiaServicio.findByCriterias(criteria);

		} else {
			Iterator<Parroquia> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Parroquia parroquia = (Parroquia) iterator.next();
				if (parroquia.getParroquia() != null
						&& parroquia.getParroquia().toLowerCase().indexOf(pref.toLowerCase()) >= 0 || "".equals(pref)) {
					resp.add(parroquia);
				}
			}

		}

		return resp;
	}

	public List<Persona> autocompletePersona(String pref) {

		List<Persona> resp = new ArrayList<Persona>();

		int minChars = 1;

		if (personaLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "tipoIdentificacion.codTipoIdentificacion", "denominacion" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS, CriteriaTypeEnum.STRING_LIKE };
			Object[] params = new Object[] { TipoIdentificacionEnum.RUC.getCodigo(), pref };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = personaServicio.findByCriterias(criteria);

		}

		return resp;
	}

	public List<Profesion> autocompleteProfesion(String pref) {

		List<Profesion> resp = new ArrayList<Profesion>();

		int minChars = 1;

		if (profesionLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "profesion" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE };
			Object[] params = new Object[] { pref };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = profesionServicio.findByCriterias(criteria);

		} else {
			Iterator<Profesion> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Profesion profesion = (Profesion) iterator.next();
				if (profesion.getProfesion() != null
						&& profesion.getProfesion().toLowerCase().indexOf(pref.toLowerCase()) >= 0 || "".equals(pref)) {
					resp.add(profesion);
				}
			}

		}

		return resp;
	}

	public List<Ocupacion> autocompleteOcupacion(String pref) {

		List<Ocupacion> resp = new ArrayList<Ocupacion>();

		int minChars = 1;

		if (ocupacionLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "ocupacion", "codigoVisible" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, CriteriaTypeEnum.STRING_EQUALS };
			Object[] params = new Object[] { pref, "S" };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = ocupacionServicio.findByCriterias(criteria);

		} else {
			Iterator<Ocupacion> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Ocupacion ocupacion = (Ocupacion) iterator.next();
				if (ocupacion.getOcupacion() != null
						&& ocupacion.getOcupacion().toLowerCase().indexOf(pref.toLowerCase()) >= 0 || "".equals(pref)) {
					resp.add(ocupacion);
				}
			}

		}

		return resp;
	}

	public List<TipoParentescoRelacion> autocompleteTipoRelacion(String pref) {

		List<TipoParentescoRelacion> resp = new ArrayList<TipoParentescoRelacion>();

		int minChars = 1;

		if (tipoParentescoRelacionLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "tipoParentesco" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE };
			Object[] params = new Object[] { pref };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = tipoParentescoRelacionServicio.findByCriterias(criteria);

		} else {
			Iterator<TipoParentescoRelacion> iterator = resp.iterator();

			while (iterator.hasNext()) {
				TipoParentescoRelacion tpr = (TipoParentescoRelacion) iterator.next();
				if (tpr.getTipoParentesco() != null
						&& tpr.getTipoParentesco().toLowerCase().indexOf(pref.toLowerCase()) >= 0 || "".equals(pref)) {
					resp.add(tpr);
				}
			}

		}

		return resp;
	}

	/**
	 * @return the paisServicio
	 */
	public PaisServicio getPaisServicio() {
		return paisServicio;
	}

	/**
	 * @param paisServicio the paisServicio to set
	 */
	public void setPaisServicio(PaisServicio paisServicio) {
		this.paisServicio = paisServicio;
	}

	/**
	 * @return the paisLista
	 */
	public List<Pais> getPaisLista() {
		return paisLista;
	}

	/**
	 * @param paisLista the paisLista to set
	 */
	public void setPaisLista(List<Pais> listaPaises) {
		this.paisLista = listaPaises;
	}

	/**
	 * @return the provinciaServicio
	 */
	public ProvinciaServicio getProvinciaServicio() {
		return provinciaServicio;
	}

	/**
	 * @param provinciaServicio the provinciaServicio to set
	 */
	public void setProvinciaServicio(ProvinciaServicio provinciaServicio) {
		this.provinciaServicio = provinciaServicio;
	}

	/**
	 * @return the cantonServicio
	 */
	public CantonServicio getCantonServicio() {
		return cantonServicio;
	}

	/**
	 * @param cantonServicio the cantonServicio to set
	 */
	public void setCantonServicio(CantonServicio cantonServicio) {
		this.cantonServicio = cantonServicio;
	}

	/**
	 * @return the parroquiaServicio
	 */
	public ParroquiaServicio getParroquiaServicio() {
		return parroquiaServicio;
	}

	/**
	 * @param parroquiaServicio the parroquiaServicio to set
	 */
	public void setParroquiaServicio(ParroquiaServicio parroquiaServicio) {
		this.parroquiaServicio = parroquiaServicio;
	}

	/**
	 * @return the paisDomicilioId
	 */
	public Short getPaisDomicilioId() {
		return paisDomicilioId;
	}

	/**
	 * @param paisDomicilioId the paisDomicilioId to set
	 */
	public void setPaisDomicilioId(Short paisSeleccionadoId) {
		this.paisDomicilioId = paisSeleccionadoId;
	}

	/**
	 * @return the provinciaDomicilioId
	 */
	public Short getProvinciaDomicilioId() {
		return provinciaDomicilioId;
	}

	/**
	 * @param provinciaDomicilioId the provinciaDomicilioId to set
	 */
	public void setProvinciaDomicilioId(Short provinciaSeleccionadaId) {
		this.provinciaDomicilioId = provinciaSeleccionadaId;
	}

	/**
	 * @return the cantonDomicilioId
	 */
	public Short getCantonDomicilioId() {
		return cantonDomicilioId;
	}

	/**
	 * @param cantonDomicilioId the cantonDomicilioId to set
	 */
	public void setCantonDomicilioId(Short cantonSeleccionadoId) {
		this.cantonDomicilioId = cantonSeleccionadoId;
	}

	/**
	 * @return the provinciaLista
	 */
	public List<Provincia> getProvinciaLista() {
		return provinciaLista;
	}

	/**
	 * @param provinciaLista the provinciaLista to set
	 */
	public void setProvinciaLista(List<Provincia> provinciaLista) {
		this.provinciaLista = provinciaLista;
	}

	/**
	 * @return the cantonLista
	 */
	public List<Canton> getCantonLista() {
		return cantonLista;
	}

	/**
	 * @param cantonLista the cantonLista to set
	 */
	public void setCantonLista(List<Canton> cantonLista) {
		this.cantonLista = cantonLista;
	}

	/**
	 * @return the parroquiaLista
	 */
	public List<Parroquia> getParroquiaLista() {
		return parroquiaLista;
	}

	/**
	 * @param parroquiaLista the parroquiaLista to set
	 */
	public void setParroquiaLista(List<Parroquia> parroquiaLista) {
		this.parroquiaLista = parroquiaLista;
	}

	public List<ActividadEconomica> getActividadEconomicaLista() {
		return actividadEconomicaLista;
	}

	public void setActividadEconomicaLista(List<ActividadEconomica> actividadEconomicaLista) {
		this.actividadEconomicaLista = actividadEconomicaLista;
	}

	/**
	 * @return the paisTrabajoId
	 */
	public Short getPaisTrabajoId() {
		return paisTrabajoId;
	}

	/**
	 * @param paisTrabajoId the paisTrabajoId to set
	 */
	public void setPaisTrabajoId(Short paisTrabajoId) {
		this.paisTrabajoId = paisTrabajoId;
	}

	/**
	 * @return the provinciaTrabajoId
	 */
	public Short getProvinciaTrabajoId() {
		return provinciaTrabajoId;
	}

	/**
	 * @param provinciaTrabajoId the provinciaTrabajoId to set
	 */
	public void setProvinciaTrabajoId(Short provinciaTrabajoId) {
		this.provinciaTrabajoId = provinciaTrabajoId;
	}

	/**
	 * @return the cantonTrabajoId
	 */
	public Short getCantonTrabajoId() {
		return cantonTrabajoId;
	}

	/**
	 * @param cantonTrabajoId the cantonTrabajoId to set
	 */
	public void setCantonTrabajoId(Short cantonTrabajoId) {
		this.cantonTrabajoId = cantonTrabajoId;
	}

	/**
	 * @return the paisTrabajoLista
	 */
	public List<Pais> getPaisTrabajoLista() {
		return paisTrabajoLista;
	}

	/**
	 * @param paisTrabajoLista the paisTrabajoLista to set
	 */
	public void setPaisTrabajoLista(List<Pais> paisTrabajoLista) {
		this.paisTrabajoLista = paisTrabajoLista;
	}

	/**
	 * @return the provinciaTrabajoLista
	 */
	public List<Provincia> getProvinciaTrabajoLista() {
		return provinciaTrabajoLista;
	}

	/**
	 * @param provinciaTrabajoLista the provinciaTrabajoLista to set
	 */
	public void setProvinciaTrabajoLista(List<Provincia> provinciaTrabajoLista) {
		this.provinciaTrabajoLista = provinciaTrabajoLista;
	}

	/**
	 * @return the cantonTrabajoLista
	 */
	public List<Canton> getCantonTrabajoLista() {
		return cantonTrabajoLista;
	}

	/**
	 * @param cantonTrabajoLista the cantonTrabajoLista to set
	 */
	public void setCantonTrabajoLista(List<Canton> cantonTrabajoLista) {
		this.cantonTrabajoLista = cantonTrabajoLista;
	}

	/**
	 * @return the parroquiaTrabajoLista
	 */
	public List<Parroquia> getParroquiaTrabajoLista() {
		return parroquiaTrabajoLista;
	}

	/**
	 * @param parroquiaTrabajoLista the parroquiaTrabajoLista to set
	 */
	public void setParroquiaTrabajoLista(List<Parroquia> parroquiaTrabajoLista) {
		this.parroquiaTrabajoLista = parroquiaTrabajoLista;
	}

	/**
	 * @return the personaLista
	 */
	public List<Persona> getPersonaLista() {
		return personaLista;
	}

	/**
	 * @param personaLista the personaLista to set
	 */
	public void setPersonaLista(List<Persona> personaLista) {
		this.personaLista = personaLista;
	}

	/**
	 * @return the profesionLista
	 */
	public List<Profesion> getProfesionLista() {
		return profesionLista;
	}

	/**
	 * @param profesionLista the profesionLista to set
	 */
	public void setProfesionLista(List<Profesion> profesionLista) {
		this.profesionLista = profesionLista;
	}

	/**
	 * @return the ocupacionLista
	 */
	public List<Ocupacion> getOcupacionLista() {
		return ocupacionLista;
	}

	/**
	 * @param ocupacionLista the ocupacionLista to set
	 */
	public void setOcupacionLista(List<Ocupacion> ocupacionLista) {
		this.ocupacionLista = ocupacionLista;
	}

	/**
	 * @return the tipoParentescoRelacionLista
	 */
	public List<Ocupacion> getTipoParentescoRelacionLista() {
		return tipoParentescoRelacionLista;
	}

	/**
	 * @param tipoParentescoRelacionLista the tipoParentescoRelacionLista to set
	 */
	public void setTipoParentescoRelacionLista(List<Ocupacion> tipoParentescoRelacionLista) {
		this.tipoParentescoRelacionLista = tipoParentescoRelacionLista;
	}

	public Map<String, Pais> getPaisesMap() {
		return paisesMap;
	}

	public void setPaisesMap(Map<String, Pais> paisesMap) {
		this.paisesMap = paisesMap;
	}
}