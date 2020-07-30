/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "PERSONA_NATURAL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "secPersonaNatural", "codTipoIdentificacion",
		"identificacion", "apellidoPaterno", "apellidoMaterno", "primerNombre",
		"segundoNombre", "sexoStr", "fchNacimiento", "fchMatrimonio",
		"fchFallecimiento", "informacionAdicionalList",
		"empleoDependienteList", "secProfesion", "codPais", "codEstadoCivil",
		"conyuge" })
@NamedQueries({ @NamedQuery(name = "PersonaNaturalSd.findByIdentificacion", query = "SELECT p FROM PersonaNaturalSd p WHERE p.identificacion = :identificacion") })
public class PersonaNaturalSd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "SEC_PERSONA_NATURAL")
	private Integer secPersonaNatural;
	@Basic(optional = false)
	@Column(name = "IDENTIFICACION", length = 24, nullable = false)
	private String identificacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 24)
	@Column(name = "APELLIDO_PATERNO")
	private String apellidoPaterno;
	@Size(max = 24)
	@Column(name = "APELLIDO_MATERNO")
	private String apellidoMaterno;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 16)
	@Column(name = "PRIMER_NOMBRE")
	private String primerNombre;
	@Size(max = 30)
	@Column(name = "SEGUNDO_NOMBRE")
	private String segundoNombre;
	@Basic(optional = false)
	@NotNull
	@Column(name = "SEXO")
	@XmlTransient
	private char sexo;
	@Basic(optional = false)
	@NotNull
	@Column(name = "FCH_NACIMIENTO")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaNacimiento")
	@XmlSchemaType(name = "date")
	private Date fchNacimiento;
	@Column(name = "FCH_MATRIMONIO")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaMatrimonio")
	@XmlSchemaType(name = "date")
	private Date fchMatrimonio;
	@Column(name = "FCH_FALLECIMIENTO")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "fechaFallecimiento")
	@XmlSchemaType(name = "date")
	private Date fchFallecimiento;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "USR_CREACION")
	@XmlTransient
	private String usrCreacion;
	@Basic(optional = false)
	@Column(name = "TS_CREACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date tsCreacion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "USR_MODIFICACION")
	@XmlTransient
	private String usrModificacion;
	@Basic(optional = false)
	@Column(name = "TS_MODIFICACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date tsModificacion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "secPersonaNatural")
	@XmlElement(name = "informacionAdicional")
	private List<InformacionAdicionalSd> informacionAdicionalList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personaNatural")
	@XmlElement(name = "empleoDependiente")
	private List<EmpleoDependienteSd> empleoDependienteList;

	@JoinColumn(name = "COD_TIPO_IDENTIFICACION", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = false)
	@XmlElement(name = "tipoIdentificacion")
	private TipoIdentificacionSd codTipoIdentificacion;
	@JoinColumn(name = "SEC_PROFESION", referencedColumnName = "SEC_PROFESION")
	@ManyToOne(optional = false)
	@XmlElement(name = "profesion")
	private ProfesionSd secProfesion;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@OneToOne(optional = false)
	@XmlTransient
	private PersonaSd secPersona;
	@JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS")
	@ManyToOne(optional = false)
	@XmlElement(name = "pais")
	private PaisSd codPais;
	@JoinColumn(name = "COD_ESTADO_CIVIL", referencedColumnName = "COD_ESTADO_CIVIL")
	@ManyToOne(optional = false)
	@XmlElement(name = "estadoCivil")
	private EstadoCivilSd codEstadoCivil;
	@JoinColumn(name = "SEC_CANAL", referencedColumnName = "SEC_CANAL")
	@ManyToOne(optional = false)
	@XmlTransient
	private CanalSd secCanal;

	@Transient
	@XmlTransient
	private String nombresApellidos;

	@Transient
	private RelacionSd conyuge;

	@Transient
	@XmlElement(name = "sexo")
	private String sexoStr;

	public PersonaNaturalSd() {
	}

	public PersonaNaturalSd(Integer secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
	}

	public PersonaNaturalSd(Integer secPersonaNatural, String identificacion,
			String apellidoPaterno, String primerNombre, char sexo,
			Date fchNacimiento, String usrCreacion, Date tsCreacion,
			String usrModificacion, Date tsModificacion) {
		this.secPersonaNatural = secPersonaNatural;
		this.identificacion = identificacion;
		this.apellidoPaterno = apellidoPaterno;
		this.primerNombre = primerNombre;
		this.sexo = sexo;
		this.fchNacimiento = fchNacimiento;
		this.usrCreacion = usrCreacion;
		this.tsCreacion = tsCreacion;
		this.usrModificacion = usrModificacion;
		this.tsModificacion = tsModificacion;
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
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Date getFchNacimiento() {
		return fchNacimiento;
	}

	public void setFchNacimiento(Date fchNacimiento) {
		this.fchNacimiento = fchNacimiento;
	}

	public Date getFchMatrimonio() {
		return fchMatrimonio;
	}

	public void setFchMatrimonio(Date fchMatrimonio) {
		this.fchMatrimonio = fchMatrimonio;
	}

	public Date getFchFallecimiento() {
		return fchFallecimiento;
	}

	public void setFchFallecimiento(Date fchFallecimiento) {
		this.fchFallecimiento = fchFallecimiento;
	}

	public String getUsrCreacion() {
		return usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public Date getTsCreacion() {
		return tsCreacion;
	}

	public void setTsCreacion(Date tsCreacion) {
		this.tsCreacion = tsCreacion;
	}

	public String getUsrModificacion() {
		return usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public Date getTsModificacion() {
		return tsModificacion;
	}

	public void setTsModificacion(Date tsModificacion) {
		this.tsModificacion = tsModificacion;
	}

	public TipoIdentificacionSd getCodTipoIdentificacion() {
		return codTipoIdentificacion;
	}

	public void setCodTipoIdentificacion(
			TipoIdentificacionSd codTipoIdentificacion) {
		this.codTipoIdentificacion = codTipoIdentificacion;
	}

	public ProfesionSd getSecProfesion() {
		return secProfesion;
	}

	public void setSecProfesion(ProfesionSd secProfesion) {
		this.secProfesion = secProfesion;
	}

	public PersonaSd getSecPersona() {
		return secPersona;
	}

	public void setSecPersona(PersonaSd secPersona) {
		this.secPersona = secPersona;
	}

	public PaisSd getCodPais() {
		return codPais;
	}

	public void setCodPais(PaisSd codPais) {
		this.codPais = codPais;
	}

	public EstadoCivilSd getCodEstadoCivil() {
		return codEstadoCivil;
	}

	public void setCodEstadoCivil(EstadoCivilSd codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}

	public CanalSd getSecCanal() {
		return secCanal;
	}

	public void setSecCanal(CanalSd secCanal) {
		this.secCanal = secCanal;
	}

	public List<EmpleoDependienteSd> getEmpleoDependienteList() {
		return empleoDependienteList;
	}

	public void setEmpleoDependienteList(
			List<EmpleoDependienteSd> empleoDependienteList) {
		this.empleoDependienteList = empleoDependienteList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secPersonaNatural != null ? secPersonaNatural.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PersonaNaturalSd)) {
			return false;
		}
		PersonaNaturalSd other = (PersonaNaturalSd) object;
		if ((this.secPersonaNatural == null && other.secPersonaNatural != null)
				|| (this.secPersonaNatural != null && !this.secPersonaNatural
						.equals(other.secPersonaNatural))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.smartdata.model.PersonaNatural[ secPersonaNatural="
				+ secPersonaNatural + " ]";
	}

	public String getNombresApellidos() {
		StringBuffer resp = new StringBuffer(800);
		if (this.primerNombre != null && this.primerNombre.trim().length() > 0) {
			resp.append(this.primerNombre.trim()).append(" ");
		}

		if (this.segundoNombre != null
				&& this.segundoNombre.trim().length() > 0) {
			resp.append(this.segundoNombre.trim()).append(" ");
		}

		if (this.apellidoPaterno != null
				&& this.apellidoPaterno.trim().length() > 0) {
			resp.append(this.segundoNombre.trim()).append(" ");
		}

		if (this.apellidoMaterno != null
				&& this.apellidoMaterno.trim().length() > 0) {
			resp.append(this.segundoNombre.trim()).append(" ");
		}

		nombresApellidos = resp.toString().trim();

		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public List<InformacionAdicionalSd> getInformacionAdicionalList() {
		return informacionAdicionalList;
	}

	public void setInformacionAdicionalList(
			List<InformacionAdicionalSd> informacionAdicionalList) {
		this.informacionAdicionalList = informacionAdicionalList;
	}

	/**
	 * @return the sexoStr
	 */
	public String getSexoStr() {
		sexoStr = new String(new char[] { this.sexo });

		return sexoStr;
	}

	/**
	 * @param sexoStr
	 *            the sexoStr to set
	 */
	public void setSexoStr(String sexoStr) {
		this.sexoStr = sexoStr;
	}

}
