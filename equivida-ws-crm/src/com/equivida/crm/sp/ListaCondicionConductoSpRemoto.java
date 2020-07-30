/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaCondicionConductoRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaCondicionConductoSpRemoto {

	List<ListaCondicionConductoRs> listaCondicionConductoSp()
			throws SQLException;
}
