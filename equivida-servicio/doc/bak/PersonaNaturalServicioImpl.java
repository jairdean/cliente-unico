package com.equivida.servicio.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.log4j.Logger;

import com.equivida.comparator.ReferenciaComparator;
import com.equivida.constant.Constantes;
import com.equivida.constant.EsClienteEnum;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.GanadoPerdidoEnum;
import com.equivida.constant.PersonaRechazoListaReservadaEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoEmpleoEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.dao.DetalleHabitoEnfermedadDao;
import com.equivida.dao.DireccionDao;
import com.equivida.dao.DireccionTelefonoDao;
import com.equivida.dao.HabitoEnfermedadDao;
import com.equivida.dao.PersonaComponenteExclusionDao;
import com.equivida.dao.PersonaDao;
import com.equivida.dao.PersonaNaturalDao;
import com.equivida.dao.TelefonoDao;
import com.equivida.dto.EmpleoDto;
import com.equivida.dto.ResultadoWebserviceListaReservada;
import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.mensajeria.AsyncEmailServicio;
import com.equivida.mensajeria.EmailServicio;
import com.equivida.modelo.Actividad;
import com.equivida.modelo.Biometrica;
import com.equivida.modelo.Canton;
import com.equivida.modelo.CategoriaPpe;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.ContactoPreferido;
import com.equivida.modelo.DetalleActividadFuncion;
import com.equivida.modelo.DetalleHabitoEnfermedad;
import com.equivida.modelo.DetallePasaporte;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.EmpleoDependiente;
import com.equivida.modelo.EmpleoIndependiente;
import com.equivida.modelo.EstadoPersona;
import com.equivida.modelo.HabitoEnfermedad;
import com.equivida.modelo.HistoriaMedicaFamiliar;
import com.equivida.modelo.IngresoMensual;
import com.equivida.modelo.IngresoMensualAdicional;
import com.equivida.modelo.InstitucionFinanciera;
import com.equivida.modelo.MotivoSeguro;
import com.equivida.modelo.Ocupacion;
import com.equivida.modelo.OtraOcupacion;
import com.equivida.modelo.Pais;
import com.equivida.modelo.PerfilFinancieroNatural;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaComponenteExclusion;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.PersonaPe;
import com.equivida.modelo.Profesion;
import com.equivida.modelo.Rcs;
import com.equivida.modelo.Referencia;
import com.equivida.modelo.ReferenciaBancaria;
import com.equivida.modelo.ReferenciaComercial;
import com.equivida.modelo.Relacion;
import com.equivida.modelo.SeguroAdicional;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoActividad;
import com.equivida.modelo.TipoContactoPreferido;
import com.equivida.modelo.TipoDireccionElectronica;
import com.equivida.modelo.TipoEstado;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoInstitucionFinanciera;
import com.equivida.modelo.TipoMotivoSeguro;
import com.equivida.modelo.TipoOtroSeguro;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.modelo.TipoRiesgo;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.BiometricaServicio;
import com.equivida.servicio.ContactoPreferidoServicio;
import com.equivida.servicio.DetalleActividadFuncionServicio;
import com.equivida.servicio.DetallePasaporteServicio;
import com.equivida.servicio.DireccionElectronicaServicio;
import com.equivida.servicio.DireccionTelefonoServicio;
import com.equivida.servicio.OtraOcupacionServicio;
import com.equivida.servicio.PerfilFinancieroNaturalServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.ReferenciaBancariaServicio;
import com.equivida.servicio.ReferenciaComercialServicio;
import com.equivida.servicio.ReferenciaServicio;
import com.equivida.servicio.RelacionServicio;
import com.equivida.servicio.TipoActividadServicio;
import com.equivida.servicio.TipoMotivoSeguroServicio;
import com.equivida.servicio.TipoOtroSeguroServicio;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.equivida.util.EquivalenciaKilosLibras;
import com.equivida.util.MailMessage;
import com.equivida.util.webservice.WsRiesgoUtil;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PersonaNaturalServicio")
public class PersonaNaturalServicioImpl extends
		GenericServiceImpl<PersonaNatural, Integer> implements
		PersonaNaturalServicio {

	private Logger log = Logger.getLogger(PersonaNaturalServicioImpl.class);

	@Resource
	private SessionContext sessionContext;// para auditoria

	@EJB
	private PersonaNaturalDao personaNaturalDao;

	@EJB
	private DireccionTelefonoDao direccionTelefonoDao;

	@EJB
	private TelefonoDao telefonoDao;

	@EJB
	private ContactoPreferidoServicio contactoPreferidoServicio;

	@EJB
	private ReferenciaServicio referenciaServicio;

	@EJB
	private ReferenciaBancariaServicio referenciaBancariaServicio;

	@EJB
	private ReferenciaComercialServicio referenciaComercialServicio;

	@EJB
	private BiometricaServicio biometricaServicio;

	@EJB
	private PerfilFinancieroNaturalServicio perfilFinancieroNaturalServicio;

	@EJB
	private DetalleActividadFuncionServicio detalleActividadFuncionServicio;

	@EJB
	private DetallePasaporteServicio detallePasaporteServicio;

	@EJB
	private DireccionDao direccionDao;

	@EJB
	private PersonaComponenteExclusionDao personaComponenteExclusionDao;

	@EJB
	private PersonaDao personaDao;

	@EJB
	private TipoActividadServicio tipoActividadServicio;

	@EJB
	private TipoMotivoSeguroServicio tipoMotivoSeguroServicio;

	@EJB
	private TipoOtroSeguroServicio tipoOtroSeguroServicio;

	@EJB
	private HabitoEnfermedadDao habitoEnfermedadDao;

	@EJB
	private DetalleHabitoEnfermedadDao detalleHabitoEnfermedadDao;

	@EJB
	private PersonaNaturalServicio personaNaturalServicio;

	@EJB
	private DireccionTelefonoServicio direccionTelefonoServicio;

	@EJB
	private DireccionElectronicaServicio direccionElectronicaServicio;

	@EJB
	private RelacionServicio relacionServicio;

	@EJB
	private OtraOcupacionServicio otraOcupacionServicio;

	@EJB
	private WsDatosPersonaServicio wsDatosPersonaServicio;

	@EJB(mappedName = "EmailServicio/local")
	private EmailServicio emailServicio;

	@EJB(mappedName = "AsyncEmailServicio/local")
	private AsyncEmailServicio asyncEmailServicio;

	@EJB
	private RcsServicio rcsServicio;

	public GenericDao<PersonaNatural, Integer> getDao() {
		return personaNaturalDao;
	}

	@Override
	public BigDecimal calcularSaldoMensual(IngresoMensual ingresoMensual,
			Collection<IngresoMensualAdicional> ingresoMensualAdicionalLista) {

		BigDecimal totalIngresos = BigDecimal.valueOf(0);
		BigDecimal totalEgresos = BigDecimal.valueOf(0);
		BigDecimal saldoTotal = BigDecimal.valueOf(0);

		if (ingresoMensual.getMntIngresoMensual() == null) {
			ingresoMensual.setMntIngresoMensual(BigDecimal.valueOf(0));
		}
		if (ingresoMensual.getMntEgresoMensual() == null) {
			ingresoMensual.setMntEgresoMensual(BigDecimal.valueOf(0));
		}

		totalIngresos = totalIngresos
				.add(ingresoMensual.getMntIngresoMensual());
		totalEgresos = totalEgresos.add(ingresoMensual.getMntEgresoMensual());

		if (ingresoMensualAdicionalLista != null) {
			for (IngresoMensualAdicional ingresoMensualAdicional : ingresoMensualAdicionalLista) {
				totalIngresos = totalIngresos.add(ingresoMensualAdicional
						.getMntIngresoAdicional());
				totalEgresos = totalEgresos.add(ingresoMensualAdicional
						.getMntEgresoAdicional());
			}
		}

		saldoTotal = totalIngresos.subtract(totalEgresos);
		saldoTotal = saldoTotal.setScale(2, BigDecimal.ROUND_HALF_UP);

		return saldoTotal;
	}

	@Override
	public PersonaNatural findByPkParaFormularioWeb(Integer secPersonaNatural) {

		// para que pueda copiar el date (nullo) en BeanUtils.copyProperties
		java.util.Date defaultValue = null;
		DateConverter converter = new DateConverter(defaultValue);
		ConvertUtils.register(converter, java.util.Date.class);

		PersonaNatural personaNatural = this.findByPk(secPersonaNatural);

		// auditoria persona natural
		PersonaNatural originalPN = new PersonaNatural();
		try {
			BeanUtils.copyProperties(originalPN, personaNatural);
			personaNatural.setOriginal(originalPN);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		// auditoria persona
		Persona originalP = new Persona();
		try {
			BeanUtils.copyProperties(originalP, personaNatural.getPersona());
			personaNatural.getPersona().setOriginal(originalP);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		// conyuge
		Persona conyuge = obtenerConyuge(personaNatural.getPersona()
				.getSecPersona());
		if (conyuge != null) {

			PersonaNatural pnconyuge = obtenerPersonaNaturalByPersona(conyuge
					.getSecPersona());
			conyuge.setPersonaNaturalTransient(pnconyuge);

			// si esta guardado en persona natural
			if (conyuge.getPersonaNaturalTransient() != null
					&& conyuge.getPersonaNaturalTransient()
							.getSecPersonaNatural() != null) {
				personaNatural.setConyuge(conyuge.getPersonaNaturalTransient());
			} else {
				// si solo esta en persona
				PersonaNatural pnC = new PersonaNatural();
				pnC.setPersona(conyuge);
				pnC.setTipoIdentificacion(conyuge.getTipoIdentificacion());
				personaNatural.setConyuge(pnC);
			}
		} else {
			conyuge = new Persona();
			PersonaNatural pnC = new PersonaNatural();
			pnC.setPersona(conyuge);
			pnC.setTipoIdentificacion(new TipoIdentificacion(
					TipoIdentificacionEnum.CEDULA.getCodigo()));
			personaNatural.setConyuge(pnC);
		}

		Collection<Direccion> dirCollection = personaNatural.getPersona()
				.getDireccionCollection();
		dirCollection.size();

		// recupero contacto preferido y le pongo en el transient
		personaNatural.getPersona().setContactoPreferidoTransient(
				contactoPreferidoServicio.obtenerPorPersona(personaNatural
						.getPersona().getSecPersona()));

		ContactoPreferido contactoPreferido = personaNatural.getPersona()
				.getContactoPreferidoTransient();

		if (contactoPreferido != null) {
			contactoPreferido.ponerHorarioEnHorasMinutos();
		} else {
			// no tiene contacto preferido, fue realizado de la migracion
			contactoPreferido = new ContactoPreferido();
			contactoPreferido.setDireccion(new Direccion());
			contactoPreferido.setPersona(personaNatural.getPersona());
			contactoPreferido.setTelefono(new Telefono());
			contactoPreferido
					.setTipoContactoPreferido(new TipoContactoPreferido());
			personaNatural.getPersona().setContactoPreferidoTransient(
					contactoPreferido);
		}

		for (Direccion direccion : dirCollection) {
			if (direccion != null) {

				Short secCanton = direccion.getCanton().getSecCanton();
				String nombreCanton = direccion.getCanton().getCanton();
				Short secProvincia = direccion.getCanton().getProvincia()
						.getSecProvincia();
				String nombreProv = direccion.getCanton().getProvincia()
						.getProvincia();
				Short secPais = direccion.getCanton().getProvincia().getPais()
						.getCodPais();
				String nombrePais = direccion.getCanton().getProvincia()
						.getPais().getPais();

				direccion.setCanton(new Canton(secCanton, nombreCanton,
						secProvincia, nombreProv, secPais, nombrePais));

				// auditoria
				Direccion original = new Direccion();
				try {
					BeanUtils.copyProperties(original, direccion);
					direccion.setOriginal(original);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}

				// busca las direciones telefono y ponerlos en el lista
				// transient
				// persona.secPersona=personaNatural.getpersona.getsecpersona

				String[] criteriasAnd = { "persona.secPersona",
						"direccion.secDireccion" };
				CriteriaTypeEnum[] typesAnd = {
						CriteriaTypeEnum.INTEGER_EQUALS,
						CriteriaTypeEnum.INTEGER_EQUALS };
				Object[] params = new Object[] {
						personaNatural.getPersona().getSecPersona(),
						direccion.getSecDireccion() };
				Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

				Collection<DireccionTelefono> direccionTelefonoCollection = direccionTelefonoDao
						.findByCriterias(criteria);

				for (DireccionTelefono dt : direccionTelefonoCollection) {
					if (contactoPreferido.getTelefono().getSecTelefono() != null
							&& dt.getTelefono()
									.getSecTelefono()
									.compareTo(
											contactoPreferido.getTelefono()
													.getSecTelefono()) == 0) {
						dt.getTelefono().setPrincipal(true);
					} else {
						dt.getTelefono().setPrincipal(false);
					}
					// feaso
					dt.getTelefono().setTipoTelefono(
							new TipoTelefono(dt.getTelefono().getTipoTelefono()
									.getCodTipoTelefono()));
					// para pasar con asteriscos
					dt.getTelefono().setTelefonoConCodigoAreaAnterior(
							dt.getTelefono().getTelefonoConCodigoArea());

					// auditoria telefono con direccion
					Telefono originalT = new Telefono();
					try {
						BeanUtils.copyProperties(originalT, dt.getTelefono());
						dt.getTelefono().setOriginal(originalT);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}

					// auditoria direccion telefono
					DireccionTelefono originalDT = new DireccionTelefono();
					try {
						BeanUtils.copyProperties(originalDT, dt);
						dt.setOriginal(originalDT);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}
				}

				direccion
						.setDireccionTelefonoCollection(direccionTelefonoCollection);

			}
		}

		// telefonos adicionales
		String[] criteriasAnd = { "persona.secPersona",
				"tipoTelefono.reqDireccion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.STRING_EQUALS };
		Object[] params = new Object[] {
				personaNatural.getPersona().getSecPersona(),
				RespuestaEnum.NO.getCodigo() };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		Collection<Telefono> telefonoSinDireccionCollection = telefonoDao
				.findByCriterias(criteria);

		for (Telefono telefono : telefonoSinDireccionCollection) {
			telefono.setPrincipal(false);
			// este if es porque no puede tener contacto preferido
			if (contactoPreferido.getTelefono().getSecTelefono() != null) {
				if (telefono.getSecTelefono().compareTo(
						contactoPreferido.getTelefono().getSecTelefono()) == 0) {
					telefono.setPrincipal(true);
				}
			}
			// feaso
			telefono.setTipoTelefono(new TipoTelefono(telefono
					.getTipoTelefono().getCodTipoTelefono()));
			// para pasar con asteriscos
			telefono.setTelefonoConCodigoAreaAnterior(telefono
					.getTelefonoConCodigoArea());

			// auditoria telefono SIN direccion
			Telefono originalT = new Telefono();
			try {
				BeanUtils.copyProperties(originalT, telefono);
				telefono.setOriginal(originalT);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}

		personaNatural.getPersona().setTelefonoSinDireccionCollection(
				telefonoSinDireccionCollection);

		// personaNatural.getPersona().getDireccionElectronicaCollection().size();
		Collection<DireccionElectronica> deList = direccionElectronicaServicio
				.obtenerPorPersona(personaNatural.getPersona().getSecPersona());
		personaNatural.getPersona()
				.setDireccionElectronicaFormularioCollection(deList);
		// personaNatural.getPersona()
		// .getDireccionElectronicaCollection();
		for (DireccionElectronica de : deList) {
			// feaso
			de.setTipoDireccionElectronica(new TipoDireccionElectronica(de
					.getTipoDireccionElectronica()
					.getCodTipoDireccionElectronica()));

			// auditoria telefono con direccion
			DireccionElectronica original = new DireccionElectronica();

			try {
				BeanUtils.copyProperties(original, de);
				de.setOriginal(original);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}

		}

		personaNatural.getIngresoMensualAdicionalCollection().size();

		personaNatural.getIngresoAnualCollection().size();

		List<Referencia> refCollection = referenciaServicio
				.obtenerPorPersonaNatural(personaNatural.getSecPersonaNatural());

		Collections.sort(refCollection, new ReferenciaComparator());

		// Referencias personales
		List<Referencia> referenciaLista = new ArrayList<Referencia>();

		for (Referencia r : refCollection) {
			r.setTipoParentescoRelacion(new TipoParentescoRelacion(r
					.getTipoParentescoRelacion().getCodTipoParentesco()));
			referenciaLista.add(r);
		}

		int llenarFaltaR = 2 - refCollection.size();

		if (llenarFaltaR > 0) {
			for (int i = 0; i < llenarFaltaR; i++) {
				Referencia r = new Referencia();
				r.setTipoParentescoRelacion(new TipoParentescoRelacion());
				r.setPersonaNatural(personaNatural);
				r.setFamiliarNoVivaUd(RespuestaEnum.NO.getCodigo());
				referenciaLista.add(r);
			}
		}

		for (Referencia referencia : referenciaLista) {
			Referencia original = new Referencia();
			try {
				BeanUtils.copyProperties(original, referencia);
				referencia.setOriginal(original);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}

		personaNatural.setReferenciaFormularioCollection(referenciaLista);

		// se vuelve a setear la collection
		personaNatural.setReferenciaFormularioCollection(referenciaLista);

		// referencias bancarias
		List<ReferenciaBancaria> refBCollection = referenciaBancariaServicio
				.obtenerPorPersonaNatural(personaNatural.getSecPersonaNatural());

		List<ReferenciaBancaria> referenciaBLista = new ArrayList<ReferenciaBancaria>();

		for (ReferenciaBancaria rb : refBCollection) {
			rb.setInstitucionFinanciera(new InstitucionFinanciera(
					rb.getInstitucionFinanciera().getSecInstitucionFinanciera(),
					rb.getInstitucionFinanciera().getInstitucionFinanciera(),
					new TipoInstitucionFinanciera(rb.getInstitucionFinanciera()
							.getTipoInstitucionFinanciera()
							.getSecTipoFinanciera(), rb
							.getInstitucionFinanciera()
							.getTipoInstitucionFinanciera().getTipoFinanciera())));
			referenciaBLista.add(rb);
		}

		int llenarFaltaBancaria = 2 - referenciaBLista.size();

		if (llenarFaltaBancaria > 0) {
			for (int i = 0; i < llenarFaltaBancaria; i++) {
				ReferenciaBancaria rb = new ReferenciaBancaria();
				rb.setInstitucionFinanciera(new InstitucionFinanciera(
						new TipoInstitucionFinanciera()));
				rb.setPersonaNatural(personaNatural);
				referenciaBLista.add(rb);
			}
		}

		personaNatural
				.setReferenciaBancariaFormularioCollection(referenciaBLista);

		// referencias comerciales
		List<ReferenciaComercial> refCCollection = referenciaComercialServicio
				.obtenerPorPersonaNatural(personaNatural.getSecPersonaNatural());

		int llenarFaltaComercial = 2 - refCCollection.size();

		if (llenarFaltaComercial > 0) {
			for (int i = 0; i < llenarFaltaComercial; i++) {
				ReferenciaComercial rc = new ReferenciaComercial();
				rc.setPersonaNatural(personaNatural);
				refCCollection.add(rc);
			}
		}

		personaNatural
				.setReferenciaComercialFormularioCollection(refCCollection);

		personaNatural.getOtroEmpleoCollection().size();

		personaNatural.getSegurosVigentesCollection().size();

		personaNatural.getDeportePracticaCollection().size();

		int totalPeActivos = 0;

		Collection<PersonaPe> personaPeCollecction = personaNatural
				.getPersonaPeCollection();

		for (PersonaPe personaPe : personaPeCollecction) {
			// el bug de selectonemenu
			personaPe.setCategoriaPpe(new CategoriaPpe(personaPe
					.getCategoriaPpe().getCodCategoriaPpe()));
			if (personaPe.getActivo()) {
				totalPeActivos++;
			}
		}

		// respuestaPep
		if (totalPeActivos > 0) {
			personaNatural.setRespuestaPep(RespuestaEnum.SI.getCodigo() + "");
		} else {
			personaNatural.setRespuestaPep(RespuestaEnum.NO.getCodigo() + "");
		}

		Collection<EmpleoDto> empleoDtoList = new ArrayList<EmpleoDto>();

		Collection<EmpleoDependiente> dependientes = personaNatural
				.getEmpleoDependienteCollection();

		for (EmpleoDependiente empleoDependiente : dependientes) {
			EmpleoDto dto = new EmpleoDto();
			dto.setSecEmpleo(empleoDependiente.getSecEmpleoDependiente());
			dto.setEstado(empleoDependiente.getEstado());
			dto.setCargo(empleoDependiente.getCargo());
			dto.setTiempoEmpresa(empleoDependiente.getTiempoEmpresa());
			dto.setCodTiempo(empleoDependiente.getCodTiempo());
			dto.setNegocioEmpresaNombre(empleoDependiente.getNegocioEmpresa());
			dto.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());
			dto.setTipoEmpleoNombre(TipoEmpleoEnum.DEPENDIENTE.getKeyBundle());
			dto.setActividadEconomicaId(empleoDependiente
					.getActividadEconomica().getCodActividadEconomica());
			dto.setActividadEconomicaNombre(empleoDependiente
					.getActividadEconomica().getActividadEconomica());

			empleoDtoList.add(dto);
		}

		Collection<EmpleoIndependiente> independientes = personaNatural
				.getEmpleoIndependienteCollection();

		for (EmpleoIndependiente empleoIndependiente : independientes) {
			EmpleoDto dto = new EmpleoDto();
			dto.setSecEmpleo(empleoIndependiente.getSecEmpleoIndependiente());
			dto.setEstado(empleoIndependiente.getEstado());
			dto.setCargo(empleoIndependiente.getCargo());
			dto.setTiempoEmpresa(empleoIndependiente.getTiempoEmpresa());
			dto.setCodTiempo(empleoIndependiente.getCodTiempo());
			dto.setTipoEmpleo(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());
			dto.setTipoEmpleoNombre(TipoEmpleoEnum.INDEPENDIENTE.getKeyBundle());
			dto.setActividadEconomicaId(empleoIndependiente
					.getActividadEconomica().getCodActividadEconomica());
			dto.setActividadEconomicaNombre(empleoIndependiente
					.getActividadEconomica().getActividadEconomica());

			empleoDtoList.add(dto);
		}

		Collection<OtraOcupacion> otraOcupacionLista = otraOcupacionServicio
				.obtenerPorPersonaNatural(secPersonaNatural);

		for (OtraOcupacion otraOcupacion : otraOcupacionLista) {
			EmpleoDto dto = new EmpleoDto();
			dto.setSecEmpleo(otraOcupacion.getSecOtraOcupacion());
			dto.setEstado(EstadoEnum.ACTIVO.getCodigo());
			dto.setTiempoEmpresa(otraOcupacion.getTiempoOcupacion());
			dto.setCodTiempo(otraOcupacion.getCodTiempo());
			dto.setTipoEmpleo(otraOcupacion.getTipoOtraOcupacion()
					.getCodOtraOcupacion());
			dto.setTipoEmpleoNombre(otraOcupacion.getTipoOtraOcupacion()
					.getOtraOcupacion());

			empleoDtoList.add(dto);
		}

		personaNatural.setEmpleoCollection(empleoDtoList);

		personaNatural.setIdentificacionOriginal(personaNatural
				.getIdentificacion().toString());

		// actividades

		Collection<Actividad> actividadBDDList = personaNatural
				.getActividadCollection();

		List<TipoActividad> tipoActividadLista = tipoActividadServicio
				.findAll();

		System.out.println("tipo actividad:" + tipoActividadLista.size());

		List<Actividad> actividadLista = new ArrayList<Actividad>();// la lista
																	// final

		// itero todos los tipos
		for (TipoActividad tipoActividad : tipoActividadLista) {

			Actividad act = null;
			// busco si ese tipo ya esta en la base para esa persona
			for (Actividad bdd : actividadBDDList) {
				if (bdd.getTipoActividad().getCodActividad()
						.compareTo(tipoActividad.getCodActividad()) == 0) {
					act = bdd;
					break;
				}
			}
			// si no encontro crea una nueva para que aparezca en el formulario
			if (act == null) {
				act = new Actividad();
				act.setPersonaNatural(personaNatural);
				act.setSeleccionado(false);
				act.setEstado(EstadoEnum.INACTIVO.getCodigo());
				act.setTipoActividad(tipoActividad);
			}
			actividadLista.add(act);
		}

		System.out.println("actividad persona:" + actividadLista.size());

		personaNatural.setActividadFormularioCollection(actividadLista);

		if (personaNatural.getInformacionAdicional() != null) {

			if (personaNatural.getInformacionAdicional().getCilindraje() != null) {
				personaNatural.getInformacionAdicional().setConduceMoto(
						RespuestaEnum.SI.getCodigo());
			} else {
				personaNatural.getInformacionAdicional().setConduceMoto(
						RespuestaEnum.NO.getCodigo());
			}

			if (personaNatural.getInformacionAdicional().getAccidentes() != null
					&& !personaNatural.getInformacionAdicional()
							.getAccidentes().equals("")) {
				personaNatural.getInformacionAdicional().setRespuestaSN(
						RespuestaEnum.SI.getCodigo());
			} else {
				personaNatural.getInformacionAdicional().setRespuestaSN(
						RespuestaEnum.NO.getCodigo());
			}

		}

		// motivo seguro

		Collection<MotivoSeguro> motivoBDDList = personaNatural.getPersona()
				.getMotivoSeguroCollection();

		List<TipoMotivoSeguro> tipoMotivoSeguroLista = tipoMotivoSeguroServicio
				.findAll();

		System.out.println("tipo motivo seg total:"
				+ tipoMotivoSeguroLista.size());

		List<MotivoSeguro> motivoSeguroLista = new ArrayList<MotivoSeguro>();

		for (TipoMotivoSeguro tipoMotivoSeguro : tipoMotivoSeguroLista) {

			MotivoSeguro ms = null;
			for (MotivoSeguro bdd : motivoBDDList) {
				if (bdd.getTipoMotivoSeguro().getCodTipoMotivo()
						.compareTo(tipoMotivoSeguro.getCodTipoMotivo()) == 0) {
					ms = bdd;
					break;
				}
			}
			// si no encontro crea una nueva para que aparezca en el formulario
			if (ms == null) {
				ms = new MotivoSeguro();
				ms.setPersona(personaNatural.getPersona());
				ms.setSeleccionado(false);
				ms.setEstado(EstadoEnum.INACTIVO.getCodigo());
				ms.setTipoMotivoSeguro(tipoMotivoSeguro);
			}

			motivoSeguroLista.add(ms);
		}

		personaNatural.getPersona().setMotivoSeguroFormularioCollection(
				motivoSeguroLista);

		// otro seguros adicionales
		List<TipoOtroSeguro> otrosSegurosAll = tipoOtroSeguroServicio.findAll();

		Collection<SeguroAdicional> bddOSList = personaNatural
				.getSeguroAdicionalCollection();

		Collection<SeguroAdicional> finalOSList = new ArrayList<SeguroAdicional>();

		for (TipoOtroSeguro tos : otrosSegurosAll) {

			SeguroAdicional sa = null;
			for (SeguroAdicional bdd : bddOSList) {
				if (bdd.getTipoOtroSeguro().getSecTipoAdicional()
						.compareTo(tos.getSecTipoAdicional()) == 0) {
					sa = bdd;
					break;
				}
			}

			if (sa == null) {
				// entonces no encotro
				sa = new SeguroAdicional();
				sa.setPersonaNatural(personaNatural);
				sa.setTipoOtroSeguro(tos);
			}
			finalOSList.add(sa);
		}

		personaNatural.setSeguroAdicionalFormularioCollection(finalOSList);

		// beneficiarios
		// personaNatural.getPersona().setBeneficiarioPolizaCollection(
		// beneficiarioPolizaDao.obtenerPorSecPersona(personaNatural
		// .getPersona().getSecPersona()));

		// perfil financiero
		PerfilFinancieroNatural pfn = perfilFinancieroNaturalServicio
				.buscarPorSecPersonaNatural(secPersonaNatural);
		personaNatural.setPerfilFinancieroNatural(pfn);

		// detalleactividadfuncion
		DetalleActividadFuncion daf = detalleActividadFuncionServicio
				.buscarPorSecPersonaNatural(secPersonaNatural);
		personaNatural.setDetalleActividadFuncion(daf);

		// detallepasaporte
		DetallePasaporte dp = detallePasaporteServicio
				.buscarPorSecPersonaNatural(secPersonaNatural);
		personaNatural.setDetallePasaporte(dp);

		// biometrica
		Biometrica biometrica = biometricaServicio
				.buscarPorSecPersonaNatural(secPersonaNatural);
		personaNatural.setBiometrica(biometrica);

		if (personaNatural.getBiometrica() != null) {

			BigDecimal pesoKilos = personaNatural.getBiometrica().getPeso();
			personaNatural.getBiometrica().setPesoKilos(pesoKilos);
			personaNatural.getBiometrica().setPesoLibras(
					EquivalenciaKilosLibras.getLibras(pesoKilos));

			BigDecimal pesoKilosDiferencia = personaNatural.getBiometrica()
					.getDiferenciaUltimoAnio();
			if (personaNatural.getBiometrica().getGanadoPerdido() == GanadoPerdidoEnum.GANADO
					.getCodigo()) {
				personaNatural.getBiometrica().setGanadosUltAnioKilos(
						pesoKilosDiferencia);
				personaNatural.getBiometrica().setGanadosUltAnioLibras(
						EquivalenciaKilosLibras.getLibras(pesoKilosDiferencia));
			}

			if (personaNatural.getBiometrica().getGanadoPerdido() == GanadoPerdidoEnum.PERDIDO
					.getCodigo()) {
				personaNatural.getBiometrica().setPerdidosUltAnioKilos(
						pesoKilosDiferencia);
				personaNatural.getBiometrica().setPerdidosUltAnioLibras(
						EquivalenciaKilosLibras.getLibras(pesoKilosDiferencia));
			}

		}// fin biometrica

		Collection<HistoriaMedicaFamiliar> bddList = personaNatural
				.getHistoriaMedicaFamiliarCollection();

		List<HistoriaMedicaFamiliar> hmfList = new ArrayList<HistoriaMedicaFamiliar>();

		for (HistoriaMedicaFamiliar bdd : bddList) {
			HistoriaMedicaFamiliar hmf = null;
			if (bdd.getVivo() == RespuestaEnum.SI.getCodigo()) {
				hmf = bdd;
			} else {
				hmf = new HistoriaMedicaFamiliar();
				hmf.setSecHistoriaMedica(bdd.getSecHistoriaMedica());
				hmf.setTipoParentescoRelacion(bdd.getTipoParentescoRelacion());
				hmf.setDetallesM(bdd.getDetalles());
				hmf.setPersonaNatural(personaNatural);
				hmf.setEdadM(bdd.getEdad());
			}
			hmfList.add(hmf);

		}

		for (HistoriaMedicaFamiliar hmf : hmfList) {
			hmf.setTipoParentescoRelacion(new TipoParentescoRelacion(hmf
					.getTipoParentescoRelacion().getCodTipoParentesco()));
		}

		personaNatural.setHistoriaMedicaFamiliarFormularioCollection(hmfList);

		// estado persona
		Collection<EstadoPersona> epLista = personaNatural
				.getEstadoPersonaCollection();

		for (EstadoPersona ep : epLista) {
			// bug
			ep.setTipoEstado(new TipoEstado(ep.getTipoEstado().getCodEstado()));
		}

		// personaexclusion
		personaNatural.getPersona().setPersonaComponenteExclusionTransient(
				llenarPersonaExclusion(personaNatural.getPersona()
						.getSecPersona()));

		return personaNatural;
	}

	private PersonaComponenteExclusion llenarPersonaExclusion(Integer secPersona) {

		PersonaComponenteExclusion exclusion = null;

		String[] criteriasAnd = { "persona.secPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] params = new Object[] { secPersona };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<PersonaComponenteExclusion> eCollection = personaComponenteExclusionDao
				.findByCriterias(criteria);

		if (eCollection.size() >= 1) {
			exclusion = eCollection.get(0);
		}

		return exclusion;
	}

	public void crearPersonaNaturalFormularioWeb(PersonaNatural personaNatural,
			String usuario, String ip) throws EmpleoDependienteException,
			EmpleoIndependienteException, ServiceException, RemoteException,
			ErrorIngresoWsSiseException {

		log.info("creando solicitud PN ..."
				+ personaNatural.getNombresApellidos());
		Persona persona = personaNatural.getPersona();
		IngresoMensual ingresoMensual = personaNatural.getIngresoMensual();

		if (persona == null) {
			String mensaje = "no se ha ingresado persona!";
			log.error(mensaje);
			throw new RuntimeException(mensaje);
		}

		if (ingresoMensual == null) {
			String mensaje = "no se ha ingresado ingreso mensual!";
			log.error(mensaje);
			throw new RuntimeException(mensaje);
		}

		// genera denominacion
		persona.setDenominacion(personaNatural.getApellidosNombres());// 1erAp
																		// 2doAp
																		// 1erNom
		// 2doNom

		Collection<IngresoMensualAdicional> ingresoMensualAdicionalLista = personaNatural
				.getIngresoMensualAdicionalCollection();
		BigDecimal saldoMensual = personaNaturalServicio.calcularSaldoMensual(
				ingresoMensual, ingresoMensualAdicionalLista);
		personaNatural.setMntSaldoMensual(saldoMensual);// calculado

		// llena valores auditoria
		llenaValoresAuditoria(personaNatural, true);
		// de forma sincronizada envia a guardar
		crearSync(personaNatural, persona, usuario, ip);
	}

	private void llenaValoresAuditoria(PersonaNatural personaNatural,
			boolean nuevo) {
		String usuario = sessionContext.getCallerPrincipal().getName();
		String terminal = getCurrentClientIpAddress();
		String programa = Constantes.FORMULARIO_WEB_PROGRAMA_AUDITORIA;
		String cuenta = this.getClass().getSimpleName();
		Persona persona = personaNatural.getPersona();
		// persona y persona natural
		if (nuevo) {
			llenarPersonaNaturalAuditoria(personaNatural, usuario, terminal,
					programa, cuenta);
			llenarPersonaAuditoria(usuario, terminal, programa, cuenta, persona);

		} else {
			personaNatural.setUsrModificacion(usuario);
			personaNatural.setTtyModificacion(terminal);
			personaNatural.setPrgModificacion(programa);
			personaNatural.setCtaModificacion(cuenta);
			persona.setUsrModificacion(usuario);
			persona.setTtyModificacion(terminal);
			persona.setPrgModificacion(programa);
			persona.setCtaModificacion(cuenta);

		}

		// direcciones, telefonos y direcciones telefonos
		Collection<Direccion> direcciones = persona.getDireccionCollection();

		for (Telefono t : persona.getTelefonoCollection()) {
			if (t.getSecTelefono() != null) {
				t.setUsrModificacion(usuario);
				t.setTtyModificacion(terminal);
				t.setPrgModificacion(programa);
				t.setCtaModificacion(cuenta);
			} else {
				t.setUsrCreacion(usuario);
				t.setTtyCreacion(terminal);
				t.setPrgCreacion(programa);
				t.setCtaCreacion(cuenta);
				t.setUsrModificacion(usuario);
				t.setTtyModificacion(terminal);
				t.setPrgModificacion(programa);
				t.setCtaModificacion(cuenta);
			}
		}
		for (Direccion d : direcciones) {
			if (d.getSecDireccion() != null) {
				d.setUsrModificacion(usuario);
				d.setTtyModificacion(terminal);
				d.setPrgModificacion(programa);
				d.setCtaModificacion(cuenta);
			} else {
				d.setUsrCreacion(usuario);
				d.setTtyCreacion(terminal);
				d.setPrgCreacion(programa);
				d.setCtaCreacion(cuenta);
				d.setUsrModificacion(usuario);
				d.setTtyModificacion(terminal);
				d.setPrgModificacion(programa);
				d.setCtaModificacion(cuenta);
			}

			Collection<DireccionTelefono> dtCollection = d
					.getDireccionTelefonoCollection();
			for (DireccionTelefono dt : dtCollection) {
				if (dt.getSecDireccionTelefono() != null) {
					dt.setUsrModificacion(usuario);
					dt.setTtyModificacion(terminal);
					dt.setPrgModificacion(programa);
					dt.setCtaModificacion(cuenta);
				} else {
					dt.setUsrCreacion(usuario);
					dt.setTtyCreacion(terminal);
					dt.setPrgCreacion(programa);
					dt.setCtaCreacion(cuenta);
					dt.setUsrModificacion(usuario);
					dt.setTtyModificacion(terminal);
					dt.setPrgModificacion(programa);
					dt.setCtaModificacion(cuenta);
				}

			}
		}

		// direcciones electronicas
		Collection<DireccionElectronica> deLista = persona
				.getDireccionElectronicaFormularioCollection();
		for (DireccionElectronica de : deLista) {
			if (de.getSecDireccionElectronica() != null) {
				de.setUsrModificacion(usuario);
				de.setTtyModificacion(terminal);
				de.setPrgModificacion(programa);
				de.setCtaModificacion(cuenta);
			} else {
				de.setUsrCreacion(usuario);
				de.setTtyCreacion(terminal);
				de.setPrgCreacion(programa);
				de.setCtaCreacion(cuenta);
				de.setUsrModificacion(usuario);
				de.setTtyModificacion(terminal);
				de.setPrgModificacion(programa);
				de.setCtaModificacion(cuenta);
			}

		}

		// referencia
		Collection<Referencia> rLista = personaNatural
				.getReferenciaFormularioCollection();
		for (Referencia referencia : rLista) {
			if (referencia.getSecReferencia() != null) {
				referencia.setUsrModificacion(usuario);
				referencia.setTtyModificacion(terminal);
				referencia.setPrgModificacion(programa);
				referencia.setCtaModificacion(cuenta);
			} else {
				referencia.setUsrCreacion(usuario);
				referencia.setTtyCreacion(terminal);
				referencia.setPrgCreacion(programa);
				referencia.setCtaCreacion(cuenta);
				referencia.setUsrModificacion(usuario);
				referencia.setTtyModificacion(terminal);
				referencia.setPrgModificacion(programa);
				referencia.setCtaModificacion(cuenta);
			}
		}
	}

	private void llenarPersonaAuditoria(String usuario, String terminal,
			String programa, String cuenta, Persona persona) {
		persona.setUsrCreacion(usuario);
		persona.setTtyCreacion(terminal);
		persona.setPrgCreacion(programa);
		persona.setCtaCreacion(cuenta);
		persona.setUsrModificacion(usuario);
		persona.setTtyModificacion(terminal);
		persona.setPrgModificacion(programa);
		persona.setCtaModificacion(cuenta);
	}

	private void llenarPersonaNaturalAuditoria(PersonaNatural personaNatural,
			String usuario, String terminal, String programa, String cuenta) {
		personaNatural.setUsrCreacion(usuario);
		personaNatural.setTtyCreacion(terminal);
		personaNatural.setPrgCreacion(programa);
		personaNatural.setCtaCreacion(cuenta);

		personaNatural.setUsrModificacion(usuario);
		personaNatural.setTtyModificacion(terminal);
		personaNatural.setPrgModificacion(programa);
		personaNatural.setCtaModificacion(cuenta);
	}

	private String getCurrentClientIpAddress() {
		String remoteIp = "";
		try {
			HttpServletRequest request = (HttpServletRequest) PolicyContext
					.getContext("javax.servlet.http.HttpServletRequest");
			if (request != null) {
				remoteIp = request.getRemoteAddr();
			}
		} catch (PolicyContextException e) {
			System.out.println("ERROR al obtener ip:" + e);
		}
		// ServerConfigUtil.
		// String currentThreadName = Thread.currentThread().getName();
		// int begin = currentThreadName.indexOf('[') +1;
		// int end = currentThreadName.indexOf(']')-1;
		// String remoteClient = currentThreadName.substring(begin, end);
		return remoteIp;
	}

	private synchronized void crearSync(PersonaNatural personaNatural,
			Persona persona, String usuario, String ip) throws RemoteException,
			ServiceException, ErrorIngresoWsSiseException {

		System.out.println("ingresa persona denominacion:"
				+ persona.getDenominacion());

		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] params = new Object[] { personaNatural.getIdentificacion() };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		Long total = personaNaturalServicio.totalFindByCriterias(criteria);
		if (total > 0) {
			log.error("ha intentado ingresar duplicado para"
					+ personaNatural.getIdentificacion()
					+ ", ignora esta peticion...");
			return;
		}

		// ingresa persona natural sise
		// Integer personaIdSise = null;
		// try {
		// personaIdSise = insertarWsSiseMpersona(personaNatural);
		// } catch (Exception e) {
		// System.out.println("timeout sise");
		// personaIdSise = new Integer(-1);
		// }

		if (persona.getSecPersona() == null) {// cuando se cargo desde un
												// beneficiario
			personaDao.save(persona);
		} else {
			System.out.println("secpersona:" + persona.getSecPersona());
			personaDao.update(persona);
		}

		log.info("contacto preferido");

		// contacto preferido
		if (persona.getContactoPreferidoTransient().getSecContactoPreferido() == null) {
			contactoPreferidoServicio.create(persona
					.getContactoPreferidoTransient());
		} else {
			contactoPreferidoServicio.updateLight(persona
					.getContactoPreferidoTransient());
		}

		log.info("fin contacto preferido");

		log.info("inicio direcciones electronicas");
		ingresarActualizaDireccionesElectronicas(persona);
		log.info("fin direcciones electronicas");

		// lista reservada persona natural
		System.out.println("verifica lista reservada perona natural");
		verificarRiesgoPersona(personaNatural, usuario, ip, true);

		// beneficiarios
		// crearBeneficiarios(persona, usuario, ip);

		// ingresarActualizarDireccionTelefono(persona, personaIdSise, true);
		ingresarActualizarDireccionTelefono(persona, true);

		System.out.println("guardado direcciones....");

		log.info("datos conyuge");
		// conyuge
		ingresarActualizaConyuge(personaNatural, true);
		verificarRiesgoConyuge(personaNatural, usuario, ip, true);

		log.info("fin datos conyuge");

		log.info("PersonaComponenteExclusion");
		PersonaComponenteExclusion exclusion = persona
				.getPersonaComponenteExclusionTransient();
		if (exclusion != null) {
			// Transient

			personaComponenteExclusionDao.save(exclusion);
			log.info("fin PersonaComponenteExclusion");

		}

		log.info("persona natural");
		personaNaturalServicio.create(personaNatural);

		log.info("fin persona natural");

		log.info("otra ocupacion");
		ingresarActualizaOtraOcupacion(personaNatural);
		log.info("fin otra ocupaciono");

		log.info("referencias");
		ingresarActualizaReferencias(personaNatural);
		log.info("fin referencias");

		log.info("referencias b");
		ingresarActualizaReferenciasBancarias(personaNatural);
		log.info("fin referencias b");

		log.info("referencias c");
		ingresarActualizaReferenciasComerciales(personaNatural);
		log.info("fin referencias c");

		// habito enfermedad
		crearHabitoEnfermedad(personaNatural);

		// biometrica
		log.info("biometrica");
		Biometrica biometrica = personaNatural.getBiometrica();
		biometrica.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
		biometricaServicio.create(biometrica);
		log.info("fin biometrica");

		// perfil financiero
		log.info("perfil financiero");
		PerfilFinancieroNatural pfn = personaNatural
				.getPerfilFinancieroNatural();
		pfn.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
		perfilFinancieroNaturalServicio.create(pfn);
		log.info("fin perfil financiero");

		// daf
		log.info("daf");
		DetalleActividadFuncion daf = personaNatural
				.getDetalleActividadFuncion();
		daf.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
		
		detalleActividadFuncionServicio.create(daf);
		log.info("fin daf");

		// detalle pasaporte
		log.info("detalle pasaporte");
		if (personaNatural.isCasillaExtranjero()) {
			DetallePasaporte dp = personaNatural.getDetallePasaporte();
			dp.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
			detallePasaporteServicio.create(dp);
		}
		log.info("fin detalle pasaporte");

		log.info("fin crear");

	}

	private void ingresarActualizaDireccionesElectronicas(Persona persona) {

		Collection<DireccionElectronica> lista = persona
				.getDireccionElectronicaFormularioCollection();
		for (DireccionElectronica de : lista) {
			if (de.getSecDireccionElectronica() == null) {
				direccionElectronicaServicio.create(de);
			} else {
				log.info("COMPARA EMAILS");
				if (!de.equals(de.getOriginal())) {
					direccionElectronicaServicio.update(de);
					// telefonoDao.updateLight(t);
				}
			}
		}

	}

	private void ingresarActualizaReferencias(PersonaNatural personaNatural) {

		Collection<Referencia> referencias = personaNatural
				.getReferenciaFormularioCollection();

		for (Referencia r : referencias) {
			if (r.getSecReferencia() == null) {
				referenciaServicio.create(r);
			} else {
				// solo si no es el mismo objecto
				log.info("REFERENCIAS COMPARA");
				if (!r.equals(r.getOriginal())) {
					referenciaServicio.update(r);
					// telefonoDao.updateLight(t);
				}
			}
		}
	}

	private void ingresarActualizaReferenciasBancarias(
			PersonaNatural personaNatural) {

		Collection<ReferenciaBancaria> referenciasB = personaNatural
				.getReferenciaBancariaFormularioCollection();

		for (ReferenciaBancaria r : referenciasB) {
			if (r.getSecReferenciaBancaria() == null) {
				referenciaBancariaServicio.create(r);
			} else {
				// solo si no es el mismo objecto
				// log.info("REFERENCIAS BACOMPARA");
				// if (!r.equals(r.getOriginal())) {
				referenciaBancariaServicio.update(r);
				// telefonoDao.updateLight(t);
				// }
			}
		}
	}

	private void ingresarActualizaOtraOcupacion(PersonaNatural personaNatural) {

		Collection<OtraOcupacion> otraOcupacionLista = personaNatural
				.getOtraOcupacionCollection();

		for (OtraOcupacion o : otraOcupacionLista) {
			o.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
			if (o.getSecOtraOcupacion() == null) {
				otraOcupacionServicio.create(o);
			} else {
				// solo si no es el mismo objecto
				// log.info("REFERENCIAS BACOMPARA");
				// if (!r.equals(r.getOriginal())) {
				otraOcupacionServicio.update(o);
				// telefonoDao.updateLight(t);
				// }
			}
		}
	}

	private void ingresarActualizaReferenciasComerciales(
			PersonaNatural personaNatural) {

		Collection<ReferenciaComercial> referenciasC = personaNatural
				.getReferenciaComercialFormularioCollection();

		for (ReferenciaComercial r : referenciasC) {
			if (r.getSecReferenciaComercial() == null) {
				referenciaComercialServicio.create(r);
			} else {
				// solo si no es el mismo objecto
				// log.info("REFERENCIAS BACOMPARA");
				// if (!r.equals(r.getOriginal())) {
				referenciaComercialServicio.update(r);
				// telefonoDao.updateLight(t);
				// }
			}
		}
	}

	private void ingresarActualizaConyuge(PersonaNatural personaNatural,
			boolean nuevaPersona) throws RemoteException, ServiceException,
			ErrorIngresoWsSiseException {

		if (!nuevaPersona && !personaNatural.isConConyuge()) {
			System.out.println("inhabilita conyuge");
			// inhabilita todos los conyuges activos
			inactivarRelacion(personaNatural, Constantes.TIPO_RELACION_CONYUGE);
			return;
		}

		if (!nuevaPersona) {

			Persona con = obtenerConyuge(personaNatural.getPersona()
					.getSecPersona());

			if (con != null && personaNatural.isConConyuge()) {

				// entonces ya tenia conyuge antes, no hace nada, si quiere
				// cambiar
				// de conyuge debe poner primero divorciado

				con.setPersonaNaturalTransient(this
						.obtenerPersonaNaturalByPersona(con.getSecPersona()));
				PersonaNatural conPN = con.getPersonaNaturalTransient();

				// si no tenia persona natural
				if (conPN == null || conPN.getSecPersonaNatural() == null) {

					// y ahora agrega identificacion
					if (personaNatural.getConyuge().getIdentificacion() != null
							&& !personaNatural.getConyuge().getIdentificacion()
									.equals("")) {
						// entonces graba en persona natural
						Calendar c = Calendar.getInstance();
						c.set(1801, 0, 1);

						conPN = new PersonaNatural();
						con.setPersonaNaturalTransient(conPN);
						conPN.setPersona(con);
						conPN.setTipoIdentificacion(personaNatural.getConyuge()
								.getTipoIdentificacion());
						conPN.setIdentificacion(personaNatural.getConyuge()
								.getIdentificacion());
						conPN.setNumHijos(new Integer(0).shortValue());

						conPN.setApellidoMaterno(personaNatural.getConyuge()
								.getApellidoMaterno());
						conPN.setApellidoPaterno(personaNatural.getConyuge()
								.getApellidoPaterno());
						conPN.setPrimerNombre(personaNatural.getConyuge()
								.getPrimerNombre());
						conPN.setSegundoNombre(personaNatural.getConyuge()
								.getSegundoNombre());

						conPN.setFchNacimiento(c.getTime());
						// el del conyuge
						conPN.setEstadoCivil(personaNatural.getEstadoCivil());
						conPN.setPaisNacionalidad(new Pais(
								Constantes.PAIS_ID_ECUADOR));
						conPN.setCiudadNacimiento(new Ciudad(
								Constantes.CIUDAD_ND));
						conPN.setProfesion(new Profesion(
								Constantes.PROFESION_NO_DISPONIBLE));
						conPN.setOcupacion(new Ocupacion(
								Constantes.OCUPACION_ID_DEFAULT));
						conPN.setTipoRiesgo(new TipoRiesgo(
								Constantes.TIPO_RIESGO_ID_DEFAULT));
						conPN.setMntSaldoMensual(Constantes.MNT_SALDO_MENSUAL_DEFAULT);

						conPN.setMntSaldoMensual(Constantes.MNT_SALDO_MENSUAL_DEFAULT);
						conPN.setSexo(Constantes.getSexoOpuesto(personaNatural
								.getSexo()));

						personaNaturalDao.save(conPN);
					}
				}

				// si persona natural ya existia
				if (conPN != null) {
					// actualiza solo nombres y apellidos
					conPN.setApellidoMaterno(personaNatural.getConyuge()
							.getApellidoMaterno());
					conPN.setApellidoPaterno(personaNatural.getConyuge()
							.getApellidoPaterno());
					conPN.setPrimerNombre(personaNatural.getConyuge()
							.getPrimerNombre());
					conPN.setSegundoNombre(personaNatural.getConyuge()
							.getSegundoNombre());
					personaNaturalDao.update(conPN);

				}

				con.setDenominacion(personaNatural.getConyuge()
						.getApellidosNombres());
				personaDao.update(con);

				System.out
						.println("actualiza conyuge que ya estaba registrado");
				return;
			}
		}

		// continua si se va a registrar o actualizar conyuge
		if (!personaNatural.isConConyuge()) {
			return;
		}

		// valores auditoria
		String usuario = sessionContext.getCallerPrincipal().getName();
		String terminal = getCurrentClientIpAddress();
		String programa = Constantes.FORMULARIO_WEB_PROGRAMA_AUDITORIA;
		String cuenta = this.getClass().getSimpleName();

		// ingresa conyuge con identificacion
		if (personaNatural.getConyuge().getIdentificacion() != null
				&& !personaNatural.getConyuge().getIdentificacion().equals("")) {

			Relacion relacion = new Relacion();

			if (personaNatural.getConyuge().getSecPersonaNatural() != null) {
				// Entonces ya existe en la tabla persona, registrado el conyuge
				if (personaNatural.getConyuge().getPersona().getSecPersona() == null) {
					throw new RuntimeException(
							"ERROR INESPERADO: Persona natural id="
									+ personaNatural.getConyuge()
											.getSecPersonaNatural()
									+ ", no existe en persona");
				}
				relacion.setPersona1(personaNatural.getPersona());
				relacion.setPersona(personaNatural.getConyuge().getPersona());
				relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
				relacion.setTipoParentescoRelacion(new TipoParentescoRelacion(
						Constantes.TIPO_RELACION_CONYUGE));
			} else {
				// se crea una nueva persona para el conyuge en caso de q no se
				// haya encontrado en la base
				relacion.setPersona1(personaNatural.getPersona());
				Persona conyuge = new Persona();
				conyuge.setCliente(EsClienteEnum.NO.getCodigo());
				conyuge.setDenominacion(personaNatural.getConyuge()
						.getApellidosNombres());
				conyuge.setTipoIdentificacion(personaNatural.getConyuge()
						.getTipoIdentificacion());
				relacion.setPersona(conyuge);
				relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
				relacion.setTipoParentescoRelacion(new TipoParentescoRelacion(
						Constantes.TIPO_RELACION_CONYUGE));

				personaNatural.getConyuge().setPersona(conyuge);
				conyuge.setPersonaNaturalTransient(personaNatural.getConyuge());
				Calendar c = Calendar.getInstance();
				c.set(1801, 0, 1);
				conyuge.getPersonaNaturalTransient().setFchNacimiento(
						c.getTime());
				conyuge.getPersonaNaturalTransient().setEstadoCivil(
						personaNatural.getEstadoCivil());
				conyuge.getPersonaNaturalTransient().setPaisNacionalidad(
						new Pais(Constantes.PAIS_ID_ECUADOR));
				conyuge.getPersonaNaturalTransient().setCiudadNacimiento(
						new Ciudad(Constantes.CIUDAD_ND));
				conyuge.getPersonaNaturalTransient().setProfesion(
						new Profesion(Constantes.PROFESION_NO_DISPONIBLE));
				conyuge.getPersonaNaturalTransient().setOcupacion(
						new Ocupacion(Constantes.OCUPACION_ID_DEFAULT));
				conyuge.getPersonaNaturalTransient().setTipoRiesgo(
						new TipoRiesgo(Constantes.TIPO_RIESGO_ID_DEFAULT));
				conyuge.getPersonaNaturalTransient().setMntSaldoMensual(
						Constantes.MNT_SALDO_MENSUAL_DEFAULT);

				conyuge.getPersonaNaturalTransient().setMntSaldoMensual(
						Constantes.MNT_SALDO_MENSUAL_DEFAULT);
				conyuge.getPersonaNaturalTransient().setSexo(
						Constantes.getSexoOpuesto(personaNatural.getSexo()));

				llenarPersonaAuditoria(usuario, terminal, programa, cuenta,
						conyuge);

				personaDao.save(conyuge);

				llenarPersonaNaturalAuditoria(
						conyuge.getPersonaNaturalTransient(), usuario,
						terminal, programa, cuenta);
				personaNaturalDao.save(conyuge.getPersonaNaturalTransient());

			}
			relacionServicio.create(relacion);

		} else {
			// entonces no hay identificacion
			// solo se guarda solo en la tabla PERSONA (denoimnacion,tipoId)

			Relacion relacion = new Relacion();

			if (personaNatural.getConyuge().getSecPersonaNatural() != null) {
				// Entonces ya existe en la tabla persona, registrado el conyuge
				if (personaNatural.getConyuge().getPersona().getSecPersona() == null) {
					throw new RuntimeException(
							"ERROR INESPERADO: Persona natural id="
									+ personaNatural.getConyuge()
											.getSecPersonaNatural()
									+ ", no existe en persona");
				}
				relacion.setPersona1(personaNatural.getPersona());
				relacion.setPersona(personaNatural.getConyuge().getPersona());
				relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
				relacion.setTipoParentescoRelacion(new TipoParentescoRelacion(
						Constantes.TIPO_RELACION_CONYUGE));
			} else {
				// se crea una nueva persona para el conyuge en caso de q no se
				// haya encontrado en la base
				relacion.setPersona1(personaNatural.getPersona());
				Persona conyuge = new Persona();
				conyuge.setCliente(EsClienteEnum.NO.getCodigo());
				conyuge.setDenominacion(personaNatural.getConyuge()
						.getApellidosNombres());
				conyuge.setTipoIdentificacion(personaNatural.getConyuge()
						.getTipoIdentificacion());
				relacion.setPersona(conyuge);
				relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
				relacion.setTipoParentescoRelacion(new TipoParentescoRelacion(
						Constantes.TIPO_RELACION_CONYUGE));

				llenarPersonaAuditoria(usuario, terminal, programa, cuenta,
						conyuge);

				personaDao.save(conyuge);
				// personaNaturalDao.save(conyuge.getPersonaNatural());

			}
			relacionServicio.create(relacion);
		}
	}

	private void ingresarActualizarDireccionTelefono(Persona persona,
			boolean nuevaPersona) {

		Collection<Direccion> direcciones = persona.getDireccionCollection();

		Collection<DireccionTelefono> direccionTelefonosGuardar = new ArrayList<DireccionTelefono>();

		log.info("telefonos");

		// guarda telefonos nuevos
		for (Telefono t : persona.getTelefonoCollection()) {
			if (t.getSecTelefono() == null) {
				telefonoDao.save(t);
			} else {
				// solo si no es el mismo objecto
				if (!t.equals(t.getOriginal())) {
					telefonoDao.update(t);
					if (!nuevaPersona) {
						if (t.getPrincipal()) {
							persona.getContactoPreferidoTransient()
									.setTelefono(t);
						}
					}
					// telefonoDao.updateLight(t);
				}
			}

			// log.info("ingresando telefonos de direccion sise");
			// try {
			// for (DireccionTelefono direccionTelefono : direccion
			// .getDireccionTelefonoCollection()) {
			// insertarWsSiseMpersonaTelef(personaIdSise, t);
			// }
			// } catch (Exception e) {
			// System.out.println("error en ingreso de direcciones sise:" + e);
			// e.printStackTrace();
			// }
		}

		log.info("fin telefonos");

		log.info("direcciones");

		// guarda direcciones nuevas
		for (Direccion dir : direcciones) {
			if (dir.getSecDireccion() == null) {
				direccionDao.save(dir);
			} else {
				// solo si no es el mismo objecto
				System.out.println("COMPARA DIRECCION");
				if (!dir.equals(dir.getOriginal())) {
					direccionDao.update(dir);
					// direccionDao.updateLight(dir);
				}
			}
		}

		log.info("fin direcciones");

		// guarda direcciones en el sise y crea lista a guardar
		for (Direccion dir : direcciones) {
			direccionTelefonosGuardar.addAll(dir
					.getDireccionTelefonoCollection());
			// try {
			// insertarWsSiseMpersonaDir(personaIdSise, dir);
			// } catch (Exception e) {
			// System.out.println("error en ingreso de direcciones sise:" + e);
			// e.printStackTrace();
			// }

		}
		if (!nuevaPersona) {
			boolean contactoPreferidoEnDireccion = false;
			for (DireccionTelefono dt : direccionTelefonosGuardar) {
				if (dt.getTelefono().getPrincipal()) {
					persona.getContactoPreferidoTransient().setDireccion(
							dt.getDireccion());
					contactoPreferidoEnDireccion = true;
				}
			}
			if (!contactoPreferidoEnDireccion) {
				persona.getContactoPreferidoTransient().setDireccion(null);
			}
		}

		// guarda en equivida
		direccionTelefonoServicio.guardarLista(direccionTelefonosGuardar);

		log.info("fin teldir");
	}

	/**
	 * @param personaNatural
	 */
	private void crearHabitoEnfermedad(PersonaNatural personaNatural) {
		Collection<HabitoEnfermedad> todas = personaNatural
				.getHabitoEnfermedadFormularioCollection();

		PersonaNatural plight = new PersonaNatural();
		plight = new PersonaNatural(personaNatural.getSecPersonaNatural());

		if (todas != null) {
			for (HabitoEnfermedad habitoEnfermedad : todas) {

				// si el INDICADOR es diferente de aficionado o profesional,
				// entonces siempre guarda en RESPUESTA: A=afi,P=prof,N=ninguno
				Collection<DetalleHabitoEnfermedad> listG = habitoEnfermedad
						.getDetalleHabitoEnfermedadFormularioCollection();

				// if (!personaNatural.isConDatosCompletos()) {// porq sale
				// flush
				// transient
				// PersonaNatural p = new PersonaNatural(
				// personaNatural.getSecPersonaNatural());
				habitoEnfermedad.setPersonaNatural(plight);
				// }

				habitoEnfermedadDao.save(habitoEnfermedad);

				if (listG != null) {
					for (DetalleHabitoEnfermedad detalleHabitoEnfermedad : listG) {
						detalleHabitoEnfermedadDao
								.save(detalleHabitoEnfermedad);
					}
				}

			}

		}
		System.out.println("fin crear habito enfermedad");
	}

	/**
	 * @param personaNatural
	 */
	private void actualizarHabitoEnfermedad(PersonaNatural personaNatural) {
		Collection<HabitoEnfermedad> todas = personaNatural
				.getHabitoEnfermedadFormularioCollection();

		PersonaNatural plight = new PersonaNatural();
		plight = new PersonaNatural(personaNatural.getSecPersonaNatural());

		if (todas != null) {
			for (HabitoEnfermedad habitoEnfermedad : todas) {

				Collection<DetalleHabitoEnfermedad> listG = habitoEnfermedad
						.getDetalleHabitoEnfermedadFormularioCollection();

				if (habitoEnfermedad.getSecHabitoEnfermedad() == null) {
					habitoEnfermedad.setPersonaNatural(plight);
					habitoEnfermedadDao.save(habitoEnfermedad);
				} else {
					habitoEnfermedadDao.updateLight(habitoEnfermedad);
				}

				if (listG != null) {
					for (DetalleHabitoEnfermedad detalleHabitoEnfermedad : listG) {
						if (detalleHabitoEnfermedad.getSecDetalle() == null) {
							detalleHabitoEnfermedadDao
									.save(detalleHabitoEnfermedad);
						} else {
							detalleHabitoEnfermedadDao
									.update(detalleHabitoEnfermedad);
						}

					}
				}
			}
		}
	}

	/**
	 * Si es true entonces tiene riesgo
	 * 
	 * @return
	 */
	private void verificarRiesgoPersona(PersonaNatural personaNatural,
			String usuario, String ip, boolean nuevo) {

		System.out.println("verifica score persona");

		int tipoIdentificacion = WsRiesgoUtil
				.obtenerTipoIdentificacion(personaNatural
						.getTipoIdentificacion().getCodTipoIdentificacion());
		String identificacion = personaNatural.getIdentificacion();

		personaNatural.setApellidos(null);// para que vuelva a generar
		String lastname = personaNatural.getApellidos();

		try {
			ResultadoWebserviceListaReservada r = wsDatosPersonaServicio
					.validarRiesgo(personaNatural.getPrimerNombre(),
							personaNatural.getSegundoNombre(), lastname,
							tipoIdentificacion, identificacion,
							personaNatural.getTipoIdentificacion());

			if (r.isConRiesgo()) {
				personaNatural.setNombres(null);// para que vuelva a generar
				MailMessage mailMessage = emailServicio
						.prepararCorreoListasReservadas(
								usuario,
								ip,
								personaNatural.getApellidoPaterno(),
								personaNatural.getApellidoMaterno(),
								personaNatural.getNombres(),
								personaNatural.getIdentificacion(),
								PersonaRechazoListaReservadaEnum.EN_LISTA_PERSONA,
								r.getRiesgoDtoLista(), nuevo, null, null, null,
								null);

				asyncEmailServicio.encolarMail(mailMessage);

				Rcs rcs = new Rcs();
				rcs.setIdUsuario(usuario);
				rcs.setIdentificacion(identificacion);
				rcs.setTipoIdentificacion(personaNatural
						.getTipoIdentificacion());
				rcsServicio.create(rcs);
				log.info("registra log que no ha pasado score de riesgo");

			}

		} catch (RemoteException e) {
			System.out.println("error web ervice riesgo:" + e);
		} catch (ServiceException e) {
			System.out.println("error web ervice riesgo:" + e);
			// String mensaje = getBundleMensajes("error.web.service", null);
			// addErrorMessage(null, mensaje, mensaje);
		}
	}

	private void verificarRiesgoConyuge(PersonaNatural personaNatural,
			String usuario, String ip, boolean nuevo) {

		if (!personaNatural.isConConyuge()) {
			return;
		}

		PersonaNatural conyuge = personaNatural.getConyuge();

		System.out.println("verifica score conyuge"
				+ conyuge.getNombresApellidos());

		int tipoIdentificacion = WsRiesgoUtil.obtenerTipoIdentificacion(conyuge
				.getTipoIdentificacion().getCodTipoIdentificacion());
		String identificacion = conyuge.getIdentificacion();

		conyuge.setApellidos(null);// para que vuelva a generar
		String lastname = conyuge.getApellidos();

		try {
			ResultadoWebserviceListaReservada r = wsDatosPersonaServicio
					.validarRiesgo(conyuge.getPrimerNombre(),
							conyuge.getSegundoNombre(), lastname,
							tipoIdentificacion, identificacion,
							conyuge.getTipoIdentificacion());

			if (r.isConRiesgo()) {
				conyuge.setNombres(null);// para que vuelva a generar
				MailMessage mailMessage = emailServicio
						.prepararCorreoListasReservadas(
								usuario,
								ip,
								personaNatural.getApellidoPaterno(),
								personaNatural.getApellidoMaterno(),
								personaNatural.getNombres(),
								personaNatural.getIdentificacion(),
								PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_PERSONA,
								r.getRiesgoDtoLista(), nuevo,
								conyuge.getApellidoPaterno(),
								conyuge.getApellidoMaterno(),
								conyuge.getNombres(),
								conyuge.getIdentificacion());

				asyncEmailServicio.encolarMail(mailMessage);

				Rcs rcs = new Rcs();
				rcs.setIdUsuario(usuario);
				rcs.setIdentificacion(identificacion);
				rcs.setTipoIdentificacion(conyuge.getTipoIdentificacion());
				rcsServicio.create(rcs);
				log.info("registra log que no ha pasado score de riesgo");

			}

		} catch (RemoteException e) {
			System.out.println("error web ervice riesgo:" + e);
		} catch (ServiceException e) {
			System.out.println("error web ervice riesgo:" + e);
			// String mensaje = getBundleMensajes("error.web.service", null);
			// addErrorMessage(null, mensaje, mensaje);
		}
	}

	@Override
	public Persona obtenerConyuge(Integer secPersona) {

		String[] criteriasAnd = { "persona1.secPersona",
				"tipoParentescoRelacion.codTipoParentesco", "estado" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.SHORT_EQUALS, CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { secPersona,
				Constantes.TIPO_RELACION_CONYUGE, EstadoEnum.ACTIVO.getCodigo() };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<Relacion> conyugeLista = relacionServicio
				.findByCriterias(criteria);

		int total = conyugeLista.size();

		// PersonaNatural conyuge = null;
		Persona conyuge = null;

		if (total == 1) {
			conyuge = conyugeLista.get(0).getPersona();// .getPersonaNatural();
		} else {
			if (total > 1) {
				log.error("hay mas de un conyuge!!!!");
				conyuge = conyugeLista.get(0).getPersona();// .getPersonaNatural();
			}
		}
		return conyuge;
	}

	public TipoOtroSeguroServicio getTipoOtroSeguroServicio() {
		return tipoOtroSeguroServicio;
	}

	public void setTipoOtroSeguroServicio(
			TipoOtroSeguroServicio tipoOtroSeguroServicio) {
		this.tipoOtroSeguroServicio = tipoOtroSeguroServicio;
	}

	@Override
	public void actualizarPersonaNaturalFormularioWeb(
			PersonaNatural personaNatural, String usuario, String ip)
			throws EmpleoDependienteException, EmpleoIndependienteException,
			ServiceException, RemoteException, ErrorIngresoWsSiseException {

		log.info("actualizando PN ...");
		Persona persona = personaNatural.getPersona();
		IngresoMensual ingresoMensual = personaNatural.getIngresoMensual();

		if (persona == null) {
			String mensaje = "no se ha ingresado persona!";
			log.error(mensaje);
			throw new RuntimeException(mensaje);
		}

		if (ingresoMensual == null) {
			String mensaje = "no se ha ingresado ingreso mensual!";
			log.error(mensaje);
			throw new RuntimeException(mensaje);
		}

		// genera denominacion
		personaNatural.setApellidosNombres(null);// para que vuelva a generar
		persona.setDenominacion(personaNatural.getApellidosNombres());// 1erAp
																		// 2doAp
																		// 1erNom
		// 2doNom

		Collection<IngresoMensualAdicional> ingresoMensualAdicionalLista = personaNatural
				.getIngresoMensualAdicionalCollection();
		BigDecimal saldoMensual = personaNaturalServicio.calcularSaldoMensual(
				ingresoMensual, ingresoMensualAdicionalLista);
		personaNatural.setMntSaldoMensual(saldoMensual);// calculado

		// ******** ACTUALIZACION ***********************//

		// ingresa persona natural sise
		// Integer personaIdSise = insertarWsSiseMpersona(personaNatural);

		persona.setCliente(RespuestaEnum.SI.getCodigo());
		// si no esta con datos completos graba contacto preferido

		// llena valores auditoria
		llenaValoresAuditoria(personaNatural, false);

		// conyuge
		log.info("datos conyuge");
		ingresarActualizaConyuge(personaNatural, false);
		verificarRiesgoConyuge(personaNatural, usuario, ip, false);
		log.info("fin datos conyuge");

		log.info("ingresa direcciones/telefonos");
		ingresarActualizarDireccionTelefono(persona, false);
		log.info("FIN ingresa direcciones/telefonos");

		log.info("contacto preferido");
		if (persona.getContactoPreferidoTransient().getSecContactoPreferido() == null) {
			contactoPreferidoServicio.create(persona
					.getContactoPreferidoTransient());
		} else {
			contactoPreferidoServicio.updateLight(persona
					.getContactoPreferidoTransient());
		}
		log.info("fin contacto preferido");

		log.info("inicio direcciones electronicas");
		ingresarActualizaDireccionesElectronicas(persona);
		log.info("fin direcciones electronicas");

		PersonaComponenteExclusion exclusion = persona
				.getPersonaComponenteExclusionTransient();
		log.info("PersonaComponenteExclusion:");

		if (exclusion != null) {
			System.out.println(exclusion.getSecPersonaComponente());
			// Transient
			if (exclusion.getSecPersonaComponente() == null) {
				personaComponenteExclusionDao.save(exclusion);
			} else {
				personaComponenteExclusionDao.update(exclusion);
			}
		}

		log.info("fin PersonaComponenteExclusion");

		log.info("actualiza persona");
		// solo si no es el mismo objecto
		log.info("COMPARA PERSONA");
		// System.out.println(persona);
		// System.out.println(persona.getOriginal());

		if (!persona.equals(persona.getOriginal())) {
			personaDao.update(persona);
		}

		log.info("fin actualiza persona");

		// lista reservada persona natural
		System.out.println("verifica lista reservada perona natural");
		verificarRiesgoPersona(personaNatural, usuario, ip, false);

		// beneficiarios
		// crearBeneficiarios(persona, usuario, ip);

		// habito enfermedad
		// if (personaNatural.isConDatosCompletos()) {
		actualizarHabitoEnfermedad(personaNatural);
		// }
		// } else {
		// crearHabitoEnfermedad(personaNatural);
		// }

		log.info("update persona natural...");
		// solo si no es el mismo objecto
		// if (!personaNatural.equals(personaNatural.getOriginal())) {
		personaNaturalServicio.update(personaNatural);
		// }

		log.info("otra ocupacion");
		ingresarActualizaOtraOcupacion(personaNatural);
		log.info("fin otra ocupaciono");

		log.info("referencias");
		ingresarActualizaReferencias(personaNatural);
		log.info("fin referencias");

		log.info("referencias b");
		ingresarActualizaReferenciasBancarias(personaNatural);
		log.info("fin referencias b");

		log.info("referencias c");
		ingresarActualizaReferenciasComerciales(personaNatural);
		log.info("fin referencias c");

		// biometrica
		log.info("biometrica");
		Biometrica biometrica = personaNatural.getBiometrica();
		biometrica.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
		if (biometrica.getSecInformacionBiometrica() == null) {
			biometricaServicio.create(biometrica);
		} else {
			biometricaServicio.update(biometrica);
		}
		log.info("fin biometrica");

		// perfil financiero
		log.info("perfil financiero");
		PerfilFinancieroNatural pfn = personaNatural
				.getPerfilFinancieroNatural();
		pfn.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
		if (pfn.getSecPerfilFinanciero() == null) {
			perfilFinancieroNaturalServicio.create(pfn);
		} else {
			perfilFinancieroNaturalServicio.update(pfn);
		}
		log.info("perfil financiero");

		// daf
		log.info("daf");
		DetalleActividadFuncion daf = personaNatural
				.getDetalleActividadFuncion();
		daf.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
		if (daf.getSecDetalleFunciones() == null) {
			detalleActividadFuncionServicio.create(daf);
		} else {
			detalleActividadFuncionServicio.update(daf);
		}
		log.info("fin daf");

		// detallepasaporte
		log.info("detallepasaporte");
		if (personaNatural.isCasillaExtranjero()) {
			DetallePasaporte dp = personaNatural.getDetallePasaporte();
			dp.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
			if (dp.getSecDetallePasaporte() == null) {
				detallePasaporteServicio.create(dp);
			} else {
				detallePasaporteServicio.update(dp);
			}
		}
		log.info("fin detallepasaporte");

		log.debug("fin persona natural");

	}

	@Override
	public void inactivarRelacion(PersonaNatural personaPrincipal,
			Short parentezcoId) {

		// if (personaPrincipal.isConDatosCompletos()) {

		String[] criteriasAnd = { "persona1.secPersona",
				"tipoParentescoRelacion.codTipoParentesco", "estado" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.SHORT_EQUALS, CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = {
				personaPrincipal.getPersona().getSecPersona(),
				Constantes.TIPO_RELACION_CONYUGE, EstadoEnum.ACTIVO.getCodigo() };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<Relacion> conyugeLista = relacionServicio
				.findByCriterias(criteria);

		for (Relacion relacion : conyugeLista) {
			relacion.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}

		System.out.println("inactiva conyuge total:" + conyugeLista.size());

		// }
	}

	@Override
	public PersonaNatural obtenerPersonaNaturalByPersona(Integer secPersona) {

		String[] criteriasAnd = { "persona.secPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersona };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<PersonaNatural> lista = personaNaturalDao
				.findByCriterias(criteria);

		PersonaNatural personaNatural = null;

		if (lista.size() >= 1) {
			personaNatural = lista.get(0);
			if (lista.size() > 1) {
				System.out
						.println("WARN: existe mas una persona natural atada a una persona ");
			}
		}

		return personaNatural;
	}

	@Override
	public List<PersonaNatural> obtenerListadoPaginado(String numeroDocumento,
			String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, RespuestaEnum cliente,
			String ordenadoPor, boolean asc, int firstRows, int totalRows) {
		return personaNaturalDao.obtenerListadoPaginado(numeroDocumento,
				apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre,
				cliente, ordenadoPor, asc, firstRows, totalRows);
	}

	@Override
	public Long obtenerTotalListadoPaginado(String numeroDocumento,
			String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, RespuestaEnum cliente) {
		return personaNaturalDao.obtenerTotalListadoPaginado(numeroDocumento,
				apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre,
				cliente);
	}

	public DetallePasaporteServicio getDetallePasaporteServicio() {
		return detallePasaporteServicio;
	}

	public void setDetallePasaporteServicio(
			DetallePasaporteServicio detallePasaporteServicio) {
		this.detallePasaporteServicio = detallePasaporteServicio;
	}

	@Override
	public PersonaNatural obtenerPersonaNaturalByDocumento(
			String numeroDocumento) {

		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { numeroDocumento };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<PersonaNatural> lista = personaNaturalDao
				.findByCriterias(criteria);

		PersonaNatural pn = null;

		if (lista.size() > 0) {
			pn = lista.get(0);
			if (lista.size() > 1) {
				System.out
						.println("ERROR: Existe mas de una persona natural con el documento:"
								+ numeroDocumento);
			}
		}

		return pn;
	}
}