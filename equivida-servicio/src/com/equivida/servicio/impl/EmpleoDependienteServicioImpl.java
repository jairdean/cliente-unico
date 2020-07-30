package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.EmpleoDependienteDao;
import com.equivida.modelo.EmpleoDependiente;
import com.equivida.servicio.EmpleoDependienteServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "EmpleoDependienteServicio")
public class EmpleoDependienteServicioImpl extends GenericServiceImpl<EmpleoDependiente, Integer>
		implements EmpleoDependienteServicio {

	@EJB
	private EmpleoDependienteDao empleoDependienteDao;

	public GenericDao<EmpleoDependiente, Integer> getDao() {
		return empleoDependienteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.EmpleoDependienteServicio#persistir(com.equivida.modelo
	 * .EmpleoDependiente)
	 */
	@Override
	public void persistir(EmpleoDependiente empleoDependiente) {
		if (empleoDependiente.getSecEmpleoDependiente() == null) {
			create(empleoDependiente);
		} else {
			update(empleoDependiente);
		}
	}
}
