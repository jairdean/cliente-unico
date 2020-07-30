/**
 * 
 */
package com.equivida.intranet.ctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONException;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoCivilEnum;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.PersonaRechazoListaReservadaEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.constant.TipoPersonaEnum;
import com.equivida.dto.MensajeRcsDto;
import com.equivida.dto.RespuestaGeneralDto;
import com.equivida.exception.ContratanteException;
import com.equivida.exception.RcsException;
import com.equivida.helper.ContratanteHelper;
import com.equivida.intranet.util.BasePersonaCtrl;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Canton;
import com.equivida.modelo.CategoriaPpe;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.PersonaPe;
import com.equivida.modelo.Provincia;
import com.equivida.modelo.Relacion;
import com.equivida.modelo.SectorMercado;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.modelo.TipoPersonaJuridica;
import com.equivida.servicio.ActividadEconomicaServicio;
import com.equivida.servicio.ConsultaGeneralCUServicio;
import com.equivida.servicio.ContratanteServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.SectorMercadoServicio;
import com.equivida.servicio.TipoIdentificacionServicio;
import com.equivida.servicio.TipoParentescoRelacionServicio;
import com.equivida.servicio.TipoPersonaJuridicaServicio;
import com.equivida.util.CedulaValidator;
import com.equivida.util.LWResources;
import com.equivida.util.ValidatorUtils;

/**
 * @author juan
 * @author Daniel Cardenas
 *
 */
@ManagedBean(name = "contratantePJCtrl")
@ViewScoped
public class ContratantePJCtrl extends BasePersonaCtrl {
	private static final long serialVersionUID = 7046636433885387744L;

	@EJB(mappedName = "TipoIdentificacionServicio/local")
	private TipoIdentificacionServicio tipoIdentificacionServicio;
	@EJB(mappedName = "ContratanteServicio/local")
	private ContratanteServicio contratanteServicio;

	@EJB(mappedName = "TipoPersonaJuridicaServicio/local")
	private TipoPersonaJuridicaServicio tipoPersonaJuridicaServicio;

	@EJB(mappedName = "SectorMercadoServicio/local")
	private SectorMercadoServicio sectorMercadoServicio;

	@ManagedProperty("#{ciudadLWAutocompleteCtrl}")
	private CiudadLWAutocompleteCtrl ciudadLWAutocompleteCtrl;

	@EJB(mappedName = "RcsServicio/local")
	private RcsServicio rcsServicio;

	@EJB(mappedName = "PersonaNaturalServicio/local")
	private PersonaNaturalServicio personaNaturalServicio;

	@EJB(mappedName = "ConsultaGeneralCUServicio/local")
	private ConsultaGeneralCUServicio consultaGeneralCUServicio;

	@EJB(mappedName = "ActividadEconomicaServicio/local")
	private ActividadEconomicaServicio actividadEconomicaServicio;

	@EJB(mappedName = "TipoParentescoRelacionServicio/local")
	private TipoParentescoRelacionServicio tipoParentescoRelacionServicio;

	@ManagedProperty("#{sesionCtrl}")
	private SesionCtrl sesionCtrl;

	private boolean sexoAdvertencia = false;

	private TipoPersonaEnum tipoPersona = TipoPersonaEnum.J;

	private ActividadEconomica actividadEconomica;
	private String codigoAE;

	private PersonaJuridica personaJuridica;
	private List<SelectItem> tipoIdentificacionItems;

	private List<SelectItem> tipoAccionistaItems;

	private List<SelectItem> tipoPersonaItems;

	private List<SelectItem> sectorMercadoItems;

	private boolean habilitarBtnGuardar = true;

	private boolean verificadoListasJuridica = false;

	private boolean verificadoListasRepLegal = false;

	private boolean verificadoListasRepLegalConyuge = false;

	private boolean verificadoListasAccionistas = false;

	private String conyugeDelConyuge = "";// nombre del conyuge registrado

	private boolean muestraRcsPopUp = false;

	private String idSise;

	// la respuesta de RCS
	// private MensajeRcsDto mensajeRcsDto;

	// private List<SelectItem> tipoPersonaItems;

	public String getStyleDivBotonGuardar() {

		String estilo = "display:none";

		if (verificadoListasJuridica && verificadoListasRepLegal) {
			if (personaJuridica.getRepresentante().getPersonaNaturalTransient().isConConyuge()) {

				if (verificadoListasRepLegalConyuge && verificadoListasAccionistas) {
					estilo = "display:block";
				}
			} else {
				if (verificadoListasAccionistas) {
					estilo = "display:block";
				}
			}
		}

		return estilo;
	}

	public String getStyleDivTodoFormulario() {

		String estilo = "display:none";

		System.out.println("verificadoListasJuridica:" + verificadoListasJuridica);
		System.out.println("verificadoListasRepLegal:" + verificadoListasRepLegal);

		if (verificadoListasJuridica && verificadoListasRepLegal) {

			System.out.println("RL isConConyuge:"
					+ personaJuridica.getRepresentante().getPersonaNaturalTransient().isConConyuge());

			if (personaJuridica.getRepresentante().getPersonaNaturalTransient().isConConyuge()) {
				if (verificadoListasRepLegalConyuge) {
					estilo = "display:block";
				}
			} else {

				// si no pone con conyuge, entonces se muestra
				if (sesionCtrl.getMensajeRcsDto() != null && sesionCtrl.getMensajeRcsDto().isPuedeContinuar()) {
					estilo = "display:block";
				}
			}

		}
		return estilo;

	}

	public String getStyleDivRepLegal() {

		if (verificadoListasJuridica) {
			return "display:block";
		} else {
			return "display:none";
		}

	}

	public String getStyleDivRepLegalConyuge() {

		if (verificadoListasJuridica && verificadoListasRepLegal) {
			return "display:block";
		} else {
			return "display:none";
		}

	}

	public void ponerPep(AjaxBehaviorEvent event) {
		PersonaNatural personaNatural = personaJuridica.getRepresentante().getPersonaNaturalTransient();
		if (personaNatural.getRespuestaPep().equals(RespuestaEnum.SI.getCodigo() + "")) {
			if (personaNatural.getPersonaPeCollectionActivosCollection().size() == 0) {
				crearPersonaPEP(null);
			}
		} else {
			if (personaNatural.getPersonaPeCollection() != null && !personaNatural.getPersonaPeCollection().isEmpty()) {
				// Se quitan los que estan en nulo
				for (Iterator<PersonaPe> itr = personaNatural.getPersonaPeCollection().iterator(); itr.hasNext();) {
					if (itr.next().getSecPersonaPpe() == null) {
						itr.remove();
					}
				}

				// Se ponen inactivos los que tiene ID
				for (PersonaPe p : personaNatural.getPersonaPeCollection()) {
					if (p.getSecPersonaPpe() != null) {
						p.setEstado(EstadoEnum.INACTIVO.getCodigo());
					}
				}
			}
		}
	}

