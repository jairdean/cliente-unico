/**
 *Direccionservicio.java
 *
 *Tue Jan 26 12:34:07 ECT 2016
 */
package com.equivida.smartdata.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.smartdata.model.DireccionSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface DireccionSdServicio extends
		GenericService<DireccionSd, Integer> {
	/**
	 * Se actualiza las direcciones de una persona.
	 * 
	 * @param secPersona
	 * @param direccionList
	 */
	void actualizaDirecciones(Integer secPersona,
			List<DireccionSd> direccionList);

	/**
	 * Obtiene direccion por no de documento y por la direccion.
	 * 
	 * @param noDocumento
	 * @param direccion
	 * @return
	 */
	DireccionSd obtenerPorDocumentoDireccion(String noDocumento,
			String direccion);
}