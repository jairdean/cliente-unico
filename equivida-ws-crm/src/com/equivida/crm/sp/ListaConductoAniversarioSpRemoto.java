/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaConductoAniversarioRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaConductoAniversarioSpRemoto {

	List<ListaConductoAniversarioRs> listaConductoAniversarioSp(Integer meses,
			Integer codigoCobertura) throws SQLException;
}
