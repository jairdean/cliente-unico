package com.equivida.servicio.impl;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoHabitoEnfermedadDao;
import com.equivida.modelo.TipoHabitoEnfermedad;
import com.equivida.servicio.TipoHabitoEnfermedadServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoHabitoEnfermedadServicio")
public class TipoHabitoEnfermedadServicioImpl extends GenericServiceImpl<TipoHabitoEnfermedad, Short>
		implements TipoHabitoEnfermedadServicio {

	@EJB
	private TipoHabitoEnfermedadDao tipoHabitoEnfermedadDao;

	public GenericDao<TipoHabitoEnfermedad, Short> getDao() {
		return tipoHabitoEnfermedadDao;
	}

	@Override
	public Collection<TipoHabitoEnfermedad> buscarPorCategoria(String[] categorias) {
		return tipoHabitoEnfermedadDao.buscarPorCategoria(categorias);
	}
}
