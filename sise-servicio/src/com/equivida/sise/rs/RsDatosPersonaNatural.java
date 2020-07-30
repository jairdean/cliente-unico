/**
 * 
 */
package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author saviasoft5
 * 
 */
public class RsDatosPersonaNatural implements Serializable {

	private static final long serialVersionUID = -8812308218077779984L;

	private BigDecimal idPersona;
	private BigDecimal idPersonaNatural;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String primerNombre;
	private String segundoNombre;
	private Date fechaNacimiento;

	public BigDecimal getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(BigDecimal idPersona) {
		this.idPersona = idPersona;
	}

	public BigDecimal getIdPersonaNatural() {
		return idPersonaNatural;
	}

	public void setIdPersonaNatural(BigDecimal idPersonaNatural) {
		this.idPersonaNatural = idPersonaNatural;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
