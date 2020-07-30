package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ReferenciaDao;
import com.equivida.modelo.Referencia;
import com.equivida.servicio.ReferenciaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ReferenciaServicio")
public class ReferenciaServicioImpl extends
		GenericServiceImpl<Referencia, Integer> implements ReferenciaServicio {

	@EJB
	private ReferenciaDao referenciaDao;

	public GenericDao<Referencia, Integer> getDao() {
		return referenciaDao;
	}

	public List<Referencia> obtenerPorPersonaNatural(Integer secPersonaNatural) {
		String[] criteriasAnd = { "personaNatural.secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaNatural };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<Referencia> lista = referenciaDao.findByCriterias(criteria);

		return lista;
	}

}
