package com.equivida.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Canton;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.EmpleoDependiente;
import com.equivida.modelo.EmpleoIndependiente;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Profesion;
import com.equivida.modelo.Provincia;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoDireccion;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.CantonServicio;
import com.equivida.servicio.PaisServicio;
import com.equivida.smartdata.constante.CodigoAsumidoEnum;
import com.equivida.smartdata.constante.EstadoEnum;
import com.equivida.smartdata.constante.TipoIdentificacionSDEnum;
import com.equivida.smartdata.model.CanalSd;
import com.equivida.smartdata.model.CantonSd;
import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.model.DireccionTelefonoSd;
import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.equivida.smartdata.model.EstadoCivilSd;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.equivida.smartdata.model.PaisSd;
import com.equivida.smartdata.model.ParroquiaSd;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.model.ProfesionSd;
import com.equivida.smartdata.model.ProvinciaSd;
import com.equivida.smartdata.model.RelacionSd;
import com.equivida.smartdata.model.TelefonoSd;
import com.equivida.smartdata.model.TipoDireccionSd;
import com.equivida.smartdata.model.TipoIdentificacionSd;
import com.equivida.smartdata.model.TipoTelefonoSd;

/**
 * Utilitario para transformar objetos de SD en CU y viceversa.
 * 
 * @author juan
 *
 */
public class TransformSdCuUtil implements Serializable {

	private static final long serialVersionUID = -8875216680550229613L;

	private CantonServicio cantonServicio;
	private PaisServicio paisServicio;

	/**
	 * Transforma de persona SD a persona natural de CU.
	 * 
	 * @param pSd
	 * @param usuario
	 * @return
	 */
	public PersonaNatural transformarPersonaSdEnPersonaNaturalCU(PersonaSd pSd, String usuario,
			CantonServicio cantonServicio, PaisServicio paisServicio) {
		this.cantonServicio = cantonServicio;
		this.paisServicio = paisServicio;

		// Se crea persona de CU
		PersonaNatural pn = armarPersonaNaturalCu(pSd, usuario);

		return pn;
	}

