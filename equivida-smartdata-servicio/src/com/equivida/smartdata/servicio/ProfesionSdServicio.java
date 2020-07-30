/**
 *Profesionservicio.java
 *
 *Tue Jan 26 12:43:26 ECT 2016
 */
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.ProfesionSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface ProfesionSdServicio extends GenericService<ProfesionSd, Short> {
	/**
	 * Cosulta profesion por el codigo de Databook.
	 * 
	 * @param idProfesionDB
	 * @return
	 */
	ProfesionSd consultarPorCodigoDB(String idProfesionDB);

	/**
	 * Obtiene profesion por defecto: 2327
	 * 
	 * @return
	 */
	ProfesionSd obtenerPorDefecto();
}