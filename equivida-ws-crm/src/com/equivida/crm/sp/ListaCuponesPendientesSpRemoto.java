/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaCuponesPendientesRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaCuponesPendientesSpRemoto {

	List<ListaCuponesPendientesRs> listaCuponesPendientesSp(Integer idPersona,
			Integer tipo, String numeroDocumento) throws SQLException;
}