/**
 *TelefonoservicioImpl.java
 *
 *Tue Jan 26 12:46:16 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TelefonoSdDao;
import com.equivida.smartdata.model.TelefonoSd;
import com.equivida.smartdata.servicio.TelefonoSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "TelefonoSdServicio")
public class TelefonoSdServicioImpl extends
		GenericServiceImpl<TelefonoSd, Integer> implements TelefonoSdServicio {

	@EJB
	private TelefonoSdDao telefonoDao;

	@Override
	public GenericDao<TelefonoSd, Integer> getDao() {
		return telefonoDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.servicio.TelefonoSdServicio#actualizaTelefonos
	 * (java.lang.Integer, java.util.List)
	 */
	@Override
	public void actualizaTelefonos(Integer secPersona,
			List<TelefonoSd> telefonosList) {
		// 1. Se cambia todos los telefonos de la persona a estado INACTIVO
		telefonoDao.desactivarTelefonsosPersona(secPersona);

		// 2. Se insertan los nuevos telefonos
		if (telefonosList != null && !telefonosList.isEmpty()) {
			for (TelefonoSd t : telefonosList) {
				telefonoDao.save(t);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.TelefonoSdServicio#
	 * obtenerPorDocumentoTelefono(java.lang.String, java.lang.String)
	 */
	@Override
	public TelefonoSd obtenerPorDocumentoTelefono(String noDocumento,
			String telefono) {
		return telefonoDao.obtenerPorDocumentoTelefono(noDocumento, telefono);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.TelefonoSdServicio#
	 * ingresarTelefono(TelefonoSd)
	 */
	@Override
	public void ingresarTelefono(TelefonoSd telefono) {
		 telefonoDao.save(telefono);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.TelefonoSdServicio#
	 * obtenerTelefonoByPersonaSecPersona(secPersona)
	 */
	@Override
	public List<TelefonoSd> obtenerTelefonoByPersonaSecPersona(Integer secPersona) {
		return telefonoDao.obtenerTelefonoByPersonaSecPersona(secPersona);
	}

	
	@Override	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.TelefonoSdServicio#
	 * obtenerTelefonoBySecPersonaAndNroTeefono(secPersona, nroTelefono, codArea)
	 */
	public TelefonoSd obtenerTelefonoBySecPersonaAndNroTeefono(Integer secPersona, String nroTelefono, String codArea) {
		return telefonoDao.obtenerTelefonoBySecPersonaAndNroTeefono(secPersona, nroTelefono, codArea);
	}
}
