/**
*ProvinciaservicioImpl.java
*
*Tue Jan 26 12:44:41 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.ProvinciaSdDao;
import com.equivida.smartdata.model.ProvinciaSd;
import com.equivida.smartdata.servicio.ProvinciaSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "ProvinciaSdServicio")
public class ProvinciaSdServicioImpl extends GenericServiceImpl<ProvinciaSd, Short> implements ProvinciaSdServicio {

	@EJB
	private ProvinciaSdDao provinciaDao;

	@Override
	public GenericDao<ProvinciaSd, Short> getDao() {
		return provinciaDao;
	}

}
