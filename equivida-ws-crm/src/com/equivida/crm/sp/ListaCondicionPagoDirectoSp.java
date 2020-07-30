/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaCondicionPagoDirectoRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaCondicionPagoDirectoSp {

	List<ListaCondicionPagoDirectoRs> listaCondicionPagoDirectoSp()
			throws SQLException;
}
