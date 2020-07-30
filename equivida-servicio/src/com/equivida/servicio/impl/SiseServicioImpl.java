package com.equivida.servicio.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.equivida.constant.Constantes;
import com.equivida.dto.RespuestaSiseDto;
import com.equivida.exception.ContratanteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.exception.NoEncuentraDatosException;
import com.equivida.exception.RazonSocialException;
import com.equivida.homologacion.modelo.DireccionElectronicaEquivida;
import com.equivida.homologacion.modelo.DireccionEquivida;
import com.equivida.homologacion.modelo.PersonaEquivida;
import com.equivida.homologacion.modelo.TelefonoEquivida;
import com.equivida.homologacion.servicio.DireccionElectronicaEquividaServicio;
import com.equivida.homologacion.servicio.DireccionEquividaServicio;
import com.equivida.homologacion.servicio.PersonaEquividaServicio;
import com.equivida.homologacion.servicio.TelefonoEquividaServicio;
import com.equivida.modelo.CantonTMunicipio;
import com.equivida.modelo.DeportePractica;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.EstadoPersona;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Telefono;
import com.equivida.servicio.CantonTMunicipioServicio;
import com.equivida.servicio.SiseServicio;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.equivida.sise.ws.client.ConductoPagoWs;
import com.equivida.sise.ws.client.ContratanteDto;
import com.equivida.sise.ws.client.ContratantesWs;
import com.equivida.sise.ws.client.CumulosDePagoWs;
import com.equivida.sise.ws.client.MasegHeader;
import com.equivida.sise.ws.client.MasegHeaderWs;
import com.equivida.sise.ws.client.RsConductoPago;
import com.equivida.sise.ws.client.RsContratante;
import com.equivida.sise.ws.client.RsCumulosDePago;
import com.equivida.sise.ws.client.impl.ConductaPagoWsImplServiceLocator;
import com.equivida.sise.ws.client.impl.ContratanteWsImplServiceLocator;
import com.equivida.sise.ws.client.impl.CumulosDePagoWsImplServiceLocator;
import com.equivida.sise.ws.client.impl.MasegHeaderWsImplServiceLocator;
import com.equivida.util.Parametros;
import com.equivida.util.webservice.MPersonaDirUtil;
import com.equivida.util.webservice.MPersonaTelUtil;
import com.equivida.util.webservice.MPersonaUtil;

/**
 * @author daniel cardenas
 * 
 */
@Stateless(name = "SiseServicio")
public class SiseServicioImpl implements SiseServicio {
	// private static final SimpleDateFormat SDF = new
	// SimpleDateFormat("dd/MM/yyyy");

	private Logger log = Logger.getLogger(SiseServicioImpl.class);

	@EJB
	private WsDatosPersonaServicio wsDatosPersonaServicio;

	@EJB
	private CantonTMunicipioServicio cantonTMunicipioServicio;

	@EJB(mappedName = "PersonaEquividaServicio/local")
	private PersonaEquividaServicio personaEquividaServicio;

	@EJB(mappedName = "DireccionEquividaServicio/local")
	private DireccionEquividaServicio direccionEquividaServicio;

	@EJB(mappedName = "TelefonoEquividaServicio/local")
	private TelefonoEquividaServicio telefonoEquividaServicio;

	@EJB(mappedName = "DireccionElectronicaEquividaServicio/local")
	private DireccionElectronicaEquividaServicio direccionElectronicaEquividaServicio;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RespuestaSiseDto insertarWsSiseMpersona(PersonaNatural personaNatural, Persona persona, boolean nuevo,
			String usuario, boolean persisteDirecion)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		log.info("PersonaNatural: " + personaNatural);
		log.info("----------------------------------");
		log.info("Persona: " + persona);

		RespuestaSiseDto dto = new RespuestaSiseDto();
		dto.setErrorLista(new ArrayList<String>());

		String txt_apellido1 = personaNatural.getApellidoPaterno();
		String txt_apellido2 = personaNatural.getApellidoMaterno();
		personaNatural.setNombres(null);// para que vuelva a generar
		String txt_nombre = personaNatural.getNombres();
		int cod_tipo_doc = MPersonaUtil
				.obtenerTipoIdentificacion(personaNatural.getTipoIdentificacion().getCodTipoIdentificacion());
		String nro_doc = personaNatural.getIdentificacion();
		Date fec_nac = personaNatural.getFchNacimiento();
		String txt_lugar_nac = personaNatural.getLugarNacimientoParaSise();
		if (txt_lugar_nac.equals("No Disponible Ecuador")) {
			txt_lugar_nac = "";
		}
		String txt_sexo = personaNatural.getSexo() + "";
		int cod_est_civil = MPersonaUtil.obtenerEstadoCivil(personaNatural.getEstadoCivil().getCodEstadoCivil());

		String txt_nombres_conyuge = null;
		String txt_apellidos_conyuge = null;
		int cod_tipo_doc_conyuge = MPersonaUtil.obtenerTipoIdentificacion('C');
		String nro_doc_conyugue = null;
		if (personaNatural.isConConyuge() && personaNatural.getConyuge() != null) {

			PersonaNatural conyuge = personaNatural.getConyuge();
			// entonces debe tener ingresado ocnyuge
			if (conyuge != null) {
				txt_nombres_conyuge = conyuge.getNombres();
				txt_apellidos_conyuge = conyuge.getApellidos();
				cod_tipo_doc_conyuge = MPersonaUtil
						.obtenerTipoIdentificacion(conyuge.getTipoIdentificacion().getCodTipoIdentificacion());
				nro_doc_conyugue = conyuge.getIdentificacion();
			}
		}

