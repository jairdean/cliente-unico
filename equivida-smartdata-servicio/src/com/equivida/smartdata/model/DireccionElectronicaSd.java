/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equivida.smartdata.model;

import java.util.Date;

public class DireccionElectronicaSd {
	private Integer secDireccionElectronica;
	private Integer secPersona;
	private String direccionElectronica;
	private Short codTipoDireccionElectronica;
	private Short secCanal;
	private char estado;
	private String usrCreacion;
	private Date tsCreacion;
	private String usrModificacion;
	
	public Integer getSecDireccionElectronica() {
		return secDireccionElectronica;
	}
	public void setSecDireccionElectronica(Integer secDireccionElectronica) {
		this.secDireccionElectronica = secDireccionElectronica;
	}
	public Integer getSecPersona() {
		return secPersona;
	}
	public void setSecPersona(Integer secPersona) {
		this.secPersona = secPersona;
	}
	public String getDireccionElectronica() {
		return direccionElectronica;
	}
	public void setDireccionElectronica(String direccionElectronica) {
		this.direccionElectronica = direccionElectronica;
	}
	public Short getCodTipoDireccionElectronica() {
		return codTipoDireccionElectronica;
	}
	public void setCodTipoDireccionElectronica(Short codTipoDireccionElectronica) {
		this.codTipoDireccionElectronica = codTipoDireccionElectronica;
	}
	public Short getSecCanal() {
		return secCanal;
	}
	public void setSecCanal(Short secCanal) {
		this.secCanal = secCanal;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
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
}
