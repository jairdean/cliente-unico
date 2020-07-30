/**
 * 
 */
package com.equivida.helper;

import java.util.Collection;

import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Relacion;
import com.equivida.modelo.Telefono;

/**
 * Clase que ayuda a llenar los campos para control de audoria en las entidades.
 * 
 * @author juan
 *
 */
public class AuditoriaHelper {
	private String usuario;
	private String terminal;
	private String programa;
	private String cuenta;

	public AuditoriaHelper() {
	}

	public AuditoriaHelper(String usuario, String terminal, String programa, String cuenta) {
		this.usuario = usuario;
		this.terminal = terminal;
		this.programa = programa;
		this.cuenta = cuenta;
	}

	/**
	 * Se llenan todos los campos de auditoria de las entidades relacionadas a
	 * persona natural.
	 * 
	 * @param personaNatural
	 */
	public void llenarAuditoriaContratantePN(PersonaNatural personaNatural) {
		Persona persona = personaNatural.getPersona();

		// 1. Se llena auditoria de la Persona Natural
		llenarCamposAuditoriaPersonaNatural(personaNatural);

		// 2. Se llena auditoria de la Persona
		llenarCamposAuditoriaPersona(persona);

		// 3. se llena auditoria de conyuge
		if (personaNatural.isConConyuge()) {
			llenarCamposAuditoriaPersonaNatural(personaNatural.getConyuge());

			llenarCamposAuditoriaPersona(personaNatural.getConyuge().getPersona());
		}

		// 4. Auditoria direccion electronica
		llenarCamposAuditoriaDireccionElectronica(persona.getEmailFacturacionElectronicaTra());
		llenarCamposAuditoriaDireccionElectronica(persona.getEmailPersonalTra());

		// 5. Auditoria de direcciones y direccion con telefono
		for (Direccion d : persona.getDireccionCollection()) {
			llenarCamposAuditoriaDireccion(d);

			for (DireccionTelefono dt : d.getDireccionTelefonoCollection()) {
				llenarCamposAuditoriaDireccionTelefono(dt);
			}
		}

		// 6. Se llenan campos de auditoria para telefono
		if (persona.getNoCelularSoloTra() != null && persona.getNoCelularSoloTra().getNroTelefono() != null
				&& persona.getNoCelularSoloTra().getNroTelefono().trim().length() > 0)
			llenarCamposAuditoriaTelefono(persona.getNoCelularSoloTra());
	}

	/**
	 * Se llenan todos los campos de auditoria de las entidades relacionadas a
	 * persona natural.
	 * 
	 * @param personaNatural
	 */
	public void llenarAuditoriaContratantePJ(PersonaJuridica personaJuridica) {
		Persona persona = personaJuridica.getPersona();

		// 1. Se llena auditoria de la Persona Natural
		llenarCamposAuditoriaPersonaJuridica(personaJuridica);

		// 2. Se llena auditoria de la Persona
		llenarCamposAuditoriaPersona(persona);

		// 3. Se llena auditoria de la Rep Legal
		llenarCamposAuditoriaPersona(personaJuridica.getRepresentante());

		llenarCamposAuditoriaPersonaNatural(personaJuridica.getRepresentante().getPersonaNaturalTransient());

		// 3.1. se llena auditoria de conyuge de RL
		if (personaJuridica.getRepresentante().getPersonaNaturalTransient().isConConyuge()) {
			llenarCamposAuditoriaPersonaNatural(
					personaJuridica.getRepresentante().getPersonaNaturalTransient().getConyuge());

			llenarCamposAuditoriaPersona(
					personaJuridica.getRepresentante().getPersonaNaturalTransient().getConyuge().getPersona());
		}

		// 4. Auditoria direccion electronica
		llenarCamposAuditoriaDireccionElectronica(persona.getEmailFacturacionElectronicaTra());
		//llenarCamposAuditoriaDireccionElectronica(persona.getEmailPersonalTra());

		// 5. Auditoria de direcciones y direccion con telefono
		for (Direccion d : persona.getDireccionCollection()) {
			llenarCamposAuditoriaDireccion(d);

			for (DireccionTelefono dt : d.getDireccionTelefonoCollection()) {
				llenarCamposAuditoriaDireccionTelefono(dt);
			}
		}

		// 6. Se llenan campos de auditoria para telefono
		if (persona.getNoCelularSoloTra() != null && persona.getNoCelularSoloTra().getNroTelefono() != null
				&& persona.getNoCelularSoloTra().getNroTelefono().trim().length() > 0)
			llenarCamposAuditoriaTelefono(persona.getNoCelularSoloTra());

		// representante legal
		Persona personaRL = personaJuridica.getRepresentante();

		// 5. Auditoria de direcciones y direccion con telefono
		for (Direccion d : personaRL.getDireccionCollection()) {
			llenarCamposAuditoriaDireccion(d);

			for (DireccionTelefono dt : d.getDireccionTelefonoCollection()) {
				llenarCamposAuditoriaDireccionTelefono(dt);
			}
		}

		// 6. Se llenan campos de auditoria para telefono
		if (personaRL.getNoCelularSoloTra() != null && personaRL.getNoCelularSoloTra().getNroTelefono() != null
				&& personaRL.getNoCelularSoloTra().getNroTelefono().trim().length() > 0)
			llenarCamposAuditoriaTelefono(personaRL.getNoCelularSoloTra());

		// 7. Campos de accionistas
		Collection<Relacion> accLista = personaJuridica.getAccionistaListTra();
		for (Relacion r : accLista) {
			llenarCamposAuditoriaPersona(r.getPersona());
			llenarCamposAuditoriaPersonaNatural(r.getPersona().getPersonaNaturalTransient());
		}

	}

