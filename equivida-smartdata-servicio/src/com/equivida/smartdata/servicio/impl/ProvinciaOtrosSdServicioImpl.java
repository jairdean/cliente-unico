/**
*ProvinciaOtrosservicioImpl.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.ProvinciaOtrosSdDao;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.equivida.smartdata.model.ProvinciaOtrosSd;
import com.equivida.smartdata.servicio.ProvinciaOtrosSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "ProvinciaOtrosSdServicio")
public class ProvinciaOtrosSdServicioImpl extends GenericServiceImpl<ProvinciaOtrosSd, Short> 
implements ProvinciaOtrosSdServicio {

	@EJB
	private ProvinciaOtrosSdDao provinciaOtrosDao;

	@Override
	public GenericDao<ProvinciaOtrosSd, Short> getDao() {
		return provinciaOtrosDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.ProvinciaOtrosSdServicio#
	 * Obtener SecProvinciaOtroByCodProvIess
	 */
	@Override
	public Short obtenerSecProvinciaOtroByCodProvIess(Integer codProvIess) {
		return provinciaOtrosDao.obtenerSecProvinciaOtroByCodProvIess(codProvIess);
	}
}
