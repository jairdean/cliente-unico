package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.IngresoMensualAdicionalDao;
import com.equivida.modelo.IngresoMensualAdicional;
import com.equivida.servicio.IngresoMensualAdicionalServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "IngresoMensualAdicionalServicio")
public class IngresoMensualAdicionalServicioImpl extends GenericServiceImpl<IngresoMensualAdicional, Integer>
		implements IngresoMensualAdicionalServicio {

	@EJB
	private IngresoMensualAdicionalDao ingresoMensualAdicionalDao;

	public GenericDao<IngresoMensualAdicional, Integer> getDao() {
		return ingresoMensualAdicionalDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.IngresoMensualAdicionalServicio#persistir(com.equivida.
	 * modelo.IngresoMensualAdicional)
	 */
	@Override
	public void persistir(IngresoMensualAdicional ingresoMensualAdicional) {
		if (ingresoMensualAdicional.getSecIngresoAdicional() == null) {
			create(ingresoMensualAdicional);
		} else {
			update(ingresoMensualAdicional);
		}
	}
}
