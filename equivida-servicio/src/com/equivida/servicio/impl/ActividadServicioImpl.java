package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ActividadDao;
import com.equivida.modelo.Actividad;
import com.equivida.servicio.ActividadServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ActividadServicio")
public class ActividadServicioImpl extends
		GenericServiceImpl<Actividad, Integer> implements ActividadServicio {

	@EJB
	private ActividadDao actividadDao;

	public GenericDao<Actividad, Integer> getDao() {
		return actividadDao;
	}
}
