package com.equivida.intranet.util;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.xml.rpc.ServiceException;

import com.equivida.constant.Constantes;
import com.equivida.constant.EsClienteEnum;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.modelo.BeneficiarioPoliza;
import com.equivida.modelo.Canton;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.ContactoPreferido;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.Hijo;
import com.equivida.modelo.Ocupacion;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Parroquia;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Provincia;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoDireccion;
import com.equivida.modelo.TipoDireccionElectronica;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoMotivoSeguro;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.CantonServicio;
import com.equivida.servicio.CiudadServicio;
import com.equivida.servicio.PaisServicio;
import com.equivida.servicio.ParroquiaServicio;
import com.equivida.servicio.ProvinciaServicio;
import com.equivida.servicio.TipoMotivoSeguroServicio;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.equivida.util.PersonaUtil;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.util.Criteria;

public class BasePersonaCtrl extends BaseCtrl {

	private static final long serialVersionUID = -7569995446868286363L;

	@EJB(mappedName = "PaisServicio/local")
	protected PaisServicio paisServicio;

	@EJB(mappedName = "ProvinciaServicio/local")
	protected ProvinciaServicio provinciaServicio;

	@EJB(mappedName = "CiudadServicio/local")
	protected CiudadServicio ciudadServicio;

	@EJB(mappedName = "CantonServicio/local")
	protected CantonServicio cantonServicio;

	@EJB(mappedName = "ParroquiaServicio/local")
	protected ParroquiaServicio parroquiaServicio;

	@EJB(mappedName = "TipoMotivoSeguroServicio/local")
	protected TipoMotivoSeguroServicio tipoMotivoSeguroServicio;

	@EJB(mappedName = "WsDatosPersonaServicio/local")
	protected WsDatosPersonaServicio wsDatosPersonaServicio;

	// protected Persona persona;// para reusar codigo de persona (direcciones
	// telefonos etc...)

	protected PersonaNatural personaNatural;
	protected PersonaNatural personaNaturalPopUp;

	protected boolean muestraPopUp;

	protected Provincia provinciaNoDisponible;

	protected Canton cantonNoDisponible;

	protected Parroquia parroquiaNoDisponible;

	protected Map<String, Ciudad> ciudadesMap;

	public void cambiarOcupacionHijo(ValueChangeEvent event) {

		// Hijo h = (Hijo) getExternalContext().getRequestMap().get("h");

		String ejemplo = (String) event.getNewValue();

		System.out.println("----" + ejemplo);

	}

	public void verificarTelefonoDireccion(AjaxBehaviorEvent event) {

		System.out.println("borrar:" + event.getComponent().getClientId());

		DireccionTelefono direccionTelefono = (DireccionTelefono) getExternalContext().getRequestMap().get("dt");

		System.out.println("valida direccion telefono:" + direccionTelefono.getTelefono().getPais().getCodPais());

		if (direccionTelefono.getTelefono().getPais().getCodPais().equals(Constantes.PAIS_ID_ECUADOR)) {
			String nroTelefono = direccionTelefono.getTelefono().getCodArea()
					+ direccionTelefono.getTelefono().getNroTelefono();

			System.out.println("telefono:" + nroTelefono);

			boolean valida = false;
			try {
				valida = wsDatosPersonaServicio.validarTelefono(nroTelefono);
				if (!valida) {
					System.out.println("si es false ");
					String mensaje = getBundleMensajes("numero.telefono.incorrecto", null);
					System.out.println("****id***" + event.getComponent().getId());
					System.out.println("****client id***" + event.getComponent().getClientId());
					addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
					return;

				}

			} catch (RemoteException e) {
				e.printStackTrace();
				String mensaje = getBundleMensajes("error.web.service.telefono", null) + " " + e.getMessage();
				addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
				System.out.println(e);
			} catch (ServiceException e) {
				e.printStackTrace();
				String mensaje = getBundleMensajes("error.web.service.telefono", null) + " " + e.getMessage();
				addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
				System.out.println(e);
			}

		}
	}

