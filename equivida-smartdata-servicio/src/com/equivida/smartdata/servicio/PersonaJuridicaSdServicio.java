/**
 *PersonaJuridicaservicio.java
 *
 *Tue Jan 26 12:40:53 ECT 2016
 */
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.exception.FindException;
import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.equivida.smartdata.model.PersonaSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface PersonaJuridicaSdServicio extends
		GenericService<PersonaJuridicaSd, Integer> {

	/**
	 * Busca persona juridica por numero de documento, si no lo encuentra lanza
	 * excepcion
	 * 
	 * @param noDocumento
	 * @return
	 */
	PersonaJuridicaSd buscarPorDocumento(String noDocumento)
			throws FindException;

	/**
	 * Crea Persona Juridica.
	 * 
	 * @param persona
	 * @param personaJuridica
	 */
	void crearPersonaJuridica(PersonaSd persona,
			PersonaJuridicaSd personaJuridica);
	
	/**
	 * Crea Persona Juridica.
	 * 
	 * @param PersonaJuridicaSd
	 */
	void crearSoloPersonaJuridica(PersonaJuridicaSd personaJuridica);
}