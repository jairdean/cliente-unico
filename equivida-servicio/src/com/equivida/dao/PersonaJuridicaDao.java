package com.equivida.dao;

import com.equivida.modelo.PersonaJuridica;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface PersonaJuridicaDao extends
		GenericDao<PersonaJuridica, Integer> {
	/**
	 * Obtiene persona juridica por numero de docuemnto.
	 * 
	 * @param noDocumento
	 * @return
	 */
	PersonaJuridica obtenerPorNoDocumento(String noDocumento);
}
