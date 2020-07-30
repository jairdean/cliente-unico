/**
 *ProfesionservicioImpl.java
 *
 *Tue Jan 26 12:43:26 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.constante.CodigoAsumidoEnum;
import com.equivida.smartdata.dao.ProfesionSdDao;
import com.equivida.smartdata.model.ProfesionSd;
import com.equivida.smartdata.servicio.ProfesionSdServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

@Stateless(name = "ProfesionSdServicio")
public class ProfesionSdServicioImpl extends
		GenericServiceImpl<ProfesionSd, Short> implements ProfesionSdServicio {

	@EJB
	private ProfesionSdDao profesionDao;

	@Override
	public GenericDao<ProfesionSd, Short> getDao() {
		return profesionDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.servicio.ProfesionServicio#consultarPorCodigoDB(
	 * java.lang.String)
	 */
	@Override
	public ProfesionSd consultarPorCodigoDB(String idProfesionDB) {
		String criteriasAnd[] = { "codProfesionRc" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { idProfesionDB };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<ProfesionSd> lista = findByCriterias(criteria);

		ProfesionSd resp = null;

		if (lista != null && !lista.isEmpty()) {
			resp = lista.get(0);
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.servicio.ProfesionSdServicio#obtenerPorDefecto()
	 */
	@Override
	public ProfesionSd obtenerPorDefecto() {
		String criteriasAnd[] = { "secProfesion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.SHORT_EQUALS };
		Object[] valuesCriteriaAnd = { CodigoAsumidoEnum.PROFESION
				.getCodigoSd() };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<ProfesionSd> lista = findByCriterias(criteria);

		ProfesionSd resp = null;

		if (lista != null && !lista.isEmpty()) {
			resp = lista.get(0);
		}

		return resp;
	}
}
