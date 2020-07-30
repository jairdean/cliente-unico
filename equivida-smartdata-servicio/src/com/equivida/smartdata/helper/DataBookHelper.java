package com.equivida.smartdata.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.equivida.databook.model.Registros;
import com.equivida.databook.model.Registros.Civil;
import com.equivida.databook.model.Registros.Contactabilidad;
import com.equivida.databook.model.Registros.Sri;
import com.equivida.smartdata.constante.ConsultaEnEnum;
import com.equivida.smartdata.constante.EstadoEnum;
import com.equivida.smartdata.constante.GeneroEnum;
import com.equivida.smartdata.constante.TipoDireccionEnum;
import com.equivida.smartdata.constante.TipoIdentificacionSDEnum;
import com.equivida.smartdata.constante.TipoTelefonoEnum;
import com.equivida.smartdata.dao.NoPkTablasSdDao;
import com.equivida.smartdata.exception.FindException;
import com.equivida.smartdata.model.ActividadEconomicaSd;
import com.equivida.smartdata.model.CanalSd;
import com.equivida.smartdata.model.CantonSd;
import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.model.DireccionTelefonoSd;
import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.equivida.smartdata.model.EstadoCivilSd;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.equivida.smartdata.model.PaisSd;
import com.equivida.smartdata.model.ParroquiaSd;
import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.model.ProfesionSd;
import com.equivida.smartdata.model.ProvinciaSd;
import com.equivida.smartdata.model.TelefonoSd;
import com.equivida.smartdata.model.TipoDireccionSd;
import com.equivida.smartdata.model.TipoIdentificacionSd;
import com.equivida.smartdata.model.TipoTelefonoSd;
import com.equivida.smartdata.model.VehiculoSd;
import com.equivida.smartdata.servicio.ActividadEconomicaSdServicio;
import com.equivida.smartdata.servicio.DireccionSdServicio;
import com.equivida.smartdata.servicio.EstadoCivilSdServicio;
import com.equivida.smartdata.servicio.PersonaJuridicaSdServicio;
import com.equivida.smartdata.servicio.PersonaSdServicio;
import com.equivida.smartdata.servicio.ProfesionSdServicio;
import com.equivida.smartdata.servicio.TelefonoSdServicio;

/**
 * Clase que ayuda a obtner los objetos en estilo Smartdata de objetos Databook.
 * 
 * @author juan
 *
 */
public class DataBookHelper implements Serializable {
	
	private static final long serialVersionUID = -10686540848404949L;

	private static final SimpleDateFormat SDF_SRI = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat SDF_LABORAL = new SimpleDateFormat("dd/MM/yyyy");
	private Logger log = Logger.getLogger(DataBookHelper.class);

	private Registros registros;
	private String usuario;
	private NoPkTablasSdDao noPkTablasDao;
	private EstadoCivilSdServicio estadoCivilServicio;
	private ProfesionSdServicio profesionServicio;
	private ActividadEconomicaSdServicio actividadEconomicaServicio;
	private PersonaJuridicaSdServicio personaJuridicaServicio;
	private PersonaSdServicio personaServicio;
	private DireccionSdServicio direccionSdServicio;
	private TelefonoSdServicio telefonoSdServicio;

	/**
	 * Costructor con parametros.
	 * 
	 * @param registros
	 * @param usuario
	 * @param noPkTablasDao
	 * @param estadoCivilServicio
	 * @param profesionServicio
	 * @param actividadEconomicaServicio
	 */
	public DataBookHelper(Registros registros, String usuario, NoPkTablasSdDao noPkTablasDao,
			EstadoCivilSdServicio estadoCivilServicio, ProfesionSdServicio profesionServicio,
			ActividadEconomicaSdServicio actividadEconomicaServicio, PersonaJuridicaSdServicio personaJuridicaServicio,
			PersonaSdServicio personaServicio, DireccionSdServicio direccionSdServicio,
			TelefonoSdServicio telefonoSdServicio) {
		super();
		this.registros = registros;
		this.usuario = usuario;
		this.noPkTablasDao = noPkTablasDao;
		this.estadoCivilServicio = estadoCivilServicio;
		this.profesionServicio = profesionServicio;
		this.actividadEconomicaServicio = actividadEconomicaServicio;
		this.personaJuridicaServicio = personaJuridicaServicio;
		this.personaServicio = personaServicio;
		this.direccionSdServicio = direccionSdServicio;
		this.telefonoSdServicio = telefonoSdServicio;
	}

	/**
	 * Asigna los datos de:
	 * 
	 * Cargo, Salario Actual, Fecha Entrada, Fecha Salida
	 * 
	 * @param ruc
	 * @param ed
	 * @throws ParseException
	 */
	public void asignarDatosRelacion(String ruc, EmpleoDependienteSd ed) throws ParseException {
		if (registros.getActual() != null && registros.getActual().getRucempleador() != null
				&& registros.getActual().getRucempleador().equals(ruc)) {
			Registros.Actual actual = registros.getActual();

			ed.setCargo(getCargoFormateado(actual.getCargo()));

			Short salarioActual = actual.getSalarioactual();
			ed.setMntSalario(BigDecimal.valueOf(salarioActual.longValue()));
			if (actual.getFechaentrada() != null && actual.getFechaentrada().trim().length() > 0) {
				ed.setFchIngreso(SDF_LABORAL.parse(actual.getFechaentrada().trim()));
			}

			if (actual.getFechasalida() != null && actual.getFechasalida().trim().length() > 0) {
				ed.setFchSalida(SDF_LABORAL.parse(actual.getFechasalida().trim()));
			}
		}

		// Se comenta porque solo se procesa el empleo actual.
		/*
		 * else if (registros.getPrimeroAnterior() != null &&
		 * registros.getPrimeroAnterior().getRucempleador() != null &&
		 * registros.getPrimeroAnterior().getRucempleador().equals(ruc)) {
		 * Registros.PrimeroAnterior pa = registros.getPrimeroAnterior();
		 * 
		 * ed.setCargo(getCargoFormateado(pa.getCargoempleador()));
		 * 
		 * Short salarioActual = pa.getSalarioempleador();
		 * ed.setMntSalario(BigDecimal.valueOf(salarioActual.longValue())); if
		 * (pa.getFechaentradaempleador() != null &&
		 * pa.getFechaentradaempleador().trim().length() > 0) {
		 * ed.setFchIngreso(SDF_LABORAL.parse(pa .getFechaentradaempleador().trim())); }
		 * 
		 * if (pa.getFechasalidaempleador() != null &&
		 * pa.getFechasalidaempleador().trim().length() > 0) {
		 * ed.setFchSalida(SDF_LABORAL.parse(pa.getFechasalidaempleador() .trim())); } }
		 * else if (registros.getSegundoAnterior() != null &&
		 * registros.getSegundoAnterior().getRucempleador() != null &&
		 * registros.getSegundoAnterior().getRucempleador().equals(ruc)) {
		 * Registros.SegundoAnterior sa = registros.getSegundoAnterior();
		 * 
		 * ed.setCargo(getCargoFormateado(sa.getCargoempleador()));
		 * 
		 * Short salarioActual = sa.getSalarioempleador();
		 * ed.setMntSalario(BigDecimal.valueOf(salarioActual.longValue())); if
		 * (sa.getFechaentradaempleador() != null &&
		 * sa.getFechaentradaempleador().trim().length() > 0) {
		 * ed.setFchIngreso(SDF_LABORAL.parse(sa .getFechaentradaempleador().trim())); }
		 * 
		 * if (sa.getFechasalidaempleador() != null &&
		 * sa.getFechasalidaempleador().trim().length() > 0) {
		 * ed.setFchSalida(SDF_LABORAL.parse(sa.getFechasalidaempleador() .trim())); } }
		 */
	}

