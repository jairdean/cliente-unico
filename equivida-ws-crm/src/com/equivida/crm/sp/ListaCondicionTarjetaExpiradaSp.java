/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaCondicionTarjetaExpiradaRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaCondicionTarjetaExpiradaSp {

	List<ListaCondicionTarjetaExpiradaRs> listaCondicionTarjetaExpiradaSp() throws SQLException;

	List<ListaCondicionTarjetaExpiradaRs> listaCondicionTarjetaExpiradaSp(int meses, int mes, int anio)
			throws SQLException;
}
