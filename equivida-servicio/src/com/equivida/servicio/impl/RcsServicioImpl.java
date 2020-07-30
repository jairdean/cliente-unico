package com.equivida.servicio.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoRcsEnum;
import com.equivida.constant.PersonaRechazoListaReservadaEnum;
import com.equivida.dao.RcsDao;
import com.equivida.dto.MensajeRcsDto;
import com.equivida.dto.ResultadoWebserviceListaReservada;
import com.equivida.mensajeria.AsyncEmailServicio;
import com.equivida.mensajeria.EmailServicio;
import com.equivida.modelo.EstadoRcs;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Rcs;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.servicio.EstadoRcsServicio;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.equivida.util.DateUtil;
import com.equivida.util.MailMessage;
import com.equivida.util.webservice.WsRiesgoUtil;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "RcsServicio")
public class RcsServicioImpl extends GenericServiceImpl<Rcs, Integer> implements RcsServicio {

	private Logger log = Logger.getLogger(RcsServicioImpl.class);

	@EJB
	private RcsDao rcsDao;

	@EJB
	private EstadoRcsServicio estadoRcsServicio;

	@EJB
	private WsDatosPersonaServicio wsDatosPersonaServicio;

	@EJB(mappedName = "EmailServicio/local")
	private EmailServicio emailServicio;

	@EJB(mappedName = "AsyncEmailServicio/local")
	private AsyncEmailServicio asyncEmailServicio;

	public GenericDao<Rcs, Integer> getDao() {
		return rcsDao;
	}

	@Override
	public List<Rcs> obtenerPorFechaUsuarioId(Date fechaDesde, Date fechaHasta, String idUsuario) {
		return rcsDao.obtenerPorFechaUsuarioId(fechaDesde, fechaHasta, idUsuario);
	}

	@Override
	public List<String> obtenerDistintosUsuarios() {
		return rcsDao.obtenerDistintosUsuarios();
	}

	@Override
	public List<Rcs> obtenerPorFechaUsuarioIdPaginado(Date fechaDesde, Date fechaHasta, String idUsuario,
			Character codEstadoRcs, boolean contratante, int ini, int total) {
		return rcsDao.obtenerPorFechaUsuarioIdPaginado(fechaDesde, fechaHasta, idUsuario, codEstadoRcs, contratante,
				ini, total);
	}

	@Override
	public Long obtenerPorFechaUsuarioIdPaginadoTotal(Date fechaDesde, Date fechaHasta, String idUsuario,
			Character codEstadoRcs, boolean contratante) {
		return rcsDao.obtenerPorFechaUsuarioIdPaginadoTotal(fechaDesde, fechaHasta, idUsuario, codEstadoRcs,
				contratante);
	}

	/**
	 * @see com.equivida.servicio.RcsServicio#obtenerMasReciente(java.lang.String)
	 */
	@Override
	public Rcs obtenerMasReciente(String identificacion) {
		return rcsDao.obtenerMasReciente(identificacion);
	}

