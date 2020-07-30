/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.dto.portal.RsDireccionPersonaPW;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface DireccionPersonaPwSp {

	List<RsDireccionPersonaPW> consultaDireccionPersona(Integer secPersona)
			throws SQLException;

}