	/**
	 * Transforma de persona SD a persona natural de CU.
	 * 
	 * @param pSd
	 * @param usuario
	 * @return
	 */
	private PersonaNatural armarPersonaNaturalCu(PersonaSd pSd, String usuario) {
		PersonaNaturalSd pnsd = pSd.getPersonaNatural();

		// 1. Se arma la persona
		Persona personaCu = armarPersonaCu(pSd, usuario);

		// 2. Se arma la persona natural
		PersonaNatural pn = new PersonaNatural();

		pn.setPersona(personaCu);

		pn.setIdentificacion(pnsd.getIdentificacion());
		pn.setTipoIdentificacion(getTipoIdentificacionCedulaCu());
		pn.setApellidoPaterno(pnsd.getApellidoPaterno());
		pn.setApellidoMaterno(pnsd.getApellidoMaterno());
		pn.setPrimerNombre(pnsd.getPrimerNombre());
		pn.setSegundoNombre(pnsd.getSegundoNombre());
		pn.setSexo(pnsd.getSexo());
		pn.setFchNacimiento(pnsd.getFchNacimiento());
		pn.setFchFallecimiento(pnsd.getFchFallecimiento());
		pn.setPaisNacionalidad(getPaisNacimientoCu(pnsd.getCodPais().getCodPais(), pnsd.getCodPais().getPais()));
		pn.setEstadoCivil(getEstadoCivilCu(pnsd.getCodEstadoCivil().getCodEstadoCivil()));

		// Se llenan los datos del conyuge de existir
		if (pSd.getConyuge() != null) {
			RelacionSd conyugeSd = pSd.getConyuge();
			conyugeSd.setUsrCreacion(usuario);
			conyugeSd.setUsrModificacion(usuario);

			// Se pone los datos del conyuge
			PersonaNatural conyugeCu = new PersonaNatural();

			conyugeCu.setIdentificacion(conyugeSd.getPersonaR().getIdentificacion());
			conyugeCu.setEstadoCivil(getEstadoCivilCu(pnsd.getCodEstadoCivil().getCodEstadoCivil()));

			if (conyugeSd.getPersonaR().getPersonaNatural() != null) {
				conyugeCu.setApellidoPaterno(conyugeSd.getPersonaR().getPersonaNatural().getApellidoPaterno());
				conyugeCu.setApellidoMaterno(conyugeSd.getPersonaR().getPersonaNatural().getApellidoMaterno());
				conyugeCu.setPrimerNombre(conyugeSd.getPersonaR().getPersonaNatural().getPrimerNombre());
				conyugeCu.setSegundoNombre(conyugeSd.getPersonaR().getPersonaNatural().getSegundoNombre());
				conyugeCu.setConyugeCompleto(true);

				conyugeCu.setNombresApellidosConyugeIncompleto(conyugeSd.getPersonaR().getDenominacion());
			} else {
				conyugeCu.setNombresApellidosConyugeIncompleto(conyugeSd.getPersonaR().getDenominacion());
				conyugeCu.setConyugeCompleto(false);
			}

			pn.setTieneConyuge(true);
			pn.setConyuge(conyugeCu);
		} else {
			pn.setConyugeCompleto(false);
			pn.setTieneConyuge(false);
		}

		// Se llenan los datos de la Direccion (Ubicacion Geográfica de existir)
		if (pSd.getTodasDirecciones() != null && !pSd.getTodasDirecciones().isEmpty()) {

			// Se inicializa la coleccion
			if (personaCu.getDireccionCollection() == null) {
				personaCu.setDireccionCollection(new ArrayList<Direccion>());
			}

			for (DireccionSd ds : pSd.getTodasDirecciones()) {
				if (ds.getSecCanton() != null && ds.getSecCanton().getSecCanton() != null) {

					Direccion direccionCu = new Direccion();
					direccionCu.setPersona(personaCu);
					direccionCu.setDireccionCompleta(ds.getDireccion());
					direccionCu.setCanton(getCantonCu(ds.getSecCanton().getSecCanton()));
					direccionCu.setTipoDireccion(getTipoDireccionCu(ds.getCodTipoDireccion().getCodTipoDireccion()));
					direccionCu.setParroquia(ds.getSecParroquia().getParroquia());
					direccionCu.setTieneDireccionCompleta(false);
					direccionCu.setEnvioCorrespondencia('N');
					direccionCu.setEstado(EstadoEnum.A.getEstadoChar());
					direccionCu.setLatitud(BigDecimal.ZERO);
					direccionCu.setLongitud(BigDecimal.ZERO);
					direccionCu.setVerificada('N');

					if (ds.isDireccionCompleta()) {
						direccionCu.setPrincipal(ds.getPrincipalTr());
						direccionCu.setNumero(ds.getNumeroTr());
						direccionCu.setSecundaria(ds.getSecundariaTr());
						direccionCu.setTieneDireccionCompleta(true);
					} else {
						direccionCu.setPrincipal(ds.getDireccion());
					}

					// Se ponen lso telefonos
					direccionCu.setDireccionTelefonoCollection(new ArrayList<DireccionTelefono>());
					if (ds.getDireccionTelefonoList() != null && !ds.getDireccionTelefonoList().isEmpty()) {
						for (DireccionTelefonoSd dtSd : ds.getDireccionTelefonoList()) {
							// Revisar si ya esta el telefono en la direccion.
							boolean telefonoRegistrado = verificarTelefonoEnLista(dtSd.getTelefono().getNroTelefono(),
									direccionCu.getDireccionTelefonoCollection());

							if (!telefonoRegistrado) {
								TelefonoSd t = dtSd.getTelefono();

								// Se crea el telefono
								Telefono telefonoCu = new Telefono();
								telefonoCu.setCodArea(t.getCodArea());
								telefonoCu.setEstado(EstadoEnum.A.getEstadoChar());
								telefonoCu.setNroTelefono(t.getNroTelefono());
								telefonoCu.setPersona(personaCu);
								telefonoCu.setTipoTelefono(
										getCodigotipoTelefonoCu(t.getCodTipoTelefono().getCodTipoTelefono()));
								telefonoCu.setPais(new Pais());

								// Se crea la direccion telefono
								DireccionTelefono dt = new DireccionTelefono();
								dt.setPersona(personaCu);
								dt.setDireccion(direccionCu);
								dt.setTelefono(telefonoCu);

								// Se pone la direccion telefono en la direccion
								direccionCu.getDireccionTelefonoCollection().add(dt);
							}
						}
					}

					// Se pone la direccion en la coleccion y se pone la
					// coleccion
					// en la
					// persona de Cliente Unico
					personaCu.getDireccionCollection().add(direccionCu);
				}
			}
		}

		// Se llenan los datos de contactabilidad Telefonos si los hay
		// Ahora todos los telefonos se atan a una direccion
		/*
		 * if (pSd.getTodosTelefonos() != null && !pSd.getTodosTelefonos().isEmpty()) {
		 * Collection<Telefono> telefonoCollection = new ArrayList<Telefono>();
		 * 
		 * for (TelefonoSd t : pSd.getTodosTelefonos()) { Telefono telefonoCu = new
		 * Telefono(); telefonoCu.setCodArea(t.getCodArea());
		 * telefonoCu.setEstado(t.getEstado());
		 * telefonoCu.setNroTelefono(t.getNroTelefono());
		 * telefonoCu.setPersona(personaCu);
		 * telefonoCu.setTipoTelefono(getCodigotipoTelefonoCu(t
		 * .getCodTipoTelefono().getCodTipoTelefono())); telefonoCu.setPais(new Pais());
		 * 
		 * telefonoCollection.add(telefonoCu); }
		 * 
		 * personaCu.setTelefonoSinDireccionCollection(telefonoCollection); }
		 */

		// Se llena los datos de empleo dependiente
		if (pnsd.getEmpleoDependienteList() != null && !pnsd.getEmpleoDependienteList().isEmpty()) {

			Collection<EmpleoDependiente> empleoLista = new ArrayList<EmpleoDependiente>();

			for (EmpleoDependienteSd eSd : pnsd.getEmpleoDependienteList()) {
				EmpleoDependiente edCu = new EmpleoDependiente();

				edCu.setCodTiempo(crearCodigoTiempoAniosCu());
				edCu.setNegocioEmpresa(eSd.getPersonaJuridica().getRazonSocial());
				edCu.setCargo(eSd.getCargo());
				edCu.setEstado(eSd.getEstado());
				edCu.setPersonaNatural(pn);
				edCu.setSalarioTr(eSd.getMntSalario());
				edCu.setTiempoEmpresa(calcularTiempoenEmpresa(eSd.getFchIngreso(), eSd.getFchSalida()));
				// Se pone actividad economica por defecto (0): sin actividad
				edCu.setActividadEconomica(crearActividadEconomicaPorDefecto());

				empleoLista.add(edCu);
			}

			pn.setEmpleoDependienteCollection(empleoLista);
		}

		// Se llenan los datos de empleo independiente, se toman de la tabla
		// informacion adicional
		if (pnsd.getInformacionAdicionalList() != null && !pnsd.getInformacionAdicionalList().isEmpty()) {
			InformacionAdicionalSd ia = pnsd.getInformacionAdicionalList().get(0);

			if (ia.getCodActividadEconomica() != null && ia.getFchInicioActividades() != null) {
				EmpleoIndependiente eiCu = new EmpleoIndependiente();
				eiCu.setPersonaNatural(pn);
				eiCu.setCargo("EMPLEO INDEPENDIENTE");
				if (ia.getRazonSocial() != null && ia.getRazonSocial().trim().length() > 0) {
					eiCu.setRazonSocialTr(ia.getRazonSocial());
				} else {
					eiCu.setRazonSocialTr("INDEPENDIENTE");
				}
				eiCu.setEstado('A');
				eiCu.setCodTiempo(crearCodigoTiempoAniosCu());
				eiCu.setTiempoEmpresa(calcularTiempoenEmpresa(ia.getFchInicioActividades(), ia.getFchSuspension()));
				eiCu.setNombreActividadEconomicaTr(ia.getCodActividadEconomica().getActividadEconomicaSri());

				// Se crea la lista de empleo independiente
				Collection<EmpleoIndependiente> empleoIndependienteLista = new ArrayList<EmpleoIndependiente>();
				empleoIndependienteLista.add(eiCu);

				// Se pone la lista en la persona natural de CU
				pn.setEmpleoIndependienteCollection(empleoIndependienteLista);
			}
		}

		return pn;
	}