		String campo_in_1 = null;
		String campo_in_2 = null;
		String campo_in_3 = null;
		String campo_in_4 = null;
		String campo_in_5 = null;

		// if (cod_tipo_doc == TipoIdentificacionEnum.RUC.getCodigo() && nro_doc != null
		// && (nro_doc.charAt(2) == '9' || nro_doc.charAt(2) == '6')) {
		// nro_doc = nro_doc.substring(0, 10);
		// }

		wsDatosPersonaServicio.ingresarPersonaNaturalSise(txt_apellido1, txt_apellido2, txt_nombre, cod_tipo_doc,
				nro_doc, fec_nac, txt_lugar_nac, txt_sexo, cod_est_civil, txt_nombres_conyuge, txt_apellidos_conyuge,
				cod_tipo_doc_conyuge, nro_doc_conyugue, campo_in_1, campo_in_2, campo_in_3, campo_in_4, campo_in_5,
				dto);

		if (dto.getIdPersonaSise() != null) {
			boolean existe = personaEquividaServicio.existe(persona.getSecPersona(),
					personaNatural.getSecPersonaNatural(), dto.getIdPersonaSise(), Constantes.COD_SISTEMA_SISE,
					Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);
			// solo si no existe en las tablas de homologacion graba
			if (!existe) {
				// solo si tiene sec persona se envia a guardar
				if (persona.getSecPersona() != null) {
					PersonaEquivida personaEquivida = new PersonaEquivida();
					personaEquivida.setCodSistema(Constantes.COD_SISTEMA_SISE);
					personaEquivida.setCodTipoPersona(Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);
					personaEquivida.setIdPersonaDestino(dto.getIdPersonaSise());
					personaEquivida.setSecPersonaEquivida(persona.getSecPersona());
					personaEquivida.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
					System.out.println("persona equivida:" + personaEquivida.toString());
					personaEquividaServicio.create(personaEquivida);
				}
			}

			// msheader
			// solo si exite un aceptdo extraprima o aceptado sin extraprima
			Collection<EstadoPersona> listaEstado = personaNatural.getEstadoPersonaCollection();
			boolean encontroEstadoParaHeader = false;
			if (listaEstado != null && !listaEstado.isEmpty()) {
				for (EstadoPersona estadoPersona : listaEstado) {
					if (estadoPersona.getTipoEstado().getCodEstado()
							.compareTo(Constantes.SEC_ESTADO_ACEPTADO_CON_EXTRAPRIMA) == 0
							|| estadoPersona.getTipoEstado().getCodEstado()
									.compareTo(Constantes.SEC_ESTADO_ACEPTADO_SIN_EXTRAPRIMA) == 0) {
						encontroEstadoParaHeader = true;
						break;
					}
				}
			}

			if (encontroEstadoParaHeader) {
				insertarWsSiseMsHeader(dto.getIdPersonaSise(), persona, personaNatural, nuevo, usuario, dto);
			}

			// ingresa telefonos y direciones
			if (persisteDirecion) {
				ingresarActualizarDireccionTelefono(persona, dto.getIdPersonaSise(), dto);
			}
		}

		return dto;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RespuestaSiseDto insertarWsSiseMpersonaJuridica(PersonaJuridica personaJuridica, Persona persona,
			boolean nuevo, String usuario, boolean persisteDirecion)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		RespuestaSiseDto dto = new RespuestaSiseDto();
		dto.setErrorLista(new ArrayList<String>());

		String txt_apellido1 = personaJuridica.getRazonSocial();
		String txt_apellido2 = null;
		String txt_nombre = null;
		int cod_tipo_doc = MPersonaUtil
				.obtenerTipoIdentificacion(personaJuridica.getTipoIdentificacion().getCodTipoIdentificacion());
		String nro_doc = personaJuridica.getIdentificacion();
		Date fec_nac = personaJuridica.getFchConstitucion();
		String txt_lugar_nac = null;
		String txt_sexo = "F";
		int cod_est_civil = 0;

		String txt_nombres_conyuge = null;
		String txt_apellidos_conyuge = null;
		int cod_tipo_doc_conyuge = MPersonaUtil.obtenerTipoIdentificacion('C');
		String nro_doc_conyugue = null;

		String campo_in_1 = null;
		String campo_in_2 = null;
		String campo_in_3 = null;
		String campo_in_4 = null;
		String campo_in_5 = null;

		System.out.println("ingresarPersonaNaturalSise....");

		wsDatosPersonaServicio.ingresarPersonaNaturalSise(txt_apellido1, txt_apellido2, txt_nombre, cod_tipo_doc,
				nro_doc, fec_nac, txt_lugar_nac, txt_sexo, cod_est_civil, txt_nombres_conyuge, txt_apellidos_conyuge,
				cod_tipo_doc_conyuge, nro_doc_conyugue, campo_in_1, campo_in_2, campo_in_3, campo_in_4, campo_in_5,
				dto);

		System.out.println("ingresarPersonaNaturalSise...FIN");

