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

import com.equivida.crm.rs.ListaConductoAniversarioRs;

/**
 * @author saviasoft5
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ListaCondicionAniversarioWS {

	List<ListaConductoAniversarioRs> sp_lista_cond_aniv(
			@WebParam(name = "cnt_meses") Integer meses,
			@WebParam(name = "cod_cobertura") Integer codigoCobertura)
			throws SQLException;

}