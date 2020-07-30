/**
 * 
 */
package com.equivida.buenviaje.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.equivida.buenviaje.constante.TipoDocumentoEnum;
import com.equivida.buenviaje.dto.PersonaConsulta;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Canton;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.Ocupacion;
import com.equivida.modelo.Pais;
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
import com.equivida.modelo.TipoRiesgo;
import com.equivida.modelo.TipoTelefono;
import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.model.TipoIdentificacionSd;

/**
 * @author juan
 *
 */
public class TransformaHelper {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

	public PersonaNatural transformarDesdeBuenViaje(PersonaConsulta pc, PersonaNatural pnEnriquecida) {

		Persona persona = createPersona(pc, pnEnriquecida);

		PersonaNatural resp = new PersonaNatural();

		resp.setPersona(persona);

		resp.setTipoIdentificacion(getTipoIdentificacion(pc.getTipoDocumento()));
		resp.setIdentificacion(pc.getNoDocumento());
		resp.setApellidoPaterno(pc.getPrimerApellido());
		resp.setPrimerNombre(pc.getPrimerNombre());

		if (pnEnriquecida != null) {
			resp.setApellidoMaterno(pnEnriquecida.getApellidoMaterno());
			resp.setSegundoNombre(pnEnriquecida.getSegundoNombre());
			resp.setFchNacimiento(pnEnriquecida.getFchNacimiento());
			resp.setSexo(pnEnriquecida.getSexo());
			resp.setEstadoCivil(pnEnriquecida.getEstadoCivil());
			if (pnEnriquecida.getConyuge() != null) {
				resp.setConyuge(pnEnriquecida.getConyuge());
			}
			resp.setTipoEmpleo(pnEnriquecida.getTipoEmpleo());
			resp.setNumHijos(pnEnriquecida.getNumHijos());
			resp.setEstadoCivil(pnEnriquecida.getEstadoCivil());
			resp.setPaisNacionalidad(pnEnriquecida.getPaisNacionalidad());
			resp.setCiudadNacimiento(pnEnriquecida.getCiudadNacimiento());
			resp.setProfesion(pnEnriquecida.getProfesion());
			resp.setOcupacion(pnEnriquecida.getOcupacion());
			resp.setTipoRiesgo(pnEnriquecida.getTipoRiesgo());
			resp.setMntSaldoMensual(pnEnriquecida.getMntSaldoMensual());
		} else {
			if (pc.getFechaNacimiento() != null) {
				resp.setFchNacimiento(pc.getFechaNacimiento());
			}
			if (pc.getGenero() != null) {
				resp.setSexo(pc.getGenero().toString().charAt(0));
			}
			if (pc.getCodigoPais() != null && pc.getCodigoPais().trim().length() > 0) {
				resp.setPaisNacionalidad(getPais(pc.getCodigoPais()));
			}

			resp.setConyuge(null);
			resp.setTipoEmpleo(new Short("0"));
			resp.setNumHijos(new Short("0"));
			resp.setEstadoCivil(new EstadoCivil(new Short("1")));
			resp.setCiudadNacimiento(new Ciudad(new Short("0")));
			resp.setProfesion(new Profesion(new Short("0")));
			resp.setOcupacion(new Ocupacion(new Short("0")));
			resp.setTipoRiesgo(new TipoRiesgo(new Short("0")));
			resp.setMntSaldoMensual(BigDecimal.ZERO);
		}

		return resp;
	}

	/**
	 * Crea persona.
	 * 
	 * @param pc
	 * @param pnEnriquecida
	 * @return
	 */
	private Persona createPersona(PersonaConsulta pc, PersonaNatural pnEnriquecida) {
		Persona persona = new Persona();
		if (pnEnriquecida != null && pnEnriquecida.getPersona() != null) {
			persona.setSecPersona(pnEnriquecida.getPersona().getSecPersona());
		} else {
			persona.setSecPersona(null);
			persona.setDenominacion(pc.getPrimerNombre().concat(" ").concat(pc.getPrimerApellido()));
			persona.setCliente('S');
		}

		if (TipoDocumentoEnum.R.equals(pc.getTipoDocumento())) {
			persona.setTipoIdentificacion(getTipoIdentificacion(TipoDocumentoEnum.C));
		} else {
			persona.setTipoIdentificacion(getTipoIdentificacion(pc.getTipoDocumento()));
		}

		// Se asigna datos relacionados a la persona
		persona.setTelefonoCollection(crearTelefonos(persona, pc));
		persona.setDireccionCollection(crearDireccion(persona, pc));
		persona.setDireccionElectronicaFormularioCollection(crearDireccionElectronica(persona, pc));
		persona.setMotivoSeguroFormularioCollection(null);

		return persona;
	}

