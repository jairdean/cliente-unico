package com.equivida.databook.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "titular", "conyuge" })
@XmlRootElement(name = "registros")
public class RegistrosEntity {

	@XmlElement(required = true)
	protected RegistrosEntity.Titular titular;
	@XmlElement(required = true)
	protected RegistrosEntity.Conyuge conyuge;

	/**
	 * Gets the value of the titular property.
	 * 
	 * @return possible object is {@link RegistrosEntity.Titular }
	 * 
	 */
	public RegistrosEntity.Titular getTitular() {
		return titular;
	}

	/**
	 * Sets the value of the titular property.
	 * 
	 * @param value allowed object is {@link RegistrosEntity.Titular }
	 * 
	 */
	public void setTitular(RegistrosEntity.Titular value) {
		this.titular = value;
	}

	/**
	 * Gets the value of the conyuge property.
	 * 
	 * @return possible object is {@link RegistrosEntity.Conyuge }
	 * 
	 */
	public RegistrosEntity.Conyuge getConyuge() {
		return conyuge;
	}

	/**
	 * Sets the value of the conyuge property.
	 * 
	 * @param value allowed object is {@link RegistrosEntity.Conyuge }
	 * 
	 */
	public void setConyuge(RegistrosEntity.Conyuge value) {
		this.conyuge = value;
	}

