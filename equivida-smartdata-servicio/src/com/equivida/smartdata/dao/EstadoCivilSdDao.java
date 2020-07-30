/**
*EstadoCivilDao.java
*
*Tue Jan 26 12:35:33 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.EstadoCivilSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface EstadoCivilSdDao extends GenericDao<EstadoCivilSd, Short> {

}