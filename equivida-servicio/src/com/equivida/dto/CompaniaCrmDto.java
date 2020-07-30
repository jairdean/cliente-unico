package com.equivida.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CompaniaCrmDto {

	private String codTipoIdentificacion;
	private String identificacion;
	private Integer secuencialPersona;
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaNacimiento;
	private String estadoCivil;
	private String sexo;
	private int secuencialProfesion;
	private String nivelEducacion;
	private int codOcupacion;
	private String conyuge;
	private int numHijos;
	private String horaInicioContacto;
	private String horaFinContacto;

	private String relacionLaboral;
	private String empresaTrabaja;
	private String cargo;

	private String tipoEmpleo;
	private String dependiente;
	private String independiente;
	private String email;
	private String emailAlterno;

	// direccion
	private String tipoDireccion;
	private String direccion;// string completo con comas

	private String codPais;// 0
	private String region;// 1
	private String codProv;// 2
	private String codCanton;// 3
	private String ciudad;// 4
	private String codParr;// 5
	private String barrio;// 6
	private String dirCalle1;// 7
	private String dirNumero;// 8
	private String dirCalle2;// 9
	private String dirEdif;// 10
	private String longitud;// 11
	private String latitud;// 12
	private String referencia;// 13

	private String tipoTelefono;
	private String telefono;
	private String codTelPais;
	private String codArea;
	private String tel;
	private String telExt;

	private String contacto;
	private String emailContacto;
	private BigDecimal montoIngresoMensual;

	/**
	 * @return the codTipoIdentificacion
	 */
	public String getCodTipoIdentificacion() {
		return codTipoIdentificacion;
	}

	/**
	 * @param codTipoIdentificacion
	 *            the codTipoIdentificacion to set
	 */
	public void setCodTipoIdentificacion(String codTipoIdentificacion) {
		this.codTipoIdentificacion = codTipoIdentificacion;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion
	 *            the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the secuencialPersona
	 */
	public Integer getSecuencialPersona() {
		return secuencialPersona;
	}

	/**
	 * @param secuencialPersona
	 *            the secuencialPersona to set
	 */
	public void setSecuencialPersona(Integer secuencialPersona) {
		this.secuencialPersona = secuencialPersona;
	}

	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @param primerNombre
	 *            the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @param segundoNombre
	 *            the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno
	 *            the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno
	 *            the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil
	 *            the estadoCivil to set
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the secuencialProfesion
	 */
	public int getSecuencialProfesion() {
		return secuencialProfesion;
	}

	/**
	 * @param secuencialProfesion
	 *            the secuencialProfesion to set
	 */
	public void setSecuencialProfesion(int secuencialProfesion) {
		this.secuencialProfesion = secuencialProfesion;
	}

	/**
	 * @return the nivelEducacion
	 */
	public String getNivelEducacion() {
		return nivelEducacion;
	}

	/**
	 * @param nivelEducacion
	 *            the nivelEducacion to set
	 */
	public void setNivelEducacion(String nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	/**
	 * @return the codOcupacion
	 */
	public int getCodOcupacion() {
		return codOcupacion;
	}

	/**
	 * @param codOcupacion
	 *            the codOcupacion to set
	 */
	public void setCodOcupacion(int codOcupacion) {
		this.codOcupacion = codOcupacion;
	}

	/**
	 * @return the conyuge
	 */
	public String getConyuge() {
		return conyuge;
	}

	/**
	 * @param conyuge
	 *            the conyuge to set
	 */
	public void setConyuge(String conyuge) {
		this.conyuge = conyuge;
	}

	/**
	 * @return the numHijos
	 */
	public int getNumHijos() {
		return numHijos;
	}

	/**
	 * @param numHijos
	 *            the numHijos to set
	 */
	public void setNumHijos(int numHijos) {
		this.numHijos = numHijos;
	}

	/**
	 * @return the horaInicioContacto
	 */
	public String getHoraInicioContacto() {
		return horaInicioContacto;
	}

	/**
	 * @param horaInicioContacto
	 *            the horaInicioContacto to set
	 */
	public void setHoraInicioContacto(String horaInicioContacto) {
		this.horaInicioContacto = horaInicioContacto;
	}

	/**
	 * @return the horaFinContacto
	 */
	public String getHoraFinContacto() {
		return horaFinContacto;
	}

	/**
	 * @param horaFinContacto
	 *            the horaFinContacto to set
	 */
	public void setHoraFinContacto(String horaFinContacto) {
		this.horaFinContacto = horaFinContacto;
	}

	/**
	 * @return the tipoEmpleo
	 */
	public String getTipoEmpleo() {
		return tipoEmpleo;
	}

	/**
	 * @param tipoEmpleo
	 *            the tipoEmpleo to set
	 */
	public void setTipoEmpleo(String tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	/**
	 * @return the dependiente
	 */
	public String getDependiente() {
		return dependiente;
	}

	/**
	 * @param dependiente
	 *            the dependiente to set
	 */
	public void setDependiente(String dependiente) {
		this.dependiente = dependiente;
	}

	/**
	 * @return the independiente
	 */
	public String getIndependiente() {
		return independiente;
	}

	/**
	 * @param independiente
	 *            the independiente to set
	 */
	public void setIndependiente(String independiente) {
		this.independiente = independiente;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the emailAlterno
	 */
	public String getEmailAlterno() {
		return emailAlterno;
	}

	/**
	 * @param emailAlterno
	 *            the emailAlterno to set
	 */
	public void setEmailAlterno(String emailAlterno) {
		this.emailAlterno = emailAlterno;
	}

	/**
	 * @return the tipoDireccion
	 */
	public String getTipoDireccion() {
		return tipoDireccion;
	}

	/**
	 * @param tipoDireccion
	 *            the tipoDireccion to set
	 */
	public void setTipoDireccion(String tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the contacto
	 */
	public String getContacto() {
		return contacto;
	}

	/**
	 * @param contacto
	 *            the contacto to set
	 */
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	/**
	 * @return the emailContacto
	 */
	public String getEmailContacto() {
		return emailContacto;
	}

	/**
	 * @param emailContacto
	 *            the emailContacto to set
	 */
	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	/**
	 * @return the montoIngresoMensual
	 */
	public BigDecimal getMontoIngresoMensual() {
		return montoIngresoMensual;
	}

	/**
	 * @param montoIngresoMensual
	 *            the montoIngresoMensual to set
	 */
	public void setMontoIngresoMensual(BigDecimal montoIngresoMensual) {
		this.montoIngresoMensual = montoIngresoMensual;
	}

	/**
	 * @return the tipoTelefono
	 */
	public String getTipoTelefono() {
		return tipoTelefono;
	}

	/**
	 * @param tipoTelefono
	 *            the tipoTelefono to set
	 */
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	public String getRelacionLaboral() {
		return relacionLaboral;
	}

	public void setRelacionLaboral(String relacionLaboral) {
		this.relacionLaboral = relacionLaboral;
	}

	public String getEmpresaTrabaja() {
		return empresaTrabaja;
	}

	public void setEmpresaTrabaja(String empresaTrabaja) {
		this.empresaTrabaja = empresaTrabaja;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCodProv() {
		return codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodParr() {
		return codParr;
	}

	public void setCodParr(String codParr) {
		this.codParr = codParr;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getDirCalle1() {
		return dirCalle1;
	}

	public void setDirCalle1(String dirCalle1) {
		this.dirCalle1 = dirCalle1;
	}

	public String getDirNumero() {
		return dirNumero;
	}

	public void setDirNumero(String dirNumero) {
		this.dirNumero = dirNumero;
	}

	public String getDirCalle2() {
		return dirCalle2;
	}

	public void setDirCalle2(String dirCalle2) {
		this.dirCalle2 = dirCalle2;
	}

	public String getDirEdif() {
		return dirEdif;
	}

	public void setDirEdif(String dirEdif) {
		this.dirEdif = dirEdif;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCodCanton() {
		return codCanton;
	}

	public void setCodCanton(String codCanton) {
		this.codCanton = codCanton;
	}

	public String getCodTelPais() {
		return codTelPais;
	}

	public void setCodTelPais(String codTelPais) {
		this.codTelPais = codTelPais;
	}

	public String getCodArea() {
		return codArea;
	}

	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTelExt() {
		return telExt;
	}

	public void setTelExt(String telExt) {
		this.telExt = telExt;
	}

}
