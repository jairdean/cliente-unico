package com.equivida.servicio.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.PersonaDao;
import com.equivida.dto.CompaniaCrmDto;
import com.equivida.dto.PersonaCrmDto;
import com.equivida.modelo.Persona;
import com.equivida.servicio.PersonaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PersonaServicio")
public class PersonaServicioImpl extends GenericServiceImpl<Persona, Integer> implements PersonaServicio {

	@EJB
	private PersonaDao personaDao;

	public GenericDao<Persona, Integer> getDao() {
		return personaDao;
	}

	@Override
	public PersonaCrmDto obtenerPersonaCrm(Long secPersona) {
		return personaDao.obtenerpersonaCrm(secPersona);
	}

	@Override
	public CompaniaCrmDto obtenerCompaniaCrm(Long secCompania) {
		return personaDao.obtenerCompaniaCrm(secCompania);
	}

	@Override
	public Map<String, Integer> obtenerPersonaByDocumento(List<String> numeroDocumento) {
		return personaDao.obtenerPersonaByDocumento(numeroDocumento);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.PersonaServicio#persitir(com.equivida.modelo.Persona)
	 */
	@Override
	public void persitir(Persona persona) {
		if (persona.getSecPersona() == null) {
			create(persona);
		} else {
			update(persona);
		}
	}
}
