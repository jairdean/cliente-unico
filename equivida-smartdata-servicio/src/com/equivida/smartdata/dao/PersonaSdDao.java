/**
 *PersonaDao.java
 *
 *Tue Jan 26 12:39:40 ECT 2016
 */
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.model.PersonaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface PersonaSdDao extends GenericDao<PersonaSd, Integer> {
	/**
	 * Actualiza los datos personales de la persona.
	 * 
	 * @param datosActualiza
	 */
	void actualizaDatosPersonales(DatosActualizaSdDto datosActualiza);

	/**
	 * Busca una persona por el numero de documento.
	 * 
	 * @param noDocumento
	 * @return
	 */
	PersonaSd obtenerPersonaByIdentificacion(String noDocumento);
}