/**
 *InformacionAdicionalSdServicioImpl.java
 *
 *Tue Aug 04 11:15:45 ECT 2020
 */
package com.equivida.smartdata.servicio.impl;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.Stateless;
import javax.ejb.EJB;

import java.util.List;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.equivida.smartdata.servicio.InformacionAdicionalSdServicio;
import com.equivida.smartdata.dao.InformacionAdicionalSdDao;
import com.saviasoft.persistence.util.dao.GenericDao;


@Stateless(name = "InformacionAdicionalSdServicio")
public class InformacionAdicionalSdServicioImpl extends GenericServiceImpl<InformacionAdicionalSd, Integer> implements InformacionAdicionalSdServicio  {


	@EJB
	private InformacionAdicionalSdDao InformacionAdicionalSdDao;

	@Override
	public GenericDao<InformacionAdicionalSd, Integer> getDao() {
		return InformacionAdicionalSdDao;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.InformacionAdicionalSdServicio#
	 */
	@Override
	public boolean crearInformacionAdicional(InformacionAdicionalSd informacionAdicionalSd) {
		return InformacionAdicionalSdDao.crearInformacionAdicional(informacionAdicionalSd);
    }
    
    
	
}
