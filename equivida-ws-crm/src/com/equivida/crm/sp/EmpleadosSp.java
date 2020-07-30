/**
 * 
 */
package com.equivida.crm.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.crm.rs.EmpleadosRs;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface EmpleadosSp {

	List<EmpleadosRs> consultaEmpleadosSp() throws SQLException;
}
