/**
*CanalDao.java
*
*Tue Jan 26 12:28:45 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.CanalSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface CanalSdDao extends GenericDao<CanalSd, Short> {

}