		if (dto.getIdPersonaSise() != null) {

			System.out.println("existeJuridica....");

			boolean existe = personaEquividaServicio.existeJuridica(persona.getSecPersona(),
					personaJuridica.getSecPersonaJuridica(), dto.getIdPersonaSise(), Constantes.COD_SISTEMA_SISE,
					Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);

			System.out.println("existeJuridica...FIN");

			// solo si no existe en las tablas de homologacion graba
			if (!existe) {
				if (persona.getSecPersona() != null && personaJuridica.getSecPersonaJuridica() != null) {
					PersonaEquivida personaEquivida = new PersonaEquivida();
					personaEquivida.setCodSistema(Constantes.COD_SISTEMA_SISE);
					personaEquivida.setCodTipoPersona(Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);
					personaEquivida.setIdPersonaDestino(dto.getIdPersonaSise());
					personaEquivida.setSecPersonaEquivida(persona.getSecPersona());
					personaEquivida.setSecPersonaJuridica(personaJuridica.getSecPersonaJuridica());
					System.out.println("persona equivida:" + personaEquivida.toString());

					personaEquividaServicio.create(personaEquivida);
				}
			}

			// ingresa telefonos y direciones
			if (persisteDirecion) {
				ingresarActualizarDireccionTelefono(persona, dto.getIdPersonaSise(), dto);
			}
		}