	/**
	 * @see com.equivida.servicio.RcsServicio#verificarBloqueo(com.equivida.modelo.PersonaNatural,
	 *      java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public MensajeRcsDto verificarBloqueo(PersonaNatural personaNatural, String usuario, String ip, boolean nuevo) {

		String identificacion = personaNatural.getIdentificacion();

		// si tiene ruc es contratante
		if (personaNatural.getRucPersonaNaturalTra() != null) {
			identificacion = personaNatural.getRucPersonaNaturalTra().getIdentificacion();
		}

		Rcs ultimoRcs = this.obtenerMasReciente(identificacion);

		MensajeRcsDto mensajeRcsDto = new MensajeRcsDto();
		mensajeRcsDto.setPuedeContinuar(true);

		// si tiene rcs se verifica las fechas de bloqueo
		if (ultimoRcs != null) {

			// si es pendiente
			siEsPendiente(personaNatural, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, false);
			siEsAprobado(personaNatural, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, false);
			siEsRechazado(personaNatural, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, false);

		} else {
			// si no tiene rcs registrado, entonces envia a buscar
			ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcs(personaNatural, usuario, ip,
					nuevo);

			if (res.isConRiesgo()) {
				mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
				mensajeRcsDto.setPuedeContinuar(false);
				mensajeRcsDto.setRcs(res.getRcs());
			}
		}

		return mensajeRcsDto;
	}

	private void siEsPendiente(PersonaNatural personaNatural, String usuario, String ip, boolean nuevo, Rcs ultimoRcs,
			MensajeRcsDto mensajeRcsDto, boolean conyuge) {

		if (ultimoRcs.getEstadoRcs().isPendiente()) {
			int numeroDiasPasados = DateUtil.getDiferenciaEnDias(new Date(), ultimoRcs.getTsCreacion());
			if (ultimoRcs.getEstadoRcs().getNumDias() < numeroDiasPasados) {

				if (!conyuge) {

					ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcs(personaNatural, usuario,
							ip, nuevo);
					if (res.isConRiesgo()) {
						mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
						mensajeRcsDto.setPuedeContinuar(false);
						mensajeRcsDto.setRcs(res.getRcs());
					}

				} else {

					ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcsConyuge(personaNatural,
							usuario, ip, nuevo);
					if (res.isConRiesgo()) {
						mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
						mensajeRcsDto.setPuedeContinuar(false);
						mensajeRcsDto.setRcs(res.getRcs());
						log.info("bloqueo por conyuge");
					}

				}

			} else {
				// si esta pendiente y dentro de los dias de espera solo
				// muestra el mensaje
				mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
				mensajeRcsDto.setPuedeContinuar(false);
				mensajeRcsDto.setRcs(ultimoRcs);
			}
		}
	}

	private void siEsAprobado(PersonaNatural personaNatural, String usuario, String ip, boolean nuevo, Rcs ultimoRcs,
			MensajeRcsDto mensajeRcsDto, boolean conyuge) {

		if (ultimoRcs.getEstadoRcs().isAprobado()) {
			int numeroDiasPasados = DateUtil.getDiferenciaEnDias(new Date(), ultimoRcs.getTsModificacion());
			if (ultimoRcs.getEstadoRcs().getNumDias() < numeroDiasPasados) {

				if (!conyuge) {
					ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcs(personaNatural, usuario,
							ip, nuevo);
					if (res.isConRiesgo()) {
						mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
						mensajeRcsDto.setPuedeContinuar(false);
						mensajeRcsDto.setRcs(res.getRcs());

					}
				} else {
					ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcsConyuge(personaNatural,
							usuario, ip, nuevo);
					if (res.isConRiesgo()) {
						mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
						mensajeRcsDto.setPuedeContinuar(false);
						mensajeRcsDto.setRcs(res.getRcs());
						log.info("bloqueo por conyuge");
					}
				}

			} else {
				// si esta pendiente y dentro de los dias de espera solo
				// muestra el mensaje
				mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_APROBADO);
				mensajeRcsDto.setPuedeContinuar(true);
			}
		}
	}

	private void siEsRechazado(PersonaNatural personaNatural, String usuario, String ip, boolean nuevo, Rcs ultimoRcs,
			MensajeRcsDto mensajeRcsDto, boolean conyuge) {

		if (ultimoRcs.getEstadoRcs().isRechazado()) {
			int numeroDiasPasados = DateUtil.getDiferenciaEnDias(new Date(), ultimoRcs.getTsModificacion());
			if (ultimoRcs.getEstadoRcs().getNumDias() <= numeroDiasPasados) {

				if (!conyuge) {
					ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcs(personaNatural, usuario,
							ip, nuevo);
					if (res.isConRiesgo()) {
						mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
						mensajeRcsDto.setPuedeContinuar(false);
						mensajeRcsDto.setRcs(res.getRcs());

					}
				} else {
					ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcsConyuge(personaNatural,
							usuario, ip, nuevo);
					if (res.isConRiesgo()) {
						mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
						mensajeRcsDto.setPuedeContinuar(false);
						mensajeRcsDto.setRcs(res.getRcs());
						log.info("bloqueo por conyuge");
					}
				}

			} else {
				// si esta pendiente y dentro de los dias de espera solo
				// muestra el mensaje
				mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
				mensajeRcsDto.setPuedeContinuar(false);
				mensajeRcsDto.setRcs(ultimoRcs);
			}
		}
	}

	private void siEsPendientePJ(PersonaJuridica personaJuridica, String usuario, String ip, boolean nuevo,
			Rcs ultimoRcs, MensajeRcsDto mensajeRcsDto, PersonaRechazoListaReservadaEnum rcsEnum) {

		if (ultimoRcs.getEstadoRcs().isPendiente()) {
			int numeroDiasPasados = DateUtil.getDiferenciaEnDias(new Date(), ultimoRcs.getTsCreacion());
			if (ultimoRcs.getEstadoRcs().getNumDias() < numeroDiasPasados) {

				ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcsPJ(personaJuridica, usuario, ip,
						nuevo, rcsEnum);
				if (res.isConRiesgo()) {
					mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
					mensajeRcsDto.setPuedeContinuar(false);
					mensajeRcsDto.setRcs(res.getRcs());
				}

			} else {
				// si esta pendiente y dentro de los dias de espera solo
				// muestra el mensaje
				mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
				mensajeRcsDto.setPuedeContinuar(false);
				mensajeRcsDto.setRcs(ultimoRcs);
			}
		}
	}

	private void siEsAprobadoPJ(PersonaJuridica personaJuridica, String usuario, String ip, boolean nuevo,
			Rcs ultimoRcs, MensajeRcsDto mensajeRcsDto, PersonaRechazoListaReservadaEnum rcsEnum) {

		if (ultimoRcs.getEstadoRcs().isAprobado()) {
			int numeroDiasPasados = DateUtil.getDiferenciaEnDias(new Date(), ultimoRcs.getTsModificacion());
			if (ultimoRcs.getEstadoRcs().getNumDias() < numeroDiasPasados) {

				ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcsPJ(personaJuridica, usuario, ip,
						nuevo, rcsEnum);
				if (res.isConRiesgo()) {
					mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
					mensajeRcsDto.setPuedeContinuar(false);
					mensajeRcsDto.setRcs(res.getRcs());
				}

			} else {
				// si esta pendiente y dentro de los dias de espera solo
				// muestra el mensaje
				mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_APROBADO);
				mensajeRcsDto.setPuedeContinuar(true);
			}
		}
	}

	private void siEsRechazadoPJ(PersonaJuridica personaJuridica, String usuario, String ip, boolean nuevo,
			Rcs ultimoRcs, MensajeRcsDto mensajeRcsDto, PersonaRechazoListaReservadaEnum rcsEnum) {

		if (ultimoRcs.getEstadoRcs().isRechazado()) {
			int numeroDiasPasados = DateUtil.getDiferenciaEnDias(new Date(), ultimoRcs.getTsModificacion());
			if (ultimoRcs.getEstadoRcs().getNumDias() <= numeroDiasPasados) {

				ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcsPJ(personaJuridica, usuario, ip,
						nuevo, rcsEnum);
				if (res.isConRiesgo()) {
					mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
					mensajeRcsDto.setPuedeContinuar(false);
					mensajeRcsDto.setRcs(res.getRcs());
				}

			} else {
				// si esta pendiente y dentro de los dias de espera solo
				// muestra el mensaje
				mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
				mensajeRcsDto.setPuedeContinuar(false);
				mensajeRcsDto.setRcs(ultimoRcs);
			}
		}
	}

	@Override
	public ResultadoWebserviceListaReservada consultarRegistrarServicioWebRcs(PersonaNatural personaNatural,
			String usuario, String ip, boolean nuevo) {

		log.info("OJO: Verifica score persona 1");

		if (personaNatural.getPrimerNombre() == null || personaNatural.getPrimerNombre().trim().equals("")) {
			// si no tiene primer nombre entonces no busca riesgo
			ResultadoWebserviceListaReservada r = new ResultadoWebserviceListaReservada();
			r.setConRiesgo(false);
			return r;
		}

		log.info("OJO: Verifica score persona 2");

		int tipoIdentificacion = WsRiesgoUtil
				.obtenerTipoIdentificacion(personaNatural.getTipoIdentificacion().getCodTipoIdentificacion());
		String identificacion = personaNatural.getIdentificacion();

		// si es contratante
		if (personaNatural.getRucPersonaNaturalTra() != null) {
			tipoIdentificacion = WsRiesgoUtil.obtenerTipoIdentificacion(
					personaNatural.getRucPersonaNaturalTra().getTipoIdentificacion().getCodTipoIdentificacion());
			identificacion = personaNatural.getRucPersonaNaturalTra().getIdentificacion();
		}

		personaNatural.setApellidos(null);// para que vuelva a generar
		String lastname = personaNatural.getApellidos();

		try {
			log.info("OJO: Verifica score persona 3");
			ResultadoWebserviceListaReservada r = registrarRcs(personaNatural, usuario, ip, nuevo, tipoIdentificacion,
					identificacion, lastname);

			return r;

		} catch (RemoteException e) {
			System.out.println("error web ervice riesgo:" + e);
		} catch (ServiceException e) {
			System.out.println("error web ervice riesgo:" + e);
			// String mensaje = getBundleMensajes("error.web.service", null);
			// addErrorMessage(null, mensaje, mensaje);
		}
		return null;

	}

	private ResultadoWebserviceListaReservada registrarRcs(PersonaNatural personaNatural, String usuario, String ip,
			boolean nuevo, int tipoIdentificacion, String identificacion, String lastname)
			throws ServiceException, RemoteException {

		log.info("<< Antes de Valida Riesgo");
		ResultadoWebserviceListaReservada r = wsDatosPersonaServicio.validarRiesgo(personaNatural.getPrimerNombre(),
				personaNatural.getSegundoNombre(), lastname, tipoIdentificacion, identificacion);

		log.info("<< Luego de Valida Riesgo");

		if (r.isConRiesgo()) {

			Rcs rcs = new Rcs();
			rcs.setIdUsuario(usuario);
			rcs.setUsrCreacion(usuario);
			rcs.setUsrModificacion(usuario);
			rcs.setIdentificacion(identificacion);

			rcs.setTipoIdentificacion(personaNatural.getTipoIdentificacion());

			// si es contratante
			boolean contratante = false;
			if (personaNatural.getRucPersonaNaturalTra() != null) {
				rcs.setTipoIdentificacion(personaNatural.getRucPersonaNaturalTra().getTipoIdentificacion());
				contratante = true;
			}

			rcs.setTsCreacion(new Date());
			rcs.setTsModificacion(new Date());
			rcs.setContenidoXml(r.getContenidoXml());
			rcs.setCodControl(PersonaRechazoListaReservadaEnum.EN_LISTA_PERSONA.getCodigo());
			rcs.setDenominacion(personaNatural.getNombresApellidos());
			if (nuevo) {
				rcs.setCodEstado(EstadoRcsEnum.NUEVO.getCodigo());
			} else {
				rcs.setCodEstado(EstadoRcsEnum.MODIFICACION.getCodigo());
			}
			rcs.setEstadoRcs(new EstadoRcs(Constantes.ID_ESTADO_RCS_PENDIENTE));

			this.create(rcs);
			log.info("registra log que no ha pasado score de riesgo");

			r.setRcs(rcs);

			// envia correo
			personaNatural.setNombres(null);// para que vuelva a generar
			MailMessage mailMessage = emailServicio.prepararCorreoListasReservadas(usuario, ip,
					personaNatural.getApellidoPaterno(), personaNatural.getApellidoMaterno(),
					personaNatural.getNombres(), identificacion, PersonaRechazoListaReservadaEnum.EN_LISTA_PERSONA,
					r.getRiesgoDtoLista(), nuevo, null, null, null, null, contratante);

			for (String mt : mailMessage.getTo()) {
				log.info("<< Se envia mail a: " + mt);
			}

			log.info("<< Se ecnola mail");
			asyncEmailServicio.encolarMail(mailMessage);

		}
		return r;
	}

	private ResultadoWebserviceListaReservada registrarRcsPJ(PersonaJuridica personaJuridica, String usuario, String ip,
			boolean nuevo, int tipoIdentificacion, String identificacion, PersonaRechazoListaReservadaEnum rcsEnum)
			throws ServiceException, RemoteException {

		log.info("<< Antes de Valida Riesgo");

		ResultadoWebserviceListaReservada r = null;

		if (rcsEnum.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_PERSONA_JURIDICA)) {
			r = wsDatosPersonaServicio.validarRiesgo(personaJuridica.getRazonSocial(), "", "", tipoIdentificacion,
					identificacion);
		}

		PersonaNatural personaNatural = null;

		if (rcsEnum.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_REPRESENTANTE_LEGAL)) {
			personaNatural = personaJuridica.getRepresentante().getPersonaNaturalTransient();
			String lastname = personaNatural.getApellidos();
			tipoIdentificacion = WsRiesgoUtil
					.obtenerTipoIdentificacion(personaNatural.getTipoIdentificacion().getCodTipoIdentificacion());
			r = wsDatosPersonaServicio.validarRiesgo(personaNatural.getPrimerNombre(),
					personaNatural.getSegundoNombre(), lastname, tipoIdentificacion,
					personaNatural.getIdentificacion());

		}

		if (rcsEnum.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_REPRESENTANTE_LEGAL)) {
			personaNatural = personaJuridica.getRepresentante().getPersonaNaturalTransient().getConyuge();
			String lastname = personaNatural.getApellidos();
			tipoIdentificacion = WsRiesgoUtil
					.obtenerTipoIdentificacion(personaNatural.getTipoIdentificacion().getCodTipoIdentificacion());
			r = wsDatosPersonaServicio.validarRiesgo(personaNatural.getPrimerNombre(),
					personaNatural.getSegundoNombre(), lastname, tipoIdentificacion,
					personaNatural.getIdentificacion());
		}

		if (rcsEnum.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_ACCIONISTA)) {
			personaNatural = personaJuridica.getAccionistaIteracionRcs();
			tipoIdentificacion = WsRiesgoUtil
					.obtenerTipoIdentificacion(personaNatural.getTipoIdentificacion().getCodTipoIdentificacion());
			if (personaNatural.getTipoIdentificacion().isCedula()) {
				String lastname = personaNatural.getApellidos();
				r = wsDatosPersonaServicio.validarRiesgo(personaNatural.getPrimerNombre(),
						personaNatural.getSegundoNombre(), lastname, tipoIdentificacion,
						personaNatural.getIdentificacion());

			} else {
				String razonSocial = personaNatural.getApellidos();
				r = wsDatosPersonaServicio.validarRiesgo(razonSocial, "", "", tipoIdentificacion,
						personaNatural.getIdentificacion());

			}
		}

		String apellido1 = null;
		String apellido2 = null;
		String nombres = null;

		TipoIdentificacion tipoInvolucrado = null;
		String identificacionInvolucrado = null;

		if (personaNatural != null) {
			apellido1 = personaNatural.getApellidoPaterno();
			apellido2 = personaNatural.getApellidoMaterno();
			nombres = personaNatural.getNombres();
			identificacionInvolucrado = personaNatural.getIdentificacion();
			tipoInvolucrado = personaNatural.getTipoIdentificacion();
		}

		log.info("<< Luego de Valida Riesgo");

		if (r.isConRiesgo()) {

			Rcs rcs = new Rcs();
			rcs.setIdUsuario(usuario);
			rcs.setUsrCreacion(usuario);
			rcs.setUsrModificacion(usuario);
			rcs.setIdentificacion(identificacion);

			// datos involucrado, conyuge, rep legal o rep legal conyuge o accionista
			if (identificacionInvolucrado != null) {
				rcs.setIdentificacionConyuge(identificacionInvolucrado);
			}
			if (tipoInvolucrado != null) {
				rcs.setTipoIdentificacionConyuge(tipoInvolucrado);
			}

			rcs.setTipoIdentificacion(personaJuridica.getTipoIdentificacion());
			rcs.setTsCreacion(new Date());
			rcs.setTsModificacion(new Date());
			rcs.setContenidoXml(r.getContenidoXml());
			rcs.setCodControl(rcsEnum.getCodigo());
			rcs.setDenominacion(personaJuridica.getRazonSocial());
			if (nuevo) {
				rcs.setCodEstado(EstadoRcsEnum.NUEVO.getCodigo());
			} else {
				rcs.setCodEstado(EstadoRcsEnum.MODIFICACION.getCodigo());
			}
			rcs.setEstadoRcs(new EstadoRcs(Constantes.ID_ESTADO_RCS_PENDIENTE));

			this.create(rcs);
			log.info("registra log que no ha pasado score de riesgo");

			r.setRcs(rcs);

			// envia correo
			MailMessage mailMessage = emailServicio.prepararCorreoListasReservadas(usuario, ip, "", "",
					personaJuridica.getRazonSocial(), identificacion, rcsEnum, r.getRiesgoDtoLista(), nuevo, apellido1,
					apellido2, nombres, identificacionInvolucrado, true);

			for (String mt : mailMessage.getTo()) {
				log.info("<< Se envia mail a: " + mt);
			}

			log.info("<< Se ecnola mail");
			asyncEmailServicio.encolarMail(mailMessage);

		}
		return r;
	}

	@Override
	public ResultadoWebserviceListaReservada consultarRegistrarServicioWebRcsConyuge(PersonaNatural personaNatural,
			String usuario, String ip, boolean nuevo) {

		if (!personaNatural.isConConyuge()) {
			return null;
		}

		PersonaNatural conyuge = personaNatural.getConyuge();

		System.out.println("verifica score conyuge" + conyuge.getNombresApellidos());

		int tipoIdentificacionConyuge = WsRiesgoUtil
				.obtenerTipoIdentificacion(conyuge.getTipoIdentificacion().getCodTipoIdentificacion());
		String identificacionConyuge = conyuge.getIdentificacion();

		conyuge.setApellidos(null);// para que vuelva a generar
		String lastnameConyuge = conyuge.getApellidos();

		try {
			ResultadoWebserviceListaReservada r = registrarRcsConyuge(personaNatural, usuario, ip, nuevo, conyuge,
					tipoIdentificacionConyuge, identificacionConyuge, lastnameConyuge);

			return r;

		} catch (RemoteException e) {
			System.out.println("error web ervice riesgo:" + e);
		} catch (ServiceException e) {
			System.out.println("error web ervice riesgo:" + e);
			// String mensaje = getBundleMensajes("error.web.service", null);
			// addErrorMessage(null, mensaje, mensaje);
		}

		return null;
	}

	private ResultadoWebserviceListaReservada registrarRcsConyuge(PersonaNatural personaNatural, String usuario,
			String ip, boolean nuevo, PersonaNatural conyuge, int tipoIdentificacionConyuge,
			String identificacionConyuge, String lastnameConyuge) throws ServiceException, RemoteException {
		ResultadoWebserviceListaReservada r = wsDatosPersonaServicio.validarRiesgo(conyuge.getPrimerNombre(),
				conyuge.getSegundoNombre(), lastnameConyuge, tipoIdentificacionConyuge, identificacionConyuge);

		if (r.isConRiesgo()) {
			conyuge.setNombres(null);// para que vuelva a generar

			Rcs rcs = new Rcs();
			rcs.setIdUsuario(usuario);
			rcs.setUsrCreacion(usuario);
			rcs.setUsrModificacion(usuario);

			rcs.setIdentificacion(personaNatural.getIdentificacion());
			rcs.setTipoIdentificacion(personaNatural.getTipoIdentificacion());

			// si es contratante
			boolean contratante = false;
			if (personaNatural.getRucPersonaNaturalTra() != null) {
				contratante = true;
				rcs.setTipoIdentificacion(personaNatural.getRucPersonaNaturalTra().getTipoIdentificacion());
				rcs.setIdentificacion(personaNatural.getRucPersonaNaturalTra().getIdentificacion());
			}

			rcs.setIdentificacionConyuge(identificacionConyuge);
			rcs.setTipoIdentificacionConyuge(conyuge.getTipoIdentificacion());
			rcs.setTsCreacion(new Date());
			rcs.setContenidoXml(r.getContenidoXml());
			rcs.setCodControl(PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_PERSONA.getCodigo());
			rcs.setDenominacion(personaNatural.getNombresApellidos());
			if (nuevo) {
				rcs.setCodEstado(EstadoRcsEnum.NUEVO.getCodigo());
			} else {
				rcs.setCodEstado(EstadoRcsEnum.MODIFICACION.getCodigo());
			}
			rcs.setEstadoRcs(new EstadoRcs(Constantes.ID_ESTADO_RCS_PENDIENTE));

			this.create(rcs);

			log.info("registra log que no ha pasado score de riesgo");

			MailMessage mailMessage = emailServicio.prepararCorreoListasReservadas(usuario, ip,
					personaNatural.getApellidoPaterno(), personaNatural.getApellidoMaterno(),
					personaNatural.getNombres(), rcs.getIdentificacion(),
					PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_PERSONA, r.getRiesgoDtoLista(), nuevo,
					conyuge.getApellidoPaterno(), conyuge.getApellidoMaterno(), conyuge.getNombres(),
					conyuge.getIdentificacion(), contratante);

			for (String mt : mailMessage.getTo()) {
				log.info("<< Se envia mail a: " + mt);
			}

			log.info("<< Se ecnola mail");
			asyncEmailServicio.encolarMail(mailMessage);

		}
		return r;
	}

	/**
	 * @see com.equivida.servicio.RcsServicio#aprobar(com.equivida.modelo.Rcs,
	 *      java.lang.String)
	 */
	@Override
	public void aprobar(Rcs rcs, String usuarioAprobar) {
		Rcs rcsBdd = this.findByPk(rcs.getSecPersonaControl());
		rcsBdd.setComentario(rcs.getComentario());
		rcsBdd.setUsrModificacion(usuarioAprobar);
		rcsBdd.setIdUsuario(usuarioAprobar);
		rcsBdd.setEstadoRcs(estadoRcsServicio.findByPk(Constantes.ID_ESTADO_RCS_APROBADO));
		try {
			MailMessage mm = emailServicio.prepararCorreoListasReservadasAprobacion(rcsBdd);
			asyncEmailServicio.encolarMail(mm);
		} catch (NamingException e) {
			System.out.println("No se envia correo seq: " + rcs.getSecPersonaControl());
			e.printStackTrace();
		}
	}

