/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaCondicionConductoRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaCondicionConductoBcoEqSp {

	List<ListaCondicionConductoRs> listaCondicionConductoBcoEqSp()
			throws SQLException;
}
