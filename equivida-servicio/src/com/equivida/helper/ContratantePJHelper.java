/**
 * 
 */
package com.equivida.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.xml.rpc.ServiceException;

import org.hornetq.utils.json.JSONException;
import org.hornetq.utils.json.JSONObject;

import com.equivida.constant.Constantes;
import com.equivida.constant.EsClienteEnum;
import com.equivida.constant.PersonaRechazoListaReservadaEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoPersonaEnum;
import com.equivida.dto.GeocodeAddressLW;
import com.equivida.dto.MensajeRcsDto;
import com.equivida.dto.RespuestaSiseDto;
import com.equivida.exception.ContratanteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.exception.RcsException;
import com.equivida.homologacion.modelo.PersonaEquivida;
import com.equivida.homologacion.servicio.PersonaEquividaServicio;
import com.equivida.modelo.Canton;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.IngresoMensualAdicional;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Profesion;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoRiesgo;
import com.equivida.servicio.ActividadEconomicaServicio;
import com.equivida.servicio.CantonServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.SiseServicio;
import com.equivida.sise.ws.client.ContratanteDto;
import com.equivida.sise.ws.client.RsContratante;
import com.equivida.smartdata.constante.TipoDireccionEnum;
import com.equivida.util.CedulaValidator;
import com.equivida.util.LWResources;
import com.equivida.util.ValidatorUtils;
import com.equivida.util.webservice.MPersonaUtil;

/**
 * @author Daniel Cardenas
 *
 */
public class ContratantePJHelper {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private PersonaJuridica personaJuridica;
	private RcsServicio rcsServicio;
	private String remoteAddr;
	private String remoteUser;
	private boolean nuevo;
	private PersonaNaturalServicio personaNaturalServicio;
	private SiseServicio siseServicio;
	private CantonServicio cantonServicio;
	private PersonaEquividaServicio personaEquividaServicio;
	private ActividadEconomicaServicio actividadEconomicaServicio;

	public ContratantePJHelper() {
	}

	public ContratantePJHelper(PersonaJuridica personaJuridica, RcsServicio rcsServicio, String remoteAddr,
			String remoteUser, boolean nuevo, PersonaNaturalServicio personaNaturalServicio, SiseServicio siseServicio,
			CantonServicio cantonServicio, PersonaEquividaServicio personaEquividaServicio,
			ActividadEconomicaServicio actividadEconomicaServicio) {
		super();
		this.personaJuridica = personaJuridica;
		this.rcsServicio = rcsServicio;
		this.remoteAddr = remoteAddr;
		this.remoteUser = remoteUser;
		this.nuevo = nuevo;
		this.personaNaturalServicio = personaNaturalServicio;
		this.siseServicio = siseServicio;
		this.cantonServicio = cantonServicio;
		this.personaEquividaServicio = personaEquividaServicio;
		this.actividadEconomicaServicio = actividadEconomicaServicio;
	}

	/**
	 * Se guarda en tabla persona_equivida.
	 * 
	 * @param idPersonaSise
	 */
	public void grabarPersonaEquivida(Long idPersonaSise) {
		System.out.println("10.1 Se llama persiste persona_equivida------------------------------------");
		boolean existe = personaEquividaServicio.existeJuridicaNoTx(personaJuridica.getPersona().getSecPersona(),
				personaJuridica.getSecPersonaJuridica(), idPersonaSise, Constantes.COD_SISTEMA_SISE,
				Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);

		System.out.println("10.2 Se llama persiste persona_equivida------------------------------------: " + existe);
		// solo si no existe en las tablas de homologacion graba
		if (!existe) {
			// solo si tiene sec persona se envia a guardar
			if (personaJuridica.getPersona().getSecPersona() != null) {
				System.out.println("10.3 Se llama persiste persona_equivida------------------------------------");
				PersonaEquivida personaEquivida = new PersonaEquivida();
				personaEquivida.setCodSistema(Constantes.COD_SISTEMA_SISE);
				personaEquivida.setCodTipoPersona(Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);
				personaEquivida.setIdPersonaDestino(idPersonaSise);
				personaEquivida.setSecPersonaEquivida(personaJuridica.getPersona().getSecPersona());
				personaEquivida.setSecPersonaJuridica(personaJuridica.getSecPersonaJuridica());
				System.out.println("persona equivida:" + personaEquivida.toString());

				personaEquividaServicio.createNoTx(personaEquivida);
			}
		}
	}