	/**
	 * Obtiene el nomb re del cargo con formato para SD.
	 * 
	 * @param cargo
	 * @return
	 */
	private String getCargoFormateado(String cargo) {
		String resp = "";
		if (cargo != null) {
			resp = cargo.trim();

			if (resp.length() > 32) {
				resp = resp.substring(0, 31);
			}
		}

		return resp;
	}

	/**
	 * Devuelve la persona natural.
	 * 
	 * @return
	 */
	public PersonaSd getPersonaNatural() {
		// Se crea la persona
		PersonaSd persona = construyePersona();

		// Se crea la persona natural
		armarPersonaNatural(persona);

		return persona;
	}

	/**
	 * Retorna las personas juridicas.
	 * 
	 * Si la persona juridica no existe en smartdata, la crea, caso contrario
	 * devulve la que encuentra en la bse de datos.
	 * 
	 * @return
	 */
	public List<PersonaSd> getPersonasJuridicas() {
		return procesarPersonasJuridicas();
	}

	/**
	 * Revisa las relaciones de la persona natural con la personas juridicas, si no
	 * existen, crea las personas juridicas, casoc ontrario toma la que ya existe en
	 * la base.
	 * 
	 * @param r
	 * @param personaNatural
	 * @param usuario
	 */
	private List<PersonaSd> procesarPersonasJuridicas() {
		// String rucActual = "";
		// String rucPrimeroAnterior = "";

		// Lista de Personas Juridicas
		List<PersonaSd> listaPj = new ArrayList<PersonaSd>();

		// Tags de personas juridicas
		Registros.Actual actual = registros.getActual();

		// Solo se obtiene la info del empleo actual
		// Registros.PrimeroAnterior pa = registros.getPrimeroAnterior();
		// Registros.SegundoAnterior sa = registros.getSegundoAnterior();

		// Actual
		PersonaJuridicaSd pjActual = procesarActual(actual);
		if (pjActual != null) {
			// rucActual = pjActual.getIdentificacion();
			listaPj.add(pjActual.getSecPersona());
		}

		// Primero Anterior
		/*
		 * PersonaJuridicaSd pjPrimeroAnterior = procesarPrimeroAnterior(pa, rucActual);
		 * if (pjPrimeroAnterior != null) { rucPrimeroAnterior =
		 * pjPrimeroAnterior.getIdentificacion();
		 * listaPj.add(pjPrimeroAnterior.getSecPersona()); }
		 */

		// Segundo Anterior
		/*
		 * PersonaJuridicaSd pjSegundoAnterior = procesarSegundoAnterior(sa, rucActual,
		 * rucPrimeroAnterior); if (pjSegundoAnterior != null) {
		 * listaPj.add(pjSegundoAnterior.getSecPersona()); }
		 */

		return listaPj;
	}

	/**
	 * Procesa la persona juridica actual.
	 * 
	 * No se utiliza porque se pide solo el empleo actual.
	 * 
	 * @param actual
	 * @return
	 */
	@Deprecated
	private PersonaJuridicaSd procesarPrimeroAnterior(Registros.PrimeroAnterior pa, String rucActual) {
		PersonaJuridicaSd pjPrimeroAnterior = null;

		// Si en el tag Primero Anterior viene informacion y si el ruc es
		// distinto al del ruc actual
		if (pa != null && pa.getNombreempleador() != null && pa.getNombreempleador().trim().length() > 0
				&& pa.getRucempleador() != null && pa.getRucempleador().trim().length() >= 13
				&& !pa.getRucempleador().trim().equals(rucActual)) {

			// 1. Consulta si ya existe la persona juridica en SmartData
			pjPrimeroAnterior = buscarPersonaJuridicaPorIdentificaicon(pa.getRucempleador());

			// 2. Si no existe en SmartData, se lo crea caso contrario se
			// devuelve el registro de smartdata
			if (pjPrimeroAnterior == null) {
				// 2.1 Si no existe en SmartData, se lo crea y se lo devuelve
				pjPrimeroAnterior = construirPjPrimeroAnterior(pa);

				// Se persiste
				personaServicio.create(pjPrimeroAnterior.getSecPersona());
			}
		}

		return pjPrimeroAnterior;
	}

	/**
	 * Procesa la persona juridica actual. No se utiliza porque se pide solo el
	 * empleo actual.
	 * 
	 * @param actual
	 * @return
	 */
	@Deprecated
	private PersonaJuridicaSd procesarSegundoAnterior(Registros.SegundoAnterior sa, String rucActual,
			String rucPrimeroAnterior) {
		PersonaJuridicaSd pjSegundoAnterior = null;

		// Si en el tag Primero Anterior viene informacion y si el ruc es
		// distinto al del ruc actual y distinto al ruc de primero anterior
		if (sa != null && sa.getNombreempleador() != null && sa.getNombreempleador().trim().length() > 0
				&& sa.getRucempleador() != null && sa.getRucempleador().trim().length() >= 13
				&& !sa.getRucempleador().trim().equals(rucActual) && !sa.getRucempleador().equals(rucPrimeroAnterior)) {

			// 1. Consulta si ya existe la persona juridica en SmartData
			pjSegundoAnterior = buscarPersonaJuridicaPorIdentificaicon(sa.getRucempleador());

			// 2. Si no existe en SmartData, se lo crea caso contrario se
			// devuelve el registro de smartdata
			if (pjSegundoAnterior == null) {
				// 2.1 Si no existe en SmartData, se lo crea y se lo devuelve
				pjSegundoAnterior = construirPjSegundoAnterior(sa);

				// Se persiste
				personaServicio.create(pjSegundoAnterior.getSecPersona());
			}
		}

		return pjSegundoAnterior;
	}

