/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaPagosRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaPagosSpRemoto {

	List<ListaPagosRs> listaPagosSp(Integer idPersona, Integer tipo,
			String numeroDocumento) throws SQLException;
}