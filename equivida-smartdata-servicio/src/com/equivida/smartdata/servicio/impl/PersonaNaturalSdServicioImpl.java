/**
 *PersonaNaturalservicioImpl.java
 *
 *Tue Jan 26 12:42:07 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import com.equivida.smartdata.dao.PersonaNaturalSdDao;
import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.helper.SmartDataHelper;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.equivida.smartdata.model.RelacionSd;
import com.equivida.smartdata.model.TelefonoSd;
import com.equivida.smartdata.servicio.PersonaNaturalSdServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

@Stateless(name = "PersonaNaturalSdServicio")
public class PersonaNaturalSdServicioImpl extends GenericServiceImpl<PersonaNaturalSd, Integer>
		implements PersonaNaturalSdServicio {

	private Logger log = Logger.getLogger(SmartDataSdServicioImpl.class);

	@EJB
	private PersonaNaturalSdDao personaNaturalDao;

	@Override
	public GenericDao<PersonaNaturalSd, Integer> getDao() {
		return personaNaturalDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.PersonaNaturalSdServicio#
	 * obtenerPersonaByIdentificacion(java.lang.String, boolean)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersonaNaturalSd obtenerPersonaByIdentificacion(String noDocumento, boolean conRelaciones) {
		PersonaNaturalSd pn = personaNaturalDao.obtenerPersonaByIdentificacion(noDocumento);

		if (pn != null) {
			if (conRelaciones) {
				System.out.println("--------------------------------RELACIONES SD 1..");
				if (pn.getEmpleoDependienteList() != null) {
					pn.getEmpleoDependienteList().size();
				}

				if (pn.getInformacionAdicionalList() != null) {
					pn.getInformacionAdicionalList().size();
				}

				if (pn.getSecPersona() != null && pn.getSecPersona().getDireccionList() != null) {
					pn.getSecPersona().getDireccionList().size();
				}

				if (pn.getSecPersona() != null && pn.getSecPersona().getTelefonoList() != null) {
					pn.getSecPersona().getTelefonoList().size();

					for (TelefonoSd t : pn.getSecPersona().getTelefonoList()) {
						if (t.getDireccionTelefonoList() != null) {
							t.getDireccionTelefonoList().size();
						}
					}
				}

				// Se cara las direcciones telefono en direccion
				SmartDataHelper.cargarDireccionTelefonoAdireccion(pn.getSecPersona().getTelefonoList(),
						pn.getSecPersona().getDireccionList());

				if (pn.getSecPersona() != null && pn.getSecPersona().getVehiculoList() != null) {
					pn.getSecPersona().getVehiculoList().size();
				}

				if (pn.getSecPersona() != null && pn.getSecPersona().getRelaciones() != null) {
					pn.getSecPersona().getRelaciones().size();
					for (RelacionSd r : pn.getSecPersona().getRelaciones()) {
						r.getTipoParentesco().getDetalleRelacionList().size();
					}
				}

				if (pn.getSecPersona() != null && pn.getSecPersona().getRelacionesSinBase() != null) {
					pn.getSecPersona().getRelacionesSinBase().size();
				}

				System.out.println("--------------------------------RELACIONES SD 2");
			}
		}

		return pn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.PersonaNaturalSdServicio#
	 * actualizaDatosPersonales(com.equivida.smartdata.dto.DatosActualizaSdDto)
	 */
	@Override
	public void actualizaDatosPersonales(DatosActualizaSdDto datosActualiza) {
		personaNaturalDao.actualizaDatosPersonales(datosActualiza);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.PersonaNaturalSdServicio#
	 * insertarPersonaNatural(com.equivida.smartdata.dto.DatosActualizaSdDto)
	 */
	@Override
	public void insertarPersonaNatural(PersonaNaturalSd personaNatural) {
		personaNaturalDao.save(personaNatural);
	}
}
