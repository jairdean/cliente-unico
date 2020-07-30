/**
 * 
 */
package com.equivida.crm.ws;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.crm.rs.ListaCoberturasRs;

/**
 * @author saviasoft5
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ListaCoberturasWS {

	List<ListaCoberturasRs> sp_lista_coberturas(
			@WebParam(name = "cod_suc") Integer codigoSucursal,
			@WebParam(name = "cod_ramo") Integer ramo,
			@WebParam(name = "nro_pol") BigDecimal numeroPoliza)
			throws SQLException;

}