	// SECCION TITULAR
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "direccion", "telefonos", "persona", "direccionElectronico", "empleos",
			"personaNatural", "informacionAdicional" })
	public static class Titular {

		@XmlElement
		protected Persona persona;
		@XmlElement
		protected Direccion direccion;
		@XmlElement
		protected Telefonos telefonos;
		@XmlElement
		protected DireccionElectronico direccionElectronico;
		@XmlElement
		protected Empleos empleos;
		@XmlElement
		protected PersonaNatural personaNatural;
		@XmlElement
		protected InformacionAdicional informacionAdicional;

		/**
		 * Gets the value of the Persona property.
		 * 
		 * @return possible object is {@link Persona }
		 * 
		 */
		public Persona getPersona() {
			return persona;
		}

		/**
		 * Sets the value of the Persona property.
		 * 
		 * @param value allowed object is {@link Persona }
		 * 
		 */
		public void setPersona(Persona value) {
			this.persona = value;
		}

		/**
		 * Gets the value of the Direccion property.
		 * 
		 * @return possible object is {@link Direccion }
		 * 
		 */
		public Direccion getDireccion() {
			return direccion;
		}

		/**
		 * Sets the value of the Direccion property.
		 * 
		 * @param value allowed object is {@link Direccion }
		 * 
		 */
		public void setDireccion(Direccion value) {
			this.direccion = value;
		}

		/**
		 * Gets the value of the Telefonos property.
		 * 
		 * @return possible object is {@link Telefonos }
		 * 
		 */
		public Telefonos getTelefonos() {
			return telefonos;
		}

		/**
		 * Sets the value of the Telefonos property.
		 * 
		 * @param value allowed object is {@link Telefonos }
		 * 
		 */
		public void setTelefonos(Telefonos value) {
			this.telefonos = value;
		}

		/**
		 * Gets the value of the DireccionElectronico property.
		 * 
		 * @return possible object is {@link DireccionElectronico }
		 * 
		 */
		public DireccionElectronico getDireccionElectronico() {
			return direccionElectronico;
		}

		/**
		 * Sets the value of the DireccionElectronico property.
		 * 
		 * @param value allowed object is {@link DireccionElectronico }
		 * 
		 */
		public void setDireccionElectronico(DireccionElectronico value) {
			this.direccionElectronico = value;
		}

		/**
		 * Gets the value of the Empleos property.
		 * 
		 * @return possible object is {@link Empleos }
		 * 
		 */
		public Empleos getEmpleos() {
			return empleos;
		}

		/**
		 * Sets the value of the Empleos property.
		 * 
		 * @param value allowed object is {@link Empleos }
		 * 
		 */
		public void setEmpleos(Empleos value) {
			this.empleos = value;
		}

		/**
		 * Gets the value of the PersonaNatural property.
		 * 
		 * @return possible object is {@link PersonaNatural }
		 * 
		 */
		public PersonaNatural getPersonaNatural() {
			return personaNatural;
		}

		/**
		 * Sets the value of the PersonaNatural property.
		 * 
		 * @param value allowed object is {@link PersonaNatural }
		 * 
		 */
		public void setPersonaNatural(PersonaNatural value) {
			this.personaNatural = value;
		}

		/**
		 * Gets the value of the InformacionAdicional property.
		 * 
		 * @return possible object is {@link InformacionAdicional }
		 * 
		 */
		public InformacionAdicional getInformacionAdicional() {
			return informacionAdicional;
		}

		/**
		 * Sets the value of the InformacionAdicional property.
		 * 
		 * @param value allowed object is {@link InformacionAdicional }
		 * 
		 */
		public void setInformacionAdicional(InformacionAdicional value) {
			this.informacionAdicional = value;
		}
	}

	// SECCION CONYUGE
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "direccion", "telefonos", "persona", "direccionElectronico", "empleos",
			"personaNatural", "informacionAdicional", "error" })
	public static class Conyuge {

		@XmlElement(required = false)
		protected String error;
		@XmlElement(required = false)
		protected Persona persona;
		@XmlElement(required = false)
		protected Direccion direccion;
		@XmlElement(required = false)
		protected Telefonos telefonos;
		@XmlElement(required = false)
		protected DireccionElectronico direccionElectronico;
		@XmlElement(required = false)
		protected Empleos empleos;
		@XmlElement(required = false)
		protected PersonaNatural personaNatural;
		@XmlElement(required = false)
		protected InformacionAdicional informacionAdicional;

		/**
		 * Gets the value of the error property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getError() {
			return error;
		}

		/**
		 * Sets the value of the error property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setError(String value) {
			this.error = value;
		}
		
		/**
		 * Gets the value of the Persona property.
		 * 
		 * @return possible object is {@link Persona }
		 * 
		 */
		public Persona getPersona() {
			return persona;
		}

		/**
		 * Sets the value of the Persona property.
		 * 
		 * @param value allowed object is {@link Persona }
		 * 
		 */
		public void setPersona(Persona value) {
			this.persona = value;
		}

		/**
		 * Gets the value of the Direccion property.
		 * 
		 * @return possible object is {@link Direccion }
		 * 
		 */
		public Direccion getDireccion() {
			return direccion;
		}

		/**
		 * Sets the value of the Direccion property.
		 * 
		 * @param value allowed object is {@link Direccion }
		 * 
		 */
		public void setDireccion(Direccion value) {
			this.direccion = value;
		}

		/**
		 * Gets the value of the Telefonos property.
		 * 
		 * @return possible object is {@link Telefonos }
		 * 
		 */
		public Telefonos getTelefonos() {
			return telefonos;
		}

		/**
		 * Sets the value of the Telefonos property.
		 * 
		 * @param value allowed object is {@link Telefonos }
		 * 
		 */
		public void setTelefonos(Telefonos value) {
			this.telefonos = value;
		}

		/**
		 * Gets the value of the DireccionElectronico property.
		 * 
		 * @return possible object is {@link DireccionElectronico }
		 * 
		 */
		public DireccionElectronico getDireccionElectronico() {
			return direccionElectronico;
		}

		/**
		 * Sets the value of the DireccionElectronico property.
		 * 
		 * @param value allowed object is {@link DireccionElectronico }
		 * 
		 */
		public void setDireccionElectronico(DireccionElectronico value) {
			this.direccionElectronico = value;
		}

		/**
		 * Gets the value of the Empleos property.
		 * 
		 * @return possible object is {@link Empleos }
		 * 
		 */
		public Empleos getEmpleos() {
			return empleos;
		}

		/**
		 * Sets the value of the Empleos property.
		 * 
		 * @param value allowed object is {@link Empleos }
		 * 
		 */
		public void setEmpleos(Empleos value) {
			this.empleos = value;
		}

		/**
		 * Gets the value of the PersonaNatural property.
		 * 
		 * @return possible object is {@link PersonaNatural }
		 * 
		 */
		public PersonaNatural getPersonaNatural() {
			return personaNatural;
		}

		/**
		 * Sets the value of the PersonaNatural property.
		 * 
		 * @param value allowed object is {@link PersonaNatural }
		 * 
		 */
		public void setPersonaNatural(PersonaNatural value) {
			this.personaNatural = value;
		}

		/**
		 * Gets the value of the InformacionAdicional property.
		 * 
		 * @return possible object is {@link InformacionAdicional }
		 * 
		 */
		public InformacionAdicional getInformacionAdicional() {
			return informacionAdicional;
		}

		/**
		 * Sets the value of the InformacionAdicional property.
		 * 
		 * @param value allowed object is {@link InformacionAdicional }
		 * 
		 */
		public void setInformacionAdicional(InformacionAdicional value) {
			this.informacionAdicional = value;
		}
	}

	// SECCION INFORMACIONADICIONAL
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "numero", "secundaria", "telefono", "secParroquia", "direccion", "principal",
			"referencia", "secProvincia", "razonSocial", "secCanton", "identificacion", "codTipoIdentificacion",
			"nombreComercial", "fechaInscripcion", "codActividadEconomica", "fechaReinicioActividades",
			"fechaSuspencionActividades", "fechaInicioActividades", "fechaCancelacionActividades", "email" })
	@XmlRootElement(name = "informacionAdicional")
	public static class InformacionAdicional {

		protected String numero;
		protected String secundaria;
		protected String telefono;
		protected String secParroquia;
		protected String direccion;
		protected String principal;
		protected String referencia;
		protected String secProvincia;
		protected String razonSocial;
		protected String secCanton;
		protected String identificacion;
		protected String codTipoIdentificacion;
		protected String nombreComercial;
		protected String fechaInscripcion;
		protected String codActividadEconomica;
		protected String fechaReinicioActividades;
		protected String fechaSuspencionActividades;
		protected String fechaInicioActividades;
		protected String fechaCancelacionActividades;
		protected String email;
		
		/**
		 * Gets the value of the email property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * Sets the value of the email property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setEmail(String value) {
			this.email = value;
		}

		/**
		 * Gets the value of the fechaCancelacionActividades property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFechaCancelacionActividades() {
			return fechaCancelacionActividades;
		}

		/**
		 * Sets the value of the fechaCancelacionActividades property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setFechaCancelacionActividades(String value) {
			this.fechaCancelacionActividades = value;
		}

		/**
		 * Gets the value of the fechaInicioActividades property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFechaInicioActividades() {
			return fechaInicioActividades;
		}

		/**
		 * Sets the value of the fechaInicioActividades property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setFechaInicioActividades(String value) {
			this.fechaInicioActividades = value;
		}

		/**
		 * Gets the value of the fechaSuspencionActividades property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFechaSuspencionActividades() {
			return fechaSuspencionActividades;
		}

		/**
		 * Sets the value of the fechaSuspencionActividades property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setFechaSuspencionActividades(String value) {
			this.fechaSuspencionActividades = value;
		}

		/**
		 * Gets the value of the fechaReinicioActividades property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFechaReinicioActividades() {
			return fechaReinicioActividades;
		}

		/**
		 * Sets the value of the fechaReinicioActividades property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setFechaReinicioActividades(String value) {
			this.fechaReinicioActividades = value;
		}

		/**
		 * Gets the value of the codActividadEconomica property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodActividadEconomica() {
			return codActividadEconomica;
		}

		/**
		 * Sets the value of the codActividadEconomica property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodActividadEconomica(String value) {
			this.codActividadEconomica = value;
		}

		/**
		 * Gets the value of the fechaInscripcion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFechaInscripcion() {
			return fechaInscripcion;
		}

		/**
		 * Sets the value of the fechaInscripcion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setFechaInscripcion(String value) {
			this.fechaInscripcion = value;
		}

		/**
		 * Gets the value of the nombreComercial property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNombreComercial() {
			return nombreComercial;
		}

		/**
		 * Sets the value of the nombreComercial property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNombreComercial(String value) {
			this.nombreComercial = value;
		}

		/**
		 * Gets the value of the codTipoIdentificacion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodTipoIdentificacion() {
			return codTipoIdentificacion;
		}

		/**
		 * Sets the value of the codTipoIdentificacion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setcodTipoIdentificacion(String value) {
			this.codTipoIdentificacion = value;
		}

		/**
		 * Gets the value of the identificacion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getIdentificacion() {
			return identificacion;
		}

		/**
		 * Sets the value of the identificacion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setIdentificacion(String value) {
			this.identificacion = value;
		}

		/**
		 * Gets the value of the secCanton property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSecCanton() {
			return secCanton;
		}

		/**
		 * Sets the value of the secCanton property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSecCanton(String value) {
			this.secCanton = value;
		}

		/**
		 * Gets the value of the razonSocial property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getRazonSocial() {
			return razonSocial;
		}

		/**
		 * Sets the value of the razonSocial property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setRazonSocial(String value) {
			this.razonSocial = value;
		}

		/**
		 * Gets the value of the secProvincia property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSecProvincia() {
			return secProvincia;
		}

		/**
		 * Sets the value of the secProvincia property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSecProvincia(String value) {
			this.secProvincia = value;
		}

		/**
		 * Gets the value of the referencia property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getReferencia() {
			return referencia;
		}

		/**
		 * Sets the value of the referencia property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setReferencia(String value) {
			this.referencia = value;
		}

		/**
		 * Gets the value of the principal property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPrincipal() {
			return principal;
		}

		/**
		 * Sets the value of the principal property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setPrincipal(String value) {
			this.principal = value;
		}

		/**
		 * Gets the value of the direccion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getDireccion() {
			return direccion;
		}

		/**
		 * Sets the value of the direccion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setDireccion(String value) {
			this.direccion = value;
		}

		/**
		 * Gets the value of the secParroquia property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSecParroquia() {
			return secParroquia;
		}

		/**
		 * Sets the value of the secParroquia property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSecParroquia(String value) {
			this.secParroquia = value;
		}

		/**
		 * Gets the value of the telefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getTelefono() {
			return telefono;
		}

		/**
		 * Sets the value of the telefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setTelefono(String value) {
			this.telefono = value;
		}

		/**
		 * Gets the value of the secundaria property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSecundaria() {
			return secundaria;
		}

		/**
		 * Sets the value of the secundaria property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSecundaria(String value) {
			this.secundaria = value;
		}

		/**
		 * Gets the value of the numero property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNumero() {
			return numero;
		}

		/**
		 * Sets the value of the numero property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNumero(String value) {
			this.numero = value;
		}
	}

	// SECCION PERSONANATURAL
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "segundoNombre", "primerNombre", "identificacion", "fechaNacimiento",
			"fechaMatrimonio", "codEstadoCivil", "apellidoMaterno", "apellidoPaterno", "fechaFallecimiento", "sexo" })
	@XmlRootElement(name = "personaNatural")
	public static class PersonaNatural {

		protected String segundoNombre;
		protected String primerNombre;
		protected String identificacion;
		protected String fechaNacimiento;
		protected String fechaMatrimonio;
		protected String codEstadoCivil;
		protected String apellidoMaterno;
		protected String apellidoPaterno;
		protected String fechaFallecimiento;
		protected String sexo;

		/**
		 * Gets the value of the sexo property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSexo() {
			return sexo;
		}

		/**
		 * Sets the value of the sexo property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSexo(String value) {
			this.sexo = value;
		}

		/**
		 * Gets the value of the fechaFallecimiento property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFechaFallecimiento() {
			return fechaFallecimiento;
		}

		/**
		 * Sets the value of the fechaFallecimiento property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setFechaFallecimiento(String value) {
			this.fechaFallecimiento = value;
		}

		/**
		 * Gets the value of the apellidoPaterno property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getApellidoPaterno() {
			return apellidoPaterno;
		}

		/**
		 * Sets the value of the apellidoPaterno property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setApellidoPaterno(String value) {
			this.apellidoPaterno = value;
		}

		/**
		 * Gets the value of the apellidoMaterno property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getApellidoMaterno() {
			return apellidoMaterno;
		}

		/**
		 * Sets the value of the apellidoMaterno property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setApellidoMaterno(String value) {
			this.apellidoMaterno = value;
		}

		/**
		 * Gets the value of the codEstadoCivil property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodEstadoCivil() {
			return codEstadoCivil;
		}

		/**
		 * Sets the value of the codEstadoCivil property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodEstadoCivil(String value) {
			this.codEstadoCivil = value;
		}

		/**
		 * Gets the value of the fechaMatrimonio property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFechaMatrimonio() {
			return fechaMatrimonio;
		}

		/**
		 * Sets the value of the fechaMatrimonio property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setFechaMatrimonio(String value) {
			this.fechaMatrimonio = value;
		}

		/**
		 * Gets the value of the fechaNacimiento property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFechaNacimiento() {
			return fechaNacimiento;
		}

		/**
		 * Sets the value of the fechaNacimiento property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setFechaNacimiento(String value) {
			this.fechaNacimiento = value;
		}

		/**
		 * Gets the value of the identificacion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getIdentificacion() {
			return identificacion;
		}

		/**
		 * Sets the value of the identificacion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setIdentificacion(String value) {
			this.identificacion = value;
		}

		/**
		 * Gets the value of the primerNombre property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPrimerNombre() {
			return primerNombre;
		}

		/**
		 * Sets the value of the primerNombre property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setPrimerNombre(String value) {
			this.primerNombre = value;
		}

		/**
		 * Gets the value of the segundoNombre property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSegundoNombre() {
			return segundoNombre;
		}

		/**
		 * Sets the value of the segundoNombre property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSegundoNombre(String value) {
			this.segundoNombre = value;
		}
	}
		
	// SECCION EMPLEOS
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "empleo1", "empleo2", "empleoActual" })
	@XmlRootElement(name = "empleos")
	public static class Empleos {

		@XmlElement
		protected Trabajo empleo1;
		@XmlElement
		protected Trabajo empleo2;
		@XmlElement
		protected Trabajo empleoActual;		

		/**
		 * Gets the value of the Empleo1 property.
		 * 
		 * @return possible object is {@link Trabajo }
		 * 
		 */
		public Trabajo getEmpleo1() {
			return empleo1;
		}

		/**
		 * Sets the value of the Empleo1 property.
		 * 
		 * @param value allowed object is {@link Trabajo }
		 * 
		 */
		public void setEmpleo1(Trabajo value) {
			this.empleo1 = value;
		}
		
		/**
		 * Gets the value of the Empleo2 property.
		 * 
		 * @return possible object is {@link Trabajo }
		 * 
		 */
		public Trabajo getEmpleo2() {
			return empleo2;
		}

		/**
		 * Sets the value of the Empleo1 property.
		 * 
		 * @param value allowed object is {@link Trabajo }
		 * 
		 */
		public void setEmpleo2(Trabajo value) {
			this.empleo2 = value;
		}
		
		/**
		 * Gets the value of the EmpleoActual property.
		 * 
		 * @return possible object is {@link Trabajo }
		 * 
		 */
		public Trabajo getEmpleoActual() {
			return empleoActual;
		}

		/**
		 * Sets the value of the EmpleoActual property.
		 * 
		 * @param value allowed object is {@link Trabajo }
		 * 
		 */
		public void setEmpleoActual(Trabajo value) {
			this.empleoActual = value;
		}
	}

	// SECCION EMPLEOS
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "direccion", "fechaIngreso", "codPais", "razon_Social", "nro_Telefono",
				"sec_Canton", "sec_Provincia", "sec_Parroquia", "mntSalario", "codArea", "descripcion", "secProfesion",
				"identificacion", "actividad_Economica", "cargo" })
		@XmlRootElement(name = "empleos")
		public static class Trabajo {

			protected String direccion;
			protected String fechaIngreso;
			protected String codPais;
			protected String razon_Social;
			protected String nro_Telefono;
			protected String sec_Canton;
			protected String sec_Provincia;
			protected String sec_Parroquia;
			protected String mntSalario;
			protected String codArea;
			protected String descripcion;
			protected String secProfesion;
			protected String identificacion;
			protected String actividad_Economica;
			protected String cargo;

			/**
			 * Gets the value of the cargo property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getCargo() {
				return cargo;
			}

			/**
			 * Sets the value of the cargo property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setCargo(String value) {
				this.cargo = value;
			}

			/**
			 * Gets the value of the actividad_Economica property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getActividad_Economica() {
				return actividad_Economica;
			}

			/**
			 * Sets the value of the actividad_Economica property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setActividad_Economica(String value) {
				this.actividad_Economica = value;
			}

			/**
			 * Gets the value of the identificacion property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getIdentificacion() {
				return identificacion;
			}

			/**
			 * Sets the value of the identificacion property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setIdentificacion(String value) {
				this.identificacion = value;
			}

			/**
			 * Gets the value of the secProfesion property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSecProfesion() {
				return secProfesion;
			}

			/**
			 * Sets the value of the secProfesion property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setSecProfesion(String value) {
				this.secProfesion = value;
			}

			/**
			 * Gets the value of the descripcion property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getDescripcion() {
				return descripcion;
			}

			/**
			 * Sets the value of the descripcion property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setDescripcion(String value) {
				this.descripcion = value;
			}

			/**
			 * Gets the value of the codArea property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getCodArea() {
				return codArea;
			}

			/**
			 * Sets the value of the codArea property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setCodArea(String value) {
				this.codArea = value;
			}

			/**
			 * Gets the value of the mntSalario property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getMntSalario() {
				return mntSalario;
			}

			/**
			 * Sets the value of the mntSalario property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setMntSalario(String value) {
				this.mntSalario = value;
			}

			/**
			 * Gets the value of the sec_Parroquia property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSec_Parroquia() {
				return sec_Parroquia;
			}

			/**
			 * Sets the value of the sec_Parroquia property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setSec_Parroquia(String value) {
				this.sec_Parroquia = value;
			}

			/**
			 * Gets the value of the sec_Provincia property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSec_Provincia() {
				return sec_Provincia;
			}

			/**
			 * Sets the value of the sec_Provincia property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setSec_Provincia(String value) {
				this.sec_Provincia = value;
			}

			/**
			 * Gets the value of the sec_Canton property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSec_Canton() {
				return sec_Canton;
			}

			/**
			 * Sets the value of the sec_Canton property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setSec_Canton(String value) {
				this.sec_Canton = value;
			}

			/**
			 * Gets the value of the nro_Telefono property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getNro_Telefono() {
				return nro_Telefono;
			}

			/**
			 * Sets the value of the nro_Telefono property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setNro_Telefono(String value) {
				this.nro_Telefono = value;
			}

			/**
			 * Gets the value of the razon_Social property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getRazon_Social() {
				return razon_Social;
			}

			/**
			 * Sets the value of the razon_Social property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setRazon_Social(String value) {
				this.razon_Social = value;
			}

			/**
			 * Gets the value of the codPais property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getCodPais() {
				return codPais;
			}

			/**
			 * Sets the value of the codPais property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setCodPais(String value) {
				this.codPais = value;
			}

			/**
			 * Gets the value of the fechaIngreso property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getFechaIngreso() {
				return fechaIngreso;
			}

			/**
			 * Sets the value of the fechaIngreso property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setFechaIngreso(String value) {
				this.fechaIngreso = value;
			}

			/**
			 * Gets the value of the direccion property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getDireccion() {
				return direccion;
			}

			/**
			 * Sets the value of the direccion property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setDireccion(String value) {
				this.direccion = value;
			}
		}
		
	// SECCION DIRECCIONELECTRONICO
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "correo_electronico1", "correo_electronico2" })
	@XmlRootElement(name = "direccionElectronico")
	public static class DireccionElectronico {

		protected String correo_electronico1;
		protected String correo_electronico2;

		/**
		 * Gets the value of the correo_electronico1 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCorreo_electronico1() {
			return correo_electronico1;
		}

		/**
		 * Sets the value of the correo_electronico1 property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCorreo_electronico1(String value) {
			this.correo_electronico1 = value;
		}

		/**
		 * Gets the value of the correo_electronico2 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCorreo_electronico2() {
			return correo_electronico2;
		}

		/**
		 * Sets the value of the correo_electronico2 property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCorreo_electronico2(String value) {
			this.correo_electronico2 = value;
		}
	}

	// SECCION TELEFONOS
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "telefono1", "telefono2", "telefono3", "telefono4", "telefono5", "telefono6" })
	@XmlRootElement(name = "telefonos")
	public static class Telefonos {

		@XmlElement
		protected Telefono1 telefono1;
		@XmlElement
		protected Telefono2 telefono2;
		@XmlElement
		protected Telefono3 telefono3;
		@XmlElement
		protected Telefono4 telefono4;
		@XmlElement
		protected Telefono5 telefono5;
		@XmlElement
		protected Telefono6 telefono6;

		/**
		 * Gets the value of the Telefono6 property.
		 * 
		 * @return possible object is {@link Telefono6 }
		 * 
		 */
		public Telefono6 getTelefono6() {
			return telefono6;
		}

		/**
		 * Sets the value of the Telefono6 property.
		 * 
		 * @param value allowed object is {@link Telefono6 }
		 * 
		 */
		public void setTelefono6(Telefono6 value) {
			this.telefono6 = value;
		}

		/**
		 * Gets the value of the Telefono5 property.
		 * 
		 * @return possible object is {@link Telefono5 }
		 * 
		 */
		public Telefono5 getTelefono5() {
			return telefono5;
		}

		/**
		 * Sets the value of the Telefono5 property.
		 * 
		 * @param value allowed object is {@link Telefono5 }
		 * 
		 */
		public void setTelefono5(Telefono5 value) {
			this.telefono5 = value;
		}

		/**
		 * Gets the value of the Telefono4 property.
		 * 
		 * @return possible object is {@link Telefono4 }
		 * 
		 */
		public Telefono4 getTelefono4() {
			return telefono4;
		}

		/**
		 * Sets the value of the Telefono4 property.
		 * 
		 * @param value allowed object is {@link Telefono4 }
		 * 
		 */
		public void setTelefono4(Telefono4 value) {
			this.telefono4 = value;
		}

		/**
		 * Gets the value of the Telefono3 property.
		 * 
		 * @return possible object is {@link Telefono3 }
		 * 
		 */
		public Telefono3 getTelefono3() {
			return telefono3;
		}

		/**
		 * Sets the value of the Telefono3 property.
		 * 
		 * @param value allowed object is {@link Telefono3 }
		 * 
		 */
		public void setTelefono3(Telefono3 value) {
			this.telefono3 = value;
		}

		/**
		 * Gets the value of the Telefono2 property.
		 * 
		 * @return possible object is {@link Telefono2 }
		 * 
		 */
		public Telefono2 getTelefono2() {
			return telefono2;
		}

		/**
		 * Sets the value of the Telefono2 property.
		 * 
		 * @param value allowed object is {@link Telefono2 }
		 * 
		 */
		public void setTelefono2(Telefono2 value) {
			this.telefono2 = value;
		}

		/**
		 * Gets the value of the Telefono1 property.
		 * 
		 * @return possible object is {@link Telefono1 }
		 * 
		 */
		public Telefono1 getTelefono1() {
			return telefono1;
		}

		/**
		 * Sets the value of the Telefono1 property.
		 * 
		 * @param value allowed object is {@link Telefono1 }
		 * 
		 */
		public void setTelefono1(Telefono1 value) {
			this.telefono1 = value;
		}
	}

	// SECCION DIRECCION
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "numero", "secParroquia", "direccion", "referencia", "secProvincia", "secCanton",
			"tipoDireccion", "callePrincipal", "calleSecundaria", "email" })
	@XmlRootElement(name = "direccion")
	public static class Direccion {

		protected String numero;
		protected String secParroquia;
		protected String direccion;
		protected String referencia;
		protected String secProvincia;
		protected String secCanton;
		protected String tipoDireccion;
		protected String callePrincipal;
		protected String calleSecundaria;
		protected String email;

		/**
		 * Gets the value of the email property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * Sets the value of the email property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setEmail(String value) {
			this.email = value;
		}

		/**
		 * Gets the value of the calleSecundaria property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCalleSecundaria() {
			return calleSecundaria;
		}

		/**
		 * Sets the value of the calleSecundaria property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCalleSecundaria(String value) {
			this.calleSecundaria = value;
		}

		/**
		 * Gets the value of the callePrincipal property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCallePrincipal() {
			return callePrincipal;
		}

		/**
		 * Sets the value of the callePrincipal property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCallePrincipal(String value) {
			this.callePrincipal = value;
		}

		/**
		 * Gets the value of the tipoDireccion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getTipoDireccion() {
			return tipoDireccion;
		}

		/**
		 * Sets the value of the tipoDireccion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setTipoDireccion(String value) {
			this.tipoDireccion = value;
		}

		/**
		 * Gets the value of the secCanton property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSecCanton() {
			return secCanton;
		}

		/**
		 * Sets the value of the secCanton property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSecCanton(String value) {
			this.secCanton = value;
		}

		/**
		 * Gets the value of the secProvincia property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSecProvincia() {
			return secProvincia;
		}

		/**
		 * Sets the value of the secProvincia property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSecProvincia(String value) {
			this.secProvincia = value;
		}

		/**
		 * Gets the value of the referencia property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getReferencia() {
			return referencia;
		}

		/**
		 * Sets the value of the referencia property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setReferencia(String value) {
			this.referencia = value;
		}

		/**
		 * Gets the value of the direccion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getDireccion() {
			return direccion;
		}

		/**
		 * Sets the value of the direccion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setDireccion(String value) {
			this.direccion = value;
		}

		/**
		 * Gets the value of the secParroquia property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSecParroquia() {
			return secParroquia;
		}

		/**
		 * Sets the value of the secParroquia property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSecParroquia(String value) {
			this.secParroquia = value;
		}

		/**
		 * Gets the value of the numero property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNumero() {
			return numero;
		}

		/**
		 * Sets the value of the numero property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNumero(String value) {
			this.numero = value;
		}
	}

	// SECCION PERSONA
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "identificacion", "codTipoIdentificacion", "denominacion" })
	@XmlRootElement(name = "persona")
	public static class Persona {

		protected String identificacion;
		protected String codTipoIdentificacion;
		protected String denominacion;

		/**
		 * Gets the value of the identificacion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getIdentificacion() {
			return identificacion;
		}

		/**
		 * Sets the value of the identificacion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setIdentificacion(String value) {
			this.identificacion = value;
		}

		/**
		 * Gets the value of the codTipoIdentificacion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodTipoIdentificacion() {
			return codTipoIdentificacion;
		}

		/**
		 * Sets the value of the codTipoIdentificacion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodTipoIdentificacion(String value) {
			this.codTipoIdentificacion = value;
		}

		/**
		 * Gets the value of the denominacion property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getDenominacion() {
			return denominacion;
		}

		/**
		 * Sets the value of the denominacion property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setDenominacion(String value) {
			this.denominacion = value;
		}
	}

	// SECCION TELEFONO1
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "nroTelefono", "codArea", "codTipoTelefono" })
	@XmlRootElement(name = "telefono1")
	public static class Telefono1 {

		protected String nroTelefono;
		protected String codArea;
		protected String codTipoTelefono;

		/**
		 * Gets the value of the nroTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNroTelefono() {
			return nroTelefono;
		}

		/**
		 * Sets the value of the nroTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNroTelefono(String value) {
			this.nroTelefono = value;
		}

		/**
		 * Gets the value of the codArea property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodArea() {
			return codArea;
		}

		/**
		 * Sets the value of the codArea property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodArea(String value) {
			this.codArea = value;
		}

		/**
		 * Gets the value of the codTipoTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodTipoTelefono() {
			return codTipoTelefono;
		}

		/**
		 * Sets the value of the codTipoTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodTipoTelefono(String value) {
			this.codTipoTelefono = value;
		}
	}

	// SECCION TELEFONO2
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "nroTelefono", "codArea", "codTipoTelefono" })
	@XmlRootElement(name = "telefono2")
	public static class Telefono2 {

		protected String nroTelefono;
		protected String codArea;
		protected String codTipoTelefono;

		/**
		 * Gets the value of the nroTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNroTelefono() {
			return nroTelefono;
		}

		/**
		 * Sets the value of the nroTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNroTelefono(String value) {
			this.nroTelefono = value;
		}

		/**
		 * Gets the value of the codArea property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodArea() {
			return codArea;
		}

		/**
		 * Sets the value of the codArea property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodArea(String value) {
			this.codArea = value;
		}

		/**
		 * Gets the value of the codTipoTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodTipoTelefono() {
			return codTipoTelefono;
		}

		/**
		 * Sets the value of the codTipoTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodTipoTelefono(String value) {
			this.codTipoTelefono = value;
		}
	}

	// SECCION TELEFONO3
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "nroTelefono", "codArea", "codTipoTelefono" })
	@XmlRootElement(name = "telefono3")
	public static class Telefono3 {

		protected String nroTelefono;
		protected String codArea;
		protected String codTipoTelefono;

		/**
		 * Gets the value of the nroTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNroTelefono() {
			return nroTelefono;
		}

		/**
		 * Sets the value of the nroTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNroTelefono(String value) {
			this.nroTelefono = value;
		}

		/**
		 * Gets the value of the codArea property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodArea() {
			return codArea;
		}

		/**
		 * Sets the value of the codArea property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodArea(String value) {
			this.codArea = value;
		}

		/**
		 * Gets the value of the codTipoTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodTipoTelefono() {
			return codTipoTelefono;
		}

		/**
		 * Sets the value of the codTipoTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodTipoTelefono(String value) {
			this.codTipoTelefono = value;
		}
	}

	// SECCION TELEFONO4
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "nroTelefono", "codArea", "codTipoTelefono" })
	@XmlRootElement(name = "telefono4")
	public static class Telefono4 {

		protected String nroTelefono;
		protected String codArea;
		protected String codTipoTelefono;

		/**
		 * Gets the value of the nroTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNroTelefono() {
			return nroTelefono;
		}

		/**
		 * Sets the value of the nroTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNroTelefono(String value) {
			this.nroTelefono = value;
		}

		/**
		 * Gets the value of the codArea property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodArea() {
			return codArea;
		}

		/**
		 * Sets the value of the codArea property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodArea(String value) {
			this.codArea = value;
		}

		/**
		 * Gets the value of the codTipoTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodTipoTelefono() {
			return codTipoTelefono;
		}

		/**
		 * Sets the value of the codTipoTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodTipoTelefono(String value) {
			this.codTipoTelefono = value;
		}
	}

	// SECCION TELEFONO5
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "nroTelefono", "codArea", "codTipoTelefono" })
	@XmlRootElement(name = "telefono5")
	public static class Telefono5 {

		protected String nroTelefono;
		protected String codArea;
		protected String codTipoTelefono;

		/**
		 * Gets the value of the nroTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNroTelefono() {
			return nroTelefono;
		}

		/**
		 * Sets the value of the nroTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNroTelefono(String value) {
			this.nroTelefono = value;
		}

		/**
		 * Gets the value of the codArea property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodArea() {
			return codArea;
		}

		/**
		 * Sets the value of the codArea property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodArea(String value) {
			this.codArea = value;
		}

		/**
		 * Gets the value of the codTipoTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodTipoTelefono() {
			return codTipoTelefono;
		}

		/**
		 * Sets the value of the codTipoTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodTipoTelefono(String value) {
			this.codTipoTelefono = value;
		}
	}

	// SECCION TELEFONO6
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "nroTelefono", "codArea", "codTipoTelefono" })
	@XmlRootElement(name = "telefono6")
	public static class Telefono6 {

		protected String nroTelefono;
		protected String codArea;
		protected String codTipoTelefono;

		/**
		 * Gets the value of the nroTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNroTelefono() {
			return nroTelefono;
		}

		/**
		 * Sets the value of the nroTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNroTelefono(String value) {
			this.nroTelefono = value;
		}

		/**
		 * Gets the value of the codArea property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodArea() {
			return codArea;
		}

		/**
		 * Sets the value of the codArea property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodArea(String value) {
			this.codArea = value;
		}

		/**
		 * Gets the value of the codTipoTelefono property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCodTipoTelefono() {
			return codTipoTelefono;
		}

		/**
		 * Sets the value of the codTipoTelefono property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCodTipoTelefono(String value) {
			this.codTipoTelefono = value;
		}
	}
}
