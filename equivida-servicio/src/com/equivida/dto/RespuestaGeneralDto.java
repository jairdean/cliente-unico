package com.equivida.dto;

import java.io.Serializable;
import java.util.Collection;

import com.equivida.constant.LugarEncuentraInfoEnum;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.smartdata.model.PersonaSd;

/**
 * 
 * @author Juan Ochoa
 * @author Daniel Cardenas
 *
 */
public class RespuestaGeneralDto implements Serializable {

	private static final String ESTILO_NO_EXISTE = "info-no-existe";
	private static final String ESTILO_DIFERENTE = "info-diferente";

	private static final long serialVersionUID = -2509471455413736059L;
	private PersonaNatural personaNatural;
	private PersonaNatural personaNaturalPopUp;
	private PersonaJuridica personaJuridica;
	private PersonaSd personaSD;
	private LugarEncuentraInfoEnum lugarEncuentra;
	private String mensaje;
	private boolean nuevoRegistro;
	private boolean muestraPopUp;
	private boolean encuentraCu;
	private boolean encuentraSd;
	private boolean encuentraDb;
	private boolean hayMensaje;
	private String rucVerificar;// para ver si guarda o no en ruc_persona_natural

	public PersonaNatural getPersonaEncontradaUnica() {
		PersonaNatural resp = new PersonaNatural();

		if (encuentraCu && !encuentraSd) {
			resp = personaNatural;
		} else if (!encuentraCu && encuentraSd) {
			resp = personaNatural;
		} else if (encuentraCu && encuentraSd) {
			resp = personaNatural;
		} else if (!encuentraCu && !encuentraSd) {
			resp = personaNaturalPopUp;
		}

		return resp;
	}

	/**
	 * Aplica los estilos del objeto que se muestra en el pop-up
	 */
	public void aplicarEstilos() {

		if (this.personaNatural != null && this.personaNaturalPopUp != null) {
			// Estilo apellido paterno
			this.personaNaturalPopUp.setEstiloApellidoPaterno(obtenerEstilo(this.personaNatural.getApellidoPaterno(),
					this.personaNaturalPopUp.getApellidoPaterno()));

			// Estilo apellido materno
			this.personaNaturalPopUp.setEstiloApellidoMaterno(obtenerEstilo(this.personaNatural.getApellidoMaterno(),
					this.personaNaturalPopUp.getApellidoMaterno()));

			// Estilo primer nombre
			this.personaNaturalPopUp.setEstiloPrimerNombre(
					obtenerEstilo(this.personaNatural.getPrimerNombre(), this.personaNaturalPopUp.getPrimerNombre()));

			// Estilo seundo nombre
			this.personaNaturalPopUp.setEstiloSegundoNombre(
					obtenerEstilo(this.personaNatural.getSegundoNombre(), this.personaNaturalPopUp.getSegundoNombre()));

			// Estilo Sexo
			this.personaNaturalPopUp
					.setEstiloSexo(obtenerEstilo(this.personaNatural.getSexo(), this.personaNaturalPopUp.getSexo()));

			// Estilo Fecha Nacimiento
			this.personaNaturalPopUp.setEstiloFchNacimiento(
					obtenerEstilo(this.personaNatural.getFchNacimiento(), this.personaNaturalPopUp.getFchNacimiento()));

			// Estilo Pais
			this.personaNaturalPopUp.setEstiloPaisNacionalidad(obtenerEstilo(
					this.personaNatural.getPaisNacionalidad() != null
							? this.personaNatural.getPaisNacionalidad().getCodPais()
							: null,
					this.personaNaturalPopUp.getPaisNacionalidad() != null
							? this.personaNaturalPopUp.getPaisNacionalidad().getCodPais()
							: null));

			// Estilo Fecha fallecimiento
			this.personaNaturalPopUp.setEstiloFchFallecimiento(obtenerEstilo(this.personaNatural.getFchFallecimiento(),
					this.personaNaturalPopUp.getFchFallecimiento()));

			// Estilo estado civil
			this.personaNaturalPopUp.setEstiloEstadoCivil(obtenerEstilo(
					this.personaNatural.getEstadoCivil() != null
							? this.personaNatural.getEstadoCivil().getCodEstadoCivil()
							: null,
					this.personaNaturalPopUp.getEstadoCivil() != null
							? this.personaNaturalPopUp.getEstadoCivil().getCodEstadoCivil()
							: null));
			// TODO: revisar que al traer el conyuge de la persona natural se
			// cargue en el trasient conyuge
			// Estilo conyuge
			this.personaNaturalPopUp.setEstiloConyuge(obtenerEstilo(
					this.personaNatural.getConyuge() != null ? this.personaNatural.getConyuge().getIdentificacion()
							: null,
					this.personaNaturalPopUp.getConyuge() != null
							? this.personaNaturalPopUp.getConyuge().getIdentificacion()
							: null));
		}

		/*
		 * TODO: se comenta porque no se especifica que se pinte la direccion
		 * 
		 * if (this.personaNatural.getPersona() != null &&
		 * this.personaNaturalPopUp.getPersona() != null) { // Estilo de las direcciones
		 * if (this.personaNatural.getPersona().getDireccionCollection() != null &&
		 * !this.personaNatural.getPersona() .getDireccionCollection().isEmpty()) { if
		 * (this.personaNaturalPopUp.getPersona() .getDireccionCollection() != null &&
		 * !this.personaNaturalPopUp.getPersona() .getDireccionCollection().isEmpty()) {
		 * for (Direccion d : this.personaNaturalPopUp.getPersona()
		 * .getDireccionCollection()) { verificaDireccion(d,
		 * this.personaNatural.getPersona() .getDireccionCollection()); } } } else { //
		 * Se pone todas las direcciones como nuevas if
		 * (this.personaNaturalPopUp.getPersona() .getDireccionCollection() != null &&
		 * !this.personaNaturalPopUp.getPersona() .getDireccionCollection().isEmpty()) {
		 * for (Direccion d : this.personaNaturalPopUp.getPersona()
		 * .getDireccionCollection()) { d.setEstiloDireccion(ESTILO_NO_EXISTE); } } } }
		 */

		// TODO: no se dice nada de la empleabilidad para mostrar las
		// diferencias.
	}

