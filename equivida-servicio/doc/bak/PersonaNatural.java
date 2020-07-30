/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.equivida.constant.EstadoCivilEnum;
import com.equivida.constant.SexoEnum;
import com.equivida.dto.EmpleoDto;
import com.equivida.util.Edad;
import com.equivida.util.StringUtil;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "PERSONA_NATURAL")
@NamedQueries({
		@NamedQuery(name = "PersonaNatural.findAll", query = "SELECT p FROM PersonaNatural p"),
		@NamedQuery(name = "PersonaNatural.findBySecPersonaNatural", query = "SELECT p FROM PersonaNatural p WHERE p.secPersonaNatural = :secPersonaNatural"),
		@NamedQuery(name = "PersonaNatural.findByIdentificacion", query = "SELECT p FROM PersonaNatural p WHERE p.identificacion = :identificacion"),
		@NamedQuery(name = "PersonaNatural.findByApellidoPaterno", query = "SELECT p FROM PersonaNatural p WHERE p.apellidoPaterno = :apellidoPaterno"),
		@NamedQuery(name = "PersonaNatural.findByApellidoMaterno", query = "SELECT p FROM PersonaNatural p WHERE p.apellidoMaterno = :apellidoMaterno"),
		@NamedQuery(name = "PersonaNatural.findByPrimerNombre", query = "SELECT p FROM PersonaNatural p WHERE p.primerNombre = :primerNombre"),
		@NamedQuery(name = "PersonaNatural.findBySegundoNombre", query = "SELECT p FROM PersonaNatural p WHERE p.segundoNombre = :segundoNombre"),
		@NamedQuery(name = "PersonaNatural.findBySexo", query = "SELECT p FROM PersonaNatural p WHERE p.sexo = :sexo"),
		@NamedQuery(name = "PersonaNatural.findByTipoEmpleo", query = "SELECT p FROM PersonaNatural p WHERE p.tipoEmpleo = :tipoEmpleo"),
		@NamedQuery(name = "PersonaNatural.findByNumHijos", query = "SELECT p FROM PersonaNatural p WHERE p.numHijos = :numHijos"),
		@NamedQuery(name = "PersonaNatural.findByFchNacimiento", query = "SELECT p FROM PersonaNatural p WHERE p.fchNacimiento = :fchNacimiento"),
		@NamedQuery(name = "PersonaNatural.findByMntSaldoMensual", query = "SELECT p FROM PersonaNatural p WHERE p.mntSaldoMensual = :mntSaldoMensual"),
		@NamedQuery(name = "PersonaNatural.findByFchFallecimiento", query = "SELECT p FROM PersonaNatural p WHERE p.fchFallecimiento = :fchFallecimiento"),
		@NamedQuery(name = "PersonaNatural.findByTsCreacion", query = "SELECT p FROM PersonaNatural p WHERE p.tsCreacion = :tsCreacion"),
		@NamedQuery(name = "PersonaNatural.findByTsModificacion", query = "SELECT p FROM PersonaNatural p WHERE p.tsModificacion = :tsModificacion") })
public class PersonaNatural implements Serializable {

