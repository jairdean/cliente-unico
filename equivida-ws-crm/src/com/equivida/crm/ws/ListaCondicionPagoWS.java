/**
 * 
 */
package com.equivida.crm.ws;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.crm.rs.ListaConductoPagoRs;

/**
 * @author saviasoft5
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ListaCondicionPagoWS {

	List<ListaConductoPagoRs> sp_lista_cond_pago(
			@WebParam(name = "fec_emi") String fechaEmision,
			@WebParam(name = "COD_CONDUCTO") Integer codigoConducto)
			throws SQLException;

}