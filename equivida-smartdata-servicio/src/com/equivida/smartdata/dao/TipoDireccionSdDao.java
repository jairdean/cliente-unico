/**
*TipoDireccionDao.java
*
*Tue Jan 26 12:47:28 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.TipoDireccionSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface TipoDireccionSdDao extends GenericDao<TipoDireccionSd, Short> {

}