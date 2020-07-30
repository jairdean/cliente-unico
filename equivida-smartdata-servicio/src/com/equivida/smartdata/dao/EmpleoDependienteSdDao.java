/**
*EmpleoDependienteDao.java
*
*Wed Feb 17 12:55:38 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface EmpleoDependienteSdDao extends GenericDao<EmpleoDependienteSd, Integer> {

}