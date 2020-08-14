/**
*ProvinciaOtrosservicioImpl.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.ParroquiaOtrosSdDao;
import com.equivida.smartdata.model.ParroquiaOtrosSd;
import com.equivida.smartdata.servicio.ParroquiaOtrosSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "ParroquiaOtrosSdServicio")
public class ParroquiaOtrosSdServicioImpl extends GenericServiceImpl<ParroquiaOtrosSd, Short>
		implements ParroquiaOtrosSdServicio {

	@EJB
	private ParroquiaOtrosSdDao parroquiaOtrosSdDao;

	@Override
	public GenericDao<ParroquiaOtrosSd, Short> getDao() {
		return parroquiaOtrosSdDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.ParroquiaOtrosSdServicio# Obtener
	 * obtenerSecParroquiaOtroByCodParrIess
	 */
	@Override
	public ParroquiaOtrosSd obtenerSecParroquiaOtroByCodParrIess(String codParrIess) {
		return parroquiaOtrosSdDao.obtenerSecParroquiaOtroByCodParrIess(codParrIess);
	}
}
