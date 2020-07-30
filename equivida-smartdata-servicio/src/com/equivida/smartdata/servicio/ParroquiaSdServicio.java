/**
 *Parroquiaservicio.java
 *
 *Tue Jan 26 12:38:27 ECT 2016
 */
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.ParroquiaSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface ParroquiaSdServicio extends GenericService<ParroquiaSd, Short> {

	/**
	 * Obtiene la parroquia por defecto de un canton.
	 * 
	 * @param secCanton
	 * @return
	 */
	ParroquiaSd obtenerSinParroquiaPorCanton(Short secCanton);
}