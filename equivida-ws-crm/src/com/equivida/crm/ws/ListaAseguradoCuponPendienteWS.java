/**
 * 
 */
package com.equivida.crm.ws;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.crm.rs.ListaAseguradoCuponPendienteRs;

/**
 * @author saviasoft5
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ListaAseguradoCuponPendienteWS {

	List<ListaAseguradoCuponPendienteRs> sp_lista_aseg_cupon_pend()
			throws SQLException;
}