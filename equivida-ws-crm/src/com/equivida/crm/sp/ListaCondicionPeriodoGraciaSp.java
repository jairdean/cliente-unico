/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaCondicionPeriodoGraciaRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaCondicionPeriodoGraciaSp {

	List<ListaCondicionPeriodoGraciaRs> listaCondicionPeriodoGraciaSp()
			throws SQLException;
}
