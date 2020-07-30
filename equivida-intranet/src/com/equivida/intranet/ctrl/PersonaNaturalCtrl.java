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
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.xml.rpc.ServiceException;

import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONException;
import org.richfaces.json.JSONObject;

import com.equivida.constant.AntiguedadEnum;
import com.equivida.constant.Constantes;
import com.equivida.constant.EmpresaEnum;
import com.equivida.constant.EncontradoBeneficiarioEnum;
import com.equivida.constant.EstadoCivilEnum;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.GanadoPerdidoEnum;
import com.equivida.constant.PerfilEquividaEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.SexoEnum;
import com.equivida.constant.TipoDeportePracticaEnum;
import com.equivida.constant.TipoEmpleoEnum;
import com.equivida.constant.TipoFormularioEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.crm.ws.CrmWs;
import com.equivida.dto.EmpleoDto;
import com.equivida.dto.GeocodeAddressLW;
import com.equivida.dto.MensajeRcsDto;
import com.equivida.dto.RespuestaGeneralDto;
import com.equivida.dto.RespuestaSiseDto;
import com.equivida.dto.ResultadoWebserviceNombresDto;
import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.homologacion.servicio.PersonaEquividaServicio;
import com.equivida.intranet.util.BasePersonaCtrl;
import com.equivida.intranet.util.DateUtils;
import com.equivida.mensajeria.AsyncEmailServicio;
import com.equivida.mensajeria.EmailServicio;
import com.equivida.modelo.Actividad;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Beneficiario;
import com.equivida.modelo.BeneficiarioPoliza;
import com.equivida.modelo.Biometrica;
import com.equivida.modelo.Canton;
import com.equivida.modelo.CategoriaPpe;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.CompaniaSeguro;
import com.equivida.modelo.Deporte;
import com.equivida.modelo.DeportePractica;
import com.equivida.modelo.DetalleActividadFuncion;
import com.equivida.modelo.DetalleHabitoEnfermedad;
import com.equivida.modelo.DetallePasaporte;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.EmpleoDependiente;
import com.equivida.modelo.EmpleoIndependiente;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.EstadoPersona;
import com.equivida.modelo.HabitoEnfermedad;
import com.equivida.modelo.Hijo;
import com.equivida.modelo.HistoriaMedicaFamiliar;
import com.equivida.modelo.InformacionAdicional;
import com.equivida.modelo.IngresoAnual;
import com.equivida.modelo.IngresoMensual;
import com.equivida.modelo.IngresoMensualAdicional;
import com.equivida.modelo.InstitucionFinanciera;
import com.equivida.modelo.MotivoSeguro;
import com.equivida.modelo.Ocupacion;
import com.equivida.modelo.OtraOcupacion;
import com.equivida.modelo.OtroEmpleo;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Parroquia;
import com.equivida.modelo.PerfilFinancieroNatural;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaComponenteExclusion;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.PersonaPe;
import com.equivida.modelo.PreguntaHabitoEnfermedad;
import com.equivida.modelo.Profesion;
import com.equivida.modelo.Provincia;
import com.equivida.modelo.RamoSeguro;
import com.equivida.modelo.Referencia;
import com.equivida.modelo.ReferenciaBancaria;
import com.equivida.modelo.ReferenciaComercial;
import com.equivida.modelo.SeguroAdicional;
import com.equivida.modelo.SeguroVigente;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoActividad;
import com.equivida.modelo.TipoContactoPreferido;
import com.equivida.modelo.TipoDireccionElectronica;
import com.equivida.modelo.TipoEstado;
import com.equivida.modelo.TipoHabitoEnfermedad;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoInstitucionFinanciera;
import com.equivida.modelo.TipoOtraOcupacion;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.modelo.TipoRiesgo;
import com.equivida.modelo.TipoServicioInstFin;
import com.equivida.modelo.TipoVisa;
import com.equivida.servicio.ActividadEconomicaServicio;
import com.equivida.servicio.BeneficiarioServicio;
import com.equivida.servicio.ConsultaGeneralCUServicio;
import com.equivida.servicio.DetalleHabitoEnfermedadServicio;
import com.equivida.servicio.HabitoEnfermedadServicio;
import com.equivida.servicio.InstitucionFinancieraServicio;
import com.equivida.servicio.PersistenciaGeneralServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.PreguntaHabitoEnfermedadServicio;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.SiseServicio;
import com.equivida.servicio.TipoActividadServicio;
import com.equivida.servicio.TipoHabitoEnfermedadServicio;
import com.equivida.servicio.TipoOtraOcupacionServicio;
import com.equivida.servicio.TipoOtroSeguroServicio;
import com.equivida.servicio.TipoParentescoRelacionServicio;
import com.equivida.servicio.TipoServicioInstFinServicio;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.equivida.sise.ws.client.RsConductoPago;
import com.equivida.sise.ws.client.RsCumulosDePago;
import com.equivida.util.CedulaValidator;
import com.equivida.util.CrmUtil;
import com.equivida.util.EmailUtils;
import com.equivida.util.EquivalenciaKilosLibras;
import com.equivida.util.LWResources;
import com.equivida.util.MailMessage;
import com.equivida.util.ValidatorUtils;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.util.Criteria;

@ManagedBean(name = "personaNaturalCtrl")
@ViewScoped
public class PersonaNaturalCtrl extends BasePersonaCtrl {

	private static final long serialVersionUID = 8016098231826708483L;

	@EJB(mappedName = "HabitoEnfermedadServicio/local")
	private HabitoEnfermedadServicio habitoEnfermedadServicio;

	@EJB(mappedName = "TipoServicioInstFinServicio/local")
	private TipoServicioInstFinServicio tipoServicioInstFinServicio;

	@EJB(mappedName = "PersonaEquividaServicio/local")
	private PersonaEquividaServicio personaEquividaServicio;

	@EJB(mappedName = "SiseServicio/local")
	private SiseServicio siseServicio;

	@EJB(mappedName = "DetalleHabitoEnfermedadServicio/local")
	private DetalleHabitoEnfermedadServicio detalleHabitoEnfermedadServicio;

	@EJB(mappedName = "TipoHabitoEnfermedadServicio/local")
	private TipoHabitoEnfermedadServicio tipoHabitoEnfermedadServicio;

	@EJB(mappedName = "PersonaNaturalServicio/local")
	private PersonaNaturalServicio personaNaturalServicio;

	@EJB(mappedName = "TipoActividadServicio/local")
	private TipoActividadServicio tipoActividadServicio;

	@EJB(mappedName = "WsDatosPersonaServicio/local")
	private WsDatosPersonaServicio wsDatosPersonaServicio;

	@EJB(mappedName = "BeneficiarioServicio/local")
	private BeneficiarioServicio beneficiarioServicio;

	@EJB(mappedName = "PreguntaHabitoEnfermedadServicio/local")
	private PreguntaHabitoEnfermedadServicio preguntaHabitoEnfermedadServicio;

	@EJB(mappedName = "TipoOtroSeguroServicio/local")
	private TipoOtroSeguroServicio tipoOtroSeguroServicio;

	@EJB(mappedName = "TipoParentescoRelacionServicio/local")
	private TipoParentescoRelacionServicio tipoParentescoRelacionServicio;

	@EJB(mappedName = "TipoOtraOcupacionServicio/local")
	private TipoOtraOcupacionServicio tipoOtraOcupacionServicio;

	@EJB(mappedName = "InstitucionFinancieraServicio/local")
	private InstitucionFinancieraServicio institucionFinancieraServicio;

	@EJB(mappedName = "EmailServicio/local")
	private EmailServicio emailServicio;

	@EJB(mappedName = "AsyncEmailServicio/local")
	private AsyncEmailServicio asyncEmailServicio;

	@EJB(mappedName = "RcsServicio/local")
	private RcsServicio rcsServicio;

	@EJB(mappedName = "ActividadEconomicaServicio/local")
	private ActividadEconomicaServicio actividadEconomicaServicio;

	@EJB(mappedName = "CrmWs/remote")
	private CrmWs crmWs;

	@EJB(mappedName = "ConsultaGeneralCUServicio/local")
	private ConsultaGeneralCUServicio consultaGeneralCUServicio;

	@ManagedProperty("#{ciudadLWAutocompleteCtrl}")
	private CiudadLWAutocompleteCtrl ciudadLWAutocompleteCtrl;

	@EJB(mappedName = "PersistenciaGeneralServicio/local")
	private PersistenciaGeneralServicio persistenciaGeneralServicio;

	@ManagedProperty("#{sesionCtrl}")
	private SesionCtrl sesionCtrl;

	private Collection<HabitoEnfermedad> habitoEnfermedadPorPersonaLista;

	private Integer paramSecPersonaNatural;

	private Boolean nuevo;

	private boolean habilitarBtnGuardar = true;

	private boolean yaExiste = false;

	private boolean mostrarPanelNombreInvalido;

	private boolean mostrarPanelNombreSugerencia;

	private ResultadoWebserviceNombresDto resultado;

	private List<RsCumulosDePago> consultaCumulosLista = null;

	private double totalMontoMyda = 0.0;
	private double totalMontoVida = 0.0;

	private RespuestaGeneralDto respuestaDto;

	private boolean verificadoListasPersona = false;

	private boolean verificadoListasConyuge = false;

	private short estadoCivilIdInicial = Constantes.ESTADO_CIVIL_ID_SOLTERO;

	public double getTotalMontoMyda() {
		return totalMontoMyda;
	}

	public void setTotalMontoMyda(double totalMontoMyda) {
		this.totalMontoMyda = totalMontoMyda;
	}

	public double getTotalMontoVida() {
		return totalMontoVida;
	}

	public void setTotalMontoVida(double totalMontoVida) {
		this.totalMontoVida = totalMontoVida;
	}

	private String verificarNombresDe = "P"; // puede ser P (Persona) o C
												// (Conyuge) o B (Beneficiario)

	private BeneficiarioPoliza beneficiarioPoliza;// al que se le valida los
													// nombres, para que en el
													// popup saber a cual se
													// esta aceptando la
													// sugerencia

	private List<RsConductoPago> conductoDePagoLista;

	private String conyugeDelConyuge = "";// nombre del conyuge registrado

	private boolean sexoAdvertencia = false;

	private Short tipoEmpleoNuevo;

	public void seleccionarOcupacionHijo() {

		Hijo h = (Hijo) getExternalContext().getRequestMap().get("h");

		String lnn = h.getOcupacion().getOcupacion();

		if (lnn != null && !lnn.isEmpty()) {

			String[] arr = lnn.split("\\|");
			h.getOcupacion().setCodOcupacion(Short.parseShort(arr[0]));
			h.getOcupacion().setOcupacion(arr[1]);
			System.out.println("ocupacion id:" + arr[0]);
			System.out.println("ocupacion:" + arr[1]);
		} else {

			Ocupacion o = new Ocupacion(new Short((short) 0));
			o.setOcupacion("No disponible");
		}
	}

	public void cerrarPopDataBook(ActionEvent event) {
		setMuestraPopUp(false);
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

	public void ponerTipoInstitucion(AjaxBehaviorEvent event) {
		ReferenciaBancaria rb = (ReferenciaBancaria) getExternalContext().getRequestMap().get("refb");
		Short secInst = rb.getInstitucionFinanciera().getSecInstitucionFinanciera();
		if (secInst != -1) {
			InstitucionFinanciera ifi = institucionFinancieraServicio.findByPk(secInst);
			rb.setInstitucionFinanciera(ifi);
			// carga los posibles tipos
			rb.setTipoServicioInstFinServicioLista(tipoServicioInstFinServicio
					.obtenerPorTipoFinanciera(ifi.getTipoInstitucionFinanciera().getSecTipoFinanciera()));

		}

	}

	public void seleccionaSexo(AjaxBehaviorEvent event) {
		sexoAdvertencia = false;
		if (resultado != null && resultado.getSexoSugerido() != null) {
			String sexoSeleccionado = personaNatural.getSexo() + "";
			System.out.println("sexo sel:" + sexoSeleccionado);
			if (!sexoSeleccionado.equals(resultado.getSexoSugerido())) {
				sexoAdvertencia = true;
			}
		}

	}

	public String getFocusPopupNombres() {
		String focus = "SSIPNForm:autoPaisFNInput";

		if (verificarNombresDe.equals("C")) {
			focus = "SSIPNForm:txtNumHijos";
		}

		if (verificarNombresDe.equals("B")) {
			focus = "SSIPNForm:btnBeneficiarioPoliza";
		}

		return focus;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		System.out.println("setPersonaNatural");
		this.personaNatural = personaNatural;
	}

	public List<String> getErrores() {
		List<String> errores = new ArrayList<String>();
		RespuestaSiseDto dto = (RespuestaSiseDto) getSession().getAttribute("dto");
		if (dto != null) {
			errores = dto.getErrorLista();
		}
		return errores;
	}

	public PersonaNatural getPersonaNatural() {
		if (this.personaNatural == null) {
			conductoDePagoLista = null;

			// si es nuevo
			if (getNuevo()) {

				System.out.println("Es persona nueva");
				personaNatural = new PersonaNatural();

				personaNatural.setRespuestaPep(RespuestaEnum.NO.getCodigo() + "");

				personaNatural.setSexo(SexoEnum.MASCULINO.getCodigo());
				EstadoCivil estadoCivil = new EstadoCivil(EstadoCivilEnum.SOLTERO.getCodigo());
				personaNatural.setEstadoCivil(estadoCivil);

				iniciarConyuge(personaNatural);

				personaNatural.setEmpleoCollection(new ArrayList<EmpleoDto>());

				personaNatural.setOtroEmpleoCollection(new ArrayList<OtroEmpleo>());

				personaNatural.setTipoRiesgo(new TipoRiesgo());

				// actividades

				List<TipoActividad> tipoActividadLista = tipoActividadServicio.findAll();

				List<Actividad> actividadLista = new ArrayList<Actividad>();

				for (TipoActividad tipoActividad : tipoActividadLista) {
					Actividad act = new Actividad();
					act.setPersonaNatural(personaNatural);
					act.setSeleccionado(false);
					act.setTipoActividad(tipoActividad);
					actividadLista.add(act);
				}

				System.out.println("actividad persona:" + actividadLista.size());

				personaNatural.setActividadFormularioCollection(actividadLista);

				// deportes
				personaNatural.setDeportePracticaCollection(new ArrayList<DeportePractica>());

				personaNatural.setSegurosVigentesCollection(new ArrayList<SeguroVigente>());

				// perfil financiero tmp
				PerfilFinancieroNatural perfilFinancieroNatural = new PerfilFinancieroNatural();
				perfilFinancieroNatural.setMntActivos(BigDecimal.ZERO);
				perfilFinancieroNatural.setMntPasivos(BigDecimal.ZERO);

				personaNatural.setPerfilFinancieroNatural(perfilFinancieroNatural);

				personaNatural.setIngresoMensualAdicionalCollection(new ArrayList<IngresoMensualAdicional>());

				int anioActual = DateUtils.getCurrentYearInt();
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

				personaNatural.setPersonaPeCollection(new ArrayList<PersonaPe>());

				InformacionAdicional informacionAdicional = new InformacionAdicional();
				informacionAdicional.setConduceMoto(RespuestaEnum.NO.getCodigo());
				informacionAdicional.setPiloto(RespuestaEnum.NO.getCodigo());
				informacionAdicional.setRespuestaSN(RespuestaEnum.NO.getCodigo());
				informacionAdicional.setPersonaNatural(personaNatural);
				personaNatural.setInformacionAdicional(informacionAdicional);

				personaNatural.setProfesion(new Profesion());

				Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);

				Provincia provincia = new Provincia();
				provincia.setPais(pais);
				Ciudad ciudad = new Ciudad();
				ciudad.setPais(pais);
				Canton canton = new Canton();
				canton.setProvincia(provincia);
				Parroquia parroquia = new Parroquia();
				parroquia.setCanton(canton);

				TipoIdentificacion tipoIdentificacion = new TipoIdentificacion(Constantes.TIPO_IDENTIFICACION_CEDULA);

				// ingreso mensual
				IngresoMensual ingresoMensual = new IngresoMensual();
				ingresoMensual.setMntIngresoMensual(new BigDecimal(0));
				ingresoMensual.setMntEgresoMensual(new BigDecimal(0));
				ingresoMensual.setPersonaNatural(personaNatural);
				personaNatural.setIngresoMensual(ingresoMensual);

				// Detalle funciones
				DetalleActividadFuncion detalleActividadFuncion = new DetalleActividadFuncion();
				// detalleActividadFuncion.setPersonaNatural(personaNatural);
				personaNatural.setDetalleActividadFuncion(detalleActividadFuncion);

				// Detalle pasaporte
				DetallePasaporte detallePasaporte = new DetallePasaporte();
				detallePasaporte.setTipoVisa(new TipoVisa());
				personaNatural.setDetallePasaporte(detallePasaporte);

				// Referencias personales
				Collection<Referencia> referenciaLista = new ArrayList<Referencia>();

				// Referencia referenciaFamQueNoVivaConUd = new Referencia();
				// TipoParentescoRelacion familiar = new
				// TipoParentescoRelacion();
				// familiar.setFlgFamiliar(Constantes.TIPO_PARENTEZCO_FAMILIAR);
				// referenciaFamQueNoVivaConUd.setPersonaNatural(personaNatural);
				// referenciaFamQueNoVivaConUd.setTipoParentescoRelacion(familiar);
				// referenciaFamQueNoVivaConUd
				// .setFamiliarNoVivaUd(RespuestaEnum.SI.getCodigo());
				// referenciaLista.add(referenciaFamQueNoVivaConUd);

				Referencia referenciaUno = new Referencia();
				TipoParentescoRelacion noFamiliar1 = new TipoParentescoRelacion();
				// noFamiliar.setFlgFamiliar(Constantes.TIPO_PARENTEZCO_NO_FAMILIAR);
				referenciaUno.setPersonaNatural(personaNatural);
				referenciaUno.setTipoParentescoRelacion(noFamiliar1);
				referenciaUno.setFamiliarNoVivaUd(RespuestaEnum.NO.getCodigo());
				referenciaLista.add(referenciaUno);

				Referencia referenciaDos = new Referencia();
				TipoParentescoRelacion noFamiliar2 = new TipoParentescoRelacion();
				// noFamiliar.setFlgFamiliar(Constantes.TIPO_PARENTEZCO_NO_FAMILIAR);
				referenciaDos.setPersonaNatural(personaNatural);
				referenciaDos.setTipoParentescoRelacion(noFamiliar2);
				referenciaDos.setFamiliarNoVivaUd(RespuestaEnum.NO.getCodigo());
				referenciaLista.add(referenciaDos);

				personaNatural.setReferenciaFormularioCollection(referenciaLista);

				// Referencias comerciales
				Collection<ReferenciaComercial> referenciaComercialLista = new ArrayList<ReferenciaComercial>();
				ReferenciaComercial referenciaComercialUno = new ReferenciaComercial();
				referenciaComercialUno.setPersonaNatural(personaNatural);
				referenciaComercialLista.add(referenciaComercialUno);

				ReferenciaComercial referenciaComercialDos = new ReferenciaComercial();
				referenciaComercialDos.setPersonaNatural(personaNatural);
				referenciaComercialLista.add(referenciaComercialDos);

				personaNatural.setReferenciaComercialFormularioCollection(referenciaComercialLista);

				// Referencias bancarias
				Collection<ReferenciaBancaria> referenciaBancariaLista = new ArrayList<ReferenciaBancaria>();
				ReferenciaBancaria referenciaBancariaUno = new ReferenciaBancaria();
				referenciaBancariaUno.setPersonaNatural(personaNatural);
				referenciaBancariaUno
						.setInstitucionFinanciera(new InstitucionFinanciera(new TipoInstitucionFinanciera()));
				referenciaBancariaUno.setTipoServicioInstFin(new TipoServicioInstFin());
				referenciaBancariaLista.add(referenciaBancariaUno);

				ReferenciaBancaria referenciaBancariaDos = new ReferenciaBancaria();
				referenciaBancariaDos.setPersonaNatural(personaNatural);
				referenciaBancariaDos
						.setInstitucionFinanciera(new InstitucionFinanciera(new TipoInstitucionFinanciera()));
				referenciaBancariaDos.setTipoServicioInstFin(new TipoServicioInstFin());
				referenciaBancariaLista.add(referenciaBancariaDos);

				personaNatural.setReferenciaBancariaFormularioCollection(referenciaBancariaLista);

				Ocupacion ocupacion = new Ocupacion();
				ocupacion.setTipoRiesgo(new TipoRiesgo());
				Profesion profesion = new Profesion();

				personaNatural.setPaisNacionalidad(pais);
				personaNatural.setTipoIdentificacion(tipoIdentificacion);
				// Calendar hoyHace18 = Calendar.getInstance();
				// hoyHace18.add(Calendar.YEAR, -18);
				// opersonaNatural.setFchNacimiento(hoyHace18.getTime());

				personaNatural.setCiudadNacimiento(ciudad);
				personaNatural.setProfesion(profesion);
				personaNatural.setOcupacion(ocupacion);

				// biometrica
				Biometrica biometrica = new Biometrica();
				// biometrica.setPersonaNatural(personaNatural);
				biometrica.setGanadoPerdido(GanadoPerdidoEnum.GANADO.getCodigo());
				personaNatural.setBiometrica(biometrica);

				// historia medica familiar
				HistoriaMedicaFamiliar padre = new HistoriaMedicaFamiliar();
				TipoParentescoRelacion tipoPadre = tipoParentescoRelacionServicio
						.findByPk(Constantes.TIPO_RELACION_PADRE);
				padre.setPersonaNatural(personaNatural);
				padre.setDetalles("");
				padre.setTipoParentescoRelacion(tipoPadre);

				HistoriaMedicaFamiliar madre = new HistoriaMedicaFamiliar();
				TipoParentescoRelacion tipoMadre = tipoParentescoRelacionServicio
						.findByPk(Constantes.TIPO_RELACION_MADRE);
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

				// estado persona
				personaNatural.setEstadoPersonaCollection(new ArrayList<EstadoPersona>());

				personaNatural.setHistoriaMedicaFamiliarFormularioCollection(listaHistoriaMF);

				// inicia datos para persona
				initPersonaParaFormulario(tipoIdentificacion);

				// personaNatural.setPersona(persona);
			} else {
				System.out.println("Es persona ANTIGUA");
				// cuando es edicion, es decir hay parametro
				// 'paramSecPersonaNatural'
				personaNatural = personaNaturalServicio.findByPkParaFormularioWeb(paramSecPersonaNatural);
				// personaNatural.setConDatosCompletos(true);

				iniciarParaFormularioWeb();

			} // fin edicion

			// setea extranejro

			if (personaNatural.getDetallePasaporte() != null
					&& personaNatural.getDetallePasaporte().getSecDetallePasaporte() != null) {
				personaNatural.setCasillaExtranjero(true);
			} else {
				personaNatural.setCasillaExtranjero(false);
			}

			// guarda estado civil oroginal
			estadoCivilIdInicial = personaNatural.getEstadoCivil().getCodEstadoCivil();

		}
		return personaNatural;
	}

