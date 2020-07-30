package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsClientesAgente;

@Local
public interface ClientesAgenteSp {

	List<RsClientesAgente> llamarClientesAgenteSp(String numDoc,
			Integer tipoAgente, Integer codAgente, Integer snActivas,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException;
}