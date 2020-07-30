package com.equivida.crm.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.crm.dto.RespuestaActualizacionDto;
import com.equivida.exception.NoEncuentraDatosException;
import com.equivida.modelo.Canton;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Persona;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoDireccion;
import com.equivida.modelo.TipoDireccionElectronica;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.CantonServicio;
import com.equivida.servicio.PaisServicio;
import com.equivida.servicio.WsDatosPersonaServicio;

public class ActualizacionPersonaUtil {

	public static boolean analizarMail1(String mail1,
			WsDatosPersonaServicio wsDatosPersonaServicio, StringBuilder log,
			int registro, RespuestaActualizacionDto res, Persona pp) {

		if (mail1 != null && !mail1.trim().equals("")) {

			// if (wsDatosPersonaServicio.validarEmail(mail1)) {
			// log.append("En el registro " + registro
			// + " el campo mail1 no es valido, ");
			// res.anadirCantidadRegistrosRechazados();
			// return false;
			// }

			Collection<DireccionElectronica> deListaBDD = pp
					.getDireccionElectronicaFormularioCollection();
			boolean encontradoMail1 = false;

			for (DireccionElectronica de : deListaBDD) {

				if (de.getTipoDireccionElectronica().isPersonal()) {
					if (de.getActivo()) {
						encontradoMail1 = true;
						de.setDireccionElectronica(mail1);
					}
				}
			}

			// si no encontro ingresa mail1
			if (!encontradoMail1) {
				DireccionElectronica deNuevo = new DireccionElectronica();
				deNuevo.setDireccionElectronica(mail1);
				deNuevo.setEstado(EstadoEnum.ACTIVO.getCodigo());
				deNuevo.setPersona(pp);
				deNuevo.setTipoDireccionElectronica(new TipoDireccionElectronica(
						Constantes.TIPO_DIRECCION_ELECTRONICA_ID_PERSONAL));
				pp.getDireccionElectronicaFormularioCollection().add(deNuevo);
			}

		}// fin mail1

		return true;

	}

	public static boolean analizarMail2(String mail2,
			WsDatosPersonaServicio wsDatosPersonaServicio, StringBuilder log,
			int registro, RespuestaActualizacionDto res, Persona pp) {

		if (mail2 != null && !mail2.trim().equals("")) {

			// if (wsDatosPersonaServicio.validarEmail(mail2)) {
			// log.append("En el registro " + registro
			// + " el campo mail2 no es valido, ");
			// res.anadirCantidadRegistrosRechazados();
			// return false;
			// }

			Collection<DireccionElectronica> deListaBDD = pp
					.getDireccionElectronicaFormularioCollection();
			boolean encontradoMail2 = false;

			for (DireccionElectronica de : deListaBDD) {

				if (de.getTipoDireccionElectronica().isTrabajo()) {
					if (de.getActivo()) {
						encontradoMail2 = true;
						de.setDireccionElectronica(mail2);
					}
				}
			}

			// si no encontro ingresa mail2
			if (!encontradoMail2) {
				DireccionElectronica deNuevo = new DireccionElectronica();
				deNuevo.setDireccionElectronica(mail2);
				deNuevo.setEstado(EstadoEnum.ACTIVO.getCodigo());
				deNuevo.setPersona(pp);
				deNuevo.setTipoDireccionElectronica(new TipoDireccionElectronica(
						Constantes.TIPO_DIRECCION_ELECTRONICA_ID_TRABAJO));
				pp.getDireccionElectronicaFormularioCollection().add(deNuevo);
			}

		}// fin mail2

		return true;

	}

