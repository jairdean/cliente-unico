/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsRespuestaPregunta;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface RespuestaPreguntaPwSpRemoto {

	RsRespuestaPregunta respuestaPregunta(Integer secUsuario,
			Integer secPregunta, String estado) throws SQLException;

}