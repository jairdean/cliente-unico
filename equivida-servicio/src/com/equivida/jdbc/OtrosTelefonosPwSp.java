/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.dto.portal.RsTelefonoPW;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface OtrosTelefonosPwSp {

	List<RsTelefonoPW> consultaOtrosTelefonos(Integer secPersona)
			throws SQLException;

}