	private static final long serialVersionUID = 2583952906975644162L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_PERSONA_NATURAL")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secPersonaNatural;
	@Basic(optional = false)
	@Column(name = "IDENTIFICACION")
	private String identificacion;
	@Basic(optional = false)
	@Column(name = "APELLIDO_PATERNO")
	private String apellidoPaterno;
	@Column(name = "APELLIDO_MATERNO")
	private String apellidoMaterno;
	@Basic(optional = false)
	@Column(name = "PRIMER_NOMBRE")
	private String primerNombre;
	@Column(name = "SEGUNDO_NOMBRE")
	private String segundoNombre;
	@Basic(optional = false)
	@Column(name = "SEXO")
	private char sexo;
	@Basic(optional = false)
	@Column(name = "TIPO_EMPLEO", insertable = false)
	private short tipoEmpleo;
	@Basic(optional = false)
	@Column(name = "NUM_HIJOS")
	private short numHijos;
	@Basic(optional = false)
	@Column(name = "FCH_NACIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fchNacimiento;
	@Basic(optional = false)
	@Column(name = "MNT_SALDO_MENSUAL", precision = 10, scale = 2, columnDefinition = "decimal(10,2)")
	private BigDecimal mntSaldoMensual;
	@Column(name = "FCH_FALLECIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fchFallecimiento;
	@Basic(optional = false)
	@Column(name = "TS_CREACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tsCreacion;
	@Basic(optional = false)
	@Column(name = "TS_MODIFICACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tsModificacion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<DeportePractica> deportePracticaCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<EstadoPersona> estadoPersonaCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<EmpleoDependiente> empleoDependienteCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<IngresoMensualAdicional> ingresoMensualAdicionalCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<IngresoAnual> ingresoAnualCollection;
	// @OneToOne(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	@Transient
	private DetalleActividadFuncion detalleActividadFuncion;
	@Transient
	private DetallePasaporte detallePasaporte;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<SeguroAdicional> seguroAdicionalCollection;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private InformacionAdicional informacionAdicional;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<PersonaPe> personaPeCollection;
	// @OneToOne(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	@Transient
	private PerfilFinancieroNatural perfilFinancieroNatural;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<Actividad> actividadCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<MedicoConsultado> medicoConsultadoCollection;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	// private Collection<Referencia> referenciaCollection;
	// @OneToOne(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	@Transient
	private Biometrica biometrica;
	@JoinColumn(name = "COD_TIPO_IDENTIFICACION", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = false)
	private TipoIdentificacion tipoIdentificacion;
	@JoinColumn(name = "SEC_PROFESION", referencedColumnName = "SEC_PROFESION")
	@ManyToOne
	private Profesion profesion;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@OneToOne(optional = false)
	private Persona persona;
	@JoinColumn(name = "COD_PAIS_NACIONALIDAD", referencedColumnName = "COD_PAIS")
	@ManyToOne(optional = false)
	private Pais paisNacionalidad;
	@JoinColumn(name = "SEC_CIUDAD_NACIMIENTO", referencedColumnName = "SEC_CIUDAD")
	@ManyToOne(optional = false)
	private Ciudad ciudadNacimiento;
	@JoinColumn(name = "COD_OCUPACION", referencedColumnName = "COD_OCUPACION")
	@ManyToOne(optional = false)
	private Ocupacion ocupacion;
	@JoinColumn(name = "COD_ESTADO_CIVIL", referencedColumnName = "COD_ESTADO_CIVIL")
	@ManyToOne(optional = false)
	private EstadoCivil estadoCivil;

	@Transient
	private PerfilFinancieroNaturalTmp perfilFinancieroNaturalTmp;

	@Transient
	private Collection<OtraOcupacion> otraOcupacionCollection;

	@Transient
	private Collection<Referencia> referenciaFormularioCollection;

	@Transient
	private Collection<ReferenciaBancaria> referenciaBancariaFormularioCollection;

	@Transient
	private Collection<ReferenciaComercial> referenciaComercialFormularioCollection;

	@Transient
	private Collection<HabitoEnfermedad> habitoEnfermedadFormularioCollection;
	@Transient
	private Collection<SeguroAdicional> seguroAdicionalFormularioCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<HistoriaMedicaFamiliar> historiaMedicaFamiliarCollection;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private IngresoMensual ingresoMensual;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<EmpleoIndependiente> empleoIndependienteCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	private Collection<OtroEmpleo> otroEmpleoCollection;

	@JoinColumn(name = "COD_TIPO_RIESGO", referencedColumnName = "COD_TIPO_RIESGO")
	@ManyToOne(optional = false)
	private TipoRiesgo tipoRiesgo;

	// Se guardan todos los empleos en un collection para despues pasarlos a las
	// tablas de emleos dependientes e independientes
	@Transient
	private Collection<EmpleoDto> empleoCollection;

	@Transient
	private Collection<HistoriaMedicaFamiliar> historiaMedicaFamiliarFormularioCollection;

	@Transient
	private Collection<Actividad> actividadFormularioCollection;

	@Transient
	private PersonaNatural conyuge;

	@Transient
	private String identificacionOriginal;// para ver si cambio el numero de
											// documento

	@Transient
	private String nombres;

	@Transient
	private String nombresApellidos;

	@Transient
	private String apellidosNombres;

	@Transient
	private String nombreApellido;

	@Transient
	private String apellidos;

	@Transient
	private String respuestaPep;

	@Transient
	private Edad edad;

	// @Transient
	// private boolean conDatosCompletos;// PARA ACTUALIZACION, para diferenciar
	// si
	// tiene todos los datos para el
	// formulario o no (por ejemplo cuando
	// es conyuge no carga
	// contacto preferido, direciones, etc)

	// auditoria
	@Column(name = "USR_CREACION")
	private String usrCreacion;

	@Column(name = "TTY_CREACION")
	private String ttyCreacion;// terminal

	@Column(name = "PRG_CREACION")
	private String prgCreacion;

	@Column(name = "CTA_CREACION")
	private String ctaCreacion;

	@Column(name = "USR_MODIFICACION")
	private String usrModificacion;

	@Column(name = "TTY_MODIFICACION")
	private String ttyModificacion;

	@Column(name = "PRG_MODIFICACION")
	private String prgModificacion;

	@Column(name = "CTA_MODIFICACION")
	private String ctaModificacion;

	@Transient
	public Collection<PersonaPe> getPersonaPeCollectionActivosCollection() {
		Collection<PersonaPe> activos = new ArrayList<PersonaPe>();
		for (PersonaPe pe : personaPeCollection) {
			if (pe.getActivo()) {
				activos.add(pe);
			}
		}
		return activos;
	}

	@Transient
	private boolean casillaExtranjero;

	public boolean isCasillaExtranjero() {
		return casillaExtranjero;

	}

	public void setCasillaExtranjero(boolean casillaExtranjero) {
		this.casillaExtranjero = casillaExtranjero;
	}

	@Transient
	public int getTotalPersonasPeActivos() {
		return getPersonaPeCollectionActivosCollection().size();
	}

	@Transient
	public Collection<SeguroVigente> getSegurosVigentesActivosCollection() {
		Collection<SeguroVigente> activos = new ArrayList<SeguroVigente>();
		for (SeguroVigente sv : segurosVigentesCollection) {
			if (sv.getActivo()) {
				activos.add(sv);
			}
		}
		return activos;
	}

	@Transient
	public int getTotalSegurosVigentesActivos() {
		return getSegurosVigentesActivosCollection().size();
	}

	@Transient
	public Collection<EmpleoDto> getEmpleoActivosCollection() {
		Collection<EmpleoDto> activos = new ArrayList<EmpleoDto>();
		for (EmpleoDto e : empleoCollection) {
			if (e.getActivo()) {
				activos.add(e);
			}
		}
		return activos;
	}

	@Transient
	public int getTotalEmpleosActivos() {
		return getEmpleoActivosCollection().size();
	}

	@Transient
	private PersonaNatural original;

	public PersonaNatural() {
	}

	public PersonaNatural(Integer secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
	}

	public PersonaNatural(Integer secPersonaNatural, String identificacion,
			String apellidoPaterno, String primerNombre, char sexo,
			short tipoEmpleo, short numHijos, Date fchNacimiento,
			BigDecimal mntSaldoMensual, Date tsCreacion, Date tsModificacion) {
		this.secPersonaNatural = secPersonaNatural;
		this.identificacion = identificacion;
		this.apellidoPaterno = apellidoPaterno;
		this.primerNombre = primerNombre;
		this.sexo = sexo;
		this.tipoEmpleo = tipoEmpleo;
		this.numHijos = numHijos;
		this.fchNacimiento = fchNacimiento;
		this.mntSaldoMensual = mntSaldoMensual;
		this.tsCreacion = tsCreacion;
		this.tsModificacion = tsModificacion;
	}

	@Transient
	public boolean isConConyuge() {
		boolean conCunyuge = false;
		EstadoCivilEnum[] conConyuge = EstadoCivilEnum.getEstadoConConyuge();
		for (EstadoCivilEnum estadoCivilEnum : conConyuge) {
			if (this.getEstadoCivil().getCodEstadoCivil()
					.compareTo(estadoCivilEnum.getCodigo()) == 0) {
				conCunyuge = true;
			}
		}
		return conCunyuge;
	}

	@Transient
	public String getLugarNacimientoParaSise() {
		String lugar = "";

		if (ciudadNacimiento.getSecCiudad() != null) {
			lugar = lugar + ciudadNacimiento.getCiudad() + " "
					+ ciudadNacimiento.getPais().getPais();
		}

		// se garantiza que no se mande un texto mas largo de 40
		if (lugar.length() >= 40) {
			lugar = lugar.substring(0, 39);
		}

		return lugar;
	}

	public Integer getSecPersonaNatural() {
		return secPersonaNatural;
	}

	public void setSecPersonaNatural(Integer secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		if (apellidoPaterno != null) {
			this.apellidoPaterno = StringUtil.toUpper(apellidoPaterno).trim();
		} else {
			this.apellidoPaterno = apellidoPaterno;
		}
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		if (apellidoMaterno != null) {
			this.apellidoMaterno = StringUtil.toUpper(apellidoMaterno).trim();
		} else {
			this.apellidoMaterno = apellidoMaterno;
		}
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		if (primerNombre != null) {
			this.primerNombre = StringUtil.toUpper(primerNombre).trim();
		} else {
			this.primerNombre = primerNombre;
		}
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		if (segundoNombre != null) {
			this.segundoNombre = StringUtil.toUpper(segundoNombre).trim();
		} else {
			this.segundoNombre = segundoNombre;
		}
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public short getTipoEmpleo() {
		return tipoEmpleo;
	}

	public void setTipoEmpleo(short tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	public short getNumHijos() {
		return numHijos;
	}

	public void setNumHijos(short numHijos) {
		this.numHijos = numHijos;
	}

	public Date getFchNacimiento() {
		return fchNacimiento;
	}

	public void setFchNacimiento(Date fchNacimiento) {
		this.fchNacimiento = fchNacimiento;
	}

	public BigDecimal getMntSaldoMensual() {
		return mntSaldoMensual;
	}

	public void setMntSaldoMensual(BigDecimal mntSaldoMensual) {
		this.mntSaldoMensual = mntSaldoMensual;
	}

	public Date getFchFallecimiento() {
		return fchFallecimiento;
	}

	public void setFchFallecimiento(Date fchFallecimiento) {
		this.fchFallecimiento = fchFallecimiento;
	}

	public Date getTsCreacion() {
		return tsCreacion;
	}

	public void setTsCreacion(Date tsCreacion) {
		this.tsCreacion = tsCreacion;
	}

	public Date getTsModificacion() {
		return tsModificacion;
	}

	public void setTsModificacion(Date tsModificacion) {
		this.tsModificacion = tsModificacion;
	}

	public Collection<IngresoMensualAdicional> getIngresoMensualAdicionalCollection() {
		return ingresoMensualAdicionalCollection;
	}

	public void setIngresoMensualAdicionalCollection(
			Collection<IngresoMensualAdicional> ingresoMensualAdicionalCollection) {
		this.ingresoMensualAdicionalCollection = ingresoMensualAdicionalCollection;
	}

	public Collection<IngresoAnual> getIngresoAnualCollection() {
		return ingresoAnualCollection;
	}

	public void setIngresoAnualCollection(
			Collection<IngresoAnual> ingresoAnualCollection) {
		this.ingresoAnualCollection = ingresoAnualCollection;
	}

	public Collection<SeguroAdicional> getSeguroAdicionalCollection() {
		return seguroAdicionalCollection;
	}

	public void setSeguroAdicionalCollection(
			Collection<SeguroAdicional> seguroAdicionalCollection) {
		this.seguroAdicionalCollection = seguroAdicionalCollection;
	}

	public InformacionAdicional getInformacionAdicional() {
		return informacionAdicional;
	}

	public void setInformacionAdicional(
			InformacionAdicional informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	public Collection<SeguroVigente> getSegurosVigentesCollection() {
		return segurosVigentesCollection;
	}

	public void setSegurosVigentesCollection(
			Collection<SeguroVigente> segurosVigentesCollection) {
		this.segurosVigentesCollection = segurosVigentesCollection;
	}

	public Collection<PersonaPe> getPersonaPeCollection() {
		return personaPeCollection;
	}

	public void setPersonaPeCollection(Collection<PersonaPe> personaPeCollection) {
		this.personaPeCollection = personaPeCollection;
	}

	public Collection<Actividad> getActividadCollection() {
		return actividadCollection;
	}

	public void setActividadCollection(Collection<Actividad> actividadCollection) {
		this.actividadCollection = actividadCollection;
	}

	public Collection<MedicoConsultado> getMedicoConsultadoCollection() {
		return medicoConsultadoCollection;
	}

	public void setMedicoConsultadoCollection(
			Collection<MedicoConsultado> medicoConsultadoCollection) {
		this.medicoConsultadoCollection = medicoConsultadoCollection;
	}

	// public Collection<Referencia> getReferenciaCollection() {
	// return referenciaCollection;
	// }

	// public void setReferenciaCollection(
	// Collection<Referencia> referenciaCollection) {
	// this.referenciaCollection = referenciaCollection;
	// }

	public Biometrica getBiometrica() {
		return biometrica;
	}

	public void setBiometrica(Biometrica biometrica) {
		this.biometrica = biometrica;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public Profesion getProfesion() {
		return profesion;
	}

	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Pais getPaisNacionalidad() {
		return paisNacionalidad;
	}

	public void setPaisNacionalidad(Pais pais) {
		this.paisNacionalidad = pais;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Collection<HistoriaMedicaFamiliar> getHistoriaMedicaFamiliarCollection() {
		return historiaMedicaFamiliarCollection;
	}

	public void setHistoriaMedicaFamiliarCollection(
			Collection<HistoriaMedicaFamiliar> historiaMedicaFamiliarCollection) {
		this.historiaMedicaFamiliarCollection = historiaMedicaFamiliarCollection;
	}

	public IngresoMensual getIngresoMensual() {
		return ingresoMensual;
	}

	public void setIngresoMensual(IngresoMensual ingresoMensual) {
		this.ingresoMensual = ingresoMensual;
	}

	public Collection<EmpleoIndependiente> getEmpleoIndependienteCollection() {
		return empleoIndependienteCollection;
	}

	public void setEmpleoIndependienteCollection(
			Collection<EmpleoIndependiente> empleoIndependienteCollection) {
		this.empleoIndependienteCollection = empleoIndependienteCollection;
	}

	public void setEmpleoDependienteCollection(
			Collection<EmpleoDependiente> empleoDependienteCollection) {
		this.empleoDependienteCollection = empleoDependienteCollection;
	}

	public Collection<EmpleoDependiente> getEmpleoDependienteCollection() {
		return empleoDependienteCollection;
	}

	@Transient
	public boolean isMasculino() {
		return (this.getSexo() == SexoEnum.MASCULINO.getCodigo());
	}

	@Transient
	public boolean isFemenino() {
		return (this.getSexo() == SexoEnum.FEMENINO.getCodigo());
	}

	/**
	 * @return the perfilFinancieroNatural
	 */
	public PerfilFinancieroNatural getPerfilFinancieroNatural() {
		return perfilFinancieroNatural;
	}

	/**
	 * @param perfilFinancieroNatural
	 *            the perfilFinancieroNatural to set
	 */
	public void setPerfilFinancieroNatural(
			PerfilFinancieroNatural perfilFinancieroNatural) {
		this.perfilFinancieroNatural = perfilFinancieroNatural;
	}

	/**
	 * @return the ocupacion
	 */
	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	/**
	 * @param ocupacion
	 *            the ocupacion to set
	 */
	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	public Collection<EmpleoDto> getEmpleoCollection() {
		return empleoCollection;
	}

	public void setEmpleoCollection(Collection<EmpleoDto> empleoCollection) {
		this.empleoCollection = empleoCollection;
	}

	/**
	 * @return the detalleActividadFuncion
	 */
	public DetalleActividadFuncion getDetalleActividadFuncion() {
		return detalleActividadFuncion;
	}

	/**
	 * @param detalleActividadFuncion
	 *            the detalleActividadFuncion to set
	 */
	public void setDetalleActividadFuncion(
			DetalleActividadFuncion detalleActividadFuncion) {
		this.detalleActividadFuncion = detalleActividadFuncion;
	}

	public String getIdentificacionOriginal() {
		return identificacionOriginal;
	}

	public void setIdentificacionOriginal(String identificacionOriginal) {
		this.identificacionOriginal = identificacionOriginal;
	}

	/**
	 * @return the otroEmpleoCollection
	 */
	public Collection<OtroEmpleo> getOtroEmpleoCollection() {
		return otroEmpleoCollection;
	}

	/**
	 * @param otroEmpleoCollection
	 *            the otroEmpleoCollection to set
	 */
	public void setOtroEmpleoCollection(
			Collection<OtroEmpleo> otroEmpleoCollection) {
		this.otroEmpleoCollection = otroEmpleoCollection;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		if (nombres == null) {
			nombres = primerNombre + " " + segundoNombre;
		}
		return nombres;
	}

	/**
	 * @param nombres
	 *            the nombres to set
	 */
	public void setNombres(String nombres) {
		if (nombres != null) {
			this.nombres = StringUtil.toUpper(nombres).trim();
		} else {
			this.nombres = nombres;
		}
	}

	/**
	 * @return the respuestaPep
	 */
	public String getRespuestaPep() {
		return respuestaPep;
	}

	/**
	 * @param respuestaPep
	 *            the respuestaPep to set
	 */
	public void setRespuestaPep(String respuestaPep) {
		this.respuestaPep = respuestaPep;
	}

	public String getNombresApellidos() {
		if (nombresApellidos == null) {
			if (primerNombre == null) {
				primerNombre = "";
			}
			if (segundoNombre == null) {
				segundoNombre = "";
			}
			if (apellidoPaterno == null) {
				apellidoPaterno = "";
			}
			if (apellidoMaterno == null) {
				apellidoMaterno = "";
			}

			nombresApellidos = primerNombre.trim() + " " + segundoNombre.trim()
					+ " " + apellidoPaterno.trim() + " "
					+ apellidoMaterno.trim();
		}
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getNombreApellido() {
		if (nombreApellido == null) {
			if (primerNombre == null) {
				primerNombre = "";
			}
			if (apellidoPaterno == null) {
				apellidoPaterno = "";
			}
			nombreApellido = primerNombre + " " + apellidoPaterno;
		}
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getApellidos() {
		if (apellidos == null) {
			if (apellidoPaterno == null) {
				apellidoPaterno = "";
			}
			if (apellidoMaterno == null) {
				apellidoMaterno = "";
			}
			apellidos = apellidoPaterno + " " + apellidoMaterno;
		}
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		if (apellidos != null) {
			this.apellidos = apellidos;
		} else {
			this.apellidos = apellidos;
		}
	}

	public Ciudad getCiudadNacimiento() {
		return ciudadNacimiento;
	}

	public void setCiudadNacimiento(Ciudad ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public Edad getEdad() {
		if (fchNacimiento != null) {
			edad = new Edad(fchNacimiento.getTime());
		} else {
			edad = new Edad();
			edad.setEdadTotal("");
		}
		return edad;
	}

	public void setEdad(Edad edad) {
		this.edad = edad;
	}

	public PersonaNatural getConyuge() {
		return conyuge;
	}

	public void setConyuge(PersonaNatural conyuge) {
		this.conyuge = conyuge;
	}

	public TipoRiesgo getTipoRiesgo() {
		return tipoRiesgo;
	}

	public void setTipoRiesgo(TipoRiesgo tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}

	public Collection<Actividad> getActividadFormularioCollection() {
		return actividadFormularioCollection;
	}

	public void setActividadFormularioCollection(
			Collection<Actividad> actividadFormularioCollection) {
		this.actividadFormularioCollection = actividadFormularioCollection;
	}

	public Collection<DeportePractica> getDeportePracticaCollection() {
		return deportePracticaCollection;
	}

	public void setDeportePracticaCollection(
			Collection<DeportePractica> deportePracticaCollection) {
		this.deportePracticaCollection = deportePracticaCollection;
	}

	public Collection<HabitoEnfermedad> getHabitoEnfermedadFormularioCollection() {
		return habitoEnfermedadFormularioCollection;
	}

	public void setHabitoEnfermedadFormularioCollection(
			Collection<HabitoEnfermedad> habitoEnfermedadFormularioCollection) {
		this.habitoEnfermedadFormularioCollection = habitoEnfermedadFormularioCollection;
	}

	public Collection<EstadoPersona> getEstadoPersonaCollection() {
		return estadoPersonaCollection;
	}

	public void setEstadoPersonaCollection(
			Collection<EstadoPersona> estadoPersonaCollection) {
		this.estadoPersonaCollection = estadoPersonaCollection;
	}

	public Collection<HistoriaMedicaFamiliar> getHistoriaMedicaFamiliarFormularioCollection() {
		return historiaMedicaFamiliarFormularioCollection;
	}

	public void setHistoriaMedicaFamiliarFormularioCollection(
			Collection<HistoriaMedicaFamiliar> historiaMedicaFamiliarFormularioCollection) {
		this.historiaMedicaFamiliarFormularioCollection = historiaMedicaFamiliarFormularioCollection;
	}

	public Collection<SeguroAdicional> getSeguroAdicionalFormularioCollection() {
		return seguroAdicionalFormularioCollection;
	}

	public void setSeguroAdicionalFormularioCollection(
			Collection<SeguroAdicional> seguroAdicionalFormularioCollection) {
		this.seguroAdicionalFormularioCollection = seguroAdicionalFormularioCollection;
	}

	public String getApellidosNombres() {
		if (apellidosNombres == null) {
			if (primerNombre == null) {
				primerNombre = "";
			}
			if (segundoNombre == null) {
				segundoNombre = "";
			}
			if (apellidoPaterno == null) {
				apellidoPaterno = "";
			}
			if (apellidoMaterno == null) {
				apellidoMaterno = "";
			}

			apellidosNombres = apellidoPaterno.trim();
			if (!apellidoMaterno.equals("")) {
				apellidosNombres += " ";
			}
			apellidosNombres += apellidoMaterno.trim();

			if (!primerNombre.equals("")) {
				apellidosNombres += " ";
			}
			apellidosNombres += primerNombre.trim();

			if (!segundoNombre.equals("")) {
				apellidosNombres += " ";
			}
			apellidosNombres += segundoNombre.trim();

			apellidosNombres = apellidosNombres.trim();
		}
		return apellidosNombres;
	}

	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}

	// public boolean isConDatosCompletos() {
	// return conDatosCompletos;
	// }

	// public void setConDatosCompletos(boolean conDatosCompletos) {
	// this.conDatosCompletos = conDatosCompletos;
	// }

	public Collection<Referencia> getReferenciaFormularioCollection() {
		return referenciaFormularioCollection;
	}

	public void setReferenciaFormularioCollection(
			Collection<Referencia> referenciaFormularioCollection) {
		this.referenciaFormularioCollection = referenciaFormularioCollection;
	}

	public String getUsrCreacion() {
		return usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public String getTtyCreacion() {
		return ttyCreacion;
	}

	public void setTtyCreacion(String ttyCreacion) {
		this.ttyCreacion = ttyCreacion;
	}

	public String getPrgCreacion() {
		return prgCreacion;
	}

	public void setPrgCreacion(String prgCreacion) {
		this.prgCreacion = prgCreacion;
	}

	public String getCtaCreacion() {
		return ctaCreacion;
	}

	public void setCtaCreacion(String ctaCreacion) {
		this.ctaCreacion = ctaCreacion;
	}

	public String getUsrModificacion() {
		return usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public String getTtyModificacion() {
		return ttyModificacion;
	}

	public void setTtyModificacion(String ttyModificacion) {
		this.ttyModificacion = ttyModificacion;
	}

	public String getPrgModificacion() {
		return prgModificacion;
	}

	public void setPrgModificacion(String prgModificacion) {
		this.prgModificacion = prgModificacion;
	}

	public String getCtaModificacion() {
		return ctaModificacion;
	}

	public void setCtaModificacion(String ctaModificacion) {
		this.ctaModificacion = ctaModificacion;
	}

	public PersonaNatural getOriginal() {
		return original;
	}

	public void setOriginal(PersonaNatural original) {
		this.original = original;
	}

	@Override
	public String toString() {
		return "PersonaNatural [secPersonaNatural=" + secPersonaNatural
				+ ", identificacion=" + identificacion + ", apellidoPaterno="
				+ apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno
				+ ", primerNombre=" + primerNombre + ", segundoNombre="
				+ segundoNombre + ", sexo=" + sexo + ", tipoEmpleo="
				+ tipoEmpleo + ", numHijos=" + numHijos + ", fchNacimiento="
				+ fchNacimiento + ", mntSaldoMensual=" + mntSaldoMensual
				+ ", fchFallecimiento=" + fchFallecimiento
				+ ", tipoIdentificacion=" + tipoIdentificacion + ", profesion="
				+ profesion + ", paisNacionalidad=" + paisNacionalidad
				+ ", ciudadNacimiento=" + ciudadNacimiento + ", ocupacion="
				+ ocupacion + ", estadoCivil=" + estadoCivil + ", tipoRiesgo="
				+ tipoRiesgo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellidoMaterno == null) ? 0 : apellidoMaterno.hashCode());
		result = prime * result
				+ ((apellidoPaterno == null) ? 0 : apellidoPaterno.hashCode());
		result = prime
				* result
				+ ((ciudadNacimiento == null) ? 0 : ciudadNacimiento.hashCode());
		result = prime * result
				+ ((estadoCivil == null) ? 0 : estadoCivil.hashCode());
		result = prime
				* result
				+ ((fchFallecimiento == null) ? 0 : fchFallecimiento.hashCode());
		result = prime * result
				+ ((fchNacimiento == null) ? 0 : fchNacimiento.hashCode());
		result = prime * result
				+ ((identificacion == null) ? 0 : identificacion.hashCode());
		result = prime
				* result
				+ ((informacionAdicional == null) ? 0 : informacionAdicional
						.hashCode());
		result = prime * result
				+ ((mntSaldoMensual == null) ? 0 : mntSaldoMensual.hashCode());
		result = prime * result + numHijos;
		result = prime * result
				+ ((ocupacion == null) ? 0 : ocupacion.hashCode());
		result = prime
				* result
				+ ((paisNacionalidad == null) ? 0 : paisNacionalidad.hashCode());
		result = prime * result
				+ ((profesion == null) ? 0 : profesion.hashCode());
		result = prime * result
				+ ((segundoNombre == null) ? 0 : segundoNombre.hashCode());
		result = prime * result + sexo;
		result = prime
				* result
				+ ((tipoIdentificacion == null) ? 0 : tipoIdentificacion
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaNatural other = (PersonaNatural) obj;
		if (apellidoMaterno == null) {
			if (other.apellidoMaterno != null)
				return false;
		} else if (!apellidoMaterno.equals(other.apellidoMaterno))
			return false;
		if (apellidoPaterno == null) {
			if (other.apellidoPaterno != null)
				return false;
		} else if (!apellidoPaterno.equals(other.apellidoPaterno))
			return false;
		if (ciudadNacimiento == null) {
			if (other.ciudadNacimiento != null)
				return false;
		} else if (!ciudadNacimiento.equals(other.ciudadNacimiento))
			return false;
		if (estadoCivil == null) {
			if (other.estadoCivil != null)
				return false;
		} else if (!estadoCivil.equals(other.estadoCivil))
			return false;
		if (fchFallecimiento == null) {
			if (other.fchFallecimiento != null)
				return false;
		} else if (!fchFallecimiento.equals(other.fchFallecimiento))
			return false;
		if (fchNacimiento == null) {
			if (other.fchNacimiento != null)
				return false;
		} else if (!fchNacimiento.equals(other.fchNacimiento))
			return false;
		if (identificacion == null) {
			if (other.identificacion != null)
				return false;
		} else if (!identificacion.equals(other.identificacion))
			return false;
		if (informacionAdicional == null) {
			if (other.informacionAdicional != null)
				return false;
		} else if (!informacionAdicional.equals(other.informacionAdicional))
			return false;
		if (mntSaldoMensual == null) {
			if (other.mntSaldoMensual != null)
				return false;
		} else if (!mntSaldoMensual.equals(other.mntSaldoMensual))
			return false;
		if (numHijos != other.numHijos)
			return false;
		if (ocupacion == null) {
			if (other.ocupacion != null)
				return false;
		} else if (!ocupacion.equals(other.ocupacion))
			return false;
		if (paisNacionalidad == null) {
			if (other.paisNacionalidad != null)
				return false;
		} else if (!paisNacionalidad.equals(other.paisNacionalidad))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		if (primerNombre == null) {
			if (other.primerNombre != null)
				return false;
		} else if (!primerNombre.equals(other.primerNombre))
			return false;
		if (profesion == null) {
			if (other.profesion != null)
				return false;
		} else if (!profesion.equals(other.profesion))
			return false;
		if (segundoNombre == null) {
			if (other.segundoNombre != null)
				return false;
		} else if (!segundoNombre.equals(other.segundoNombre))
			return false;
		if (sexo != other.sexo)
			return false;
		if (tipoIdentificacion == null) {
			if (other.tipoIdentificacion != null)
				return false;
		} else if (!tipoIdentificacion.equals(other.tipoIdentificacion))
			return false;
		return true;
	}

	/**
	 * @return the referenciaBancariaFormularioCollection
	 */
	public Collection<ReferenciaBancaria> getReferenciaBancariaFormularioCollection() {
		return referenciaBancariaFormularioCollection;
	}

	/**
	 * @param referenciaBancariaFormularioCollection
	 *            the referenciaBancariaFormularioCollection to set
	 */
	public void setReferenciaBancariaFormularioCollection(
			Collection<ReferenciaBancaria> referenciaBancariaFormularioCollection) {
		this.referenciaBancariaFormularioCollection = referenciaBancariaFormularioCollection;
	}

	/**
	 * @return the referenciaComercialFormularioCollection
	 */
	public Collection<ReferenciaComercial> getReferenciaComercialFormularioCollection() {
		return referenciaComercialFormularioCollection;
	}

	/**
	 * @param referenciaComercialFormularioCollection
	 *            the referenciaComercialFormularioCollection to set
	 */
	public void setReferenciaComercialFormularioCollection(
			Collection<ReferenciaComercial> referenciaComercialFormularioCollection) {
		this.referenciaComercialFormularioCollection = referenciaComercialFormularioCollection;
	}

	/**
	 * @return the otraOcupacionCollection
	 */
	public Collection<OtraOcupacion> getOtraOcupacionCollection() {
		return otraOcupacionCollection;
	}

	/**
	 * @param otraOcupacionCollection
	 *            the otraOcupacionCollection to set
	 */
	public void setOtraOcupacionCollection(
			Collection<OtraOcupacion> otraOcupacionCollection) {
		this.otraOcupacionCollection = otraOcupacionCollection;
	}

	/**
	 * @return the perfilFinancieroNaturalTmp
	 */
	public PerfilFinancieroNaturalTmp getPerfilFinancieroNaturalTmp() {
		return perfilFinancieroNaturalTmp;
	}

	/**
	 * @param perfilFinancieroNaturalTmp
	 *            the perfilFinancieroNaturalTmp to set
	 */
	public void setPerfilFinancieroNaturalTmp(
			PerfilFinancieroNaturalTmp perfilFinancieroNaturalTmp) {
		this.perfilFinancieroNaturalTmp = perfilFinancieroNaturalTmp;
	}

	public DetallePasaporte getDetallePasaporte() {
		return detallePasaporte;
	}

	public void setDetallePasaporte(DetallePasaporte detallePasaporte) {
		this.detallePasaporte = detallePasaporte;
	}
}