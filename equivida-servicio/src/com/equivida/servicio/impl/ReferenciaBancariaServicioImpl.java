package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ReferenciaBancariaDao;
import com.equivida.modelo.ReferenciaBancaria;
import com.equivida.servicio.ReferenciaBancariaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ReferenciaBancariaServicio")
public class ReferenciaBancariaServicioImpl extends
		GenericServiceImpl<ReferenciaBancaria, Integer> implements
		ReferenciaBancariaServicio {

	@EJB
	private ReferenciaBancariaDao referenciaBancariaDao;

	public GenericDao<ReferenciaBancaria, Integer> getDao() {
		return referenciaBancariaDao;
	}

	@Override
	public List<ReferenciaBancaria> obtenerPorPersonaNatural(
			Integer secPersonaNatural) {
		String[] criteriasAnd = { "personaNatural.secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaNatural };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<ReferenciaBancaria> lista = referenciaBancariaDao
				.findByCriterias(criteria);

		return lista;
	}
}