	public void crearPersonaPEP(ActionEvent event) {

		PersonaNatural personaNatural = personaJuridica.getRepresentante().getPersonaNaturalTransient();

		PersonaPe personaPe = new PersonaPe();
		personaPe.setEstado(EstadoEnum.ACTIVO.getCodigo());
		personaPe.setPersonaNatural(personaNatural);
		personaPe.setCategoriaPpe(new CategoriaPpe());
		personaPe.setOrganismoEntidad("");
		personaNatural.getPersonaPeCollection().add(personaPe);

	}

	public void eliminarPersonaPep(ActionEvent event) {

		PersonaNatural personaNatural = personaJuridica.getRepresentante().getPersonaNaturalTransient();

		PersonaPe e = (PersonaPe) getExternalContext().getRequestMap().get("personaPepVar");
		if (e.getSecPersonaPpe() == null) {
			personaNatural.getPersonaPeCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void verificarSiExisteRepresentanteLegal(ValueChangeEvent event) {

		if (event.getNewValue() != null) {

			String identificacion = event.getNewValue().toString();

			System.out.println("1111:" + personaJuridica.getRepresentante());

			System.out.println("2222:" + personaJuridica.getRepresentante().getPersonaNaturalTransient());

			if (personaJuridica.getRepresentante().getPersonaNaturalTransient().getTipoIdentificacion() == null
					|| personaJuridica.getRepresentante().getPersonaNaturalTransient().getTipoIdentificacion()
							.getCodTipoIdentificacion() == null) {
				personaJuridica.getRepresentante().getPersonaNaturalTransient().getTipoIdentificacion()
						.setCodTipoIdentificacion(TipoIdentificacionEnum.CEDULA.getCodigo());
			}

			TipoIdentificacionEnum tipoId = TipoIdentificacionEnum.obtenerPorCodigo(personaJuridica.getRepresentante()
					.getPersonaNaturalTransient().getTipoIdentificacion().getCodTipoIdentificacion());

			char tipoDoc = personaJuridica.getRepresentante().getPersonaNaturalTransient().getTipoIdentificacion()
					.getCodTipoIdentificacion();

			boolean documentoValido = true;

			if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
				documentoValido = CedulaValidator.validate(identificacion);
				// verifica
				if (!documentoValido) {
					String mensaje = getBundleMensajes("numero.documento.incorrecto", null);
					addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
					return;
				}
			}

			PersonaNatural rl = contratanteServicio.verificarExistenciaRepresentanteLegal(identificacion, tipoId);
			Persona rep = rl.getPersona();

			personaJuridica.setRepresentante(rep);
			personaJuridica.getRepresentante().setPersonaNaturalTransient(rl);
		}

	}

	public void verificarSiExisteAccionista(ValueChangeEvent event) {

		if (event.getNewValue() != null) {

			String identificacion = event.getNewValue().toString();

			List<Relacion> lista = personaJuridica.getAccionistaListTra();

			// verifica si ya se ha ingresado antes
			for (Relacion r : lista) {
				if (r.getPersona().getPersonaNaturalTransient() != null
						&& r.getPersona().getPersonaNaturalTransient().getIdentificacion() != null
						&& r.getPersona().getPersonaNaturalTransient().getIdentificacion().equals(identificacion)) {
					String mensaje = "Documento ya ingresado";// getBundleMensajes("numero.documento.incorrecto", null);
					addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
					return;
				}
			}

			System.out.println("busca enriqueimiento, accionista con identificacion: " + identificacion);

			Relacion acc = (Relacion) getExternalContext().getRequestMap().get("acci");

			int index = lista.indexOf(acc);

			PersonaNatural personaNatural = acc.getPersona().getPersonaNaturalTransient();

			System.out.println("borra nombres apellidos conyuge");
			personaNatural.setPrimerNombre("");
			personaNatural.setSegundoNombre("");
			personaNatural.setApellidoPaterno("");
			personaNatural.setApellidoMaterno("");
			personaNatural.setApellidos(null);

			char tipoDoc = personaNatural.getTipoIdentificacion().getCodTipoIdentificacion();

			boolean documentoValido = true;

			if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
				documentoValido = CedulaValidator.validate(identificacion);
				// verifica
				if (!documentoValido) {
					String mensaje = getBundleMensajes("numero.documento.incorrecto", null);
					addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
					return;
				}

				if (identificacion.equals(personaNatural.getIdentificacion())) {
					String mensaje = getBundleMensajes("identificacion.igual.persona.conyuge", null);
					addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
					return;
				}

				if (identificacion.charAt(2) == '9') {
					String m = "Identificaci\u00F3n no corresponde a persona natural";
					addErrorMessage(event.getComponent().getClientId(), m, m);
					return;
				}

				verificarSiExisteAccionista(identificacion, index, acc, event.getComponent().getClientId());
			}

			// si es ruc se asume que es juridica
			if (tipoDoc == Constantes.TIPO_IDENTIFICACION_RUC) {

				if (identificacion.length() != 13) {
					String m = "Ruc incorrecto";
					addErrorMessage(event.getComponent().getClientId(), m, m);
					return;
				}

				boolean error = ValidatorUtils.validaSoloNumeros(identificacion);
				if (error) {
					String m = "Ruc incorrecto";
					addErrorMessage(event.getComponent().getClientId(), m, m);
					return;
				}

				if (identificacion.charAt(2) != '9') {
					String m = "Identificaci\u00F3n no corresponde a persona juridica";
					addErrorMessage(event.getComponent().getClientId(), m, m);
					return;
				}

				verificarSiExisteAccionistaEnJuridica(identificacion, index, acc, event.getComponent().getClientId());

			}

		}

	}

