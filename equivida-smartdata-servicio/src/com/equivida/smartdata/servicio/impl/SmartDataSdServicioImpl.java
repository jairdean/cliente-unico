package com.equivida.smartdata.servicio.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
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
import com.equivida.smartdata.constante.PropiedadesKeyEnum;
import com.equivida.smartdata.constante.TipoParentescoEnum;
import com.equivida.smartdata.dao.NoPkTablasSdDao;
import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.exception.FindException;
import com.equivida.smartdata.exception.SmartdataException;
import com.equivida.smartdata.helper.DataBookHelper;
import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.model.RelacionSd;
import com.equivida.smartdata.model.TelefonoSd;
import com.equivida.smartdata.model.TipoIdentificacionSd;
import com.equivida.smartdata.model.TipoParentescoRelacionSd;
import com.equivida.smartdata.servicio.ActividadEconomicaSdServicio;
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
		persona.setIdentificacion(""+pn.getSecPersona());
		
		return persona;
		
		// 1. Se busca Cliente en las tablas del esquema SMARTDATA
		/*PersonaNaturalSd pn = personaNaturalServicio.obtenerPersonaByIdentificacion(identificacion, conRelaciones);

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
		
		return persona;*/
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
			System.out.println("===========================consulta en DB: " + identificacion + new Date());
			// Se consulta en el WS.
			Registros registros = obtenerRegistrosWs(identificacion);
			System.out.println("===========================FIN consulta en DB: " + identificacion + new Date());
			
			
			PersonaSd personaSd = new PersonaSd();
			personaSd.setIdentificacion(identificacion);
			personaSd.setDenominacion("Ejemplo");
			
			TipoIdentificacionSd tipoIdentificacion = new TipoIdentificacionSd();
			tipoIdentificacion.setCodTipoIdentificacion('C');
			personaSd.setCodTipoIdentificacion(tipoIdentificacion);

						
			//INGRESO LA PERSONA
			boolean resp = personaServicio.IngresarDatosPersona(personaSd);
			

			/*
			System.out.println(
					"===========================transforma DB a SD consulta en DB: " + identificacion + new Date());
			DataBookHelper dbh = new DataBookHelper(registros, usuario, noPkTablasDao, estadoCivilServicio,
					profesionServicio, actividadEconomicaServicio, personaJuridicaServicio, personaServicio,
					direccionSdServicio, telefonoSdServicio);
			PersonaSd personaSd = dbh.getPersonaNatural();
			// 4. Se debe crear las relaciones con conyuge, padre, madre
			crearRelacionesPersonales(registros, personaSd, usuario);
			// 5. Obtiene o Crea Personas juridicas
			List<PersonaSd> listaPj = dbh.getPersonasJuridicas();
			// 6. Crea relaciones laborales en el caso de existir personas
			// juridicas
			if (listaPj != null && !listaPj.isEmpty()) {
				try {
					relacionarPersonaNaturalPersonaJuridica(personaSd, listaPj, registros, usuario);
				} catch (ParseException e) {
					log.error(e.getMessage(), e.getCause());
				}
			}
			System.out.println(
					"===========================FIN transforma DB a SD consulta en DB: " + identificacion + new Date());
			// Se pone esta linea para que se pueda presentar la informacion en
			// xml soap de respuesta
			activarPresentacionParaWs(personaSd);*/
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
				
		System.out.println("XX3-FIN");
		// Se consulta en el WS de Databook
		Registros registros = dbs.consultaDatabook();
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
}
