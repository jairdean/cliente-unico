package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsConsultaPolizas;

@Local
public interface ConsultaPolizasSp {

	List<RsConsultaPolizas> llamarConsultaPolizasSp(BigDecimal id_persona,
			String num_doc, Integer sn_activas, Integer tipo_busca,
			String campo1, String campo2, String campo3, String campo4,
			String campo5) throws SQLException;
}