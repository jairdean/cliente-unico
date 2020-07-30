/**
*TipoDireccionElectronicaservicioImpl.java
*
*Tue Jan 26 12:48:34 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoDireccionElectronicaSdDao;
import com.equivida.smartdata.model.TipoDireccionElectronicaSd;
import com.equivida.smartdata.servicio.TipoDireccionElectronicaSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "TipoDireccionElectronicaSdServicio")
public class TipoDireccionElectronicaSdServicioImpl extends GenericServiceImpl<TipoDireccionElectronicaSd, Short>
		implements TipoDireccionElectronicaSdServicio {

	@EJB
	private TipoDireccionElectronicaSdDao tipoDireccionElectronicaDao;

	@Override
	public GenericDao<TipoDireccionElectronicaSd, Short> getDao() {
		return tipoDireccionElectronicaDao;
	}

}
