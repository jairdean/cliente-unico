/**
 *DireccionElectronicaDao.java
 *
 *Wed Aug 05 19:07:12 ECT 2020
 */
package com.equivida.smartdata.dao;

import java.util.List;

import javax.ejb.Local;

import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface DireccionElectronicaSdDao extends GenericDao<DireccionElectronicaSd, Integer> {

	/**
	 * Obtiene List<DireccionElectronicaSd> por secPersona
	 * 
	 * @param secPersona
	 * @return List<DireccionElectronicaSd>
	 */
	List<DireccionElectronicaSd> obtenerDireccionElectronicaByPersonaSecPersona(Integer secPersona);

}