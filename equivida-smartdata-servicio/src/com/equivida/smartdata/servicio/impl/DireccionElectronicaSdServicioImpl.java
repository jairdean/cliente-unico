/**
 *DireccionservicioImpl.java
 *
 *Wed Aug 05 19:07:12 ECT 2020
 */
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.equivida.smartdata.dao.DireccionElectronicaSdDao;
import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.equivida.smartdata.servicio.DireccionElectronicaSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "DireccionElectronicaSdServicio")
public class DireccionElectronicaSdServicioImpl extends GenericServiceImpl<DireccionElectronicaSd, Integer>
		implements DireccionElectronicaSdServicio {

	@EJB
	private DireccionElectronicaSdDao direccionElectronicaDao;

	@Override
	public GenericDao<DireccionElectronicaSd, Integer> getDao() {
		// TODO Auto-generated method stub
		return direccionElectronicaDao;
	}

	@Override
	public void ingresarDireccionElectronica(DireccionElectronicaSd direccion) {
		direccionElectronicaDao.ingresarDireccionElectronica(direccion);		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.PersonaJuridicaSdServicio#
	 * crearPersonaJuridica(com.equivida.smartdata.model.PersonaSd,
	 * com.equivida.smartdata.model.PersonaJuridicaSd)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void jairoflores(DireccionElectronicaSd direccionElectronicaSd) {
		direccionElectronicaDao.save(direccionElectronicaSd);

	}
}
