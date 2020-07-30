/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.dto.portal.RsRespuestaPregunta;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface RespuestaPreguntaPwSp {

	RsRespuestaPregunta respuestaPregunta(Integer secUsuario,
			Integer secPregunta, String estado) throws SQLException;

}