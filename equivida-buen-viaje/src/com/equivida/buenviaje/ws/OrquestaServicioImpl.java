/**
 * 
 */
package com.equivida.buenviaje.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.tempuri.WebService1Locator;
import org.tempuri.WebService1Soap;

import com.equivida.buenviaje.constante.TipoDocumentoEnum;
import com.equivida.buenviaje.constante.TipoPersonaConsultaEnum;
import com.equivida.buenviaje.dto.ConsultaIn;
import com.equivida.buenviaje.dto.ConsultaOut;
import com.equivida.buenviaje.dto.PersonaConsulta;
import com.equivida.buenviaje.dto.PersonaRespuesta;
import com.equivida.buenviaje.exception.PersitenciaException;
import com.equivida.buenviaje.exception.RequeridoException;
import com.equivida.buenviaje.util.Comparador;
import com.equivida.buenviaje.util.TransformaHelper;
import com.equivida.constant.LugarEncuentraInfoEnum;
import com.equivida.dto.RespuestaGeneralDto;
import com.equivida.dto.RespuestaSiseDto;
import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.servicio.ConsultaGeneralCUServicio;
import com.equivida.servicio.PersonaJuridicaServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.RucPersonaNaturalServicio;
import com.equivida.servicio.SiseServicio;
import com.equivida.smartdata.exception.SmartdataException;
import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.equivida.smartdata.servicio.PersonaJuridicaSdServicio;
import com.equivida.util.Parametros;

/**
 * @author juan
 *
 */
@Stateless(name = "OrquestaServicio")
public class OrquestaServicioImpl implements OrquestaServicio {

	private static final Logger LOG = Logger.getLogger(OrquestaServicioImpl.class.getName());

	@EJB(mappedName = "ConsultaGeneralCUServicio/local")
	private ConsultaGeneralCUServicio consultaGeneralCUServicio;

	@EJB(mappedName = "SiseServicio/local")
	private SiseServicio siseServicio;

	@EJB(mappedName = "PersonaNaturalServicio/local")
	private PersonaNaturalServicio personaNaturalServicio;

	@EJB(mappedName = "PersonaJuridicaServicio/local")
	private PersonaJuridicaServicio personaJuridicaServicio;

	@EJB(mappedName = "PersonaJuridicaSdServicio/local")
	private PersonaJuridicaSdServicio personaJuridicaSdServicio;

	@EJB
	private LlamadoRcsAsyncServicio llamadoRcsAsyncServicio;