	/**
	 * Verifica si el telefono ya esta en la lista de telefonos de la direccion.
	 * 
	 * @param telefono
	 * @param direccionTelefonoLista
	 * @return
	 */
	private boolean verificarTelefonoEnLista(String telefono, Collection<DireccionTelefono> direccionTelefonoLista) {
		boolean resp = false;

		if (direccionTelefonoLista != null && !direccionTelefonoLista.isEmpty()) {
			for (DireccionTelefono dt : direccionTelefonoLista) {
				if (dt.getTelefono().getNroTelefono().equals(telefono)) {
					resp = true;
					break;
				}
			}
		}

		return resp;
	}

	/**
	 * Crea actividad economica por defecto (0): Sin actividad
	 * 
	 * @return
	 */
	private ActividadEconomica crearActividadEconomicaPorDefecto() {
		return new ActividadEconomica(Short.valueOf("0"));
	}

	/**
	 * Crea el codigo de tiempo anios para CU.
	 * 
	 * @return
	 */
	private char crearCodigoTiempoAniosCu() {
		return new Character('A');
	}

	/**
	 * Calcula los anios que una persona trabaja.
	 * 
	 * @param fechaIngreso
	 * @param fechaSalida
	 * @return
	 */
	private BigDecimal calcularTiempoenEmpresa(Date fechaIngreso, Date fechaSalida) {

		// Si la fecha de salida es nula, se pone la fecha actual pporque el
		// trabajo es el actual
		if (fechaSalida == null) {
			fechaSalida = new Date();
		}

		Calendar fechaDesde = Calendar.getInstance();
		fechaDesde.setTime(fechaIngreso);

		Calendar fechaHasta = Calendar.getInstance();
		fechaHasta.setTime(fechaSalida);

		int anios = fechaHasta.get(Calendar.YEAR) - fechaDesde.get(Calendar.YEAR);

		return new BigDecimal(anios);
	}

