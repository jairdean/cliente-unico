/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaPolizasRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaPolizasSp {

	List<ListaPolizasRs> listaPolizasSp(Integer idPersona,  Integer tipo, String numeroDocumento) throws SQLException;
}
