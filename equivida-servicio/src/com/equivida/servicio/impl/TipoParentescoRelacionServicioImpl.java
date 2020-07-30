package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoParentescoRelacionDao;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.servicio.TipoParentescoRelacionServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoParentescoRelacionServicio")
public class TipoParentescoRelacionServicioImpl extends
		GenericServiceImpl<TipoParentescoRelacion, Short> implements
		TipoParentescoRelacionServicio {

	@EJB
	private TipoParentescoRelacionDao tipoParentescoRelacionDao;

	public GenericDao<TipoParentescoRelacion, Short> getDao() {
		return tipoParentescoRelacionDao;
	}

	@Override
	public List<TipoParentescoRelacion> obtenerPorIds(short[] ids) {
		return tipoParentescoRelacionDao.obtenerPorIds(ids);
	}
}
