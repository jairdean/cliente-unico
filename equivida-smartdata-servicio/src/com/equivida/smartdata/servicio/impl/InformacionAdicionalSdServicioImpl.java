/**
 *InformacionAdicionalSdServicioImpl.java
 *
 *Tue Aug 04 11:15:45 ECT 2020
 */
package com.equivida.smartdata.servicio.impl;

import javax.ejb.Stateless;
import javax.ejb.EJB;

import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.equivida.smartdata.servicio.InformacionAdicionalSdServicio;
import com.equivida.smartdata.dao.InformacionAdicionalSdDao;
import com.saviasoft.persistence.util.dao.GenericDao;


@Stateless(name = "InformacionAdicionalSdServicio")
public class InformacionAdicionalSdServicioImpl extends GenericServiceImpl<InformacionAdicionalSd, Integer> 
implements InformacionAdicionalSdServicio  {


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
	 * Ingresar InformacionAdicionalSd
	 */
	@Override
	public void crearInformacionAdicional(InformacionAdicionalSd informacionAdicionalSd) {
		InformacionAdicionalSdDao.save(informacionAdicionalSd);
    }
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.InformacionAdicionalSdServicio#
	 * Obtener InformacionAdicionalSd
	 */
	@Override
	public InformacionAdicionalSd obtenerInformacionAdicionalBySecPersonaNatural(Integer secPersonaNatural) {
		return InformacionAdicionalSdDao.obtenerInformacionAdicionalBySecPersonaNatural(secPersonaNatural);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.InformacionAdicionalSdServicio#
	 * Obtener InformacionAdicionalSd
	 */
	public InformacionAdicionalSd obtenerInformacionAdicionalBySecIdentificacion(String identificacion) {
		return InformacionAdicionalSdDao.obtenerInformacionAdicionalBySecIdentificacion(identificacion);
	}
    

	
}
