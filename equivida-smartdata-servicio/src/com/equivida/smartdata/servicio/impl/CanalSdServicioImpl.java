/**
*CanalservicioImpl.java
*
*Tue Jan 26 12:29:38 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.CanalSdDao;
import com.equivida.smartdata.model.CanalSd;
import com.equivida.smartdata.servicio.CanalSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "CanalSdServicio")
public class CanalSdServicioImpl extends GenericServiceImpl<CanalSd, Short> implements CanalSdServicio {

	@EJB
	private CanalSdDao canalDao;

	@Override
	public GenericDao<CanalSd, Short> getDao() {
		return canalDao;
	}

}
