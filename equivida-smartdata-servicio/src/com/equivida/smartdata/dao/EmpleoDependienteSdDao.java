/**
*EmpleoDependienteDao.java
*
*Fri Aug 07 12:55:38 ECT 2020
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface EmpleoDependienteSdDao extends GenericDao<EmpleoDependienteSd, Integer> {

	/**
	 * Obtiene EmpleoDependienteSd por secPersona
	 * 
	 * @param secPersona
	 * @return secPersonaNatural
	 * @return secPersonaJuridica
	 */
	EmpleoDependienteSd obtenerEmpleoDependienteBySecPersonaNatural(Integer secPersonaNatural, Integer secPersonaJuridica);

}