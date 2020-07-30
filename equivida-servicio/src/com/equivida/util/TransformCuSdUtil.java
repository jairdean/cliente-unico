package com.equivida.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.equivida.exception.NoEncuentraDatosException;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Provincia;
import com.equivida.modelo.Telefono;
import com.equivida.smartdata.constante.CodigoAsumidoEnum;
import com.equivida.smartdata.constante.EstadoEnum;
import com.equivida.smartdata.constante.TipoParentescoEnum;
import com.equivida.smartdata.helper.DataBookHelper;
import com.equivida.smartdata.model.CanalSd;
import com.equivida.smartdata.model.CantonSd;
import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.model.EstadoCivilSd;
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
import com.equivida.smartdata.model.TipoParentescoRelacionSd;
import com.equivida.smartdata.model.TipoTelefonoSd;
import com.equivida.smartdata.servicio.ParroquiaSdServicio;
import com.equivida.smartdata.servicio.PersonaSdServicio;

/**
 * Transforma Persona de CU a persona de SD.
 * 
 * @author juan
 *
 */
public class TransformCuSdUtil implements Serializable {
	private PersonaNatural personaNaturalCu;
	private PersonaSd personaSd;
	private String usuario;
	private ParroquiaSdServicio parroquiaSdServicio;
	private PersonaSdServicio personaSdServicio;

	public TransformCuSdUtil(PersonaNatural personaNaturalCu,
			PersonaSd personaSd, String usuario,
			ParroquiaSdServicio parroquiaSdServicio,
			PersonaSdServicio personaSdServicio) {
		this.personaNaturalCu = personaNaturalCu;
		this.personaSd = personaSd;
		this.usuario = usuario;
		this.parroquiaSdServicio = parroquiaSdServicio;
		this.personaSdServicio = personaSdServicio;
	}

	public void transformar() throws NoEncuentraDatosException {
		// 1. Transforma Persona de CU a SD
		transformaPersona();

		// 2. Transforma Persona Natural de CU a SD
		transformaPersonaNatural();
	}

	/**
	 * Obtiene los telefonos desde CU para SD.
	 * 
	 * @return
	 */
	public List<TelefonoSd> getTelefonos() {
		List<TelefonoSd> resp = new ArrayList<TelefonoSd>();

		if (personaNaturalCu.getPersona() != null
				&& personaNaturalCu.getPersona()
						.getTelefonoSinDireccionCollection() != null
				&& !personaNaturalCu.getPersona()
						.getTelefonoSinDireccionCollection().isEmpty()) {
			for (Telefono t : personaNaturalCu.getPersona()
					.getTelefonoSinDireccionCollection()) {
				TelefonoSd tsd = new TelefonoSd();
				tsd.setCodArea(t.getCodArea());
				tsd.setCodTipoTelefono(new TipoTelefonoSd(t.getTipoTelefono()
						.getCodTipoTelefono()));
				tsd.setEstado(EstadoEnum.A.getEstadoChar());
				tsd.setNroTelefono(t.getNroTelefono());
				tsd.setSecCanal(DataBookHelper.getCanalClienteUnico());
				tsd.setSecPersona(personaSd);
				tsd.setUsrCreacion(usuario);
				tsd.setUsrModificacion(usuario);

				// Se pone en la lista de respuesta
				resp.add(tsd);
			}
		}

		if (resp.isEmpty()) {
			resp = null;
		}

		return resp;
	}