	/**
	 * @see com.equivida.servicio.RcsServicio#rechazar(com.equivida.modelo.Rcs,
	 *      java.lang.String)
	 */
	@Override
	public void rechazar(Rcs rcs, String usuarioRechaza) {
		Rcs rcsBdd = this.findByPk(rcs.getSecPersonaControl());
		rcsBdd.setComentario(rcs.getComentario());
		rcsBdd.setUsrModificacion(usuarioRechaza);
		rcsBdd.setIdUsuario(usuarioRechaza);
		rcsBdd.setEstadoRcs(estadoRcsServicio.findByPk(Constantes.ID_ESTADO_RCS_RECHAZADO));

		try {
			MailMessage mm = emailServicio.prepararCorreoListasReservadasRechazo(rcsBdd);
			asyncEmailServicio.encolarMail(mm);
		} catch (NamingException e) {
			System.out.println("No se envia correo seq: " + rcs.getSecPersonaControl());
			e.printStackTrace();
		}
	}

	@Override
	public MensajeRcsDto verificarBloqueoConyuge(PersonaNatural personaNatural, String usuario, String ip,
			boolean nuevo) {

		Rcs ultimoRcs = this.obtenerMasReciente(personaNatural.getConyuge().getIdentificacion());

		MensajeRcsDto mensajeRcsDto = new MensajeRcsDto();
		mensajeRcsDto.setPuedeContinuar(true);

		// si tiene rcs se verifica las fechas de bloqueo
		if (ultimoRcs != null) {

			// si es pendiente
			siEsPendiente(personaNatural, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, true);
			siEsAprobado(personaNatural, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, true);
			siEsRechazado(personaNatural, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, true);

		} else {
			// si no tiene rcs registrado, entonces envia a buscar
			ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcsConyuge(personaNatural, usuario, ip,
					nuevo);
			if (res.isConRiesgo()) {
				mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
				mensajeRcsDto.setPuedeContinuar(false);
				mensajeRcsDto.setRcs(res.getRcs());

			}
		}

		return mensajeRcsDto;
	}

