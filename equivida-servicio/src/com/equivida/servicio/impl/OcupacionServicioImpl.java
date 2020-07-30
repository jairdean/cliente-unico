package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.OcupacionDao;
import com.equivida.modelo.Ocupacion;
import com.equivida.servicio.OcupacionServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "OcupacionServicio")
public class OcupacionServicioImpl extends GenericServiceImpl<Ocupacion, Short>
		implements OcupacionServicio {

	@EJB
	private OcupacionDao OcupacionDao;

	public GenericDao<Ocupacion, Short> getDao() {
		return OcupacionDao;
	}
}
