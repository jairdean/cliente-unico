package com.equivida.servicio.impl;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.rpc.ServiceException;

import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.exception.NoEncuentraDatosException;
import com.equivida.modelo.PersonaNatural;
import com.equivida.servicio.PersistenciaGeneralServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.exception.SmartdataException;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.servicio.ParroquiaSdServicio;
import com.equivida.smartdata.servicio.PersonaSdServicio;
import com.equivida.smartdata.servicio.SmartDataSdServicio;
import com.equivida.util.TransformCuSdUtil;

/**
 * Persistencia General de CU.
 * 
 * @author juan
 *
 */
@Stateless(name = "PersistenciaGeneralServicio")
public class PersistenciaGeneralServicioImpl implements
		PersistenciaGeneralServicio {

	@EJB
	private PersonaNaturalServicio personaNaturalServicio;
	@EJB(mappedName = "SmartDataSdServicio/local")
	private SmartDataSdServicio smartDataSdServicio;
	@EJB(mappedName = "ParroquiaSdServicio/local")
	private ParroquiaSdServicio parroquiaSdServicio;
	@EJB(mappedName = "PersonaSdServicio/local")
	private PersonaSdServicio personaSdServicio;

	@Override
	public void persistirGeneral(PersonaNatural personaNatural, String usuario,
			String ip) throws RemoteException, EmpleoDependienteException,
			EmpleoIndependienteException, ServiceException,
			ErrorIngresoWsSiseException {

		// Se guarda en Cliente unico
		personaNaturalServicio.crearPersonaNaturalFormularioWeb(personaNatural,
				usuario, ip);

		// Se persiste en SmartData
		try {
			if (!personaNatural.getTipoIdentificacion().isPasaporte()) {
				persistirSmartData(personaNatural, usuario);
			}
		} catch (SmartdataException e) {
			// TODO: se debe mostrar en la pantalla que nos e guardó en
			// SmartData
			e.printStackTrace();
		}
	}

	/**
	 * TRansforma los datos de Persona natural de Cliente Unico y los persiste
	 * en Smart Data.
	 * 
	 * @param personaNatural
	 * @throws SmartdataException
	 */
	private void persistirSmartData(PersonaNatural personaNatural,
			String usuario) throws SmartdataException {
		PersonaSd personaSd;

		boolean nueva = true;
		try {
			// Se consulta en SmarData.
			personaSd = smartDataSdServicio.consultaClienteSmartData(
					personaNatural.getIdentificacion(), false);
			nueva = false;
		} catch (SmartdataException e) {
			personaSd = new PersonaSd();
		}

		try {

			// Cuando no existe en smart data
			if (nueva) {
				// Se Transforma los datos de Cliente Unico a Smart Data
				TransformCuSdUtil util = new TransformCuSdUtil(personaNatural,
						personaSd, usuario, parroquiaSdServicio,
						personaSdServicio);
				util.transformar();

				// Se persiste en SmartData
				smartDataSdServicio.guardaEnSmartData(util.getPersonaSd());
			} else {
				DatosActualizaSdDto datosActualiza = new DatosActualizaSdDto();

				datosActualiza
						.setSecPersonaActualiza(personaSd.getSecPersona());
				datosActualiza.setIdentificacion(personaNatural
						.getIdentificacion());
				datosActualiza
						.setPrimerNombre(personaNatural.getPrimerNombre());
				datosActualiza.setSegundoNombre(personaNatural
						.getSegundoNombre());
				datosActualiza.setApellidoMaterno(personaNatural
						.getApellidoMaterno());
				datosActualiza.setApellidoPaterno(personaNatural
						.getApellidoPaterno());
				datosActualiza.setSexo(personaNatural.getSexo());
				datosActualiza.setFchNacimiento(personaNatural
						.getFchNacimiento());
				datosActualiza.setFchFallecimiento(personaNatural
						.getFchFallecimiento());
				datosActualiza.setUsrProcesa(usuario);
				datosActualiza.setCodEstadoCivil(personaNatural
						.getEstadoCivil().getCodEstadoCivil());
				datosActualiza.setIdentificacionConyuge(personaNatural
						.getConyuge().getIdentificacion());

				if (personaNatural.getPersona()
						.getTelefonoSinDireccionActivosCollection() != null
						&& !personaNatural.getPersona()
								.getTelefonoSinDireccionActivosCollection()
								.isEmpty()) {
					TransformCuSdUtil transformador = new TransformCuSdUtil(
							personaNatural, personaSd, usuario,
							parroquiaSdServicio, personaSdServicio);

					datosActualiza
							.setTelefonoList(transformador.getTelefonos());
					datosActualiza.setDireccionList(transformador
							.getDirecciones());
				}

				// Realizar el proceso de actualizacion de los campos que se
				// muestran en el popUp
				smartDataSdServicio
						.actualizaDatosPersonaNatural(datosActualiza);
			}
		} catch (NoEncuentraDatosException e) {
			throw new SmartdataException(e.getMessage());
		}
	}
}
