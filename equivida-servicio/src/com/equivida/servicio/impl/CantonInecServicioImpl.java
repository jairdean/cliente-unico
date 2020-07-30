package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.CantonInecDao;
import com.equivida.modelo.CantonInec;
import com.equivida.servicio.CantonInecServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "CantonInecServicio")
public class CantonInecServicioImpl extends
		GenericServiceImpl<CantonInec, Short> implements CantonInecServicio {

	@EJB
	private CantonInecDao cantonInecDao;

	public GenericDao<CantonInec, Short> getDao() {
		return cantonInecDao;
	}
}
