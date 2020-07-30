package com.equivida.intranet.ctrl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.equivida.modelo.Canton;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Parroquia;
import com.equivida.modelo.Provincia;
import com.equivida.servicio.CantonServicio;
import com.equivida.servicio.PaisServicio;
import com.equivida.servicio.ParroquiaServicio;
import com.equivida.servicio.ProvinciaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.util.Criteria;

@ManagedBean(name = "autocompletePersonaJurCtrl")
@ViewScoped
public class AutocompletePersonaJurCtrl {

	@EJB(mappedName = "PaisServicio/local")
	private PaisServicio paisServicio;

	@EJB(mappedName = "ProvinciaServicio/local")
	private ProvinciaServicio provinciaServicio;

	@EJB(mappedName = "CantonServicio/local")
	private CantonServicio cantonServicio;

	@EJB(mappedName = "ParroquiaServicio/local")
	private ParroquiaServicio parroquiaServicio;

	private List<Pais> paisLista;
	private List<Provincia> provinciaLista;
	private List<Canton> cantonLista;
	private List<Parroquia> parroquiaLista;

	private Short paisOficinaId;
	private Short provinciaOficinaId;
	private Short cantonOficinaId;

	public List<Pais> autocompletePais(String pref) {

		List<Pais> resp = new ArrayList<Pais>();

		int minChars = 1;

		if (paisLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "pais" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, };
			Object[] params = new Object[] { pref };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = paisServicio.findByCriterias(criteria);

		} else {
			Iterator<Pais> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Pais pais = (Pais) iterator.next();
				if (pais.getPais() != null
						&& pais.getPais().toLowerCase()
								.indexOf(pref.toLowerCase()) >= 0
						|| "".equals(pref)) {
					resp.add(pais);
				}
			}

		}

		return resp;
	}

	public List<Provincia> autocompleteProvincia(String pref) {

		List<Provincia> resp = new ArrayList<Provincia>();

		int minChars = 1;

		if (provinciaLista == null || pref.length() >= minChars) {
			String[] criteriasAnd = { "provincia", "pais.codPais" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE,
					CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, paisOficinaId };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = provinciaServicio.findByCriterias(criteria);

		} else {
			Iterator<Provincia> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Provincia pais = (Provincia) iterator.next();
				if (pais.getProvincia() != null
						&& pais.getProvincia().toLowerCase()
								.indexOf(pref.toLowerCase()) >= 0
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
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE,
					CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, provinciaOficinaId };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = cantonServicio.findByCriterias(criteria);

		} else {
			Iterator<Canton> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Canton canton = (Canton) iterator.next();
				if (canton.getCanton() != null
						&& canton.getCanton().toLowerCase()
								.indexOf(pref.toLowerCase()) >= 0
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
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE,
					CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, cantonOficinaId };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = parroquiaServicio.findByCriterias(criteria);

		} else {
			Iterator<Parroquia> iterator = resp.iterator();

			while (iterator.hasNext()) {
				Parroquia parroquia = (Parroquia) iterator.next();
				if (parroquia.getParroquia() != null
						&& parroquia.getParroquia().toLowerCase()
								.indexOf(pref.toLowerCase()) >= 0
						|| "".equals(pref)) {
					resp.add(parroquia);
				}
			}

		}

		return resp;
	}

	/**
	 * @return the cantonServicio
	 */
	public CantonServicio getCantonServicio() {
		return cantonServicio;
	}

	/**
	 * @param cantonServicio
	 *            the cantonServicio to set
	 */
	public void setCantonServicio(CantonServicio cantonServicio) {
		this.cantonServicio = cantonServicio;
	}

	/**
	 * @return the paisLista
	 */
	public List<Pais> getPaisLista() {
		return paisLista;
	}

	/**
	 * @param paisLista
	 *            the paisLista to set
	 */
	public void setPaisLista(List<Pais> paisLista) {
		this.paisLista = paisLista;
	}

	/**
	 * @return the provinciaLista
	 */
	public List<Provincia> getProvinciaLista() {
		return provinciaLista;
	}

	/**
	 * @param provinciaLista
	 *            the provinciaLista to set
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
	 * @param cantonLista
	 *            the cantonLista to set
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
	 * @param parroquiaLista
	 *            the parroquiaLista to set
	 */
	public void setParroquiaLista(List<Parroquia> parroquiaLista) {
		this.parroquiaLista = parroquiaLista;
	}

	/**
	 * @return the provinciaOficinaId
	 */
	public Short getProvinciaOficinaId() {
		return provinciaOficinaId;
	}

	/**
	 * @param provinciaOficinaId
	 *            the provinciaOficinaId to set
	 */
	public void setProvinciaOficinaId(Short provinciaOficinaId) {
		this.provinciaOficinaId = provinciaOficinaId;
	}

	/**
	 * @return the cantonOficinaId
	 */
	public Short getCantonOficinaId() {
		return cantonOficinaId;
	}

	/**
	 * @param cantonOficinaId
	 *            the cantonOficinaId to set
	 */
	public void setCantonOficinaId(Short cantonOficinaId) {
		this.cantonOficinaId = cantonOficinaId;
	}

	/**
	 * @return the paisOficinaId
	 */
	public Short getPaisOficinaId() {
		return paisOficinaId;
	}

	/**
	 * @param paisOficinaId
	 *            the paisOficinaId to set
	 */
	public void setPaisOficinaId(Short paisOficinaId) {
		this.paisOficinaId = paisOficinaId;
	}

}
