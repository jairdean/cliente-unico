/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaCondicionPagoDirectoRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaCondicionPagoDirectoSpRemoto {

	List<ListaCondicionPagoDirectoRs> listaCondicionPagoDirectoSp()
			throws SQLException;
}
