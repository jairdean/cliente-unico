/**
*DetalleRelacionservicioImpl.java
*
*Tue Jan 26 12:32:38 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.DetalleRelacionSdDao;
import com.equivida.smartdata.model.DetalleRelacionSd;
import com.equivida.smartdata.servicio.DetalleRelacionSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "DetalleRelacionSdServicio")
public class DetalleRelacionSdServicioImpl extends GenericServiceImpl<DetalleRelacionSd, Short>
		implements DetalleRelacionSdServicio {

	@EJB
	private DetalleRelacionSdDao detalleRelacionDao;

	@Override
	public GenericDao<DetalleRelacionSd, Short> getDao() {
		return detalleRelacionDao;
	}

}
