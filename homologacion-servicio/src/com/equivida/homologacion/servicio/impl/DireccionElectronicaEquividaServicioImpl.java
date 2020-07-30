package com.equivida.homologacion.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.homologacion.dao.DireccionElectronicaEquividaDao;
import com.equivida.homologacion.modelo.DireccionElectronicaEquivida;
import com.equivida.homologacion.servicio.DireccionElectronicaEquividaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

@Stateless(name = "DireccionElectronicaEquividaServicio")
public class DireccionElectronicaEquividaServicioImpl extends
		GenericServiceImpl<DireccionElectronicaEquivida, Integer> implements
		DireccionElectronicaEquividaServicio {

	@EJB
	private DireccionElectronicaEquividaDao direccionElectronicaEquividaDao;

	@Override
	public GenericDao<DireccionElectronicaEquivida, Integer> getDao() {
		return direccionElectronicaEquividaDao;
	}

	@Override
	public boolean existe(Integer secPersona,
			Integer secDireccionElectronicaEquivida,
			Integer idDireccionElectronicaSise, Integer idPersonaSise,
			Short sistemaOid) {

		String[] criteriasAnd = { "secPersonaEquivida",
				"secDireccionElectEquivida", "secDireccionElectDestino",
				"idPersonaDestino", "codSistema" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersona,
				secDireccionElectronicaEquivida, idDireccionElectronicaSise,
				idPersonaSise, sistemaOid };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<DireccionElectronicaEquivida> lista = direccionElectronicaEquividaDao
				.findByCriterias(criteria);

		return !lista.isEmpty();
	}
}