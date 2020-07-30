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
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoEmpleoEnum;
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
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.IngresoMensualAdicional;
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
import com.equivida.util.EmailUtils;
import com.equivida.util.LWResources;
import com.equivida.util.ValidatorUtils;
import com.equivida.util.webservice.MPersonaUtil;

/**
 * @author juan
 *
 */
public class ContratantePNHelper {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private PersonaNatural personaNatural;
	private RcsServicio rcsServicio;
	private String remoteAddr;
	private String remoteUser;
	private boolean nuevo;
	private PersonaNaturalServicio personaNaturalServicio;
	private SiseServicio siseServicio;
	private CantonServicio cantonServicio;
	private PersonaEquividaServicio personaEquividaServicio;
	private ActividadEconomicaServicio actividadEconomicaServicio;

	public ContratantePNHelper() {
	}

	public ContratantePNHelper(PersonaNatural personaNatural, RcsServicio rcsServicio, String remoteAddr,
			String remoteUser, boolean nuevo, PersonaNaturalServicio personaNaturalServicio, SiseServicio siseServicio,
			CantonServicio cantonServicio, PersonaEquividaServicio personaEquividaServicio,
			ActividadEconomicaServicio actividadEconomicaServicio) {
		this.personaNatural = personaNatural;
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
		boolean existe = personaEquividaServicio.existeNoTx(personaNatural.getPersona().getSecPersona(),
				personaNatural.getSecPersonaNatural(), idPersonaSise, Constantes.COD_SISTEMA_SISE,
				Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);

		System.out.println("10.2 Se llama persiste persona_equivida------------------------------------: " + existe);
		// solo si no existe en las tablas de homologacion graba
		if (!existe) {
			// solo si tiene sec persona se envia a guardar
			if (personaNatural.getPersona().getSecPersona() != null) {
				System.out.println("10.3 Se llama persiste persona_equivida------------------------------------");
				PersonaEquivida personaEquivida = new PersonaEquivida();
				personaEquivida.setCodSistema(Constantes.COD_SISTEMA_SISE);
				personaEquivida.setCodTipoPersona(Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);
				personaEquivida.setIdPersonaDestino(idPersonaSise);
				personaEquivida.setSecPersonaEquivida(personaNatural.getPersona().getSecPersona());
				personaEquivida.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
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
			armarDtoIntegracionContratantePNSise(dtoIn);

			System.out.println("8.1 Se llama a SP SISE rsContratante PN");
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
				siseServicio.ingresarActualizarDireccionTelefonoContratante(personaNatural.getPersona(),
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
				siseServicio.insertarWsSiseMsHeader(dto.getIdPersonaSise(), personaNatural.getPersona(), personaNatural,
						nuevo, remoteUser, dto);
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
	private void armarDtoIntegracionContratantePNSise(ContratanteDto dtoIn) {
		Direccion domicilio = getDireccionDomicilio();
		if (domicilio == null) {
			domicilio = getDireccionTrabajo();
		}

		// Se calcula el monti mensuald e ingresos
		BigDecimal totalIngresoMensual = BigDecimal.ZERO;
		totalIngresoMensual = totalIngresoMensual.add(personaNatural.getIngresoMensual().getMntIngresoMensual());
		Short codActividadEconomica = null;
		if (personaNatural.getTipoEmpleo() == TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo()) {
			codActividadEconomica = personaNatural.getEmpleoDependienteTra().getActividadEconomica()
					.getCodActividadEconomica();
		} else {
			codActividadEconomica = personaNatural.getEmpleoIndependienteTra().getActividadEconomica()
					.getCodActividadEconomica();
		}

		if (personaNatural.getIngresoMensualAdicionalTra() != null
				&& personaNatural.getIngresoMensualAdicionalTra().getMntIngresoAdicional() != null && personaNatural
						.getIngresoMensualAdicionalTra().getMntIngresoAdicional().compareTo(BigDecimal.ZERO) > 0) {
			totalIngresoMensual = totalIngresoMensual
					.add(personaNatural.getIngresoMensualAdicionalTra().getMntIngresoAdicional());
		}

		dtoIn.setTxt_apellido1(personaNatural.getApellidoPaterno());
		dtoIn.setTxt_apellido2(personaNatural.getApellidoMaterno());
		dtoIn.setTxt_nombre(personaNatural.getNombres());
		dtoIn.setCod_tipo_doc(BigDecimal.valueOf(2)); // En base a correo de Kathy, indica que para persona natural vaya
														// 1
		dtoIn.setNro_doc(personaNatural.getIdentificacion()); // Aqui va la cedula
		dtoIn.setNro_nit(personaNatural.getRucPersonaNaturalTra().getIdentificacion()); // Aquí va el RUC
		dtoIn.setCod_tipo_iva(BigDecimal.ONE);
		dtoIn.setFec_nac(sdf.format(personaNatural.getFchNacimiento()));
		dtoIn.setTxt_lugar_nac(personaNatural.getCiudadNacimiento().getCiudad());
		dtoIn.setTxt_sexo(String.valueOf(personaNatural.getSexo()));
		int cod_est_civil = MPersonaUtil.obtenerEstadoCivil(personaNatural.getEstadoCivil().getCodEstadoCivil());
		dtoIn.setCod_est_civil(BigDecimal.valueOf(cod_est_civil));
		dtoIn.setCod_tipo_persona(TipoPersonaEnum.N.getCodSise());
		
		// conyuge
		if (personaNatural.isConConyuge()) {
			if (personaNatural.getConyuge().getIdentificacion() != null
					&& personaNatural.getConyuge().getIdentificacion().trim().length() > 0) {
				personaNatural.getConyuge().setApellidosNombres(null);
				dtoIn.setTxt_apellidos_conyugue(personaNatural.getConyuge().getApellidos());
				dtoIn.setTxt_nombres_conyugue(personaNatural.getConyuge().getNombres());
				dtoIn.setCod_tipo_doc_conyugue(BigDecimal.valueOf(1));
				dtoIn.setNro_doc_conyugue(personaNatural.getConyuge().getIdentificacion());
				// dtoIn.setSn_relacionl("0");
			}
		}

		dtoIn.setImp_prom_ing_mensual(totalIngresoMensual.toString());

		// se obtiene el codigo CIIU
		String codigoCiiu = "0";
		System.out.println("CODIGO ACTIVIDAD:"
				+ personaNatural.getEmpleoDependienteTra().getActividadEconomica().getCodActividadEconomica());
		if (codActividadEconomica != null) {
			codigoCiiu = actividadEconomicaServicio.obtenerCiiuParaSise(codActividadEconomica);
		}

		// se envia el 10 para pruebas
		dtoIn.setCod_actividad(codigoCiiu); // el codugo ciiu de la tactividad_economica
		// dtoIn.setCod_ingreso_pm(new BigDecimal("3")); // tingreso_prom_mensual
		if (domicilio != null) {
			dtoIn.setTxt_edificio(domicilio.getEdificio());
			dtoIn.setTxt_sector(domicilio.getBarrio());
		}
		// dtoIn.setCod_sector_mercado(BigDecimal.ONE); // Se manda por defecto 1 de
		// acuerdo a definición de Kathy
		// dtoIn.setCod_tipo_soc(BigDecimal.ONE); // Se manda por defecto 1 de acuerdo a
		
		// definición de Kathy
		dtoIn.setCod_moneda_ing(BigDecimal.ONE);
		
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

		Collection<Direccion> direccionList = personaNatural.getPersona().getDireccionCollection();

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

		Collection<Direccion> direccionList = personaNatural.getPersona().getDireccionCollection();

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
	}

	/**
	 * Se realizan las acciones para registros nuevos.
	 * 
	 */
	public void tareasRegistroNuevo() {
		// Se pone la denominación de la persona en base de los datos de la persona
		// natural.
		personaNatural.getPersona().setDenominacion(personaNatural.getApellidosNombres());

		// Se identifica como cliente
		personaNatural.getPersona().setCliente(EsClienteEnum.SI.getCodigo());

		// verifica ciudad
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
	 * Se validan datos de PN antes de persitir.
	 * 
	 * @param personaNatural
	 * @throws ContratanteException
	 */
	public void validarPN() throws ContratanteException {
		// 1. Se valida nombre
		boolean nombreConError = ValidatorUtils.validaNombre(personaNatural.getPrimerNombre());
		if (nombreConError) {
			throw new ContratanteException("valida.nombre", null, true);
		}

		boolean apllidoConError = ValidatorUtils.validaNombre(personaNatural.getApellidoPaterno());
		if (apllidoConError) {
			throw new ContratanteException("valida.nombre", null, true);
		}

		// 1. Se valida la edad
		Calendar c = Calendar.getInstance();
		c.setTime(personaNatural.getFchNacimiento());
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

		if (personaNatural.getCiudadNacimiento().getSecCiudad().shortValue() == Constantes.CIUDAD_ND) {
			throw new ContratanteException("Seleccione ciudad, no se acepta 'No Disponible'");
		}

		if (personaNatural.getCiudadNacimiento().getPais().getCodPais()
				.shortValue() == Constantes.PAIS_ID_NO_DISPONIBLE) {
			throw new ContratanteException("Seleccione pa\u00EDs de nacimiento, no se acepta 'No Disponible'");
		}

		if (personaNatural.getPaisNacionalidad().getCodPais().shortValue() == Constantes.PAIS_ID_NO_DISPONIBLE) {
			throw new ContratanteException("Seleccione pa\u00EDs de nacionalidad, no se acepta 'No Disponible'");
		}
		
		if (personaNatural.getOcupacion().getCodOcupacion()
				.shortValue() == Constantes.OCUPACION_ID_NO_DISPONIBLE) {
			throw new ContratanteException("Seleccione ocupaci\u00F3n, no se acepta 'No Disponible'");
		}

		// Se valida que existan Direcciones al menos una de trabajo y una de domicilio
		if (personaNatural.getPersona().getDireccionCollection() == null
				|| personaNatural.getPersona().getDireccionCollection().isEmpty()) {
			throw new ContratanteException("error.direcciones.requeridas", null, true);
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
				throw new ContratanteException("error.canton.requerido.incorrecto", null, true);
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

		// Se valida que los correos esten bien
		DireccionElectronica mailFacElec = personaNatural.getPersona().getEmailFacturacionElectronicaTra();
		if (!EmailUtils.validar(mailFacElec.getDireccionElectronica())) {
			throw new ContratanteException("email.incorrecto", null, true);
		}

		DireccionElectronica mailPersonal = personaNatural.getPersona().getEmailPersonalTra();
		if (mailPersonal != null && mailPersonal.getDireccionElectronica() != null
				&& mailPersonal.getDireccionElectronica().trim().length() > 0) {
			if (!EmailUtils.validar(mailPersonal.getDireccionElectronica())) {
				throw new ContratanteException("email.incorrecto", null, true);
			}
		}

		System.out.println("V personaNatural.getIngresoMensual(): " + personaNatural.getIngresoMensual());
		System.out.println("V personaNatural.getIngresoMensual().getMntIngresoMensual(): "
				+ personaNatural.getIngresoMensual().getMntIngresoMensual());

		if (personaNatural.getIngresoMensual() != null
				&& personaNatural.getIngresoMensual().getMntIngresoMensual() != null) {
			if (personaNatural.getIngresoMensual().getMntIngresoMensual().compareTo(BigDecimal.ZERO) <= 0) {
				throw new ContratanteException("Ingresos deben ser mayor a 0.");
			}
		} else {
			throw new ContratanteException("Ingrese valor de ingresos.");
		}

		// Se valida que exista fuente de otros ingresos mensuales (si aplica).
		if (personaNatural.getIngresoMensualAdicionalTra() != null && personaNatural.getIngresoMensualAdicionalTra()
				.getMntIngresoAdicional().compareTo(BigDecimal.ZERO) > 0) {
			// ha ingresado valor, entonces verifica que se ingrese fuente
			if (personaNatural.getIngresoMensualAdicionalTra().getFuenteIngresoAdicional().trim().length() <= 0) {
				throw new ContratanteException("Ingrese 'Fuente' en Otros Ingresos");
			}
		}

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

	}

	/**
	 * Se determina si se ha seleccionado una direccion preferida.
	 * 
	 * @return
	 */
	private boolean isDireccionPreferida() {
		boolean seleccionoDirPrincipal = false;
		for (Direccion direccion : personaNatural.getPersona().getDireccionCollection()) {
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

		for (Direccion direccion : personaNatural.getPersona().getDireccionCollection()) {
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
		Collection<Direccion> dirList = personaNatural.getPersona().getDireccionCollection();

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
		MensajeRcsDto rcsDtoPN = verificarRiesgoPN();

		if (!rcsDtoPN.isPuedeContinuar()) {
			throw new RcsException(rcsDtoPN);
		}

		// Se validan lis datos del conyuge
		if (personaNatural.getConyuge() != null && personaNatural.getConyuge().getIdentificacion() != null
				&& personaNatural.getConyuge().getIdentificacion().trim().length() > 0) {
			System.out.println("____SE va a verificar listas reservadas del conyuge: "
					+ personaNatural.getConyuge().getIdentificacion());

			MensajeRcsDto rcsDtpConyuge = verificarRiesgoConyuge();

			if (!rcsDtpConyuge.isPuedeContinuar()) {
				throw new RcsException(rcsDtpConyuge);
			}
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
	private MensajeRcsDto verificarRiesgoPN() {
		boolean nuevo = false;
		if (personaNatural.getSecPersonaNatural() == null) {
			nuevo = true;
		}

		System.out
				.println("Se verifica RCS para la persona natural contratante: " + personaNatural.getIdentificacion());
		MensajeRcsDto resp = rcsServicio.verificarBloqueo(personaNatural, remoteUser, remoteAddr, nuevo);

		return resp;

	}

	/**
	 * Verifica si la persona enviada se encuentra en listas reservadas.
	 * 
	 * @param personaNatural
	 * @param remoteAddr
	 * @param remoteUser
	 * @return
	 */
	private MensajeRcsDto verificarRiesgoConyuge() {
		boolean nuevo = false;
		if (personaNatural.getConyuge().getSecPersonaNatural() == null) {
			nuevo = true;
		}

		MensajeRcsDto resp = rcsServicio.verificarBloqueoConyuge(personaNatural, remoteUser, remoteAddr, nuevo);

		return resp;

	}

	/**
	 * @return the personaNatural
	 */
	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	/**
	 * @param personaNatural the personaNatural to set
	 */
	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
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
