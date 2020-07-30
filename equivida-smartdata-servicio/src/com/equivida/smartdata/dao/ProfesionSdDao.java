/**
*ProfesionDao.java
*
*Tue Jan 26 12:43:26 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.ProfesionSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface ProfesionSdDao extends GenericDao<ProfesionSd, Short> {

}