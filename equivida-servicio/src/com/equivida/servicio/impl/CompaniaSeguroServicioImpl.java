package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.CompaniaSeguroDao;
import com.equivida.modelo.CompaniaSeguro;
import com.equivida.servicio.CompaniaSeguroServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "CompaniaSeguroServicio")
public class CompaniaSeguroServicioImpl extends
		GenericServiceImpl<CompaniaSeguro, Short> implements
		CompaniaSeguroServicio {

	@EJB
	private CompaniaSeguroDao companiaSeguroDao;

	public GenericDao<CompaniaSeguro, Short> getDao() {
		return companiaSeguroDao;
	}

	@Override
	public List<CompaniaSeguro> obtenerOrdenadoPorNombre() {
		String[] criteriasAnd = null;
		CriteriaTypeEnum[] typesAnd = null;
		Object[] valuesCriteriaAnd = null;

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd, new String[] { "ciaSeguro" },
				new boolean[] { true });

		List<CompaniaSeguro> lista = companiaSeguroDao
				.findByCriterias(criteria);

		return lista;
	}
}
