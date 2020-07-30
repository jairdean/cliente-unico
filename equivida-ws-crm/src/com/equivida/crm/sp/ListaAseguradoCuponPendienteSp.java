/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaAseguradoCuponPendienteRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaAseguradoCuponPendienteSp {

	List<ListaAseguradoCuponPendienteRs> listaAseguradoCuponPendienteSp()
			throws SQLException;
}
