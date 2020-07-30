/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaCondicionTarjetaExpiradaRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaCondicionTarjetaExpiradaSpRemoto {

	List<ListaCondicionTarjetaExpiradaRs> listaCondicionTarjetaExpiradaSp()
			throws SQLException;
}
