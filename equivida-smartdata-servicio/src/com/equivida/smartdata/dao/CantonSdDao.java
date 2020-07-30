/**
*CantonDao.java
*
*Tue Jan 26 12:31:00 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.CantonSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface CantonSdDao extends GenericDao<CantonSd, Short> {

}