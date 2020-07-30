package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.PaisIsoDao;
import com.equivida.modelo.PaisIso;
import com.equivida.servicio.PaisIsoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PaisIsoServicio")
public class PaisIsoServicioImpl extends GenericServiceImpl<PaisIso, Short>
		implements PaisIsoServicio {

	@EJB
	private PaisIsoDao paisIsoDao;

	public GenericDao<PaisIso, Short> getDao() {
		return paisIsoDao;
	}
}
