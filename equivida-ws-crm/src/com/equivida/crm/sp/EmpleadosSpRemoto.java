/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.crm.rs.EmpleadosRs;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface EmpleadosSpRemoto {

	List<EmpleadosRs> consultaEmpleadosSp() throws SQLException;
}
