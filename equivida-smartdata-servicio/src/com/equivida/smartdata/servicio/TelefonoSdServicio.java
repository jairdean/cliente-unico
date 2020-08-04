/**
 *Telefonoservicio.java
 *
 *Tue Jan 26 12:46:16 ECT 2016
 */
package com.equivida.smartdata.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.smartdata.model.TelefonoSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface TelefonoSdServicio extends GenericService<TelefonoSd, Integer> {
	/**
	 * Actualiza los telefonos relacionados a una persona.
	 * 
	 * @param secPersona
	 * @param telefonosList
	 */
	void actualizaTelefonos(Integer secPersona, List<TelefonoSd> telefonosList);

	/**
	 * Obtiene direccion por no de documento y por la direccion.
	 * 
	 * @param noDocumento
	 * @param direccion
	 * @return
	 */
	TelefonoSd obtenerPorDocumentoTelefono(String noDocumento, String telefono);
	
	/**
	 * Ingresa un teléfono.
	 * 
	 * @param telefono
	 */
	void ingresarTelefono(TelefonoSd telefono);
}