package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.OtraOcupacionDao;
import com.equivida.modelo.OtraOcupacion;
import com.equivida.servicio.OtraOcupacionServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "OtraOcupacionServicio")
public class OtraOcupacionServicioImpl extends
		GenericServiceImpl<OtraOcupacion, Integer> implements
		OtraOcupacionServicio {

	@EJB
	private OtraOcupacionDao otraOcupacionDao;

	public GenericDao<OtraOcupacion, Integer> getDao() {
		return otraOcupacionDao;
	}

	@Override
	public List<OtraOcupacion> obtenerPorPersonaNatural(
			Integer secPersonaNatural) {
		String[] criteriasAnd = { "secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaNatural };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<OtraOcupacion> lista = otraOcupacionDao.findByCriterias(criteria);

		return lista;
	}
}