/**
 * 
 */
package com.equivida.intranet.ctrl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoCivilEnum;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoEmpleoEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.constant.TipoPersonaEnum;
import com.equivida.dto.RespuestaGeneralDto;
import com.equivida.exception.ContratanteException;
import com.equivida.exception.RcsException;
import com.equivida.helper.ContratanteHelper;
import com.equivida.intranet.util.BasePersonaCtrl;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.CategoriaPpe;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.PersonaPe;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoRiesgo;
import com.equivida.servicio.ConsultaGeneralCUServicio;
import com.equivida.servicio.ContratanteServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.TipoIdentificacionServicio;
import com.equivida.util.CedulaValidator;
import com.equivida.util.ValidatorUtils;

/**
 * @author juan
 *
 */
@ManagedBean(name = "contratantePNCtrl")
@ViewScoped
public class ContratantePNCtrl extends BasePersonaCtrl {
	private static final long serialVersionUID = 7046636433885387744L;

	@EJB(mappedName = "TipoIdentificacionServicio/local")
	private TipoIdentificacionServicio tipoIdentificacionServicio;

	@EJB(mappedName = "ContratanteServicio/local")
	private ContratanteServicio contratanteServicio;

	@EJB(mappedName = "PersonaNaturalServicio/local")
	private PersonaNaturalServicio personaNaturalServicio;

	@EJB(mappedName = "RcsServicio/local")
	private RcsServicio rcsServicio;

	@EJB(mappedName = "ConsultaGeneralCUServicio/local")
	private ConsultaGeneralCUServicio consultaGeneralCUServicio;

	private PersonaNatural personaNatural;
	private List<SelectItem> tipoIdentificacionPNItems;
	private TipoPersonaEnum tipoPersona = TipoPersonaEnum.N;
	private boolean sexoAdvertencia = false;
	private ActividadEconomica actividadEconomica;
	private String codigoAE;
	// private MensajeRcsDto mensajeRcsDto;
	private boolean muestraRcsPopUp = false;
	private boolean habilitarBtnCancelar = true;
	private String idSise;

	private boolean verificadoListasPersona = false;

	private boolean verificadoListasConyuge = false;

	private String conyugeDelConyuge = "";// nombre del conyuge registrado

	@ManagedProperty("#{sesionCtrl}")
	private SesionCtrl sesionCtrl;

