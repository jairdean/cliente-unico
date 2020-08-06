/**
 * 
 */
package com.equivida.smartdata.constante;

/**
 * @author juan
 *
 */
public enum UsuarioEnum {
	USUARIO_CREACION(new String("ACTUALIZACION")), USUARIO_MODIFICACION(new String("ACTUALIZACION"));

	private String usuario;

	UsuarioEnum(String usuario) {
		this.usuario = usuario;
	}

	public String getValor() {
		return usuario;
	}
}
