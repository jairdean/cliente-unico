/**
 * 
 */
package com.equivida.helper;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoCivilEnum;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.LugarEncuentraInfoEnum;
import com.equivida.constant.TipoEmpleoEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.constant.TipoPersonaEnum;
import com.equivida.constant.TipoTelefonoEqvEnum;
import com.equivida.dto.ContratanteListaDto;
import com.equivida.dto.RespuestaGeneralDto;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Beneficiario;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.ContactoPreferido;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.EmpleoDependiente;
import com.equivida.modelo.EmpleoIndependiente;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.IngresoMensual;
import com.equivida.modelo.IngresoMensualAdicional;
import com.equivida.modelo.Ocupacion;
import com.equivida.modelo.Pais;
import com.equivida.modelo.PerfilFinancieroJuridica;
import com.equivida.modelo.PerfilFinancieroNatural;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.PersonaPe;
import com.equivida.modelo.Profesion;
import com.equivida.modelo.Relacion;
import com.equivida.modelo.RucPersonaNatural;
import com.equivida.modelo.SectorMercado;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoDireccionElectronica;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoPersonaJuridica;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.DireccionElectronicaServicio;
import com.equivida.servicio.PerfilFinancieroNaturalServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.TipoIdentificacionServicio;

/**
 * @author juan
 *
 */
public class ContratanteHelper {

	/**
	 * Los datos del arreglo son:
	 * 
	 * 0 - ID (PErsona natural o Persona Juridica) 1 - identificacion - Para PN y PJ
	 * 2 - APELLIDO_PATERNO - Para PN 3 - APELLIDO_MATERNO - Para PN 4 -
	 * PRIMER_NOMBRE - Para PN 5 - SEGUNDO_NOMBRE - Para PN 6 - RAZON_SOCIAL - Para
	 * Persona juridica
	 * 
	 * @param arr
	 */
	public static ContratanteListaDto armarDtoListado(Object[] arr) {
		ContratanteListaDto dto = new ContratanteListaDto();

		// La posicion 0 es elrownum.

		dto.setId((Integer) arr[1]);
		dto.setIdentificacion(arr[2].toString());

		if (arr[7].toString().equals("NAT")) {
			dto.setTipo(TipoPersonaEnum.N);
			dto.setApellidoPaterno((String) arr[3]);
			dto.setApellidoMaterno((String) arr[4]);
			dto.setPrimerNombre((String) arr[5]);
			dto.setSegundoNombre((String) arr[6]);
		} else {
			dto.setTipo(TipoPersonaEnum.J);
			dto.setRazonSocial(arr[3].toString());
		}

		return dto;
	}

	/**
	 * Se inicializa un telefono para una direccion de una persona.
	 * 
	 * @param direccionTelefono
	 * @param persona
	 * @param direccion
	 * @param paisTelefono
	 */
	public static void crearDireccionTelefono(DireccionTelefono direccionTelefono, Persona persona, Direccion direccion,
			Pais paisTelefono) {

		// Se crea el objeto telefono
		Telefono telefono = new Telefono();
		telefono.setPersona(persona);
		telefono.setTipoTelefono(new TipoTelefono());
		telefono.setPais(paisTelefono);
		telefono.setCodArea(direccion.getCanton().getProvincia().getCodArea());
		telefono.setEstado(EstadoEnum.ACTIVO.getCodigo());
		telefono.setPrincipal(false);

		// Se pone datos en DireccionTelefono
		direccionTelefono.setTelefono(telefono);
		direccionTelefono.setPersona(persona);
		direccionTelefono.setDireccion(direccion);

		// Se pone DireccionTelefono en listado de la direccion
		if (direccion.getDireccionTelefonoCollection() == null) {
			direccion.setDireccionTelefonoCollection(new ArrayList<DireccionTelefono>());
		}

		direccion.getDireccionTelefonoCollection().add(direccionTelefono);
	}

	public static void inicializarPersonaNatural(PersonaNatural personaNatural, String identificacion,
			TipoIdentificacionServicio tipoIdentificacionServicio, TipoIdentificacionEnum tipoIdentificacionEnum) {
		// Se obtiene tipoidentificacion tipo RUC
		TipoIdentificacion tipoId = tipoIdentificacionServicio.findByPk(tipoIdentificacionEnum.getCodigo());// TipoIdentificacionEnum.RUC.getCodigo()

		// Se crea objeto para almacenar los datos del conyuge
		PersonaNatural pnConyuge = new PersonaNatural();
		inicializaPersonaNaturalRepLegal(pnConyuge);

		Persona persona = new Persona();
		persona.setDireccionCollection(new ArrayList<Direccion>());
		persona.setPersonaNaturalTransient(personaNatural);
		persona.setTipoIdentificacion(tipoId);
		identificarCelular(persona);
		identificarCorreosElectronicosSolos(persona);

		IngresoMensual im = new IngresoMensual();
		im.setMntEgresoMensual(BigDecimal.ZERO);
		im.setPersonaNatural(personaNatural);

		IngresoMensualAdicional ima = new IngresoMensualAdicional();
		ima.setMntEgresoAdicional(BigDecimal.ZERO);
		ima.setPersonaNatural(personaNatural);

		personaNatural.setIdentificacion(identificacion);
		personaNatural.setConyuge(pnConyuge);
		personaNatural.setPersona(persona);
		personaNatural.setTipoIdentificacion(new TipoIdentificacion());
		personaNatural.setCiudadNacimiento(new Ciudad());
		personaNatural.setDireccionList(new ArrayList<Direccion>());
		personaNatural.setEstadoCivil(new EstadoCivil());
		personaNatural.setPaisNacionalidad(new Pais());
		personaNatural.setProfesion(new Profesion());
		personaNatural.setOcupacion(new Ocupacion());
		personaNatural.setPerfilFinancieroNatural(new PerfilFinancieroNatural());
		personaNatural.setIngresoMensual(im);
		personaNatural.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());
		personaNatural.setIngresoMensualAdicionalTra(ima);

