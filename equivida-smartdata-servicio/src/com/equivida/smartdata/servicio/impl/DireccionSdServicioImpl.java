/**
 *DireccionservicioImpl.java
 *
 *Tue Jan 26 12:34:07 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.DireccionSdDao;
import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.servicio.DireccionSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "DireccionSdServicio")
public class DireccionSdServicioImpl extends GenericServiceImpl<DireccionSd, Integer> implements DireccionSdServicio {

	@EJB
	private DireccionSdDao direccionDao;

	@Override
	public GenericDao<DireccionSd, Integer> getDao() {
		return direccionDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.DireccionSdServicio#actualizaDirecciones
	 * (java.lang.Integer, java.util.List)
	 */
	@Override
	public void actualizaDirecciones(Integer secPersona, List<DireccionSd> direccionList) {
		// 1. Se inactivan las direcciones actuales.
		direccionDao.desactivarDireccionesPersona(secPersona);

		// 2. Se crean las direcciones
		if (direccionList != null && !direccionList.isEmpty()) {
			for (DireccionSd d : direccionList) {
				if (d.getSecDireccion() != null) {
					direccionDao.save(d);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.DireccionSdServicio#
	 * obtenerPorDocumentoDireccion(java.lang.String, java.lang.String)
	 */
	@Override
	public DireccionSd obtenerPorDocumentoDireccion(String noDocumento, String direccion) {
		return direccionDao.obtenerPorDocumentoDireccion(noDocumento, direccion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.DireccionSdServicio#
	 * ingresarDireccion(DireccionSd)
	 */
	@Override
	public void ingresarDireccion(DireccionSd direccion) {
		direccionDao.save(direccion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.DireccionSdServicio#
	 * obtenerDireccionByPersonaSecPersona(DireccionSd)
	 */
	@Override
	public List<DireccionSd> obtenerDireccionByPersonaSecPersona(int secPersona) {
		return direccionDao.obtenerDireccionByPersonaSecPersona(secPersona);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.DireccionSdServicio#
	 * obtenerDireccionBySecPersonaAndDireccion(int secPersona, String direccion)
	 */
	@Override
	public DireccionSd obtenerDireccionBySecPersonaAndDireccion(int secPersona, String direccion) {
		return direccionDao.obtenerDireccionBySecPersonaAndDireccion(secPersona, direccion);
	}
}
