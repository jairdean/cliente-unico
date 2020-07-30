package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.EstadoRcsDao;
import com.equivida.modelo.EstadoRcs;
import com.equivida.servicio.EstadoRcsServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "EstadoRcsServicio")
public class EstadoRcsServicioImpl extends
		GenericServiceImpl<EstadoRcs, Character> implements EstadoRcsServicio {

	@EJB
	private EstadoRcsDao estadoRcsDao;

	public GenericDao<EstadoRcs, Character> getDao() {
		return estadoRcsDao;
	}

	@Override
	public List<EstadoRcs> obtenerTodos() {
		return estadoRcsDao.obtenerTodos();
	}
}
