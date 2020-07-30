package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.DeportePracticaDao;
import com.equivida.modelo.DeportePractica;
import com.equivida.servicio.DeportePracticaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "DeportePracticaServicio")
public class DeportePracticaServicioImpl extends
		GenericServiceImpl<DeportePractica, Integer> implements
		DeportePracticaServicio {

	@EJB
	private DeportePracticaDao deportePracticaDao;

	public GenericDao<DeportePractica, Integer> getDao() {
		return deportePracticaDao;
	}
}