	/**
	 * Devuelve el tipo de telefono de CU.
	 * 
	 * @param codTipoTelefono
	 * @return
	 */
	private TipoTelefono getCodigotipoTelefonoCu(Short codTipoTelefono) {
		return new TipoTelefono(codTipoTelefono);
	}

	/**
	 * Devuelve el tipo de direccion de CU.
	 * 
	 * @param codTipoDireccion
	 * @return
	 */
	private TipoDireccion getTipoDireccionCu(Short codTipoDireccion) {
		return new TipoDireccion(codTipoDireccion);
	}

	/**
	 * Devuelve el Canton de CU.
	 * 
	 * @param secCanton
	 * @return
	 */
	private Canton getCantonCu(Short secCanton) {
		Canton c = cantonServicio.findByPk(secCanton);
		if (c != null) {
			return c;
		} else {
			return new Canton(secCanton);
		}
	}

	/**
	 * Devuelve estaado civil de CU.
	 * 
	 * @param codEstadoCivil
	 * @return
	 */
	private EstadoCivil getEstadoCivilCu(Short codEstadoCivil) {
		return new EstadoCivil(codEstadoCivil);
	}

	/**
	 * Devuelve Pais de CU.
	 * 
	 * @param codigpPais
	 * @return
	 */
	private Pais getPaisNacimientoCu(Short codigpPais, String nombrePais) {
		Pais pais = paisServicio.findByPk(codigpPais);

		if (pais != null) {
			return pais;
		} else {
			return new Pais();
		}
	}

	/**
	 * Armar la persona de Cliente Unico.
	 * 
	 * @param psd
	 * @return
	 */
	private Persona armarPersonaCu(PersonaSd pSd, String usuario) {
		Persona p = new Persona();

		p.setDenominacion(pSd.getDenominacion());
		p.setTipoIdentificacion(getTipoIdentificacionCedulaCu());
		p.setUsrCreacion(usuario);
		p.setUsrModificacion(usuario);

		return p;
	}

	/**
	 * Transforma PersonaNatural de CU en PersonaSd.
	 * 
	 * @param pn
	 * @return
	 */
	public PersonaSd transformarPersonaNaturalCuEnPersonaSd(PersonaNatural pn, String usuario) {
		// Se crea la persona en SmartData

		System.out.println("ARMA SMARTDATA...");

		PersonaSd pSd = armarPersonaSd(pn, usuario);

		// Se crea la persona natural en SmartData
		armarPersonaNatural(pSd, pn, usuario);

		return pSd;
	}

