package com.equivida.ws.producer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.dto.RespuestaGeneralDto;
import com.equivida.modelo.PersonaNatural;
import com.equivida.servicio.ConsultaGeneralCUServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.smartdata.exception.SmartdataException;
import com.equivida.smartdata.servicio.SmartDataSdServicio;

/**
 * Clase que implementa a: {@link ConsultaGenericaServicio}
 * 
 * @see com.equivida.ws.producer.ConsultaGenericaServicio
 * 
 * @author juan
 *
 */
@WebService
@Stateless(name = "ConsultaGenericaServicio")
public class ConsultaGenericaServicioImpl implements ConsultaGenericaServicio {
	private Logger log = Logger.getLogger(ConsultaGenericaServicioImpl.class.toString());

	@EJB(mappedName = "SmartDataSdServicio/local")
	private SmartDataSdServicio smartDataSdServicio;
	@EJB(mappedName = "PersonaNaturalServicio/local")
	private PersonaNaturalServicio personaNaturalClienteUnicoServicio;
	@EJB(mappedName = "ConsultaGeneralCUServicio/local")
	private ConsultaGeneralCUServicio consultaGeneralCUServicio;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.ws.producer.ConsultaGenericaServicio#
	 * consultaPorDocumentoClienteUnico(java.lang.String)
	 */
	@Override
	@WebMethod
	public @WebResult(name = "respuesta") com.equivida.modelo.PersonaNatural consultaPorDocumentoClienteUnico(
			@WebParam(name = "noDocumento") String noDocumento) {

		com.equivida.modelo.PersonaNatural pn = personaNaturalClienteUnicoServicio
				.obtenerPersonaNaturalByDocumento(noDocumento, true);

		if (pn != null) {
			return pn;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.ws.producer.ConsultaGenericaServicio#
	 * consultaPorDocumentoSmartdata(java.lang.String)
	 */
	@Override
	@WebMethod
	public @WebResult(name = "respuesta") com.equivida.smartdata.model.PersonaSd consultaPorDocumentoSmartdata(
			@WebParam(name = "noDocumento") String noDocumento) {
		try {
			return smartDataSdServicio.consultaClienteSmartData(noDocumento, true);
		} catch (SmartdataException e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.ws.producer.ConsultaGenericaServicio#
	 * consultaPorDocumentoDataBook(java.lang.String)
	 */
	@Override
	@WebMethod
	public @WebResult(name = "respuesta") com.equivida.smartdata.model.PersonaSd consultaPorDocumentoDataBook(
			@WebParam(name = "noDocumento") String noDocumento) {
		try {
			// TODO: Revisar que usuario se puede enviar.
			return smartDataSdServicio.consultaDatabook(noDocumento, "WS");
		} catch (SmartdataException e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.ws.producer.ConsultaGenericaServicio#consultaEnriquecida
	 * (java.lang.String, java.lang.String)
	 */
	@WebMethod
	@Override
	public @WebResult(name = "respuesta") PersonaNatural consultaEnriquecida(
			@WebParam(name = "noDocumento") String noDocumento, @WebParam(name = "usuario") String usuario) {
		// TODO preguntar o definir si aca tambien viene la logica de buscar personas
		// natural con
		// ruc dsitinto el 3er digito a 6 y 9
		RespuestaGeneralDto resp = consultaGeneralCUServicio.consultaGenerica(noDocumento, usuario, null);
		PersonaNatural pn = resp.getPersonaEncontradaUnica();

		if (pn.getSecPersonaNatural() == null) {
			pn.setSecPersonaNatural(1);
		}

		if (pn.getPersona().getSecPersona() == null) {
			pn.getPersona().setSecPersona(1);
		}

		return pn;
	}
}
