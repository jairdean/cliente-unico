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

import com.equivida.crm.rs.ListaAseguradoPorAsesorRs;

/**
 * @author saviasoft5
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ListaAseguradoPorAsesorWS {

	List<ListaAseguradoPorAsesorRs> sp_lista_aseg_x_asesor(
			@WebParam(name = "sn_asesores_activos") Integer asesoresActivos)
			throws SQLException;
}