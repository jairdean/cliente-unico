/**
*ProvinciaOtrosSdDao.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.ProvinciaOtrosSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface ProvinciaOtrosSdDao extends GenericDao<ProvinciaOtrosSd, Short> {

	/**
	 * Obtiene SecProvinciaOtro por codProvIess
	 * 
	 * @param codProvIess
	 * @return secProvinciaOtros
	 */
	Short obtenerSecProvinciaOtroByCodProvIess(Integer codProvIess);
}