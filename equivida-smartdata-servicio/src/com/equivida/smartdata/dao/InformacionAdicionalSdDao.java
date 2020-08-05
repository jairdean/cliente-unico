/**
 *InformacionAdicionalSdDao.java
 *
 */
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.equivida.smartdata.model.PersonaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface InformacionAdicionalSdDao extends GenericDao<InformacionAdicionalSd, Integer> {
	
	boolean crearInformacionAdicional(InformacionAdicionalSd inf);
	
}