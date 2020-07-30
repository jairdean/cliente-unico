package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.BiometricaDao;
import com.equivida.modelo.Biometrica;
import com.equivida.servicio.BiometricaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "BiometricaServicio")
public class BiometricaServicioImpl extends
		GenericServiceImpl<Biometrica, Integer> implements BiometricaServicio {

	@EJB
	private BiometricaDao biometricaDao;

	public GenericDao<Biometrica, Integer> getDao() {
		return biometricaDao;
	}

	@Override
	public Biometrica buscarPorSecPersonaNatural(Integer secPersonaNatural) {

		String[] criteriasAnd = { "secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaNatural };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<Biometrica> lista = biometricaDao.findByCriterias(criteria);

		Biometrica b = null;

		int total = lista.size();
		if (total > 0) {
			b = lista.get(0);
			if (total > 1) {
				System.out
						.println("ERROR, existe mas de una biometrica!!! para secpersonaNatural:"
								+ secPersonaNatural);
			}
		}

		return b;
	}
}
