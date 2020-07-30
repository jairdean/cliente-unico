package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoActividadDao;
import com.equivida.modelo.TipoActividad;
import com.equivida.servicio.TipoActividadServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoActividadServicio")
public class TipoActividadServicioImpl extends
		GenericServiceImpl<TipoActividad, Short> implements
		TipoActividadServicio {

	@EJB
	private TipoActividadDao tipoActividadDao;

	public GenericDao<TipoActividad, Short> getDao() {
		return tipoActividadDao;
	}
}
