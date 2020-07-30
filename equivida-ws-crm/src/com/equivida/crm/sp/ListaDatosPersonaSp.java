/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaDatosPersonaRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaDatosPersonaSp {

	ListaDatosPersonaRs listaDatosPersonaSp(Integer idPersona, String numeroDocumento)
			throws SQLException;
}
