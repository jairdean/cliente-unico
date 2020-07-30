package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsConsultaEmpleadoAF;

@Remote
public interface ConsultaEmpleadoAFSpRemoto {

	List<RsConsultaEmpleadoAF> consultaEmpleadoAFSp(String cedula)
			throws SQLException;
}