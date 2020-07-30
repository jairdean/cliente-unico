/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.CompromisosPagosRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface CompromisosPagosSp {

	List<CompromisosPagosRs> compromisosPagosSp(String poliza,
			Integer sucursal, Integer ramo) throws SQLException;
}
