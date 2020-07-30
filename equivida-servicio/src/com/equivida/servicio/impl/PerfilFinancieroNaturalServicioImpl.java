package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.PerfilFinancieroNaturalDao;
import com.equivida.modelo.PerfilFinancieroNatural;
import com.equivida.servicio.PerfilFinancieroNaturalServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PerfilFinancieroNaturalServicio")
public class PerfilFinancieroNaturalServicioImpl extends GenericServiceImpl<PerfilFinancieroNatural, Integer>
		implements PerfilFinancieroNaturalServicio {

	@EJB
	private PerfilFinancieroNaturalDao perfilFinancieroNaturalDao;

	public GenericDao<PerfilFinancieroNatural, Integer> getDao() {
		return perfilFinancieroNaturalDao;
	}

	@Override
	public PerfilFinancieroNatural buscarPorSecPersonaNatural(Integer secPersonaNatural) {
		String[] criteriasAnd = { "secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaNatural };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		List<PerfilFinancieroNatural> lista = perfilFinancieroNaturalDao.findByCriterias(criteria);

		PerfilFinancieroNatural pfn = null;

		int total = lista.size();
		if (total > 0) {
			pfn = lista.get(0);
			if (total > 1) {
				System.out.println("ERROR, existe mas de una PerfilFinancieroNatural!!! para secpersonaNatural:"
						+ secPersonaNatural);
			}
		}

		return pfn;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.PerfilFinancieroNaturalServicio#persistir(com.equivida.
	 * modelo.PerfilFinancieroNatural)
	 */
	@Override
	public void persistir(PerfilFinancieroNatural perfilFinancieroNatural) {
		if (perfilFinancieroNatural.getSecPerfilFinanciero() == null) {
			create(perfilFinancieroNatural);
		} else {
			update(perfilFinancieroNatural);
		}
	}
}
