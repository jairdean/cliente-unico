package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoIdentificacionDao;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.servicio.TipoIdentificacionServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoIdentificacionServicio")
public class TipoIdentificacionServicioImpl extends
		GenericServiceImpl<TipoIdentificacion, Character> implements
		TipoIdentificacionServicio {

	@EJB
	private TipoIdentificacionDao tipoHabitoEnfermedadDao;

	public GenericDao<TipoIdentificacion, Character> getDao() {
		return tipoHabitoEnfermedadDao;
	}
}