	/**
	 * Procesa la persona juridica actual.
	 * 
	 * @param actual
	 * @return
	 */
	private PersonaJuridicaSd procesarActual(Registros.Actual actual) {
		PersonaJuridicaSd pjActual = null;

		// Si en el tag Actul viene informacion:
		if (actual != null && actual.getNombreempleador() != null && actual.getNombreempleador().trim().length() > 0
				&& actual.getRucempleador() != null && actual.getRucempleador().trim().length() >= 13) {

			// 1. Consulta si ya existe la persona juridica en SmartData
			pjActual = buscarPersonaJuridicaPorIdentificaicon(actual.getRucempleador());

			// 2. Si no existe en SmartData, se lo crea caso contrario se
			// devuelve el registro de smartdata
			if (pjActual == null) {
				pjActual = construirPjActual(actual);

				System.out.println("en procesarActual, envia a guardar personasd");
				System.out.println(pjActual.getSecPersona());
				System.out.println(pjActual.getSecPersona().getSecPersona());
				System.out.println(pjActual.getSecPersona().getIdentificacion());
				System.out.println(pjActual.getSecPersona().getDenominacion());
				System.out.println(pjActual.getSecPersona().getCodTipoIdentificacion());
				System.out.println(pjActual.getSecPersona().getPersonaJuridica());

				// Se persiste la Persona
				personaServicio.create(pjActual.getSecPersona());
			}
		}

		return pjActual;
	}

	/**
	 * Costruye una persona juridica en base del tag primeroanterior.
	 * 
	 * no se contruye este campo
	 * 
	 * @param actual
	 * @return
	 */
	@Deprecated
	private PersonaJuridicaSd construirPjPrimeroAnterior(Registros.PrimeroAnterior pa) {
		// Se crea la persona
		PersonaSd p = new PersonaSd();
		p.setCodTipoIdentificacion(getTipoIdentificacionRuc());
		p.setDenominacion(pa.getNombreempleador());
		p.setIdentificacion(pa.getRucempleador());

		// Se pone la direccion
		if (pa.getDireccionempleador() != null && pa.getDireccionempleador().trim().length() > 0
				&& pa.getProvinciaempleador() != null && pa.getCantonempleador() != null
				&& pa.getParroquiaempleador() != null) {
			String codProvincia = pa.getProvinciaempleador();
			String codCanton = pa.getCantonempleador();
			String codParroquia = pa.getParroquiaempleador();

			List<String> telefonoList = new ArrayList<String>();
			if (pa.getTelefonoempleador() != null && pa.getTelefonoempleador().trim().length() > 0) {
				telefonoList.add(pa.getTelefonoempleador().trim());
			}

			crearDireccionEnPersona(p, pa.getDireccionempleador(), codProvincia, codCanton, codParroquia, null, false,
					null, null, null, telefonoList, TipoDireccionEnum.TRABAJO, TipoTelefonoEnum.OFICINA);
		}

		// Se pone el telefono
		if (pa.getTelefonoempleador() != null && pa.getTelefonoempleador().trim().length() > 0) {
			crearTelefonoPersona(pa.getTelefonoempleador(), p);
			if (p.getTelefonoList().isEmpty()) {
				p.setTelefonoList(null);
			}
		}

		// Se crea la persona juridica
		PersonaJuridicaSd pj = new PersonaJuridicaSd();

		pj.setIdentificacion(pa.getRucempleador());
		pj.setRazonSocial(pa.getNombreempleador());
		pj.setActividadIess(pa.getActividadempleador());
		pj.setUsrCreacion(usuario);
		pj.setUsrModificacion(usuario);
		pj.setCodTipoIdentificacion(getTipoIdentificacionRuc());
		pj.setSecPersona(p);
		pj.setSecCanal(getCanal());
		pj.setCodActividadEconomica(actividadEconomicaServicio.obtenerPorDefecto());

		// Se pone persona juridica en persona
		p.setPersonaJuridica(pj);

		return pj;
	}

	/**
	 * Costruye una persona juridica en base del tag segundoanterior.
	 * 
	 * @param actual
	 * @return
	 */
	@Deprecated
	private PersonaJuridicaSd construirPjSegundoAnterior(Registros.SegundoAnterior sa) {
		// Se crea la persona
		PersonaSd p = new PersonaSd();
		p.setCodTipoIdentificacion(getTipoIdentificacionRuc());
		p.setDenominacion(sa.getNombreempleador());
		p.setIdentificacion(sa.getRucempleador());

		// Se pone la direccion
		if (sa.getDireccionempleador() != null && sa.getDireccionempleador().trim().length() > 0
				&& sa.getProvinciaempleador() != null && sa.getCantonempleador() != null
				&& sa.getParroquiaempleador() != null) {
			String codProvincia = sa.getProvinciaempleador();
			String codCanton = sa.getCantonempleador();
			String codParroquia = sa.getParroquiaempleador();

			List<String> telefonoList = new ArrayList<String>();
			if (sa.getTelefonoempleador() != null && sa.getTelefonoempleador().trim().length() > 0) {
				telefonoList.add(sa.getTelefonoempleador().trim());
			}

			crearDireccionEnPersona(p, sa.getDireccionempleador(), codProvincia, codCanton, codParroquia, null, false,
					null, null, null, telefonoList, TipoDireccionEnum.TRABAJO, TipoTelefonoEnum.OFICINA);
		}

		// Se pone el telefono
		if (sa.getTelefonoempleador() != null && sa.getTelefonoempleador().trim().length() > 0) {
			crearTelefonoPersona(sa.getTelefonoempleador(), p);
			if (p.getTelefonoList().isEmpty()) {
				p.setTelefonoList(null);
			}
		}

		// Se crea la persona juridica
		PersonaJuridicaSd pj = new PersonaJuridicaSd();

		pj.setIdentificacion(sa.getRucempleador());
		pj.setRazonSocial(sa.getNombreempleador());
		pj.setActividadIess(sa.getActividadempleador());
		pj.setUsrCreacion(usuario);
		pj.setUsrModificacion(usuario);
		pj.setTsCreacion(new Date());
		pj.setCodTipoIdentificacion(getTipoIdentificacionRuc());
		pj.setSecPersona(p);
		pj.setSecCanal(getCanal());
		pj.setCodActividadEconomica(actividadEconomicaServicio.obtenerPorDefecto());

		// Se pone persona juridica en persona
		p.setPersonaJuridica(pj);

		return pj;
	}