	private void iniciarParaFormularioWeb() {
		// inicia datos que pueden ser nulos (como cuando es conyuge)
		// iniciar estado civil
		if (personaNatural.getEstadoCivil() == null) {
			EstadoCivil estadoCivil = new EstadoCivil(EstadoCivilEnum.SOLTERO.getCodigo());
			personaNatural.setEstadoCivil(estadoCivil);
		}

		// iniciar informacion adicional
		if (personaNatural.getInformacionAdicional() == null) {
			InformacionAdicional informacionAdicional = new InformacionAdicional();
			informacionAdicional.setPersonaNatural(personaNatural);
			personaNatural.setInformacionAdicional(informacionAdicional);
		}

		// iniciar biometrica
		if (personaNatural.getBiometrica() == null) {
			Biometrica biometrica = new Biometrica();
			// biometrica.setPersonaNatural(personaNatural);
			biometrica.setGanadoPerdido(GanadoPerdidoEnum.GANADO.getCodigo());
			personaNatural.setBiometrica(biometrica);
		}

		// detalle actividad funcion
		if (personaNatural.getDetalleActividadFuncion() == null) {
			DetalleActividadFuncion detalleActividadFuncion = new DetalleActividadFuncion();
			// detalleActividadFuncion
			// .setPersonaNatural(personaNatural);
			personaNatural.setDetalleActividadFuncion(detalleActividadFuncion);
		}

		// detalle pasaporte
		if (personaNatural.getDetallePasaporte() == null) {
			DetallePasaporte detallePasaporte = new DetallePasaporte();
			detallePasaporte.setTipoVisa(new TipoVisa());
			personaNatural.setDetallePasaporte(detallePasaporte);
		}

		if (personaNatural.getIngresoMensual() == null) {
			// ingreso mensual
			IngresoMensual ingresoMensual = new IngresoMensual();
			ingresoMensual.setMntIngresoMensual(new BigDecimal(0));
			ingresoMensual.setMntEgresoMensual(new BigDecimal(0));
			ingresoMensual.setPersonaNatural(personaNatural);
			personaNatural.setIngresoMensual(ingresoMensual);
		}
		// perfil financioero
		if (personaNatural.getPerfilFinancieroNatural() == null) {
			// perfil financiero
			PerfilFinancieroNatural perfilFinancieroNatural = new PerfilFinancieroNatural();
			// perfilFinancieroNatural
			// .setPersonaNatural(personaNatural);
			perfilFinancieroNatural.setMntActivos(BigDecimal.ZERO);
			perfilFinancieroNatural.setMntPasivos(BigDecimal.ZERO);
			// perfilFinancieroNatural.setMntInmuebles(BigDecimal.ZERO);
			// perfilFinancieroNatural.setMntDeudas(BigDecimal.ZERO);
			// perfilFinancieroNatural.setMntVehiculos(BigDecimal.ZERO);
			// perfilFinancieroNatural.setMntHipotecas(BigDecimal.ZERO);
			// perfilFinancieroNatural.setMntOtrosActivos(BigDecimal.ZERO);
			// perfilFinancieroNatural.setMntOtrosPasivos(BigDecimal.ZERO);
			personaNatural.setPerfilFinancieroNatural(perfilFinancieroNatural);
		}

		// historia medica familiar
		if (personaNatural.getHistoriaMedicaFamiliarFormularioCollection().size() == 0) {

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
	}

	/**
	 * @return
	 */
	private String getParamSecPersonaString() {
		String paramSecPersonaString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("p");
		return paramSecPersonaString;
	}

	/**
	 * @param paramSecPersonaString
	 * @return
	 */
	public Boolean getNuevo() {

		if (nuevo == null) {

			String paramSecPersonaString = getParamSecPersonaString();

			System.out.println("paramSecPersonaString:" + paramSecPersonaString);

			if (paramSecPersonaString == null || paramSecPersonaString.equals("")) {
				nuevo = true;
			} else {
				paramSecPersonaNatural = Integer.parseInt(paramSecPersonaString);
				nuevo = false;
			}
		}
		return nuevo;
	}

	public void crearEmpleo() {

		if (tipoEmpleoNuevo.shortValue() == -99) {
			String m = "Seleccione un tipo de empleo";
			System.out.println("No hace caso pone seleccione");
			addErrorMessage("SSIPNForm:cmbTipoEmpNuevo", m, m);
			return;
		}

		EmpleoDto empleoDto = new EmpleoDto();
		empleoDto.setEstado(EstadoEnum.ACTIVO.getCodigo());
		empleoDto.setTipoEmpleo(tipoEmpleoNuevo);
		if (tipoEmpleoNuevo == -1 || tipoEmpleoNuevo == -2) {
			TipoEmpleoEnum tipoEmpleoEnum = null;
			if (tipoEmpleoNuevo == -1) {
				tipoEmpleoEnum = TipoEmpleoEnum.DEPENDIENTE;
			} else {
				tipoEmpleoEnum = TipoEmpleoEnum.INDEPENDIENTE;
			}
			empleoDto.setTipoEmpleoNombre(tipoEmpleoEnum.getKeyBundle());
		} else {
			TipoOtraOcupacion tipo = tipoOtraOcupacionServicio.findByPk(tipoEmpleoNuevo);
			empleoDto.setTipoEmpleoNombre(tipo.getOtraOcupacion());
		}
		empleoDto.setCodTiempo(AntiguedadEnum.ANIOS.getCodigo());
		// emple
		personaNatural.getEmpleoCollection().add(empleoDto);
	}

	protected void correo() {
		String usuario = "admin";
		String ip = "";
		String apellido = "";
		String segundoApellido = "";
		String nombres = "";
		String tipoRelacion = "";// persona, conyuge
		boolean nacional = false;
		String listing = "";

		MailMessage mailMessage = emailServicio.prepararCorreoListasReservadas(usuario, ip, apellido, segundoApellido,
				nombres, tipoRelacion, nacional, listing);

		asyncEmailServicio.encolarMail(mailMessage);
	}

	public void crearOtroEmpleo(ActionEvent event) {
		// Se crea nuevo objeto
		OtroEmpleo e = new OtroEmpleo();
		e.setPersonaNatural(personaNatural);
		personaNatural.getOtroEmpleoCollection().add(e);
	}

	// este metodo se debe borrar
	public void crearActividad(ActionEvent event) {
		// Se crea nuevo objeto
		Actividad e = new Actividad();
		e.setEstado(EstadoEnum.ACTIVO.getCodigo());
		e.setPersonaNatural(personaNatural);
		e.setTipoActividad(new TipoActividad());
		personaNatural.getActividadCollection().add(e);
	}

	public void crearOtrosIngresos(ActionEvent event) {
		// Se crea nuevo objeto
		IngresoMensualAdicional e = new IngresoMensualAdicional();
		e.setPersonaNatural(personaNatural);
		e.setMntEgresoAdicional(BigDecimal.ZERO);
		e.setMntIngresoAdicional(BigDecimal.ZERO);
		personaNatural.getIngresoMensualAdicionalCollection().add(e);
	}

	public void crearIngresoAnual(ActionEvent event) {
		// Se crea nuevo objeto
		IngresoAnual e = new IngresoAnual();
		e.setPersonaNatural(personaNatural);
		personaNatural.getIngresoAnualCollection().add(e);
	}

	public void crearPersonaPEP(ActionEvent event) {

		PersonaPe personaPe = new PersonaPe();
		personaPe.setEstado(EstadoEnum.ACTIVO.getCodigo());
		personaPe.setPersonaNatural(personaNatural);
		personaPe.setCategoriaPpe(new CategoriaPpe());
		personaPe.setOrganismoEntidad("");
		personaNatural.getPersonaPeCollection().add(personaPe);

	}

	public void crearBeneficiarioPoliza(ActionEvent event) {
		// Se crea nuevo objeto

		BeneficiarioPoliza beneficiarioPoliza = new BeneficiarioPoliza();
		Beneficiario beneficiario = new Beneficiario();
		beneficiario.setTipoIdentificacion(new TipoIdentificacion(TipoIdentificacionEnum.CEDULA.getCodigo()));
		beneficiarioPoliza.setBeneficiario(beneficiario);
		beneficiarioPoliza.setEncontradoBeneficiarioEnum(EncontradoBeneficiarioEnum.NO_ENCONTRO);
		beneficiarioPoliza.setTipoParentescoRelacion(new TipoParentescoRelacion());
		personaNatural.getPersona().getBeneficiarioPolizaCollection().add(beneficiarioPoliza);
	}

	public void buscarBeneficiarioPorIdentificacion(AjaxBehaviorEvent event) {

		beneficiarioPoliza = (BeneficiarioPoliza) getExternalContext().getRequestMap().get("varBneneficiario");

		if (beneficiarioPoliza.getBeneficiario().getIdentificacion() == null
				|| beneficiarioPoliza.getBeneficiario().getIdentificacion().equals("")) {
			System.out.println("no verifica por identificacion");
			return;
		}

		String numDoc = beneficiarioPoliza.getBeneficiario().getIdentificacion();
		char tipoDoc = beneficiarioPoliza.getBeneficiario().getTipoIdentificacion().getCodTipoIdentificacion();

		boolean documentoValido = true;

		if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
			documentoValido = CedulaValidator.validate(numDoc);
		}

		// verifica
		if (!documentoValido) {
			String mensaje = getBundleMensajes("numero.documento.incorrecto", null);
			addErrorMessage(event.getComponent().getId(), mensaje, mensaje);
			return;
		}

		beneficiarioPoliza.setEncontradoBeneficiarioEnum(EncontradoBeneficiarioEnum.NO_ENCONTRO);

		// buscar en pn
		List<PersonaNatural> listaPN = new ArrayList<PersonaNatural>();

		String[] criteriasAndPN = { "identificacion" };
		CriteriaTypeEnum[] typesAndPN = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] paramsPN = new Object[] { numDoc };
		Criteria criteriaPN = new Criteria(criteriasAndPN, typesAndPN, paramsPN);

		listaPN = personaNaturalServicio.findByCriterias(criteriaPN);

		if (listaPN.size() > 0) {
			// si encuentra
			PersonaNatural encontradoPN = listaPN.get(0);
			beneficiarioPoliza.setEncontradoBeneficiarioEnum(EncontradoBeneficiarioEnum.ENCONTRO_EN_PERSONA_NATURAL);
			beneficiarioPoliza.getBeneficiario().setDenominacion(encontradoPN.getPersona().getDenominacion());
			beneficiarioPoliza.getBeneficiario().setApellidoPaterno(encontradoPN.getApellidoPaterno());
			beneficiarioPoliza.getBeneficiario().setApellidoMaterno(encontradoPN.getApellidoMaterno());
			beneficiarioPoliza.getBeneficiario().setPrimerNombre(encontradoPN.getPrimerNombre());
			beneficiarioPoliza.getBeneficiario().setSegundoNombre(encontradoPN.getSegundoNombre());
			beneficiarioPoliza.getBeneficiario().setTipoIdentificacion(encontradoPN.getTipoIdentificacion());
			beneficiarioPoliza.getBeneficiario().setIdentificacion(encontradoPN.getIdentificacion());

			return;
		}

		// buscar en beneficiario
		List<Beneficiario> listaB = new ArrayList<Beneficiario>();

