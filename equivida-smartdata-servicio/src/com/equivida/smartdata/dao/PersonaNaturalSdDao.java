/**
 *PersonaNaturalDao.java
 *
 *Tue Jan 26 12:42:07 ECT 2016
 */
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.equivida.smartdata.model.PersonaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface PersonaNaturalSdDao extends GenericDao<PersonaNaturalSd, Integer> {
	/**
	 * Actualiza los datos personales de una persona natural.
	 * 
	 * @param datosActualiza
	 */
	void actualizaDatosPersonales(DatosActualizaSdDto datosActualiza);

	/**
	 * Obtiene persona por el numero de documento.
	 * 
	 * @param noDocumento
	 * @return
	 */
	PersonaNaturalSd obtenerPersonaByIdentificacion(String noDocumento);
}