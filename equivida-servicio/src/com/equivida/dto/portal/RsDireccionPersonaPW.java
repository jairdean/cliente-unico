package com.equivida.dto.portal;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsDireccionPersonaPW implements Serializable {

	private static final long serialVersionUID = -1295324918358770676L;

	private BigDecimal idDireccion;
	private Integer codTipoDireccion;
	private String callePrincipal;
	private String numero;
	private String calleSecundaria;
	private String referencia;
	private Integer idCanton;
	private String barrio;
	private Integer idProvincia;
	private Integer idPais;

	/**
	 * @return the idDireccion
	 */
	public BigDecimal getIdDireccion() {
		return idDireccion;
	}

	/**
	 * @param idDireccion
	 *            the idDireccion to set
	 */
	public void setIdDireccion(BigDecimal idDireccion) {
		this.idDireccion = idDireccion;
	}

	/**
	 * @return the codTipoDireccion
	 */
	public Integer getCodTipoDireccion() {
		return codTipoDireccion;
	}

	/**
	 * @param codTipoDireccion
	 *            the codTipoDireccion to set
	 */
	public void setCodTipoDireccion(Integer codTipoDireccion) {
		this.codTipoDireccion = codTipoDireccion;
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
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia
	 *            the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the idCanton
	 */
	public Integer getIdCanton() {
		return idCanton;
	}

	/**
	 * @param idCanton
	 *            the idCanton to set
	 */
	public void setIdCanton(Integer idCanton) {
		this.idCanton = idCanton;
	}

	/**
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}

	/**
	 * @param barrio
	 *            the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	/**
	 * @return the idProvincia
	 */
	public Integer getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia
	 *            the idProvincia to set
	 */
	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * @return the idPais
	 */
	public Integer getIdPais() {
		return idPais;
	}

	/**
	 * @param idPais
	 *            the idPais to set
	 */
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
