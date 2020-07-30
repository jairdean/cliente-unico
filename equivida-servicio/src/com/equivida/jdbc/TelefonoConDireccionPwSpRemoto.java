/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsTelefonoDireccionPW;


/**
 * @author saviasoft5
 * 
 */
@Remote
public interface TelefonoConDireccionPwSpRemoto {

	List<RsTelefonoDireccionPW> consultaTelefonoDireccion(Integer secPersona)
			throws SQLException;

}