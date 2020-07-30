/**
 * 
 */
package com.equivida.smartdata.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.model.TelefonoSd;

/**
 * @author juan
 *
 */
public class DatosActualizaSdDto implements Serializable {
	private static final long serialVersionUID = -3996480650141660967L;

	private Integer secPersonaActualiza;
	private String identificacion;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String primerNombre;
	private String segundoNombre;
	private char sexo;
	private Date fchNacimiento;
	private Date fchFallecimiento;
	private String usrProcesa;
	private Short codEstadoCivil;
	private String identificacionConyuge;
	private Short secCanal;
	private List<TelefonoSd> telefonoList;
	private List<DireccionSd> direccionList;

	public String getNombresApellidos() {
		StringBuffer resp = new StringBuffer(200);

		if (this.primerNombre != null && this.primerNombre.trim().length() > 0) {
			resp.append(this.primerNombre.trim()).append(" ");
		}

		if (this.segundoNombre != null
				&& this.segundoNombre.trim().length() > 0) {
			resp.append(this.segundoNombre.trim()).append(" ");
		}

		if (this.apellidoPaterno != null
				&& this.apellidoPaterno.trim().length() > 0) {
			resp.append(this.apellidoPaterno.trim()).append(" ");
		}

		if (this.apellidoMaterno != null
				&& this.apellidoMaterno.trim().length() > 0) {
			resp.append(this.apellidoMaterno.trim()).append(" ");
		}

		return resp.toString();
	}

	/**
	 * @return the secPersonaActualiza
	 */
	public Integer getSecPersonaActualiza() {
		return secPersonaActualiza;
	}

	/**
	 * @param secPersonaActualiza
	 *            the secPersonaActualiza to set
	 */
	public void setSecPersonaActualiza(Integer secPersonaActualiza) {
		this.secPersonaActualiza = secPersonaActualiza;
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
	 * @return the sexo
	 */
	public char getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the fchNacimiento
	 */
	public Date getFchNacimiento() {
		return fchNacimiento;
	}

	/**
	 * @param fchNacimiento
	 *            the fchNacimiento to set
	 */
	public void setFchNacimiento(Date fchNacimiento) {
		this.fchNacimiento = fchNacimiento;
	}

	/**
	 * @return the fchFallecimiento
	 */
	public Date getFchFallecimiento() {
		return fchFallecimiento;
	}

	/**
	 * @param fchFallecimiento
	 *            the fchFallecimiento to set
	 */
	public void setFchFallecimiento(Date fchFallecimiento) {
		this.fchFallecimiento = fchFallecimiento;
	}

	/**
	 * @return the usrProcesa
	 */
	public String getUsrProcesa() {
		return usrProcesa;
	}

	/**
	 * @param usrProcesa
	 *            the usrProcesa to set
	 */
	public void setUsrProcesa(String usrProcesa) {
		this.usrProcesa = usrProcesa;
	}

	/**
	 * @return the codEstadoCivil
	 */
	public Short getCodEstadoCivil() {
		return codEstadoCivil;
	}

	/**
	 * @param codEstadoCivil
	 *            the codEstadoCivil to set
	 */
	public void setCodEstadoCivil(Short codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}

	/**
	 * @return the identificacionConyuge
	 */
	public String getIdentificacionConyuge() {
		return identificacionConyuge;
	}

	/**
	 * @param identificacionConyuge
	 *            the identificacionConyuge to set
	 */
	public void setIdentificacionConyuge(String identificacionConyuge) {
		this.identificacionConyuge = identificacionConyuge;
	}

	/**
	 * @return the telefonoList
	 */
	public List<TelefonoSd> getTelefonoList() {
		return telefonoList;
	}

	/**
	 * @param telefonoList
	 *            the telefonoList to set
	 */
	public void setTelefonoList(List<TelefonoSd> telefonoList) {
		this.telefonoList = telefonoList;
	}

	/**
	 * @return the direccionList
	 */
	public List<DireccionSd> getDireccionList() {
		return direccionList;
	}

	/**
	 * @param direccionList
	 *            the direccionList to set
	 */
	public void setDireccionList(List<DireccionSd> direccionList) {
		this.direccionList = direccionList;
	}

	/**
	 * @return the secCanal
	 */
	public Short getSecCanal() {
		return secCanal;
	}

	/**
	 * @param secCanal
	 *            the secCanal to set
	 */
	public void setSecCanal(Short secCanal) {
		this.secCanal = secCanal;
	}

}
