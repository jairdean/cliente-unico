/**
*PaisservicioImpl.java
*
*Tue Jan 26 12:37:15 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.PaisSdDao;
import com.equivida.smartdata.model.PaisSd;
import com.equivida.smartdata.servicio.PaisSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "PaisSdServicio")
public class PaisSdServicioImpl extends GenericServiceImpl<PaisSd, Short> implements PaisSdServicio {

	@EJB
	private PaisSdDao paisDao;

	@Override
	public GenericDao<PaisSd, Short> getDao() {
		return paisDao;
	}

}