	/**
	 * Arma la persona natural.
	 * 
	 * @param persona
	 * @param r
	 * @param usuario
	 */
	private void armarPersonaNatural(PersonaSd persona, PersonaNatural pn, String usuario) {
		PersonaNaturalSd pnSd = new PersonaNaturalSd();

		// Persona
		pnSd.setSecPersona(persona);

		pnSd.setApellidoMaterno(pn.getApellidoMaterno());
		pnSd.setApellidoPaterno(pn.getApellidoPaterno());
		pnSd.setCodEstadoCivil(armarEstadoCivilSd(pn.getEstadoCivil()));
		pnSd.setCodPais(armarPaisSd(pn.getPaisNacionalidad()));
		pnSd.setCodTipoIdentificacion(getTipoIdentificacionCedulaSd());
		pnSd.setFchFallecimiento(pn.getFchFallecimiento());
		// pnSd.setFchMatrimonio(); //FIXME: Revisar de donde se obtiene esto
		pnSd.setFchNacimiento(pn.getFchNacimiento());
		pnSd.setIdentificacion(pn.getIdentificacion());
		pnSd.setPrimerNombre(pn.getPrimerNombre());
		pnSd.setSecCanal(getCanal());
		pnSd.setSecProfesion(armarProfesionSd());
		pnSd.setSegundoNombre(pn.getSegundoNombre());
		pnSd.setSexo(pn.getSexo());
		pnSd.setUsrCreacion(usuario);
		pnSd.setUsrModificacion(usuario);

		// Se pone la persona natural en persona
		persona.setPersonaNatural(pnSd);

		// Se debe poner los datos del SRI en CU Profesión tipo de empleo
		// actividad económica antigüedad
		// FIXME: La información adicioal de CU es distinta a la
		// informacionAdicional de SD
		// pnSd.setInformacionAdicionalList();

		// FIXME: Preguntar como se pasa esta info si no existe el dato de la
		// persona juridica en CU
		// pnSd.setEmpleoDependienteList();
	}

	/**
	 * Armar la profesion para SD.
	 * 
	 * @param p
	 * @return
	 */
	private ProfesionSd armarProfesionSd(Profesion p) {
		ProfesionSd resp = armarProfesionSd();

		// FIXME: Se comenta hasta definir de donde se oftiene el codigo de
		// profesion
		/*
		 * if (p != null) { resp = new ProfesionSd();
		 * resp.setSecProfesion(p.getSecProfesion());
		 * resp.setProfesionRc(p.getProfesion()); } else { resp = armarProfesionSd(); }
		 */

		return resp;
	}

	/**
	 * Arma una profesion asumida con el codigo 2327.
	 * 
	 * @return
	 */
	private ProfesionSd armarProfesionSd() {
		return new ProfesionSd(CodigoAsumidoEnum.PROFESION.getCodigoSd());
	}

	/**
	 * Armar pais de SmartData.
	 * 
	 * @param p
	 * @return
	 */
	private PaisSd armarPaisSd(Pais p) {
		PaisSd resp = null;

		if (p != null) {
			resp = new PaisSd();
			resp.setCodPais(p.getCodPais());
			resp.setPais(p.getPais());
		}

		return resp;
	}

	/**
	 * Arma estado civil para SmartData.
	 * 
	 * @param ec
	 * @return
	 */
	private EstadoCivilSd armarEstadoCivilSd(EstadoCivil ec) {
		EstadoCivilSd resp = null;

		if (ec != null) {
			resp = new EstadoCivilSd();
			resp.setCodEstadoCivil(ec.getCodEstadoCivil());
			resp.setEstadoCivil(ec.getEstadoCivil());
		}

		return resp;
	}

	/**
	 * Arma persona SmartData.
	 * 
	 * @param pn
	 * @return
	 */
	private PersonaSd armarPersonaSd(PersonaNatural pn, String usuario) {
		PersonaSd p = new PersonaSd();

		p.setCodTipoIdentificacion(getTipoIdentificacionCedulaSd());
		p.setDenominacion(pn.getNombresApellidos());
		p.setIdentificacion(pn.getIdentificacion());
		p.setTelefonoList(getTelefonos(p, pn, usuario));
		p.setDireccionList(armarDireccionSd(pn.getPersona(), p, usuario));

		return p;
	}

	/**
	 * Arma la direccion de la persona.
	 * 
	 * @param persona
	 * @return
	 */
	private List<DireccionSd> armarDireccionSd(Persona persona, PersonaSd personaSd, String usuario) {
		List<DireccionSd> resp = new ArrayList<DireccionSd>();

		if (persona.getDireccionActivosCollection() != null && !persona.getDireccionActivosCollection().isEmpty()) {

			// Se toma la direccion principal
			for (Direccion d : persona.getDireccionActivosCollection()) {
				DireccionSd ds = new DireccionSd();
				ds.setCodTipoDireccion(armarTipoDireccionSd(d.getTipoDireccion()));
				ds.setDireccion(d.getDireccionParaSise());
				ds.setEstado(d.getEstado());
				ds.setSecCanal(getCanal());
				ds.setSecCanton(armarCantonSd(d.getCanton()));
				ds.setSecParroquia(getParroquiaQuemadaSd()); // Parroquia 0 en
																// base a
																// provincia
																// y canton
				ds.setSecPersona(personaSd);
				if (d.getCanton() != null) {
					ds.setSecProvincia(armarProvinciaSd(d.getCanton().getProvincia()));
				}
				ds.setUsrCreacion(usuario);
				ds.setUsrModificacion(usuario);

				// Se pone en el listado de respuesta
				resp.add(ds);
			}
		}

		if (resp.isEmpty()) {
			resp = null;
		}

		return resp;
	}

