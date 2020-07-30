package com.equivida.servicio.impl;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TelefonoDao;
import com.equivida.modelo.Telefono;
import com.equivida.servicio.TelefonoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TelefonoServicio")
public class TelefonoServicioImpl extends GenericServiceImpl<Telefono, Integer> implements TelefonoServicio {

	@EJB
	private TelefonoDao telefonoDao;

	public GenericDao<Telefono, Integer> getDao() {
		return telefonoDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.servicio.TelefonoServicio#persistir(com.equivida.modelo.
	 * Telefono)
	 */
	@Override
	public void persistir(Telefono telefono) {
		if (telefono.getSecTelefono() == null) {
			create(telefono);
		} else {
			update(telefono);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.servicio.TelefonoServicio#persistir(java.util.Collection)
	 */
	@Override
	public void persistir(Collection<Telefono> telefonoCol) {
		if (telefonoCol != null && !telefonoCol.isEmpty()) {
			for (Telefono t : telefonoCol) {
				persistir(t);
			}
		}
	}
}
