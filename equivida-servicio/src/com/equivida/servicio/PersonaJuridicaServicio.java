package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface PersonaJuridicaServicio extends GenericService<PersonaJuridica, Integer> {

	PersonaJuridica findByPk(Integer secPersonaJuridica, boolean loadRepresentanteLegal, boolean loadDirecciones,
			boolean loadDireccionesConTelefonos, boolean loadPerfilFinanciero, boolean loadAccionistas);

	/**
	 * Busca por numero de docuemtno.
	 * 
	 * @param noDocumento
	 * @param loadRepresentanteLegal
	 * @param loadDirecciones
	 * @param loadDireccionesConTelefonos
	 * @param loadAccionistas
	 * @return
	 */
	PersonaJuridica findByNoDocumento(String noDocumento, boolean loadRepresentanteLegal, boolean loadDirecciones,
			boolean loadDireccionesConTelefonos, boolean loadPerfilFinanciero, boolean loadAccionistas);

	/**
	 * Crea persona juridica.
	 * 
	 * @param persona
	 * @param personaJuridica
	 */
	void crearPersonaJuridica(Persona persona, PersonaJuridica personaJuridica);

	/**
	 * Periste objeto personaJuridica.
	 * 
	 * @param personaNatural
	 */
	void persitir(PersonaJuridica personaJuridica);

	/**
	 * @param secPersona
	 * @return
	 */
	PersonaJuridica obtenerPersonaJuridicaByPersona(Integer secPersona);
}