package com.equivida.homologacion.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.equivida.homologacion.constant.SistemaEnum;
import com.equivida.homologacion.dao.PersonaEquividaDao;
import com.equivida.homologacion.modelo.PersonaEquivida;
import com.equivida.homologacion.servicio.PersonaEquividaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

@Stateless(name = "PersonaEquividaServicio")
public class PersonaEquividaServicioImpl extends GenericServiceImpl<PersonaEquivida, Integer>
		implements PersonaEquividaServicio {

	@EJB
	private PersonaEquividaDao personaEquividaDao;

	@Override
	public GenericDao<PersonaEquivida, Integer> getDao() {
		return personaEquividaDao;
	}

	@Override
	public Long obtenerIdPersonaDestinoPN(Integer secPersona, Integer secPersonaNatural, Short sistemaOid,
			Short codTipoPersona) {

		String[] criteriasAnd = { "secPersonaEquivida", "secPersonaNatural", "codSistema", "codTipoPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.SHORT_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersona, secPersonaNatural, sistemaOid, codTipoPersona };
		// ordenado para devolver el ultimo id en el caso de q exista mas de uno
		String[] criteriaOrderBy = new String[] { "idPersonaDestino" };
		boolean[] asc = new boolean[] { false };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params, criteriaOrderBy, asc);

		List<PersonaEquivida> lista = personaEquividaDao.findByCriterias(criteria);

		Long id = null;
		int total = lista.size();
		if (total >= 1) {
			id = lista.get(0).getIdPersonaDestino();
		}
		return id;
	}

	@Override
	public boolean existe(Integer secPersona, Integer secPersonaNatural, Long idPersonaSise, Short sistemaOid,
			Short codTipoPersona) {

		String[] criteriasAnd = { "secPersonaEquivida", "secPersonaNatural", "idPersonaDestino", "codSistema",
				"codTipoPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.LONG_EQUALS, CriteriaTypeEnum.SHORT_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersona, secPersonaNatural, idPersonaSise, sistemaOid, codTipoPersona };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<PersonaEquivida> lista = personaEquividaDao.findByCriterias(criteria);

		return !lista.isEmpty();

	}

	@Override
	public boolean existeJuridica(Integer secPersona, Integer secPersonaJuridica, Long idPersonaSise, Short sistemaOid,
			Short codTipoPersona) {
		String[] criteriasAnd = { "secPersonaEquivida", "secPersonaJuridica", "idPersonaDestino", "codSistema",
				"codTipoPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.LONG_EQUALS, CriteriaTypeEnum.SHORT_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersona, secPersonaJuridica, idPersonaSise, sistemaOid, codTipoPersona };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<PersonaEquivida> lista = personaEquividaDao.findByCriterias(criteria);

		return !lista.isEmpty();
	}

	@Override
	public Long obtenerIdSisePersonaNatural(Integer secPersonaNatural) {
		String[] criteriasAnd = { "secPersonaNatural", "codSistema" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersonaNatural, SistemaEnum.SISE.getCodigo() };
		// ordenado para devolver el ultimo id en el caso de q exista mas de uno
		String[] criteriaOrderBy = new String[] { "idPersonaDestino" };
		boolean[] asc = new boolean[] { false };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params, criteriaOrderBy, asc);

		List<PersonaEquivida> lista = personaEquividaDao.findByCriterias(criteria);

		Long id = null;
		int total = lista.size();
		if (total >= 1) {
			id = lista.get(0).getIdPersonaDestino();
		}
		return id;
	}

	@Override
	public Integer obtenerSecuencialPersonaNatural(Long idSise) {
		String[] criteriasAnd = { "idPersonaDestino", "codSistema" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.LONG_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { idSise, SistemaEnum.SISE.getCodigo() };
		// ordenado para devolver el ultimo id en el caso de q exista mas de uno
		String[] criteriaOrderBy = new String[] { "idPersonaDestino" };
		boolean[] asc = new boolean[] { false };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params, criteriaOrderBy, asc);

		List<PersonaEquivida> lista = personaEquividaDao.findByCriterias(criteria);

		Integer secuencial = null;
		int total = lista.size();
		if (total >= 1) {
			secuencial = lista.get(0).getSecPersonaNatural();
		}
		return secuencial;

	}

	@Override
	public List<Long> obtenerIdSisePersona(Integer secPersona) {

		String[] criteriasAnd = { "secPersonaEquivida", "codSistema" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersona, SistemaEnum.SISE.getCodigo() };
		// ordenado para devolver el ultimo id en el caso de q exista mas de uno
		String[] criteriaOrderBy = new String[] { "idPersonaDestino" };
		boolean[] asc = new boolean[] { false };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params, criteriaOrderBy, asc);

		List<PersonaEquivida> lista = personaEquividaDao.findByCriterias(criteria);

		List<Long> listaIds = new ArrayList<Long>();

		for (PersonaEquivida personaEquivida : lista) {
			listaIds.add(personaEquivida.getIdPersonaDestino());
		}

		return listaIds;
	}

	@Override
	public Integer obtenerSecuencialPersona(Long idSise) {
		String[] criteriasAnd = { "idPersonaDestino", "codSistema" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.LONG_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { idSise, SistemaEnum.SISE.getCodigo() };
		// ordenado para devolver el ultimo id en el caso de q exista mas de uno
		String[] criteriaOrderBy = new String[] { "idPersonaDestino" };
		boolean[] asc = new boolean[] { false };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params, criteriaOrderBy, asc);

		List<PersonaEquivida> lista = personaEquividaDao.findByCriterias(criteria);

		Integer secuencial = null;
		int total = lista.size();
		if (total >= 1) {
			secuencial = lista.get(0).getSecPersonaEquivida();
		}
		return secuencial;
	}

	@Override
	public Map<Long, Long> obtenerSecuencialPersona(List<Long> idsSise) {
		return personaEquividaDao.obtenerSecuencialPersona(idsSise);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.homologacion.servicio.PersonaEquividaServicio#createNoTx(com.
	 * equivida.homologacion.modelo.PersonaEquivida)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void createNoTx(PersonaEquivida personaEquivida) {
		create(personaEquivida);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.homologacion.servicio.PersonaEquividaServicio#existeNoTx(java.
	 * lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Short,
	 * java.lang.Short)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean existeNoTx(Integer secPersona, Integer secPersonaNatural, Long idPersonaSise, Short sistemaOid,
			Short codTipoPersona) {
		String[] criteriasAnd = { "secPersonaEquivida", "secPersonaNatural", "idPersonaDestino", "codSistema",
				"codTipoPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.LONG_EQUALS, CriteriaTypeEnum.SHORT_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersona, secPersonaNatural, idPersonaSise, sistemaOid, codTipoPersona };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<PersonaEquivida> lista = personaEquividaDao.findByCriterias(criteria);

		return !lista.isEmpty();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean existeJuridicaNoTx(Integer secPersona, Integer secPersonaJuridica, Long idPersonaSise,
			Short sistemaOid, Short codTipoPersona) {
		String[] criteriasAnd = { "secPersonaEquivida", "secPersonaJuridica", "idPersonaDestino", "codSistema",
				"codTipoPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.LONG_EQUALS, CriteriaTypeEnum.SHORT_EQUALS, CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { secPersona, secPersonaJuridica, idPersonaSise, sistemaOid, codTipoPersona };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<PersonaEquivida> lista = personaEquividaDao.findByCriterias(criteria);

		return !lista.isEmpty();
	}
}