		System.out.println("FIN SISE");

		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.SiseServicio#ingresarActualizarDireccionTelefono(com.
	 * equivida.modelo.Persona, java.lang.Long, com.equivida.dto.RespuestaSiseDto)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void ingresarActualizarDireccionTelefono(Persona persona, Long personaIdSise, RespuestaSiseDto dto)
			throws RemoteException, ServiceException, ErrorIngresoWsSiseException {
		Collection<Direccion> direcciones = persona.getDireccionCollection();

		Collection<DireccionTelefono> direccionTelefonosGuardar = new ArrayList<DireccionTelefono>();

		log.info("telefonos");

		// guarda telefonos nuevos
		if (persona.getTelefonoCollection() != null && !persona.getTelefonoCollection().isEmpty()) {
			for (Telefono t : persona.getTelefonoCollection()) {
				log.info("ingresando telefonos de direccion sise");
				try {
					// for (DireccionTelefono direccionTelefono : direccion
					// .getDireccionTelefonoCollection()) {

					insertarWsSiseMpersonaTelef(personaIdSise, t, persona.getSecPersona(), dto);
					// }
				} catch (Exception e) {
					System.out.println("error en ingreso de direcciones sise:" + e);
					e.printStackTrace();
				}
			}
		}

		log.info("fin telefonos");

		log.info("direcciones");

		// guarda direcciones nuevas
		// for (Direccion dir : persona.getDireccionCollection()) {
		// if (dir.getSecDireccion() == null) {
		// direccionDao.save(dir);
		// } else {
		// direccionDao.update(dir);
		// }
		// }

		log.info("fin direcciones");

		// guarda direcciones en el sise y crea lista a guardar
		if (direcciones != null && !direcciones.isEmpty()) {
			for (Direccion dir : direcciones) {
				if (dir.getDireccionTelefonoCollection() != null && !dir.getDireccionTelefonoCollection().isEmpty()) {
					direccionTelefonosGuardar.addAll(dir.getDireccionTelefonoCollection());
				}
				try {
					insertarWsSiseMpersonaDir(personaIdSise, dir, persona.getSecPersona(), dto);
				} catch (Exception e) {
					System.out.println("error en ingreso de direcciones sise:" + e);
					e.printStackTrace();
				}
			}
		}

		log.info("fin teldir");

		log.info("direcciones electronicas");
		Collection<DireccionElectronica> direccionesElectronicas = persona
				.getDireccionElectronicaFormularioCollection();

		// Se pone este if para controlar que si se persista
		if (direccionesElectronicas != null && !direccionesElectronicas.isEmpty()
				&& dto.getCantonTMunicipioPrimeraDireccion() != null) {
			DireccionElectronica emailPrincipal = null;
			for (DireccionElectronica d : direccionesElectronicas) {
				if (d.getPrincipalTransient().booleanValue()) {
					emailPrincipal = d;
					break;
				}
				if (d.getActivo()) {
					emailPrincipal = d;
				}
			}
			if (emailPrincipal != null) {
				insertarWsSiseMpersonaDirElectronica(personaIdSise, emailPrincipal, persona.getSecPersona(), dto, true);
			}
		}

		log.info("fin direcciones electronicas");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.SiseServicio#insertarWsSiseMsHeader(java.lang.Long,
	 * com.equivida.modelo.Persona, com.equivida.modelo.PersonaNatural, boolean,
	 * java.lang.String, com.equivida.dto.RespuestaSiseDto)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void insertarWsSiseMsHeader(Long personaIdSise, Persona persona, PersonaNatural personaNatural,
			boolean nuevo, String usuario, RespuestaSiseDto dto) {

		MasegHeaderWsImplServiceLocator locator = new MasegHeaderWsImplServiceLocator();

		String address = Parametros.getString("location.web.service.sise.msheader");
		URL url;

		try {
			url = new URL(address);

			MasegHeaderWs servicio = locator.getMasegHeaderWsImplPort(url);

			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

			// parametros
			BigDecimal idPersona = BigDecimal.valueOf(personaIdSise);
			BigDecimal codFiguraAseg = BigDecimal.valueOf(2);
			BigDecimal codTipoAseg = BigDecimal.valueOf(2);// antes se enviaba 1
			BigDecimal codImpAseg = BigDecimal.valueOf(1);
			BigDecimal codTipoAgente = BigDecimal.valueOf(3);
			BigDecimal codAgente = new BigDecimal(1110);

			String fechaAlta = null;
			if (nuevo) {
				fechaAlta = formato.format(new Date());
			} else {
				// TODO verificar fecha cuando es modificacion
				fechaAlta = formato.format(personaNatural.getTsCreacion());
			}

			String fechaBaja = null;

			BigDecimal codigoOcupacion = BigDecimal.valueOf(personaNatural.getOcupacion().getCodOcupacion());
			Integer avisoVto = -1;
			String codAsegVinc = "0";
			String fechaUltMod = formato.format(new Date());
			String codUsuario = usuario;
			String nombFactura = personaNatural.getNombresApellidos();
			BigDecimal tazaFianzas = BigDecimal.valueOf(0);
			Integer consorcio = -1;
			BigDecimal codMoneda = new BigDecimal(1);
			BigDecimal impSueldo = personaNatural.getIngresoMensual().getMntIngresoMensual();

			BigDecimal codDeporte = new BigDecimal(0);
			if (personaNatural.getDeportePracticaCollection() != null
					&& personaNatural.getDeportePracticaCollection().size() > 0) {
				List<DeportePractica> listaDP = new ArrayList<DeportePractica>(
						personaNatural.getDeportePracticaCollection());
				DeportePractica dp = listaDP.get(0);
				codDeporte = new BigDecimal(dp.getDeporte().getCodDeporte());
			}

			String edificio = "5";
			String urbanizacion = "5";
			String sector = "5";
			String txt_nombres_conyuge = null;
			String txt_apellidos_conyuge = null;
			BigDecimal cod_tipo_doc_conyuge = BigDecimal.valueOf(MPersonaUtil.obtenerTipoIdentificacion('C'));
			String nro_doc_conyugue = null;

			if (personaNatural.isConConyuge()) {

				PersonaNatural conyuge = personaNatural.getConyuge();
				// entonces debe tener ingresado ocnyuge
				if (conyuge != null) {
					txt_nombres_conyuge = conyuge.getNombres();
					txt_apellidos_conyuge = conyuge.getApellidos();
					cod_tipo_doc_conyuge = BigDecimal.valueOf(MPersonaUtil
							.obtenerTipoIdentificacion(conyuge.getTipoIdentificacion().getCodTipoIdentificacion()));
					nro_doc_conyugue = conyuge.getIdentificacion();
				}
			}

			String campoIn1 = null;
			String campoIn2 = null;
			String campoIn3 = null;
			String campoIn4 = null;
			String campoIn5 = null;
			// fin parametros

			MasegHeader repuesta = servicio.ws_sise_insert_maseg_header_edm(idPersona, codFiguraAseg, codTipoAseg,
					codImpAseg, codTipoAgente, codAgente, fechaAlta, fechaBaja, codigoOcupacion, avisoVto, codAsegVinc,
					fechaUltMod, codUsuario, nombFactura, tazaFianzas, consorcio, codMoneda, impSueldo, codDeporte,
					edificio, urbanizacion, sector, txt_nombres_conyuge, txt_apellidos_conyuge, cod_tipo_doc_conyuge,
					nro_doc_conyugue, campoIn1, campoIn2, campoIn3, campoIn4, campoIn5);

			if (repuesta.getMsgError() != null) {
				dto.getErrorLista().add(repuesta.getMsgError());
			} else {
				dto.setCodAseguridadoSise(repuesta.getCodAseg().longValue());
			}

			System.out.println("MSHEADER numfilas:" + repuesta.getNumFilas());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void insertarWsSiseMpersonaTelef(Long idPersonaSise, Telefono telefono, Integer personaEquividaId,
			RespuestaSiseDto dto) throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		String campo_in_1 = null;
		String campo_in_2 = null;
		String campo_in_3 = null;
		String campo_in_4 = null;
		String campo_in_5 = null;

		log.info(">>>>>>>>>>>>>>>>>>>> obtiene tipo: ");
		int cod_tipo_telefono = MPersonaTelUtil.obtenerTipoTelefono(telefono.getTipoTelefono().getCodTipoTelefono(),
				telefono.getPrincipal() != null ? telefono.getPrincipal() : false);

		log.info(">>>>>>>>>>>>>>>>>>>> idSiseTelefono: ");

		Long idSiseTelefono = wsDatosPersonaServicio.ingresarPersonaTelefonoSise(idPersonaSise, cod_tipo_telefono,
				telefono.getTelefonoConCodigoArea(), campo_in_1, campo_in_2, campo_in_3, campo_in_4, campo_in_5, dto);

		log.info("<<<<<<<<<<<< idSiseTelefono: " + idSiseTelefono);

		if (idSiseTelefono != null) {

			boolean existe = telefonoEquividaServicio.existe(personaEquividaId, telefono.getSecTelefono(),
					idSiseTelefono.intValue(), idPersonaSise.intValue(), Constantes.COD_SISTEMA_SISE);

			if (!existe) {
				TelefonoEquivida telefonoEquivida = new TelefonoEquivida();
				telefonoEquivida.setCodSistema(Constantes.COD_SISTEMA_SISE);
				telefonoEquivida.setIdPersonaDestino(idPersonaSise.intValue());
				telefonoEquivida.setSecTelefonoDestino(idSiseTelefono.intValue());
				telefonoEquivida.setSecTelefonoEquivida(telefono.getSecTelefono());
				telefonoEquivida.setSecPersonaEquivida(personaEquividaId);
				telefonoEquividaServicio.create(telefonoEquivida);
				System.out.println("telefono homologacion:" + telefonoEquivida.toString());
			}

		}
	}

	private void insertarWsSiseContratanteTelef(Long idPersonaSise, Telefono telefono, Integer personaEquividaId,
			RespuestaSiseDto dto) throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		int cod_tipo_telefono = MPersonaTelUtil.obtenerTipoTelefono(telefono.getTipoTelefono().getCodTipoTelefono(),
				telefono.getPrincipal() != null ? telefono.getPrincipal() : false);
		log.info(">>>>>>>>>>>>>>>>>>>> obtiene tipo: " + cod_tipo_telefono);
		log.info("numero: " + telefono.getTelefonoConCodigoArea());

		Long idSiseTelefono = wsDatosPersonaServicio.ingresarContratanteTelefonoSise(idPersonaSise, cod_tipo_telefono,
				telefono.getTelefonoConCodigoArea(), dto);
		log.info(">>>>>>>>>>>>>>>>>>>> idSiseTelefono: " + idSiseTelefono);

		if (idSiseTelefono != null) {

			boolean existe = telefonoEquividaServicio.existe(personaEquividaId, telefono.getSecTelefono(),
					idSiseTelefono.intValue(), idPersonaSise.intValue(), Constantes.COD_SISTEMA_SISE);

			if (!existe) {
				TelefonoEquivida telefonoEquivida = new TelefonoEquivida();
				telefonoEquivida.setCodSistema(Constantes.COD_SISTEMA_SISE);
				telefonoEquivida.setIdPersonaDestino(idPersonaSise.intValue());
				telefonoEquivida.setSecTelefonoDestino(idSiseTelefono.intValue());
				telefonoEquivida.setSecTelefonoEquivida(telefono.getSecTelefono());
				telefonoEquivida.setSecPersonaEquivida(personaEquividaId);
				telefonoEquividaServicio.create(telefonoEquivida);
				System.out.println("telefono homologacion:" + telefonoEquivida.toString());
			}

		}
	}

	private void insertarWsSiseMpersonaDirElectronica(Long idPersonaSise, DireccionElectronica direccionElectronica,
			Integer personaEquividaId, RespuestaSiseDto dto, boolean enviarDosCodigos)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		if (dto.getCantonTMunicipioPrimeraDireccion() == null) {
			String mensaje = "no se puede ingresar direcciones electronicas, no se encontro canton municipio sise";
			dto.getErrorLista().add(mensaje);
			log.error(mensaje);
			return;
		}

		System.out.println("DIRECCION ELECTRONICA CANTON T MUNICIPIO DPTO:"
				+ dto.getCantonTMunicipioPrimeraDireccion().getCodDpto().intValue());
		System.out.println("DIRECCION ELECTRONICA CANTON T MUNICIPIO PAIS:"
				+ dto.getCantonTMunicipioPrimeraDireccion().getCodPais().intValue());

		int cod_tipo_dir = MPersonaDirUtil.COD_EMAIL_SISE;
		int cod_tipo_dir2 = MPersonaDirUtil.COD_EMAIL_SISE_2;
		String txt_direccion = direccionElectronica.getDireccionElectronica();

		int cod_municipio = dto.getCantonTMunicipioPrimeraDireccion().getCodMunicipio().intValue();
		int cod_dpto = dto.getCantonTMunicipioPrimeraDireccion().getCodDpto().intValue();
		int cod_pais = dto.getCantonTMunicipioPrimeraDireccion().getCodPais().intValue();

		String campoin1 = null;
		String campoin2 = null;
		String campoin3 = null;
		String campoin4 = null;
		String campoin5 = null;

		Long idDireccionElectronicaSise = wsDatosPersonaServicio.ingresarPersonaDireccionSise(idPersonaSise,
				cod_tipo_dir, txt_direccion, cod_municipio, cod_dpto, cod_pais, null, null, null, campoin1, campoin2,
				campoin3, campoin4, campoin5, dto);

		if (enviarDosCodigos) {
			// se envia por segunda vez ahora con codigo 10, correo santiago nivelo
			// 27/feb/2018
			wsDatosPersonaServicio.ingresarPersonaDireccionSise(idPersonaSise, cod_tipo_dir2, txt_direccion,
					cod_municipio, cod_dpto, cod_pais, null, null, null, campoin1, campoin2, campoin3, campoin4,
					campoin5, dto);
		}

		if (idDireccionElectronicaSise != null) {

			boolean existe = direccionElectronicaEquividaServicio.existe(personaEquividaId,
					direccionElectronica.getSecDireccionElectronica(), idDireccionElectronicaSise.intValue(),
					idPersonaSise.intValue(), Constantes.COD_SISTEMA_SISE);

			if (!existe) {
				if (idPersonaSise != null && idDireccionElectronicaSise != null
						&& direccionElectronica.getSecDireccionElectronica() != null && personaEquividaId != null) {
					DireccionElectronicaEquivida direccionElectronicaEquivida = new DireccionElectronicaEquivida();
					direccionElectronicaEquivida.setCodSistema(Constantes.COD_SISTEMA_SISE);
					direccionElectronicaEquivida.setIdPersonaDestino(idPersonaSise.intValue());
					direccionElectronicaEquivida.setSecDireccionElectDestino(idDireccionElectronicaSise.intValue());
					direccionElectronicaEquivida
							.setSecDireccionElectEquivida(direccionElectronica.getSecDireccionElectronica());
					direccionElectronicaEquivida.setSecPersonaEquivida(personaEquividaId);
					direccionElectronicaEquividaServicio.create(direccionElectronicaEquivida);
					System.out.println("direccion homologacion:" + direccionElectronicaEquivida.toString());
				}
			}
		}
	}

	private void insertarWsSiseMpersonaDir(Long idPersonaSise, Direccion direccion, Integer personaEquividaId,
			RespuestaSiseDto dto) throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		int cod_tipo_dir = MPersonaDirUtil.obtenerTipoDireccion(direccion.getTipoDireccion().getCodTipoDireccion(),
				direccion.getDireccionPrincipal());

		String txt_direccion = direccion.getDireccionParaSise();

		short secCanton = direccion.getCanton().getSecCanton();

		CantonTMunicipio cantonTMunicipio;
		try {
			System.out.println("buscando canton");

			cantonTMunicipio = cantonTMunicipioServicio.obtenerPorSecCanton(secCanton);
			System.out.println("canton:" + cantonTMunicipio);

			// pone en el dto para luego estos datos en direcciones electronicas
			if (dto.getCantonTMunicipioPrimeraDireccion() == null) {
				dto.setCantonTMunicipioPrimeraDireccion(cantonTMunicipio);
			}

			int cod_municipio = cantonTMunicipio.getCodMunicipio().intValue();
			int cod_dpto = cantonTMunicipio.getCodDpto().intValue();
			int cod_pais = cantonTMunicipio.getCodPais().intValue();

			String txt_edificio = direccion.getEdificio();
			String txt_urbanizacion = direccion.getBarrio();
			String txt_sector = direccion.getReferencia();
			String campoin1 = null;
			String campoin2 = null;
			String campoin3 = null;
			String campoin4 = null;
			String campoin5 = null;

			Long idDireccionSise = wsDatosPersonaServicio.ingresarPersonaDireccionSise(idPersonaSise, cod_tipo_dir,
					txt_direccion, cod_municipio, cod_dpto, cod_pais, txt_edificio, txt_urbanizacion, txt_sector,
					campoin1, campoin2, campoin3, campoin4, campoin5, dto);

			log.info("id direccion sise: " + idDireccionSise);

			if (idDireccionSise != null) {

				boolean existe = direccionEquividaServicio.existe(personaEquividaId, direccion.getSecDireccion(),
						idDireccionSise.intValue(), idPersonaSise.intValue(), Constantes.COD_SISTEMA_SISE);

				if (!existe) {
					if (direccion.getSecDireccion() != null && idPersonaSise != null && idDireccionSise != null
							&& personaEquividaId != null) {
						DireccionEquivida direccionEquivida = new DireccionEquivida();
						direccionEquivida.setCodSistema(Constantes.COD_SISTEMA_SISE);
						direccionEquivida.setIdPersonaDestino(idPersonaSise.intValue());
						direccionEquivida.setSecDireccionDestino(idDireccionSise.intValue());
						direccionEquivida.setSecDireccionEquivida(direccion.getSecDireccion());
						direccionEquivida.setSecPersonaEquivida(personaEquividaId);
						direccionEquividaServicio.create(direccionEquivida);
						System.out.println("direccion homologacion:" + direccionEquivida.toString());
					}
				}

			}

		} catch (NoEncuentraDatosException e) {
			log.error("no se pudo ingresar direccion. ERROR: " + e);
		}
	}

	private void insertarWsSiseContratanteDir(Long idPersonaSise, Direccion direccion, Integer personaEquividaId,
			RespuestaSiseDto dto) throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		int cod_tipo_dir = MPersonaDirUtil.obtenerTipoDireccion(direccion.getTipoDireccion().getCodTipoDireccion(),
				direccion.getDireccionPrincipal());

		String txt_direccion = direccion.getDireccionParaSise();

		short secCanton = direccion.getCanton().getSecCanton();

		CantonTMunicipio cantonTMunicipio;
		try {
			System.out.println("buscando canton");

			cantonTMunicipio = cantonTMunicipioServicio.obtenerPorSecCanton(secCanton);
			System.out.println("canton:" + cantonTMunicipio);

			// pone en el dto para luego estos datos en direcciones electronicas
			if (dto.getCantonTMunicipioPrimeraDireccion() == null) {
				dto.setCantonTMunicipioPrimeraDireccion(cantonTMunicipio);
			}

			int cod_municipio = cantonTMunicipio.getCodMunicipio().intValue();
			int cod_dpto = cantonTMunicipio.getCodDpto().intValue();
			int cod_pais = cantonTMunicipio.getCodPais().intValue();

			log.info("txt_direccion: " + txt_direccion);

			Long idDireccionSise = wsDatosPersonaServicio.ingresarContratanteDireccionSise(idPersonaSise, cod_tipo_dir,
					txt_direccion, cod_municipio, cod_dpto, cod_pais, dto);

			log.info("id direccion sise: " + idDireccionSise);

			if (idDireccionSise != null) {

				boolean existe = direccionEquividaServicio.existe(personaEquividaId, direccion.getSecDireccion(),
						idDireccionSise.intValue(), idPersonaSise.intValue(), Constantes.COD_SISTEMA_SISE);

				if (!existe) {
					if (direccion.getSecDireccion() != null && idPersonaSise != null && idDireccionSise != null
							&& personaEquividaId != null) {
						DireccionEquivida direccionEquivida = new DireccionEquivida();
						direccionEquivida.setCodSistema(Constantes.COD_SISTEMA_SISE);
						direccionEquivida.setIdPersonaDestino(idPersonaSise.intValue());
						direccionEquivida.setSecDireccionDestino(idDireccionSise.intValue());
						direccionEquivida.setSecDireccionEquivida(direccion.getSecDireccion());
						direccionEquivida.setSecPersonaEquivida(personaEquividaId);
						direccionEquividaServicio.create(direccionEquivida);
						System.out.println("direccion homologacion:" + direccionEquivida.toString());
					}
				}

			}

		} catch (NoEncuentraDatosException e) {
			log.error("no se pudo ingresar direccion. ERROR: " + e);
		}
	}

	@Override
	public List<RsConductoPago> consultarConductosDePago(long personaIdSise) {

		ConductaPagoWsImplServiceLocator locator = new ConductaPagoWsImplServiceLocator();

		String address = Parametros.getString("location.web.service.sise.conductos.de.pago");
		URL url;

		List<RsConductoPago> listaR = new ArrayList<RsConductoPago>();

		try {
			url = new URL(address);

			ConductoPagoWs servicio = locator.getConductaPagoWsImplPort(url);

			RsConductoPago[] arreglo = servicio.ws_sise_lista_conductos(BigDecimal.valueOf(personaIdSise));

			for (RsConductoPago rsConductoPago : arreglo) {
				listaR.add(rsConductoPago);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaR;
	}

	@Override
	public List<RsCumulosDePago> consultar(String cedula) {

		List<RsCumulosDePago> listaR = null;

		if (cedula != null && !cedula.trim().equals("")) {

			listaR = new ArrayList<RsCumulosDePago>();

			CumulosDePagoWsImplServiceLocator locator = new CumulosDePagoWsImplServiceLocator();

			String address = Parametros.getString("location.web.service.sise.consulta.cumulos.polizas");
			URL url;

			try {
				url = new URL(address);
				CumulosDePagoWs servicio = locator.getCumulosDePagoWsImplPort(url);

				RsCumulosDePago[] lista = servicio.consultar(cedula);

				for (RsCumulosDePago rs : lista) {
					System.out.println(rs.getPoliza());
					listaR.add(rs);
				}

			} catch (ServiceException e) {
				e.printStackTrace();
				return null;
			} catch (RemoteException e) {
				e.printStackTrace();
				return null;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		return listaR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.SiseServicio#insertarContratante(com.equivida.sise.dto.
	 * ContratanteInDto)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RsContratante insertarContratante(ContratanteDto dtoIn) throws ContratanteException {
		URL url;
		try {
			String address = Parametros.getString("location.web.service.sise.contratante");
			url = new URL(address);

			System.out.println("url servicio web: " + address);

			ContratanteWsImplServiceLocator locator = new ContratanteWsImplServiceLocator();
			ContratantesWs servicio = locator.getContratanteWsImplPort(url);

			RsContratante response = servicio.ws_sise_sp_contratante(dtoIn);

			return response;
		} catch (ServiceException e) {
			throw new ContratanteException(e.getMessage(), e.getCause());
		} catch (RemoteException e) {
			throw new ContratanteException(e.getMessage(), e.getCause());
		} catch (MalformedURLException e) {
			throw new ContratanteException(e.getMessage(), e.getCause());
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String consultarRazonSocial(String documento) throws RazonSocialException {
		URL url;
		String address = Parametros.getString("location.web.service.sise.razonsocial");
		String res = null;
		System.out.println("--------------------------------------address: " + address);

		try {
			url = new URL(address + "/" + documento);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;
			while ((line = in.readLine()) != null) {
				JSONObject obj = new JSONObject(line);
				res = obj.get("RazonSocial").toString();

			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void ingresarActualizarDireccionTelefonoContratante(Persona persona, Long personaIdSise,
			RespuestaSiseDto dto) throws RemoteException, ServiceException, ErrorIngresoWsSiseException {

		Collection<Direccion> direcciones = persona.getDireccionCollection();

		Collection<Telefono> telefonos = persona.getTelefonoCollection();

		Collection<DireccionTelefono> direccionTelefonosGuardar = new ArrayList<DireccionTelefono>();

		log.info("telefonos");

		// guarda telefonos nuevos
		if (telefonos != null && !telefonos.isEmpty()) {
			for (Telefono t : telefonos) {
				try {
					// for (DireccionTelefono direccionTelefono : direccion
					// .getDireccionTelefonoCollection()) {
					System.out.println("sec_telefono:" + t.getSecTelefono());
					insertarWsSiseContratanteTelef(personaIdSise, t, persona.getSecPersona(), dto);
					// insertarWsSiseMpersonaTelef(personaIdSise, t, persona.getSecPersona(), dto);
					// }
				} catch (Exception e) {
					System.out.println("error en ingreso de direcciones sise:" + e);
					e.printStackTrace();
				}
			}
		}

		log.info("fin telefonos");

		// guarda direcciones en el sise y crea lista a guardar
		if (direcciones != null && !direcciones.isEmpty()) {
			for (Direccion dir : direcciones) {
				if (dir.getDireccionTelefonoCollection() != null && !dir.getDireccionTelefonoCollection().isEmpty()) {
					direccionTelefonosGuardar.addAll(dir.getDireccionTelefonoCollection());
				}
				try {
					insertarWsSiseContratanteDir(personaIdSise, dir, persona.getSecPersona(), dto);
				} catch (Exception e) {
					System.out.println("error en ingreso de direcciones sise:" + e);
					e.printStackTrace();
				}
			}
		}

		log.info("celular transient");
		if (persona.getNoCelularSoloTra() != null && persona.getNoCelularSoloTra().getSecTelefono() != null) {

			// verifica si ya se envio antes
			// ya se ha envido o no
			boolean yaEnviado = false;

			if (telefonos != null && !telefonos.isEmpty()) {

				for (Telefono t : telefonos) {

					if (persona.getNoCelularSoloTra().getSecTelefono().equals(t.getSecTelefono())) {
						yaEnviado = true;
						System.out.println("ya enviado");
						break;
					}

				}
			}

			if (!yaEnviado) {
				try {
					System.out.println("sec_telefono:" + persona.getNoCelularSoloTra().getSecTelefono());
					insertarWsSiseContratanteTelef(personaIdSise, persona.getNoCelularSoloTra(),
							persona.getSecPersona(), dto);
				} catch (Exception e) {
					System.out.println("error en ingreso de direcciones sise:" + e);
					e.printStackTrace();
				}
			}

		}
		log.info("fin celular transient");

		log.info("inicio teldir");
		if (!direccionTelefonosGuardar.isEmpty()) {
			for (DireccionTelefono dirTel : direccionTelefonosGuardar) {

				// ya se ha envido o no
				boolean yaEnviado = false;

				if (telefonos != null && !telefonos.isEmpty()) {

					for (Telefono t : telefonos) {
						if (dirTel.getTelefono().getNroTelefono().equals(t.getNroTelefono())) {
							yaEnviado = true;
							break;
						}
					}
				}
				
				if (yaEnviado) {
					continue;
				}
				try {
					// for (DireccionTelefono direccionTelefono : direccion
					// .getDireccionTelefonoCollection()) {
					insertarWsSiseContratanteTelef(personaIdSise, dirTel.getTelefono(), persona.getSecPersona(), dto);
					// insertarWsSiseMpersonaTelef(personaIdSise, t, persona.getSecPersona(), dto);
					// }
				} catch (Exception e) {
					System.out.println("error en ingreso de direcciones sise:" + e);
					e.printStackTrace();
				}
			}
		}

		log.info("fin teldir");

		log.info("direcciones electronicas - facturacion electronica");
		DireccionElectronica emailFac = persona.getEmailFacturacionElectronicaTra();
		if (emailFac != null && emailFac.getDireccionElectronica() != null) {
			insertarWsSiseMpersonaDirElectronica(personaIdSise, emailFac, persona.getSecPersona(), dto, false);
			log.info("fin direcciones electronicas");
		}
	}

	@Override
	public void insertarWsSiseMsHeaderPJ(Long personaIdSise, Persona persona, PersonaJuridica personaJuridica,
			boolean nuevo, String usuario, RespuestaSiseDto dto) {

		MasegHeaderWsImplServiceLocator locator = new MasegHeaderWsImplServiceLocator();

		String address = Parametros.getString("location.web.service.sise.msheader");
		URL url;

		try {
			url = new URL(address);

			MasegHeaderWs servicio = locator.getMasegHeaderWsImplPort(url);

			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

			// parametros
			BigDecimal idPersona = BigDecimal.valueOf(personaIdSise);
			BigDecimal codFiguraAseg = BigDecimal.valueOf(2);
			BigDecimal codTipoAseg = BigDecimal.valueOf(2);// antes se enviaba 1
			BigDecimal codImpAseg = BigDecimal.valueOf(1);
			BigDecimal codTipoAgente = BigDecimal.valueOf(3);
			BigDecimal codAgente = new BigDecimal(1110);

			String fechaAlta = null;
			if (nuevo) {
				fechaAlta = formato.format(new Date());
			} else {
				// TODO verificar fecha cuando es modificacion
				fechaAlta = formato.format(personaJuridica.getTsCreacion());
			}

			String fechaBaja = null;

			BigDecimal codigoOcupacion = BigDecimal.ONE;// correo septiembre 2019 a Katy
			Integer avisoVto = -1;
			String codAsegVinc = "0";
			String fechaUltMod = formato.format(new Date());
			String codUsuario = usuario;
			String nombFactura = personaJuridica.getRazonSocial();
			BigDecimal tazaFianzas = BigDecimal.valueOf(0);
			Integer consorcio = -1;
			BigDecimal codMoneda = new BigDecimal(1);
			BigDecimal impSueldo = BigDecimal.ZERO;// personaJuridica.getIngresoMensual().getMntIngresoMensual();

			BigDecimal codDeporte = new BigDecimal(0);

			String edificio = "5";
			String urbanizacion = "5";
			String sector = "5";
			String txt_nombres_conyuge = null;
			String txt_apellidos_conyuge = null;
			BigDecimal cod_tipo_doc_conyuge = BigDecimal.valueOf(MPersonaUtil.obtenerTipoIdentificacion('C'));
			String nro_doc_conyugue = null;

			String campoIn1 = null;
			String campoIn2 = null;
			String campoIn3 = null;
			String campoIn4 = null;
			String campoIn5 = null;
			// fin parametros

			MasegHeader repuesta = servicio.ws_sise_insert_maseg_header_edm(idPersona, codFiguraAseg, codTipoAseg,
					codImpAseg, codTipoAgente, codAgente, fechaAlta, fechaBaja, codigoOcupacion, avisoVto, codAsegVinc,
					fechaUltMod, codUsuario, nombFactura, tazaFianzas, consorcio, codMoneda, impSueldo, codDeporte,
					edificio, urbanizacion, sector, txt_nombres_conyuge, txt_apellidos_conyuge, cod_tipo_doc_conyuge,
					nro_doc_conyugue, campoIn1, campoIn2, campoIn3, campoIn4, campoIn5);

			if (repuesta.getMsgError() != null) {
				dto.getErrorLista().add(repuesta.getMsgError());
			} else {
				dto.setCodAseguridadoSise(repuesta.getCodAseg().longValue());
			}

			System.out.println("MSHEADER numfilas:" + repuesta.getNumFilas());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}