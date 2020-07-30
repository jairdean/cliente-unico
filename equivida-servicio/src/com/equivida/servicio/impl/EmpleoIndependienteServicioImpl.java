package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.EmpleoIndependienteDao;
import com.equivida.modelo.EmpleoIndependiente;
import com.equivida.servicio.EmpleoIndependienteServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "EmpleoIndependienteServicio")
public class EmpleoIndependienteServicioImpl extends GenericServiceImpl<EmpleoIndependiente, Integer>
		implements EmpleoIndependienteServicio {

	@EJB
	private EmpleoIndependienteDao empleoIndependienteDao;

	public GenericDao<EmpleoIndependiente, Integer> getDao() {
		return empleoIndependienteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.EmpleoIndependienteServicio#persistir(com.equivida.
	 * modelo.EmpleoIndependiente)
	 */
	@Override
	public void persistir(EmpleoIndependiente empleoIndependiente) {
		if (empleoIndependiente.getSecEmpleoIndependiente() == null) {
			create(empleoIndependiente);
		} else {
			update(empleoIndependiente);
		}
	}
}
