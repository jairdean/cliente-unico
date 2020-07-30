package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.DeporteDao;
import com.equivida.modelo.Deporte;
import com.equivida.servicio.DeporteServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "DeporteServicio")
public class DeporteServicioImpl extends GenericServiceImpl<Deporte, Short>
		implements DeporteServicio {

	@EJB
	private DeporteDao deporteDao;

	public GenericDao<Deporte, Short> getDao() {
		return deporteDao;
	}
}
