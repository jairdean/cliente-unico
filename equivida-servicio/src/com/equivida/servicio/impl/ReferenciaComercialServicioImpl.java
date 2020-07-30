package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ReferenciaComercialDao;
import com.equivida.modelo.ReferenciaComercial;
import com.equivida.servicio.ReferenciaComercialServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ReferenciaComercialServicio")
public class ReferenciaComercialServicioImpl extends
		GenericServiceImpl<ReferenciaComercial, Integer> implements
		ReferenciaComercialServicio {

	@EJB
	private ReferenciaComercialDao referenciaComercialDao;

	public GenericDao<ReferenciaComercial, Integer> getDao() {
		return referenciaComercialDao;
	}

	@Override
	public List<ReferenciaComercial> obtenerPorPersonaNatural(
			Integer secPersonaNatural) {
		String[] criteriasAnd = { "personaNatural.secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaNatural };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<ReferenciaComercial> lista = referenciaComercialDao
				.findByCriterias(criteria);

		return lista;
	}
}