package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ActividadEconomicaDao;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.servicio.ActividadEconomicaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ActividadEconomicaServicio")
public class ActividadEconomicaServicioImpl extends GenericServiceImpl<ActividadEconomica, Short>
		implements ActividadEconomicaServicio {

	@EJB
	private ActividadEconomicaDao actividadEconomicaDao;

	public GenericDao<ActividadEconomica, Short> getDao() {
		return actividadEconomicaDao;
	}

	@Override
	public String obtenerCiiuParaSise(Short codActividadEconomica) {
		return actividadEconomicaDao.obtenerCiiuParaSise(codActividadEconomica);
	}

	@Override
	public ActividadEconomica obtenerPorCodigoCiiu(String codigoCiiu) {

		String[] criteriasAnd = { "codCiiu" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] params = new Object[] { codigoCiiu };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<ActividadEconomica> lista = this.findByCriterias(criteria);

		ActividadEconomica ac = null;

		if (!lista.isEmpty()) {
			ac = lista.get(0);
		}

		return ac;
	}

	@Override
	public void ponerNivel2Nivel1(ActividadEconomica act) {
		String codCiiu2 = act.getCodCiiuPadre();
		ActividadEconomica nivel2 = this.obtenerPorCodigoCiiu(codCiiu2);
		if (nivel2 != null) {
			act.setActividadEconomicaNivel2Transient(nivel2.getActividadEconomica());
			String codCiiu1 = nivel2.getCodCiiuPadre();
			ActividadEconomica nivel1 = this.obtenerPorCodigoCiiu(codCiiu1);
			if (nivel1 != null) {
				act.setActividadEconomicaNivel1Transient(nivel1.getActividadEconomica());
			}
		}

	}
}
