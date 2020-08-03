/**
 *Personaservicio.java
 *
 *Tue Jan 26 12:39:40 ECT 2016
 */
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.databook.model.Registros;
import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.model.PersonaSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface PersonaSdServicio extends GenericService<PersonaSd, Integer> {
	/**
	 * Busca una persona por el numero de documento.
	 * 
	 * @param noDocumento
	 * @return
	 */
	PersonaSd obtenerPersonaByIdentificacion(String noDocumento);

	/**
	 * Actualiza los datos personales de la persona.
	 * 
	 * @param datosActualiza
	 */
	void actualizaDatosPersonales(DatosActualizaSdDto datosActualiza);

	/**
	 * Ingresa los datos personales de una persona
	 * 
	 * @param persona
	 */
	void IngresarPersona(PersonaSd persona);
}