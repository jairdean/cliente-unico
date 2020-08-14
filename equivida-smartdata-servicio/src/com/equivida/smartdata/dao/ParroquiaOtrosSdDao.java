/**
*CantonDao.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.ParroquiaOtrosSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface ParroquiaOtrosSdDao extends GenericDao<ParroquiaOtrosSd, Short> {

	/**
	 * Obtiene SecParroquia por codParrIess
	 * 
	 * @param codParrIess
	 * @return SecParroquiaOtro
	 */
	ParroquiaOtrosSd obtenerSecParroquiaOtroByCodParrIess(String codParrIess);
}