/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.ListaAseguradoPorAsesorRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface ListaAseguradoPorAsesorSp {

	List<ListaAseguradoPorAsesorRs> listaAseguradoPorAsesorSp(Integer asesoresActivos)
			throws SQLException;
}
