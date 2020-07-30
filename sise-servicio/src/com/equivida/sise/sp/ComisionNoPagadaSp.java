package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsComisionNoPagada;

@Local
public interface ComisionNoPagadaSp {

	List<RsComisionNoPagada> llamarComisionNoPagadaSp(Integer cod_tipo_agente,
			Integer cod_agente, String fec_hasta, String fec_desde,String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException;
}