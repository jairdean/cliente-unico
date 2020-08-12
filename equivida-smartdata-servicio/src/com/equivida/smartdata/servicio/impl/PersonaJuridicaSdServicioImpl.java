/**
 *PersonaJuridicaservicioImpl.java
 *
 *Tue Jan 26 12:40:53 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.equivida.smartdata.dao.PersonaJuridicaSdDao;
import com.equivida.smartdata.dao.PersonaSdDao;
import com.equivida.smartdata.exception.FindException;
import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.servicio.PersonaJuridicaSdServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

@Stateless(name = "PersonaJuridicaSdServicio")
public class PersonaJuridicaSdServicioImpl extends
		GenericServiceImpl<PersonaJuridicaSd, Integer> implements
		PersonaJuridicaSdServicio {

	@EJB
	private PersonaJuridicaSdDao personaJuridicaDao;

	@EJB
	private PersonaSdDao personaSdDao;

	@Override
	public GenericDao<PersonaJuridicaSd, Integer> getDao() {
		return personaJuridicaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.PersonaJuridicaServicio#
	 * buscarPorDocumento(java.lang.String)
	 */
	@Override
	public PersonaJuridicaSd buscarPorDocumento(String noDocumento)
			throws FindException {
		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { noDocumento };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);
		List<PersonaJuridicaSd> lista = findByCriterias(criteria);

		if (lista != null && !lista.isEmpty()) {
			return lista.get(0);
		} else {
			throw new FindException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.PersonaJuridicaSdServicio#
	 * crearPersonaJuridica(com.equivida.smartdata.model.PersonaSd,
	 * com.equivida.smartdata.model.PersonaJuridicaSd)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void crearPersonaJuridica(PersonaSd persona,
			PersonaJuridicaSd personaJuridica) {
		// 1. Persiswte persona
		System.out.println("envia a guardar personasd");
		System.out.println(persona.getSecPersona());
		System.out.println(persona.getIdentificacion());
		System.out.println(persona.getDenominacion());
		System.out.println(persona.getCodTipoIdentificacion());

		personaSdDao.save(persona);

		// 2. Persiste PErsona Juridica
		personaJuridicaDao.save(personaJuridica);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.PersonaJuridicaSdServicio#
	 * crearSoloPersonaJuridica(com.equivida.smartdata.model.PersonaJuridicaSd)
	 */
	@Override
	public void	crearSoloPersonaJuridica(PersonaJuridicaSd personaJuridica) {
		personaJuridicaDao.save(personaJuridica);
	}
	
	@Override
	public PersonaJuridicaSd buscarPersonaPorIdentificacion(String identificacion) {
		return personaJuridicaDao.obtenerPersonaJuridicaPorIdentifiacion(identificacion);
	}
}