	/**
	 * Integra la información mediante los WS de SISE
	 */
	public RespuestaSiseDto integrarSise() throws ContratanteException {
		RespuestaSiseDto dto = null;
		try {
			// BigDecimal resp = null;

			// 1. Se persiste utilizando SP de Contratante
			ContratanteDto dtoIn = new ContratanteDto();
			armarDtoIntegracionContratantePJSise(dtoIn);

			System.out.println("8.1 Se llama a SP SISE rsContratante PJ");
			RsContratante rsContratante = siseServicio.insertarContratante(dtoIn);
			System.out
					.println("-----------------------------EL ID RECUPERADO ES: " + rsContratante.getId_persona_wkf());
			System.out
					.println("-----------------------------EL ID RECUPERADO ES: " + rsContratante.getId_proceso_wkf());

			if (rsContratante.getId_persona_wkf() != null) {

				// 2. Se arma un OBjeto DTO que se envia para la persistencia de telefonos y
				// direcciones
				dto = new RespuestaSiseDto();
				dto.setIdPersonaSise(rsContratante.getId_persona_wkf().longValue());

				// Se persisten telefonos y direcciones
				System.out.println("8.2 Se llama a SP SISE direcciones y telefonos");
				siseServicio.ingresarActualizarDireccionTelefonoContratante(personaJuridica.getPersona(),
						dto.getIdPersonaSise(), dto);

				// 3. Se persiste msheader solo si exite un aceptdo extraprima o aceptado sin
				// extraprima
//				Collection<EstadoPersona> listaEstado = personaNatural.getEstadoPersonaCollection();
//				System.out.println("8.3 Se obtiene listado de estaods de la ṕersonanatural");
//				boolean encontroEstadoParaHeader = false;
//				if (listaEstado != null && !listaEstado.isEmpty()) {
//					System.out.println("8.4 Si hay estado...");
//					for (EstadoPersona estadoPersona : listaEstado) {
//						if (estadoPersona.getTipoEstado().getCodEstado()
//								.compareTo(Constantes.SEC_ESTADO_ACEPTADO_CON_EXTRAPRIMA) == 0
//								|| estadoPersona.getTipoEstado().getCodEstado()
//										.compareTo(Constantes.SEC_ESTADO_ACEPTADO_SIN_EXTRAPRIMA) == 0) {
//							encontroEstadoParaHeader = true;
//							break;
//						}
//					}
//				}
				// if (encontroEstadoParaHeader) {
				System.out.println("8.5 Siempre Se llama a persistir el header...");
				System.out.println("id persona sise: " + dto.getIdPersonaSise());
				System.out.println("razon social  persona juridica: " + personaJuridica.getRazonSocial());
				siseServicio.insertarWsSiseMsHeaderPJ(dto.getIdPersonaSise(), personaJuridica.getPersona(),
						personaJuridica, nuevo, remoteUser, dto);
				if (dto.getErrorLista().isEmpty()) {
					System.out.println("id aseg:" + dto.getCodAseguridadoSise());
				} else {
					System.out.println("Error al guardar msheader: " + dto.getErrorLista().get(0));
				}
				System.out.println("8.6 FIN Se llama a SP SISE MsHeader");

			}

			System.out.println("8.7 ------------------------------- FIN integracion con SISE contratante: "
					+ rsContratante.getId_persona_wkf());

			return dto;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ContratanteException(e.getCause());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ContratanteException(e.getCause());
		} catch (ErrorIngresoWsSiseException e) {
			e.printStackTrace();
			throw new ContratanteException(e.getCause());
		}
	}

