package com.equivida.homologacion.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.homologacion.dao.TelefonoEquividaDao;
import com.equivida.homologacion.modelo.TelefonoEquivida;
import com.equivida.homologacion.servicio.TelefonoEquividaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

@Stateless(name = "TelefonoEquividaServicio")
public class TelefonoEquividaServicioImpl extends
		GenericServiceImpl<TelefonoEquivida, Integer> implements
		TelefonoEquividaServicio {

	@EJB
	private TelefonoEquividaDao telefonoEquividaDao;

	@Override
	public GenericDao<TelefonoEquivida, Integer> getDao() {
		return telefonoEquividaDao;
	}

	@Override
	public boolean existe(Integer secPersona, Integer secTelefonoEquivida,
			Integer idTelefonoSise, Integer idPersonaSise, Short sistemaOid) {

		String[] criteriasAnd = { "secPersonaEquivida", "secTelefonoEquivida",
				"secTelefonoDestino", "idPersonaDestino", "codSistema" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersona, secTelefonoEquivida,
				idTelefonoSise, idPersonaSise, sistemaOid };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<TelefonoEquivida> lista = telefonoEquividaDao
				.findByCriterias(criteria);

		return !lista.isEmpty();
	}
}