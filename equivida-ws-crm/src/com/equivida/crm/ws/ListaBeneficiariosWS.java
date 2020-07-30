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

import com.equivida.crm.rs.ListaBeneficiariosRs;

/**
 * @author saviasoft5
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ListaBeneficiariosWS {

	List<ListaBeneficiariosRs> sp_lista_benef_pol(
			@WebParam(name = "cod_suc") Integer codigoSucursal,
			@WebParam(name = "cod_ramo") Integer ramo,
			@WebParam(name = "nro_pol") Long numeroPoliza)
			throws SQLException;

}