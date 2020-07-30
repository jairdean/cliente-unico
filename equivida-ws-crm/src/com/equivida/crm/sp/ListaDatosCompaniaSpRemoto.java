/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaDatosCompaniaRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaDatosCompaniaSpRemoto {

	List<ListaDatosCompaniaRs> listaDatosCompaniaSp(Integer idPersona)
			throws SQLException;
}
