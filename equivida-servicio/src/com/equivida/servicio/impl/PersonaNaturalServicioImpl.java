package com.equivida.servicio.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.log4j.Logger;

import com.equivida.comparator.ReferenciaComparator;
import com.equivida.constant.Constantes;
import com.equivida.constant.EsClienteEnum;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.GanadoPerdidoEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoEmpleoEnum;
import com.equivida.dao.DetalleHabitoEnfermedadDao;
import com.equivida.dao.DireccionDao;
import com.equivida.dao.DireccionTelefonoDao;
import com.equivida.dao.HabitoEnfermedadDao;
import com.equivida.dao.PersonaComponenteExclusionDao;
import com.equivida.dao.PersonaDao;
import com.equivida.dao.PersonaNaturalDao;
import com.equivida.dao.TelefonoDao;
import com.equivida.dto.EmpleoDto;
import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.helper.HttpHelper;
import com.equivida.mensajeria.AsyncEmailServicio;
import com.equivida.mensajeria.EmailServicio;
import com.equivida.modelo.Actividad;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Biometrica;
import com.equivida.modelo.Canton;
import com.equivida.modelo.CategoriaPpe;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.CompaniaSeguro;
import com.equivida.modelo.ContactoPreferido;
import com.equivida.modelo.DetalleActividadFuncion;
import com.equivida.modelo.DetalleHabitoEnfermedad;
import com.equivida.modelo.DetallePasaporte;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.EmpleoDependiente;
import com.equivida.modelo.EmpleoIndependiente;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.EstadoPersona;
import com.equivida.modelo.HabitoEnfermedad;
import com.equivida.modelo.HistoriaMedicaFamiliar;
import com.equivida.modelo.IngresoAnual;
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
import com.equivida.modelo.RamoSeguro;
import com.equivida.modelo.Referencia;
import com.equivida.modelo.ReferenciaBancaria;
import com.equivida.modelo.ReferenciaComercial;
import com.equivida.modelo.Relacion;
import com.equivida.modelo.RucPersonaNatural;
import com.equivida.modelo.SeguroAdicional;
import com.equivida.modelo.SeguroVigente;
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
import com.equivida.modelo.TipoServicioInstFin;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.BiometricaServicio;
import com.equivida.servicio.ContactoPreferidoServicio;
import com.equivida.servicio.DetalleActividadFuncionServicio;
import com.equivida.servicio.DetallePasaporteServicio;
import com.equivida.servicio.DireccionElectronicaServicio;
import com.equivida.servicio.DireccionTelefonoServicio;
import com.equivida.servicio.HijoServicio;
import com.equivida.servicio.MotivoSeguroServicio;
import com.equivida.servicio.OtraOcupacionServicio;
import com.equivida.servicio.PerfilFinancieroNaturalServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.ReferenciaBancariaServicio;
import com.equivida.servicio.ReferenciaComercialServicio;
import com.equivida.servicio.ReferenciaServicio;
import com.equivida.servicio.RelacionServicio;
import com.equivida.servicio.RucPersonaNaturalServicio;
import com.equivida.servicio.TipoActividadServicio;
import com.equivida.servicio.TipoMotivoSeguroServicio;
import com.equivida.servicio.TipoOtroSeguroServicio;
import com.equivida.servicio.TipoServicioInstFinServicio;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.equivida.util.EquivalenciaKilosLibras;
import com.equivida.util.PersonaUtil;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PersonaNaturalServicio")
public class PersonaNaturalServicioImpl extends GenericServiceImpl<PersonaNatural, Integer>
		implements PersonaNaturalServicio {

	private Logger log = Logger.getLogger(PersonaNaturalServicioImpl.class);

	@Resource
	private SessionContext sessionContext;// para auditoria

	@EJB
	private PersonaNaturalDao personaNaturalDao;

	@EJB
	private TipoServicioInstFinServicio tipoServicioInstFinServicio;

	@EJB
	private RucPersonaNaturalServicio rucPersonaNaturalServicio;

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
	private MotivoSeguroServicio motivoSeguroServicio;

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

	@EJB
	private HijoServicio hijoServicio;

	public GenericDao<PersonaNatural, Integer> getDao() {
		return personaNaturalDao;
	}

	@Override
	public BigDecimal calcularSaldoMensual(IngresoMensual ingresoMensual,
			Collection<IngresoMensualAdicional> ingresoMensualAdicionalLista) {

		if (ingresoMensual == null) {
			return BigDecimal.ZERO;
		}

		BigDecimal totalIngresos = BigDecimal.valueOf(0);
		BigDecimal totalEgresos = BigDecimal.valueOf(0);
		BigDecimal saldoTotal = BigDecimal.valueOf(0);

		if (ingresoMensual.getMntIngresoMensual() == null) {
			ingresoMensual.setMntIngresoMensual(BigDecimal.valueOf(0));
		}
		if (ingresoMensual.getMntEgresoMensual() == null) {
			ingresoMensual.setMntEgresoMensual(BigDecimal.valueOf(0));
		}

		totalIngresos = totalIngresos.add(ingresoMensual.getMntIngresoMensual());
		totalEgresos = totalEgresos.add(ingresoMensual.getMntEgresoMensual());

		if (ingresoMensualAdicionalLista != null && !ingresoMensualAdicionalLista.isEmpty()) {
			for (IngresoMensualAdicional ingresoMensualAdicional : ingresoMensualAdicionalLista) {
				totalIngresos = totalIngresos.add(ingresoMensualAdicional.getMntIngresoAdicional() != null
						? ingresoMensualAdicional.getMntIngresoAdicional()
						: BigDecimal.ZERO);
				totalEgresos = totalEgresos.add(ingresoMensualAdicional.getMntEgresoAdicional() != null
						? ingresoMensualAdicional.getMntEgresoAdicional()
						: BigDecimal.ZERO);
			}
		}

		saldoTotal = totalIngresos.subtract(totalEgresos);
		saldoTotal = saldoTotal.setScale(2, BigDecimal.ROUND_HALF_UP);

		return saldoTotal;
	}

	@Override
	public PersonaNatural findByPkParaFormularioWeb(String noDocumento) {
		PersonaNatural personaNatural = personaNaturalDao.obtenerPersonaNaturalByDocumento(noDocumento);

		if (personaNatural != null) {
			inicializarParaFormulario(personaNatural);
		}

		return personaNatural;
	}

	@Override
	public PersonaNatural findByPkParaFormularioWeb(Integer secPersonaNatural) {
		PersonaNatural personaNatural = this.findByPk(secPersonaNatural);
		inicializarParaFormulario(personaNatural);

		return personaNatural;
	}

	/**
	 * Inicializa para formulario.
	 * 
	 * @param personaNatural
	 */
	private void inicializarParaFormulario(PersonaNatural personaNatural) {
		// para que pueda copiar el date (nullo) en BeanUtils.copyProperties
		java.util.Date defaultValue = null;
		java.math.BigDecimal bdDefaultValue = null;

		BigDecimalConverter bdconverter = new BigDecimalConverter(bdDefaultValue);
		DateConverter converter = new DateConverter(defaultValue);

		ConvertUtils.register(converter, java.util.Date.class);
		ConvertUtils.register(bdconverter, java.math.BigDecimal.class);

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
		Persona conyuge = obtenerConyuge(personaNatural.getPersona().getSecPersona());
		if (conyuge != null) {

			PersonaNatural pnconyuge = obtenerPersonaNaturalByPersona(conyuge.getSecPersona());
			conyuge.setPersonaNaturalTransient(pnconyuge);

			// si esta guardado en persona natural
			if (conyuge.getPersonaNaturalTransient() != null
					&& conyuge.getPersonaNaturalTransient().getSecPersonaNatural() != null) {
				personaNatural.setConyuge(conyuge.getPersonaNaturalTransient());
			} else {
				// si solo esta en persona
				PersonaNatural pnC = new PersonaNatural();
				pnC.setPersona(conyuge);
				pnC.setTipoIdentificacion(conyuge.getTipoIdentificacion());
				personaNatural.setConyuge(pnC);
			}
		} else {
			// conyuge = new Persona();
			// PersonaNatural pnC = new PersonaNatural();
			// pnC.setPersona(conyuge);
			// pnC.setTipoIdentificacion(new TipoIdentificacion(
			// TipoIdentificacionEnum.CEDULA.getCodigo()));
			// personaNatural.setConyuge(pnC);
			personaNatural.setConyuge(null);
			personaNatural.setTieneConyuge(false);
		}

		// hijos
		personaNatural.getPersona().setHijos(hijoServicio.obtenerPorPadre(personaNatural.getSecPersonaNatural()));

		ContactoPreferido contactoPreferido = iniciarDirecciones(personaNatural);

		// inicializa telefonos
		if (personaNatural.getPersona().getTelefonoCollection() != null)
			personaNatural.getPersona().getTelefonoCollection().size();

		// telefonos adicionales
		iniciarTelefonosAdicionales(personaNatural, contactoPreferido);

		// personaNatural.getPersona().getDireccionElectronicaCollection().size();
		Collection<DireccionElectronica> deList = direccionElectronicaServicio
				.obtenerPorPersona(personaNatural.getPersona().getSecPersona());

		for (DireccionElectronica de : deList) {
			de.setTipoDireccionElectronica(
					new TipoDireccionElectronica(de.getTipoDireccionElectronica().getCodTipoDireccionElectronica()));
		}

		personaNatural.getPersona().setDireccionElectronicaFormularioCollection(deList);
		// personaNatural.getPersona()
		// .getDireccionElectronicaCollection();
		ponerDireccioneElectronicaOriginal(deList);

		if (personaNatural.getIngresoMensualAdicionalCollection() != null)
			personaNatural.getIngresoMensualAdicionalCollection().size();

		if (personaNatural.getIngresoAnualCollection() != null)
			personaNatural.getIngresoAnualCollection().size();

		List<Referencia> refCollection = referenciaServicio
				.obtenerPorPersonaNatural(personaNatural.getSecPersonaNatural());

		Collections.sort(refCollection, new ReferenciaComparator());

		// Referencias personales
		List<Referencia> referenciaLista = new ArrayList<Referencia>();

		for (Referencia r : refCollection) {
			r.setTipoParentescoRelacion(
					new TipoParentescoRelacion(r.getTipoParentescoRelacion().getCodTipoParentesco()));
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
			Short secTipoFinaciera = rb.getInstitucionFinanciera().getTipoInstitucionFinanciera()
					.getSecTipoFinanciera();
			System.out.println("secTipoFinaciera:" + secTipoFinaciera);
			rb.setInstitucionFinanciera(
					new InstitucionFinanciera(rb.getInstitucionFinanciera().getSecInstitucionFinanciera(),
							rb.getInstitucionFinanciera().getInstitucionFinanciera(),
							new TipoInstitucionFinanciera(
									rb.getInstitucionFinanciera().getTipoInstitucionFinanciera().getSecTipoFinanciera(),
									rb.getInstitucionFinanciera().getTipoInstitucionFinanciera().getTipoFinanciera())));
			rb.setTipoServicioInstFin(new TipoServicioInstFin(rb.getTipoServicioInstFin().getSecTipoServicioInstFin()));
			if (secTipoFinaciera != null) {
				rb.setTipoServicioInstFinServicioLista(
						tipoServicioInstFinServicio.obtenerPorTipoFinanciera(secTipoFinaciera));
			}

			referenciaBLista.add(rb);
		}

		int llenarFaltaBancaria = 2 - referenciaBLista.size();

		if (llenarFaltaBancaria > 0) {
			for (int i = 0; i < llenarFaltaBancaria; i++) {
				ReferenciaBancaria rb = new ReferenciaBancaria();
				rb.setInstitucionFinanciera(new InstitucionFinanciera(new TipoInstitucionFinanciera()));
				rb.setPersonaNatural(personaNatural);
				rb.setTipoServicioInstFin(new TipoServicioInstFin());
				referenciaBLista.add(rb);
			}
		}

		personaNatural.setReferenciaBancariaFormularioCollection(referenciaBLista);

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

		personaNatural.setReferenciaComercialFormularioCollection(refCCollection);

		if (personaNatural.getOtroEmpleoCollection() != null)
			personaNatural.getOtroEmpleoCollection().size();

		// personaNatural.getSegurosVigentesCollection().size();

		Collection<SeguroVigente> seguroVigentes = personaNatural.getSegurosVigentesCollection();
		if (seguroVigentes != null)
			for (SeguroVigente s : seguroVigentes) {
				s.setCompaniaSeguro(new CompaniaSeguro(s.getCompaniaSeguro().getSecCiaSeguro()));
				s.setRamoSeguro(new RamoSeguro(s.getRamoSeguro().getSecTipoRamo()));
			}

		if (personaNatural.getDeportePracticaCollection() != null) {
			personaNatural.getDeportePracticaCollection().size();
		}

		int totalPeActivos = 0;

		Collection<PersonaPe> personaPeCollecction = personaNatural.getPersonaPeCollection();

		if (personaPeCollecction != null)
			for (PersonaPe personaPe : personaPeCollecction) {
				// el bug de selectonemenu
				personaPe.setCategoriaPpe(new CategoriaPpe(personaPe.getCategoriaPpe().getCodCategoriaPpe()));
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

		Collection<EmpleoDependiente> dependientes = personaNatural.getEmpleoDependienteCollection();
		System.out.println("personaNatural.getEmpleoDependienteCollection() "
				+ personaNatural.getEmpleoDependienteCollection().size());

		if (dependientes != null)
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
				dto.setActividadEconomicaId(empleoDependiente.getActividadEconomica().getCodActividadEconomica());
				dto.setActividadEconomicaNombre(empleoDependiente.getActividadEconomica().getActividadEconomica());

				revisarSinActividadEconomica(dto);
				empleoDtoList.add(dto);
			}

		Collection<EmpleoIndependiente> independientes = personaNatural.getEmpleoIndependienteCollection();

		if (independientes != null)
			for (EmpleoIndependiente empleoIndependiente : independientes) {
				EmpleoDto dto = new EmpleoDto();
				dto.setSecEmpleo(empleoIndependiente.getSecEmpleoIndependiente());
				dto.setEstado(empleoIndependiente.getEstado());
				dto.setCargo(empleoIndependiente.getCargo());
				dto.setTiempoEmpresa(empleoIndependiente.getTiempoEmpresa());
				dto.setCodTiempo(empleoIndependiente.getCodTiempo());
				dto.setTipoEmpleo(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());
				dto.setTipoEmpleoNombre(TipoEmpleoEnum.INDEPENDIENTE.getKeyBundle());
				dto.setActividadEconomicaId(empleoIndependiente.getActividadEconomica().getCodActividadEconomica());
				dto.setActividadEconomicaNombre(empleoIndependiente.getActividadEconomica().getActividadEconomica());

				revisarSinActividadEconomica(dto);
				empleoDtoList.add(dto);
			}

		Collection<OtraOcupacion> otraOcupacionLista = otraOcupacionServicio
				.obtenerPorPersonaNatural(personaNatural.getSecPersonaNatural());

		if (otraOcupacionLista != null)
			for (OtraOcupacion otraOcupacion : otraOcupacionLista) {
				EmpleoDto dto = new EmpleoDto();
				dto.setSecEmpleo(otraOcupacion.getSecOtraOcupacion());
				dto.setEstado(otraOcupacion.getEstado());
				dto.setTiempoEmpresa(otraOcupacion.getTiempoOcupacion());
				dto.setCodTiempo(otraOcupacion.getCodTiempo());
				dto.setTipoEmpleo(otraOcupacion.getTipoOtraOcupacion().getCodOtraOcupacion());
				dto.setTipoEmpleoNombre(otraOcupacion.getTipoOtraOcupacion().getOtraOcupacion());

				empleoDtoList.add(dto);
			}

		personaNatural.setEmpleoCollection(empleoDtoList);

		personaNatural.setIdentificacionOriginal(personaNatural.getIdentificacion().toString());

		// actividades

		Collection<Actividad> actividadBDDList = personaNatural.getActividadCollection();

		List<TipoActividad> tipoActividadLista = tipoActividadServicio.findAll();

		System.out.println("tipo actividad:" + tipoActividadLista.size());

		List<Actividad> actividadLista = new ArrayList<Actividad>();// la lista
																	// final

		// itero todos los tipos
		for (TipoActividad tipoActividad : tipoActividadLista) {

			Actividad act = null;
			// busco si ese tipo ya esta en la base para esa persona
			if (actividadBDDList != null)
				for (Actividad bdd : actividadBDDList) {
					if (bdd.getTipoActividad().getCodActividad().compareTo(tipoActividad.getCodActividad()) == 0) {
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
				personaNatural.getInformacionAdicional().setConduceMoto(RespuestaEnum.SI.getCodigo());
			} else {
				personaNatural.getInformacionAdicional().setConduceMoto(RespuestaEnum.NO.getCodigo());
			}

			if (personaNatural.getInformacionAdicional().getAccidentes() != null
					&& !personaNatural.getInformacionAdicional().getAccidentes().equals("")) {
				personaNatural.getInformacionAdicional().setRespuestaSN(RespuestaEnum.SI.getCodigo());
			} else {
				personaNatural.getInformacionAdicional().setRespuestaSN(RespuestaEnum.NO.getCodigo());
			}

		}

		// motivo seguro

		// Collection<MotivoSeguro> msList = motivoSeguroServicio
		// .obtenerPorPersona(personaNatural.getPersona().getSecPersona());
		// personaNatural.getPersona()
		// .setMotivoSeguroFormularioCollection(msList);

		Collection<MotivoSeguro> motivoBDDList = motivoSeguroServicio
				.obtenerPorPersona(personaNatural.getPersona().getSecPersona());

		List<TipoMotivoSeguro> tipoMotivoSeguroLista = tipoMotivoSeguroServicio.findAll();

		PersonaUtil.inicializarMotivosSeguro(personaNatural, tipoMotivoSeguroLista, motivoBDDList);

		// otro seguros adicionales
		List<TipoOtroSeguro> otrosSegurosAll = tipoOtroSeguroServicio.findAll();

		Collection<SeguroAdicional> bddOSList = personaNatural.getSeguroAdicionalCollection();

		Collection<SeguroAdicional> finalOSList = new ArrayList<SeguroAdicional>();

		for (TipoOtroSeguro tos : otrosSegurosAll) {

			SeguroAdicional sa = null;
			if (bddOSList != null)
				for (SeguroAdicional bdd : bddOSList) {
					if (bdd.getTipoOtroSeguro().getSecTipoAdicional().compareTo(tos.getSecTipoAdicional()) == 0) {
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
				.buscarPorSecPersonaNatural(personaNatural.getSecPersonaNatural());
		personaNatural.setPerfilFinancieroNatural(pfn);

		// detalleactividadfuncion
		DetalleActividadFuncion daf = detalleActividadFuncionServicio
				.buscarPorSecPersonaNatural(personaNatural.getSecPersonaNatural());
		personaNatural.setDetalleActividadFuncion(daf);

		// detallepasaporte
		DetallePasaporte dp = detallePasaporteServicio
				.buscarPorSecPersonaNatural(personaNatural.getSecPersonaNatural());
		personaNatural.setDetallePasaporte(dp);

		// biometrica
		Biometrica biometrica = biometricaServicio.buscarPorSecPersonaNatural(personaNatural.getSecPersonaNatural());
		personaNatural.setBiometrica(biometrica);

		if (personaNatural.getBiometrica() != null) {

			BigDecimal pesoKilos = personaNatural.getBiometrica().getPeso();
			personaNatural.getBiometrica().setPesoKilos(pesoKilos);
			personaNatural.getBiometrica().setPesoLibras(EquivalenciaKilosLibras.getLibras(pesoKilos));

			BigDecimal pesoKilosDiferencia = personaNatural.getBiometrica().getDiferenciaUltimoAnio();
			if (personaNatural.getBiometrica().getGanadoPerdido() == GanadoPerdidoEnum.GANADO.getCodigo()) {
				personaNatural.getBiometrica().setGanadosUltAnioKilos(pesoKilosDiferencia);
				personaNatural.getBiometrica()
						.setGanadosUltAnioLibras(EquivalenciaKilosLibras.getLibras(pesoKilosDiferencia));
			}

			if (personaNatural.getBiometrica().getGanadoPerdido() == GanadoPerdidoEnum.PERDIDO.getCodigo()) {
				personaNatural.getBiometrica().setPerdidosUltAnioKilos(pesoKilosDiferencia);
				personaNatural.getBiometrica()
						.setPerdidosUltAnioLibras(EquivalenciaKilosLibras.getLibras(pesoKilosDiferencia));
			}

		} // fin biometrica

		Collection<HistoriaMedicaFamiliar> bddList = personaNatural.getHistoriaMedicaFamiliarCollection();

		List<HistoriaMedicaFamiliar> hmfList = new ArrayList<HistoriaMedicaFamiliar>();

		if (bddList != null)
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

		if (hmfList != null)
			for (HistoriaMedicaFamiliar hmf : hmfList) {
				hmf.setTipoParentescoRelacion(
						new TipoParentescoRelacion(hmf.getTipoParentescoRelacion().getCodTipoParentesco()));
			}

		personaNatural.setHistoriaMedicaFamiliarFormularioCollection(hmfList);

		// estado persona
		Collection<EstadoPersona> epLista = personaNatural.getEstadoPersonaCollection();

		if (epLista != null)
			for (EstadoPersona ep : epLista) {
				// bug
				ep.setTipoEstado(new TipoEstado(ep.getTipoEstado().getCodEstado()));
			}

		// personaexclusion
		personaNatural.getPersona().setPersonaComponenteExclusionTransient(
				llenarPersonaExclusion(personaNatural.getPersona().getSecPersona()));

		// carga rucpersonanatural
		if (personaNatural.getRucPersonaNaturalCollection() != null) {
			System.out.println("personaNatural.getRucPersonaNaturalCollection(): "
					+ personaNatural.getRucPersonaNaturalCollection().size());
		}

		// medicoConsultadoCollection
		if (personaNatural.getMedicoConsultadoCollection() != null) {
			System.out.println("personaNatural.getMedicoConsultadoCollection(): "
					+ personaNatural.getMedicoConsultadoCollection().size());
		}

	}

	private void revisarSinActividadEconomica(EmpleoDto dto) {
		if (dto.getActividadEconomicaNombre() != null
				&& dto.getActividadEconomicaNombre().startsWith("Sin actividad econ")) {
			dto.setActividadEconomicaId(null);
			dto.setActividadEconomicaNombre("");
		}
	}

	private void ponerDireccioneElectronicaOriginal(Collection<DireccionElectronica> deList) {
		for (DireccionElectronica de : deList) {

			// auditoria telefono con direccion
			DireccionElectronica original = new DireccionElectronica();

			try {
				BeanUtils.copyProperties(original, de);

				original.setTipoDireccionElectronica(new TipoDireccionElectronica(
						de.getTipoDireccionElectronica().getCodTipoDireccionElectronica()));

				de.setOriginal(original);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}

		}
	}

	private PersonaComponenteExclusion llenarPersonaExclusion(Integer secPersona) {

		PersonaComponenteExclusion exclusion = null;

		String[] criteriasAnd = { "persona.secPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] params = new Object[] { secPersona };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<PersonaComponenteExclusion> eCollection = personaComponenteExclusionDao.findByCriterias(criteria);

		if (eCollection.size() >= 1) {
			exclusion = eCollection.get(0);
		}

		return exclusion;
	}

	public void crearPersonaNaturalFormularioWeb(PersonaNatural personaNatural, String usuario, String ip)
			throws EmpleoDependienteException, EmpleoIndependienteException, ServiceException, RemoteException,
			ErrorIngresoWsSiseException {

		log.info("creando solicitud PN ..." + personaNatural.getNombresApellidos());
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

		// pone como cliente
		personaNatural.getPersona().setCliente(EsClienteEnum.SI.getCodigo());

		// verifica ciudad
		if (personaNatural.getCiudadNacimiento() == null
				|| personaNatural.getCiudadNacimiento().getSecCiudad() == null) {
			personaNatural.setCiudadNacimiento(new Ciudad(Constantes.CIUDAD_ND));
		}

		Collection<IngresoMensualAdicional> ingresoMensualAdicionalLista = personaNatural
				.getIngresoMensualAdicionalCollection();
		BigDecimal saldoMensual = personaNaturalServicio.calcularSaldoMensual(ingresoMensual,
				ingresoMensualAdicionalLista);
		personaNatural.setMntSaldoMensual(saldoMensual);// calculado

		// llena valores auditoria
		llenaValoresAuditoria(personaNatural, true);

		// de forma sincronizada envia a guardar
		crearSync(personaNatural, persona, usuario, ip, true);
	}

	private void llenaValoresAuditoria(PersonaNatural personaNatural, boolean nuevo) {
		String usuario = null;
		System.out.println("SECCCCC");

		try {
			usuario = sessionContext.getCallerPrincipal().getName();
		} catch (Exception e) {
			// entonces viene de Crm
			usuario = Constantes.USUARIO_CRM;
		}
		System.out.println("user auditoria:" + usuario);

		String terminal = HttpHelper.getCurrentClientIpAddress();
		String programa = Constantes.FORMULARIO_WEB_PROGRAMA_AUDITORIA;
		String cuenta = this.getClass().getSimpleName();
		Persona persona = personaNatural.getPersona();
		// persona y persona natural
		if (nuevo) {
			llenarPersonaNaturalAuditoria(personaNatural, usuario, terminal, programa, cuenta);
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

		if (persona != null && persona.getTelefonoCollection() != null && !persona.getTelefonoCollection().isEmpty()) {
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
		}

		if (direcciones != null && !direcciones.isEmpty()) {
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

				Collection<DireccionTelefono> dtCollection = d.getDireccionTelefonoCollection();
				if (dtCollection != null) {
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
			}
		}

		// direcciones electronicas
		Collection<DireccionElectronica> deLista = persona.getDireccionElectronicaFormularioCollection();
		if (deLista != null && !deLista.isEmpty()) {
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
		}

		// referencia
		Collection<Referencia> rLista = personaNatural.getReferenciaFormularioCollection();
		if (rLista != null && !rLista.isEmpty()) {
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
	}

	private void llenarPersonaAuditoria(String usuario, String terminal, String programa, String cuenta,
			Persona persona) {
		persona.setUsrCreacion(usuario);
		persona.setTtyCreacion(terminal);
		persona.setPrgCreacion(programa);
		persona.setCtaCreacion(cuenta);
		persona.setUsrModificacion(usuario);
		persona.setTtyModificacion(terminal);
		persona.setPrgModificacion(programa);
		persona.setCtaModificacion(cuenta);
	}

	private void llenarPersonaNaturalAuditoria(PersonaNatural personaNatural, String usuario, String terminal,
			String programa, String cuenta) {
		personaNatural.setUsrCreacion(usuario);
		personaNatural.setTtyCreacion(terminal);
		personaNatural.setPrgCreacion(programa);
		personaNatural.setCtaCreacion(cuenta);

		personaNatural.setUsrModificacion(usuario);
		personaNatural.setTtyModificacion(terminal);
		personaNatural.setPrgModificacion(programa);
		personaNatural.setCtaModificacion(cuenta);
	}

	private synchronized void crearSync(PersonaNatural personaNatural, Persona persona, String usuario, String ip,
			boolean persisteConyuge) throws RemoteException, ServiceException, ErrorIngresoWsSiseException {

		System.out.println("identificacion persona natural: " + personaNatural.getIdentificacion());
		System.out.println("tipo identificacion persona natural: "
				+ personaNatural.getTipoIdentificacion().getCodTipoIdentificacion());

		if (personaNatural.getIdentificacion().length() == 13 && personaNatural.getTipoIdentificacion() != null
				&& personaNatural.getTipoIdentificacion().isRuc()) {

			System.out.println("agregando a ruc persona natural.. ");

			RucPersonaNatural rucPersonaNatural = new RucPersonaNatural();
			rucPersonaNatural.setTipoIdentificacion(new TipoIdentificacion('1'));
			rucPersonaNatural.setIdentificacion(personaNatural.getIdentificacion());
			if (personaNatural.getRucPersonaNaturalCollection() == null) {
				personaNatural.setRucPersonaNaturalCollection(new ArrayList<RucPersonaNatural>());
			}
			personaNatural.getRucPersonaNaturalCollection().add(rucPersonaNatural);
		}

		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		personaNatural.setIdentificacion(
				personaNatural.getIdentificacion() != null && personaNatural.getIdentificacion().length() > 10
						? personaNatural.getIdentificacion().substring(0, 10)
						: personaNatural.getIdentificacion());
		Object[] params = new Object[] { personaNatural.getIdentificacion() };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		System.out.println("PERSONA NATURAL OBJECT " + personaNatural);

		if (personaNatural != null && personaNatural.getRucPersonaNaturalCollection() != null
				&& !personaNatural.getRucPersonaNaturalCollection().isEmpty()) {
			System.out.println("PERSONA NATURAL RUC COLLECTION " + personaNatural.getRucPersonaNaturalCollection());
			log.info("RUC PARA PERSONA NATURAL ES: "
					+ personaNatural.getRucPersonaNaturalCollection().get(0).getIdentificacion());
		}

		Long total = personaNaturalServicio.totalFindByCriterias(criteria);
		if (total > 0) {
			String m = "ha intentado ingresar duplicado para " + personaNatural.getIdentificacion()
					+ ", ignora esta peticion...";
			log.error(m);
			personaNatural.setError(m);
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
		if (persona.getContactoPreferidoTransient() != null) {
			if (persona.getContactoPreferidoTransient().getSecContactoPreferido() == null) {
				if (persona.getContactoPreferidoTransient() != null
						&& persona.getContactoPreferidoTransient().getPersona() != null
						&& persona.getContactoPreferidoTransient().getTipoContactoPreferido() != null) {
					contactoPreferidoServicio.create(persona.getContactoPreferidoTransient());
				}
			} else {
				contactoPreferidoServicio.updateLight(persona.getContactoPreferidoTransient());
			}
		}

		log.info("fin contacto preferido");

		log.info("inicio direcciones electronicas");
		ingresarActualizaDireccionesElectronicas(persona);
		log.info("fin direcciones electronicas");

		log.info("inicio motivos seguro");
		ingresarActualizaMotivosSeguro(persona);
		log.info("fin motivos seguro");

		// lista reservada persona natural
		// System.out.println("verifica lista reservada perona natural");
		// verificarRiesgoPersona(personaNatural, usuario, ip, true);

		// beneficiarios
		// crearBeneficiarios(persona, usuario, ip);

		// ingresarActualizarDireccionTelefono(persona, personaIdSise, true);
		ingresarActualizarDireccionTelefono(persona, true);

		System.out.println("guardado direcciones....");

		log.info("datos conyuge");
		// conyuge
		if (persisteConyuge) {
			ingresarActualizaConyuge(personaNatural, true);
		}
		// verificarRiesgoConyuge(personaNatural, usuario, ip, true);

		log.info("fin datos conyuge");

		log.info("PersonaComponenteExclusion");
		PersonaComponenteExclusion exclusion = persona.getPersonaComponenteExclusionTransient();
		if (exclusion != null) {
			// Transient

			personaComponenteExclusionDao.save(exclusion);
			log.info("fin PersonaComponenteExclusion");

		}

		log.info("persona natural");
		if (personaNatural.getCiudadNacimiento() == null
				|| personaNatural.getCiudadNacimiento().getSecCiudad() == null) {
			Ciudad ciudadNacimineto = new Ciudad(new Short("0"));
			personaNatural.setCiudadNacimiento(ciudadNacimineto);
		}

		if (personaNatural.getProfesion() == null || personaNatural.getProfesion().getSecProfesion() == null) {
			Profesion profesion = new Profesion(new Short("0"));
			personaNatural.setProfesion(profesion);
		}

		if (personaNatural.getOcupacion() == null || personaNatural.getOcupacion().getCodOcupacion() == null) {
			Ocupacion ocupacion = new Ocupacion(new Short("0"));
			personaNatural.setOcupacion(ocupacion);
		}

		if (personaNatural.getTipoRiesgo() == null || personaNatural.getTipoRiesgo().getCodTipoRiesgo() == null) {
			TipoRiesgo tipo = new TipoRiesgo(new Short("0"));
			personaNatural.setTipoRiesgo(tipo);
		}

		if (personaNatural.getMntSaldoMensual() == null) {
			personaNatural.setMntSaldoMensual(BigDecimal.ZERO);
		}

		// estado civil 0
		// 56 nacionalidad
		// 1800-01-01

		if (personaNatural.getEstadoCivil() == null) {
			EstadoCivil estadoCivil = new EstadoCivil(new Short("0"));
			personaNatural.setEstadoCivil(estadoCivil);
		}

		if (personaNatural.getPaisNacionalidad() == null) {
			Pais pais = new Pais(new Short("56"));
			personaNatural.setPaisNacionalidad(pais);
		}

		if (personaNatural.getFchNacimiento() == null) {
			Calendar fechaNacim = Calendar.getInstance();
			fechaNacim.set(1900, Calendar.JANUARY, 1);
			personaNatural.setFchNacimiento(fechaNacim.getTime());
		}

		if (personaNatural.getEmpleoDependienteCollection() != null
				&& !personaNatural.getEmpleoDependienteCollection().isEmpty()) {
			for (EmpleoDependiente ed : personaNatural.getEmpleoDependienteCollection()) {
				if (ed.getActividadEconomica() == null
						|| ed.getActividadEconomica().getCodActividadEconomica() == null) {
					ed.setActividadEconomica(new ActividadEconomica(new Short("0")));
				}

				if (ed.getTiempoEmpresa() == null) {
					ed.setTiempoEmpresa(BigDecimal.ZERO);
				}

				ed.setEstado('A');
			}
		}

		if (personaNatural.getEmpleoIndependienteCollection() != null
				&& !personaNatural.getEmpleoIndependienteCollection().isEmpty()) {
			for (EmpleoIndependiente ei : personaNatural.getEmpleoIndependienteCollection()) {
				if (ei.getActividadEconomica() == null
						|| ei.getActividadEconomica().getCodActividadEconomica() == null) {
					ei.setActividadEconomica(new ActividadEconomica(new Short("0")));
				}
			}
		}

		if (personaNatural.getIngresoAnualCollection() != null
				&& !personaNatural.getIngresoAnualCollection().isEmpty()) {
			for (IngresoAnual ia : personaNatural.getIngresoAnualCollection()) {
				if (ia.getMntIngresoAnual() == null) {
					ia.setMntIngresoAnual(BigDecimal.ZERO);
				}
			}
		}

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

		// guardamos y eliminar hijos
		// De la lista hijos y hijosEliminar
		// se deb setear previamente personaNatural

		hijoServicio.guardarActualizarEliminarHijos(persona, personaNatural);

		// habito enfermedad
		crearHabitoEnfermedad(personaNatural);

		// biometrica
		log.info("biometrica");
		Biometrica biometrica = personaNatural.getBiometrica();
		if (biometrica != null) {
			if (biometrica.getEstatura() != null && biometrica.getPeso() != null
					&& biometrica.getDiferenciaUltimoAnio() != null) {
				biometrica.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
				biometricaServicio.create(biometrica);
			}
		}
		log.info("fin biometrica");

		// perfil financiero
		log.info("perfil financiero");
		PerfilFinancieroNatural pfn = personaNatural.getPerfilFinancieroNatural();
		if (pfn != null) {
			pfn.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
			perfilFinancieroNaturalServicio.create(pfn);
		}
		log.info("fin perfil financiero");

		// daf
		log.info("daf");
		DetalleActividadFuncion daf = personaNatural.getDetalleActividadFuncion();
		if (daf != null) {
			if (daf.getDetalleFunciones() != null) {
				daf.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
				detalleActividadFuncionServicio.create(daf);
			}
		}
		log.info("fin daf");

		// detalle pasaporte
		log.info("detalle pasaporte");
		if (personaNatural.isCasillaExtranjero()) {
			DetallePasaporte dp = personaNatural.getDetallePasaporte();
			if (dp.getTipoVisa() != null && dp.getTipoVisa().getSecTipoVisa() != null && dp.getFechaExpedicion() != null
					&& dp.getFechaCaducidad() != null && dp.getFechaEntrada() != null) {
				dp.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
				detallePasaporteServicio.create(dp);
			}
		}
		log.info("fin detalle pasaporte");

		log.info("verifica ruc");
		if (personaNatural.getRucVerificarTransient() != null) {
			System.out.println("crea ruc");
			RucPersonaNatural nuevoRuc = new RucPersonaNatural();
			nuevoRuc.setIdentificacion(personaNatural.getRucVerificarTransient());
			nuevoRuc.setPersonaNatural(personaNatural);
			nuevoRuc.setTipoIdentificacion(new TipoIdentificacion(Constantes.TIPO_IDENTIFICACION_RUC));
			rucPersonaNaturalServicio.create(nuevoRuc);
		}

		log.info("fin crear");

	}

	private void ingresarActualizaDireccionesElectronicas(Persona persona) {

		Collection<DireccionElectronica> lista = persona.getDireccionElectronicaFormularioCollection();
		if (lista != null && !lista.isEmpty()) {
			for (DireccionElectronica de : lista) {
				if (de.getSecDireccionElectronica() == null) {
					direccionElectronicaServicio.create(de);
				} else {
					log.info("COMPARA EMAILS");
					if (!de.equals(de.getOriginal())) {
						log.info("no son iguales");
						direccionElectronicaServicio.update(de);
					}
				}
			}
		}

	}

	private void ingresarActualizaMotivosSeguro(Persona persona) {

		Collection<MotivoSeguro> lista = persona.getMotivoSeguroFormularioCollection();
		if (lista != null && !lista.isEmpty()) {
			for (MotivoSeguro ms : lista) {
				if (ms.getSecMotivoSeguro() == null) {
					log.info("crea motivo seguro");
					motivoSeguroServicio.create(ms);
				} else {
					log.info("actualiza motivo seguro");
					motivoSeguroServicio.update(ms);
				}
			}
		}
	}

	private void ingresarActualizaReferencias(PersonaNatural personaNatural) {

		Collection<Referencia> referencias = personaNatural.getReferenciaFormularioCollection();

		if (referencias != null && !referencias.isEmpty()) {
			for (Referencia r : referencias) {
				if (r.getSecReferencia() == null) {
					if (r.getDenominacion() != null && r.getDenominacion().trim().length() > 0
							&& r.getTipoParentescoRelacion() != null
							&& r.getTipoParentescoRelacion().getCodTipoParentesco() != null) {
						referenciaServicio.create(r);
					}
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
	}

	private void ingresarActualizaReferenciasBancarias(PersonaNatural personaNatural) {

		Collection<ReferenciaBancaria> referenciasB = personaNatural.getReferenciaBancariaFormularioCollection();

		if (referenciasB != null && !referenciasB.isEmpty()) {
			for (ReferenciaBancaria r : referenciasB) {
				if (r.getInstitucionFinanciera() != null
						&& r.getInstitucionFinanciera().getSecInstitucionFinanciera() != null
						&& r.getTipoServicioInstFin() != null
						&& r.getTipoServicioInstFin().getSecTipoServicioInstFin() != null) {

					if (r.getSecReferenciaBancaria() == null) {
						// crea
						referenciaBancariaServicio.create(r);
					} else {
						// actualiza
						// solo si no es el mismo objecto
						// log.info("REFERENCIAS BACOMPARA");
						// if (!r.equals(r.getOriginal())) {
						referenciaBancariaServicio.update(r);
						// telefonoDao.updateLight(t);
						// }
					}
				} else {
					log.info("No actualiza ni crea ref ban, tienes datos nulos");
				}
			}
		}
	}

	private void ingresarActualizaOtraOcupacion(PersonaNatural personaNatural) {

		Collection<OtraOcupacion> otraOcupacionLista = personaNatural.getOtraOcupacionCollection();

		if (otraOcupacionLista != null && !otraOcupacionLista.isEmpty()) {
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
	}

	private void ingresarActualizaReferenciasComerciales(PersonaNatural personaNatural) {

		Collection<ReferenciaComercial> referenciasC = personaNatural.getReferenciaComercialFormularioCollection();

		if (referenciasC != null && !referenciasC.isEmpty()) {
			for (ReferenciaComercial r : referenciasC) {
				if (r.getSecReferenciaComercial() == null) {
					if (r.getEntidadReferencia() != null && r.getMntReferencia() != null) {
						referenciaComercialServicio.create(r);
					}
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
	}

	@Override
	public void ingresarActualizaConyuge(PersonaNatural personaNatural, boolean nuevaPersona) {

		// si es modificacion
		if (!nuevaPersona) {
			// si en la actualizacion ya no se pone con conyuge
			if (!personaNatural.isConConyuge()) {
				System.out.println("inhabilita conyuge");
				// inhabilita todos los conyuges activos
				inactivarRelacion(personaNatural, Constantes.TIPO_RELACION_CONYUGE);
				return;
			}
		}

		// si es modificacion
		if (!nuevaPersona) {

			Persona con = obtenerConyuge(personaNatural.getPersona().getSecPersona());

			if (con != null && personaNatural.isConConyuge() && con.getSecPersona() != null) {

				System.out.println("consulta con sec per " + con.getSecPersona());

				// entonces ya tenia conyuge antes, no hace nada, si quiere
				// cambiar
				// de conyuge debe poner primero divorciado

				PersonaNatural conPN = this.obtenerPersonaNaturalByPersona(con.getSecPersona());

				con.setPersonaNaturalTransient(conPN);
				// PersonaNatural conPN = con.getPersonaNaturalTransient();

				// si no tenia persona natural
				if (conPN == null || conPN.getSecPersonaNatural() == null) {

					// y ahora agrega identificacion
					if (personaNatural.getConyuge().getIdentificacion() != null
							&& !personaNatural.getConyuge().getIdentificacion().equals("")) {
						// entonces graba en persona natural
						Calendar c = Calendar.getInstance();
						c.set(1801, 0, 1);

						conPN = new PersonaNatural();
						con.setPersonaNaturalTransient(conPN);
						conPN.setPersona(con);
						conPN.setTipoIdentificacion(personaNatural.getConyuge().getTipoIdentificacion());
						conPN.setIdentificacion(personaNatural.getConyuge().getIdentificacion());
						conPN.setNumHijos(new Integer(0).shortValue());

						conPN.setApellidoMaterno(personaNatural.getConyuge().getApellidoMaterno());
						conPN.setApellidoPaterno(personaNatural.getConyuge().getApellidoPaterno());
						conPN.setPrimerNombre(personaNatural.getConyuge().getPrimerNombre());
						conPN.setSegundoNombre(personaNatural.getConyuge().getSegundoNombre());

						conPN.setFchNacimiento(c.getTime());
						// el del conyuge
						conPN.setEstadoCivil(personaNatural.getEstadoCivil());
						conPN.setPaisNacionalidad(new Pais(Constantes.PAIS_ID_ECUADOR));
						conPN.setCiudadNacimiento(new Ciudad(Constantes.CIUDAD_ND));
						conPN.setProfesion(new Profesion(Constantes.PROFESION_NO_DISPONIBLE));
						conPN.setOcupacion(new Ocupacion(Constantes.OCUPACION_ID_NO_DISPONIBLE));
						conPN.setTipoRiesgo(new TipoRiesgo(Constantes.TIPO_RIESGO_ID_DEFAULT));
						conPN.setMntSaldoMensual(Constantes.MNT_SALDO_MENSUAL_DEFAULT);

						conPN.setMntSaldoMensual(Constantes.MNT_SALDO_MENSUAL_DEFAULT);
						conPN.setSexo(Constantes.getSexoOpuesto(personaNatural.getSexo()));

						llenaValoresAuditoria(conPN, true);

						System.out.println("antes de guardar conPN....." + conPN.toString());

						personaNaturalDao.save(conPN);
					}
				}

				// si persona natural ya existia
				if (conPN != null) {
					// actualiza solo nombres y apellidos
					conPN.setApellidoMaterno(personaNatural.getConyuge().getApellidoMaterno());
					conPN.setApellidoPaterno(personaNatural.getConyuge().getApellidoPaterno());
					conPN.setPrimerNombre(personaNatural.getConyuge().getPrimerNombre());
					conPN.setSegundoNombre(personaNatural.getConyuge().getSegundoNombre());
					personaNaturalDao.update(conPN);

				}

				con.setDenominacion(personaNatural.getConyuge().getApellidosNombres());
				personaDao.update(con);

				System.out.println("actualiza conyuge que ya estaba registrado");
				return;
			}
		}

		// continua si se va a registrar o actualizar conyuge
		if (!personaNatural.isConConyuge()) {
			return;
		}

		// valores auditoria
		String usuario = "";
		try {
			usuario = sessionContext.getCallerPrincipal().getName();
		} catch (Exception e) {
			// entonces viene de Crm
			usuario = Constantes.USUARIO_CRM;
		}

		String terminal = HttpHelper.getCurrentClientIpAddress();
		String programa = Constantes.FORMULARIO_WEB_PROGRAMA_AUDITORIA;
		String cuenta = this.getClass().getSimpleName();

		if (personaNatural.getConyuge() != null && personaNatural.getConyuge().getApellidosNombres() != null
				&& personaNatural.getConyuge().getApellidosNombres().trim().length() > 0) {
			// ingresa conyuge con identificacion
			if (personaNatural.getConyuge().getIdentificacion() != null
					&& !personaNatural.getConyuge().getIdentificacion().equals("")) {

				Relacion relacion = new Relacion();

				if (personaNatural.getConyuge().getSecPersonaNatural() != null) {
					// Entonces ya existe en la tabla persona, registrado el
					// conyuge
					if (personaNatural.getConyuge().getPersona().getSecPersona() == null) {
						throw new RuntimeException("ERROR INESPERADO: Persona natural id="
								+ personaNatural.getConyuge().getSecPersonaNatural() + ", no existe en persona");
					}
					relacion.setPersona1(personaNatural.getPersona());
					relacion.setPersona(personaNatural.getConyuge().getPersona());
					relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
					relacion.setTipoParentescoRelacion(new TipoParentescoRelacion(Constantes.TIPO_RELACION_CONYUGE));
				} else {
					// se crea una nueva persona para el conyuge en caso de q no
					// se
					// haya encontrado en la base

					// verifica si la cedula del comnyuige ya existe
					PersonaNatural conBDD = this
							.obtenerPersonaNaturalByDocumento(personaNatural.getConyuge().getIdentificacion(), false);

					// si no esta creado en persona natural lo crea
					if (conBDD == null) {

						relacion.setPersona1(personaNatural.getPersona());
						Persona conyuge = new Persona();
						conyuge.setCliente(EsClienteEnum.NO.getCodigo());
						conyuge.setDenominacion(personaNatural.getConyuge().getApellidosNombres());
						conyuge.setTipoIdentificacion(personaNatural.getConyuge().getTipoIdentificacion());
						relacion.setPersona(conyuge);
						relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
						relacion.setTipoParentescoRelacion(
								new TipoParentescoRelacion(Constantes.TIPO_RELACION_CONYUGE));

						personaNatural.getConyuge().setPersona(conyuge);
						conyuge.setPersonaNaturalTransient(personaNatural.getConyuge());
						Calendar c = Calendar.getInstance();
						c.set(1801, 0, 1);
						conyuge.getPersonaNaturalTransient().setFchNacimiento(c.getTime());
						conyuge.getPersonaNaturalTransient().setEstadoCivil(personaNatural.getEstadoCivil());
						conyuge.getPersonaNaturalTransient().setPaisNacionalidad(new Pais(Constantes.PAIS_ID_ECUADOR));
						conyuge.getPersonaNaturalTransient().setCiudadNacimiento(new Ciudad(Constantes.CIUDAD_ND));
						conyuge.getPersonaNaturalTransient()
								.setProfesion(new Profesion(Constantes.PROFESION_NO_DISPONIBLE));
						conyuge.getPersonaNaturalTransient()
								.setOcupacion(new Ocupacion(Constantes.OCUPACION_ID_DEFAULT));
						conyuge.getPersonaNaturalTransient()
								.setTipoRiesgo(new TipoRiesgo(Constantes.TIPO_RIESGO_ID_DEFAULT));
						conyuge.getPersonaNaturalTransient().setMntSaldoMensual(Constantes.MNT_SALDO_MENSUAL_DEFAULT);

						conyuge.getPersonaNaturalTransient().setMntSaldoMensual(Constantes.MNT_SALDO_MENSUAL_DEFAULT);
						conyuge.getPersonaNaturalTransient()
								.setSexo(Constantes.getSexoOpuesto(personaNatural.getSexo()));

						llenarPersonaAuditoria(usuario, terminal, programa, cuenta, conyuge);

						personaDao.save(conyuge);

						llenarPersonaNaturalAuditoria(conyuge.getPersonaNaturalTransient(), usuario, terminal, programa,
								cuenta);

					} else {
						relacion.setPersona1(personaNatural.getPersona());
						relacion.setPersona(conBDD.getPersona());
						relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
						relacion.setTipoParentescoRelacion(
								new TipoParentescoRelacion(Constantes.TIPO_RELACION_CONYUGE));

					}

				}

				guardarRelacion(personaNatural, relacion);

			} else {
				// entonces no hay identificacion
				// solo se guarda solo en la tabla PERSONA (denoimnacion,tipoId)

				Relacion relacion = new Relacion();

				if (personaNatural.getConyuge().getSecPersonaNatural() != null) {
					// Entonces ya existe en la tabla persona, registrado el
					// conyuge
					if (personaNatural.getConyuge().getPersona().getSecPersona() == null) {
						throw new RuntimeException("ERROR INESPERADO: Persona natural id="
								+ personaNatural.getConyuge().getSecPersonaNatural() + ", no existe en persona");
					}
					relacion.setPersona1(personaNatural.getPersona());
					relacion.setPersona(personaNatural.getConyuge().getPersona());
					relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
					relacion.setTipoParentescoRelacion(new TipoParentescoRelacion(Constantes.TIPO_RELACION_CONYUGE));
				} else {
					// se crea una nueva persona para el conyuge en caso de q no
					// se
					// haya encontrado en la base
					relacion.setPersona1(personaNatural.getPersona());
					Persona conyuge = new Persona();
					conyuge.setCliente(EsClienteEnum.NO.getCodigo());
					conyuge.setDenominacion(personaNatural.getConyuge().getApellidosNombres());
					conyuge.setTipoIdentificacion(personaNatural.getConyuge().getTipoIdentificacion());
					relacion.setPersona(conyuge);
					relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
					relacion.setTipoParentescoRelacion(new TipoParentescoRelacion(Constantes.TIPO_RELACION_CONYUGE));

					llenarPersonaAuditoria(usuario, terminal, programa, cuenta, conyuge);

					personaDao.save(conyuge);
					// personaNaturalDao.save(conyuge.getPersonaNatural());

				}
				guardarRelacion(personaNatural, relacion);
			}
		}
	}

	private void guardarRelacion(PersonaNatural personaNatural, Relacion relacion) {
		// busca si ya existe relacion con ese conyuge actualiza la tabla relacion
		List<Relacion> relaciones = relacionServicio.obtener(personaNatural.getPersona().getSecPersona(),
				personaNatural.getConyuge().getPersona().getSecPersona());
		System.out.println("tenia antes el mismo conyuge? " + relaciones.size());
		if (relaciones.size() > 0) {
			System.out.println("actualiza");
			for (Relacion r : relaciones) {
				r.setEstado(EstadoEnum.ACTIVO.getCodigo());
			}
		} else {
			System.out.println("crea");
			// caso contrario la crea
			relacionServicio.create(relacion);
		}
	}

	private void ingresarActualizarDireccionTelefono(Persona persona, boolean nuevaPersona) {

		Collection<Direccion> direcciones = persona.getDireccionCollection();

		Collection<DireccionTelefono> direccionTelefonosGuardar = new ArrayList<DireccionTelefono>();

		log.info("telefonos");

		// guarda telefonos nuevos
		if (persona.getTelefonoCollection() != null && !persona.getTelefonoCollection().isEmpty()) {
			for (Telefono t : persona.getTelefonoCollection()) {
				if (t.getSecTelefono() == null) {
					log.info("...guarda telf:" + t.toString());
					telefonoDao.save(t);
				} else {
					// solo si no es el mismo objecto
					if (!t.equals(t.getOriginal())) {
						telefonoDao.update(t);
						log.info("...actualiza telf:" + t.toString());
						if (!nuevaPersona) {
							if (t.getPrincipal()) {
								persona.getContactoPreferidoTransient().setTelefono(t);
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
				// System.out.println("error en ingreso de direcciones sise:" +
				// e);
				// e.printStackTrace();
				// }
			}
		}

		log.info("fin telefonos");

		log.info("direcciones");

		// guarda direcciones nuevas
		if (direcciones != null && !direcciones.isEmpty()) {
			for (Direccion dir : direcciones) {
				if (dir.getSecDireccion() == null) {
					// Se aumenta validacion si tiene barrio porque es campo
					// requerido
					if (dir.getBarrio() == null || dir.getBarrio().trim().length() <= 0) {
						dir.setBarrio("");
					}

					if (dir.getLatitud() == null) {
						dir.setLatitud(BigDecimal.ZERO);
					}

					if (dir.getLongitud() == null) {
						dir.setLongitud(BigDecimal.ZERO);
					}

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
		}

		log.info("fin direcciones");

		// guarda direcciones en el sise y crea lista a guardar
		if (direcciones != null && !direcciones.isEmpty()) {
			for (Direccion dir : direcciones) {
				if (dir.getBarrio() != null) {
					if (dir.getDireccionTelefonoCollection() != null
							&& !dir.getDireccionTelefonoCollection().isEmpty()) {
						direccionTelefonosGuardar.addAll(dir.getDireccionTelefonoCollection());
					}
				}

				// try {
				// insertarWsSiseMpersonaDir(personaIdSise, dir);
				// } catch (Exception e) {
				// System.out.println("error en ingreso de direcciones sise:" +
				// e);
				// e.printStackTrace();
				// }
			}
		}

		if (!nuevaPersona) {
			boolean contactoPreferidoEnDireccion = false;
			for (DireccionTelefono dt : direccionTelefonosGuardar) {
				if (dt.getTelefono().getPrincipal()) {
					persona.getContactoPreferidoTransient().setDireccion(dt.getDireccion());
					contactoPreferidoEnDireccion = true;
				}
			}
			if (!contactoPreferidoEnDireccion) {
				persona.getContactoPreferidoTransient().setDireccion(null);
			}
		}

		// guarda en equivida
		if (direccionTelefonosGuardar != null && !direccionTelefonosGuardar.isEmpty()) {
			direccionTelefonoServicio.guardarLista(direccionTelefonosGuardar);
		}

		log.info("fin teldir");
	}

	/**
	 * @param personaNatural
	 */
	private void crearHabitoEnfermedad(PersonaNatural personaNatural) {
		Collection<HabitoEnfermedad> todas = personaNatural.getHabitoEnfermedadFormularioCollection();

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
						if (detalleHabitoEnfermedad.getPreguntaHabitoEnfermedad().isNumerico()) {
							if (detalleHabitoEnfermedad.getDetalleDouble() != null) {
								detalleHabitoEnfermedad
										.setDetalle(detalleHabitoEnfermedad.getDetalleDouble().toString());
							} else {
								detalleHabitoEnfermedad.setDetalle("");
							}
						}
						detalleHabitoEnfermedadDao.save(detalleHabitoEnfermedad);
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
		Collection<HabitoEnfermedad> todas = personaNatural.getHabitoEnfermedadFormularioCollection();

		System.out.println("ini habito enfermedad");

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
					// System.out.println("updateLight...");
					// habitoEnfermedadDao.updateLight(habitoEnfermedad);
					habitoEnfermedadDao.update(habitoEnfermedad);
				}

				if (listG != null) {
					for (DetalleHabitoEnfermedad detalleHabitoEnfermedad : listG) {
						if (detalleHabitoEnfermedad.getPreguntaHabitoEnfermedad().isNumerico()) {
							if (detalleHabitoEnfermedad.getDetalleDouble() != null) {
								detalleHabitoEnfermedad
										.setDetalle(detalleHabitoEnfermedad.getDetalleDouble().toString());
							}
						}
						if (detalleHabitoEnfermedad.getSecDetalle() == null) {
							detalleHabitoEnfermedadDao.save(detalleHabitoEnfermedad);
						} else {
							detalleHabitoEnfermedadDao.update(detalleHabitoEnfermedad);
						}

					}
				}
			}
		}
		System.out.println("fin habito enfermedad");
	}

	@Override
	public Persona obtenerConyuge(Integer secPersona) {

		String[] criteriasAnd = { "persona1.secPersona", "tipoParentescoRelacion.codTipoParentesco", "estado" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.SHORT_EQUALS,
				CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { secPersona, Constantes.TIPO_RELACION_CONYUGE, EstadoEnum.ACTIVO.getCodigo() };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		List<Relacion> conyugeLista = relacionServicio.findByCriterias(criteria);

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

	@Override
	public PersonaNatural obtenerConyugePN(Integer secPersona) {
		System.out.println("Se obtiene conyuge para: " + secPersona);
		Persona personaConyuge = obtenerConyuge(secPersona);

		System.out.println("Si hay conyuge: " + personaConyuge);

		PersonaNatural conyuge = null;

		if (personaConyuge != null) {
			conyuge = obtenerPersonaNaturalByPersona(personaConyuge.getSecPersona());

			System.out.println("Si hay conyuge de verdad: " + conyuge);
		}

		return conyuge;
	}

	public TipoOtroSeguroServicio getTipoOtroSeguroServicio() {
		return tipoOtroSeguroServicio;
	}

	public void setTipoOtroSeguroServicio(TipoOtroSeguroServicio tipoOtroSeguroServicio) {
		this.tipoOtroSeguroServicio = tipoOtroSeguroServicio;
	}

	@Override
	public void actualizarPersonaNaturalFormularioWeb(PersonaNatural personaNatural, String usuario, String ip)
			throws EmpleoDependienteException, EmpleoIndependienteException, ServiceException, RemoteException,
			ErrorIngresoWsSiseException {

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
		BigDecimal saldoMensual = personaNaturalServicio.calcularSaldoMensual(ingresoMensual,
				ingresoMensualAdicionalLista);
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
		// verificarRiesgoConyuge(personaNatural, usuario, ip, false);
		log.info("fin datos conyuge");

		log.info("ingresa direcciones/telefonos");
		ingresarActualizarDireccionTelefono(persona, false);
		log.info("FIN ingresa direcciones/telefonos");

		log.info("contacto preferido");
		if (persona.getContactoPreferidoTransient().getSecContactoPreferido() == null) {
			contactoPreferidoServicio.create(persona.getContactoPreferidoTransient());
		} else {
			contactoPreferidoServicio.updateLight(persona.getContactoPreferidoTransient());
		}
		log.info("fin contacto preferido");

		log.info("inicio direcciones electronicas");
		ingresarActualizaDireccionesElectronicas(persona);
		log.info("fin direcciones electronicas");

		log.info("inicio motivos seguro");
		ingresarActualizaMotivosSeguro(persona);
		log.info("fin motivos seguro");

		PersonaComponenteExclusion exclusion = persona.getPersonaComponenteExclusionTransient();
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
		// System.out.println("verifica lista reservada perona natural");
		// verificarRiesgoPersona(personaNatural, usuario, ip, false);

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
		PerfilFinancieroNatural pfn = personaNatural.getPerfilFinancieroNatural();
		pfn.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
		if (pfn.getSecPerfilFinanciero() == null) {
			perfilFinancieroNaturalServicio.create(pfn);
		} else {
			perfilFinancieroNaturalServicio.update(pfn);
		}
		log.info("perfil financiero");

		// daf
		log.info("daf");
		DetalleActividadFuncion daf = personaNatural.getDetalleActividadFuncion();
		daf.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
		if (daf.getSecDetalleFunciones() == null) {
			if (daf.getDetalleFunciones() != null) {
				// hay información
				detalleActividadFuncionServicio.create(daf);
			}
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
	public void inactivarRelacion(PersonaNatural personaPrincipal, Short parentezcoId) {

		// if (personaPrincipal.isConDatosCompletos()) {

		String[] criteriasAnd = { "persona1.secPersona", "tipoParentescoRelacion.codTipoParentesco", "estado" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.SHORT_EQUALS,
				CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { personaPrincipal.getPersona().getSecPersona(), Constantes.TIPO_RELACION_CONYUGE,
				EstadoEnum.ACTIVO.getCodigo() };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		List<Relacion> conyugeLista = relacionServicio.findByCriterias(criteria);

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

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		List<PersonaNatural> lista = personaNaturalDao.findByCriterias(criteria);

		PersonaNatural personaNatural = null;

		if (lista.size() >= 1) {
			personaNatural = lista.get(0);
			if (lista.size() > 1) {
				System.out.println("WARN: existe mas una persona natural atada a una persona ");
			}
		}

		return personaNatural;
	}

	@Override
	public PersonaNatural obtenerPersonaNaturalByPersonaParaActualizacionCrm(Integer secPersona) {

		String[] criteriasAndPersonaNatural = { "persona.secPersona" };
		CriteriaTypeEnum[] typesAndPersonaNatural = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAndPersonaNatural = { secPersona };

		Criteria criteriaPersonaNatural = new Criteria(criteriasAndPersonaNatural, typesAndPersonaNatural,
				valuesCriteriaAndPersonaNatural);

		List<PersonaNatural> lista = personaNaturalDao.findByCriterias(criteriaPersonaNatural);

		PersonaNatural personaNatural = null;

		if (lista.size() >= 1) {
			personaNatural = lista.get(0);
			if (lista.size() > 1) {
				System.out.println("WARN: existe mas una persona natural atada a una persona ");
			}
		}

		if (personaNatural != null) {

			// direcciones
			ContactoPreferido contactoPreferido = iniciarDirecciones(personaNatural);

			// telefonos adicionales
			iniciarTelefonosAdicionales(personaNatural, contactoPreferido);

			// direcciones electronicas
			Collection<DireccionElectronica> deList = direccionElectronicaServicio
					.obtenerPorPersona(personaNatural.getPersona().getSecPersona());
			personaNatural.getPersona().setDireccionElectronicaFormularioCollection(deList);

			ponerDireccioneElectronicaOriginal(deList);
		}

		// limpia para que se haga detach y futuros cambios no cambien la base
		personaNaturalDao.clear();

		return personaNatural;
	}

	private void iniciarTelefonosAdicionales(PersonaNatural personaNatural, ContactoPreferido contactoPreferido) {
		// telefonos adicionales
		String[] criteriasAnd = { "persona.secPersona", "tipoTelefono.reqDireccion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.STRING_EQUALS };
		Object[] params = new Object[] { personaNatural.getPersona().getSecPersona(), RespuestaEnum.NO.getCodigo() };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		Collection<Telefono> telefonoSinDireccionCollection = telefonoDao.findByCriterias(criteria);

		for (Telefono telefono : telefonoSinDireccionCollection) {
			telefono.setPrincipal(false);
			// este if es porque no puede tener contacto preferido
			if (contactoPreferido.getTelefono().getSecTelefono() != null) {
				if (telefono.getSecTelefono().compareTo(contactoPreferido.getTelefono().getSecTelefono()) == 0) {
					telefono.setPrincipal(true);
				}
			}
			// feaso
			telefono.setTipoTelefono(new TipoTelefono(telefono.getTipoTelefono().getCodTipoTelefono()));
			// para pasar con asteriscos
			telefono.setTelefonoConCodigoAreaAnterior(telefono.getTelefonoConCodigoArea());

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

		personaNatural.getPersona().setTelefonoSinDireccionCollection(telefonoSinDireccionCollection);
	}

	private ContactoPreferido iniciarDirecciones(PersonaNatural personaNatural) {
		// direcciones
		Collection<Direccion> dirCollection = personaNatural.getPersona().getDireccionCollection();
		if (dirCollection != null)
			dirCollection.size();

		// recupero contacto preferido y le pongo en el transient
		personaNatural.getPersona().setContactoPreferidoTransient(
				contactoPreferidoServicio.obtenerPorPersona(personaNatural.getPersona().getSecPersona()));

		ContactoPreferido contactoPreferido = personaNatural.getPersona().getContactoPreferidoTransient();

		if (contactoPreferido != null) {
			contactoPreferido.ponerHorarioEnHorasMinutos();
		} else {
			// no tiene contacto preferido, fue realizado de la migracion
			contactoPreferido = new ContactoPreferido();
			contactoPreferido.setDireccion(new Direccion());
			contactoPreferido.setPersona(personaNatural.getPersona());
			contactoPreferido.setTelefono(new Telefono());
			contactoPreferido.setTipoContactoPreferido(new TipoContactoPreferido());
			personaNatural.getPersona().setContactoPreferidoTransient(contactoPreferido);
		}

		if (dirCollection != null)
			for (Direccion direccion : dirCollection) {
				if (direccion != null) {

					Short secCanton = direccion.getCanton().getSecCanton();
					String nombreCanton = direccion.getCanton().getCanton();
					Short secProvincia = direccion.getCanton().getProvincia().getSecProvincia();
					String nombreProv = direccion.getCanton().getProvincia().getProvincia();
					Short secPais = direccion.getCanton().getProvincia().getPais().getCodPais();
					String nombrePais = direccion.getCanton().getProvincia().getPais().getPais();

					direccion.setCanton(
							new Canton(secCanton, nombreCanton, secProvincia, nombreProv, secPais, nombrePais));

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

					String[] criteriasAnd = { "persona.secPersona", "direccion.secDireccion" };
					CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS };
					Object[] params = new Object[] { personaNatural.getPersona().getSecPersona(),
							direccion.getSecDireccion() };
					Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

					Collection<DireccionTelefono> direccionTelefonoCollection = direccionTelefonoDao
							.findByCriterias(criteria);

					for (DireccionTelefono dt : direccionTelefonoCollection) {
						if (contactoPreferido != null && contactoPreferido.getTelefono() != null
								&& contactoPreferido.getTelefono().getSecTelefono() != null
								&& dt.getTelefono().getSecTelefono()
										.compareTo(contactoPreferido.getTelefono().getSecTelefono()) == 0) {
							dt.getTelefono().setPrincipal(true);
						} else {
							dt.getTelefono().setPrincipal(false);
						}
						// feaso
						dt.getTelefono().setTipoTelefono(
								new TipoTelefono(dt.getTelefono().getTipoTelefono().getCodTipoTelefono()));
						// para pasar con asteriscos
						dt.getTelefono().setTelefonoConCodigoAreaAnterior(dt.getTelefono().getTelefonoConCodigoArea());

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

					direccion.setDireccionTelefonoCollection(direccionTelefonoCollection);

				}
			}
		return contactoPreferido;
	}

	@Override
	public List<PersonaNatural> obtenerListadoPaginado(String numeroDocumento, String apellidoPaterno,
			String apellidoMaterno, String primerNombre, String segundoNombre, RespuestaEnum cliente,
			String ordenadoPor, boolean asc, int firstRows, int totalRows) {
		return personaNaturalDao.obtenerListadoPaginado(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre,
				segundoNombre, cliente, ordenadoPor, asc, firstRows, totalRows);
	}

	@Override
	public Long obtenerTotalListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, RespuestaEnum cliente) {
		return personaNaturalDao.obtenerTotalListadoPaginado(numeroDocumento, apellidoPaterno, apellidoMaterno,
				primerNombre, segundoNombre, cliente);
	}

	public DetallePasaporteServicio getDetallePasaporteServicio() {
		return detallePasaporteServicio;
	}

	public void setDetallePasaporteServicio(DetallePasaporteServicio detallePasaporteServicio) {
		this.detallePasaporteServicio = detallePasaporteServicio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.PersonaNaturalServicio#obtenerPersonaNaturalByDocumento
	 * (java.lang.String, boolean)
	 */
	@Override
	public PersonaNatural obtenerPersonaNaturalByDocumento(String numeroDocumento, boolean conRelaciones) {

		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { numeroDocumento };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		List<PersonaNatural> lista = personaNaturalDao.findByCriterias(criteria);

		PersonaNatural pn = null;

		if (lista.size() > 0) {
			pn = lista.get(0);

			if (lista.size() > 1) {
				System.out.println("ERROR: Existe mas de una persona natural con el documento:" + numeroDocumento);
			}

			if (conRelaciones) {
				if (pn.getPersona().getDireccionCollection() != null) {
					pn.getPersona().getDireccionCollection().size();
				}

				if (pn.getPersona().getTelefonoCollection() != null) {
					pn.getPersona().getTelefonoCollection().size();
				}

				if (pn.getActividadCollection() != null) {
					pn.getActividadCollection().size();
				}

				if (pn.getEmpleoDependienteCollection() != null) {
					pn.getEmpleoDependienteCollection().size();
				}

				if (pn.getEmpleoIndependienteCollection() != null) {
					pn.getEmpleoIndependienteCollection().size();
				}

				if (pn.getOtroEmpleoCollection() != null) {
					pn.getOtroEmpleoCollection().size();
				}

				if (pn.getPersonaPeCollection() != null) {
					pn.getPersonaPeCollection().size();
				}
			}
		}

		return pn;
	}

	@Override
	public void actualizarPersonaNaturalCrm(PersonaNatural personaNatural, String usuario, String ip)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		IngresoMensual ingresoMensual = personaNatural.getIngresoMensual();

		if (ingresoMensual == null) {

			ingresoMensual = new IngresoMensual();
			ingresoMensual.setMntIngresoMensual(new BigDecimal(0));
			ingresoMensual.setMntEgresoMensual(new BigDecimal(0));
			ingresoMensual.setPersonaNatural(personaNatural);
			personaNatural.setIngresoMensual(ingresoMensual);

			// String mensaje = "no se ha ingresado ingreso mensual!";
			// log.error(mensaje);
			// throw new RuntimeException(mensaje);
		}

		log.info("actualizando PN ...");
		Persona persona = personaNatural.getPersona();
		if (persona == null) {
			String mensaje = "no se ha ingresado persona!";
			log.error(mensaje);
			throw new RuntimeException(mensaje);
		}

		// llena valores auditoria
		llenaValoresAuditoria(personaNatural, false);

		log.info("ingresa direcciones/telefonos");
		ingresarActualizarDireccionTelefono(persona, false);
		log.info("FIN ingresa direcciones/telefonos");

		log.info("contacto preferido");
		if (persona.getContactoPreferidoTransient().getSecContactoPreferido() != null) {
			// contactoPreferidoServicio.create(persona
			// .getContactoPreferidoTransient());
			// } else {
			contactoPreferidoServicio.updateLight(persona.getContactoPreferidoTransient());
		} else {
			// ContactoPreferido cp=new ContactoPreferido();
			persona.getContactoPreferidoTransient()
					.setTipoContactoPreferido(new TipoContactoPreferido(Constantes.TIPO_CONTACTO_PREFERIDO_TELEFONO));
			persona.getContactoPreferidoTransient().setDireccion(persona.getDireccionCollection().iterator().next());
			persona.getContactoPreferidoTransient().setTelefono(persona.getTelefonoCollection().iterator().next());
			contactoPreferidoServicio.create(persona.getContactoPreferidoTransient());
		}
		log.info("fin contacto preferido");

		log.info("inicio direcciones electronicas");
		ingresarActualizaDireccionesElectronicas(persona);
		log.info("fin direcciones electronicas");

	}

	@Override
	public Map<String, Integer> obtenerPersonaNaturalByDocumento(List<String> numeroDocumento) {
		return personaNaturalDao.obtenerPersonaNaturalByDocumento(numeroDocumento);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.PersonaNaturalServicio#crearPersonaNaturalBuenViaje
	 * (com.equivida.modelo.PersonaNatural, java.lang.String, java.lang.String)
	 */
	@Override
	public void crearPersonaNaturalBuenViaje(PersonaNatural personaNatural, String usuario, String ip)
			throws EmpleoDependienteException, EmpleoIndependienteException, ServiceException, RemoteException,
			ErrorIngresoWsSiseException {
		Persona persona = personaNatural.getPersona();
		personaNatural.setConyuge(null);

		// llena valores auditoria
		llenaValoresAuditoria(personaNatural, true);

		// de forma sincronizada envia a guardar
		crearSync(personaNatural, persona, usuario, ip, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.servicio.PersonaNaturalServicio#detach(com.equivida.modelo.
	 * PersonaNatural)
	 */
	@Override
	public void detach(PersonaNatural personaNatural) {
		personaNaturalDao.detach(personaNatural);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.PersonaNaturalServicio#persitir(com.equivida.modelo.
	 * PersonaNatural)
	 */
	@Override
	public void persitir(PersonaNatural personaNatural) {
		if (personaNatural.getSecPersonaNatural() == null) {
			create(personaNatural);
		} else {
			update(personaNatural);
		}
	}

	@Override
	public PersonaNatural obtenerPersonaNaturalByPersonaFormulario(Integer secPersona) {
		PersonaNatural persona = obtenerPersonaNaturalByPersona(secPersona);

		inicializarParaFormulario(persona);
		return persona;
	}
}