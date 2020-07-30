/**
*TipoTelefonoDao.java
*
*Tue Jan 26 12:52:47 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.TipoTelefonoSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface TipoTelefonoSdDao extends GenericDao<TipoTelefonoSd, Short> {

}