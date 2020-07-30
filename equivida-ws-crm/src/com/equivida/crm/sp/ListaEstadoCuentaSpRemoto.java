/**
 * 
 */
package com.equivida.crm.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaEstadoCuentaRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaEstadoCuentaSpRemoto {

	List<ListaEstadoCuentaRs> listaEstadoCuentaSp(Integer cod_suc,
			Integer cod_ramo, BigDecimal nro_pol, String fec_desde,
			String fec_hasta) throws SQLException;
}