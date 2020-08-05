/**
 * 
 */
package com.equivida.smartdata.constante;

/**
 * @author juan
 *
 */
public enum UsuarioEnum {
	USUARIO_CREACION(new String("ACTUALIZACION_EN_LINEA")), USUARIO_MODIFICACION(new String("ACTUALIZACION_EN_LINEA"));

	private String usuario;

	UsuarioEnum(String usuario) {
		this.usuario = usuario;
	}

	public String getValor() {
		return usuario;
	}
}