	public void iniciarConyuge(PersonaNatural personaNaturalLocal) {
		// conyuge
		PersonaNatural conyuge = new PersonaNatural();
		conyuge.setPersona(new Persona());
		conyuge.setTipoIdentificacion(new TipoIdentificacion(TipoIdentificacionEnum.CEDULA.getCodigo()));

		personaNaturalLocal.setConyuge(conyuge);
	}

	public void crearHijo(ActionEvent event) {

		if (personaNatural.getNumHijos() > 0) {

			// se agrega hijos
			Hijo hijo = new Hijo();
			hijo.setOcupacion(new Ocupacion());
			hijo.setPersonaNatural(personaNatural);

			if (personaNatural.getPersona().getHijos() == null) {
				personaNatural.getPersona().setHijos(new ArrayList<Hijo>());
			}

			if (personaNatural.getPersona().getHijos().size() < personaNatural.getNumHijos()) {
				// no puede agregar mas hijos que los indicados en numero hijos
				personaNatural.getPersona().getHijos().add(hijo);
			} else {

				String mensaje = getBundleMensajes("numero.hijos.error", null);

				addErrorMessage(null, mensaje, mensaje);

			}

		} else {

			String mensaje = getBundleMensajes("no.cero.hijos", null);

			addErrorMessage(null, mensaje, mensaje);

		}

	}

	public void eliminarHijo(ActionEvent event) {
		Hijo h = (Hijo) getExternalContext().getRequestMap().get("h");

		if (h.getSecHijo() != null) {

			if (personaNatural.getPersona().getHijosEliminar() == null) {
				personaNatural.getPersona().setHijosEliminar(new ArrayList<Hijo>());
			}

			personaNatural.getPersona().getHijosEliminar().add(h);

		}

		personaNatural.getPersona().getHijos().remove(h);
	}

	protected boolean tieneProvincias(Short codPais) {
		boolean tiene = false;
		String[] criteriasAnd = { "pais.codPais" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.SHORT_EQUALS };
		Object[] params = new Object[] { codPais };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		Long total = provinciaServicio.totalFindByCriterias(criteria);
		System.out.println("total:" + total);
		if (total > 0) {
			tiene = true;
		}

		System.out.println("tiene" + tiene);

		return tiene;
	}

	protected List<Provincia> buscarProvincias(String pref, Short codPais) {
		List<Provincia> resp = new ArrayList<Provincia>();

		int minChars = 1;

		if (pref.length() >= minChars) {
			String[] criteriasAnd = { "provincia", "pais.codPais" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_STARTS_WITH, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, codPais };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = provinciaServicio.findByCriterias(criteria);
		}

		return resp;
	}

	protected List<Ciudad> buscarCiudades(String pref, Short codPais) {
		List<Ciudad> resp = new ArrayList<Ciudad>();

		int minChars = 1;

		if (pref.length() >= minChars) {
			String[] criteriasAnd = { "ciudad", "pais.codPais" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_STARTS_WITH, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, codPais };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = ciudadServicio.findByCriterias(criteria);
		}

		ciudadesMap = new HashMap<String, Ciudad>();
		for (Ciudad c : resp) {
			ciudadesMap.put(c.getCiudad(), c);
		}

		return resp;
	}

	protected List<Canton> buscarCantones(String pref, Short secProvincia) {
		List<Canton> resp = new ArrayList<Canton>();

		int minChars = 1;

		if (pref.length() >= minChars) {
			String[] criteriasAnd = { "canton", "provincia.secProvincia" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, secProvincia };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = cantonServicio.findByCriterias(criteria);
		}
		return resp;
	}

	protected List<Parroquia> buscarParroquias(String pref, Short secCanton) {
		List<Parroquia> resp = new ArrayList<Parroquia>();

		int minChars = 1;

		System.out.println("obteniendo parroquias canton:" + secCanton);

		if (pref.length() >= minChars) {
			String[] criteriasAnd = { "parroquia", "canton.secCanton" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_LIKE, CriteriaTypeEnum.SHORT_EQUALS };
			Object[] params = new Object[] { pref, secCanton };
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

			resp = parroquiaServicio.findByCriterias(criteria);

			for (Parroquia parroquia : resp) {
				// se hace esto porque en la bdd se esta guardando como vacio,
				if (parroquia.getParroquia() == null || parroquia.getParroquia().equals("")) {

					System.out.println("poniendo no disponible...");

					parroquia.setParroquia(getBundleMensajes("no.disponible", null));
				}
			}

		}

		return resp;
	}

