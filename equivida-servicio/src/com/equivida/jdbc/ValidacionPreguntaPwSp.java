/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.dto.portal.RsValidacionPregunta;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ValidacionPreguntaPwSp {

	RsValidacionPregunta validacionPregunta(Integer secUsuario, String estado)
			throws SQLException;

}