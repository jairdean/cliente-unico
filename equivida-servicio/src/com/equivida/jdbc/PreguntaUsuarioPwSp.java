/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.dto.portal.RsPreguntaUsuario;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface PreguntaUsuarioPwSp {

	RsPreguntaUsuario preguntaUsuario(Integer secPersona,
			Integer secPregunta, String respuesta) throws SQLException;

}