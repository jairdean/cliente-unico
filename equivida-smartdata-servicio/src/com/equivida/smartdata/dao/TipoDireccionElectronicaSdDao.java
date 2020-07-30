/**
*TipoDireccionElectronicaDao.java
*
*Tue Jan 26 12:48:34 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.TipoDireccionElectronicaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface TipoDireccionElectronicaSdDao extends GenericDao<TipoDireccionElectronicaSd, Short> {

}