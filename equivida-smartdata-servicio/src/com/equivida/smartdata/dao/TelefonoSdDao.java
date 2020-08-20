/**
 *TelefonoDao.java
 *
 *Tue Jan 26 12:46:16 ECT 2016
 */
package com.equivida.smartdata.dao;

import java.util.List;

import javax.ejb.Local;

import com.equivida.smartdata.model.TelefonoSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface TelefonoSdDao extends GenericDao<TelefonoSd, Integer> {
	/**
	 * Cambia de estado a Inactivo los telefonos de una persona.
	 * 
	 * @param secPersona
	 */
	void desactivarTelefonsosPersona(Integer secPersona);

	/**
	 * Obtiene direccion por no de documento y por la direccion.
	 * 
	 * @param noDocumento
	 * @param direccion
	 * @return
	 */
	TelefonoSd obtenerPorDocumentoTelefono(String noDocumento, String telefono);

	/**
	 * Obtiene Telefonos por secPersona
	 * 
	 * @param secPersona
	 * @return List<TelefonoSd>
	 */
	List<TelefonoSd> obtenerTelefonoByPersonaSecPersona(Integer secPersona);

	/**
	 * Obtiene Telefono por secPersona, nroTelefono, codArea
	 * 
	 * @param secPersona, nroTelefono, codArea
	 * @return TelefonoSd
	 */
	TelefonoSd obtenerTelefonoBySecPersonaAndNroTeefono(Integer secPersona, String nroTelefono, String codArea);
}