/**
*TipoIdentificacionDao.java
*
*Tue Jan 26 12:49:53 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.TipoIdentificacionSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface TipoIdentificacionSdDao extends GenericDao<TipoIdentificacionSd, Character> {

}