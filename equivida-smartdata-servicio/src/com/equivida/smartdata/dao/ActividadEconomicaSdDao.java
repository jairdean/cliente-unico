/**
*ActividadEconomicaDao.java
*
*Tue Jan 26 12:17:53 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.ActividadEconomicaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface ActividadEconomicaSdDao extends GenericDao<ActividadEconomicaSd, Short> {

}