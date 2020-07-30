package com.equivida.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoCivilEnum;
import com.equivida.constant.GanadoPerdidoEnum;
import com.equivida.constant.LugarEncuentraInfoEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.dto.EmpleoDto;
import com.equivida.dto.RespuestaGeneralDto;
import com.equivida.exception.NoEncuentraDatosException;
import com.equivida.modelo.Actividad;
import com.equivida.modelo.BeneficiarioPoliza;
import com.equivida.modelo.Biometrica;
import com.equivida.modelo.Canton;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.ContactoPreferido;
import com.equivida.modelo.DeportePractica;
import com.equivida.modelo.DetalleActividadFuncion;
import com.equivida.modelo.DetallePasaporte;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.EmpleoDependiente;
import com.equivida.modelo.EmpleoIndependiente;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.EstadoPersona;
import com.equivida.modelo.HistoriaMedicaFamiliar;
import com.equivida.modelo.InformacionAdicional;
import com.equivida.modelo.IngresoAnual;
import com.equivida.modelo.IngresoMensual;
import com.equivida.modelo.IngresoMensualAdicional;
import com.equivida.modelo.InstitucionFinanciera;
import com.equivida.modelo.Ocupacion;
import com.equivida.modelo.OtroEmpleo;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Parroquia;
import com.equivida.modelo.PerfilFinancieroNatural;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.PersonaPe;
import com.equivida.modelo.Profesion;
import com.equivida.modelo.Provincia;
import com.equivida.modelo.Referencia;
import com.equivida.modelo.ReferenciaBancaria;
import com.equivida.modelo.ReferenciaComercial;
import com.equivida.modelo.SeguroAdicional;
import com.equivida.modelo.SeguroVigente;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoActividad;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoInstitucionFinanciera;
import com.equivida.modelo.TipoMotivoSeguro;
import com.equivida.modelo.TipoOtroSeguro;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.modelo.TipoRiesgo;
import com.equivida.modelo.TipoServicioInstFin;
import com.equivida.modelo.TipoVisa;
import com.equivida.servicio.BeneficiarioServicio;
import com.equivida.servicio.CantonServicio;
import com.equivida.servicio.ConsultaGeneralCUServicio;
import com.equivida.servicio.ConsultaGeneralCUServicioRemoto;
import com.equivida.servicio.PaisServicio;
import com.equivida.servicio.PersonaJuridicaServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.RucPersonaNaturalServicio;
import com.equivida.servicio.TipoActividadServicio;
import com.equivida.servicio.TipoMotivoSeguroServicio;
import com.equivida.servicio.TipoOtroSeguroServicio;
import com.equivida.servicio.TipoParentescoRelacionServicio;
import com.equivida.smartdata.exception.SmartdataException;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.servicio.SmartDataSdServicio;
import com.equivida.util.PersonaUtil;
import com.equivida.util.TransformSdCuUtil;

/**
 * 
 * @author juan
 *
 */
@Stateless(name = "ConsultaGeneralCUServicio")
public class ConsultaGeneralCUServicioImpl implements ConsultaGeneralCUServicio, ConsultaGeneralCUServicioRemoto {

	@EJB(mappedName = "SmartDataSdServicio/local")
	private SmartDataSdServicio smartDataSdServicio;
	@EJB
	private PersonaNaturalServicio personaNaturalServicio;
	@EJB
	private PersonaJuridicaServicio personaJuridicaServicio;
	@EJB
	private BeneficiarioServicio beneficiarioServicio;
	@EJB
	private CantonServicio cantonServicio;
	@EJB
	private PaisServicio paisServicio;
	@EJB
	private TipoParentescoRelacionServicio tipoParentescoRelacionServicio;
	@EJB
	private TipoOtroSeguroServicio tipoOtroSeguroServicio;
	@EJB
	private TipoActividadServicio tipoActividadServicio;
	@EJB
	private TipoMotivoSeguroServicio tipoMotivoSeguroServicio;
	@EJB
	private RucPersonaNaturalServicio rucPersonaNaturalServicio;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.servicio.ConsultaGeneralCUServicio#consultaGenerica(java
	 * .lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RespuestaGeneralDto consultaGenerica(String noDocumento, String usuario, String rucPersonaNaturalEnviado) {
		RespuestaGeneralDto respuesta = new RespuestaGeneralDto();
		respuesta.setEncuentraCu(false);
		respuesta.setEncuentraSd(false);
		respuesta.setEncuentraDb(false);
		respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.NONE);
		respuesta.setMuestraPopUp(false);
		respuesta.setNuevoRegistro(false);
		respuesta.setPersonaNatural(null);
		respuesta.setPersonaNaturalPopUp(null);
		respuesta.setPersonaJuridica(null);
		respuesta.setPersonaSD(null);

