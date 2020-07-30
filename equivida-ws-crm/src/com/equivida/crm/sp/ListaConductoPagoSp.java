/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaConductoPagoRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaConductoPagoSp {

	List<ListaConductoPagoRs> listaConductoPagoSp(String fechaEmision,
			Integer codigoConducto) throws SQLException;
}