	/**
	 * Arma las direcciones desde CU para SD.
	 * 
	 * @return
	 * @throws NoEncuentraDatosException
	 */
	public List<DireccionSd> getDirecciones() throws NoEncuentraDatosException {
		List<DireccionSd> resp = new ArrayList<DireccionSd>();

		if (personaNaturalCu.getPersona() != null
				&& personaNaturalCu.getPersona()
						.getDireccionActivosCollection() != null
				&& !personaNaturalCu.getPersona()
						.getDireccionActivosCollection().isEmpty()) {
			for (Direccion d : personaNaturalCu.getPersona()
					.getDireccionActivosCollection()) {
				StringBuffer dir = new StringBuffer(200);
				if (d.getPrincipal() != null
						&& d.getPrincipal().trim().length() > 0) {
					dir.append(d.getPrincipal().trim()).append(" ");
				}
				if (d.getNumero() != null && d.getNumero().trim().length() > 0) {
					dir.append(d.getNumero().trim()).append(" ");
				}
				if (d.getSecundaria() != null
						&& d.getSecundaria().trim().length() > 0) {
					dir.append(d.getSecundaria().trim());
				}

				DireccionSd dsd = new DireccionSd();
				dsd.setCodTipoDireccion(new TipoDireccionSd(d
						.getTipoDireccion().getCodTipoDireccion()));
				dsd.setDireccion(dir.toString());
				dsd.setEstado(EstadoEnum.A.getEstadoChar());
				dsd.setReferencia(d.getReferencia());
				dsd.setSecCanal(DataBookHelper.getCanalClienteUnico());
				if (d.getCanton() != null
						&& d.getCanton().getSecCanton() != null) {
					dsd.setSecCanton(new CantonSd(d.getCanton().getSecCanton()));

					Provincia provinciaCu = d.getCanton().getProvincia();
					if (provinciaCu != null
							&& provinciaCu.getSecProvincia() != null) {
						dsd.setSecProvincia(new ProvinciaSd(d.getCanton()
								.getProvincia().getSecProvincia()));
					}

					ParroquiaSd parroquiaPorDefecto = parroquiaSdServicio
							.obtenerSinParroquiaPorCanton(d.getCanton()
									.getSecCanton());
					if (parroquiaPorDefecto != null) {
						dsd.setSecParroquia(parroquiaPorDefecto);
					} else {
						throw new NoEncuentraDatosException(
								"No se puede determinar parroquia");
					}
				}
				dsd.setSecPersona(personaSd);
				dsd.setUsrCreacion(usuario);
				dsd.setUsrModificacion(usuario);

				// Se pone en la lista de respuesta
				resp.add(dsd);
			}
		}

		if (resp.isEmpty()) {
			resp = null;
		}

		return resp;
	}

	/**
	 * Se transforma datos de persona
	 */
	private void transformaPersona() throws NoEncuentraDatosException {
		// Datos de la persona
		personaSd.setIdentificacion(personaNaturalCu.getIdentificacion());
		personaSd.setDenominacion(personaNaturalCu.getApellidosNombres());
		personaSd.setCodTipoIdentificacion(getTipoIdentificacion());
		personaSd.setTelefonoList(getTelefonos());
		personaSd.setDireccionList(getDirecciones());
	}