		// 1. Consulta en cliente unico
		System.out.println("===========================Consulta CU: " + noDocumento + new Date());
		consultaEnClienteUnico(noDocumento, respuesta);

		System.out.println("===========================FIN Consulta CU: " + noDocumento + new Date());

		// 2. Consulta en smartData
		System.out.println("===========================Consulta SD: " + noDocumento + new Date());
		consultaEnSmartData(noDocumento, respuesta, usuario);
		System.out.println("===========================FIN Consulta SD: " + noDocumento + new Date());

		if (respuesta.isEncuentraCu()) {
			if (rucPersonaNaturalEnviado != null) {
				respuesta.setRucVerificar(rucPersonaNaturalEnviado);
			}
		}

		// 3. Caso 1: SI Encuentra en CU y NO Encuentra en SD
		if (respuesta.isEncuentraCu() && !respuesta.isEncuentraSd()) {
			System.out.println("3....");
			respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.CU);
			persistirClienteUnicoEnSmarData(respuesta, usuario);
		}

		// 4. Caso 2: NO Encuentra en CU y SI Encuentra en SD
		if (!respuesta.isEncuentraCu() && respuesta.isEncuentraSd()) {
			System.out.println("4....");
			respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.SD);
			cargarInformacionSmartDataAClienteUnico(respuesta, usuario);
		}

		// 5. Caso 3: SI encuentra en CU y SI encuentra en Smartdata.
		if (respuesta.isEncuentraCu() && respuesta.isEncuentraSd()) {
			System.out.println("5....");
			respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.CU_SD);
			pasarSmartDataAClienteUnicoPopUp(respuesta, usuario);
		}

		// 6. Caso 4: NO encuentra en CU y NO encuentra en Smartdata.
		if (!respuesta.isEncuentraCu() && !respuesta.isEncuentraSd()) {
			System.out.println("6....");
			respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.DB);
			buscarEnDataBookGuardaSmartdata(noDocumento, respuesta, usuario);
		}

		// Se aplican los estilos
		respuesta.aplicarEstilos();

		return respuesta;
	}

	/*
	 * 
	 */
	@Override
	public RespuestaGeneralDto consultaGenericaConSise(String noDocumento, String usuario,
			String rucPersonaNaturalEnviado) {
		RespuestaGeneralDto resp = consultaGenerica(noDocumento, usuario, rucPersonaNaturalEnviado);
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.servicio.ConsultaGeneralCUServicio#
	 * consultaGenericaPersonaJuridica(java.lang.String, java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RespuestaGeneralDto consultaGenericaPersonaJuridica(String noDocumento, String usuario) {
		RespuestaGeneralDto respuesta = new RespuestaGeneralDto();
		respuesta.setEncuentraCu(false);
		respuesta.setEncuentraSd(false);
		respuesta.setEncuentraDb(false);
		respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.NONE);
		respuesta.setMuestraPopUp(false);
		respuesta.setNuevoRegistro(false);
		respuesta.setPersonaNatural(null);
		respuesta.setPersonaNaturalPopUp(null);
		respuesta.setPersonaJuridica(null);
		respuesta.setPersonaSD(null);

		// 1. Consulta en CU.
		consultaEnClienteUnicoPersonaJuridica(noDocumento, respuesta);

		// 2. Conulta en SD
		consultaPersonaJuridicaEnSmartData(noDocumento, respuesta, usuario);

		return respuesta;
	}

	/**
	 * Servicio Web DataBook Si no encuentra en el servicio web, muestro un mensaje
	 * no se encontró esta persona y permite ingresar en el formulario de cliente
	 * unico.
	 * 
	 * Si encuentra en el servicio web, guarda en SMARDATA y muestra en cliente
	 * único.
	 * 
	 * Se debe resaltar en la popup los campos (información básica personal) que
	 * difieren con amarillo los diferentes y con rojo el inexistente (por ejemplo
	 * fecha de fallecimiento)
	 *
	 * @param respuesta
	 * @param usuario
	 */
	private void buscarEnDataBookGuardaSmartdata(String noDocumento, RespuestaGeneralDto respuesta, String usuario) {
		PersonaSd personaSd = null;

		try {
			// 1. Consulta en DataBook
			personaSd = smartDataSdServicio.consultaDatabook(noDocumento, usuario);

			if (respuesta.getPersonaSD() != null && respuesta.getPersonaSD().getSecPersona() != null) {
				personaSd.setSecPersona(respuesta.getPersonaSD().getSecPersona());
			}

			respuesta.setPersonaSD(personaSd);
			respuesta.setEncuentraDb(true);
			respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.DB);
		} catch (SmartdataException e) {
			respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.NONE);
			respuesta.setHayMensaje(true);
			respuesta.setEncuentraDb(false);
			respuesta.setMensaje("No se encuentra esta persona");
		}

		// Si encuentra en dataBook
		if (respuesta.isEncuentraDb()) {
			// 3. Transforma a PersonaNatural de CU
			TransformSdCuUtil t = new TransformSdCuUtil();
			PersonaNatural personaNatural = t.transformarPersonaSdEnPersonaNaturalCU(respuesta.getPersonaSD(), usuario,
					cantonServicio, paisServicio);

			respuesta.setMuestraPopUp(true);
			respuesta.setPersonaNaturalPopUp(personaNatural);
			respuesta.setPersonaNatural(new PersonaNatural());
			respuesta.setNuevoRegistro(true);

			// 2. Guarda en SmartData
			try {
				System.out.println("===========================Persiste DB en SD: " + noDocumento + new Date());
				smartDataSdServicio.guardaEnSmartData(personaSd);
				System.out.println("===========================FIN Persiste DB en SD: " + noDocumento + new Date());
			} catch (Exception e) {
				e.printStackTrace();

				respuesta.setMensaje("No se pudo guardar en SmartData desde DataBook");
				respuesta.setHayMensaje(true);
			}
		}
	}

	/**
	 * Popup con información de Smartdata, esta información está en el requerimiento
	 * funcional
	 * 
	 * Se debe resaltar en la popup los campos (información básica personal) que
	 * difieren con amarillo los diferentes y con rojo el inexistente (por ejemplo
	 * fecha de fallecimiento)
	 *
	 * @param respuesta
	 * @param usuario
	 */
	private void pasarSmartDataAClienteUnicoPopUp(RespuestaGeneralDto respuesta, String usuario) {
		// 1. Transforma Persona de smart Data a persona de cliente unico
		TransformSdCuUtil t = new TransformSdCuUtil();
		PersonaNatural personaNatural = t.transformarPersonaSdEnPersonaNaturalCU(respuesta.getPersonaSD(), usuario,
				cantonServicio, paisServicio);

		// 2. Carga persona natural en objeto de CU, no persiste
		respuesta.setPersonaNaturalPopUp(personaNatural);

		// 3. Se configuran variables para la pantalla
		respuesta.setMuestraPopUp(true);
	}

	/**
	 * 2. Cargar la informacion encontrada en SmartData en un objeto PersonaNatural
	 * de Cliente Unico
	 * 
	 * Trae información del Smartdata y muestra en cliente único, sin guardar
	 * 
	 * @param respuesta
	 * @param usuario
	 */
	private void cargarInformacionSmartDataAClienteUnico(RespuestaGeneralDto respuesta, String usuario) {
		// 1. Transforma Persona de smart Data a persona de cliente unico
		TransformSdCuUtil t = new TransformSdCuUtil();
		PersonaNatural personaNatural = t.transformarPersonaSdEnPersonaNaturalCU(respuesta.getPersonaSD(), usuario,
				cantonServicio, paisServicio);

		// Se inicializa Con los datos que no vienen para que existan errores en
		// el formulario
		inicializaPersonaNaturalCu(personaNatural);

		// 2. Carga persona natural en objeto de CU, no persiste
		respuesta.setPersonaNatural(personaNatural);

		// 3. Se configuran variables para la pantalla
		respuesta.setNuevoRegistro(true);
	}

	/**
	 * Inicializa los datos de persona natural.
	 * 
	 * @param personaNatural
	 */
	private void inicializaPersonaNaturalCu(PersonaNatural personaNatural) {
		List<TipoMotivoSeguro> tipoMotivoSeguroLista = tipoMotivoSeguroServicio.findAll();

		PersonaUtil.inicializarMotivosSeguro(personaNatural, tipoMotivoSeguroLista,
				personaNatural.getPersona().getMotivoSeguroFormularioCollection());

		if (personaNatural.getPersona().getBeneficiarioPolizaCollection() == null) {
			personaNatural.getPersona().setBeneficiarioPolizaCollection(new ArrayList<BeneficiarioPoliza>());
		}

		if (personaNatural.getPersona().getTelefonoSinDireccionCollection() == null) {
			personaNatural.getPersona().setTelefonoSinDireccionCollection(new ArrayList<Telefono>());
		}

		if (personaNatural.getPersona().getTelefonoSinDireccionActivosCollection() == null) {
			personaNatural.getPersona().setTelefonoSinDireccionCollection(new ArrayList<Telefono>());
		}

		if (personaNatural.getPersona().getDireccionElectronicaFormularioCollection() == null) {
			personaNatural.getPersona()
					.setDireccionElectronicaFormularioCollection(new ArrayList<DireccionElectronica>());
		}

		if (personaNatural.getPersona().getDireccionCollection() == null) {
			personaNatural.getPersona().setDireccionCollection(new ArrayList<Direccion>());
		}

		if (personaNatural.getPersona().getTelefonoCollection() == null) {
			personaNatural.getPersona().setTelefonoCollection(new ArrayList<Telefono>());
		}

		if (personaNatural.getPersona().getContactoPreferidoTransient() == null) {
			personaNatural.getPersona().setContactoPreferidoTransient(new ContactoPreferido());
			personaNatural.getPersona().getContactoPreferidoTransient().ponerHorarioEnHorasMinutos();
		}

		if (personaNatural.getEmpleoDependienteCollection() == null) {
			personaNatural.setEmpleoDependienteCollection(new ArrayList<EmpleoDependiente>());
		}

		if (personaNatural.getEmpleoIndependienteCollection() == null) {
			personaNatural.setEmpleoIndependienteCollection(new ArrayList<EmpleoIndependiente>());
		}

		if (personaNatural.getEmpleoCollection() == null) {
			personaNatural.setEmpleoCollection(new ArrayList<EmpleoDto>());
		}

		if (personaNatural.getOtroEmpleoCollection() == null) {
			personaNatural.setOtroEmpleoCollection(new ArrayList<OtroEmpleo>());
		}

		if (personaNatural.getDeportePracticaCollection() == null) {
			personaNatural.setDeportePracticaCollection(new ArrayList<DeportePractica>());
		}

		if (personaNatural.getSegurosVigentesCollection() == null) {
			personaNatural.setSegurosVigentesCollection(new ArrayList<SeguroVigente>());
		}

		if (personaNatural.getIngresoMensualAdicionalCollection() == null) {
			personaNatural.setIngresoMensualAdicionalCollection(new ArrayList<IngresoMensualAdicional>());
		}

		if (personaNatural.getPersonaPeCollection() == null) {
			personaNatural.setPersonaPeCollection(new ArrayList<PersonaPe>());
		}

		if (personaNatural.getEstadoPersonaCollection() == null) {
			personaNatural.setEstadoPersonaCollection(new ArrayList<EstadoPersona>());
		}

		if (personaNatural.getDetalleActividadFuncion() == null) {
			DetalleActividadFuncion detalleActividadFuncion = new DetalleActividadFuncion();
			personaNatural.setDetalleActividadFuncion(detalleActividadFuncion);
		}

		if (personaNatural.getDetallePasaporte() == null) {
			DetallePasaporte detallePasaporte = new DetallePasaporte();
			detallePasaporte.setTipoVisa(new TipoVisa());
			personaNatural.setDetallePasaporte(detallePasaporte);
		}

		if (personaNatural.getHistoriaMedicaFamiliarFormularioCollection() == null) {
			HistoriaMedicaFamiliar padre = new HistoriaMedicaFamiliar();
			TipoParentescoRelacion tipoPadre = tipoParentescoRelacionServicio.findByPk(Constantes.TIPO_RELACION_PADRE);
			padre.setPersonaNatural(personaNatural);
			padre.setDetalles("");
			padre.setTipoParentescoRelacion(tipoPadre);

			HistoriaMedicaFamiliar madre = new HistoriaMedicaFamiliar();
			TipoParentescoRelacion tipoMadre = tipoParentescoRelacionServicio.findByPk(Constantes.TIPO_RELACION_MADRE);
			madre.setPersonaNatural(personaNatural);
			madre.setDetalles("");
			madre.setTipoParentescoRelacion(tipoMadre);

			HistoriaMedicaFamiliar hermano = new HistoriaMedicaFamiliar();
			TipoParentescoRelacion tipoHermano = tipoParentescoRelacionServicio
					.findByPk(Constantes.TIPO_RELACION_HERMANO);
			hermano.setPersonaNatural(personaNatural);
			hermano.setDetalles("");
			hermano.setTipoParentescoRelacion(tipoHermano);

			List<HistoriaMedicaFamiliar> listaHistoriaMF = new ArrayList<HistoriaMedicaFamiliar>();
			listaHistoriaMF.add(padre);
			listaHistoriaMF.add(madre);
			listaHistoriaMF.add(hermano);

			personaNatural.setHistoriaMedicaFamiliarFormularioCollection(listaHistoriaMF);
		}

		if (personaNatural.getSeguroAdicionalFormularioCollection() == null) {
			Collection<TipoOtroSeguro> otroSeguroPreguntas = tipoOtroSeguroServicio.findAll();

			Collection<SeguroAdicional> seguroAdicionalLista = new ArrayList<SeguroAdicional>();

			for (TipoOtroSeguro tipoOtroSeguro : otroSeguroPreguntas) {
				SeguroAdicional seguroAdicional = new SeguroAdicional();
				seguroAdicional.setPersonaNatural(personaNatural);
				seguroAdicional.setTipoOtroSeguro(tipoOtroSeguro);
				seguroAdicionalLista.add(seguroAdicional);
			}

			personaNatural.setSeguroAdicionalFormularioCollection(seguroAdicionalLista);
		}

		if (personaNatural.getActividadFormularioCollection() == null) {
			List<TipoActividad> tipoActividadLista = tipoActividadServicio.findAll();

			List<Actividad> actividadLista = new ArrayList<Actividad>();

			for (TipoActividad tipoActividad : tipoActividadLista) {
				Actividad act = new Actividad();
				act.setPersonaNatural(personaNatural);
				act.setSeleccionado(false);
				act.setTipoActividad(tipoActividad);
				actividadLista.add(act);
			}

			personaNatural.setActividadFormularioCollection(actividadLista);
		}

		if (personaNatural.getReferenciaFormularioCollection() == null) {
			Collection<Referencia> referenciaLista = new ArrayList<Referencia>();

			Referencia referenciaUno = new Referencia();
			TipoParentescoRelacion noFamiliar1 = new TipoParentescoRelacion();
			referenciaUno.setPersonaNatural(personaNatural);
			referenciaUno.setTipoParentescoRelacion(noFamiliar1);
			referenciaUno.setFamiliarNoVivaUd(RespuestaEnum.NO.getCodigo());
			referenciaLista.add(referenciaUno);

			Referencia referenciaDos = new Referencia();
			TipoParentescoRelacion noFamiliar2 = new TipoParentescoRelacion();
			referenciaDos.setPersonaNatural(personaNatural);
			referenciaDos.setTipoParentescoRelacion(noFamiliar2);
			referenciaDos.setFamiliarNoVivaUd(RespuestaEnum.NO.getCodigo());
			referenciaLista.add(referenciaDos);

			personaNatural.setReferenciaFormularioCollection(referenciaLista);
		}

		if (personaNatural.getReferenciaComercialFormularioCollection() == null) {
			Collection<ReferenciaComercial> referenciaComercialLista = new ArrayList<ReferenciaComercial>();
			ReferenciaComercial referenciaComercialUno = new ReferenciaComercial();
			referenciaComercialUno.setPersonaNatural(personaNatural);
			referenciaComercialLista.add(referenciaComercialUno);

			ReferenciaComercial referenciaComercialDos = new ReferenciaComercial();
			referenciaComercialDos.setPersonaNatural(personaNatural);
			referenciaComercialLista.add(referenciaComercialDos);

			personaNatural.setReferenciaComercialFormularioCollection(referenciaComercialLista);
		}

		if (personaNatural.getReferenciaBancariaFormularioCollection() == null) {
			Collection<ReferenciaBancaria> referenciaBancariaLista = new ArrayList<ReferenciaBancaria>();
			ReferenciaBancaria referenciaBancariaUno = new ReferenciaBancaria();
			referenciaBancariaUno.setPersonaNatural(personaNatural);
			referenciaBancariaUno.setInstitucionFinanciera(new InstitucionFinanciera(new TipoInstitucionFinanciera()));
			referenciaBancariaUno.setTipoServicioInstFin(new TipoServicioInstFin());
			referenciaBancariaLista.add(referenciaBancariaUno);

			ReferenciaBancaria referenciaBancariaDos = new ReferenciaBancaria();
			referenciaBancariaDos.setPersonaNatural(personaNatural);
			referenciaBancariaDos.setInstitucionFinanciera(new InstitucionFinanciera(new TipoInstitucionFinanciera()));
			referenciaBancariaDos.setTipoServicioInstFin(new TipoServicioInstFin());
			referenciaBancariaLista.add(referenciaBancariaDos);

			personaNatural.setReferenciaBancariaFormularioCollection(referenciaBancariaLista);
		}

		if (personaNatural.getBiometrica() == null) {
			// biometrica
			Biometrica biometrica = new Biometrica();
			// biometrica.setPersonaNatural(personaNatural);
			biometrica.setGanadoPerdido(GanadoPerdidoEnum.GANADO.getCodigo());
			personaNatural.setBiometrica(biometrica);
		}

		if (personaNatural.getInformacionAdicional() == null) {
			InformacionAdicional informacionAdicional = new InformacionAdicional();
			informacionAdicional.setConduceMoto(RespuestaEnum.NO.getCodigo());
			informacionAdicional.setPiloto(RespuestaEnum.NO.getCodigo());
			informacionAdicional.setRespuestaSN(RespuestaEnum.NO.getCodigo());
			informacionAdicional.setPersonaNatural(personaNatural);
			personaNatural.setInformacionAdicional(informacionAdicional);
		}

		if (personaNatural.getIngresoMensual() == null) {
			IngresoMensual ingresoMensual = new IngresoMensual();
			ingresoMensual.setMntIngresoMensual(new BigDecimal(0));
			ingresoMensual.setMntEgresoMensual(new BigDecimal(0));
			ingresoMensual.setPersonaNatural(personaNatural);
			personaNatural.setIngresoMensual(ingresoMensual);
		}

		if (personaNatural.getIngresoAnualCollection() == null) {
			int anioActual = Calendar.getInstance().get(Calendar.YEAR);
			// anio menos 1
			IngresoAnual anio1 = new IngresoAnual();
			Integer a1 = anioActual - 1;
			anio1.setAnio(a1.shortValue());
			anio1.setPersonaNatural(personaNatural);
			// anio menos 2
			IngresoAnual anio2 = new IngresoAnual();
			Integer a2 = anioActual - 2;
			anio2.setAnio(a2.shortValue());
			anio2.setPersonaNatural(personaNatural);

			List<IngresoAnual> ingAnualList = new ArrayList<IngresoAnual>();
			ingAnualList.add(anio2);
			ingAnualList.add(anio1);

			personaNatural.setIngresoAnualCollection(ingAnualList);
		}
		// perfil financiero tmp
		if (personaNatural.getPerfilFinancieroNatural() == null) {
			PerfilFinancieroNatural perfilFinancieroNatural = new PerfilFinancieroNatural();
			perfilFinancieroNatural.setMntActivos(BigDecimal.ZERO);
			perfilFinancieroNatural.setMntPasivos(BigDecimal.ZERO);
			personaNatural.setPerfilFinancieroNatural(perfilFinancieroNatural);
		}
		if (personaNatural.getTipoRiesgo() == null) {
			personaNatural.setTipoRiesgo(new TipoRiesgo());
		}

		Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);

		if (personaNatural.getPaisNacionalidad() == null) {
			personaNatural.setPaisNacionalidad(pais);

		}

		if (personaNatural.getCiudadNacimiento() == null) {
			Provincia provincia = new Provincia();
			provincia.setPais(pais);
			Ciudad ciudad = new Ciudad();
			ciudad.setPais(pais);
			Canton canton = new Canton();
			canton.setProvincia(provincia);
			Parroquia parroquia = new Parroquia();
			parroquia.setCanton(canton);

			personaNatural.setCiudadNacimiento(ciudad);
		}

		if (personaNatural.getProfesion() == null) {
			personaNatural.setProfesion(new Profesion());
		}

		if (personaNatural.getOcupacion() == null) {
			Ocupacion ocupacion = new Ocupacion();
			ocupacion.setTipoRiesgo(new TipoRiesgo());
			personaNatural.setOcupacion(ocupacion);
		}

		if (personaNatural.getEstadoCivil() == null) {
			EstadoCivil estadoCivil = new EstadoCivil(EstadoCivilEnum.SOLTERO.getCodigo());
			personaNatural.setEstadoCivil(estadoCivil);
		}

		if (personaNatural.getConyuge() == null) {
			PersonaNatural conyuge = new PersonaNatural();
			conyuge.setPersona(new Persona());
			conyuge.setTipoIdentificacion(new TipoIdentificacion(TipoIdentificacionEnum.CEDULA.getCodigo()));
			personaNatural.setConyuge(conyuge);
		}
	}

	/**
	 * Consulta en Smart Data.
	 * 
	 * Si no enceuntra en SmartData pero si existe en Cliente unico, se guarda lo
	 * encontrado en Cliente unico en SmartData
	 *
	 * @param noDocumento
	 * @return
	 * @throws NoEncuentraDatosException
	 */
	private void consultaEnSmartData(String noDocumento, RespuestaGeneralDto respuesta, String usuario) {
		try {
			// Busca en SmartData
			PersonaSd personaSD = smartDataSdServicio.consultaClienteSmartData(noDocumento, true);

			if (personaSD.getPersonaNatural() == null) {
				respuesta.setEncuentraSd(false);
			} else {
				respuesta.setEncuentraSd(true);
			}

			respuesta.setPersonaSD(personaSD);
		} catch (SmartdataException e) {
		}
	}

	/**
	 * Transforma persona natural de Cliente Unico a persona de smartdata y luego lo
	 * persiste en SmartData.
	 * 
	 * @param respuesta
	 * @param usuario
	 */
	private void persistirClienteUnicoEnSmarData(RespuestaGeneralDto respuesta, String usuario) {
		// 1. Transforma PersonaNatural de CU a personaNatural de SD
		TransformSdCuUtil t = new TransformSdCuUtil();
		PersonaSd personaSd = t.transformarPersonaNaturalCuEnPersonaSd(respuesta.getPersonaNatural(), usuario);

		// existe en persona el secuencial pero no en persona natural
		if (respuesta.getPersonaSD() != null && respuesta.getPersonaSD().getSecPersona() != null) {
			personaSd.setSecPersona(respuesta.getPersonaSD().getSecPersona());
		}

		System.out.println("---------->>>> PersonaSd:" + personaSd);

		try {
			// 3. Persiste Smartdata
			smartDataSdServicio.guardaEnSmartData(personaSd);

			// 4. Se pone el objeto persistido en la respuesta para la pantalla
			respuesta.setPersonaSD(personaSd);
		} catch (SmartdataException e) {
			respuesta.setMensaje("No se pudo guardar en smartdata noDocumento: "
					+ respuesta.getPersonaNatural().getIdentificacion() + ", por: " + e.getMessage());
			respuesta.setHayMensaje(true);
		}
	}

	/**
	 * Consulta en cliente unico.
	 * 
	 * @param noDocumento
	 * @return
	 * @throws NoEncuentraDatosException
	 */
	private void consultaEnClienteUnico(String noDocumento, RespuestaGeneralDto respuesta) {
		// Se consulta la persona natural.
		PersonaNatural personaNatural = personaNaturalServicio.findByPkParaFormularioWeb(noDocumento);

		// Si no existe en persona natural, se busca en beneficiarios
		if (personaNatural != null) {
			// Se inicializa los datos que no vienen de la persona
			inicializaPersonaNaturalCu(personaNatural);

			respuesta.setPersonaNatural(personaNatural);
			respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.CU);
			respuesta.setNuevoRegistro(false);
			respuesta.setEncuentraCu(true);
		}
	}

	/**
	 * Consulta persona juridica en cliente unico.
	 * 
	 * @param noDocumento
	 * @return
	 * @throws NoEncuentraDatosException
	 */
	private void consultaEnClienteUnicoPersonaJuridica(String noDocumento, RespuestaGeneralDto respuesta) {
		boolean loadRepresentanteLegal = false;
		boolean loadDirecciones = true;
		boolean loadDireccionesConTelefonos = true;
		boolean loadPerfilFinanciero = true;
		boolean loadAccionistas = true;

		// Se consulta la persona natural.
		PersonaJuridica personaJuridica = personaJuridicaServicio.findByNoDocumento(noDocumento, loadRepresentanteLegal,
				loadDirecciones, loadDireccionesConTelefonos, loadPerfilFinanciero, loadAccionistas);

		// Si no existe en persona natural, se busca en beneficiarios
		if (personaJuridica != null) {
			respuesta.setPersonaJuridica(personaJuridica);
			respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.CU);
			respuesta.setNuevoRegistro(false);
			respuesta.setEncuentraCu(true);
		}
	}

	/**
	 * Consulta Persona Juridica en Smart Data.
	 * 
	 *
	 * @param noDocumento
	 * @return
	 * @throws NoEncuentraDatosException
	 */
	private void consultaPersonaJuridicaEnSmartData(String noDocumento, RespuestaGeneralDto respuesta, String usuario) {
		try {
			// Busca en SmartData
			PersonaSd personaSD = smartDataSdServicio.consultaPersonaJuridicaSmartData(noDocumento, true);

			if (personaSD != null) {
				respuesta.setEncuentraSd(true);
				respuesta.setLugarEncuentra(LugarEncuentraInfoEnum.SD);
				respuesta.setPersonaSD(personaSD);
			} else {
				respuesta.setEncuentraSd(false);
			}
		} catch (SmartdataException e) {
			respuesta.setEncuentraSd(false);
			respuesta.setPersonaSD(null);
		}
	}
}
