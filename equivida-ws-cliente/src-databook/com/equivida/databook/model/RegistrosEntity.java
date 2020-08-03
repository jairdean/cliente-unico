package com.equivida.databook.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "titular" })
@XmlRootElement(name = "registros")
public class RegistrosEntity {

	@XmlElement(required = true)
	protected RegistrosEntity.Titular titular;
	// protected RegistrosEntity.Conyugue conyugue;

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

	// SECCION TITULAR
	@XmlAccessorType(XmlAccessType.FIELD)
	// @XmlType(name = "", propOrder = { "direccion", "telefonos", "persona",
	// "direccionElectronico",
	// "personaNatural", "informacionAdicional", "empleoDependiente", })
	@XmlType(name = "", propOrder = { "direccion", "telefonos", "persona", "direccionElectronico",
			"empleoDependiente" })
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
		protected EmpleoDependiente empleoDependiente;

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
		 * Gets the value of the EmpleoDependiente property.
		 * 
		 * @return possible object is {@link EmpleoDependiente }
		 * 
		 */
		public EmpleoDependiente getEmpleoDependiente() {
			return empleoDependiente;
		}

		/**
		 * Sets the value of the EmpleoDependiente property.
		 * 
		 * @param value allowed object is {@link EmpleoDependiente }
		 * 
		 */
		public void setEmpleoDependiente(EmpleoDependiente value) {
			this.empleoDependiente = value;
		}
	}

	// SECCION EMPLEODEPENDIENTE
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "direccion", "fechaIngreso", "codPais", "razon_Social", "nro_Telefono",
			"sec_Canton", "sec_Provincia", "sec_Parroquia", "mntSalario", "codArea", "descripcion", "secProfesion",
			"identificacion", "actividad_Economica", "cargo" })
	@XmlRootElement(name = "empleoDependiente")
	public static class EmpleoDependiente {

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