	/**
	 * Costruye una persona juridica en base del tag actual.
	 * 
	 * @param actual
	 * @return
	 */
	private PersonaJuridicaSd construirPjActual(Registros.Actual actual) {
		// Se crea la persona
		PersonaSd p = new PersonaSd();
		p.setCodTipoIdentificacion(getTipoIdentificacionRuc());
		p.setDenominacion(actual.getNombreempleador());
		p.setIdentificacion(actual.getRucempleador());

		// Se pone la direccion
		if (actual.getDireccionempleador() != null && actual.getDireccionempleador().trim().length() > 0
				&& actual.getProvincia() != null && actual.getCanton() != null && actual.getParroquia() != null) {
			String codProvincia = actual.getProvincia();
			String codCanton = actual.getCanton();
			String codParroquia = actual.getParroquia();

			List<String> telefonoList = new ArrayList<String>();
			if (actual.getTelefonoempleador() != null && actual.getTelefonoempleador().trim().length() > 0) {
				telefonoList.add(actual.getTelefonoempleador().trim());
			}

			crearDireccionEnPersona(p, actual.getDireccionempleador(), codProvincia, codCanton, codParroquia, null,
					false, null, null, null, telefonoList, TipoDireccionEnum.TRABAJO, TipoTelefonoEnum.OFICINA);
		}

		// Se pone el telefono
		if (actual.getTelefonoempleador() != null && actual.getTelefonoempleador().trim().length() > 0) {
			crearTelefonoPersona(actual.getTelefonoempleador(), p);
			if (p.getTelefonoList().isEmpty()) {
				p.setTelefonoList(null);
			}
		}

		// Se crea la persona juridica
		PersonaJuridicaSd pj = new PersonaJuridicaSd();

		pj.setIdentificacion(actual.getRucempleador());
		pj.setRazonSocial(actual.getNombreempleador());
		pj.setActividadIess(actual.getActividadempleador());
		pj.setUsrCreacion(usuario);
		pj.setUsrModificacion(usuario);
		pj.setTsCreacion(new Date());
		pj.setCodTipoIdentificacion(getTipoIdentificacionRuc());
		pj.setSecPersona(p);
		pj.setSecCanal(getCanal());
		pj.setCodActividadEconomica(actividadEconomicaServicio.obtenerPorDefecto());

		// Se pone persona juridica en persona
		p.setPersonaJuridica(pj);

		return pj;
	}

	/**
	 * 
	 * @param identificacion
	 * @return
	 */
	private PersonaJuridicaSd buscarPersonaJuridicaPorIdentificaicon(String identificacion) {
		try {
			return personaJuridicaServicio.buscarPorDocumento(identificacion);
		} catch (FindException e) {
			return null;
		}
	}

	/**
	 * Arma la persona natural.
	 * 
	 * @param persona
	 * @param r
	 * @param usuario
	 */
	private void armarPersonaNatural(PersonaSd persona) {
		PersonaNaturalSd pn = new PersonaNaturalSd();

		// Persona
		pn.setSecPersona(persona);

		Civil civil = registros.getCivil();

		// Tipo Identificacion
		pn.setCodTipoIdentificacion(getTipoIdentificacionCedula());
		// Identificacion
		pn.setIdentificacion(civil.getCedula());

		log.info("databook log... cedula: " + civil.getCedula());

		// Apellido Paterno
		if (civil.getPrimerapellido() != null && civil.getPrimerapellido().trim().length() > 0) {
			pn.setApellidoPaterno(civil.getPrimerapellido());
			log.info("primer ape: " + civil.getPrimerapellido());
		}
		// Apellido materno
		if (civil.getSegundoapellido() != null && civil.getSegundoapellido().trim().length() > 0) {
			pn.setApellidoMaterno(civil.getSegundoapellido());
			log.info("segundo seg: " + civil.getSegundoapellido());
		}

		// Primer Nombre
		if (civil.getPrimernombre() != null && civil.getPrimernombre().trim().length() > 0) {
			pn.setPrimerNombre(civil.getPrimernombre());
			log.info("primer nombre: " + civil.getPrimernombre());
		}
		// Segundo Nombre
		if (civil.getSegundonombre() != null && civil.getSegundonombre().trim().length() > 0) {
			pn.setSegundoNombre(civil.getSegundonombre());
			log.info("segundo nombre: " + civil.getSegundonombre());
		}

		// Sexo
		pn.setSexo(GeneroEnum.getCodigoSexoSMPorCodigoGeneroDB(civil.getGenero()));

		// Pais
		PaisSd pais = getPaisPorDataBookCodigo(civil.getNacionalidad());
		if (pais != null) {
			pn.setCodPais(pais);
		}

		// Estado Civil
		pn.setCodEstadoCivil(consultarEstadoCivil(new Short(civil.getEstadocivil())));

		// Profesion
		if (civil.getProfesion() != null && civil.getProfesion().trim().length() > 0) {
			ProfesionSd pro = profesionServicio.consultarPorCodigoDB(civil.getProfesion().trim());
			if (pro != null) {
				pn.setSecProfesion(pro);
			} else {
				pn.setSecProfesion(profesionServicio.obtenerPorDefecto());
			}
		}

		// Fecha Nacimiento
		Date fchNacimiento = construyeFecha(Short.toString(registros.getCivil().getAnionacimiento()),
				Byte.toString(registros.getCivil().getMesnacimiento()),
				Byte.toString(registros.getCivil().getDianacimiento()));
		pn.setFchNacimiento(fchNacimiento);

		// Fecha Matrimonio
		Date fechaMatrimonio = construyeFecha(registros.getCivil().getAniomatrimonio(),
				registros.getCivil().getMesmatrimonio(), registros.getCivil().getDiamatrimonio());
		pn.setFchMatrimonio(fechaMatrimonio);

		// Fecha Fallece
		Date fechaFallece = construyeFecha(registros.getCivil().getAniodefuncion(),
				registros.getCivil().getMesdefuncion(), registros.getCivil().getDiadefuncion());
		if (fechaFallece != null) {
			pn.setFchFallecimiento(fechaFallece);
		}

		// Canal
		pn.setSecCanal(getCanal());

		// Usuario crea
		pn.setUsrCreacion(usuario);

		// Usuario modifica
		pn.setUsrModificacion(usuario);

		// Fecha crea
		pn.setTsCreacion(new Date());

		// Fecha modifica
		pn.setTsModificacion(new Date());

		// Se cargar informacion adicional
		cargarInformacionAdicional(pn);

		// Se pone la persona natural en persona
		persona.setPersonaNatural(pn);
	}

