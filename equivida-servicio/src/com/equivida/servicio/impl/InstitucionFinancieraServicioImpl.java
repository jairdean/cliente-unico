package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.InstitucionFinancieraDao;
import com.equivida.modelo.InstitucionFinanciera;
import com.equivida.servicio.InstitucionFinancieraServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "InstitucionFinancieraServicio")
public class InstitucionFinancieraServicioImpl extends
		GenericServiceImpl<InstitucionFinanciera, Short> implements
		InstitucionFinancieraServicio {

	@EJB
	private InstitucionFinancieraDao institucionFinancieraDao;

	public GenericDao<InstitucionFinanciera, Short> getDao() {
		return institucionFinancieraDao;
	}

	@Override
	public List<InstitucionFinanciera> obtenerOrdenadoPorNombre() {

		String[] criteriasAnd = null;
		CriteriaTypeEnum[] typesAnd = null;
		Object[] params = null;
		String[] orderBy = { "institucionFinanciera" };
		boolean[] asc = { true };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params,
				orderBy, asc);

		List<InstitucionFinanciera> tmpList = institucionFinancieraDao
				.findByCriterias(criteria);

		return tmpList;
	}
}