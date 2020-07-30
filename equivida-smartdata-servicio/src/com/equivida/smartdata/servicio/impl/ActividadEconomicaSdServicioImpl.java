/**
 *ActividadEconomicaservicioImpl.java
 *
 *Tue Jan 26 12:18:50 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.constante.CodigoAsumidoEnum;
import com.equivida.smartdata.dao.ActividadEconomicaSdDao;
import com.equivida.smartdata.model.ActividadEconomicaSd;
import com.equivida.smartdata.servicio.ActividadEconomicaSdServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

@Stateless(name = "ActividadEconomicaSdServicio")
public class ActividadEconomicaSdServicioImpl extends
		GenericServiceImpl<ActividadEconomicaSd, Short> implements
		ActividadEconomicaSdServicio {

	@EJB
	private ActividadEconomicaSdDao actividadEconomicaDao;

	@Override
	public GenericDao<ActividadEconomicaSd, Short> getDao() {
		return actividadEconomicaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.ActividadEconomicaServicio#
	 * obtenerPorCodigoSri(java.lang.String)
	 */
	@Override
	public ActividadEconomicaSd obtenerPorCodigoSri(String codigoSri) {
		String[] criteriasAnd = { "codActEconomicaSri" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { codigoSri };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);
		List<ActividadEconomicaSd> lista = findByCriterias(criteria);

		ActividadEconomicaSd resp = null;

		if (lista != null && !lista.isEmpty()) {
			resp = lista.get(0);
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.ActividadEconomicaSdServicio#
	 * obtenerPorDefecto()
	 */
	@Override
	public ActividadEconomicaSd obtenerPorDefecto() {
		String[] criteriasAnd = { "codActividadEconomica" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.SHORT_EQUALS };
		Object[] valuesCriteriaAnd = { CodigoAsumidoEnum.ACTIVIDAD_ECONOMICA
				.getCodigoSd() };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);
		List<ActividadEconomicaSd> lista = findByCriterias(criteria);

		ActividadEconomicaSd resp = null;

		if (lista != null && !lista.isEmpty()) {
			resp = lista.get(0);
		}

		return resp;
	}
}