	/**
	 * Transforma persona Natural.
	 */
	private void transformaPersonaNatural() {
		PersonaNaturalSd pn = personaSd.getPersonaNatural() != null ? personaSd
				.getPersonaNatural() : new PersonaNaturalSd();

		pn.setApellidoMaterno(personaNaturalCu.getApellidoMaterno());
		pn.setApellidoPaterno(personaNaturalCu.getApellidoPaterno());
		pn.setCodEstadoCivil(getEstadoCivil());
		pn.setCodPais(getPais());
		pn.setCodTipoIdentificacion(getTipoIdentificacion());
		pn.setFchFallecimiento(personaNaturalCu.getFchFallecimiento());
		// pn.setFchMatrimonio(personaNaturalCu.getf); TODO: de donde???
		pn.setFchNacimiento(personaNaturalCu.getFchNacimiento());
		pn.setIdentificacion(personaNaturalCu.getIdentificacion());
		pn.setPrimerNombre(personaNaturalCu.getPrimerNombre());
		pn.setSegundoNombre(personaNaturalCu.getSegundoNombre());
		pn.setSecCanal(getCanal());
		pn.setSecPersona(personaSd);
		pn.setSecProfesion(getProfesion());
		pn.setSexo(personaNaturalCu.getSexo());
		pn.setUsrCreacion(usuario);
		pn.setUsrModificacion(usuario);

		// Se llenan los datos del conyuge si los hay
		if (personaNaturalCu.getConyuge() != null
				&& personaNaturalCu.getConyuge().getIdentificacion() != null
				&& personaNaturalCu.getConyuge().getIdentificacion().trim()
						.length() > 0) {

			PersonaSd personaR = personaSdServicio
					.obtenerPersonaByIdentificacion(personaNaturalCu
							.getConyuge().getIdentificacion());

			if (personaR != null) {
				RelacionSd relacion = new RelacionSd();
				relacion.setPersonaP(personaSd);
				relacion.setPersonaR(personaR);
				relacion.setSecCanal(DataBookHelper.getCanalClienteUnico());
				relacion.setTipoParentesco(new TipoParentescoRelacionSd(
						TipoParentescoEnum.CONYUGE.getCodigoTipoParentesco()));
				relacion.setUsrCreacion(usuario);
				relacion.setUsrModificacion(usuario);

				if (personaSd.getRelaciones() == null) {
					personaSd.setRelaciones(new ArrayList<RelacionSd>());
				}
				personaSd.getRelaciones().add(relacion);
			}

		}

		// Empleo dependiente, no se carga porque no se tiene los datos de
		// persona juridica para el empleo dependiente en Smartdada.
		// FIXME
		// pn.setEmpleoDependienteList(crearLitaEmpleoDependiente());

		// FIXME: No se puede ingresar datos de empleo independiente porque no
		// hay daos de fecha de inscripcion del ruc ni fecha de inicioa de
		// actividades

		personaSd.setPersonaNatural(pn);
	}

	private CanalSd getCanal() {
		// Canal 2 significa Cliente Unico
		return new CanalSd(Short.valueOf("2"));
	}

	/**
	 * Transforma profesion a Smart Data
	 * 
	 * @return
	 */
	private ProfesionSd getProfesion() {
		// Se asume codigo 2327 "Sin Profesión" de acuerdo a indicaciones de
		// Viviana
		return new ProfesionSd(CodigoAsumidoEnum.PROFESION.getCodigoSd());
	}

	/**
	 * Obtiene pais de Smart Data.
	 * 
	 * @return
	 */
	private PaisSd getPais() {
		PaisSd resp = new PaisSd(personaNaturalCu.getPaisNacionalidad()
				.getCodPais());

		return resp;
	}

	/**
	 * 
	 * @return
	 */
	private EstadoCivilSd getEstadoCivil() {
		EstadoCivilSd resp = new EstadoCivilSd(personaNaturalCu
				.getEstadoCivil().getCodEstadoCivil());

		return resp;
	}

	/**
	 * 
	 * @return
	 */
	private TipoIdentificacionSd getTipoIdentificacion() {
		TipoIdentificacionSd resp = new TipoIdentificacionSd(
				getPersonaNaturalCu().getTipoIdentificacion()
						.getCodTipoIdentificacion());

		return resp;
	}

	/**
	 * @return the personaNaturalCu
	 */
	public PersonaNatural getPersonaNaturalCu() {
		return personaNaturalCu;
	}

	/**
	 * @param personaNaturalCu
	 *            the personaNaturalCu to set
	 */
	public void setPersonaNaturalCu(PersonaNatural personaNaturalCu) {
		this.personaNaturalCu = personaNaturalCu;
	}

	/**
	 * @return the personaSd
	 */
	public PersonaSd getPersonaSd() {
		return personaSd;
	}

	/**
	 * @param personaSd
	 *            the personaSd to set
	 */
	public void setPersonaSd(PersonaSd personaSd) {
		this.personaSd = personaSd;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