	public static boolean analizarDireccion(String tipoDireccion,
			CantonServicio cantonServicio, PaisServicio paisServicio,
			String codCanton, String codTelefonoPais, String ciudad,
			String direccionCalle1, String direccionCalle2, String codArea,
			String barrio, String direccionEdificio, String direccionNumero,
			String referencia, String tipoTelefono, String telefono,
			String telefonoExtension, StringBuilder log, int registro,
			String secuencial, RespuestaActualizacionDto res, Persona pp) {

		if (tipoDireccion != null && !tipoDireccion.trim().equals("")) {

			Canton c = null;

			try {

				c = cantonServicio.findByPk(Short.parseShort(codCanton));

			} catch (NumberFormatException e) {
				log.append("En el registro " + registro + " el SECPER "
						+ secuencial
						+ " tiene un identificador de canton incorrecto , ");
				res.anadirCantidadRegistrosRechazados();
				return false;
			}

			if (c == null) {
				log.append("En el registro " + registro + " el SECPER "
						+ secuencial
						+ " tiene un identificador de canton incorrecto , ");
				res.anadirCantidadRegistrosRechazados();
				return false;
			}

			Pais pais = null;

			try {

				pais = paisServicio.findByPk(Short.parseShort(codTelefonoPais));

				if (pais == null) {
					log.append("En el registro "
							+ registro
							+ " el SECPER "
							+ secuencial
							+ " tiene un identificador de pais (telefono) incorrecto , ");
					res.anadirCantidadRegistrosRechazados();
					return false;
				}

			} catch (NumberFormatException e) {

				log.append("En el registro "
						+ registro
						+ " el SECPER "
						+ secuencial
						+ " tiene un identificador de pais (telefono) incorrecto , ");
				res.anadirCantidadRegistrosRechazados();
				return false;
			}

			if (ciudad == null || ciudad.trim().equals("")) {
				log.append("En el registro " + registro + " el SECPER "
						+ secuencial + " debe ingresar ciudad , ");
				res.anadirCantidadRegistrosRechazados();
				return false;
			}

			if (direccionCalle1 == null || direccionCalle1.trim().equals("")) {
				log.append("En el registro " + registro + " el SECPER "
						+ secuencial
						+ " debe ingresar calle 1 (calle principal), ");
				res.anadirCantidadRegistrosRechazados();
				return false;
			}

			if (codArea == null || codArea.trim().equals("")) {
				log.append("En el registro " + registro + " el SECPER "
						+ secuencial
						+ " debe ingresar codigo de area (para telefono), ");
				res.anadirCantidadRegistrosRechazados();
				return false;
			}

			if (tipoTelefono == null || tipoTelefono.trim().equals("")) {
				log.append("En el registro "
						+ registro
						+ " el SECPER "
						+ secuencial
						+ " debe ingresar tipo de telefono (domicilio, oficina, celular), ");
				res.anadirCantidadRegistrosRechazados();
				return false;
			}

			if (telefono == null || telefono.trim().equals("")) {
				log.append("En el registro " + registro + " el SECPER "
						+ secuencial + " debe ingresar numero de telefono, ");
				res.anadirCantidadRegistrosRechazados();
				return false;
			}

			Collection<Direccion> direccionLista = pp.getDireccionCollection();

			if (tipoDireccion.toLowerCase().equals("domicilio")) {

				try {
					analizarDireccionDomicilio(ciudad, direccionCalle1,
							direccionCalle2, codArea, barrio,
							direccionEdificio, direccionNumero, referencia,
							tipoTelefono, telefono, telefonoExtension, pp, c,
							pais, direccionLista);
				} catch (NoEncuentraDatosException e) {
					log.append("En el registro " + registro + " el SECPER "
							+ secuencial + " " + e.getMessage() + " , ");
					res.anadirCantidadRegistrosRechazados();
					return false;
				}

			} else if (tipoDireccion.toLowerCase().equals("trabajo")) {

				try {
					analizarDireccionOficina(ciudad, direccionCalle1,
							direccionCalle2, codArea, barrio,
							direccionEdificio, direccionNumero, referencia,
							tipoTelefono, telefono, telefonoExtension, pp, c,
							pais, direccionLista);
				} catch (NoEncuentraDatosException e) {
					log.append("En el registro " + registro + " el SECPER "
							+ secuencial + " " + e.getMessage() + " , ");
					res.anadirCantidadRegistrosRechazados();
					return false;
				}

			} else {
				log.append("En el registro "
						+ registro
						+ " el SECPER "
						+ secuencial
						+ " tiene un tipo de direccion incorrecto (solo se permite Domicilio y Trabajo), ");
				res.anadirCantidadRegistrosRechazados();
				return false;
			}

		} else {
			log.append("En el registro " + registro + " el SECPER "
					+ secuencial + " no tiene tipo de direccion, ");
			res.anadirCantidadRegistrosRechazados();
			return false;
		}

		return true;

	}

