package com.equivida.servicio.impl;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.PreguntaHabitoEnfermedadDao;
import com.equivida.modelo.PreguntaHabitoEnfermedad;
import com.equivida.servicio.PreguntaHabitoEnfermedadServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PreguntaHabitoEnfermedadServicio")
public class PreguntaHabitoEnfermedadServicioImpl extends GenericServiceImpl<PreguntaHabitoEnfermedad, Integer>
		implements PreguntaHabitoEnfermedadServicio {

	@EJB
	private PreguntaHabitoEnfermedadDao preguntaHabitoEnfermedadDao;

	public GenericDao<PreguntaHabitoEnfermedad, Integer> getDao() {
		return preguntaHabitoEnfermedadDao;
	}

	@Override
	public Collection<PreguntaHabitoEnfermedad> buscarPorCategoria(short codTipoHabito, String[] categorias) {
		return preguntaHabitoEnfermedadDao.buscarPorCategoria(codTipoHabito, categorias);
	}
}
