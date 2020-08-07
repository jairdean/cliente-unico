/**
*EmpleoDependienteServicio.java
*
*Wed Feb 17 12:55:38 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface EmpleoDependienteSdServicio extends GenericService<EmpleoDependienteSd, Integer> {

	/**
	 * Valida si existe la relacion entre persona natural y persona juridica.
	 * 
	 * @param secPersonaNatural
	 * @param secPersonaJuridica
	 * @return
	 */
	boolean existeRelacion(Integer secPersonaNatural, Integer secPersonaJuridica);

		/**
	 * Ingresa los datos Empleo DependienteSd.
	 * 
	 * @param EmpleoDependienteSd
	 */
	void crearEmpleoDependiente(EmpleoDependienteSd empleoDependienteSd);

	/**
	 * Obtener los datos Empleo DependienteSd.
	 * 
	 * @param secPersonaNatural
	 * @return EmpleoDependienteSd
	 */
	EmpleoDependienteSd obtenerEmpleoDependienteBySecPersonaNatural(Integer secPersonaNatural);
	
}