	private static void analizarDireccionDomicilio(String ciudad,
			String direccionCalle1, String direccionCalle2, String codArea,
			String barrio, String direccionEdificio, String direccionNumero,
			String referencia, String tipoTelefono, String telefono,
			String telefonoExtension, Persona pp, Canton c, Pais pais,
			Collection<Direccion> direccionLista)
			throws NoEncuentraDatosException {
		boolean encontroDomicilio = false;

		if (direccionLista == null) {
			direccionLista = new ArrayList<Direccion>();
			pp.setDireccionCollection(direccionLista);
		}

		for (Direccion d : direccionLista) {
			if (d.getTipoDireccion().isDomicilio()) {

				// reemplaza si es activo
				if (d.getActivo()) {

					encontroDomicilio = true;

					d.setBarrio(barrio);

					d.setCanton(c);
					d.setCiudad(ciudad);
					d.setEdificio(direccionEdificio);
					d.setLatitud(BigDecimal.ZERO);
					d.setLongitud(BigDecimal.ZERO);
					d.setNumero(direccionNumero);
					// d.setPersona(pp);
					d.setReferencia(referencia);
					d.setPrincipal(direccionCalle1);
					d.setSecundaria(direccionCalle2);
					d.setVerificada(RespuestaEnum.NO.getCodigo());

					// telefono por
					// direccion
					Collection<DireccionTelefono> dtLista = d
							.getDireccionTelefonoCollection();

					if (tipoTelefono != null && !tipoTelefono.trim().equals("")) {

						// telefono domicilio
						analizarTelefonoDomicilio(codArea, telefono,
								telefonoExtension, pp, pais, d, dtLista,
								tipoTelefono);

						// telefono oficina
						// analizarTelefonoOficina(codArea, telefono,
						// telefonoExtension, pp, pais, d, dtLista,
						// tipoTelefono);
					}

				}// fin activo

			}
		}

		System.out.println("direccion encontroDomicilio:" + encontroDomicilio);
		System.out.println("tipoTelefono.toLowerCase():"
				+ tipoTelefono.toLowerCase());

		if (!encontroDomicilio) {
			Direccion d = new Direccion();
			d.setBarrio(barrio);
			d.setCanton(c);
			d.setCiudad(ciudad);
			d.setEdificio(direccionEdificio);
			d.setEnvioCorrespondencia(RespuestaEnum.NO.getCodigo());
			d.setEstado(EstadoEnum.ACTIVO.getCodigo());
			d.setLatitud(BigDecimal.ZERO);
			d.setLongitud(BigDecimal.ZERO);
			d.setNumero(direccionNumero);
			d.setPersona(pp);
			d.setReferencia(referencia);
			d.setPrincipal(direccionCalle1);
			d.setSecundaria(direccionCalle2);
			TipoDireccion td = new TipoDireccion(
					Constantes.TIPO_DIRECCION_ID_DOMICILIO);
			d.setTipoDireccion(td);
			d.setVerificada(RespuestaEnum.NO.getCodigo());
			// telefono por
			// direccion
			Collection<DireccionTelefono> dtLista = d
					.getDireccionTelefonoCollection();

			if (tipoTelefono != null && !tipoTelefono.trim().equals("")) {

				// telefono domicilio
				if (tipoTelefono.toLowerCase().equals("domicilio")) {
					analizarTelefonoDomicilio(codArea, telefono,
							telefonoExtension, pp, pais, d, dtLista,
							tipoTelefono);

				}
				// telefono oficina
				// analizarTelefonoOficina(codArea, telefono,
				// telefonoExtension, pp, pais, d, dtLista,
				// tipoTelefono);
			}

			pp.getDireccionCollection().add(d);
		}// fin no encontro domicilio

		// verifica si es oficina
		if (tipoTelefono.toLowerCase().equals("oficina")) {
			boolean encontroDirOficina = false;
			for (Direccion dirOficina : direccionLista) {
				if (dirOficina.getActivo()
						&& dirOficina.getTipoDireccion().isTrabajo()) {
					encontroDirOficina = true;
					analizarTelefonoOficina(codArea, telefono,
							telefonoExtension, pp, pais, dirOficina,
							dirOficina.getDireccionTelefonoCollection(),
							tipoTelefono);
					System.out
							.println("encontro direccion Trabajo para telefono oficina ...");
					break;
				}
			}
			System.out.println("encontro dir de trabajo:" + encontroDirOficina);
			if (!encontroDirOficina) {
				throw new NoEncuentraDatosException(
						"No existe direcci\u00F3n trabajo para telf. de oficina");
			}
		}
	}

