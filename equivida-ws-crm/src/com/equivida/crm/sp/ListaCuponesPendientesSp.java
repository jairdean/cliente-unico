/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaCuponesPendientesRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaCuponesPendientesSp {

	List<ListaCuponesPendientesRs> listaCuponesPendientesSp(Integer idPersona,
			Integer tipo, String numeroDocumento) throws SQLException;
}
