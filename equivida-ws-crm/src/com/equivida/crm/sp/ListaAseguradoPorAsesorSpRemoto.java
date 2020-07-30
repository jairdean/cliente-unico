/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.ListaAseguradoPorAsesorRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface ListaAseguradoPorAsesorSpRemoto {

	List<ListaAseguradoPorAsesorRs> listaAseguradoPorAsesorSp(Integer asesoresActivos)
			throws SQLException;
}