	@Override
	public List<String> obtenerDistintosUsuariosCreacion() {
		return rcsDao.obtenerDistintosUsuariosCreacion();
	}

	@Override
	public List<Rcs> obtenerRcs(List<PersonaNatural> personaLista, String usuario, String ip) {

		List<Rcs> rcsLista = new ArrayList<Rcs>();

		for (PersonaNatural pn : personaLista) {

			Rcs ultimoRcs = this.obtenerMasReciente(pn.getIdentificacion());

			MensajeRcsDto mensajeRcsDto = new MensajeRcsDto();
			mensajeRcsDto.setPuedeContinuar(true);

			boolean nuevo = true;

			// si tiene rcs se verifica las fechas de bloqueo
			if (ultimoRcs != null) {
				log.info("ultimoRcs no es nulo");

				// si es pendiente
				siEsPendiente(pn, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, false);
				siEsAprobado(pn, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, false);
				siEsRechazado(pn, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, false);

			} else {
				log.info("ultimoRcs ES nulo");

				// si no tiene rcs registrado, entonces envia a buscar
				ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcs(pn, usuario, ip, nuevo);
				if (res.isConRiesgo()) {
					mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
					mensajeRcsDto.setPuedeContinuar(false);
					mensajeRcsDto.setRcs(res.getRcs());
				}
			}

			// si tiene riesgo o esta en la tabla RCS, devuelve el registro o
			// los registros que esten
			if (!mensajeRcsDto.isPuedeContinuar()) {
				Rcs respuesta = new Rcs();
				Rcs tmp = null;
				if (mensajeRcsDto.getRcs() != null) {
					tmp = mensajeRcsDto.getRcs();
					respuesta.setIdentificacion(tmp.getIdentificacion());
					respuesta.setContenidoXml(tmp.getContenidoXml());
					rcsLista.add(respuesta);
				}
			}

		}

		return rcsLista;
	}

