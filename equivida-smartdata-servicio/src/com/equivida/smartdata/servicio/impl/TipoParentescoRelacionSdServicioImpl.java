/**
*TipoParentescoRelacionservicioImpl.java
*
*Tue Jan 26 12:51:11 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoParentescoRelacionSdDao;
import com.equivida.smartdata.model.TipoParentescoRelacionSd;
import com.equivida.smartdata.servicio.TipoParentescoRelacionSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "TipoParentescoRelacionSdServicio")
public class TipoParentescoRelacionSdServicioImpl extends GenericServiceImpl<TipoParentescoRelacionSd, Short>
		implements TipoParentescoRelacionSdServicio {

	@EJB
	private TipoParentescoRelacionSdDao tipoParentescoRelacionDao;

	@Override
	public GenericDao<TipoParentescoRelacionSd, Short> getDao() {
		return tipoParentescoRelacionDao;
	}

}
