package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.CiudadDao;
import com.equivida.modelo.Ciudad;
import com.equivida.servicio.CiudadServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "CiudadServicio")
public class CiudadServicioImpl extends GenericServiceImpl<Ciudad, Short>
		implements CiudadServicio {

	@EJB
	private CiudadDao ciudadDao;

	public GenericDao<Ciudad, Short> getDao() {
		return ciudadDao;
	}
}
