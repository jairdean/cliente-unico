package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoRiesgoDao;
import com.equivida.modelo.TipoRiesgo;
import com.equivida.servicio.TipoRiesgoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoRiesgoServicio")
public class TipoRiesgoServicioImpl extends
		GenericServiceImpl<TipoRiesgo, Short> implements TipoRiesgoServicio {

	@EJB
	private TipoRiesgoDao tipoRiesgoDao;

	public GenericDao<TipoRiesgo, Short> getDao() {
		return tipoRiesgoDao;
	}
}
