/**
 * 
 */
package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author saviasoft5
 * 
 */
public class RsDireccionPersona implements Serializable {

	private static final long serialVersionUID = -8812308218077779984L;

	private BigDecimal idDireccion;
	private Integer codTipoDireccion;
	private Integer idCanton;
	private String tipoDireccion;
	private String canton;
	private String provincia;
	private String ciudad;
	private String callePrincipal;
	private String numero;
	private String calleSecundaria;
	private String referencia;
	private String envioCorrespondencia;

	public BigDecimal getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(BigDecimal idDireccion) {
		this.idDireccion = idDireccion;
	}

	public Integer getCodTipoDireccion() {
		return codTipoDireccion;
	}

	public void setCodTipoDireccion(Integer codTipoDireccion) {
		this.codTipoDireccion = codTipoDireccion;
	}

	public Integer getIdCanton() {
		return idCanton;
	}

	public void setIdCanton(Integer idCanton) {
		this.idCanton = idCanton;
	}

	public String getTipoDireccion() {
		return tipoDireccion;
	}

	public void setTipoDireccion(String tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCalleSecundaria() {
		return calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEnvioCorrespondencia() {
		return envioCorrespondencia;
	}

	public void setEnvioCorrespondencia(String envioCorrespondencia) {
		this.envioCorrespondencia = envioCorrespondencia;
	}

}
