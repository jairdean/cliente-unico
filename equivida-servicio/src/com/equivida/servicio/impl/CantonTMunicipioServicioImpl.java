package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.CantonTMunicipioDao;
import com.equivida.exception.NoEncuentraDatosException;
import com.equivida.modelo.CantonTMunicipio;
import com.equivida.servicio.CantonTMunicipioServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "CantonTMunicipioServicio")
public class CantonTMunicipioServicioImpl extends
		GenericServiceImpl<CantonTMunicipio, Short> implements
		CantonTMunicipioServicio {

	@EJB
	private CantonTMunicipioDao cantonTMunicipioDao;

	public GenericDao<CantonTMunicipio, Short> getDao() {
		return cantonTMunicipioDao;
	}

	@Override
	public CantonTMunicipio obtenerPorSecCanton(Short secCanton)
			throws NoEncuentraDatosException {

		String[] criteriasAnd = { "secCanton" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.SHORT_EQUALS };
		Object[] valuesCriteriaAnd = { secCanton };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		System.out.println("busca sec:"+secCanton);

		List<CantonTMunicipio> tmpList = cantonTMunicipioDao.findByCriterias(criteria);
		System.out.println("total:" + tmpList);
		if (tmpList.size() == 0) {
			throw new NoEncuentraDatosException(
					"No hay datos en tabla canton_tmunicipio para canton con secCanton="
							+ secCanton);
		}

		return tmpList.get(0);
	}
}