	/**
	 * Crea persona.
	 * 
	 * @param pc
	 * @param pnEnriquecida
	 * @return
	 */
	private Persona createPersona(PersonaConsulta pc) {
		Persona persona = new Persona();
		persona.setSecPersona(null);
		persona.setTipoIdentificacion(getTipoIdentificacion(pc.getTipoDocumento()));
		persona.setCliente('S');
		persona.setDenominacion(pc.getRazonSocial());
		llenarPersonaAuditoria("buenviaje", persona);

		// Se asigna datos relacionados a la persona
		persona.setTelefonoCollection(crearTelefonos(persona, pc));
		persona.setDireccionCollection(crearDireccion(persona, pc));
		persona.setDireccionElectronicaFormularioCollection(crearDireccionElectronica(persona, pc));
		persona.setMotivoSeguroFormularioCollection(null);

		return persona;
	}

	private void llenarPersonaAuditoria(String usuario, Persona persona) {
		persona.setUsrCreacion(usuario);
		persona.setTtyCreacion(getCurrentClientIpAddress());
		persona.setPrgCreacion(usuario);
		persona.setCtaCreacion(this.getClass().getSimpleName());
		persona.setUsrModificacion(usuario);
		persona.setTtyModificacion(getCurrentClientIpAddress());
		persona.setPrgModificacion(usuario);
		persona.setCtaModificacion(this.getClass().getSimpleName());
	}

	private String getCurrentClientIpAddress() {
		String remoteIp = "";
		return remoteIp;
	}

	/**
	 * Crea telefono de la persona.
	 * 
	 * @param persona
	 * @param pc
	 * @return
	 */
	private Collection<Telefono> crearTelefonos(Persona persona, PersonaConsulta pc) {
		Collection<Telefono> resp = new ArrayList<Telefono>();

		if (pc.getTelefono() != null && pc.getTelefono().trim().length() > 0 && pc.getCodigoPais() != null
				&& pc.getCodigoPais().trim().length() > 0 && pc.getCodigoArea() != null
				&& pc.getCodigoArea().trim().length() > 0) {
			Telefono t = new Telefono();
			t.setCodArea(pc.getCodigoArea());
			t.setNroTelefono(pc.getTelefono());
			t.setTipoTelefono(new TipoTelefono(new Short("0")));
			t.setEstado('A');
			t.setPais(new Pais(new Short(pc.getCodigoPais())));
			t.setPersona(persona);

			resp.add(t);
		} else {
			resp = null;
		}

		return resp;
	}

	/**
	 * Crea direccion de persona.
	 * 
	 * @param persona
	 * @param pc
	 * @return
	 */
	private Collection<Direccion> crearDireccion(Persona persona, PersonaConsulta pc) {
		Collection<Direccion> resp = new ArrayList<Direccion>();

		if (pc.getCallePrincipal() != null && pc.getCallePrincipal().trim().length() > 0 && pc.getCiudad() != null
				&& pc.getCiudad().trim().length() > 0) {
			Direccion d = new Direccion();
			d.setTipoDireccion(new TipoDireccion(new Short("1")));
			d.setEstado('A');
			d.setPrincipal(pc.getCallePrincipal());
			d.setNumero(pc.getNumeroDireccion());
			d.setSecundaria(pc.getCalleSecundaria());
			d.setBarrio("");
			d.setEnvioCorrespondencia('N');
			d.setVerificada('N');
			// Para SISE no se necesita provincia ni pais porque los obtiene de
			// canton
			d.setCanton(getCanton(pc.getCiudad()));
			d.setPersona(persona);

			resp.add(d);
		} else {
			resp = null;
		}

		return resp;
	}

