/**
 *DireccionElectronicaDao.java
 *
 *Wed Aug 05 19:07:12 ECT 2020
 */
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface DireccionElectronicaSdDao extends GenericDao<DireccionElectronicaSd, Integer> {
	/**
	 * ingresa una Direccion ElectronicaSd.
	 * 
	 * @param DireccionElectronicaSd
	 * @return
	 */
	boolean ingresarDireccionElectronica(DireccionElectronicaSd direccion);
}