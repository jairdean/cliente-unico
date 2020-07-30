/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaDatosPersonaRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaDatosPersonaSpRemoto {

	ListaDatosPersonaRs listaDatosPersonaSp(Integer idPersona, String numeroDocumento)
			throws SQLException;
}