	/**
	 * Se arma el objeto que permite integrar con SISE contratante.
	 * 
	 * @param dtoIn
	 */
	private void armarDtoIntegracionContratantePJSise(ContratanteDto dtoIn) {
		Direccion domicilio = getDireccionDomicilio();
		if (domicilio == null) {
			domicilio = getDireccionTrabajo();
		}

		dtoIn.setTxt_apellido1(personaJuridica.getRazonSocial());
		dtoIn.setCod_tipo_doc(BigDecimal.valueOf(2)); // En base a correo de Kathy, indica que para persona natural vaya
														// 1

		dtoIn.setNro_nit(personaJuridica.getIdentificacion()); // Aquí va el RUC
		// dtoIn.setNro_doc(personaJuridica.getIdentificacion()); // null
		dtoIn.setCod_tipo_iva(BigDecimal.ONE);
		// dtoIn.setFec_nac(sdf.format(personaNatural.getFchNacimiento()));
		// dtoIn.setTxt_lugar_nac(personaNatural.getCiudadNacimiento().getCiudad());
		dtoIn.setTxt_sexo("M");// null da error, Katy soicita enviar M hasta que cambien en el SP
		dtoIn.setCod_est_civil(BigDecimal.ONE);// null da error, Katy soicita enviar 1 hasta que cambien en el SP
		dtoIn.setCod_tipo_persona(TipoPersonaEnum.J.getCodSise());

		// si es no disponble (cero en CU) envia 1 al sise, correo de katy flores,
		// septiembre 2019
		if (personaJuridica.getTipoPersonaJuridica().getCodTipoPersonaJuridica().shortValue() == 0) {
			dtoIn.setCod_tipo_soc(BigDecimal.ONE);
		} else {
			dtoIn.setCod_tipo_soc(
					BigDecimal.valueOf(personaJuridica.getTipoPersonaJuridica().getCodTipoPersonaJuridica()));
		}
		dtoIn.setCod_sector_mercado(BigDecimal.valueOf(personaJuridica.getSectorMercado().getCodSectorMercado()));
		dtoIn.setCod_tipo_empresa(BigDecimal.ONE);

		dtoIn.setTxt_nombres_rep_legal(personaJuridica.getRepresentante().getPersonaNaturalTransient().getNombres());
		dtoIn.setTxt_apellidos_rep_legal(
				personaJuridica.getRepresentante().getPersonaNaturalTransient().getApellidos());

		int cod_tipo_doc_rep = MPersonaUtil.obtenerTipoIdentificacion(personaJuridica.getRepresentante()
				.getPersonaNaturalTransient().getTipoIdentificacion().getCodTipoIdentificacion());

		dtoIn.setCod_tipo_doc_rep_legal(BigDecimal.valueOf(cod_tipo_doc_rep));
		dtoIn.setNro_doc_rep_legal(personaJuridica.getRepresentante().getPersonaNaturalTransient().getIdentificacion());

		dtoIn.setIngresos_anuales(personaJuridica.getPerfilFinancieroJuridicaTransient().getIngresos());
		dtoIn.setCod_moneda_ing(BigDecimal.ONE);

		if (personaJuridica.getPerfilFinancieroJuridicaTransient().getFchCorte() != null) {
			String fec_corte_ing = sdf.format(personaJuridica.getPerfilFinancieroJuridicaTransient().getFchCorte());
			dtoIn.setFec_corte_ing(fec_corte_ing);
		}

		if (personaJuridica.getFchConstitucion() != null) {
			String fec_const = sdf.format(personaJuridica.getFchConstitucion());
			dtoIn.setFec_contitucion(fec_const);
		}

		dtoIn.setImp_total_activos(personaJuridica.getPerfilFinancieroJuridicaTransient().getActivos());
		dtoIn.setImp_total_pasivos(personaJuridica.getPerfilFinancieroJuridicaTransient().getPasivos());

		dtoIn.setCnt_tiempo_mercado(BigDecimal.valueOf(personaJuridica.getTiempoMercado()));

		dtoIn.setImp_prom_ing_mensual("0");

		dtoIn.setSn_pep("0");
		dtoIn.setSn_relacion_laboral_pep("0");

		dtoIn.setCod_ingreso_pm(BigDecimal.valueOf(5));

		// se obtiene el codigo CIIU
		String codigoCiiu = "0";
		System.out.println("CODIGO ACTIVIDAD:" + personaJuridica.getActividadEconomica().getCodActividadEconomica());
		if (personaJuridica.getActividadEconomica().getCodActividadEconomica() != null) {
			codigoCiiu = actividadEconomicaServicio
					.obtenerCiiuParaSise(personaJuridica.getActividadEconomica().getCodActividadEconomica());
		}

		// se envia el 10 para pruebas
		dtoIn.setCod_actividad(codigoCiiu); // el codugo ciiu de la tactividad_economica

		// dtoIn.setCod_ingreso_pm(new BigDecimal("3")); // tingreso_prom_mensual
		if (domicilio != null) {
			if (domicilio.getEdificio() != null && domicilio.getEdificio().trim().length() > 0) {
				dtoIn.setTxt_edificio(domicilio.getEdificio());
			}
			if (domicilio.getBarrio() != null && domicilio.getBarrio().trim().length() > 0) {
				dtoIn.setTxt_sector(domicilio.getBarrio());
			}
		}

		dtoIn.setTxt_nombre_contacto(personaJuridica.getRepresentante().getPersonaNaturalTransient().getApellidos()
				+ " " + personaJuridica.getRepresentante().getPersonaNaturalTransient().getNombres());
		dtoIn.setSn_parteRel("0");

		dtoIn.setTxt_nombre_gerente(dtoIn.getTxt_nombre_contacto());

		System.out.println("---------------------------DTO");
		System.out.println(dtoIn);
		System.out.println("---------------------------");
	}