	public void crearDirecciones(ActionEvent event) {
		crearDireccionesGenerico(personaNatural.getPersona());
	}

	/**
	 * Metodo generico para crear una direccion.
	 * 
	 * @param persona
	 */
	public void crearDireccionesGenerico(Persona persona) {
		Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);

		Provincia provincia = new Provincia();
		provincia.setPais(pais);

		Canton canton = new Canton();
		canton.setProvincia(provincia);

		Direccion direccion = new Direccion();
		direccion.setPersona(persona);
		direccion.setVerificada(RespuestaEnum.NO.getCodigo());
		direccion.setLatitud(new BigDecimal(0));
		direccion.setLongitud(new BigDecimal(0));
		direccion.setCanton(canton);
		direccion.setTipoDireccion(new TipoDireccion());
		direccion.setEstado(EstadoEnum.ACTIVO.getCodigo());
		direccion.setDireccionTelefonoCollection(new ArrayList<DireccionTelefono>());

		// Se pone la direccion en la persona
		if (persona.getDireccionCollection() == null) {
			persona.setDireccionCollection(new ArrayList<Direccion>());
		}

		persona.getDireccionCollection().add(direccion);
	}

	public void eliminarDireccion(ActionEvent event) {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");
		if (e.getSecDireccion() == null) {
			personaNatural.getPersona().getDireccionCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
			Collection<DireccionTelefono> telList = e.getDireccionTelefonoCollection();
			for (DireccionTelefono dt : telList) {
				dt.getTelefono().setEstado(EstadoEnum.INACTIVO.getCodigo());
			}
		}
	}

	public void eliminarCorreoElectronico(ActionEvent event) {
		DireccionElectronica e = (DireccionElectronica) getExternalContext().getRequestMap().get("b");
		if (e.getSecDireccionElectronica() == null) {
			personaNatural.getPersona().getDireccionElectronicaFormularioCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void crearDireccionTelefono(ActionEvent event) {
		DireccionTelefono direccionTelefono = new DireccionTelefono();
		direccionTelefono.setPersona(personaNatural.getPersona());
		Direccion direccion = (Direccion) getExternalContext().getRequestMap().get("dir");
		direccionTelefono.setDireccion(direccion);
		Telefono telefono = new Telefono();
		telefono.setPersona(personaNatural.getPersona());
		telefono.setTipoTelefono(new TipoTelefono());
		Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);
		telefono.setPais(pais);
		System.out.println("cod area:" + direccion.getCanton().getProvincia().getCodArea());
		telefono.setCodArea(direccion.getCanton().getProvincia().getCodArea());
		telefono.setEstado(EstadoEnum.ACTIVO.getCodigo());
		direccionTelefono.setTelefono(telefono);

		if (direccion.getDireccionTelefonoCollection() == null) {
			direccion.setDireccionTelefonoCollection(new ArrayList<DireccionTelefono>());
		}

		direccion.getDireccionTelefonoCollection().add(direccionTelefono);

		Collection<DireccionTelefono> dtList = direccion.getDireccionTelefonoCollection();
		for (DireccionTelefono dt : dtList) {
			System.out.println(dt.getTelefono().toString());

		}

	}

	public void eliminarTelefono(ActionEvent event) {
		DireccionTelefono e = (DireccionTelefono) getExternalContext().getRequestMap().get("dt");
		Direccion direccion = (Direccion) getExternalContext().getRequestMap().get("dir");
		if (e.getTelefono().getSecTelefono() == null) {
			direccion.getDireccionTelefonoCollection().remove(e);
		} else {
			e.getTelefono().setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void crearCorreoElectronico(ActionEvent event) {
		DireccionElectronica de = new DireccionElectronica();
		de.setEstado(EstadoEnum.ACTIVO.getCodigo());
		de.setPersona(personaNatural.getPersona());
		de.setTipoDireccionElectronica(new TipoDireccionElectronica());
		personaNatural.getPersona().getDireccionElectronicaFormularioCollection().add(de);
	}

	public void crearTelefonoAdicional(ActionEvent event) {
		Telefono telefono = new Telefono();
		Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);
		telefono.setTipoTelefono(new TipoTelefono());
		telefono.setPersona(personaNatural.getPersona());
		telefono.setEstado(EstadoEnum.ACTIVO.getCodigo());
		telefono.setCodArea("09");
		telefono.setPais(pais);
		personaNatural.getPersona().getTelefonoSinDireccionCollection().add(telefono);
		// persona.getTelefonoCollection().add(telefono);
	}

	public void eliminarTelefonoAdicional(ActionEvent event) {
		Telefono e = (Telefono) getExternalContext().getRequestMap().get("t");
		if (e.getSecTelefono() == null) {
			personaNatural.getPersona().getTelefonoSinDireccionCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void initPersonaParaFormulario(TipoIdentificacion tipoIdentificacion) {

		personaNatural.setPersona(new Persona());
		personaNatural.getPersona().setContactoPreferidoTransient(new ContactoPreferido());
		// horario contacto preferido
		personaNatural.getPersona().getContactoPreferidoTransient().ponerHorarioEnHorasMinutos();

		personaNatural.getPersona().setDireccionCollection(new ArrayList<Direccion>());
		personaNatural.getPersona().setDireccionElectronicaFormularioCollection(new ArrayList<DireccionElectronica>());
		personaNatural.getPersona().setTelefonoSinDireccionCollection(new ArrayList<Telefono>());
		personaNatural.getPersona().setTipoIdentificacion(tipoIdentificacion);
		personaNatural.getPersona().setCliente(EsClienteEnum.SI.getCodigo());

		// motivo seguro

		List<TipoMotivoSeguro> tipoMotivoSeguroLista = tipoMotivoSeguroServicio.findAll();

		PersonaUtil.inicializarMotivosSeguro(personaNatural, tipoMotivoSeguroLista, null);

		System.out.println(
				"tipo seguro persona:" + personaNatural.getPersona().getMotivoSeguroFormularioCollection().size());

		// benefiarios
		personaNatural.getPersona().setBeneficiarioPolizaCollection(new ArrayList<BeneficiarioPoliza>());

	}

	public Provincia getProvinciaNoDisponible() {
		if (provinciaNoDisponible == null) {
			provinciaNoDisponible = provinciaServicio.findByPk(Constantes.PROVINCIA_ID_NO_DISPONIBLE);
		}
		return provinciaNoDisponible;
	}

	public void setProvinciaNoDisponible(Provincia provinciaNoDisponible) {
		this.provinciaNoDisponible = provinciaNoDisponible;
	}

	public Canton getCantonNoDisponible() {
		if (cantonNoDisponible == null) {
			cantonNoDisponible = cantonServicio.findByPk(Constantes.CANTON_ID_NO_DISPONIBLE);
		}
		return cantonNoDisponible;
	}

	public void setCantonNoDisponible(Canton cantonNoDisponible) {
		this.cantonNoDisponible = cantonNoDisponible;
	}

	public Parroquia getParroquiaNoDisponible() {
		if (parroquiaNoDisponible == null) {
			parroquiaNoDisponible = parroquiaServicio.findByPk(Constantes.PARROQUIA_ID_NO_DISPONIBLE);
		}
		return parroquiaNoDisponible;
	}

	public void setParroquiaNoDisponible(Parroquia parroquiaNoDisponible) {
		this.parroquiaNoDisponible = parroquiaNoDisponible;
	}

	public Map<String, Ciudad> getCiudadesMap() {
		return ciudadesMap;
	}

	public void setCiudadesMap(Map<String, Ciudad> ciudadesMap) {
		this.ciudadesMap = ciudadesMap;
	}

	/**
	 * @return the muestraPopUp
	 */
	public boolean isMuestraPopUp() {
		return muestraPopUp;
	}

	/**
	 * @param muestraPopUp the muestraPopUp to set
	 */
	public void setMuestraPopUp(boolean muestraPopUp) {
		this.muestraPopUp = muestraPopUp;
	}

}