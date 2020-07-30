/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsCreacionUsuario;


/**
 * @author saviasoft5
 * 
 */
@Remote
public interface CreacionUsuarioPwSpRemoto {

	RsCreacionUsuario creacionUsuario(Integer secPersona, String usuario,
			String password, Integer secPerfil, Integer secFigura,
			String modificar) throws SQLException;

}