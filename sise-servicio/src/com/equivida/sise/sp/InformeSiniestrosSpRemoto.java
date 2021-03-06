package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsInformeSiniestros;

@Remote
public interface InformeSiniestrosSpRemoto {

	List<RsInformeSiniestros> llamarInformeSiniestrosSp(Integer cod_suc,
			Integer cod_ramo, Integer nro_stro, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException;
}