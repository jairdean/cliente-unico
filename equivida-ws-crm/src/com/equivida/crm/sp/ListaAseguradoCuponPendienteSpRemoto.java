/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaAseguradoCuponPendienteRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaAseguradoCuponPendienteSpRemoto {

	List<ListaAseguradoCuponPendienteRs> listaAseguradoCuponPendienteSp()
			throws SQLException;
}