	private static void analizarDireccionOficina(String ciudad,
			String direccionCalle1, String direccionCalle2, String codArea,
			String barrio, String direccionEdificio, String direccionNumero,
			String referencia, String tipoTelefono, String telefono,
			String telefonoExtension, Persona pp, Canton c, Pais pais,
			Collection<Direccion> direccionLista)
			throws NoEncuentraDatosException {
		boolean encontroTrabajo = false;

		if (direccionLista == null) {
			direccionLista = new ArrayList<Direccion>();
			pp.setDireccionCollection(direccionLista);
		}

		for (Direccion d : direccionLista) {
			if (d.getTipoDireccion().isTrabajo()) {

				// reemplaza si es activo
				if (d.getActivo()) {

					encontroTrabajo = true;

					d.setBarrio(barrio);

					d.setCanton(c);
					d.setCiudad(ciudad);
					d.setEdificio(direccionEdificio);
					d.setLatitud(BigDecimal.ZERO);
					d.setLongitud(BigDecimal.ZERO);
					d.setNumero(direccionNumero);
					// d.setPersona(pp);
					d.setReferencia(referencia);
					d.setPrincipal(direccionCalle1);
					d.setSecundaria(direccionCalle2);
					d.setVerificada(RespuestaEnum.NO.getCodigo());

					// telefono por
					// direccion
					Collection<DireccionTelefono> dtLista = d
							.getDireccionTelefonoCollection();

					if (tipoTelefono != null && !tipoTelefono.trim().equals("")) {

						// telefono domicilio
						// analizarTelefonoDomicilio(codArea, telefono,
						// telefonoExtension, pp, pais, d, dtLista,
						// tipoTelefono);

						// telefono oficina
						analizarTelefonoOficina(codArea, telefono,
								telefonoExtension, pp, pais, d, dtLista,
								tipoTelefono);
					}

				}// fin activo

			}
		}

		System.out.println("direccion encontroTrabajo:" + encontroTrabajo);
		System.out.println("tipoTelefono.toLowerCase():"
				+ tipoTelefono.toLowerCase());

		if (!encontroTrabajo) {

			Direccion d = new Direccion();
			d.setBarrio(barrio);
			d.setCanton(c);
			d.setCiudad(ciudad);
			d.setEdificio(direccionEdificio);
			d.setEnvioCorrespondencia(RespuestaEnum.NO.getCodigo());
			d.setEstado(EstadoEnum.ACTIVO.getCodigo());
			d.setLatitud(BigDecimal.ZERO);
			d.setLongitud(BigDecimal.ZERO);
			d.setNumero(direccionNumero);
			d.setPersona(pp);
			d.setReferencia(referencia);
			d.setPrincipal(direccionCalle1);
			d.setSecundaria(direccionCalle2);
			TipoDireccion td = new TipoDireccion(
					Constantes.TIPO_DIRECCION_ID_TRABAJO);
			d.setTipoDireccion(td);
			d.setVerificada(RespuestaEnum.NO.getCodigo());
			// telefono por
			// direccion
			Collection<DireccionTelefono> dtLista = d
					.getDireccionTelefonoCollection();

			if (tipoTelefono != null && !tipoTelefono.trim().equals("")) {

				// telefono domicilio
				// analizarTelefonoDomicilio(codArea, telefono,
				// telefonoExtension, pp, pais, d, dtLista,
				// tipoTelefono);

				// telefono oficina
				if (tipoTelefono.toLowerCase().equals("oficina")) {
					analizarTelefonoOficina(codArea, telefono,
							telefonoExtension, pp, pais, d, dtLista,
							tipoTelefono);
				}
			}

			pp.getDireccionCollection().add(d);
		}// fin no encontro oficina

		// verifica si es domicilio
		if (tipoTelefono.toLowerCase().equals("domicilio")) {
			boolean encontroDirDomi = false;
			for (Direccion dirDomi : direccionLista) {
				if (dirDomi.getActivo()
						&& dirDomi.getTipoDireccion().isDomicilio()) {
					System.out
							.println("encontro direccion domicilio para telefono domicilio ...");
					encontroDirDomi = true;
					analizarTelefonoDomicilio(codArea, telefono,
							telefonoExtension, pp, pais, dirDomi,
							dirDomi.getDireccionTelefonoCollection(),
							tipoTelefono);
					break;
				}
			}
			System.out.println("encontro dir de domicilio:" + encontroDirDomi);
			if (!encontroDirDomi) {
				throw new NoEncuentraDatosException(
						"No existe direcci\u00F3n trabajo para telf. de oficina");
			}
		}
	}

