package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsComisionPagada;

@Remote
public interface ComisionPagadaSpRemoto {

	List<RsComisionPagada> llamarComsionPagadaSp(Integer tipoAgente,
			Integer codAgente, String fechaDesde, String fechaHasta,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException;
}