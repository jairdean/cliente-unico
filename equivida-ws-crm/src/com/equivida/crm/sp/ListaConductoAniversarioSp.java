/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaConductoAniversarioRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaConductoAniversarioSp {

	List<ListaConductoAniversarioRs> listaConductoAniversarioSp(Integer meses,
			Integer codigoCobertura) throws SQLException;
}
