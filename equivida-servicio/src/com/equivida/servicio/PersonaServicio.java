package com.equivida.servicio;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.equivida.dto.CompaniaCrmDto;
import com.equivida.dto.PersonaCrmDto;
import com.equivida.modelo.Persona;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface PersonaServicio extends GenericService<Persona, Integer> {

	PersonaCrmDto obtenerPersonaCrm(Long secPersona);

	CompaniaCrmDto obtenerCompaniaCrm(Long secCompania);

	Map<String, Integer> obtenerPersonaByDocumento(List<String> numeroDocumento);

	/**
	 * Persiste un objeto Persona.
	 * 
	 * @param persona
	 */
	void persitir(Persona persona);
}