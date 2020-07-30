/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_SERVICIO_INST_FIN")
public class TipoServicioInstFin implements Serializable {

	private static final long serialVersionUID = 916313860944610458L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_TIPO_SERVICIO_INST_FIN")
	private Short secTipoServicioInstFin;

	@JoinColumn(name = "SEC_TIPO_FINANCIERA", referencedColumnName = "SEC_TIPO_FINANCIERA")
	@ManyToOne(optional = false)
	private TipoInstitucionFinanciera tipoInstitucionFinanciera;

	@JoinColumn(name = "SEC_TIPO_SERVICIO", referencedColumnName = "SEC_TIPO_SERVICIO")
	@ManyToOne(optional = false)
	private TipoServicioFinanciero tipoServicioFinanciero;

	@Column(name = "CODIGO_VISIBLE")
	private char codigoVisible;

	public TipoServicioInstFin() {
	}
	
	public TipoServicioInstFin(Short secTipoServicioInstFin) {
		super();
		this.secTipoServicioInstFin = secTipoServicioInstFin;
	}



	public Short getSecTipoServicioInstFin() {
		return secTipoServicioInstFin;
	}

	public void setSecTipoServicioInstFin(Short secTipoServicioInstFin) {
		this.secTipoServicioInstFin = secTipoServicioInstFin;
	}

	public TipoInstitucionFinanciera getTipoInstitucionFinanciera() {
		return tipoInstitucionFinanciera;
	}

	public void setTipoInstitucionFinanciera(
			TipoInstitucionFinanciera tipoInstitucionFinanciera) {
		this.tipoInstitucionFinanciera = tipoInstitucionFinanciera;
	}

	public TipoServicioFinanciero getTipoServicioFinanciero() {
		return tipoServicioFinanciero;
	}

	public void setTipoServicioFinanciero(
			TipoServicioFinanciero tipoServicioFinanciero) {
		this.tipoServicioFinanciero = tipoServicioFinanciero;
	}

	public char getCodigoVisible() {
		return codigoVisible;
	}

	public void setCodigoVisible(char codigoVisible) {
		this.codigoVisible = codigoVisible;
	}
}