	@Override
	public MensajeRcsDto verificarBloqueoPJ(PersonaJuridica personaJuridica, String usuario, String ip, boolean nuevo,
			PersonaRechazoListaReservadaEnum rcsEnum) {

		String identificacion = personaJuridica.getIdentificacion();

		if (rcsEnum.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_PERSONA_JURIDICA)) {
			identificacion = personaJuridica.getIdentificacion();
		}
		if (rcsEnum.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_REPRESENTANTE_LEGAL)) {
			identificacion = personaJuridica.getRepresentante().getPersonaNaturalTransient().getIdentificacion();
		}
		if (rcsEnum.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_REPRESENTANTE_LEGAL)) {
			identificacion = personaJuridica.getRepresentante().getPersonaNaturalTransient().getConyuge()
					.getIdentificacion();
		}
		if (rcsEnum.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_ACCIONISTA)) {
			identificacion = personaJuridica.getAccionistaIteracionRcs().getIdentificacion();
		}

		MensajeRcsDto mensajeRcsDto = new MensajeRcsDto();
		mensajeRcsDto.setPuedeContinuar(true);

		if (identificacion.equals("")) {
			// if (true) {
			System.out.println("identificacion no puede ser vacia para listas!!! verificar!!!");
			return mensajeRcsDto;
		}

		Rcs ultimoRcs = this.obtenerMasReciente(identificacion);

		// si tiene rcs se verifica las fechas de bloqueo
		if (ultimoRcs != null) {

			// si es pendiente
			siEsPendientePJ(personaJuridica, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, rcsEnum);
			siEsAprobadoPJ(personaJuridica, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, rcsEnum);
			siEsRechazadoPJ(personaJuridica, usuario, ip, nuevo, ultimoRcs, mensajeRcsDto, rcsEnum);

		} else {
			// si no tiene rcs registrado, entonces envia a buscar
			ResultadoWebserviceListaReservada res = consultarRegistrarServicioWebRcsPJ(personaJuridica, usuario, ip,
					nuevo, rcsEnum);

			if (res.isConRiesgo()) {
				mensajeRcsDto.setMensajeInterfaz(Constantes.MENSAJE_RCS_NO_APROBADO);
				mensajeRcsDto.setPuedeContinuar(false);
				mensajeRcsDto.setRcs(res.getRcs());

			}
		}

		return mensajeRcsDto;
	}

	@Override
	public ResultadoWebserviceListaReservada consultarRegistrarServicioWebRcsPJ(PersonaJuridica personaJuridica,
			String usuario, String ip, boolean nuevo, PersonaRechazoListaReservadaEnum rcsEnum) {
		log.info("OJO: Verifica score persona 1");

		int tipoIdentificacion = WsRiesgoUtil
				.obtenerTipoIdentificacion(personaJuridica.getTipoIdentificacion().getCodTipoIdentificacion());

		String identificacion = personaJuridica.getIdentificacion();

		log.info("OJO: Verifica score persona 2");

		try {
			log.info("OJO: Verifica score persona 3");
			ResultadoWebserviceListaReservada r = registrarRcsPJ(personaJuridica, usuario, ip, nuevo,
					tipoIdentificacion, identificacion, rcsEnum);

			return r;

		} catch (RemoteException e) {
			System.out.println("error web ervice riesgo:" + e);
		} catch (ServiceException e) {
			System.out.println("error web ervice riesgo:" + e);
			// String mensaje = getBundleMensajes("error.web.service", null);
			// addErrorMessage(null, mensaje, mensaje);
		}
		return null;
	}
}