	/**
	 * Construye persona natural.
	 * 
	 * @param r
	 * @param usuario
	 * @return
	 */
	private PersonaSd construyePersona() {
		PersonaSd p = new PersonaSd();

		p.setCodTipoIdentificacion(getTipoIdentificacionCedula());
		p.setDenominacion(getNombresApellidos(registros));
		p.setIdentificacion(registros.getCivil().getCedula());

		// Los telefonos son dependientes de las direcciones
		// crearTelefonosPersonaNaturalContactabilidad(p);

		// Se pone la direccion de la persona de contactabilidad
		if (registros.getContactabilidad() != null && registros.getContactabilidad().getDireccion() != null
				&& registros.getContactabilidad().getDireccion().trim().length() > 0) {

			Contactabilidad contac = registros.getContactabilidad();

			String codProvincia = contac.getProvincia();
			String codCanton = contac.getCanton();
			String codParroquia = contac.getParroquia();

			List<String> telefonoList = new ArrayList<String>();

			if (contac.getTel1() != null && contac.getTel1().trim().length() > 0) {
				telefonoList.add(contac.getTel1().trim());
			}
			if (contac.getTel2() != null && contac.getTel2().trim().length() > 0) {
				telefonoList.add(contac.getTel2().trim());
			}
			if (contac.getTel3() != null && contac.getTel3().trim().length() > 0) {
				telefonoList.add(contac.getTel3().trim());
			}
			if (contac.getTel4() != null && contac.getTel4().trim().length() > 0) {
				telefonoList.add(contac.getTel4().trim());
			}
			if (contac.getTel5() != null && contac.getTel5().trim().length() > 0) {
				telefonoList.add(contac.getTel5().trim());
			}

			// Se crea la direccion y se enlaza al objeto persona
			crearDireccionEnPersona(p, getDireccionDeContactabilidad(), codProvincia, codCanton, codParroquia, null,
					false, null, null, null, telefonoList, TipoDireccionEnum.DOMICILIO, TipoTelefonoEnum.DOMICILIO);
		}

		// Se pone direccion de SRI
		Registros.Sri sri = registros.getSri();
		if (sri != null && sri.getCalle() != null && sri.getProvincia() != null
				&& sri.getProvincia().trim().length() > 0 && sri.getCanton() != null
				&& sri.getCanton().trim().length() > 0 && sri.getParroquia() != null
				&& sri.getParroquia().trim().length() > 0) {
			String codProvincia = sri.getProvincia().trim();
			String codCanton = sri.getCanton().trim();
			String codParroquia = sri.getParroquia().trim();

			StringBuffer direccion = new StringBuffer(500);

			boolean direccionCompleta = false;

			if (sri.getCalle() != null && sri.getCalle().trim().length() > 0) {
				direccion.append(sri.getCalle());
				direccionCompleta = true;
			}
			if (sri.getNumero() != null && sri.getNumero().trim().length() > 0) {
				direccion.append(" ").append(sri.getNumero());
				direccionCompleta = true;
			}
			if (sri.getInterseccion() != null && sri.getInterseccion().trim().length() > 0) {
				direccion.append(" ").append(sri.getInterseccion());
				direccionCompleta = true;
			}

			List<String> telefonoList = new ArrayList<String>();
			if (sri.getTelefono() != null && sri.getTelefono().trim().length() > 0) {
				telefonoList.add(sri.getTelefono().trim());
			}

			// Se crea la direccion y se enlaza al objeto persona
			crearDireccionEnPersona(p, direccion.toString(), codProvincia, codCanton, codParroquia, sri.getReferencia(),
					direccionCompleta, sri.getCalle(), sri.getNumero(), sri.getInterseccion(), telefonoList,
					TipoDireccionEnum.DOMICILIO, TipoTelefonoEnum.DOMICILIO);
		}

		// Se pone direccion del empleo actual
		if (registros.getActual() != null) {
			Registros.Actual actual = registros.getActual();

			if (actual.getDireccionempleador() != null && actual.getDireccionempleador().trim().length() > 0
					&& actual.getProvincia() != null && actual.getProvincia().trim().length() > 0
					&& actual.getCanton() != null && actual.getCanton().trim().length() > 0
					&& actual.getParroquia() != null && actual.getParroquia().trim().length() > 0) {
				String codProvincia = actual.getProvincia().trim();
				String codCanton = actual.getCanton().trim();
				String codParroquia = actual.getParroquia().trim();

				List<String> telefonoList = new ArrayList<String>();
				if (actual.getTelefonoempleador() != null && actual.getTelefonoempleador().trim().length() > 0) {
					telefonoList.add(actual.getTelefonoempleador().trim());
				}

				// Se crea la direccion y se enlaza al objeto persona
				crearDireccionEnPersona(p, actual.getDireccionempleador().trim(), codProvincia, codCanton, codParroquia,
						null, false, sri.getCalle(), null, null, telefonoList, TipoDireccionEnum.TRABAJO,
						TipoTelefonoEnum.OFICINA);
			}
		}

		// Se carga vehiculos
		cargarVehiculos(p);

		return p;
	}

