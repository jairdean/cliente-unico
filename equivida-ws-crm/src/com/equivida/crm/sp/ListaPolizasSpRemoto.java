/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaPolizasRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaPolizasSpRemoto {

	List<ListaPolizasRs> listaPolizasSp(Integer idPersona,  Integer tipo, String numeroDocumento) throws SQLException;
}
