package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.CantonDao;
import com.equivida.modelo.Canton;
import com.equivida.servicio.CantonServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "CantonServicio")
public class CantonServicioImpl extends GenericServiceImpl<Canton, Short>
		implements CantonServicio {

	@EJB
	private CantonDao cantonDao;

	public GenericDao<Canton, Short> getDao() {
		return cantonDao;
	}
}