	/**
	 * Arma la informacion adicional de ser el caso.
	 * 
	 * @param r
	 * @param personaNatural
	 * @param usuario
	 */
	private void cargarInformacionAdicional(PersonaNaturalSd personaNatural) {
		if (registros.getSri() != null && registros.getSri().getRucpersonanatual().trim().length() > 0) {
			Sri sri = registros.getSri();

			InformacionAdicionalSd ia = new InformacionAdicionalSd();
			ia.setCodTipoIdentificacion(new TipoIdentificacionSd('R'));
			ia.setIdentificacion(sri.getRucpersonanatual());
			ia.setRazonSocial(sri.getRazonsocial());
			if (sri.getNombrefantasia() != null && sri.getNombrefantasia().trim().length() > 0) {
				ia.setNombreComercial(sri.getNombrefantasia());
			} else {
				// Se define con Vivi, que si no existe nombre de fantasia, se
				// debe colocar la razon social
				ia.setNombreComercial(sri.getRazonsocial());
			}

			if (sri.getFechainicio() != null && sri.getFechainicio().trim().length() >= 10) {
				ia.setFchInicioActividades(obtenerFecha(sri.getFechainicio(), SDF_SRI));
				// Se consulta con Vivi y se define que en este campo se pone la
				// fecha de inicio que veine del WS
				ia.setFchInscripcion(ia.getFchInicioActividades());
			}
			if (sri.getSuspension() != null && sri.getSuspension().trim().length() >= 10) {
				ia.setFchSuspension(obtenerFecha(sri.getSuspension(), SDF_SRI));
			}
			if (sri.getReinicio() != null && sri.getReinicio().trim().length() >= 10) {
				ia.setFchReinicio(obtenerFecha(sri.getReinicio(), SDF_SRI));
			}
			ia.setPrincipal(sri.getCalle());
			ia.setNumero(sri.getNumero());
			ia.setSecundaria(sri.getInterseccion());
			ia.setReferencia(sri.getReferencia());
			ia.setTelefono(sri.getTelefono());
			if (sri.getProvincia() != null && sri.getProvincia().trim().length() > 0) {
				ia.setSecProvincia(getProvinciaSD(sri.getProvincia()));
			}
			if (sri.getCanton() != null && sri.getCanton().trim().length() > 0) {
				ia.setSecCanton(getCantonSD(sri.getCanton()));
			}
			if (sri.getParroquia() != null && sri.getParroquia().trim().length() > 0) {
				ia.setSecParroquia(getParroquiaSD(sri.getParroquia()));
			}
			if (sri.getActividad() != null && sri.getActividad().trim().length() > 0) {
				// Si el codigo de actividad economica no existe, se pone
				// actividad economica por defecto: 4829
				ActividadEconomicaSd ae = actividadEconomicaServicio.obtenerPorCodigoSri(sri.getActividad().trim());

				if (ae != null) {
					ia.setCodActividadEconomica(ae);
				} else {
					ia.setCodActividadEconomica(actividadEconomicaServicio.obtenerPorDefecto());
				}
			}
			ia.setUsrCreacion(usuario);
			ia.setUsrModificacion(usuario);
			ia.setSecPersonaNatural(personaNatural);
			ia.setSecCanal(getCanal());

			// Se crea la lista
			List<InformacionAdicionalSd> iaLista = new ArrayList<InformacionAdicionalSd>();
			iaLista.add(ia);

			// Se pone lista en persona natural
			personaNatural.setInformacionAdicionalList(iaLista);
		}
	}

	/**
	 * Se crea la lista de vehiculos y se la ata a la persona.
	 * 
	 * @param r
	 * @param persona
	 * @param usuario
	 */
	private void cargarVehiculos(PersonaSd persona) {
		if (registros.getVehicular() != null && registros.getVehicular().getPlaca() != null
				&& registros.getVehicular().getPlaca().trim().length() > 0) {
			VehiculoSd v = new VehiculoSd();
			v.setPlaca(registros.getVehicular().getPlaca());
			v.setChasis("");
			v.setMarca("");
			v.setAvaluo(BigDecimal.ZERO);
			v.setModelo("");
			v.setAnio(0);

			v.setSecCanal(getCanal());
			v.setSecPersona(persona);
			v.setUsrCreacion(usuario);
			v.setUsrModificacion(usuario);

			List<VehiculoSd> listaVehiculos = new ArrayList<VehiculoSd>();
			listaVehiculos.add(v);

			// Se pone la lista en la persona
			persona.setVehiculoList(listaVehiculos);
		}
	}

	/**
	 * Crea objeto direccion dado los parametros del metodo.
	 * 
	 * TODO: preguntar si ya existe en la BDD de Smart Data.
	 * 
	 * @param p
	 * @param direccionFisica
	 * @param provincia
	 * @param canton
	 * @param parroquia
	 * @return
	 */
	private void crearDireccionEnPersona(PersonaSd p, String direccionFisica, String provincia, String canton,
			String parroquia, String referencia, boolean direccionCompleta, String callePrinciapal, String numero,
			String calleSecundaria, List<String> telefonos, TipoDireccionEnum tipoDireccion,
			TipoTelefonoEnum tipoTelefono) {

		if (provincia != null && canton != null && parroquia != null && provincia.trim().length() > 0
				&& canton.trim().length() > 0 && parroquia.trim().length() > 0 && direccionFisica != null
				&& direccionFisica.trim().length() > 0) {

			DireccionSd direccionEncontrada = direccionSdServicio.obtenerPorDocumentoDireccion(p.getIdentificacion(),
					direccionFisica);

			if (direccionEncontrada == null) {
				DireccionSd direccion = new DireccionSd();
				direccion.setCodTipoDireccion(new TipoDireccionSd(tipoDireccion.getCodigoenBase()));
				direccion.setDireccion(direccionFisica);
				direccion.setEstado(EstadoEnum.A.getEstadoChar());
				direccion.setSecCanal(getCanal());
				direccion.setUsrCreacion(usuario);
				direccion.setUsrModificacion(usuario);
				direccion.setSecPersona(p);
				direccion.setReferencia(referencia);
				direccion.setDireccionCompleta(direccionCompleta);

				if (direccionCompleta) {
					direccion.setPrincipalTr(callePrinciapal);
					direccion.setNumeroTr(numero);
					direccion.setSecundariaTr(calleSecundaria);
				}

				if (provincia != null && provincia.trim().length() > 0) {
					direccion.setSecProvincia(getProvinciaSD(provincia));
				}

				if (canton != null && canton.trim().length() > 0) {
					direccion.setSecCanton(getCantonSD(canton));
				}

				if (parroquia != null && parroquia.trim().length() > 0) {
					direccion.setSecParroquia(getParroquiaSD(parroquia));
				}

				if (telefonos != null && !telefonos.isEmpty()) {
					for (String t : telefonos) {
						crearTelefonoDireccion(t, direccion, tipoTelefono, p);
					}
				}

				if (p.getDireccionList() == null) {
					p.setDireccionList(new ArrayList<DireccionSd>());
				}

				// Se pone la direccion en la persona
				p.getDireccionList().add(direccion);
			} else {
				if (p.getDireccionNoPersisteList() == null) {
					p.setDireccionNoPersisteList(new ArrayList<DireccionSd>());
				}

				p.getDireccionNoPersisteList().add(direccionEncontrada);
			}
		}
	}

