package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoTelefonoDao;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.TipoTelefonoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoTelefonoServicio")
public class TipoTelefonoServicioImpl extends
		GenericServiceImpl<TipoTelefono, Short> implements TipoTelefonoServicio {

	@EJB
	private TipoTelefonoDao tipoTelefonoDao;

	public GenericDao<TipoTelefono, Short> getDao() {
		return tipoTelefonoDao;
	}
}
