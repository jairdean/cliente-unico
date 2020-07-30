package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.DetalleHabitoEnfermedadDao;
import com.equivida.modelo.DetalleHabitoEnfermedad;
import com.equivida.servicio.DetalleHabitoEnfermedadServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "DetalleHabitoEnfermedadServicio")
public class DetalleHabitoEnfermedadServicioImpl extends
		GenericServiceImpl<DetalleHabitoEnfermedad, Integer> implements
		DetalleHabitoEnfermedadServicio {

	@EJB
	private DetalleHabitoEnfermedadDao detalleHabitoEnfermedadDao;

	public GenericDao<DetalleHabitoEnfermedad, Integer> getDao() {
		return detalleHabitoEnfermedadDao;
	}
}