	/**
	 * Crea arreglo de telefonos para persona natural.
	 * 
	 * @param r
	 * @param p
	 * @param usuario
	 * @return
	 */
	@Deprecated
	private void crearTelefonosPersonaNaturalContactabilidad(PersonaSd p) {
		if (registros.getContactabilidad() != null) {
			Contactabilidad contac = registros.getContactabilidad();
			if (contac.getTel1() != null && contac.getTel1().trim().length() > 0) {
				crearTelefonoPersona(contac.getTel1().trim(), p);
			}

			if (contac.getTel2() != null && contac.getTel2().trim().length() > 0) {
				crearTelefonoPersona(contac.getTel2().trim(), p);
			}

			if (contac.getTel3() != null && contac.getTel3().trim().length() > 0) {
				crearTelefonoPersona(contac.getTel3().trim(), p);
			}

			if (contac.getTel4() != null && contac.getTel4().trim().length() > 0) {
				crearTelefonoPersona(contac.getTel4().trim(), p);
			}

			if (contac.getTel5() != null && contac.getTel5().trim().length() > 0) {
				crearTelefonoPersona(contac.getTel5().trim(), p);
			}
		}

		// Se aumenta el telefono del SRI
		if (registros.getSri() != null && registros.getSri().getTelefono() != null
				&& registros.getSri().getTelefono().trim().length() > 0) {
			crearTelefonoPersona(registros.getSri().getTelefono().trim(), p);
		}

		if (p.getTelefonoList() != null && p.getTelefonoList().isEmpty()) {
			p.setTelefonoList(null);
		}
	}

	/**
	 * Se crea los telefonos de la direccion.
	 * 
	 * @param telefono
	 * @param d
	 */
	private void crearTelefonoDireccion(String telefono, DireccionSd d, TipoTelefonoEnum tipoTelefono,
			PersonaSd persona) {
		TipoTelefonoSd tipo = new TipoTelefonoSd(tipoTelefono.getCodigoTipoTelefono());

		String codArea = "00";
		String telefonosimple = telefono;

		if (telefono.length() >= 9 || telefono.startsWith("09") || telefono.startsWith("08")) {
			codArea = telefono.substring(0, 2);
			telefonosimple = telefono.substring(2);
		}

		if (d.getDireccionTelefonoList() == null) {
			d.setDireccionTelefonoList(new ArrayList<DireccionTelefonoSd>());
		}

		if (persona.getTelefonoList() == null) {
			persona.setTelefonoList(new ArrayList<TelefonoSd>());
		}

		// Pregunta si el telefono ya esta en el listado
		boolean existeEnPersona = false;
		TelefonoSd resp = new TelefonoSd();

		for (TelefonoSd t : persona.getTelefonoList()) {
			if (t.getNroTelefono().equals(telefonosimple)) {
				existeEnPersona = true;
				resp = t;
				break;
			}
		}

		if (!existeEnPersona) {
			resp.setCodArea(codArea);
			resp.setCodTipoTelefono(tipo);
			resp.setEstado(EstadoEnum.A.getEstadoChar());
			resp.setNroTelefono(telefonosimple);
			resp.setSecCanal(getCanal());
			resp.setSecPersona(persona);
			resp.setUsrCreacion(usuario);
			resp.setUsrModificacion(usuario);

			persona.getTelefonoList().add(resp);
		}

		// Se verifica si no existe en la direccion
		boolean existeDireccion = false;
		for (DireccionTelefonoSd dt : d.getDireccionTelefonoList()) {
			if (dt.getTelefono().getNroTelefono().equals(telefonosimple)) {
				existeDireccion = true;
				break;
			}
		}

		if (!existeDireccion) {
			DireccionTelefonoSd dirTel = new DireccionTelefonoSd();
			dirTel.setDireccion(d);
			dirTel.setPersona(persona);
			dirTel.setTelefono(resp);

			d.getDireccionTelefonoList().add(dirTel);

			if (resp.getDireccionTelefonoList() == null) {
				resp.setDireccionTelefonoList(new ArrayList<DireccionTelefonoSd>());
			}

			resp.getDireccionTelefonoList().add(dirTel);
		}
	}

	/**
	 * Crea un telefono.
	 * 
	 * @param telefono
	 * @param persona
	 * @return
	 */
	private void crearTelefonoPersona(String telefono, PersonaSd persona) {
		TipoTelefonoSd tipo = new TipoTelefonoSd(new Short("1"));
		String codArea = "00";
		String telefonosimple = telefono;

		if (telefono.length() >= 9 || telefono.startsWith("09") || telefono.startsWith("08")) {
			codArea = telefono.substring(0, 2);
			telefonosimple = telefono.substring(2);
		}

		// Pregunta si el telefono ya esta en el listado
		boolean existe = false;
		for (TelefonoSd t : persona.getTodosTelefonos()) {
			if (t.getNroTelefono().equals(telefonosimple)) {
				existe = true;
				break;
			}
		}

		if (!existe) {
			// consulata si el telefono ya existe en la BDD
			TelefonoSd telefonoEncontrado = telefonoSdServicio.obtenerPorDocumentoTelefono(persona.getIdentificacion(),
					telefonosimple);

			if (telefonoEncontrado == null) {
				TelefonoSd resp = new TelefonoSd();
				resp.setCodArea(codArea);
				resp.setCodTipoTelefono(tipo);
				resp.setEstado(EstadoEnum.A.getEstadoChar());
				resp.setNroTelefono(telefonosimple);
				resp.setSecCanal(getCanal());
				resp.setSecPersona(persona);
				resp.setUsrCreacion(usuario);
				resp.setUsrModificacion(usuario);

				if (persona.getTelefonoList() == null) {
					persona.setTelefonoList(new ArrayList<TelefonoSd>());
				}

				persona.getTelefonoList().add(resp);
			} else {
				if (persona.getTelefonoNoPersisteList() == null) {
					persona.setTelefonoNoPersisteList(new ArrayList<TelefonoSd>());
				}

				persona.getTelefonoNoPersisteList().add(telefonoEncontrado);
			}
		}
	}

	/**
	 * Obtener nombres y apellidos.
	 * 
	 * @param r
	 * @return
	 */
	private String getNombresApellidos(Registros r) {
		StringBuffer nombresApellidos = new StringBuffer(300);

		if (r.getCivil().getPrimernombre() != null && r.getCivil().getPrimernombre().trim().length() > 0) {
			nombresApellidos.append(r.getCivil().getPrimernombre()).append(" ");
		}

		if (r.getCivil().getSegundonombre() != null && r.getCivil().getSegundonombre().trim().length() > 0) {
			nombresApellidos.append(r.getCivil().getSegundonombre()).append(" ");
		}

		if (r.getCivil().getPrimerapellido() != null && r.getCivil().getPrimerapellido().trim().length() > 0) {
			nombresApellidos.append(r.getCivil().getPrimerapellido()).append(" ");
		}

		if (r.getCivil().getSegundoapellido() != null && r.getCivil().getSegundoapellido().trim().length() > 0) {
			nombresApellidos.append(r.getCivil().getSegundoapellido()).append(" ");
		}

		String resp = "";

		if (nombresApellidos.length() > 0) {
			resp = nombresApellidos.toString().trim();
		}

		return resp;
	}

