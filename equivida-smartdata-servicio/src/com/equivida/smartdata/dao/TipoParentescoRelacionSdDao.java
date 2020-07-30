/**
*TipoParentescoRelacionDao.java
*
*Tue Jan 26 12:51:11 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.TipoParentescoRelacionSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface TipoParentescoRelacionSdDao extends GenericDao<TipoParentescoRelacionSd, Short> {

}