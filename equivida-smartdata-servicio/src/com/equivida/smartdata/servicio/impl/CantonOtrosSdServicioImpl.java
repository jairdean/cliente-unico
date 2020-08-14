/**
*ProvinciaOtrosservicioImpl.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.servicio.CantonOtrosSdServicio;
import com.equivida.smartdata.dao.CantonOtrosSdDao;
import com.equivida.smartdata.model.CantonOtrosSd;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "CantonOtrosSdServicio")
public class CantonOtrosSdServicioImpl extends GenericServiceImpl<CantonOtrosSd, Short> 
implements CantonOtrosSdServicio {

	@EJB
	private CantonOtrosSdDao cantonOtrosSdDao;

	@Override
	public GenericDao<CantonOtrosSd, Short> getDao() {
		return cantonOtrosSdDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.CantonOtrosSdServicio#
	 * Obtener obtenerSecCantonOtroByCodCantIess
	 */
	@Override
	public Short obtenerSecCantonOtroByCodCantIess(Integer codCantIess) {
		return cantonOtrosSdDao.obtenerSecCantonOtroByCodCantIess(codCantIess);
	}
}