	/**
	 * Se obtiene la direccion del dimicilio.
	 * 
	 * @return
	 */
	private Direccion getDireccionDomicilio() {
		Direccion domicilio = null;

		Collection<Direccion> direccionList = personaJuridica.getPersona().getDireccionCollection();

		for (Direccion d : direccionList) {
			if (d.getTipoDireccion().getCodTipoDireccion().equals(TipoDireccionEnum.DOMICILIO.getCodigoenBase())) {
				domicilio = d;
				break;
			}
		}

		return domicilio;
	}

	/**
	 * Se obtiene la direccion del dimicilio.
	 * 
	 * @return
	 */
	private Direccion getDireccionTrabajo() {
		Direccion domicilio = null;

		Collection<Direccion> direccionList = personaJuridica.getPersona().getDireccionCollection();

		for (Direccion d : direccionList) {
			if (d.getTipoDireccion().getCodTipoDireccion().equals(TipoDireccionEnum.TRABAJO.getCodigoenBase())) {
				domicilio = d;
				break;
			}
		}

		return domicilio;
	}

	/**
	 * Se llenan campos obligatorios pero que no estan disponibles.
	 * 
	 */
	public void establecerValoresPorDefecto() {

		// Se pone la denominación de la persona en base de los datos de la persona
		// natural.
		personaJuridica.getPersona().setDenominacion(personaJuridica.getRazonSocial());
		personaJuridica.getPersona().setTipoIdentificacion(personaJuridica.getTipoIdentificacion());

		// RL
		personaJuridica.getRepresentante()
				.setDenominacion(personaJuridica.getRepresentante().getPersonaNaturalTransient().getApellidosNombres());
		personaJuridica.getRepresentante().setTipoIdentificacion(
				personaJuridica.getRepresentante().getPersonaNaturalTransient().getTipoIdentificacion());

		PersonaNatural personaNatural = personaJuridica.getRepresentante().getPersonaNaturalTransient();

		// Se pone profesion por defecto
		if (personaNatural.getProfesion() == null || personaNatural.getProfesion().getSecProfesion() == null) {
			Profesion profesion = new Profesion();
			profesion.setSecProfesion(Constantes.PROFESION_NO_DISPONIBLE); // Profesion no disponible
			personaNatural.setProfesion(profesion);
		}

		// Tipo riesgo por defecto
		if (personaNatural.getTipoRiesgo() == null || personaNatural.getTipoRiesgo().getCodTipoRiesgo() == null) {
			TipoRiesgo tipoRiesgo = new TipoRiesgo(Constantes.RIESGO_NO_DISPONIBLE);
			personaNatural.setTipoRiesgo(tipoRiesgo);
		}

		// email de contacto
		if (personaJuridica.getPersona().getEmailFacturacionElectronicaTra() != null
				&& personaJuridica.getPersona().getEmailFacturacionElectronicaTra().getDireccionElectronica() != null) {
			personaJuridica.setEmailContacto(
					personaJuridica.getPersona().getEmailFacturacionElectronicaTra().getDireccionElectronica());

		}
	}

	/**
	 * Se realizan las acciones para registros nuevos.
	 * 
	 */
	public void tareasRegistroNuevo() {
		// Se identifica como cliente
		personaJuridica.getPersona().setCliente(EsClienteEnum.SI.getCodigo());

		if (personaJuridica.getNombreComercial() == null) {
			personaJuridica.setNombreComercial("");
		}
		if (personaJuridica.getObjetoSocial() == null) {
			personaJuridica.setObjetoSocial("");
		}
		if (personaJuridica.getNombreContacto() == null) {
			personaJuridica.setNombreContacto("");
		}

		// verifica ciudad RL
		PersonaNatural personaNatural = personaJuridica.getRepresentante().getPersonaNaturalTransient();
		if (personaNatural.getCiudadNacimiento() == null
				|| personaNatural.getCiudadNacimiento().getSecCiudad() == null) {
			personaNatural.setCiudadNacimiento(new Ciudad(Constantes.CIUDAD_ND));
		}

		// Se calcula el saldo de ingreso mensual
		Collection<IngresoMensualAdicional> ingresoMensualAdicionalLista = new ArrayList<IngresoMensualAdicional>();
		if (personaNatural.getIngresoMensualAdicionalTra() != null
				&& personaNatural.getIngresoMensualAdicionalTra().getMntIngresoAdicional() != null) {
			ingresoMensualAdicionalLista.add(personaNatural.getIngresoMensualAdicionalTra());
		}
		BigDecimal saldoMensual = personaNaturalServicio.calcularSaldoMensual(personaNatural.getIngresoMensual(),
				ingresoMensualAdicionalLista);
		personaNatural.setMntSaldoMensual(saldoMensual);// calculado
	}

