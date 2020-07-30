package com.equivida.dao;

import java.util.List;
import java.util.Map;

import com.equivida.dto.CompaniaCrmDto;
import com.equivida.dto.PersonaCrmDto;
import com.equivida.modelo.Persona;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
public interface PersonaDao extends GenericDao<Persona, Integer> {

	PersonaCrmDto obtenerpersonaCrm(Long secPersona);

	CompaniaCrmDto obtenerCompaniaCrm(Long secCompania);

	Map<String, Integer> obtenerPersonaByDocumento(List<String> numeroDocumento);
}
