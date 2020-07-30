/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.CompromisosPagosRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface CompromisosPagosSpRemoto {

	List<CompromisosPagosRs> compromisosPagosSp(String poliza, Integer sucursal,Integer ramo) throws SQLException;
}