	/**
	 * 
	 * @param d
	 * @param direccionCollection
	 */
	@Deprecated
	private void verificaDireccion(Direccion d, Collection<Direccion> direccionCollection) {
		for (Direccion i : direccionCollection) {
		}
	}

	/**
	 * Compara los objetos enviados y devuelve el estilo.
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	private String obtenerEstilo(Object o1, Object o2) {
		String estilo = "";

		if (o1 != null && o2 != null) {
			if (!o1.equals(o2)) {
				estilo = ESTILO_DIFERENTE;
			}
		} else {
			if (o1 == null && o2 != null) {
				estilo = ESTILO_NO_EXISTE;
			}
		}

		return estilo;
	}

	/**
	 * @return the personaNatural
	 */
	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	/**
	 * @param personaNatural
	 *            the personaNatural to set
	 */
	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	/**
	 * @return the lugarEncuentra
	 */
	public LugarEncuentraInfoEnum getLugarEncuentra() {
		return lugarEncuentra;
	}

	/**
	 * @param lugarEncuentra
	 *            the lugarEncuentra to set
	 */
	public void setLugarEncuentra(LugarEncuentraInfoEnum lugarEncuentra) {
		this.lugarEncuentra = lugarEncuentra;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the nuevoRegistro
	 */
	public boolean isNuevoRegistro() {
		return nuevoRegistro;
	}

	/**
	 * @param nuevoRegistro
	 *            the nuevoRegistro to set
	 */
	public void setNuevoRegistro(boolean nuevoRegistro) {
		this.nuevoRegistro = nuevoRegistro;
	}

	/**
	 * @return the muestraPopUp
	 */
	public boolean isMuestraPopUp() {
		return muestraPopUp;
	}

	/**
	 * @param muestraPopUp
	 *            the muestraPopUp to set
	 */
	public void setMuestraPopUp(boolean muestraPopUp) {
		this.muestraPopUp = muestraPopUp;
	}

	/**
	 * @return the personaNaturalPopUp
	 */
	public PersonaNatural getPersonaNaturalPopUp() {
		return personaNaturalPopUp;
	}

	/**
	 * @param personaNaturalPopUp
	 *            the personaNaturalPopUp to set
	 */
	public void setPersonaNaturalPopUp(PersonaNatural personaNaturalPopUp) {
		this.personaNaturalPopUp = personaNaturalPopUp;
	}

	/**
	 * @return the personaSD
	 */
	public PersonaSd getPersonaSD() {
		return personaSD;
	}

	/**
	 * @param personaSD
	 *            the personaSD to set
	 */
	public void setPersonaSD(PersonaSd personaSD) {
		this.personaSD = personaSD;
	}

	/**
	 * @return the encuentraCu
	 */
	public boolean isEncuentraCu() {
		return encuentraCu;
	}

	/**
	 * @param encuentraCu
	 *            the encuentraCu to set
	 */
	public void setEncuentraCu(boolean encuentraCu) {
		this.encuentraCu = encuentraCu;
	}

	/**
	 * @return the encuentraSd
	 */
	public boolean isEncuentraSd() {
		return encuentraSd;
	}

	/**
	 * @param encuentraSd
	 *            the encuentraSd to set
	 */
	public void setEncuentraSd(boolean encuentraSd) {
		this.encuentraSd = encuentraSd;
	}

	/**
	 * @return the encuentraDb
	 */
	public boolean isEncuentraDb() {
		return encuentraDb;
	}

	/**
	 * @param encuentraDb
	 *            the encuentraDb to set
	 */
	public void setEncuentraDb(boolean encuentraDb) {
		this.encuentraDb = encuentraDb;
	}

	/**
	 * @return the hayMensaje
	 */
	public boolean isHayMensaje() {
		return hayMensaje;
	}

	/**
	 * @param hayMensaje
	 *            the hayMensaje to set
	 */
	public void setHayMensaje(boolean hayMensaje) {
		this.hayMensaje = hayMensaje;
	}

	/**
	 * @return the personaJuridica
	 */
	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	/**
	 * @param personaJuridica
	 *            the personaJuridica to set
	 */
	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	@Override
	public String toString() {
		return "RespuestaGeneralDto [personaNatural=" + personaNatural + ", personaNaturalPopUp=" + personaNaturalPopUp
				+ ", personaJuridica=" + personaJuridica + ", personaSD=" + personaSD + ", lugarEncuentra="
				+ lugarEncuentra + ", mensaje=" + mensaje + ", nuevoRegistro=" + nuevoRegistro + ", muestraPopUp="
				+ muestraPopUp + ", encuentraCu=" + encuentraCu + ", encuentraSd=" + encuentraSd + ", encuentraDb="
				+ encuentraDb + ", hayMensaje=" + hayMensaje + "]";
	}

	public String getRucVerificar() {
		return rucVerificar;
	}

	public void setRucVerificar(String rucVerificar) {
		this.rucVerificar = rucVerificar;
	}

}