	/**
	 * Se validan datos de PJ antes de persitir.
	 * 
	 * @throws ContratanteException
	 */
	public void validarPJ() throws ContratanteException {

		// se validan direcciones de la persona juridica (no Rep Legal)
		
		if (personaJuridica.getTipoPersonaJuridica().getCodTipoPersonaJuridica().shortValue() == Constantes.TIPO_PERSONA_JURIDICA_ND) {
			throw new ContratanteException("Seleccione tipo de empresa, no se acepta 'No Disponible'");
		}
		

		// Se valida que existan Direcciones al menos una de trabajo y una de domicilio
		if (personaJuridica.getPersona().getDireccionCollection() == null
				|| personaJuridica.getPersona().getDireccionCollection().isEmpty()) {
			throw new ContratanteException("error.direcciones.requeridas", null, true);
		} else {
			boolean errorCantonIncorrecto = false;

			// Se valida datos de canton de domicilio
			for (Direccion d : personaJuridica.getPersona().getDireccionCollection()) {

				if (d.getCanton() != null && d.getCanton().getSecCanton() != null) {
					// Se valida que el nombre del canton sea correcto
					Canton cantonBuscado = cantonServicio.findByPk(d.getCanton().getSecCanton());

					if (!cantonBuscado.getCanton().equals(d.getCanton().getCanton())) {
						errorCantonIncorrecto = true;
						break;
					}
				} else {
					errorCantonIncorrecto = true;
					break;
				}
			}

			if (errorCantonIncorrecto) {
				throw new ContratanteException("error.canton.requerido.incorrecto", null, true);
			}
		}

		if (personaJuridica.getPais() == null || personaJuridica.getPais().getCodPais() == null) {
			throw new ContratanteException("Seleccione pa\u00EDs de origen");
		}

		if (personaJuridica.getPais().getCodPais().shortValue() == Constantes.PAIS_ID_NO_DISPONIBLE) {
			throw new ContratanteException("Seleccione pa\u00EDs de origen, no se acepta 'No Disponible'");
		}

		// valida si tiene direccion preferida
		if (!isDireccionPreferida()) {
			throw new ContratanteException("direccion.principal.no.seleccionada", null, true);
		}

		// Se valida que haya seleccionado un telefono de contracto preferido
		Telefono telefonoPreferido = obtenerTelefonoPreferido();
		if (telefonoPreferido == null) {
			throw new ContratanteException("seleccione.contacto.preferido", null, true);
		} else {
			if (telefonoPreferido.getNroTelefono() == null || telefonoPreferido.getNroTelefono().trim().length() <= 0) {
				throw new ContratanteException("seleccione.contacto.preferido", null, true);
			}
		}

		// valida email
		// String email = personaJuridica.gettra
		// if (!EmailUtils.validar(email)) {
		// throw new ContratanteException("email.incorrecto", null, true);
		// }

		/* REPRESETANTE LEGAL */

		// 1. Se valida representante legal
		boolean nombreConError = ValidatorUtils
				.validaNombre(personaJuridica.getRepresentante().getPersonaNaturalTransient().getPrimerNombre());
		if (nombreConError) {
			throw new ContratanteException("valida.nombre", null, true);
		}

		boolean apllidoConError = ValidatorUtils
				.validaNombre(personaJuridica.getRepresentante().getPersonaNaturalTransient().getApellidoPaterno());
		if (apllidoConError) {
			throw new ContratanteException("valida.nombre", null, true);
		}

		if (personaJuridica.getRepresentante().getPersonaNaturalTransient().getCiudadNacimiento() == null
				|| personaJuridica.getRepresentante().getPersonaNaturalTransient().getCiudadNacimiento()
						.getSecCiudad() == null) {
			throw new ContratanteException("Seleccione ciudad para representante legal");
		}

		if (personaJuridica.getRepresentante().getPersonaNaturalTransient().getCiudadNacimiento().getSecCiudad()
				.equals(Constantes.CIUDAD_ND)) {
			throw new ContratanteException("Seleccione ciudad para representante legal, no se acepta 'No Disponible'");
		}

		// 1. Se valida la edad de RL
		Calendar c = Calendar.getInstance();
		c.setTime(personaJuridica.getRepresentante().getPersonaNaturalTransient().getFchNacimiento());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		Calendar hoy = Calendar.getInstance();

		// si nacio en el futuro
		if (hoy.compareTo(c) < 0) {
			throw new ContratanteException("fecha.nacimiento.futuro", null, true);
		}

		int anioNacimiento = c.get(Calendar.YEAR);
		int anioActual = Calendar.getInstance().get(Calendar.YEAR);

		// si tiene mas de la edad maxima
		int edad = anioActual - anioNacimiento;
		if (edad >= Constantes.MAX_EDAD) {
			throw new ContratanteException("fecha.nacimiento.futuro", new Object[] { Constantes.MAX_EDAD }, true);
		}

		PersonaNatural personaNatural = personaJuridica.getRepresentante().getPersonaNaturalTransient();

		// Se valida que existan Direcciones al menos una de trabajo y una de domicilio
		if (personaJuridica.getRepresentante().getDireccionCollection() == null
				|| personaJuridica.getRepresentante().getDireccionCollection().isEmpty()) {
			throw new ContratanteException("error.direcciones.requeridas.rl", null, true);
		} else {
			boolean errorCantonIncorrecto = false;

			// Se valida datos de canton de domicilio
			for (Direccion d : personaNatural.getPersona().getDireccionCollection()) {

				if (d.getCanton() != null && d.getCanton().getSecCanton() != null) {
					// Se valida que el nombre del canton sea correcto
					Canton cantonBuscado = cantonServicio.findByPk(d.getCanton().getSecCanton());

					if (!cantonBuscado.getCanton().equals(d.getCanton().getCanton())) {
						errorCantonIncorrecto = true;
						break;
					}
				} else {
					errorCantonIncorrecto = true;
					break;
				}
			}

			if (errorCantonIncorrecto) {
				throw new ContratanteException("error.canton.requerido.incorrecto.rl", null, true);
			}
		}

		// Se validan datos del conyuge
		if (personaNatural.isConConyuge() && personaNatural.getConyuge() != null
				&& personaNatural.getConyuge().getIdentificacion() != null
				&& personaNatural.getConyuge().getIdentificacion().trim().length() > 0) {
			String numDoc = personaNatural.getConyuge().getIdentificacion();
			Character tipoDoc = personaNatural.getConyuge().getTipoIdentificacion().getCodTipoIdentificacion();

			boolean documentoValido = true;

			if (numDoc == null) {
				numDoc = "";
			}

			if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
				documentoValido = CedulaValidator.validate(numDoc);
			}

			// verifica
			if (!documentoValido) {
				throw new ContratanteException("numero.documento.incorrecto", null, true);
			}

			if (numDoc.equals(personaNatural.getIdentificacion())) {
				throw new ContratanteException("identificacion.igual.persona.conyuge", null, true);
			}
		}