		// Empleo DEpendiente
		EmpleoDependiente ed = new EmpleoDependiente();
		ed.setActividadEconomica(new ActividadEconomica());
		ed.setEstado(EstadoEnum.ACTIVO.getCodigo());
		ed.setPersonaNatural(personaNatural);

		personaNatural.setEmpleoDependienteTra(ed);

		// Empleo independiente
		EmpleoIndependiente ei = new EmpleoIndependiente();
		ei.setPersonaNatural(personaNatural);
		ei.setActividadEconomica(new ActividadEconomica());
		ei.setEstado(EstadoEnum.ACTIVO.getCodigo());

		personaNatural.setEmpleoIndependienteTra(ei);

		// pep
		personaNatural.setPersonaPeCollection(new ArrayList<PersonaPe>());
	}

	public static void inicializarPersonaNaturalContratante(PersonaNatural personaNatural, String identificacion,
			TipoIdentificacionServicio tipoIdentificacionServicio) {

		inicializarPersonaNatural(personaNatural, identificacion, tipoIdentificacionServicio,
				TipoIdentificacionEnum.RUC);

		// Se pone la persona RUC
		RucPersonaNatural rucPn = new RucPersonaNatural();
		rucPn.setIdentificacion(identificacion);
		rucPn.setPersonaNatural(personaNatural);
		rucPn.setTipoIdentificacion(personaNatural.getPersona().getTipoIdentificacion());

		personaNatural.setRucPersonaNaturalTra(rucPn);
	}

	/**
	 * Se ponen datos del beneficiario en la persona natural.
	 * 
	 * @param personaNatural
	 * @param beneficiario
	 */
	public static void inicializarPersonaNaturalDeBeneficiario(PersonaNatural personaNatural,
			Beneficiario beneficiario) {
		personaNatural.setApellidoPaterno(beneficiario.getApellidoPaterno());
		personaNatural.setApellidoMaterno(
				beneficiario.getApellidoMaterno() != null ? beneficiario.getApellidoMaterno().trim() : "");
		personaNatural.setPrimerNombre(beneficiario.getPrimerNombre());
		personaNatural.setSegundoNombre(
				beneficiario.getSegundoNombre() != null ? beneficiario.getSegundoNombre().trim() : "");
	}

	/**
	 * Se inicializa PN desde enriquecimiento.
	 * 
	 * @param personaNatural
	 * @param respuestaDto
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void inicializarDeEnriquecimientoContrantante(PersonaNatural personaNatural,
			RespuestaGeneralDto respuestaDto, String identificacion, String usuario,
			DireccionElectronicaServicio direccionElectronicaServicio,
			TipoIdentificacionServicio tipoIdentificacionServicio) {

		PersonaNatural pnRef = null;
		if (LugarEncuentraInfoEnum.SD.equals(respuestaDto.getLugarEncuentra())) {
			pnRef = respuestaDto.getPersonaNatural();
		}
		if (LugarEncuentraInfoEnum.DB.equals(respuestaDto.getLugarEncuentra())) {
			pnRef = respuestaDto.getPersonaNaturalPopUp();
		}

		if (pnRef != null) {

			// Se incializan datos de la Persona natural que vienen de SD, son solo los
			// campos de abajo
			personaNatural.setPersona(pnRef.getPersona());
			personaNatural.setIdentificacion(pnRef.getIdentificacion());
			personaNatural.setTipoIdentificacion(pnRef.getTipoIdentificacion());
			personaNatural.setApellidoPaterno(pnRef.getApellidoPaterno());
			personaNatural.setApellidoMaterno(pnRef.getApellidoMaterno());
			personaNatural.setPrimerNombre(pnRef.getPrimerNombre());
			personaNatural.setSegundoNombre(pnRef.getSegundoNombre());
			personaNatural.setSexo(pnRef.getSexo());
			personaNatural.setFchNacimiento(pnRef.getFchNacimiento());
			personaNatural.setFchFallecimiento(pnRef.getFchFallecimiento());
			personaNatural.setPaisNacionalidad(pnRef.getPaisNacionalidad());
			personaNatural.setEstadoCivil(pnRef.getEstadoCivil());
			personaNatural.setCiudadNacimiento(new Ciudad());

			if (pnRef.isTieneConyuge() && pnRef.isConyugeCompleto()) {
				personaNatural.setConyuge(pnRef.getConyuge());
			} else {
				// Se define el estado civil
				if (personaNatural.getEstadoCivil() != null
						&& personaNatural.getEstadoCivil().getCodEstadoCivil() != null) {
					boolean conConyuge = false;
					for (EstadoCivilEnum ec : EstadoCivilEnum.getEstadoConConyuge()) {
						if (personaNatural.getEstadoCivil().getCodEstadoCivil() == ec.getCodigo()) {
							conConyuge = true;
							break;
						}
					}

					if (conConyuge) {
						// Se inicializa con un objeto vacio
						PersonaNatural conyuge = new PersonaNatural();
						inicializaPersonaNaturalRepLegal(conyuge);
						personaNatural.setConyuge(conyuge);
					}
				}
			}

			// Se obtiene tipoidentificacion tipo RUC
			TipoIdentificacion tipoIdRuc = tipoIdentificacionServicio.findByPk(TipoIdentificacionEnum.RUC.getCodigo());

			// Se inicializa rucPersonaNatural
			personaNatural.setExisteRucPersonaNatural(false);

			RucPersonaNatural rucPn = new RucPersonaNatural();
			rucPn.setIdentificacion(identificacion);
			rucPn.setPersonaNatural(personaNatural);
			rucPn.setTipoIdentificacion(tipoIdRuc);

			personaNatural.setRucPersonaNaturalTra(rucPn);

			// Se obteiene telefono celular
			identificarCelular(personaNatural.getPersona());

			// Se obtienen los correos electronicos solos
			identificarCorreosElectronicosSolos(personaNatural.getPersona(), direccionElectronicaServicio);

			// Inicializar perfil financiero natural
			PerfilFinancieroNatural pfnBlanco = new PerfilFinancieroNatural();
			pfnBlanco.setSecPersonaNatural(personaNatural.getSecPersonaNatural());

			personaNatural.setPerfilFinancieroNatural(pfnBlanco);

			// Se inicializa objeto Ingreso Mensual
			if (personaNatural.getIngresoMensual() == null
					|| personaNatural.getIngresoMensual().getSecIngresoPersona() == null) {
				IngresoMensual im = new IngresoMensual();
				im.setPersonaNatural(personaNatural);
				personaNatural.setIngresoMensual(im);
			}

			// Se inicializa el ingreso adicional
			if (personaNatural.getIngresoMensualAdicionalCollection() != null
					&& !personaNatural.getIngresoMensualAdicionalCollection().isEmpty()) {
				personaNatural.setIngresoMensualAdicionalTra(
						personaNatural.getIngresoMensualAdicionalCollection().iterator().next());
			} else {
				IngresoMensualAdicional ia = new IngresoMensualAdicional();
				personaNatural.setIngresoMensualAdicionalTra(ia);
			}

			boolean tieneTipoEmpleoIndependiente = false;
			boolean tieneTipoEmpleoDependiente = false;
			short campoTipoEmpleo = personaNatural.getTipoEmpleo();// solo se usa si tiene los dos tipos de empleos

			// Se inicializa empleo independiente
			if (personaNatural.getEmpleoIndependienteCollection() != null
					&& !personaNatural.getEmpleoIndependienteCollection().isEmpty()) {
				personaNatural
						.setEmpleoIndependienteTra(personaNatural.getEmpleoIndependienteCollection().iterator().next());

				personaNatural.setTipoEmpleo(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());

				personaNatural.setShowIndependienteTra(true);
			} else {
				EmpleoIndependiente ei = new EmpleoIndependiente();
				ei.setPersonaNatural(personaNatural);
				ei.setActividadEconomica(new ActividadEconomica());
				ei.setEstado(EstadoEnum.ACTIVO.getCodigo());

				personaNatural.setEmpleoIndependienteTra(ei);

				personaNatural.setTipoEmpleo(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());
			}

			// Se inicializa empleo Dependiente
			if (personaNatural.getEmpleoDependienteCollection() != null
					&& !personaNatural.getEmpleoDependienteCollection().isEmpty()) {
				personaNatural
						.setEmpleoDependienteTra(personaNatural.getEmpleoDependienteCollection().iterator().next());

				personaNatural.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());

				personaNatural.setShowIndependienteTra(false);
			} else {
				EmpleoDependiente ed = new EmpleoDependiente();
				ed.setActividadEconomica(new ActividadEconomica());
				ed.setEstado(EstadoEnum.ACTIVO.getCodigo());
				ed.setPersonaNatural(personaNatural);

				personaNatural.setEmpleoDependienteTra(ed);

				personaNatural.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());
			}

			if (tieneTipoEmpleoIndependiente && tieneTipoEmpleoDependiente) {
				personaNatural.setTipoEmpleo(campoTipoEmpleo);
			}

			// inicializa pep
			personaNatural.setPersonaPeCollection(new ArrayList<PersonaPe>());

			// Se inicializa ocupacion
			if (personaNatural.getOcupacion() == null || personaNatural.getOcupacion().getCodOcupacion() == null) {
				personaNatural.setOcupacion(new Ocupacion());
			}

		}
	}

	/**
	 * Se inicializa PN desde enriquecimiento.
	 * 
	 * @param personaNatural
	 * @param respuestaDto
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void inicializarDeEnriquecimiento(PersonaNatural personaNatural, RespuestaGeneralDto respuestaDto,
			String identificacion, String usuario, DireccionElectronicaServicio direccionElectronicaServicio) {

		PersonaNatural pnRef = null;
		if (LugarEncuentraInfoEnum.SD.equals(respuestaDto.getLugarEncuentra())) {
			pnRef = respuestaDto.getPersonaNatural();
		}
		if (LugarEncuentraInfoEnum.DB.equals(respuestaDto.getLugarEncuentra())) {
			pnRef = respuestaDto.getPersonaNaturalPopUp();
		}

		if (pnRef != null) {

			// Se incializan datos de la Persona natural que vienen de SD, son solo los
			// campos de abajo
			personaNatural.setPersona(pnRef.getPersona());
			personaNatural.setIdentificacion(pnRef.getIdentificacion());
			personaNatural.setTipoIdentificacion(pnRef.getTipoIdentificacion());
			personaNatural.setApellidoPaterno(pnRef.getApellidoPaterno());
			personaNatural.setApellidoMaterno(pnRef.getApellidoMaterno());
			personaNatural.setPrimerNombre(pnRef.getPrimerNombre());
			personaNatural.setSegundoNombre(pnRef.getSegundoNombre());
			personaNatural.setSexo(pnRef.getSexo());
			personaNatural.setFchNacimiento(pnRef.getFchNacimiento());
			personaNatural.setFchFallecimiento(pnRef.getFchFallecimiento());
			personaNatural.setPaisNacionalidad(pnRef.getPaisNacionalidad());
			personaNatural.setEstadoCivil(pnRef.getEstadoCivil());
			personaNatural.setCiudadNacimiento(new Ciudad());

			if (pnRef.isTieneConyuge() && pnRef.isConyugeCompleto()) {
				personaNatural.setConyuge(pnRef.getConyuge());
			} else {
				// Se define el estado civil
				if (personaNatural.getEstadoCivil() != null
						&& personaNatural.getEstadoCivil().getCodEstadoCivil() != null) {
					boolean conConyuge = false;
					for (EstadoCivilEnum ec : EstadoCivilEnum.getEstadoConConyuge()) {
						if (personaNatural.getEstadoCivil().getCodEstadoCivil() == ec.getCodigo()) {
							conConyuge = true;
							break;
						}
					}

					if (conConyuge) {
						// Se inicializa con un objeto vacio
						PersonaNatural conyuge = new PersonaNatural();
						inicializaPersonaNaturalRepLegal(conyuge);
						personaNatural.setConyuge(conyuge);
					}
				}
			}

			// Se obteiene telefono celular
			identificarCelular(personaNatural.getPersona());

			// Se obtienen los correos electronicos solos
			identificarCorreosElectronicosSolos(personaNatural.getPersona(), direccionElectronicaServicio);

			// Inicializar perfil financiero natural
			PerfilFinancieroNatural pfnBlanco = new PerfilFinancieroNatural();
			pfnBlanco.setSecPersonaNatural(personaNatural.getSecPersonaNatural());

			personaNatural.setPerfilFinancieroNatural(pfnBlanco);

			// Se inicializa objeto Ingreso Mensual
			if (personaNatural.getIngresoMensual() == null
					|| personaNatural.getIngresoMensual().getSecIngresoPersona() == null) {
				IngresoMensual im = new IngresoMensual();
				im.setPersonaNatural(personaNatural);
				personaNatural.setIngresoMensual(im);
			}

			// Se inicializa el ingreso adicional
			if (personaNatural.getIngresoMensualAdicionalCollection() != null
					&& !personaNatural.getIngresoMensualAdicionalCollection().isEmpty()) {
				personaNatural.setIngresoMensualAdicionalTra(
						personaNatural.getIngresoMensualAdicionalCollection().iterator().next());
			} else {
				IngresoMensualAdicional ia = new IngresoMensualAdicional();
				personaNatural.setIngresoMensualAdicionalTra(ia);
			}

			boolean tieneTipoEmpleoIndependiente = false;
			boolean tieneTipoEmpleoDependiente = false;
			short campoTipoEmpleo = personaNatural.getTipoEmpleo();// solo se usa si tiene los dos tipos de empleos

			// Se inicializa empleo independiente
			if (personaNatural.getEmpleoIndependienteCollection() != null
					&& !personaNatural.getEmpleoIndependienteCollection().isEmpty()) {
				personaNatural
						.setEmpleoIndependienteTra(personaNatural.getEmpleoIndependienteCollection().iterator().next());

				personaNatural.setTipoEmpleo(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());

				personaNatural.setShowIndependienteTra(true);
			} else {
				EmpleoIndependiente ei = new EmpleoIndependiente();
				ei.setPersonaNatural(personaNatural);
				ei.setActividadEconomica(new ActividadEconomica());
				ei.setEstado(EstadoEnum.ACTIVO.getCodigo());

				personaNatural.setEmpleoIndependienteTra(ei);

				personaNatural.setTipoEmpleo(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());
			}

			// Se inicializa empleo Dependiente
			if (personaNatural.getEmpleoDependienteCollection() != null
					&& !personaNatural.getEmpleoDependienteCollection().isEmpty()) {
				personaNatural
						.setEmpleoDependienteTra(personaNatural.getEmpleoDependienteCollection().iterator().next());

				personaNatural.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());

				personaNatural.setShowIndependienteTra(false);
			} else {
				EmpleoDependiente ed = new EmpleoDependiente();
				ed.setActividadEconomica(new ActividadEconomica());
				ed.setEstado(EstadoEnum.ACTIVO.getCodigo());
				ed.setPersonaNatural(personaNatural);

				personaNatural.setEmpleoDependienteTra(ed);

				personaNatural.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());
			}

			if (tieneTipoEmpleoIndependiente && tieneTipoEmpleoDependiente) {
				personaNatural.setTipoEmpleo(campoTipoEmpleo);
			}

			// Se inicializa ocupacion
			if (personaNatural.getOcupacion() == null || personaNatural.getOcupacion().getCodOcupacion() == null) {
				personaNatural.setOcupacion(new Ocupacion());
			}

		}
	}

	/**
	 * Se inicializan datos de la persona natural.
	 * 
	 * @param personaNatural
	 * @param personaNaturalServicio
	 */
	public static void inicializarPersonaNaturalEncontrada(PersonaNatural personaNatural,
			PersonaNaturalServicio personaNaturalServicio,
			PerfilFinancieroNaturalServicio perfilFinancieroNaturalServicio,
			DireccionElectronicaServicio direccionElectronicaServicio) {

		// Se define el estado civil
		if (personaNatural.getEstadoCivil() != null && personaNatural.getEstadoCivil().getCodEstadoCivil() != null) {
			boolean conConyuge = false;
			for (EstadoCivilEnum ec : EstadoCivilEnum.getEstadoConConyuge()) {
				if (personaNatural.getEstadoCivil().getCodEstadoCivil() == ec.getCodigo()) {
					conConyuge = true;
					break;
				}
			}

			if (conConyuge) {
				// Se busca al conyuge
				PersonaNatural conyuge = personaNaturalServicio
						.obtenerConyugePN(personaNatural.getPersona().getSecPersona());

				if (conyuge != null) {
					personaNatural.setConyuge(conyuge);
				} else {
					// Se inicializa con un objeto vacio
					conyuge = new PersonaNatural();
					inicializaPersonaNaturalRepLegal(conyuge);
					personaNatural.setConyuge(conyuge);
				}
			}
		}

		// Se obteiene telefono celular
		identificarCelular(personaNatural.getPersona());

		// Se obtienen los correos electronicos solos
		identificarCorreosElectronicosSolos(personaNatural.getPersona(), direccionElectronicaServicio);

		// Inicializar perfil financiero natural
		PerfilFinancieroNatural pfn = perfilFinancieroNaturalServicio
				.buscarPorSecPersonaNatural(personaNatural.getSecPersonaNatural());

		if (pfn != null && pfn.getSecPerfilFinanciero() != null) {
			personaNatural.setPerfilFinancieroNatural(pfn);
		} else {
			PerfilFinancieroNatural pfnBlanco = new PerfilFinancieroNatural();
			pfnBlanco.setSecPersonaNatural(personaNatural.getSecPersonaNatural());

			personaNatural.setPerfilFinancieroNatural(pfnBlanco);
		}

		// Se inicializa objeto Ingreso Mensual
		if (personaNatural.getIngresoMensual() == null
				|| personaNatural.getIngresoMensual().getSecIngresoPersona() == null) {
			IngresoMensual im = new IngresoMensual();
			im.setPersonaNatural(personaNatural);
			im.setMntEgresoMensual(BigDecimal.ZERO);

			personaNatural.setIngresoMensual(im);
		} else if (personaNatural.getIngresoMensual().getMntEgresoMensual() == null) {
			personaNatural.getIngresoMensual().setMntEgresoMensual(BigDecimal.ZERO);
		}

		System.out.println("C personaNatural.getIngresoMensual(): " + personaNatural.getIngresoMensual());
		System.out.println("C personaNatural.getIngresoMensual().getMntIngresoMensual(): "
				+ personaNatural.getIngresoMensual().getMntIngresoMensual());

		// Se inicializa el ingreso adicional
		if (personaNatural.getIngresoMensualAdicionalCollection() != null
				&& !personaNatural.getIngresoMensualAdicionalCollection().isEmpty()) {
			personaNatural.setIngresoMensualAdicionalTra(
					personaNatural.getIngresoMensualAdicionalCollection().iterator().next());
			if (personaNatural.getIngresoMensualAdicionalTra().getMntEgresoAdicional() == null) {
				personaNatural.getIngresoMensualAdicionalTra().setMntEgresoAdicional(BigDecimal.ZERO);
			}
		} else {
			IngresoMensualAdicional ia = new IngresoMensualAdicional();
			ia.setMntEgresoAdicional(BigDecimal.ZERO);
			personaNatural.setIngresoMensualAdicionalTra(ia);
		}

		boolean tieneTipoEmpleoIndependiente = false;
		boolean tieneTipoEmpleoDependiente = false;
		short campoTipoEmpleo = personaNatural.getTipoEmpleo();// solo se usa si tiene los dos tipos de empleos

		// Se inicializa empleo independiente
		if (personaNatural.getEmpleoIndependienteCollection() != null
				&& !personaNatural.getEmpleoIndependienteCollection().isEmpty()) {
			personaNatural
					.setEmpleoIndependienteTra(personaNatural.getEmpleoIndependienteCollection().iterator().next());

			personaNatural.getEmpleoIndependienteTra().setNombreActividadEconomicaTr(
					personaNatural.getEmpleoIndependienteTra().getActividadEconomica().getActividadEconomica());
			personaNatural.getEmpleoIndependienteTra().setIdActividadEconomicaTr(
					personaNatural.getEmpleoIndependienteTra().getActividadEconomica().getCodActividadEconomica());

			personaNatural.setTipoEmpleo(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());

			// Si el codigo es menor que 2 significa que es sin actividad económica, por
			// enede se limpia el texto que se muestra
			if (personaNatural.getEmpleoIndependienteTra().getActividadEconomica().getCodActividadEconomica()
					.intValue() <= 2) {
				personaNatural.getEmpleoIndependienteTra().setNombreActividadEconomicaTr("");
			}

			personaNatural.setShowIndependienteTra(true);

			tieneTipoEmpleoIndependiente = true;

		} else {
			EmpleoIndependiente ei = new EmpleoIndependiente();
			ei.setPersonaNatural(personaNatural);
			ei.setActividadEconomica(new ActividadEconomica());
			ei.setEstado(EstadoEnum.ACTIVO.getCodigo());

			personaNatural.setEmpleoIndependienteTra(ei);

			personaNatural.setTipoEmpleo(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());
		}

		// Se inicializa empleo Dependiente
		if (personaNatural.getEmpleoDependienteCollection() != null
				&& !personaNatural.getEmpleoDependienteCollection().isEmpty()) {

			personaNatural.setEmpleoDependienteTra(personaNatural.getEmpleoDependienteCollection().iterator().next());
			personaNatural.getEmpleoDependienteTra().setNombreActividadEconomicaTr(
					personaNatural.getEmpleoDependienteTra().getActividadEconomica().getActividadEconomica());
			personaNatural.getEmpleoDependienteTra().setIdActividadEconomicaTr(
					personaNatural.getEmpleoDependienteTra().getActividadEconomica().getCodActividadEconomica());

			personaNatural.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());

			// Si el codigo es menor que 2 significa que es sin actividad económica, por
			// enede se limpia el texto que se muestra
			if (personaNatural.getEmpleoDependienteTra().getActividadEconomica().getCodActividadEconomica()
					.intValue() <= 2) {
				personaNatural.getEmpleoDependienteTra().setNombreActividadEconomicaTr("");
			}

			tieneTipoEmpleoDependiente = true;

			personaNatural.setShowIndependienteTra(false);
		} else {
			EmpleoDependiente ed = new EmpleoDependiente();
			ed.setActividadEconomica(new ActividadEconomica());
			ed.setEstado(EstadoEnum.ACTIVO.getCodigo());
			ed.setPersonaNatural(personaNatural);

			personaNatural.setEmpleoDependienteTra(ed);

			// si ya tiene en bdd el
			if (!tieneTipoEmpleoIndependiente) {
				personaNatural.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());
			}

		}

		if (tieneTipoEmpleoIndependiente && tieneTipoEmpleoDependiente) {
			if (campoTipoEmpleo == 0) {
				// se pone dependiente porq es el ultimo q se llena
				personaNatural.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());
			} else {
				personaNatural.setTipoEmpleo(campoTipoEmpleo);
			}
		}

		// Se inicializa ocupacion
		if (personaNatural.getOcupacion() == null || personaNatural.getOcupacion().getCodOcupacion() == null) {
			personaNatural.setOcupacion(new Ocupacion());
		}

		// Se pone el total de ingresos mensuales
		BigDecimal total = BigDecimal.ZERO;
		if (personaNatural.getIngresoMensual() != null) {
			total = total.add(personaNatural.getIngresoMensual().getMntIngresoMensual() != null
					? personaNatural.getIngresoMensual().getMntIngresoMensual()
					: BigDecimal.ZERO);
		}
		if (personaNatural.getIngresoMensualAdicionalTra() != null) {
			total = total.add(personaNatural.getIngresoMensualAdicionalTra().getMntIngresoAdicional() != null
					? personaNatural.getIngresoMensualAdicionalTra().getMntIngresoAdicional()
					: BigDecimal.ZERO);
		}
		personaNatural.setTotalIngresosTra(total);
	}

	/**
	 * Se identifica el correo electrónico personal y el correo electrónico para
	 * facturacion electrónica.
	 * 
	 * @param persona
	 */
	public static void identificarCorreosElectronicosSolos(Persona persona,
			DireccionElectronicaServicio direccionElectronicaServicio) {
		// 1. Se buscan las direcciones de la persona si las hay
		if (persona.getSecPersona() != null) {
			List<DireccionElectronica> direccionElectronicaList = direccionElectronicaServicio
					.obtenerPorPersona(persona.getSecPersona());

			persona.setDireccionElectronicaFormularioCollection(direccionElectronicaList);
		}

		// 2. Se obtiene la direccion personal y la direccion para facturacion
		// electronica
		DireccionElectronica mailPersonal = null;
		DireccionElectronica mailFacElec = null;

		if (persona.getDireccionElectronicaFormularioCollection() != null
				&& !persona.getDireccionElectronicaFormularioCollection().isEmpty()) {
			boolean hayPersonal = false;
			boolean hayFacElec = false;

			for (DireccionElectronica de : persona.getDireccionElectronicaFormularioCollection()) {
				if (de.getTipoDireccionElectronica().getCodTipoDireccionElectronica()
						.equals(Constantes.TIPO_DIRECCION_ELECTRONICA_ID_PERSONAL)) {
					mailPersonal = de;
					hayPersonal = true;
				}

				if (de.getTipoDireccionElectronica().getCodTipoDireccionElectronica()
						.equals(Constantes.TIPO_DIRECCION_ELECTRONICA_FAC_ELEC)) {
					mailFacElec = de;
					hayFacElec = true;
				}
			}

			if (!hayPersonal) {
				mailPersonal = armarDireccionElectronica(persona, Constantes.TIPO_DIRECCION_ELECTRONICA_ID_PERSONAL);
			}

			if (!hayFacElec) {
				mailFacElec = armarDireccionElectronica(persona, Constantes.TIPO_DIRECCION_ELECTRONICA_FAC_ELEC);
			}

		} else {
			mailPersonal = armarDireccionElectronica(persona, Constantes.TIPO_DIRECCION_ELECTRONICA_ID_PERSONAL);
			mailFacElec = armarDireccionElectronica(persona, Constantes.TIPO_DIRECCION_ELECTRONICA_FAC_ELEC);
		}

		persona.setEmailPersonalTra(mailPersonal);
		persona.setEmailFacturacionElectronicaTra(mailFacElec);
	}

	/**
	 * Se identifica el correo electrónico personal y el correo electrónico para
	 * facturacion electrónica.
	 * 
	 * @param persona
	 */
	public static void identificarCorreosElectronicosSolosPJ(Persona persona,
			DireccionElectronicaServicio direccionElectronicaServicio) {
		// 1. Se buscan las direcciones de la persona si las hay
		if (persona.getSecPersona() != null) {
			List<DireccionElectronica> direccionElectronicaList = direccionElectronicaServicio
					.obtenerPorPersona(persona.getSecPersona());

			persona.setDireccionElectronicaFormularioCollection(direccionElectronicaList);
		}

		// 2. Se obtiene la direccion personal y la direccion para facturacion
		// electronica
		DireccionElectronica mailFacElec = null;

		if (persona.getDireccionElectronicaFormularioCollection() != null
				&& !persona.getDireccionElectronicaFormularioCollection().isEmpty()) {
			boolean hayFacElec = false;

			for (DireccionElectronica de : persona.getDireccionElectronicaFormularioCollection()) {

				if (de.getTipoDireccionElectronica().getCodTipoDireccionElectronica()
						.equals(Constantes.TIPO_DIRECCION_ELECTRONICA_FAC_ELEC)) {
					mailFacElec = de;
					hayFacElec = true;
				}
			}

			if (!hayFacElec) {
				mailFacElec = armarDireccionElectronica(persona, Constantes.TIPO_DIRECCION_ELECTRONICA_FAC_ELEC);
			}

		} else {
			mailFacElec = armarDireccionElectronica(persona, Constantes.TIPO_DIRECCION_ELECTRONICA_FAC_ELEC);
		}

		persona.setEmailFacturacionElectronicaTra(mailFacElec);
	}

	/**
	 * Se identifica el correo electrónico personal y el correo electrónico para
	 * facturacion electrónica.
	 * 
	 * @param persona
	 */
	public static void identificarCorreosElectronicosSolos(Persona persona) {
		DireccionElectronica mailPersonal = armarDireccionElectronica(persona,
				Constantes.TIPO_DIRECCION_ELECTRONICA_ID_PERSONAL);
		DireccionElectronica mailFacElec = armarDireccionElectronica(persona,
				Constantes.TIPO_DIRECCION_ELECTRONICA_FAC_ELEC);

		persona.setEmailPersonalTra(mailPersonal);
		persona.setEmailFacturacionElectronicaTra(mailFacElec);
	}

	/**
	 * Se identifica el correo electrónico personal y el correo electrónico para
	 * facturacion electrónica.
	 * 
	 * @param persona
	 */
	public static void identificarCorreosElectronicosSolosPJ(Persona persona) {
		DireccionElectronica mailFacElec = armarDireccionElectronica(persona,
				Constantes.TIPO_DIRECCION_ELECTRONICA_FAC_ELEC);

		persona.setEmailFacturacionElectronicaTra(mailFacElec);
	}

	public static void enlazarDireccionTelefono(Persona persona, Direccion direccion,
			Collection<Telefono> telefonoCol) {

	}

	/**
	 * Se identifica si la persona tiene definido teléfono celular, caso contrario
	 * se inicializa un objeto de clase Telefono.
	 * 
	 * @param persona
	 */
	public static void identificarCelular(Persona persona) {
		Telefono celular = null;

		if (persona.getTelefonoCollection() != null && !persona.getTelefonoCollection().isEmpty()) {

			for (Telefono t : persona.getTelefonoCollection()) {
				if (t.getActivo() && t.getTipoTelefono().getCodTipoTelefono()
						.equals(TipoTelefonoEqvEnum.MOVIL_OFICINA.getCodigoTipoTelefono())) {
					celular = t;
					break;
				}
			}

			if (celular == null) {
				celular = armarCelularNuevo(persona);
			}
		} else {
			celular = armarCelularNuevo(persona);
		}

		// Se pone objeto Telefono en atributo transient de Persona
		persona.setNoCelularSoloTra(celular);
	}

	/**
	 * Se arma un telefono Celular en blanco.
	 * 
	 * @param persona
	 * @return
	 */
	private static Telefono armarCelularNuevo(Persona persona) {
		TipoTelefono tipoTelefono = new TipoTelefono();
		tipoTelefono.setCodTipoTelefono(TipoTelefonoEqvEnum.MOVIL_OFICINA.getCodigoTipoTelefono());
		Pais pais = new Pais();
		pais.setCodPais(Constantes.PAIS_ID_ECUADOR);

		Telefono celular = new Telefono();
		celular.setTipoTelefono(tipoTelefono);
		celular.setCodArea("09");// codigo de celular por default
		celular.setPais(pais);
		celular.setEstado(EstadoEnum.ACTIVO.getCodigo());
		celular.setPersona(persona);

		return celular;
	}

	/**
	 * Se arma una direccion electrónica en blanco.
	 * 
	 * @param persona
	 * @param tipoCorreoElectronico
	 * @return
	 */
	private static DireccionElectronica armarDireccionElectronica(Persona persona, Short tipoCorreoElectronico) {
		TipoDireccionElectronica tipo = new TipoDireccionElectronica();
		tipo.setCodTipoDireccionElectronica(tipoCorreoElectronico);

		DireccionElectronica email = new DireccionElectronica();
		email.setEstado(EstadoEnum.ACTIVO.getCodigo());
		email.setPersona(persona);
		email.setTipoDireccionElectronica(tipo);

		return email;
	}

	/**
	 * Inicializa los objetos de una persona juridica nueva.
	 * 
	 * @param pj
	 */
	public static void inicializarPersonaJuridica(PersonaJuridica pj, String identificacion,
			TipoIdentificacionServicio tipoIdentificacionServicio) {
		// Se obtiene tipoidentificacion tipo RUC
		TipoIdentificacion tipoIdRuc = tipoIdentificacionServicio.findByPk(TipoIdentificacionEnum.RUC.getCodigo());

		// Se inicializan los datos de la Persona
		Persona persona = new Persona();
		persona.setDireccionCollection(new ArrayList<Direccion>());
		persona.setTipoIdentificacion(tipoIdRuc);

		// inicializa celular
		identificarCelular(persona);
		identificarCorreosElectronicosSolosPJ(persona);

		// Se inicializan los datos de la PersonaJuridica
		pj.setPersona(persona);
		pj.setTipoIdentificacion(tipoIdRuc);
		pj.setIdentificacion(identificacion);
		pj.setActividadEconomica(new ActividadEconomica());
		pj.setSectorMercado(new SectorMercado());
		pj.setPais(new Pais());
		pj.setPerfilFinancieroJuridicaTransient(new PerfilFinancieroJuridica());
		pj.getPerfilFinancieroJuridicaTransient().setPatrimonio(BigDecimal.ZERO);
		pj.getPerfilFinancieroJuridicaTransient().setEgresos(BigDecimal.ZERO);
		pj.getPerfilFinancieroJuridicaTransient().setUtilidad(BigDecimal.ZERO);
		pj.setTipoPersonaJuridica(new TipoPersonaJuridica());
		pj.setAccionistaListTra(new ArrayList<Relacion>());
		persona.setContactoPreferidoTransient(new ContactoPreferido());

		// inicializa representante legal

		inicializarRepresentanteLegal(pj);

	}

	public static void inicializarRepresentanteLegal(PersonaJuridica pj) {
		if (pj.getRepresentante() == null) {

			Persona representante = new Persona();

			representante.setContactoPreferidoTransient(new ContactoPreferido());

			PersonaNatural pn = new PersonaNatural();

			inicializaPersonaNaturalRepLegal(pn);
			representante.setPersonaNaturalTransient(pn);

			pj.setRepresentante(representante);
		}
	}

	/**
	 * Se inicializan los datos para el representante legal de una empresa.
	 * 
	 * @param pn
	 */
	public static void inicializaPersonaNaturalRepLegal(PersonaNatural pn) {
		Persona persona = new Persona();

		pn.setCiudadNacimiento(new Ciudad());
		pn.setDireccionList(new ArrayList<Direccion>());
		pn.setEstadoCivil(new EstadoCivil());
		pn.setPaisNacionalidad(new Pais());
		pn.setTipoIdentificacion(new TipoIdentificacion());
		pn.getTipoIdentificacion().setCodTipoIdentificacion(TipoIdentificacionEnum.CEDULA.getCodigo());

		persona.setTipoIdentificacion(new TipoIdentificacion());
		persona.getTipoIdentificacion().setCodTipoIdentificacion(TipoIdentificacionEnum.CEDULA.getCodigo());
		pn.setPersona(persona);
	}
}
