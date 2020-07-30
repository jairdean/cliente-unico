/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.dto.portal.RsCreacionUsuario;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface CreacionUsuarioPwSp {

	RsCreacionUsuario creacionUsuario(Integer secPersona, String usuario,
			String password, Integer secPerfil, Integer secFigura,
			String modificar) throws SQLException;

}