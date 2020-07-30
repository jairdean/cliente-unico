/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsValidacionPregunta;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ValidacionPreguntaPwSpRemoto {

	RsValidacionPregunta validacionPregunta(Integer secUsuario, String estado)
			throws SQLException;

}