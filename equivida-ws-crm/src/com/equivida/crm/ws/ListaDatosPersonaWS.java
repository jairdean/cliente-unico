/**
 * 
 */
package com.equivida.crm.ws;

import java.sql.SQLException;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.crm.rs.ListaDatosPersonaRs;

/**
 * @author saviasoft5
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ListaDatosPersonaWS {

	ListaDatosPersonaRs sp_lista_datos_persona(
			@WebParam(name = "id_persona") Integer id_persona, String numeroDocumento)
			throws SQLException;

}