	private static void analizarTelefonoOficina(String codArea,
			String telefono, String telefonoExtension, Persona pp, Pais pais,
			Direccion d, Collection<DireccionTelefono> dtLista,
			String tipoTelefono) {
		boolean encontroTelefonoOficina = false;

		if (dtLista == null) {
			dtLista = new ArrayList<DireccionTelefono>();
			d.setDireccionTelefonoCollection(dtLista);
		}

		for (DireccionTelefono dt : dtLista) {

			if (dt.getTelefono().getTipoTelefono().isOficina()
					&& tipoTelefono.toLowerCase().equals("oficina")) {
				if (dt.getTelefono().getActivo()) {
					encontroTelefonoOficina = true;
					dt.getTelefono().setCodArea(codArea);
					dt.getTelefono().setExtTelefono(telefonoExtension);
					dt.getTelefono().setNroTelefono(telefono);
					dt.getTelefono().setPais(pais);
				}
			}
		}

		// COMENTADO 11-feb-2015
		if (!encontroTelefonoOficina
				&& tipoTelefono.toLowerCase().equals("oficina")) {
			System.out.println("No encontro oficina, entonces crea");
			DireccionTelefono dt = new DireccionTelefono();
			dt.setDireccion(d);
			dt.setPersona(pp);
			Telefono t = new Telefono();
			t.setPrincipal(false);
			t.setCodArea(codArea);
			t.setPais(pais);
			t.setEstado(EstadoEnum.ACTIVO.getCodigo());
			t.setExtTelefono(telefonoExtension);
			t.setNroTelefono(telefono);
			t.setTipoTelefono(new TipoTelefono(Constantes.TIPO_TELEFONO_OFICINA));
			t.setPersona(pp);
			dt.setTelefono(t);
			d.getDireccionTelefonoCollection().add(dt);
		}
	}

	private static void analizarTelefonoDomicilio(String codArea,
			String telefono, String telefonoExtension, Persona pp, Pais pais,
			Direccion d, Collection<DireccionTelefono> dtLista,
			String tipoTelefono) {
		boolean encontroTelefonoDomicilio = false;

		if (dtLista == null) {
			dtLista = new ArrayList<DireccionTelefono>();
			d.setDireccionTelefonoCollection(dtLista);
		}

		for (DireccionTelefono dt : dtLista) {

			if (dt.getTelefono().getTipoTelefono().isDomicilio()
					&& tipoTelefono.toLowerCase().equals("domicilio")) {
				if (dt.getTelefono().getActivo()) {
					System.out.println("cambia telefono:" + dt.getTelefono());
					encontroTelefonoDomicilio = true;
					dt.getTelefono().setCodArea(codArea);
					dt.getTelefono().setExtTelefono(telefonoExtension);
					dt.getTelefono().setNroTelefono(telefono);
					dt.getTelefono().setPais(pais);
					System.out.println("fin cambia telefono:"
							+ dt.getTelefono());
				}
			}

		}

		// COMENTADO 11-feb-2015
		if (!encontroTelefonoDomicilio
				&& tipoTelefono.toLowerCase().equals("domicilio")) {
			System.out.println("No encontro domicilio, entonces crea");
			DireccionTelefono dt = new DireccionTelefono();
			dt.setDireccion(d);
			dt.setPersona(pp);
			Telefono t = new Telefono();
			t.setPrincipal(false);
			t.setCodArea(codArea);
			t.setPais(pais);
			t.setEstado(EstadoEnum.ACTIVO.getCodigo());
			t.setExtTelefono(telefonoExtension);
			t.setNroTelefono(telefono);
			t.setTipoTelefono(new TipoTelefono(
					Constantes.TIPO_TELEFONO_DOMICILIO));
			t.setPersona(pp);
			dt.setTelefono(t);
			d.getDireccionTelefonoCollection().add(dt);
		}
		// FIN COMENTADO 11-feb-2015
	}

}