	public void verificarSiExisteConyugeRl(ValueChangeEvent event) {
		if (this.personaJuridica.getRepresentante().getPersonaNaturalTransient() != null) {
			System.out.println("borra nombres apellidos conyuge");
			PersonaNatural personaNatural = personaJuridica.getRepresentante().getPersonaNaturalTransient();
			// Se inicializa con un objeto vacio
			PersonaNatural conyuge = new PersonaNatural();
			ContratanteHelper.inicializaPersonaNaturalRepLegal(conyuge);
			personaNatural.setConyuge(conyuge);
			verificadoListasRepLegal = false;

			if (event.getNewValue() == null || event.getNewValue().toString().trim().equals("")) {
				return;
			}

			String numDoc = event.getNewValue().toString();// personaNatural.getConyuge().getIdentificacion();
			char tipoDoc = personaNatural.getConyuge().getTipoIdentificacion().getCodTipoIdentificacion();

			boolean documentoValido = true;

			if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
				documentoValido = CedulaValidator.validate(numDoc);
			}

			// verifica
			if (!documentoValido) {
				String mensaje = getBundleMensajes("numero.documento.incorrecto", null);
				addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
				return;
			}

			if (numDoc.equals(personaNatural.getIdentificacion())) {
				String mensaje = getBundleMensajes("identificacion.igual.persona.conyuge", null);
				addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
				return;
			}

			verificarListaExistentesConyugeRl(numDoc);

			if (personaNatural.getConyuge() != null && personaNatural.getConyuge().getPersona() != null
					&& personaNatural.getConyuge().getPersona().getSecPersona() != null) {

				Persona delConyuge = personaNaturalServicio
						.obtenerConyuge(personaNatural.getConyuge().getPersona().getSecPersona());

				if (delConyuge != null) {
					conyugeDelConyuge = "Tiene conyuge registrado:" + delConyuge.getDenominacion();
				} else {
					conyugeDelConyuge = "";
				}
			}
		}
	}

	private void verificarListaExistentesConyugeRl(String numDoc) {

		// Se busca en enriquecimiento
		RespuestaGeneralDto respuestaDto = consultaGeneralCUServicio.consultaGenerica(numDoc, getRemoteUser(), null);

		PersonaNatural pn = respuestaDto.getPersonaEncontradaUnica();

		// busca en pers. natural
		if (pn != null) {
			if (pn.getSecPersonaNatural() != null && pn.getEdad() != null) {
				// si encuentra verifica si es mayor de edad
				if (pn.getEdad().getAnios() >= Constantes.MAYOR_DE_EDAD) {
					// si encuentra uno, carga en el form los datos de conyuge
					personaJuridica.getRepresentante().getPersonaNaturalTransient().setConyuge(pn);
					String mensaje = getBundleMensajes("numero.documento.existe", null);
					addInfoMessage("formContratante:txtIdentificacionRlCon", mensaje, mensaje);
					// totalEncontrados = totalEncontrados + existentesPN.size();
				} else {
					if (!pn.getEdad().getEdadTotal().equals("")) {
						personaJuridica.getRepresentante().getPersonaNaturalTransient().setConyuge(pn);// le pone para q
																										// siga
																										// validando la
																										// edad
						String mensaje = "Persona es menor de edad";
						addErrorMessage("formContratante:txtIdentificacionRlCon", mensaje, mensaje);
					}
				}
			}

		}

	}

	private void verificarSiExisteAccionista(String numDoc, int index, Relacion relacion, String componentClientId) {

		// Se busca en enriquecimiento
		RespuestaGeneralDto respuestaDto = consultaGeneralCUServicio.consultaGenerica(numDoc, getRemoteUser(), null);

		PersonaNatural pn = respuestaDto.getPersonaEncontradaUnica();

		// busca en pers. natural
		if (pn != null) {
			// si encuentra uno, carga en el form los datos de conyuge
			// personaJuridica.getAccionistaListTra().
			List<Relacion> lista = personaJuridica.getAccionistaListTra();

			relacion.setPersona(pn.getPersona());

			relacion.getPersona().setPersonaJuridicaTransient(null);

			relacion.getPersona().setPersonaNaturalTransient(pn);

			lista.set(index, relacion);

			String mensaje = "Documento encontrado";
			addInfoMessage(componentClientId, mensaje, mensaje);

		}

	}

	/**
	 * Si la persona accionista es JURIDICA
	 * 
	 * @param numDoc
	 * @param index
	 * @param relacion
	 */
	private void verificarSiExisteAccionistaEnJuridica(String numDoc, int index, Relacion relacion,
			String componentClientId) {

		// Se busca en enriquecimiento
		PersonaJuridica pj = contratanteServicio.verificarExistenciaPJ(numDoc);

		// busca en pers. natural
		if (pj != null) {
			if (pj.getRazonSocial() != null && !pj.getRazonSocial().equals("") && !pj.getRazonSocial().equals("null")) {
				// entonces no encontro

				List<Relacion> lista = personaJuridica.getAccionistaListTraActivos();

				PersonaNatural pn = relacion.getPersona().getPersonaNaturalTransient();
				relacion.setPersona(pj.getPersona());
				relacion.getPersona().setPersonaJuridicaTransient(pj);
				// solo para mostrar razon social en apellido paterno

				pn.setApellidoPaterno(pj.getRazonSocial());
				pn.setPersona(personaJuridica.getPersona());
				relacion.getPersona().setPersonaNaturalTransient(pn);// .setApellidoPaterno(pj.getRazonSocial());

				lista.set(index, relacion);

				String mensaje = "Documento encontrado";
				addInfoMessage(componentClientId, mensaje, mensaje);
			}
		}

	}

	/*
	 * Verifica la Razon social
	 */
	public void verificarListasJuridica() {

		// Se valida nombres

		boolean error = false;

		// error = ValidatorUtils.validaNombre(personaNatural.getPrimerNombre());
		// System.out.println("error nombre 1: " + error);

		if (error) {
			addErrorMessage("formContratante:txtRazonSocial", "Error, existen caracteres incorrectos", null);
			return;
		}

		System.out.println("error ... : " + error);

		if (!error) {

			sesionCtrl.setMensajeRcsDto(null);// para que vuelva a buscar

			verificarListasJuridicaSync();

			verificadoListasJuridica = true;

		}

	}

	private synchronized void verificarListasJuridicaSync() {

		System.out.println("verificarListasJuridicaSync()");

		// personaJuridica.getRazonSocial();

		String nombreValidar = personaJuridica.getRazonSocial();
		System.out.println("nombre que envia a validar:" + nombreValidar);

		try {

			if (sesionCtrl.getMensajeRcsDto() == null) {
				boolean nuevo = personaJuridica.getSecPersonaJuridica() == null ? true : false;
				sesionCtrl.setMensajeRcsDto(rcsServicio.verificarBloqueoPJ(personaJuridica, getRemoteUser(),
						getHttpServletRequest().getRemoteAddr(), nuevo,
						PersonaRechazoListaReservadaEnum.EN_LISTA_PERSONA_JURIDICA));

				System.out.println("MensajeRcsDto:" + sesionCtrl.getMensajeRcsDto());
			}

			// } catch (RemoteException e) {
			// e.printStackTrace();
			// } catch (ServiceException e) {
			// e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Verifica el representante legal
	 */
	public void verificarListasRepLegal() {

		// Se valida nombres

		boolean error = false;

		PersonaNatural repLegal = personaJuridica.getRepresentante().getPersonaNaturalTransient();

		error = ValidatorUtils.validaNombre(repLegal.getPrimerNombre());
		System.out.println("error nombre 1: " + error);

		if (error) {
			addErrorMessage("formContratante:txtPrimerNombreRl", getBundleMensajes("valida.nombre", null), null);
		} else {
			error = ValidatorUtils.validaNombre(repLegal.getApellidoPaterno());
			System.out.println("error ap 1: " + error);

			if (error) {

				addErrorMessage("formContratante:txtApellidoPaternoRl", getBundleMensajes("valida.nombre", null), null);
			} else {

				if (repLegal.getSegundoNombre() != null && !repLegal.getSegundoNombre().isEmpty()) {
					// no se contempla esta validacion
					// error = ValidatorUtils.validaNombre(personaNatural.getSegundoNombre());
					System.out.println("error nombre 2: " + error);
				}

				if (error) {

					addErrorMessage("formContratante:txtSegundoNombreRl", getBundleMensajes("valida.nombre", null),
							null);
				} else {

					if (repLegal.getApellidoMaterno() != null && !repLegal.getApellidoMaterno().isEmpty()) {
						error = ValidatorUtils.validaNombre(repLegal.getApellidoMaterno());
						System.out.println("error ap 2: " + error);
					}

					if (error) {

						addErrorMessage("formContratante:txtApellidoMaternoRl",
								getBundleMensajes("valida.nombre", null), null);
					}
				}
			}

		}

		System.out.println("error ... : " + error);

		if (!error) {
			verificarListasRepLegalSync();
			verificadoListasRepLegal = true;
		}

	}

	/*
	 * Verifica el representante legal
	 */
	public void verificarListasRepLegalConyuge() {

		// Se valida nombres

		boolean error = false;

		PersonaNatural repLegalConyuge = personaJuridica.getRepresentante().getPersonaNaturalTransient().getConyuge();

		if (repLegalConyuge.getEdad() != null) {
			// si encuentra verifica si es mayor de edad
			if (!repLegalConyuge.getEdad().getEdadTotal().equals("")
					&& repLegalConyuge.getEdad().getAnios() < Constantes.MAYOR_DE_EDAD) {
				String mensaje = "Persona es menor de edad";
				addErrorMessage("formContratante:txtIdentificacionRlCon", mensaje, mensaje);
				return;
			}
		}

		error = ValidatorUtils.validaNombre(repLegalConyuge.getPrimerNombre());
		System.out.println("error nombre 1: " + error);

		if (error) {
			addErrorMessage("formContratante:txtPrimerNombreRlCon", getBundleMensajes("valida.nombre", null), null);
		} else {
			error = ValidatorUtils.validaNombre(repLegalConyuge.getApellidoPaterno());
			System.out.println("error ap 1: " + error);

			if (error) {

				addErrorMessage("formContratante:txtApellidoPaternoRlCon", getBundleMensajes("valida.nombre", null),
						null);
			} else {

				if (repLegalConyuge.getSegundoNombre() != null && !repLegalConyuge.getSegundoNombre().isEmpty()) {
					// no se contempla esta validacion
					// error = ValidatorUtils.validaNombre(personaNatural.getSegundoNombre());
					System.out.println("error nombre 2: " + error);
				}

				if (error) {

					addErrorMessage("formContratante:txtSegundoNombreRlCon", getBundleMensajes("valida.nombre", null),
							null);
				} else {

					if (repLegalConyuge.getApellidoMaterno() != null
							&& !repLegalConyuge.getApellidoMaterno().isEmpty()) {
						error = ValidatorUtils.validaNombre(repLegalConyuge.getApellidoMaterno());
						System.out.println("error ap 2: " + error);
					}

					if (error) {

						addErrorMessage("formContratante:txtApellidoMaternoRlCon",
								getBundleMensajes("valida.nombre", null), null);
					}
				}
			}

		}

		System.out.println("error ... : " + error);

		if (!error) {
			verificarListasRepLegalConyugeSync();
			verificadoListasRepLegalConyuge = true;
		}

	}

	/*
	 * Verifica el representante legal
	 */
	public void verificarListasAccionistas() {

		// Se valida nombres

		boolean error = false;

		Collection<Relacion> accionistas = personaJuridica.getAccionistaListTraActivos();
		int cont = 0;
		boolean existeError = false;

		List<String> buscaRepetidos = new ArrayList<String>();

		for (Relacion a : accionistas) {
			if (a.getPersona().getPersonaJuridicaTransient() == null) {
				error = ValidatorUtils.validaNombre(a.getPersona().getPersonaNaturalTransient().getPrimerNombre());
				if (error) {
					addErrorMessage("formContratante:tblAccionistas:" + cont + ":txtActN1",
							getBundleMensajes("valida.nombre", null), null);
					existeError = true;
				}
				error = ValidatorUtils.validaNombre(a.getPersona().getPersonaNaturalTransient().getApellidoPaterno());
				if (error) {
					addErrorMessage("formContratante:tblAccionistas:" + cont + ":txtActAp",
							getBundleMensajes("valida.nombre", null), null);
					existeError = true;
				}
				// se pone null los apellidos por si cambiaron el apellido luego de poner la
				// cedula y cargar datos existentes
				a.getPersona().getPersonaNaturalTransient().setApellidos(null);
			}

			String identificacion = a.getPersona().getPersonaNaturalTransient().getIdentificacion();
			if (buscaRepetidos.contains(identificacion)) {
				addErrorMessage("formContratante:tblAccionistas:" + cont + ":txtIdentificacionAcc",
						"Documento duplicado en lista de accionistas", null);
				existeError = true;
			}

			buscaRepetidos.add(identificacion);

			cont++;
		}

		if (!existeError) {
			verificarListasAccionistasSync();
			verificadoListasAccionistas = true;
		}

	}

	private synchronized void verificarListasRepLegalConyugeSync() {

		System.out.println("verificarListasRepLegalConyugeSync()");

		// personaJuridica.getRazonSocial();

		String nombreValidar = personaJuridica.getRepresentante().getPersonaNaturalTransient().getConyuge()
				.getApellidosNombres();
		System.out.println("nombre que envia a validar:" + nombreValidar);

		try {

			// if (getMensajeRcsDto() == null) {
			boolean nuevo = personaJuridica.getSecPersonaJuridica() == null ? true : false;
			sesionCtrl.setMensajeRcsDto(rcsServicio.verificarBloqueoPJ(personaJuridica, getRemoteUser(),
					getHttpServletRequest().getRemoteAddr(), nuevo,
					PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_REPRESENTANTE_LEGAL));

			System.out.println("MensajeRcsDto:" + sesionCtrl.getMensajeRcsDto());
			// }

			// } catch (RemoteException e) {
			// e.printStackTrace();
			// } catch (ServiceException e) {
			// e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private synchronized void verificarListasRepLegalSync() {

		System.out.println("verificarListasRepLegalSync()");

		// personaJuridica.getRazonSocial();

		String nombreValidar = personaJuridica.getRepresentante().getPersonaNaturalTransient().getApellidosNombres();
		System.out.println("nombre que envia a validar:" + nombreValidar);

		try {

			// if (getMensajeRcsDto() == null) {
			boolean nuevo = personaJuridica.getSecPersonaJuridica() == null ? true : false;
			sesionCtrl.setMensajeRcsDto(rcsServicio.verificarBloqueoPJ(personaJuridica, getRemoteUser(),
					getHttpServletRequest().getRemoteAddr(), nuevo,
					PersonaRechazoListaReservadaEnum.EN_LISTA_REPRESENTANTE_LEGAL));

			System.out.println("MensajeRcsDto:" + sesionCtrl.getMensajeRcsDto());
			// }

			// } catch (RemoteException e) {
			// e.printStackTrace();
			// } catch (ServiceException e) {
			// e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private synchronized void verificarListasAccionistasSync() {

		System.out.println("verificarListasAccionistasSync()");

		// personaJuridica.getRazonSocial();

		Collection<Relacion> accionistas = personaJuridica.getAccionistaListTraActivos();

		boolean nuevo = personaJuridica.getSecPersonaJuridica() == null ? true : false;

		for (Relacion relacion : accionistas) {
			try {

				personaJuridica.setAccionistasIteracionRcs(relacion.getPersona().getPersonaNaturalTransient());

				List<MensajeRcsDto> listaAccionistaRcs = new ArrayList<MensajeRcsDto>();

				listaAccionistaRcs.add(rcsServicio.verificarBloqueoPJ(personaJuridica, getRemoteUser(),
						getHttpServletRequest().getRemoteAddr(), nuevo,
						PersonaRechazoListaReservadaEnum.EN_LISTA_ACCIONISTA));

				for (MensajeRcsDto m : listaAccionistaRcs) {
					System.out.println("MensajeRcsDto:" + m);
					if (m != null && !m.isPuedeContinuar()) {
						System.out.println("m.isPuedeContinuar():" + m.isPuedeContinuar());
						sesionCtrl.setMensajeRcsDto(m);
					}
				}

				// } catch (RemoteException e) {
				// e.printStackTrace();
				// } catch (ServiceException e) {
				// e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void seleccionarActividad() {
		String lnn = personaJuridica.getActividadEconomica().getActividadEconomica();
		String[] arr = lnn.split("\\|");

		ActividadEconomica act = actividadEconomicaServicio.findByPk(Short.parseShort(arr[0]));

		// personaJuridica.getActividadEconomica().setCodActividadEconomica(Short.parseShort(arr[0]));
		// personaJuridica.getActividadEconomica().setActividadEconomica(arr[1]);

		// llena el nivel 1 y nivel 2
		if (act != null) {
			actividadEconomicaServicio.ponerNivel2Nivel1(act);
			personaJuridica.setActividadEconomica(act);
		}

	}

	public void cambiarTipoIdentificacionRepLegalPJ(ValueChangeEvent e) {

		TipoIdentificacion ti = tipoIdentificacionServicio.findByPk((Character) e.getNewValue());

		personaJuridica.getRepresentante().getPersonaNaturalTransient().setTipoIdentificacion(ti);

	}

	public void crearAccionista() {
		System.out.println("ingresa-------------------------------crearAccionista");

		TipoIdentificacion tipoIdCed = tipoIdentificacionServicio.findByPk(TipoIdentificacionEnum.CEDULA.getCodigo());
		if (personaJuridica.getAccionistaListTra() == null) {
			personaJuridica.setAccionistaListTra(new ArrayList<Relacion>());
		}
		// Agregamos uno

		Relacion r = new Relacion();
		r.setPersona1(personaJuridica.getPersona());
		r.setEstado(EstadoEnum.ACTIVO.getCodigo());

		TipoParentescoRelacion t = new TipoParentescoRelacion(Constantes.TIPO_RELACION_ACCIONISTA);

		r.setTipoParentescoRelacion(t);

		Persona p = new Persona();
		p.setPersonaNaturalTransient(new PersonaNatural());
		p.getPersonaNaturalTransient().setTipoIdentificacion(tipoIdCed);
		r.setPersona(p);

		personaJuridica.getAccionistaListTra().add(r);

		System.out.println("tamano de accionistas: " + personaJuridica.getAccionistaListTra().size());

		verificadoListasAccionistas = false;
	}

	public void eliminarAccionista() {
		Relacion e = (Relacion) getExternalContext().getRequestMap().get("acci");

		if (e.getSecRelacion() == null) {
			personaJuridica.getAccionistaListTra().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void crearDireccionC(ActionEvent event) {
		crearDireccionesGenerico(personaJuridica.getPersona());
	}

	public void crearDireccionPJ(ActionEvent event) {
		crearDireccionesGenerico(personaJuridica.getPersona());
	}

	public void crearDireccionRL(ActionEvent event) {
		crearDireccionesGenerico(personaJuridica.getRepresentante());
	}

	public void eliminarDireccionPJ(ActionEvent event) {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");
		if (e.getSecDireccion() == null) {
			personaJuridica.getPersona().getDireccionCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
			Collection<DireccionTelefono> telList = e.getDireccionTelefonoCollection();
			for (DireccionTelefono dt : telList) {
				dt.getTelefono().setEstado(EstadoEnum.INACTIVO.getCodigo());
			}
		}
	}

	public void eliminarDireccionRL(ActionEvent event) {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");
		if (e.getSecDireccion() == null) {
			personaJuridica.getRepresentante().getDireccionCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
			Collection<DireccionTelefono> telList = e.getDireccionTelefonoCollection();
			for (DireccionTelefono dt : telList) {
				dt.getTelefono().setEstado(EstadoEnum.INACTIVO.getCodigo());
			}
		}
	}

	public void eliminarDireccionC(ActionEvent event) {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");
		if (e.getSecDireccion() == null) {
			personaJuridica.getPersona().getDireccionCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
			Collection<DireccionTelefono> telList = e.getDireccionTelefonoCollection();
			for (DireccionTelefono dt : telList) {
				dt.getTelefono().setEstado(EstadoEnum.INACTIVO.getCodigo());
			}
		}
	}

	public void cambiarEstadoCivilPJ(ValueChangeEvent event) throws AbortProcessingException {

		Short valorNuevo = (Short) event.getNewValue();

		// verifica si pone casado o union
		boolean conConyuge = false;
		for (EstadoCivilEnum ec : EstadoCivilEnum.getEstadoConConyuge()) {
			if (valorNuevo.shortValue() == ec.getCodigo()) {
				conConyuge = true;
				break;
			}
		}

		if (conConyuge) {
			if (personaJuridica.getRepresentante().getPersonaNaturalTransient().getConyuge() == null) {
				iniciarConyuge(personaJuridica.getRepresentante().getPersonaNaturalTransient());
			}
		}

	}

	public void guardarPJ() {
		System.out.println("Guardando PJ...");

		try {
			muestraRcsPopUp = false;

			BigDecimal idProcesoSise = contratanteServicio.persistirPJ(personaJuridica);
			// BigDecimal idPersonaSise = mockPersistir();

			System.out.println("contratante--------------------------------idProcesoSise: " + idProcesoSise);

			if (idProcesoSise != null) {
				idSise = idProcesoSise.toString();

				System.out.println("CONTRATATE Se persiste sin problemas...");
			} else {
				addErrorMessage(null, getBundleMensajes("error.guardar.sise", null), null);
			}
		} catch (ContratanteException e) {
			e.printStackTrace();
			if (e.isBuscarKey()) {
				addErrorMessage(null, getBundleMensajes(e.getKeyLabel(), e.getParams()), null);
			} else {
				addErrorMessage(null, e.getMessage(), null);
			}
		} catch (RcsException e) {
			e.printStackTrace();
			muestraRcsPopUp = true;
			// mensajeRcsDto = e.getMensajeRcs();
		}
	}

	public void cambiarApellidoNombre(ValueChangeEvent e) {
		verificadoListasAccionistas = false;
	}

	public void cambiarTipoIdentificacionAccionista(ValueChangeEvent e) {

		Relacion acc = (Relacion) getExternalContext().getRequestMap().get("acci");

		TipoIdentificacion ti = tipoIdentificacionServicio.findByPk((Character) e.getNewValue());

		acc.getPersona().getPersonaNaturalTransient().setTipoIdentificacion(ti);
		acc.getPersona().getPersonaNaturalTransient().setIdentificacion("");
		acc.getPersona().getPersonaNaturalTransient().setApellidoPaterno("");
		acc.getPersona().getPersonaNaturalTransient().setApellidoMaterno("");
		acc.getPersona().getPersonaNaturalTransient().setPrimerNombre("");
		acc.getPersona().getPersonaNaturalTransient().setSegundoNombre("");

		if (ti.isCedula() || ti.isPasaporte()) {
			acc.getPersona().setPersonaJuridicaTransient(null);
		} else {
			acc.getPersona().setPersonaJuridicaTransient(new PersonaJuridica());
			acc.getPersona().getPersonaJuridicaTransient().setTipoIdentificacion(ti);
		}

		verificadoListasAccionistas = false;

	}

	public void seleccionarPaisNacimientoRL() {

		Ciudad ciudad = new Ciudad();
		ciudad.setPais(personaJuridica.getRepresentante().getPersonaNaturalTransient().getCiudadNacimiento().getPais());
		personaJuridica.getRepresentante().getPersonaNaturalTransient().setCiudadNacimiento(ciudad);

		System.out.println("en listener, ciduad reseteada:"
				+ personaJuridica.getRepresentante().getPersonaNaturalTransient().getCiudadNacimiento().getCiudad());
	}

	/**
	 * parroquia fecha de nacimiento
	 * 
	 * @param pref
	 * @return
	 */
	public List<Ciudad> autocompleteCiudadPJ(String pref) {
		Short codPais = getPersonaJuridica().getRepresentante().getPersonaNaturalTransient().getCiudadNacimiento()
				.getPais().getCodPais();

		if (codPais == null) {
			return new ArrayList<Ciudad>();
		}

		return buscarCiudades(pref, codPais);
	}

	/**
	 * @return the tipoAccionistaItems
	 */
	public List<SelectItem> getTipoAccionistaItems() {
		if (tipoAccionistaItems == null) {
			tipoAccionistaItems = new ArrayList<SelectItem>();

			List<TipoParentescoRelacion> lista = tipoParentescoRelacionServicio
					.obtenerPorIds(Constantes.IDS_TIPO_RELACION_ACCIONISTAS);

			for (TipoParentescoRelacion ta : lista) {
				tipoAccionistaItems.add(new SelectItem(ta.getCodTipoParentesco(), ta.getTipoParentesco()));
			}
		}
		return tipoAccionistaItems;
	}

	/**
	 * @param tipoAccionistaItems the tipoAccionistaItems to set
	 */
	public void setTipoAccionistaItems(List<SelectItem> tipoAccionistaItems) {
		this.tipoAccionistaItems = tipoAccionistaItems;
	}

	public void crearDireccionTelefonoRL(ActionEvent event) {
		Direccion direccion = (Direccion) getExternalContext().getRequestMap().get("dir");
		Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);

		DireccionTelefono direccionTelefono = new DireccionTelefono();

		// Se inicializa los valores del telefono para la direccion del representante
		// legal
		ContratanteHelper.crearDireccionTelefono(direccionTelefono, personaJuridica.getRepresentante(), direccion,
				pais);
	}

	public void crearDireccionTelefonoC(ActionEvent event) {
		Direccion direccion = (Direccion) getExternalContext().getRequestMap().get("dir");
		Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);

		DireccionTelefono direccionTelefono = new DireccionTelefono();

		// Se inicializa los valores del telefono para la direccion del representante
		// legal
		ContratanteHelper.crearDireccionTelefono(direccionTelefono, personaJuridica.getPersona(), direccion, pais);
	}

	public void crearDireccionTelefonoPJ(ActionEvent event) {
		Direccion direccion = (Direccion) getExternalContext().getRequestMap().get("dir");
		Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);

		DireccionTelefono direccionTelefono = new DireccionTelefono();

		// Se inicializa los valores del telefono para la direccion del representante
		// legal
		ContratanteHelper.crearDireccionTelefono(direccionTelefono, personaJuridica.getPersona(), direccion, pais);
	}

	/**
	 * @return the personaJuridica
	 */
	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	/**
	 * @param personaJuridica the personaJuridica to set
	 */
	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	/**
	 * @return the tipoPersona
	 */
	public TipoPersonaEnum getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(TipoPersonaEnum tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * @return the actividadEconomica
	 */
	public ActividadEconomica getActividadEconomica() {
		return actividadEconomica;
	}

	/**
	 * @param actividadEconomica the actividadEconomica to set
	 */
	public void setActividadEconomica(ActividadEconomica actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	/**
	 * @return the codigoAE
	 */
	public String getCodigoAE() {
		return codigoAE;
	}

	/**
	 * @param codigoAE the codigoAE to set
	 */
	public void setCodigoAE(String codigoAE) {
		this.codigoAE = codigoAE;
	}

	// desde aqui

	public List<SelectItem> getTipoIdentificacionItems() {
		if (tipoIdentificacionItems == null) {
			tipoIdentificacionItems = new ArrayList<SelectItem>();

			List<TipoIdentificacion> tmpList = tipoIdentificacionServicio.findAll();

			for (TipoIdentificacion tipoIdentificacion : tmpList) {
				if (TipoIdentificacionEnum
						.isParaRepresentateLegal(tipoIdentificacion.getCodTipoIdentificacion().charValue())) {
					SelectItem item = new SelectItem(tipoIdentificacion.getCodTipoIdentificacion(),
							tipoIdentificacion.getTipoIdentificacion());
					tipoIdentificacionItems.add(item);
				}
			}
		}

		return tipoIdentificacionItems;
	}

	public void setTipoIdentificacionItems(List<SelectItem> tipoIdentificacionItems) {
		this.tipoIdentificacionItems = tipoIdentificacionItems;
	}

	/**
	 * @return the sexoAdvertencia
	 */
	public boolean isSexoAdvertencia() {
		return sexoAdvertencia;
	}

	/**
	 * @param sexoAdvertencia the sexoAdvertencia to set
	 */
	public void setSexoAdvertencia(boolean sexoAdvertencia) {
		this.sexoAdvertencia = sexoAdvertencia;
	}

	public List<SelectItem> getTipoPersonaItems() {
		if (tipoPersonaItems == null) {
			tipoPersonaItems = new ArrayList<SelectItem>();
			List<TipoPersonaJuridica> lista = tipoPersonaJuridicaServicio.findAll();

			for (TipoPersonaJuridica t : lista) {

				tipoPersonaItems.add(new SelectItem(t.getCodTipoPersonaJuridica(), t.getTipoPersonaJuridica()));
			}

		}
		return tipoPersonaItems;
	}

	public void setTipoPersonaItems(List<SelectItem> tipoPersonaItems) {
		this.tipoPersonaItems = tipoPersonaItems;
	}

	/**
	 * @return the sectorMercadoItems
	 */
	public List<SelectItem> getSectorMercadoItems() {
		if (sectorMercadoItems == null) {
			sectorMercadoItems = new ArrayList<SelectItem>();
			// no se que falta
			List<SectorMercado> lista = sectorMercadoServicio.findAll();
			for (SectorMercado s : lista) {

				sectorMercadoItems.add(new SelectItem(s.getCodSectorMercado(), s.getSectorMercado()));
			}
		}
		return sectorMercadoItems;
	}

	/**
	 * provincia direccion
	 * 
	 * @param pref
	 * @return
	 */
	public List<Provincia> autocompleteProvinciaD(String pref) {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dirPJ");
		Short codPais = e.getCanton().getProvincia().getPais().getCodPais();
		return buscarProvincias(pref, codPais);
	}

	/**
	 * canton direccion
	 * 
	 * @param pref
	 * @return
	 */
	public List<Canton> autocompleteCantonD(String pref) {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dirPJ");
		Short secProvincia = e.getCanton().getProvincia().getSecProvincia();
		return buscarCantones(pref, secProvincia);
	}

	public List<String> autocompleteCiudadConLW(String pref) {
		// String j="[{\"Id\":110117,\"Name\":\"Abanin -
		// Azuay\",\"PhonePrefix\":\"00\",\"Zone\":null},{\"Id\":11647,\"Name\":\"Abanin
		// - El Oro\",\"PhonePrefix\":\"00\",\"Zone\":null}]"; //$NON-NLS-1$

		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");

		Short codPais = e.getCanton().getProvincia().getPais().getCodPais();

		List<String> listaR = new ArrayList<String>();

		HashSet<String> ciudades = getCiudadLWAutocompleteCtrl().getCiudadConLWLista(codPais, ciudadServicio);

		Iterator<String> iterator = ciudades.iterator();

		while (iterator.hasNext()) {
			String ciudad = iterator.next();
			if (ciudad != null && ciudad.toLowerCase().indexOf(pref.toLowerCase()) >= 0 || "".equals(pref)) {
				listaR.add(ciudad);
			}
		}

		return listaR;
	}

	public List<String> autocompleteCallePrincipalConLW(String pref) {
		// String j="[{\"Id\":110117,\"Name\":\"Abanin -
		// Azuay\",\"PhonePrefix\":\"00\",\"Zone\":null},{\"Id\":11647,\"Name\":\"Abanin
		// - El Oro\",\"PhonePrefix\":\"00\",\"Zone\":null}]"; //$NON-NLS-1$

		Direccion d = (Direccion) getExternalContext().getRequestMap().get("dirPJ");

		// Short codPais = e.getCanton().getProvincia().getPais().getCodPais();

		List<String> listaR = new ArrayList<String>();

		String webHandlerCallesPrincipales = LWResources.getString("calles.principales.webhandler"); //$NON-NLS-1$

		try {

			if (d.getCiudad() == null || d.getCiudad().trim().equals("")) {
				System.out.println("no ha seleccionado ciudad:" + d.getCiudad());
				return listaR;
			}

			String ciudad = URLEncoder.encode(d.getCiudad(), "utf-8");

			webHandlerCallesPrincipales = webHandlerCallesPrincipales.replace("@ciudad", ciudad);

			String calleutf8 = URLEncoder.encode(pref, "utf-8");

			webHandlerCallesPrincipales = webHandlerCallesPrincipales.replace("@calle", calleutf8);

			String jsonCalles = ""; //$NON-NLS-1$

			URL url = new URL(webHandlerCallesPrincipales);

			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setConnectTimeout(5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				jsonCalles += inputLine;
			}

			in.close();

			JSONArray jsonArray = new JSONArray(jsonCalles);
			int length = jsonArray.length();
			for (int cont = 0; cont < length; cont++) {
				String calleP = jsonArray.getString(cont);
				listaR.add(calleP);
			}

		} catch (JSONException e) {
			e.printStackTrace();
			// j="";System.out
			System.out.println("Error Connection Host: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);

		} catch (java.net.UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Error Connection Host: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			System.out.println("Error Connection: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Error1: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error2: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		}

		return listaR;
	}

	public void seleccionarProvinciaD() {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dirPJ");
		String lnn = e.getCanton().getProvincia().getProvincia();
		System.out.println("-------------         " + lnn);
		String[] arr = lnn.split("\\|");
		e.getCanton().getProvincia().setSecProvincia(Short.parseShort(arr[0]));
		e.getCanton().getProvincia().setProvincia(arr[1]);
		if (arr.length > 1) {// si hay cod area para telefono
			e.getCanton().getProvincia().setCodArea(arr[2]);
		}

		if (e.getCanton().getProvincia().getSecProvincia() == Constantes.PROVINCIA_ID_NO_DISPONIBLE) {
			e.setCanton(getCantonNoDisponible());
		}

	}

	public void seleccionarCantonD() {

		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dirPJ");

		String lnn = e.getCanton().getCanton();
		String[] arr = lnn.split("\\|");
		e.getCanton().setSecCanton(Short.parseShort(arr[0]));
		e.getCanton().setCanton(arr[1]);
	}

	/**
	 * @param sectorMercadoItems the sectorMercadoItems to set
	 */
	public void setSectorMercadoItems(List<SelectItem> sectorMercadoItems) {
		this.sectorMercadoItems = sectorMercadoItems;
	}

	/**
	 * @return the habilitarBtnGuardar
	 */
	public boolean isHabilitarBtnGuardar() {
		return habilitarBtnGuardar;
	}

	/**
	 * @param habilitarBtnGuardar the habilitarBtnGuardar to set
	 */
	public void setHabilitarBtnGuardar(boolean habilitarBtnGuardar) {
		this.habilitarBtnGuardar = habilitarBtnGuardar;
	}

	/**
	 * @return the ciudadLWAutocompleteCtrl
	 */
	public CiudadLWAutocompleteCtrl getCiudadLWAutocompleteCtrl() {
		return ciudadLWAutocompleteCtrl;
	}

	/**
	 * @param ciudadLWAutocompleteCtrl the ciudadLWAutocompleteCtrl to set
	 */
	public void setCiudadLWAutocompleteCtrl(CiudadLWAutocompleteCtrl ciudadLWAutocompleteCtrl) {
		this.ciudadLWAutocompleteCtrl = ciudadLWAutocompleteCtrl;
	}

	/**
	 * @return the contratanteServicio
	 */
	public ContratanteServicio getContratanteServicio() {
		return contratanteServicio;
	}

	/**
	 * @param contratanteServicio the contratanteServicio to set
	 */
	public void setContratanteServicio(ContratanteServicio contratanteServicio) {
		this.contratanteServicio = contratanteServicio;
	}

	/**
	 * @return the verificadoListasJuridica
	 */
	public boolean isVerificadoListasJuridica() {
		return verificadoListasJuridica;
	}

	/**
	 * @param verificadoListasJuridica the verificadoListasJuridica to set
	 */
	public void setVerificadoListasJuridica(boolean verificadoListasJuridica) {
		this.verificadoListasJuridica = verificadoListasJuridica;
	}

	/**
	 * @return the verificadoListasRepLegal
	 */
	public boolean isVerificadoListasRepLegal() {
		return verificadoListasRepLegal;
	}

	/**
	 * @param verificadoListasRepLegal the verificadoListasRepLegal to set
	 */
	public void setVerificadoListasRepLegal(boolean verificadoListasRepLegal) {
		this.verificadoListasRepLegal = verificadoListasRepLegal;
	}

	/**
	 * @return the verificadoListasRepLegalConyuge
	 */
	public boolean isVerificadoListasRepLegalConyuge() {
		return verificadoListasRepLegalConyuge;
	}

	/**
	 * @param verificadoListasRepLegalConyuge the verificadoListasRepLegalConyuge to
	 *                                        set
	 */
	public void setVerificadoListasRepLegalConyuge(boolean verificadoListasRepLegalConyuge) {
		this.verificadoListasRepLegalConyuge = verificadoListasRepLegalConyuge;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isVerificadoListasAccionistas() {
		return verificadoListasAccionistas;
	}

	public void setVerificadoListasAccionistas(boolean verificadoListasAccionistas) {
		this.verificadoListasAccionistas = verificadoListasAccionistas;
	}

	public String getConyugeDelConyuge() {
		return conyugeDelConyuge;
	}

	public void setConyugeDelConyuge(String conyugeDelConyuge) {
		this.conyugeDelConyuge = conyugeDelConyuge;
	}

	public SesionCtrl getSesionCtrl() {
		return sesionCtrl;
	}

	public void setSesionCtrl(SesionCtrl sesionCtrl) {
		this.sesionCtrl = sesionCtrl;
	}

	public boolean isMuestraRcsPopUp() {
		return muestraRcsPopUp;
	}

	public void setMuestraRcsPopUp(boolean muestraRcsPopUp) {
		this.muestraRcsPopUp = muestraRcsPopUp;
	}

	public String getIdSise() {
		return idSise;
	}

	public void setIdSise(String idSise) {
		this.idSise = idSise;
	}

}