	@EJB(mappedName = "RucPersonaNaturalServicio/local")
	private RucPersonaNaturalServicio rucPersonaNaturalServicio;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.buenviaje.ws.OrquestaServicio#procesarPeticion(com.equivida
	 * .buenviaje.dto.ConsultaIn)
	 */
	@Override
	public ConsultaOut procesarPeticion(ConsultaIn datosConsulta) {
		LOG.info("ingresar a procesar peticion:");
		LOG.info(datosConsulta.toString());

		ConsultaOut resp = new ConsultaOut();

		try {
			// Se llama al consumo de servicios de RCS. de manera asyncronica
			llamadoRcsAsyncServicio.llamarRcs(datosConsulta);

			// Se valida los datos de entrada del servicio
			validaDatosEntrada(datosConsulta);

			List<PersonaRespuesta> lista = new ArrayList<PersonaRespuesta>();

			// Se realiza consulta en base a la lista de no repetidos
			for (PersonaConsulta pc : datosConsulta.getPersonaConsultaListaNoRepetidos()) {

				LOG.info("PERSONA CONSULTA \n +++++++++++++++++++++++++++ " + pc + "\n +++++++++++++++++++++++++++++");
				// Se construye persona respuesta
				PersonaRespuesta personaResp = new PersonaRespuesta(procesaUnaPersona(pc), "", pc.getNoDocumento());

				// Se consulta codigo de asegurado
				if (personaResp.getIdPersona() != null) {
					personaResp.setCodigoAsegurado(getCodigoAsegurado(personaResp.getIdPersona()));
				}

				lista.add(personaResp);
			}

			resp.setPersonaRespuestaLista(lista);
			resp.setError("N");
		} catch (SmartdataException e) {
			resp.setError("S");
			resp.setMensaje(e.getMessage());
		} catch (RequeridoException e) {
			resp.setError("S");
			resp.setMensaje(e.getMessage());
		} catch (PersitenciaException e) {
			resp.setError("S");
			resp.setMensaje(e.getMessage());
		}

		return resp;
	}

	/**
	 * Valida datos requeridos de la petición.
	 * 
	 * @throws RequeridoException
	 */
	private void validaDatosEntrada(ConsultaIn datosConsulta) throws RequeridoException {
		// Verifica que haya al menos un rol de cada tipo (Solicitante,
		// Asegurado, Facturacion)
		int haySolicitante = 0;
		int hayAsegurado = 0;
		int hayFactura = 0;

		for (PersonaConsulta pc : datosConsulta.getPersonaConsultaLista()) {
			if (TipoPersonaConsultaEnum.SOLICITANTE.equals(pc.getTipoPersonaConsulta())) {
				haySolicitante++;
			}
			if (TipoPersonaConsultaEnum.ASEGURADO.equals(pc.getTipoPersonaConsulta())) {
				hayAsegurado++;
			}
			if (TipoPersonaConsultaEnum.FACTURA.equals(pc.getTipoPersonaConsulta())) {
				hayFactura++;
			}
		}

		if (haySolicitante > 0 && hayAsegurado > 0 && hayFactura > 0) {

			if (haySolicitante > 1) {
				throw new RequeridoException("La peticion debe contemplar solo UN (1) solicitante");
			}

			if (hayFactura > 1) {
				throw new RequeridoException("La peticion debe contemplar solo UN (1) facturado");
			}

			// Se valida que los datos cumpla los requeridos
			for (PersonaConsulta pc : datosConsulta.getPersonaConsultaLista()) {
				validaCamposRequeridos(pc);
			}
		} else {
			throw new RequeridoException("La peticion debe contemplar datos de: Solicitante, Asegurado, Facturación");
		}
	}

	/**
	 * Valida los campos de cada petición.
	 * 
	 * @param pc
	 * @throws RequeridoException
	 */
	private void validaCamposRequeridos(PersonaConsulta pc) throws RequeridoException {
		boolean error = false;
		StringBuffer mensaje = new StringBuffer(400);

		// Datos que se validan para los 3 roles

		if (pc.getTipoDocumento() == null) {
			error = true;
			mensaje.append("Tipo de Documento, ");
		}

		if (isStrinEmpty(pc.getNoDocumento())) {
			error = true;
			mensaje.append("No. de Documento, ");
		}

		if (isStrinEmpty(pc.getPrimerApellido())) {
			error = true;
			mensaje.append("Primer Apellido, ");
		}

		if (isStrinEmpty(pc.getPrimerNombre())) {
			error = true;
			mensaje.append("Primer Nombre, ");
		}

		// Datos que se validan solo para Solicitante y Asegurado
		if (TipoPersonaConsultaEnum.SOLICITANTE.equals(pc.getTipoPersonaConsulta())
				|| TipoPersonaConsultaEnum.ASEGURADO.equals(pc.getTipoPersonaConsulta())) {
			if (pc.getGenero() == null) {
				error = true;
				mensaje.append("Genero, ");
			}

			if (pc.getFechaNacimiento() == null) {
				error = true;
				mensaje.append("Fecha de nacimiento, ");
			}
		}

		// Validacion campos requeridos de datos de facturacion
		if (TipoPersonaConsultaEnum.FACTURA.equals(pc.getTipoPersonaConsulta())) {
			if (pc.getTipoDePersona() == null) {
				error = true;
				mensaje.append("Tipo de persona, ");
			}

			if (isStrinEmpty(pc.getRazonSocial())) {
				error = true;
				mensaje.append("Razon Social, ");
			}

			if (isStrinEmpty(pc.getCallePrincipal())) {
				error = true;
				mensaje.append("Calle Principal, ");
			}

			if (isStrinEmpty(pc.getNumeroDireccion())) {
				error = true;
				mensaje.append("Numero Direccion, ");
			}

			if (isStrinEmpty(pc.getCalleSecundaria())) {
				error = true;
				mensaje.append("Calle Secundaria, ");
			}

			if (isStrinEmpty(pc.getPais())) {
				error = true;
				mensaje.append("Pais, ");
			}

			if (isStrinEmpty(pc.getCodigoPais())) {
				error = true;
				mensaje.append("Cod. Pais, ");
			}

			if (isStrinEmpty(pc.getCiudad())) {
				error = true;
				mensaje.append("Ciudad, ");
			}

			if (isStrinEmpty(pc.getTelefono())) {
				error = true;
				mensaje.append("Telefono, ");
			}

			if (isStrinEmpty(pc.getCorreoElectronico())) {
				error = true;
				mensaje.append("Correo Electronico, ");
			}
		}

		// Datos que se validan solo para Solicitante y Facturacion
		if (TipoPersonaConsultaEnum.SOLICITANTE.equals(pc.getTipoPersonaConsulta())
				|| TipoPersonaConsultaEnum.FACTURA.equals(pc.getTipoPersonaConsulta())) {
			if (isStrinEmpty(pc.getCodigoPais())) {
				error = true;
				mensaje.append("Codigo Pais, ");
			}
		}

		// Si hay errores, se lanza la excepcion
		if (error) {
			throw new RequeridoException(
					"Campos requeridos: ".concat(pc.getNoDocumento() != null ? pc.getNoDocumento() : "").concat(" ")
							.concat(mensaje.toString()));
		}
	}

	/**
	 * VErifica si una String esta vacio o nulo.
	 * 
	 * @param valor
	 * @return
	 */
	private boolean isStrinEmpty(String valor) {
		if (valor != null && valor.trim().length() > 0) {
			return false;
		}

		return true;
	}

	/**
	 * Procesa los datos de cada persona.
	 * 
	 * @param pc
	 * @return
	 * @throws SmartdataException
	 * @throws PersitenciaException
	 */
	private Long procesaUnaPersona(PersonaConsulta pc)
			throws SmartdataException, RequeridoException, PersitenciaException {
		try {
			LOG.info("PERSONA OJO A PERSISTIR **************** " + pc);

			Long resp = new Long(-1);

			if (TipoDocumentoEnum.C.equals(pc.getTipoDocumento())) {
				// Cuando es nacional Cedula
				resp = procesaPersonaNacional(pc);
			} else if (TipoDocumentoEnum.R.equals(pc.getTipoDocumento())) {
				// Cuando es nacional RUC
				if (pc.getNoDocumento().charAt(2) == '9' || pc.getNoDocumento().charAt(2) == '6') {
					resp = procesaPersonaJuridica(pc);
				} else {
					resp = procesaPersonaNacional(pc);
				}
			} else {
				// Cuando es extranjero
				resp = procesaExtranjero(pc);
			}

			return resp;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new PersitenciaException(e.getMessage());
		} catch (EmpleoDependienteException e) {
			e.printStackTrace();
			throw new PersitenciaException(e.getMessage());
		} catch (EmpleoIndependienteException e) {
			e.printStackTrace();
			throw new PersitenciaException(e.getMessage());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new PersitenciaException(e.getMessage());
		} catch (ErrorIngresoWsSiseException e) {
			e.printStackTrace();
			throw new PersitenciaException(e.getMessage());
		}
	}

	/**
	 * Procesa persona juridica.
	 * 
	 * @param pc
	 * @return
	 * @throws RequeridoException
	 * @throws RemoteException
	 * @throws EmpleoDependienteException
	 * @throws EmpleoIndependienteException
	 * @throws ServiceException
	 * @throws ErrorIngresoWsSiseException
	 * @throws PersitenciaException
	 */
	private Long procesaPersonaJuridica(PersonaConsulta pc) throws RequeridoException {
		try {
			Long resp = null;

			// 1. Consulta en CU y en SD
			RespuestaGeneralDto persona = consultaGeneralCUServicio.consultaGenericaPersonaJuridica(pc.getNoDocumento(),
					"buenviaje");

			TransformaHelper th = new TransformaHelper();

			// 2. Si no existe en CU, se persiste los datos de buen viaje
			if (!persona.isEncuentraCu()) {
				PersonaJuridica pj = th.crearPersonaJuridicaDesdeBuenViaje(pc);

				LOG.info("PERSONA JURIDICA" + pj);
				if (pj != null) {
					LOG.info("PERSONA JURIDICA" + pj.getPersona());
				}
				// Se persiste en CU
				personaJuridicaServicio.crearPersonaJuridica(pj.getPersona(), pj);
			}

			// 3. Si no existe en SD, se persiste los datos de buen viaje
			if (!persona.isEncuentraSd()) {
				PersonaJuridicaSd pj = th.crearPersonaJuridicaSdDesdeBuenViaje(pc);

				// Se persiste en SD
				personaJuridicaSdServicio.crearPersonaJuridica(pj.getSecPersona(), pj);
			}

			// Se envia a SISE
			PersonaJuridica pjEnvia = th.crearPersonaJuridicaDesdeBuenViaje(pc);
			if (persona.isEncuentraCu()) {
				if (persona.getPersonaJuridica() != null
						&& persona.getPersonaJuridica().getSecPersonaJuridica() != null) {
					pjEnvia.setSecPersonaJuridica(persona.getPersonaJuridica().getSecPersonaJuridica());
				}

				if (persona.getPersonaJuridica().getPersona() != null
						&& persona.getPersonaJuridica().getPersona().getSecPersona() != null) {
					pjEnvia.getPersona().setSecPersona(persona.getPersonaJuridica().getPersona().getSecPersona());
				}
			}
			resp = persistirSisePersonaJuridica(pjEnvia, !persona.isEncuentraCu(), pc.getTipoPersonaConsulta());

			return resp;
		} catch (ParseException e) {
			throw new RequeridoException(e.getMessage(), e.getCause());
		} catch (PersitenciaException e) {
			throw new RequeridoException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Procesa parsona nacional natural.
	 * 
	 * @param pc
	 * @return
	 * @throws RequeridoException
	 * @throws RemoteException
	 * @throws EmpleoDependienteException
	 * @throws EmpleoIndependienteException
	 * @throws ServiceException
	 * @throws ErrorIngresoWsSiseException
	 * @throws PersitenciaException
	 */
	private Long procesaPersonaNacional(PersonaConsulta pc)
			throws RequeridoException, RemoteException, EmpleoDependienteException, EmpleoIndependienteException,
			ServiceException, ErrorIngresoWsSiseException, PersitenciaException {
		Long resp = null;

		// si es ruc o cedula, siempre busca por cedula

		String cedula = pc.getNoDocumento();
		String ruc = null;
		if (pc.getTipoDocumento().equals(TipoDocumentoEnum.R)) {
			ruc = pc.getNoDocumento();
			cedula = pc.getNoDocumento().substring(0, 10);
			System.out.println("La persona natural se ha enviado con RUC ...");
		}

		// si el ruc es null, la persona obviamente no tiene ruc (caso mas comun)
		RespuestaGeneralDto persona = consultaGeneralCUServicio.consultaGenericaConSise(cedula, "buenviaje", ruc);

		System.out.println("BUSQUEDA GENERICA SISE " + persona);

		if (LugarEncuentraInfoEnum.NONE.equals(persona.getLugarEncuentra())) {
			// No se encuentra la persona en ninguna parte
			throw new RequeridoException(
					"No se ha encontrado: " + pc.getNoDocumento() + " en ninguna fuente de datos.");
		}

		// 2. Se realiza la comparación de datos
		boolean datosIguales = isDatosIguales(pc, persona.getPersonaEncontradaUnica());

		PersonaNatural personaEnriquecida = persona.getPersonaEncontradaUnica();

		// Se pone el genero que se envia desde WS
		if (TipoPersonaConsultaEnum.ASEGURADO.equals(pc.getTipoPersonaConsulta())
				|| TipoPersonaConsultaEnum.SOLICITANTE.equals(pc.getTipoPersonaConsulta())) {
			if (personaEnriquecida != null && pc.getGenero() != null) {
				System.out.println("PersonaEnriquecida >>>>>>>>>>. " + personaEnriquecida);
				System.out.println("PC: " + pc);
				personaEnriquecida.setSexo(pc.getGenero().toString().charAt(0));
				personaEnriquecida.setFchNacimiento(pc.getFechaNacimiento());
			}
		}

		if (personaEnriquecida == null) {
			personaEnriquecida = new PersonaNatural();
			String identificacion = pc.getNoDocumento().substring(0, 10);

			Persona per = new Persona();
			per.setCliente(pc.getTipoDePersona().name().charAt(0));

			if ((pc.getNoDocumento().charAt(2) == '6' || pc.getNoDocumento().charAt(2) == '9')
					&& pc.getNoDocumento().length() == 13) {
				personaEnriquecida.setIdentificacion(pc.getNoDocumento());
				personaEnriquecida.setTipoIdentificacion(new TipoIdentificacion('R'));
			} else {
				personaEnriquecida.setIdentificacion(identificacion);
				personaEnriquecida.setTipoIdentificacion(new TipoIdentificacion('C'));
			}

			personaEnriquecida.setApellidoPaterno(pc.getPrimerApellido());
			personaEnriquecida.setPrimerNombre(pc.getPrimerNombre());
			personaEnriquecida.setSexo(pc.getGenero().name().charAt(0));
			personaEnriquecida.setUsrCreacion("audcrm");
			personaEnriquecida.setTtyCreacion("172.16.153.101");
			personaEnriquecida.setPrgCreacion("CLIENTE EQUIVIDA WEB");
			personaEnriquecida.setCtaCreacion("PersonaNaturalServicioImpl");
			EstadoCivil estadoCivil = new EstadoCivil(new Short("0"));
			personaEnriquecida.setEstadoCivil(estadoCivil);

			TipoIdentificacion ti = new TipoIdentificacion();
			ti.setCodTipoIdentificacion(pc.getTipoDocumento().name().charAt(0));
			per.setTipoIdentificacion(personaEnriquecida.getTipoIdentificacion());
			per.setDenominacion(pc.getPrimerApellido() + " " + pc.getPrimerNombre());
			per.setUsrCreacion("audcrm"); // revisar...
			per.setTtyCreacion("172.16.153.101");
			per.setPrgCreacion("CLIENTE EQUIVIDA WEB");
			per.setCtaCreacion("PersonaNaturalServicioImpl");

			personaEnriquecida.setPersona(per);
		}

		if (persona.getRucVerificar() != null) {
			personaEnriquecida.setRucVerificarTransient(persona.getRucVerificar());
		}

		// Esto sirve para SICE
		if (datosIguales) {
			if (!persona.isEncuentraCu()) {
				// Se persiste en CU
				personaNaturalServicio.crearPersonaNaturalBuenViaje(personaEnriquecida, "buenviaje", "127.0.0.0");
			}

			// Si son iguales, se persisten datos enriquecidos
			LOG.info("DATOS IGUALES");
			if (persona.getPersonaEncontradaUnica() == null) {
				persona.setPersonaNatural(personaEnriquecida);
				persona.setEncuentraCu(true);
			}
			resp = persistirSise(persona.getPersonaEncontradaUnica(), !persona.isEncuentraCu(),
					pc.getTipoPersonaConsulta());
		} else {
			// Se pasan los datos de persona enriquecida
			personaNaturalServicio.crearPersonaNaturalBuenViaje(personaEnriquecida, "buenviaje", "127.0.0.0");

			LOG.info("DATOS DISTINTOS");
			// Si son diferenctes, se persisten los datos de buen viaje
			resp = persistirSise(new TransformaHelper().transformarDesdeBuenViaje(pc, null), !persona.isEncuentraCu(),
					pc.getTipoPersonaConsulta());
		}

		// veririca ruc
		rucPersonaNaturalServicio.verificarRuc(personaEnriquecida);

		return resp;
	}

	/**
	 * Proceso cuando es persona extranjera.
	 * 
	 * @param pc
	 * @return
	 * @throws PersitenciaException
	 * @throws ErrorIngresoWsSiseException
	 * @throws ServiceException
	 * @throws EmpleoIndependienteException
	 * @throws EmpleoDependienteException
	 * @throws RemoteException
	 */
	private Long procesaExtranjero(PersonaConsulta pc) throws PersitenciaException, RemoteException,
			EmpleoDependienteException, EmpleoIndependienteException, ServiceException, ErrorIngresoWsSiseException {
		// Se consulta en CU
		boolean existe = false;

		// Busca si existen en CU
		PersonaNatural pnCu = personaNaturalServicio.obtenerPersonaNaturalByDocumento(pc.getNoDocumento(), true);

		PersonaNatural pn = new TransformaHelper().transformarDesdeBuenViaje(pc, null);

		if (pnCu != null && pnCu.getSecPersonaNatural() != null && pnCu.getPersona() != null
				&& pnCu.getPersona().getSecPersona() != null) {
			existe = true;

			// Se pone el genero que se envia desde WS porque hay casos que en
			// CU esta mal el codigo
			if (TipoPersonaConsultaEnum.ASEGURADO.equals(pc.getTipoPersonaConsulta())
					|| TipoPersonaConsultaEnum.SOLICITANTE.equals(pc.getTipoPersonaConsulta())) {
				pnCu.setSexo(pc.getGenero().toString().charAt(0));
			}

			// Si ya existe se pone los datos de la persona encontrada
			pn = pnCu;
		}

		// Si no existe en CU, se persisten datos en PERSONA, Persona natural y
		// en Direccion.
		if (!existe) {
			personaNaturalServicio.crearPersonaNaturalBuenViaje(pn, "buenviaje", "127.0.0.0");
		}

		// Se envia a SISE
		Long resp = persistirSise(pn, false, pc.getTipoPersonaConsulta());

		return resp;
	}

	/**
	 * Guarda en SISE.
	 * 
	 * @param pn
	 * @param nuevo
	 * @return
	 * @throws PersitenciaException
	 */
	private Long persistirSisePersonaJuridica(PersonaJuridica pj, boolean nuevo, TipoPersonaConsultaEnum tipoPersona)
			throws PersitenciaException {
		// ingresa persona natural sise
		StringBuffer error = new StringBuffer(500);

		try {
			boolean persisteDirecion = false;

			if (TipoPersonaConsultaEnum.FACTURA.equals(tipoPersona)) {
				persisteDirecion = true;
			}

			RespuestaSiseDto dto = siseServicio.insertarWsSiseMpersonaJuridica(pj, pj.getPersona(), nuevo, "buenviaje",
					persisteDirecion);

			// muestra si hubo mensajes de error
			if (dto.getErrorLista() != null && !dto.getErrorLista().isEmpty()) {
				boolean hayError = false;

				for (String m : dto.getErrorLista()) {
					if (m != null && m.trim().length() > 0) {
						hayError = true;
						error.append(m).append(",");
					}
				}

				if (hayError) {
					throw new PersitenciaException(error.toString());
				}
			}

			return dto.getIdPersonaSise();
		} catch (Exception e) {
			e.printStackTrace();
			error.append(e.getMessage());

			throw new PersitenciaException(e.toString());
		}
	}

	/**
	 * Guarda en SISE.
	 * 
	 * @param pn
	 * @param nuevo
	 * @return
	 * @throws PersitenciaException
	 */
	@SuppressWarnings("unused")
	private Long persistirSise(PersonaNatural pn, boolean nuevo, TipoPersonaConsultaEnum tipoPersona)
			throws PersitenciaException {

		LOG.info("pn.getSegundoNombre(): " + pn.getSegundoNombre());
		LOG.info("pn.getApellidoMaterno(): " + pn.getApellidoMaterno());

		boolean tieneTextoNull = false;

		if (pn.getSegundoNombre() != null && pn.getSegundoNombre().equalsIgnoreCase("null")) {
			LOG.info("tiene texto null en segundo nombre");
			tieneTextoNull = true;
			pn.setSegundoNombre("");
		}

		if (pn.getApellidoMaterno() != null && pn.getApellidoMaterno().equalsIgnoreCase("null")) {
			LOG.info("tiene texto null en apellido materno");
			tieneTextoNull = true;
			pn.setApellidoMaterno("");
		}

		if (tieneTextoNull) {
			pn.setNombresApellidos(null);
			pn.setApellidosNombres(null);
		}

		// ingresa persona natural sise
		StringBuffer error = new StringBuffer(500);
		try {
			boolean persisteDirecion = false;

			if (TipoPersonaConsultaEnum.FACTURA.equals(tipoPersona)) {
				persisteDirecion = true;
			}

			LOG.info("PERSONA NATURAL " + pn);
			LOG.info("PERSONA .... " + pn.getPersona());

			RespuestaSiseDto dto = siseServicio.insertarWsSiseMpersona(pn, pn.getPersona(), nuevo, "buenviaje", true);
			LOG.info("RESPUESTA SISE ////////////// :  " + dto);

			// muestra si hubo mensajes de error
			if (dto.getErrorLista() != null && !dto.getErrorLista().isEmpty()) {
				boolean hayError = false;

				for (String m : dto.getErrorLista()) {
					LOG.info("error +++++++" + m);
					if (m != null && m.trim().length() > 0) {
						hayError = true;
						error.append(m).append(",");
					}
				}

				if (hayError) {
					throw new PersitenciaException(error.toString());
				}
			}

			return dto.getIdPersonaSise();
		} catch (Exception e) {
			e.printStackTrace();
			error.append(e.getMessage());

			throw new PersitenciaException(e.toString());
		}
	}

	/**
	 * 
	 * @param pc
	 * @param personaEncontradaUnica
	 * @return
	 */
	private boolean isDatosIguales(PersonaConsulta pc, PersonaNatural pn) {
		return new Comparador().compare(pc, pn) == 0 ? true : false;
	}

	/**
	 * Obtiene el Codigo de asegurado.
	 * 
	 * @param idPersonaSise
	 * @return
	 */
	private String getCodigoAsegurado(Long idPersonaSise) {
		String resp = "";

		// TODO: poner en archivo de propiedades
		// String address = "http://10.10.52.107/WSCreaAseg/WSCreaAseg.asmx";

		// Esta ip es la internat 10.10.30.146, si no funciona con la
		// 200.32.69.189 probar con la interna
		// String address = "http://200.32.69.189/WSCreaAseg/WSCreaAseg.asmx";
		String address = Parametros.getString("location.web.service.crea.asegurado");// "http://10.10.52.57/WSCreaAseg/WSCreaAseg.asmx";

		LOG.info("wsdL: " + address);

		URL url;
		try {
			url = new URL(address);

			WebService1Locator locator = new WebService1Locator();

			WebService1Soap service = locator.getWebService1Soap(url);

			String result = service.WSCreaAsegurado(idPersonaSise.intValue());

			int posIni = result.indexOf("<CodAseg>");
			posIni = posIni + "<CodAseg>".length();
			int posFin = result.indexOf("</CodAseg>");

			resp = result.substring(posIni, posFin);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return resp;
	}
}