	/**
	 * Obtiene Objeto Pais dado el codigo pais de dataBook.
	 * 
	 * @param codigoPaisDB
	 * @return
	 */
	private PaisSd getPaisPorDataBookCodigo(Short codigoPaisDB) {
		Short codPais = noPkTablasDao.obtenerCodigoPaisPorCodigoDB(codigoPaisDB);
		PaisSd pais = null;

		if (codPais != null) {
			pais = new PaisSd();
			pais.setCodPais(codPais);
		}

		return pais;
	}

	/**
	 * Obtiene direccion.
	 * 
	 * @param r
	 * @return
	 */
	private String getDireccionDeContactabilidad() {
		String resp = "";

		if (registros.getContactabilidad() != null) {
			if (registros.getContactabilidad().getDireccion() != null
					&& registros.getContactabilidad().getDireccion().trim().length() > 0) {
				resp = registros.getContactabilidad().getDireccion().trim();
			}
		}

		return resp;
	}

	/**
	 * Obtiene tipo de identificacion.
	 * 
	 * @return
	 */
	private TipoIdentificacionSd getTipoIdentificacionCedula() {
		return new TipoIdentificacionSd(TipoIdentificacionSDEnum.C.toString().charAt(0));
	}

	/**
	 * Obtiene tipo de identificacion.
	 * 
	 * @return
	 */
	private TipoIdentificacionSd getTipoIdentificacionRuc() {
		return new TipoIdentificacionSd(TipoIdentificacionSDEnum.R.toString().charAt(0));
	}

	/**
	 * btiene Canal. TODO: Se asume que es WEB
	 * 
	 * @return
	 */
	public static CanalSd getCanal() {
		return new CanalSd(new Short("3"));
	}

	/**
	 * btiene Canal. TODO: Se asume que es WEB
	 * 
	 * @return
	 */
	public static CanalSd getCanalClienteUnico() {
		return new CanalSd(new Short("2"));
	}

	/**
	 * Obtiene el estado civil de Smartdata.
	 * 
	 * @param idEstadoCivil
	 * @return
	 */
	private EstadoCivilSd consultarEstadoCivil(Short idEstadoCivil) {
		EstadoCivilSd ec = new EstadoCivilSd(new Short("0"));

		try {
			ec = estadoCivilServicio.findByPk(idEstadoCivil);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return ec;
	}

	/**
	 * Obtiene Date from String.
	 * 
	 * @param fechaStr
	 * @param sdf
	 * @return
	 */
	private Date obtenerFecha(String fechaStr, SimpleDateFormat sdf) {
		Date resp = null;

		try {
			resp = sdf.parse(fechaStr);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		return resp;
	}

	/**
	 * Construye objeto fecha en base a los valores del databook. TODO: revisar tema
	 * de mes
	 * 
	 * @param anio
	 * @param mes
	 * @param dia
	 * @return
	 */
	private Date construyeFecha(String anio, String mes, String dia) {
		if (anio != null && anio.trim().length() > 0 && mes != null && mes.trim().length() > 0 && dia != null
				&& dia.trim().length() > 0) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, Integer.parseInt(anio));
			c.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));

			return c.getTime();
		}

		return null;
	}

	/**
	 * Obtiene provincia dado el id de DataBook.
	 * 
	 * @param idProvinciaDB
	 * @return
	 */
	private ProvinciaSd getProvinciaSD(String idProvinciaDB) {
		ProvinciaSd resp = null;

		Short secProvincia = noPkTablasDao.obtenerCodigoProvinciaPorCodigoDB(idProvinciaDB, ConsultaEnEnum.IESS);

		if (secProvincia != null) {
			resp = new ProvinciaSd();
			resp.setSecProvincia(secProvincia);
		}

		return resp;
	}

	/**
	 * Obtiene canton dado el id de DataBook.
	 * 
	 * @param idCantonDB
	 * @return
	 */
	private CantonSd getCantonSD(String idCantonDB) {
		CantonSd resp = null;

		Short secCanton = noPkTablasDao.obtenerCodigoCantonPorCodigoDB(idCantonDB, ConsultaEnEnum.IESS);

		if (secCanton != null) {
			resp = new CantonSd();
			resp.setSecCanton(secCanton);
		}

		return resp;
	}

	/**
	 * Obtiene canton dado el id de DataBook.
	 * 
	 * @param idPArroquiaDB
	 * @return
	 */
	private ParroquiaSd getParroquiaSD(String idPArroquiaDB) {
		ParroquiaSd resp = null;

		Short secParroquia = noPkTablasDao.obtenerCodigoParroquiaPorCodigoDB(idPArroquiaDB, ConsultaEnEnum.IESS);

		if (secParroquia != null) {
			resp = new ParroquiaSd();
			resp.setSecParroquia(secParroquia);
		}

		return resp;
	}

	// Gets / Sets
	public Registros getRegistros() {
		return registros;
	}

	public void setRegistros(Registros registros) {
		this.registros = registros;
	}

	public NoPkTablasSdDao getNoPkTablasDao() {
		return noPkTablasDao;
	}

	public void setNoPkTablasDao(NoPkTablasSdDao noPkTablasDao) {
		this.noPkTablasDao = noPkTablasDao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public EstadoCivilSdServicio getEstadoCivilServicio() {
		return estadoCivilServicio;
	}

	public void setEstadoCivilServicio(EstadoCivilSdServicio estadoCivilServicio) {
		this.estadoCivilServicio = estadoCivilServicio;
	}

	public ProfesionSdServicio getProfesionServicio() {
		return profesionServicio;
	}

	public void setProfesionServicio(ProfesionSdServicio profesionServicio) {
		this.profesionServicio = profesionServicio;
	}

	public ActividadEconomicaSdServicio getActividadEconomicaServicio() {
		return actividadEconomicaServicio;
	}

	public void setActividadEconomicaServicio(ActividadEconomicaSdServicio actividadEconomicaServicio) {
		this.actividadEconomicaServicio = actividadEconomicaServicio;
	}

}
