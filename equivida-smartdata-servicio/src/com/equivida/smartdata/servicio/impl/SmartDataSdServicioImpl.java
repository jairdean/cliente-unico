package com.equivida.smartdata.servicio.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import com.equivida.databook.client.DatabookService;
import com.equivida.databook.client.impl.DatabookServiceImpl;
import com.equivida.databook.exception.DatabookException;
import com.equivida.databook.model.Registros;
import com.equivida.databook.model.RegistrosEntity;
import com.equivida.databook.model.RegistrosEntity.Direccion;
import com.equivida.databook.model.RegistrosEntity.InformacionAdicional;
import com.equivida.databook.model.RegistrosEntity.Persona;
import com.equivida.databook.model.RegistrosEntity.PersonaNatural;
import com.equivida.databook.model.RegistrosEntity.Telefono1;
import com.equivida.databook.model.RegistrosEntity.Telefono2;
import com.equivida.databook.model.RegistrosEntity.Telefono3;
import com.equivida.databook.model.RegistrosEntity.Telefono4;
import com.equivida.databook.model.RegistrosEntity.Telefono5;
import com.equivida.databook.model.RegistrosEntity.Telefono6;
import com.equivida.databook.model.RegistrosEntity.Titular;
import com.equivida.databook.model.RegistrosEntity.Trabajo;
import com.equivida.smartdata.constante.ConsultaEnEnum;
import com.equivida.smartdata.constante.EstadoEnum;
import com.equivida.smartdata.constante.PropiedadesKeyEnum;
import com.equivida.smartdata.constante.TipoDireccionEnum;
import com.equivida.smartdata.constante.TipoParentescoEnum;
import com.equivida.smartdata.constante.UsuarioEnum;
import com.equivida.smartdata.dao.NoPkTablasSdDao;
import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.exception.FindException;
import com.equivida.smartdata.exception.SmartdataException;
import com.equivida.smartdata.helper.DataBookHelper;
import com.equivida.smartdata.model.ActividadEconomicaSd;
import com.equivida.smartdata.model.CanalSd;
import com.equivida.smartdata.model.CantonSd;
import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.equivida.smartdata.model.EstadoCivilSd;
import com.equivida.smartdata.model.PaisSd;
import com.equivida.smartdata.model.ParroquiaSd;
import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.model.ProfesionSd;
import com.equivida.smartdata.model.ProvinciaSd;
import com.equivida.smartdata.model.RelacionSd;
import com.equivida.smartdata.model.TelefonoSd;
import com.equivida.smartdata.model.TipoDireccionElectronicaSd;
import com.equivida.smartdata.model.TipoDireccionSd;
import com.equivida.smartdata.model.TipoIdentificacionSd;
import com.equivida.smartdata.model.TipoParentescoRelacionSd;
import com.equivida.smartdata.model.TipoTelefonoSd;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.equivida.smartdata.servicio.ActividadEconomicaSdServicio;
import com.equivida.smartdata.servicio.CantonSdServicio;
import com.equivida.smartdata.servicio.DireccionElectronicaSdServicio;
import com.equivida.smartdata.servicio.DireccionSdServicio;
import com.equivida.smartdata.servicio.EmpleoDependienteSdServicio;
import com.equivida.smartdata.servicio.EstadoCivilSdServicio;
import com.equivida.smartdata.servicio.PersonaJuridicaSdServicio;
import com.equivida.smartdata.servicio.PersonaNaturalSdServicio;
import com.equivida.smartdata.servicio.PersonaSdServicio;
import com.equivida.smartdata.servicio.ProfesionSdServicio;
import com.equivida.smartdata.servicio.ProvinciaSdServicio;
import com.equivida.smartdata.servicio.RelacionSdServicio;
import com.equivida.smartdata.servicio.SmartDataSdServicio;
import com.equivida.smartdata.servicio.SmartDataServicioSdRemote;
import com.equivida.smartdata.servicio.TelefonoSdServicio;
import com.equivida.smartdata.servicio.TipoTelefonoSdServicio;
import com.equivida.smartdata.servicio.PersonaNaturalServicio;
import com.equivida.smartdata.servicio.InformacionAdicionalSdServicio;
import com.equivida.smartdata.servicio.ParroquiaSdServicio;

@Stateless(name = "SmartDataSdServicio")
public class SmartDataSdServicioImpl implements SmartDataSdServicio, SmartDataServicioSdRemote {
	private Logger log = Logger.getLogger(SmartDataSdServicioImpl.class);