	public String getStyleDivTodoFormulario() {

		String estilo = "display:none";

		System.out.println("verificadoListasPersona:" + verificadoListasPersona);

		if (verificadoListasPersona) {

			System.out.println("isConConyuge:" + personaNatural.isConConyuge());

			if (personaNatural.isConConyuge()) {
				if (verificadoListasConyuge) {
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

	public String getStyleDivEstadoCivil() {

		if (verificadoListasPersona) {
			return "display:block";
		} else {
			return "display:none";
		}

	}

	public void ponerPep(AjaxBehaviorEvent event) {
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

		PersonaPe personaPe = new PersonaPe();
		personaPe.setEstado(EstadoEnum.ACTIVO.getCodigo());
		personaPe.setPersonaNatural(personaNatural);
		personaPe.setCategoriaPpe(new CategoriaPpe());
		personaPe.setOrganismoEntidad("");
		personaNatural.getPersonaPeCollection().add(personaPe);

	}

	public void eliminarPersonaPep(ActionEvent event) {
		PersonaPe e = (PersonaPe) getExternalContext().getRequestMap().get("personaPepVar");
		if (e.getSecPersonaPpe() == null) {
			personaNatural.getPersonaPeCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public synchronized void verificarNombresConyuge() {

		if (personaNatural.getConyuge().getEdad() != null) {
			// si encuentra verifica si es mayor de edad
			if (!personaNatural.getConyuge().getEdad().getEdadTotal().equals("")
					&& personaNatural.getConyuge().getEdad().getAnios() < Constantes.MAYOR_DE_EDAD) {
				String mensaje = "Persona es menor de edad";
				addErrorMessage("formContratante:txtIdentificacionPNCon", mensaje, mensaje);
				return;
			}
		}

		sesionCtrl.setMensajeRcsDto(null);// para que vuelva a buscar

		String primerNombre = personaNatural.getConyuge().getPrimerNombre();
		// String segundoNombre = personaNatural.getSegundoNombre();
		String primerApellido = personaNatural.getConyuge().getApellidoPaterno();
		// String segundoApellido = personaNatural.getApellidoMaterno();

		if (primerNombre != null && primerNombre.trim().length() > 0 && primerApellido != null
				&& primerApellido.trim().length() > 0) {

			System.out.println("entra a validar nombres conyuge");

			personaNatural.getConyuge().setNombresApellidos(null);// para que
																	// vuelva a
			// generar
			String nombreValidar = personaNatural.getConyuge().getNombresApellidos();
			System.out.println("nombre que envia a validar:" + nombreValidar);

			// try {

			/*
			 * resultado = wsDatosPersonaServicio.validarNombre(nombreValidar);
			 * 
			 * if (!resultado.isError()) {
			 * 
			 * if (!resultado.isValido()) { System.out.println("no es valido");
			 * mostrarPanelNombreInvalido = true; verificarNombresDe = "C"; return; }
			 * 
			 * if (resultado.isCambio()) { System.out.println("existe sugerencia");
			 * mostrarPanelNombreSugerencia = true; verificarNombresDe = "C"; return; }
			 * 
			 * System.out .println("no existe error ni sugerencia en nombre");
			 * 
			 * } else { System.out.println("Error al consultar QA nombres"); }
			 */

			verificarRiesgoConyuge();

			// } catch (RemoteException e) {
			// e.printStackTrace();
			// } catch (ServiceException e) {
			// e.printStackTrace();
			// }
		}

		verificadoListasConyuge = true;
	}

	private void verificarRiesgoConyuge() {

		System.out.println("verificarRiesgoConyuge()");

		if (sesionCtrl.getMensajeRcsDto() == null) {

			boolean nuevo = personaNatural.getSecPersonaNatural() == null ? true : false;

			sesionCtrl.setMensajeRcsDto(rcsServicio.verificarBloqueoConyuge(personaNatural, getRemoteUser(),
					getHttpServletRequest().getRemoteAddr(), nuevo));

			System.out.println("MensajeRcsDto conyuge:" + sesionCtrl.getMensajeRcsDto());

		}

	}

	public void verificarListasReservadas() {

		// Se valida nombres

		boolean error = false;

		error = ValidatorUtils.validaNombre(personaNatural.getPrimerNombre());
		System.out.println("error nombre 1: " + error);

		if (error) {
			addErrorMessage("formContratante:txtPrimerNombrePN", getBundleMensajes("valida.nombre", null), null);
		} else {
			error = ValidatorUtils.validaNombre(personaNatural.getApellidoPaterno());
			System.out.println("error ap 1: " + error);

			if (error) {

				addErrorMessage("formContratante:txtApellidoPaternoPN", getBundleMensajes("valida.nombre", null), null);
			} else {

				if (personaNatural.getSegundoNombre() != null && !personaNatural.getSegundoNombre().isEmpty()) {
					// no se contempla esta validacion
					// error = ValidatorUtils.validaNombre(personaNatural.getSegundoNombre());
					System.out.println("error nombre 2: " + error);
				}

				if (error) {

					addErrorMessage("formContratante:txtSegundoNombre", getBundleMensajes("valida.nombre", null), null);
				} else {

					if (personaNatural.getApellidoMaterno() != null && !personaNatural.getApellidoMaterno().isEmpty()) {
						error = ValidatorUtils.validaNombre(personaNatural.getApellidoMaterno());
						System.out.println("error ap 2: " + error);
					}

					if (error) {

						addErrorMessage("formContratante:txtApellidoMaternoPN",
								getBundleMensajes("valida.nombre", null), null);
					}
				}
			}

		}

		System.out.println("error ... : " + error);

		if (!error) {
			verificarListasReservadasSync();
		}

	}

	public synchronized void verificarListasReservadasSync() {

		sesionCtrl.setMensajeRcsDto(null);// para que vuelva a buscar

		String segundoNombre = personaNatural.getSegundoNombre();

		System.out.println("SEGUNDO NOMBRE:" + segundoNombre + ".");

		verificarRiesgoNombres();

		// buscarFlujo();

		verificadoListasPersona = true;
	}

	private synchronized void verificarRiesgoNombres() {

		// if (true) {
		// return;
		// }

		String primerNombre = personaNatural.getPrimerNombre();
		// String segundoNombre = personaNatural.getSegundoNombre();
		String primerApellido = personaNatural.getApellidoPaterno();
		// String segundoApellido = personaNatural.getApellidoMaterno();

		// verifica que tenga al menos indentificacion , nombre1 y apellido1

		if (primerNombre != null && primerNombre.trim().length() > 0 && primerApellido != null
				&& primerApellido.trim().length() > 0) {

			System.out.println("entra a validar");

			personaNatural.setNombresApellidos(null);// para que vuelva a
														// generar
			String nombreValidar = personaNatural.getNombresApellidos();
			System.out.println("nombre que envia a validar:" + nombreValidar);

			try {

				verificarRiesgo();

				// } catch (RemoteException e) {
				// e.printStackTrace();
				// } catch (ServiceException e) {
				// e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(" no valida nombres porq no tiene nombre1 ni apellido1 ni identificacion");
		}
	}

	public void verificarSiExisteConyuge(ValueChangeEvent event) {
		if (this.personaNatural != null) {
			System.out.println("borra nombres apellidos conyuge");
			PersonaNatural conyuge = new PersonaNatural();
			ContratanteHelper.inicializaPersonaNaturalRepLegal(conyuge);
			personaNatural.setConyuge(conyuge);
			verificadoListasConyuge = false;

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
				addErrorMessage("formContratante:txtIdentificacionPNCon", mensaje, mensaje);
				return;
			}

			if (numDoc.equals(personaNatural.getIdentificacion())) {
				String mensaje = getBundleMensajes("identificacion.igual.persona.conyuge", null);
				addErrorMessage("formContratante:txtIdentificacionPNCon", mensaje, mensaje);
				return;
			}

			verificarListaExistentesConyuge(numDoc);

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

	private void verificarListaExistentesConyuge(String numDoc) {

		// Se busca en enriquecimiento
		RespuestaGeneralDto respuestaDto = consultaGeneralCUServicio.consultaGenerica(numDoc, getRemoteUser(), null);

		PersonaNatural pn = respuestaDto.getPersonaEncontradaUnica();

		// busca en pers. natural
		if (pn != null) {
			if (pn.getEdad() != null) {
				// si encuentra verifica si es mayor de edad
				if (pn.getEdad().getAnios() >= Constantes.MAYOR_DE_EDAD) {
					// si encuentra uno, carga en el form los datos de conyuge
					personaNatural.setConyuge(pn);
					String mensaje = getBundleMensajes("numero.documento.existe", null);
					addInfoMessage("formContratante:txtIdentificacionPNCon", mensaje, mensaje);
					// totalEncontrados = totalEncontrados + existentesPN.size();
				} else {
					if (!pn.getEdad().getEdadTotal().equals("")) {
						personaNatural.setConyuge(pn);// le pone para q siga validando la edad
						String mensaje = "Persona es menor de edad";
						addErrorMessage("formContratante:txtIdentificacionPNCon", mensaje, mensaje);
					}
				}
			}

		}

	}

	private void verificarRiesgo() {

		System.out.println("verificarRiesgo()");

		if (sesionCtrl.getMensajeRcsDto() == null) {
			boolean nuevo = personaNatural.getSecPersonaNatural() == null ? true : false;
			sesionCtrl.setMensajeRcsDto(rcsServicio.verificarBloqueo(personaNatural, getRemoteUser(),
					getHttpServletRequest().getRemoteAddr(), nuevo));

			System.out.println("MensajeRcsDto:" + sesionCtrl.getMensajeRcsDto());
		}
	}

	public void guardar() {
		try {
			muestraRcsPopUp = false;
			habilitarBtnCancelar = true;

			// Por alguna razon se debe hacer esta carga con la variable trasien
			if (this.personaNatural.getIngresoMensual().getTemporalMontoIngreso() != null) {
				this.personaNatural.getIngresoMensual()
						.setMntIngresoMensual(this.personaNatural.getIngresoMensual().getTemporalMontoIngreso());
			}

			BigDecimal idProcesoSise = contratanteServicio.persistirPN(personaNatural);
			// BigDecimal idPersonaSise = mockPersistir();

			System.out.println("contratante--------------------------------idProcesoSise: " + idProcesoSise);

			if (idProcesoSise != null) {
				idSise = idProcesoSise.toString();

				habilitarBtnCancelar = false;

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

	public void sumarIngresoMensual(AjaxBehaviorEvent event) {
		this.personaNatural.getIngresoMensual()
				.setTemporalMontoIngreso(this.personaNatural.getIngresoMensual().getMntIngresoMensual());

		BigDecimal total = BigDecimal.ZERO;
		total = total.add(this.personaNatural.getIngresoMensual().getMntIngresoMensual() != null
				? this.personaNatural.getIngresoMensual().getMntIngresoMensual()
				: BigDecimal.ZERO);

		total = total.add(this.personaNatural.getIngresoMensualAdicionalTra().getMntIngresoAdicional() != null
				? this.personaNatural.getIngresoMensualAdicionalTra().getMntIngresoAdicional()
				: BigDecimal.ZERO);

		personaNatural.setTotalIngresosTra(total);
	}

	public String nueva() {
		sesionCtrl.setMensajeRcsDto(null);
		return "/pages/contratante/formulario.jsf?faces-redirect=true";
	}

	public void seleccionarOcupacion() {
		String lnn = personaNatural.getOcupacion().getOcupacion();
		String[] arr = lnn.split("\\|");
		personaNatural.getOcupacion().setCodOcupacion(Short.parseShort(arr[0]));
		personaNatural.getOcupacion().setOcupacion(arr[1]);

		personaNatural.setTipoRiesgo(new TipoRiesgo(Short.parseShort(arr[2]), arr[3].charAt(0)));
	}

	public void seleccionarED() {
		String lnn = personaNatural.getEmpleoDependienteTra().getNombreActividadEconomicaTr();
		String[] arr = lnn.split("\\|");

		personaNatural.getEmpleoDependienteTra().setIdActividadEconomicaTr(Short.parseShort(arr[0]));
		personaNatural.getEmpleoDependienteTra().setNombreActividadEconomicaTr(arr[1]);

		ActividadEconomica ae = new ActividadEconomica(Short.parseShort(arr[0]));
		personaNatural.getEmpleoDependienteTra().setActividadEconomica(ae);
	}

	public void seleccionarEI() {
		String lnn = personaNatural.getEmpleoIndependienteTra().getNombreActividadEconomicaTr();
		String[] arr = lnn.split("\\|");

		personaNatural.getEmpleoIndependienteTra().setIdActividadEconomicaTr(Short.parseShort(arr[0]));
		personaNatural.getEmpleoIndependienteTra().setNombreActividadEconomicaTr(arr[1]);

		ActividadEconomica ae = new ActividadEconomica(Short.parseShort(arr[0]));
		personaNatural.getEmpleoIndependienteTra().setActividadEconomica(ae);
	}

	public void crearDireccionPN(ActionEvent event) {
		crearDireccionesGenerico(personaNatural.getPersona());
	}

	public void eliminarDireccionPN(ActionEvent event) {
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

	public void crearDireccionTelefonoPN(ActionEvent event) {
		Direccion direccion = (Direccion) getExternalContext().getRequestMap().get("dir");
		Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);

		DireccionTelefono direccionTelefono = new DireccionTelefono();

		// Se inicializa los valores del telefono para la direccion del representante
		// legal
		ContratanteHelper.crearDireccionTelefono(direccionTelefono, personaNatural.getPersona(), direccion, pais);
	}

	public void cambiarEstadoCivilPN(ValueChangeEvent event) throws AbortProcessingException {

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
			if (personaNatural.getConyuge() == null) {
				iniciarConyuge(personaNatural);
			}
		}

	}

	public void cambiarTipoOcupacionPN(ValueChangeEvent event) throws AbortProcessingException {
		// Se pone el nuevo tipo de empleo en la persona natural
		Short valorNuevo = (Short) event.getNewValue();
		personaNatural.setTipoEmpleo(valorNuevo);

		if (TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo() == valorNuevo.shortValue()) {
			personaNatural.setShowIndependienteTra(true);
		} else {
			personaNatural.setShowIndependienteTra(false);
		}

	}

	public void seleccionarPaisFN() {
		System.out
				.println("en listener, pais seleccionado:" + personaNatural.getCiudadNacimiento().getPais().getPais());

		Ciudad ciudad = new Ciudad();
		ciudad.setPais(personaNatural.getCiudadNacimiento().getPais());
		personaNatural.setCiudadNacimiento(ciudad);

		System.out.println("en listener, ciduad reseteada:" + personaNatural.getCiudadNacimiento().getCiudad());
	}

	/**
	 * parroquia fecha de nacimiento
	 * 
	 * @param pref
	 * @return
	 */
	public List<Ciudad> autocompleteCiudadPN(String pref) {
		List<Ciudad> resp = null;

		Pais pais = this.personaNatural.getCiudadNacimiento().getPais();
		if (pais != null) {
			Short codPais = pais.getCodPais();

			System.out.println("auto Ciudad--------------------codPais: " + codPais);

			if (codPais != null) {
				resp = buscarCiudades(pref, codPais);
			}
		}

		return resp;
	}

	/**
	 * @return the tipoIdentificacionPNItems
	 */
	public List<SelectItem> getTipoIdentificacionPNItems() {
		if (tipoIdentificacionPNItems == null) {
			tipoIdentificacionPNItems = new ArrayList<SelectItem>();

			List<TipoIdentificacion> tmpList = tipoIdentificacionServicio.findAll();

			for (TipoIdentificacion tipoIdentificacion : tmpList) {
				if (TipoIdentificacionEnum
						.isParaIngresoPersonaNatural(tipoIdentificacion.getCodTipoIdentificacion().charValue())) {
					SelectItem item = new SelectItem(tipoIdentificacion.getCodTipoIdentificacion(),
							tipoIdentificacion.getTipoIdentificacion());
					tipoIdentificacionPNItems.add(item);
				}
			}
		}
		return tipoIdentificacionPNItems;
	}

	/**
	 * @param tipoIdentificacionPNItems the tipoIdentificacionPNItems to set
	 */
	public void setTipoIdentificacionPNItems(List<SelectItem> tipoIdentificacionPNItems) {
		this.tipoIdentificacionPNItems = tipoIdentificacionPNItems;
	}

	/**
	 * @return the personaNatural
	 */
	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	/**
	 * @param personaNatural the personaNatural to set
	 */
	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
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

	/**
	 * @return the muestraRcsPopUp
	 */
	public boolean isMuestraRcsPopUp() {
		return muestraRcsPopUp;
	}

	/**
	 * @param muestraRcsPopUp the muestraRcsPopUp to set
	 */
	public void setMuestraRcsPopUp(boolean muestraRcsPopUp) {
		this.muestraRcsPopUp = muestraRcsPopUp;
	}

	/**
	 * @return the habilitarBtnCancelar
	 */
	public boolean isHabilitarBtnCancelar() {
		return habilitarBtnCancelar;
	}

	/**
	 * @param habilitarBtnCancelar the habilitarBtnCancelar to set
	 */
	public void setHabilitarBtnCancelar(boolean habilitarBtnCancelar) {
		this.habilitarBtnCancelar = habilitarBtnCancelar;
	}

	/**
	 * @return the idSise
	 */
	public String getIdSise() {
		return idSise;
	}

	/**
	 * @param idSise the idSise to set
	 */
	public void setIdSise(String idSise) {
		this.idSise = idSise;
	}

	public SesionCtrl getSesionCtrl() {
		return sesionCtrl;
	}

	public void setSesionCtrl(SesionCtrl sesionCtrl) {
		this.sesionCtrl = sesionCtrl;
	}

	public String getConyugeDelConyuge() {
		return conyugeDelConyuge;
	}

	public void setConyugeDelConyuge(String conyugeDelConyuge) {
		this.conyugeDelConyuge = conyugeDelConyuge;
	}

}
