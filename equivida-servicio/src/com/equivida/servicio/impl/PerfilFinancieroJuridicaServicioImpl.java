package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.PerfilFinancieroJuridicaDao;
import com.equivida.modelo.PerfilFinancieroJuridica;
import com.equivida.servicio.PerfilFinancieroJuridicaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PerfilFinancieroJuridicaServicio")
public class PerfilFinancieroJuridicaServicioImpl extends GenericServiceImpl<PerfilFinancieroJuridica, Integer>
		implements PerfilFinancieroJuridicaServicio {

	@EJB
	private PerfilFinancieroJuridicaDao perfilFinancieroJuridicaDao;

	public GenericDao<PerfilFinancieroJuridica, Integer> getDao() {
		return perfilFinancieroJuridicaDao;
	}

	@Override
	public PerfilFinancieroJuridica buscarPorSecPersonaJuridica(Integer secPersonaJuridica) {

		PerfilFinancieroJuridica pfj = null;

		String[] criteriasAnd = { "personaJuridica.secPersonaJuridica" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] params = new Object[] { secPersonaJuridica };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<PerfilFinancieroJuridica> lista = perfilFinancieroJuridicaDao.findByCriterias(criteria);

		if (lista.size() >= 1) {
			pfj = lista.get(0);
		}

		return pfj;

	}

	@Override
	public void persistir(PerfilFinancieroJuridica perfilFinancieroJuridica) {
		if (perfilFinancieroJuridica.getSecPerfilFinanciero() == null) {
			perfilFinancieroJuridicaDao.save(perfilFinancieroJuridica);
		} else {
			perfilFinancieroJuridicaDao.update(perfilFinancieroJuridica);
		}
	}
}
