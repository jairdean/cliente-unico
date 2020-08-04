/**
 *DireccionDao.java
 *
 *Tue Jan 26 12:34:07 ECT 2016
 */
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.equivida.smartdata.model.DireccionSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface DireccionElectronicaSdDao extends GenericDao<DireccionSd, Integer> {
	/**
	 * ingresa una direccion.
	 * 
	 * @param direccion
	 * @return
	 */
	boolean ingresarDireccionElectronica(DireccionElectronicaSd direccion);
}