/**
*TipoTelefonoservicioImpl.java
*
*Tue Jan 26 12:52:47 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoTelefonoSdDao;
import com.equivida.smartdata.model.TipoTelefonoSd;
import com.equivida.smartdata.servicio.TipoTelefonoSdServicio;
import com.equivida.smartdata.servicio.TipoTelefonoServicioSdRemoto;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "TipoTelefonoSdServicio")
public class TipoTelefonoSdServicioImpl extends GenericServiceImpl<TipoTelefonoSd, Short>
		implements TipoTelefonoSdServicio, TipoTelefonoServicioSdRemoto {

	@EJB
	private TipoTelefonoSdDao tipoTelefonoDao;

	@Override
	public GenericDao<TipoTelefonoSd, Short> getDao() {
		return tipoTelefonoDao;
	}

}
