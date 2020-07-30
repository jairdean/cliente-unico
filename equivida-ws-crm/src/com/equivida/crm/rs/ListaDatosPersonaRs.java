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
public class ListaDatosPersonaRs implements Serializable {

	private static final long serialVersionUID = -3549457935371005804L;

	Integer Secuencial_Persona;
	Integer Broker;
	String Contacto_Broker;
	String Canal_Venta;
	String Asesor_Comercial;
	String Asesor_Post_Venta ;
	String Director;
	String Asistente;
	String Segmento_Cliente ;
	String Informacion_Siniestros;
	BigDecimal imp_stros;
	String Nivel_Riesgo_Cliente;
	String Beneficios_Externos;
	String Cliente_Desde;
	String Cliente_individual;
	String Asegurado_de_empresa_cliente;
	String Contacto_empresa;
	String Contacto_Broker_2;
	String Inactivo;
	BigDecimal Valor_Cliente;
	
	
	String mensaje;


	/**
	 * @return the secuencial_Persona
	 */
	public Integer getSecuencial_Persona() {
		return Secuencial_Persona;
	}


	/**
	 * @param secuencial_Persona the secuencial_Persona to set
	 */
	public void setSecuencial_Persona(Integer secuencial_Persona) {
		Secuencial_Persona = secuencial_Persona;
	}


	/**
	 * @return the broker
	 */
	public Integer getBroker() {
		return Broker;
	}


	/**
	 * @param broker the broker to set
	 */
	public void setBroker(Integer broker) {
		Broker = broker;
	}


	/**
	 * @return the contacto_Broker
	 */
	public String getContacto_Broker() {
		return Contacto_Broker;
	}


	/**
	 * @param contacto_Broker the contacto_Broker to set
	 */
	public void setContacto_Broker(String contacto_Broker) {
		Contacto_Broker = contacto_Broker;
	}


	/**
	 * @return the canal_Venta
	 */
	public String getCanal_Venta() {
		return Canal_Venta;
	}


	/**
	 * @param canal_Venta the canal_Venta to set
	 */
	public void setCanal_Venta(String canal_Venta) {
		Canal_Venta = canal_Venta;
	}


	/**
	 * @return the asesor_Comercial
	 */
	public String getAsesor_Comercial() {
		return Asesor_Comercial;
	}


	/**
	 * @param asesor_Comercial the asesor_Comercial to set
	 */
	public void setAsesor_Comercial(String asesor_Comercial) {
		Asesor_Comercial = asesor_Comercial;
	}


	/**
	 * @return the asesor_Post_Venta
	 */
	public String getAsesor_Post_Venta() {
		return Asesor_Post_Venta;
	}


	/**
	 * @param asesor_Post_Venta the asesor_Post_Venta to set
	 */
	public void setAsesor_Post_Venta(String asesor_Post_Venta) {
		Asesor_Post_Venta = asesor_Post_Venta;
	}


	/**
	 * @return the director
	 */
	public String getDirector() {
		return Director;
	}


	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		Director = director;
	}


	/**
	 * @return the asistente
	 */
	public String getAsistente() {
		return Asistente;
	}


	/**
	 * @param asistente the asistente to set
	 */
	public void setAsistente(String asistente) {
		Asistente = asistente;
	}


	/**
	 * @return the segmento_Cliente
	 */
	public String getSegmento_Cliente() {
		return Segmento_Cliente;
	}


	/**
	 * @param segmento_Cliente the segmento_Cliente to set
	 */
	public void setSegmento_Cliente(String segmento_Cliente) {
		Segmento_Cliente = segmento_Cliente;
	}


	/**
	 * @return the información_Siniestros
	 */
	public String getInformacion_Siniestros() {
		return Informacion_Siniestros;
	}


	/**
	 * @param información_Siniestros the información_Siniestros to set
	 */
	public void setInformacion_Siniestros(String informacion_Siniestros) {
		Informacion_Siniestros = informacion_Siniestros;
	}


	/**
	 * @return the imp_stros
	 */
	public BigDecimal getImp_stros() {
		return imp_stros;
	}


	/**
	 * @param imp_stros the imp_stros to set
	 */
	public void setImp_stros(BigDecimal imp_stros) {
		this.imp_stros = imp_stros;
	}


	/**
	 * @return the nivel_Riesgo_Cliente
	 */
	public String getNivel_Riesgo_Cliente() {
		return Nivel_Riesgo_Cliente;
	}


	/**
	 * @param nivel_Riesgo_Cliente the nivel_Riesgo_Cliente to set
	 */
	public void setNivel_Riesgo_Cliente(String nivel_Riesgo_Cliente) {
		Nivel_Riesgo_Cliente = nivel_Riesgo_Cliente;
	}


	/**
	 * @return the beneficios_Externos
	 */
	public String getBeneficios_Externos() {
		return Beneficios_Externos;
	}


	/**
	 * @param beneficios_Externos the beneficios_Externos to set
	 */
	public void setBeneficios_Externos(String beneficios_Externos) {
		Beneficios_Externos = beneficios_Externos;
	}


	/**
	 * @return the cliente_Desde
	 */
	public String getCliente_Desde() {
		return Cliente_Desde;
	}


	/**
	 * @param cliente_Desde the cliente_Desde to set
	 */
	public void setCliente_Desde(String cliente_Desde) {
		Cliente_Desde = cliente_Desde;
	}


	/**
	 * @return the cliente_individual
	 */
	public String getCliente_individual() {
		return Cliente_individual;
	}


	/**
	 * @param cliente_individual the cliente_individual to set
	 */
	public void setCliente_individual(String cliente_individual) {
		Cliente_individual = cliente_individual;
	}


	/**
	 * @return the asegurado_de_empresa_cliente
	 */
	public String getAsegurado_de_empresa_cliente() {
		return Asegurado_de_empresa_cliente;
	}


	/**
	 * @param asegurado_de_empresa_cliente the asegurado_de_empresa_cliente to set
	 */
	public void setAsegurado_de_empresa_cliente(String asegurado_de_empresa_cliente) {
		Asegurado_de_empresa_cliente = asegurado_de_empresa_cliente;
	}


	/**
	 * @return the contacto_empresa
	 */
	public String getContacto_empresa() {
		return Contacto_empresa;
	}


	/**
	 * @param contacto_empresa the contacto_empresa to set
	 */
	public void setContacto_empresa(String contacto_empresa) {
		Contacto_empresa = contacto_empresa;
	}


	/**
	 * @return the contacto_Broker_2
	 */
	public String getContacto_Broker_2() {
		return Contacto_Broker_2;
	}


	/**
	 * @param contacto_Broker_2 the contacto_Broker_2 to set
	 */
	public void setContacto_Broker_2(String contacto_Broker_2) {
		Contacto_Broker_2 = contacto_Broker_2;
	}


	/**
	 * @return the inactivo
	 */
	public String getInactivo() {
		return Inactivo;
	}


	/**
	 * @param inactivo the inactivo to set
	 */
	public void setInactivo(String inactivo) {
		Inactivo = inactivo;
	}


	/**
	 * @return the valor_Cliente
	 */
	public BigDecimal getValor_Cliente() {
		return Valor_Cliente;
	}


	/**
	 * @param valor_Cliente the valor_Cliente to set
	 */
	public void setValor_Cliente(BigDecimal valor_Cliente) {
		Valor_Cliente = valor_Cliente;
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

	
}
