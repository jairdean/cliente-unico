package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsDatosAgente;

@Local
public interface DatosAgenteSp {

	List<RsDatosAgente> llamarDatosAgenteSp(String nro_doc,
			String campo1, String campo2, String campo3, String campo4,
			String campo5) throws SQLException;
}