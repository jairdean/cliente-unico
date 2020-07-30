/**
 *ParroquiaservicioImpl.java
 *
 *Tue Jan 26 12:38:27 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.equivida.smartdata.dao.ParroquiaSdDao;
import com.equivida.smartdata.model.ParroquiaSd;
import com.equivida.smartdata.servicio.ParroquiaSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "ParroquiaSdServicio")
public class ParroquiaSdServicioImpl extends
		GenericServiceImpl<ParroquiaSd, Short> implements ParroquiaSdServicio {

	@EJB
	private ParroquiaSdDao parroquiaDao;

	@Override
	public GenericDao<ParroquiaSd, Short> getDao() {
		return parroquiaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.ParroquiaSdServicio#
	 * obtenerSinParroquiaPorCanton(java.lang.Short)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ParroquiaSd obtenerSinParroquiaPorCanton(Short secCanton) {
		return parroquiaDao.obtenerSinParroquiaPorCanton(secCanton);
	}

}
