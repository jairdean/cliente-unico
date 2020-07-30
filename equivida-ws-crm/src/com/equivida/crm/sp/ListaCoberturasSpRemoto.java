/**
 * 
 */
package com.equivida.crm.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaCoberturasRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaCoberturasSpRemoto {

	List<ListaCoberturasRs> listaCoberturasSp(Integer codigoSucursal,
			Integer ramo, BigDecimal numeroPoliza) throws SQLException;
}