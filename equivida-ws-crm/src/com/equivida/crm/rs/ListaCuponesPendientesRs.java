/**
 * 
 */
package com.equivida.crm.rs;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author saviasoft5
 * 
 */
public class ListaCuponesPendientesRs implements Serializable {

	private static final long serialVersionUID = -3549457935371005804L;

	Integer secuencial_persona;
	Integer nro_conducto;
	Integer fecha_vigencia_conducto;
	BigDecimal numero_poliza;
	String origen;
	Integer nro_cupones_pendientes;
	String Estado_ult_cupon;
	Integer dias_morosidad;
	BigDecimal monto;
	String periodo_gracia;
	String institucion_conducto;
	String mensaje;
	/**
	 * @return the secuencial_persona
	 */
	public Integer getSecuencial_persona() {
		return secuencial_persona;
	}
	/**
	 * @param secuencial_persona the secuencial_persona to set
	 */
	public void setSecuencial_persona(Integer secuencial_persona) {
		this.secuencial_persona = secuencial_persona;
	}
	/**
	 * @return the nro_conducto
	 */
	public Integer getNro_conducto() {
		return nro_conducto;
	}
	/**
	 * @param nro_conducto the nro_conducto to set
	 */
	public void setNro_conducto(Integer nro_conducto) {
		this.nro_conducto = nro_conducto;
	}
	/**
	 * @return the fecha_vigencia_conducto
	 */
	public Integer getFecha_vigencia_conducto() {
		return fecha_vigencia_conducto;
	}
	/**
	 * @param fecha_vigencia_conducto the fecha_vigencia_conducto to set
	 */
	public void setFecha_vigencia_conducto(Integer fecha_vigencia_conducto) {
		this.fecha_vigencia_conducto = fecha_vigencia_conducto;
	}
	/**
	 * @return the numero_poliza
	 */
	public BigDecimal getNumero_poliza() {
		return numero_poliza;
	}
	/**
	 * @param numero_poliza the numero_poliza to set
	 */
	public void setNumero_poliza(BigDecimal numero_poliza) {
		this.numero_poliza = numero_poliza;
	}
	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return the nro_cupones_pendientes
	 */
	public Integer getNro_cupones_pendientes() {
		return nro_cupones_pendientes;
	}
	/**
	 * @param nro_cupones_pendientes the nro_cupones_pendientes to set
	 */
	public void setNro_cupones_pendientes(Integer nro_cupones_pendientes) {
		this.nro_cupones_pendientes = nro_cupones_pendientes;
	}
	/**
	 * @return the estado_ult_cupon
	 */
	public String getEstado_ult_cupon() {
		return Estado_ult_cupon;
	}
	/**
	 * @param estado_ult_cupon the estado_ult_cupon to set
	 */
	public void setEstado_ult_cupon(String estado_ult_cupon) {
		Estado_ult_cupon = estado_ult_cupon;
	}
	/**
	 * @return the dias_morosidad
	 */
	public Integer getDias_morosidad() {
		return dias_morosidad;
	}
	/**
	 * @param dias_morosidad the dias_morosidad to set
	 */
	public void setDias_morosidad(Integer dias_morosidad) {
		this.dias_morosidad = dias_morosidad;
	}
	/**
	 * @return the monto
	 */
	public BigDecimal getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	/**
	 * @return the periodo_gracia
	 */
	public String getPeriodo_gracia() {
		return periodo_gracia;
	}
	/**
	 * @param periodo_gracia the periodo_gracia to set
	 */
	public void setPeriodo_gracia(String periodo_gracia) {
		this.periodo_gracia = periodo_gracia;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the institucion_conducto
	 */
	public String getInstitucion_conducto() {
		return institucion_conducto;
	}
	/**
	 * @param institucion_conducto the institucion_conducto to set
	 */
	public void setInstitucion_conducto(String institucion_conducto) {
		this.institucion_conducto = institucion_conducto;
	}
	

}
