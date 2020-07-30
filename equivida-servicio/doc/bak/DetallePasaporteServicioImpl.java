package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.DetallePasaporteDao;
import com.equivida.modelo.DetallePasaporte;
import com.equivida.servicio.DetallePasaporteServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "DetallePasaporteServicio")
public class DetallePasaporteServicioImpl extends
		GenericServiceImpl<DetallePasaporte, Integer> implements
		DetallePasaporteServicio {

	@EJB
	private DetallePasaporteDao detallePasaporteDao;

	public GenericDao<DetallePasaporte, Integer> getDao() {
		return detallePasaporteDao;
	}

	@Override
	public DetallePasaporte buscarPorSecPersonaNatural(Integer secPersonaNatural) {
		String[] criteriasAnd = { "secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaNatural };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<DetallePasaporte> lista = detallePasaporteDao
				.findByCriterias(criteria);

		DetallePasaporte d = null;

		int total = lista.size();
		if (total > 0) {
			d = lista.get(0);
			if (total > 1) {
				System.out
						.println("ERROR, existe mas de una DetallePasaporte!!! para secpersonaNatural:"
								+ secPersonaNatural);
			}
		}

		return d;
	}
}