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

import com.equivida.crm.rs.ListaPagosRs;

/**
 * @author saviasoft5
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ListaPagosWS {

	List<ListaPagosRs> sp_lista_pagos(
			@WebParam(name = "id_persona") Integer idPersona)
			throws SQLException;

}