		if (!isDireccionPreferidaRL()) {
			throw new ContratanteException("direccion.principal.no.seleccionada.rl", null, true);
		}

		// Se valida que haya seleccionado un telefono de contracto preferido
		Telefono telefonoPreferidoRL = obtenerTelefonoPreferidoRL();
		if (telefonoPreferidoRL == null) {
			throw new ContratanteException("seleccione.contacto.preferido.rl", null, true);
		} else {
			if (telefonoPreferidoRL.getNroTelefono() == null
					|| telefonoPreferidoRL.getNroTelefono().trim().length() <= 0) {
				throw new ContratanteException("seleccione.contacto.preferido.rl", null, true);
			}
		}

	}

	/**
	 * Se determina si se ha seleccionado una direccion preferida.
	 * 
	 * @return
	 */
	private boolean isDireccionPreferida() {
		boolean seleccionoDirPrincipal = false;
		for (Direccion direccion : personaJuridica.getPersona().getDireccionCollection()) {
			if (direccion.getActivo()) {
				if (direccion.getDireccionPrincipal()) {
					seleccionoDirPrincipal = true;
					direccion.setEnvioCorrespondencia(RespuestaEnum.SI.getCodigo());
				} else {
					direccion.setEnvioCorrespondencia(RespuestaEnum.NO.getCodigo());
				}
			}
		}

		return seleccionoDirPrincipal;
	}

	/**
	 * Se determina si se ha seleccionado una direccion preferida.
	 * 
	 * @return
	 */
	private boolean isDireccionPreferidaRL() {
		boolean seleccionoDirPrincipal = false;
		for (Direccion direccion : personaJuridica.getRepresentante().getDireccionCollection()) {
			if (direccion.getActivo()) {
				if (direccion.getDireccionPrincipal()) {
					seleccionoDirPrincipal = true;
					direccion.setEnvioCorrespondencia(RespuestaEnum.SI.getCodigo());
				} else {
					direccion.setEnvioCorrespondencia(RespuestaEnum.NO.getCodigo());
				}
			}
		}

		return seleccionoDirPrincipal;
	}

	/**
	 * Obtiene Telefono marcado como preferido.
	 * 
	 * @param direccionLista
	 * @return
	 */
	private Telefono obtenerTelefonoPreferido() {
		Telefono preferido = null;

		for (Direccion direccion : personaJuridica.getPersona().getDireccionCollection()) {
			if (direccion.getActivo()) {
				if (direccion.getDireccionTelefonoCollection() != null
						&& !direccion.getDireccionTelefonoCollection().isEmpty()) {
					Collection<DireccionTelefono> tmtDireccionTelefono = direccion.getDireccionTelefonoCollection();
					System.out.println("direccion:" + direccion.getPrincipal());

					for (DireccionTelefono direccionTelefono : tmtDireccionTelefono) {
						if (direccionTelefono.getTelefono().getActivo()) {
							if (direccionTelefono.getTelefono().getPrincipal()) {
								preferido = direccionTelefono.getTelefono();
								break;
							} else {
							}
						}
					}
				}
			}
		}

		return preferido;
	}

	/**
	 * Obtiene Telefono marcado como preferido.
	 * 
	 * @param direccionLista
	 * @return
	 */
	private Telefono obtenerTelefonoPreferidoRL() {
		Telefono preferido = null;

		for (Direccion direccion : personaJuridica.getRepresentante().getDireccionCollection()) {
			if (direccion.getActivo()) {
				if (direccion.getDireccionTelefonoCollection() != null
						&& !direccion.getDireccionTelefonoCollection().isEmpty()) {
					Collection<DireccionTelefono> tmtDireccionTelefono = direccion.getDireccionTelefonoCollection();
					System.out.println("direccion:" + direccion.getPrincipal());

					for (DireccionTelefono direccionTelefono : tmtDireccionTelefono) {
						if (direccionTelefono.getTelefono().getActivo()) {
							if (direccionTelefono.getTelefono().getPrincipal()) {
								preferido = direccionTelefono.getTelefono();
								break;
							} else {
							}
						}
					}
				}
			}
		}

		return preferido;
	}

	/**
	 * Se colocan latitud y longitud de las direcciones.
	 * 
	 * @throws ContratanteException
	 */
	public void colocarLatitudLocationWorld() throws ContratanteException {
		// direcciones, pone latitudes de location world
		Collection<Direccion> dirList = personaJuridica.getPersona().getDireccionCollection();

		for (Direccion dir : dirList) {

			if (dir.getActivo()) {

				String secundaria = dir.getSecundaria();
				// solo si secundaria no es vacia, llama a LW
				if (dir.getCiudad() != null && dir.getCiudad().trim().length() > 0 && secundaria != null
						&& !secundaria.equals("")) {
					String ciudad = dir.getCiudad();
					String principal = dir.getPrincipal();

					GeocodeAddressLW geocodeAddressLW = encontrarGeocode(ciudad, principal, secundaria);

					if (geocodeAddressLW != null) {
						dir.setLongitud(geocodeAddressLW.getLongitude());
						dir.setLatitud(geocodeAddressLW.getLatitude());
					}

				}
			}
		}
	}

	/**
	 * Se colocan latitud y longitud de las direcciones.
	 * 
	 * @throws ContratanteException
	 */
	public void colocarLatitudLocationWorldRL() throws ContratanteException {
		// direcciones, pone latitudes de location world
		Collection<Direccion> dirList = personaJuridica.getRepresentante().getDireccionCollection();

		for (Direccion dir : dirList) {

			if (dir.getActivo()) {

				String secundaria = dir.getSecundaria();
				// solo si secundaria no es vacia, llama a LW
				if (dir.getCiudad() != null && dir.getCiudad().trim().length() > 0 && secundaria != null
						&& !secundaria.equals("")) {
					String ciudad = dir.getCiudad();
					String principal = dir.getPrincipal();

					GeocodeAddressLW geocodeAddressLW = encontrarGeocode(ciudad, principal, secundaria);

					if (geocodeAddressLW != null) {
						dir.setLongitud(geocodeAddressLW.getLongitude());
						dir.setLatitud(geocodeAddressLW.getLatitude());
					}

				}
			}
		}
	}

	/**
	 * Se verifica en Listas reservadas a la PErsona y a su conyuge de ser el caso.
	 * 
	 * @param personaNatural
	 * @param remoteAddr
	 * @param remoteUser
	 * @throws RcsException
	 */
	public void verificarRcs() throws RcsException {
		// verifica rcs
		MensajeRcsDto rcsDtoPN = verificarRiesgoPJ();

		if (!rcsDtoPN.isPuedeContinuar()) {
			throw new RcsException(rcsDtoPN);
		}
	}

	private GeocodeAddressLW encontrarGeocode(String ciudad, String principal, String secundaria) {

		GeocodeAddressLW geocodeAddressLW = null;

		String webHandlerGeocode = LWResources.getString("geocode.webhandler");

		try {
			String ciudadE = URLEncoder.encode(ciudad, "utf-8");
			String principalE = URLEncoder.encode(principal, "utf-8");
			String secundariaE = URLEncoder.encode(secundaria, "utf-8");

			webHandlerGeocode = webHandlerGeocode.replace("@ciudad", ciudadE);
			webHandlerGeocode = webHandlerGeocode.replace("@calle", principalE);
			webHandlerGeocode = webHandlerGeocode.replace("@secundaria", secundariaE);

			String jsonCalles = ""; //$NON-NLS-1$

			URL url = new URL(webHandlerGeocode);

			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setConnectTimeout(5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				jsonCalles += inputLine;
			}

			in.close();

			if (!jsonCalles.equals("'null'")) {
				JSONObject obj = new JSONObject(jsonCalles);

				// int length = jsonArray.length();
				// for (int cont = 0; cont < length; cont++) {
				// JSONObject obj = jsonArray.getJSONObject(cont);
				if (obj != null) {

					JSONObject objCoord = (JSONObject) obj.get("Coordinates");

					BigDecimal latitude = new BigDecimal(objCoord.get("Latitude").toString());
					System.out.println("latitude" + latitude);
					BigDecimal longitude = new BigDecimal(objCoord.get("Longitude").toString());
					System.out.println("longitude" + longitude);
					geocodeAddressLW = new GeocodeAddressLW(latitude, longitude);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
			// j="";System.out
			System.out.println("Error Connection Host: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);

		} catch (java.net.UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Error Connection Host: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			System.out.println("Error Connection: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Error1: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error2: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		}

		return geocodeAddressLW;
	}

	/**
	 * Verifica si la persona enviada se encuentra en listas reservadas.
	 * 
	 * @param personaNatural
	 * @param remoteAddr
	 * @param remoteUser
	 * @return
	 */
	private MensajeRcsDto verificarRiesgoPJ() {
		boolean nuevo = false;
		if (personaJuridica.getSecPersonaJuridica() == null) {
			nuevo = true;
		}

		System.out.println("Se verifica RCS para la persona juridica (rep legal, conyuge, etc), contratante: "
				+ personaJuridica.getIdentificacion());
		MensajeRcsDto resp = rcsServicio.verificarBloqueoPJ(personaJuridica, remoteUser, remoteAddr, nuevo,
				PersonaRechazoListaReservadaEnum.EN_LISTA_PERSONA_JURIDICA);
		if (resp.isPuedeContinuar()) {
			resp = rcsServicio.verificarBloqueoPJ(personaJuridica, remoteUser, remoteAddr, nuevo,
					PersonaRechazoListaReservadaEnum.EN_LISTA_REPRESENTANTE_LEGAL);
			if (resp.isPuedeContinuar()) {
				resp = rcsServicio.verificarBloqueoPJ(personaJuridica, remoteUser, remoteAddr, nuevo,
						PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_REPRESENTANTE_LEGAL);
			}
		}

		return resp;

	}

	/**
	 * @return the rcsServicio
	 */
	public RcsServicio getRcsServicio() {
		return rcsServicio;
	}

	/**
	 * @param rcsServicio the rcsServicio to set
	 */
	public void setRcsServicio(RcsServicio rcsServicio) {
		this.rcsServicio = rcsServicio;
	}

	/**
	 * @return the remoteAddr
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}

	/**
	 * @param remoteAddr the remoteAddr to set
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	/**
	 * @return the remoteUser
	 */
	public String getRemoteUser() {
		return remoteUser;
	}

	/**
	 * @param remoteUser the remoteUser to set
	 */
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	/**
	 * @return the personaNaturalServicio
	 */
	public PersonaNaturalServicio getPersonaNaturalServicio() {
		return personaNaturalServicio;
	}

	/**
	 * @param personaNaturalServicio the personaNaturalServicio to set
	 */
	public void setPersonaNaturalServicio(PersonaNaturalServicio personaNaturalServicio) {
		this.personaNaturalServicio = personaNaturalServicio;
	}

	/**
	 * @return the nuevo
	 */
	public boolean isNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the siseServicio
	 */
	public SiseServicio getSiseServicio() {
		return siseServicio;
	}

	/**
	 * @param siseServicio the siseServicio to set
	 */
	public void setSiseServicio(SiseServicio siseServicio) {
		this.siseServicio = siseServicio;
	}

}
