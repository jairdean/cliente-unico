/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaConductoPagoRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaConductoPagoSpRemoto {

	List<ListaConductoPagoRs> listaConductoPagoSp(String fechaEmision,
			Integer codigoConducto) throws SQLException;
}
