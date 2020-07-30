/**
*TipoDireccionservicioImpl.java
*
*Tue Jan 26 12:47:28 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoDireccionSdDao;
import com.equivida.smartdata.model.TipoDireccionSd;
import com.equivida.smartdata.servicio.TipoDireccionSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "TipoDireccionSdServicio")
public class TipoDireccionSdServicioImpl extends GenericServiceImpl<TipoDireccionSd, Short>
		implements TipoDireccionSdServicio {

	@EJB
	private TipoDireccionSdDao tipoDireccionDao;

	@Override
	public GenericDao<TipoDireccionSd, Short> getDao() {
		return tipoDireccionDao;
	}

}