	/**
	 * Obtiene parroquia 0 en base a pronvicia y canton TODO: ver los codigos en la
	 * base.
	 * 
	 * @return
	 */
	private ParroquiaSd getParroquiaQuemadaSd() {
		return new ParroquiaSd(new Short("0"));
	}

	/**
	 * Arma el tipo de direccion en SmartData.
	 * 
	 * @param tipoCu
	 * @return
	 */
	private TipoDireccionSd armarTipoDireccionSd(TipoDireccion tipoCu) {
		TipoDireccionSd resp = new TipoDireccionSd();

		resp.setCodTipoDireccion(tipoCu.getCodTipoDireccion());
		resp.setTipoDireccion(tipoCu.getTipoDireccion());

		return resp;
	}

	/**
	 * Arma la provincia en smartData.
	 * 
	 * @param provinciaCu
	 * @return
	 */
	private ProvinciaSd armarProvinciaSd(Provincia provinciaCu) {
		ProvinciaSd resp = null;

		if (provinciaCu != null) {
			resp = new ProvinciaSd();
			resp.setSecProvincia(provinciaCu.getSecProvincia());
		}

		return resp;
	}

	/**
	 * Arma la provincia en smartData.
	 * 
	 * @param provinciaCu
	 * @return
	 */
	private CantonSd armarCantonSd(Canton cantonCu) {
		CantonSd resp = null;

		if (cantonCu != null) {
			resp = new CantonSd();
			resp.setSecCanton(cantonCu.getSecCanton());
		}

		return resp;
	}

	/**
	 * Se pasan los telefonos.
	 * 
	 * @param p
	 * @param pn
	 * @param usuario
	 * @return
	 */
	private List<TelefonoSd> getTelefonos(PersonaSd p, PersonaNatural pn, String usuario) {
		List<TelefonoSd> resp = null;

		if (pn.getPersona() != null && pn.getPersona().getTelefonoCollection() != null
				&& !pn.getPersona().getTelefonoCollection().isEmpty()) {

			resp = new ArrayList<TelefonoSd>();

			for (Telefono t : pn.getPersona().getTelefonoCollection()) {
				TelefonoSd telefonoSd = new TelefonoSd();
				telefonoSd.setCodArea(t.getCodArea());
				telefonoSd.setCodTipoTelefono(armarTipoTelefonoSd(t.getTipoTelefono()));
				telefonoSd.setEstado(t.getEstado());
				telefonoSd.setNroTelefono(t.getNroTelefono());
				telefonoSd.setSecCanal(getCanal());
				telefonoSd.setSecPersona(p);
				telefonoSd.setUsrCreacion(usuario);
				telefonoSd.setUsrModificacion(usuario);

				// Se pone telefono en la lista
				resp.add(telefonoSd);
			}
		}

		return resp;
	}

	/**
	 * btiene Canal. TODO: Se asume que es WEB
	 * 
	 * @return
	 */
	private CanalSd getCanal() {
		return new CanalSd(new Short("3"));
	}

	/**
	 * Arma el tipo de telefono para SmartData.
	 * 
	 * @param tipoTelefonoCu
	 * @return
	 */
	private TipoTelefonoSd armarTipoTelefonoSd(TipoTelefono tipoTelefonoCu) {
		TipoTelefonoSd resp = new TipoTelefonoSd();

		resp.setCodTipoTelefono(tipoTelefonoCu.getCodTipoTelefono());
		resp.setTipoTelefono(tipoTelefonoCu.getTipoTelefono());

		return resp;
	}

	/**
	 * Obtiene tipo de identificacion.
	 * 
	 * @return
	 */
	private TipoIdentificacionSd getTipoIdentificacionCedulaSd() {
		return new TipoIdentificacionSd(TipoIdentificacionSDEnum.C.toString().charAt(0));
	}

	/**
	 * Obtiene tipo de identificacion en CU
	 * 
	 * @return
	 */
	private TipoIdentificacion getTipoIdentificacionCedulaCu() {
		return new TipoIdentificacion(TipoIdentificacionSDEnum.C.toString().charAt(0));
	}
}