	@EJB
	private PersonaNaturalSdServicio personaNaturalServicio;
	@EJB
	private PersonaJuridicaSdServicio personaJuridicaServicio;
	@EJB
	private PersonaSdServicio personaServicio;
	@EJB
	private NoPkTablasSdDao noPkTablasDao;
	@EJB
	private EstadoCivilSdServicio estadoCivilServicio;
	@EJB
	private ProfesionSdServicio profesionServicio;
	@EJB
	private ActividadEconomicaSdServicio actividadEconomicaServicio;
	@EJB
	private EmpleoDependienteSdServicio empleoDependienteServicio;
	@EJB
	private RelacionSdServicio relacionServicio;
	@EJB
	private TelefonoSdServicio telefonoSdServicio;
	@EJB
	private DireccionSdServicio direccionSdServicio;
	@EJB
	private PersonaNaturalServicio personaNatServicio;
	@EJB
	private DireccionElectronicaSdServicio direccionElectronicaSdServicio;
	@EJB
	private InformacionAdicionalSdServicio informacionAdicionalSdServicio;
	@EJB
	private ProvinciaSdServicio provinciaSdServicio;
	@EJB
	private CantonSdServicio cantonSdServicio;
	@EJB
	private ParroquiaSdServicio parroquiaSdServicio;
	@EJB
	private TipoTelefonoSdServicio tipoTelefonoSdServicio;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.SmartDataServicio#
	 * consultaClienteSmartData(java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersonaSd consultaClienteSmartData(String identificacion, boolean conRelaciones) throws SmartdataException {

		// 1. Se busca Cliente en las tablas del esquema SMARTDATA
		PersonaNaturalSd pn = personaNaturalServicio.obtenerPersonaByIdentificacion(identificacion, conRelaciones);

		PersonaSd persona = null;

		// 2. Hay ocaciones en las que no existe la persona en la tabla PersonaNatural,
		// en tonces se busca en la tabla Persona
		if (pn == null) {
			System.out.println(
					"===========================No hay PersonaNaturalSd, se consulta PersonaSd con identificacion: "
							+ identificacion + " - " + new Date());
			persona = personaServicio.obtenerPersonaByIdentificacion(identificacion);

		}

		if (pn == null && persona == null) {
			throw new SmartdataException(
					"No se encuentra datos con identificacion: ".concat(identificacion).concat(" en Smartdata"));
		}

		if (pn != null) {
			return pn.getSecPersona();
		}

		return persona;

		// 1. Se busca Cliente en las tablas del esquema SMARTDATA
		/*
		 * PersonaNaturalSd pn =
		 * personaNaturalServicio.obtenerPersonaByIdentificacion(identificacion,
		 * conRelaciones);
		 * 
		 * PersonaSd persona = null;
		 * 
		 * // 2. Hay ocaciones en las que no existe la persona en la tabla
		 * PersonaNatural, // en tonces se busca en la tabla Persona if (pn == null) {
		 * System.out.println(
		 * "===========================No hay PersonaNaturalSd, se consulta PersonaSd con identificacion: "
		 * + identificacion + " - " + new Date()); persona =
		 * personaServicio.obtenerPersonaByIdentificacion(identificacion);
		 * 
		 * }
		 * 
		 * if (pn == null && persona == null) { throw new SmartdataException(
		 * "No se encuentra datos con identificacion: ".concat(identificacion).
		 * concat(" en Smartdata")); }
		 * 
		 * if (pn != null) { return pn.getSecPersona(); }
		 * 
		 * return persona;
		 */
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersonaSd consultaPersonaJuridicaSmartData(String identificacion, boolean conRelaciones)
			throws SmartdataException {
		PersonaJuridicaSd personaJuridica = null;
		PersonaSd persona = null;

		try {
			// 1. Busca persona juridica
			personaJuridica = personaJuridicaServicio.buscarPorDocumento(identificacion);
		} catch (FindException e) {

		}

		if (personaJuridica == null) {
			persona = personaServicio.obtenerPersonaByIdentificacion(identificacion);
		}

		if (personaJuridica == null && persona == null) {
			throw new SmartdataException(
					"No se encuentra datos con identificacion: ".concat(identificacion).concat(" en Smartdata"));
		}

		if (personaJuridica != null) {
			return personaJuridica.getSecPersona();
		}

		return persona;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.servicio.SmartDataServicioRemote#guardaEnSmartData
	 * (com.equivida.smartdata.model.Persona)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void guardaEnSmartData(PersonaSd persona) throws SmartdataException {
		if (persona.getSecPersona() == null) {
			personaServicio.create(persona);
		} else {

			if (persona.getPersonaNatural().getSecPersonaNatural() == null) {
				persona.getPersonaNatural().setSecPersona(persona);
				personaNaturalServicio.create(persona.getPersonaNatural());
			}

			PersonaSd bdd = personaServicio.findByPk(persona.getSecPersona());

			bdd.setCodTipoIdentificacion(persona.getCodTipoIdentificacion());
			bdd.setConyuge(persona.getConyuge());
			bdd.setDenominacion(persona.getDenominacion());
			bdd.setIdentificacion(persona.getIdentificacion());
			if (bdd.getTelefonoList() != null) {
				bdd.getTelefonoList().size();
			}
			if (bdd.getDireccionList() != null) {
				bdd.getDireccionList().size();
			}
			List<TelefonoSd> telefonos = bdd.getTelefonoList();
			List<DireccionSd> direcciones = bdd.getDireccionList();

			telefonos = actualizarTelefonosSd(persona, telefonos);

			bdd.setTelefonoList(telefonos);

			direcciones = actualizarDireccionesSd(persona, direcciones);

			bdd.setDireccionList(direcciones);

			persona = bdd;
		}

	}

	private List<TelefonoSd> actualizarTelefonosSd(PersonaSd persona, List<TelefonoSd> telefonos) {
		if (persona.getTelefonoList() != null) {

			for (TelefonoSd t : persona.getTelefonoList()) {

				// busca telefono para ver si crea
				boolean encontrado = false;

				if (telefonos == null) {
					telefonos = new ArrayList<TelefonoSd>();
				}

				for (TelefonoSd tbdd : telefonos) {

					if (tbdd.getNroTelefono().equals(t.getNroTelefono())
							&& tbdd.getNroTelefono().equals(t.getNroTelefono())) {

						if (tbdd.getCodTipoTelefono() != null && t.getCodTipoTelefono() != null) {
							if (tbdd.getCodTipoTelefono().getCodTipoTelefono()
									.equals(t.getCodTipoTelefono().getCodTipoTelefono())) {

								encontrado = true;
								break;

							}
						}
					}
				}

				if (!encontrado) {
					telefonos.add(t);
				}
			}
		}
		return telefonos;
	}

	private List<DireccionSd> actualizarDireccionesSd(PersonaSd persona, List<DireccionSd> direcciones) {
		if (persona.getDireccionList() != null) {

			for (DireccionSd d : persona.getDireccionList()) {

				// busca telefono para ver si crea
				boolean encontrado = false;

				if (direcciones == null) {
					direcciones = new ArrayList<DireccionSd>();
				}

				for (DireccionSd dbdd : direcciones) {

					if (dbdd.getDireccion().equals(d.getDireccion())) {

						if (dbdd.getCodTipoDireccion() != null && d.getCodTipoDireccion() != null) {
							if (dbdd.getCodTipoDireccion().getCodTipoDireccion()
									.equals(d.getCodTipoDireccion().getCodTipoDireccion())) {

								encontrado = true;
								break;

							}
						}
					}
				}

				if (!encontrado) {
					direcciones.add(d);
				}
			}
		}
		return direcciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.SmartDataServicio#
	 * consultaEnDataBookPersisteSmartData(java.lang.String, java.lang.String)
	 */
	@Override
	public PersonaSd consultaEnDataBookPersisteSmartData(String identificacion, String usuario)
			throws SmartdataException {
		try {
			// 1. Consulta en databook
			Registros registros = obtenerRegistrosWs(identificacion);

			// 2. Se crea el helper para obtener las distintas personas del WS
			DataBookHelper dbh = new DataBookHelper(registros, usuario, noPkTablasDao, estadoCivilServicio,
					profesionServicio, actividadEconomicaServicio, personaJuridicaServicio, personaServicio,
					direccionSdServicio, telefonoSdServicio);

			// 3 Obtiene persona natural
			PersonaSd personaSd = dbh.getPersonaNatural();

			// 4. Se debe crear las relaciones con conyuge, padre, madre
			crearRelacionesPersonales(registros, personaSd, usuario);

			// 5. Obtiene o Crea Personas juridicas
			List<PersonaSd> listaPj = dbh.getPersonasJuridicas();

			// 6. Crea relaciones laborales en el caso de existir personas
			// juridicas
			if (listaPj != null && !listaPj.isEmpty()) {
				relacionarPersonaNaturalPersonaJuridica(personaSd, listaPj, registros, usuario);
			}

			// 7. Persite en datasmart persona natural con todas las listas
			personaServicio.create(personaSd);

			return personaSd;
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e.getCause());
			throw new SmartdataException(e.getMessage(), e.getCause());
		} catch (IOException e) {
			log.error(e.getMessage(), e.getCause());
			throw new SmartdataException(e.getMessage(), e.getCause());
		} catch (DatabookException e) {
			log.error(e.getMessage(), e.getCause());
			throw new SmartdataException(e.getMessage(), e.getCause());
		} catch (ParseException e) {
			log.error(e.getMessage(), e.getCause());
			throw new SmartdataException(e.getMessage(), e.getCause());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.SmartDataServicio#consultaDatabook(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersonaSd consultaDatabook(String identificacion, String usuario) throws SmartdataException {
		try {
			// Se consulta en el WS.
			log.error("LLEGA 1");
			RegistrosEntity registros = obtenerRegistrosWsRegistrosEntity(identificacion);

			if (registros.getTitular() == null || registros.getTitular().getPersona() == null) {
				/*
				 * <?xml version="1.0" encoding="UTF-8"?> <registros> <error>NUT no
				 * existe</error> </registros>
				 */
				log.error("NUT no existe");
				return null;
			}

			// INGRESO RESGISTROS DE LA PERSONA EN LA BASE DE DATOS
			PersonaSd retornar = GuardarInformacionPersona(registros.getTitular());

			if (registros.getConyuge() != null && retornar != null)
				GuardarInformacionConyugue(registros.getConyuge());

			log.error("LLEGA 2");

			/*
			 * Registros abc = new Registros();
			 * 
			 * System.out.
			 * println("===========================transforma DB a SD consulta en DB: "
			 * +identificacion + new Date()); DataBookHelper dbh = new DataBookHelper(abc,
			 * usuario, noPkTablasDao, estadoCivilServicio, profesionServicio,
			 * actividadEconomicaServicio, personaJuridicaServicio, personaServicio,
			 * direccionSdServicio, telefonoSdServicio);
			 * 
			 * 
			 * PersonaSd personaSd = dbh.getPersonaNatural();
			 * 
			 * // 4. Se debe crear las relaciones con conyuge, padre, madre
			 * crearRelacionesPersonales(abc, personaSd, usuario);
			 * 
			 * // 5. Obtiene o Crea Personas juridicas List<PersonaSd> listaPj =
			 * dbh.getPersonasJuridicas();
			 * 
			 * // 6. Crea relaciones laborales en el caso de existir personas // juridicas
			 * if (listaPj != null && !listaPj.isEmpty()) { try {
			 * relacionarPersonaNaturalPersonaJuridica(personaSd, listaPj, registros,
			 * usuario); } catch (ParseException e) { log.error(e.getMessage(),
			 * e.getCause()); } }
			 * 
			 * System.out.
			 * println("===========================FIN transforma DB a SD consulta en DB: "
			 * +identificacion + new Date());
			 * 
			 * // Se pone esta linea para que se pueda presentar la informacion en xml soap
			 * de respuesta activarPresentacionParaWs(personaSd);
			 */
			return retornar;
		} catch (DatabookException e) {
			log.error(e.getMessage(), e.getCause());
			throw new SmartdataException(e.getMessage(), e.getCause());
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e.getCause());
			throw new SmartdataException(e.getMessage(), e.getCause());
		} catch (IOException e) {
			log.error(e.getMessage(), e.getCause());
			throw new SmartdataException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Activa datos para presentacion en WS.
	 * 
	 * @param personaSd
	 */
	private void activarPresentacionParaWs(PersonaSd personaSd) {
		personaSd.getCodTipoIdentificacion().getCodTipoIdentificacionStr();
		personaSd.getPersonaNatural().getSexoStr();
		if (personaSd.getRelaciones() != null && !personaSd.getRelaciones().isEmpty()) {
			for (RelacionSd r : personaSd.getRelaciones()) {
				r.getPersonaRelacion();
			}
		}
		if (personaSd.getRelacionesSinBase() != null && !personaSd.getRelacionesSinBase().isEmpty()) {
			for (RelacionSd r : personaSd.getRelacionesSinBase()) {
				r.getPersonaRelacion();
			}
		}
		if (personaSd.getTodosTelefonos() != null && !personaSd.getTodosTelefonos().isEmpty()) {
			for (TelefonoSd t : personaSd.getTodosTelefonos()) {
				t.getSecTelefono();
			}
		}

		if (personaSd.getConyuge() != null) {
			personaSd.getConyuge().getPersonaRelacion();
		}
	}

	/**
	 * Se crean las relaciones para, Conyuge, Padre, Madre, si los hay. Se consulta
	 * la persona natural en la base si existe, se crea la relacion.
	 * 
	 */
	private void crearRelacionesPersonales(Registros registros, PersonaSd personaSd, String usuario) {
		// Cuando existen datos de conyuge
		if (registros.getConyugecedula() != null && registros.getConyugecedula().getConyugecedula() != null
				&& registros.getConyugecedula().getConyugecedula().trim().length() > 0) {
			creaParentesco(personaSd, registros.getConyugecedula().getConyugecedula().trim(),
					TipoParentescoEnum.CONYUGE, usuario, registros.getConyuge().getConyugenombre());
		}

		// Cuando existe padre
		if (registros.getCedulaspadres() != null && registros.getCedulaspadres().getTipo1() != null
				&& registros.getCedulaspadres().getTipo1().trim().length() > 0) {
			creaParentesco(personaSd, registros.getCedulaspadres().getTipo1().trim(), TipoParentescoEnum.PADRE, usuario,
					registros.getPadres().getNombrepadre());
		}

		// Cuando existe madre
		if (registros.getCedulaspadres() != null && registros.getCedulaspadres().getTipo2() != null
				&& registros.getCedulaspadres().getTipo2().trim().length() > 0) {
			creaParentesco(personaSd, registros.getCedulaspadres().getTipo2().trim(), TipoParentescoEnum.MADRE, usuario,
					registros.getPadres().getNombremadre());
		}
	}

	/**
	 * Realiza el proceso de verificacion de parentesco, si existe la persona en la
	 * base, se verifica si ya existe el prentesco, caso contrario se lo crea
	 * 
	 * @param personaP
	 * @param identificacionPersonaR
	 * @param tipoParentesco
	 * @param usuario
	 */
	private void creaParentesco(PersonaSd personaP, String identificacionPersonaR, TipoParentescoEnum tipoParentesco,
			String usuario, String denominacionPersonaR) {
		// Se obtiene el conyuge
		PersonaSd personaR = personaServicio.obtenerPersonaByIdentificacion(identificacionPersonaR);

		boolean relacionado = false;

		// si el conyuge existe en la base se crea la relacion
		if (personaR != null) {

			// Se busca si el parentesco ya existe antes, si no existe se lo
			// crea
			if (!relacionServicio.existeParentesco(personaP.getSecPersona(), personaR.getSecPersona(),
					tipoParentesco.getCodigoTipoParentesco())) {

				RelacionSd r = new RelacionSd();
				r.setExistePersonaR(true);
				r.setPersonaP(personaP);
				r.setPersonaR(personaR);
				r.setSecCanal(DataBookHelper.getCanal());
				r.setTipoParentesco(new TipoParentescoRelacionSd(tipoParentesco.getCodigoTipoParentesco()));
				r.setUsrCreacion(usuario);

				if (personaP.getRelaciones() == null) {
					personaP.setRelaciones(new ArrayList<RelacionSd>());
				}

				// Se pone la relacion en el listado del objeto persona
				personaP.getRelaciones().add(r);
				// Se crea el parentezco
				// relacionServicio.create(r);

				relacionado = true;
			}
		}

		// Cuando el conyuge no existe en la BDD pero para mostrar en la
		// pantalla
		if (TipoParentescoEnum.CONYUGE.equals(tipoParentesco) && !relacionado) {

			personaR = new PersonaSd();
			personaR.setIdentificacion(identificacionPersonaR);
			personaR.setDenominacion(denominacionPersonaR);

			RelacionSd r = new RelacionSd();
			r.setExistePersonaR(false);
			r.setPersonaP(personaP);
			r.setPersonaR(personaR);
			r.setSecCanal(DataBookHelper.getCanal());
			r.setTipoParentesco(new TipoParentescoRelacionSd(tipoParentesco.getCodigoTipoParentesco()));
			r.setUsrCreacion(usuario);

			// Se pone la relacion en el listado del objeto persona
			if (personaP.getRelacionesSinBase() == null) {
				personaP.setRelacionesSinBase(new ArrayList<RelacionSd>());
			}

			personaP.getRelacionesSinBase().add(r);
		}
	}

	/**
	 * Relaciona la persona natural con las personas juridicas. si la persona
	 * juridica ya existe en la base, se la consulta, caso contrario se la crea.
	 * 
	 * @param pn
	 * @param personaJuridicaList
	 * @throws ParseException
	 */
	private void relacionarPersonaNaturalPersonaJuridica(PersonaSd pn, List<PersonaSd> personaJuridicaList,
			Registros registros, String usuario) throws ParseException {
		Integer pnSec = pn.getPersonaNatural().getSecPersonaNatural();

		// if (pnSec != null) {
		for (PersonaSd pj : personaJuridicaList) {

			Integer pjSec = pj.getPersonaJuridica().getSecPersonaJuridica();
			boolean existeRelacion = false;

			if (pjSec != null && pnSec != null) {
				// 1. Se busca si ya existe la relacion
				existeRelacion = empleoDependienteServicio.existeRelacion(pnSec, pjSec);
			}

			if (!existeRelacion) {
				// 2. cuando no existe, se crea la relacion y se
				// persiste
				crearUnaRelacion(pn.getPersonaNatural(), pj.getPersonaJuridica(), registros, usuario);
			}
		}
		// }
	}

	/**
	 * Se crea y persiste la realcion entre persona natural y persona juridica.
	 * 
	 * @param pn
	 * @param pj
	 * @throws ParseException
	 */
	private void crearUnaRelacion(PersonaNaturalSd pn, PersonaJuridicaSd pj, Registros registros, String usuario)
			throws ParseException {
		DataBookHelper dbh = new DataBookHelper(registros, usuario, noPkTablasDao, estadoCivilServicio,
				profesionServicio, actividadEconomicaServicio, personaJuridicaServicio, personaServicio,
				direccionSdServicio, telefonoSdServicio);

		EmpleoDependienteSd ed = new EmpleoDependienteSd();
		ed.setEstado('A');
		ed.setPersonaJuridica(pj);
		ed.setPersonaNatural(pn);
		ed.setSecCanal(DataBookHelper.getCanal());
		ed.setUsrCreacion(usuario);
		ed.setUsrModificacion(usuario);

		dbh.asignarDatosRelacion(pj.getIdentificacion(), ed);

		if (ed.getFchIngreso() == null) {
			System.out.println("No tiene empleo fecha de ingreso... pasa al siguiente empleo");
			return;
		}

		if (pn.getEmpleoDependienteList() == null) {
			pn.setEmpleoDependienteList(new ArrayList<EmpleoDependienteSd>());
		}

		// Se enlaza los datos del empleo dependiente a la lista de la persona
		// natural que se va a persistir
		pn.getEmpleoDependienteList().add(ed);

		// Se persiste la relacion
		// empleoDependienteServicio.create(ed);
	}

	/**
	 * Consulta informacion en WS DataBook
	 * 
	 * @param identificacion
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws DatabookException
	 */
	private Registros obtenerRegistrosWs(String identificacion)
			throws FileNotFoundException, IOException, DatabookException {

		// Se obtienen las propiedades
		Properties props = obtenerArchivoPropiedades();

		// Se construye el servicio DATABOOK
		DatabookService dbs = new DatabookServiceImpl(props.getProperty(PropiedadesKeyEnum.url.toString()),
				identificacion, props.getProperty(PropiedadesKeyEnum.usuario.toString()));

		// Se consulta en el WS de Databook
		Registros registros = dbs.consultaDatabook();

		return registros;
	}

	private RegistrosEntity obtenerRegistrosWsRegistrosEntity(String identificacion)
			throws FileNotFoundException, IOException, DatabookException {

		// Se obtienen las propiedades
		Properties props = obtenerArchivoPropiedades();

		// Se construye el servicio DATABOOK
		DatabookService dbs = new DatabookServiceImpl(props.getProperty(PropiedadesKeyEnum.url.toString()),
				identificacion, props.getProperty(PropiedadesKeyEnum.usuario.toString()));

		System.out.println("XX3-FIN");
		// Se consulta en el WS de Databook
		RegistrosEntity registros = dbs.consultaDatabookRegistrosEntity();// JAIRO

		return registros;
	}

	/**
	 * Obtiene el archivos de propiedades con los datos de acceso al WS.
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private Properties obtenerArchivoPropiedades() throws FileNotFoundException, IOException {
		Properties prop = new Properties();

		System.out.println("LLEGA");
		prop.load(new FileInputStream("../server/equivida/conf/databookActualizado.properties"));
		System.out.println("NO LLEGA");
		return prop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.SmartDataSdServicio#
	 * actualizaDatosPersonaNatural (com.equivida.smartdata.dto.DatosActualizaSdDto)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void actualizaDatosPersonaNatural(DatosActualizaSdDto datosActualiza) throws SmartdataException {
		// Se pone el canal
		datosActualiza.setSecCanal(DataBookHelper.getCanalClienteUnico().getSecCanal());

		// 1. FIXME: NO Se actualizan losa datos de Empleo dependiente no hay
		// sec de la persona juridica
		// 2. FIXME: NO Se actulaizan los datos de empleo independiente no hay
		// datos de fechas de inicio de actividades ni fecha de inscripcion del
		// ruc esto se guarda en la tabla informaicon adicional

		// 3. Se actualizan los datos de telefono atados a Persona
		telefonoSdServicio.actualizaTelefonos(datosActualiza.getSecPersonaActualiza(),
				datosActualiza.getTelefonoList());

		// 4. Se actulaizan los datos de direccion atados a Persona
		direccionSdServicio.actualizaDirecciones(datosActualiza.getSecPersonaActualiza(),
				datosActualiza.getDireccionList());

		// 5. Se actualiza los datos de conyuge
		if (datosActualiza.getIdentificacionConyuge() != null
				&& datosActualiza.getIdentificacionConyuge().trim().length() > 0) {
			// 5.1 Busca si existe conyuge en la BDD de SD
			PersonaSd conyuge = personaServicio
					.obtenerPersonaByIdentificacion(datosActualiza.getIdentificacionConyuge());

			if (conyuge != null) {
				// si existe, se puede crear la relacion
				boolean existeRelacion = relacionServicio.existeParentesco(datosActualiza.getSecPersonaActualiza(),
						conyuge.getSecPersona(), TipoParentescoEnum.CONYUGE.getCodigoTipoParentesco());

				// Si no existe la relación de tipo conyuge, se puede crear pero
				// antes se debe dasactivar todas las relaciones de conyuge que
				// tenga la persona
				if (!existeRelacion) {
					RelacionSd r = new RelacionSd();
					r.setExistePersonaR(true);
					r.setPersonaP(new PersonaSd(datosActualiza.getSecPersonaActualiza()));
					r.setPersonaR(conyuge);
					r.setSecCanal(DataBookHelper.getCanal());
					r.setTipoParentesco(
							new TipoParentescoRelacionSd(TipoParentescoEnum.CONYUGE.getCodigoTipoParentesco()));
					r.setUsrCreacion(datosActualiza.getUsrProcesa());
					r.setUsrModificacion(datosActualiza.getUsrProcesa());

					// Se crea la relacion
					relacionServicio.create(r);
				}
			}
		}

		// 6. Se actualizan los datos personales persona natural
		personaNaturalServicio.actualizaDatosPersonales(datosActualiza);

		// 7. Se actualizan los datos personales de persona
		personaServicio.actualizaDatosPersonales(datosActualiza);
	}

	public void GuardarInformacionConyugue(RegistrosEntity.Conyuge registro) {
		log.error("ENTRA CONYUGE");
		Titular conyuge = new Titular();
		conyuge.setPersona(registro.getPersona());
		conyuge.setPersonaNatural(registro.getPersonaNatural());
		conyuge.setDireccionElectronico(registro.getDireccionElectronico());
		conyuge.setDirecciones(registro.getDirecciones());
		conyuge.setInformacionAdicional(registro.getInformacionAdicional());
		conyuge.setTelefonos(registro.getTelefonos());
		conyuge.setEmpleos(registro.getEmpleos());

		log.error("LLEGA CONYUGE");
		GuardarInformacionPersona(conyuge);
	}

	public PersonaSd GuardarInformacionPersona(Titular registro) {
		PersonaSd objRetorno = new PersonaSd();

		// 1. Se consulta persona por identificacion
		PersonaSd existePersona = personaServicio
				.obtenerPersonaByIdentificacion(registro.getPersona().getIdentificacion());
		CanalSd canalSd = new CanalSd();
		canalSd.setSecCanal((short) 3);// ++++++PONER CONSTANTE++++++++++//

		TipoIdentificacionSd tipoIdentificacion = new TipoIdentificacionSd();
		tipoIdentificacion.setCodTipoIdentificacion(registro.getPersona().getCodTipoIdentificacion().charAt(0));
		if (tipoIdentificacion.getCodTipoIdentificacion() != null)
			if (tipoIdentificacion.getCodTipoIdentificacion() != 'C'
					&& tipoIdentificacion.getCodTipoIdentificacion() != 'R')
				tipoIdentificacion.setCodTipoIdentificacion('C');

		TipoIdentificacionSd tipoIdentificacionRuc = new TipoIdentificacionSd();
		tipoIdentificacionRuc.setCodTipoIdentificacion('R');
		// SOLO INGRESAR DATOS CUADNO NO EXISTA LA PERSONA
		if (existePersona == null) {

			// CREAR PERSONA
			PersonaSd persona = MapperPersona(registro.getPersona(), tipoIdentificacion);
			log.error("LLEGO PERSONA");
			personaServicio.IngresarPersona(persona);
			log.error(persona);
			log.error("GUARDO PERSONA");

			// >>
			objRetorno = persona;

			// CREA PERSONA NATURAL
			PersonaNaturalSd personaNatural = MapperPersonaNatural(registro.getPersonaNatural(), tipoIdentificacion,
					persona, canalSd, registro.getEmpleos().getEmpleoActual().getSecProfesion());

			log.error("PASA PERSONA NATURAL");
			personaNaturalServicio.insertarPersonaNatural(personaNatural);
			// >>
			objRetorno.setPersonaNatural(personaNatural);
			log.error("GUARDA PERSONA NATURAL");

			// CREA DIRECCION 1
			List<DireccionSd> direccionList = new ArrayList<DireccionSd>();
			log.error("LLEGA DIRECCION 1");
			if (!VerificarVacios(registro.getDirecciones().getDireccion1().getDireccion().trim())) {
				log.error("---->");
				DireccionSd direccionsd = MapeoDireccionSd(registro.getDirecciones().getDireccion1(), canalSd, persona);
				log.error("LLEGA DIRECCION 1");
				direccionSdServicio.ingresarDireccion(direccionsd);
				// >>
				
				direccionList.add(direccionsd);
				objRetorno.setDireccionList(direccionList);
				log.error("GUARDA DIRECCION 1");
			}

			log.error("LLEGA DIRECCION 2");
			if (!VerificarVacios(registro.getDirecciones().getDireccion2().getDireccion().trim())) {
				
				if(registro.getDirecciones().getDireccion1().getDireccion().trim().equalsIgnoreCase(registro.getDirecciones().getDireccion2().getDireccion().trim()) == false) 
				{
				log.error("---->");
				DireccionSd direccionsd = MapeoDireccionSd(registro.getDirecciones().getDireccion2(), canalSd, persona);
				log.error("LLEGA DIRECCION 2");
				direccionSdServicio.ingresarDireccion(direccionsd);
				// >>
				direccionList.add(direccionsd);
				objRetorno.setDireccionList(direccionList);
				log.error("GUARDA DIRECCION ");
				}
			}
			
			if(direccionList.size() == 0) {
				direccionList.add(null);
				objRetorno.setDireccionList(direccionList);
			}
			
			// CREA TELEFONOS
			log.error("LLEGA TELEFONOS");
			List<TelefonoSd> listaTelefonos = new ArrayList<TelefonoSd>();

			if (!VerificarVacios(registro.getTelefonos().getTelefono1().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono1().getNroTelefono())) {

				TelefonoSd telefono = MapperTelefono(registro.getTelefonos().getTelefono1(), null, null, null, null,
						null, canalSd, persona);
				log.error("LLEGA TELEFONO 1");
				telefonoSdServicio.ingresarTelefono(telefono);
				// >>
				listaTelefonos.add(telefono);
				log.error("GUARDA TELEFONO 1");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono2().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono2().getNroTelefono())) {

				TelefonoSd telefono2 = MapperTelefono(null, registro.getTelefonos().getTelefono2(), null, null, null,
						null, canalSd, persona);

				log.error("LLEGA TELEFONO 2");
				telefonoSdServicio.ingresarTelefono(telefono2);
				// >>
				listaTelefonos.add(telefono2);
				log.error("GUARDA TELEFONO 2");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono3().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono3().getNroTelefono())) {

				TelefonoSd telefono3 = MapperTelefono(null, null, registro.getTelefonos().getTelefono3(), null, null,
						null, canalSd, persona);

				log.error("LLEGA TELEFONO 3");
				telefonoSdServicio.ingresarTelefono(telefono3);
				// >>
				listaTelefonos.add(telefono3);
				log.error("GUARDA TELEFONO 3");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono4().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono4().getNroTelefono())) {

				TelefonoSd telefono4 = MapperTelefono(null, null, null, registro.getTelefonos().getTelefono4(), null,
						null, canalSd, persona);

				log.error("LLEGA TELEFONO 4");
				telefonoSdServicio.ingresarTelefono(telefono4);
				// >>
				listaTelefonos.add(telefono4);
				log.error("GUARDA TELEFONO 4");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono5().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono5().getNroTelefono())) {

				TelefonoSd telefono5 = MapperTelefono(null, null, null, null, registro.getTelefonos().getTelefono5(),
						null, canalSd, persona);

				log.error("LLEGA TELEFONO 5");
				telefonoSdServicio.ingresarTelefono(telefono5);
				// >>
				listaTelefonos.add(telefono5);
				log.error("GUARDA TELEFONO 5");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono6().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono6().getNroTelefono())) {

				TelefonoSd telefono6 = MapperTelefono(null, null, null, null, null,
						registro.getTelefonos().getTelefono6(), canalSd, persona);

				log.error("LLEGA TELEFONO 6");
				telefonoSdServicio.ingresarTelefono(telefono6);
				// >>
				listaTelefonos.add(telefono6);
				log.error("GUARDA TELEFONO 6");
			}

			// >>
			objRetorno.setTelefonoList(listaTelefonos);

			// CREA DIRECCION ELECTRONICA 1
			log.error("DIRECCION ELECTRONICA");
			TipoDireccionElectronicaSd tipoDireccionElectronicaSd = new TipoDireccionElectronicaSd();
			tipoDireccionElectronicaSd.setCodTipoDireccionElectronica((short) 1);

			DireccionElectronicaSd direccionElectronica1 = new DireccionElectronicaSd();
			if (!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico1().trim())) {
				direccionElectronica1 = MapeoDireccionElectronica(
						registro.getDireccionElectronico().getCorreo_electronico1().trim(), persona, canalSd);

				log.error("LLEGA DIRECCION ELECTRONICA 1");
				direccionElectronicaSdServicio.ingresarDireccionElectronica(direccionElectronica1);
				log.error("GUARDA DIRECCION ELECTRONICA 1");
			}

			// CREA DIRECCION ELECTRONICA 2
			DireccionElectronicaSd direccionElectronica2 = new DireccionElectronicaSd();
			if (!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico2().trim())) {
				if (registro.getDireccionElectronico().getCorreo_electronico1()
						.equalsIgnoreCase(registro.getDireccionElectronico().getCorreo_electronico2()) == false) {

					direccionElectronica2 = MapeoDireccionElectronica(
							registro.getDireccionElectronico().getCorreo_electronico2().trim(), persona, canalSd);

					log.error("LLEGA DIRECCION ELECTRONICA 2");
					direccionElectronicaSdServicio.ingresarDireccionElectronica(direccionElectronica2);
					log.error("GUARDA DIRECCION ELECTRONICA 2");
				}
			}
			
			// CREA DIRECCION ELECTRONICA 3
			DireccionElectronicaSd direccionElectronica3 = new DireccionElectronicaSd();
			if (!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico3().trim())) {
				
				if (registro.getDireccionElectronico().getCorreo_electronico1()
						.equalsIgnoreCase(registro.getDireccionElectronico().getCorreo_electronico2()) == false
						&& registro.getDireccionElectronico().getCorreo_electronico1()
						.equalsIgnoreCase(registro.getDireccionElectronico().getCorreo_electronico3()) == false
						&& registro.getDireccionElectronico().getCorreo_electronico2()
						.equalsIgnoreCase(registro.getDireccionElectronico().getCorreo_electronico3()) == false) {

					direccionElectronica3 = MapeoDireccionElectronica(
							registro.getDireccionElectronico().getCorreo_electronico3().trim(), persona, canalSd);

					log.error("LLEGA DIRECCION ELECTRONICA 3");
					direccionElectronicaSdServicio.ingresarDireccionElectronica(direccionElectronica3);
					log.error("GUARDA DIRECCION ELECTRONICA 3");
				}
			}

			List<DireccionElectronicaSd> listaDireccionElectronica = new ArrayList<DireccionElectronicaSd>();
			if (direccionElectronica1.getSecDireccionElectronica() != null)
				listaDireccionElectronica.add(direccionElectronica1);
			else
				listaDireccionElectronica.add(null);

			if (direccionElectronica2.getSecDireccionElectronica() != null)
				listaDireccionElectronica.add(direccionElectronica2);

			objRetorno.setDireccionElectronicaSinBase(listaDireccionElectronica);

			// CREA INFORMACION ADCIONAL
			log.error("LLEGA INFO ADICIONAL");
			if (personaNatural != null && personaNatural.getSecPersonaNatural() != null // PN
					&& !VerificarVacios(registro.getInformacionAdicional().getIdentificacion().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getCodTipoIdentificacion().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getRazonSocial().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getFechaInscripcion().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getFechaInicioActividades().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getPrincipal().trim())) {

				log.error("ENTRAAAA INFORMACION ADICIONAL");

				InformacionAdicionalSd existeInformacionAdicionalSd = informacionAdicionalSdServicio
						.obtenerInformacionAdicionalBySecIdentificacion(
								registro.getInformacionAdicional().getIdentificacion());

				Integer secPesonaNatural = null;
				if (existeInformacionAdicionalSd != null)
					secPesonaNatural = existeInformacionAdicionalSd.getSecPersonaNatural().getSecPersonaNatural();

				InformacionAdicionalSd informacionAdicionalSd = MapperInformacionAdicional(
						registro.getInformacionAdicional(), personaNatural, canalSd);

				if (secPesonaNatural == informacionAdicionalSd.getSecPersonaNatural().getSecPersonaNatural()
						|| secPesonaNatural == null) {
					log.error("LLEGA INFORMACION ADICIONAL");
					informacionAdicionalSdServicio.crearInformacionAdicional(informacionAdicionalSd);
					log.error("GUARDA INFORMACION ADICIONAL");

					List<InformacionAdicionalSd> informacionAdicionalList = new ArrayList<InformacionAdicionalSd>();
					informacionAdicionalList.add(informacionAdicionalSd);
					personaNatural.setInformacionAdicionalList(informacionAdicionalList);
					objRetorno.setPersonaNatural(personaNatural);
				} else {
					log.error(secPesonaNatural + "<---->"
							+ informacionAdicionalSd.getSecPersonaNatural().getSecPersonaNatural());
					log.error(
							"Ya existe el registro en la persona natural no se puede duplicar la informacion adicional"
									+ informacionAdicionalSd.getSecPersonaNatural().getSecPersonaNatural());

					personaNatural.setInformacionAdicionalList(null);
					objRetorno.setPersonaNatural(personaNatural);
				}
			} else {
				List<InformacionAdicionalSd> informacionAdicionalList = new ArrayList<InformacionAdicionalSd>();
				informacionAdicionalList.add(null);
				personaNatural.setInformacionAdicionalList(informacionAdicionalList);
				objRetorno.setPersonaNatural(personaNatural);
			}

			// VERIFICAR DATOS PERSONA JURIDICA EMPLEO ACTUAL
			PersonaJuridicaSd existePersonaJuridicaActual = CrearPersonaJuridicaEmpleo(
					registro.getEmpleos().getEmpleoActual(), tipoIdentificacionRuc, canalSd,
					registro.getInformacionAdicional().getCodActividadEconomica());

			EmpleoDependienteSd empleoActual = EmpleoDependiente(existePersonaJuridicaActual, personaNatural,
					registro.getEmpleos().getEmpleoActual(), canalSd);

			// VERIFICAR DATOS PERSONA JURIDICA EMPLEO 1
			PersonaJuridicaSd existePersonaJuridica1 = CrearPersonaJuridicaEmpleo(registro.getEmpleos().getEmpleo1(),
					tipoIdentificacionRuc, canalSd, registro.getInformacionAdicional().getCodActividadEconomica());

			EmpleoDependienteSd empleo1 = EmpleoDependiente(existePersonaJuridica1, personaNatural,
					registro.getEmpleos().getEmpleo1(), canalSd);

			// VERIFICAR DATOS PERSONA JURIDICA EMPLEO 2
			PersonaJuridicaSd existePersonaJuridica2 = CrearPersonaJuridicaEmpleo(registro.getEmpleos().getEmpleo2(),
					tipoIdentificacionRuc, canalSd, registro.getInformacionAdicional().getCodActividadEconomica());

			EmpleoDependienteSd empleo2 = EmpleoDependiente(existePersonaJuridica2, personaNatural,
					registro.getEmpleos().getEmpleo2(), canalSd);

			List<EmpleoDependienteSd> empleoDependienteList = new ArrayList<EmpleoDependienteSd>();
			if (empleoActual.getSecEmpleoDependiente() != null)
				empleoDependienteList.add(empleoActual);
			else
				empleoDependienteList.add(null);

			if (empleo1.getSecEmpleoDependiente() != null)
				empleoDependienteList.add(empleo1);

			if (empleo2.getSecEmpleoDependiente() != null)
				empleoDependienteList.add(empleo2);

			PersonaNaturalSd perN = new PersonaNaturalSd();
			perN = persona.getPersonaNatural();
			perN.setEmpleoDependienteList(empleoDependienteList);
			objRetorno.setPersonaNatural(perN);

			log.error("FIN PROCESO CREAR TITULAR");
		} else {
			// ZONA DE ACTUALIZACION
			objRetorno = existePersona;

			log.error("ACTUALIZACION------------------------------------");
			log.error(existePersona.getPersonaNatural());

			// VERIFICO SI EXISTE LA PERSONA NATURAL
			if (existePersona.getPersonaNatural() == null) {
				// CREA PERSONA NATURAL
				PersonaNaturalSd personaNatural = MapperPersonaNatural(registro.getPersonaNatural(), tipoIdentificacion,
						existePersona, canalSd, registro.getEmpleos().getEmpleoActual().getSecProfesion());

				log.error("PASA PERSONA NATURAL");
				personaNaturalServicio.insertarPersonaNatural(personaNatural);
				// >>
				objRetorno.setPersonaNatural(personaNatural);
				existePersona.setPersonaNatural(personaNatural);
				log.error("GUARDA PERSONA NATURAL");
			}

			// ACTUALIZACION DIRECCION
			List<DireccionSd> direccionesConsultadasList = direccionSdServicio
					.obtenerDireccionByPersonaSecPersona(existePersona.getSecPersona());

			//SE CONTROLA EN CASO DE QUE EL DIRECCION 1 ESTE VACIO Y EL DIRECCION 2 ESTE LLENO
			if (VerificarVacios(registro.getDirecciones().getDireccion1().getDireccion().trim())
					&& !VerificarVacios(registro.getDirecciones().getDireccion2().getDireccion().trim())) {
				registro.getDirecciones().setDireccion1(registro.getDirecciones().getDireccion2());
			}
			
			if(!VerificarVacios(registro.getDirecciones().getDireccion1().getDireccion().trim())) 
			{
				int secDirecion = direccionesConsultadasList.get(0).getSecDireccion();
				
				direccionsd = MapeoDireccionSd(registro.getDirecciones().getDireccion1(), canalSd, existePersona);
				direccionsd.setSecDireccion(secDirecion);
				
				if(direccionesConsultadasList != null && direccionesConsultadasList.size() >= 1) {
					

					
					log.error(direccionsd);
					if (direccionsd.getSecDireccion() != null) {
						log.error("ACTUALIZA DIRECCION");
						direccionSdServicio.update(direccionsd);
					
				}				
			}
			
			}
			
			Integer secDirecion = null;
			if (direccionsd != null) {
				secDirecion = direccionsd.getSecDireccion();
				log.error(direccionsd.getSecDireccion());
			}

			log.error("LLEGA DIRECCION 1");
			if (!VerificarVacios(registro.getDirecciones().getDireccion1().getDireccion().trim())) {
				direccionsd = MapeoDireccionSd(registro.getDirecciones().getDireccion1(), canalSd, existePersona);
				direccionsd.setSecDireccion(secDirecion);

				
				log.error(direccionsd);
				if (direccionsd.getSecDireccion() != null) {
					log.error("ACTUALIZA DIRECCION");
					direccionSdServicio.update(direccionsd);
				} else {
					log.error("INGRESA DIRECCION");
					direccionSdServicio.ingresarDireccion(direccionsd);
				}

				// >>
				List<DireccionSd> listaDirec = new ArrayList<DireccionSd>();
				listaDirec.add(direccionsd);
				objRetorno.setDireccionList(listaDirec);
			}

			// ACTUALIZACION TELEFONOS
			// >>
			List<TelefonoSd> retornarListaTelefono = new ArrayList<TelefonoSd>();

			List<TelefonoSd> listaTelefonos = telefonoSdServicio
					.obtenerTelefonoByPersonaSecPersona(existePersona.getSecPersona());

			if (!VerificarVacios(registro.getTelefonos().getTelefono1().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 1) {
					TelefonoSd telefono1 = telefonoSdServicio.findByPk(listaTelefonos.get(0).getSecTelefono());

					TipoTelefonoSd existeTipoTelefono = null;
					try {
						if (!VerificarVacios(registro.getTelefonos().getTelefono1().getCodTipoTelefono().trim()))
							existeTipoTelefono = tipoTelefonoSdServicio.findByPk(Short
									.parseShort(registro.getTelefonos().getTelefono1().getCodTipoTelefono().trim()));
					} catch (Exception e) {
						log.error("El campo tipo telefono no es un tipo de dato short --->"
								+ registro.getTelefonos().getTelefono1().getCodTipoTelefono().trim());
					}

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					if (existeTipoTelefono != null)
						tipoTelefonoSd = existeTipoTelefono;
					else
						tipoTelefonoSd.setCodTipoTelefono((short) 0);
					telefono1.setSecPersona(existePersona);
					telefono1.setSecCanal(canalSd);
					telefono1.setEstado(EstadoEnum.A.getEstadoChar());
					telefono1.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono1.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono1.setTsCreacion(new Date());
					telefono1.setCodArea(listaTelefonos.get(0).getCodArea());
					telefono1.setNroTelefono(listaTelefonos.get(0).getNroTelefono());
					telefono1.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO1");
					log.error(telefono1);
					telefonoSdServicio.update(telefono1);
					// >>
					retornarListaTelefono.add(telefono1);
				} else {
					TelefonoSd telefono1 = MapperTelefono(registro.getTelefonos().getTelefono1(), null, null, null,
							null, null, canalSd, existePersona);

					log.error("INGRESA TELEFONO1");
					log.error(telefono1);
					telefonoSdServicio.ingresarTelefono(telefono1);
					// >>
					retornarListaTelefono.add(telefono1);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono2().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 2) {
					TelefonoSd telefono2 = telefonoSdServicio.findByPk(listaTelefonos.get(1).getSecTelefono());

					// CONTROLO QUE EL CODIGO DEL TELEFONO SEA VALIDO
					TipoTelefonoSd existeTipoTelefono = null;
					try {
						if (!VerificarVacios(registro.getTelefonos().getTelefono2().getCodTipoTelefono().trim()))
							existeTipoTelefono = tipoTelefonoSdServicio.findByPk(Short
									.parseShort(registro.getTelefonos().getTelefono2().getCodTipoTelefono().trim()));
					} catch (Exception e) {
						log.error("El campo tipo telefono no es un tipo de dato short --->"
								+ registro.getTelefonos().getTelefono2().getCodTipoTelefono().trim());
					}

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					if (existeTipoTelefono != null)
						tipoTelefonoSd = existeTipoTelefono;
					else
						tipoTelefonoSd.setCodTipoTelefono((short) 0);

					telefono2.setSecPersona(existePersona);
					telefono2.setSecCanal(canalSd);
					telefono2.setEstado(EstadoEnum.A.getEstadoChar());
					telefono2.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono2.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono2.setTsCreacion(new Date());
					telefono2.setCodArea(listaTelefonos.get(1).getCodArea());
					telefono2.setNroTelefono(listaTelefonos.get(1).getNroTelefono());
					telefono2.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO2");
					log.error(telefono2);
					telefonoSdServicio.update(telefono2);
					// >>
					retornarListaTelefono.add(telefono2);
				} else {
					TelefonoSd telefono2 = MapperTelefono(null, registro.getTelefonos().getTelefono2(), null, null,
							null, null, canalSd, existePersona);

					log.error("INGRESA TELEFONO2");
					log.error(telefono2);
					telefonoSdServicio.ingresarTelefono(telefono2);
					// >>
					retornarListaTelefono.add(telefono2);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono3().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 3) {
					TelefonoSd telefono3 = telefonoSdServicio.findByPk(listaTelefonos.get(2).getSecTelefono());

					// CONTROLO QUE EL CODIGO DEL TELEFONO SEA VALIDO
					TipoTelefonoSd existeTipoTelefono = null;
					try {
						if (!VerificarVacios(registro.getTelefonos().getTelefono3().getCodTipoTelefono().trim()))
							existeTipoTelefono = tipoTelefonoSdServicio.findByPk(Short
									.parseShort(registro.getTelefonos().getTelefono3().getCodTipoTelefono().trim()));
					} catch (Exception e) {
						log.error("El campo tipo telefono no es un tipo de dato short --->"
								+ registro.getTelefonos().getTelefono3().getCodTipoTelefono().trim());
					}

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					if (existeTipoTelefono != null)
						tipoTelefonoSd = existeTipoTelefono;
					else
						tipoTelefonoSd.setCodTipoTelefono((short) 0);

					telefono3.setSecPersona(existePersona);
					telefono3.setSecCanal(canalSd);
					telefono3.setEstado(EstadoEnum.A.getEstadoChar());
					telefono3.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono3.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono3.setTsCreacion(new Date());
					telefono3.setCodArea(listaTelefonos.get(2).getCodArea());
					telefono3.setNroTelefono(listaTelefonos.get(2).getNroTelefono());
					telefono3.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO3");
					log.error(telefono3);
					telefonoSdServicio.update(telefono3);
					// >>
					retornarListaTelefono.add(telefono3);
				} else {
					TelefonoSd telefono3 = MapperTelefono(null, null, registro.getTelefonos().getTelefono3(), null,
							null, null, canalSd, existePersona);

					log.error("INGRESA TELEFONO3");
					log.error(telefono3);
					telefonoSdServicio.ingresarTelefono(telefono3);
					// >>
					retornarListaTelefono.add(telefono3);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono4().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 4) {
					TelefonoSd telefono4 = telefonoSdServicio.findByPk(listaTelefonos.get(3).getSecTelefono());

					// CONTROLO QUE EL CODIGO DEL TELEFONO SEA VALIDO
					TipoTelefonoSd existeTipoTelefono = null;
					try {
						if (!VerificarVacios(registro.getTelefonos().getTelefono4().getCodTipoTelefono().trim()))
							existeTipoTelefono = tipoTelefonoSdServicio.findByPk(Short
									.parseShort(registro.getTelefonos().getTelefono4().getCodTipoTelefono().trim()));
					} catch (Exception e) {
						log.error("El campo tipo telefono no es un tipo de dato short --->"
								+ registro.getTelefonos().getTelefono4().getCodTipoTelefono().trim());
					}

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					if (existeTipoTelefono != null)
						tipoTelefonoSd = existeTipoTelefono;
					else
						tipoTelefonoSd.setCodTipoTelefono((short) 0);

					telefono4.setSecPersona(existePersona);
					telefono4.setSecCanal(canalSd);
					telefono4.setEstado(EstadoEnum.A.getEstadoChar());
					telefono4.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono4.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono4.setTsCreacion(new Date());
					telefono4.setCodArea(listaTelefonos.get(3).getCodArea());
					telefono4.setNroTelefono(listaTelefonos.get(3).getNroTelefono());
					telefono4.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO4");
					log.error(telefono4);
					telefonoSdServicio.update(telefono4);
					// >>
					retornarListaTelefono.add(telefono4);
				} else {
					TelefonoSd telefono4 = MapperTelefono(null, null, null, registro.getTelefonos().getTelefono4(),
							null, null, canalSd, existePersona);

					log.error("INGRESA TELEFONO4");
					log.error(telefono4);
					telefonoSdServicio.ingresarTelefono(telefono4);
					// >>
					retornarListaTelefono.add(telefono4);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono5().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 5) {
					TelefonoSd telefono5 = telefonoSdServicio.findByPk(listaTelefonos.get(4).getSecTelefono());

					// CONTROLO QUE EL CODIGO DEL TELEFONO SEA VALIDO
					TipoTelefonoSd existeTipoTelefono = null;
					try {
						if (!VerificarVacios(registro.getTelefonos().getTelefono5().getCodTipoTelefono().trim()))
							existeTipoTelefono = tipoTelefonoSdServicio.findByPk(Short
									.parseShort(registro.getTelefonos().getTelefono5().getCodTipoTelefono().trim()));
					} catch (Exception e) {
						log.error("El campo tipo telefono no es un tipo de dato short --->"
								+ registro.getTelefonos().getTelefono5().getCodTipoTelefono().trim());
					}

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					if (existeTipoTelefono != null)
						tipoTelefonoSd = existeTipoTelefono;
					else
						tipoTelefonoSd.setCodTipoTelefono((short) 0);

					telefono5.setSecPersona(existePersona);
					telefono5.setSecCanal(canalSd);
					telefono5.setEstado(EstadoEnum.A.getEstadoChar());
					telefono5.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono5.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono5.setTsCreacion(new Date());
					telefono5.setCodArea(listaTelefonos.get(4).getCodArea());
					telefono5.setNroTelefono(listaTelefonos.get(4).getNroTelefono());
					telefono5.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO5");
					log.error(telefono5);
					telefonoSdServicio.update(telefono5);
					// >>
					retornarListaTelefono.add(telefono5);
				} else {
					TelefonoSd telefono5 = MapperTelefono(null, null, null, null,
							registro.getTelefonos().getTelefono5(), null, canalSd, existePersona);

					log.error("INGRESA TELEFONO5");
					log.error(telefono5);
					telefonoSdServicio.ingresarTelefono(telefono5);
					// >>
					retornarListaTelefono.add(telefono5);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono6().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 6) {
					TelefonoSd telefono6 = telefonoSdServicio.findByPk(listaTelefonos.get(5).getSecTelefono());

					// CONTROLO QUE EL CODIGO DEL TELEFONO SEA VALIDO
					TipoTelefonoSd existeTipoTelefono = null;
					try {
						if (!VerificarVacios(registro.getTelefonos().getTelefono6().getCodTipoTelefono().trim()))
							existeTipoTelefono = tipoTelefonoSdServicio.findByPk(Short
									.parseShort(registro.getTelefonos().getTelefono6().getCodTipoTelefono().trim()));
					} catch (Exception e) {
						log.error("El campo tipo telefono no es un tipo de dato short --->"
								+ registro.getTelefonos().getTelefono6().getCodTipoTelefono().trim());
					}

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					if (existeTipoTelefono != null)
						tipoTelefonoSd = existeTipoTelefono;
					else
						tipoTelefonoSd.setCodTipoTelefono((short) 0);

					telefono6.setSecPersona(existePersona);
					telefono6.setSecCanal(canalSd);
					telefono6.setEstado(EstadoEnum.A.getEstadoChar());
					telefono6.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono6.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono6.setTsCreacion(new Date());
					telefono6.setCodArea(listaTelefonos.get(5).getCodArea());
					telefono6.setNroTelefono(listaTelefonos.get(5).getNroTelefono());
					telefono6.setCodTipoTelefono(tipoTelefonoSd);
					log.error("ACTUALIZA TELEFONO6");
					log.error(telefono6);
					telefonoSdServicio.update(telefono6);
					// >>
					retornarListaTelefono.add(telefono6);
				} else {
					TelefonoSd telefono6 = MapperTelefono(null, null, null, null, null,
							registro.getTelefonos().getTelefono6(), canalSd, existePersona);

					log.error("INGRESA TELEFONO6");
					log.error(telefono6);
					telefonoSdServicio.ingresarTelefono(telefono6);
					// >>
					retornarListaTelefono.add(telefono6);
				}
			}

			objRetorno.setTelefonoList(retornarListaTelefono);
			log.error("PASA TELEFONO");

			// ACTUALIZA DIRECCION ELECTRONICA 1-2
			List<DireccionElectronicaSd> direccionElectronica = direccionElectronicaSdServicio
					.obtenerDireccionElectronicaByPersonaSecPersona(existePersona.getSecPersona());

			TipoDireccionElectronicaSd tipoDireccionElectronicaSd = new TipoDireccionElectronicaSd();
			tipoDireccionElectronicaSd.setCodTipoDireccionElectronica((short) 1);

			// SE CONTROLA EN CASO DE QUE EL CORREO 1 ESTE VACIO Y EL CORREO 2 ESTE LLENO
			if (VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico1().trim())
					&& !VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico2().trim()))
				registro.getDireccionElectronico()
						.setCorreo_electronico1(registro.getDireccionElectronico().getCorreo_electronico2());

			// CREA ACTUALIZA DIRECCION ELECTRONICA 1
			DireccionElectronicaSd d1 = new DireccionElectronicaSd();
			if (!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico1().trim())) {
				if (direccionElectronica != null && direccionElectronica.size() >= 1) {
					d1 = direccionElectronicaSdServicio
							.findByPk(direccionElectronica.get(0).getSecDireccionElectronica());
					d1.setSecPersona(existePersona);
					d1.setCodTipoDireccionElectronica(tipoDireccionElectronicaSd);
					d1.setSecCanal(canalSd);
					d1.setEstado(EstadoEnum.A.getEstadoChar());
					d1.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					d1.setTsCreacion(new Date());
					d1.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					d1.setDireccionElectronica(registro.getDireccionElectronico().getCorreo_electronico1());

					log.error("ACTUALIZA DIRECCION ELECTRONICA 1");
					log.error(d1);
					direccionElectronicaSdServicio.update(d1);
				} else {

					d1 = MapeoDireccionElectronica(registro.getDireccionElectronico().getCorreo_electronico1(),
							existePersona, canalSd);

					log.error("CREA DIRECCION ELECTRONICA 1");
					direccionElectronicaSdServicio.ingresarDireccionElectronica(d1);
					log.error("GUARDA DIRECCION ELECTRONICA 1");
				}
			}

			// CREA/ACTUALIZA DIRECCION ELECTRONICA 2
			DireccionElectronicaSd d2 = new DireccionElectronicaSd();
			if (!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico2().trim())) {

				if (registro.getDireccionElectronico().getCorreo_electronico1() != null
						&& registro.getDireccionElectronico().getCorreo_electronico1().equalsIgnoreCase(
								registro.getDireccionElectronico().getCorreo_electronico2()) == false) {

					if (direccionElectronica != null && direccionElectronica.size() >= 2) {

						d2 = direccionElectronicaSdServicio
								.findByPk(direccionElectronica.get(1).getSecDireccionElectronica());
						d2.setSecPersona(existePersona);
						d2.setCodTipoDireccionElectronica(tipoDireccionElectronicaSd);
						d2.setSecCanal(canalSd);
						d2.setEstado(EstadoEnum.A.getEstadoChar());
						d2.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
						d2.setTsCreacion(new Date());
						d2.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
						d2.setDireccionElectronica(registro.getDireccionElectronico().getCorreo_electronico2());

						log.error("ACTUALIZA DIRECCION ELECTRONICA 2");
						log.error(d2);
						direccionElectronicaSdServicio.update(d2);
					} else {
						d2 = MapeoDireccionElectronica(registro.getDireccionElectronico().getCorreo_electronico2(),
								existePersona, canalSd);

						log.error(d2);
						log.error(d2.getSecPersona());
						log.error(d2.getDireccionElectronica());
						log.error(d2.getCodTipoDireccionElectronica());
						log.error(d2.getSecCanal());
						log.error(d2.getEstado());
						log.error(d2.getUsrCreacion());
						log.error(d2.getTsCreacion());
						log.error(d2.getUsrModificacion());

						log.error("CREA DIRECCION ELECTRONICA 2");
						direccionElectronicaSdServicio.ingresarDireccionElectronica(d2);
						log.error("GUARDA DIRECCION ELECTRONICA 2");

					}
					log.error("PASA DIRECCION ELECTRONICA");
				}
			}

			List<DireccionElectronicaSd> listaDireccionElectronica = new ArrayList<DireccionElectronicaSd>();
			if (d1.getSecDireccionElectronica() != null)
				listaDireccionElectronica.add(d1);
			else
				listaDireccionElectronica.add(null);

			if (d2.getSecDireccionElectronica() != null)
				listaDireccionElectronica.add(d2);

			objRetorno.setDireccionElectronicaSinBase(listaDireccionElectronica);

			// ACTUALIZA INFORMACION ADCIONAL
			if (existePersona.getPersonaNatural() != null
					&& existePersona.getPersonaNatural().getSecPersonaNatural() != null
					&& !VerificarVacios(registro.getInformacionAdicional().getIdentificacion().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getCodTipoIdentificacion().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getRazonSocial().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getFechaInscripcion().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getFechaInicioActividades().trim())
					&& !VerificarVacios(registro.getInformacionAdicional().getPrincipal().trim())) {

				InformacionAdicionalSd informacionAdicionalSd = informacionAdicionalSdServicio
						.obtenerInformacionAdicionalBySecPersonaNatural(
								existePersona.getPersonaNatural().getSecPersonaNatural());

				InformacionAdicionalSd existeInformacionAdicionalSd = informacionAdicionalSdServicio
						.obtenerInformacionAdicionalBySecIdentificacion(
								registro.getInformacionAdicional().getIdentificacion());

				Integer secInformacionAcional = null;
				Integer secPesonaNatural = null;

				if (informacionAdicionalSd != null)
					secInformacionAcional = informacionAdicionalSd.getSecInformacionAdic();

				if (existeInformacionAdicionalSd != null)
					secPesonaNatural = existeInformacionAdicionalSd.getSecPersonaNatural().getSecPersonaNatural();

				informacionAdicionalSd = MapperInformacionAdicional(registro.getInformacionAdicional(),
						existePersona.getPersonaNatural(), canalSd);
				informacionAdicionalSd.setSecInformacionAdic(secInformacionAcional);

				if (secPesonaNatural == informacionAdicionalSd.getSecPersonaNatural().getSecPersonaNatural()
						|| secPesonaNatural == null) {
					if (informacionAdicionalSd.getSecInformacionAdic() != null) {
						log.error("ACTUALIZA");
						informacionAdicionalSdServicio.update(informacionAdicionalSd);
						log.error("ACTUALIZA INFORMACIONADICIONAL");
						log.error(informacionAdicionalSd);
					} else {
						log.error("INGRESA");
						informacionAdicionalSdServicio.crearInformacionAdicional(informacionAdicionalSd);
						log.error("INGRESA INFORMACIONADICIONAL");
						log.error(informacionAdicionalSd);
					}
				} else {
					log.error(secPesonaNatural + "<---->"
							+ informacionAdicionalSd.getSecPersonaNatural().getSecPersonaNatural());
					log.error("Ya existe el registro en la persona natural "
							+ informacionAdicionalSd.getSecPersonaNatural().getSecPersonaNatural());
				}

				// >>
				PersonaNaturalSd personaNatural = new PersonaNaturalSd();
				List<InformacionAdicionalSd> lista = new ArrayList<InformacionAdicionalSd>();
				lista.add(informacionAdicionalSd);
				personaNatural = existePersona.getPersonaNatural();
				personaNatural.setInformacionAdicionalList(lista);
				objRetorno.setPersonaNatural(personaNatural);

			} else {
				PersonaNaturalSd personaNatural = new PersonaNaturalSd();
				personaNatural = existePersona.getPersonaNatural();
				List<InformacionAdicionalSd> lista = new ArrayList<InformacionAdicionalSd>();
				InformacionAdicionalSd informacionAdicional = null;
				lista.add(informacionAdicional);
				personaNatural.setInformacionAdicionalList(lista);
				objRetorno.setPersonaNatural(personaNatural);
			}

			log.error("PASA INFORMACION ADICIONAL");

			log.error("LLEGA EMPLEOS");
			// ACTUALIZAR EMPLEO ACTUAL
			PersonaJuridicaSd existePersonaJuridica = CrearPersonaJuridicaEmpleo(
					registro.getEmpleos().getEmpleoActual(), tipoIdentificacionRuc, canalSd,
					registro.getInformacionAdicional().getCodActividadEconomica());

			EmpleoDependienteSd empleoActual = ActualizarEmpleoDependiente(existePersona.getPersonaNatural(),
					existePersonaJuridica, registro.getEmpleos().getEmpleoActual(), canalSd);

			// ACTUALIZAR EMPLEO 1
			PersonaJuridicaSd existePersonaJuridica1 = CrearPersonaJuridicaEmpleo(registro.getEmpleos().getEmpleo1(),
					tipoIdentificacionRuc, canalSd, registro.getInformacionAdicional().getCodActividadEconomica());

			EmpleoDependienteSd empleo1 = ActualizarEmpleoDependiente(existePersona.getPersonaNatural(),
					existePersonaJuridica1, registro.getEmpleos().getEmpleo1(), canalSd);

			// ACTUALIZAR EMPLEO 2
			PersonaJuridicaSd existePersonaJuridica2 = CrearPersonaJuridicaEmpleo(registro.getEmpleos().getEmpleo2(),
					tipoIdentificacionRuc, canalSd, registro.getInformacionAdicional().getCodActividadEconomica());

			EmpleoDependienteSd empleo2 = ActualizarEmpleoDependiente(existePersona.getPersonaNatural(),
					existePersonaJuridica2, registro.getEmpleos().getEmpleo2(), canalSd);

			log.error("MAPEO OBJETO");
			log.error(empleoActual);
			log.error(empleo1);
			log.error(empleo2);

			List<EmpleoDependienteSd> empleoDependienteList = new ArrayList<EmpleoDependienteSd>();
			if (empleoActual.getSecEmpleoDependiente() != null)
				empleoDependienteList.add(empleoActual);
			else
				empleoDependienteList.add(null);

			log.error("MAPEO OBJETO");

			if (empleo1.getSecEmpleoDependiente() != null)
				empleoDependienteList.add(empleo1);

			if (empleo2.getSecEmpleoDependiente() != null)
				empleoDependienteList.add(empleo2);

			PersonaNaturalSd perN = new PersonaNaturalSd();
			perN = existePersona.getPersonaNatural();
			perN.setEmpleoDependienteList(empleoDependienteList);
			objRetorno.setPersonaNatural(perN);

			log.error("FIN EMPLEOS");
			log.error("FIN PROCESO ACTUALIZACION TITULAR");
		}

		return objRetorno;
	}

	public boolean VerificarVacios(String valor) {
		if (valor.trim().isEmpty() || valor == null)
			return true;
		return false;
	}

	public Date ConvertirFecha(String valor) {

		String[] fechas = valor.split("/");
		String fechaTransformada = fechas[1] + "-" + fechas[0] + "-" + fechas[2];

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = formatter.parse(fechaTransformada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}

	public DireccionSd MapeoDireccionSd(Direccion registro, CanalSd canalSd, PersonaSd personaSd) {
		DireccionSd direccionsd = new DireccionSd();

		// CONTROLO Y VERIFICO SI EXISTE LA PROVINCIA
		ProvinciaSd provinciaSdDireccion = TraerProvinciaSd(registro.getSecProvincia());

		// CONTROLO Y VERIFICO SI EXISTE EL CANTON
		CantonSd cantoSdDireccion = TraerCantonSd(registro.getSecCanton());

		// CONTROLO Y VERIFICO SI EXISTE LA PARROQUIA
		ParroquiaSd parroquiaSdDireccion = TraerParroquiaSd(registro.getSecParroquia());

		TipoDireccionSd tipoDireccionSd = new TipoDireccionSd();
		tipoDireccionSd.setCodTipoDireccion(TipoDireccionEnum.DOMICILIO.getCodigoenBase());

		direccionsd.setSecPersona(personaSd);
		direccionsd.setDireccion(registro.getDireccion());
		direccionsd.setCodTipoDireccion(tipoDireccionSd);
		direccionsd.setSecProvincia(provinciaSdDireccion);
		direccionsd.setSecCanton(cantoSdDireccion);
		direccionsd.setSecParroquia(parroquiaSdDireccion);
		direccionsd.setSecCanal(canalSd);
		direccionsd.setEstado(EstadoEnum.A.getEstadoChar());
		direccionsd.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
		direccionsd.setTsCreacion(new Date());
		direccionsd.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

		return direccionsd;
	}

	public TelefonoSd MapperTelefono(Telefono1 telefono1, Telefono2 telefono2, Telefono3 telefono3, Telefono4 telefono4,
			Telefono5 telefono5, Telefono6 telefono6, CanalSd canalSd, PersonaSd persona) {
		TelefonoSd telefono = new TelefonoSd();
		TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();

		telefono.setSecPersona(persona);
		telefono.setSecCanal(canalSd);
		telefono.setEstado(EstadoEnum.A.getEstadoChar());
		telefono.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
		telefono.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
		telefono.setTsCreacion(new Date());

		if (telefono1 != null) {

			// CONTROLO Y VERIFICO SI EXISTE EL CODIGO DEL TELEFONO
			TipoTelefonoSd existeTipoTelefono = null;
			try {
				if (!VerificarVacios(telefono1.getCodTipoTelefono().trim()))
					existeTipoTelefono = tipoTelefonoSdServicio
							.findByPk(Short.parseShort(telefono1.getCodTipoTelefono().trim()));
			} catch (Exception e) {
				log.error("El campo tipo telefono no es un tipo de dato short --->"
						+ telefono1.getCodTipoTelefono().trim());
			}

			tipoTelefonoSd.setCodTipoTelefono(existeTipoTelefono != null ? existeTipoTelefono.getCodTipoTelefono() : 0);
			telefono.setCodArea(telefono1.getCodArea());
			telefono.setNroTelefono(telefono1.getNroTelefono());
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono2 != null) {
			// CONTROLO Y VERIFICO SI EXISTE EL CODIGO DEL TELEFONO
			TipoTelefonoSd existeTipoTelefono = null;
			try {
				if (!VerificarVacios(telefono2.getCodTipoTelefono().trim()))
					existeTipoTelefono = tipoTelefonoSdServicio
							.findByPk(Short.parseShort(telefono2.getCodTipoTelefono().trim()));
			} catch (Exception e) {
				log.error("El campo tipo telefono no es un tipo de dato short --->"
						+ telefono2.getCodTipoTelefono().trim());
			}

			tipoTelefonoSd.setCodTipoTelefono(existeTipoTelefono != null ? existeTipoTelefono.getCodTipoTelefono() : 0);
			telefono.setCodArea(telefono2.getCodArea());
			telefono.setNroTelefono(telefono2.getNroTelefono());
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono3 != null) {
			// CONTROLO Y VERIFICO SI EXISTE EL CODIGO DEL TELEFONO
			TipoTelefonoSd existeTipoTelefono = null;
			try {
				if (!VerificarVacios(telefono3.getCodTipoTelefono().trim()))
					existeTipoTelefono = tipoTelefonoSdServicio
							.findByPk(Short.parseShort(telefono3.getCodTipoTelefono().trim()));
			} catch (Exception e) {
				log.error("El campo tipo telefono no es un tipo de dato short --->"
						+ telefono3.getCodTipoTelefono().trim());
			}

			tipoTelefonoSd.setCodTipoTelefono(existeTipoTelefono != null ? existeTipoTelefono.getCodTipoTelefono() : 0);
			telefono.setCodArea(telefono3.getCodArea());
			telefono.setNroTelefono(telefono3.getNroTelefono());
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono4 != null) {
			// CONTROLO Y VERIFICO SI EXISTE EL CODIGO DEL TELEFONO
			TipoTelefonoSd existeTipoTelefono = null;
			try {
				if (!VerificarVacios(telefono4.getCodTipoTelefono().trim()))
					existeTipoTelefono = tipoTelefonoSdServicio
							.findByPk(Short.parseShort(telefono4.getCodTipoTelefono().trim()));
			} catch (Exception e) {
				log.error("El campo tipo telefono no es un tipo de dato short --->"
						+ telefono4.getCodTipoTelefono().trim());
			}

			tipoTelefonoSd.setCodTipoTelefono(existeTipoTelefono != null ? existeTipoTelefono.getCodTipoTelefono() : 0);
			telefono.setCodArea(telefono4.getCodArea());
			telefono.setNroTelefono(telefono4.getNroTelefono());
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono5 != null) {
			// CONTROLO Y VERIFICO SI EXISTE EL CODIGO DEL TELEFONO
			TipoTelefonoSd existeTipoTelefono = null;
			try {
				if (!VerificarVacios(telefono5.getCodTipoTelefono().trim()))
					existeTipoTelefono = tipoTelefonoSdServicio
							.findByPk(Short.parseShort(telefono5.getCodTipoTelefono().trim()));
			} catch (Exception e) {
				log.error("El campo tipo telefono no es un tipo de dato short --->"
						+ telefono5.getCodTipoTelefono().trim());
			}

			tipoTelefonoSd.setCodTipoTelefono(existeTipoTelefono != null ? existeTipoTelefono.getCodTipoTelefono() : 0);
			telefono.setCodArea(telefono5.getCodArea());
			telefono.setNroTelefono(telefono5.getNroTelefono());
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono6 != null) {
			// CONTROLO Y VERIFICO SI EXISTE EL CODIGO DEL TELEFONO
			TipoTelefonoSd existeTipoTelefono = null;
			try {
				if (!VerificarVacios(telefono6.getCodTipoTelefono().trim()))
					existeTipoTelefono = tipoTelefonoSdServicio
							.findByPk(Short.parseShort(telefono6.getCodTipoTelefono().trim()));
			} catch (Exception e) {
				log.error("El campo tipo telefono no es un tipo de dato short --->"
						+ telefono6.getCodTipoTelefono().trim());
			}

			tipoTelefonoSd.setCodTipoTelefono(existeTipoTelefono != null ? existeTipoTelefono.getCodTipoTelefono() : 0);
			telefono.setCodArea(telefono6.getCodArea());
			telefono.setNroTelefono(telefono6.getNroTelefono());
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		}

		return telefono;
	}

	public InformacionAdicionalSd MapperInformacionAdicional(InformacionAdicional registro,
			PersonaNaturalSd personaNatural, CanalSd canalSd) {
		InformacionAdicionalSd informacionAdicionalSd = new InformacionAdicionalSd();

		// CONTROLO Y VERIFICO SI EXISTE LA PROVINCIA
		ProvinciaSd provinciaSdInfoAdd = TraerProvinciaSd(registro.getSecProvincia());

		// SACO EL CANTON SD
		CantonSd cantonSdInfoAdd = TraerCantonSd(registro.getSecCanton());

		// SACO LA PARROQUIA SD
		ParroquiaSd parroquiaSdInfoAdd = TraerParroquiaSd(registro.getSecParroquia());

		// CONTROLO Y VERIFICO SI EXISTE LA ACTIVIDAD ECONOMICA
		ActividadEconomicaSd existeActividadEco = null;
		try {
			if (!VerificarVacios(registro.getCodActividadEconomica().trim()))
				existeActividadEco = actividadEconomicaServicio
						.findByPk(Short.parseShort(registro.getCodActividadEconomica().trim()));
		} catch (Exception e) {
			log.error("El campo codigoActividadEconomica no es un tipo de dato short --->"
					+ registro.getCodActividadEconomica().trim());
		}

		ActividadEconomicaSd actividadEconomicaSd = new ActividadEconomicaSd();
		actividadEconomicaSd.setCodActividadEconomica(
				existeActividadEco != null ? existeActividadEco.getCodActividadEconomica() : 1);

		TipoIdentificacionSd tipoIdentificacionInfoAdd = new TipoIdentificacionSd();
		tipoIdentificacionInfoAdd.setCodTipoIdentificacion(registro.getCodTipoIdentificacion().charAt(0));

		if (tipoIdentificacionInfoAdd.getCodTipoIdentificacion() != null)
			if (tipoIdentificacionInfoAdd.getCodTipoIdentificacion() != 'C'
					&& tipoIdentificacionInfoAdd.getCodTipoIdentificacion() != 'R')
				tipoIdentificacionInfoAdd.setCodTipoIdentificacion('R');

		informacionAdicionalSd.setSecPersonaNatural(personaNatural);
		informacionAdicionalSd.setCodTipoIdentificacion(tipoIdentificacionInfoAdd);
		informacionAdicionalSd.setIdentificacion(registro.getIdentificacion());
		informacionAdicionalSd.setRazonSocial(registro.getRazonSocial());
		informacionAdicionalSd.setNombreComercial(registro.getNombreComercial());
		informacionAdicionalSd.setFchInscripcion(
				!VerificarVacios(registro.getFechaInscripcion()) ? ConvertirFecha(registro.getFechaInscripcion())
						: null);
		informacionAdicionalSd.setFchInicioActividades(!VerificarVacios(registro.getFechaInicioActividades())
				? ConvertirFecha(registro.getFechaInicioActividades())
				: null);
		informacionAdicionalSd.setFchCancelacion(!VerificarVacios(registro.getFechaCancelacionActividades())
				? ConvertirFecha(registro.getFechaCancelacionActividades())
				: null);
		informacionAdicionalSd.setFchSuspension(!VerificarVacios(registro.getFechaSuspencionActividades())
				? ConvertirFecha(registro.getFechaSuspencionActividades())
				: null);
		informacionAdicionalSd.setFchReinicio(!VerificarVacios(registro.getFechaReinicioActividades())
				? ConvertirFecha(registro.getFechaReinicioActividades())
				: null);
		informacionAdicionalSd.setPrincipal(registro.getPrincipal());
		informacionAdicionalSd.setNumero(registro.getNumero());
		informacionAdicionalSd.setSecundaria(registro.getSecundaria());
		informacionAdicionalSd.setReferencia(registro.getReferencia());
		informacionAdicionalSd.setTelefono(registro.getTelefono());
		informacionAdicionalSd.setEMail(registro.getEmail());
		informacionAdicionalSd.setCodActividadEconomica(actividadEconomicaSd);
		informacionAdicionalSd.setSecProvincia(provinciaSdInfoAdd);
		informacionAdicionalSd.setSecCanton(cantonSdInfoAdd);
		informacionAdicionalSd.setSecParroquia(parroquiaSdInfoAdd);
		informacionAdicionalSd.setSecCanal(canalSd);
		informacionAdicionalSd.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
		informacionAdicionalSd.setTsCreacion(new Date());
		informacionAdicionalSd.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

		return informacionAdicionalSd;
	}

	public PersonaSd MapperPersona(Persona registro, TipoIdentificacionSd tipoIdentificacion) {

		// CREA PERSONA
		PersonaSd persona = new PersonaSd();
		persona.setIdentificacion(registro.getIdentificacion());
		persona.setCodTipoIdentificacion(tipoIdentificacion);
		persona.setDenominacion(registro.getDenominacion());

		return persona;
	}

	public PersonaNaturalSd MapperPersonaNatural(PersonaNatural registro, TipoIdentificacionSd tipoIdentificacion,
			PersonaSd persona, CanalSd canalSd, String codigoProfesion) {

		PersonaNaturalSd personaNatural = new PersonaNaturalSd();

		// CONTROLO Y VERIFICO QUE EXISTA EL ESTADO CIVIL
		EstadoCivilSd estadoCivilSd = new EstadoCivilSd();
		try {
			if (!VerificarVacios(registro.getCodEstadoCivil().trim()))
				estadoCivilSd = estadoCivilServicio.findByPk(Short.parseShort(registro.getCodEstadoCivil().trim()));
			else
				estadoCivilSd.setCodEstadoCivil((short) 0);
		} catch (Exception e) {
			log.error("El campo estado civil no es un tipo de dato short --->" + registro.getCodEstadoCivil().trim());
			estadoCivilSd.setCodEstadoCivil((short) 0);
		}

		PaisSd paisSd = new PaisSd();
		paisSd.setCodPais((short) 56); // ++++++PONER CONSTANTE++++++++++//

		// BUSCO LA PROFRESION
		ProfesionSd profesionSd = new ProfesionSd();
		if (!VerificarVacios(codigoProfesion.trim()))
			profesionSd = profesionServicio.consultarPorCodigoDB(codigoProfesion);
		else
			profesionSd.setSecProfesion((short) 1);

		if (profesionSd == null) {
			profesionSd = new ProfesionSd();
			profesionSd.setSecProfesion((short) 1);
		}

		personaNatural.setSecPersona(persona);
		personaNatural.setCodTipoIdentificacion(tipoIdentificacion);
		personaNatural.setIdentificacion(registro.getIdentificacion());
		personaNatural.setApellidoPaterno(registro.getApellidoPaterno());
		personaNatural.setApellidoMaterno(registro.getApellidoMaterno());
		personaNatural.setPrimerNombre(registro.getPrimerNombre());
		personaNatural.setSegundoNombre(registro.getSegundoNombre());
		personaNatural.setSexo(registro.getSexo().charAt(0));
		personaNatural.setCodPais(paisSd);
		personaNatural.setCodEstadoCivil(estadoCivilSd);
		personaNatural.setSecProfesion(profesionSd);
		personaNatural.setFchNacimiento(
				!VerificarVacios(registro.getFechaNacimiento()) ? ConvertirFecha(registro.getFechaNacimiento()) : null);
		personaNatural.setFchMatrimonio(
				!VerificarVacios(registro.getFechaMatrimonio()) ? ConvertirFecha(registro.getFechaMatrimonio()) : null);
		personaNatural.setFchFallecimiento(
				!VerificarVacios(registro.getFechaFallecimiento()) ? ConvertirFecha(registro.getFechaFallecimiento())
						: null);
		personaNatural.setSecCanal(canalSd);
		personaNatural.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
		personaNatural.setTsCreacion(new Date());
		personaNatural.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

		return personaNatural;
	}

	public DireccionElectronicaSd MapeoDireccionElectronica(String correoElectronico, PersonaSd persona,
			CanalSd canalSd) {

		TipoDireccionElectronicaSd tipoDireccionElectronicaSd = new TipoDireccionElectronicaSd();
		tipoDireccionElectronicaSd.setCodTipoDireccionElectronica((short) 1);

		DireccionElectronicaSd dirElectronica = new DireccionElectronicaSd();
		dirElectronica.setSecPersona(persona);
		dirElectronica.setCodTipoDireccionElectronica(tipoDireccionElectronicaSd);
		dirElectronica.setSecCanal(canalSd);
		dirElectronica.setEstado(EstadoEnum.A.getEstadoChar());
		dirElectronica.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
		dirElectronica.setTsCreacion(new Date());
		dirElectronica.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
		dirElectronica.setDireccionElectronica(!VerificarVacios(correoElectronico.trim()) ? correoElectronico : null);
		return dirElectronica;
	}

	public EmpleoDependienteSd MapeoEmpleoDependiente(Trabajo empleoDependiente, PersonaNaturalSd personaNatural,
			PersonaJuridicaSd PersonaJuridica, CanalSd canalSd) {
		EmpleoDependienteSd empDep = new EmpleoDependienteSd();
		empDep.setPersonaNatural(personaNatural);
		empDep.setPersonaJuridica(PersonaJuridica);
		empDep.setCargo(empleoDependiente.getCargo());
		empDep.setMntSalario(
				!VerificarVacios(empleoDependiente.getMntSalario()) ? new BigDecimal(empleoDependiente.getMntSalario())
						: new BigDecimal(0));
		empDep.setFchIngreso(ConvertirFecha(empleoDependiente.getFechaIngreso()));
		empDep.setFchSalida(new Date());//
		empDep.setSecCanal(canalSd);
		empDep.setEstado(EstadoEnum.A.getEstadoChar());//
		empDep.setTsCreacion(new Date());
		empDep.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
		empDep.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
		return empDep;
	}

	public PersonaJuridicaSd MapperPersonaJuridica(Trabajo registro, PersonaSd persona,
			TipoIdentificacionSd tipoIdentificacion, CanalSd canalSd, String codigoActividadEc) {

		PersonaJuridicaSd personaJuridicaSd = new PersonaJuridicaSd();
		ActividadEconomicaSd existeActividadEco = null;

		// CONTROLO Y VERIFICO SI EXISTE LA ACTIVIDAD ECONOMICA
		try {
			if (!VerificarVacios(codigoActividadEc.trim()))
				existeActividadEco = actividadEconomicaServicio.findByPk(Short.parseShort(codigoActividadEc.trim()));
		} catch (Exception e) {
			log.error("El campo codigoActividadEconomica no es un tipo de dato short --->" + codigoActividadEc);
		}

		ActividadEconomicaSd actividadEconomicaSdPJ = new ActividadEconomicaSd();
		actividadEconomicaSdPJ.setCodActividadEconomica(
				existeActividadEco != null ? existeActividadEco.getCodActividadEconomica() : 1);

		personaJuridicaSd.setSecPersona(persona);
		personaJuridicaSd.setCodTipoIdentificacion(tipoIdentificacion);
		personaJuridicaSd.setIdentificacion(registro.getIdentificacion());
		personaJuridicaSd.setRazonSocial(registro.getRazon_Social());
		personaJuridicaSd.setCodActividadEconomica(actividadEconomicaSdPJ);
		personaJuridicaSd.setActividadIess(registro.getDescripcion());
		personaJuridicaSd.setSecCanal(canalSd);
		personaJuridicaSd.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
		personaJuridicaSd.setTsCreacion(new Date());
		personaJuridicaSd.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

		return personaJuridicaSd;
	}

	public ParroquiaSd TraerParroquiaSd(String secParroquia) {
		// CONTROLO Y VERIFICO SI EXISTE LA PARROQUIA
		ParroquiaSd parroquiaSd = new ParroquiaSd();
		Short primarySecParroquia = null;
		// CONTROLO Y VERIFICO SI EXISTE EL CANTON
		try {
			if (!VerificarVacios(secParroquia.trim())) {
				primarySecParroquia = noPkTablasDao.obtenerCodigoParroquiaPorCodigoDB(secParroquia.trim(),
						ConsultaEnEnum.IESS);
				log.error("BUSCA SEC PARROQUIA: " + primarySecParroquia);
				if (primarySecParroquia == null) {
					primarySecParroquia = noPkTablasDao.obtenerCodigoParroquiaPorCodigoDB(secParroquia.trim(),
							ConsultaEnEnum.SRI);
					log.error("BUSCA SEC PARROQUIA: " + primarySecParroquia);
				}
				if (primarySecParroquia != null)
					parroquiaSd = parroquiaSdServicio.findByPk(primarySecParroquia);
				else
					parroquiaSd.setSecParroquia((short) 0);
			}
		} catch (Exception e) {
			parroquiaSd.setSecParroquia((short) 0);
			log.error("El campo secPaqrroquia no es un tipo de dato short --->" + secParroquia.trim());
		}

		return parroquiaSd;
	}

	public CantonSd TraerCantonSd(String secCanton) {
		CantonSd cantonSd = new CantonSd();
		Short primarySecCanton = null;
		// CONTROLO Y VERIFICO SI EXISTE EL CANTON
		try {
			if (!VerificarVacios(secCanton.trim())) {
				primarySecCanton = noPkTablasDao.obtenerCodigoCantonPorCodigoDB(secCanton.trim(), ConsultaEnEnum.IESS);
				log.error("BUSCA SEC CANTON: " + primarySecCanton);
				if (primarySecCanton == null) {
					primarySecCanton = noPkTablasDao.obtenerCodigoCantonPorCodigoDB(secCanton.trim(),
							ConsultaEnEnum.SRI);
					log.error("BUSCA SEC CANTON: " + primarySecCanton);
				}
				if (primarySecCanton != null) {
					cantonSd = cantonSdServicio.findByPk(primarySecCanton);
				} else {
					cantonSd.setSecCanton((short) 0);
				}
			}
		} catch (Exception e) {
			cantonSd.setSecCanton((short) 0);
			log.error("El campo secCanton no es un tipo de dato short --->" + secCanton);
		}

		return cantonSd;
	}

	public ProvinciaSd TraerProvinciaSd(String secProvincia) {
		ProvinciaSd provinciaSd = new ProvinciaSd();
		Short primarySecProvincia = null;
		// CONTROLO Y VERIFICO SI EXISTE EL CANTON
		try {
			if (!VerificarVacios(secProvincia.trim())) {
				primarySecProvincia = noPkTablasDao.obtenerCodigoProvinciaPorCodigoDB(secProvincia.trim(),
						ConsultaEnEnum.IESS);
				log.error("BUSCA SEC PROVINCIA: " + secProvincia);
				if (primarySecProvincia == null) {
					primarySecProvincia = noPkTablasDao.obtenerCodigoProvinciaPorCodigoDB(secProvincia.trim(),
							ConsultaEnEnum.SRI);
					log.error("BUSCA SEC PROVINCIA: " + primarySecProvincia);
				}
				if (primarySecProvincia != null) {
					provinciaSd = provinciaSdServicio.findByPk(primarySecProvincia);
				} else {
					provinciaSd.setSecProvincia((short) 0);
				}
			}
		} catch (Exception e) {
			provinciaSd.setSecProvincia((short) 0);
			log.error("El campo secCanton no es un tipo de dato short --->" + secProvincia);
		}

		return provinciaSd;
	}

	public PersonaJuridicaSd CrearPersonaJuridicaEmpleo(Trabajo trabajo, TipoIdentificacionSd tipoIdentificacionRuc,
			CanalSd canalSd, String codigoActividadEconomica) {

		log.error("Entra CrearPersonaJuridicaEmpleo");

		PersonaJuridicaSd existePersonaJuridica = new PersonaJuridicaSd();
		if (!VerificarVacios(trabajo.getRazon_Social().trim())
				|| !VerificarVacios(trabajo.getIdentificacion().trim())) {

			// VERIFICAR SI EXISTE PERSONA
			PersonaSd existePersonaJ = personaServicio.obtenerPersonaByIdentificacion(trabajo.getIdentificacion());

			log.error("LLEGA ");
			log.error(existePersonaJ);

			// SI NO EXISTE LA PERSONA JURIDICA DENTO DE PERSONA GUARDO LA PERSONA JURIDICA
			if (existePersonaJ == null) {
				log.error("ENTRA");

				existePersonaJ = new PersonaSd();
				existePersonaJ.setIdentificacion(trabajo.getIdentificacion());
				existePersonaJ.setCodTipoIdentificacion(tipoIdentificacionRuc);
				existePersonaJ.setDenominacion(trabajo.getRazon_Social());

				log.error("LLEGO PERSONA R");
				personaServicio.IngresarPersona(existePersonaJ);
				log.error(existePersonaJ);
				log.error("GUARDO PERSONA R");
			}

			log.error("PASA");

			log.error("aqui esta el error ya me vien en la consultas de arriba el persona juridica");
			// BUSCO LA PERSONA JURIDICA DENTRO DE LA TABLA PERSONA JURIDICA
			if (existePersonaJ.getPersonaJuridica() == null)
				existePersonaJuridica = personaJuridicaServicio
						.buscarPersonaPorIdentificacion(existePersonaJ.getIdentificacion());
			else
				existePersonaJuridica = existePersonaJ.getPersonaJuridica();

			log.error(existePersonaJuridica);

			// SI NO EXISTE SE PROCEDE A INGRESAR LA PERSONA JURIDICA
			if (existePersonaJuridica == null) {
				existePersonaJuridica = MapperPersonaJuridica(trabajo, existePersonaJ, tipoIdentificacionRuc, canalSd,
						codigoActividadEconomica);

				log.error("LLEGA PERSONA JURIDICA");
				personaJuridicaServicio.crearSoloPersonaJuridica(existePersonaJuridica);
				log.error("GUARDA PERSONA JURIDICA");
			}
		}

		log.error("Sale CrearPersonaJuridicaEmpleo");
		log.error(existePersonaJuridica);

		return existePersonaJuridica;
	}

	public EmpleoDependienteSd EmpleoDependiente(PersonaJuridicaSd existePersonaJuridica,
			PersonaNaturalSd personaNatrual, Trabajo trabajo, CanalSd canalSd) {

		log.error("Entra EmpleoDependiente");

		// ACA SE INGRESAN LOS DATOS DEL EMPLEO
		EmpleoDependienteSd empleoDependienteSd = new EmpleoDependienteSd();
		if (existePersonaJuridica.getSecPersonaJuridica() != null && !VerificarVacios(trabajo.getFechaIngreso())) {

			// CREA EMPLEOD EPENDIENTE
			log.error("AAAA");
			empleoDependienteSd = MapeoEmpleoDependiente(trabajo, personaNatrual, existePersonaJuridica, canalSd);
			log.error("LLEGA EMPLEO DEPENDIENTE");
			empleoDependienteServicio.crearEmpleoDependiente(empleoDependienteSd);
			log.error("GUARDA EMPLEO DEPENDIENTE");
		}

		log.error("Sale EmpleoDependiente");

		return empleoDependienteSd;
	}

	public EmpleoDependienteSd ActualizarEmpleoDependiente(PersonaNaturalSd personaNatural,
			PersonaJuridicaSd personaJuridica, Trabajo trabajo, CanalSd canalSd) {

		EmpleoDependienteSd empleoDependienteSd = new EmpleoDependienteSd();

		log.error("Entra ActualizarEmpleoDependiente");
		if (personaNatural != null && personaNatural.getSecPersonaNatural() != null && personaJuridica != null
				&& personaJuridica.getSecPersonaJuridica() != null && !VerificarVacios(trabajo.getFechaIngreso())) {

			empleoDependienteSd = empleoDependienteServicio.obtenerEmpleoDependienteBySecPersonaNatural(
					personaNatural.getSecPersonaNatural(), personaJuridica.getSecPersonaJuridica());

			if (empleoDependienteSd != null) {

				empleoDependienteSd.setPersonaNatural(personaNatural);
				empleoDependienteSd.setPersonaJuridica(personaJuridica);
				empleoDependienteSd.setCargo(trabajo.getCargo());
				empleoDependienteSd.setMntSalario(
						!VerificarVacios(trabajo.getMntSalario()) ? new BigDecimal(trabajo.getMntSalario())
								: new BigDecimal(0));
				empleoDependienteSd.setFchIngreso(ConvertirFecha(trabajo.getFechaIngreso()));
				empleoDependienteSd.setFchSalida(new Date());//
				empleoDependienteSd.setSecCanal(canalSd);
				empleoDependienteSd.setEstado(EstadoEnum.A.getEstadoChar());//
				empleoDependienteSd.setTsCreacion(new Date());
				empleoDependienteSd.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
				empleoDependienteSd.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

				empleoDependienteServicio.update(empleoDependienteSd);
				log.error("ACTUALIZA EMPLEO DEPENDIENTE");
			} else {
				empleoDependienteSd = MapeoEmpleoDependiente(trabajo, personaNatural, personaJuridica, canalSd);
				empleoDependienteServicio.crearEmpleoDependiente(empleoDependienteSd);
				log.error("GUARDA EMPLEO DEPENDIENTE");
			}
			log.error("PASA EMPLEO DEPENDIENTE");
		}

		log.error("Sale ActualizarEmpleoDependiente");

		return empleoDependienteSd;
	}
}
