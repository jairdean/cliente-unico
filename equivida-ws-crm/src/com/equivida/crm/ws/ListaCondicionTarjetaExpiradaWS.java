/**
 * 
 */
package com.equivida.crm.ws;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.crm.rs.ListaCondicionTarjetaExpiradaRs;

/**
 * @author saviasoft5
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ListaCondicionTarjetaExpiradaWS {

	List<ListaCondicionTarjetaExpiradaRs> sp_lista_cond_tarj_exp()
			throws SQLException;
}