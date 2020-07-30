package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ProfesionDao;
import com.equivida.modelo.Profesion;
import com.equivida.servicio.ProfesionServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ProfesionServicio")
public class ProfesionServicioImpl extends GenericServiceImpl<Profesion, Short>
		implements ProfesionServicio {

	@EJB
	private ProfesionDao profesionDao;

	public GenericDao<Profesion, Short> getDao() {
		return profesionDao;
	}
}
