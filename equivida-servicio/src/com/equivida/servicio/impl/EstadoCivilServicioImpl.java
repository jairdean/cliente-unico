package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.EstadoCivilDao;
import com.equivida.modelo.EstadoCivil;
import com.equivida.servicio.EstadoCivilServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "EstadoCivilServicio")
public class EstadoCivilServicioImpl extends
		GenericServiceImpl<EstadoCivil, Short> implements EstadoCivilServicio {

	@EJB
	private EstadoCivilDao estadoCivilDao;

	public GenericDao<EstadoCivil, Short> getDao() {
		return estadoCivilDao;
	}
}
