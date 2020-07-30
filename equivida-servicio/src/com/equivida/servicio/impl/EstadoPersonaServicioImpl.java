package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.EstadoPersonaDao;
import com.equivida.modelo.EstadoPersona;
import com.equivida.servicio.EstadoPersonaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "EstadoPersonaServicio")
public class EstadoPersonaServicioImpl extends
		GenericServiceImpl<EstadoPersona, Integer> implements
		EstadoPersonaServicio {

	@EJB
	private EstadoPersonaDao estadoPersonaDao;

	public GenericDao<EstadoPersona, Integer> getDao() {
		return estadoPersonaDao;
	}
}
