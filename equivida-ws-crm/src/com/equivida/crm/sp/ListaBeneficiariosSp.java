/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaBeneficiariosRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaBeneficiariosSp {

	List<ListaBeneficiariosRs> listaBeneficiariosSp(Integer codigoSucursal,
			Integer ramo, Long numeroPoliza) throws SQLException;
}
