package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ApoderadoDao;
import com.equivida.modelo.Apoderado;
import com.equivida.servicio.ApoderadoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ApoderadoServicio")
public class ApoderadoServicioImpl extends
		GenericServiceImpl<Apoderado, Integer> implements ApoderadoServicio {

	@EJB
	private ApoderadoDao apoderadoDao;

	public GenericDao<Apoderado, Integer> getDao() {
		return apoderadoDao;
	}
}
