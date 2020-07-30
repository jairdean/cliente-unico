package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoOtraOcupacionDao;
import com.equivida.modelo.TipoOtraOcupacion;
import com.equivida.servicio.TipoOtraOcupacionServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoOtraOcupacionServicio")
public class TipoOtraOcupacionServicioImpl extends
		GenericServiceImpl<TipoOtraOcupacion, Short> implements
		TipoOtraOcupacionServicio {

	@EJB
	private TipoOtraOcupacionDao tipoOtraOcupacionDao;

	public GenericDao<TipoOtraOcupacion, Short> getDao() {
		return tipoOtraOcupacionDao;
	}
}
