/**
*EstadoCivilservicioImpl.java
*
*Tue Jan 26 12:35:33 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.EstadoCivilSdDao;
import com.equivida.smartdata.model.EstadoCivilSd;
import com.equivida.smartdata.servicio.EstadoCivilSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "EstadoCivilSdServicio")
public class EstadoCivilSdServicioImpl extends GenericServiceImpl<EstadoCivilSd, Short> implements EstadoCivilSdServicio {

	@EJB
	private EstadoCivilSdDao estadoCivilDao;

	@Override
	public GenericDao<EstadoCivilSd, Short> getDao() {
		return estadoCivilDao;
	}

}
