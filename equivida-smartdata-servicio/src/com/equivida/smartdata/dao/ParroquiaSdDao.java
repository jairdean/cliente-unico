/**
 *ParroquiaDao.java
 *
 *Tue Jan 26 12:38:27 ECT 2016
 */
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.ParroquiaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface ParroquiaSdDao extends GenericDao<ParroquiaSd, Short> {
	/**
	 * Obtiene la parroquia por defecto de un canton.
	 * 
	 * @param secCanton
	 * @return
	 */
	ParroquiaSd obtenerSinParroquiaPorCanton(Short secCanton);
}