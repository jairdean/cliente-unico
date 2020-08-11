/**
 *InformacionAdicionalSdDao.java
 *
 */
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface InformacionAdicionalSdDao extends GenericDao<InformacionAdicionalSd, Integer> {

	/**
	 * Obtiene InformacionAdicionalSd por secPersonaNatural
	 * 
	 * @param secPersonaNatural
	 * @return InformacionAdicionalSd
	 */
	InformacionAdicionalSd obtenerInformacionAdicionalBySecIdentificacion(String identificacion);
	

	/**
	 * Obtiene InformacionAdicionalSd por secPersonaNatural
	 * 
	 * @param secPersonaNatural
	 * @return InformacionAdicionalSd
	 */
	InformacionAdicionalSd obtenerInformacionAdicionalBySecPersonaNatural(Integer secPersonaNatural);
	
}