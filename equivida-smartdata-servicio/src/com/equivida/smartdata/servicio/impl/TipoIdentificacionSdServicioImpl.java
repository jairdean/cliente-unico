/**
*TipoIdentificacionservicioImpl.java
*
*Tue Jan 26 12:49:53 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoIdentificacionSdDao;
import com.equivida.smartdata.model.TipoIdentificacionSd;
import com.equivida.smartdata.servicio.TipoIdentificacionSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "TipoIdentificacionSdServicio")
public class TipoIdentificacionSdServicioImpl extends GenericServiceImpl<TipoIdentificacionSd, Character>
		implements TipoIdentificacionSdServicio {

	@EJB
	private TipoIdentificacionSdDao tipoIdentificacionDao;

	@Override
	public GenericDao<TipoIdentificacionSd, Character> getDao() {
		return tipoIdentificacionDao;
	}

}
