/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.dto.portal.RsTelefonoDireccionPW;


/**
 * @author saviasoft5
 * 
 */
@Local
public interface TelefonoConDireccionPwSp {

	List<RsTelefonoDireccionPW> consultaTelefonoDireccion(Integer secPersona)
			throws SQLException;

}