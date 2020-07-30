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
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.xml.rpc.ServiceException;

import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONException;
import org.richfaces.json.JSONObject;

import com.equivida.constant.AntiguedadEnum;
import com.equivida.constant.Constantes;
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
import com.equivida.dto.EmpleoDto;
import com.equivida.dto.GeocodeAddressLW;
import com.equivida.dto.RespuestaSiseDto;
import com.equivida.dto.ResultadoWebserviceNombresDto;
import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.homologacion.servicio.PersonaEquividaServicio;
import com.equivida.intranet.util.BasePersonaCtrl;
import com.equivida.intranet.util.DateUtils;
import com.equivida.intranet.util.LWResources;
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
import com.equivida.modelo.Referencia;
import com.equivida.modelo.ReferenciaBancaria;
import com.equivida.modelo.ReferenciaComercial;
import com.equivida.modelo.SeguroAdicional;
import com.equivida.modelo.SeguroVigente;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoActividad;
import com.equivida.modelo.TipoContactoPreferido;
import com.equivida.modelo.TipoEstado;
import com.equivida.modelo.TipoHabitoEnfermedad;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoInstitucionFinanciera;
import com.equivida.modelo.TipoOtraOcupacion;
import com.equivida.modelo.TipoOtroSeguro;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.modelo.TipoRiesgo;
import com.equivida.modelo.TipoVisa;
import com.equivida.servicio.BeneficiarioServicio;
import com.equivida.servicio.DetalleHabitoEnfermedadServicio;
import com.equivida.servicio.HabitoEnfermedadServicio;
import com.equivida.servicio.InstitucionFinancieraServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.PreguntaHabitoEnfermedadServicio;
import com.equivida.servicio.SiseServicio;
import com.equivida.servicio.TipoActividadServicio;
import com.equivida.servicio.TipoHabitoEnfermedadServicio;
import com.equivida.servicio.TipoOtraOcupacionServicio;
import com.equivida.servicio.TipoOtroSeguroServicio;
import com.equivida.servicio.TipoParentescoRelacionServicio;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.equivida.sise.ws.client.client.client.client.client.RsConductoPago;
import com.equivida.sise.ws.client.client.client.client.client.RsCumulosDePago;
import com.equivida.util.CedulaValidator;
import com.equivida.util.EquivalenciaKilosLibras;
import com.equivida.util.MailMessage;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.util.Criteria;

@ManagedBean(name = "personaNaturalCtrl")
@ViewScoped
public class PersonaNaturalCtrl extends BasePersonaCtrl {

	private static final long serialVersionUID = 8016098231826708483L;

	@EJB(mappedName = "HabitoEnfermedadServicio/local")
	private HabitoEnfermedadServicio habitoEnfermedadServicio;

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

	@ManagedProperty("#{ciudadLWAutocompleteCtrl}")
	private CiudadLWAutocompleteCtrl ciudadLWAutocompleteCtrl;

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

