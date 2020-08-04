/**
 *TelefonoDao.java
 *
 *Tue Jan 26 12:46:16 ECT 2016
 */
package com.equivida.smartdata.dao;

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
	 * ingresa un teléfono.
	 * 
	 * @param telefono
	 */
	boolean ingresarTelefono(TelefonoSd telefono);
}