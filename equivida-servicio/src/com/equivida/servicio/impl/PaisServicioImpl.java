package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.PaisDao;
import com.equivida.modelo.Pais;
import com.equivida.servicio.PaisServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PaisServicio")
public class PaisServicioImpl extends GenericServiceImpl<Pais, Short> implements PaisServicio {

	@EJB
	private PaisDao paisDao;

	public GenericDao<Pais, Short> getDao() {
		return paisDao;
	}

	@Override
	public Pais buscarPorPais(String nombre) {

		String[] criteriasAnd = { "pais" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] params = new Object[] { nombre };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<Pais> lista = this.findByCriterias(criteria);

		Pais p = null;

		if (lista.size() > 0) {
			p = lista.get(0);
		}

		return p;
	}
}
