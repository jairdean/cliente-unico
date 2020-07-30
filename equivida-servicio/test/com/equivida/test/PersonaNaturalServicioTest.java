package com.equivida.test;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.SexoEnum;
import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Canton;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.EmpleoDependiente;
import com.equivida.modelo.EmpleoIndependiente;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.IngresoMensual;
import com.equivida.modelo.Ocupacion;
import com.equivida.modelo.Pais;
import com.equivida.modelo.PerfilFinancieroJuridica;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Profesion;
import com.equivida.modelo.Provincia;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoDireccion;
import com.equivida.modelo.TipoDireccionElectronica;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoPersonaJuridica;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.PersonaNaturalServicioRemoto;
import com.equivida.test.util.ContextUtil;

public class PersonaNaturalServicioTest {

	static PersonaNaturalServicioRemoto servicioRemoto;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		servicioRemoto = (PersonaNaturalServicioRemoto) ContextUtil
				.lookup("java:PersonaNaturalServicio/remote");
	}

	@Test
	public void testCrearSolicitudPersonaNatural() {

		PersonaNatural personaNatural = new PersonaNatural();

		Persona persona = new Persona();

		TipoIdentificacion tipoIdentificacion = new TipoIdentificacion('C');
		persona.setTipoIdentificacion(tipoIdentificacion); // cedula,ruc
		persona.setCliente('S');// indicador cliente S o N

		short tipoEmpleo = 0;// 0 = Dep , 1 = Independiebnte
		short numeroHijos = 0;// 0 si no tiene hijos

		IngresoMensual ingresoMensual = new IngresoMensual();
		ingresoMensual.setPersonaNatural(personaNatural);
		ingresoMensual.setMntIngresoMensual(BigDecimal.valueOf(2000));
		ingresoMensual.setMntEgresoMensual(BigDecimal.valueOf(1000));
		personaNatural.setIngresoMensual(ingresoMensual);

		personaNatural.setApellidoMaterno("Zurita");
		personaNatural.setApellidoPaterno("Cardenas");
		EstadoCivil estadoCivil = new EstadoCivil(
				Constantes.ESTADO_CIVIL_ID_SOLTERO);
		personaNatural.setEstadoCivil(estadoCivil);
		personaNatural.setFchNacimiento(new Date());
		personaNatural.setIdentificacion("17134714251");
		personaNatural.setNumHijos(numeroHijos);
		Pais pais = new Pais(Constantes.PAIS_ID_ECUADOR);
		personaNatural.setPaisNacionalidad(pais);
		Profesion profesion = new Profesion(Constantes.PROFESION_ID_DEFAULT);
		Ocupacion ocupacion = new Ocupacion(Constantes.OCUPACION_ID_NO_DISPONIBLE);
		personaNatural.setOcupacion(ocupacion);
		personaNatural.setPersona(persona);
		personaNatural.setPrimerNombre("Daniel");
		personaNatural.setSegundoNombre("Esteban");
		personaNatural.setSexo(SexoEnum.MASCULINO.getCodigo());// M o F
		personaNatural.setTipoEmpleo(tipoEmpleo);
		personaNatural.setTipoIdentificacion(tipoIdentificacion);
		Canton canton = new Canton(Integer.valueOf(1).shortValue());
		Ciudad ciudad = new Ciudad();
		ciudad.setSecCiudad(new Short("1"));
		personaNatural.setCiudadNacimiento(ciudad);
		personaNatural.setProfesion(profesion);
		personaNatural.setPersona(persona);

		Collection<DireccionTelefono> direccionTelefonos = new ArrayList<DireccionTelefono>();
		DireccionTelefono direccionTelefono = new DireccionTelefono();
		direccionTelefono.setPersona(persona);

		Collection<Direccion> direcciones = new ArrayList<Direccion>();

		Direccion direccion = new Direccion();
		direccion.setBarrio("Barrio");
		direccion.setDireccionPrincipal(true);
		direccion.setEdificio("edificio");
		direccion.setEnvioCorrespondencia(RespuestaEnum.SI.getCodigo());
		direccion.setNumero("Oe-12");
		direccion.setOficina("Oficina");
		direccion.setPiso("");
		direccion.setPrincipal("calle principal");
		direccion.setReferencia("referencia");
		direccion.setSecundaria("calle secundaria");
		TipoDireccion tipoDireccion = new TipoDireccion(
				Constantes.TIPO_DIRECCION_ID_DOMICILIO);
		direccion.setTipoDireccion(tipoDireccion);
		direccion.setVerificada(RespuestaEnum.NO.getCodigo());
		direccion.setPersona(persona);
		direccion.setVerificada(RespuestaEnum.NO.getCodigo());
		Provincia provincia = new Provincia();
		provincia.setPais(pais);
		// Canton canton = new Canton();
		direccion.setLatitud(new BigDecimal(0));
		direccion.setLongitud(new BigDecimal(0));
		direccion.setCanton(canton);
		direcciones.add(direccion);

		Collection<Telefono> telefonos = new ArrayList<Telefono>();
		Telefono telefono = new Telefono();
		telefono.setCodArea("02");
		telefono.setExtTelefono("100");
		telefono.setNroTelefono("56954754");
		telefono.setPais(pais);
		telefono.setPersona(persona);
		TipoTelefono tipoTelefono = new TipoTelefono(
				Constantes.TIPO_TELEFONO_DOMICILIO);
		telefono.setTipoTelefono(tipoTelefono);
		telefonos.add(telefono);

		Collection<DireccionElectronica> direccionElectronicaCollection = new ArrayList<DireccionElectronica>();
		DireccionElectronica direccionElectronica = new DireccionElectronica();
		direccionElectronica.setPersona(persona);
		direccionElectronica.setDireccionElectronica("mail@mail.com");
		TipoDireccionElectronica tipoDireccionElectronica = new TipoDireccionElectronica(
				Constantes.TIPO_DIRECCION_ELECTRONICA_ID_PERSONAL);
		direccionElectronica
				.setTipoDireccionElectronica(tipoDireccionElectronica);
		direccionElectronicaCollection.add(direccionElectronica);
		direccionTelefonos.add(direccionTelefono);
		//
		direccionTelefono.setTelefono(telefono);
		direccionTelefono.setDireccion(direccion);
		direccion.setDireccionTelefonoCollection(direccionTelefonos);

		// persona.setTelefonoCollection(telefonos);
		persona.setDireccionCollection(direcciones);
		persona.setPersonaNaturalTransient(personaNatural);
		// persona.setDireccionTelefonoCollection(direccionTelefonos);

		Collection<EmpleoDependiente> empleoLista = new ArrayList<EmpleoDependiente>();
		EmpleoDependiente empleoDependiente = new EmpleoDependiente();
		empleoDependiente.setCargo("Asesor");
		empleoDependiente.setEstado(EstadoEnum.ACTIVO.getCodigo());
		empleoDependiente.setNegocioEmpresa("Mi negocio");
		ActividadEconomica actividadEconomica = new ActividadEconomica(
				Constantes.ACTIVIDAD_ECONOMICA_DEFAULT);
		empleoDependiente.setActividadEconomica(actividadEconomica);
		empleoDependiente.setPersonaNatural(personaNatural);
		empleoDependiente.setTiempoEmpresa(BigDecimal.valueOf(2));
		empleoLista.add(empleoDependiente);
		personaNatural.setEmpleoDependienteCollection(empleoLista);

		Collection<EmpleoIndependiente> empleoIndependienteLista = new ArrayList<EmpleoIndependiente>();
		EmpleoIndependiente empleoIndependiente = new EmpleoIndependiente();
		empleoIndependiente.setPersonaNatural(personaNatural);
		empleoIndependiente.setEstado(EstadoEnum.ACTIVO.getCodigo());
		ActividadEconomica actividadEconomica2 = new ActividadEconomica(
				Constantes.ACTIVIDAD_ECONOMICA_DEFAULT);
		empleoIndependiente.setActividadEconomica(actividadEconomica2);
		empleoIndependiente.setTiempoEmpresa(new BigDecimal(2));
		empleoIndependiente.setCargo("asesor");
		empleoIndependienteLista.add(empleoIndependiente);
		personaNatural
				.setEmpleoIndependienteCollection(empleoIndependienteLista);

		try {
			servicioRemoto.crearPersonaNaturalFormularioWeb(personaNatural);
		} catch (EmpleoDependienteException e) {
			e.printStackTrace();
		} catch (EmpleoIndependienteException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ErrorIngresoWsSiseException e) {
			e.printStackTrace();
		}

		// fail("Not yet implemented");
	}

	@Test
	public void testCrearSolicitudPersonaJuridica() {

		PersonaJuridica personaJuridica = new PersonaJuridica();

		Persona persona = new Persona();

		personaJuridica.setPersona(persona);

		TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
		tipoIdentificacion
				.setCodTipoIdentificacion(Constantes.TIPO_IDENTIFICACION_CEDULA);

		personaJuridica
				.setRazonSocial("Laboratorio linico bacte Gonzalo Zrita");
		personaJuridica.setIdentificacion("000111222333");
		personaJuridica.setNombreComercial("Zurita & Zurita Laboratorios");
		personaJuridica.setObjetoSocial("Objeto social");
		personaJuridica.setFchConstitucion(new Date());
		// contacto
		personaJuridica.setNombreContacto("Gonzalo Cardenas");
		personaJuridica.setEmailContacto("daniel.card@saviasoft.com");

		persona.setTipoIdentificacion(tipoIdentificacion); // cedula,ruc
		persona.setCliente('N');// indicador cliente S o N

		TipoPersonaJuridica tipoPersonaJuridica = new TipoPersonaJuridica(
				Constantes.TIPO_PERSONA_JURIDICA_DEFAULT);
		personaJuridica.setTipoPersonaJuridica(tipoPersonaJuridica);

		// perfil financiero
		PerfilFinancieroJuridica perfilFinancieroJuridica = new PerfilFinancieroJuridica();
		perfilFinancieroJuridica.setPersonaJuridica(personaJuridica);
		perfilFinancieroJuridica.setActivos(BigDecimal.TEN);
		perfilFinancieroJuridica.setPasivos(BigDecimal.ZERO);
		perfilFinancieroJuridica.setPatrimonio(BigDecimal.TEN);
		perfilFinancieroJuridica.setIngresos(BigDecimal.ONE);
		perfilFinancieroJuridica.setEgresos(BigDecimal.ONE);
		perfilFinancieroJuridica.setUtilidad(BigDecimal.ZERO);

		personaJuridica.setPerfilFinancieroJuridicaTransient(perfilFinancieroJuridica);

		TipoIdentificacion tipoIdentificacionR = new TipoIdentificacion(
				Constantes.TIPO_IDENTIFICACION_RUC);

		personaJuridica.setTipoIdentificacion(tipoIdentificacionR);
		persona.setTipoIdentificacion(tipoIdentificacionR);

		Pais pais = new Pais(Constantes.PAIS_ID_ECUADOR);
		personaJuridica.setPais(pais);

		ActividadEconomica actividadEconomica = new ActividadEconomica(
				Constantes.ACTIVIDAD_ECONOMICA_DEFAULT);
		personaJuridica.setActividadEconomica(actividadEconomica);

		Collection<Direccion> direccionLista = new ArrayList<Direccion>();

		Direccion direccionTrabajo = new Direccion();
		TipoDireccion tipoDireccionTrabajo = new TipoDireccion();
		tipoDireccionTrabajo
				.setCodTipoDireccion(Constantes.TIPO_DIRECCION_ID_TRABAJO);
		direccionTrabajo.setPrincipal(new String());
		direccionTrabajo.setNumero(new String());
		direccionTrabajo.setBarrio(new String());
		direccionTrabajo.setTipoDireccion(tipoDireccionTrabajo);
		direccionTrabajo.setPersona(persona);
		// direccionTrabajo.setCanton(canton);
		direccionTrabajo.setLatitud(new BigDecimal(0));
		direccionTrabajo.setLongitud(new BigDecimal(0));
		direccionLista.add(direccionTrabajo);

		persona.setDireccionCollection(direccionLista);

		Collection<Telefono> telefonoLista = new ArrayList<Telefono>();
		// telefono oficina
		Telefono telefonoOfi = new Telefono();
		TipoTelefono tipoTelefonoOfi = new TipoTelefono();
		tipoTelefonoOfi.setCodTipoTelefono(Constantes.TIPO_TELEFONO_OFICINA);
		telefonoOfi.setTipoTelefono(tipoTelefonoOfi);
		telefonoOfi.setPais(pais);
		telefonoOfi.setPersona(persona);
		telefonoOfi.setCodArea("2");
		telefonoOfi.setNroTelefono("22424777");
		telefonoLista.add(telefonoOfi);

		Telefono telefonoMovilOficina = new Telefono();
		TipoTelefono tipoTelefonoMovilPersonal = new TipoTelefono();
		tipoTelefonoMovilPersonal
				.setCodTipoTelefono(Constantes.TIPO_TELEFONO_MOVIL_OFICINA);
		telefonoMovilOficina.setTipoTelefono(tipoTelefonoMovilPersonal);
		telefonoMovilOficina.setPersona(persona);
		telefonoMovilOficina.setPais(pais);
		telefonoMovilOficina.setCodArea("9");
		telefonoMovilOficina.setNroTelefono("6793991");
		telefonoLista.add(telefonoMovilOficina);

		Telefono fax = new Telefono();
		TipoTelefono tipoTelefonoFax = new TipoTelefono();
		tipoTelefonoFax.setCodTipoTelefono(Constantes.TIPO_TELEFONO_FAX);
		fax.setTipoTelefono(tipoTelefonoFax);
		fax.setPersona(persona);
		fax.setPais(pais);
		fax.setCodArea("2");
		fax.setNroTelefono("2242776");
		telefonoLista.add(fax);

		// persona.setTelefonoCollection(telefonoLista);

		// servicioRemoto.crearSolicitudPersonaJuridica(personaJuridica);

		// fail("Not yet implemented");
	}
}