/**
*CantonservicioImpl.java
*
*Tue Jan 26 12:31:00 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.CantonSdDao;
import com.equivida.smartdata.model.CantonSd;
import com.equivida.smartdata.servicio.CantonSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "CantonSdServicio")
public class CantonSdServicioImpl extends GenericServiceImpl<CantonSd, Short> implements CantonSdServicio {

	@EJB
	private CantonSdDao cantonDao;

	@Override
	public GenericDao<CantonSd, Short> getDao() {
		return cantonDao;
	}

}
