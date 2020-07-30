package com.equivida.buenviaje.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.equivida.buenviaje.constante.GeneroEnum;
import com.equivida.buenviaje.constante.TipoDocumentoEnum;
import com.equivida.buenviaje.constante.TipoPersona;
import com.equivida.buenviaje.constante.TipoPersonaConsultaEnum;
import com.equivida.buenviaje.util.DateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personaConsulta", propOrder = { "tipoDocumento",
		"noDocumento", "primerApellido", "primerNombre", "genero",
		"fechaNacimiento", "tipoPersonaConsulta", "codigoPais",
		"tipoDePersona", "razonSocial", "callePrincipal", "numeroDireccion",
		"calleSecundaria", "codigoArea", "telefono", "correoElectronico",
		"ciudad", "provincia", "pais" })
public class PersonaConsulta implements Serializable {
	private static final long serialVersionUID = -2647880796805626516L;

	@XmlElement(name = "tipoDocumento", required = true)
	private TipoDocumentoEnum tipoDocumento;
	@XmlElement(name = "noDocumento", required = true)
	private String noDocumento;
	@XmlElement(name = "primerApellido", required = true)
	private String primerApellido;
	@XmlElement(name = "primerNombre", required = true)
	private String primerNombre;
	@XmlElement(name = "genero", required = true)
	private GeneroEnum genero;
	@XmlElement(name = "fechaNacimiento", required = true)
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date fechaNacimiento;
	@XmlElement(name = "tipoPersonaConsulta", required = true)
	private TipoPersonaConsultaEnum tipoPersonaConsulta;

	// Es requerido solo para Solicitante y para Facturacion
	@XmlElement(name = "codigoPais")
	private String codigoPais;

	// Datos para persona de facturacion
	@XmlElement(name = "tipoDePersona")
	private TipoPersona tipoDePersona;
	@XmlElement(name = "razonSocial")
	private String razonSocial;
	@XmlElement(name = "callePrincipal")
	private String callePrincipal;
	@XmlElement(name = "numeroDireccion")
	private String numeroDireccion;
	@XmlElement(name = "calleSecundaria")
	private String calleSecundaria;
	@XmlElement(name = "codigoArea")
	private String codigoArea;;
	@XmlElement(name = "telefono")
	private String telefono;
	@XmlElement(name = "correoElectronico")
	private String correoElectronico;
	@XmlElement(name = "ciudad")
	private String ciudad;
	@XmlElement(name = "provincia")
	private String provincia;
	@XmlElement(name = "pais")
	private String pais;

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the noDocumento
	 */
	public String getNoDocumento() {
		return noDocumento;
	}

	/**
	 * @param noDocumento
	 *            the noDocumento to set
	 */
	public void setNoDocumento(String noDocumento) {
		this.noDocumento = noDocumento;
	}

	/**
	 * @return the primerApellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * @param primerApellido
	 *            the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
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
	 * @return the genero
	 */
	public GeneroEnum getGenero() {
		return genero;
	}

	/**
	 * @param genero
	 *            the genero to set
	 */
	public void setGenero(GeneroEnum genero) {
		this.genero = genero;
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
	 * @return the tipoPersonaConsulta
	 */
	public TipoPersonaConsultaEnum getTipoPersonaConsulta() {
		return tipoPersonaConsulta;
	}

	/**
	 * @param tipoPersonaConsulta
	 *            the tipoPersonaConsulta to set
	 */
	public void setTipoPersonaConsulta(
			TipoPersonaConsultaEnum tipoPersonaConsulta) {
		this.tipoPersonaConsulta = tipoPersonaConsulta;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the tipoDePersona
	 */
	public TipoPersona getTipoDePersona() {
		return tipoDePersona;
	}

	/**
	 * @param tipoDePersona
	 *            the tipoDePersona to set
	 */
	public void setTipoDePersona(TipoPersona tipoDePersona) {
		this.tipoDePersona = tipoDePersona;
	}

	/**
	 * @return the callePrincipal
	 */
	public String getCallePrincipal() {
		return callePrincipal;
	}

	/**
	 * @param callePrincipal
	 *            the callePrincipal to set
	 */
	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	/**
	 * @return the numeroDireccion
	 */
	public String getNumeroDireccion() {
		return numeroDireccion;
	}

	/**
	 * @param numeroDireccion
	 *            the numeroDireccion to set
	 */
	public void setNumeroDireccion(String numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}

	/**
	 * @return the calleSecundaria
	 */
	public String getCalleSecundaria() {
		return calleSecundaria;
	}

	/**
	 * @param calleSecundaria
	 *            the calleSecundaria to set
	 */
	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
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
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico
	 *            the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the codigoArea
	 */
	public String getCodigoArea() {
		return codigoArea;
	}

	/**
	 * @param codigoArea
	 *            the codigoArea to set
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad
	 *            the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia
	 *            the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial
	 *            the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Override
	public String toString() {
		return "PersonaConsulta ["
				+ (tipoDocumento != null ? "tipoDocumento=" + tipoDocumento
						+ ", " : "")
				+ (noDocumento != null ? "noDocumento=" + noDocumento + ", "
						: "")
				+ (primerApellido != null ? "primerApellido=" + primerApellido
						+ ", " : "")
				+ (primerNombre != null ? "primerNombre=" + primerNombre + ", "
						: "")
				+ (genero != null ? "genero=" + genero + ", " : "")
				+ (fechaNacimiento != null ? "fechaNacimiento="
						+ fechaNacimiento + ", " : "")
				+ (tipoPersonaConsulta != null ? "tipoPersonaConsulta="
						+ tipoPersonaConsulta + ", " : "")
				+ (codigoPais != null ? "codigoPais=" + codigoPais + ", " : "")
				+ (tipoDePersona != null ? "tipoDePersona=" + tipoDePersona
						+ ", " : "")
				+ (razonSocial != null ? "razonSocial=" + razonSocial + ", "
						: "")
				+ (callePrincipal != null ? "callePrincipal=" + callePrincipal
						+ ", " : "")
				+ (numeroDireccion != null ? "numeroDireccion="
						+ numeroDireccion + ", " : "")
				+ (calleSecundaria != null ? "calleSecundaria="
						+ calleSecundaria + ", " : "")
				+ (codigoArea != null ? "codigoArea=" + codigoArea + ", " : "")
				+ (telefono != null ? "telefono=" + telefono + ", " : "")
				+ (correoElectronico != null ? "correoElectronico="
						+ correoElectronico + ", " : "")
				+ (ciudad != null ? "ciudad=" + ciudad + ", " : "")
				+ (provincia != null ? "provincia=" + provincia + ", " : "")
				+ (pais != null ? "pais=" + pais : "") + "]";
	}
}