	public void ponerTipoInstitucion(AjaxBehaviorEvent event) {
		ReferenciaBancaria rb = (ReferenciaBancaria) getExternalContext()
				.getRequestMap().get("refb");
		Short secInst = rb.getInstitucionFinanciera()
				.getSecInstitucionFinanciera();
		if (secInst != -1) {
			rb.setInstitucionFinanciera(institucionFinancieraServicio
					.findByPk(secInst));
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
		this.personaNatural = personaNatural;
	}

	public List<String> getErrores() {
		List<String> errores = new ArrayList<String>();
		RespuestaSiseDto dto = (RespuestaSiseDto) getSession().getAttribute(
				"dto");
		if (dto != null) {
			errores = dto.getErrorLista();
		}
		return errores;
	}

	public PersonaNatural getPersonaNatural() {
		if (personaNatural == null) {

			conductoDePagoLista = null;

			// si es nuevo
			if (getNuevo()) {

				personaNatural = new PersonaNatural();

				personaNatural.setSexo(SexoEnum.MASCULINO.getCodigo());
				EstadoCivil estadoCivil = new EstadoCivil(
						EstadoCivilEnum.SOLTERO.getCodigo());
				personaNatural.setEstadoCivil(estadoCivil);

				// conyuge
				PersonaNatural conyuge = new PersonaNatural();
				conyuge.setPersona(new Persona());
				conyuge.setTipoIdentificacion(new TipoIdentificacion(
						TipoIdentificacionEnum.CEDULA.getCodigo()));
				personaNatural.setConyuge(conyuge);

				personaNatural.setEmpleoCollection(new ArrayList<EmpleoDto>());

				personaNatural
						.setOtroEmpleoCollection(new ArrayList<OtroEmpleo>());

				personaNatural.setTipoRiesgo(new TipoRiesgo());

				// actividades

				List<TipoActividad> tipoActividadLista = tipoActividadServicio
						.findAll();

				List<Actividad> actividadLista = new ArrayList<Actividad>();

				for (TipoActividad tipoActividad : tipoActividadLista) {
					Actividad act = new Actividad();
					act.setPersonaNatural(personaNatural);
					act.setSeleccionado(false);
					act.setTipoActividad(tipoActividad);
					actividadLista.add(act);
				}

				System.out
						.println("actividad persona:" + actividadLista.size());

				personaNatural.setActividadFormularioCollection(actividadLista);

				// deportes
				personaNatural
						.setDeportePracticaCollection(new ArrayList<DeportePractica>());

				personaNatural
						.setSegurosVigentesCollection(new ArrayList<SeguroVigente>());

				// perfil financiero
				// PerfilFinancieroNatural perfilFinancieroNatural = new
				// PerfilFinancieroNatural();
				// perfilFinancieroNatural.setPersonaNatural(personaNatural);
				// perfilFinancieroNatural.setMntInmuebles(BigDecimal.ZERO);
				// perfilFinancieroNatural.setMntDeudas(BigDecimal.ZERO);
				// perfilFinancieroNatural.setMntVehiculos(BigDecimal.ZERO);
				// perfilFinancieroNatural.setMntHipotecas(BigDecimal.ZERO);
				// perfilFinancieroNatural.setMntOtrosActivos(BigDecimal.ZERO);
				// perfilFinancieroNatural.setMntOtrosPasivos(BigDecimal.ZERO);
				// personaNatural
				// .setPerfilFinancieroNatural(perfilFinancieroNatural);

				// perfil financiero tmp
				PerfilFinancieroNatural perfilFinancieroNatural = new PerfilFinancieroNatural();
				perfilFinancieroNatural.setMntActivos(BigDecimal.ZERO);
				perfilFinancieroNatural.setMntPasivos(BigDecimal.ZERO);

				personaNatural
						.setPerfilFinancieroNatural(perfilFinancieroNatural);

				personaNatural
						.setIngresoMensualAdicionalCollection(new ArrayList<IngresoMensualAdicional>());

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

				personaNatural
						.setPersonaPeCollection(new ArrayList<PersonaPe>());

				InformacionAdicional informacionAdicional = new InformacionAdicional();
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

				TipoIdentificacion tipoIdentificacion = new TipoIdentificacion(
						Constantes.TIPO_IDENTIFICACION_CEDULA);

				// ingreso mensual
				IngresoMensual ingresoMensual = new IngresoMensual();
				ingresoMensual.setMntIngresoMensual(new BigDecimal(0));
				ingresoMensual.setMntEgresoMensual(new BigDecimal(0));
				ingresoMensual.setPersonaNatural(personaNatural);
				personaNatural.setIngresoMensual(ingresoMensual);

				// Detalle funciones
				DetalleActividadFuncion detalleActividadFuncion = new DetalleActividadFuncion();
				// detalleActividadFuncion.setPersonaNatural(personaNatural);
				personaNatural
						.setDetalleActividadFuncion(detalleActividadFuncion);

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

				personaNatural
						.setReferenciaFormularioCollection(referenciaLista);

				// Referencias comerciales
				Collection<ReferenciaComercial> referenciaComercialLista = new ArrayList<ReferenciaComercial>();
				ReferenciaComercial referenciaComercialUno = new ReferenciaComercial();
				referenciaComercialUno.setPersonaNatural(personaNatural);
				referenciaComercialLista.add(referenciaComercialUno);

				ReferenciaComercial referenciaComercialDos = new ReferenciaComercial();
				referenciaComercialDos.setPersonaNatural(personaNatural);
				referenciaComercialLista.add(referenciaComercialDos);

				personaNatural
						.setReferenciaComercialFormularioCollection(referenciaComercialLista);

				// Referencias bancarias
				Collection<ReferenciaBancaria> referenciaBancariaLista = new ArrayList<ReferenciaBancaria>();
				ReferenciaBancaria referenciaBancariaUno = new ReferenciaBancaria();
				referenciaBancariaUno.setPersonaNatural(personaNatural);
				referenciaBancariaUno
						.setInstitucionFinanciera(new InstitucionFinanciera(
								new TipoInstitucionFinanciera()));
				referenciaBancariaLista.add(referenciaBancariaUno);

				ReferenciaBancaria referenciaBancariaDos = new ReferenciaBancaria();
				referenciaBancariaDos.setPersonaNatural(personaNatural);
				referenciaBancariaDos
						.setInstitucionFinanciera(new InstitucionFinanciera(
								new TipoInstitucionFinanciera()));
				referenciaBancariaLista.add(referenciaBancariaDos);

				personaNatural
						.setReferenciaBancariaFormularioCollection(referenciaBancariaLista);

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
				biometrica.setGanadoPerdido(GanadoPerdidoEnum.GANADO
						.getCodigo());
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
				personaNatural
						.setEstadoPersonaCollection(new ArrayList<EstadoPersona>());

				personaNatural
						.setHistoriaMedicaFamiliarFormularioCollection(listaHistoriaMF);

				// seguro adicional

				Collection<TipoOtroSeguro> otroSeguroPreguntas = tipoOtroSeguroServicio
						.findAll();

				Collection<SeguroAdicional> seguroAdicionalLista = new ArrayList<SeguroAdicional>();

				for (TipoOtroSeguro tipoOtroSeguro : otroSeguroPreguntas) {
					SeguroAdicional seguroAdicional = new SeguroAdicional();
					seguroAdicional.setPersonaNatural(personaNatural);
					seguroAdicional.setTipoOtroSeguro(tipoOtroSeguro);
					seguroAdicionalLista.add(seguroAdicional);
				}

				personaNatural
						.setSeguroAdicionalFormularioCollection(seguroAdicionalLista);

				// inicia datos para persona
				initPersonaParaFormulario(tipoIdentificacion);

				// personaNatural.setPersona(persona);
			} else {
				// cuando es edicion, es decir hay parametro
				// 'paramSecPersonaNatural'
				personaNatural = personaNaturalServicio
						.findByPkParaFormularioWeb(paramSecPersonaNatural);
				// personaNatural.setConDatosCompletos(true);

				// inicia datos que pueden ser nulos (como cuando es conyuge)
				// iniciar estado civil
				if (personaNatural.getEstadoCivil() == null) {
					EstadoCivil estadoCivil = new EstadoCivil(
							EstadoCivilEnum.SOLTERO.getCodigo());
					personaNatural.setEstadoCivil(estadoCivil);
				}

				// iniciar informacion adicional
				if (personaNatural.getInformacionAdicional() == null) {
					InformacionAdicional informacionAdicional = new InformacionAdicional();
					informacionAdicional.setPersonaNatural(personaNatural);
					personaNatural
							.setInformacionAdicional(informacionAdicional);
				}

				// iniciar biometrica
				if (personaNatural.getBiometrica() == null) {
					Biometrica biometrica = new Biometrica();
					// biometrica.setPersonaNatural(personaNatural);
					biometrica.setGanadoPerdido(GanadoPerdidoEnum.GANADO
							.getCodigo());
					personaNatural.setBiometrica(biometrica);
				}

				// detalle actividad funcion
				if (personaNatural.getDetalleActividadFuncion() == null) {
					DetalleActividadFuncion detalleActividadFuncion = new DetalleActividadFuncion();
					// detalleActividadFuncion
					// .setPersonaNatural(personaNatural);
					personaNatural
							.setDetalleActividadFuncion(detalleActividadFuncion);
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
					personaNatural
							.setPerfilFinancieroNatural(perfilFinancieroNatural);
				}

				// historia medica familiar
				if (personaNatural
						.getHistoriaMedicaFamiliarFormularioCollection().size() == 0) {

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

					personaNatural
							.setHistoriaMedicaFamiliarFormularioCollection(listaHistoriaMF);
				}

			}// fin edicion

			// setea extranejro

			if (personaNatural.getDetallePasaporte() != null
					&& personaNatural.getDetallePasaporte()
							.getSecDetallePasaporte() != null) {
				personaNatural.setCasillaExtranjero(true);
			} else {
				personaNatural.setCasillaExtranjero(false);
			}

		}
		return personaNatural;
	}

	/**
	 * @return
	 */
	private String getParamSecPersonaString() {
		String paramSecPersonaString = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("p");
		return paramSecPersonaString;
	}

	/**
	 * @param paramSecPersonaString
	 * @return
	 */
	public Boolean getNuevo() {

		if (nuevo == null) {

			String paramSecPersonaString = getParamSecPersonaString();

			System.out
					.println("paramSecPersonaString:" + paramSecPersonaString);

			if (paramSecPersonaString == null
					|| paramSecPersonaString.equals("")) {
				nuevo = true;
			} else {
				paramSecPersonaNatural = Integer
						.parseInt(paramSecPersonaString);
				nuevo = false;
			}
		}
		return nuevo;
	}

	public void crearEmpleo() {

		// log.info("guardando...");
		// correo();

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
			TipoOtraOcupacion tipo = tipoOtraOcupacionServicio
					.findByPk(tipoEmpleoNuevo);
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

		MailMessage mailMessage = emailServicio.prepararCorreoListasReservadas(
				usuario, ip, apellido, segundoApellido, nombres, tipoRelacion,
				nacional, listing);

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
		beneficiario.setTipoIdentificacion(new TipoIdentificacion(
				TipoIdentificacionEnum.CEDULA.getCodigo()));
		beneficiarioPoliza.setBeneficiario(beneficiario);
		beneficiarioPoliza
				.setEncontradoBeneficiarioEnum(EncontradoBeneficiarioEnum.NO_ENCONTRO);
		beneficiarioPoliza
				.setTipoParentescoRelacion(new TipoParentescoRelacion());
		personaNatural.getPersona().getBeneficiarioPolizaCollection()
				.add(beneficiarioPoliza);
	}

	public void buscarBeneficiarioPorIdentificacion(AjaxBehaviorEvent event) {

		beneficiarioPoliza = (BeneficiarioPoliza) getExternalContext()
				.getRequestMap().get("varBneneficiario");

		if (beneficiarioPoliza.getBeneficiario().getIdentificacion() == null
				|| beneficiarioPoliza.getBeneficiario().getIdentificacion()
						.equals("")) {
			System.out.println("no verifica por identificacion");
			return;
		}

		String numDoc = beneficiarioPoliza.getBeneficiario()
				.getIdentificacion();
		char tipoDoc = beneficiarioPoliza.getBeneficiario()
				.getTipoIdentificacion().getCodTipoIdentificacion();

		boolean documentoValido = true;

		if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
			documentoValido = CedulaValidator.validate(numDoc);
		}

		// verifica
		if (!documentoValido) {
			String mensaje = getBundleMensajes("numero.documento.incorrecto",
					null);
			addErrorMessage(event.getComponent().getId(), mensaje, mensaje);
			return;
		}

		beneficiarioPoliza
				.setEncontradoBeneficiarioEnum(EncontradoBeneficiarioEnum.NO_ENCONTRO);

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
			beneficiarioPoliza
					.setEncontradoBeneficiarioEnum(EncontradoBeneficiarioEnum.ENCONTRO_EN_PERSONA_NATURAL);
			beneficiarioPoliza.getBeneficiario().setDenominacion(
					encontradoPN.getPersona().getDenominacion());
			beneficiarioPoliza.getBeneficiario().setApellidoPaterno(
					encontradoPN.getApellidoPaterno());
			beneficiarioPoliza.getBeneficiario().setApellidoMaterno(
					encontradoPN.getApellidoMaterno());
			beneficiarioPoliza.getBeneficiario().setPrimerNombre(
					encontradoPN.getPrimerNombre());
			beneficiarioPoliza.getBeneficiario().setSegundoNombre(
					encontradoPN.getSegundoNombre());
			beneficiarioPoliza.getBeneficiario().setTipoIdentificacion(
					encontradoPN.getTipoIdentificacion());
			beneficiarioPoliza.getBeneficiario().setIdentificacion(
					encontradoPN.getIdentificacion());

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
			beneficiarioPoliza
					.setEncontradoBeneficiarioEnum(EncontradoBeneficiarioEnum.ENCONTRO_EN_BENEFICIARIO);
			beneficiarioPoliza.setBeneficiario(encontradoB);
			return;
		}
	}

	public void crearSeguroVigente(ActionEvent event) {
		// Se crea nuevo objeto
		SeguroVigente e = new SeguroVigente();
		e.setPersonaNatural(personaNatural);
		e.setPersona(new Persona());
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
		IngresoMensualAdicional e = (IngresoMensualAdicional) getExternalContext()
				.getRequestMap().get("a");
		personaNatural.getIngresoMensualAdicionalCollection().remove(e);
	}

	public void eliminarIngresoAnual(ActionEvent event) {
		IngresoAnual e = (IngresoAnual) getExternalContext().getRequestMap()
				.get("b");
		personaNatural.getIngresoAnualCollection().remove(e);
	}

	public void eliminarPersonaPep(ActionEvent event) {
		PersonaPe e = (PersonaPe) getExternalContext().getRequestMap().get(
				"personaPepVar");
		if (e.getSecPersonaPpe() == null) {
			personaNatural.getPersonaPeCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void eliminarOtroEmpleo(ActionEvent event) {
		OtroEmpleo e = (OtroEmpleo) getExternalContext().getRequestMap().get(
				"b");
		personaNatural.getOtroEmpleoCollection().remove(e);
	}

	public void eliminarSeguroVigente(ActionEvent event) {
		SeguroVigente e = (SeguroVigente) getExternalContext().getRequestMap()
				.get("seguroVigente");
		if (e.getSecSeguro() == null) {
			personaNatural.getSegurosVigentesCollection().remove(e);
		} else {
			e.setEstado(EstadoEnum.INACTIVO.getCodigo());
		}
	}

	public void eliminarBeneficiarioPoliza(ActionEvent event) {
		BeneficiarioPoliza e = (BeneficiarioPoliza) getExternalContext()
				.getRequestMap().get("varBneneficiario");
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
				.println("en listener, pais seleccionado:"
						+ getPersonaNatural().getCiudadNacimiento().getPais()
								.getPais());

		Ciudad ciudad = new Ciudad();
		ciudad.setPais(getPersonaNatural().getCiudadNacimiento().getPais());
		getPersonaNatural().setCiudadNacimiento(ciudad);

		System.out.println("en listener, ciduad reseteada:"
				+ getPersonaNatural().getCiudadNacimiento().getCiudad());

	}

	public void seleccionarPaisNAC() {
		String lnn = getPersonaNatural().getPaisNacionalidad().getPais();
		String[] arr = lnn.split("\\|");
		getPersonaNatural().getPaisNacionalidad().setCodPais(
				Short.parseShort(arr[0]));
		getPersonaNatural().getPaisNacionalidad().setPais(arr[1]);

	}

	public void seleccionarSexo() {
		// char sexo = getPersonaNatural().getSexo();
		// habitoEnfermedadPorPersonaLista = null;
	}

	public void seleccionarProfesion() {
		String lnn = getPersonaNatural().getProfesion().getProfesion();
		String[] arr = lnn.split("\\|");
		getPersonaNatural().getProfesion().setSecProfesion(
				Short.parseShort(arr[0]));
		getPersonaNatural().getProfesion().setProfesion(arr[1]);

		System.out.println("profesion id:" + arr[0]);
		System.out.println("profesion:" + arr[1]);
	}

	public void seleccionarOcupacion() {
		String lnn = getPersonaNatural().getOcupacion().getOcupacion();
		String[] arr = lnn.split("\\|");
		getPersonaNatural().getOcupacion().setCodOcupacion(
				Short.parseShort(arr[0]));
		getPersonaNatural().getOcupacion().setOcupacion(arr[1]);

		getPersonaNatural().setTipoRiesgo(
				new TipoRiesgo(Short.parseShort(arr[2]), arr[3].charAt(0)));
		// getPersonaNatural().getTipoRiesgo()
		// .setTipoRiesgo(arr[3].charAt(0));

		System.out.println("ocupacion id:" + arr[0]);
		System.out.println("ocupacion:" + arr[1]);
		System.out.println("ocupacion riesgo:" + arr[2]);
		System.out.println("ocupacion riesgo char:" + arr[3]);
	}

	public void seleccionarPersonaSeguroVigente() {
		SeguroVigente seguroVigente = (SeguroVigente) getExternalContext()
				.getRequestMap().get("seguroVigente");
		String lnn = seguroVigente.getPersona().getDenominacion();
		String[] arr = lnn.split("\\|");
		seguroVigente.getPersona().setSecPersona(Integer.parseInt(arr[0]));
		seguroVigente.getPersona().setDenominacion(arr[1]);

		System.out.println("persona id:" + arr[0]);
		System.out.println("persona denominacion:" + arr[1]);
	}

	public void seleccionarPaisD() {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get(
				"dir");
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
		DireccionTelefono dt = (DireccionTelefono) getExternalContext()
				.getRequestMap().get("dt");
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
		Direccion e = (Direccion) getExternalContext().getRequestMap().get(
				"dir");
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

		Direccion e = (Direccion) getExternalContext().getRequestMap().get(
				"dir");

		String lnn = e.getCanton().getCanton();
		String[] arr = lnn.split("\\|");
		e.getCanton().setSecCanton(Short.parseShort(arr[0]));
		e.getCanton().setCanton(arr[1]);
	}

	public void seleccionarCiudadFN() {
		String lnn = getPersonaNatural().getCiudadNacimiento().getCiudad();
		String[] arr = lnn.split("\\|");
		getPersonaNatural().getCiudadNacimiento().setSecCiudad(
				Short.parseShort(arr[0]));
		getPersonaNatural().getCiudadNacimiento().setCiudad(arr[1]);
		System.out.println("ciudad id:" + arr[0]);
		System.out.println("ciudad:" + arr[1]);
	}

	public void verificarNombresSegundoNombre() {

		String segundoNombre = personaNatural.getSegundoNombre();

		System.out.println("SEGUNDO NOMBRE:" + segundoNombre + ".");

		if (segundoNombre != null && !segundoNombre.equals("")) {
			verificarNombres();
		}
	}

	public synchronized void verificarNombres() {

		// if (true) {
		// return;
		// }

		String primerNombre = personaNatural.getPrimerNombre();
		// String segundoNombre = personaNatural.getSegundoNombre();
		String primerApellido = personaNatural.getApellidoPaterno();
		// String segundoApellido = personaNatural.getApellidoMaterno();

		if (primerNombre != null && primerNombre.trim().length() > 0
				&& primerApellido != null && primerApellido.trim().length() > 0) {

			System.out.println("entra a validar");

			personaNatural.setNombresApellidos(null);// para que vuelva a
														// generar
			String nombreValidar = personaNatural.getNombresApellidos();
			System.out.println("nombre que envia a validar:" + nombreValidar);

			try {
				resultado = wsDatosPersonaServicio.validarNombre(nombreValidar);
				// TODO
				System.out.println("sexo:" + personaNatural.getSexo());
				System.out.println("sexo sugerido:"
						+ resultado.getSexoSugerido());
				String sexoSeleccionado = personaNatural.getSexo() + "";

				if (resultado.getSexoSugerido() != null) {
					int comp = resultado.getSexoSugerido().compareTo(
							sexoSeleccionado);
					if (comp != 0) {
						sexoAdvertencia = true;
					}
				} else {
					sexoAdvertencia = false;
				}

				if (!resultado.isValido()) {
					System.out.println("no es valido");
					verificarNombresDe = "P";
					mostrarPanelNombreInvalido = true;
					return;
				}

				if (resultado.isCambio()) {
					System.out.println("existe sugerencia");
					verificarNombresDe = "P";
					mostrarPanelNombreSugerencia = true;
					return;
				}

				System.out.println("no existe error ni sugerencia en nombre");

				// verificarRiesgo();

			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void verificarNombresConyuge() {

		// if (true) {
		// return;
		// }

		String primerNombre = personaNatural.getConyuge().getPrimerNombre();
		// String segundoNombre = personaNatural.getSegundoNombre();
		String primerApellido = personaNatural.getConyuge()
				.getApellidoPaterno();
		// String segundoApellido = personaNatural.getApellidoMaterno();

		if (primerNombre != null && primerNombre.trim().length() > 0
				&& primerApellido != null && primerApellido.trim().length() > 0) {

			System.out.println("entra a validar nombres conyuge");

			personaNatural.getConyuge().setNombresApellidos(null);// para que
																	// vuelva a
			// generar
			String nombreValidar = personaNatural.getConyuge()
					.getNombresApellidos();
			System.out.println("nombre que envia a validar:" + nombreValidar);

			try {
				resultado = wsDatosPersonaServicio.validarNombre(nombreValidar);

				if (!resultado.isValido()) {
					System.out.println("no es valido");
					mostrarPanelNombreInvalido = true;
					verificarNombresDe = "C";
					return;
				}

				if (resultado.isCambio()) {
					System.out.println("existe sugerencia");
					mostrarPanelNombreSugerencia = true;
					verificarNombresDe = "C";
					return;
				}

				System.out.println("no existe error ni sugerencia en nombre");

				// verificarRiesgo();

			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void verificarNombresBeneficiario() {

		beneficiarioPoliza = (BeneficiarioPoliza) getExternalContext()
				.getRequestMap().get("varBneneficiario");

		String primerNombre = beneficiarioPoliza.getBeneficiario()
				.getPrimerNombre();
		String primerApellido = beneficiarioPoliza.getBeneficiario()
				.getApellidoPaterno();

		if (primerNombre != null && primerNombre.trim().length() > 0
				&& primerApellido != null && primerApellido.trim().length() > 0) {

			System.out.println("entra a validar nombres beneficiario");

			beneficiarioPoliza.getBeneficiario().setNombresApellidos(null);// para
																			// que
			// vuelva a
			// generar
			String nombreValidar = beneficiarioPoliza.getBeneficiario()
					.getNombresApellidos();
			System.out.println("nombre que envia a validar:" + nombreValidar);

			try {
				resultado = wsDatosPersonaServicio.validarNombre(nombreValidar);

				if (!resultado.isValido()) {
					System.out.println("no es valido");
					mostrarPanelNombreInvalido = true;
					verificarNombresDe = "B";
					return;
				}

				if (resultado.isCambio()) {
					System.out.println("existe sugerencia");
					mostrarPanelNombreSugerencia = true;
					verificarNombresDe = "B";
					return;
				}

				System.out.println("no existe error ni sugerencia en nombre");

				// verificarRiesgo();

			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}

	public void aceptarPanelNombreInvalido() {
		System.out.println("acepta nombre invalido");
		mostrarPanelNombreInvalido = false;
		// verificarRiesgo();
	}

	public void cancelarPanelNombreInvalido() {
		mostrarPanelNombreInvalido = false;
	}

	public void aceptarPanelSugerencia() {
		System.out.println("acepta sugenrecia");
		mostrarPanelNombreSugerencia = false;

		if (verificarNombresDe.equals("P")) {
			personaNatural.setPrimerNombre(resultado.getSugerenciaNombre1());
			personaNatural.setSegundoNombre(resultado.getSugerenciaNombre2());
			personaNatural.setApellidoPaterno(resultado
					.getSugerenciaApellido1());
			personaNatural.setApellidoMaterno(resultado
					.getSugerenciaApellido2());
			personaNatural.setNombresApellidos(null);
			System.out.println("sugerencia ya aplicada:"
					+ personaNatural.getNombresApellidos());
		}

		if (verificarNombresDe.equals("C")) {
			personaNatural.getConyuge().setPrimerNombre(
					resultado.getSugerenciaNombre1());
			personaNatural.getConyuge().setSegundoNombre(
					resultado.getSugerenciaNombre2());
			personaNatural.getConyuge().setApellidoPaterno(
					resultado.getSugerenciaApellido1());
			personaNatural.getConyuge().setApellidoMaterno(
					resultado.getSugerenciaApellido2());
			personaNatural.getConyuge().setNombresApellidos(null);
			System.out.println("sugerencia ya aplicada:"
					+ personaNatural.getConyuge().getNombresApellidos());
		}

		if (verificarNombresDe.equals("B")) {
			beneficiarioPoliza.getBeneficiario().setPrimerNombre(
					resultado.getSugerenciaNombre1());
			beneficiarioPoliza.getBeneficiario().setSegundoNombre(
					resultado.getSugerenciaNombre2());
			beneficiarioPoliza.getBeneficiario().setApellidoPaterno(
					resultado.getSugerenciaApellido1());
			beneficiarioPoliza.getBeneficiario().setApellidoMaterno(
					resultado.getSugerenciaApellido2());
			beneficiarioPoliza.getBeneficiario().setNombresApellidos(null);
			System.out.println("sugerencia ya aplicada:"
					+ beneficiarioPoliza.getBeneficiario()
							.getNombresApellidos());
		}

		// verificarRiesgo();
	}

	public void cancelarPanelSugerencia() {
		mostrarPanelNombreSugerencia = false;
	}

	public void verificarSiExisteConyuge() {

		System.out.println("borra nombres apellidos conyuge");
		getPersonaNatural().getConyuge().setPrimerNombre("");
		getPersonaNatural().getConyuge().setSegundoNombre("");
		getPersonaNatural().getConyuge().setApellidoPaterno("");
		getPersonaNatural().getConyuge().setApellidoMaterno("");

		String numDoc = getPersonaNatural().getConyuge().getIdentificacion();
		char tipoDoc = getPersonaNatural().getConyuge().getTipoIdentificacion()
				.getCodTipoIdentificacion();

		boolean documentoValido = true;

		if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
			documentoValido = CedulaValidator.validate(numDoc);
		}

		// verifica
		if (!documentoValido) {
			String mensaje = getBundleMensajes("numero.documento.incorrecto",
					null);
			addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
			return;
		}

		if (numDoc.equals(getPersonaNatural().getIdentificacion())) {
			String mensaje = getBundleMensajes(
					"identificacion.igual.persona.conyuge", null);
			addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
			return;
		}

		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { numDoc };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		verificarListaExistentesConyuge(criteria);

		if (personaNatural.getConyuge() != null
				&& personaNatural.getConyuge().getPersona() != null
				&& personaNatural.getConyuge().getPersona().getSecPersona() != null) {

			Persona delConyuge = personaNaturalServicio
					.obtenerConyuge(personaNatural.getConyuge().getPersona()
							.getSecPersona());

			if (delConyuge != null) {
				conyugeDelConyuge = "Tiene conyuge registrado:"
						+ delConyuge.getDenominacion();
			} else {
				conyugeDelConyuge = "";
			}
		}
	}

	public void verificarSiExiste() {
		yaExiste = false;
		String numDoc = getPersonaNatural().getIdentificacion();
		char tipoDoc = getPersonaNatural().getTipoIdentificacion()
				.getCodTipoIdentificacion();

		boolean documentoValido = true;

		if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
			documentoValido = CedulaValidator.validate(numDoc);
		}

		// verifica
		if (!documentoValido) {
			String mensaje = getBundleMensajes("numero.documento.incorrecto",
					null);
			addErrorMessage("SSIPNForm:txtId", mensaje, mensaje);
			return;
		}

		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { numDoc };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		if (getNuevo()) {

			verificarListaExistentes(criteria);

		} else {
			// modificacion
			System.out.println("nuevo:" + numDoc);
			System.out.println("orig:"
					+ getPersonaNatural().getIdentificacionOriginal());
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

	private void verificarListaExistentes(Criteria criteria) {

		List<PersonaNatural> existentesPN = personaNaturalServicio
				.findByCriterias(criteria);

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
				String mensaje = getBundleMensajes(
						"numero.documento.existe.n.veces",
						new Object[] { existentesPN.size() });
				addErrorMessage("SSIPNForm:txtId", mensaje, mensaje);
				yaExiste = true;
			}
		}

		// si no encuentra en pers. natural, busca en beneficiarios
		if (totalEncontrados == 0) {
			List<Beneficiario> existentesB = beneficiarioServicio
					.findByCriterias(criteria);
			// si encuentra UNO en beneficiarios, lo carga en el form
			if (existentesB.size() == 1) {
				totalEncontrados = totalEncontrados + 1;
				paramSecPersonaNatural = null;
				nuevo = true;
				Beneficiario beneficiario = existentesB.get(0);
				// personaNatural.setPersona(beneficiario.getPersona());
				personaNatural.setApellidoPaterno(beneficiario
						.getApellidoPaterno());
				personaNatural.setApellidoMaterno(beneficiario
						.getApellidoMaterno());
				personaNatural.setTipoIdentificacion(beneficiario
						.getTipoIdentificacion());
				personaNatural.setIdentificacion(beneficiario
						.getIdentificacion());

				String mensaje = getBundleMensajes(
						"numero.documento.existe.beneficiario", null);
				addInfoMessage("SSIPNForm:txtId", mensaje, mensaje);
				yaExiste = true;
			}
		}
	}

	private void verificarListaExistentesConyuge(Criteria criteria) {

		List<PersonaNatural> existentesPN = personaNaturalServicio
				.findByCriterias(criteria);

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
				String mensaje = getBundleMensajes(
						"numero.documento.existe.n.veces",
						new Object[] { existentesPN.size() });
				addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
				// yaExiste = true;
			}
		}

		// si no encuentra en pers. natural, busca en beneficiarios
		if (totalEncontrados == 0) {
			List<Beneficiario> existentesB = beneficiarioServicio
					.findByCriterias(criteria);
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
				pnConyuge.setTipoIdentificacion(personaNatural.getConyuge()
						.getTipoIdentificacion());
				pnConyuge.setIdentificacion(personaNatural.getConyuge()
						.getIdentificacion());

				personaNatural.setConyuge(pnConyuge);

				String mensaje = getBundleMensajes(
						"numero.documento.existe.beneficiario", null);
				addInfoMessage("SSIPNForm:txtId", mensaje, mensaje);
			}
		}
	}

	public void locationWorldListener(ActionEvent event) {
		System.out.println("LISTENER LW");

		Direccion dir = (Direccion) getExternalContext().getRequestMap().get(
				"dir");
		dir.setMostrarNoCargoLW(false);

		String secundaria = dir.getSecundaria();

		if (secundaria != null && !secundaria.equals("")) {
			String ciudad = dir.getCiudad();
			String principal = dir.getPrincipal();
			GeocodeAddressLW geocodeAddressLW = encontrarGeocode(ciudad,
					principal, secundaria);
			if (geocodeAddressLW != null) {
				System.out.println("se obtubvo latitud y longitud");
				dir.setLongitud(geocodeAddressLW.getLongitude());
				dir.setLatitud(geocodeAddressLW.getLatitude());
				// si son ceros entonces muestra el mensaje
				if (geocodeAddressLW.getLongitude().equals(BigDecimal.ZERO)
						&& geocodeAddressLW.getLatitude().equals(
								BigDecimal.ZERO)) {
					dir.setMostrarNoCargoLW(true);
					System.out.println("sugerencia LW:"
							+ event.getComponent().getId());
					// System.out.println("sugerencia LW:"
					// + event.getComponent().get);
					// addInfoMessage(event.getComponent().getId(),
					// "Dir. no validada", "Dir. no validada");
				} else {
					dir.setMostrarNoCargoLW(false);
				}
			} else {
				System.out.println("sugerencia LW:"
						+ event.getComponent().getId());
				addInfoMessage(event.getComponent().getId(),
						"Dir. no validada", "Dir. no validada");
				dir.setMostrarNoCargoLW(true);
			}
		}
	}

	public String guardar() {

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
			String mensaje = getBundleMensajes("fecha.nacimiento.antiguo",
					new Object[] { Constantes.MAX_EDAD });
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		// conyuge
		if (personaNatural.isConConyuge()
				&& isObligatorioIdentificacionConyuge()) {
			String numDoc = getPersonaNatural().getConyuge()
					.getIdentificacion();
			char tipoDoc = getPersonaNatural().getConyuge()
					.getTipoIdentificacion().getCodTipoIdentificacion();

			boolean documentoValido = true;

			if (numDoc == null) {
				numDoc = "";
			}

			if (tipoDoc == Constantes.TIPO_IDENTIFICACION_CEDULA) {
				documentoValido = CedulaValidator.validate(numDoc);
			}

			// verifica
			if (!documentoValido) {
				String mensaje = getBundleMensajes(
						"numero.documento.incorrecto", null);
				addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
				return null;
			}

			if (numDoc.equals(getPersonaNatural().getIdentificacion())) {
				String mensaje = getBundleMensajes(
						"identificacion.igual.persona.conyuge", null);
				addErrorMessage("SSIPNForm:txtIdConyuge", mensaje, mensaje);
				return null;
			}
		}

		// direcciones, pone latitudes de location world
		Collection<Direccion> dirList = personaNatural.getPersona()
				.getDireccionCollection();
		for (Direccion dir : dirList) {

			if (dir.getActivo()) {

				String secundaria = dir.getSecundaria();
				// solo si secundaria no es vacia, llama a LW
				if (secundaria != null && !secundaria.equals("")) {
					String ciudad = dir.getCiudad();
					String principal = dir.getPrincipal();
					GeocodeAddressLW geocodeAddressLW = encontrarGeocode(
							ciudad, principal, secundaria);
					if (geocodeAddressLW != null) {
						System.out.println("se obtubvo latitud y longitud");
						dir.setLongitud(geocodeAddressLW.getLongitude());
						dir.setLatitud(geocodeAddressLW.getLatitude());
					}

				}

				// TODO valida que exista canton
				if (dir.getCanton() == null
						|| dir.getCanton().getSecCanton() == null) {
					String mensaje = getBundleMensajes("requerido.campo",
							new Object[] { "Cant\u00F3n" });
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}

				// valida que inrgese ciudad
				if (dir.getCiudad() == null
						|| dir.getCiudad().trim().equals("")) {
					String mensaje = getBundleMensajes("requerido.campo",
							new Object[] { "Ciudad" });
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
			}
		}

		// tipo riesgo
		System.out.println("tipoRiesgo:"
				+ personaNatural.getTipoRiesgo().getCodTipoRiesgo());
		if (personaNatural.getTipoRiesgo().getCodTipoRiesgo() == null) {
			String mensaje = getBundleMensajes("seleccione.riesgo", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		// actividades
		Collection<Actividad> actList = personaNatural
				.getActividadFormularioCollection();
		Collection<Actividad> actListFinal = new ArrayList<Actividad>();
		for (Actividad act : actList) {
			if (act.isSeleccionado()) {
				if (!act.getTipoActividad().getPregunta().equals("N")) {
					// entonces valida que ingrese detalle
					if (act.getDetalle() == null || act.getDetalle().equals("")) {
						String mensaje = getBundleMensajes(
								"actividad.ingrese.detalle", new Object[] {
										act.getTipoActividad().getPregunta(),
										act.getTipoActividad().getActividad() });
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
			String mensaje = getBundleMensajes(
					"actividad.seleccione.al.menos.una", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		personaNatural.setActividadCollection(actListFinal);

		// seguroviegte

		Collection<SeguroVigente> listaSV = personaNatural
				.getSegurosVigentesCollection();
		for (SeguroVigente seguroVigente : listaSV) {
			if (seguroVigente.getPersona() == null
					|| seguroVigente.getPersona().getSecPersona() == null) {
				String mensaje = getBundleMensajes(
						"seleccione.empresa.seguro.vigente", null);
				addErrorMessage(null, mensaje, mensaje);
				return null;
			}
		}

		// motivo seguro
		Collection<MotivoSeguro> msList = personaNatural.getPersona()
				.getMotivoSeguroFormularioCollection();
		Collection<MotivoSeguro> msListFinal = new ArrayList<MotivoSeguro>();
		for (MotivoSeguro ms : msList) {
			if (ms.isSeleccionado()) {
				System.out.println(">>>"
						+ (ms.getTipoMotivoSeguro().getDetallar() == 'S'));
				if ((ms.getTipoMotivoSeguro().getDetallar() == 'S')) {
					// entonces valida que ingrese detalle
					if (ms.getDetalle() == null || ms.getDetalle().equals("")) {
						String mensaje = getBundleMensajes(
								"motivo.seguro.ingrese.detalle", null);
						addErrorMessage(null, mensaje, mensaje);
						return null;
					}
				}
				msListFinal.add(ms);
			} else {
				if (ms.getSecMotivoSeguro() != null) {
					msListFinal.add(ms);
				}
			}
		}
		System.out.println("motivo seguro guardar total:" + msListFinal.size());
		personaNatural.getPersona().setMotivoSeguroCollection(msListFinal);

		// otro seguro
		Collection<SeguroAdicional> osList = personaNatural
				.getSeguroAdicionalFormularioCollection();
		Collection<SeguroAdicional> osFinalList = new ArrayList<SeguroAdicional>();
		for (SeguroAdicional seguroAdicional : osList) {
			if (seguroAdicional.getRespuesta() == RespuestaEnum.SI.getCodigo()
					|| seguroAdicional.getRespuesta() == RespuestaEnum.NO
							.getCodigo()) {
				osFinalList.add(seguroAdicional);
			}
		}
		personaNatural.setSeguroAdicionalCollection(osFinalList);

		// profesion
		if (personaNatural.getProfesion().getSecProfesion() == null) {
			personaNatural.getProfesion().setSecProfesion(
					Constantes.PROFESION_NO_DISPONIBLE);// le pone cero
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
		for (EmpleoDto empleo : tmpList) {

			if (empleo.getTipoEmpleo().equals(
					TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo())
					|| empleo.getTipoEmpleo().equals(
							TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo())) {

				if (empleo.getCargo() == null) {
					String mensaje = getBundleMensajes(
							"lista.empleos.fila.cargo.requerido",
							new Object[] { fila });
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}

				if (empleo.getTipoEmpleo().equals(
						TipoEmpleoEnum.DEPENDIENTE.getTipoEmpleo())) {

					if (empleo.getNegocioEmpresaNombre() == null
							|| empleo.getNegocioEmpresaNombre().equals("")) {
						String mensaje = getBundleMensajes(
								"lista.empleos.fila.empresa.requerido",
								new Object[] { fila });
						addErrorMessage(null, mensaje, mensaje);
						return null;
					}

					EmpleoDependiente e = new EmpleoDependiente();
					e.setSecEmpleoDependiente(empleo.getSecEmpleo());
					e.setCargo(empleo.getCargo());
					e.setCodTiempo(empleo.getCodTiempo());
					e.setEstado(empleo.getEstado());
					ActividadEconomica actividadEconomica = new ActividadEconomica(
							empleo.getActividadEconomicaId());
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

				if (empleo.getTipoEmpleo().equals(
						TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo())) {

					EmpleoIndependiente e = new EmpleoIndependiente();
					e.setSecEmpleoIndependiente(empleo.getSecEmpleo());
					e.setCargo(empleo.getCargo());
					e.setCodTiempo(empleo.getCodTiempo());
					e.setEstado(empleo.getEstado());
					ActividadEconomica actividadEconomica = new ActividadEconomica(
							empleo.getActividadEconomicaId());
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
				otraOcupacion.setSecPersonaNatural(personaNatural
						.getSecPersonaNatural());
				otraOcupacion.setTiempoOcupacion(empleo.getTiempoEmpresa());
				otraOcupacion.setTipoOtraOcupacion(new TipoOtraOcupacion(empleo
						.getTipoEmpleo()));

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
		Collection<Direccion> direccionLista = personaNatural.getPersona()
				.getDireccionCollection();
		for (Direccion direccion : direccionLista) {
			if (direccion.getActivo()) {
				Collection<DireccionTelefono> tmtDireccionTelefono = direccion
						.getDireccionTelefonoCollection();
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

		Collection<Telefono> tmpTelefonoAdicionales = personaNatural
				.getPersona().getTelefonoSinDireccionCollection();
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
					direccion.setEnvioCorrespondencia(RespuestaEnum.SI
							.getCodigo());
				} else {
					direccion.setEnvioCorrespondencia(RespuestaEnum.NO
							.getCodigo());
				}
			}
		}

		if (!seleccionoDirPrincipal) {
			String mensaje = getBundleMensajes(
					"direccion.principal.no.seleccionada", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		Collection<DireccionElectronica> tmpDirElectronica = personaNatural
				.getPersona().getDireccionElectronicaFormularioCollection();

		// valida emails nuevamente, para no guardar incoherencias
		try {
			for (DireccionElectronica direccionElectronica : tmpDirElectronica) {
				if (direccionElectronica.getActivo()) {
					boolean valida;

					valida = wsDatosPersonaServicio
							.validarEmail(direccionElectronica
									.getDireccionElectronica());
					if (!valida) {
						String mensaje = getBundleMensajes("email.incorrecto",
								null);
						addErrorMessage(null, mensaje, mensaje);
						return null;
					}
				}
			}
		} catch (RemoteException e) {
			System.out.println("no se pudo consumir servicio web de email");
		} catch (ServiceException e) {
			System.out.println("no se pudo consumir servicio web de email");
		}

		// REFERENCIAS PERSONALES
		// se verifica q las referencias tengan al menos un telefono
		Collection<Referencia> tmpRef = personaNatural
				.getReferenciaFormularioCollection();

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
				if (referencia.getDenominacion() != null
						&& !referencia.getDenominacion().equals("")) {
					if (referencia.getNroTelefonoCelular() == null
							|| referencia.getNroTelefonoCelular().equals("")) {
						if (referencia.getNroTelefonoConvencional() == null
								|| referencia.getNroTelefonoConvencional()
										.equals("")) {
							System.out.println("ene l if N");
							String mensaje = getBundleMensajes(
									"referencia.ingreso.telefono", null);
							addErrorMessage(null, mensaje, mensaje);
							return null;
						}

					}
				}
			}

			if (referencia.getDenominacion() != null
					&& !referencia.getDenominacion().equals("")) {
				refFinal.add(referencia);
			}
		}

		personaNatural.setReferenciaFormularioCollection(refFinal);

		// REFERENCIAS COMERCIALES
		// la lista final que va a guardar, es decir solo referencias ocon datos
		// completos
		Collection<ReferenciaComercial> refComercialFinal = new ArrayList<ReferenciaComercial>();

		Collection<ReferenciaComercial> tmpComercialRef = personaNatural
				.getReferenciaComercialFormularioCollection();

		for (ReferenciaComercial referenciaComercial : tmpComercialRef) {
			if (referenciaComercial.getEntidadReferencia() != null
					&& !referenciaComercial.getEntidadReferencia().trim()
							.equals("")) {
				refComercialFinal.add(referenciaComercial);
			}
		}

		personaNatural
				.setReferenciaComercialFormularioCollection(refComercialFinal);

		// REFERENCIAS BANCARIAS
		// la lista final que va a guardar, es decir solo referencias ocon datos
		// completos
		Collection<ReferenciaBancaria> refBancariaFinal = new ArrayList<ReferenciaBancaria>();

		Collection<ReferenciaBancaria> tmpBancariaRef = personaNatural
				.getReferenciaBancariaFormularioCollection();

		for (ReferenciaBancaria referenciaBancaria : tmpBancariaRef) {
			if (referenciaBancaria.getInstitucionFinanciera()
					.getSecInstitucionFinanciera() != null
					&& referenciaBancaria.getInstitucionFinanciera()
							.getSecInstitucionFinanciera() != -1) {
				refBancariaFinal.add(referenciaBancaria);
			}
		}

		personaNatural
				.setReferenciaBancariaFormularioCollection(refBancariaFinal);

		// a telf q es contacto preferido (principal)
		TipoContactoPreferido tipoContactoPreferido = new TipoContactoPreferido(
				Constantes.TIPO_CONTACTO_PREFERIDO_TELEFONO);
		personaNatural.getPersona().getContactoPreferidoTransient()
				.setTipoContactoPreferido(tipoContactoPreferido);
		personaNatural.getPersona().getContactoPreferidoTransient()
				.setPersona(personaNatural.getPersona());
		if (preferido.getNroTelefono() == null
				|| preferido.getNroTelefono().equals("")) {
			String mensaje = getBundleMensajes("seleccione.contacto.preferido",
					null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}
		personaNatural.getPersona().getContactoPreferidoTransient()
				.setTelefono(preferido);
		// horario contacto preferido
		boolean correcto = personaNatural.getPersona()
				.getContactoPreferidoTransient().ponerHorario();
		if (!correcto) {
			String mensaje = getBundleMensajes(
					"ingrese.horario.contacto.preferido", null);
			addErrorMessage(null, mensaje, mensaje);
			return null;
		}

		// prepara tabla telefono
		List<Telefono> guardarListaTel = new ArrayList<Telefono>();

		Collection<Direccion> direcciones = personaNatural.getPersona()
				.getDireccionCollection();

		for (Direccion dir : direcciones) {
			if (dir.getActivo()) {
				for (DireccionTelefono dt : dir
						.getDireccionTelefonoCollection()) {
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
		personaNatural
				.getPersona()
				.getTelefonoCollection()
				.addAll(personaNatural.getPersona()
						.getTelefonoSinDireccionCollection());

		// prepara biometrica y deja listo para guardar

		// se guarda en kilos
		personaNatural.getBiometrica().setPeso(
				personaNatural.getBiometrica().getPesoKilos());

		personaNatural.getBiometrica().setGanadosUltAnioKilos(
				personaNatural.getBiometrica().getGanadosUltAnioKilos());

		personaNatural.getBiometrica().setPerdidosUltAnioKilos(
				personaNatural.getBiometrica().getPerdidosUltAnioKilos());

		// si gano peso
		if (personaNatural.getBiometrica().getGanadosUltAnioKilos() != null) {
			if (personaNatural.getBiometrica().getGanadosUltAnioKilos()
					.compareTo(BigDecimal.ZERO) > 0) {
				personaNatural.getBiometrica().setGanadoPerdido(
						GanadoPerdidoEnum.GANADO.getCodigo());
				personaNatural.getBiometrica()
						.setDiferenciaUltimoAnio(
								personaNatural.getBiometrica()
										.getGanadosUltAnioKilos());
			}
		}

		// si perdio peso
		if (personaNatural.getBiometrica().getPerdidosUltAnioKilos() != null) {
			if (personaNatural.getBiometrica().getPerdidosUltAnioKilos()
					.compareTo(BigDecimal.ZERO) > 0) {
				personaNatural.getBiometrica().setGanadoPerdido(
						GanadoPerdidoEnum.PERDIDO.getCodigo());
				personaNatural.getBiometrica().setDiferenciaUltimoAnio(
						personaNatural.getBiometrica()
								.getPerdidosUltAnioKilos());
			}
		}

		// controla q no sea null
		if (personaNatural.getBiometrica().getDiferenciaUltimoAnio() == null) {
			personaNatural.getBiometrica().setDiferenciaUltimoAnio(
					BigDecimal.ZERO);
		}

		// prepara historia medica familiar y deja listo para guardar
		Collection<HistoriaMedicaFamiliar> listaHMF = personaNatural
				.getHistoriaMedicaFamiliarFormularioCollection();

		Collection<HistoriaMedicaFamiliar> listaHMFGuardar = new ArrayList<HistoriaMedicaFamiliar>();

		for (HistoriaMedicaFamiliar hmf : listaHMF) {

			boolean ingresadoDifunto = false;
			boolean ingresadoVivo = false;

			// si es difunto
			if ((hmf.getDetallesM() != null && !hmf.getDetallesM().equals(""))
					|| hmf.getEdadM() > 0) {
				ingresadoDifunto = true;
			}
			// si es vivo
			if (hmf.getEdad() > 0
					|| (hmf.getDetalles() != null && !hmf.getDetalles().equals(
							""))) {
				ingresadoVivo = true;
			}

			if (ingresadoVivo && ingresadoDifunto) {
				String mensaje = getBundleMensajes(
						"esta.ingresando.vivo.muerto", new Object[] { hmf
								.getTipoParentescoRelacion()
								.getTipoParentesco() });
				addErrorMessage(null, mensaje, mensaje);
				return null;
			}

			if (ingresadoVivo) {
				if (hmf.getDetalles().equals("")) {
					String mensaje = getBundleMensajes("ingrese.detalle.hmf",
							new Object[] { hmf.getTipoParentescoRelacion()
									.getTipoParentesco() });
					addErrorMessage(null, mensaje, mensaje);
					return null;
				}
				hmf.setVivo(RespuestaEnum.SI.getCodigo());
				listaHMFGuardar.add(hmf);
			}

			if (ingresadoDifunto) {
				if (hmf.getDetallesM().equals("")) {
					String mensaje = getBundleMensajes("ingrese.detalle.hmf",
							new Object[] { hmf.getTipoParentescoRelacion()
									.getTipoParentesco() });
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
		personaNatural
				.setHabitoEnfermedadFormularioCollection(habitoEnfermedadPorPersonaLista);

		// servicios para guardar

		try {
			if (getNuevo()) {
				System.out.println("ingreso en cliente unico .....");

				// persona compoenente exclusion
				if (sesionCtrl.getFormularioSeleccionado().equals(
						TipoFormularioEnum.ASOCIACION.getSecuencial())) {
					PersonaComponenteExclusion personaComponenteExclusion = new PersonaComponenteExclusion();
					personaComponenteExclusion.setPersona(personaNatural
							.getPersona());
					personaNatural.getPersona()
							.setPersonaComponenteExclusionTransient(
									personaComponenteExclusion);
				}

				personaNaturalServicio.crearPersonaNaturalFormularioWeb(
						personaNatural, getRemoteUser(),
						getHttpServletRequest().getRemoteAddr());

				System.out.println("ingreso en sise .....");

				// ingresa persona natural sise
				Long codigoSISE = null;
				try {
					RespuestaSiseDto dto = siseServicio.insertarWsSiseMpersona(
							personaNatural, personaNatural.getPersona(),
							getNuevo(), getRemoteUser(),true);
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

				String mensaje = getBundleMensajes(
						"persona.natural.ingresada.codigo.sise",
						new Object[] { "" + codigoSISE });
				addInfoMessage(null, mensaje, mensaje);

				habilitarBtnGuardar = false;

			} else {
				System.out.println("actualizacion.....");

				// persona compoenente exclusion
				if (sesionCtrl.getFormularioSeleccionado().equals(
						TipoFormularioEnum.ASOCIACION.getSecuencial())) {

					if (personaNatural.getPersona()
							.getPersonaComponenteExclusionTransient() == null) {
						PersonaComponenteExclusion personaComponenteExclusion = new PersonaComponenteExclusion();
						personaComponenteExclusion.setPersona(personaNatural
								.getPersona());
						personaNatural.getPersona()
								.setPersonaComponenteExclusionTransient(
										personaComponenteExclusion);
					} else {
						PersonaComponenteExclusion personaComponenteExclusion = personaNatural
								.getPersona()
								.getPersonaComponenteExclusionTransient();
						personaComponenteExclusion.setPersona(personaNatural
								.getPersona());
						personaNatural.getPersona()
								.setPersonaComponenteExclusionTransient(
										personaComponenteExclusion);
					}
				} else {
					// if(personaNatural.getPersona().getPersonaComponenteExclusion()!=null){
					// }
				}

				// actualizacion
				personaNaturalServicio.actualizarPersonaNaturalFormularioWeb(
						personaNatural, getRemoteUser(),
						getHttpServletRequest().getRemoteAddr());

				// ingresa persona natural sise
				Long codigoSISE = null;
				try {
					RespuestaSiseDto dto = siseServicio.insertarWsSiseMpersona(
							personaNatural, personaNatural.getPersona(),
							getNuevo(), getRemoteUser(),true);
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

				String mensaje = getBundleMensajes(
						"persona.natural.actualizada.codigo.sise",
						new Object[] { "" + codigoSISE });
				addInfoMessage(null, mensaje, mensaje);
				habilitarBtnGuardar = false;
				// TODO return a otroa pagina?

				return "/pages/formularios/listadoPersonasNaturales?faces-redirect=true&codigoSise="
						+ codigoSISE;

			}

		} catch (EmpleoDependienteException e) {
			System.out.println("11");
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		} catch (EmpleoIndependienteException e) {
			System.out.println("22");
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		} catch (RemoteException e) {
			System.out.println("33");
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		} catch (ServiceException e) {
			System.out.println("44");
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		} catch (ErrorIngresoWsSiseException e) {
			System.out.println("55");
			addErrorMessage(null, e.getMessage(), e.getMessage());
			return null;
		}
		return null;
	}

	public void verificarTelefonoDireccion(AjaxBehaviorEvent event) {

		System.out.println("borrar:" + event.getComponent().getClientId());

		DireccionTelefono direccionTelefono = (DireccionTelefono) getExternalContext()
				.getRequestMap().get("dt");

		System.out.println("valida direccion telefono:"
				+ direccionTelefono.getTelefono().getPais().getCodPais());

		if (direccionTelefono.getTelefono().getPais().getCodPais()
				.equals(Constantes.PAIS_ID_ECUADOR)) {
			String nroTelefono = direccionTelefono.getTelefono().getCodArea()
					+ direccionTelefono.getTelefono().getNroTelefono();

			System.out.println("telefono:" + nroTelefono);

			boolean valida = false;
			try {
				valida = wsDatosPersonaServicio.validarTelefono(nroTelefono);
				if (!valida) {
					System.out.println("si es false ");
					String mensaje = getBundleMensajes(
							"numero.telefono.incorrecto", null);
					System.out.println("****id***"
							+ event.getComponent().getId());
					System.out.println("****client id***"
							+ event.getComponent().getClientId());
					addErrorMessage(event.getComponent().getClientId(),
							mensaje, mensaje);
					return;

				}

			} catch (RemoteException e) {
				String mensaje = getBundleMensajes("error.web.service", null)
						+ " " + e.getMessage();
				addErrorMessage(event.getComponent().getClientId(), mensaje,
						mensaje);
				System.out.println(e);
			} catch (ServiceException e) {
				String mensaje = getBundleMensajes("error.web.service", null)
						+ " " + e.getMessage();
				addErrorMessage(event.getComponent().getClientId(), mensaje,
						mensaje);
				System.out.println(e);
			}

		}
	}

	public void verificarTelefono(AjaxBehaviorEvent event) {

		Telefono telefono = (Telefono) getExternalContext().getRequestMap()
				.get("t");

		System.out
				.println("valida telefono:" + telefono.getPais().getCodPais());

		if (telefono.getPais().getCodPais().equals(Constantes.PAIS_ID_ECUADOR)) {
			String nroTelefono = telefono.getCodArea()
					+ telefono.getNroTelefono();

			System.out.println("nimero:" + nroTelefono);

			boolean valida = false;
			try {
				valida = wsDatosPersonaServicio.validarTelefono(nroTelefono);
				if (!valida) {
					String mensaje = getBundleMensajes(
							"numero.telefono.incorrecto", null);
					addErrorMessage(event.getComponent().getClientId(),
							mensaje, mensaje);
					return;

				}

			} catch (RemoteException e) {
				String mensaje = getBundleMensajes("error.web.service", null);
				addErrorMessage(event.getComponent().getClientId(), mensaje,
						mensaje);
				System.out.println(e);
			} catch (ServiceException e) {
				String mensaje = getBundleMensajes("error.web.service", null);
				addErrorMessage(event.getComponent().getClientId(), mensaje,
						mensaje);
				System.out.println(e);
			}
		}
	}

	public void verificarEmail(AjaxBehaviorEvent event) {

		DireccionElectronica email = (DireccionElectronica) getExternalContext()
				.getRequestMap().get("b");

		boolean valida = false;
		try {
			valida = wsDatosPersonaServicio.validarEmail(email
					.getDireccionElectronica());
			if (!valida) {
				String mensaje = getBundleMensajes("email.incorrecto", null);
				addErrorMessage(event.getComponent().getClientId(), mensaje,
						mensaje);
				return;

			}

		} catch (RemoteException e) {
			String mensaje = getBundleMensajes("error.web.service", null);
			addErrorMessage(null, mensaje, mensaje);
		} catch (ServiceException e) {
			String mensaje = getBundleMensajes("error.web.service", null);
			addErrorMessage(null, mensaje, mensaje);
		}

	}

	/**
	 * @return the habitoEnfermedadPorPersonaLista
	 */
	public Collection<HabitoEnfermedad> getHabitoEnfermedadPorPersonaLista() {
		if (habitoEnfermedadPorPersonaLista == null) {

			Collection<TipoHabitoEnfermedad> tipoHabitoLista = tipoHabitoEnfermedadServicio
					.findAll();
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
					habitoEnfermedad
							.setTipoHabitoEnfermedad(tipoHabitoEnfermedad);
					// respuesta no aplica por defecto
					habitoEnfermedad.setRespuesta(Constantes.ID_NINGUNO);

					if (tipoHabitoEnfermedad.getDetallar() == RespuestaEnum.SI
							.getCodigo()) {
						List<DetalleHabitoEnfermedad> detalles = new ArrayList<DetalleHabitoEnfermedad>();

						// burscar las preguntas de este tipoHabito
						String[] criteriasAnd = { "tipoHabitoEnfermedad.codTipoHabito" };
						CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.SHORT_EQUALS };
						Object[] params = new Object[] { tipoHabitoEnfermedad
								.getCodTipoHabito() };
						Criteria criteria = new Criteria(criteriasAnd,
								typesAnd, params);

						List<PreguntaHabitoEnfermedad> preguntas = preguntaHabitoEnfermedadServicio
								.findByCriterias(criteria);

						for (PreguntaHabitoEnfermedad preguntaHabitoEnfermedad : preguntas) {
							// for e preguntas, crear un DetalleHabitoEnfermedad
							// por cada pregunta
							DetalleHabitoEnfermedad detalleHabitoEnfermedad = new DetalleHabitoEnfermedad();
							// HabitoEnfermedad he = new HabitoEnfermedad(
							// habitoEnfermedad.getSecHabitoEnfermedad());
							detalleHabitoEnfermedad
									.setHabitoEnfermedad(habitoEnfermedad);
							detalleHabitoEnfermedad
									.setPreguntaHabitoEnfermedad(new PreguntaHabitoEnfermedad(
											preguntaHabitoEnfermedad
													.getCodPregunta(),
											preguntaHabitoEnfermedad
													.getPregunta()));
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
						}// fin for

						habitoEnfermedad
								.setDetalleHabitoEnfermedadFormularioCollection(detalles);
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

				Collection<HabitoEnfermedad> habitoEnfermedadColl = habitoEnfermedadServicio
						.findByCriterias(criteria);

				for (TipoHabitoEnfermedad tipoHabitoEnfermedad : tipoHabitoLista) {

					HabitoEnfermedad hb = null;

					for (HabitoEnfermedad bdd : habitoEnfermedadColl) {
						if (bdd.getTipoHabitoEnfermedad()
								.getCodTipoHabito()
								.compareTo(
										tipoHabitoEnfermedad.getCodTipoHabito()) == 0) {
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

					if (tipoHabitoEnfermedad.getDetallar() == RespuestaEnum.SI
							.getCodigo()) {
						List<DetalleHabitoEnfermedad> detalles = new ArrayList<DetalleHabitoEnfermedad>();

						// burscar las preguntas de este tipoHabito
						String[] criteriasAndP = { "tipoHabitoEnfermedad.codTipoHabito" };
						CriteriaTypeEnum[] typesAndP = { CriteriaTypeEnum.SHORT_EQUALS };
						Object[] paramsP = new Object[] { tipoHabitoEnfermedad
								.getCodTipoHabito() };
						Criteria criteriaP = new Criteria(criteriasAndP,
								typesAndP, paramsP);

						List<PreguntaHabitoEnfermedad> preguntas = preguntaHabitoEnfermedadServicio
								.findByCriterias(criteriaP);

						if (hb.getSecHabitoEnfermedad() != null) {
							// burscar las respondidas
							String[] criteriasAndD = { "habitoEnfermedad.secHabitoEnfermedad" };
							CriteriaTypeEnum[] typesAndD = { CriteriaTypeEnum.INTEGER_EQUALS };
							Object[] paramsD = new Object[] { hb
									.getSecHabitoEnfermedad() };
							Criteria criteriaD = new Criteria(criteriasAndD,
									typesAndD, paramsD);
							List<DetalleHabitoEnfermedad> detallesBDD = detalleHabitoEnfermedadServicio
									.findByCriterias(criteriaD);

							detalles = detallesBDD;

							// System.out
							// .println("total detaless respondidos anteriormente:"
							// + detalles.size());

							// hb.setDetalleHabitoEnfermedadFormularioCollection(detallesBDD);

						} else {

							for (PreguntaHabitoEnfermedad preguntaHabitoEnfermedad : preguntas) {
								DetalleHabitoEnfermedad detalleHabitoEnfermedad = new DetalleHabitoEnfermedad();
								// HabitoEnfermedad he = new HabitoEnfermedad(
								// habitoEnfermedad.getSecHabitoEnfermedad());
								detalleHabitoEnfermedad.setHabitoEnfermedad(hb);
								detalleHabitoEnfermedad
										.setPreguntaHabitoEnfermedad(new PreguntaHabitoEnfermedad(
												preguntaHabitoEnfermedad
														.getCodPregunta(),
												preguntaHabitoEnfermedad
														.getPregunta()));
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
	 * @param habitoEnfermedadPorPersonaLista
	 *            the habitoEnfermedadPorPersonaLista to set
	 */
	public void setHabitoEnfermedadPorPersonaLista(
			Collection<HabitoEnfermedad> habitoEnfermedadPorPersonaLista) {
		this.habitoEnfermedadPorPersonaLista = habitoEnfermedadPorPersonaLista;
	}

	/**
	 * provincia direccion
	 * 
	 * @param pref
	 * @return
	 */
	public List<Provincia> autocompleteProvinciaD(String pref) {
		Direccion e = (Direccion) getExternalContext().getRequestMap().get(
				"dir");
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
		Direccion e = (Direccion) getExternalContext().getRequestMap().get(
				"dir");
		Short secProvincia = e.getCanton().getProvincia().getSecProvincia();
		return buscarCantones(pref, secProvincia);
	}

	public List<String> autocompleteCiudadConLW(String pref) {
		//String j="[{\"Id\":110117,\"Name\":\"Abanin - Azuay\",\"PhonePrefix\":\"00\",\"Zone\":null},{\"Id\":11647,\"Name\":\"Abanin - El Oro\",\"PhonePrefix\":\"00\",\"Zone\":null}]"; //$NON-NLS-1$

		Direccion e = (Direccion) getExternalContext().getRequestMap().get(
				"dir");

		Short codPais = e.getCanton().getProvincia().getPais().getCodPais();

		List<String> listaR = new ArrayList<String>();

		HashSet<String> ciudades = getCiudadLWAutocompleteCtrl()
				.getCiudadConLWLista(codPais, ciudadServicio);

		Iterator<String> iterator = ciudades.iterator();

		while (iterator.hasNext()) {
			String ciudad = iterator.next();
			if (ciudad != null
					&& ciudad.toLowerCase().indexOf(pref.toLowerCase()) >= 0
					|| "".equals(pref)) {
				listaR.add(ciudad);
			}
		}

		return listaR;
	}

	public List<String> autocompleteCallePrincipalConLW(String pref) {
		//String j="[{\"Id\":110117,\"Name\":\"Abanin - Azuay\",\"PhonePrefix\":\"00\",\"Zone\":null},{\"Id\":11647,\"Name\":\"Abanin - El Oro\",\"PhonePrefix\":\"00\",\"Zone\":null}]"; //$NON-NLS-1$

		Direccion d = (Direccion) getExternalContext().getRequestMap().get(
				"dir");

		// Short codPais = e.getCanton().getProvincia().getPais().getCodPais();

		List<String> listaR = new ArrayList<String>();

		String webHandlerCallesPrincipales = LWResources
				.getString("calles.principales.webhandler"); //$NON-NLS-1$

		try {

			if (d.getCiudad() == null || d.getCiudad().equals("")) {
				throw new JSONException("no ha seleccionado ciudad");
			}

			String ciudad = URLEncoder.encode(d.getCiudad(), "utf-8");

			webHandlerCallesPrincipales = webHandlerCallesPrincipales.replace(
					"@ciudad", ciudad);

			String calleutf8 = URLEncoder.encode(pref, "utf-8");

			webHandlerCallesPrincipales = webHandlerCallesPrincipales.replace(
					"@calle", calleutf8);

			String jsonCalles = ""; //$NON-NLS-1$

			URL url = new URL(webHandlerCallesPrincipales);

			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setConnectTimeout(5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));

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
			// j="";System.out
			System.out
					.println("Error Connection Host: no puede cargar lista de calles de LW" //$NON-NLS-1$
							+ e);

		} catch (java.net.UnknownHostException e) {
			System.out
					.println("Error Connection Host: no puede cargar lista de calles de LW" //$NON-NLS-1$
							+ e);
		} catch (SocketTimeoutException e) {
			System.out
					.println("Error Connection: no puede cargar lista de calles de LW" //$NON-NLS-1$
							+ e);
		} catch (MalformedURLException e) {
			System.out.println("Error1: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		} catch (IOException e) {
			System.out.println("Error2: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		}

		return listaR;
	}

	public List<String> autocompleteCalleSecundariaConLW(String pref) {
		//String j="[{\"Id\":110117,\"Name\":\"Abanin - Azuay\",\"PhonePrefix\":\"00\",\"Zone\":null},{\"Id\":11647,\"Name\":\"Abanin - El Oro\",\"PhonePrefix\":\"00\",\"Zone\":null}]"; //$NON-NLS-1$

		Direccion d = (Direccion) getExternalContext().getRequestMap().get(
				"dir");

		// Short codPais = e.getCanton().getProvincia().getPais().getCodPais();

		List<String> listaR = new ArrayList<String>();

		try {

			if (d.getCiudad() == null || d.getCiudad().equals("")) {
				throw new JSONException("no ha seleccionado ciudad");
			}

			if (d.getPrincipal() == null || d.getPrincipal().equals("")) {
				throw new JSONException("no ha seleccionado calle principal");
			}

			String webHandlerCallesSecundarias = LWResources
					.getString("calles.secundarias.webhandler"); //$NON-NLS-1$

			String ciudad = URLEncoder.encode(d.getCiudad(), "utf-8");

			webHandlerCallesSecundarias = webHandlerCallesSecundarias.replace(
					"@ciudad", ciudad);

			String calleutf8 = URLEncoder.encode(d.getPrincipal(), "utf-8");
			webHandlerCallesSecundarias = webHandlerCallesSecundarias.replace(
					"@calle", calleutf8);

			String calleSutf8 = URLEncoder.encode(pref, "utf-8");

			webHandlerCallesSecundarias = webHandlerCallesSecundarias.replace(
					"@secundaria", calleSutf8);

			URL url = new URL(webHandlerCallesSecundarias);
			String jsonCallesSecundarias = ""; //$NON-NLS-1$

			URLConnection urlConn = url.openConnection();
			// urlConn.setRequestProperty("User-Agent",
			// "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.3) Gecko/20100401");
			// // Do as if you're using Firefox 3.6.3.
			// urlConn.setDoOutput(true);
			// urlConn.setConnectTimeout(5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));

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
			// j="";System.out
			System.out
					.println("Error Connection Host: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
							+ e);

		} catch (java.net.UnknownHostException e) {
			System.out
					.println("Error Connection Host: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
							+ e);
		} catch (SocketTimeoutException e) {
			System.out
					.println("Error Connection: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
							+ e);
		} catch (MalformedURLException e) {
			System.out
					.println("Error1: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
							+ e);
		} catch (IOException e) {
			System.out
					.println("Error2: no puede cargar lista de calles sec de LW" //$NON-NLS-1$
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
		Short codPais = getPersonaNatural().getCiudadNacimiento().getPais()
				.getCodPais();
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

	public void setMostrarPanelNombreSugerencia(
			boolean mostrarPanelNombreSugerencia) {
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

	public void setCiudadLWAutocompleteCtrl(
			CiudadLWAutocompleteCtrl ciudadLWAutocompleteCtrl) {
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
		TipoParentescoRelacion tipoHermano = tipoParentescoRelacionServicio
				.findByPk(Constantes.TIPO_RELACION_HERMANO);
		hmf.setTipoParentescoRelacion(tipoHermano);
		personaNatural.getHistoriaMedicaFamiliarFormularioCollection().add(hmf);

	}

	public void eliminarDeporte(ActionEvent event) {
		DeportePractica dp = (DeportePractica) getExternalContext()
				.getRequestMap().get("d");
		personaNatural.getDeportePracticaCollection().remove(dp);
	}

	public void eliminarEstadoPersona(ActionEvent event) {
		EstadoPersona ep = (EstadoPersona) getExternalContext().getRequestMap()
				.get("ep");
		personaNatural.getEstadoPersonaCollection().remove(ep);
	}

	public void eliminarHistoriaFamiliar(ActionEvent event) {
		HistoriaMedicaFamiliar hmf = (HistoriaMedicaFamiliar) getExternalContext()
				.getRequestMap().get("hmf");
		personaNatural.getHistoriaMedicaFamiliarFormularioCollection().remove(
				hmf);
	}

	public void calcularEnKilos() {

		BigDecimal pesoKilos = personaNatural.getBiometrica().getPesoKilos();

		System.out.println("peso kilos:" + pesoKilos);

		if (pesoKilos == null || pesoKilos.compareTo(BigDecimal.ZERO) == 0) {
			System.out.println("calcula en libras :)");
			personaNatural.getBiometrica().setPesoKilos(
					EquivalenciaKilosLibras.getKilos(personaNatural
							.getBiometrica().getPesoLibras()));
		}
	}

	public void calcularEnLibras() {
		personaNatural.getBiometrica().setPesoLibras(
				EquivalenciaKilosLibras.getLibras(personaNatural
						.getBiometrica().getPesoKilos()));
	}

	public void calcularGanadosEnKilos() {
		BigDecimal pesoKilos = personaNatural.getBiometrica()
				.getGanadosUltAnioKilos();
		if (pesoKilos == null || pesoKilos.compareTo(BigDecimal.ZERO) == 0) {
			personaNatural.getBiometrica().setGanadosUltAnioKilos(
					EquivalenciaKilosLibras.getKilos(personaNatural
							.getBiometrica().getGanadosUltAnioLibras()));
		}
	}

	public void calcularGanadosEnLibras() {
		int pesoLibras = EquivalenciaKilosLibras.getLibras(personaNatural
				.getBiometrica().getGanadosUltAnioKilos());
		personaNatural.getBiometrica().setGanadosUltAnioLibras(pesoLibras);
	}

	public void calcularPerdidosEnKilos() {
		BigDecimal pesoKilos = personaNatural.getBiometrica()
				.getPerdidosUltAnioKilos();

		if (pesoKilos == null || pesoKilos.compareTo(BigDecimal.ZERO) == 0) {
			personaNatural.getBiometrica().setPerdidosUltAnioKilos(
					EquivalenciaKilosLibras.getKilos(personaNatural
							.getBiometrica().getPerdidosUltAnioLibras()));
		}

	}

	public void calcularPerdidosEnLibras() {
		int pesoLibras = EquivalenciaKilosLibras.getLibras(personaNatural
				.getBiometrica().getPerdidosUltAnioKilos());
		personaNatural.getBiometrica().setPerdidosUltAnioLibras(pesoLibras);
	}

	public GeocodeAddressLW encontrarGeocode(String ciudad, String principal,
			String secundaria) {

		GeocodeAddressLW geocodeAddressLW = null;

		String webHandlerGeocode = LWResources.getString("geocode.webhandler");

		try {

			String ciudadE = URLEncoder.encode(ciudad, "utf-8");
			String principalE = URLEncoder.encode(principal, "utf-8");
			String secundariaE = URLEncoder.encode(secundaria, "utf-8");

			webHandlerGeocode = webHandlerGeocode.replace("@ciudad", ciudadE);
			webHandlerGeocode = webHandlerGeocode.replace("@calle", principalE);
			webHandlerGeocode = webHandlerGeocode.replace("@secundaria",
					secundariaE);

			String jsonCalles = ""; //$NON-NLS-1$

			URL url = new URL(webHandlerGeocode);

			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setConnectTimeout(5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));

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

					BigDecimal latitude = new BigDecimal(objCoord.get(
							"Latitude").toString());
					System.out.println("latitude" + latitude);
					BigDecimal longitude = new BigDecimal(objCoord.get(
							"Longitude").toString());
					System.out.println("longitude" + longitude);
					geocodeAddressLW = new GeocodeAddressLW(latitude, longitude);
				}
			}

		} catch (JSONException e) {
			// j="";System.out
			System.out
					.println("Error Connection Host: no puede cargar lista de calles de LW" //$NON-NLS-1$
							+ e);

		} catch (java.net.UnknownHostException e) {
			System.out
					.println("Error Connection Host: no puede cargar lista de calles de LW" //$NON-NLS-1$
							+ e);
		} catch (SocketTimeoutException e) {
			System.out
					.println("Error Connection: no puede cargar lista de calles de LW" //$NON-NLS-1$
							+ e);
		} catch (MalformedURLException e) {
			System.out.println("Error1: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		} catch (IOException e) {
			System.out.println("Error2: no puede cargar lista de calles de LW" //$NON-NLS-1$
					+ e);
		}

		return geocodeAddressLW;
	}

	public String nuevaPersonaNatural() {

		if (isUserInRole(PerfilEquividaEnum.CU_SERV_CLIENTE.toString())) {
			addInfoMessage(null, "No tiene permisos", "No tiene permisos");
			return null;
		}

		getSession().removeAttribute("dto");
		nuevo = true;
		personaNatural = null;
		// persona = null;
		habilitarBtnGuardar = true;
		return "/pages/formularios/personaNatural.jsf?faces-redirect=true";
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
		if (sesionCtrl.getFormularioSeleccionado().equals(
				TipoFormularioEnum.ASOCIACION.getSecuencial())) {
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
				Long idPersonaSise = personaEquividaServicio
						.obtenerIdPersonaDestinoPN(
								getPersonaNatural().getPersona()
										.getSecPersona(),
								getPersonaNatural().getSecPersonaNatural(),
								Constantes.COD_SISTEMA_SISE,
								Constantes.COD_HOMOLOGACION_TIPO_PERSONA_NATURAL);
				System.out.println("conducto idPersonaSise:" + idPersonaSise);
				if (idPersonaSise != null) {
					conductoDePagoLista = siseServicio
							.consultarConductosDePago(idPersonaSise);
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
	 * @param conyugeDelConyuge
	 *            the conyugeDelConyuge to set
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
	 * @param tipoEmpleoNuevo
	 *            the tipoEmpleoNuevo to set
	 */
	public void setTipoEmpleoNuevo(Short tipoEmpleoNuevo) {
		this.tipoEmpleoNuevo = tipoEmpleoNuevo;
	}

	public List<RsCumulosDePago> getConsultaCumulosLista() {
		if (consultaCumulosLista == null) {

			//System.out.println("consultando polizas...");

			String nroDoc = getPersonaNatural().getIdentificacion();

			consultaCumulosLista = siseServicio.consultar(nroDoc);

			if (consultaCumulosLista == null) {
				if (nroDoc != null && !nroDoc.trim().equals("")) {
					String m = "No se ha podido consultar p\u00F3lizas";
					addErrorMessage(null, m, m);
				}
			} else {
				for (RsCumulosDePago c : consultaCumulosLista) {
					if (c.getEstado().equals("VIGENTE")) {
						totalMontoMyda = totalMontoMyda
								+ c.getMontoMyda().doubleValue();
						totalMontoVida = totalMontoVida
								+ c.getMontoVida().doubleValue();
					}
				}
			}

			//System.out.println("... fin consultando polizas");

		}
		return consultaCumulosLista;
	}

	public void setConsultaCumulosLista(
			List<RsCumulosDePago> consultaCumulosLista) {
		this.consultaCumulosLista = consultaCumulosLista;
	}
}