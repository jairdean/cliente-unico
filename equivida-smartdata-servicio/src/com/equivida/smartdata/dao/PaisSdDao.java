/**
*PaisDao.java
*
*Tue Jan 26 12:37:15 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.PaisSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface PaisSdDao extends GenericDao<PaisSd, Short> {

}