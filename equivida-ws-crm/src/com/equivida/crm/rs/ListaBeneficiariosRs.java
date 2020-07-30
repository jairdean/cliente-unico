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
/**
 * @author saviasoft5
 *
 */
public class ListaBeneficiariosRs implements Serializable {

	private static final long serialVersionUID = -3549457935371005804L;

	BigDecimal Numero_Poliza;
	String Tipo;
	String Primer_Nombre;
	String Segundo_Nombre;
	String Apellido_Paterno;
	String Apellido_Materno;
	String Parentezco;
	BigDecimal Porcentaje;
	String Relacion_con_contratante;
	String mensaje;
	/**
	 * @return the numero_Poliza
	 */
	public BigDecimal getNumero_Poliza() {
		return Numero_Poliza;
	}
	/**
	 * @param numero_Poliza the numero_Poliza to set
	 */
	public void setNumero_Poliza(BigDecimal numero_Poliza) {
		Numero_Poliza = numero_Poliza;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return Tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	/**
	 * @return the primer_Nombre
	 */
	public String getPrimer_Nombre() {
		return Primer_Nombre;
	}
	/**
	 * @param primer_Nombre the primer_Nombre to set
	 */
	public void setPrimer_Nombre(String primer_Nombre) {
		Primer_Nombre = primer_Nombre;
	}
	/**
	 * @return the segundo_Nombre
	 */
	public String getSegundo_Nombre() {
		return Segundo_Nombre;
	}
	/**
	 * @param segundo_Nombre the segundo_Nombre to set
	 */
	public void setSegundo_Nombre(String segundo_Nombre) {
		Segundo_Nombre = segundo_Nombre;
	}
	/**
	 * @return the apellido_Paterno
	 */
	public String getApellido_Paterno() {
		return Apellido_Paterno;
	}
	/**
	 * @param apellido_Paterno the apellido_Paterno to set
	 */
	public void setApellido_Paterno(String apellido_Paterno) {
		Apellido_Paterno = apellido_Paterno;
	}
	/**
	 * @return the apellido_Materno
	 */
	public String getApellido_Materno() {
		return Apellido_Materno;
	}
	/**
	 * @param apellido_Materno the apellido_Materno to set
	 */
	public void setApellido_Materno(String apellido_Materno) {
		Apellido_Materno = apellido_Materno;
	}
	/**
	 * @return the parentezco
	 */
	public String getParentezco() {
		return Parentezco;
	}
	/**
	 * @param parentezco the parentezco to set
	 */
	public void setParentezco(String parentezco) {
		Parentezco = parentezco;
	}
	/**
	 * @return the porcentaje
	 */
	public BigDecimal getPorcentaje() {
		return Porcentaje;
	}
	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(BigDecimal porcentaje) {
		Porcentaje = porcentaje;
	}
	/**
	 * @return the relacion_con_contratante
	 */
	public String getRelacion_con_contratante() {
		return Relacion_con_contratante;
	}
	/**
	 * @param relacion_con_contratante the relacion_con_contratante to set
	 */
	public void setRelacion_con_contratante(String relacion_con_contratante) {
		Relacion_con_contratante = relacion_con_contratante;
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
