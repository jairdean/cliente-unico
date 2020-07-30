/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaCondicionPeriodoGraciaRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaCondicionPeriodoGraciaSpRemoto {

	List<ListaCondicionPeriodoGraciaRs> listaCondicionPeriodoGraciaSp()
			throws SQLException;
}
