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
import com.equivida.databook.model.RegistrosEntity.EmpleoDependiente;
import com.equivida.databook.model.RegistrosEntity.Telefono1;
import com.equivida.databook.model.RegistrosEntity.Telefono2;
import com.equivida.databook.model.RegistrosEntity.Telefono3;
import com.equivida.databook.model.RegistrosEntity.Telefono4;
import com.equivida.databook.model.RegistrosEntity.Telefono5;
import com.equivida.databook.model.RegistrosEntity.Telefono6;
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
import com.equivida.smartdata.servicio.DireccionElectronicaSdServicio;
import com.equivida.smartdata.servicio.DireccionSdServicio;
import com.equivida.smartdata.servicio.EmpleoDependienteSdServicio;
import com.equivida.smartdata.servicio.EstadoCivilSdServicio;
import com.equivida.smartdata.servicio.PersonaJuridicaSdServicio;
import com.equivida.smartdata.servicio.PersonaNaturalSdServicio;
import com.equivida.smartdata.servicio.PersonaSdServicio;
import com.equivida.smartdata.servicio.ProfesionSdServicio;
import com.equivida.smartdata.servicio.RelacionSdServicio;
import com.equivida.smartdata.servicio.SmartDataSdServicio;
import com.equivida.smartdata.servicio.SmartDataServicioSdRemote;
import com.equivida.smartdata.servicio.TelefonoSdServicio;
import com.equivida.smartdata.servicio.PersonaNaturalServicio;
import com.equivida.smartdata.servicio.InformacionAdicionalSdServicio;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.SmartDataServicio#
	 * consultaClienteSmartData(java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersonaSd consultaClienteSmartData(String identificacion, boolean conRelaciones) throws SmartdataException {

		PersonaSd persona = new PersonaSd();
		PersonaNaturalSd pn = personaNatServicio.consumirServicio(identificacion);
		persona.setIdentificacion("" + pn.getSecPersona());

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
			
			log.error("LLEGA 2");
			log.error(registros);

			// INGRESO RESGISTROS DE LA PERSONA EN LA BASE DE DATOS
			boolean actualizaIngresa = false; 
			actualizaIngresa = GuardarInformacionPersona(registros.getTitular());
			
			if(registros.getConyuge()!= null && actualizaIngresa == true)
				GuardarInformacionConyugue(registros.getConyuge());
			
			log.error("LLEGA 2");

			PersonaSd personaSd = new PersonaSd();
			personaSd.setIdentificacion(identificacion);
			personaSd.setDenominacion("Ejemplo");

			/*
			 * System.out.println(
			 * "===========================transforma DB a SD consulta en DB: " +
			 * identificacion + new Date()); DataBookHelper dbh = new
			 * DataBookHelper(registros, usuario, noPkTablasDao, estadoCivilServicio,
			 * profesionServicio, actividadEconomicaServicio, personaJuridicaServicio,
			 * personaServicio, direccionSdServicio, telefonoSdServicio);
			 * 
			 * PersonaSd personaSd = dbh.getPersonaNatural();
			 * 
			 * // 4. Se debe crear las relaciones con conyuge, padre, madre
			 * crearRelacionesPersonales(registros, personaSd, usuario);
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
			 * System.out.println(
			 * "===========================FIN transforma DB a SD consulta en DB: " +
			 * identificacion + new Date());
			 * 
			 * // Se pone esta linea para que se pueda presentar la informacion en // xml
			 * soap de respuesta activarPresentacionParaWs(personaSd);
			 */

			return personaSd;
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

	public EmpleoDependienteSd MapeoEmpleoDependiente(EmpleoDependiente empleoDependiente,
			PersonaNaturalSd personaNatural, PersonaJuridicaSd PersonaJuridica, CanalSd canalSd) {
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

	public void GuardarInformacionConyugue(RegistrosEntity.Conyuge registro){
		
		
	}
	
	public boolean GuardarInformacionPersona(RegistrosEntity.Titular registro) {
		// 1. Se consulta persona por identificacion
		PersonaSd existePersona = personaServicio
				.obtenerPersonaByIdentificacion(registro.getPersona().getIdentificacion());

		PaisSd paisSd = new PaisSd();
		paisSd.setCodPais((short) 56); // ++++++PONER CONSTANTE++++++++++//

		CanalSd canalSd = new CanalSd();
		canalSd.setSecCanal((short) 3);// ++++++PONER CONSTANTE++++++++++//

		// SOLO INGRESAR DATOS CUADNO NO EXISTA LA PERSONA
		if (existePersona == null) {
			// INICIALIZO OBJETOS POR DEFECTO
			TipoIdentificacionSd tipoIdentificacion = new TipoIdentificacionSd();
			tipoIdentificacion.setCodTipoIdentificacion(registro.getPersona().getCodTipoIdentificacion().charAt(0));

			// CREA PERSONA
			PersonaSd persona = new PersonaSd();
			persona.setIdentificacion(registro.getPersona().getIdentificacion());
			persona.setCodTipoIdentificacion(tipoIdentificacion);
			persona.setDenominacion(registro.getPersona().getDenominacion());
			log.error("LLEGO PERSONA");
			personaServicio.IngresarPersona(persona);
			log.error(persona);
			log.error("GUARDO PERSONA");

			// CREA PERSONA NATURAL
			EstadoCivilSd estadoCivilSd = new EstadoCivilSd();
			estadoCivilSd.setCodEstadoCivil(!VerificarVacios(registro.getPersonaNatural().getCodEstadoCivil())
					? Short.parseShort(registro.getPersonaNatural().getCodEstadoCivil())
					: 0);

			ProfesionSd profesionSd = new ProfesionSd();
			// BUSCO LA PROFRESION
			if (!VerificarVacios(registro.getEmpleoDependiente().getSecProfesion()))
				profesionSd = profesionServicio.consultarPorCodigoDB(registro.getEmpleoDependiente().getSecProfesion());
			else
				profesionSd.setSecProfesion((short) 1);// --PREGUNTAR---

			PersonaNaturalSd personaNatural = new PersonaNaturalSd();
			personaNatural.setSecPersona(persona);
			personaNatural.setCodTipoIdentificacion(tipoIdentificacion);
			personaNatural.setIdentificacion(registro.getPersonaNatural().getIdentificacion());
			personaNatural.setApellidoPaterno(registro.getPersonaNatural().getApellidoPaterno());
			personaNatural.setApellidoMaterno(registro.getPersonaNatural().getApellidoMaterno());
			personaNatural.setPrimerNombre(registro.getPersonaNatural().getPrimerNombre());
			personaNatural.setSegundoNombre(registro.getPersonaNatural().getSegundoNombre());
			personaNatural.setSexo(registro.getPersonaNatural().getSexo().charAt(0));
			personaNatural.setCodPais(paisSd);
			personaNatural.setCodEstadoCivil(estadoCivilSd);
			personaNatural.setSecProfesion(profesionSd);
			personaNatural.setFchNacimiento(!VerificarVacios(registro.getPersonaNatural().getFechaNacimiento())
					? ConvertirFecha(registro.getPersonaNatural().getFechaNacimiento())
					: null);
			personaNatural.setFchMatrimonio(!VerificarVacios(registro.getPersonaNatural().getFechaMatrimonio())
					? ConvertirFecha(registro.getPersonaNatural().getFechaMatrimonio())
					: null);
			personaNatural.setFchFallecimiento(!VerificarVacios(registro.getPersonaNatural().getFechaFallecimiento())
					? ConvertirFecha(registro.getPersonaNatural().getFechaFallecimiento())
					: null);
			personaNatural.setSecCanal(canalSd);
			personaNatural.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
			personaNatural.setTsCreacion(new Date());
			personaNatural.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

			log.error("PASA PERSONA NATURAL");
			personaNaturalServicio.insertarPersonaNatural(personaNatural);
			log.error("GUARDA PERSONA NATURAL");

			// CREA DIRECCION
			if (!VerificarVacios(registro.getDireccion().getDireccion().trim())) {
				DireccionSd direccionsd = MapeoDireccionSd(registro.getDireccion(), canalSd, persona);
				log.error("LLEGA DIRECCION");
				direccionSdServicio.ingresarDireccion(direccionsd);
				log.error("GUARDA DIRECCION");
			}

			// CREA TELEFONOS
			if (!VerificarVacios(registro.getTelefonos().getTelefono1().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono1().getNroTelefono())) {

				TelefonoSd telefono = MapperTelefono(registro.getTelefonos().getTelefono1(), null, null, null, null,
						null, canalSd, persona);
				log.error("LLEGA TELEFONO 1");
				telefonoSdServicio.ingresarTelefono(telefono);
				log.error("GUARDA TELEFONO 1");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono2().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono2().getNroTelefono())) {

				TelefonoSd telefono2 = MapperTelefono(null, registro.getTelefonos().getTelefono2(), null, null, null,
						null, canalSd, persona);

				log.error("LLEGA TELEFONO 2");
				telefonoSdServicio.ingresarTelefono(telefono2);
				log.error("GUARDA TELEFONO 2");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono3().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono3().getNroTelefono())) {

				TelefonoSd telefono3 = MapperTelefono(null, null, registro.getTelefonos().getTelefono3(), null, null,
						null, canalSd, persona);

				log.error("LLEGA TELEFONO 3");
				telefonoSdServicio.ingresarTelefono(telefono3);
				log.error("GUARDA TELEFONO 3");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono4().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono4().getNroTelefono())) {

				TelefonoSd telefono4 = MapperTelefono(null, null, null, registro.getTelefonos().getTelefono4(), null,
						null, canalSd, persona);

				log.error("LLEGA TELEFONO 4");
				telefonoSdServicio.ingresarTelefono(telefono4);
				log.error("GUARDA TELEFONO 4");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono5().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono5().getNroTelefono())) {

				TelefonoSd telefono5 = MapperTelefono(null, null, null, null, registro.getTelefonos().getTelefono5(),
						null, canalSd, persona);

				log.error("LLEGA TELEFONO 5");
				telefonoSdServicio.ingresarTelefono(telefono5);
				log.error("GUARDA TELEFONO 5");
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono6().getCodArea())
					&& !VerificarVacios(registro.getTelefonos().getTelefono6().getNroTelefono())) {

				TelefonoSd telefono6 = MapperTelefono(null, null, null, null, null,
						registro.getTelefonos().getTelefono6(), canalSd, persona);

				log.error("LLEGA TELEFONO 6");
				telefonoSdServicio.ingresarTelefono(telefono6);
				log.error("GUARDA TELEFONO 6");
			}

			// CREA DIRECCION ELECTRONICA
			TipoDireccionElectronicaSd tipoDireccionElectronicaSd = new TipoDireccionElectronicaSd();
			tipoDireccionElectronicaSd.setCodTipoDireccionElectronica((short) 1);

			if (!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico1().trim())) {
				DireccionElectronicaSd direccionElectronica = new DireccionElectronicaSd();
				direccionElectronica.setSecPersona(persona);
				direccionElectronica.setCodTipoDireccionElectronica(tipoDireccionElectronicaSd);
				direccionElectronica.setSecCanal(canalSd);
				direccionElectronica.setEstado(EstadoEnum.A.getEstadoChar());
				direccionElectronica.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
				direccionElectronica.setTsCreacion(new Date());
				direccionElectronica.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
				log.error("--->" + registro.getDireccionElectronico().getCorreo_electronico1());
				direccionElectronica.setDireccionElectronica(
						!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico1().trim())
								? registro.getDireccionElectronico().getCorreo_electronico1()
								: null);

				log.error("--->" + direccionElectronica.getDireccionElectronica());

				log.error("LLEGA DIRECCION ELECTRONICA 1");
				direccionElectronicaSdServicio.ingresarDireccionElectronica(direccionElectronica);
				log.error("GUARDA DIRECCION ELECTRONICA 1");
			}

			// CREA DIRECCION ELECTRONICA 2
			if (!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico2().trim())) {
				DireccionElectronicaSd direccionElectronica2 = new DireccionElectronicaSd();
				direccionElectronica2.setSecPersona(persona);
				direccionElectronica2.setCodTipoDireccionElectronica(tipoDireccionElectronicaSd);
				direccionElectronica2.setSecCanal(canalSd);
				direccionElectronica2.setEstado(EstadoEnum.A.getEstadoChar());
				direccionElectronica2.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
				direccionElectronica2.setTsCreacion(new Date());
				direccionElectronica2.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
				log.error("--->" + registro.getDireccionElectronico().getCorreo_electronico2());
				direccionElectronica2.setDireccionElectronica(
						!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico2().trim())
								? registro.getDireccionElectronico().getCorreo_electronico2()
								: null);

				log.error("LLEGA DIRECCION ELECTRONICA 2");
				direccionElectronicaSdServicio.ingresarDireccionElectronica(direccionElectronica2);
				log.error("GUARDA DIRECCION ELECTRONICA 2");
			}

			// CREA INFORMACION ADCIONAL
			if (personaNatural.getSecPersonaNatural() > 0 // para saber que se INSERTO una PN
					|| !VerificarVacios(registro.getInformacionAdicional().getIdentificacion().trim())
					|| !VerificarVacios(registro.getInformacionAdicional().getCodTipoIdentificacion().trim())
					|| !VerificarVacios(registro.getInformacionAdicional().getRazonSocial().trim())
					|| !VerificarVacios(registro.getInformacionAdicional().getFechaInscripcion().trim())
					|| !VerificarVacios(registro.getInformacionAdicional().getFechaInicioActividades().trim())
					|| !VerificarVacios(registro.getInformacionAdicional().getPrincipal().trim())) {

				ProvinciaSd provinciaSdInfoAdd = new ProvinciaSd();
				provinciaSdInfoAdd
						.setSecProvincia(!VerificarVacios(registro.getInformacionAdicional().getSecProvincia())
								? Short.parseShort(registro.getInformacionAdicional().getSecProvincia().trim())
								: 0);

				CantonSd cantonSdInfoAdd = new CantonSd();
				cantonSdInfoAdd.setSecCanton(!VerificarVacios(registro.getInformacionAdicional().getSecCanton())
						? Short.parseShort(registro.getInformacionAdicional().getSecCanton().trim())
						: 0);

				ParroquiaSd parroquiaSdInfoAdd = new ParroquiaSd();
				parroquiaSdInfoAdd
						.setSecParroquia(!VerificarVacios(registro.getInformacionAdicional().getSecParroquia())
								? Short.parseShort(registro.getInformacionAdicional().getSecParroquia().trim())
								: 0);

				ActividadEconomicaSd actividadEconomicaSd = new ActividadEconomicaSd();
				actividadEconomicaSd.setCodActividadEconomica(
						!VerificarVacios(registro.getInformacionAdicional().getCodActividadEconomica().trim())
								? Short.parseShort(registro.getInformacionAdicional().getCodActividadEconomica())
								: 1);

				TipoIdentificacionSd tipoIdentificacionInfoAdd = new TipoIdentificacionSd();
				tipoIdentificacionInfoAdd.setCodTipoIdentificacion(
						registro.getInformacionAdicional().getCodTipoIdentificacion().charAt(0));

				InformacionAdicionalSd informacionAdicionalSd = new InformacionAdicionalSd();
				informacionAdicionalSd.setSecPersonaNatural(personaNatural);
				informacionAdicionalSd.setCodTipoIdentificacion(tipoIdentificacionInfoAdd);
				informacionAdicionalSd.setIdentificacion(registro.getInformacionAdicional().getIdentificacion());
				informacionAdicionalSd.setRazonSocial(registro.getInformacionAdicional().getRazonSocial());
				informacionAdicionalSd.setNombreComercial(registro.getInformacionAdicional().getNombreComercial());
				informacionAdicionalSd
						.setFchInscripcion(!VerificarVacios(registro.getInformacionAdicional().getFechaInscripcion())
								? ConvertirFecha(registro.getInformacionAdicional().getFechaInscripcion())
								: null);
				informacionAdicionalSd.setFchInicioActividades(
						!VerificarVacios(registro.getInformacionAdicional().getFechaInicioActividades())
								? ConvertirFecha(registro.getInformacionAdicional().getFechaInicioActividades())
								: null);
				informacionAdicionalSd.setFchCancelacion(
						!VerificarVacios(registro.getInformacionAdicional().getFechaCancelacionActividades())
								? ConvertirFecha(registro.getInformacionAdicional().getFechaCancelacionActividades())
								: null);
				informacionAdicionalSd.setFchSuspension(
						!VerificarVacios(registro.getInformacionAdicional().getFechaSuspencionActividades())
								? ConvertirFecha(registro.getInformacionAdicional().getFechaSuspencionActividades())
								: null);
				informacionAdicionalSd.setFchReinicio(
						!VerificarVacios(registro.getInformacionAdicional().getFechaReinicioActividades())
								? ConvertirFecha(registro.getInformacionAdicional().getFechaReinicioActividades())
								: null);
				informacionAdicionalSd.setPrincipal(registro.getInformacionAdicional().getPrincipal());
				informacionAdicionalSd.setNumero(registro.getInformacionAdicional().getNumero());
				informacionAdicionalSd.setSecundaria(registro.getInformacionAdicional().getSecundaria());
				informacionAdicionalSd.setReferencia(registro.getInformacionAdicional().getReferencia());
				informacionAdicionalSd.setTelefono(registro.getInformacionAdicional().getTelefono());
				informacionAdicionalSd.setEMail("jjj");// -------------------------------------------------------
				informacionAdicionalSd.setCodActividadEconomica(actividadEconomicaSd);
				informacionAdicionalSd.setSecProvincia(provinciaSdInfoAdd);
				informacionAdicionalSd.setSecCanton(cantonSdInfoAdd);
				informacionAdicionalSd.setSecParroquia(parroquiaSdInfoAdd);
				informacionAdicionalSd.setSecCanal(canalSd);
				informacionAdicionalSd.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
				informacionAdicionalSd.setTsCreacion(new Date());
				informacionAdicionalSd.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

				log.error("LLEGA INFORMACION ADICIONAL");
				informacionAdicionalSdServicio.crearInformacionAdicional(informacionAdicionalSd);
				log.error("GUARDA INFORMACION ADICIONAL");
			}

			// CREA PERSONA JURIDICA
			PersonaJuridicaSd personaJuridicaSd = new PersonaJuridicaSd();

			if (!VerificarVacios(registro.getEmpleoDependiente().getRazon_Social())
					|| !VerificarVacios(registro.getEmpleoDependiente().getIdentificacion())) {

				ActividadEconomicaSd actividadEconomicaSdPJ = new ActividadEconomicaSd();
				int codAodActividadEconomicaPJ = !VerificarVacios(
						registro.getInformacionAdicional().getCodActividadEconomica())
								? Integer.parseInt(registro.getInformacionAdicional().getCodActividadEconomica())
								: 0;
				actividadEconomicaSdPJ.setCodActividadEconomica((short) codAodActividadEconomicaPJ);

				personaJuridicaSd.setSecPersona(persona);
				personaJuridicaSd.setCodTipoIdentificacion(tipoIdentificacion);
				personaJuridicaSd.setIdentificacion(registro.getEmpleoDependiente().getIdentificacion());
				personaJuridicaSd.setRazonSocial(registro.getEmpleoDependiente().getRazon_Social());
				personaJuridicaSd.setCodActividadEconomica(actividadEconomicaSdPJ);
				personaJuridicaSd.setActividadIess(registro.getEmpleoDependiente().getDescripcion());
				personaJuridicaSd.setSecCanal(canalSd);
				personaJuridicaSd.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
				personaJuridicaSd.setTsCreacion(new Date());
				personaJuridicaSd.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

				log.error("LLEGA PERSONA JURIDICA");
				personaJuridicaServicio.crearSoloPersonaJuridica(personaJuridicaSd);
				log.error("GUARDA PERSONA JURIDICA");
			}

			if (personaJuridicaSd.getSecPersonaJuridica() > 0 // para saber que se INSERTO una PJ
					|| !VerificarVacios(registro.getEmpleoDependiente().getFechaIngreso())) {
				// CREA EMPLEOD EPENDIENTE
				EmpleoDependienteSd empleoDependienteSd = new EmpleoDependienteSd();
				empleoDependienteSd.setPersonaNatural(personaNatural);
				empleoDependienteSd.setPersonaJuridica(personaJuridicaSd);
				empleoDependienteSd.setCargo(registro.getEmpleoDependiente().getCargo());
				empleoDependienteSd.setMntSalario(!VerificarVacios(registro.getEmpleoDependiente().getMntSalario())
						? new BigDecimal(registro.getEmpleoDependiente().getMntSalario())
						: new BigDecimal(0));
				empleoDependienteSd.setFchIngreso(ConvertirFecha(registro.getEmpleoDependiente().getFechaIngreso()));
				empleoDependienteSd.setFchSalida(new Date());//
				empleoDependienteSd.setSecCanal(canalSd);
				empleoDependienteSd.setEstado(EstadoEnum.A.getEstadoChar());//
				empleoDependienteSd.setTsCreacion(new Date());
				empleoDependienteSd.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
				empleoDependienteSd.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

				log.error("LLEGA EMPLEO DEPENDIENTE");
				empleoDependienteServicio.crearEmpleoDependiente(empleoDependienteSd);
				log.error("GUARDA EMPLEO DEPENDIENTE");
			}
			
			log.error("FIN PROCESO CREAR TITULAR");
		} else {
			// ZONA DE ACTUALIZACION
			// NO ACTUALIZAR PERSONA, PERSONA NATURAL, PERSONA JURIDICA
			// existePersona ==> PersonaSd

			log.error("ACTUALIZACION------------------------------------");
			// ACTUALIZACION DIRECCION
			DireccionSd direccionsd = direccionSdServicio
					.obtenerDireccionByPersonaSecPersona(existePersona.getSecPersona());

			Integer secDirecion = null;
			if (direccionsd != null) {
				secDirecion = direccionsd.getSecDireccion();
				log.error(direccionsd.getSecDireccion());
			}

			if (!VerificarVacios(registro.getDireccion().getDireccion().trim())) {
				direccionsd = MapeoDireccionSd(registro.getDireccion(), canalSd, existePersona);
				direccionsd.setSecDireccion(secDirecion);

				log.error("INFORMACION");
				log.error(direccionsd);
				if (direccionsd.getSecDireccion() != null) {
					log.error("ACTUALIZA DIRECCION");
					direccionSdServicio.update(direccionsd);
				} else {
					log.error("INGRESA DIRECCION");
					direccionSdServicio.ingresarDireccion(direccionsd);
				}
			}

			// ACTUALIZACION TELEFONOS
			List<TelefonoSd> listaTelefonos = telefonoSdServicio
					.obtenerTelefonoByPersonaSecPersona(existePersona.getSecPersona());

			if (!VerificarVacios(registro.getTelefonos().getTelefono1().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 1) {
					TelefonoSd telefono1 = telefonoSdServicio.findByPk(listaTelefonos.get(0).getSecTelefono());

					// telefono1 = MapperTelefono(registro.getTelefonos().getTelefono1(), null,
					// null, null, null, null, canalSd, existePersona);
					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					telefono1.setSecPersona(existePersona);
					telefono1.setSecCanal(canalSd);
					telefono1.setEstado(EstadoEnum.A.getEstadoChar());
					telefono1.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono1.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono1.setTsCreacion(new Date());
					telefono1.setCodArea(listaTelefonos.get(0).getCodArea());
					telefono1.setNroTelefono(listaTelefonos.get(0).getNroTelefono());
					tipoTelefonoSd.setCodTipoTelefono(
							!VerificarVacios(registro.getTelefonos().getTelefono1().getCodTipoTelefono())
									? Short.parseShort(registro.getTelefonos().getTelefono1().getCodTipoTelefono())
									: (short) (0));
					telefono1.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO1");
					log.error(telefono1);
					telefonoSdServicio.update(telefono1);
				} else {
					TelefonoSd telefono1 = MapperTelefono(registro.getTelefonos().getTelefono1(), null, null, null,
							null, null, canalSd, existePersona);

					log.error("INGRESA TELEFONO1");
					log.error(telefono1);
					telefonoSdServicio.ingresarTelefono(telefono1);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono2().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 2) {
					TelefonoSd telefono2 = telefonoSdServicio.findByPk(listaTelefonos.get(1).getSecTelefono());

					// telefono1 = MapperTelefono(null, registro.getTelefonos().getTelefono2(),
					// null, null, null, null, canalSd, existePersona);
					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					telefono2.setSecPersona(existePersona);
					telefono2.setSecCanal(canalSd);
					telefono2.setEstado(EstadoEnum.A.getEstadoChar());
					telefono2.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono2.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono2.setTsCreacion(new Date());
					telefono2.setCodArea(listaTelefonos.get(1).getCodArea());
					telefono2.setNroTelefono(listaTelefonos.get(1).getNroTelefono());
					tipoTelefonoSd.setCodTipoTelefono(
							!VerificarVacios(registro.getTelefonos().getTelefono2().getCodTipoTelefono())
									? Short.parseShort(registro.getTelefonos().getTelefono2().getCodTipoTelefono())
									: (short) (0));
					telefono2.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO2");
					log.error(telefono2);
					telefonoSdServicio.update(telefono2);
				} else {
					TelefonoSd telefono2 = MapperTelefono(null, registro.getTelefonos().getTelefono2(), null, null,
							null, null, canalSd, existePersona);

					log.error("INGRESA TELEFONO2");
					log.error(telefono2);
					telefonoSdServicio.ingresarTelefono(telefono2);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono3().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 3) {
					TelefonoSd telefono3 = telefonoSdServicio.findByPk(listaTelefonos.get(2).getSecTelefono());

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					telefono3.setSecPersona(existePersona);
					telefono3.setSecCanal(canalSd);
					telefono3.setEstado(EstadoEnum.A.getEstadoChar());
					telefono3.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono3.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono3.setTsCreacion(new Date());
					telefono3.setCodArea(listaTelefonos.get(2).getCodArea());
					telefono3.setNroTelefono(listaTelefonos.get(2).getNroTelefono());
					tipoTelefonoSd.setCodTipoTelefono(
							!VerificarVacios(registro.getTelefonos().getTelefono3().getCodTipoTelefono())
									? Short.parseShort(registro.getTelefonos().getTelefono3().getCodTipoTelefono())
									: (short) (0));
					telefono3.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO3");
					log.error(telefono3);
					telefonoSdServicio.update(telefono3);
				} else {
					TelefonoSd telefono3 = MapperTelefono(null, null, registro.getTelefonos().getTelefono3(), null,
							null, null, canalSd, existePersona);

					log.error("INGRESA TELEFONO3");
					log.error(telefono3);
					telefonoSdServicio.ingresarTelefono(telefono3);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono4().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 4) {
					TelefonoSd telefono4 = telefonoSdServicio.findByPk(listaTelefonos.get(3).getSecTelefono());

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					telefono4.setSecPersona(existePersona);
					telefono4.setSecCanal(canalSd);
					telefono4.setEstado(EstadoEnum.A.getEstadoChar());
					telefono4.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono4.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono4.setTsCreacion(new Date());
					telefono4.setCodArea(listaTelefonos.get(3).getCodArea());
					telefono4.setNroTelefono(listaTelefonos.get(3).getNroTelefono());
					tipoTelefonoSd.setCodTipoTelefono(
							!VerificarVacios(registro.getTelefonos().getTelefono4().getCodTipoTelefono())
									? Short.parseShort(registro.getTelefonos().getTelefono4().getCodTipoTelefono())
									: (short) (0));
					telefono4.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO4");
					log.error(telefono4);
					telefonoSdServicio.update(telefono4);
				} else {
					TelefonoSd telefono4 = MapperTelefono(null, null, null, registro.getTelefonos().getTelefono4(),
							null, null, canalSd, existePersona);

					log.error("INGRESA TELEFONO4");
					log.error(telefono4);
					telefonoSdServicio.ingresarTelefono(telefono4);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono5().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 5) {
					TelefonoSd telefono5 = telefonoSdServicio.findByPk(listaTelefonos.get(4).getSecTelefono());

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					telefono5.setSecPersona(existePersona);
					telefono5.setSecCanal(canalSd);
					telefono5.setEstado(EstadoEnum.A.getEstadoChar());
					telefono5.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono5.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono5.setTsCreacion(new Date());
					telefono5.setCodArea(listaTelefonos.get(4).getCodArea());
					telefono5.setNroTelefono(listaTelefonos.get(4).getNroTelefono());
					tipoTelefonoSd.setCodTipoTelefono(
							!VerificarVacios(registro.getTelefonos().getTelefono5().getCodTipoTelefono())
									? Short.parseShort(registro.getTelefonos().getTelefono5().getCodTipoTelefono())
									: (short) (0));
					telefono5.setCodTipoTelefono(tipoTelefonoSd);

					log.error("ACTUALIZA TELEFONO5");
					log.error(telefono5);
					telefonoSdServicio.update(telefono5);
				} else {
					TelefonoSd telefono5 = MapperTelefono(null, null, null, null,
							registro.getTelefonos().getTelefono5(), null, canalSd, existePersona);

					log.error("INGRESA TELEFONO5");
					log.error(telefono5);
					telefonoSdServicio.ingresarTelefono(telefono5);
				}
			}

			if (!VerificarVacios(registro.getTelefonos().getTelefono6().getNroTelefono())) {
				if (listaTelefonos != null && listaTelefonos.size() >= 6) {
					TelefonoSd telefono6 = telefonoSdServicio.findByPk(listaTelefonos.get(5).getSecTelefono());

					TipoTelefonoSd tipoTelefonoSd = new TipoTelefonoSd();
					telefono6.setSecPersona(existePersona);
					telefono6.setSecCanal(canalSd);
					telefono6.setEstado(EstadoEnum.A.getEstadoChar());
					telefono6.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					telefono6.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
					telefono6.setTsCreacion(new Date());
					telefono6.setCodArea(listaTelefonos.get(5).getCodArea());
					telefono6.setNroTelefono(listaTelefonos.get(5).getNroTelefono());
					tipoTelefonoSd.setCodTipoTelefono(
							!VerificarVacios(registro.getTelefonos().getTelefono6().getCodTipoTelefono())
									? Short.parseShort(registro.getTelefonos().getTelefono6().getCodTipoTelefono())
									: (short) (0));
					telefono6.setCodTipoTelefono(tipoTelefonoSd);
					log.error("ACTUALIZA TELEFONO6");
					log.error(telefono6);
					telefonoSdServicio.update(telefono6);
				} else {
					TelefonoSd telefono6 = MapperTelefono(null, null, null, null, null,
							registro.getTelefonos().getTelefono6(), canalSd, existePersona);

					log.error("INGRESA TELEFONO6");
					log.error(telefono6);
					telefonoSdServicio.ingresarTelefono(telefono6);
				}
			}
			log.error("PASA TELEFONO");

			// ACTUALIZA DIRECCION ELECTRONICA 1-2
			List<DireccionElectronicaSd> direccionElectronica = direccionElectronicaSdServicio
					.obtenerDireccionElectronicaByPersonaSecPersona(existePersona.getSecPersona());

			TipoDireccionElectronicaSd tipoDireccionElectronicaSd = new TipoDireccionElectronicaSd();
			tipoDireccionElectronicaSd.setCodTipoDireccionElectronica((short) 1);

			// CREA/ACTUALIZA DIRECCION ELECTRONICA 1
			if (direccionElectronica != null && direccionElectronica.size() >= 1) {
				DireccionElectronicaSd d1 = direccionElectronicaSdServicio
						.findByPk(direccionElectronica.get(0).getSecDireccionElectronica());
				d1.setSecPersona(existePersona);
				d1.setCodTipoDireccionElectronica(tipoDireccionElectronicaSd);
				d1.setSecCanal(canalSd);
				d1.setEstado(EstadoEnum.A.getEstadoChar());
				d1.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
				d1.setTsCreacion(new Date());
				d1.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
				d1.setDireccionElectronica(
						!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico1().trim())
								? registro.getDireccionElectronico().getCorreo_electronico1()
								: null);

				log.error("ACTUALIZA DIRECCION ELECTRONICA 1");
				log.error(d1);
				direccionElectronicaSdServicio.update(d1);
			} else {
				if (!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico1().trim())) {
					DireccionElectronicaSd d1 = MapeoDireccionElectronica(
							registro.getDireccionElectronico().getCorreo_electronico1(), existePersona, canalSd);

					log.error("CREA DIRECCION ELECTRONICA 1");
					direccionElectronicaSdServicio.ingresarDireccionElectronica(d1);
					log.error("GUARDA DIRECCION ELECTRONICA 1");
				}
			}

			// CREA/ACTUALIZA DIRECCION ELECTRONICA 2
			if (direccionElectronica != null && direccionElectronica.size() >= 2) {
				DireccionElectronicaSd d2 = direccionElectronicaSdServicio
						.findByPk(direccionElectronica.get(1).getSecDireccionElectronica());
				d2.setSecPersona(existePersona);
				d2.setCodTipoDireccionElectronica(tipoDireccionElectronicaSd);
				d2.setSecCanal(canalSd);
				d2.setEstado(EstadoEnum.A.getEstadoChar());
				d2.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
				d2.setTsCreacion(new Date());
				d2.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());
				d2.setDireccionElectronica(
						!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico2().trim())
								? registro.getDireccionElectronico().getCorreo_electronico2()
								: null);

				log.error("ACTUALIZA DIRECCION ELECTRONICA 2");
				log.error(d2);
				direccionElectronicaSdServicio.update(d2);
			} else {
				if (!VerificarVacios(registro.getDireccionElectronico().getCorreo_electronico2().trim())) {
					DireccionElectronicaSd d2 = MapeoDireccionElectronica(
							registro.getDireccionElectronico().getCorreo_electronico2(), existePersona, canalSd);

					log.error("CREA DIRECCION ELECTRONICA 2");
					direccionElectronicaSdServicio.ingresarDireccionElectronica(d2);
					log.error("GUARDA DIRECCION ELECTRONICA 2");
				}
			}
			log.error("PASA DIRECCION ELECTRONICA");

			// ACTUALIZA INFORMACION ADCIONAL
			if (existePersona.getPersonaNatural().getSecPersonaNatural() > 0 // para saber que se INSERTO una PN
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
			}

			log.error("PASA INFORMACION ADICIONAL");

			// ACTUALIZA EMPLEOD EPENDIENTE
			if (existePersona.getPersonaNatural() != null
					&& existePersona.getPersonaNatural().getSecPersonaNatural() > 0
					&& existePersona.getPersonaJuridica() != null
					&& existePersona.getPersonaJuridica().getSecPersonaJuridica() > 0) {
				EmpleoDependienteSd empleoDependienteSd = empleoDependienteServicio
						.obtenerEmpleoDependienteBySecPersonaNatural(
								existePersona.getPersonaNatural().getSecPersonaNatural(),
								existePersona.getPersonaJuridica().getSecPersonaJuridica());

				if (empleoDependienteSd != null) {
					empleoDependienteSd.setPersonaNatural(existePersona.getPersonaNatural());
					empleoDependienteSd.setPersonaJuridica(existePersona.getPersonaJuridica());
					empleoDependienteSd.setCargo(registro.getEmpleoDependiente().getCargo());
					empleoDependienteSd.setMntSalario(!VerificarVacios(registro.getEmpleoDependiente().getMntSalario())
							? new BigDecimal(registro.getEmpleoDependiente().getMntSalario())
							: new BigDecimal(0));
					empleoDependienteSd
							.setFchIngreso(ConvertirFecha(registro.getEmpleoDependiente().getFechaIngreso()));
					empleoDependienteSd.setFchSalida(new Date());//
					empleoDependienteSd.setSecCanal(canalSd);
					empleoDependienteSd.setEstado(EstadoEnum.A.getEstadoChar());//
					empleoDependienteSd.setTsCreacion(new Date());
					empleoDependienteSd.setUsrCreacion(UsuarioEnum.USUARIO_CREACION.getValor());
					empleoDependienteSd.setUsrModificacion(UsuarioEnum.USUARIO_MODIFICACION.getValor());

					empleoDependienteServicio.update(empleoDependienteSd);
					log.error("ACTUALIZA EMPLEO DEPENDIENTE");
				} else {
					EmpleoDependienteSd emplDep = MapeoEmpleoDependiente(registro.getEmpleoDependiente(),
							existePersona.getPersonaNatural(), existePersona.getPersonaJuridica(), canalSd);

					empleoDependienteServicio.crearEmpleoDependiente(emplDep);
					log.error("GUARDA EMPLEO DEPENDIENTE");
				}
				log.error("PASA EMPLEO DEPENDIENTE");
			} // FIN IF DE EMPLEO DEPENDINETE

			log.error("FIN PROCESO ACTUALIZACION TITULAR");
		}
		return true;
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

		TipoDireccionSd tipoDireccionSd = new TipoDireccionSd();
		tipoDireccionSd.setCodTipoDireccion(TipoDireccionEnum.DOMICILIO.getCodigoenBase());

		ProvinciaSd provinciaSdDireccion = new ProvinciaSd();
		provinciaSdDireccion.setSecProvincia(
				!VerificarVacios(registro.getSecProvincia()) ? Short.parseShort(registro.getSecProvincia()) : 0);

		CantonSd cantoSdDireccion = new CantonSd();
		cantoSdDireccion.setSecCanton(
				!VerificarVacios(registro.getSecCanton()) ? Short.parseShort(registro.getSecCanton()) : 0);

		ParroquiaSd parroquiaSdDireccion = new ParroquiaSd();
		parroquiaSdDireccion.setSecParroquia(
				!VerificarVacios(registro.getSecParroquia()) ? Short.parseShort(registro.getSecParroquia()) : 0);

		direccionsd.setSecPersona(personaSd);
		direccionsd.setDireccion(registro.getDireccion());
		direccionsd.setCodTipoDireccion(tipoDireccionSd);
		direccionsd.setSecProvincia(provinciaSdDireccion);
		direccionsd.setSecCanton(cantoSdDireccion);
		direccionsd.setSecParroquia(parroquiaSdDireccion);
		direccionsd.setSecCanal(canalSd);
		direccionsd.setEstado(EstadoEnum.A.getEstadoChar());// -----------------------------------------
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
			telefono.setCodArea(telefono1.getCodArea());
			telefono.setNroTelefono(telefono1.getNroTelefono());
			tipoTelefonoSd.setCodTipoTelefono(
					!VerificarVacios(telefono1.getCodTipoTelefono()) ? Short.parseShort(telefono1.getCodTipoTelefono())
							: (short) 0);
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono2 != null) {
			telefono.setCodArea(telefono2.getCodArea());
			telefono.setNroTelefono(telefono2.getNroTelefono());
			tipoTelefonoSd.setCodTipoTelefono(
					!VerificarVacios(telefono2.getCodTipoTelefono()) ? Short.parseShort(telefono2.getCodTipoTelefono())
							: (short) 0);
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono3 != null) {
			telefono.setCodArea(telefono3.getCodArea());
			telefono.setNroTelefono(telefono3.getNroTelefono());
			tipoTelefonoSd.setCodTipoTelefono(
					!VerificarVacios(telefono3.getCodTipoTelefono()) ? Short.parseShort(telefono3.getCodTipoTelefono())
							: (short) 0);
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono4 != null) {
			telefono.setCodArea(telefono4.getCodArea());
			telefono.setNroTelefono(telefono4.getNroTelefono());
			tipoTelefonoSd.setCodTipoTelefono(
					!VerificarVacios(telefono4.getCodTipoTelefono()) ? Short.parseShort(telefono4.getCodTipoTelefono())
							: (short) 0);
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono5 != null) {
			telefono.setCodArea(telefono5.getCodArea());
			telefono.setNroTelefono(telefono5.getNroTelefono());
			tipoTelefonoSd.setCodTipoTelefono(
					!VerificarVacios(telefono5.getCodTipoTelefono()) ? Short.parseShort(telefono5.getCodTipoTelefono())
							: (short) 0);
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		} else if (telefono6 != null) {
			telefono.setCodArea(telefono6.getCodArea());
			telefono.setNroTelefono(telefono6.getNroTelefono());
			tipoTelefonoSd.setCodTipoTelefono(
					!VerificarVacios(telefono6.getCodTipoTelefono()) ? Short.parseShort(telefono6.getCodTipoTelefono())
							: (short) 0);
			telefono.setCodTipoTelefono(tipoTelefonoSd);
		}

		return telefono;
	}

	public InformacionAdicionalSd MapperInformacionAdicional(InformacionAdicional registro,
			PersonaNaturalSd personaNatural, CanalSd canalSd) {
		InformacionAdicionalSd informacionAdicionalSd = new InformacionAdicionalSd();

		ProvinciaSd provinciaSdInfoAdd = new ProvinciaSd();
		provinciaSdInfoAdd.setSecProvincia(
				!VerificarVacios(registro.getSecProvincia()) ? Short.parseShort(registro.getSecProvincia().trim()) : 0);

		CantonSd cantonSdInfoAdd = new CantonSd();
		cantonSdInfoAdd.setSecCanton(
				!VerificarVacios(registro.getSecCanton()) ? Short.parseShort(registro.getSecCanton().trim()) : 0);

		ParroquiaSd parroquiaSdInfoAdd = new ParroquiaSd();
		parroquiaSdInfoAdd.setSecParroquia(
				!VerificarVacios(registro.getSecParroquia()) ? Short.parseShort(registro.getSecParroquia().trim()) : 0);

		ActividadEconomicaSd actividadEconomicaSd = new ActividadEconomicaSd();
		actividadEconomicaSd.setCodActividadEconomica(!VerificarVacios(registro.getCodActividadEconomica().trim())
				? Short.parseShort(registro.getCodActividadEconomica())
				: 1);

		TipoIdentificacionSd tipoIdentificacionInfoAdd = new TipoIdentificacionSd();
		tipoIdentificacionInfoAdd.setCodTipoIdentificacion(registro.getCodTipoIdentificacion().charAt(0));

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
		informacionAdicionalSd.setEMail("jjj");// -------------------------------------------------------
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

}
