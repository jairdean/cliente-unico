/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaPagosRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaPagosSp {

	List<ListaPagosRs> listaPagosSp(Integer idPersona, Integer tipo,
			String numeroDocumento) throws SQLException;
}
