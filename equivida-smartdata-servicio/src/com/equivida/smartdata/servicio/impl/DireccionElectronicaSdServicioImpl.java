/**
 *DireccionservicioImpl.java
 *
 *Tue Jan 26 12:34:07 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.DireccionElectronicaSdDao;
import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.servicio.DireccionElectronicaSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "DireccionElectronicaSdServicio")
public class DireccionElectronicaSdServicioImpl extends GenericServiceImpl<DireccionSd, Integer>
		implements DireccionElectronicaSdServicio {

	@EJB
	private DireccionElectronicaSdDao direccionElectronicaDao;

	@Override
	public GenericDao<DireccionSd, Integer> getDao() {
		// TODO Auto-generated method stub
		return direccionElectronicaDao;
	}

	@Override
	public void ingresarDireccionElectronica(DireccionElectronicaSd direccion) {
		direccionElectronicaDao.ingresarDireccionElectronica(direccion);		
	}

}
