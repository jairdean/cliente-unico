/**
*ProvinciaDao.java
*
*Tue Jan 26 12:44:41 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.ProvinciaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface ProvinciaSdDao extends GenericDao<ProvinciaSd, Short> {

}