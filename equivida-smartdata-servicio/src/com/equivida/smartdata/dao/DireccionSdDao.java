/**
 *DireccionDao.java
 *
 *Tue Jan 26 12:34:07 ECT 2016
 */
package com.equivida.smartdata.dao;

import java.util.List;

import javax.ejb.Local;

import com.equivida.smartdata.model.DireccionSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface DireccionSdDao extends GenericDao<DireccionSd, Integer> {

	/**
	 * Inactiva todas las direcciones de una persona.
	 * 
	 * @param secPersona
	 */
	void desactivarDireccionesPersona(Integer secPersona);

	/**
	 * Obtiene direccion por no de documento y por la direccion.
	 * 
	 * @param noDocumento
	 * @param direccion
	 * @return
	 */
	DireccionSd obtenerPorDocumentoDireccion(String noDocumento, String direccion);

	/**
	 * Obtiene direccion por secPersona
	 * 
	 * @param secPersona
	 * @return List<DireccionSd>
	 */
	List<DireccionSd> obtenerDireccionByPersonaSecPersona(int secPersona);

	/**
	 * Obtiene direccion por secPersona y direccion
	 * 
	 * @param secPersona
	 * @param direccion
	 * @return DireccionSd
	 */
	DireccionSd obtenerDireccionBySecPersonaAndDireccion(int secPersona, String direccion);
}