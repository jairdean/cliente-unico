/**
*RelacionDao.java
*
*Thu Feb 18 10:46:12 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.RelacionSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface RelacionSdDao extends GenericDao<RelacionSd, Integer> {

}