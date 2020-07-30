/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaBeneficiariosRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaBeneficiariosSpRemoto {

	List<ListaBeneficiariosRs> listaBeneficiariosSp(Integer codigoSucursal,
			Integer ramo, Long numeroPoliza) throws SQLException;
}