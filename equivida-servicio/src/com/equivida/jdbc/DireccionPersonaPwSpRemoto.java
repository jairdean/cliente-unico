/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsDireccionPersonaPW;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface DireccionPersonaPwSpRemoto {

	List<RsDireccionPersonaPW> consultaDireccionPersona(Integer secPersona)
			throws SQLException;

}