	/**
	 * 
	 * @param persona
	 * @param pc
	 * @return
	 */
	private Collection<DireccionElectronica> crearDireccionElectronica(Persona persona, PersonaConsulta pc) {
		Collection<DireccionElectronica> resp = new ArrayList<DireccionElectronica>();

		if (pc.getCorreoElectronico() != null && pc.getCorreoElectronico().trim().length() > 0 && pc.getCiudad() != null
				&& pc.getCiudad().trim().length() > 0) {
			DireccionElectronica de = new DireccionElectronica();
			de.setDireccionElectronica(pc.getCorreoElectronico().trim());
			de.setEstado('A');
			de.setPersona(persona);
			de.setTipoDireccionElectronica(new TipoDireccionElectronica(new Short("1")));

			resp.add(de);
		} else {
			resp = null;
		}

		return resp;
	}

	/**
	 * Obtiene Canton.
	 * 
	 * @param ciudad
	 * @return
	 */
	private Canton getCanton(String ciudad) {
		Canton c = new Canton();
		c.setSecCanton(new Short(ciudad));

		return c;
	}

	/**
	 * Obtiene Provincia.
	 * 
	 * @param provincia
	 * @return
	 */
	private Provincia getProvincia(String provincia) {
		Provincia p = new Provincia();
		p.setSecProvincia(new Short(provincia));

		return p;
	}

	/**
	 * Obtiene Pais.
	 * 
	 * @param pais
	 * @return
	 */
	private Pais getPais(String pais) {
		Pais p = new Pais();
		p.setCodPais(new Short(pais));

		return p;
	}

	/**
	 * Obtiene el tipo de documento.
	 * 
	 * @param tipo
	 * @return
	 */
	private TipoIdentificacion getTipoIdentificacion(TipoDocumentoEnum tipo) {
		TipoIdentificacion resp = new TipoIdentificacion();
		resp.setCodTipoIdentificacion(tipo.toString().charAt(0));

		return resp;
	}

	/**
	 * Crea persona juridica en base de los parametros que se obtienen desde buen
	 * viaje.
	 * 
	 * @param pc
	 * @return
	 * @throws ParseException
	 */
	public PersonaJuridica crearPersonaJuridicaDesdeBuenViaje(PersonaConsulta pc) throws ParseException {
		Date fechaConstitucion = SDF.parse("01/01/1901");

		Persona persona = createPersona(pc);

		PersonaJuridica pj = new PersonaJuridica();
		pj.setPersona(persona);
		pj.setTipoIdentificacion(getTipoIdentificacion(pc.getTipoDocumento()));
		pj.setIdentificacion(pc.getNoDocumento());
		pj.setRazonSocial(pc.getRazonSocial());
		pj.setNombreComercial(pc.getRazonSocial());
		pj.setTipoPersonaJuridica(new TipoPersonaJuridica(new Short("0")));
		pj.setActividadEconomica(new ActividadEconomica(new Short("3")));
		if (pc.getCodigoPais() != null && pc.getCodigoPais().trim().length() > 0) {
			pj.setPais(getPais(pc.getCodigoPais()));
		} else {
			pj.setPais(getPais("0"));
		}
		pj.setFchConstitucion(fechaConstitucion);
		pj.setObjetoSocial("");
		pj.setNombreContacto("");
		pj.setEmailContacto("");

		return pj;
	}

	/**
	 * Crea persona juridica de Smart Data, en base de los parametros que se
	 * obtienen desde buen viaje.
	 * 
	 * @param pc
	 * @return
	 */
	public PersonaJuridicaSd crearPersonaJuridicaSdDesdeBuenViaje(PersonaConsulta pc) {
		PersonaSd persona = new PersonaSd();
		persona.setSecPersona(null);
		persona.setIdentificacion(pc.getNoDocumento());
		persona.setDenominacion(pc.getRazonSocial());
		persona.setCodTipoIdentificacion(new TipoIdentificacionSd(pc.getTipoDocumento().toString().charAt(0)));

		PersonaJuridicaSd pj = new PersonaJuridicaSd();
		pj.setSecPersona(persona);
		pj.setIdentificacion(pc.getNoDocumento());
		pj.setRazonSocial(pc.getRazonSocial());
		pj.setCodTipoIdentificacion(new TipoIdentificacionSd(pc.getTipoDocumento().toString().charAt(0)));
		pj.setUsrCreacion("buenviaje");
		pj.setUsrModificacion("buenviaje");

		return pj;
	}
}
