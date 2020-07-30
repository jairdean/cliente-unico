/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsTelefonoPW;


/**
 * @author saviasoft5
 * 
 */
@Remote
public interface OtrosTelefonosPwSpRemoto {

	List<RsTelefonoPW> consultaOtrosTelefonos(Integer secPersona)
			throws SQLException;

}