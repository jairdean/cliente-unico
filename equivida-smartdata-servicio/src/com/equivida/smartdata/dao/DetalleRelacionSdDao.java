/**
*DetalleRelacionDao.java
*
*Tue Jan 26 12:32:38 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.DetalleRelacionSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface DetalleRelacionSdDao extends GenericDao<DetalleRelacionSd, Short> {

}