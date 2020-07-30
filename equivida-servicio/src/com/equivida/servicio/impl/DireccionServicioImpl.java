package com.equivida.servicio.impl;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.DireccionDao;
import com.equivida.modelo.Direccion;
import com.equivida.servicio.DireccionServicio;
import com.equivida.servicio.DireccionTelefonoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "DireccionServicio")
public class DireccionServicioImpl extends GenericServiceImpl<Direccion, Integer> implements DireccionServicio {

	@EJB
	private DireccionDao direccionDao;
	@EJB
	private DireccionTelefonoServicio direccionTelefonoServicio;

	public GenericDao<Direccion, Integer> getDao() {
		return direccionDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.servicio.DireccionServicio#persistir(com.equivida.modelo.
	 * Direccion)
	 */
	@Override
	public void persistir(Direccion direccion) {
		if (direccion.getSecDireccion() == null) {
			create(direccion);
		} else {
			update(direccion);
		}

		// Se guardan los telefonos de la direccion.
		direccionTelefonoServicio.guardarLista(direccion.getDireccionTelefonoCollection());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.servicio.DireccionServicio#persistir(java.util.Collection)
	 */
	@Override
	public void persistir(Collection<Direccion> direccionList) {
		if (direccionList != null && !direccionList.isEmpty()) {
			for (Direccion d : direccionList) {
				persistir(d);
			}
		}
	}
}
