/**
 * 
 */
package com.equivida.crm.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaCoberturasRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaCoberturasSp {

	List<ListaCoberturasRs> listaCoberturasSp(Integer codigoSucursal,
			Integer ramo, BigDecimal numeroPoliza) throws SQLException;
}
