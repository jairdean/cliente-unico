/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsPreguntaUsuario;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface PreguntaUsuarioPwSpRemoto {

	RsPreguntaUsuario preguntaUsuario(Integer secPersona,
			Integer secPregunta, String respuesta) throws SQLException;

}