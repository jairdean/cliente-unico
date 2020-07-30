package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoEstadoDao;
import com.equivida.modelo.TipoEstado;
import com.equivida.servicio.TipoEstadoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoEstadoServicio")
public class TipoEstadoServicioImpl extends
		GenericServiceImpl<TipoEstado, Short> implements TipoEstadoServicio {

	@EJB
	private TipoEstadoDao tipoEstadoDao;

	public GenericDao<TipoEstado, Short> getDao() {
		return tipoEstadoDao;
	}
}
