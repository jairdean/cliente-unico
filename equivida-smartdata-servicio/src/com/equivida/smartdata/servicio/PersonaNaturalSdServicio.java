/**
 *PersonaNaturalservicio.java
 *
 *Tue Jan 26 12:42:07 ECT 2016
 */
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface PersonaNaturalSdServicio extends
		GenericService<PersonaNaturalSd, Integer> {

	/**
	 * Obtiene persona por el numero de documento.
	 * 
	 * @param noDocumento
	 * @return
	 */
	PersonaNaturalSd obtenerPersonaByIdentificacion(String noDocumento,
			boolean conRelaciones);

	/**
	 * Actualiza los datos personales de una persona natural.
	 * 
	 * @param datosActualiza
	 */
	void actualizaDatosPersonales(DatosActualizaSdDto datosActualiza);
	
	/**
	 * Ingresa los datos personales de una persona natural.
	 * 
	 * @param datosActualiza
	 */
	void insertarPersonaNatural(PersonaNaturalSd datosActualiza);

}