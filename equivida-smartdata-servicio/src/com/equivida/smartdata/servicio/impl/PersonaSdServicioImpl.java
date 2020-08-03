/**
 *PersonaservicioImpl.java
 *
 *Tue Jan 26 12:39:40 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import com.equivida.smartdata.dao.PersonaSdDao;
import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.helper.SmartDataHelper;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.model.RelacionSd;
import com.equivida.smartdata.model.TelefonoSd;
import com.equivida.smartdata.servicio.PersonaSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "PersonaSdServicio")
public class PersonaSdServicioImpl extends GenericServiceImpl<PersonaSd, Integer> implements PersonaSdServicio {

	private Logger log = Logger.getLogger(SmartDataSdServicioImpl.class);

	@EJB
	private PersonaSdDao personaDao;

	@Override
	public GenericDao<PersonaSd, Integer> getDao() {
		return personaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.PersonaServicio#
	 * obtenerPersonaByIdentificacion(java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersonaSd obtenerPersonaByIdentificacion(String noDocumento) {
		// 1. Se consulta persona por identificacion
		PersonaSd resp = personaDao.obtenerPersonaByIdentificacion(noDocumento);

		// 2. inicializa valores si la respuesta es distinta de null
		if (resp != null) {
			if (resp.getDireccionList() != null) {
				resp.getDireccionList().size();
			}

			if (resp.getTelefonoList() != null) {
				resp.getTelefonoList().size();

				for (TelefonoSd t : resp.getTelefonoList()) {
					if (t.getDireccionTelefonoList() != null) {
						t.getDireccionTelefonoList().size();
					}
				}
			}

			// Se cara las direcciones telefono en direccion
			SmartDataHelper.cargarDireccionTelefonoAdireccion(resp.getTelefonoList(), resp.getDireccionList());

			if (resp.getVehiculoList() != null) {
				resp.getVehiculoList().size();
			}

			if (resp.getRelaciones() != null) {
				resp.getRelaciones().size();
				for (RelacionSd r : resp.getRelaciones()) {
					r.getTipoParentesco().getDetalleRelacionList().size();
				}
			}

			if (resp.getRelacionesSinBase() != null) {
				resp.getRelacionesSinBase().size();
			}
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.servicio.PersonaSdServicio#actualizaDatosPersonales
	 * (com.equivida.smartdata.dto.DatosActualizaSdDto)
	 */
	@Override
	public void actualizaDatosPersonales(DatosActualizaSdDto datosActualiza) {
		personaDao.actualizaDatosPersonales(datosActualiza);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.PersonaSdServicio#IngresarDatosPersona
	 * (com.equivida.smartdata.dto.DatosActualizaSdDto)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean IngresarDatosPersona(PersonaSd persona) {
		// 1. Se consulta persona por identificacion
		log.error("Entra IngresarDatosPersona");
		PersonaSd existePersona = personaDao.obtenerPersonaByIdentificacion(persona.getIdentificacion());

		log.error(existePersona);

		if (existePersona == null) {
			// INGRESO LA PERSONA
			personaDao.ingresaPersona(persona);
		}

		return true;
	}
}
