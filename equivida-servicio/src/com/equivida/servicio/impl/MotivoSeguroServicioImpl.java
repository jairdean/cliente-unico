package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.MotivoSeguroDao;
import com.equivida.modelo.MotivoSeguro;
import com.equivida.servicio.MotivoSeguroServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "MotivoSeguroServicio")
public class MotivoSeguroServicioImpl extends
		GenericServiceImpl<MotivoSeguro, Integer> implements
		MotivoSeguroServicio {

	@EJB
	private MotivoSeguroDao motivoSeguroDao;

	public GenericDao<MotivoSeguro, Integer> getDao() {
		return motivoSeguroDao;
	}

	@Override
	public List<MotivoSeguro> obtenerPorPersona(Integer secPersona) {
		String[] criteriasAnd = { "persona.secPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersona };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<MotivoSeguro> lista = motivoSeguroDao.findByCriterias(criteria);

		return lista;
	}
}
