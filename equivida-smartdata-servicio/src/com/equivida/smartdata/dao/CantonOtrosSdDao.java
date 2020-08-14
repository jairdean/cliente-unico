/**
*CantonDao.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.CantonOtrosSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface CantonOtrosSdDao extends GenericDao<CantonOtrosSd, Short> {

	/**
	 * Obtiene SecCantonOtro por codCantIess
	 * 
	 * @param codCantIess
	 * @return SecCantonOtro
	 */
	Short obtenerSecCantonOtroByCodCantIess(String codCantIess);
	
	/**
	 * Obtiene SecCantonOtro por codCantSri
	 * 
	 * @param codCantSri
	 * @return SecCantonOtro
	 */
	Short obtenerSecCantonOtroByCodCantSri(String codCantSri);
}