	/**
	 * Se llena auditoria de Telefono.
	 * 
	 * @param t
	 */
	public void llenarCamposAuditoriaTelefono(Telefono t) {
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

	/**
	 * Se llena auditoria de una direccion.
	 * 
	 * @param d
	 */
	public void llenarCamposAuditoriaDireccion(Direccion d) {
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
	}

	/**
	 * Se llena auditoria de una direccion electrónica.
	 * 
	 * @param dt
	 */
	public void llenarCamposAuditoriaDireccionTelefono(DireccionTelefono dt) {
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

		// Auditoria del telefono relacionado
		if (dt.getTelefono().getSecTelefono() != null) {
			dt.getTelefono().setUsrModificacion(usuario);
			dt.getTelefono().setTtyModificacion(terminal);
			dt.getTelefono().setPrgModificacion(programa);
			dt.getTelefono().setCtaModificacion(cuenta);
		} else {
			dt.getTelefono().setUsrCreacion(usuario);
			dt.getTelefono().setTtyCreacion(terminal);
			dt.getTelefono().setPrgCreacion(programa);
			dt.getTelefono().setCtaCreacion(cuenta);

			dt.getTelefono().setUsrModificacion(usuario);
			dt.getTelefono().setTtyModificacion(terminal);
			dt.getTelefono().setPrgModificacion(programa);
			dt.getTelefono().setCtaModificacion(cuenta);
		}
	}

	/**
	 * Se llena auditoria de una direccion electrónica.
	 * 
	 * @param de
	 */
	public void llenarCamposAuditoriaDireccionElectronica(DireccionElectronica de) {
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

	/**
	 * Se llena campos auditoria para persona.
	 * 
	 * @param persona
	 */
	public void llenarCamposAuditoriaPersona(Persona persona) {
		if (persona != null) {
			if (persona.getSecPersona() != null) {
				persona.setUsrCreacion(usuario);
				persona.setTtyCreacion(terminal);
				persona.setPrgCreacion(programa);
				persona.setCtaCreacion(cuenta);
			} else {
				persona.setUsrCreacion(usuario);
				persona.setTtyCreacion(terminal);
				persona.setPrgCreacion(programa);
				persona.setCtaCreacion(cuenta);

				persona.setUsrModificacion(usuario);
				persona.setTtyModificacion(terminal);
				persona.setPrgModificacion(programa);
				persona.setCtaModificacion(cuenta);
			}
		}
	}

	/**
	 * Se llena auditoria de Persona Natural.
	 * 
	 * @param personaNatural
	 */
	public void llenarCamposAuditoriaPersonaNatural(PersonaNatural personaNatural) {
		if (personaNatural != null) {
			if (personaNatural.getSecPersonaNatural() != null) {
				personaNatural.setUsrCreacion(usuario);
				personaNatural.setTtyCreacion(terminal);
				personaNatural.setPrgCreacion(programa);
				personaNatural.setCtaCreacion(cuenta);
			} else {
				personaNatural.setUsrCreacion(usuario);
				personaNatural.setTtyCreacion(terminal);
				personaNatural.setPrgCreacion(programa);
				personaNatural.setCtaCreacion(cuenta);

				personaNatural.setUsrModificacion(usuario);
				personaNatural.setTtyModificacion(terminal);
				personaNatural.setPrgModificacion(programa);
				personaNatural.setCtaModificacion(cuenta);
			}
		}
	}

	/**
	 * Se llena auditoria de Persona Natural.
	 * 
	 * @param personaNatural
	 */
	public void llenarCamposAuditoriaPersonaJuridica(PersonaJuridica personaJuridica) {
		if (personaJuridica != null) {
			if (personaJuridica.getSecPersonaJuridica() != null) {
				personaJuridica.setUsrCreacion(usuario);
				personaJuridica.setTtyCreacion(terminal);
				personaJuridica.setPrgCreacion(programa);
				personaJuridica.setCtaCreacion(cuenta);
			} else {
				personaJuridica.setUsrCreacion(usuario);
				personaJuridica.setTtyCreacion(terminal);
				personaJuridica.setPrgCreacion(programa);
				personaJuridica.setCtaCreacion(cuenta);

				personaJuridica.setUsrModificacion(usuario);
				personaJuridica.setTtyModificacion(terminal);
				personaJuridica.setPrgModificacion(programa);
				personaJuridica.setCtaModificacion(cuenta);
			}
		}
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * @param terminal the terminal to set
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * @return the programa
	 */
	public String getPrograma() {
		return programa;
	}

	/**
	 * @param programa the programa to set
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

}
