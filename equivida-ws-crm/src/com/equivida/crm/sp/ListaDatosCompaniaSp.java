/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaDatosCompaniaRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaDatosCompaniaSp {

	List<ListaDatosCompaniaRs> listaDatosCompaniaSp(Integer idPersona)
			throws SQLException;
}