		String[] criteriasAndB = { "identificacion" };
		CriteriaTypeEnum[] typesAndB = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] paramsB = new Object[] { numDoc };
		Criteria criteriaB = new Criteria(criteriasAndB, typesAndB, paramsB);

		listaB = beneficiarioServicio.findByCriterias(criteriaB);

		if (listaB.size() > 0) {
			// si encuentra
			Beneficiario encontradoB = listaB.get(0);
			beneficiarioPoliza.setEncontradoBeneficiarioEnum(EncontradoBeneficiarioEnum.ENCONTRO_EN_BENEFICIARIO);
			beneficiarioPoliza.setBeneficiario(encontradoB);
			return;
		}
	}

	public void crearSeguroVigente(ActionEvent event) {
		// Se crea nuevo objeto
		SeguroVigente e = new SeguroVigente();
		e.setPersonaNatural(personaNatural);
		e.setCompaniaSeguro(new CompaniaSeguro());
		e.setRamoSeguro(new RamoSeguro());
		e.setEstado(EstadoEnum.ACTIVO.getCodigo());
		personaNatural.getSegurosVigentesCollection().add(e);
	}

	public void eliminarEmpleo(ActionEvent event) {
		EmpleoDto e = (EmpleoDto) getExternalContext().getRequestMap().get("e");
		if (e.getSecEmpleo() == null) {
			personaNatural.getEmpleoCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void eliminarActividad(ActionEvent event) {
		Actividad e = (Actividad) getExternalContext().getRequestMap().get("a");
		personaNatural.getActividadCollection().remove(e);
	}

	public void eliminarOtroIngreso(ActionEvent event) {
		IngresoMensualAdicional e = (IngresoMensualAdicional) getExternalContext().getRequestMap().get("a");
		personaNatural.getIngresoMensualAdicionalCollection().remove(e);
	}

	public void eliminarIngresoAnual(ActionEvent event) {
		IngresoAnual e = (IngresoAnual) getExternalContext().getRequestMap().get("b");
		personaNatural.getIngresoAnualCollection().remove(e);
	}

	public void eliminarPersonaPep(ActionEvent event) {
		PersonaPe e = (PersonaPe) getExternalContext().getRequestMap().get("personaPepVar");
		if (e.getSecPersonaPpe() == null) {
			personaNatural.getPersonaPeCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void eliminarOtroEmpleo(ActionEvent event) {
		OtroEmpleo e = (OtroEmpleo) getExternalContext().getRequestMap().get("b");
		personaNatural.getOtroEmpleoCollection().remove(e);
	}

	public void eliminarSeguroVigente(ActionEvent event) {
		SeguroVigente e = (SeguroVigente) getExternalContext().getRequestMap().get("seguroVigente");
		if (e.getSecSeguro() == null) {
			personaNatural.getSegurosVigentesCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void eliminarBeneficiarioPoliza(ActionEvent event) {
		BeneficiarioPoliza e = (BeneficiarioPoliza) getExternalContext().getRequestMap().get("varBneneficiario");
		personaNatural.getPersona().getBeneficiarioPolizaCollection().remove(e);
	}

	public void seleccionarPaisFN() {
		// String lnn = getPersonaNatural().getCiudadNacimiento().getPais()
		// .getPais();
		// String[] arr = lnn.split("\\|");

		// Pais p = getPersonaNatural().getCiudadNacimiento().getPais();
		// p.setCodPais(Short.parseShort(arr[0]));
		// p.setPais(arr[1]);

		// /if (p.getCodPais().compareTo(Constantes.PAIS_ID_NO_DISPONIBLE) == 0)
		// {
		// getPersonaNatural().setCiudadNacimiento(new Ciudad());//
		// automaticamente
		// se
		// debe poner canton, provincia y pais 0
		// getPersonaNatural().getCiudadNacimiento().setPais(p);
		// } else {

		// se resetea ciudad y pais

		System.out
				.println("en listener, pais seleccionado:" + personaNatural.getCiudadNacimiento().getPais().getPais());

		Ciudad ciudad = new Ciudad();
		ciudad.setPais(personaNatural.getCiudadNacimiento().getPais());
		personaNatural.setCiudadNacimiento(ciudad);

		System.out.println("en listener, ciduad reseteada:" + personaNatural.getCiudadNacimiento().getCiudad());

	}

	public void seleccionarPaisNAC() {
		String lnn = personaNatural.getPaisNacionalidad().getPais();
		String[] arr = lnn.split("\\|");
		personaNatural.getPaisNacionalidad().setCodPais(Short.parseShort(arr[0]));
		personaNatural.getPaisNacionalidad().setPais(arr[1]);

	}

	public void seleccionarSexo() {
		// char sexo = getPersonaNatural().getSexo();
		// habitoEnfermedadPorPersonaLista = null;
	}

	public void seleccionarProfesion() {
		String lnn = personaNatural.getProfesion().getProfesion();
		String[] arr = lnn.split("\\|");
		personaNatural.getProfesion().setSecProfesion(Short.parseShort(arr[0]));
		personaNatural.getProfesion().setProfesion(arr[1]);

		System.out.println("profesion id:" + arr[0]);
		System.out.println("profesion:" + arr[1]);
	}

	public void seleccionarOcupacion() {
		String lnn = personaNatural.getOcupacion().getOcupacion();
		String[] arr = lnn.split("\\|");
		personaNatural.getOcupacion().setCodOcupacion(Short.parseShort(arr[0]));
		personaNatural.getOcupacion().setOcupacion(arr[1]);

		personaNatural.setTipoRiesgo(new TipoRiesgo(Short.parseShort(arr[2]), arr[3].charAt(0)));
		// getPersonaNatural().getTipoRiesgo()
		// .setTipoRiesgo(arr[3].charAt(0));

		System.out.println("ocupacion id:" + arr[0]);
		System.out.println("ocupacion:" + arr[1]);
		System.out.println("ocupacion riesgo:" + arr[2]);
		System.out.println("ocupacion riesgo char:" + arr[3]);
	}

	public void seleccionarPaisD() {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");
		String lnn = e.getCanton().getProvincia().getPais().getPais();
		String[] arr = lnn.split("\\|");

		Pais p = new Pais();
		p.setCodPais(Short.parseShort(arr[0]));
		p.setPais(arr[1]);

		if (p.getCodPais().compareTo(Constantes.PAIS_ID_NO_DISPONIBLE) == 0) {
			System.out.println("todo no diponible");
			e.setCanton(getCantonNoDisponible());// automaticamente se
			// debe poner canton, provincia y pais 0
			e.getCanton().getProvincia().setPais(p);
		} else {
			// se resetea parroquia,canton y pais
			// parroquia
			Provincia provincia = new Provincia();
			provincia.setPais(p);
			Canton canton = new Canton();
			canton.setProvincia(provincia);
			e.setCanton(canton);

			System.out.println("se vacia todo");
		}
	}

	public void seleccionarPaisDT() {
		DireccionTelefono dt = (DireccionTelefono) getExternalContext().getRequestMap().get("dt");
		String lnn = dt.getTelefono().getPais().getPais();
		String[] arr = lnn.split("\\|");

		Pais p = new Pais();
		p.setCodPais(Short.parseShort(arr[0]));
		p.setPais(arr[1]);
		dt.getTelefono().setPais(p);
	}

	public void seleccionarPaisTA() {
		Telefono t = (Telefono) getExternalContext().getRequestMap().get("t");
		String lnn = t.getPais().getPais();
		String[] arr = lnn.split("\\|");

		Pais p = new Pais();
		p.setCodPais(Short.parseShort(arr[0]));
		p.setPais(arr[1]);
		t.setPais(p);
	}

	public void seleccionarAE() {// actividad economica
		EmpleoDto e = (EmpleoDto) getExternalContext().getRequestMap().get("e");
		String lnn = e.getActividadEconomicaNombre();
		String[] arr = lnn.split("\\|");

		e.setActividadEconomicaId(Short.parseShort(arr[0]));
		e.setActividadEconomicaNombre(arr[1]);
	}

	public void seleccionarProvinciaD() {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");
		String lnn = e.getCanton().getProvincia().getProvincia();
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

		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");

		String lnn = e.getCanton().getCanton();
		String[] arr = lnn.split("\\|");
		e.getCanton().setSecCanton(Short.parseShort(arr[0]));
		e.getCanton().setCanton(arr[1]);
	}

	public void seleccionarCiudadFN() {
		String lnn = personaNatural.getCiudadNacimiento().getCiudad();
		String[] arr = lnn.split("\\|");

		personaNatural.getCiudadNacimiento().setSecCiudad(Short.parseShort(arr[0]));
		personaNatural.getCiudadNacimiento().setCiudad(arr[1]);
	}

	public void verificarListasReservadas() {

		// Se valida nombres

		boolean error = false;

		error = ValidatorUtils.validaNombre(personaNatural.getPrimerNombre());
		System.out.println("error nombre 1: " + error);

		if (error) {
			addErrorMessage("SSIPNForm:txtPrimerNombre", getBundleMensajes("valida.nombre", null), null);
		} else {
			error = ValidatorUtils.validaNombre(personaNatural.getApellidoPaterno());
			System.out.println("error ap 1: " + error);

			if (error) {

				addErrorMessage("SSIPNForm:txtApellidoP", getBundleMensajes("valida.nombre", null), null);
			} else {

				if (personaNatural.getSegundoNombre() != null && !personaNatural.getSegundoNombre().isEmpty()) {
					// no se contempla esta validacion
					// error = ValidatorUtils.validaNombre(personaNatural.getSegundoNombre());
					System.out.println("error nombre 2: " + error);
				}

				if (error) {

					addErrorMessage("SSIPNForm:txtSegundoNombre", getBundleMensajes("valida.nombre", null), null);
				} else {

					if (personaNatural.getApellidoMaterno() != null && !personaNatural.getApellidoMaterno().isEmpty()) {
						error = ValidatorUtils.validaNombre(personaNatural.getApellidoMaterno());
						System.out.println("error ap 2: " + error);
					}

					if (error) {

						addErrorMessage("SSIPNForm:txtApellidoM", getBundleMensajes("valida.nombre", null), null);
					}
				}
			}

		}

		System.out.println("error ... : " + error);

		if (!error) {
			verificarListasReservadasSync();
		}

	}

	/**
	 * Realiza el proceso de búsqueda en CU, luego en SD o en DB.
	 */
	private void buscarFlujo() {

		// Si no hay popUps de nombres o sugerencias, se realiza la busqueda de
		// acuerdo a flujo
		if (mostrarPanelNombreInvalido || mostrarPanelNombreSugerencia
				|| (sesionCtrl.getMensajeRcsDto() != null && !sesionCtrl.getMensajeRcsDto().isPuedeContinuar())) {
			return;
		}

		// Se realiza el proceso de busqueda en CU, SD o DB
		// if (getNuevo()) {
		String numDoc = personaNatural.getIdentificacion();

		if (!personaNatural.getTipoIdentificacion().isPasaporte()) {

			String ruc = null;
			String cedula = numDoc;
			if (personaNatural.getTipoIdentificacion().isRuc()) {
				ruc = numDoc;
				cedula = numDoc.substring(0, 10);
			}

			respuestaDto = consultaGeneralCUServicio.consultaGenerica(cedula, getRemoteUser(), ruc);

			if (respuestaDto.isEncuentraCu() || respuestaDto.isEncuentraDb() || respuestaDto.isEncuentraSd()) {

				// 3. Caso 1: SI Encuentra en CU y NO Encuentra en SD
				if (respuestaDto.isEncuentraCu() && !respuestaDto.isEncuentraSd()) {
					setPersonaNatural(respuestaDto.getPersonaNatural());
					if (personaNatural.getConyuge() == null) {
						iniciarConyuge(personaNatural);

					}
				}

				// 4. Caso 2: NO Encuentra en CU y SI Encuentra en SD
				if (!respuestaDto.isEncuentraCu() && respuestaDto.isEncuentraSd()) {
					setPersonaNatural(respuestaDto.getPersonaNatural());
					if (personaNatural.getConyuge() == null) {
						iniciarConyuge(personaNatural);

					}
				}

				// 5. Caso 3: SI encuentra en CU y SI encuentra en Smartdata.
				if (respuestaDto.isEncuentraCu() && respuestaDto.isEncuentraSd()) {
					// Se pone la persona
					setPersonaNatural(respuestaDto.getPersonaNatural());
					if (personaNatural.getConyuge() == null) {
						iniciarConyuge(personaNatural);

					}

					// Se pone la persona popUp
					setPersonaNaturalPopUp(respuestaDto.getPersonaNaturalPopUp());
				}

				if (!respuestaDto.isEncuentraCu() && !respuestaDto.isEncuentraSd() && respuestaDto.isEncuentraDb()) {
					// Se pone la persona popUp
					setPersonaNaturalPopUp(respuestaDto.getPersonaNaturalPopUp());
				}

				// Se pone en variable si se debe presentar o no el popUp.
				setMuestraPopUp(respuestaDto.isMuestraPopUp());

				// Si es nuevo registro en CU
				nuevo = respuestaDto.isNuevoRegistro();

				// Se muestran mensajes extras al usuario
				if (respuestaDto.isHayMensaje()) {
					addInfoMessage(null, respuestaDto.getMensaje(), null);
				}
				// pone ruc si es que existiese
				if (respuestaDto.getRucVerificar() != null) {
					personaNatural.setRucVerificarTransient(respuestaDto.getRucVerificar());
				}

			} else {
				addInfoMessage(null, getBundleMensajes("no.encontro.persona", null), null);
			}
			// }

			// TODO verificar guardar
			// INICIALIZAR, esto reusar del servicio
			System.out.println("PERSONA natural:" + personaNatural);
			System.out.println("conyuge:" + personaNatural.getConyuge());
			if (personaNatural.getCiudadNacimiento() == null) {
				personaNatural.setCiudadNacimiento(new Ciudad());
				personaNatural.getCiudadNacimiento().setPais(new Pais());
			}
			if (personaNatural.getSegurosVigentesCollection() == null) {
				personaNatural.setSegurosVigentesCollection(new ArrayList<SeguroVigente>());
			}
			if (personaNatural.getPersona().getDireccionCollection() == null) {
				personaNatural.getPersona().setDireccionCollection(new ArrayList<Direccion>());
			}
			if (personaNatural.getConyuge().getTipoIdentificacion() == null) {
				personaNatural.getConyuge()
						.setTipoIdentificacion(new TipoIdentificacion(TipoIdentificacionEnum.CEDULA.getCodigo()));
			}
			// fin verificar

		} else {// else no es pasaporte
			// busca si el extranjero existe
			PersonaNatural pn = personaNaturalServicio.findByPkParaFormularioWeb(numDoc);
			if (pn != null) {
				personaNatural = pn;
				iniciarParaFormularioWeb();
			}

			// para que se abra el formulario
			sesionCtrl.setMensajeRcsDto(new MensajeRcsDto());

		}
		// fin no es pasaporte

		// guarda estado civil oroginal
		estadoCivilIdInicial = personaNatural.getEstadoCivil().getCodEstadoCivil();

	}

	public synchronized void verificarListasReservadasSync() {

		sesionCtrl.setMensajeRcsDto(null);// para que vuelva a buscar

		String segundoNombre = personaNatural.getSegundoNombre();

		System.out.println("SEGUNDO NOMBRE:" + segundoNombre + ".");

		verificarNombresSync();

		buscarFlujo();

		verificadoListasPersona = true;
	}

	public void verificarNombres(ValueChangeEvent event) {
		verificarNombresSync();
	}

	private synchronized void verificarNombresSync() {

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

	private void verificarRiesgo() {

		System.out.println("verificarRiesgo()");

		if (sesionCtrl.getMensajeRcsDto() == null) {
			sesionCtrl.setMensajeRcsDto(rcsServicio.verificarBloqueo(personaNatural, getRemoteUser(),
					getHttpServletRequest().getRemoteAddr(), getNuevo()));

			System.out.println("MensajeRcsDto:" + sesionCtrl.getMensajeRcsDto());
		}
	}

	private void verificarRiesgoConyuge() {

		System.out.println("verificarRiesgoConyuge()");

		if (sesionCtrl.getMensajeRcsDto() == null) {
			sesionCtrl.setMensajeRcsDto(rcsServicio.verificarBloqueoConyuge(personaNatural, getRemoteUser(),
					getHttpServletRequest().getRemoteAddr(), getNuevo()));

			System.out.println("MensajeRcsDto conyuge:" + sesionCtrl.getMensajeRcsDto());

		}

	}

	public synchronized void verificarNombresConyuge() {

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

	public void aceptarPanelNombreInvalido() {
		System.out.println("acepta nombre invalido:" + verificarNombresDe);
		mostrarPanelNombreInvalido = false;

		if (verificarNombresDe.equals("P")) {
			System.out.println("1111111");
			verificarRiesgo();
			System.out.println("2222222");
			buscarFlujo();
		}

		if (verificarNombresDe.equals("C")) {
			System.out.println("3333333");
			verificarRiesgoConyuge();
		}

		System.out.println("444444 fin");
	}

	public void cancelarPanelNombreInvalido() {
		mostrarPanelNombreInvalido = false;
		if (verificarNombresDe.equals("P")) {
			verificarRiesgo();
			buscarFlujo();
		}

		if (verificarNombresDe.equals("C")) {
			verificarRiesgoConyuge();
		}

	}

	public void aceptarPanelSugerencia() {
		System.out.println("acepta sugenrecia");
		mostrarPanelNombreSugerencia = false;

		if (verificarNombresDe.equals("P")) {
			personaNatural.setPrimerNombre(resultado.getSugerenciaNombre1());
			personaNatural.setSegundoNombre(resultado.getSugerenciaNombre2());
			personaNatural.setApellidoPaterno(resultado.getSugerenciaApellido1());
			personaNatural.setApellidoMaterno(resultado.getSugerenciaApellido2());
			personaNatural.setNombresApellidos(null);
			System.out.println("sugerencia ya aplicada:" + personaNatural.getNombresApellidos());

			verificarRiesgo();
			buscarFlujo();

		}

		if (verificarNombresDe.equals("C")) {
			personaNatural.getConyuge().setPrimerNombre(resultado.getSugerenciaNombre1());
			personaNatural.getConyuge().setSegundoNombre(resultado.getSugerenciaNombre2());
			personaNatural.getConyuge().setApellidoPaterno(resultado.getSugerenciaApellido1());
			personaNatural.getConyuge().setApellidoMaterno(resultado.getSugerenciaApellido2());
			personaNatural.getConyuge().setNombresApellidos(null);
			System.out.println("sugerencia ya aplicada:" + personaNatural.getConyuge().getNombresApellidos());
			verificarRiesgoConyuge();
		}

		if (verificarNombresDe.equals("B")) {
			beneficiarioPoliza.getBeneficiario().setPrimerNombre(resultado.getSugerenciaNombre1());
			beneficiarioPoliza.getBeneficiario().setSegundoNombre(resultado.getSugerenciaNombre2());
			beneficiarioPoliza.getBeneficiario().setApellidoPaterno(resultado.getSugerenciaApellido1());
			beneficiarioPoliza.getBeneficiario().setApellidoMaterno(resultado.getSugerenciaApellido2());
			beneficiarioPoliza.getBeneficiario().setNombresApellidos(null);
			System.out.println("sugerencia ya aplicada:" + beneficiarioPoliza.getBeneficiario().getNombresApellidos());
		}

	}

	public void cambiarEstadoCivil(ValueChangeEvent event) throws AbortProcessingException {

		Short valorNuevo = (Short) event.getNewValue();

		if (estadoCivilIdInicial != 0 && estadoCivilIdInicial != Constantes.ESTADO_CIVIL_ID_SOLTERO) {
			if (valorNuevo != null && valorNuevo.shortValue() == Constantes.ESTADO_CIVIL_ID_SOLTERO) {
				String error = "No se puede cambiar a estado civil Soltero.";
				addErrorMessage(event.getComponent().getClientId(), error, error);
				throw new AbortProcessingException(error);
			}
		}
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

	public void cancelarPanelSugerencia() {
		mostrarPanelNombreSugerencia = false;
		if (verificarNombresDe.equals("P")) {
			verificarRiesgo();
			buscarFlujo();
		}

		if (verificarNombresDe.equals("C")) {
			verificarRiesgoConyuge();
		}
	}

	public void verificarSiExisteConyuge() {
		if (this.personaNatural != null) {
			System.out.println("borra nombres apellidos conyuge");
			personaNatural.getConyuge().setPrimerNombre("");
			personaNatural.getConyuge().setSegundoNombre("");
			personaNatural.getConyuge().setApellidoPaterno("");
			personaNatural.getConyuge().setApellidoMaterno("");

			String numDoc = personaNatural.getConyuge().getIdentificacion();
			char tipoDoc = personaNatural.getConyuge().getTipoIdentificacion().getCodTipoIdentificacion();

			boolean documentoValido = true;

			if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
				documentoValido = CedulaValidator.validate(numDoc);
			}

			// verifica
			if (!documentoValido) {
				String mensaje = getBundleMensajes("numero.documento.incorrecto", null);
				addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
				return;
			}

			if (numDoc.equals(personaNatural.getIdentificacion())) {
				String mensaje = getBundleMensajes("identificacion.igual.persona.conyuge", null);
				addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
				return;
			}

			String[] criteriasAnd = { "identificacion" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
			Object[] valuesCriteriaAnd = { numDoc };

			Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

			verificarListaExistentesConyuge(criteria);

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

	public void verificarSiExiste() {
		yaExiste = false;
		String numDoc = personaNatural.getIdentificacion();
		char tipoDoc = personaNatural.getTipoIdentificacion().getCodTipoIdentificacion();

		boolean documentoValido = true;

		if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
			documentoValido = CedulaValidator.validate(numDoc);
		}

		// verifica
		if (!documentoValido) {
			String mensaje = getBundleMensajes("numero.documento.incorrecto", null);
			addErrorMessage("SSIPNForm:txtId", mensaje, mensaje);
			return;
		}

		// verifica si ya existe
		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { numDoc };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		if (getNuevo()) {

			verificarListaExistentes(criteria);

		}

		if (yaExiste) {
			if (paramSecPersonaNatural != null) {
				// entonces existe en persona natural

				String pagina = "/pages/formularios/personaNatural.jsf?faces-redirect=true&p=" + paramSecPersonaNatural;
				System.out.println("se ha encontrado en personanatural...");
				if (getSesionCtrl().getEmpresa().isGlobal()) {
					pagina = "/pages/formularios/personaNaturalColvida.jsf?faces-redirect=true&p="
							+ paramSecPersonaNatural;
				}

				getFacesContext().getApplication().getNavigationHandler().handleNavigation(getFacesContext(), null,
						pagina);

			} else {
				// entonces encontro en beneficiario
				// no hace nada porque no esta creado en personanatural, que
				// siga ingresando los datos
			}
		}

		// se verifica riesgo por si es nuevo o modificacion y aparesca el
		// mensaje de bloqueo
		// verificarRiesgo();

		// if (!sesionCtrl.getMensajeRcsDto().isPuedeContinuar()) {
		// return;
		// }

		// Se comenta por cambio de orden de ejecucion de flujo de consulta
		/*
		 * if (getNuevo()) {
		 * 
		 * respuestaDto = consultaGeneralCUServicio.consultaGenerica(numDoc,
		 * getRemoteUser());
		 * 
		 * if (respuestaDto.isEncuentraCu() || respuestaDto.isEncuentraDb() ||
		 * respuestaDto.isEncuentraSd()) { if (respuestaDto.isEncuentraCu()) {
		 * setPersonaNatural(respuestaDto.getPersonaNatural()); if
		 * (personaNatural.getConyuge() == null) { PersonaNatural conyuge = new
		 * PersonaNatural(); conyuge.setPersona(new Persona());
		 * conyuge.setTipoIdentificacion(new TipoIdentificacion(
		 * TipoIdentificacionEnum.CEDULA.getCodigo()));
		 * personaNatural.setConyuge(conyuge);
		 * 
		 * } nuevo = false; }
		 * 
		 * if (respuestaDto.isEncuentraDb() || respuestaDto.isEncuentraSd()) {
		 * setPersonaNaturalPopUp(respuestaDto .getPersonaNaturalPopUp()); }
		 * 
		 * // Se pone en variable si se debe presentar o no el popUp.
		 * setMuestraPopUp(respuestaDto.isMuestraPopUp());
		 * 
		 * // Se muestran mensajes extras al usuario if (respuestaDto.isHayMensaje()) {
		 * addInfoMessage(null, respuestaDto.getMensaje(), null); } } else {
		 * addInfoMessage(null, getBundleMensajes("no.encontro.persona", null), null); }
		 * } else { // Cuando no es nuevo no se hace nada }
		 */
	}

	@Deprecated
	public void verificarSiExiste_Old() {
		yaExiste = false;
		String numDoc = getPersonaNatural().getIdentificacion();
		char tipoDoc = getPersonaNatural().getTipoIdentificacion().getCodTipoIdentificacion();

		boolean documentoValido = true;

		if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
			documentoValido = CedulaValidator.validate(numDoc);
		}

		// verifica
		if (!documentoValido) {
			String mensaje = getBundleMensajes("numero.documento.incorrecto", null);
			addErrorMessage("SSIPNForm:txtId", mensaje, mensaje);
			return;
		}

		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { numDoc };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		if (getNuevo()) {

			verificarListaExistentes(criteria);

		} else {
			// modificacion
			System.out.println("nuevo:" + numDoc);
			System.out.println("orig:" + getPersonaNatural().getIdentificacionOriginal());
			// entonces verifica si cambia con referencia a la identificacion
			// original
			if (!numDoc.equals(getPersonaNatural().getIdentificacionOriginal())) {
				// ha cambiado!
				// se valida q ya no exista en la bdd

				verificarListaExistentes(criteria);

			}

		}
		if (yaExiste) {
			if (paramSecPersonaNatural != null) {
				// entonces existe en persona natural
				System.out.println("se ha encontrado en personanatural...");
				// getFacesContext()
				// .getApplication()
				// .getNavigationHandler()
				// .handleNavigation(
				// getFacesContext(),
				// / null,
				// "/pages/formularios/personaNatural.jsf?faces-redirect=true&p="
				// + paramSecPersonaNatural);
				// return
				// "/pages/formularios/personaNatural.jsf?faces-redirect=true&p="
				// + paramSecPersonaNatural;
				System.out.println("listo");

			} else {
				// entonces encontro en beneficiario
				// no hace nada porque no esta creado en personanatural, que
				// siga ingresando los datos
			}
		}
	}

	/**
	 * Aqui se consulta en varias plataformas: - CU - SmartData - DataBook
	 * 
	 * @param criteria
	 */
	private void verificarListaExistentes(Criteria criteria) {

		List<PersonaNatural> existentesPN = personaNaturalServicio.findByCriterias(criteria);

		int totalEncontrados = 0;

		// busca en pers. natural
		if (existentesPN.size() == 1) {
			// si encuentra uno, carga en el form todos los datos
			totalEncontrados = totalEncontrados + 1;
			paramSecPersonaNatural = existentesPN.get(0).getSecPersonaNatural();
			nuevo = false;
			personaNatural = null;// para que cargue como modificacion
			consultaCumulosLista = null;// para que consulte
			String mensaje = getBundleMensajes("numero.documento.existe", null);
			addInfoMessage("SSIPNForm:txtId", mensaje, mensaje);
			yaExiste = true;
		} else {
			// si encuentra mas de uno, muestra mensaje q hay n veces
			if (existentesPN.size() > 1) {
				totalEncontrados = totalEncontrados + existentesPN.size();
				String mensaje = getBundleMensajes("numero.documento.existe.n.veces",
						new Object[] { existentesPN.size() });
				addErrorMessage("SSIPNForm:txtId", mensaje, mensaje);
				yaExiste = true;
			}
		}

		// si no encuentra en pers. natural, busca en beneficiarios
		if (totalEncontrados == 0) {
			List<Beneficiario> existentesB = beneficiarioServicio.findByCriterias(criteria);
			// si encuentra UNO en beneficiarios, lo carga en el form
			if (existentesB.size() == 1) {
				totalEncontrados = totalEncontrados + 1;
				paramSecPersonaNatural = null;
				nuevo = true;
				Beneficiario beneficiario = existentesB.get(0);
				// personaNatural.setPersona(beneficiario.getPersona());
				personaNatural.setApellidoPaterno(beneficiario.getApellidoPaterno());
				personaNatural.setApellidoMaterno(beneficiario.getApellidoMaterno());
				personaNatural.setTipoIdentificacion(beneficiario.getTipoIdentificacion());
				personaNatural.setIdentificacion(beneficiario.getIdentificacion());

				String mensaje = getBundleMensajes("numero.documento.existe.beneficiario", null);
				addInfoMessage("SSIPNForm:txtId", mensaje, mensaje);
				yaExiste = true;
			}
		}
	}

	private void verificarListaExistentesConyuge(Criteria criteria) {

		List<PersonaNatural> existentesPN = personaNaturalServicio.findByCriterias(criteria);

		int totalEncontrados = 0;

		// busca en pers. natural
		if (existentesPN.size() == 1) {
			// si encuentra uno, carga en el form los datos de conyuge
			personaNatural.setConyuge(existentesPN.get(0));
			String mensaje = getBundleMensajes("numero.documento.existe", null);
			addInfoMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
			totalEncontrados = totalEncontrados + existentesPN.size();
		} else {
			// si encuentra mas de uno, muestra mensaje q hay n veces
			if (existentesPN.size() > 1) {
				totalEncontrados = totalEncontrados + existentesPN.size();
				String mensaje = getBundleMensajes("numero.documento.existe.n.veces",
						new Object[] { existentesPN.size() });
				addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
				// yaExiste = true;
			}
		}

		// si no encuentra en pers. natural, busca en beneficiarios
		if (totalEncontrados == 0) {
			List<Beneficiario> existentesB = beneficiarioServicio.findByCriterias(criteria);
			// si encuentra UNO en beneficiarios, lo carga en el form
			if (existentesB.size() == 1) {
				totalEncontrados = totalEncontrados + 1;
				Beneficiario beneficiario = existentesB.get(0);
				PersonaNatural pnConyuge = new PersonaNatural();
				// pnConyuge.setPersona(beneficiario.getPersona());
				pnConyuge.setPrimerNombre(beneficiario.getPrimerNombre());
				pnConyuge.setSegundoNombre(beneficiario.getSegundoNombre());
				pnConyuge.setApellidoPaterno(beneficiario.getApellidoPaterno());
				pnConyuge.setApellidoMaterno(beneficiario.getApellidoMaterno());
				pnConyuge.setTipoIdentificacion(personaNatural.getConyuge().getTipoIdentificacion());
				pnConyuge.setIdentificacion(personaNatural.getConyuge().getIdentificacion());

				personaNatural.setConyuge(pnConyuge);

				String mensaje = getBundleMensajes("numero.documento.existe.beneficiario", null);
				addInfoMessage("SSIPNForm:txtId", mensaje, mensaje);
			}
		}
	}

	public void locationWorldListener(ActionEvent event) {
		System.out.println("LISTENER LW");

		Direccion dir = (Direccion) getExternalContext().getRequestMap().get("dir");
		dir.setMostrarNoCargoLW(false);

		String secundaria = dir.getSecundaria();

		if (secundaria != null && !secundaria.equals("")) {
			String ciudad = dir.getCiudad();
			String principal = dir.getPrincipal();
			GeocodeAddressLW geocodeAddressLW = encontrarGeocode(ciudad, principal, secundaria);
			if (geocodeAddressLW != null) {
				System.out.println("se obtubvo latitud y longitud");
				dir.setLongitud(geocodeAddressLW.getLongitude());
				dir.setLatitud(geocodeAddressLW.getLatitude());
				// si son ceros entonces muestra el mensaje
				if (geocodeAddressLW.getLongitude().equals(BigDecimal.ZERO)
						&& geocodeAddressLW.getLatitude().equals(BigDecimal.ZERO)) {
					dir.setMostrarNoCargoLW(true);
					System.out.println("sugerencia LW:" + event.getComponent().getId());
					// System.out.println("sugerencia LW:"
					// + event.getComponent().get);
					// addInfoMessage(event.getComponent().getId(),
					// "Dir. no validada", "Dir. no validada");
				} else {
					dir.setMostrarNoCargoLW(false);
				}
			} else {
				System.out.println("sugerencia LW:" + event.getComponent().getId());
				addInfoMessage(event.getComponent().getId(), "Dir. no validada", "Dir. no validada");
				dir.setMostrarNoCargoLW(true);
			}
		}
	}

	public String guardar() {
		// Se pone para que no se muestre el pop
		setMuestraPopUp(false);

		// soltero
		if (estadoCivilIdInicial != 0 && estadoCivilIdInicial != Constantes.ESTADO_CIVIL_ID_SOLTERO) {
			if (personaNatural.getEstadoCivil().getCodEstadoCivil()
					.shortValue() == Constantes.ESTADO_CIVIL_ID_SOLTERO) {
				String mensaje = "No se puede cambiar a estado civil Soltero.";
				addErrorMessage("SSIPNForm:estadoCivilSel", mensaje, mensaje);
				return null;
			}
		}

		// verifica rcs
		verificarRiesgo();

		if (sesionCtrl.getMensajeRcsDto() != null && !sesionCtrl.getMensajeRcsDto().isPuedeContinuar()) {
			return null;
		}
		if (personaNatural.isConConyuge()) {
			verificarRiesgoConyuge();
			if (sesionCtrl.getMensajeRcsDto() != null && !sesionCtrl.getMensajeRcsDto().isPuedeContinuar()) {
				return null;
			}
		}

		String numDoc = getPersonaNatural().getIdentificacion();
		char tipoDoc = getPersonaNatural().getTipoIdentificacion().getCodTipoIdentificacion();

		boolean documentoValido = true;

		// valida cedula
		if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
			documentoValido = CedulaValidator.validate(numDoc);
		}

		// verifica
		if (!documentoValido) {
			String mensaje = getBundleMensajes("numero.documento.incorrecto", null);
			addErrorMessage("SSIPNForm:txtId", mensaje, mensaje);
			return null;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(personaNatural.getFchNacimiento());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		Calendar hoy = Calendar.getInstance();
		// si nacio en el futuro
		if (hoy.compareTo(c) < 0) {
			String mensaje = getBundleMensajes("fecha.nacimiento.futuro", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		int anioNacimiento = c.get(Calendar.YEAR);
		int anioActual = Calendar.getInstance().get(Calendar.YEAR);

		// si tiene mas de la edad maxima
		int edad = anioActual - anioNacimiento;
		if (edad >= Constantes.MAX_EDAD) {
			String mensaje = getBundleMensajes("fecha.nacimiento.antiguo", new Object[] { Constantes.MAX_EDAD });
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		// validacion de datos de conyuge
		if (personaNatural.isConConyuge() && isObligatorioIdentificacionConyuge()) {
			numDoc = personaNatural.getConyuge().getIdentificacion();
			tipoDoc = personaNatural.getConyuge().getTipoIdentificacion().getCodTipoIdentificacion();

			documentoValido = true;

			if (numDoc == null) {
				numDoc = "";
			}

			if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
				documentoValido = CedulaValidator.validate(numDoc);
			}

			// verifica
			if (!documentoValido) {
				String mensaje = getBundleMensajes("numero.documento.incorrecto", null);
				addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
				return null;
			}

			if (numDoc.equals(personaNatural.getIdentificacion())) {
				String mensaje = getBundleMensajes("identificacion.igual.persona.conyuge", null);
				addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
				return null;
			}
		}

		// Validar hijos

		if (sesionCtrl.getEmpresa().equals(EmpresaEnum.COLVIDA)) {

			if (personaNatural.getPersona().getHijos() != null && !personaNatural.getPersona().getHijos().isEmpty()) {

				for (Hijo h : personaNatural.getPersona().getHijos()) {

					if (h.getOcupacion().getCodOcupacion() == null) {

						String mensaje = getBundleMensajes("ocupacion.hijo", null);

						// validar no ocupaciones
						addErrorMessage(null, mensaje, mensaje);
						return null;
					}
				}

			}
		}

		// direcciones, pone latitudes de location world
		Collection<Direccion> dirList = personaNatural.getPersona().getDireccionCollection();
		for (Direccion dir : dirList) {

			if (dir.getActivo()) {

				String secundaria = dir.getSecundaria();
				// solo si secundaria no es vacia, llama a LW
				if (secundaria != null && !secundaria.equals("")) {
					String ciudad = dir.getCiudad();
					String principal = dir.getPrincipal();
					GeocodeAddressLW geocodeAddressLW = encontrarGeocode(ciudad, principal, secundaria);
					if (geocodeAddressLW != null) {
						System.out.println("se obtubvo latitud y longitud");
						dir.setLongitud(geocodeAddressLW.getLongitude());
						dir.setLatitud(geocodeAddressLW.getLatitude());
					}

				}

				// TODO valida que exista canton
				if (dir.getCanton() == null || dir.getCanton().getSecCanton() == null) {
					String mensaje = getBundleMensajes("requerido.campo", new Object[] { "Cant\u00F3n" });
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}

				// valida que inrgese ciudad
				if (dir.getCiudad() == null || dir.getCiudad().trim().equals("")) {
					String mensaje = getBundleMensajes("requerido.campo", new Object[] { "Ciudad" });
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
			}
		}

		// pep
		if (personaNatural.getRespuestaPep().equals(RespuestaEnum.SI.getCodigo() + "")) {
			if (personaNatural.getPersonaPeCollectionActivosCollection().size() == 0) {
				String mensaje = "Ingrese PEP (Persona Expuesta Pol\u00EDticamente)";
				addErrorMessage(null, mensaje, mensaje);
				return null;
			}
		} else {
			for (PersonaPe pep : personaNatural.getPersonaPeCollection()) {
				pep.setEstado(EstadoEnum.INACTIVO.getCodigo());
			}
		}

		// tipo riesgo
		System.out.println("tipoRiesgo:" + personaNatural.getTipoRiesgo().getCodTipoRiesgo());
		if (personaNatural.getTipoRiesgo().getCodTipoRiesgo() == null) {
			String mensaje = getBundleMensajes("seleccione.riesgo", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		// Se valida que tenga correo electronico. JO
		if (!(personaNatural.getPersona().getDireccionElectronicaActivosCollection() != null
				&& !personaNatural.getPersona().getDireccionElectronicaActivosCollection().isEmpty())) {
			String mensaje = getBundleMensajes("correo.electronico.requerido", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		// se valida que marque un correo como preferido
		Collection<DireccionElectronica> lista = personaNatural.getPersona().getDireccionElectronicaActivosCollection();
		boolean tieneEmailPrincipal = false;
		for (DireccionElectronica direccionElectronica : lista) {
			if (direccionElectronica.getPrincipalTransient().booleanValue()) {
				tieneEmailPrincipal = true;
				break;
			}
		}
		// colvida se pone direccion electronica por defecto personal
		System.out.println("tipo empresa:" + sesionCtrl.getEmpresa());
		for (DireccionElectronica direccionElectronica : lista) {
			System.out.println("tipo email:"
					+ direccionElectronica.getTipoDireccionElectronica().getCodTipoDireccionElectronica());
			System.out.println("pone uno");
			if (sesionCtrl.getEmpresa().equals(EmpresaEnum.COLVIDA)) {
				if (direccionElectronica.getTipoDireccionElectronica() == null || direccionElectronica
						.getTipoDireccionElectronica().getCodTipoDireccionElectronica() == null) {

					TipoDireccionElectronica tipo = new TipoDireccionElectronica((short) 1);
					direccionElectronica.setTipoDireccionElectronica(tipo);
				}
			}
		}

		if (!tieneEmailPrincipal) {
			String mensaje = getBundleMensajes("correo.electronico.preferido.requerido", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		if (sesionCtrl.getEmpresa().equals(EmpresaEnum.EQUIVIDA)) {

			// actividades
			Collection<Actividad> actList = personaNatural.getActividadFormularioCollection();
			Collection<Actividad> actListFinal = new ArrayList<Actividad>();
			for (Actividad act : actList) {
				if (act.isSeleccionado()) {
					if (!act.getTipoActividad().getPregunta().equals("N")) {
						// entonces valida que ingrese detalle
						if (act.getDetalle() == null || act.getDetalle().equals("")) {
							String mensaje = getBundleMensajes("actividad.ingrese.detalle", new Object[] {
									act.getTipoActividad().getPregunta(), act.getTipoActividad().getActividad() });
							addErrorMessage(null, mensaje, mensaje);
							return null;
						}
					}
					// act.setEstado(EstadoEnum.ACTIVO.getCodigo());
					actListFinal.add(act);
				} else {
					if (act.getSecActividad() != null) {
						actListFinal.add(act);
					}
				}
			}
			System.out.println("actividades guardar total:" + actListFinal.size());

			if (actListFinal.size() == 0) {
				String mensaje = getBundleMensajes("actividad.seleccione.al.menos.una", null);
				addErrorMessage(null, mensaje, mensaje);
				return null;
			}

			personaNatural.setActividadCollection(actListFinal);

			// seguroviegte

			Collection<SeguroVigente> listaSV = personaNatural.getSegurosVigentesCollection();
			for (SeguroVigente seguroVigente : listaSV) {
				if (seguroVigente.getRamoSeguro().getSecTipoRamo().intValue() == -1) {
					String mensaje = getBundleMensajes("seleccione.ramo.seguro.vigente", null);
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
				if (seguroVigente.getCompaniaSeguro().getSecCiaSeguro().intValue() == -1) {
					String mensaje = getBundleMensajes("seleccione.empresa.seguro.vigente", null);
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
				if (seguroVigente.getValorSeguro() == null) {
					String mensaje = getBundleMensajes("ingrese.valor.seguro.vigente", null);
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
			}

			// motivo seguro
			Collection<MotivoSeguro> msList = personaNatural.getPersona().getMotivoSeguroFormularioCollection();
			Collection<MotivoSeguro> msListFinal = new ArrayList<MotivoSeguro>();
			short totaMotivoSeguro = 0;
			for (MotivoSeguro ms : msList) {
				if (ms.isSeleccionado()) {
					if ((ms.getTipoMotivoSeguro().getDetallar() == 'S')) {
						// entonces valida que ingrese detalle
						if (ms.getDetalle() == null || ms.getDetalle().equals("")) {
							String mensaje = getBundleMensajes("motivo.seguro.ingrese.detalle", null);
							addErrorMessage(null, mensaje, mensaje);
							return null;
						}
					}
					totaMotivoSeguro++;
					msListFinal.add(ms);
				} else {
					if (ms.getSecMotivoSeguro() != null) {
						msListFinal.add(ms);
					}
				}
			}
			System.out.println("motivo seguro guardar total:" + msListFinal.size());

			if (totaMotivoSeguro == 0) {
				String mensaje = getBundleMensajes("motivo.seguro.ingrese.uno", null);
				addErrorMessage(null, mensaje, mensaje);
				addErrorMessage("SSIPNForm:tblMotivoS", mensaje, mensaje);
				return null;
			}

			personaNatural.getPersona().setMotivoSeguroFormularioCollection(msListFinal);

			// otro seguro
			Collection<SeguroAdicional> osList = personaNatural.getSeguroAdicionalFormularioCollection();
			Collection<SeguroAdicional> osFinalList = new ArrayList<SeguroAdicional>();
			if (osList != null) {
				for (SeguroAdicional seguroAdicional : osList) {
					if (seguroAdicional.getRespuesta() == RespuestaEnum.SI.getCodigo()
							|| seguroAdicional.getRespuesta() == RespuestaEnum.NO.getCodigo()) {
						osFinalList.add(seguroAdicional);
					}
				}
			}
			personaNatural.setSeguroAdicionalCollection(osFinalList);
		} // fin empresa equivida (especialista)

		// profesion
		if (personaNatural.getProfesion().getSecProfesion() == null) {
			personaNatural.getProfesion().setSecProfesion(Constantes.PROFESION_NO_DISPONIBLE);// le pone cero
		}

		// ocupacion
		if (personaNatural.getOcupacion().getCodOcupacion() == null) {
			personaNatural.setOcupacion(null);
		}

		// se separa empleos en 3 colecciones
		Collection<EmpleoDependiente> empDeplista = new ArrayList<EmpleoDependiente>();
		Collection<EmpleoIndependiente> empIndeplista = new ArrayList<EmpleoIndependiente>();
		Collection<OtraOcupacion> otraOcupacionLista = new ArrayList<OtraOcupacion>();

		Collection<EmpleoDto> tmpList = personaNatural.getEmpleoCollection();
		int fila = 1;
		if (tmpList == null || tmpList.size() == 0) {
			// asdf sd
			String mensaje = getBundleMensajes("lista.empleos.uno.requerido", new Object[] { fila });
			addErrorMessage(null, mensaje, mensaje);
			return null;

		}
		for (EmpleoDto empleo : tmpList) {

			if (!empleo.getActivo()) {
				continue;
			}

			if (empleo.getTipoEmpleo().equals(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo())
					|| empleo.getTipoEmpleo().equals(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo())) {

				if (empleo.getCargo() == null) {
					String mensaje = getBundleMensajes("lista.empleos.fila.cargo.requerido", new Object[] { fila });
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}

				if (sesionCtrl.getEmpresa().equals(EmpresaEnum.EQUIVIDA)) {
					if (empleo.getActividadEconomicaNombre() == null
							|| empleo.getActividadEconomicaNombre().trim().equals("")) {
						String mensaje = getBundleMensajes("ingrese.actividad.economica", new Object[] { fila });
						addErrorMessage(null, mensaje, mensaje);
						return null;
					}
				}

				if (empleo.getTipoEmpleo().equals(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo())) {

					if (sesionCtrl.getEmpresa().equals(EmpresaEnum.EQUIVIDA)) {

						if (empleo.getNegocioEmpresaNombre() == null || empleo.getNegocioEmpresaNombre().equals("")) {
							String mensaje = getBundleMensajes("lista.empleos.fila.empresa.requerido",
									new Object[] { fila });
							addErrorMessage(null, mensaje, mensaje);
							return null;
						}
					}

					EmpleoDependiente e = new EmpleoDependiente();
					e.setSecEmpleoDependiente(empleo.getSecEmpleo());
					e.setCargo(empleo.getCargo());
					e.setCodTiempo(empleo.getCodTiempo());
					e.setEstado(empleo.getEstado());
					ActividadEconomica actividadEconomica = new ActividadEconomica(empleo.getActividadEconomicaId());
					e.setActividadEconomica(actividadEconomica);
					e.setNegocioEmpresa(empleo.getNegocioEmpresaNombre());
					e.setPersonaNatural(personaNatural);
					e.setTiempoEmpresa(empleo.getTiempoEmpresa());

					if (e.getActivo()) {
						empDeplista.add(e);
					} else {
						if (e.getSecEmpleoDependiente() != null) {
							empDeplista.add(e);
						}
					}
				}

				if (empleo.getTipoEmpleo().equals(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo())) {

					EmpleoIndependiente e = new EmpleoIndependiente();
					e.setSecEmpleoIndependiente(empleo.getSecEmpleo());
					e.setCargo(empleo.getCargo());
					e.setCodTiempo(empleo.getCodTiempo());
					e.setEstado(empleo.getEstado());
					ActividadEconomica actividadEconomica = new ActividadEconomica(empleo.getActividadEconomicaId());
					e.setActividadEconomica(actividadEconomica);
					e.setPersonaNatural(personaNatural);
					e.setTiempoEmpresa(empleo.getTiempoEmpresa());

					if (e.getActivo()) {
						empIndeplista.add(e);
					} else {
						if (e.getSecEmpleoIndependiente() != null) {
							empIndeplista.add(e);
						}
					}

				}
			} else {
				// OTRA OCUPACION
				OtraOcupacion otraOcupacion = new OtraOcupacion();
				otraOcupacion.setSecOtraOcupacion(empleo.getSecEmpleo());
				otraOcupacion.setCodTiempo(empleo.getCodTiempo());
				otraOcupacion.setSecPersonaNatural(personaNatural.getSecPersonaNatural());
				otraOcupacion.setTiempoOcupacion(empleo.getTiempoEmpresa());
				otraOcupacion.setTipoOtraOcupacion(new TipoOtraOcupacion(empleo.getTipoEmpleo()));
				otraOcupacion.setEstado(empleo.getEstado());

				// if (otraOcupacion.getActivo()) {
				// empIndeplista.add(e);
				// } else {
				// if (e.getSecEmpleoIndependiente() != null) {
				// empIndeplista.add(e);
				// }
				// }

				otraOcupacionLista.add(otraOcupacion);

			}

			fila++;
		}

		personaNatural.setEmpleoDependienteCollection(empDeplista);
		personaNatural.setEmpleoIndependienteCollection(empIndeplista);
		personaNatural.setOtraOcupacionCollection(otraOcupacionLista);

		// agrega telefono preferido
		Telefono preferido = new Telefono();
		Collection<Direccion> direccionLista = personaNatural.getPersona().getDireccionCollection();
		for (Direccion direccion : direccionLista) {
			if (direccion.getActivo()) {
				if (direccion.getDireccionTelefonoCollection() != null
						&& !direccion.getDireccionTelefonoCollection().isEmpty()) {
					Collection<DireccionTelefono> tmtDireccionTelefono = direccion.getDireccionTelefonoCollection();
					System.out.println("direccion:" + direccion.getPrincipal());

					for (DireccionTelefono direccionTelefono : tmtDireccionTelefono) {
						if (direccionTelefono.getTelefono().getActivo()) {
							if (direccionTelefono.getTelefono().getPrincipal()) {
								preferido = direccionTelefono.getTelefono();
								break;
							} else {
							}
						}
					}
				}

			}
		}

		Collection<Telefono> tmpTelefonoAdicionales = personaNatural.getPersona().getTelefonoSinDireccionCollection();
		for (Telefono telefono : tmpTelefonoAdicionales) {
			if (telefono.getActivo()) {
				if (telefono.getPrincipal()) {
					preferido = telefono;
					break;
				}
			}
		}

		// verifica que ingrese u a direccion principal

		boolean seleccionoDirPrincipal = false;
		for (Direccion direccion : direccionLista) {
			if (direccion.getActivo()) {
				if (direccion.getDireccionPrincipal()) {
					seleccionoDirPrincipal = true;
					direccion.setEnvioCorrespondencia(RespuestaEnum.SI.getCodigo());
				} else {
					direccion.setEnvioCorrespondencia(RespuestaEnum.NO.getCodigo());
				}
			}
		}

		if (!seleccionoDirPrincipal) {
			String mensaje = getBundleMensajes("direccion.principal.no.seleccionada", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		// VALIDACION DE EMAIL
		Collection<DireccionElectronica> tmpDirElectronica = personaNatural.getPersona()
				.getDireccionElectronicaFormularioCollection();

		// valida emails nuevamente, para no guardar incoherencias

		for (DireccionElectronica direccionElectronica : tmpDirElectronica) {

			if (direccionElectronica.getActivo()) {
				boolean valida = EmailUtils.validar(direccionElectronica.getDireccionElectronica());

				// valida = wsDatosPersonaServicio.validarEmail();
				if (!valida) {
					String mensaje = getBundleMensajes("email.incorrecto", null);
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
			}
		}

		// VALIDA INGRESOS si tiene valor
		Collection<IngresoMensualAdicional> ingresosAdicionales = personaNatural.getIngresoMensualAdicionalCollection();
		for (IngresoMensualAdicional ing : ingresosAdicionales) {
			if (ing.getMntIngresoAdicional() != null && ing.getMntIngresoAdicional().compareTo(BigDecimal.ZERO) > 0) {
				// ha ingresado valor, entonces verifica que se ingrese fuente
				if (ing.getFuenteIngresoAdicional().trim().equals("")) {
					String mensaje = "Ingrese 'Fuente' en Otros Ingresos";
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
			}
		}

		// REFERENCIAS PERSONALES
		// se verifica q las referencias tengan al menos un telefono
		Collection<Referencia> tmpRef = personaNatural.getReferenciaFormularioCollection();

		// la lista final que va a guardar, es decir solo referencias ocon datos
		// completos
		Collection<Referencia> refFinal = new ArrayList<Referencia>();

		for (Referencia referencia : tmpRef) {

			System.out.println("REFERENCIAS FORM");
			System.out.println(referencia);

			if (referencia.getFamiliarNoVivaUd() == 'S') {

				// valida que sea obligatorio referencia de familiar que no viva
				// con ud
				// if (isObligatorioReferenciaFamiliarNoVivaConUd()) {
				// if (referencia.getDenominacion() == null
				// || referencia.getDenominacion().equals("")) {
				// System.out.println("no hay referencia obligada");
				// String mensaje = getBundleMensajes(
				// "ingrese.referencia.familia.no.viva.ud", null);
				// addErrorMessage(null, mensaje, mensaje);
				// return null;
				// }
				// System.out.println("REF cel:"
				// + referencia.getNroTelefonoCelular() + "X");
				// System.out.println("REF con:"
				// + referencia.getNroTelefonoConvencional() + "X");
				// if (referencia.getNroTelefonoCelular() == null
				// || referencia.getNroTelefonoCelular().equals("")) {
				// System.out.println("no hay celular");
				// if (referencia.getNroTelefonoConvencional() == null
				// || referencia.getNroTelefonoConvencional()
				// .equals("")) {
				// System.out.println("tampoco convencional");
				// String mensaje = getBundleMensajes(
				// "referencia.ingreso.telefono", null);
				// addErrorMessage(null, mensaje, mensaje);
				// return null;
				// }

				// }
				// }// fin isObligatorioReferenciaFamiliarNoVivaConUd()
			} else {
				if (referencia.getDenominacion() != null && !referencia.getDenominacion().equals("")) {
					if (referencia.getNroTelefonoCelular() == null || referencia.getNroTelefonoCelular().equals("")) {
						if (referencia.getNroTelefonoConvencional() == null
								|| referencia.getNroTelefonoConvencional().equals("")) {
							System.out.println("ene l if N");
							String mensaje = getBundleMensajes("referencia.ingreso.telefono", null);
							addErrorMessage(null, mensaje, mensaje);
							return null;
						}

					}
				}
			}

			if (referencia.getDenominacion() != null && !referencia.getDenominacion().equals("")) {
				refFinal.add(referencia);
			}
		}

		personaNatural.setReferenciaFormularioCollection(refFinal);

		if (sesionCtrl.getEmpresa().equals(EmpresaEnum.EQUIVIDA)) {

			// REFERENCIAS COMERCIALES
			// la lista final que va a guardar, es decir solo referencias ocon datos
			// completos
			Collection<ReferenciaComercial> refComercialFinal = new ArrayList<ReferenciaComercial>();

			Collection<ReferenciaComercial> tmpComercialRef = personaNatural
					.getReferenciaComercialFormularioCollection();

			for (ReferenciaComercial referenciaComercial : tmpComercialRef) {
				if (referenciaComercial.getEntidadReferencia() != null
						&& !referenciaComercial.getEntidadReferencia().trim().equals("")) {
					refComercialFinal.add(referenciaComercial);
				}
			}

			personaNatural.setReferenciaComercialFormularioCollection(refComercialFinal);

		}

		if (sesionCtrl.getEmpresa().equals(EmpresaEnum.EQUIVIDA)) {

			// REFERENCIAS BANCARIAS
			// la lista final que va a guardar, es decir solo referencias ocon datos
			// completos
			Collection<ReferenciaBancaria> refBancariaFinal = new ArrayList<ReferenciaBancaria>();

			Collection<ReferenciaBancaria> tmpBancariaRef = personaNatural.getReferenciaBancariaFormularioCollection();

			for (ReferenciaBancaria referenciaBancaria : tmpBancariaRef) {
				if (referenciaBancaria.getInstitucionFinanciera().getSecInstitucionFinanciera() != null
						&& referenciaBancaria.getInstitucionFinanciera().getSecInstitucionFinanciera() != -1) {

					if (referenciaBancaria.getTipoServicioInstFin().getSecTipoServicioInstFin() == -1) {
						String m = "Seleccione tipo de cuenta en Referencias Bancarias";
						addErrorMessage(null, m, m);
						return null;
					}

					refBancariaFinal.add(referenciaBancaria);
				}
			}

			personaNatural.setReferenciaBancariaFormularioCollection(refBancariaFinal);

		}

		// a telf q es contacto preferido (principal)
		TipoContactoPreferido tipoContactoPreferido = new TipoContactoPreferido(
				Constantes.TIPO_CONTACTO_PREFERIDO_TELEFONO);
		personaNatural.getPersona().getContactoPreferidoTransient().setTipoContactoPreferido(tipoContactoPreferido);
		personaNatural.getPersona().getContactoPreferidoTransient().setPersona(personaNatural.getPersona());
		if (preferido.getNroTelefono() == null || preferido.getNroTelefono().equals("")) {
			String mensaje = getBundleMensajes("seleccione.contacto.preferido", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}
		personaNatural.getPersona().getContactoPreferidoTransient().setTelefono(preferido);

		if (sesionCtrl.getEmpresa().equals(EmpresaEnum.EQUIVIDA)) {
			// horario contacto preferido
			boolean correcto = personaNatural.getPersona().getContactoPreferidoTransient().ponerHorario();
			if (!correcto) {
				String mensaje = getBundleMensajes("ingrese.horario.contacto.preferido", null);
				addErrorMessage(null, mensaje, mensaje);
				return null;
			}
		} else {
			// es colvida
			if (personaNatural.getPersona().getContactoPreferidoTransient().getHorarioInicio() == null) {
				personaNatural.getPersona().getContactoPreferidoTransient().setHoraDesde("00");
				personaNatural.getPersona().getContactoPreferidoTransient().setMinutoDesde("00");
			}
			if (personaNatural.getPersona().getContactoPreferidoTransient().getHorarioFin() == null) {
				personaNatural.getPersona().getContactoPreferidoTransient().setHoraHasta("00");
				personaNatural.getPersona().getContactoPreferidoTransient().setMinutoHasta("01");
			}
			boolean correcto = personaNatural.getPersona().getContactoPreferidoTransient().ponerHorario();
			if (!correcto) {
				String mensaje = getBundleMensajes("ingrese.horario.contacto.preferido", null);
				addErrorMessage(null, mensaje, mensaje);
				return null;
			}
		}

		// prepara tabla telefono
		List<Telefono> guardarListaTel = new ArrayList<Telefono>();

		Collection<Direccion> direcciones = personaNatural.getPersona().getDireccionCollection();

		for (Direccion dir : direcciones) {
			if (dir.getActivo()) {
				for (DireccionTelefono dt : dir.getDireccionTelefonoCollection()) {
					if (dt.getTelefono().getActivo()) {
						guardarListaTel.add(dt.getTelefono());
					} else {
						if (dt.getTelefono().getSecTelefono() != null) {
							guardarListaTel.add(dt.getTelefono());
						}
					}
				}
			}
		}

		personaNatural.getPersona().setTelefonoCollection(guardarListaTel);
		personaNatural.getPersona().getTelefonoCollection()
				.addAll(personaNatural.getPersona().getTelefonoSinDireccionCollection());

		// prepara biometrica y deja listo para guardar

		// se guarda en kilos
		personaNatural.getBiometrica().setPeso(personaNatural.getBiometrica().getPesoKilos());

		personaNatural.getBiometrica().setGanadosUltAnioKilos(personaNatural.getBiometrica().getGanadosUltAnioKilos());

		personaNatural.getBiometrica()
				.setPerdidosUltAnioKilos(personaNatural.getBiometrica().getPerdidosUltAnioKilos());

		// si gano peso
		if (personaNatural.getBiometrica().getGanadosUltAnioKilos() != null) {
			if (personaNatural.getBiometrica().getGanadosUltAnioKilos().compareTo(BigDecimal.ZERO) > 0) {
				personaNatural.getBiometrica().setGanadoPerdido(GanadoPerdidoEnum.GANADO.getCodigo());
				personaNatural.getBiometrica()
						.setDiferenciaUltimoAnio(personaNatural.getBiometrica().getGanadosUltAnioKilos());
			}
		}

		// si perdio peso
		if (personaNatural.getBiometrica().getPerdidosUltAnioKilos() != null) {
			if (personaNatural.getBiometrica().getPerdidosUltAnioKilos().compareTo(BigDecimal.ZERO) > 0) {
				personaNatural.getBiometrica().setGanadoPerdido(GanadoPerdidoEnum.PERDIDO.getCodigo());
				personaNatural.getBiometrica()
						.setDiferenciaUltimoAnio(personaNatural.getBiometrica().getPerdidosUltAnioKilos());
			}
		}

		// controla q no sea null
		if (personaNatural.getBiometrica().getDiferenciaUltimoAnio() == null) {
			personaNatural.getBiometrica().setDiferenciaUltimoAnio(BigDecimal.ZERO);
		}

		// prepara historia medica familiar y deja listo para guardar
		Collection<HistoriaMedicaFamiliar> listaHMF = personaNatural.getHistoriaMedicaFamiliarFormularioCollection();

		Collection<HistoriaMedicaFamiliar> listaHMFGuardar = new ArrayList<HistoriaMedicaFamiliar>();

		for (HistoriaMedicaFamiliar hmf : listaHMF) {

			boolean ingresadoDifunto = false;
			boolean ingresadoVivo = false;

			// si es difunto
			if ((hmf.getDetallesM() != null && !hmf.getDetallesM().equals("")) || hmf.getEdadM() > 0) {
				ingresadoDifunto = true;
			}
			// si es vivo
			if (hmf.getEdad() > 0 || (hmf.getDetalles() != null && !hmf.getDetalles().equals(""))) {
				ingresadoVivo = true;
			}

			if (ingresadoVivo && ingresadoDifunto) {
				String mensaje = getBundleMensajes("esta.ingresando.vivo.muerto",
						new Object[] { hmf.getTipoParentescoRelacion().getTipoParentesco() });
				addErrorMessage(null, mensaje, mensaje);
				return null;
			}

			if (ingresadoVivo) {
				if (hmf.getDetalles().equals("")) {
					String mensaje = getBundleMensajes("ingrese.detalle.hmf",
							new Object[] { hmf.getTipoParentescoRelacion().getTipoParentesco() });
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
				hmf.setVivo(RespuestaEnum.SI.getCodigo());
				listaHMFGuardar.add(hmf);
			}

			if (ingresadoDifunto) {
				if (hmf.getDetallesM().equals("")) {
					String mensaje = getBundleMensajes("ingrese.detalle.hmf",
							new Object[] { hmf.getTipoParentescoRelacion().getTipoParentesco() });
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
				// hmf.setVivo(RespuestaEnum.NO.getCodigo());
				// hmf.setDetalles(hmf.getDetallesM());
				// hmf.setEdad(hmf.getEdadM());
				HistoriaMedicaFamiliar hmf2 = new HistoriaMedicaFamiliar();// por
																			// el
																			// problema
																			// de
																			// repetido
																			// en
																			// detalle
				hmf2.setSecHistoriaMedica(hmf.getSecHistoriaMedica());
				hmf2.setDetalles(hmf.getDetallesM());
				hmf2.setEdad(hmf.getEdadM());
				hmf2.setPersonaNatural(hmf.getPersonaNatural());
				hmf2.setTipoParentescoRelacion(hmf.getTipoParentescoRelacion());
				hmf2.setVivo(RespuestaEnum.NO.getCodigo());
				listaHMFGuardar.add(hmf2);
			}
		}
		// guarda solo los ingresados
		personaNatural.setHistoriaMedicaFamiliarCollection(listaHMFGuardar);

		// preguntas listado

		personaNatural.setHabitoEnfermedadFormularioCollection(habitoEnfermedadPorPersonaLista);

		// pone categoria E o G
		personaNatural.getPersona().setCategoria(sesionCtrl.getEmpresa().getCategorias()[0].charAt(0));

		// servicios para guardar

		try {
			if (getNuevo()) {
				System.out.println("ingreso en cliente unico .....");

				// persona componenente exclusion
				if (sesionCtrl.getFormularioSeleccionado().equals(TipoFormularioEnum.ASOCIACION.getSecuencial())) {
					PersonaComponenteExclusion personaComponenteExclusion = new PersonaComponenteExclusion();
					personaComponenteExclusion.setPersona(personaNatural.getPersona());
					personaNatural.getPersona().setPersonaComponenteExclusionTransient(personaComponenteExclusion);
				}

				// TODO: se comenta para probar nueva persistencia
				// personaNaturalServicio.crearPersonaNaturalFormularioWeb(
				// personaNatural, getRemoteUser(),
				// getHttpServletRequest().getRemoteAddr());

				// Nueva persistencia
				persistenciaGeneralServicio.persistirGeneral(personaNatural, getRemoteUser(),
						getHttpServletRequest().getRemoteAddr());

				guardarCrm();

				System.out.println("ingreso en sise .....");

				// ingresa persona natural sise
				Long codigoSISE = null;
				try {
					RespuestaSiseDto dto = siseServicio.insertarWsSiseMpersona(personaNatural,
							personaNatural.getPersona(), getNuevo(), getRemoteUser(), true);
					codigoSISE = dto.getIdPersonaSise();
					// muestra si hubo mensajes de error
					for (String m : dto.getErrorLista()) {
						addErrorMessage(null, m, m);
					}
				} catch (Exception e) {
					System.out.println("timeout sise:" + e);
					e.printStackTrace();
					codigoSISE = new Long(-1);
					addErrorMessage(null, e.getMessage(), e.getMessage());
				}

				String mensaje = getBundleMensajes("persona.natural.ingresada.codigo.sise",
						new Object[] { "" + codigoSISE });
				addInfoMessage(null, mensaje, mensaje);

				habilitarBtnGuardar = false;

			} else {
				System.out.println("actualizacion.....");
				String error = "";

				// persona compoenente exclusion
				if (sesionCtrl.getFormularioSeleccionado().equals(TipoFormularioEnum.ASOCIACION.getSecuencial())) {

					if (personaNatural.getPersona().getPersonaComponenteExclusionTransient() == null) {
						PersonaComponenteExclusion personaComponenteExclusion = new PersonaComponenteExclusion();
						personaComponenteExclusion.setPersona(personaNatural.getPersona());
						personaNatural.getPersona().setPersonaComponenteExclusionTransient(personaComponenteExclusion);
					} else {
						PersonaComponenteExclusion personaComponenteExclusion = personaNatural.getPersona()
								.getPersonaComponenteExclusionTransient();
						personaComponenteExclusion.setPersona(personaNatural.getPersona());
						personaNatural.getPersona().setPersonaComponenteExclusionTransient(personaComponenteExclusion);
					}
				} else {
					// if(personaNatural.getPersona().getPersonaComponenteExclusion()!=null){
					// }
				}

				// actualizacion
				personaNaturalServicio.actualizarPersonaNaturalFormularioWeb(personaNatural, getRemoteUser(),
						getHttpServletRequest().getRemoteAddr());

				System.out.println("fin guardado cliente unico...");

				error = actualizarCrm(error);

				// ingresa persona natural sise
				Long codigoSISE = null;
				try {
					RespuestaSiseDto dto = siseServicio.insertarWsSiseMpersona(personaNatural,
							personaNatural.getPersona(), getNuevo(), getRemoteUser(), true);
					codigoSISE = dto.getIdPersonaSise();
					// muestra si hubo mensajes de error
					for (String m : dto.getErrorLista()) {
						addErrorMessage(null, m, m);
					}
					getSession().setAttribute("dto", dto);
				} catch (Exception e) {
					System.out.println("timeout sise" + e);
					e.printStackTrace();
					codigoSISE = new Long(-1);
					addErrorMessage(null, e.getMessage(), e.getMessage());
				}

				String mensaje = getBundleMensajes("persona.natural.actualizada.codigo.sise",
						new Object[] { "" + codigoSISE });
				addInfoMessage(null, mensaje, mensaje);
				habilitarBtnGuardar = false;
				// TODO return a otroa pagina?

				return "/pages/formularios/listadoPersonasNaturales?faces-redirect=true&codigoSise=" + codigoSISE
						+ "&error=" + error;

			} // fin actualizacion

		} catch (EmpleoDependienteException e) {
			e.printStackTrace();
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		} catch (EmpleoIndependienteException e) {
			e.printStackTrace();
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		} catch (ServiceException e) {
			e.printStackTrace();
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		} catch (ErrorIngresoWsSiseException e) {
			e.printStackTrace();
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		}
		return null;
	}

	public String guardarColvida() {

		// llama al metodo guardar general
		// se valida por sesionCtrl si la empresa es colvida
		return guardar();

	}

	private boolean verificaSiGuardaCrm() {
		System.out.println("====> verificaSiGuardaCrm.....");

		Collection<EstadoPersona> lista = personaNatural.getEstadoPersonaCollection();
		boolean guardar = false;

		System.out.println("lista: " + lista.size());

		for (EstadoPersona estadoPersona : lista) {
			System.out.println("estadoPersona:" + estadoPersona);
			System.out.println("estadoPersona.getTipoEstado():" + estadoPersona.getTipoEstado());
			System.out
					.println("estadoPersona.getTipoEstado().getCodddd:" + estadoPersona.getTipoEstado().getCodEstado());
			if (estadoPersona.getTipoEstado().getCodEstado().intValue() == Constantes.SEC_ESTADO_ACEPTADO_CON_EXTRAPRIMA
					.intValue()
					|| estadoPersona.getTipoEstado().getCodEstado()
							.intValue() == Constantes.SEC_ESTADO_ACEPTADO_SIN_EXTRAPRIMA.intValue()) {
				guardar = true;
				break;
			}
		}

		System.out.println(" guarda en CRM????? " + guardar);

		return guardar;
	}

	private String actualizarCrm(String error) throws ServiceException, RemoteException {

		if (!verificaSiGuardaCrm()) {
			return error;
		}

		System.out.println("guardando en CRM");

		System.out.println("SECPER:" + personaNatural.getPersona().getSecPersona());
		System.out.println("NRODOC:" + personaNatural.getIdentificacion());

		String xmlEnvioConsulta = "<STREAM>" + "<HEADER><ITRANSACCION>009</ITRANSACCION></HEADER>" + "<PARAMETROS>"
				+ "<SECPER>" + personaNatural.getPersona().getSecPersona() + "</SECPER>" + "<NRODOC>"
				+ personaNatural.getIdentificacion() + "</NRODOC>" + "</PARAMETROS>" + "</STREAM>";

		System.out.println("xmlEnvioConsulta:" + xmlEnvioConsulta);

		String respuestaConsulta = crmWs.ConsultarPersonas(xmlEnvioConsulta);

		System.out.println("respuestaConsulta:");
		System.out.println(respuestaConsulta);

		if (respuestaConsulta.contains("<CODIGO>1</CODIGO>")) {
			error = "No se pudo actualizar en CRM, ver log para el detalle del error";
		} else {
			String xmlPersona = CrmUtil.obtenerXmlSoloDePersona(respuestaConsulta);

			String paraEnviarACrm = "<PERSONAS>" + xmlPersona + "</PERSONAS>";

			System.out.println("paraEnviarACrm::::" + paraEnviarACrm);

//			String address = Parametros.getString("location.web.service.actualizacion.crm");
//			try {
//				URL url = new URL(address);
//				ActualizacionesRegularesLocator locator = new ActualizacionesRegularesLocator();
//				ActualizacionesRegularesSoap servicio = locator.getActualizacionesRegularesSoap(url);

			// String respuestaCrm = servicio
			// .consultarPersonas(paraEnviarACrm);
			// System.out.println("RESPUESTA CRM:");
			// System.out.println(respuestaCrm);

//				System.out.println("FIN CRM");
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		System.out.println("FIN CRM");
		return error;
	}

	private void guardarCrm() throws ServiceException, RemoteException {

		if (!verificaSiGuardaCrm()) {
			return;
		}

		System.out.println("guardando en CRM");

		System.out.println("SECPER:" + personaNatural.getPersona().getSecPersona());
		System.out.println("NRODOC:" + personaNatural.getIdentificacion());

		String xmlEnvioConsulta = "<STREAM>" + "<HEADER><ITRANSACCION>009</ITRANSACCION></HEADER>" + "<PARAMETROS>"
				+ "<SECPER>" + personaNatural.getPersona().getSecPersona() + "</SECPER>" + "<NRODOC>"
				+ personaNatural.getIdentificacion() + "</NRODOC>" + "</PARAMETROS>" + "</STREAM>";

		System.out.println("xmlEnvioConsulta:" + xmlEnvioConsulta);

		String respuestaConsulta = crmWs.ConsultarPersonas(xmlEnvioConsulta);

		System.out.println("respuestaConsulta:");
		System.out.println(respuestaConsulta);
		String codigoErrorXml = CrmUtil.obtenerXmlPorTag(respuestaConsulta, "<CODIGO>", "</CODIGO>");
		Integer codigoError = 0;
		if (codigoErrorXml != null) {
			codigoError = Integer.parseInt(codigoErrorXml);
		}
		if (codigoError.intValue() > 0) {
			String m = "No se puede guardar en CRM, ver log para el detalle del mismo.";
			System.out.println("m:" + m);
			// TODO
			// addErrorMessage(null, m, m);
		} else {
			String xmlPersona = CrmUtil.obtenerXmlSoloDePersona(respuestaConsulta);

			String paraEnviarACrm = "<PERSONAS>" + xmlPersona + "</PERSONAS>";

			System.out.println("paraEnviarACrm::::" + paraEnviarACrm);

//			String address = Parametros.getString("location.web.service.actualizacion.crm");
//			try {
//				URL url = new URL(address);
//				ActualizacionesRegularesLocator locator = new ActualizacionesRegularesLocator();
//				ActualizacionesRegularesSoap servicio = locator.getActualizacionesRegularesSoap(url);

			// String respuestaCrm = servicio
			// .consultarPersonas(paraEnviarACrm);
			// System.out.println("RESPUESTA CRM:");
			// System.out.println(respuestaCrm);
//
//				System.out.println("FIN CRM");
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
	}

	public void verificarTelefono(AjaxBehaviorEvent event) {

		Telefono telefono = (Telefono) getExternalContext().getRequestMap().get("t");

		System.out.println("valida telefono:" + telefono.getPais().getCodPais());

		if (telefono.getPais().getCodPais().equals(Constantes.PAIS_ID_ECUADOR)) {
			String nroTelefono = telefono.getCodArea() + telefono.getNroTelefono();

			System.out.println("nimero:" + nroTelefono);

			boolean valida = false;
			try {
				valida = wsDatosPersonaServicio.validarTelefono(nroTelefono);
				if (!valida) {
					String mensaje = getBundleMensajes("numero.telefono.incorrecto", null);
					addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
					return;

				}

			} catch (RemoteException e) {
				e.printStackTrace();
				String mensaje = getBundleMensajes("error.web.service", null);
				addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
				System.out.println(e);
			} catch (ServiceException e) {
				e.printStackTrace();
				String mensaje = getBundleMensajes("error.web.service", null);
				addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
				System.out.println(e);
			}
		}
	}

	public void verificarEmail(AjaxBehaviorEvent event) {

		DireccionElectronica email = (DireccionElectronica) getExternalContext().getRequestMap().get("b");

		boolean valida = false;
		try {
			valida = wsDatosPersonaServicio.validarEmail(email.getDireccionElectronica());
			if (!valida) {
				String mensaje = getBundleMensajes("email.incorrecto", null);
				addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
				return;

			}

		} catch (RemoteException e) {
			e.printStackTrace();
			String mensaje = getBundleMensajes("error.web.service", null);
			addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
			String mensaje = getBundleMensajes("error.web.service", null);
			addErrorMessage(event.getComponent().getClientId(), mensaje, mensaje);
			e.printStackTrace();
		}

	}

	/**
	 * @return the habitoEnfermedadPorPersonaLista
	 */
	public Collection<HabitoEnfermedad> getHabitoEnfermedadPorPersonaLista() {
		if (habitoEnfermedadPorPersonaLista == null) {

			// Collection<TipoHabitoEnfermedad> tipoHabitoLista =
			// tipoHabitoEnfermedadServicio.findAll();
			Collection<TipoHabitoEnfermedad> tipoHabitoLista = tipoHabitoEnfermedadServicio
					.buscarPorCategoria(sesionCtrl.getEmpresa().getCategorias());

			habitoEnfermedadPorPersonaLista = new ArrayList<HabitoEnfermedad>();

			// cuando es nuevo
			if (getNuevo()) {

				for (TipoHabitoEnfermedad tipoHabitoEnfermedad : tipoHabitoLista) {
					// if (tipoHabitoEnfermedad.getSexo() !=
					// Constantes.ID_SEXO_TODOS
					// && tipoHabitoEnfermedad.getSexo() != getPersonaNatural()
					// .getSexo()) {
					// continue;// en este caso no se anade al listado de
					// preguntas
					// }
					HabitoEnfermedad habitoEnfermedad = new HabitoEnfermedad();
					habitoEnfermedad.setPersonaNatural(personaNatural);
					habitoEnfermedad.setTipoHabitoEnfermedad(tipoHabitoEnfermedad);
					// respuesta no aplica por defecto
					habitoEnfermedad.setRespuesta(Constantes.ID_NINGUNO);

					if (tipoHabitoEnfermedad.getDetallar() == RespuestaEnum.SI.getCodigo()) {
						List<DetalleHabitoEnfermedad> detalles = new ArrayList<DetalleHabitoEnfermedad>();

						// burscar las preguntas de este tipoHabito
						/*
						 * String[] criteriasAnd = { "tipoHabitoEnfermedad.codTipoHabito", "categoria"
						 * }; CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.SHORT_EQUALS,
						 * CriteriaTypeEnum.STRING_IN_LIST }; Object[] params = new Object[] {
						 * tipoHabitoEnfermedad.getCodTipoHabito(),
						 * sesionCtrl.getEmpresa().getCategoriasList() }; Criteria criteria = new
						 * Criteria(criteriasAnd, typesAnd, params);
						 */

						// List<PreguntaHabitoEnfermedad> preguntas = preguntaHabitoEnfermedadServicio
						// .findByCriterias(criteria);
						Collection<PreguntaHabitoEnfermedad> preguntas = preguntaHabitoEnfermedadServicio
								.buscarPorCategoria(tipoHabitoEnfermedad.getCodTipoHabito(),
										sesionCtrl.getEmpresa().getCategorias());

						for (PreguntaHabitoEnfermedad phe : preguntas) {
							// for e preguntas, crear un DetalleHabitoEnfermedad
							// por cada pregunta
							DetalleHabitoEnfermedad detalleHabitoEnfermedad = new DetalleHabitoEnfermedad();
							// HabitoEnfermedad he = new HabitoEnfermedad(
							// habitoEnfermedad.getSecHabitoEnfermedad());
							detalleHabitoEnfermedad.setHabitoEnfermedad(habitoEnfermedad);

							PreguntaHabitoEnfermedad pregunta = new PreguntaHabitoEnfermedad(phe.getCodPregunta(),
									phe.getPregunta(), phe.getTipoHabitoEnfermedad(), phe.getTipoDato(),
									phe.getLimiteInferior(), phe.getLimiteSuperior(), phe.getCategoria());

							detalleHabitoEnfermedad.setPreguntaHabitoEnfermedad(pregunta);
							// detalleHabitoEnfermedad
							// .setDetalle("x"
							// + preguntaHabitoEnfermedad
							// .getCodPregunta());// porq
							// al
							// guardar
							// sale null
							// anadir a detalles
							detalleHabitoEnfermedad.setDetalle("");

							detalles.add(detalleHabitoEnfermedad);
						} // fin for

						habitoEnfermedad.setDetalleHabitoEnfermedadFormularioCollection(detalles);
					}

					habitoEnfermedadPorPersonaLista.add(habitoEnfermedad);
				}
			} else {
				// cuando es edicion
				// paramSecPersonaNatural

				String[] criteriasAnd = { "personaNatural.secPersonaNatural" };
				CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
				Object[] params = new Object[] { paramSecPersonaNatural };
				Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

				habitoEnfermedadPorPersonaLista = new ArrayList<HabitoEnfermedad>();

				Collection<HabitoEnfermedad> habitoEnfermedadColl = habitoEnfermedadServicio.findByCriterias(criteria);

				for (TipoHabitoEnfermedad tipoHabitoEnfermedad : tipoHabitoLista) {

					HabitoEnfermedad hb = null;

					for (HabitoEnfermedad bdd : habitoEnfermedadColl) {
						if (bdd.getTipoHabitoEnfermedad().getCodTipoHabito()
								.compareTo(tipoHabitoEnfermedad.getCodTipoHabito()) == 0) {
							hb = bdd;
							// hb.getDetalleHabitoEnfermedadFormularioCollection()
							break;
						}
					}

					// si no encontro antes contestada
					if (hb == null) {
						hb = new HabitoEnfermedad();
						hb.setPersonaNatural(personaNatural);
						hb.setRespuesta(Constantes.ID_NINGUNO);
						hb.setTipoHabitoEnfermedad(tipoHabitoEnfermedad);

					}

					if (tipoHabitoEnfermedad.getDetallar() == RespuestaEnum.SI.getCodigo()) {
						List<DetalleHabitoEnfermedad> detalles = new ArrayList<DetalleHabitoEnfermedad>();

						// burscar las preguntas de este tipoHabito
						/*
						 * String[] criteriasAndP = { "tipoHabitoEnfermedad.codTipoHabito", "categoria"
						 * }; CriteriaTypeEnum[] typesAndP = { CriteriaTypeEnum.SHORT_EQUALS,
						 * CriteriaTypeEnum.STRING_IN_LIST }; Object[] paramsP = new Object[] {
						 * tipoHabitoEnfermedad.getCodTipoHabito(),
						 * sesionCtrl.getEmpresa().getCategoriasList() }; Criteria criteriaP = new
						 * Criteria(criteriasAndP, typesAndP, paramsP);
						 * 
						 * List<PreguntaHabitoEnfermedad> preguntas = preguntaHabitoEnfermedadServicio
						 * .findByCriterias(criteriaP);
						 */

						Collection<PreguntaHabitoEnfermedad> preguntas = preguntaHabitoEnfermedadServicio
								.buscarPorCategoria(tipoHabitoEnfermedad.getCodTipoHabito(),
										sesionCtrl.getEmpresa().getCategorias());

						if (hb.getSecHabitoEnfermedad() != null) {
							// burscar las respondidas
							String[] criteriasAndD = { "habitoEnfermedad.secHabitoEnfermedad" };
							CriteriaTypeEnum[] typesAndD = { CriteriaTypeEnum.INTEGER_EQUALS };
							Object[] paramsD = new Object[] { hb.getSecHabitoEnfermedad() };
							Criteria criteriaD = new Criteria(criteriasAndD, typesAndD, paramsD);
							List<DetalleHabitoEnfermedad> detallesBDD = detalleHabitoEnfermedadServicio
									.findByCriterias(criteriaD);

							detalles = detallesBDD;

							// System.out
							// .println("total detaless respondidos anteriormente:"
							// + detalles.size());

							// hb.setDetalleHabitoEnfermedadFormularioCollection(detallesBDD);

						} else {

							for (PreguntaHabitoEnfermedad phe : preguntas) {
								DetalleHabitoEnfermedad detalleHabitoEnfermedad = new DetalleHabitoEnfermedad();
								// HabitoEnfermedad he = new HabitoEnfermedad(
								// habitoEnfermedad.getSecHabitoEnfermedad());
								detalleHabitoEnfermedad.setHabitoEnfermedad(hb);

								PreguntaHabitoEnfermedad pregunta = new PreguntaHabitoEnfermedad(phe.getCodPregunta(),
										phe.getPregunta(), phe.getTipoHabitoEnfermedad(), phe.getTipoDato(),
										phe.getLimiteInferior(), phe.getLimiteSuperior(), phe.getCategoria());

								detalleHabitoEnfermedad.setPreguntaHabitoEnfermedad(pregunta);

								// detalleHabitoEnfermedad
								// .setDetalle("x"
								// + preguntaHabitoEnfermedad
								// .getCodPregunta());// porq
								// al
								// guardar
								// sale null
								// anadir a detalles
								detalleHabitoEnfermedad.setDetalle("");

								detalles.add(detalleHabitoEnfermedad);
							}

						}

						hb.setDetalleHabitoEnfermedadFormularioCollection(detalles);

					}

					habitoEnfermedadPorPersonaLista.add(hb);

				}
			}

		}

		return habitoEnfermedadPorPersonaLista;
	}

	/**
	 * @param habitoEnfermedadPorPersonaLista the habitoEnfermedadPorPersonaLista to
	 *                                        set
	 */
	public void setHabitoEnfermedadPorPersonaLista(Collection<HabitoEnfermedad> habitoEnfermedadPorPersonaLista) {
		this.habitoEnfermedadPorPersonaLista = habitoEnfermedadPorPersonaLista;
	}

	/**
	 * provincia direccion
	 * 
	 * @param pref
	 * @return
	 */
	public List<Provincia> autocompleteProvinciaD(String pref) {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");
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
		Direccion e = (Direccion) getExternalContext().getRequestMap().get("dir");
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

		Direccion d = (Direccion) getExternalContext().getRequestMap().get("dir");

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

	public List<String> autocompleteCalleSecundariaConLW(String pref) {
		// String j="[{\"Id\":110117,\"Name\":\"Abanin -
		// Azuay\",\"PhonePrefix\":\"00\",\"Zone\":null},{\"Id\":11647,\"Name\":\"Abanin
		// - El Oro\",\"PhonePrefix\":\"00\",\"Zone\":null}]"; //$NON-NLS-1$

		Direccion d = (Direccion) getExternalContext().getRequestMap().get("dir");

		// Short codPais = e.getCanton().getProvincia().getPais().getCodPais();

		List<String> listaR = new ArrayList<String>();

		try {

			if (d.getCiudad() == null || d.getCiudad().trim().equals("")) {
				System.out.println("no ha seleccionado ciudad:" + d.getCiudad());
				return listaR;
				// throw new JSONException("no ha seleccionado ciudad");
			}

			if (d.getPrincipal() == null || d.getPrincipal().trim().equals("")) {
				System.out.println("no ha seleccionado calle principal:" + d.getPrincipal());
				return listaR;
				// throw new JSONException("no ha seleccionado calle principal");
			}

			String webHandlerCallesSecundarias = LWResources.getString("calles.secundarias.webhandler"); //$NON-NLS-1$

			String ciudad = URLEncoder.encode(d.getCiudad(), "utf-8");

			webHandlerCallesSecundarias = webHandlerCallesSecundarias.replace("@ciudad", ciudad);

			String calleutf8 = URLEncoder.encode(d.getPrincipal(), "utf-8");
			webHandlerCallesSecundarias = webHandlerCallesSecundarias.replace("@calle", calleutf8);

			String calleSutf8 = URLEncoder.encode(pref, "utf-8");

			webHandlerCallesSecundarias = webHandlerCallesSecundarias.replace("@secundaria", calleSutf8);

			URL url = new URL(webHandlerCallesSecundarias);
			String jsonCallesSecundarias = ""; //$NON-NLS-1$

			URLConnection urlConn = url.openConnection();
			// urlConn.setRequestProperty("User-Agent",
			// "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.3)
			// Gecko/20100401");
			// // Do as if you're using Firefox 3.6.3.
			// urlConn.setDoOutput(true);
			// urlConn.setConnectTimeout(5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				jsonCallesSecundarias += inputLine;
			}

			in.close();

			JSONArray jsonArray = new JSONArray(jsonCallesSecundarias);
			int length = jsonArray.length();
			for (int cont = 0; cont < length; cont++) {
				String calleS = jsonArray.getString(cont);
				listaR.add(calleS);
			}

		} catch (JSONException e) {
			e.printStackTrace();
			// j="";System.out
			System.out.println("Error Connection Host: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
					+ e);

		} catch (java.net.UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Error Connection Host: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
					+ e);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			System.out.println("Error Connection: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
					+ e);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Error1: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
					+ e);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error2: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
					+ e);
		}

		return listaR;
	}

	/**
	 * parroquia fecha de nacimiento
	 * 
	 * @param pref
	 * @return
	 */
	public List<Ciudad> autocompleteCiudadFN(String pref) {
		Short codPais = personaNatural.getCiudadNacimiento().getPais().getCodPais();
		if (codPais == null) {
			return new ArrayList<Ciudad>();
		}
		return buscarCiudades(pref, codPais);
	}

	public boolean isMostrarPanelNombreInvalido() {
		return mostrarPanelNombreInvalido;
	}

	public void setMostrarPanelNombreInvalido(boolean mostrarPanelNombreInvalido) {
		this.mostrarPanelNombreInvalido = mostrarPanelNombreInvalido;
	}

	public boolean isMostrarPanelNombreSugerencia() {
		return mostrarPanelNombreSugerencia;
	}

	public void setMostrarPanelNombreSugerencia(boolean mostrarPanelNombreSugerencia) {
		this.mostrarPanelNombreSugerencia = mostrarPanelNombreSugerencia;
	}

	public ResultadoWebserviceNombresDto getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoWebserviceNombresDto resultado) {
		this.resultado = resultado;
	}

	public boolean isYaExiste() {
		return yaExiste;
	}

	public void setYaExiste(boolean yaExiste) {
		this.yaExiste = yaExiste;
	}

	public CiudadLWAutocompleteCtrl getCiudadLWAutocompleteCtrl() {
		return ciudadLWAutocompleteCtrl;
	}

	public void setCiudadLWAutocompleteCtrl(CiudadLWAutocompleteCtrl ciudadLWAutocompleteCtrl) {
		this.ciudadLWAutocompleteCtrl = ciudadLWAutocompleteCtrl;
	}

	public void crearDeporte(ActionEvent event) {
		DeportePractica dp = new DeportePractica();
		dp.setPersonaNatural(personaNatural);
		dp.setDeporte(new Deporte());
		dp.setTipoPractica(TipoDeportePracticaEnum.AFICIONADO.getCodigo());
		personaNatural.getDeportePracticaCollection().add(dp);
	}

	public void crearEstadoPersona(ActionEvent event) {
		EstadoPersona ep = new EstadoPersona();
		ep.setPersonaNatural(personaNatural);
		ep.setObservaciones("");
		ep.setTipoEstado(new TipoEstado());
		personaNatural.getEstadoPersonaCollection().add(ep);
	}

	public void crearHistoriaFamiliar(ActionEvent event) {
		HistoriaMedicaFamiliar hmf = new HistoriaMedicaFamiliar();
		hmf.setPersonaNatural(personaNatural);
		TipoParentescoRelacion tipoHermano = tipoParentescoRelacionServicio.findByPk(Constantes.TIPO_RELACION_HERMANO);
		hmf.setTipoParentescoRelacion(tipoHermano);
		personaNatural.getHistoriaMedicaFamiliarFormularioCollection().add(hmf);

	}

	public void eliminarDeporte(ActionEvent event) {
		DeportePractica dp = (DeportePractica) getExternalContext().getRequestMap().get("d");
		personaNatural.getDeportePracticaCollection().remove(dp);
	}

	public void eliminarEstadoPersona(ActionEvent event) {
		EstadoPersona ep = (EstadoPersona) getExternalContext().getRequestMap().get("ep");
		personaNatural.getEstadoPersonaCollection().remove(ep);
	}

	public void eliminarHistoriaFamiliar(ActionEvent event) {
		HistoriaMedicaFamiliar hmf = (HistoriaMedicaFamiliar) getExternalContext().getRequestMap().get("hmf");
		personaNatural.getHistoriaMedicaFamiliarFormularioCollection().remove(hmf);
	}

	public void calcularEnKilos() {

		BigDecimal pesoKilos = personaNatural.getBiometrica().getPesoKilos();

		System.out.println("peso kilos:" + pesoKilos);

		if (pesoKilos == null || pesoKilos.compareTo(BigDecimal.ZERO) == 0) {
			System.out.println("calcula en libras :)");
			personaNatural.getBiometrica()
					.setPesoKilos(EquivalenciaKilosLibras.getKilos(personaNatural.getBiometrica().getPesoLibras()));
		}
	}

	public void calcularEnLibras() {
		personaNatural.getBiometrica()
				.setPesoLibras(EquivalenciaKilosLibras.getLibras(personaNatural.getBiometrica().getPesoKilos()));
	}

	public void calcularGanadosEnKilos() {
		BigDecimal pesoKilos = personaNatural.getBiometrica().getGanadosUltAnioKilos();
		if (pesoKilos == null || pesoKilos.compareTo(BigDecimal.ZERO) == 0) {
			personaNatural.getBiometrica().setGanadosUltAnioKilos(
					EquivalenciaKilosLibras.getKilos(personaNatural.getBiometrica().getGanadosUltAnioLibras()));
		}
	}

	public void calcularGanadosEnLibras() {
		int pesoLibras = EquivalenciaKilosLibras.getLibras(personaNatural.getBiometrica().getGanadosUltAnioKilos());
		personaNatural.getBiometrica().setGanadosUltAnioLibras(pesoLibras);
	}

	public void calcularPerdidosEnKilos() {
		BigDecimal pesoKilos = personaNatural.getBiometrica().getPerdidosUltAnioKilos();

		if (pesoKilos == null || pesoKilos.compareTo(BigDecimal.ZERO) == 0) {
			personaNatural.getBiometrica().setPerdidosUltAnioKilos(
					EquivalenciaKilosLibras.getKilos(personaNatural.getBiometrica().getPerdidosUltAnioLibras()));
		}

	}

	public void calcularPerdidosEnLibras() {
		int pesoLibras = EquivalenciaKilosLibras.getLibras(personaNatural.getBiometrica().getPerdidosUltAnioKilos());
		personaNatural.getBiometrica().setPerdidosUltAnioLibras(pesoLibras);
	}

	public GeocodeAddressLW encontrarGeocode(String ciudad, String principal, String secundaria) {

		GeocodeAddressLW geocodeAddressLW = null;

		String webHandlerGeocode = LWResources.getString("geocode.webhandler");

		try {

			String ciudadE = URLEncoder.encode(ciudad, "utf-8");
			String principalE = URLEncoder.encode(principal, "utf-8");
			String secundariaE = URLEncoder.encode(secundaria, "utf-8");

			webHandlerGeocode = webHandlerGeocode.replace("@ciudad", ciudadE);
			webHandlerGeocode = webHandlerGeocode.replace("@calle", principalE);
			webHandlerGeocode = webHandlerGeocode.replace("@secundaria", secundariaE);

			String jsonCalles = ""; //$NON-NLS-1$

			URL url = new URL(webHandlerGeocode);

			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setConnectTimeout(5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				jsonCalles += inputLine;
			}

			in.close();

			if (!jsonCalles.equals("'null'")) {
				JSONObject obj = new JSONObject(jsonCalles);
				// int length = jsonArray.length();
				// for (int cont = 0; cont < length; cont++) {
				// JSONObject obj = jsonArray.getJSONObject(cont);
				if (obj != null) {

					JSONObject objCoord = (JSONObject) obj.get("Coordinates");

					BigDecimal latitude = new BigDecimal(objCoord.get("Latitude").toString());
					System.out.println("latitude" + latitude);
					BigDecimal longitude = new BigDecimal(objCoord.get("Longitude").toString());
					System.out.println("longitude" + longitude);
					geocodeAddressLW = new GeocodeAddressLW(latitude, longitude);
				}
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

		return geocodeAddressLW;
	}

	public String nuevaPersonaNatural() {

		sesionCtrl.setMensajeRcsDto(null);

		if (isUserInRole(PerfilEquividaEnum.CU_SERV_CLIENTE.toString())) {
			addInfoMessage(null, "No tiene permisos", "No tiene permisos");
			return null;
		}

		getSession().removeAttribute("dto");
		nuevo = true;
		personaNatural = null;
		// persona = null;
		habilitarBtnGuardar = true;

		if (getSesionCtrl().getEmpresa().equals(EmpresaEnum.EQUIVIDA)) {
			return "/pages/formularios/personaNatural.jsf?faces-redirect=true";
		} else {
			return "/pages/formularios/personaNaturalColvida.jsf?faces-redirect=true";
		}
	}

	public boolean isHabilitarBtnGuardar() {
		if (isUserInRole(PerfilEquividaEnum.ConsultaCU.toString())) {
			habilitarBtnGuardar = false;
		}
		return habilitarBtnGuardar;
	}

	public void setHabilitarBtnGuardar(boolean habilitarBtnGuardar) {
		this.habilitarBtnGuardar = habilitarBtnGuardar;
	}

	public SesionCtrl getSesionCtrl() {
		return sesionCtrl;
	}

	public void setSesionCtrl(SesionCtrl sesionCtrl) {
		this.sesionCtrl = sesionCtrl;
	}

	private boolean isObligatorioIdentificacionConyuge() {
		boolean obligatorio = true;
		if (sesionCtrl.getFormularioSeleccionado().equals(TipoFormularioEnum.ASOCIACION.getSecuencial())) {
			obligatorio = false;
		}
		return obligatorio;
	}

	// private boolean isObligatorioReferenciaFamiliarNoVivaConUd() {
	// boolean obligatorio = true;
	// if (sesionCtrl.getFormularioSeleccionado().equals(
	// TipoFormularioEnum.ASOCIACION.getSecuencial())) {
	// obligatorio = false;
	// }
	// return obligatorio;
	// }

	public List<RsConductoPago> getConductoDePagoLista() {
		if (conductoDePagoLista == null) {
			if (!getNuevo()) {
				Long idPersonaSise = personaEquividaServicio.obtenerIdPersonaDestinoPN(
						personaNatural.getPersona().getSecPersona(), personaNatural.getSecPersonaNatural(),
						Constantes.COD_SISTEMA_SISE, Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);
				System.out.println("conducto idPersonaSise:" + idPersonaSise);
				if (idPersonaSise != null) {
					conductoDePagoLista = siseServicio.consultarConductosDePago(idPersonaSise);
				}
			}
		}
		return conductoDePagoLista;
	}

	public void setConductoDePagoLista(List<RsConductoPago> conductoDePagoLista) {
		this.conductoDePagoLista = conductoDePagoLista;
	}

	public boolean isSexoAdvertencia() {
		return sexoAdvertencia;
	}

	public void setSexoAdvertencia(boolean sexoAdvertencia) {
		this.sexoAdvertencia = sexoAdvertencia;
	}

	/**
	 * @return the conyugeDelConyuge
	 */
	public String getConyugeDelConyuge() {
		return conyugeDelConyuge;
	}

	/**
	 * @param conyugeDelConyuge the conyugeDelConyuge to set
	 */
	public void setConyugeDelConyuge(String conyugeDelConyuge) {
		this.conyugeDelConyuge = conyugeDelConyuge;
	}

	/**
	 * @return the tipoEmpleoNuevo
	 */
	public Short getTipoEmpleoNuevo() {
		return tipoEmpleoNuevo;
	}

	/**
	 * @param tipoEmpleoNuevo the tipoEmpleoNuevo to set
	 */
	public void setTipoEmpleoNuevo(Short tipoEmpleoNuevo) {
		this.tipoEmpleoNuevo = tipoEmpleoNuevo;
	}

	public List<RsCumulosDePago> getConsultaCumulosLista() {
		if (consultaCumulosLista == null) {

			// System.out.println("consultando polizas...");
			String nroDoc = personaNatural.getIdentificacion();

			consultaCumulosLista = siseServicio.consultar(nroDoc);

			if (consultaCumulosLista == null) {
				if (nroDoc != null && !nroDoc.trim().equals("")) {
					String m = "No se ha podido consultar p\u00F3lizas";
					addErrorMessage(null, m, m);
					consultaCumulosLista = new ArrayList<RsCumulosDePago>();
				}
			} else {
				for (RsCumulosDePago c : consultaCumulosLista) {
					if (c != null && c.getEstado() != null) {
						if (c.getEstado().equals("VIGENTE")) {
							totalMontoMyda = totalMontoMyda + c.getMontoMyda().doubleValue();
							totalMontoVida = totalMontoVida + c.getMontoVida().doubleValue();
						}
					}
				}
			}

			// System.out.println("... fin consultando polizas");

		}
		return consultaCumulosLista;
	}

	public void setConsultaCumulosLista(List<RsCumulosDePago> consultaCumulosLista) {
		this.consultaCumulosLista = consultaCumulosLista;
	}

	public PersonaNatural getPersonaNaturalPopUp() {
		return personaNaturalPopUp;
	}

	public void setPersonaNaturalPopUp(PersonaNatural personaNaturalPopUp) {
		this.personaNaturalPopUp = personaNaturalPopUp;
	}

	// Metodos para pasar informacion desde PopUp a Formulario

	/**
	 * Pasa los datos personales del popUp al formulario.
	 * 
	 * @param e
	 */
	public void pasarDatosPersonales(ActionEvent e) {
		if (this.personaNatural != null) {
			if (this.personaNaturalPopUp != null) {
				this.personaNatural.setPrimerNombre(this.personaNaturalPopUp.getPrimerNombre());
				this.personaNatural.setSegundoNombre(this.personaNaturalPopUp.getSegundoNombre());
				this.personaNatural.setApellidoPaterno(this.personaNaturalPopUp.getApellidoPaterno());
				this.personaNatural.setApellidoMaterno(this.personaNaturalPopUp.getApellidoMaterno());
				this.personaNatural.setFchNacimiento(this.personaNaturalPopUp.getFchNacimiento());
				this.personaNatural.setSexo(this.personaNaturalPopUp.getSexo());
				if (this.personaNaturalPopUp.getCiudadNacimiento() != null) {
					this.personaNatural.setCiudadNacimiento(this.personaNaturalPopUp.getCiudadNacimiento());
				}
			}
		}
	}

	/**
	 * Pasar los datos del estado civil del popUp al formulario.
	 * 
	 * @param e
	 */
	public void pasarEstadoCivil(ActionEvent e) {
		if (this.personaNaturalPopUp.getEstadoCivil() != null) {
			this.personaNatural.setEstadoCivil(this.personaNaturalPopUp.getEstadoCivil());

			if (this.personaNaturalPopUp.getConyuge() != null) {
				if (this.personaNaturalPopUp.isConyugeCompleto()) {
					this.personaNatural.getConyuge()
							.setIdentificacion(this.personaNaturalPopUp.getConyuge().getIdentificacion());
					this.personaNatural.getConyuge()
							.setPrimerNombre(this.personaNaturalPopUp.getConyuge().getPrimerNombre());
					this.personaNatural.getConyuge()
							.setSegundoNombre(this.personaNaturalPopUp.getConyuge().getSegundoNombre());
					this.personaNatural.getConyuge()
							.setApellidoPaterno(this.personaNaturalPopUp.getConyuge().getApellidoPaterno());
					this.personaNatural.getConyuge()
							.setApellidoMaterno(this.personaNaturalPopUp.getConyuge().getApellidoMaterno());
				} else {
					this.personaNatural.getConyuge()
							.setIdentificacion(this.personaNaturalPopUp.getConyuge().getIdentificacion());

					String[] nombresApellidos = this.personaNaturalPopUp.getConyuge()
							.getNombresApellidosConyugeIncompleto().split(" ");

					if (nombresApellidos.length == 4) {
						this.personaNatural.getConyuge().setApellidoPaterno(nombresApellidos[0]);
						this.personaNatural.getConyuge().setApellidoMaterno(nombresApellidos[1]);
						this.personaNatural.getConyuge().setPrimerNombre(nombresApellidos[2]);
						this.personaNatural.getConyuge().setSegundoNombre(nombresApellidos[3]);
					} else if (nombresApellidos.length >= 4) {
						this.personaNatural.getConyuge().setApellidoPaterno(nombresApellidos[0]);
						this.personaNatural.getConyuge().setApellidoMaterno(nombresApellidos[1]);
						this.personaNatural.getConyuge().setPrimerNombre(nombresApellidos[2]);

						StringBuffer textofinal = new StringBuffer(150);

						for (int i = 3; i < nombresApellidos.length; i++) {
							textofinal.append(nombresApellidos[i]).append(" ");
						}
						this.personaNatural.getConyuge().setSegundoNombre(textofinal.toString());

					} else if (nombresApellidos.length == 1) {
						this.personaNatural.getConyuge().setApellidoPaterno(nombresApellidos[0]);
					} else if (nombresApellidos.length == 2) {
						this.personaNatural.getConyuge().setApellidoPaterno(nombresApellidos[0]);
						this.personaNatural.getConyuge().setApellidoMaterno(nombresApellidos[1]);
					} else if (nombresApellidos.length == 3) {
						this.personaNatural.getConyuge().setApellidoPaterno(nombresApellidos[0]);
						this.personaNatural.getConyuge().setApellidoMaterno(nombresApellidos[1]);
						this.personaNatural.getConyuge().setPrimerNombre(nombresApellidos[2]);
					}
				}
			}
		}
	}

	public void pasarUbicacionGeografica(ActionEvent e) {
		Direccion d = (Direccion) getExternalContext().getRequestMap().get("d");
		Short cantonViene = d.getCanton() != null ? d.getCanton().getSecCanton() : new Short("-1");
		String direccionViene = "";
		if (d.getPrincipal() != null && d.getPrincipal().trim().length() > 0) {
			direccionViene = d.getPrincipal();
		} else if (d.getDireccionCompleta() != null && d.getDireccionCompleta().trim().length() > 0) {
			direccionViene = d.getDireccionCompleta();
		}

		boolean existe = false;

		if (this.personaNatural.getPersona().getDireccionCollection() != null) {
			for (Direccion di : this.personaNatural.getPersona().getDireccionCollection()) {
				Short cantonItera = di.getCanton() != null ? di.getCanton().getSecCanton() : new Short("-1");
				String direccionItera = di.getPrincipal() != null ? di.getPrincipal().trim() : "";

				if (cantonItera != null && cantonViene != null && cantonItera.equals(cantonViene)
						&& direccionItera.equals(direccionViene)) {
					existe = true;
					break;
				}
			}
		}

		if (!existe) {
			d.setEstado(EstadoEnum.ACTIVO.getCodigo());
			d.setPersona(this.personaNatural.getPersona());
			d.setSecDireccion(null);

			if (!d.isTieneDireccionCompleta()) {
				d.setPrincipal(d.getDireccionCompleta());
			}

			// Se verifca que la lista no sea nula
			if (this.personaNatural.getPersona().getDireccionCollection() == null) {
				this.personaNatural.getPersona().setDireccionCollection(new ArrayList<Direccion>());
			}

			Collection<DireccionTelefono> dtLista = d.getDireccionTelefonoCollection();
			for (DireccionTelefono dt : dtLista) {
				dt.setPersona(this.personaNatural.getPersona());
				dt.getDireccion().setPersona(this.personaNatural.getPersona());
				dt.getTelefono().setPersona(this.personaNatural.getPersona());
			}

			this.personaNatural.getPersona().getDireccionCollection().add(d);
		} else {
			addInfoMessage(null, getBundleMensajes("direccion.existe.formulario", null), null);
		}
	}

	public void pasarTelefonoSinDireccion(ActionEvent e) {
		Telefono t = (Telefono) getExternalContext().getRequestMap().get("t");

		boolean existe = false;

		if (personaNatural.getPersona().getTelefonoSinDireccionCollection() != null) {
			for (Telefono te : personaNatural.getPersona().getTelefonoSinDireccionCollection()) {
				if (te.getNroTelefono().equals(t.getNroTelefono())) {
					existe = true;
					break;
				}
			}
		}

		if (!existe) {
			if (personaNatural.getPersona().getTelefonoSinDireccionCollection() == null) {
				personaNatural.getPersona().setTelefonoSinDireccionCollection(new ArrayList<Telefono>());
			}

			t.setEstado(EstadoEnum.ACTIVO.getCodigo());
			t.setPersona(personaNatural.getPersona());
			t.setSecTelefono(null);
			t.setPais(new Pais());

			personaNatural.getPersona().getTelefonoSinDireccionCollection().add(t);
		} else {
			Object[] param = { t.getNroTelefono() };
			addInfoMessage(null, getBundleMensajes("telefono.existe.formulario", param), null);
		}
	}

	public void pasarEmpleoDependiente(ActionEvent e) {
		EmpleoDependiente ed = (EmpleoDependiente) getExternalContext().getRequestMap().get("item");

		String negocioViene = ed.getNegocioEmpresa() != null ? ed.getNegocioEmpresa().trim() : "";
		String cargoViene = ed.getCargo() != null ? ed.getCargo().trim() : "";

		boolean existe = false;
		if (this.personaNatural.getEmpleoCollection() != null) {
			for (EmpleoDto d : this.personaNatural.getEmpleoCollection()) {
				String negocioItera = d.getNegocioEmpresaNombre() != null ? d.getNegocioEmpresaNombre().trim() : "";
				String cargoItera = d.getCargo() != null ? d.getCargo().trim() : "";

				if (negocioViene.equals(negocioItera) && cargoViene.equals(cargoItera)) {

					if (EstadoEnum.INACTIVO.getCodigo() == d.getEstado()) {
						d.setActividadEconomicaId(null);
						d.setActividadEconomicaNombre("");
						d.setTiempoEmpresa(ed.getTiempoEmpresa());
					}
					d.setEstado(EstadoEnum.ACTIVO.getCodigo());

					existe = true;
					break;
				}
			}
		}

		if (this.personaNatural.getIngresoMensual() == null) {
			IngresoMensual ingresoMensual = new IngresoMensual();
			ingresoMensual.setPersonaNatural(this.personaNatural);

			this.personaNatural.setIngresoMensual(ingresoMensual);
		}

		// Se pasa el valor del salario del emplo a ingreso mensual
		this.personaNatural.getIngresoMensual()
				.setMntIngresoMensual(ed.getSalarioTr() != null ? ed.getSalarioTr() : BigDecimal.ZERO);

		if (!existe) {
			if (this.personaNatural.getEmpleoCollection() == null) {
				this.personaNatural.setEmpleoCollection(new ArrayList<EmpleoDto>());
			}

			EmpleoDto dto = new EmpleoDto();
			dto.setEstado(EstadoEnum.ACTIVO.getCodigo());
			dto.setTipoEmpleo(TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo());
			dto.setTipoEmpleoNombre(TipoEmpleoEnum.DEPENDIENTE.getKeyBundle());
			dto.setCargo(ed.getCargo());
			dto.setCodTiempo('A');
			dto.setSecEmpleo(null);
			dto.setNegocioEmpresaNombre(ed.getNegocioEmpresa());
			dto.setTiempoEmpresa(ed.getTiempoEmpresa());
			dto.setActividadEconomicaId(null);
			dto.setActividadEconomicaNombre("");

			// Se comenta todo por solicitud, siempre va a ir en blano
			/*
			 * if (ed.getActividadEconomica() != null &&
			 * ed.getActividadEconomica().getCodActividadEconomica() != null) {
			 * ActividadEconomica ae = actividadEconomicaServicio.findByPk(ed
			 * .getActividadEconomica().getCodActividadEconomica());
			 * 
			 * dto.setActividadEconomicaId(ae.getCodActividadEconomica());
			 * dto.setActividadEconomicaNombre(ae.getActividadEconomica()); }
			 */

			this.personaNatural.getEmpleoCollection().add(dto);
		} else {
			Object[] params = { negocioViene, cargoViene };
			addInfoMessage(null, getBundleMensajes("empleo.dependiente.existe.formulario", params), null);
		}
	}

	public void pasarEmpleoIndependiente(ActionEvent e) {
		// Se debe buscar si el empleo independiente ya esta en el registro
		EmpleoIndependiente ei = (EmpleoIndependiente) getExternalContext().getRequestMap().get("item");

		String cargoViene = ei.getCargo() != null ? ei.getCargo().trim() : "";

		if (this.personaNatural.getEmpleoCollection() == null) {
			this.personaNatural.setEmpleoCollection(new ArrayList<EmpleoDto>());
		}

		boolean existe = false;
		for (EmpleoDto d : this.personaNatural.getEmpleoCollection()) {
			String cargoItera = d.getCargo() != null ? d.getCargo().trim() : "";
			if (cargoViene.equals(cargoItera)) {

				// Si el cargo ya existe pero estaba inactivo
				if (EstadoEnum.INACTIVO.getCodigo() == d.getEstado()) {
					d.setActividadEconomicaId(null);
					d.setActividadEconomicaNombre("");
					d.setTiempoEmpresa(ei.getTiempoEmpresa());
				}

				d.setEstado(EstadoEnum.ACTIVO.getCodigo());
				existe = true;
				break;
			}
		}

		if (!existe) {
			EmpleoDto dto = new EmpleoDto();
			dto.setEstado(EstadoEnum.ACTIVO.getCodigo());
			dto.setTipoEmpleo(TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo());
			dto.setTipoEmpleoNombre(TipoEmpleoEnum.INDEPENDIENTE.getKeyBundle());
			dto.setTiempoEmpresa(ei.getTiempoEmpresa());
			dto.setCargo(ei.getCargo());
			dto.setNegocioEmpresaNombre(ei.getPersonaNatural().getApellidosNombres());
			dto.setActividadEconomicaId(null);
			dto.setActividadEconomicaNombre("");
			// Se comenta, siempre va a ir en blanco
			/*
			 * if (ei.getActividadEconomica() != null &&
			 * ei.getActividadEconomica().getCodActividadEconomica() != null) {
			 * ActividadEconomica ae = actividadEconomicaServicio.findByPk(ei
			 * .getActividadEconomica().getCodActividadEconomica());
			 * dto.setActividadEconomicaId(ae.getCodActividadEconomica());
			 * dto.setActividadEconomicaNombre(ae.getActividadEconomica()); } else {
			 * ActividadEconomica ae = actividadEconomicaServicio
			 * .findByPk(Short.valueOf("0"));
			 * dto.setActividadEconomicaId(ae.getCodActividadEconomica());
			 * dto.setActividadEconomicaNombre(ae.getActividadEconomica()); }
			 */

			this.personaNatural.getEmpleoCollection().add(dto);
		} else {
			addInfoMessage(null, getBundleMensajes("empleo.independiente.existe", null), "");
		}
	}

	/**
	 * @return the respuestaDto
	 */
	public RespuestaGeneralDto getRespuestaDto() {
		return respuestaDto;
	}

	/**
	 * @param respuestaDto the respuestaDto to set
	 */
	public void setRespuestaDto(RespuestaGeneralDto respuestaDto) {
		this.respuestaDto = respuestaDto;
	}

	public String getStyleDivEstadoCivil() {

		if (verificadoListasPersona) {
			return "display:block";
		} else {
			return "display:none";
		}

	}

	public String getStyleDivTodoFormulario() {

		String estilo = "display:none";

		if (verificadoListasPersona) {

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

	public boolean isVerificadoListasPersona() {
		return verificadoListasPersona;
	}

	public void setVerificadoListasPersona(boolean verificadoListasPersona) {
		this.verificadoListasPersona = verificadoListasPersona;
	}

	public boolean isVerificadoListasConyuge() {
		return verificadoListasConyuge;
	}

	public void setVerificadoListasConyuge(boolean verificadoListasConyuge) {
		this.verificadoListasConyuge = verificadoListasConyuge;
	}

	public short getEstadoCivilIdInicial() {
		return estadoCivilIdInicial;
	}

	public void setEstadoCivilIdInicial(short estadoCivilIdInicial) {
		this.estadoCivilIdInicial = estadoCivilIdInicial;
	}

}
