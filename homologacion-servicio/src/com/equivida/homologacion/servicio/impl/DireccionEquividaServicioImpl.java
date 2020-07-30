package com.equivida.homologacion.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.homologacion.dao.DireccionEquividaDao;
import com.equivida.homologacion.modelo.DireccionEquivida;
import com.equivida.homologacion.servicio.DireccionEquividaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

@Stateless(name = "DireccionEquividaServicio")
public class DireccionEquividaServicioImpl extends
		GenericServiceImpl<DireccionEquivida, Integer> implements
		DireccionEquividaServicio {

	@EJB
	private DireccionEquividaDao direccionEquividaDao;

	@Override
	public GenericDao<DireccionEquivida, Integer> getDao() {
		return direccionEquividaDao;
	}

	@Override
	public boolean existe(Integer secPersona, Integer secDireccionEquivida,
			Integer idDireccionSise, Integer idPersonaSise, Short sistemaOid) {

		String[] criteriasAnd = { "secPersonaEquivida", "secDireccionEquivida",
				"secDireccionDestino", "idPersonaDestino", "codSistema" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersona, secDireccionEquivida,
				idDireccionSise, idPersonaSise, sistemaOid };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<DireccionEquivida> lista = direccionEquividaDao
				.findByCriterias(criteria);

		return !lista.isEmpty();
	}
}