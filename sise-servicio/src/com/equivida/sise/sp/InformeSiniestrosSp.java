package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsInformeSiniestros;

@Local
public interface InformeSiniestrosSp {

	List<RsInformeSiniestros> llamarInformeSiniestrosSp(Integer cod_suc,
			Integer cod_ramo, Integer nro_stro, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException;
}