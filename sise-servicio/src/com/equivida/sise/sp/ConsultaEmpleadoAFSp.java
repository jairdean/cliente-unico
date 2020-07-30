package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsConsultaEmpleadoAF;

@Local
public interface ConsultaEmpleadoAFSp {

	List<RsConsultaEmpleadoAF> consultaEmpleadoAFSp(String cedula)
			throws SQLException;
}